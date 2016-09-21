/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;


import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmPhanLoaiThuocDelegate;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B4135_BCnhapxuat1mathang")
@Scope(CONVERSATION)
public class BCnhapxuatmotmathang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB4135=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private Integer mathang_maso=null;
	private Integer khoa_maso=null;
	private Integer hangSX_maso=null;
	private Integer nuocSX_maso=null;
	private Integer nct_maso=null;
	private Integer nkp_maso=null;
	private String mathang_ma="";
	private String khoa_ma="";
	private String hangSX_ma="";
	private String nuocSX_ma="";
	private String nct_ma="";
	private String nkp_ma="";
	private int index=0;
	private String nxSelect=null;
	private String lthuoc_ma=null;
	private String plthuoc_ma=null;
	private String donvi_ma=null;	
	private DmThuocDelegate dmThuocDelegate;
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private DmPhanLoaiThuocDelegate dmPhanLoaiThuocDelegate;
	private String dmtTen="";
	private List<SelectItem> listDmThuocs = new ArrayList<SelectItem>();
	private String dmLoaiTen ="";
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	private String dmPhanLoaiTen ="";
	private List<SelectItem> listDmPhanLoaiThuocs = new ArrayList<SelectItem>();
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	HashMap<String, DmPhanLoaiThuoc> hmPhanLoaiThuoc = new HashMap<String, DmPhanLoaiThuoc>();
	HashMap<String,DmThuoc> hmDmThuoc = new HashMap<String,DmThuoc>();
	
	String dmKhoXuat = "";
	
	
	public String getDmKhoXuat() {
		return dmKhoXuat;
	}
	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	
	
	@Factory("resetFormB4135")
	public void initresetFormB4135() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();	
	}
	@Begin (join = true)
	public String init(String kho) {
		dmKhoXuat = kho;
		return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InChiTietNhapXuatMotMatHang";
	}
	@End
	public void end(){
		
	}
	//Tho add
	public String getDmLoaiTen() {
		return dmLoaiTen;
	}

	public void setDmLoaiTen(String dmLoaiTen) {
		this.dmLoaiTen = dmLoaiTen;
	}

	public List<SelectItem> getListDmLoaiThuocs() {
		return listDmLoaiThuocs;
	}

	public void setListDmLoaiThuocs(List<SelectItem> listDmLoaiThuocs) {
		this.listDmLoaiThuocs = listDmLoaiThuocs;
	}
	
	public String getDmPhanLoaiTen() {
		return dmPhanLoaiTen;
	}

	public void setDmPhanLoaiTen(String dmPhanLoaiTen) {
		this.dmPhanLoaiTen = dmPhanLoaiTen;
	}

	public List<SelectItem> getListDmPhanLoaiThuocs() {
		return listDmPhanLoaiThuocs;
	}

	public void setListDmPhanLoaiThuocs(List<SelectItem> listDmPhanLoaiThuocs) {
		this.listDmPhanLoaiThuocs = listDmPhanLoaiThuocs;
	}
	
	public void onblurMaLoaiAction(){
		log.info("-----BEGIN onblurMaLoaiAction()-----"+lthuoc_ma);
		DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)hmLoaiThuoc.get(lthuoc_ma.toUpperCase());
		if(dmLoaiThuoc != null) {
			setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
		}
		else {
			setDmLoaiTen("");			
		}
		setDonvi_ma("");
	    setPlthuoc_ma("");
	    setDmPhanLoaiTen("");
		refreshDmPhanLoaiThuoc();
		refreshDmThuoc();
		log.info("-----END onblurMaLoaiAction()-----");
	}
	
	public void onblurTenLoaiThuocAction(){
		log.info("-----BEGIN onblurTenLoaiThuocAction()-----");
		Boolean hasTenLoai = false;
		String maLoai = null;
		if (dmLoaiTen == ""){
			setLthuoc_ma("");
			setDonvi_ma("");
		}
		java.util.Set set = hmLoaiThuoc.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
	    	if(dmLoaiThuoc.getDmloaithuocTen() == dmLoaiTen || dmLoaiThuoc.getDmloaithuocTen().equals(dmLoaiTen)){
	    		hasTenLoai = true;
	    		maLoai = dmLoaiThuoc.getDmloaithuocMa();
	    		break;
	    	}	    		
	    }	
	    if(hasTenLoai){
	    	setLthuoc_ma(maLoai);
	    }else{
    		setLthuoc_ma("");
	    }
	    setDonvi_ma("");
	    setPlthuoc_ma("");
	    setDmPhanLoaiTen("");
	    refreshDmPhanLoaiThuoc();
	    refreshDmThuoc();
		log.info("-----END onblurTenLoaiThuocAction()-----");
	}
	
	public void refreshDmLoaiThuoc(){
		listDmLoaiThuocs.clear();
		dmLoaiThuocDelegate = DmLoaiThuocDelegate.getInstance();
		hmLoaiThuoc.clear();
		hmLoaiThuoc = dmLoaiThuocDelegate.findAllDm();
		if( hmLoaiThuoc != null ){
			java.util.Set set = hmLoaiThuoc.entrySet();
		    Iterator i = set.iterator();
		    listDmLoaiThuocs.add(new SelectItem("All"));
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
		    	listDmLoaiThuocs.add(new SelectItem(dmLoaiThuoc.getDmloaithuocTen()));
		    }
		}
	}
	
	public void onblurMaPhanLoaiAction(){
		log.info("-----BEGIN onblurMaPhanLoaiAction()-----");
		DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)hmPhanLoaiThuoc.get(plthuoc_ma.toUpperCase());
		if(dmPhanLoaiThuoc != null) {
			setDmPhanLoaiTen(dmPhanLoaiThuoc.getDmphanloaithuocTen());
		}
		else {
			setDmPhanLoaiTen("");
			setPlthuoc_ma("");
		}
		setDonvi_ma("");
		refreshDmThuoc();
		log.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenPhanLoaiThuocAction(){
		log.info("-----BEGIN onblurTenPhanLoaiThuocAction()-----");
		Boolean hasTenLoai = false;
		String maPhanLoai = null;
		if (dmPhanLoaiTen == ""){
			setPlthuoc_ma("");
		}
		java.util.Set set = hmPhanLoaiThuoc.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)me.getValue();
	    	if(dmPhanLoaiThuoc.getDmphanloaithuocTen() == dmPhanLoaiTen || dmPhanLoaiThuoc.getDmphanloaithuocTen().equals(dmPhanLoaiTen)){
	    		hasTenLoai = true;
	    		maPhanLoai = dmPhanLoaiThuoc.getDmphanloaithuocMa();
	    		break;
	    	}	    		
	    }	
	    if(hasTenLoai){
	    	setPlthuoc_ma(maPhanLoai);
	    }else{
	    	setPlthuoc_ma("");
	    }
	    setDonvi_ma("");
		refreshDmThuoc();
		log.info("-----END onblurTenThuocAction()-----");
	}

	public void refreshDmPhanLoaiThuoc(){
		listDmPhanLoaiThuocs.clear();		
		listDmPhanLoaiThuocs.add(new SelectItem("All"));
		dmPhanLoaiThuocDelegate = DmPhanLoaiThuocDelegate.getInstance();
		hmPhanLoaiThuoc.clear();
		if( !lthuoc_ma.equals("") ){
			List<DmPhanLoaiThuoc> lstDmPLThuoc = dmPhanLoaiThuocDelegate.findByDtdmloaiMa(lthuoc_ma);
			if(lstDmPLThuoc != null && lstDmPLThuoc.size()>0){
				for(DmPhanLoaiThuoc o: lstDmPLThuoc){
					listDmPhanLoaiThuocs.add(new SelectItem(o.getDmphanloaithuocTen()));
					hmPhanLoaiThuoc.put(o.getDmphanloaithuocMa(), o);
				}
			}
		}
	}
	//End - Tho add
	/** ==================== BEGIN LY THEM CODE - Tho edit again==================== */	
	
	
	public String getDmtTen() {
		return dmtTen;
	}

	public void setDmtTen(String dmtTen) {
		this.dmtTen = dmtTen;
	}

	public List<SelectItem> getListDmThuocs() {
		return listDmThuocs;
	}

	public void setListDmThuocs(List<SelectItem> listDmThuocs) {
		this.listDmThuocs = listDmThuocs;
	}
	
	public void onblurMaThuocAction(){
		log.info("-----BEGIN onblurMaThuocAction()-----");
		DmThuoc dmThuoc = hmDmThuoc.get(mathang_ma.toUpperCase());
		if(dmThuoc != null) {
			setDmtTen(dmThuoc.getDmthuocTen());
			setDonvi_ma(dmThuoc.getDmdonvitinhMaso().getDmdonvitinhMa());
			log.info("-----DA THAY DMTHUOC-----");
		}
		else {
			setDmtTen("");
			setDonvi_ma("");
		}
		log.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenThuocAction(){
		log.info("-----BEGIN onblurTenThuocAction()-----"+dmtTen);
		Boolean hasTenThuoc = false;
		String maThuoc = null;
		String maDv = null;
		if( hmDmThuoc != null ){
			java.util.Set set = hmDmThuoc.entrySet();
		    Iterator i = set.iterator();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmThuoc dmThuoc = (DmThuoc)me.getValue();
		    	if(dmThuoc.getDmthuocTen().trim() == dmtTen || dmThuoc.getDmthuocTen().trim().equals(dmtTen)){
		    		hasTenThuoc = true;
		    		maThuoc = dmThuoc.getDmthuocMa();
		    		maDv = dmThuoc.getDmdonvitinhMaso().getDmdonvitinhMa();
		    		break;
		    	}	    		
		    }
	    }
	    if(hasTenThuoc){
	    	setMathang_ma(maThuoc);
			setDonvi_ma(maDv);
			log.info("-----DA THAY DMTHUOC-----");
	    }else{
	    	setMathang_ma("");
			setDonvi_ma("");
	    }		
		log.info("-----END onblurTenThuocAction()-----");
	}
	//Tho edit again
	public void refreshDmThuoc(){
		listDmThuocs.clear();
		hmDmThuoc.clear();
		dmThuocDelegate = DmThuocDelegate.getInstance();
		List<DmThuoc> lstDmThuoc = dmThuocDelegate.findByLoaiPhanLoaiThuoc(lthuoc_ma, plthuoc_ma);	
		if(lstDmThuoc !=null && lstDmThuoc.size()>0)
		{
			for(DmThuoc o: lstDmThuoc){
				listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
				hmDmThuoc.put(o.getDmthuocMa(), o);
			}
		}
	}

