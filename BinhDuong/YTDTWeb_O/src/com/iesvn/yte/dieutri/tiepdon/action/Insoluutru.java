/**
 * author : HOAI NAM
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

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.YteSequenceDelegate;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B147_Insoluutru")
@Scope(CONVERSATION)
public class Insoluutru implements Serializable {
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
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB147=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private String soTDtuStr = "";
	private String soTDdenStr = "" ;
	private String bankham_ma = "" ;
	private String tugio = "";
	private String dengio = "";
	private Integer bankham_maso = null ; 
	
	private Integer inTheo=null;
	
	private int index=0;	
	
	@Factory("resetFormB147")
	public void initresetFormB147() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	
	public String thuchienAction(){
		XuatReport();
		resetFormB147=null;
		return "B160_Chonmenuxuattaptin";
	}
	
	public void loadMTDTu(){
		if(!soTDtuStr.trim().equals("")){
			log.info("bat dau load MTD tu ");
			YteSequenceDelegate yteUtils = YteSequenceDelegate.getInstance();
			setSoTDtuStr(yteUtils.formatMa(null, soTDtuStr));
			log.info("ket thuc load MTD tu ");
		}
	}
	
	public void loadMTDDen(){
		if(!soTDdenStr.trim().equals("")){
			log.info("bat dau load MTD den");
			YteSequenceDelegate yteUtils = YteSequenceDelegate.getInstance();
			setSoTDdenStr(yteUtils.formatMa(null, soTDdenStr));
			log.info("ket thuc load MTD den ");
		}
	}
	
	
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		log.info("Bat dau ham reset form");
		tugio = dengio = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		resetFormB147 = "";
		if(currentDate.getHours()<10)
			tugio += "0"+currentDate.getHours();
		else 
			tugio += currentDate.getHours();
		if(currentDate.getMinutes()<10)
			tugio += ":0"+currentDate.getMinutes();
		else 
			tugio += ":"+currentDate.getMinutes();
		dengio = tugio ;
		
		
		/**
		 * 
		 * 
		 * 
		 */
		
		
		tugio = "00:00";
		dengio = "23.59";
		
		setInTheo(new Integer(1));
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		tungay = sdf.format(cal.getTime());
		denngay = sdf.format(currentDate);
		soTDtuStr = "";
		soTDdenStr = "" ;
		bankham_ma = "" ;
		log.info("ket thuc ham reset form");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeTD="Insoluutru";
		log.info("Vao Method XuatReport In so luu tru");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			if(inTheo == 1)
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T13_DSBenhnhandangkykhambenh_ngaykham.jrxml";
			else if(inTheo == 2)
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T13_DSBenhnhandangkykhambenh_bankham.jrxml";
			log.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			DieuTriUtilDelegate dtUtils=DieuTriUtilDelegate.getInstance();
			SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
			params.put("TUNGAY", sdf.parse(tungay));
			params.put("DENNGAY", sdf.parse(denngay));
			params.put("TUGIO", tugio);
			params.put("DENGIO", dengio);
			if(!bankham_ma.trim().equals("")){
				params.put("BANKHAM", bankham_maso);
			}
		
			if(!soTDtuStr.equals("")) {
				log.info("set gia tri cho so tiep don tu " + Integer.parseInt(soTDtuStr.trim().substring(4,12)) );
				params.put("STDTU", Integer.parseInt(soTDtuStr.trim().substring(4,12)));
				log.info("STDTU", Integer.parseInt(soTDtuStr.trim().substring(4,12)));
			}
			
			if(!soTDdenStr.equals("")) {
				log.info("set gia tri cho so tiep don den " + Integer.parseInt(soTDdenStr.trim().substring(4,12)) );
				params.put("STDDEN", Integer.parseInt(soTDdenStr.trim().substring(4,12)));
				log.info("STDDEN", Integer.parseInt(soTDdenStr.trim().substring(4,12)));
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
			    jasperPrintTD =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","Insoluutru");
			    reportPathTD=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathTD);
			    index+=1;
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

	public Integer getInTheo() {
		return inTheo;
	}

	public void setInTheo(Integer inTheo) {
		this.inTheo = inTheo;
	}

	public String getSoTDtuStr() {
		return soTDtuStr;
	}


	public void setSoTDtuStr(String soTDtuStr) {
		this.soTDtuStr = soTDtuStr;
	}


	public String getSoTDdenStr() {
		return soTDdenStr;
	}


	public void setSoTDdenStr(String soTDdenStr) {
		this.soTDdenStr = soTDdenStr;
	}


	public String getBankham_ma() {
		return bankham_ma;
	}


	public void setBankham_ma(String bankham_ma) {
		this.bankham_ma = bankham_ma;
	}


	public Integer getBankham_maso() {
		return bankham_maso;
	}


	public void setBankham_maso(Integer bankham_maso) {
		this.bankham_maso = bankham_maso;
	}


	public String getTugio() {
		return tugio;
	}


	public void setTugio(String tugio) {
		this.tugio = tugio;
	}


	public String getDengio() {
		return dengio;
	}


	public void setDengio(String dengio) {
		this.dengio = dengio;
	}
}
