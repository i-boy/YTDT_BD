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
@Name("B3155_BaoCaoSanPhamDD")

public class B3155_BaoCaoSanPhamDDAction implements Serializable
{

	private static final long serialVersionUID = 10L;					
		
	private String tungay;
	
	private String denngay;
	private String ngayhientai;
	
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
	private String strBcSPDD;
	@Logger
	private Log log;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Factory("strBcSPDD")
	public void init() {		
		tungay = "";
		denngay = ngayhientai = sdf.format(new Date());		
		strBcSPDD = "";
	}
	
	
	public String initFromMenu()
	{	
		return "/B3_Vienphi/TaiKhoa/B3155_BaoCaoSanPhamDD.xhtml";
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
		reportTypeVP = "baocaosanphamDD";
		log.info("Vao Method XuatReport san pham dinh duong");
		
	
		
		try {
		
			String baocao1 = null;
			Integer DUONG_MA_SO = new Integer("4");   // 4 la ma so cua duong trong table dt_dm_loai_an_2, 
			                                          // neu gia tri nay co thay doi trong database, thi o day cung phai thay doi theo de report xuat du lieu dung
			Integer SUA_DAC_MA_SO = new Integer("5"); // 5 la ma so cua sua dac trong table dt_dm_loai_an_2
			Integer SAN_PHAM_DINH_DUONG_MA_SO = new Integer("2");   // 2 la ma so cua san pham dinh duong trong table dt_dm_loai_an
				log.info("bat dau method XuatReport ");
				/*
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_subreport0.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_subreport1.jrxml";
				String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_subreport2.jrxml";
				String sub3Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_subreport3.jrxml";
				String sub4Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_subreport4.jrxml";
				String sub5Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_subreport5.jrxml";
				String sub6Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_subreport6.jrxml";				
				*/							
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_redesign.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_redesign_subreport0.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_redesign_subreport1.jrxml";
				String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_redesign_subreport2.jrxml";
				String sub3Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BaoCaoSanPhamDinhDuong_redesign_subreport3.jrxml";
				
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
				JasperReport sub3Report =JasperCompileManager.compileReport(sub3Template);
				//JasperReport sub4Report =JasperCompileManager.compileReport(sub4Template);
				//JasperReport sub5Report =JasperCompileManager.compileReport(sub5Template);
				//JasperReport sub6Report =JasperCompileManager.compileReport(sub6Template);
			
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("tungay", sdf.parse(tungay));
				params.put("denngay", sdf.parse(denngay));
				params.put("duongmaso", DUONG_MA_SO);
				params.put("suadacmaso", SUA_DAC_MA_SO);
				params.put("spdd", SAN_PHAM_DINH_DUONG_MA_SO);
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
				params.put("sub2", sub2Report);
				params.put("sub3", sub3Report);
				params.put("tendonvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
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
				    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","baocaosanphamDD");
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
	
}
