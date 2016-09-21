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
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B4415_Inbctiensudungtaikhoa")
@Scope(CONVERSATION)
public class Inbctiensudungtaikhoa implements Serializable {
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
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private static int index=0;
	
	private Integer loaiHang;
	private Integer phanLoai;
	private Integer khoaXuat;
	private Integer khoaNhan;
	
	private String khoaXuatMa;
	private String khoaNhanMa;
	
	String dmKhoXuat = "";
	
	//Tho add	
	private String dmTenKho ="";
	private DmKhoaDelegate dmKhoaDelegate;
	private List<SelectItem> listDmKhos = new ArrayList<SelectItem>();
	private HashMap hmDmKho = new HashMap();
	
	private Integer doituongMaso;
	private String doituong;
	
	public String getDmKhoXuat() {
		return dmKhoXuat;
	}

	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}

	@Begin(join = true)
	public String init(String kho) {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		dmKhoXuat = kho;		
		refreshDmKhoXuat();
		resetForm();
		return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoTienSuDungTaiKhoa";
	}
	
	@End
	public void endFunc(){
		
	}
	
	public String thuchienAction(){
		XuatReport();
		resetForm();
		return "B4160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		ngayhientai = Utils.getCurrentDate();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);	
		khoaXuatMa = "";
		khoaXuat = null;
		dmTenKho = "";
		khoaNhan = null;
		khoaNhanMa = "";
		doituong="";
		doituongMaso=null;
	}
	
	//Begin Tho add
	public String getDmTenKho() {
		return dmTenKho;
	}

	public void setDmTenKho(String dmTenKho) {
		this.dmTenKho = dmTenKho;
	}

	public List<SelectItem> getListDmKhos() {
		return listDmKhos;
	}

	public void setListDmKhos(List<SelectItem> listDmKhos) {
		this.listDmKhos = listDmKhos;
	}
	
	public void onblurMaKhoAction(){
		log.info("-----BEGIN onblurMaKhoAction()-----");
		DmKhoa dmKhoa = (DmKhoa)hmDmKho.get(khoaXuatMa.toUpperCase());
		if(dmKhoa != null) {
			setDmTenKho(dmKhoa.getDmkhoaTen());
			setKhoaXuat(dmKhoa.getDmkhoaMaso());
		}
		else {
			setDmTenKho("");	
			setKhoaXuat(null);
		}
		log.info("-----END onblurMaLoaiAction()-----");
	}
	
	public void onblurTenKhoAction(){
		log.info("-----BEGIN onblurTenKhoAction()-----");	
		DmKhoa dmKhoaFinded = new DmKhoa();
		boolean havingData = false;
		java.util.Set set = hmDmKho.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()){
		    Map.Entry me = (Map.Entry)i.next();
		    DmKhoa dmKhoa = (DmKhoa)me.getValue();
		    if(dmKhoa.getDmkhoaTen() == dmTenKho || dmKhoa.getDmkhoaTen().equals(dmTenKho)){
		    	havingData = true;
		    	dmKhoaFinded = dmKhoa;
		    	break;
		    }	    		
		}
		if(havingData){
			setKhoaXuatMa(dmKhoaFinded.getDmkhoaMa());
	    	setKhoaXuat(dmKhoaFinded.getDmkhoaMaso());
		} else {
			setKhoaXuatMa("");
	    	setKhoaXuat(null);
		}
		log.info("-----END onblurTenKhoAction()-----");
	}
	
	public void refreshDmKhoXuat(){
		listDmKhos.clear();
		hmDmKho.clear();
		dmKhoaDelegate = DmKhoaDelegate.getInstance();
		List<DmKhoa> lstDmKhoa = dmKhoaDelegate.getKhoChinh_KhoLe();
		listDmKhos.add(new SelectItem("T\u1EE7 tr\u1EF1c"));
		if(lstDmKhoa !=null && lstDmKhoa.size()>0)
		{
			for(DmKhoa o: lstDmKhoa){
				listDmKhos.add(new SelectItem(o.getDmkhoaTen()));
				hmDmKho.put(o.getDmkhoaMa(), o);
			}
		}
		lstDmKhoa.clear();
	}
	//End Tho add
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="Inbctiensudungtaikhoa";
		log.info("Vao Method XuatReport in bao cao tien su dung tai khoa");
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D21_solieusudungtaikhoa.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("DENNGAYDATE", sdf.parse(denngay));
			
			String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;		
			Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(strInputTonDauDate));
			c.add(Calendar.DATE, 1);
			strInputTonDauDate = sdf.format(c.getTime());
			Date dNgayTonDau = sdf.parse(strInputTonDauDate);
			Date dTuNgay = sdf.parse(tungay);
			if(dTuNgay.before(dNgayTonCuoi) || dTuNgay.equals(dNgayTonCuoi)){
				params.put("TUNGAYDATE", dNgayTonDau);
			}else{
				params.put("TUNGAYDATE", dTuNgay);
			}		
			
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			log.info("loaiHang:"+loaiHang);
			log.info("phanLoai:"+phanLoai);
			
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			if(loaiHang != null){				
				DmLoaiThuoc dmloaithuoc = new DmLoaiThuoc();
				Object objLThuoc= dtUtil.findByMaSo(loaiHang ,"DmLoaiThuoc" , "dmloaithuocMaso");
				dmloaithuoc = (DmLoaiThuoc)objLThuoc;
				params.put("LOAITHUOC", dmloaithuoc.getDmloaithuocTen());
				params.put("LOAITHUOCMASO", loaiHang);
			}else{
				params.put("LOAITHUOC", "T\u1EA5t c\u1EA3");
				params.put("LOAITHUOCMASO", null);
			}	
			
			if(phanLoai != null){				
				DmPhanLoaiThuoc dmplthuoc = new DmPhanLoaiThuoc();
				Object objLThuoc= dtUtil.findByMaSo(phanLoai ,"DmPhanLoaiThuoc" , "dmphanloaithuocMaso");
				dmplthuoc = (DmPhanLoaiThuoc)objLThuoc;
				params.put("PHANLOAI", dmplthuoc.getDmphanloaithuocTen());
				params.put("PLTHUOCMA", phanLoai);
			}else{
				params.put("PHANLOAI", "T\u1EA5t c\u1EA3");
				params.put("PLTHUOCMA", null);
			}
			String khoaNhanTen = "";
			if (khoaNhanMa != null && !khoaNhanMa.equals("")){
				DmKhoa khoanhan_temp = (DmKhoa)dtUtil.findByMa(khoaNhanMa, "DmKhoa", "dmkhoaMa");
				if (khoanhan_temp != null){
					params.put("KHOANHANTEN", khoanhan_temp.getDmkhoaTen());
					khoaNhanTen = khoanhan_temp.getDmkhoaTen();
				}else{
					params.put("KHOANHANTEN", "T\u1EA5t c\u1EA3");
				}
			}else{
				params.put("KHOANHANTEN", "T\u1EA5t c\u1EA3");
			}
			params.put("KHOANHAN", khoaNhan);
			log.info("khoaNhan:"+khoaNhan);
			
			if (khoaXuatMa != null && !khoaXuatMa.equals("")){
				DmKhoa khoaxuat_temp = (DmKhoa)dtUtil.findByMa(khoaXuatMa, "DmKhoa", "dmkhoaMa");
				if (khoaxuat_temp != null){
					params.put("KHOAXUATTEN", khoaxuat_temp.getDmkhoaTen());
				}else{
					params.put("KHOAXUATTEN", "T\u1EA5t c\u1EA3");
				}
			}else{
				if (dmTenKho != null && !dmTenKho.equals("")) { //Ten thuoc la tu truc
					khoaXuat = khoaNhan;
					params.put("KHOAXUATTEN", khoaNhanTen);
				} else {
					params.put("KHOAXUATTEN", "T\u1EA5t c\u1EA3");
				}
			}
			params.put("KHOAXUAT", khoaXuat);
			
			log.info("khoaXuat:"+khoaXuat);
			
			if (khoaNhanMa != null && !khoaNhanMa.equals("")){
				DmDoiTuong doituong_temp = (DmDoiTuong)dtUtil.findByMa(doituong, "DmDoiTuong", "dmdoituongMa");
				if (doituong_temp != null){
					params.put("DOITUONGTEN", doituong_temp.getDmdoituongTen());
				}else{
					params.put("DOITUONGTEN", "T\u1EA5t c\u1EA3");
				}
			}else{
				params.put("DOITUONGTEN", "T\u1EA5t c\u1EA3");
			}
			params.put("DOITUONGMASO", doituongMaso);
			log.info("doituong:"+doituong);
			
			log.info("================= ");
			Class.forName(IConstantsRes.ORACLE_DRIVER);
			    log.info("da thay driver Oracle");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,Inbctiensudungtaikhoa.index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Baocaotiensudung");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    Inbctiensudungtaikhoa.index+=1;
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

	public static void setIndex(int index) {
		Inbctiensudungtaikhoa.index = index;
	}

	public static int getIndex() {
		return index;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public String getReportPathKC() {
		return reportPathKC;
	}

	public void setReportPathKC(String reportPathKC) {
		this.reportPathKC = reportPathKC;
	}

	public String getReportTypeKC() {
		return reportTypeKC;
	}

	public void setReportTypeKC(String reportTypeKC) {
		this.reportTypeKC = reportTypeKC;
	}

	public JasperPrint getJasperPrintKC() {
		return jasperPrintKC;
	}

	public void setJasperPrintKC(JasperPrint jasperPrintKC) {
		this.jasperPrintKC = jasperPrintKC;
	}

	public Integer getLoaiHang() {
		return loaiHang;
	}

	public void setLoaiHang(Integer loaiHang) {
		this.loaiHang = loaiHang;
	}

	public Integer getPhanLoai() {
		return phanLoai;
	}

	public void setPhanLoai(Integer phanLoai) {
		this.phanLoai = phanLoai;
	}

	public Integer getKhoaXuat() {
		return khoaXuat;
	}

	public void setKhoaXuat(Integer khoaXuat) {
		this.khoaXuat = khoaXuat;
	}

	public Integer getKhoaNhan() {
		return khoaNhan;
	}

	public void setKhoaNhan(Integer khoaNhan) {
		this.khoaNhan = khoaNhan;
	}

	public String getKhoaXuatMa() {
		return khoaXuatMa;
	}

	public void setKhoaXuatMa(String khoaXuatMa) {
		this.khoaXuatMa = khoaXuatMa;
	}

	public String getKhoaNhanMa() {
		return khoaNhanMa;
	}

	public void setKhoaNhanMa(String khoaNhanMa) {
		this.khoaNhanMa = khoaNhanMa;
	}
	
	public String getDoituong() {
		return doituong;
	}

	public void setDoituong(String doituong) {
		this.doituong = doituong;
	}
	
	public Integer getDoituongMaso() {
		return doituongMaso;
	}

	public void setDoituongMaso(Integer doituongMaso) {
		this.doituongMaso = doituongMaso;
	}
}
