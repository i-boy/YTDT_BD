/**
 * author : i-boy
 */

package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@Name("B281_DanhSachBenhNhanChuyenVien")
@Scope(CONVERSATION)
public class B281_DanhSachBenhNhanChuyenVien implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Logger
	private Log log;
	
	@In(required=false)
	@Out(required=false)
	private String duongdanStrDT = null;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT=null;
	
	@In(required=false)
	@Out(required=false)
	JasperPrint jasperPrintDT = null;
	
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String resetFormB281=null;
		
	private String tungay = "";
	private String denngay = "";
	private String ngayhientai = "";
	private Integer khoaMaso = null;
	private String khoaMa = "";
	private String loaiBN = "";
	private int index=0;	
	
	@Factory("resetFormB281")
	public String init() {		 
		resetForm();
		return "/B2_Dieutri/B281_DanhSachBenhNhanChuyenVien.xhtml";
	}
	
	
	public String thuchienAction(){
		XuatReport();
		resetFormB281=null;
		return "B260_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		log.info("Bat dau ham reset form");
		ngayhientai = Utils.getCurrentDate();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		resetFormB281 = "";		
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		khoaMaso = null;
		khoaMa  = "";
		loaiBN = "ngoai";
		log.info("ket thuc ham reset form");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="DanhSachBenhNhanChuyenVien";
		log.info("Vao Method XuatReport Danh Sach Benh Nhan Chuyen Vien");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport, Loai Benh nhan: " + loaiBN);
			
			if(loaiBN.equals("noi")){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/B281_DanhsachChuyenvienNoitru.jrxml";
			} else {
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/B281_DanhsachChuyenvienNgoaitru.jrxml";
			}
			
			log.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
			
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);						
			params.put("TUNGAY", sdf.parse(tungay));
			params.put("DENNGAY", sdf.parse(denngay));
			//params.put("KhoaMaso", khoaMaso);
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintDT =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","DanhSachBenhNhanChuyenVien");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    index+=1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
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
	
	public Integer getKhoaMaso() {
		return khoaMaso;
	}
	public void setKhoaMaso(Integer khoaMaso) {
		this.khoaMaso = khoaMaso;
	}
	
	public String getKhoaMa() {
		return khoaMa;
	}
	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}

	public String getLoaiBN() {
		return loaiBN;
	}
	public void setLoaiBN(String loaiBN) {
		this.loaiBN = loaiBN;
	}
	
	
}
