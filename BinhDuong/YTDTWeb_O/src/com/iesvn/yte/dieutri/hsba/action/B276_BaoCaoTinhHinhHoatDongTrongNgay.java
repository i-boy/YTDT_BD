/**
 * author : i-boy
 */

package com.iesvn.yte.dieutri.hsba.action;

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

import org.jboss.seam.ScopeType;
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
import com.iesvn.yte.util.Utils;


@Name("B276_BaoCaoTinhHinhHoatDongTrongNgay")
@Scope(CONVERSATION)
public class B276_BaoCaoTinhHinhHoatDongTrongNgay implements Serializable {
	
	private static final long serialVersionUID = 1749463646874557659L;

	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String duongdanStrDT=null;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintDT = null;
	
	private String thoigian_thang = "";  
	private String thoigian_nam = "";
	private String tungay = "";
	private String denngay = "";
	private String ngayhientai = "";
	
	
	private int index=0;	
	
	@Begin (join = true)
	public String init() {
		resetForm();
		return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoTinhHinhHoatDongTrongNgay";
	}
	@End
	public void end(){
		
	}
	
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
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
		log.info("Ket thuc ham reset form B276_BaoCaoTinhHinhHoatDongTrongNgay");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="BCTinhHinhHoatDongTrongNgay";
		log.info("Vao Method XuatReport In danh sach B276_BaoCaoTinhHinhHoatDongTrongNgay");
		String baocao1=null;
		String pathTemplate = null;
		String sub0Template = null;
		String sub1Template = null;
		String sub2Template = null;
		String sub3Template = null;
		String sub4Template = null;
		try {
			System.out.println("Den ngay = "+denngay);
			Date time1= null;
			Date time2 = null;
			Date time3 = null;
			time1 = Utils.getDBDate("07:30", denngay).getTime();
			time2 = Utils.getDBDate("16:30", denngay).getTime();
			Calendar c = Calendar.getInstance();
			c.setTime(time1);
			c.add(Calendar.DATE, 1);
			time3 = c.getTime();
			
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA01_baocaotinhhinhhoatdongtrongngay.jrxml";
			sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA01_baocaotinhhinhhoatdongtrongngay_subreport0.jrxml";			
			sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA01_baocaotinhhinhhoatdongtrongngay_subreport1.jrxml";
			sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA01_baocaotinhhinhhoatdongtrongngay_subreport2.jrxml";
			sub3Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA01_baocaotinhhinhhoatdongtrongngay_subreport3.jrxml";
			sub4Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/BA01_baocaotinhhinhhoatdongtrongngay_subreport4.jrxml";
			log.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
			JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
			JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
			JasperReport sub3Report =JasperCompileManager.compileReport(sub3Template);
			JasperReport sub4Report =JasperCompileManager.compileReport(sub4Template);
			Map<String, Object> params = new HashMap<String, Object>();

			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");	
			params.put("sub1", sub0Report);			
			params.put("sub2", sub1Report);
			params.put("sub3", sub2Report);
			params.put("sub4", sub3Report);
			params.put("sub5", sub4Report);
			params.put("time1",time1);
			params.put("time2",time2);
			params.put("time3",time3);
			
			params.put("soYTe", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);	
			params.put("dayBefore", df.parse(tungay));
			params.put("currentDate", df.parse(denngay));
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","tinhhinhhoatdongtrongngay");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");

			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
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