/** ==================== END LY THEM CODE ==================== */
	public String thuchienAction(){
		XuatReport();
		resetFormB4135=null;
		return "B4160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		setNxSelect("r1");
		resetFormB4135 = "";
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		mathang_maso=null;
		hangSX_maso=null;
		nuocSX_maso=null;
		nct_maso=null;
		nkp_maso=null;
		mathang_ma=null;
		dmtTen = "";
		hangSX_ma="";
		nuocSX_ma="";
		nct_ma="";
		nkp_ma="";
		lthuoc_ma="";
		plthuoc_ma="";
		donvi_ma="";
		refreshDmLoaiThuoc();
		refreshDmPhanLoaiThuoc();
		refreshDmThuoc();
	}	
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="BCnhapxuat1mathang";
		log.info("Vao Method XuatReport nhap xuat 1 mat hang");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			System.out.println("Kho xuat "+ dmKhoXuat);
			if(nxSelect.equals("r1"))
				// xuat hang cho bn bhyt nen dung phieu xuat bh + phieu xuat
				if ("BHYT".equals(dmKhoXuat) || "TE".equals(dmKhoXuat) || "NT".equals(dmKhoXuat)){
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D08_tinhhinhxuat_khole.jrxml";
					System.out.println("KNT, TE, BHYT");
				}else{//Kho chinh
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D08_tinhhinhxuat_kc.jrxml";					
				}
			else if(nxSelect.equals("r2"))
			{
				if ("BHYT".equals(dmKhoXuat) || "TE".equals(dmKhoXuat) || "NT".equals(dmKhoXuat)){
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D08_tinhhinhnhap_khole.jrxml";
				}else{//Kho chinh
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D08_tinhhinhnhap_kc.jrxml";					
				}
			}
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			DieuTriUtilDelegate dtUtils=DieuTriUtilDelegate.getInstance();
			SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
			
			String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;		
			Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(strInputTonDauDate));
			c.add(Calendar.DATE, 1);
			strInputTonDauDate = sdf.format(c.getTime());
			Date dNgayTonDau = sdf.parse(strInputTonDauDate);
			Date dTuNgay = sdf.parse(tungay);
			if(dTuNgay.before(dNgayTonCuoi) || dTuNgay.equals(dNgayTonCuoi))
				params.put("TUNGAY", dNgayTonDau);
			else
				params.put("TUNGAY", dTuNgay);
			
			params.put("DENNGAY", sdf.parse(denngay));
			// DuyenLP kiem tra ma thuoc va co tim thay khong?
			if(!mathang_ma.trim().equals("") || mathang_ma.trim()!= null){
				DmThuoc dmthuoc = new DmThuoc();
				dmthuoc = dmThuocDelegate.findByMaThuoc(mathang_ma.toUpperCase());
				if(dmthuoc != null){
					mathang_maso = dmthuoc.getDmthuocMaso();
				
	//				Object objThuoc= dtUtils.findByMa(mathang_ma ,"DmThuoc" , "dmthuocMa");
	//				dmthuoc = (DmThuoc)objThuoc;
					params.put("DONVITINH", dmthuoc.getDmdonvitinhMaso().getDmdonvitinhTen());
					params.put("PHANLOAIHANG", dmthuoc.getDmphanloaithuocMaso().getDmphanloaithuocTen());
					params.put("THUOCMASO", mathang_maso);
					params.put("MAHANG", dmthuoc.getDmthuocMa());
					params.put("TENHANG", dmthuoc.getDmthuocTen());
					log.info("set dmthuoc.getDmdonvitinhMaso().getDmdonvitinhTen()" + dmthuoc.getDmdonvitinhMaso().getDmdonvitinhTen());
				}
			}else{
				mathang_maso = null;
				params.put("THUOCMASO", mathang_maso);
			}
			
			log.info("set gia tri cho nguon kinh phi");
			if(!nkp_ma.trim().equals("")){
				DmNguonKinhPhi dmnkp = new DmNguonKinhPhi();
				Object objdmnkp = dtUtils.findByMa(nkp_ma , "DmNguonKinhPhi", "dmnguonkinhphiMa");
				dmnkp = (DmNguonKinhPhi)objdmnkp;
				params.put("NKPMASO", nkp_maso);
			}else{
				nkp_maso =null;
				params.put("NKPMASO", nkp_maso);
			}
			
			log.info("set gia tri nuoc san xuat");
			if(!nuocSX_ma.trim().equals("")){
				DmQuocGia dmqg = new DmQuocGia();
				Object objdmqg = dtUtils.findByMa(nuocSX_ma , "DmQuocGia", "dmquocgiaMa");
				dmqg = (DmQuocGia)objdmqg;
				params.put("NUOCSX", dmqg.getDmquocgiaTen());
				params.put("QGMASO", nuocSX_maso );
			}else{
				nuocSX_maso = null;
				params.put("QGMASO", nuocSX_maso);
			}
			log.info("set gia tri hang san xuat");
			if(!hangSX_ma.trim().equals("")){
				DmNhaSanXuat dmnsx = new DmNhaSanXuat();
				Object objdmnsx = dtUtils.findByMa(hangSX_ma , "DmNhaSanXuat", "dmnhasanxuatMa");
				dmnsx = (DmNhaSanXuat)objdmnsx;
				params.put("NSXMASO", hangSX_maso);
			}else{
				hangSX_maso = null;
				params.put("NSXMASO", hangSX_maso);
			}
			log.info("set gia tri cho nguon chuong trinh");
			if(!nct_ma.trim().equals("")){
				DmNguonChuongTrinh dmnct = new DmNguonChuongTrinh();
				Object objdmnct = dtUtils.findByMa(nct_ma , "DmNguonChuongTrinh", "dmnctMa");
				dmnct = (DmNguonChuongTrinh)objdmnct;
				params.put("NGUON", dmnct.getDmnctTen());
				params.put("NCTMASO", nct_maso);
			}else{
				nct_maso = null;
				params.put("NCTMASO", nct_maso);
			}
			if(!khoa_ma.trim().equals("")){
				DmKhoa dmkhoa = new DmKhoa();
				Object objdmkhoa = dtUtils.findByMa(khoa_ma , "DmKhoa", "dmkhoaMa");
				dmkhoa = (DmKhoa)objdmkhoa;
				params.put("KHOXUAT", dmkhoa.getDmkhoaTen());
				params.put("KHOMASO", dmkhoa.getDmkhoaMaso());
			}else{
				khoa_maso = null;
				params.put("KHOMASO", khoa_maso);
			}
			
			log.info("================= ");
			Class.forName(IConstantsRes.ORACLE_DRIVER);
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","BCnhapxuat1mathang");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	
	public String getThoigian_thang() {
		return thoigian_thang;
	}
	public void setThoigian_thang(String thoigian_thang) {
		this.thoigian_thang = thoigian_thang;
	}
	public String getThoigian_nam() {
		return thoigian_nam;
	}
	public void setThoigian_nam(String thoigian_nam) {
		this.thoigian_nam = thoigian_nam;
	}
	public String getTungay() {
		return tungay;
	}
	public void setTungay(String tungay) {
		this.tungay = tungay;
	}
	public String getDenngay() {
		return denngay;
	}
	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public Integer getMathang_maso() {
		return mathang_maso;
	}

	public void setMathang_maso(Integer mathang_maso) {
		this.mathang_maso = mathang_maso;
	}

	public Integer getKhoa_maso() {
		return khoa_maso;
	}

	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}

	public Integer getHangSX_maso() {
		return hangSX_maso;
	}

	public void setHangSX_maso(Integer hangSX_maso) {
		this.hangSX_maso = hangSX_maso;
	}

	public Integer getNuocSX_maso() {
		return nuocSX_maso;
	}

	public void setNuocSX_maso(Integer nuocSX_maso) {
		this.nuocSX_maso = nuocSX_maso;
	}

	public Integer getNct_maso() {
		return nct_maso;
	}

	public void setNct_maso(Integer nct_maso) {
		this.nct_maso = nct_maso;
	}

	public Integer getNkp_maso() {
		return nkp_maso;
	}

	public void setNkp_maso(Integer nkp_maso) {
		this.nkp_maso = nkp_maso;
	}

	public String getMathang_ma() {
		return mathang_ma;
	}

	public void setMathang_ma(String mathang_ma) {
		this.mathang_ma = mathang_ma;
	}

	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}

	public String getHangSX_ma() {
		return hangSX_ma;
	}

	public void setHangSX_ma(String hangSX_ma) {
		this.hangSX_ma = hangSX_ma;
	}

	public String getNuocSX_ma() {
		return nuocSX_ma;
	}

	public void setNuocSX_ma(String nuocSX_ma) {
		this.nuocSX_ma = nuocSX_ma;
	}

	public String getNct_ma() {
		return nct_ma;
	}	
	

	public void setNct_ma(String nct_ma) {
		this.nct_ma = nct_ma;
	}

	public String getNkp_ma() {
		return nkp_ma;
	}

	public void setNkp_ma(String nkp_ma) {
		this.nkp_ma = nkp_ma;
	}

	public void setNxSelect(String nxSelect) {
		this.nxSelect = nxSelect;
	}

	public String getNxSelect() {
		return nxSelect;
	}

	public String getLthuoc_ma() {
		return lthuoc_ma;
	}

	public void setLthuoc_ma(String lthuoc_ma) {
		this.lthuoc_ma = lthuoc_ma;
	}	

	public String getPlthuoc_ma() {
		return plthuoc_ma;
	}

	public void setPlthuoc_ma(String plthuoc_ma) {
		this.plthuoc_ma = plthuoc_ma;
	}

	public String getDonvi_ma() {
		return donvi_ma;
	}

	public void setDonvi_ma(String donvi_ma) {
		this.donvi_ma = donvi_ma;
	}

}
