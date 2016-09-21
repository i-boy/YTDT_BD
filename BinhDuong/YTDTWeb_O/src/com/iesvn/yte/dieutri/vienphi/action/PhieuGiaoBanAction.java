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
@Name("B3141_Phieugiaoban")
public class PhieuGiaoBanAction implements Serializable
{

	private static final long serialVersionUID = 10L;					
	
	private String ngayan;
	
	private String chutri;	
	private String thuky;
	private String thamdu;
	private String vang;
	private String ketluan;
		
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
	private String strXuatPgbkdd;
	@Logger
	private Log log;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Factory("strXuatPgbkdd")
	public void init() {
		//phieubaoan = new PhieuBaoAn();
		//phieubaoan.setKhoaMaso(new DmKhoa());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		//cal.add(Calendar.DATE, 1);
		ngayan = sdf.format(cal.getTime());	
		chutri = thuky = thamdu = vang = ketluan = "";
		strXuatPgbkdd = "";
	}
	
	
	public String initFromMenu()
	{	
		return "/B3_Vienphi/TaiKhoa/B3141_Phieugiaoban.xhtml";
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
		reportTypeVP = "PhieugiaobankhoaDD";
		log.info("Vao Method XuatReport phieu giao ban khoa dinh duong");
		
	
		
		try {
		
			String baocao1 = null;
			// Cac hang so duoi day tuong ung voi cac id trong bang dt_dm_loai_an
			// neu gia tri trong database thay doi, thi cac gia tri nay cung phai thay doi tuong ung
			Integer LOAIAN_KP_MASO = new Integer("1");  // Khau phan
			Integer LOAIAN_SPDD_MASO = new Integer("2"); // San pham dinh duong
			Integer LOAIAN_SDN_MASO = new Integer("3"); // Sua duong nhi
			
			// Cac hang so duoi day tuong ung voi cac id trong bang dt_dm_loai_an_2			
			Integer LOAIAN2_SUANT_MASO = new Integer("6");  // Sua non thang
			Integer LOAIAN2_SUASTEP1_MASO = new Integer("7"); // Sua step1
			
			// Cac hang so duoi day tuong ung voi cac id trong bang dt_dm_che_do_an			
			Integer CDOAN_LAT_MASO = new Integer("1");  // Lat
			Integer CDOAN_DD_MASO = new Integer("2"); // DD
			Integer CDOAN_STM_MASO = new Integer("3"); // STM
			Integer CDOAN_THUONG_MASO = new Integer("4"); // THUONG
			Integer CDOAN_CM_MASO = new Integer("5"); // CM
			Integer CDOAN_BDUONG_MASO = new Integer("6"); // BDUONG
			
			// Cac hang so duoi day tuong ung voi cac id trong bang dt_dm_gio_an			
			Integer GIOAN_6H_MASO = new Integer("1");  // 6h
			Integer GIOAN_11H_MASO = new Integer("2"); // 11h
			Integer GIOAN_16H_MASO = new Integer("3"); // 16h
			Integer GIOAN_20H_MASO = new Integer("4"); // 20h
				log.info("bat dau method XuatReport, ngay an = " + ngayan);
				
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/B3142_GiaoBanKhoaDinhDuong.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/B3142_GiaoBanKhoaDinhDuong_subreport0.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/B3142_GiaoBanKhoaDinhDuong_subreport1.jrxml";
				String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/B3142_GiaoBanKhoaDinhDuong_subreport2.jrxml";
				String sub3Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/B3142_GiaoBanKhoaDinhDuong_subreport3.jrxml";
				String sub4Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/B3142_GiaoBanKhoaDinhDuong_subreport4.jrxml";
				
				
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);												
				JasperReport sub3Report =JasperCompileManager.compileReport(sub3Template);
				JasperReport sub4Report =JasperCompileManager.compileReport(sub4Template);
			
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("ngayan", sdf.parse(ngayan));
				params.put("chutri", chutri);
				params.put("thuky", thuky);
				params.put("thamdu", thamdu);
				params.put("vang", vang);
				params.put("ketluan", ketluan);
				
				params.put("loaian_kp_maso", LOAIAN_KP_MASO);
				params.put("loaian_spdd_maso", LOAIAN_SPDD_MASO);
				params.put("loaian_sdn_maso", LOAIAN_SDN_MASO);
				
				params.put("loaian2_suant_maso", LOAIAN2_SUANT_MASO);
				params.put("loaian2_suastep1_maso", LOAIAN2_SUASTEP1_MASO);
				
				params.put("cdoan_lat_maso", CDOAN_LAT_MASO);
				params.put("cdoan_dd_maso", CDOAN_DD_MASO);
				params.put("cdoan_stm_maso", CDOAN_STM_MASO);
				params.put("cdoan_thuong_maso", CDOAN_THUONG_MASO);
				params.put("cdoan_cm_maso", CDOAN_CM_MASO);
				params.put("cdoan_bduong_maso", CDOAN_BDUONG_MASO);
				
				params.put("gioan_6h_maso", GIOAN_6H_MASO);
				params.put("gioan_11h_maso", GIOAN_11H_MASO);
				params.put("gioan_16h_maso", GIOAN_16H_MASO);
				params.put("gioan_20h_maso", GIOAN_20H_MASO);
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
				params.put("sub2", sub2Report);
				params.put("sub3", sub3Report);
				params.put("sub4", sub4Report);
				log.info("================= ");
				Class.forName("oracle.jdbc.OracleDriver");
				    log.info("da thay driver mysql");
				    Connection conn = null;
				    try{
				    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);				    	
				    }catch(Exception e){
				    	log.info("ERORR : " + e.toString());				    
				    }				    
				    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
				    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","PhieugiaobankhoaDD");
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
	public String getChutri() {
		return chutri;
	}
	public void setChutri(String chutri) {
		this.chutri = chutri;
	}
	public String getThuky() {
		return thuky;
	}
	public void setThuky(String thuky) {
		this.thuky = thuky;
	}
	public String getThamdu() {
		return thamdu;
	}
	public void setThamdu(String thamdu) {
		this.thamdu = thamdu;
	}
	public String getVang() {
		return vang;
	}
	public void setVang(String vang) {
		this.vang = vang;
	}
	public String getKetluan() {
		return ketluan;
	}
	public void setKetluan(String ketluan) {
		this.ketluan = ketluan;
	}
}