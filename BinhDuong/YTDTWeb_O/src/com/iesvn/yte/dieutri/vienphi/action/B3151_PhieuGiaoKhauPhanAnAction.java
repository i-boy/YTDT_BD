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

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("B3151_PhieuGiaoKhauPhanAn")

public class B3151_PhieuGiaoKhauPhanAnAction implements Serializable
{

	private static final long serialVersionUID = 10L;					
	private String khoaMaso; 	
	
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
	private String strPgkpa;
	@Logger
	private Log log;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Factory("strPgkpa")
	public void init() {
		//phieubaoan = new PhieuBaoAn();
		//phieubaoan.setKhoaMaso(new DmKhoa());
				
		ngayan = sdf.format(new Date());		
		strPgkpa = "";
	}
	
	
	public String initFromMenu()
	{	
		return "/B3_Vienphi/TaiKhoa/B3151_PhieuGiaoKhauPhanAn.xhtml";
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
		reportTypeVP = "phieugiaokhauphanan";
		log.info("Vao Method XuatReport phieu giao khau phan an");
		
	
		
		try {
		
			String baocao1 = null;
			Integer LOAI_AN_MA_SO = new Integer("1");   // 1 la ma so cua Khau phan trong table dt_dm_loai_an, 
            // neu gia tri nay co thay doi trong database, thi o day cung phai thay doi theo de report xuat du lieu dung
				log.info("bat dau method XuatReport ");
				// Lay danh muc khoa
				DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
				DmKhoa dmKhoa = (DmKhoa) dtUtil.findByMaSo(new Integer(khoaMaso), "DmKhoa", "dmkhoaMaso");
				
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/PhieuGiaoKhauPhanAn.jrxml";											
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);																		
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("ngayan", sdf.parse(ngayan));
				params.put("khoamaso", dmKhoa.getDmkhoaMaso());
				params.put("khoaten", dmKhoa.getDmkhoaTen());
				params.put("loaianmaso", LOAI_AN_MA_SO);
				params.put("tendonvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
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
				    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Phieugiaokhauphanan");
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
	public String getKhoaMaso() {
		return khoaMaso;
	}


	public void setKhoaMaso(String khoaMaso) {
		this.khoaMaso = khoaMaso;
	}
}
