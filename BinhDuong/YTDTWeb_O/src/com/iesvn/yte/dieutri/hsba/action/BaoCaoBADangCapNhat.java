/**
 * HOAI NAM
 */

package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;


@Scope(CONVERSATION)
@Name("B221_Baocaobenhandangcapnhat")
@Synchronized(timeout = 6000000)
public class BaoCaoBADangCapNhat  implements Serializable {
	
	private static Logger log = Logger.getLogger(BaoCaoBADangCapNhat.class);
	
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
	
	private static final long serialVersionUID = 10L;
	
	private String loaiBaoCao="";
	private String doiTuongMa="";
	private String tuNgay=null;
	private String denNgay=null;
	private String thangVaoVien=null;
	private String namVaoVien=null;
	private String benhVienMa="";
	private String khoaMa="";
	
	private String ngayhientai; 
	
	@Out(required=false)
	@In(required=false)
	private String duongdanStrDT=null;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintDT = null;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB221;
	
	private int index=0;
	
	
	@Factory("resetFormB221")
	public void initReset() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	@Begin(join=true)
	public String init() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
		return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnDangCapNhat";
	}
	@End
	public void end(){
		
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		resetFormB221 = "";
		thangVaoVien=String.valueOf(currentDate.getMonth() +1);  
		namVaoVien=String.valueOf(currentDate.getYear()+1900);
		tuNgay = sdf.format(currentDate);
		denNgay = sdf.format(currentDate);
		setLoaiBaoCao("");
		setDoiTuongMa("");
		setBenhVienMa("");
		setKhoaMa("");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="BaoCaoBADangCapnhat";
		log.info("Vao Method XuatReport bao cao BA Dang cap nhat");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			if ("1".equals(loaiBaoCao.trim())){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/b01_benhanchuaduockhoacapnhatdieutri.jrxml";
			}else if ("2".equals(loaiBaoCao.trim())){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/b01_benhandangduockhoacapnhatdieutri.jrxml";
			}else if ("3".equals(loaiBaoCao.trim())){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/b01_benhanchuaduockhoacapnhatngayravien.jrxml";
			}else if ("4".equals(loaiBaoCao.trim())){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/b01_benhandaduockhoacapnhatngayravien.jrxml";
			}
			
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();        
	        params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
	        params.put("tenDonVi",IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
	        params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
	        params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
	        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
			SimpleDateFormat sqlFormatter = new SimpleDateFormat(IConstantsRes.SQL_FORMAT_DATE);
			params.put("tuNgay", formatter.parse(tuNgay));
	        params.put("denNgay", formatter.parse(denNgay));
			
	        DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
	        if(!khoaMa.equals("")){
				DmKhoa khoa = new DmKhoa();
				Object objK = dtUtils.findByMa( khoaMa , "DmKhoa", "dmkhoaMa" );
				khoa = (DmKhoa)objK;
	        	params.put("khoaMa", khoaMa);
	        	params.put("khoaMaView", khoa.getDmkhoaTen());
				 
	        }else{
	        	params.put("khoaMaView", "");
	        }
	        
			if(!doiTuongMa.equals(""))
				params.put("doiTuong", doiTuongMa);
			if(!benhVienMa.equals(""))
				params.put("benhVienMa", benhVienMa);
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","BaoCaoBADangCapnhat");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    index+=1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	
	public static String getFORMAT_DATE() {
		return FORMAT_DATE;
	}

	public static void setFORMAT_DATE(String format_date) {
		FORMAT_DATE = format_date;
	}

	public static String getFORMAT_DATE_TIME() {
		return FORMAT_DATE_TIME;
	}

	public static void setFORMAT_DATE_TIME(String format_date_time) {
		FORMAT_DATE_TIME = format_date_time;
	}

	public String getDoiTuongMa() {
		return doiTuongMa;
	}

	public void setDoiTuongMa(String doiTuongMa) {
		this.doiTuongMa = doiTuongMa;
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

	public String getThangVaoVien() {
		return thangVaoVien;
	}

	public void setThangVaoVien(String thangVaoVien) {
		this.thangVaoVien = thangVaoVien;
	}

	public String getNamVaoVien() {
		return namVaoVien;
	}

	public void setNamVaoVien(String namVaoVien) {
		this.namVaoVien = namVaoVien;
	}

	public String getBenhVienMa() {
		return benhVienMa;
	}

	public void setBenhVienMa(String benhVienMa) {
		this.benhVienMa = benhVienMa;
	}

	public String getKhoaMa() {
		return khoaMa;
	}

	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getLoaiBaoCao() {
		return loaiBaoCao;
	}

	public void setLoaiBaoCao(String loaiBaoCao) {
		this.loaiBaoCao = loaiBaoCao;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}


