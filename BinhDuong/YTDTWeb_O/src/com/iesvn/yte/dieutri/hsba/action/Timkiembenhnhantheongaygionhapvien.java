/**
 * author : HOAI NAM
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
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B228_Timkiembenhnhantheongaygionhapvien")
@Scope(CONVERSATION)
public class Timkiembenhnhantheongaygionhapvien implements Serializable {
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
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB228=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private Integer khoa_maso=null;
	private String khoa_ma="";
	private int index=0;
	
	
	
	@Factory("resetFormB228")
	public void initresetFormB4135() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	@Begin (join = true)
	public String init() {
		return "DieuTri_BaoCaoHoatDongDieuTri_TimDanhSachBenhNhanTheoNgayGioNhapVien";
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
		
		resetFormB228 = "";
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		String strDate = sdf.format(currentDate);
		String firstDay = strDate.substring(2);
		tungay = "01" + firstDay;
		denngay = strDate;
		khoa_maso=null;
		khoa_ma="";
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="Timkiembenhnhantheongayhionhapvien";
		log.info("Vao Method XuatReport Tim kiem benh nhan theo ngay gio nhap vien");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Danhsachbenhnhan_noitru.jrxml";
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();        
	        params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
	        params.put("BENHVIEN",IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
	        params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
	        params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        params.put("TUNGAY", sdf.parse(tungay));
	        params.put("DENNGAY", sdf.parse(denngay));
	        log.info("khoa ma so " + khoa_maso);
	        if(!khoa_ma.equals("")){
	        	params.put("KHOAMASO", khoa_maso);
	        	DmKhoa dmkhoa = new DmKhoa();
	        	DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
	        	Object obj = dtUtils.findByMa(khoa_ma, "DmKhoa", "dmkhoaMa");
	        	dmkhoa = (DmKhoa)obj;
	        	params.put("KHOA", dmkhoa.getDmkhoaTen());
	        }else{
	        	params.put("KHOAMASO", null);
	        	params.put("KHOA", "");
	        	
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
			    jasperPrintDT =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","Timkiembenhnhantheongayhionhapvien");
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
		resetFormB228 = null;
		return "B260_Chonmenuxuattaptin";
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

	public Integer getKhoa_maso() {
		return khoa_maso;
	}

	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}


	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}
}
