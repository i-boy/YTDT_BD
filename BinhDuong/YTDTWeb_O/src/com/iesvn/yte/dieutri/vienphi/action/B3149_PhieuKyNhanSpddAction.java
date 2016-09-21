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
@Name("B3149_PhieuKyNhanSpdd")

public class B3149_PhieuKyNhanSpddAction implements Serializable
{

	private static final long serialVersionUID = 10L;					
		
	private String ngayan;	
	
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
	private String strXuatPknSpdd;
	@Logger
	private Log log;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Factory("strXuatPknSpdd")
	public void init() {
		//phieubaoan = new PhieuBaoAn();
		//phieubaoan.setKhoaMaso(new DmKhoa());
				
		ngayan = sdf.format(new Date());		
		strXuatPknSpdd = "";
	}
	
	
	public String initFromMenu()
	{	
		return "/B3_Vienphi/TaiKhoa/B3149_PhieuKyNhanSPDD.xhtml";
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
		reportTypeVP = "xuatphieukynhanspdd";
		log.info("Vao Method XuatReport phieu ky nhan spdd");
		
	
		
		try {
		
			String baocao1 = null;
			
				log.info("bat dau method XuatReport ");
				
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/PhieuKyNhanSanPhamDinhDuong.jrxml";				
								
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);																		
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("ngayan", sdf.parse(ngayan));								
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
				    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Phieukynhanspdd");
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
	// End Phuc add
	
	public String getNgayan() {
		return ngayan;
	}
	public void setNgayan(String ngayan) {
		this.ngayan = ngayan;
	}
/*	public String getNgayhientai() {
		return ngayhientai;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	} */
	
	public String getReportTypeVP(){
		return reportTypeVP;
	}
	public void setReportTypeVP(String reportTypeVP){
		this.reportTypeVP = reportTypeVP;
	}
	
}
