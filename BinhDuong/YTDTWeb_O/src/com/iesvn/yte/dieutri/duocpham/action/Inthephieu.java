/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.entity.TmpBaocaoThekho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B4133_inthephieu")
@Scope(CONVERSATION)
public class Inthephieu implements Serializable {
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
	private String resetFormB4133=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private int index=0;
	
	
	private String tonkhomalk;
	private String tonkhoma;
	private Integer thuocmaso;
	
	// di tu menu den : KC hoac KL
	String dmKhoXuat = "";
	
	
	private String khoma ="";
	private Integer khomaso;
	
	//Tho add
	private Integer loaihang_maso;
	private String loaihang_ma=null;
	private DmLoaiThuocDelegate dmLoaiThuocDelegate;
	private String dmLoaiTen ="";
	private List<SelectItem> listDmLoaiThuocs = new ArrayList<SelectItem>();
	HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap<String, DmLoaiThuoc>();
	
	private DmThuocDelegate dmThuocDelegate;
	HashMap<String,DmThuoc> hmDmThuoc = new HashMap<String,DmThuoc>();
	private String dmtTen="";
	private List<SelectItem> listDmThuocs = new ArrayList<SelectItem>();
	private String hang_ma=null;
	
	private List<SelectItem> listDmTonThuocs = new ArrayList<SelectItem>();
	private String dmTonThuoc="";	
	
	public Integer getKhomaso() {
		return khomaso;
	}
	public void setKhomaso(Integer khomaso) {
		this.khomaso = khomaso;
	}
	public String getDmKhoXuat() {
		return dmKhoXuat;
	}
	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	
	
	@Factory("resetFormB4133")
	public void initresetFormB4133() {
		log.info("init()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		resetFormB4133 = "";
		ngayhientai = Utils.getCurrentDate();
		Date currentDate = new Date();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		tonkhomalk = "";
		tonkhoma = "";
		hang_ma="";
		dmtTen="";
		loaihang_ma="";
		dmLoaiTen="";
		FacesMessages.instance().clear();
		listDmThuocs.clear();
		listDmLoaiThuocs.clear();
		hmLoaiThuoc.clear();
		hmDmThuoc.clear();
		refreshDmLoaiThuoc();
		refreshDmThuoc();
	}
	
	@Begin (join = true)
	public String init(String kho) {
		initresetFormB4133();
		dmKhoXuat = kho;		
		return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InTheKho";
	}
	@End
	public void end(){
		
	}
	
	public String thuchienAction(){
		if (XuatReport())
			return "B4160_Chonmenuxuattaptin";
		else
		{
			initresetFormB4133();
			FacesMessages.instance().add(IConstantsRes.DuocPham_TruyXuatTTKho_InTheKho);
			return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InTheKho";
		}
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
	
	}
	
	//Tho add
	public Integer getLoaihang_maso() {
		return loaihang_maso;
	}

	public void setLoaihang_maso(Integer loaihang_maso) {
		this.loaihang_maso = loaihang_maso;
	}
	
	public String getLoaihang_ma() {
		return loaihang_ma;
	}

	public void setLoaihang_ma(String loaihang_ma) {
		this.loaihang_ma = loaihang_ma;
	}
	
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
	
	public void onblurMaLoaiAction(){
		log.info("-----BEGIN onblurMaLoaiAction()-----"+loaihang_ma);
		DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)hmLoaiThuoc.get(loaihang_ma.toUpperCase());
		if(dmLoaiThuoc != null) {
			setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
		}
		else {
			setDmLoaiTen("");			
		}
		refreshDmThuoc();
		log.info("-----END onblurMaLoaiAction()-----");
	}
	
