package com.iesvn.yte.dieutri.vienphi.action;

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
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3125_Insothuochangngay")
@Synchronized(timeout = 6000000)
public class InSoThuocHangNgayAction implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static final long serialVersionUID = 10L;
	private static Logger log = Logger.getLogger(InSoThuocHangNgayAction.class);

	// /////////////////////////////////////////

	private Integer khoaMaSo;
	private String khoaMa;
	private String ngaySuDung;
	private Boolean thuoc;
	private Boolean vatTuTieuHao;

	private Boolean thuocthuongtv;
	private Boolean thuocthuongtk;
	private Boolean thuocdoc;
	private Boolean gaynghien;
	private Boolean huongthan;
	private Boolean dongy;
	private Boolean hoachat;

	private String tuTrucPhieuDuTru = "pdt"; // tt hoac pdt

	private int index;

	private DmKhoa dmKhoa;
	private DmTang dmTang;
	private List<SelectItem> listDmTangs = new ArrayList<SelectItem>();
	private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap<String, DmKhoa>();
	private List<SelectItem> listDmKhoaNTs = new ArrayList<SelectItem>();
	private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
	private List<DmKhoa> listDmKhoaNTAll = new ArrayList<DmKhoa>();
	// /////////////////////////////////////////

	private String ngayHienTai;

	@Begin(join = true)
	public String init() {
		log.info("init()");
		resetForm();
		refreshDmKhoaNT();
		return "VienPhiTaiKhoa_XemInPhieu_InSoThuoc";
	}

	@Out(required = false)
	@In(required = false)
	private String reportPathVP = null;

	@Out(required = false)
	@In(required = false)
	private String reportTypeVP = null;

	@Out(required = false)
	@In(required = false)
	JasperPrint jasperPrintVP = null;

	public String getLoaiThuoc() {
		String tmp = ",";
		if (thuocthuongtv) {
			tmp += "1,";
		}
		if (thuocthuongtk) {
			tmp += "2,";
		}
		if (thuocdoc) {
			tmp += "3,";
		}
		if (gaynghien) {
			tmp += "4,";
		}
		if (huongthan) {
			tmp += "5,";
		}
		if (vatTuTieuHao) {
			tmp += "6,";
		}
		if (dongy) {
			tmp += "7,";
		}
		if (hoachat) {
			tmp += "8,";
		}
		if (tmp.length() > 1)
			return tmp.substring(1, tmp.length() - 1);
		else
			return "0";
	}

	public void onblurMaKhoaAction() {
		log.info("-----BEGIN onblurMaKhoaAction()-----");
		if (dmKhoa != null && dmKhoa.getDmkhoaMa() != null) {
			String maKhoa = dmKhoa.getDmkhoaMa();
			if (hmDmKhoaNTAll != null) {
				DmKhoa dmKhoa = (DmKhoa) hmDmKhoaNTAll.get(maKhoa.toUpperCase());
				if (dmKhoa != null) {
					this.dmKhoa.setDmkhoaMaso(dmKhoa.getDmkhoaMaso());
					this.dmKhoa.setDmkhoaMa(dmKhoa.getDmkhoaMa());
					this.dmKhoa.setDmkhoaTen(dmKhoa.getDmkhoaTen());
					log.info("Tim ma khoa: Da thay khoa "
							+ dmKhoa.getDmkhoaTen());
				} else {
					this.dmKhoa = new DmKhoa();
					return;
				}
			}
			dmTang = new DmTang();
			refreshDmTang();
		}
		log.info("-----END onblurMaKhoaAction()-----");
	}

	public void refreshDmTang() {
		listDmTangs = new ArrayList<SelectItem>();
		if (dmKhoa != null && dmKhoa.getDmkhoaMaso() != null) {
			String khoaMa = dmKhoa.getDmkhoaMa();
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			// Get tat ca cac tang cua khoa chuyen den, show gia tri default
			// truoc
			List<DmTang> lstDmTangs = dtUtil.findMaLike("DmTang","dmkhoaMaso.dmkhoaMa", "dmtangChon", khoaMa, true);
			if (lstDmTangs != null && lstDmTangs.size() > 0) {
				for (DmTang dmTang : lstDmTangs) {
					listDmTangs.add(new SelectItem(dmTang.getDmtangTen()));
				}
				for (DmTang dmTang : lstDmTangs) {
					if (dmTang.getDmtangDefault().booleanValue() == true) {
						this.dmTang = dmTang;
						break;
					}
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
	
	public void onblurTenKhoaAction(){
		log.info("-----BEGIN onblurTenKhoaAction()-----");
		if(dmKhoa != null && dmKhoa.getDmkhoaTen() != null){
			String tenKhoa = dmKhoa.getDmkhoaTen();
			Boolean hasTenKhoa = false;
			java.util.Set set = hmDmKhoaNTAll.entrySet();
		    Iterator i = set.iterator();
		    DmKhoa dmKhoa_Finded = new DmKhoa();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
		    	if(dmKhoa.getDmkhoaTen() == tenKhoa || dmKhoa.getDmkhoaTen().equals(tenKhoa)){
		    		hasTenKhoa = true;
		    		dmKhoa_Finded = dmKhoa;
		    		break;
		    	}	    		
		    }
		    if(hasTenKhoa){
		    	dmKhoa.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
		    	dmKhoa.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa());
		    	dmKhoa.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
		    }else{
		    	dmKhoa = new DmKhoa();
		    	return;
		    }
		    dmTang = new DmTang();
		    refreshDmTang();
		}
		log.info("-----END onblurTenKhoaAction()-----");
	}
	
	public void onblurTenTangAction(){
		if(dmTang != null && dmTang.getDmtangTen() != null){
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			List<DmTang> lstTangs = dtUtil.findTenLike("DmTang", "dmtangTen", dmTang.getDmtangTen());
			if(lstTangs != null && lstTangs.size()>0){
				dmTang = lstTangs.get(0);
			}else{
				dmTang = new DmTang();
			}
		}
	}
	
	

	public void resetForm() {
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		ngayHienTai = formatter.format(new Date());
		khoaMa = "";
		ngaySuDung = ngayHienTai;
		thuocthuongtv = true;
		thuocthuongtk = true;
		vatTuTieuHao = false;
		thuocdoc = false;
		gaynghien = false;
		huongthan = false;
		dongy = false;
		hoachat = false;
		tuTrucPhieuDuTru = "pdt";// tt hoac pdt
		dmTang = new DmTang();
		dmKhoa = new DmKhoa();
	}

	public String thuchienAction() {
		XuatReport();
		return "B3360_Chonmenuxuattaptin";
	}

	public String XuatReport() {
		String baocao1 = null;
		reportTypeVP = "B3125_Phieulinhthuoc";
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE
					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
					+ "vienphi/Insothuoc.jrxml";
			log.info("da thay file templete  " + pathTemplate);
			JasperReport jasperReport = JasperCompileManager
					.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
			Date dNgaySuDung = formatter.parse(ngaySuDung);
			params.put("ngaySuDung", dNgaySuDung);
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("makhoa", dmKhoa.getDmkhoaMaso());
			if (dmTang != null){
				params.put("matang", dmTang.getDmtangMaso());
				log.info("khoaTANG:" + dmTang.getDmtangMaso());
			}
			// Boolean ttorPhieuDuTru = false;
			if (tuTrucPhieuDuTru != null) {
				if (tuTrucPhieuDuTru.equals("tt")) {
					// ttorPhieuDuTru = true;
					params.put("ttorpdt", 1);
				} else {
					params.put("ttorpdt", 0);
					// params.put("ttorpdt", "0");
				}
			} else {
				params.put("ttorpdt", 0);
				// params.put("ttorpdt", "0");
			}
			// params.put("ttorpdt", ttorPhieuDuTru);

			// params.put("thuoc", thuoc);
			// params.put("ydungcuvtth", vatTuTieuHao);
			params.put("LOAITHUOC", getLoaiThuoc());

			log.info("------------------------------------------------------------------");
			log.info("ngaySuDung:" + ngaySuDung);
			log.info("khoaMa:" + dmKhoa.getDmkhoaMaso());
			log.info("ttorPhieuDuTru:" + tuTrucPhieuDuTru);
			log.info("loaithuoc:" + getLoaiThuoc());
			// log.info("vatTuTieuHao:"+vatTuTieuHao);
			log
					.info("------------------------------------------------------------------");

			reportTypeVP = "B3125_Phieulinhthuoc";
			log.info("Vao Method XuatReport B3125_Phieulinhthuoc");
			log.info("================= ");

			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			log.debug(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			log.debug(String
					.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi",
					IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			log.debug(String.format("-----dienThoaiDonVi: %s", params
					.get("dienThoaiDonVi")));
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
							+ "vienphi/", "pdf", "B3125_Phieulinhthuoc");
			reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + reportPathVP);
			index += 1;
			log.info("Index :" + index);

		} catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		log.info("Thoat Method XuatReport");
		return "B3360_Chonmenuxuattaptin";
	}

	// public String getDmKhoXuat() {
	// return dmKhoXuat;
	// }
	//
	// public void setDmKhoXuat(String dmKhoXuat) {
	// this.dmKhoXuat = dmKhoXuat;
	// }

	@End
	public void end() {

	}

	public static String getFORMAT_DATE() {
		return FORMAT_DATE;
	}

	public static void setFORMAT_DATE(String format_date) {
		FORMAT_DATE = format_date;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		InSoThuocHangNgayAction.log = log;
	}

	public Integer getKhoaMaSo() {
		return khoaMaSo;
	}

	public void setKhoaMaSo(Integer khoaMaSo) {
		this.khoaMaSo = khoaMaSo;
	}

	public String getKhoaMa() {
		return khoaMa;
	}

	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}

	public String getNgaySuDung() {
		return ngaySuDung;
	}

	public void setNgaySuDung(String ngaySuDung) {
		this.ngaySuDung = ngaySuDung;
	}

	public Boolean getThuoc() {
		return thuoc;
	}

	public void setThuoc(Boolean thuoc) {
		this.thuoc = thuoc;
	}

	public Boolean getVatTuTieuHao() {
		return vatTuTieuHao;
	}

	public void setVatTuTieuHao(Boolean vatTuTieuHao) {
		this.vatTuTieuHao = vatTuTieuHao;
	}

	public String getTuTrucPhieuDuTru() {
		return tuTrucPhieuDuTru;
	}

	public void setTuTrucPhieuDuTru(String tuTrucPhieuDuTru) {
		this.tuTrucPhieuDuTru = tuTrucPhieuDuTru;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getNgayHienTai() {
		return ngayHienTai;
	}

	public void setNgayHienTai(String ngayHienTai) {
		this.ngayHienTai = ngayHienTai;
	}

	public Boolean getDongy() {
		return dongy;
	}

	public void setDongy(Boolean dongy) {
		this.dongy = dongy;
	}

	public Boolean getGaynghien() {
		return gaynghien;
	}

	public void setGaynghien(Boolean gaynghien) {
		this.gaynghien = gaynghien;
	}

	public Boolean getHoachat() {
		return hoachat;
	}

	public void setHoachat(Boolean hoachat) {
		this.hoachat = hoachat;
	}

	public Boolean getHuongthan() {
		return huongthan;
	}

	public void setHuongthan(Boolean huongthan) {
		this.huongthan = huongthan;
	}

	public Boolean getThuocdoc() {
		return thuocdoc;
	}

	public void setThuocdoc(Boolean thuocdoc) {
		this.thuocdoc = thuocdoc;
	}

	public Boolean getThuocthuongtk() {
		return thuocthuongtk;
	}

	public void setThuocthuongtk(Boolean thuocthuongtk) {
		this.thuocthuongtk = thuocthuongtk;
	}

	public Boolean getThuocthuongtv() {
		return thuocthuongtv;
	}

	public void setThuocthuongtv(Boolean thuocthuongtv) {
		this.thuocthuongtv = thuocthuongtv;
	}

	public DmKhoa getDmKhoa() {
		return dmKhoa;
	}

	public void setDmKhoa(DmKhoa dmKhoa) {
		this.dmKhoa = dmKhoa;
	}

	public List<SelectItem> getListDmTangs() {
		return listDmTangs;
	}

	public void setListDmTangs(List<SelectItem> listDmTangs) {
		this.listDmTangs = listDmTangs;
	}

	public DmTang getDmTang() {
		return dmTang;
	}

	public void setDmTang(DmTang dmTang) {
		this.dmTang = dmTang;
	}

	public HashMap<String, DmKhoa> getHmDmKhoaNTAll() {
		return hmDmKhoaNTAll;
	}

	public void setHmDmKhoaNTAll(HashMap<String, DmKhoa> hmDmKhoaNTAll) {
		this.hmDmKhoaNTAll = hmDmKhoaNTAll;
	}

	public List<SelectItem> getListDmKhoaNTs() {
		return listDmKhoaNTs;
	}

	public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs) {
		this.listDmKhoaNTs = listDmKhoaNTs;
	}

	public DmKhoaDelegate getDmKhoaDel() {
		return dmKhoaDel;
	}

	public void setDmKhoaDel(DmKhoaDelegate dmKhoaDel) {
		this.dmKhoaDel = dmKhoaDel;
	}

	public List<DmKhoa> getListDmKhoaNTAll() {
		return listDmKhoaNTAll;
	}

	public void setListDmKhoaNTAll(List<DmKhoa> listDmKhoaNTAll) {
		this.listDmKhoaNTAll = listDmKhoaNTAll;
	}

}
