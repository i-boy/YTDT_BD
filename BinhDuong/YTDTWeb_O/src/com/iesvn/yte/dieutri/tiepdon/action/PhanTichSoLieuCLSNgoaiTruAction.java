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
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B145_Phantichsolieuclsngoaitru")
@Scope(CONVERSATION)
public class PhanTichSoLieuCLSNgoaiTruAction implements Serializable {
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
	private String resetFormB145=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private Integer nhanvien_maso = null;
	private Integer doituong_maso = null;
	private Integer loaicls_maso = null;
	private String nhanvien_ma = "";
	private String doituong_ma = "";
	private String loaicls_ma = "";
	private String ngayhientai;
	

	private int index=0;	
	
	@Factory("resetFormB145")
	public void initresetFormB145() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	@Begin (join = true)
	public String init() {
		resetForm();
		return "TiepDon_PhanTichBaoCao_PhanTichSoLieuCanLamSan";
	}
	@End
	public void end(){
		
	}
	
	public String thuchienAction(){
		XuatReport();
		resetFormB145=null;
		return "B160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		log.info("Bat dau ham reset form");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		resetFormB145 = "";
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);
		
		tungay = sdf.format(cal.getTime());
		denngay = sdf.format(currentDate);
		nhanvien_ma = "";
		doituong_ma = "";
		loaicls_ma = "";
		log.info("ket thuc ham reset form");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeTD="ptsolieuclsngoaitru";
		log.info("Vao Method XuatReport phan tich so lieu can lam san ngoai tru");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T11_PTSolieuthuchiencanlamsangngoaitruccl.jrxml";
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
			
			if(!nhanvien_ma.trim().equals("")){
				log.info("set gia trin cho nhan vien " + nhanvien_ma); 
				params.put("THUNGAN", nhanvien_maso);
			}
			if(!doituong_ma.trim().equals("")){
				log.info("set gia trin cho doi tuong " + doituong_ma);
				params.put("DOITUONG", doituong_maso);
			}
			if(!loaicls_ma.trim().equals("")){
				log.info("set gia trin cho loai CLS " + loaicls_ma);
				params.put("LOAICLS", loaicls_maso);
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","ptsolieuclsngoaitru");
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

	public Integer getNhanvien_maso() {
		return nhanvien_maso;
	}

	public void setNhanvien_maso(Integer nhanvien_maso) {
		this.nhanvien_maso = nhanvien_maso;
	}

	public Integer getDoituong_maso() {
		return doituong_maso;
	}

	public void setDoituong_maso(Integer doituong_maso) {
		this.doituong_maso = doituong_maso;
	}

	public Integer getLoaicls_maso() {
		return loaicls_maso;
	}

	public void setLoaicls_maso(Integer loaicls_maso) {
		this.loaicls_maso = loaicls_maso;
	}

	public String getNhanvien_ma() {
		return nhanvien_ma;
	}

	public void setNhanvien_ma(String nhanvien_ma) {
		this.nhanvien_ma = nhanvien_ma;
	}

	public String getDoituong_ma() {
		return doituong_ma;
	}

	public void setDoituong_ma(String doituong_ma) {
		this.doituong_ma = doituong_ma;
	}

	public String getLoaicls_ma() {
		return loaicls_ma;
	}

	public void setLoaicls_ma(String loaicls_ma) {
		this.loaicls_ma = loaicls_ma;
	}
}
