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
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmKcbBhyt;
import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3340_DSNguoiBenhKCBNgoaiTru_Mau97aHD")
@Synchronized(timeout = 6000000)
public class BHYT_DanhSachNguoiBenhBHYTNgoaiTruDeNghiThanhToanMauC97aHD
		implements Serializable {
	private static Logger log = Logger
			.getLogger(B3335_BaoCaoBHYTNgoaiTru.class);

	private String thoigian_thang = null;
	private String thoigian_nam = null;
	private String tungay = null;
	private String denngay = null;
	private String ngayhientai;

	
	private Integer maSoKCBBHYT = null;
	private String maKCBBHYT = "";
	
	private Integer maSoPLBHYT = null;
	private String maPLBHYT = "";

	private Integer tuyen;

	@Out(required = false)
	@In(required = false)
	private String reportPathVP = null;

	@Out(required = false)
	@In(required = false)
	private String reportTypeVP = null;

	@Out(required = false)
	@In(required = false)
	JasperPrint jasperPrintVP = null;
	
	@Out(required = false)
	@In(required = false)
	String mauSo = null;
	

	private int index = 0;

	@Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
	@Begin(join = true)
	public String init(String mauSoTemp) throws Exception {
		log.info("***Starting init ***");
		emtyData();
		log.info("***Finished init ***");

		SimpleDateFormat format = new SimpleDateFormat(Utils.FORMAT_DATE);
		
		ngayhientai = format.format(new Date());
		
		mauSo = mauSoTemp;
		
		return "BaoCaoVienPhi_BaoCaoBHYT_BHYT_DanhSachNguoiBenhKCBNgoaiTruDeNghiThanhToanMauC98aHD";
	}

	@End
	public void endFunct() {

	}

	public void emtyData() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
		thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);

		setMaPLBHYT("");
		setTuyen(null);
		
		maSoKCBBHYT = null;
		maKCBBHYT = "";

	}

	public void resetValue() {
		emtyData();
	}

	public String thuchienAction() {
		XuatReport();
		return "B3360_Chonmenuxuattaptin";
	}

	/**
	 * xuat report
	 */
	public void XuatReport() {
		reportTypeVP = "V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD";
		log.info("Vao Method XuatReport bao cao V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD");
		String baocao1 = null;
		String pathTemplate = null;
		try {
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			if ("C97aHD".equals(mauSo)){
//				pathTemplate = IConstantsRes.PATH_BASE
//				+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//				+ "vienphi/V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD.jrxml";
				
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/MauC79aHD_Template.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/MauC79aHD_Template_TramYTe.jrxml";
				log.info("da thay file templete " + pathTemplate);
				log.info("da thay file sub 0 templete " + sub0Template);
				log.info("da thay file sub 1 templete " + sub1Template);
				
				
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
				
				params.put("noitru", false);
				
			}else if ("C80aHD".equals(mauSo)){
//				pathTemplate = IConstantsRes.PATH_BASE
//				+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//				+ "vienphi/V26_DSNguoibenhkhamchuabenhnoitrudenghithanhtoan_MauC80aHD.jrxml";
//				log.info("da thay file templete " + pathTemplate);
								
//				pathTemplate = IConstantsRes.PATH_BASE
//				+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//				+ "vienphi/V26_DSNguoibenhkhamchuabenhnoitrudenghithanhtoan_MauC80aHD.jrxml";
				
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/MauC79aHD_Template.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V26_DSNguoibenhkhamchuabenhnoitrudenghithanhtoan_MauC80aHD.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/MauC79aHD_Template_TramYTe.jrxml";
				log.info("da thay file templete " + pathTemplate);
				log.info("da thay file sub 0 templete " + sub0Template);
				log.info("da thay file sub 1 templete " + sub1Template);
				
				
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
				
				params.put("noitru", true);
			} 
			

			
			JasperReport jasperReport = JasperCompileManager
			.compileReport(pathTemplate);
			
			
			
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("TuNgay", sdf.parse(tungay));

			params.put("DenNgay", sdf.parse(denngay));
			
			DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
			
			if (maPLBHYT != null && !maPLBHYT.equals("")){
				DtDmPlBhyt plbhyt = new DtDmPlBhyt();
				Object obj = dtUtils.findByMa(maPLBHYT, "DtDmPlBhyt", " dtdmphloaibhytMa");
				plbhyt = (DtDmPlBhyt)obj;
				params.put("TenPLDoiTuong", plbhyt.getDtdmphloaibhytTen() );
				params.put("MaPLDoiTuong", plbhyt.getDtdmphloaibhytMa() );
				log.info(" ma phan loai " + maPLBHYT + "ten phan loai " + plbhyt.getDtdmphloaibhytTen());
			}
		
			if (maKCBBHYT != null && !maKCBBHYT.equals("")){
				DtDmKcbBhyt kcbbhyt = new DtDmKcbBhyt();
				Object obj = dtUtils.findByMa(maKCBBHYT, "DtDmKcbBhyt", "dtdmkcbbhytMa");
				kcbbhyt = (DtDmKcbBhyt)obj;
				params.put("TenKCBBHYT", kcbbhyt.getDtdmkcbbhytTen() );
				params.put("MaKCBBHYT", kcbbhyt.getDtdmkcbbhytMa() );
				log.info(" ma phan loai " + maKCBBHYT + "ten phan loai " + kcbbhyt.getDtdmkcbbhytTen());
			}
			
			

			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			log.info("da thay driver mysql");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
						IConstantsRes.DATABASE_USER,
						IConstantsRes.DATABASE_PASS);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			jasperPrintVP = JasperFillManager.fillReport(jasperReport, params,
					conn);
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintVP, index,
					IConstantsRes.PATH_BASE
							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
							+ "vienphi/", "pdf", "V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD");
			reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + reportPathVP);
			index += 1;
			log.info("Index :" + getIndex());
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		log.info("Thoat Method XuatReport");
	}

	public Integer getTuyen() {
		return tuyen;
	}

	public void setTuyen(Integer tuyen) {
		this.tuyen = tuyen;
	}

	public static Logger getLog() {
		return log;
	}

	public Integer getMaSoPLBHYT() {
		return maSoPLBHYT;
	}

	public void setMaSoPLBHYT(Integer maSoPLBHYT) {
		this.maSoPLBHYT = maSoPLBHYT;
	}

	public String getMaPLBHYT() {
		return maPLBHYT;
	}

	public void setMaPLBHYT(String maPLBHYT) {
		this.maPLBHYT = maPLBHYT;
	}

	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() throws Exception {

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

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public String getMauSo() {
		return mauSo;
	}

	public void setMauSo(String mauSo) {
		this.mauSo = mauSo;
	}

	public Integer getMaSoKCBBHYT() {
		return maSoKCBBHYT;
	}

	public void setMaSoKCBBHYT(Integer maSoKCBBHYT) {
		this.maSoKCBBHYT = maSoKCBBHYT;
	}

	public String getMaKCBBHYT() {
		return maKCBBHYT;
	}

	public void setMaKCBBHYT(String maKCBBHYT) {
		this.maKCBBHYT = maKCBBHYT;
	}

	public String getReportPathVP() {
		return reportPathVP;
	}

	public void setReportPathVP(String reportPathVP) {
		this.reportPathVP = reportPathVP;
	}

	public String getReportTypeVP() {
		return reportTypeVP;
	}

	public void setReportTypeVP(String reportTypeVP) {
		this.reportTypeVP = reportTypeVP;
	}

	public JasperPrint getJasperPrintVP() {
		return jasperPrintVP;
	}

	public void setJasperPrintVP(JasperPrint jasperPrintVP) {
		this.jasperPrintVP = jasperPrintVP;
	}

	public static void setLog(Logger log) {
		BHYT_DanhSachNguoiBenhBHYTNgoaiTruDeNghiThanhToanMauC97aHD.log = log;
	}

}
