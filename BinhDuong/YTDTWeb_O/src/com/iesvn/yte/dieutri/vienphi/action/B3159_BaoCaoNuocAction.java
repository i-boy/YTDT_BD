package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

@Scope(SESSION)
@Name("B3159_BaoCaoNuoc")

public class B3159_BaoCaoNuocAction implements Serializable
{

	private static final long serialVersionUID = 10L;					
	
	private String thangbc; // Thang bao cao
	private String nambc;   // Nam bao cao
	private String tungay;
	
	private String denngay;
	private String ngayhientai;
	
	private String loaibc; // Loai bao cao (0 : bao cao theo buong; 1 : bao cao theo khoa)
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strBcNuoc;
	@Logger
	private Log log;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Factory("strBcNuoc")
	public void init() {		
		tungay = "";
		denngay = ngayhientai = sdf.format(new Date());	
		Calendar cal  = Calendar.getInstance();
		cal.setTime(new Date());
		thangbc = "" + (cal.get(Calendar.MONTH) + 1);
		nambc = "" + cal.get(Calendar.YEAR);
		loaibc = "0";   // Mac dinh la bao cao theo buong
		strBcNuoc = "";
	}
	
	
	public String initFromMenu()
	{	
		return "/B3_Vienphi/TaiKhoa/B3159_BaoCaoNuoc.xhtml";
		//return "VienPhiTaiKhoa_LapPhieuBaoAnHangNgay";
	}
	
	public String thuchienAction()
	{
		XuatReport();
		return "B3360_Chonmenuxuattaptin";
	}
	/**
	 * xuat report
	 */
	public void XuatReport()
	{
		reportTypeVP = "baocaonuoc";
		log.info("Vao Method XuatReport bao cao nuoc");
		
	
		
		try {
		
			String baocao1 = null;
			
				log.info("bat dau method XuatReport ");
				String pathTemplate = "";
				if (loaibc.equals("0")) {							
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoNuocTheoBuong.jrxml";
				} else {
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoNuocTheoKhoa.jrxml";
				}
				
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);				
			
				Map<String, Object> params = new HashMap<String, Object>();
				
				//params.put("thangbaocao", thangbc);
				//params.put("nambaocao", nambc);
				params.put("tungay", sdf.parse(tungay));
				params.put("denngay", sdf.parse(denngay));				
				params.put("tendonvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL + "\n" + IConstantsRes.REPORT_KHOA_DINH_DUONG);
				
				log.info("================= ");
				Class.forName("oracle.jdbc.OracleDriver");
				    log.info("da thay driver mysql");
				    Connection conn = null;
				    try{
				    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
				    }catch(Exception e){
				    	log.info(e.getMessage());
				    }
				    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
				    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","baocaonuoc");
				    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
				    log.info("duong dan file xuat report :" + baocao1);
				    log.info("duong dan -------------------- :"+reportPathVP);
				    //index+= 1;
				    //log.info("Index :" + getIndex());
				    if(conn != null) conn.close();
			
			 
		} catch (Exception e){
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


	public String getNgayhientai() {
		return ngayhientai;
	}


	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	public String getReportTypeVP(){
		return reportTypeVP;
	}
	public void setReportTypeVP(String reportTypeVP){
		this.reportTypeVP = reportTypeVP;
	}


	public String getThangbc() {
		return thangbc;
	}


	public void setThangbc(String thangbc) {
		this.thangbc = thangbc;
	}


	public String getNambc() {
		return nambc;
	}


	public void setNambc(String nambc) {
		this.nambc = nambc;
	}


	public String getLoaibc() {
		return loaibc;
	}


	public void setLoaibc(String loaibc) {
		this.loaibc = loaibc;
	}

}
