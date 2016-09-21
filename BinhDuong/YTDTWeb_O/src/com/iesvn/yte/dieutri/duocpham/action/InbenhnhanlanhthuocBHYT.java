/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B4436_Inbenhnhanlanhthuoc")
@Scope(SESSION)
public class InbenhnhanlanhthuocBHYT implements Serializable {
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
	
	@In(scope=ScopeType.SESSION,required=false)
	@Out(scope=ScopeType.SESSION,required=false)
	private String resetFormB4436=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private static int index=0;
	private String khoa_ma=null;
	private Integer thuoc_maso=null;
	private String thuoc_ma=null;
	
	@Factory("resetFormB4436")
	public void init() {
		log.info("init()");
		setKhoa_ma("BHY");
		resetForm();
		ngayhientai = Utils.getCurrentDate();
	}
	
	public String thuchienAction(){
		XuatReport();
		resetFormB4436=null;
		return "B4160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		setThoigian_thang("");
		setThoigian_nam("");
		setTungay("");
		setDenngay("");
		setThuoc_ma(null);
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="Inbenhnhanlanhthuoc";
		log.info("Vao Method XuatReport In benh nhan lanh thuoc BHYT");
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D27_danhsachbenhnhanlinhthuocBHYT.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			log.info("tu ngay " + tungay);
			log.info("den ngay " + denngay);
			params.put("TUNGAY", tungay);
			params.put("TUNGAYDATE", sdf.parse(tungay));
			params.put("DENNGAY", denngay);
			params.put("DENNGAYDATE", sdf.parse(denngay));
			
			log.info("thuoc ma so " + thuoc_maso);
			if(thuoc_maso!=null){
				params.put("THUOCMASO", thuoc_maso);
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,InbenhnhanlanhthuocBHYT.index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Inbenhnhanlanhthuoc");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    InbenhnhanlanhthuocBHYT.index+=1;
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
		InbenhnhanlanhthuocBHYT.index = index;
	}

	public static int getIndex() {
		return index;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}

	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setThuoc_maso(Integer thuoc_maso) {
		this.thuoc_maso = thuoc_maso;
	}

	public Integer getThuoc_maso() {
		return thuoc_maso;
	}

	public void setThuoc_ma(String thuoc_ma) {
		this.thuoc_ma = thuoc_ma;
	}

	public String getThuoc_ma() {
		return thuoc_ma;
	}
}
