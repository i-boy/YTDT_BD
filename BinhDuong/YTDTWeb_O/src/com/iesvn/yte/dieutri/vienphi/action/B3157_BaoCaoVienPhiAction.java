package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

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
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;

import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("B3157_BaoCaoVienPhi")
public class B3157_BaoCaoVienPhiAction implements Serializable {

	private static final long serialVersionUID = 10L;

	private String tungay;

	private String denngay;
	private String ngayhientai;

	@Out(required = false)
	@In(required = false)
	private String reportTypeVP = null;
	@Out(required = false)
	@In(required = false)
	JasperPrint jasperPrintVP = null;
	@Out(required = false)
	@In(required = false)
	private String reportPathVP = null;
	@In(scope = ScopeType.PAGE, required = false)
	@Out(scope = ScopeType.PAGE, required = false)
	private String strBcVienPhi;
	@Logger
	private Log log;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Factory("strBcVienPhi")
	public void init() {
		tungay = "";
		denngay = ngayhientai = sdf.format(new Date());
		strBcVienPhi = "";
	}

	public String initFromMenu() {
		return "/B3_Vienphi/TaiKhoa/B3157_BaoCaoVienPhi.xhtml";
		// return "VienPhiTaiKhoa_LapPhieuBaoAnHangNgay";
	}

	public String thuchienAction() {
		XuatReport();
		return "B3360_Chonmenuxuattaptin";
	}

	/**
	 * xuat report
	 */
	public void XuatReport() {
		reportTypeVP = "baocaovienphi";
		log.info("Vao Method XuatReport bao cao vien phi dinh duong");

		try {

			String baocao1 = null;
			Integer DOI_TUONG_BENH_VIEN_MA_SO = new Integer("1"); // 1 la ma so cua doi tuong BV duyet(mien phi) trong table dt_dm_doi_tuong_an															
																// neu gia tri nay co thay doi trong database, thi o day cung phai
																// thay doi theo de report xuat du lieu dung
			Integer DOI_TUONG_NHA_NUOI_MA_SO = new Integer("2"); //  2 la ma so cua doi tuong Nha nuoi(mien phi) trong table dt_dm_doi_tuong_an
			Integer DOI_TUONG_TRUNG_CAO_MA_SO = new Integer("3"); //  3 la ma so cua doi tuong Trung cao(mien phi) trong table dt_dm_doi_tuong_an
			Integer DOI_TUONG_DONG_TIEN_MA_SO = new Integer("4"); //  4 la ma so cua doi tuong Dong tien trong table dt_dm_doi_tuong_an
			
			Integer MUC_AN_30_MA_SO = new Integer("1"); // 1 la ma so cua muc an 30.000 trong table dt_dm_muc_an													
			Integer MUC_AN_60_MA_SO = new Integer("2"); // 2 la ma so cua muc an 60.000 trong table dt_dm_muc_an
			
			Integer DONG_THEM_10_MA_SO = new Integer("1"); // 1 la ma so cua muc dong them 10.000 trong table dt_dm_dong_them
			Integer DONG_THEM_20_MA_SO = new Integer("2"); // 2 la ma so cua muc dong them 20.000 trong table dt_dm_dong_them
			Integer DONG_THEM_30_MA_SO = new Integer("3"); // 3 la ma so cua muc dong them 30.000 trong table dt_dm_dong_them
			Integer DONG_THEM_40_MA_SO = new Integer("4"); // 4 la ma so cua muc dong them 40.000 trong table dt_dm_dong_them
														
			log.info("bat dau method XuatReport ");

			String pathTemplate = IConstantsRes.PATH_BASE
					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
					+ "vienphi/BaoCaoVienPhi_DinhDuong.jrxml";
			String sub0Template = IConstantsRes.PATH_BASE
					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
					+ "vienphi/BaoCaoVienPhi_DinhDuong_subreport0.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE
					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
					+ "vienphi/BaoCaoVienPhi_DinhDuong_subreport1.jrxml";

			JasperReport jasperReport = JasperCompileManager
					.compileReport(pathTemplate);
			JasperReport sub0Report = JasperCompileManager
					.compileReport(sub0Template);
			JasperReport sub1Report = JasperCompileManager
					.compileReport(sub1Template);

			Map<String, Object> params = new HashMap<String, Object>();

			params.put("tungay", sdf.parse(tungay));
			params.put("denngay", sdf.parse(denngay));
			params.put("donvibaocao", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL + "\n" + IConstantsRes.REPORT_KHOA_DINH_DUONG);
			
			params.put("doituong_benhvien_maso", DOI_TUONG_BENH_VIEN_MA_SO);
			params.put("doituong_nhanuoi_maso", DOI_TUONG_NHA_NUOI_MA_SO);
			params.put("doituong_trungcao_maso", DOI_TUONG_TRUNG_CAO_MA_SO);
			params.put("doituong_dongtien_maso", DOI_TUONG_DONG_TIEN_MA_SO);
			
			params.put("mucan30_maso", MUC_AN_30_MA_SO);
			params.put("mucan60_maso", MUC_AN_60_MA_SO);
			
			params.put("dongthem10_maso", DONG_THEM_10_MA_SO);
			params.put("dongthem20_maso", DONG_THEM_20_MA_SO);
			params.put("dongthem30_maso", DONG_THEM_30_MA_SO);
			params.put("dongthem40_maso", DONG_THEM_40_MA_SO);
			
			params.put("sub0", sub0Report);
			params.put("sub1", sub1Report);
			
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
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintVP, new Long(
					new Date().getTime()).intValue(), IConstantsRes.PATH_BASE
					+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/",
					"pdf", "baocaovienphi");
			reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + reportPathVP);
			// index+= 1;
			// log.info("Index :" + getIndex());
			if (conn != null)
				conn.close();

		} catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		log.info("Thoat Method XuatReport");
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

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public String getReportTypeVP() {
		return reportTypeVP;
	}

	public void setReportTypeVP(String reportTypeVP) {
		this.reportTypeVP = reportTypeVP;
	}

}