	public void onblurTenLoaiThuocAction(){
		log.info("-----BEGIN onblurTenLoaiThuocAction()-----");
		Boolean hasTenLoai = false;
		String maLoai = null;
		if (dmLoaiTen == ""){
			setLoaihang_ma("");
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
	    	setLoaihang_ma(maLoai);
	    }else{
	    	setLoaihang_ma("");
	    }
	    setHang_ma("");
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
		DmThuoc dmThuoc = hmDmThuoc.get(hang_ma.toUpperCase());
		if(dmThuoc != null) {
			setDmtTen(dmThuoc.getDmthuocTen());
			setThuocmaso(dmThuoc.getDmthuocMaso());
			log.info("-----DA THAY DMTHUOC-----");
		}
		else {
			setDmtTen("");
			setThuocmaso(null);
		}
		log.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenThuocAction(){
		log.info("-----BEGIN onblurTenThuocAction()-----"+dmtTen);
		Boolean hasTenThuoc = false;
		String maThuoc = null;
		Integer masoThuoc = null;
		if( hmDmThuoc != null ){
			java.util.Set set = hmDmThuoc.entrySet();
		    Iterator i = set.iterator();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmThuoc dmThuoc = (DmThuoc)me.getValue();
		    	if(dmThuoc.getDmthuocTen().trim() == dmtTen.trim() || dmThuoc.getDmthuocTen().trim().equals(dmtTen.trim())){
		    		hasTenThuoc = true;
		    		maThuoc = dmThuoc.getDmthuocMa();
		    		masoThuoc =dmThuoc.getDmthuocMaso();
		    		break;
		    	}	    		
		    }
	    }
	    if(hasTenThuoc){
	    	setHang_ma(maThuoc);
	    	setThuocmaso(masoThuoc);
			log.info("-----DA THAY DMTHUOC-----");
	    }else{
	    	setHang_ma("");
	    	setThuocmaso(null);
	    }		
		log.info("-----END onblurTenThuocAction()-----");
	}

	public void refreshDmThuoc(){
		listDmThuocs.clear();
		hmDmThuoc.clear();
		dmThuocDelegate = DmThuocDelegate.getInstance();
		List<DmThuoc> lstDmThuoc = dmThuocDelegate.findByLoaiPhanLoaiThuoc(loaihang_ma,"");	
		if(lstDmThuoc !=null && lstDmThuoc.size()>0)
		{
			for(DmThuoc o: lstDmThuoc){
				listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
				hmDmThuoc.put(o.getDmthuocMa(), o);
			}
		}
	}
	
	public String getHang_ma() {
		return hang_ma;
	}

	public void setHang_ma(String hang_ma) {
		this.hang_ma = hang_ma;
	}
	
