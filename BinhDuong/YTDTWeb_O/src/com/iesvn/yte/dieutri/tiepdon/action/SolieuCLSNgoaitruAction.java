package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

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

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3322_Solieuclsngoaitru")
@Synchronized(timeout = 6000000)
public class SolieuCLSNgoaitruAction implements Serializable{
	private static final long serialVersionUID = 1L;
	@Logger
	private Log log;
	
	private String thang;
	private String nam;
	private String tuNgay;
	private String denNgay;
	
	private String khoaThuchien;
	private String loaiClsMa;
	private String pbClsMa;
	
	private String khoaTen;
	private String clsTen;
	
	private String reportFinished;
	private String reportFileName;
	private String ngayhientai;
	
	private String resultReportFileName;
	private String resultReportName;
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	
	
	@Out(required=false)
	@In(required=false)
	private String loaiCLSNgoaiTruHoacNoiTru=null;
	
	@Begin(join = true)
	public String init(String loaiCLSBC){
		log.info("Begin init()");
		
		loaiCLSNgoaiTruHoacNoiTru = loaiCLSBC;
		
		reportFinished = "";
		ngayhientai = Utils.getCurrentDate();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		thang = String.valueOf(currentDate.getMonth() +1);
		nam = String.valueOf(currentDate.getYear()+1900);
		tuNgay = sdf.format(currentDate);
		denNgay = sdf.format(currentDate);
		log.info("End init()");
		return "BaoCaoVienPhi_HoSoBaoCao_SoLieuCLSNgoaiTru";
	}
	
