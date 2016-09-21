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

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;


import com.iesvn.yte.XuatReportUtil;

import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("B3341_DSBenhnhanchothanhtoan")
@Synchronized(timeout = 6000000)
public class B3341_DSBenhnhanchothanhtoan implements Serializable {
	
	private String tungay;
	private String denngay;
	private String ngayhientai;
	private String makhoa;	
	private Integer masokhoa;
	private String madoituong;	
	private Integer masodoituong;
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String resetFormB3341=null;
	private static Logger log = Logger.getLogger(B3341_DSBenhnhanchothanhtoan.class);
	@Factory("resetFormB3341")
	public void init() {
		log.info("init()");
		resetForm();		
	}
	
	public void resetForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		tungay = sdf.format(new Date());
		denngay = sdf.format(new Date());
		ngayhientai = sdf.format(new Date());
		makhoa = madoituong = "";		
		resetFormB3341 = "";
		masokhoa = masodoituong = new Integer("0");
	}

	int index = 0;
	public String thuchienAction(){
		log.info("Begin thuchienAction(), makhoa: " + makhoa);
		reportTypeVP="B3341_DSBenhnhanchothanhtoan";
		log.info("Vao Method XuatReport B3341_DSBenhnhanchothanhtoan");
		String baocao1=null;
		String pathTemplate = null;
		try {
			//logger.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/B3341_danhsachbenhnhanchothanhtoan.jrxml";
			/*if (makhoa.trim().length() > 0) {
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/B3341_danhsachbenhnhanchothanhtoan_theokhoa.jrxml";
			} else {
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/B3341_danhsachbenhnhanchothanhtoan.jrxml";
			}*/
			log.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			//logger.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			//logger.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			//logger.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
						
			params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("tuNgay", sdf.parse(tungay));
			params.put("denNgay", sdf.parse(denngay));
			
			params.put("khoaMaso", masokhoa);
			params.put("doituongMaso", masodoituong);
			
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","B3341_DSBenhnhanchothanhtoan");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    //logger.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+=1;
			    //logger.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		
		return "B3360_Chonmenuxuattaptin";
		
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

	public String getMakhoa() {
		return makhoa;
	}

	public void setMakhoa(String makhoa) {
		this.makhoa = makhoa;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public Integer getMasokhoa() {
		return masokhoa;
	}

	public void setMasokhoa(Integer masokhoa) {
		this.masokhoa = masokhoa;
	}

	public String getMadoituong() {
		return madoituong;
	}

	public void setMadoituong(String madoituong) {
		this.madoituong = madoituong;
	}

	public Integer getMasodoituong() {
		return masodoituong;
	}

	public void setMasodoituong(Integer masodoituong) {
		this.masodoituong = masodoituong;
	}
	
}


