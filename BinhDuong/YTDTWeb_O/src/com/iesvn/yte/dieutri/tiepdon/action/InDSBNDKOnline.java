/**
 * author : i-boy
 */

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
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.util.IConstantsRes;


@Name("InDSBNDKOnline")
@Scope(CONVERSATION)
public class InDSBNDKOnline implements Serializable {
	
	private static final long serialVersionUID = 1749463646874557659L;

	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathTD=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeTD=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintTD = null;
	
	private String thoigian_thang = "";  
	private String thoigian_nam = "";
	private String tungay = null;
	private String denngay = null;
	private String ngayhientai = "";
	
	private int index=0;	
	
	@Begin (join = true)
	public String init() {
		resetForm();
		return "TiepDon_TiepDonBenhNhan_InDSBNDKOnline";
	}
	@End
	public void end(){
		
	}
	
	public String thuchienAction(){
		
		if(!IConstantsRes.DK_ONLINE_DB_URL.startsWith("jdbc")){
			log.info("### Khong co server Dang ky online");
			return "";
		}
		XuatReport();
		return "B160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		ngayhientai = sdf.format(currentDate);
		thoigian_nam = sdf.format(currentDate).substring(6);
		thoigian_thang = sdf.format(currentDate).substring(3, 5);  
		
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		tungay = sdf.format(cal.getTime());
		denngay = sdf.format(currentDate);
		log.info("Ket thuc ham reset form InDSBNDKOnline");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeTD="indsbndkonline";
		log.info("Vao Method XuatReport In danh sach BN dang ky Online");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T02_dsbndkonline.jrxml";
			log.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");		
			params.put("TuNgay", df.parse(tungay));
			params.put("DenNgay", df.parse(denngay));
			params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	// bao.ttc: tao connection den DB dang ky kham benh online
			    	conn = DriverManager.getConnection(IConstantsRes.DK_ONLINE_DB_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintTD =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","indsbndkonline");
			    reportPathTD=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathTD);
			    index += 1;
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

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}


}
