/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
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
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B237_Sochuyenvien")
@Scope(CONVERSATION)
public class Sochuyenvien implements Serializable {
	@Logger
	private Log log;
	
	@In(required=false)
	@Out(required=false)
	private String duongdanStrDT = null;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT = null;
	
	@In(required=false)
	@Out(required=false)
	JasperPrint jasperPrintDT = null;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB237=null;

	private int index=0;
	private String ngayhientai="";
	private String tungaystr="";
	private String denngaystr="";
	private String lydo_ma="";
	private String tainan_ma="";
	private String benhICD_ma="";
	private String khoa_ma="";
	private String benhvien_ma="";
	private Integer lydo_maso=null;
	private Integer tainan_maso=null;
	private Integer benhICD_maso=null;
	private Integer khoa_maso=null;
	private Integer benhvien_maso=null;
	
	@Factory("resetFormB237")
	public void initresetFormB237() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	@Begin (join = true)
	public String init() {
		log.info("init()");	
		resetForm();
		return "DieuTri_LapVanBanTheoMau_SoChuyenVien";
	}
	
	

	@End
	public void end(){
		
	}
	
	public String thuchienAction(){
		XuatReport();
		resetFormB237=null;
		return "B260_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		tungaystr="";
		denngaystr="";
		lydo_ma="";
		tainan_ma="";
		benhICD_ma="";
		khoa_ma="";
		benhvien_ma="";
		lydo_maso=null;
		tainan_maso=null;
		benhICD_maso=null;
		khoa_maso=null;
		benhvien_maso=null;
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="Sochuyenvien";
		log.info("Vao Method XuatReport so chuyen vien");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/sochuyenvien.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			if(!tungaystr.trim().equals(""))
				params.put("TUNGAYDATE", sdf.parse(tungaystr));
			if(!denngaystr.trim().equals(""))
				params.put("DENNGAYDATE", sdf.parse(denngaystr));
			if(!lydo_ma.trim().equals(""))
				params.put("LYDOMASO" , lydo_maso );
			if(!tainan_ma.trim().equals(""))
				params.put("TAINANMASO", tainan_maso );
			if(!benhICD_ma.trim().equals(""))
				params.put("BENHMASO", benhICD_maso );
			if(!khoa_ma.trim().equals(""))
				params.put("KHOAMASO", khoa_maso );
			if(!benhvien_ma.trim().equals(""))
				params.put("BVMASO", benhvien_maso );
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","Sochuyenvien");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    index+= 1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public String getTungaystr() {
		return tungaystr;
	}

	public void setTungaystr(String tungaystr) {
		this.tungaystr = tungaystr;
	}

	public String getDenngaystr() {
		return denngaystr;
	}

	public void setDenngaystr(String denngaystr) {
		this.denngaystr = denngaystr;
	}

	public String getLydo_ma() {
		return lydo_ma;
	}

	public void setLydo_ma(String lydo_ma) {
		this.lydo_ma = lydo_ma;
	}

	public String getTainan_ma() {
		return tainan_ma;
	}

	public void setTainan_ma(String tainan_ma) {
		this.tainan_ma = tainan_ma;
	}

	public String getBenhICD_ma() {
		return benhICD_ma;
	}

	public void setBenhICD_ma(String benhICD_ma) {
		this.benhICD_ma = benhICD_ma;
	}

	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}

	public String getBenhvien_ma() {
		return benhvien_ma;
	}

	public void setBenhvien_ma(String benhvien_ma) {
		this.benhvien_ma = benhvien_ma;
	}

	public Integer getLydo_maso() {
		return lydo_maso;
	}

	public void setLydo_maso(Integer lydo_maso) {
		this.lydo_maso = lydo_maso;
	}

	public Integer getTainan_maso() {
		return tainan_maso;
	}

	public void setTainan_maso(Integer tainan_maso) {
		this.tainan_maso = tainan_maso;
	}

	public Integer getBenhICD_maso() {
		return benhICD_maso;
	}

	public void setBenhICD_maso(Integer benhICD_maso) {
		this.benhICD_maso = benhICD_maso;
	}

	public Integer getKhoa_maso() {
		return khoa_maso;
	}

	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}

	public Integer getBenhvien_maso() {
		return benhvien_maso;
	}

	public void setBenhvien_maso(Integer benhvien_maso) {
		this.benhvien_maso = benhvien_maso;
	}

}