	@End
	public void endFunc(){
		
	}
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	int index = 0;
	public String thuchienAction(){
		XuatReport();
	
		return "B3360_Chonmenuxuattaptin";
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="V06_Chiphicanlamsangbaravien";
		log.info("Vao Method XuatReport bao cao BHYT noi tru");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			if ("NgoaiTru".equals(loaiCLSNgoaiTruHoacNoiTru)){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V06_Chiphicanlamsangbaravien.jrxml";
				
			}else{
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V07_Chiphicanlamsangnoitru.jrxml";
				
			}
			
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sqlFormatter = new SimpleDateFormat("yyyy-MM-dd");
			log.info("begin set params cho sql");
			log.info("khoaThuchienSql: "+ khoaThuchien);
			log.info("loaiClsSql: "+ loaiClsMa);
			log.info("pbClsMaSql: "+ pbClsMa);
			log.info("tuNgay: "+ tuNgay);
			log.info("denNgay: "+ denNgay);
			log.info("khoaTen: "+ khoaTen);
			log.info("clsTen: "+ clsTen);
			
			params.put("khoaThuchienSql", khoaThuchien);
			params.put("loaiClsSql", loaiClsMa);
			params.put("pbClsMaSql", pbClsMa);
			
			if(khoaThuchien == null || khoaThuchien.equals("")){
				khoaThuchien = null;//"Toàn viện";
			}
			if(loaiClsMa == null || loaiClsMa.equals("")){
				loaiClsMa = null;
			}
			params.put("khoathuchienPr", khoaTen);
			params.put("clsPr", clsTen);
			
			Date tungaySql = null;
			Date denngaySql = null;
			if(tuNgay != null && ! tuNgay.equals("")){
				Date tnTmp = formatter.parse(tuNgay);
				String tmp = sqlFormatter.format(tnTmp);
				log.info("tungaySql: "+tmp);
				sqlFormatter = new SimpleDateFormat(IConstantsRes.SQL_FORMAT_DATETIME);
				tungaySql = sqlFormatter.parse(tmp + " 00:00:00");
				params.put("tungaySql", tungaySql);
				log.info("tungaySqlFormat: "+sqlFormatter.format(tungaySql));
			}
			if(denNgay != null && ! denNgay.equals("")){
				Date tnTmp = formatter.parse(denNgay);
				sqlFormatter = new SimpleDateFormat("yyyy-MM-dd");
				String tmp = sqlFormatter.format(tnTmp);
				log.info("dengaySql: "+ tmp);
				sqlFormatter = new SimpleDateFormat(IConstantsRes.SQL_FORMAT_DATETIME);
				denngaySql = sqlFormatter.parse(tmp +" 23:59:59");
				params.put("denngaySql", denngaySql);
				log.info("denngaySqlFormat: "+sqlFormatter.format(denngaySql));
			}
			log.info("end set params cho sql");
			log.info("begin set params cho report");
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
	        params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
	        params.put("TUNGAY", formatter.parse(tuNgay));
	        params.put("DENNGAY", formatter.parse(denNgay));
	        params.put("ngayIn", ngayhientai);
	        String tmp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	        String hhMm = tmp.substring(11);
	        params.put("gioIn",hhMm);
	        Calendar cal = Calendar.getInstance();           
            params.put("ngay", "" + cal.get(Calendar.DAY_OF_MONTH));
            params.put("thang", "" + (cal.get(Calendar.MONTH) + 1));
            params.put("nam", "" + cal.get(Calendar.YEAR));
            
			log.info("End set params cho report");
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","V06_Chiphicanlamsangbaravien");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+=1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
//	public String tienhanh(){
//		log.info("Begin xuatReportSoLieuCLSNGoaitru(): ");
//		try{
//			Map<String, Object> params = new HashMap<String, Object>();
//			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//			SimpleDateFormat sqlFormatter = new SimpleDateFormat("yyyy-MM-dd");
//			log.info("begin set params cho sql");
//			log.info("khoaThuchienSql: "+ khoaThuchien);
//			log.info("loaiClsSql: "+ loaiClsMa);
//			log.info("pbClsMaSql: "+ pbClsMa);
//			log.info("tuNgay: "+ tuNgay);
//			log.info("denNgay: "+ denNgay);
//			log.info("khoaTen: "+ khoaTen);
//			log.info("clsTen: "+ clsTen);
//			
//			params.put("khoaThuchienSql", khoaThuchien);
//			params.put("loaiClsSql", loaiClsMa);
//			params.put("pbClsMaSql", pbClsMa);
//			
//			if(khoaThuchien == null || khoaThuchien.equals("")){
//				khoaThuchien = "Toàn viện";
//			}
//			if(loaiClsMa == null || loaiClsMa.equals("")){
//				loaiClsMa = "";
//			}
//			params.put("khoathuchienPr", khoaThuchien);
//			params.put("clsPr", loaiClsMa);
//			
//			Date tungaySql = null;
//			Date denngaySql = null;
//			if(tuNgay != null && ! tuNgay.equals("")){
//				Date tnTmp = formatter.parse(tuNgay);
//				String tmp = sqlFormatter.format(tnTmp);
//				log.info("tungaySql: "+tmp);
//				sqlFormatter = new SimpleDateFormat(IConstantsRes.SQL_FORMAT_DATETIME);
//				tungaySql = sqlFormatter.parse(tmp + " 00:00:00");
//				params.put("tungaySql", tungaySql);
//				log.info("tungaySqlFormat: "+sqlFormatter.format(tungaySql));
//			}
//			if(denNgay != null && ! denNgay.equals("")){
//				Date tnTmp = formatter.parse(denNgay);
//				sqlFormatter = new SimpleDateFormat("yyyy-MM-dd");
//				String tmp = sqlFormatter.format(tnTmp);
//				log.info("dengaySql: "+ tmp);
//				sqlFormatter = new SimpleDateFormat(IConstantsRes.SQL_FORMAT_DATETIME);
//				denngaySql = sqlFormatter.parse(tmp +" 23:59:59");
//				params.put("denngaySql", denngaySql);
//				log.info("denngaySqlFormat: "+sqlFormatter.format(denngaySql));
//			}
//			log.info("end set params cho sql");
//			log.info("begin set params cho report");
//			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
//	        params.put("diachiBv", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
//	        params.put("diaphuong", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
//	        params.put("tungay", tuNgay);
//	        params.put("denngay", denNgay);
//	        params.put("ngayIn", ngayhientai);
//	        String tmp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
//	        String hhMm = tmp.substring(11);
//	        params.put("gioIn",hhMm);
//	        Calendar cal = Calendar.getInstance();           
//            params.put("ngay", "" + cal.get(Calendar.DAY_OF_MONTH));
//            params.put("thang", "" + (cal.get(Calendar.MONTH) + 1));
//            params.put("nam", "" + cal.get(Calendar.YEAR));
//            
//			log.info("End set params cho report");
//			log.info("Xuat report");
//			Date currentDate = new Date();
//	        String fileNameExt = String.valueOf(currentDate.getTime()) ;
//	        String fileName = com.iesvn.yte.ReportUtil.xuatReport(IConstantsRes.PATH_BASE,
//	        		 	IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI, IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI, "vienphi/",
//	    				"V06_Chiphicanlamsangbaravien" , params, loaiFile, fileNameExt);
//	            
//	        reportFinished = IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI +"vienphi/" ;
//	        reportFileName = fileName;
//			log.info("end xuat report");
//			if(loaiFile.equalsIgnoreCase("HTML")){
//	        	resultReportName = fileName;
//	        }
//	        else{
//	        	resultReportFileName = fileName;
//	        }
//	        setIsReport("true");
//		}catch (Exception e) {
//			log.info("ERROR:(", e);
//		}
//		log.info("End xuatReportSoLieuCLSNGoaitru()");
//		return "B3322_Chonmenuxuattaptin";
//	}
	
	public String troVe(){
		try {
			log.info("***** troVe()");  
			khoaTen = "";
			clsTen = "";
			return "B3322_Solieuclsngoaitru";
		} 		
		catch (Exception e) {
			log.info("***** End exception = " + e);    	
		} 
		return null;
	}
	
	public void nhaplai(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		tuNgay = sdf.format(currentDate);
		denNgay = sdf.format(currentDate);
		thang = String.valueOf(currentDate.getMonth() +1);
		nam = String.valueOf(currentDate.getYear()+1900);
		khoaThuchien = "";
		loaiClsMa = null;
		pbClsMa = null;
		reportFileName = "";
		reportFinished ="";
		khoaTen = "";
		clsTen = "";
	}
	
	
	public String getThang() {
		return thang;
	}
	public void setThang(String thang) {
		this.thang = thang;
	}
	public String getNam() {
		return nam;
	}
	public void setNam(String nam) {
		this.nam = nam;
	}
	public String getTuNgay() {
		return tuNgay;
	}
	public void setTuNgay(String tuNgay) {
		this.tuNgay = tuNgay;
	}
	public String getDenNgay() {
		return denNgay;
	}
	public void setDenNgay(String denNgay) {
		this.denNgay = denNgay;
	}
	public String getKhoaThuchien() {
		return khoaThuchien;
	}
	public void setKhoaThuchien(String khoaThuchien) {
		this.khoaThuchien = khoaThuchien;
	}
	public String getLoaiClsMa() {
		return loaiClsMa;
	}
	public void setLoaiClsMa(String loaiClsMa) {
		this.loaiClsMa = loaiClsMa;
	}
	public String getPbClsMa() {
		return pbClsMa;
	}
	public void setPbClsMa(String pbClsMa) {
		this.pbClsMa = pbClsMa;
	}

	public String getReportFinished() {
		return reportFinished;
	}

	public void setReportFinished(String reportFinished) {
		this.reportFinished = reportFinished;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getKhoaTen() {
		return khoaTen;
	}

	public void setKhoaTen(String khoaTen) {
		this.khoaTen = khoaTen;
	}

	public String getClsTen() {
		return clsTen;
	}

	public void setClsTen(String clsTen) {
		this.clsTen = clsTen;
	}

}


