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
@Name("B3156_BaoCaoTienAnCBTC")

public class B3156_BaoCaoTienAnCBTCAction implements Serializable
{

	private static final long serialVersionUID = 10L;					
	
	private String thangbc; // Thang bao cao
	private String nambc;   // Nam bao cao
	private String tungay;
	
	private String denngay;
	private String ngayhientai;
	
	private String noinhanbc; // Noi nhan bao cao (0 : So tai chinh; 1 : Can tin)
	
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
	private String strBcTienAnCBTC;
	@Logger
	private Log log;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Factory("strBcTienAnCBTC")
	public void init() {		
		tungay = "";
		denngay = ngayhientai = sdf.format(new Date());	
		Calendar cal  = Calendar.getInstance();
		cal.setTime(new Date());
		thangbc = "" + (cal.get(Calendar.MONTH) + 1);
		nambc = "" + cal.get(Calendar.YEAR);
		noinhanbc = "0";   // Mac dinh noi nhan bao cao la So tai chinh
		strBcTienAnCBTC = "";
	}
	
	
	public String initFromMenu()
	{	
		return "/B3_Vienphi/TaiKhoa/B3156_BaoCaoTienAnCBTC.xhtml";
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
		reportTypeVP = "baocaotienanCBTC";
		log.info("Vao Method XuatReport tien an CBTC");
		
	
		
		try {
		
			String baocao1 = null;
			Integer DOI_TUONG_AN_MA_SO = new Integer("3");   // 3 la ma so cua doi tuong Trung cao (mien phi) trong table dt_dm_doi_tuong_an, 
			                                          // neu gia tri nay co thay doi trong database, thi o day cung phai thay doi theo de report xuat du lieu dung
			Integer LOAI_AN_MA_SO = new Integer("1"); // 1 la ma so cua loai an Khau phan trong table dt_dm_loai_an
			Integer LOAI_AN2_MA_SO = new Integer("2");   // 2 la ma so cua loai an San pham dinh duong trong table dt_dm_loai_an
				log.info("bat dau method XuatReport ");
				String pathTemplate = "";
				if (noinhanbc.equals("0")) {							
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoTienAnCBTC_SoTaiChinh.jrxml";
				} else {
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoTienAnCBTC_Cantin.jrxml";
				}
				
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);				
			
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("thangbaocao", thangbc);
				params.put("nambaocao", nambc);
				params.put("tungay", sdf.parse(tungay));
				params.put("denngay", sdf.parse(denngay));
				params.put("doituong_maso", DOI_TUONG_AN_MA_SO);
				params.put("loaian_maso", LOAI_AN_MA_SO);
				if (noinhanbc.equals("0")) {
					params.put("loaian2_maso", LOAI_AN2_MA_SO);
				}
				params.put("donvibaocao", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL + "\n" + IConstantsRes.REPORT_KHOA_DINH_DUONG);
				params.put("mucan", new Long(IConstantsRes.MUC_TIEN_AN_CAN_BO_TRUNG_CAO));
				params.put("subtitle", IConstantsRes.REPORT_KHOA_DINH_DUONG + " " + IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
				
				//params.put("sub4", sub4Report);
				//params.put("sub5", sub5Report);
				//params.put("sub6", sub6Report);
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
				    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","baocaotienanCBTC");
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


	public String getNoinhanbc() {
		return noinhanbc;
	}


	public void setNoinhanbc(String noinhanbc) {
		this.noinhanbc = noinhanbc;
	}
	
}
