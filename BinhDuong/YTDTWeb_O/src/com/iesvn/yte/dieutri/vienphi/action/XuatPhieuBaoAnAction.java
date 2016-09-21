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

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("B3142_Xuatphieubaoan")

public class XuatPhieuBaoAnAction implements Serializable
{

	private static final long serialVersionUID = 10L;
	
	private String khoaMaso;  
	private String ngayan;
	private String loaian1Maso;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Logger
	private Log log;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Factory("strXuatPba")
	public void init() {
		//phieubaoan = new PhieuBaoAn();
		//phieubaoan.setKhoaMaso(new DmKhoa());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 1);
		ngayan = sdf.format(cal.getTime());
		khoaMaso = "";
		loaian1Maso = "";
	}
	
	
	public String initFromMenu()
	{	
		return "/B3_Vienphi/TaiKhoa/B3142_Xuatphieubaoan.xhtml";
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
		reportTypeVP = "xuatphieubaoan";
		log.info("Vao Method XuatReport phieu bao an, laoian1Maso = " + loaian1Maso + ", khoaMaso" + khoaMaso);
		// Lay danh muc khoa
		DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
		DmKhoa dmKhoa = (DmKhoa) dtUtil.findByMaSo(new Integer(khoaMaso), "DmKhoa", "dmkhoaMaso");
		log.info("Khoa ten = " + dmKhoa != null ? dmKhoa.getDmkhoaTen() : "");
		// Lay danh muc loai an
		DtDmLoaiAn dmLoaian = (DtDmLoaiAn) dtUtil.findByMaSo(new Integer(loaian1Maso), "DtDmLoaiAn", "dtdmlaMaso");
	
		try {
			
			String baocao1 = null;
			
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE
					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
					+ ((loaian1Maso.equals("3")) ? "vienphi/B3140_Phieubaoan_suaduongnhi.jrxml" : "vienphi/B3140_Phieubaoan_loaiankhac.jrxml");
		

			JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
		
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("khoa", dmKhoa != null ? dmKhoa.getDmkhoaTen(): "");
	        params.put("khoamaso",dmKhoa != null ? dmKhoa.getDmkhoaMaso(): new Integer(0));
			params.put("ngayan", sdf.parse(ngayan));
			params.put("tendonvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
			if (dmLoaian != null) {
				params.put("loaianmaso", dmLoaian.getDtdmlaMaso());
				params.put("loaian", dmLoaian.getDtdmlaTen());
			}
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
		    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Xuatphieubaoan");
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
	
	public String getLoaian1Maso() {
		return loaian1Maso;
	}
	
	public void setLoaian1Maso(String loaian1Maso) {
		this.loaian1Maso = loaian1Maso;
	}
	
	public String getKhoaMaso() {
		return khoaMaso;
	}
	
	public void setKhoaMaso(String khoaMaso) {
		this.khoaMaso = khoaMaso;
	}
	
	public String getReportTypeVP(){
		return reportTypeVP;
	}
	
	public void setReportTypeVP(String reportTypeVP){
		this.reportTypeVP = reportTypeVP;
	}
	
}
