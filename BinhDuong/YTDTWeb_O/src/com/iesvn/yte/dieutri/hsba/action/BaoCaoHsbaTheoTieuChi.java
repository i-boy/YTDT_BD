/**

 * HOAI NAM
 */

package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("BaocaoHsbaTheotieuchi")
@Synchronized(timeout = 6000000)
public class BaoCaoHsbaTheoTieuChi implements Serializable {

	private static Logger log = Logger.getLogger(BaoCaoHsbaTheoTieuChi.class);

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;

	private String loaiBaoCao = null;
	private String tuNgay = null;
	private String denNgay = null;
	private String rvTuNgay = null;
	private String rvDenNgay = null;
	private String clsTuNgay = null;
	private String clsDenNgay = null;
	private String doiTuong;
	private DmKhoa khoaVaoVien;
	private DmKhoa khoaDT;
//	private DtDmClsBangGia clsBangGia;
	private int dtdmclsbgMaso;
	private String dtdmclsbgMa;
//	private DmBenhIcd benhIcd;
	private String dmbenhicdMa;
	private int dmbenhicdMaso;
	private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
	private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap<String, DmKhoa>();
	private List<SelectItem> listDmKhoaNTs = new ArrayList<SelectItem>();
	private List<DmKhoa> listDmKhoaNTAll = new ArrayList<DmKhoa>();

	private String ngayhientai;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Out(required = false)
	private String loaiBCDT = null;
	
	@Out(required = false)
	@In(required = false)
	private String duongdanStrDT = null;

	@Out(required = false)
	@In(required = false)
	JasperPrint jasperPrintDT = null;

	@Out(required = false)
	@In(required = false)
	private String resetFormB221;

	private int index = 0;