	/**
	 * xuat report 
	 */
	public boolean XuatReport() {
		
		if (tonkhoma ==null || tonkhoma.equals("")){
			//FacesMessages.instance().add(IConstantsRes.SOBENHAN_NOTFOUND);
			return false;
		}
		reportTypeKC="Inthephieu";
		log.info("Vao Method XuatReport in the kho");
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			String pathTemplate = "";
			String loaiIn = "";
			if (IConstantsRes.KHOA_KC_MA.equals(khoma)){
				loaiIn = "KC";
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D06_sochitietvatlieudungcusanphamhanghoa_kc.jrxml";
			}else{
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D06_sochitietvatlieudungcusanphamhanghoa.jrxml";
				params.put("khomaso", khomaso);	
				loaiIn = "KL";
			}
				
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file templete 5" + pathTemplate);		
			
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			
			TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
			TonKho tonKho = tkDelegate.find(new Integer( tonkhoma));
			if (tonKho == null){
				return false;
			}
			
			DieuTriUtilDelegate dtUtilsDeledate = DieuTriUtilDelegate.getInstance();
			DmKhoa khoa = (DmKhoa)dtUtilsDeledate.findByMa(khoma, "DmKhoa", "dmkhoaMa");
			
			
			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
			
			SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
			params.put("tenkho", khoa.getDmkhoaTen());
			
			Date dTuNgay = sdf.parse(tungay) ;
			Date dDenNgay = sdf.parse(denngay) ;
			
			params.put("nam",String.valueOf(dTuNgay.getYear()));
			
			
			params.put("tenhang", tonKho.getDmthuocMaso(true).getDmthuocTen());
			params.put("donvitinh", tonKho.getDmthuocMaso(true).getDmdonvitinhMaso(true).getDmdonvitinhTen());
			params.put("phanloai", tonKho.getDmthuocMaso(true).getDmphanloaithuocMaso(true).getDmphanloaithuocTen());
			params.put("nguonchuongtrinh", tonKho.getDmnctMaso(true).getDmnctTen());
			params.put("nguonkinhphi", tonKho.getDmnguonkinhphiMaso(true).getDmnguonkinhphiTen());
			params.put("losanxuat",tonKho.getTonkhoLo() );
			params.put("kyhieu", tonKho.getDmthuocMaso(true).getDmthuocMa());
			
			params.put("nuocsanxuat", tonKho.getDmquocgiaMaso(true).getDmquocgiaTen());
			params.put("namnhap", tonKho.getTonkhoNamnhap());
			params.put("dongia", tonKho.getTonkhoDongia());
			
			////////////////////////////////////////////////////
			String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;		
			Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(strInputTonDauDate));
			c.add(Calendar.DATE, 1);
			strInputTonDauDate = sdf.format(c.getTime());
			Date dNgayTonDau = sdf.parse(strInputTonDauDate);
			Double tonkhoDau =0.0;
			List<TmpBaocaoThekho> dataTheKho = new ArrayList<TmpBaocaoThekho>();
			DieuTriUtilDelegate dtriUtilDel = DieuTriUtilDelegate.getInstance();
			if(dTuNgay.before(dNgayTonCuoi) || dTuNgay.equals(dNgayTonCuoi)){
				params.put("tungay", dNgayTonDau);
				tonkhoDau = tkDelegate.findByTonkhoDauMalienket(tonkhomalk, khoa.getDmkhoaMaso(), dNgayTonDau, dDenNgay);
				Double tonDau = 0.0;
				if (tonkhoDau != null){
					tonDau = tonkhoDau;
				}
				//Cap nhat bang Tmp_Baocao_thekho				
				dtriUtilDel.exportDataForTheKho(khoa.getDmkhoaMaso(), dNgayTonDau, dDenNgay, tonkhomalk, tonDau, tonKho.getTonkhoDongia(), loaiIn);
			}else{
				params.put("tungay", dTuNgay);
				tonkhoDau = tkDelegate.findByTonkhoDauMalienket(tonkhomalk, khoa.getDmkhoaMaso(), dTuNgay, dDenNgay);
				Double tonDau = 0.0;
				if (tonkhoDau != null){
					tonDau = tonkhoDau;
				}
				//Cap nhat bang Tmp_Baocao_thekho				
				dtriUtilDel.exportDataForTheKho(khoa.getDmkhoaMaso(), dTuNgay, dDenNgay, tonkhomalk, tonDau, tonKho.getTonkhoDongia(), loaiIn);
			}
			params.put("denngay", dDenNgay);
			if (tonkhoDau != null){
				params.put("tonkhobandau", tonkhoDau);
			}else{
				params.put("tonkhobandau", 0);
			}
			
			params.put("malienket", tonkhomalk);
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Inthekho");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
			return false;
		}
	    log.info("Thoat Method XuatReport");
	    return true;
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
	public String getTonkhomalk() {
		return tonkhomalk;
	}
	public void setTonkhomalk(String tonkhomalk) {
		this.tonkhomalk = tonkhomalk;
	}
	public String getTonkhoma() {
		return tonkhoma;
	}
	public void setTonkhoma(String tonkhoma) {
		this.tonkhoma = tonkhoma;
	}
	public String getKhoma() {
		return khoma;
	}
	public void setKhoma(String khoma) {
		this.khoma = khoma;
	}
	public Integer getThuocmaso() {
		return thuocmaso;
	}
	public void setThuocmaso(Integer thuocmaso) {
		this.thuocmaso = thuocmaso;
	}
}
