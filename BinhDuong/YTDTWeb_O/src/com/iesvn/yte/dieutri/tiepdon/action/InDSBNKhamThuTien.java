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
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("InDSBNKhamThuTien")
@Scope(CONVERSATION)
public class InDSBNKhamThuTien implements Serializable {
	
	private static final long serialVersionUID = -619143681433776530L;

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
	private String resetFormB114=null;
	
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	private Integer doituong = null;
	private Integer doituongbhyt = null;
	private String doituong_ma = null;
	private String doituongbhyt_ma = null;
	
	private String chonloaibc = "0";
	private Integer dmdieutriMaso = null;
	private String dmdieutriMa = null;
	private String loadMenu = ""; // chon menu load tren giao dien: "NgT" "VP"
	
	private int index=0;	
	
	@Factory("resetFormB114")
	public void initresetFormB147() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	@Begin (join = true)
	public String init(String topMenu) {
		loadMenu = topMenu; // chon menu load tren giao dien: "NgT" "VP"
		resetForm();
		return "TiepDon_TiepDonBenhNhan_InDanhSachBNKhamThuTien";
	}
	@End
	public void end(){
		
	}
	
	public String thuchienAction(){
		XuatReport();
		resetFormB114=null;
		return "B160_Chonmenuxuattaptin";
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		log.info("Bat dau ham reset form");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		resetFormB114 = "";
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		tungay = sdf.format(cal.getTime());
		denngay = sdf.format(currentDate);
		doituong_ma = "";
		doituongbhyt_ma = "";
		setChonloaibc("0");
		dmdieutriMa = "";
		log.info("ket thuc ham reset form");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeTD="indsbnkhamthutien";
		log.info("Vao Method XuatReport In danh sach benh nhan kham thu tien");
		String baocao1=null;
		String pathTemplate = null;
		try {
			log.info("bat dau method XuatReport ");
			
			if (chonloaibc.equals("1")) { // Chua Thanh toan
				log.info("##### Loai BC: Chua Thanh toan");
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T02_dsbnkhamthutien_ChuaTT.jrxml";
			} else if (chonloaibc.equals("2")) { // Da thanh toan
				log.info("##### Loai BC: Da thanh toan");
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T02_dsbnkhamthutien_DaTT.jrxml";
			} else {
				log.info("##### Loai BC: Tat ca BN");
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/T02_dsbnkhamthutien.jrxml";
			}
			log.info("da thay file templete " + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");		
			params.put("TuNgay", df.parse(tungay));
			params.put("DenNgay", df.parse(denngay));
			params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			log.info("***** GiaiQuyet: " + dmdieutriMaso);
			if (dmdieutriMaso != null)
				params.put("GiaiQuyet", dmdieutriMaso);
			else
				params.put("GiaiQuyet", null);
			
			log.info("***** DoiTuong "+doituong_ma);
			if(doituong_ma!=null && !doituong_ma.equals(""))
				params.put("DoiTuong", doituong_ma);
			else
				params.put("DoiTuong", null);
			
			log.info("***** DTBHYT "+doituongbhyt_ma);
			if(doituongbhyt_ma!=null && !doituongbhyt_ma.equals(""))
				params.put("DTBHYT", doituongbhyt_ma);
			else
				params.put("DTBHYT", null);
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","indsbnkhamthutien");
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

	public Integer getDoituong() {
		return doituong;
	}

	public void setDoituong(Integer doituong) {
		this.doituong = doituong;
	}

	public Integer getDoituongbhyt() {
		return doituongbhyt;
	}

	public void setDoituongbhyt(Integer doituongbhyt) {
		this.doituongbhyt = doituongbhyt;
	}

	public String getDoituong_ma() {
		return doituong_ma;
	}

	public void setDoituong_ma(String doituong_ma) {
		this.doituong_ma = doituong_ma;
	}

	public String getDoituongbhyt_ma() {
		return doituongbhyt_ma;
	}

	public void setDoituongbhyt_ma(String doituongbhyt_ma) {
		this.doituongbhyt_ma = doituongbhyt_ma;
	}

	public String getChonloaibc() {
		return chonloaibc;
	}

	public void setChonloaibc(String chonloaibc) {
		this.chonloaibc = chonloaibc;
	}

	public Integer getDmdieutriMaso() {
		return dmdieutriMaso;
	}

	public void setDmdieutriMaso(Integer dmdieutriMaso) {
		this.dmdieutriMaso = dmdieutriMaso;
	}

	public String getDmdieutriMa() {
		return dmdieutriMa;
	}

	public void setDmdieutriMa(String dmdieutriMa) {
		this.dmdieutriMa = dmdieutriMa;
	}

	public String getLoadMenu() {
		return loadMenu;
	}

	public void setLoadMenu(String loadMenu) {
		this.loadMenu = loadMenu;
	}
	
}
