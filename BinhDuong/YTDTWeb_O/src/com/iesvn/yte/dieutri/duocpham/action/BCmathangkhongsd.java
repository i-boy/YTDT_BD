/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

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

@Name("B4136_Mathangkhongsd")
@Scope(CONVERSATION)
public class BCmathangkhongsd implements Serializable {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	@In(scope=ScopeType.SESSION,required=false)
	@Out(scope=ScopeType.SESSION,required=false)
	private String resetFormB4136=null;
	
	private String ngayhientai=null;
	private String denngay=null;
	private int index=0;
	private Integer lthuoc_maso=null;
	private Integer pthuoc_maso=null;
	private Integer khoa_maso=null;
	private Integer nguonct_maso=null;
	private Integer nguonkp_maso=null;
	private String lthuoc_ma=null;
	private String pthuoc_ma=null;
	private String khoa_ma=null;
	private String nguonct_ma=null;
	private String nguonkp_ma=null;
	private Integer thangksd=null;
	
	String dmKhoXuat = "";
	
	
	public String getDmKhoXuat() {
		return dmKhoXuat;
	}
	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	
	@Factory("resetFormB4136")
	public void init() {
		log.info("init()");
		resetForm();
		ngayhientai=Utils.getCurrentDate();
		denngay=Utils.getCurrentDate();
	}
	
	@Begin (join = true)
	public String init(String kho) {
		dmKhoXuat = kho;
		return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangKhongSuDung";
	}
	@End
	public void end(){
		
	}
	
	public String thuchienAction(){
		XuatReport();
		resetForm();
		resetFormB4136=null;
		return "B4160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		setLthuoc_ma(null);
		setPthuoc_ma(null);
		setNguonct_ma(null);
		setNguonkp_ma(null);
		lthuoc_maso=null;
		pthuoc_maso=null;
		nguonct_maso=null;
		nguonkp_maso=null;
		setThangksd(null);
		setDenngay(Utils.getCurrentDate());
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeKC="Mathangkhongsd";
		log.info("Vao Method XuatReport mat hang khong su dung");
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D09_danhmucmathangkhongsudung.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			if(thangksd!=null){
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(sdf.parse(denngay));
				calendar.add(calendar.MONTH, -thangksd);
				Date tungay=calendar.getTime();					
				
				String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;		
				Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(strInputTonDauDate));
				c.add(Calendar.DATE, 1);
				strInputTonDauDate = sdf.format(c.getTime());
				Date dNgayTonDau = sdf.parse(strInputTonDauDate);
				if(tungay.before(dNgayTonCuoi) || tungay.equals(dNgayTonCuoi)){
					params.put("TUNGAYDATE", dNgayTonDau);
				}else{
					params.put("TUNGAYDATE", tungay);
				}
				params.put("DENNGAYDATE", sdf.parse(denngay));
				
				log.info("tu ngay " + tungay);
				log.info("den ngay " + denngay);
				
				log.info("Thoi gian khong du dung " + "Trong "+thangksd.toString()+" " + IConstantsRes.THANG);
				if (thangksd ==null || thangksd.equals("")){
					params.put("THOIGIAN", null);
				}else{
					params.put("THOIGIAN",thangksd);
				}
				params.put("THOIGIAN", ""+thangksd.toString()+"");
			}
			
			log.info("truyen tham so loai thuoc " + lthuoc_maso);
			if(lthuoc_maso!=null){
				params.put("LOAITHUOCMASO", lthuoc_maso);
			}
			log.info("truyen tham so  phan loai thuoc " + pthuoc_maso);
			if(pthuoc_maso!=null){
				params.put("PLTHUOCMASO", pthuoc_maso);
			}
			log.info("truyen tham so  khoa " + khoa_maso);
			if(khoa_maso!=null){
				params.put("DMKHOAMASO", khoa_maso);
			}
			log.info("truyen tham so  nguon chuong trinh " + nguonct_maso);
			if(nguonct_maso!=null){
				params.put("NGUONCTMASO", nguonct_maso);
			}
			log.info("truyen tham so  nguon kinh phi " + nguonkp_maso);
			if(nguonkp_maso!=null){
				params.put("NGUONKPMASO", nguonkp_maso);
			}
			
			log.info("================= ");
			Class.forName(IConstantsRes.ORACLE_DRIVER);
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Mathangkhongsd");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
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

	public Integer getLthuoc_maso() {
		return lthuoc_maso;
	}

	public void setLthuoc_maso(Integer lthuoc_maso) {
		this.lthuoc_maso = lthuoc_maso;
	}

	public Integer getPthuoc_maso() {
		return pthuoc_maso;
	}

	public void setPthuoc_maso(Integer pthuoc_maso) {
		this.pthuoc_maso = pthuoc_maso;
	}

	public Integer getKhoa_maso() {
		return khoa_maso;
	}

	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}

	public Integer getNguonct_maso() {
		return nguonct_maso;
	}

	public void setNguonct_maso(Integer nguonct_maso) {
		this.nguonct_maso = nguonct_maso;
	}

	public Integer getNguonkp_maso() {
		return nguonkp_maso;
	}

	public void setNguonkp_maso(Integer nguonkp_maso) {
		this.nguonkp_maso = nguonkp_maso;
	}

	public String getLthuoc_ma() {
		return lthuoc_ma;
	}

	public void setLthuoc_ma(String lthuoc_ma) {
		this.lthuoc_ma = lthuoc_ma;
	}

	public String getPthuoc_ma() {
		return pthuoc_ma;
	}

	public void setPthuoc_ma(String pthuoc_ma) {
		this.pthuoc_ma = pthuoc_ma;
	}

	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}

	public String getNguonct_ma() {
		return nguonct_ma;
	}

	public void setNguonct_ma(String nguonct_ma) {
		this.nguonct_ma = nguonct_ma;
	}

	public String getNguonkp_ma() {
		return nguonkp_ma;
	}

	public void setNguonkp_ma(String nguonkp_ma) {
		this.nguonkp_ma = nguonkp_ma;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setThangksd(Integer thangksd) {
		this.thangksd = thangksd;
	}

	public Integer getThangksd() {
		return thangksd;
	}

	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}

	public String getDenngay() {
		return denngay;
	}
}
