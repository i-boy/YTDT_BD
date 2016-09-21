/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.vienphi.action;

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
import com.iesvn.yte.util.Utils;

@Name("B131_Indanhsachbenhnhancapcuu")
@Scope(CONVERSATION)
public class InDanhSachBenhNhanCapCuu implements Serializable {
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
	private String resetFormB3311=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private int index=0;
	
	private String maNhanVien = "";
	private Integer masoNhanVien = null;
	
	private String ngayvaovien;
	
	private Integer doiTuongMaSo;
	private String doiTuongMa;
	
	private Integer masoPLBHYT;
	private String maPLBHYT;
	
	
//	@Factory("resetFormB134_")
//	public void initResetFormB134_() {
//		log.info("init()");
//		ngayhientai = Utils.getCurrentDate();
//		resetForm();
//	}
	
	public Integer getDoiTuongMaSo() {
		return doiTuongMaSo;
	}
	public void setDoiTuongMaSo(Integer doiTuongMaSo) {
		this.doiTuongMaSo = doiTuongMaSo;
	}
	public String getDoiTuongMa() {
		return doiTuongMa;
	}
	public void setDoiTuongMa(String doiTuongMa) {
		this.doiTuongMa = doiTuongMa;
	}
	@Begin(join = true)
	public String init() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
		return "TiepDon_TiepDonKhamBenhCapCuu_InDanhSachBenhNhanCapCuu";
	}
	@End
	public void endFunc(){
		
	}
	
	
	public String thuchienAction(){
		XuatReport();
		resetFormB3311 = null;
		return "B160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		resetFormB3311 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		tungay = sdf.format(cal.getTime());
		denngay = sdf.format(currentDate);
		
		ngayvaovien = sdf.format(currentDate);
		
		setMaNhanVien("");
		
		doiTuongMaSo = null;
		doiTuongMa= null;
		
		masoPLBHYT= null;
		maPLBHYT= null;
		
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeTD="indanhsachbenhnhancapcuu";
		log.info("Vao Method XuatReport indanhsachbenhnhancapcuu");
		String baocao1=null;
		String pathTemplate = null;
		try {
			


			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T02_dsbnkhamthutien_capcuu.jrxml";
			log.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");		
			params.put("TuNgay", df.parse(tungay));
			params.put("DenNgay", df.parse(denngay));
			params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("DoiTuong", doiTuongMaSo);
			//params.put("DTBHYT", masoPLBHYT);
			
			// 20101126 bao.ttc: khong can dung vi da query theo cach: dtdmbankham_ma LIKE 'CC_'
			//params.put("BANKHAMCC_MA1", IConstantsRes.MA_BAN_KHAM_CAP_CUU_1);
			//params.put("BANKHAMCC_MA2", IConstantsRes.MA_BAN_KHAM_CAP_CUU_2);
			
			if (ngayvaovien != null && !ngayvaovien.equals("")){
				params.put("NgayVaoVien", df.parse(ngayvaovien) );
			}else{
				params.put("NgayVaoVien", null);	
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","indanhsachbenhnhancapcuu");
			    reportPathTD=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathTD);
			    index+=1;
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

	public Integer getMasoPLBHYT() {
		return masoPLBHYT;
	}
	public void setMasoPLBHYT(Integer masoPLBHYT) {
		this.masoPLBHYT = masoPLBHYT;
	}
	public String getMaPLBHYT() {
		return maPLBHYT;
	}
	public void setMaPLBHYT(String maPLBHYT) {
		this.maPLBHYT = maPLBHYT;
	}
	public String getNgayvaovien() {
		return ngayvaovien;
	}
	public void setNgayvaovien(String ngayvaovien) {
		this.ngayvaovien = ngayvaovien;
	}
}
