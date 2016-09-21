/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.vienphi.action;

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
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B3321_Solieubenhnhanduocxetmiengiam")
@Scope(CONVERSATION)
public class Solieubenhnhanduocxetmiengiam implements Serializable {
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
	private String resetFormB3321=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngaybaocao=null;
	private String ngayhientai;
	private static int index=0;
	private boolean inchitiet;
	
	@Out(required=false)
	@In(required=false)
	private String loaibaocao;
	
	@Begin(join=true)
	public String init(String loaibaocao){
		log.info("init()");
		initresetFormB3321();
		this.loaibaocao = loaibaocao;
		return "BaoCaoVienPhi_HoSoBaoCao_SoLieuBNDuocXetMienGiam";
	}
	
	@End
	public void EndFunc(){
		
	}
	
	@Factory("resetFormB3321")
	public void initresetFormB3321() {
		log.info("init()");
		
		ngayhientai = Utils.getCurrentDate();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = ngaybaocao = sdf.format(currentDate);
		resetForm();
	}
	
	public String thuchienAction(){
		XuatReport();
		resetFormB3321 = "";
		return "B3360_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		resetFormB3321 = "";
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = ngaybaocao = sdf.format(currentDate);
		inchitiet = false;
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="Solieubenhnhanduocxetmiengiam";
		log.info("Vao Method XuatReport So lieu benh nhan duoc xet mien giam");
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate ="" ;
			if ("v05BD".equals( loaibaocao)){
				//pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V05_ChiTietTienMienGiamToanVien.jrxml";
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V05_ChiTietTienMienGiamToanVien_19112010.jrxml";
			}else if ("v06BD".equals( loaibaocao)){
				if(inchitiet) {
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V06_BaoCaoSoTienBNChuaThanhToan_ChiTiet.jrxml";
				} else {
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V06_BaoCaoSoTienBNChuaThanhToan.jrxml";
				}
			}else if ("V11BD".equals( loaibaocao)){
					//pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V11BD_Chitiettinhhinhthuvienphi.jrxml";
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V11_Chitiettinhhinhthuvienphi.jrxml";
			}else{
				return ;
			}
			
			
			
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("TuNgay", sdf.parse(tungay));			
			params.put("DenNgay", sdf.parse(denngay));
			if ("v06BD".equals( loaibaocao)) {
				params.put("DenNgay", sdf.parse(ngaybaocao));
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,Solieubenhnhanduocxetmiengiam.index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Solieubenhnhanduocxetmiengiam");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    Solieubenhnhanduocxetmiengiam.index+=1;
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

	public static void setIndex(int index) {
		Solieubenhnhanduocxetmiengiam.index = index;
	}

	public static int getIndex() {
		return index;
	}

	public String getNgaybaocao() {
		return ngaybaocao;
	}

	public void setNgaybaocao(String ngaybaocao) {
		this.ngaybaocao = ngaybaocao;
	}

	public boolean isInchitiet() {
		return inchitiet;
	}

	public void setInchitiet(boolean inchitiet) {
		this.inchitiet = inchitiet;
	}
	
}
