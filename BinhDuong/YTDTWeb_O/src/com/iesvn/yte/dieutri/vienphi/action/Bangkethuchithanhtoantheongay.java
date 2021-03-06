/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
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
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B3312_Bangkethuchithanhtoantheongay")
@Scope(CONVERSATION)
public class Bangkethuchithanhtoantheongay implements Serializable {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB3312=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private int index=0;
	
	private String maNhanVien = "";
	private Integer masoNhanVien = null;
	private String chonSL = "";
	private Integer cum = null;
	private String tugio = "";
	private String dengio = "";
	@Factory("resetFormB3312")
	public void initresetFormB3312() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	@Begin(join = true)
	public String init() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
		return "BaoCaoVienPhi_SoLieuThanhToan_BangKeThuChiThanhToanTheoNgay";
	}
	
	@End
	public void endFunc(){
		
	}
	
	public String thuchienAction(){
		XuatReport();
		resetFormB3312 = null;
		return "B3360_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		resetFormB3312 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		setMaNhanVien("");
		setChonSL("r1");
		setCum(null);
		tugio = dengio = "";
		
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="Bangkethuchithanhtoantheongay";
		log.info("Vao Method XuatReport bang ke thu chi thanh toan theo ngay");
		String baocao1=null;
		String pathTemplate=null;
		try {
			log.info("bat dau method XuatReport ");
			if(chonSL.equals("r1")){
				log.info("==== So lieu thu ======");
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V02_Bangkethanhtoanthuvienphinoitru.jrxml";
			}else if(chonSL.equals("r2")){
				log.info("==== So lieu chi ======");
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V03_Bangkethanhtoanchivienphinoitru.jrxml";
			}
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			String tu_gio_ngay = tungay + (tugio.trim().length() > 0 ? " " + tugio : " 00:00");
			String den_gio_ngay = denngay + (dengio.trim().length() > 0 ? " " + dengio : " 23:59");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(tu_gio_ngay));
			Timestamp tstamTuNgay = new Timestamp(cal.getTimeInMillis());			
			cal.setTime(sdf.parse(den_gio_ngay));
			Timestamp tstamDenNgay = new Timestamp(cal.getTimeInMillis());
			
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			//params.put("TuNgay", sdf.parse(tungay));
			//params.put("DenNgay", sdf.parse(denngay));
			params.put("TuNgay", tstamTuNgay);
			params.put("DenNgay", tstamDenNgay);
			if(cum != null){
				params.put("CUM", cum);
			}
			if(!maNhanVien.equals("")){
				params.put("nhanvienMa", maNhanVien);
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
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Bangkethuchithanhtoantheongay");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
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

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public Integer getMasoNhanVien() {
		return masoNhanVien;
	}

	public void setMasoNhanVien(Integer masoNhanVien) {
		this.masoNhanVien = masoNhanVien;
	}

	public String getChonSL() {
		return chonSL;
	}

	public void setChonSL(String chonSL) {
		this.chonSL = chonSL;
	}

	public Integer getCum() {
		return cum;
	}

	public void setCum(Integer cum) {
		this.cum = cum;
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
