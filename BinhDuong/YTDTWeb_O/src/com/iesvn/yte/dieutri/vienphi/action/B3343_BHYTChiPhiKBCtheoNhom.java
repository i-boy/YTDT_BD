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
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.security.Restrict;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;


@Scope(CONVERSATION)
@Name("B3343_BHYTChiPhiKBCtheoNhom")
@Synchronized(timeout = 6000000)
public class B3343_BHYTChiPhiKBCtheoNhom implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(B3343_BHYTChiPhiKBCtheoNhom.class);
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	private int index=0;
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	
	private String chonloaibc = "";
	private String chonNhomCP = "";
	
	private String fileExport =null;
	private boolean reportFinish = false;
	
	@Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
	@Begin(join = true)
	public String init() throws Exception {
		log.info("***Starting init ***");
		emtyData();
		log.info("***Finished init ***");

		SimpleDateFormat format = new SimpleDateFormat(Utils.FORMAT_DATE);
		ngayhientai = format.format(new Date());
		
		return "BaoCaoVienPhi_BaoCaoBHYT_BHYTChiPhiKBCtheoNhom";
	}
	
	@End
	public void endFunct(){
		
	}
	public void emtyData() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		setChonloaibc("l1");
		setChonNhomCP("0");
	}

	public void resetValue() {
		emtyData();
	}
	
	public String thuchienAction(){
		XuatReport();
		return "B3360_Chonmenuxuattaptin";
	}
	
	public void XuatReport() {
		reportTypeVP="BHYTChiPhiKBCtheoNhom";
		log.info("Vao Method XuatReport Chi phi BHYT theo nhom chi phi");
		String baocao1=null;
		String pathTemplate = null;
		try {
			if (chonloaibc.equals("l1")) {//Ngoai tru
				log.info("##### Loai BC: Ngoai tru");
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BC2021BHYTNgoaitru.jrxml";
			} else { //Noi tru
				log.info("##### Loai BC: Noi tru");
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/BC2021BHYTNoitru.jrxml";
			}
			log.info("da thay file templete " + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("tuNgay", sdf.parse(tungay));
			params.put("denNgay", sdf.parse(denngay));
			int loai = 0;
			if (chonNhomCP != null && !chonNhomCP.equals("")){
				loai = Integer.parseInt(chonNhomCP);
			}
			params.put("loai", loai);
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","BHYTChiPhiKBCtheoNhom");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+=1;
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	
	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		B3343_BHYTChiPhiKBCtheoNhom.log = log;
	}

	public void nhaplai() throws Exception {
		ResetForm();
	}

	// Ham reset form
	private void ResetForm() {
		log.info("Begining ResetForm(): ");
		emtyData();
		log.info("End ResetForm(): ");
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

	public void setChonloaibc(String chonloaibc) {
		this.chonloaibc = chonloaibc;
	}

	public String getChonloaibc() {
		return chonloaibc;
	}

	public String getChonNhomCP() {
		return chonNhomCP;
	}

	public void setChonNhomCP(String chonNhomCP) {
		this.chonNhomCP = chonNhomCP;
	}

	public void setFileExport(String fileExport) {
		this.fileExport = fileExport;
	}

	public String getFileExport() {
		return fileExport;
	}
	
	public void setReportFinish(boolean reportFinish) {
		this.reportFinish = reportFinish;
	}

	public boolean isReportFinish() {
		return reportFinish;
	}
}


