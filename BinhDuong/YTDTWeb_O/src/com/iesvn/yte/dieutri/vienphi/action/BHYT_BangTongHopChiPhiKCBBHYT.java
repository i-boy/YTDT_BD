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
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3343_BangThongKeChiPhiKCBBHYTDaTuyen")
@Synchronized(timeout = 6000000)
public class BHYT_BangTongHopChiPhiKCBBHYT
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
		
		return "BaoCaoVienPhi_BaoCaoBHYT_BHYT_BangTongHopChiPhiKCBDaTuyen";
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
		reportTypeVP = "BaoCaoTongHopChiPhiKCBHYTDaTuyen";
		log.info("Vao Method XuatReport bao cao BaoCaoTongHopChiPhiKCBHYTDaTuyen");
		String baocao1 = null;
		String pathTemplate = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			
			if ("10".equals(mauSo)){
				pathTemplate = IConstantsRes.PATH_BASE
				+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
				+ "vienphi/V27_BaoCaoTongHopChiPhiKCBHYTDaTuyenNoiTinh.jrxml";
			}else if ("11".equals(mauSo)){
				pathTemplate = IConstantsRes.PATH_BASE
				+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
				+ "vienphi/V28_BaoCaoTongHopChiPhiKCBHYTDaTuyenNgoaiTinh.jrxml";
			} else if ("10b".equals(mauSo)){ // phuc.lc 16-11-2010 : Xuat mau report 10/BHYT
				//pathTemplate = IConstantsRes.PATH_BASE+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+ "vienphi/V29_BCTHChiPhiKCBHYTDaTuyenNoiTinh.jrxml";
				pathTemplate = IConstantsRes.PATH_BASE+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+ "vienphi/VP10_THChiPhiKCBHYTThanhToanDaTuyenNoiTinh.jrxml";				
			} else if ("18".equals(mauSo)){ // phuc.lc 16-11-2010 : Xuat mau report 11/BHYT
				// pathTemplate = IConstantsRes.PATH_BASE+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+ "vienphi/V30_BCTHChiPhiKCBHYTDaTuyenNgoaiTinh.jrxml";
				pathTemplate = IConstantsRes.PATH_BASE+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+ "vienphi/VP11_THChiPhiKCBHYTThanhToanDaTuyenNgoaiTinh.jrxml";
			} else if ("14a".equals(mauSo)){
				
//				pathTemplate = IConstantsRes.PATH_BASE
//				+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
//				+ "vienphi/V31_BCChiPhiKCBNgoaiTruTheoDTvaTuyenCMKT.jrxml";
				
				
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/Baocaochiphikcbtheodoituongvatuyencmkt.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/V31_BCChiPhiKCBNgoaiTruTheoDTvaTuyenCMKT.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/chiphikcbtheotuyencmkt _ngoaitru_tuyenhuyen.jrxml";
				String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/chiphikcbtheotuyencmkt.jrxml";
				
				log.info("da thay file templete " + pathTemplate);
				log.info("da thay file sub 0 templete " + sub0Template);
				log.info("da thay file sub 1 templete " + sub1Template);
				log.info("da thay file sub 1 templete " + sub2Template);
				
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
				params.put("sub2", sub2Report);
				
				params.put("NOITRU", false);
				
				
			} else if ("14b".equals(mauSo)){
				pathTemplate = IConstantsRes.PATH_BASE
				+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
				+ "vienphi/V32_BCChiPhiKCBNoiTruTheoDTvaTuyenCMKT.jrxml";
				
			} 
			
			
			
		
			
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport = JasperCompileManager
					.compileReport(pathTemplate);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			if(mauSo.equals("10b") || mauSo.equals("18")) {
				params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			}
			//params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("TuNgay", sdf.parse(tungay));
			params.put("DenNgay", sdf.parse(denngay));
			params.put("NoiTinh", IConstantsRes.TINH_BHYT_DEFAULT);
			params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			//params.put("thang", thoigian_thang);
			//params.put("nam", thoigian_nam);
			/*
			log.info("tungay:"+tungay);
			log.info("denngay:"+denngay);
			
			DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
			
			if (maKCBBHYT != null && !maKCBBHYT.equals("")){
				DtDmKcbBhyt kcbbhyt = new DtDmKcbBhyt();
				Object obj = dtUtils.findByMa(maKCBBHYT, "DtDmKcbBhyt", "dtdmkcbbhytMa");
				kcbbhyt = (DtDmKcbBhyt)obj;
				params.put("TenKCBBHYT", kcbbhyt.getDtdmkcbbhytTen() );
				params.put("MaKCBBHYT", kcbbhyt.getDtdmkcbbhytMa() );
				log.info(" ma phan loai " + maKCBBHYT + "ten phan loai " + kcbbhyt.getDtdmkcbbhytTen());
			}
			
			params.put("NoiTinh", IConstantsRes.NOI_TINH);

			*/

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
							+ "vienphi/", "pdf", "BaoCaoTongHopChiPhiKCBHYTDaTuyen");
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



	public static Logger getLog() {
		return log;
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
		BHYT_BangTongHopChiPhiKCBBHYT.log = log;
	}

}