	@Begin(join = true)
	public String init() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		refreshDmKhoaNT();
		resetForm();
		return "DieuTri_BaoCaoHsba_TheoTieuChi";
	}

	@End
	public void end() {

	}

	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm() {

		loaiBaoCao = null;
		doiTuong = null;
		dtdmclsbgMa = null;
		dmbenhicdMa = null;
		Date currentDate = new Date();
		if (khoaVaoVien == null) {
			khoaVaoVien = new DmKhoa();
		}
		if (khoaDT == null) {
			khoaDT = new DmKhoa();
		}
		tuNgay = dateFormat.format(currentDate);
		denNgay = dateFormat.format(currentDate);
		rvTuNgay = dateFormat.format(currentDate);
		rvDenNgay = dateFormat.format(currentDate);
		clsTuNgay = dateFormat.format(currentDate);
		clsDenNgay = dateFormat.format(currentDate);
	}
	
	

	public void onblurMaKhoaAction(String khoa) {
		if (khoa.equals("VV")) {
			if (khoaVaoVien != null && khoaVaoVien.getDmkhoaMa() != null) {
				String maKhoa = khoaVaoVien.getDmkhoaMa();
				if (hmDmKhoaNTAll != null) {
					DmKhoa dmKhoa = (DmKhoa) hmDmKhoaNTAll.get(maKhoa
							.toUpperCase());
					if (dmKhoa != null) {
						khoaVaoVien = dmKhoa;
					} else {
						khoaVaoVien = new DmKhoa();
						return;
					}
				}
			}
		} else {
			if (khoaDT != null && khoaDT.getDmkhoaMa() != null) {
				String maKhoa = khoaDT.getDmkhoaMa();
				if (hmDmKhoaNTAll != null) {
					DmKhoa dmKhoa = (DmKhoa) hmDmKhoaNTAll.get(maKhoa
							.toUpperCase());
					if (dmKhoa != null) {
						khoaDT = dmKhoa;
					} else {
						khoaDT = new DmKhoa();
						return;
					}
				}
			}
		}

	}

	public void onblurTenKhoaAction(String khoaTen) {
		if (khoaTen.equals("VV")) {
			if (khoaVaoVien != null && khoaVaoVien.getDmkhoaTen() != null) {
				String tenKhoa = khoaVaoVien.getDmkhoaTen();
				Boolean hasTenKhoa = false;
				java.util.Set set = hmDmKhoaNTAll.entrySet();
				Iterator i = set.iterator();
				DmKhoa dmKhoa_Finded = new DmKhoa();
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry) i.next();
					DmKhoa dmKhoa = (DmKhoa) me.getValue();
					if (dmKhoa.getDmkhoaTen() == tenKhoa
							|| dmKhoa.getDmkhoaTen().equals(tenKhoa)) {
						hasTenKhoa = true;
						dmKhoa_Finded = dmKhoa;
						break;
					}
				}
				if (hasTenKhoa) {
					khoaVaoVien.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
					khoaVaoVien.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa()
							.toUpperCase());
					khoaVaoVien.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
				} else {
					khoaVaoVien = new DmKhoa();
					return;
				}
			}
		} else {
			if (khoaDT != null && khoaDT.getDmkhoaTen() != null) {
				String tenKhoa = khoaDT.getDmkhoaTen();
				Boolean hasTenKhoa = false;
				java.util.Set set = hmDmKhoaNTAll.entrySet();
				Iterator i = set.iterator();
				DmKhoa dmKhoa_Finded = new DmKhoa();
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry) i.next();
					DmKhoa dmKhoa = (DmKhoa) me.getValue();
					if (dmKhoa.getDmkhoaTen() == tenKhoa
							|| dmKhoa.getDmkhoaTen().equals(tenKhoa)) {
						hasTenKhoa = true;
						dmKhoa_Finded = dmKhoa;
						break;
					}
				}
				if (hasTenKhoa) {
					khoaDT.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
					khoaDT.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa()
							.toUpperCase());
					khoaDT.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
				} else {
					khoaDT = new DmKhoa();
					return;
				}
			}
		}
	}

	public void refreshDmKhoaNT() {
		dmKhoaDel = DmKhoaDelegate.getInstance();
		listDmKhoaNTAll.clear();
		listDmKhoaNTs.clear();
		listDmKhoaNTAll = dmKhoaDel.getKhoaNT();
		hmDmKhoaNTAll.clear();

		for (DmKhoa o : listDmKhoaNTAll) {
			hmDmKhoaNTAll.put(o.getDmkhoaMa(), o);
		}
		for (DmKhoa each : listDmKhoaNTAll) {
			listDmKhoaNTs.add(new SelectItem(each.getDmkhoaTen()));
		}
	}

	/**
	 * xuat report
	 */
	public void XuatReport() {
		String baocao1 = null;
		String pathTemplate = null;
		loaiBCDT = "Baocaohsba";
		log.info("loaiBaoCao " + loaiBaoCao);
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (loaiBaoCao.equals("PT")) {
				pathTemplate = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "hsba/B05_BACLS.jrxml";
//				log.info("clsBangGia.getDtdmclsbgMa() "+clsBangGia.getDtdmclsbgMa());
					params.put("tenCLS", dtdmclsbgMa);
						params.put("maCLS",dtdmclsbgMa);
						log.info("dtdmclsbgMaso"+dtdmclsbgMa);
				params.put("tuNgayCLS", dateFormat.parse(clsTuNgay));
				params.put("denNgayCLS", dateFormat.parse(clsDenNgay));
			}else if (loaiBaoCao.equals("NgT")) {
				pathTemplate = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "hsba/B05_BADieutriNgoaitru.jrxml";
			}else if (loaiBaoCao.equals("RV")) {
				pathTemplate = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "hsba/B05_BARavien.jrxml";
				params.put("NgayRVTu", dateFormat.parse(rvTuNgay));
				params.put("NgayRVDen", dateFormat.parse(rvDenNgay));
			}else if (loaiBaoCao.equals("MB")) {
				pathTemplate = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "hsba/B05_BATheoBenhchinh.jrxml";
				params.put("MaBenh", dmbenhicdMa);
			}else if (loaiBaoCao.equals("TVNgV")) {
				pathTemplate = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "hsba/B05_BATuvongNgoaivien.jrxml";
			}else if (loaiBaoCao.equals("TV")) {
				pathTemplate = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "hsba/B05_BATuvong.jrxml";
			}else if (loaiBaoCao.equals("VV")) {
				pathTemplate = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "hsba/B05_BAVaovien.jrxml";
			}

			JasperReport jasperReport = JasperCompileManager
					.compileReport(pathTemplate);

			Date dTuNgay = new Date();
			dTuNgay = dateFormat.parse(tuNgay);
			Date dDenNgay = new Date();
			dDenNgay = dateFormat.parse(denNgay);
			params.put("tuNgay",dTuNgay );
			params.put("denNgay", dDenNgay);
			params.put("diadiem", "");
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			log.info("doi tuong "+ doiTuong);
			if(doiTuong!= null && !doiTuong.equals("ALL")){
				params.put("doiTuong", doiTuong);
			}
			if (khoaDT!= null && khoaDT.getDmkhoaMa() != null && khoaDT.getDmkhoaMa() != ""){
				params.put("khoaDangDT", khoaDT.getDmkhoaMa());
			}
			if (khoaVaoVien!= null && khoaVaoVien.getDmkhoaMa() != null && khoaVaoVien.getDmkhoaMa() != ""){
				params.put("khoaVaovien", khoaVaoVien.getDmkhoaMa());
			}

			Class.forName("oracle.jdbc.OracleDriver");
			log.info("da thay driver oracle");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
						IConstantsRes.DATABASE_USER,
						IConstantsRes.DATABASE_PASS);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			jasperPrintDT = JasperFillManager.fillReport(jasperReport, params,
					conn);
		
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintDT, index,
					IConstantsRes.PATH_BASE
							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
							+ "hsba/", "pdf", "Baocaohsba");

			duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			index += 1;
			log.info("Index :" + index);
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		log.info("Thoat Method XuatReport");
	}

	public String thuchienAction() {
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}

	public static String getFORMAT_DATE() {
		return FORMAT_DATE;
	}

	public static void setFORMAT_DATE(String format_date) {
		FORMAT_DATE = format_date;
	}

	public static String getFORMAT_DATE_TIME() {
		return FORMAT_DATE_TIME;
	}

	public static void setFORMAT_DATE_TIME(String format_date_time) {
		FORMAT_DATE_TIME = format_date_time;
	}

	public String getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(String tuNgay) {
		this.tuNgay = tuNgay;
	}

	public String getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(String denNgay) {
		this.denNgay = denNgay;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getLoaiBaoCao() {
		return loaiBaoCao;
	}

	public void setLoaiBaoCao(String loaiBaoCao) {
		this.loaiBaoCao = loaiBaoCao;
	}

	public DmKhoa getKhoaVaoVien() {
		return khoaVaoVien;
	}

	public void setKhoaVaoVien(DmKhoa khoaVaoVien) {
		this.khoaVaoVien = khoaVaoVien;
	}

	public DmKhoa getKhoaDT() {
		return khoaDT;
	}

	public void setKhoaDT(DmKhoa khoaDT) {
		this.khoaDT = khoaDT;
	}

	public List<SelectItem> getListDmKhoaNTs() {
		return listDmKhoaNTs;
	}

	public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs) {
		this.listDmKhoaNTs = listDmKhoaNTs;
	}

	public List<DmKhoa> getListDmKhoaNTAll() {
		return listDmKhoaNTAll;
	}

	public void setListDmKhoaNTAll(List<DmKhoa> listDmKhoaNTAll) {
		this.listDmKhoaNTAll = listDmKhoaNTAll;
	}

	public String getRvTuNgay() {
		return rvTuNgay;
	}

	public void setRvTuNgay(String rvTuNgay) {
		this.rvTuNgay = rvTuNgay;
	}

	public String getRvDenNgay() {
		return rvDenNgay;
	}

	public void setRvDenNgay(String rvDenNgay) {
		this.rvDenNgay = rvDenNgay;
	}

	public String getClsTuNgay() {
		return clsTuNgay;
	}

	public void setClsTuNgay(String clsTuNgay) {
		this.clsTuNgay = clsTuNgay;
	}

	public String getClsDenNgay() {
		return clsDenNgay;
	}

	public void setClsDenNgay(String clsDenNgay) {
		this.clsDenNgay = clsDenNgay;
	}

	public String getDoiTuong() {
		return doiTuong;
	}

	public void setDoiTuong(String doiTuong) {
		this.doiTuong = doiTuong;
	}

	public int getDtdmclsbgMaso() {
		return dtdmclsbgMaso;
	}

	public void setDtdmclsbgMaso(int dtdmclsbgMaso) {
		this.dtdmclsbgMaso = dtdmclsbgMaso;
	}

	public String getDtdmclsbgMa() {
		return dtdmclsbgMa;
	}

	public void setDtdmclsbgMa(String dtdmclsbgMa) {
		this.dtdmclsbgMa = dtdmclsbgMa;
	}

	public String getDmbenhicdMa() {
		return dmbenhicdMa;
	}

	public void setDmbenhicdMa(String dmbenhicdMa) {
		this.dmbenhicdMa = dmbenhicdMa;
	}

	public int getDmbenhicdMaso() {
		return dmbenhicdMaso;
	}

	public void setDmbenhicdMaso(int dmbenhicdMaso) {
		this.dmbenhicdMaso = dmbenhicdMaso;
	}

}
