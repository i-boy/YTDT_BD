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

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B124_Tonghopthuocvtthdadung")
@Scope(CONVERSATION)
public class Tonghopthuocvtthdadung implements Serializable {
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
	private String resetFormB124=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private int index=0;
	private String bankham_ma = "";
	private String loaithuoc_ma = "";
	private Integer bankham_maso=null;
	private Integer loaithuoc_maso=null;
	
	public Integer getLoaithuoc_maso() {
		return loaithuoc_maso;
	}
	public void setLoaithuoc_maso(Integer loaithuocMaso) {
		loaithuoc_maso = loaithuocMaso;
	}
	
	
	@Begin(join=true)
	public String init(String loaiKham) {
		if ("CCL".equals(loaiKham)){
			bankham_ma = "CCL";
		} else if ("CCN".equals(loaiKham)){
			bankham_ma = "CCN";
		} else {
			bankham_ma = "";
		}
		initTemp();
		return "TiepDon_KhamBenh_TongHopThuocVTTHDaDung";
	}
	@Factory("resetFormB124")
	public void initTemp() {
		ngayhientai = Utils.getCurrentDate();
		resetFormB124 = "";
		resetForm();
	}
	@End
	public void endFunc(){
		
	}
	
	public String thuchienAction(){
		XuatReport();
		resetFormB124= null ; 
		return "B160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		tungay = sdf.format(cal.getTime());
		denngay = sdf.format(currentDate);
		//setBankham_ma("");		
		setLoaithuoc_ma("");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeTD="Tonghopthuocvtthdadung";
		log.info("Vao Method XuatReport Tong hop thuoc vtth da dung");
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T03_Tinhhinhsudungthuocvtth.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			if(!bankham_ma.trim().equals("")){
				DtDmBanKham bankham = new DtDmBanKham();
				params.put("BANKHAMMASO", bankham_maso);
				Object obj = dtUtils.findByMa(bankham_ma , "DtDmBanKham", "dtdmbankhamMa");
				bankham = (DtDmBanKham)obj;
				params.put("BANKHAM", bankham.getDtdmbankhamTen());
			}
			params.put("TUNGAYDATE" , sdf.parse(tungay));
			params.put("DENNGAYDATE", sdf.parse(denngay));
			
			log.info("da thay file thanh " + loaithuoc_ma.trim() );
			log.info("da thay file thanh " + loaithuoc_maso );
			log.info("da thay file bankham_maso " + bankham_maso );
			if(!loaithuoc_ma.trim().equals("")){
				params.put("LOAITHUOCMASO", loaithuoc_maso);
			}
			else{
				params.put("LOAITHUOCMASO", null);
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","Tonghopthuocvtthdadung");
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

	public String getBankham_ma() {
		return bankham_ma;
	}

	public void setBankham_ma(String bankham_ma) {
		this.bankham_ma = bankham_ma;
	}

	public String getLoaithuoc_ma() {
		return loaithuoc_ma;
	}
	public void setLoaithuoc_ma(String loaithuocMa) {
		loaithuoc_ma = loaithuocMa;
	}
	public Integer getBankham_maso() {
		return bankham_maso;
	}

	public void setBankham_maso(Integer bankham_maso) {
		this.bankham_maso = bankham_maso;
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
}
