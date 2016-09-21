package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKetQua;
import com.iesvn.yte.dieutri.entity.ClsMo;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.util.HoSoThanhToanUtil;
import com.iesvn.yte.util.HsttThreadUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3121_Canlamsangdieutri")
@Synchronized(timeout = 6000000)
public class B3121_CanLamSang_DieuTri implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private String position = com.iesvn.yte.util.IConstantsRes.WEB_PATH + "/"
			+ com.iesvn.yte.util.IConstantsRes.WEB_NAME + "/"
			+ com.iesvn.yte.util.IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
			+ "vienphi/";

	private String resultReportName = "";
	private String reportFileNameHid = "";
	private String resultReportFileName = "";
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";

	private static final long serialVersionUID = 10L;
	private BenhNhan benhNhan;
	private Hsba hoSoBenhAn;
	private HsbaBhyt hsbaBHYT;

	private HsbaKhoa hsbaKhoa;
	private HsThtoan hsthtoan;

	private ClsMo clsMo;
	private String gioi;
	private String tuoi;
	private String ngaySinh;
	private String ngayTt;
	// private String diengiai;
	// private String thoiGian;
	// Phan danh cho luoi du lieu
	private String loai = "";
	private String maCLS = "";
	private Integer maSoCLS = null;
	private String tenCLS = "";
	private String ghiChu = "";
	private String maKhoa = "";
	private int tangMaso = 0;
	private Boolean mien = new Boolean(false);
	private Boolean ngoaiDanhMuc = new Boolean(false);
	private Boolean yeuCau = new Boolean(false);
	private Boolean khongthu = new Boolean(false);
	private Boolean kyThuatCao = new Boolean(false);
	private short soLuong = 0;
	private Double donGia = new Double(0);
	private Boolean dichVu = new Boolean(false);
	private String donGiaDieuChinh = "";
	// phuc.lc 24-09-2010
	private Double donGiaBH = new Double(0);
	private Double phanDV = new Double(0);
	private int selectedIndex = -1;
	private String resultHidden = "";
	private DtDmNhanVien nhanVienThucHien = new DtDmNhanVien();

	private String maPhieu = "";
	private String maDoituong;
	private String strNgayBatDauBh;
	private String strNgayHetHanBh;
	private Date ngayBatDauBh;
	private Date ngayHetHanBh;
	private Integer clsMoMaso;
	private static Logger log = Logger
			.getLogger(B3121_CanLamSang_DieuTri.class);
	private String maKhoaCLS = "";
	private Integer maSoKhoaCLS = 0;
	private String listCLSChoose = "";
	private String ngayVv;

	private String khoaCDHA;

	private String dsIn;

	@Out(required = false)
	private String loaiBCDT = null;

	@Out(required = false)
	JasperPrint jasperPrintDT = null;

	@Out(required = false)
	private String duongdanStrDT = null;

	private int index = 0;

	public Integer getMaSoKhoaCLS() {
		return maSoKhoaCLS;
	}

	public void setMaSoKhoaCLS(Integer maSoKhoaCLS) {
		this.maSoKhoaCLS = maSoKhoaCLS;
	}

	public String getMaKhoaCLS() {
		return maKhoaCLS;
	}

	public void setMaKhoaCLS(String maKhoaCLS) {
		this.maKhoaCLS = maKhoaCLS;
	}

	public java.util.List<ClsMo> getListCtkq_B3121_1() {
		return listCtkq_B3121_1;
	}

	public void setListCtkq_B3121_1(java.util.List<ClsMo> listCtkqB3121_1) {
		listCtkq_B3121_1 = listCtkqB3121_1;
	}

	public java.util.List<ClsMo> getListCtkq_B3121_2() {
		return listCtkq_B3121_2;
	}

	public void setListCtkq_B3121_2(java.util.List<ClsMo> listCtkqB3121_2) {
		listCtkq_B3121_2 = listCtkqB3121_2;
	}

	private java.util.List<ClsMo> listCtkq_B3121_1 = new java.util.ArrayList<ClsMo>();

	private java.util.List<ClsMo> listCtkq_B3121_2 = new java.util.ArrayList<ClsMo>();

	@DataModel
	private java.util.List<ClsMo> listCtkq_B3121 = new java.util.ArrayList<ClsMo>();

	private Map<ClsMo, Boolean> mapSelect = new HashMap<ClsMo, Boolean>();

	@DataModelSelection
	@Out(required = false)
	private ClsMo nhapctSelected;

	private boolean updateNhapct = false;

	@In(required = false)
	@Out(required = false)
	Identity identity;

	@In(required = false)
	@Out(required = false)
	private String checkThanhtoan = "block";

	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;

	@In(required = false)
	@Out(required = false)
	private String chuyenManHinhThuocCLS_MaKhoa;

	@In(required = false)
	@Out(required = false)
	private String chuyenManHinhThuocCLS_TangMaSo;

	@In(required = false)
	@Out(required = false)
	private String chuyenManHinhThuocCLS_SoBenhAn;

	private String focusDochuyenTuManHinhThuocCLS;

	private Double tienTamUng = 0.0;
	private Double tienSoDu = 0.0;

	public Double getTienTamUng() {
		return tienTamUng;
	}

	public void setTienTamUng(Double tienTamUng) {
		this.tienTamUng = tienTamUng;
	}

	private boolean checkLoad = false;

	private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
	private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap<String, DmKhoa>();
	private List<SelectItem> listDmKhoaNTs = new ArrayList<SelectItem>();
	private List<DmKhoa> listDmKhoaNTAll = new ArrayList<DmKhoa>();
	private DmTang tangChuyenDen = new DmTang();
	private List<SelectItem> listDmTangs = new ArrayList<SelectItem>();
	private DmKhoa khoa;

	private String maChiDan;
	private String tenChiDan;

	public String getMaChiDan() {
		return maChiDan;
	}

	public void setMaChiDan(String maChiDan) {
		this.maChiDan = maChiDan;
	}

	public String getTenChiDan() {
		return tenChiDan;
	}

	public void setTenChiDan(String tenChiDan) {
		this.tenChiDan = tenChiDan;
	}

	public List<SelectItem> getListDmKhoaNTs() {
		return listDmKhoaNTs;
	}

	public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs) {
		this.listDmKhoaNTs = listDmKhoaNTs;
	}

	public void onblurMaKhoaAction() {
		log.info("-----BEGIN onblurMaKhoaAction()-----");
		if (khoa != null && khoa.getDmkhoaMa() != null) {
			String maKhoa = khoa.getDmkhoaMa();
			if (hmDmKhoaNTAll != null) {
				DmKhoa dmKhoa = (DmKhoa) hmDmKhoaNTAll
						.get(maKhoa.toUpperCase());
				if (dmKhoa != null) {
					khoa = dmKhoa;
					log
							.info("Tim ma khoa: Da thay khoa "
									+ khoa.getDmkhoaTen());
				} else {
					khoa = new DmKhoa();
					return;
				}
			}
			// 20120316 bao.ttc: them Tang
			tangChuyenDen = new DmTang();
			refreshDmTang();
		}
		log.info("-----END onblurMaKhoaAction()-----");
	}

	public void onblurTenKhoaAction() {
		log.info("-----BEGIN onblurTenKhoaAction()-----");
		if (khoa != null && khoa.getDmkhoaTen() != null) {
			String tenKhoa = khoa.getDmkhoaTen();
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
				khoa.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
				khoa.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa().toUpperCase());
				khoa.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
			} else {
				khoa = new DmKhoa();
				return;
			}
			// 20120316 bao.ttc: them Tang
			tangChuyenDen = new DmTang();
			refreshDmTang();
		}
		log.info("-----END onblurTenKhoaAction()-----");
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

	public void onblurTenTangAction() {
		if (tangChuyenDen != null && tangChuyenDen.getDmtangTen() != null) {
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			List<DmTang> lstTangs = dtUtil.findTenLike("DmTang", "dmtangTen",
					tangChuyenDen.getDmtangTen());
			if (lstTangs != null && lstTangs.size() > 0) {
				tangChuyenDen = lstTangs.get(0);
			} else {
				tangChuyenDen = new DmTang();
			}
		}
	}

	public void refreshDmTang() {
		listDmTangs = new ArrayList<SelectItem>();
		if (khoa != null && khoa.getDmkhoaMaso() != null) {
			String khoaMa = khoa.getDmkhoaMa();
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			// Get tat ca cac tang cua khoa chuyen den, show gia tri default
			// truoc
			List<DmTang> lstDmTangs = dtUtil.findMaLike("DmTang",
					"dmkhoaMaso.dmkhoaMa", "dmtangChon", khoaMa, true);
			if (lstDmTangs != null && lstDmTangs.size() > 0) {
				for (DmTang dmTang : lstDmTangs) {
					listDmTangs.add(new SelectItem(dmTang.getDmtangTen()));
				}
				// 20120315 bao.ttc: Truong hop khong co tang default thi chon
				// tang dau tien trong list
				tangChuyenDen = lstDmTangs.get(0);
				for (DmTang dmTang : lstDmTangs) {
					if (dmTang.getDmtangDefault().booleanValue() == true) {
						tangChuyenDen = dmTang;
						break;
					}
				}
			}
		}
	}

	@Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
	@Begin(join = true)
	public String init() throws Exception {
		log.info("***Starting init ***");
		emtyData();
		resetValue();
		log.info("***Finished init ***");

		// 20120417 bao.ttc: tam thoi khong dung chuc nang nay
		if ( chuyenManHinhThuocCLS_SoBenhAn != null && !chuyenManHinhThuocCLS_SoBenhAn.equals("")
				&& chuyenManHinhThuocCLS_MaKhoa != null && !chuyenManHinhThuocCLS_MaKhoa.equals("")
				&& chuyenManHinhThuocCLS_TangMaSo != null && !chuyenManHinhThuocCLS_TangMaSo.equals("") ){
					
			hoSoBenhAn.setHsbaSovaovien(chuyenManHinhThuocCLS_SoBenhAn);
			DieuTriUtilDelegate dtDel = DieuTriUtilDelegate.getInstance();
			DmKhoa khoaTemp = (DmKhoa) dtDel.findByMa(chuyenManHinhThuocCLS_MaKhoa.toUpperCase(), "DmKhoa", "dmkhoaMa");
			
			if (khoaTemp != null && khoaTemp.getDmkhoaMa() != null && !khoaTemp.getDmkhoaMa().equals("") ) {
				khoa = khoaTemp;
				int tangMaso = 0;
				try {
					tangMaso = Integer.parseInt(chuyenManHinhThuocCLS_TangMaSo);
				} catch (Exception e) {
					log.info(" Thuoc Noi tru INIT Exception: Parse TangMaSo !!");
					tangMaso = 0;
				}
				DmTang tangChuyenDenTemp = (DmTang) dtDel.findByMaSo(tangMaso, "DmTang", "dmtangMaso");
				
				if (tangChuyenDenTemp != null) {
					tangChuyenDen = tangChuyenDenTemp;
					displayInfor();
					focusDochuyenTuManHinhThuocCLS = "true";
				}
			}
		}

		tenChuongTrinh = MyMenuYTDTAction.vienPhiTaiKhoa;
		return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_CanLamSan";
	}

	@End
	public void endFunct() {

	}

	private void emtyData() {

		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);
		hoSoBenhAn = new Hsba();
		// setInforIfNullForTiepDon();
		SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);

		clsMo = new ClsMo();
		// clsMo.setHsbaSovaovien(hoSoBenhAn);

		SetInforUtil.setInforIfNullForClsMo(clsMo);
		nhapctSelected = new ClsMo();
		SetInforUtil.setInforIfNullForClsMo(nhapctSelected);
		tuoi = "";
		ngaySinh = "";
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		ngayTt = formatter.format(new Date());

		resultHidden = "";
		reportFinished = "";
		reportFileNameHid = "";
		ngayVv = "";
		hsbaBHYT = new HsbaBhyt();
		// diengiai ="";
		khoaCDHA = "";
		
		checkLoad = false;
		checkThanhtoan ="block";
		
		refreshDmKhoaNT();
		if (khoa == null) {
			khoa = new DmKhoa();
			tangChuyenDen = new DmTang();
		}
		
		// new
		mapSelect = new HashMap<ClsMo, Boolean>();
		maKhoa = "";
		tangMaso = 0;
		maKhoaCLS = "";
		maSoKhoaCLS = 0;
		maPhieu = "";
		strNgayBatDauBh = "";
		strNgayHetHanBh = "";
		ngayBatDauBh = null;
		ngayHetHanBh = null;
		listCtkq_B3121 = new ArrayList<ClsMo>();
		listCtkq_B3121_1 = new ArrayList<ClsMo>();
		listCtkq_B3121_2 = new ArrayList<ClsMo>();

	}

	private void hasNoHSBA() {
		emtyData();
		resetValue();
	}

	public String troVe() {
		try {
			log.info("***** troVe()");
			return "B3232_Canlamsanphongmo";
		} catch (Exception e) {
			log.info("***** End exception = " + e);
		}
		return null;
	}

	public void resetValue() {
		loai = "";
		maCLS = "";
		maSoCLS = null;
		tenCLS = "";
		mien = new Boolean(false);
		ngoaiDanhMuc = new Boolean(false);
		yeuCau = new Boolean(false);
		khongthu = new Boolean(false);
		kyThuatCao = new Boolean(false);
		soLuong = 0;
		donGia = new Double(0);
		dichVu = new Boolean(false);
		donGiaDieuChinh = "";
		ghiChu = "";
		// phuc.lc 24-09-2010
		donGiaBH = new Double(0);
		phanDV = new Double(0);
		selectedIndex = -1;
		ngayVv = "";
	}

	public void CopyFrom(ClsMo cls) {
		log.info("Begining CopyFrom()" + cls);
		loai = cls.getClsmoLoai();
		maSoCLS = cls.getClsmoMahang().getDtdmclsbgMaso();
		maCLS = cls.getClsmoMahang().getDtdmclsbgMa();
		tenCLS = cls.getClsmoMahang().getDtdmclsbgDiengiai();
		// 20110307 bao.ttc: get thong tin khoa CLS tu cac CLS da add (neu co)
		maKhoaCLS = (cls.getClsmoKhoa() != null ? cls.getClsmoKhoa()
				.getDmkhoaMa() : "");
		mien = cls.getClsmoMien();
		ngoaiDanhMuc = cls.getClsmoNDM();
		yeuCau = cls.getClsmoYeucau();
		khongthu = cls.getClsmoKhongthu();
		kyThuatCao = cls.getClsmoKtcao();
		soLuong = cls.getClsmoLan();
		donGia = cls.getClsmoDongia();
		dichVu = cls.getClsmoDichvu();
		donGiaDieuChinh = "";
		
		ghiChu = cls.getClsmoGhichu();
		// phuc.lc 24-09-2010
		donGiaBH = cls.getClsmoDongiabh();
		phanDV = cls.getClsmoPhandv();
		// SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		// ngayTt = formatter.format(cls.getClsmoNgay()); // 20120427 bao.ttc: ko can vi ban dau da load toan bo list cls theo ngay
		clsMoMaso = cls.getClsmoMa();
		log.info("ndm:" + ngoaiDanhMuc);
		log.info("End CopyFrom()");
	}

	public void CopyTo(ClsMo cls) {
		log.info("Begining CopyTo()" + cls);
		// phuc.lc 24-09-2010
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate
				.getInstance();
		DtDmClsBangGia dmClsBg = (DtDmClsBangGia) dieuTriUtilDelegate
				.findByMaSo(maSoCLS, "DtDmClsBangGia", "dtdmclsbgMaso");
		log.info("dmClsBg = " + dmClsBg);
		cls.setClsmoMahang(dmClsBg);
		// cls.setClsmoLoai(loai);
		// cls.getClsmoMahang().setDtdmclsbgMa(maCLS);
		// cls.getClsmoMahang().setDtdmclsbgMaso(maSoCLS);
		// cls.getClsmoMahang().setDtdmclsbgDiengiai(tenCLS);
		cls.setClsmoLoai(dmClsBg.getDtdmclsbgPhanloai().getDtdmclsMa());
		cls.setClsmoLoaicls(dmClsBg.getDtdmclsbgMaloai());
		cls.setClsmoLoai2(dmClsBg.getDtdmclsbgLoai());
		cls.setClsmoSoml(dmClsBg.getDmclsbgSoml());
		// 20110307 bao.ttc: tim Khoa CLS trong TH search truc tiep o CLS PT-TT
		List<DtDmClsKhoa> dtDmClsKhoaList = DtDmClsKhoaDelegate.getInstance()
				.findByMaSoCLS(dmClsBg.getDtdmclsbgMaso());
		if (dtDmClsKhoaList.size() > 0) {
			// 20111011 bao.ttc: check null
			if (dtDmClsKhoaList.get(0).getDmkhoaMaso() != null) {
				maKhoaCLS = dtDmClsKhoaList.get(0).getDmkhoaMaso()
						.getDmkhoaMa();
				cls.setClsmoKhoa(dtDmClsKhoaList.get(0).getDmkhoaMaso());
			}
		} else {
			maKhoaCLS = "";
		}
		// 20110307 bao.ttc: END

		cls.setClsmoMien(mien);
		cls.setClsmoNDM(ngoaiDanhMuc);
		cls.setClsmoYeucau(yeuCau);
		cls.setClsmoKhongthu(khongthu);
		cls.setClsmoKtcao(kyThuatCao);
		cls.setClsmoLan(soLuong);
		
		Double donGiaNew = null;
		if (donGiaDieuChinh != null && !donGiaDieuChinh.equals("")) {
			try {
				donGiaNew = Double.parseDouble(donGiaDieuChinh);
			} catch (Exception ex) {
				donGiaNew = null;
			}
		}
		
		if (donGiaNew != null) {
			cls.setClsmoDongia(donGiaNew);
			cls.setClsmoDongiabh(donGiaNew);
			cls.setClsmoGhichu(ghiChu + " - \u0110i\u1EC1u ch\u1EC9nh \u0111\u01A1n gi\u00E1"); // String: " - Điều chỉnh đơn giá"
			cls.setClsmoChandoan("Dieu chinh don gia: NEW = " + donGiaNew + " -- OLD: " + donGia + " -- BH: " + donGiaBH);
			log.info("------###------ Dieu chinh don gia: NEW = " + donGiaNew + " -- OLD: " + donGia + " -- BH: " + donGiaBH);
		} else {
			cls.setClsmoDongia(donGia);
			cls.setClsmoDongiabh(donGiaBH);
			cls.setClsmoGhichu(ghiChu);
		}
		
		// phuc.lc 24-09-2010
		cls.setClsmoDichvu(dichVu);
		cls.setClsmoPhandv(phanDV);
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		try {
			cls.setClsmoNgay(formatter.parse(ngayTt));
		} catch (Exception ex) {
			cls.setClsmoNgay(new Date());
		}
		if (updateNhapct == true) {
			cls.setClsmoMa(clsMoMaso);
		}
		log.info("ndm:" + ngoaiDanhMuc);
		log.info("End CopyTo()");
	}

	public void nhapctAjax(int index) throws Exception {
		log.info("*****Begin nhapctAjax(), index  =  " + index);
		log.info("ndm:" + ngoaiDanhMuc);
//		if (maPhieu != null && !maPhieu.equals("")) {
//			log.info("maPhieu:" + maPhieu);
//			return;
//		}

		if (nhapctSelected.getClsmoMaphieu() != null
				&& !nhapctSelected.getClsmoMaphieu().equals("")) {
			log.info("nhapctSelected.getClsmoMaphieu():"
					+ nhapctSelected.getClsmoMaphieu());
			return;
		}

		selectedIndex = index;
		// CopyFrom(nhapctSelected);
		CopyFrom(listCtkq_B3121.get(index));
		updateNhapct = true;
		log.info("***********end nhapctAjax***********");

	}

	private void insertRow() {
		log.info("begin cache chi tiet ket qua");
		ClsMo cls = new ClsMo();
		// log.info(" tenCLS0 " + tenCLS);

		SetInforUtil.setInforIfNullForClsMo(cls);
		CopyTo(cls);
		// log.info(" tenCLS1 " + tenCLS);
		setinfor(cls);
		if (listCtkq_B3121 == null) {
			listCtkq_B3121 = new ArrayList<ClsMo>();
		}

		// log.info("hoSoBenhAn:"+hoSoBenhAn);
		cls.setHsbaKhoa(hsbaKhoa);

		listCtkq_B3121.add(cls);
		mapSelect.put(cls, true);
		// log.info(" tenCLS2 " + tenCLS);
		// log.info(" mapSelect " + mapSelect.get(cls));

	}

	private void updateRow() {
		log.info("*****Begin updateRow(), selectedIndex = " + selectedIndex);

		// int i = listCtkq_B3121.indexOf(nhapctSelected);
		// log.info("In updateRow(), i = " + i);
		ClsMo clsmo = new ClsMo();
		com.iesvn.yte.dieutri.setinfor.SetInforUtil
				.setInforIfNullForClsMo(clsmo);
		CopyTo(clsmo);
		// CopyTo(nhapctSelected);
		/*
		 * if (selectedIndex < 0) { insertRow(); }else{ //log.info("****i=" + i
		 * + "******");
		 * 
		 * log.info("hoSoBenhAn:"+hoSoBenhAn);
		 * nhapctSelected.setHsbaKhoa(hsbaKhoa);
		 * 
		 * listCtkq_B3121.set(selectedIndex, nhapctSelected);
		 * 
		 * 
		 * }
		 */
		mapSelect.remove(listCtkq_B3121.get(selectedIndex));
		listCtkq_B3121.set(selectedIndex, clsmo);
		mapSelect.put(clsmo, true);
		updateNhapct = false;
	}

	public String showDate(Date ngayCls) {
		if (ngayCls == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		return formatter.format(ngayCls);
	}

	public void selectCls(ClsMo clsMo) {
		dsIn = "";
		try {
			clsMo = (ClsMo) BeanUtils.cloneBean(clsMo);

			boolean check = mapSelect.get(clsMo);
			mapSelect.put(clsMo, check);
			Set<ClsMo> setCls = mapSelect.keySet();
			for (Iterator iterator = setCls.iterator(); iterator.hasNext();) {
				ClsMo clsMoTmp = (ClsMo) iterator.next();
				clsMoTmp = (ClsMo) BeanUtils.cloneBean(clsMoTmp);
				if (mapSelect.get(clsMoTmp) == true) {
					dsIn += "," + clsMoTmp.getClsmoMahang(true).getDtdmclsbgMaso();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Ham chuyen chi tiet nhap xuong duoi
	public void enter() throws Exception {
		log.info("*****Begin Enter() *****");
		log.info("ndm:" + ngoaiDanhMuc);

//		if (maPhieu != null && !maPhieu.equals("")) {
//			log.info("return maPhieu : " + maPhieu);
//			return;
//		}

		if (this.maCLS.equals("")) {
			resetValue();
			return;
		}

		if (this.soLuong == 0) {
			resetValue();
			return;
		}
		
		if (listCtkq_B3121 == null) {
			listCtkq_B3121 = new ArrayList<ClsMo>();
		}
		
		if (this.hoSoBenhAn.getHsbaSovaovien() == null
				|| this.hoSoBenhAn.getHsbaSovaovien().equals("")

		) {
			resetValue();
			return;

		}

		log.info("updateNhapct " + updateNhapct);
		if (updateNhapct) {
			updateRow();

		} else {
			insertRow();
		}
		resetValue();
		log.info("*****End Enter() *****");
	}

	// Ham delete chi tiet thuc hien khi nhan Link Xoa mot dong chi tiet
	public void delete(int index) throws Exception {
		log.info("*****Begin delete(), delete index =  " + index);

//		if (maPhieu != null && !maPhieu.equals("")) {
//			return;
//		}
		
		ClsMo clsMo = listCtkq_B3121.get(index);
		if (nhapctSelected.getClsmoMaphieu() != null
				&& !nhapctSelected.getClsmoMaphieu().equals("")) {
			return;
		}
		if (clsMo!= null){
			clsMo = (ClsMo) BeanUtils.cloneBean(clsMo);
			mapSelect.remove(clsMo);
			log.info("*****remove mapSelect ***** " + index);
		}
		
		listCtkq_B3121.remove(index);

		resetValue();
		log.info("*****End delete() *****");
	}

	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() throws Exception {
		
		// 20120508 bao.ttc: save user action log to database
		YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
		YteLog yteLog = new YteLog();
		String yteLogString = "";
		String yteListData = "";
		yteLog.setForm("CLS_Noi_Tru");
		yteLog.setUserId(identity == null ? "" : identity.getUsername());
		

		if (listCtkq_B3121 == null) {
			listCtkq_B3121 = new ArrayList<ClsMo>();
		}
		log.info("*****Begin Ghi nhan() *****");
		log.info("*****so phan tu insert *****" + listCtkq_B3121.size());

		if (this.hoSoBenhAn.getHsbaSovaovien() == null
				|| this.hoSoBenhAn.getHsbaSovaovien().equals("")) {

			resetValue();
			return;
		}
		yteLog.setObjectId(hoSoBenhAn.getHsbaSovaovien());
		yteLogString += "Khoa:" + maKhoa + " - Tang:" + (tangChuyenDen == null ? "NULL" : tangChuyenDen.getDmtangMa());

		try {
			
			ClsMoDelegate clsmoDelegate = ClsMoDelegate.getInstance();
			HsbaKhoaDelegate hsbaKhoaDel = HsbaKhoaDelegate.getInstance();
			// 20110309 bao.ttc: them thong tin Tang
			HsbaKhoa hsbaKhoa = hsbaKhoaDel.findBySoVaoVienAndKhoaMaAndTang(
					hoSoBenhAn.getHsbaSovaovien(), maKhoa, tangMaso);
			yteLogString += " - BAK:" + (hsbaKhoa == null ? "NULL" : hsbaKhoa.getHsbakhoaMaso());
			
			Date ngayThToan = new Date();
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if (!("".equals(ngayTt))) {
				Calendar cal = Calendar.getInstance();

				cal.setTime(df.parse(ngayTt));
				ngayThToan = cal.getTime();
			} else {
				ngayThToan = df.parse(df.format(ngayThToan));
			}

			ArrayList<ClsMo> arrayListHasMaPhieu = new ArrayList<ClsMo>();
			for (int i = 0; i < listCtkq_B3121.size(); i++) {
				//boolean check = mapSelect.get(listCtkq_B3121.get(i));
				//mapSelect.remove(listCtkq_B3121.get(i));

				RemoveUtil.setAllNullForClsMo(listCtkq_B3121.get(i));

				if (nhanVienThucHien != null
						&& !nhanVienThucHien.getDtdmnhanvienMa().equals("")) {
					listCtkq_B3121.get(i).setClsmoThuchien(nhanVienThucHien);
				} else {
					listCtkq_B3121.get(i).setClsmoThuchien(null);
				}
				
				if (listCtkq_B3121.get(i).getHsbaKhoa() == null) {
					listCtkq_B3121.get(i).setHsbaKhoa(hsbaKhoa);
				}
				
				if (listCtkq_B3121.get(i).getClsmoMaphieu() != null && !listCtkq_B3121.get(i).getClsmoMaphieu().equals("")) {
					arrayListHasMaPhieu.add(listCtkq_B3121.get(i));
				}
				
			}

			for (ClsMo cls : arrayListHasMaPhieu) {
				listCtkq_B3121.remove(cls);
			}

			
			String ghiNhanResult = clsmoDelegate.createClsMoForCLSPhauThuat(listCtkq_B3121, hoSoBenhAn.getHsbaSovaovien(), maKhoa, tangMaso, ngayTt);
			log.info(" ##### ghiNhanResult: " + ghiNhanResult);
			
			// thanhpham add 20/12/2011
			try {
				if (IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES")) {
					log.info("KET_NOI_MAY_XET_NGHIEM");
					String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
					// String url =
					// "jdbc:sqlserver://127.0.0.1\\test:1433;databaseName=test";
					// String username = "sa";
					// String password = "123456";
					String url = "jdbc:sqlserver://"
							+ IConstantsRes.LABCONN_SERVER + ":"
							+ IConstantsRes.LABCONN_PORT
							+ ";useUnicode=true;characterEncoding=UTF-8;"
							+ ";databaseName=" + IConstantsRes.LABCONN_DATABASE;
					String username = IConstantsRes.LABCONN_USERNAME;
					String password = IConstantsRes.LABCONN_PASSWORD;
					Class.forName(driver); // load sqlserver driver
					Connection conn = DriverManager.getConnection(url,
							username, password);
					log.info("conn");
					List<ClsMo> listClsMo = (List<ClsMo>) ClsMoDelegate
							.getInstance().findBySoVaoVien(
									hoSoBenhAn.getHsbaSovaovien());
					log.info("listClsMo " + listClsMo.size());
					for (int i = 0; i < listClsMo.size(); i++) {
						if (listClsMo.get(i).getClsmoTh() == null
								|| listClsMo.get(i).getClsmoTh() == false) {
							List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate
									.getInstance().findByAllConditions(
											"ClsKetQua", "clsmoMaso",
											"clskqTen",
											listClsMo.get(i).getClsmoMa() + "",
											"");
							for (int j = 0; j < listClsKetQua.size(); j++) {
								String OrderID = hoSoBenhAn.getHsbaSovaovien()
										.replaceFirst("BD", "NO");
								DateFormat formatter = new SimpleDateFormat(
										"yyyy/MM/dd");
								String DateOrder = formatter.format(listClsMo
										.get(i).getClsmoNgay());
								String Invoice = hsbaBHYT.getHsbabhytSothebh();
								String ObjectID = hoSoBenhAn.getDoituongMa()
										.getDmdoituongMa();
								String TestCodeHIS = listClsKetQua.get(j)
										.getClskqMa();
								String PID = hoSoBenhAn.getBenhnhanMa(true)
										.getBenhnhanMa();
								String PName = hoSoBenhAn.getBenhnhanMa(true)
										.getBenhnhanHoten();
								String Address = "";
								if (hoSoBenhAn.getBenhnhanMa(true)
										.getBenhnhanDiachi() != null
										&& !hoSoBenhAn.getBenhnhanMa(true)
												.getBenhnhanDiachi().equals(""))
									Address += hoSoBenhAn.getBenhnhanMa(true)
											.getBenhnhanDiachi()
											+ ",";
								if (hoSoBenhAn.getBenhnhanMa(true)
										.getXaMa(true).getDmxaTen() != null
										&& !hoSoBenhAn.getBenhnhanMa(true)
												.getXaMa(true).getDmxaTen()
												.equals(""))
									Address += hoSoBenhAn.getBenhnhanMa(true)
											.getXaMa(true).getDmxaTen()
											+ ",";
								if (hoSoBenhAn.getBenhnhanMa(true).getHuyenMa(
										true).getDmhuyenTen() != null
										&& !hoSoBenhAn.getBenhnhanMa(true)
												.getHuyenMa(true)
												.getDmhuyenTen().equals(""))
									Address += hoSoBenhAn.getBenhnhanMa(true)
											.getHuyenMa(true).getDmhuyenTen()
											+ ",";
								if (hoSoBenhAn.getBenhnhanMa(true).getTinhMa(
										true).getDmtinhTen() != null
										&& !hoSoBenhAn.getBenhnhanMa(true)
												.getTinhMa(true).getDmtinhTen()
												.equals(""))
									Address += hoSoBenhAn.getBenhnhanMa(true)
											.getTinhMa(true).getDmtinhTen();
								String Age = hoSoBenhAn.getBenhnhanMa(true)
										.getBenhnhanNamsinh();
								String Sex = "F";
								if (hoSoBenhAn.getBenhnhanMa(true)
										.getDmgtMaso().getDmgtTen()
										.toLowerCase().equals("nam")) {
									Sex = "M";
								}
								HsbaChuyenMon hsbaChuyenMon = HsbaChuyenMonDelegate
										.getInstance()
										.findBySoVaoVien_LastHSBACM(
												hoSoBenhAn.getHsbaSovaovien());
								String DoctorID = "a";
								if (hsbaChuyenMon.getHsbacmBacsi(true) != null) {
									DoctorID = hsbaChuyenMon.getHsbacmBacsi(
											true).getDtdmnhanvienTen();
								}
								String Diagnostic = "";
								if (hsbaChuyenMon.getHsbacmBenhchinh() != null) {
									Diagnostic = hsbaChuyenMon
											.getHsbacmBenhchinh()
											.getDmbenhicdTen();
								}
								String LocationID = "";
								if (listClsMo.get(i).getClsmoKhoa() != null) {
									LocationID = listClsMo.get(i)
											.getClsmoKhoa().getDmkhoaMa();
								}
								String query1 = " Select * from ["
										+ IConstantsRes.LABCONN_DATABASE
										+ "].[dbo].[Tbl_Order_HIS]";
								query1 += " where 1 = 1 ";
								query1 += " and OrderID = '" + OrderID + "' ";
								query1 += " and TestCodeHIS = '" + TestCodeHIS
										+ "' ";
								PreparedStatement stmt1 = conn
										.prepareStatement(query1,
												Statement.RETURN_GENERATED_KEYS);
								ResultSet rs1 = stmt1.executeQuery();
								if (rs1 != null) {
									if (rs1.next()) {
										continue;
									}

								}
								String query = " INSERT INTO ["
										+ IConstantsRes.LABCONN_DATABASE
										+ "].[dbo].[Tbl_Order_HIS] ([OrderID],[DateOrder],[Invoice],[ObjectID],[TestCodeHIS],[PID],[PName],[Address],[Age],[Sex], [DoctorID],[Diagnostic], [LocationID]) "
										+ " VALUES " + " ('" + OrderID + "','"
										+ DateOrder + "','" + Invoice + "','"
										+ ObjectID + "','" + TestCodeHIS
										+ "','" + PID + "',N'" + PName + "',N'"
										+ Address + "','" + Age + "','" + Sex
										+ "','" + DoctorID + "',N'"
										+ Diagnostic + "','" + LocationID
										+ "') ";
								// " ('1a','2010-12-31','TP','XN032', 'Phạm đức thành', '1985', 'M') ";
								PreparedStatement stmt = conn.prepareStatement(
										query, Statement.RETURN_GENERATED_KEYS);
								int num = stmt.executeUpdate();
								if (num == 1) {
									ResultSet rs = stmt.getGeneratedKeys();
									log.info("ResultSet: " + rs.toString());
									if (rs != null) {
										while (rs.next()) {
											ResultSetMetaData rsMetaData = rs
													.getMetaData();
											int columnCount = rsMetaData
													.getColumnCount();

											for (int k = 1; k <= columnCount; k++) {
												String key = rs.getString(k);
												log.info("key " + k + " is "
														+ key);
											}
										}
									}
								}
								// stmt.close();
							}
						}
					}
					// conn.close();
					// clsKetQua.setClskqMa(listDtDmClsKetQua.get(j).getDtdmclskqMa());
					// clsKetQua.setClskqTen(listDtDmClsKetQua.get(j).getDtdmclskqTen());
					// clsKetQua.setClskqGhiChu(listDtDmClsKetQua.get(j).getDtdmclskqGhiChu());
					// clsKetQua.setClskhamMaso(listCtkq_B121_2.get(i));
					// ClsKhamDelegate.getInstance().edit(listCtkq_B121_2.get(i));
					// ClsKetQuaDelegate.getInstance().create(clsKetQua);
				}

			} catch (Exception e) {
				// e.printStackTrace();
				log.error("*************loi***********" + e.toString());

			}
			// thanh add 24/11/2011

			// ================================================================
//			HsThtoanDelegate hsThtoanDelegate = HsThtoanDelegate.getInstance();
//			HsThtoan hsThtoan = hsThtoanDelegate.findBySovaovien(hoSoBenhAn.getHsbaSovaovien());
//			if (hsThtoan != null) {
//				hsThtoanDelegate.remove(hsThtoan);
//			}
//			hsThtoan = new HsThtoan();
//			hsThtoan.setHsbaSovaovien(hoSoBenhAn);
//			HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hoSoBenhAn);
//			hsthtoanUtil.tinhToanChoHSTT(hsThtoan);
//			hsThtoan.setHsthtoanKhoa(hoSoBenhAn.getHsbaKhoadangdt());
//			Utils.setInforForHsThToan(hsThtoan);
//			hsThtoanDelegate.create(hsThtoan);
			// ================================================================
			
			// 20120702 bao.ttc: THREAD RUN de tang toc do xu ly
			HsttThreadUtil capnhatHstt = new HsttThreadUtil();
			capnhatHstt.setHoSoBenhAn(hoSoBenhAn);
			capnhatHstt.start();

			yteListData = "CLS: ";
			// 20120507 bao.ttc: load lai CLS de co the In DS
			List<ClsMo> clsmo_tmp = clsmoDelegate.findBySoVaoVienAndKhoaMaAndTangAndNgay(hoSoBenhAn.getHsbaSovaovien(), maKhoa, tangMaso, ngayTt);
			if (clsmo_tmp != null && clsmo_tmp.size() > 0) {
				mapSelect = new HashMap<ClsMo, Boolean>();
				dsIn = "";
				int stt = 1;
				for (ClsMo clsMo : clsmo_tmp) {
					mapSelect.put(clsMo, true);
					dsIn += "," + clsMo.getClsmoMahang(true).getDtdmclsbgMaso();
					
					yteListData += stt + "/ " + (clsMo.getClsmoMahang() == null ? "NULL" : clsMo.getClsmoMahang(true).getDtdmclsbgMa())
								+ "  " + df.format(clsMo.getClsmoNgay())
								+ "  SL:" + clsMo.getClsmoLan()
								+ "  Gia:" + clsMo.getClsmoDongia()
								//+ "  BAK:" + (clsMo.getHsbaKhoa() == null ? "NULL" : clsMo.getHsbaKhoa(true).getHsbakhoaMaso())
								+ " # ";
					stt++;
				}
				listCtkq_B3121 = clsmo_tmp;
			}
			// END -- 20120507 bao.ttc: load lai CLS de co the In DS
			
			// 20120508 bao.ttc: save user action log to database
			yteLog.setLogString(yteLogString);
			yteLog.setListData(yteListData);
			yteLog.setDateTime(new Date());
			yteLogDele.create(yteLog);
			
			FacesMessages.instance().add(IConstantsRes.SUCCESS);
			resultHidden = "success";
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			resultHidden = "fail";
			log.error("*************loi***********" + e.toString());
			e.printStackTrace();
		}
		try {

			for (int i = 0; i < listCtkq_B3121.size(); i++) {
				SetInforUtil.setInforIfNullForClsMo(listCtkq_B3121.get(i));
				//log.info("i = " + i + ", khoa = " + listCtkq_B3121.get(i).getHsbaKhoa());
				//SetInforUtil.setInforIfNullForHSBA(listCtkq_B3121.get(i).getHsbaKhoa().getHsbaSovaovien());
			}
			// ResetForm();

		} catch (Exception e) {
			log.error("Loi reset form : " + e.toString());
			e.printStackTrace();
		}
		log.info("*****End Ghi nhan() *****");
		this.reportFinished = "";
	}

	public void nhaplai() throws Exception {
		ResetForm();
	}

	// Ham khi nhan nut sua lai
	public void sualai() throws Exception {
		try {
			ResetForm();
			resultHidden = "";
			this.reportFinished = "";
		} catch (Exception e) {
			log.error("Loi reset form : " + e.toString());
		}
	}

	// Ham khi nhan nut In phieu
	// public String inphieu(String loaiFile) throws Exception {
	// log.info("Begining inphieu()");
	//		
	// if (maPhieu == null || maPhieu.equals("")){
	// return "";
	// }
	//		
	// try {
	//			
	// xuatReportCLs("B3232_Phieuthanhtoanclsphongmo", loaiFile);
	// } catch (Exception e) {
	// log.info("Loi trong khi xuat report" + e.toString());
	// }
	// log.info("End inphieu()");
	// return "B3232_Chonmenuxuattaptin";
	// }

	private void setOtherValue() {
		log.info("Begining setOtherValue()");

		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (hoSoBenhAn.getBenhnhanMa(true).getBenhnhanNgaysinh() != null) {
			ngaySinh = formatter.format(hoSoBenhAn.getBenhnhanMa(true)
					.getBenhnhanNgaysinh().getTime());
			log.info("Ngay sinh :" + ngaySinh);
		}
		// 20120427 bao.ttc: remove vi ko con su dung
//		if (clsMo.getClsmoNgay() != null && !clsMo.getClsmoNgay().equals("")) {
//			ngayTt = formatter.format(clsMo.getClsmoNgay().getTime());
//			log.info("Ngay thanh toan :" + ngayTt);
//		}
		if (benhNhan.getDmgtMaso() != null) {
			if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())) {
				gioi = "r1"; // 1 : Name
			} else {
				gioi = "r2";
			}
		} else {
			gioi = null;
		}
		log.info("End setOtherValue()");
	}

	private void setinfor(ClsMo nhapctSelected) {
		// SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		DtDmClsBangGia dmkythuat = nhapctSelected.getClsmoMahang();
		// dmkythuat.setDtdmclsbgDiengiai(tenCLS);
		nhapctSelected.setClsmoMahang(dmkythuat);
		if (hsbaKhoa != null) {
			nhapctSelected.setHsbaKhoa(hsbaKhoa);
		}
		try {
			if (!("".equals(ngayTt))) {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				cal.setTime(df.parse(ngayTt));
				nhapctSelected.setClsmoNgay(cal.getTime());
			}
		} catch (Exception e) {

		}
		log.info(" tenCLS3 " + tenCLS);
		// if (clsmo.getClsmoBanmo() != null) {
		// nhapctSelected.setClsmoBanmo(clsmo.getClsmoBanmo());
		// }
	}

	public void SetInforAllCLS(List<ClsMo> clsmo_tmp) {
		if (clsmo_tmp == null || clsmo_tmp.size() == 0) {
			log.info("displayInfor   CLS Mo ..... NULL or size = 0");
			listCtkq_B3121 = new ArrayList<ClsMo>();
		} else {
			dsIn = "";
			for (ClsMo clsMo : clsmo_tmp) {
				mapSelect.put(clsMo, true);
				dsIn += "," + clsMo.getClsmoMahang(true).getDtdmclsbgMaso();
			}
			listCtkq_B3121 = clsmo_tmp;

			// ArrayList<Clsmo> listCtkq_B3121DaThanhToan = new
			// ArrayList<Clsmo>();

			for (int i = 0; i < listCtkq_B3121.size(); i++) {
				ClsMo cls = listCtkq_B3121.get(i);

				if (cls.getClsmoTh() == null || cls.getClsmoTh() == false
						&& cls.getClsmoMahang() != null) {
					if (cls.getClsmoMahang().getDtdmclsbgXn() != null
							&& cls.getClsmoMahang().getDtdmclsbgXn() == true) {
						cls = connectLabconn(cls);
						ClsMoDelegate.getInstance().edit(cls);
					}
					if (cls.getClsmoKhoa() != null
							&& cls.getClsmoKhoa().getDmkhoaMa().equals("CDH")) {
						cls = connectPACS(cls);
						ClsMoDelegate.getInstance().edit(cls);
					}
				}

				SetInforUtil.setInforIfNullForClsMo(cls);
				cls.setHsbaKhoa(hsbaKhoa);

			}
		}
	}

	public void displayInforMaPhieu() {
		if (maPhieu == null || maPhieu.equals("")) {
			return;
		}
		ClsMoDelegate clsmoDelegate = ClsMoDelegate.getInstance();

		List<ClsMo> clsmo_tmp = clsmoDelegate.findByMaPhieu(maPhieu);

		if (clsmo_tmp == null || clsmo_tmp.size() == 0) {
			return;
		}

		hoSoBenhAn = clsmo_tmp.get(0).getHsbaKhoa().getHsbaSovaovien();
		SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);

		benhNhan = hoSoBenhAn.getBenhnhanMa();
		SetInforUtil.setInforIfNullForBN(benhNhan);

		SetInforAllCLS(clsmo_tmp);
		setOtherValue();

	}

	// Hien thi thong tin CLS mo cua benh nhan sau khi nhap Ban mo va ma
	// tiep don
	public void displayInfor() throws Exception {
		try {
			log.info("Begining displayInfor()");
			mapSelect = new HashMap<ClsMo, Boolean>();

			if (hoSoBenhAn == null || hoSoBenhAn.getHsbaSovaovien() == null
					|| hoSoBenhAn.getHsbaSovaovien().equals("")
					|| khoa.getDmkhoaMa() == null
					|| khoa.getDmkhoaMa().equals("")) {
				hasNoHSBA();
				FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, "",
						"");
				listCtkq_B3121 = new ArrayList<ClsMo>();
				return;
			}

			// 20120417 bao.ttc: them DmTang
			maKhoa = khoa.getDmkhoaMa().toUpperCase();
			String hsbaMa = hoSoBenhAn.getHsbaSovaovien();

			if (tangChuyenDen != null && tangChuyenDen.getDmtangMaso() != null) {
				tangMaso = tangChuyenDen.getDmtangMaso();
			}

			if (maKhoa.equals("") || hsbaMa == null || hsbaMa.equals("")
					|| (tangMaso == 0)) {
				FacesMessages.instance().add(
						IConstantsRes.NHAP_CHINH_XAC_KHOA_BUONG);
				return;
			}

			// 20110628 bao.ttc: fix da chuyen khoa khac van co the xem thong
			// tin cu (khong duoc ghi nhan)
			HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
			// 20120309 bao.ttc: them thong tin Tang
			// hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(hoSoBenhAn.getHsbaSovaovien(), maKhoa);
			hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMaAndTang(
					hoSoBenhAn.getHsbaSovaovien(), maKhoa, tangMaso);

			if (hsbaKhoa == null) {
				hasNoHSBA();
				FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, "",
						"");
				listCtkq_B3121 = new ArrayList<ClsMo>();
				return;
			}

			hoSoBenhAn = hsbaKhoa.getHsbaSovaovien();
			if (hoSoBenhAn == null) {
				hasNoHSBA();
				FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, "",
						"");
				listCtkq_B3121 = new ArrayList<ClsMo>();
				return;
			}

			SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);

			maDoituong = "";
			if (hoSoBenhAn.getDoituongMa() != null) {
				maDoituong = hoSoBenhAn.getDoituongMa().getDmdoituongMa();
			}

			Date dNgayVaoV = hoSoBenhAn.getHsbaNgaygiovaov();
			if (dNgayVaoV != null) {
				SimpleDateFormat formatter;
				formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
				ngayVv = formatter.format(dNgayVaoV);
			}
			benhNhan = hoSoBenhAn.getBenhnhanMa();
			SetInforUtil.setInforIfNullForBN(benhNhan);

			HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate.getInstance();
			hsbaBHYT = hsbaBHYTDelegate.findBySoVaoVienLastHsbaBhyt(hoSoBenhAn
					.getHsbaSovaovien());
			if (hsbaBHYT == null) {
				hsbaBHYT = new HsbaBhyt();
			}

			ngayBatDauBh = hsbaBHYT.getHsbabhytGiatri2(); // Ngay bat dau bao
															// hiem tinh theo
															// gia tri the tai
															// benh vien (luu
															// trong truong
															// HSBABHYT_GIATRI2
															// cua bang
															// hsba_bhyt)
			// Neu ngay bat dau theo gia tri tai benh vien bang null, thi lay
			// theo ngay bat dau cua the
			if (ngayBatDauBh == null) {
				// ngayBatDauBh = (hsbaBHYT.getHsbabhytGiatri0() == null ? new
				// Date() : hsbaBHYT.getHsbabhytGiatri0());
				ngayBatDauBh = hsbaBHYT.getHsbabhytGiatri0();
			}
			ngayHetHanBh = hsbaBHYT.getHsbabhytGiatri3(); // Ngay het han bao
															// hiem tinh theo
															// gia tri the tai
															// benh vien (luu
															// trong truong
															// HSBABHYT_GIATRI3
															// cua bang
															// hsba_bhyt)
			// Neu ngay het han theo gia tri tai benh vien bang null, thi lay
			// theo ngay het han cua the
			if (ngayHetHanBh == null) {
				// ngayHetHanBh = (hsbaBHYT.getHsbabhytGiatri1() == null ? new
				// Date() : hsbaBHYT.getHsbabhytGiatri1());
				ngayHetHanBh = hsbaBHYT.getHsbabhytGiatri1();
			}
			SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
			strNgayBatDauBh = (ngayBatDauBh == null ? "" : formatter
					.format(ngayBatDauBh));
			strNgayHetHanBh = (ngayHetHanBh == null ? "" : formatter
					.format(ngayHetHanBh));

			// -------------------------------------------------------------------------------------

			checkThanhtoan = "block";
			hsthtoan = HsThtoanDelegate.getInstance().findBySovaovien(
					hoSoBenhAn.getHsbaSovaovien());
			if (hsthtoan == null || hsthtoan.getHsthtoanNgaytt() == null) {
				log.info("***** Chua Thanh Toan ******");
			} else {
				if (!hsthtoan.getHsthtoanNgaytt().equals("")) {
					checkThanhtoan = "none";
					FacesMessages
							.instance()
							.add(
									IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
				}
			}

			// neu khoa dang chon trung voi khoa dang dieu tri thi cho sua,
			// nguoc lai khong cho sua
			if (!khoa.getDmkhoaMaso().equals(
					hoSoBenhAn.getHsbaKhoadangdt(true).getDmkhoaMaso())) {
				checkThanhtoan = "none";
				log
						.info("Khong phai Khoa dang dieu tri, chi duoc xem thong tin!");
			}
			// 20120312 bao.ttc: kiem tra tuong tu voi Tang dang DT
			if (hoSoBenhAn.getTangDangdt() != null
					&& !hoSoBenhAn.getTangDangdt().getDmtangMaso().equals(
							tangMaso)) {
				checkThanhtoan = "none";
				log
						.info("Khong phai Tang dang dieu tri, chi duoc xem thong tin!");
			}
			checkLoad = true;

			ClsMoDelegate clsmoDelegate = ClsMoDelegate.getInstance();

			// List<ClsMo> clsmo_tmp =
			// clsmoDelegate.findBySoVaoVienAndKhoaMa(hoSoBenhAn.getHsbaSovaovien(),maKhoa);
			// List<ClsMo> clsmo_tmp = clsmoDelegate.findBySoVaoVienAndKhoaMaAndTang(hoSoBenhAn.getHsbaSovaovien(), maKhoa, tangMaso);
			List<ClsMo> clsmo_tmp = clsmoDelegate.findBySoVaoVienAndKhoaMaAndTangAndNgay(hoSoBenhAn.getHsbaSovaovien(), maKhoa, tangMaso, ngayTt);

			SetInforAllCLS(clsmo_tmp);

			setOtherValue();

			// 20120417 bao.ttc: tam thoi khong dung chuc nang nay
			chuyenManHinhThuocCLS_MaKhoa = khoa.getDmkhoaMa();
			chuyenManHinhThuocCLS_TangMaSo = tangChuyenDen.getDmtangMaso().toString();
			chuyenManHinhThuocCLS_SoBenhAn = hoSoBenhAn.getHsbaSovaovien();

			// 20120417 bao.ttc: remove vi da khong cho hien thi tren form
			// HsThtoanDelegate hsThtoanDelegate =
			// HsThtoanDelegate.getInstance();
			// HsThtoan hsThtoan =
			// hsThtoanDelegate.findBySovaovien(hoSoBenhAn.getHsbaSovaovien());
			// if (hsThtoan!=null){
			// tienTamUng = hsThtoan.getHsthtoanTamung();
			// tienSoDu = hsThtoan.getHsthtoanThtoan();
			// } else {
			// TamUngDelegate tamUng = TamUngDelegate.getInstance();
			// HoanUngDelegate hoanung = HoanUngDelegate.getInstance();
			// tienTamUng = tamUng.getTongTamUng(hoSoBenhAn.getHsbaSovaovien());
			// tienSoDu = hoanung.getTongHoanUng(hoSoBenhAn.getHsbaSovaovien())
			// - tienTamUng;
			// }

		} catch (Exception e) {
			e.printStackTrace();
			log.info((new StringBuilder()).append(
					"error on function displayInfor").append(e).toString());
		}
		log.info("End displayInfor()");
	}

	// Ham reset form
	private void ResetForm() {
		log.info("Begining ResetForm(): ");
		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);
		hoSoBenhAn = new Hsba();
		hsbaBHYT = new HsbaBhyt();
		tienSoDu = 0.0;
		tienTamUng = 0.0;

		SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);

		clsMo = new ClsMo();
		SetInforUtil.setInforIfNullForClsMo(clsMo);
		nhapctSelected = new ClsMo();
		SetInforUtil.setInforIfNullForClsMo(nhapctSelected);
		
		nhanVienThucHien = new DtDmNhanVien(); // 20101112 bao.ttc
		gioi = "";
		tuoi = "";
		ngaySinh = "";
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		ngayTt = formatter.format(new Date());
		// diengiai = "";
		loai = "";
		maCLS = "";
		maSoCLS = null;
		tenCLS = "";
		maKhoa = "";
		tangMaso = 0;
		maKhoaCLS = "";
		maSoKhoaCLS = 0;
		khoaCDHA = "";

		mien = new Boolean(false);
		ngoaiDanhMuc = new Boolean(false);
		yeuCau = new Boolean(false);
		khongthu = new Boolean(false);
		kyThuatCao = new Boolean(false);
		soLuong = 0;
		donGia = new Double(0);
		dichVu = new Boolean(false);
		donGiaDieuChinh = "";
		ghiChu = "";
		// phuc.lc 24-09-2010
		donGiaBH = new Double(0);
		phanDV = new Double(0);

		listCtkq_B3121 = new ArrayList<ClsMo>();
		listCtkq_B3121_1 = new ArrayList<ClsMo>();
		listCtkq_B3121_2 = new ArrayList<ClsMo>();
		resultHidden = "";
		maPhieu = "";
		strNgayBatDauBh = "";
		strNgayHetHanBh = "";
		ngayBatDauBh = null;
		ngayHetHanBh = null;

		checkThanhtoan = "block";
		checkLoad = false;
		mapSelect = new HashMap<ClsMo, Boolean>();
		
		refreshDmKhoaNT();
		if (khoa == null){
			khoa = new DmKhoa();
			tangChuyenDen = new DmTang();
		}
		selectedIndex = -1;

		log.info("End ResetForm(): ");
	}

	// Xuat report
	private String reportFinished = "";

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public String getReportFileNameHid() {
		return reportFileNameHid;
	}

	public void setReportFileNameHid(String reportFileNameHid) {
		this.reportFileNameHid = reportFileNameHid;
	}

	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

	public Hsba getHoSoBenhAn() {
		return hoSoBenhAn;
	}

	public void setHoSoBenhAn(Hsba hoSoBenhAn) {
		this.hoSoBenhAn = hoSoBenhAn;
	}

	public ClsMo getClsMo() {
		return clsMo;
	}

	public void setClsMo(ClsMo clsMo) {
		this.clsMo = clsMo;
	}

	public String getGioi() {
		return gioi;
	}

	public void setGioi(String gioi) {
		this.gioi = gioi;
	}

	public String getTuoi() {
		return tuoi;
	}

	public void setTuoi(String tuoi) {
		this.tuoi = tuoi;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getNgayTt() {
		return ngayTt;
	}

	public void setNgayTt(String ngayTt) {
		this.ngayTt = ngayTt;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public String getMaCLS() {
		return maCLS;
	}

	public void setMaCLS(String maCLS) {
		this.maCLS = maCLS;
	}

	public Integer getMaSoCLS() {
		return maSoCLS;
	}

	public void setMaSoCLS(Integer maSoCLS) {
		this.maSoCLS = maSoCLS;
	}

	public String getTenCLS() {
		return tenCLS;
	}

	public void setTenCLS(String tenCLS) {
		this.tenCLS = tenCLS;
	}

	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	public Boolean getMien() {
		return mien;
	}

	public void setMien(Boolean mien) {
		this.mien = mien;
	}

	public Boolean getYeuCau() {
		return yeuCau;
	}

	public void setYeuCau(Boolean yeuCau) {
		this.yeuCau = yeuCau;
	}

	public Boolean getKhongthu() {
		return khongthu;
	}

	public void setKhongthu(Boolean khongthu) {
		this.khongthu = khongthu;
	}

	public Boolean getKyThuatCao() {
		return kyThuatCao;
	}

	public void setKyThuatCao(Boolean kyThuatCao) {
		this.kyThuatCao = kyThuatCao;
	}

	public short getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(short soLuong) {
		this.soLuong = soLuong;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}

	public String getDonGiaDieuChinh() {
		return donGiaDieuChinh;
	}

	public void setDonGiaDieuChinh(String donGiaDieuChinh) {
		this.donGiaDieuChinh = donGiaDieuChinh;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public DtDmNhanVien getNhanVienThucHien() {
		return nhanVienThucHien;
	}

	public void setNhanVienThucHien(DtDmNhanVien nhanVienThucHien) {
		this.nhanVienThucHien = nhanVienThucHien;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		B3121_CanLamSang_DieuTri.log = log;
	}

	public java.util.List<ClsMo> getlistCtkq_B3121() {
		return listCtkq_B3121;
	}

	public void setlistCtkq_B3121(java.util.List<ClsMo> listCtkq_B3121) {
		this.listCtkq_B3121 = listCtkq_B3121;
	}

	public ClsMo getNhapctSelected() {
		return nhapctSelected;
	}

	public void setNhapctSelected(ClsMo nhapctSelected) {
		this.nhapctSelected = nhapctSelected;
	}

	public boolean isUpdateNhapct() {
		return updateNhapct;
	}

	public void setUpdateNhapct(boolean updateNhapct) {
		this.updateNhapct = updateNhapct;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public String getReportFinished() {
		return reportFinished;
	}

	public void setReportFinished(String reportFinished) {
		this.reportFinished = reportFinished;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Boolean getNgoaiDanhMuc() {
		return ngoaiDanhMuc;
	}

	public void setNgoaiDanhMuc(Boolean ngoaiDanhMuc) {
		this.ngoaiDanhMuc = ngoaiDanhMuc;
	}

	public Boolean getDichVu() {
		return dichVu;
	}

	public void setDichVu(Boolean dichVu) {
		this.dichVu = dichVu;
	}

	public java.util.List<ClsMo> getListCtkq_B3121() {
		return listCtkq_B3121;
	}

	public void setListCtkq_B3121(java.util.List<ClsMo> listCtkq_B3121) {
		this.listCtkq_B3121 = listCtkq_B3121;
	}

	public HsbaBhyt getHsbaBHYT() {
		return hsbaBHYT;
	}

	public void setHsbaBHYT(HsbaBhyt hsbaBHYT) {
		this.hsbaBHYT = hsbaBHYT;
	}

	public HsbaKhoa getHsbaKhoa() {
		return hsbaKhoa;
	}

	public void setHsbaKhoa(HsbaKhoa hsbaKhoa) {
		this.hsbaKhoa = hsbaKhoa;
	}

	public String getFocusDochuyenTuManHinhThuocCLS() {
		return focusDochuyenTuManHinhThuocCLS;
	}

	public void setFocusDochuyenTuManHinhThuocCLS(
			String focusDochuyenTuManHinhThuocCLS) {
		this.focusDochuyenTuManHinhThuocCLS = focusDochuyenTuManHinhThuocCLS;
	}

	public Double getTienSoDu() {
		return tienSoDu;
	}

	public void setTienSoDu(Double tienSoDu) {
		this.tienSoDu = tienSoDu;
	}

	// private String xuatReportCLs(String tenFileTemplate, String loaiFile)
	// throws Exception {
	//
	// log.info("tenFileTemplate: " + tenFileTemplate);
	// Date currentDate = new Date();
	// JasperPrint jasperPrint = null;
	// String pathExport = null;
	// try {
	// log.info("Vao method XuatReport ");
	// String pathTemplate = IConstantsRes.PATH_BASE
	// + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/"
	// + tenFileTemplate + ".jrxml";
	// log.info("pathTemplate:" + pathTemplate);
	// JasperReport jasperReport = JasperCompileManager
	// .compileReport(pathTemplate);
	// log.info("da thay file template ");
	// String gioitinh = "";
	// String thebhyt = "";
	// String tiepdonma = thammo.getTiepdonMa().getTiepdonMa();
	// String ketqua = "";
	// String banmo = "";
	//
	// if (benhNhan.getDmgtMaso() != null) {
	// if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())) {
	// gioitinh = "Nam";
	// } else {
	// gioitinh = "Nữ";
	// }
	// } else {
	// gioi = null;
	// }
	//
	// if (thammo.getTiepdonMa().getTiepdonSothebh() != null
	// && !thammo.getTiepdonMa().getTiepdonSothebh().equals("")) {
	// thebhyt = thammo.getTiepdonMa().getTiepdonSothebh();
	// }
	//			
	// Map<String, Object> params = new HashMap<String, Object>();
	// // saveSum(tuNgay, denNgay, donViMa);
	// params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
	// params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
	// params.put("dienThoaiDonVi",
	// IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
	// params.put("hoten", benhNhan.getBenhnhanHoten());
	// params.put("gioitinh", gioitinh);
	// params.put("thebhyt", thebhyt);
	// params.put("ngaythanhtoan", ngayTt);
	// params.put("banmo", banmo);
	// params.put("tiepdonma", tiepdonma);
	//			
	// log.info("maphieu:" + maPhieu);
	// params.put("maphieu", maPhieu);
	//
	// String fileNameExt = String.valueOf(currentDate.getTime());
	// String fileName = ReportUtil.xuatReportBenhAnDieuTri(
	// IConstantsRes.PATH_BASE,
	// IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI,
	// IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI, "vienphi/",
	// tenFileTemplate, params, loaiFile, fileNameExt);
	// reportFinished = position;
	// reportFileNameHid = fileName;
	// if (loaiFile.equalsIgnoreCase("HTML")) {
	// resultReportName = fileName;
	// } else {
	// resultReportFileName = fileName;
	// }
	// setIsReport("true");
	// return null;
	//
	//		
	//
	// } catch (JRException ex) {
	// ex.printStackTrace();
	// }
	// // reportFinished = position + pathExport;
	// reportFinished = pathExport.substring(pathExport.lastIndexOf("/") + 1,
	// pathExport.length());
	// return position + pathExport;
	// }

	/*
	 * Thanh Add Ham hien thi 2 danh sach can lam san
	 */
	public void displayCLS() throws Exception {
		DtDmClsBangGiaDelegate dtDmClsBangGiaDelegate = DtDmClsBangGiaDelegate
				.getInstance();
		ArrayList<DtDmClsBangGia> temp = (ArrayList<DtDmClsBangGia>) dtDmClsBangGiaDelegate
				.getDtDmClsBangGiaByMaSoKhoa((Integer) maSoKhoaCLS);

		listCtkq_B3121_1 = new java.util.ArrayList<ClsMo>();
		listCtkq_B3121_2 = new java.util.ArrayList<ClsMo>();

		if (temp == null) {
			temp = new ArrayList<DtDmClsBangGia>();
		}
		Collections.sort(temp, new Comparator<DtDmClsBangGia>() {
			public int compare(DtDmClsBangGia o1, DtDmClsBangGia o2) {
				String o1Ma2 = "";
				String o2Ma2 = "";
				if (o1.getDtdmclsbgMa2() == null) {
					o1Ma2 = "0";
					return 1;
				} else {
					o1Ma2 = o1.getDtdmclsbgMa2();
				}
				if (o2.getDtdmclsbgMa2() == null) {
					o2Ma2 = "0";
					return -1;
				} else {
					o2Ma2 = o2.getDtdmclsbgMa2();
				}
				if (Integer.parseInt(o1Ma2) < Integer.parseInt(o2Ma2)) {
					return -1;
				} else {
					return 1;
				}

			}
		});

		DmKhoa khoa = (DmKhoa) DieuTriUtilDelegate.getInstance().findByMa(
				maKhoaCLS, "DmKhoa", "dmkhoaMa");

		for (int i = 0; i < temp.size(); i++) {
			ClsMo cls = new ClsMo();
			SetInforUtil.setInforIfNullForClsMo(cls);
			DtDmClsBangGia clsBanggia = temp.get(i);
			cls.setClsmoMahang(clsBanggia);
			// 20101028 bao.ttc:
			// cls.setClsmoLoai(clsBanggia.getDtdmclsbgPhanloai().getDtdmclsMa());
			// 20101028 bao.ttc:
			// cls.setClsmoKtcao(clsBanggia.getDtdmclsbgPhanloai().getDtdmclsMaso().intValue()
			// == 9);
			// cls.getClsmoMahang().setDtdmclsbgMa(clsBanggia.getDtdmclsbgMa());
			// cls.getClsmoMahang().setDtdmclsbgMaso(clsBanggia.getDtdmclsbgMaso());
			// cls.getClsmoMahang().setDtdmclsbgDiengiai(clsBanggia.getDtdmclsbgDiengiai());

			// 20101028 bao.ttc:
			cls.setClsmoDongia(clsBanggia.getDtdmclsbgDongia());
			cls.setClsmoLan(Short.parseShort("1"));
			cls.setClsmoKhoa(khoa);
			cls.setClsmoDongiabh(clsBanggia.getDtdmclsbgDongiabh());
			cls.setClsmoPhandv(clsBanggia.getDtdmclsbgPhandv());

			// 20101028 bao.ttc:
			// cls.setClsmoMien(clsBanggia.getDtdmclsbgMien());
			// 20101028 bao.ttc: cls.setClsmoNDM(clsBanggia.getDtdmclsbgNDM());
			// 20101028 bao.ttc: cls.setClsmoDichvu(dichVu);
			if (i < temp.size() / 2) {
				listCtkq_B3121_1.add(cls);
			} else {
				listCtkq_B3121_2.add(cls);
			}
		}
	}

	/*
	 * Thanh Add Ham hien thi 2 danh sach can lam san
	 */
	public void addListCLSChoose() throws Exception {
		// log.info("addListCLSChoose " + listCLSChoose);
		// log.info(listCLSChoose);
		if (listCLSChoose != "") {
			String[] result = listCLSChoose.split(",");
			SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
			for (int i = 0; i < result.length; i++) {
				ClsMo cls = new ClsMo();
				// log.info(listCtkq_B3121_1.size());
				// log.info(listCtkq_B3121_2.size());
				for (ClsMo clsItem : listCtkq_B3121_1) {
					if (clsItem.getClsmoMahang().getDtdmclsbgMaso() == Integer
							.parseInt(result[i])) {
						cls = clsItem;
						// 20101028 bao.ttc: cls =
						// (ClsMo)BeanUtils.cloneBean(clsItem);
					}
				}
				for (ClsMo clsItem : listCtkq_B3121_2) {
					if (clsItem.getClsmoMahang().getDtdmclsbgMaso() == Integer
							.parseInt(result[i])) {
						cls = clsItem;
						// 20101028 bao.ttc: cls =
						// (ClsMo)BeanUtils.cloneBean(clsItem);
					}
				}
				cls.setClsmoLoai(cls.getClsmoMahang().getDtdmclsbgPhanloai()
						.getDtdmclsMa());
				cls.setClsmoMien(cls.getClsmoMahang().getDtdmclsbgMien());
				cls.setClsmoNDM(cls.getClsmoMahang().getDtdmclsbgNDM());
				cls.setClsmoYeucau(yeuCau);
				cls.setClsmoKhongthu(khongthu);
				cls.setClsmoKtcao(cls.getClsmoMahang().getDtdmclsbgPhanloai()
						.getDtdmclsMaso().intValue() == 9); // 9 la ma so cua
															// CLS Dich vu ky
															// thuat cao
				cls.setClsmoDichvu(dichVu);
				cls
						.setClsmoLoaicls(cls.getClsmoMahang()
								.getDtdmclsbgPhanloai());
				cls.setClsmoLoai2(cls.getClsmoMahang().getDtdmclsbgLoai());
				cls.setClsmoSoml(cls.getClsmoMahang().getDmclsbgSoml());
				// log.info("maso " + cls.getClsmoMahang().getDtdmclsbgMaso());
				// log.info("don gia " + cls.getClsmoDongia());
				// log.info("so luong " + cls.getClsmoLan());
				// log.info("mien = " + cls.getClsmoMien());
				// phuc.lc : 27-05-2011 : Set lai don gia
				if (cls.getClsmoKhongthu() != null
						&& cls.getClsmoKhongthu().booleanValue() == true) {
					// Neu CLS khong thu thi lay don gia = 0 (ap dung cho tat ca
					// cac doi tuong)
					cls.setClsmoDongia(new Double(0));
				} else if (cls.getClsmoYeucau() != null
						&& cls.getClsmoYeucau().booleanValue() == true) {
					// Neu CLS yeu cau thi lay don gia yeu cau (ap dung cho tat
					// ca cac doi tuong)
					cls.setClsmoDongia(cls.getClsmoMahang()
							.getDtdmclsbgDongiayc());
				} else if (cls.getClsmoMien() != null
						&& cls.getClsmoMien().booleanValue() == true) {
					// Neu CLS mien thi lay don gia mien (ap dung cho tat ca cac
					// doi tuong)
					cls.setClsmoDongia(cls.getClsmoMahang()
							.getDtdmclsbgDongiamp());
				} else if (maDoituong.equalsIgnoreCase("BH")) {

					Date curDate = formatter
							.parse(formatter.format(new Date()));
					// Cls ngoai danh muc hoac het han bao hiem thi lay don gia
					if ((cls.getClsmoNDM() != null && cls.getClsmoNDM()
							.booleanValue() == true)
							|| ngayBatDauBh == null
							|| ngayHetHanBh == null
							|| curDate.before(ngayBatDauBh)
							|| curDate.after(ngayHetHanBh)) {
						cls.setClsmoDongia(cls.getClsmoMahang()
								.getDtdmclsbgDongia());
					} else {
						// Lay gia bao hiem
						cls.setClsmoDongia(cls.getClsmoMahang()
								.getDtdmclsbgDongiabh());
					}
					// phuc.lc 08/08/2011 : Fix bug 3723
				} else if (maDoituong.equalsIgnoreCase("MP")) {
					cls.setClsmoDongia(cls.getClsmoMahang()
							.getDtdmclsbgDongiamp());
				} else {
					cls.setClsmoDongia(cls.getClsmoMahang()
							.getDtdmclsbgDongia());
				}
				cls.setClsmoDongiabh(cls.getClsmoMahang()
						.getDtdmclsbgDongiabh());
				cls.setClsmoNgay(formatter.parse(ngayTt));
				if (listCtkq_B3121 == null)
					listCtkq_B3121 = new ArrayList<ClsMo>();
				ClsMo clsMoTmp = (ClsMo) BeanUtils.cloneBean(cls);
				listCtkq_B3121.add(clsMoTmp);
			}
			log.info("test " + listCtkq_B3121.size());
		}
	}

	public String getListCLSChoose() {
		return listCLSChoose;
	}

	public void setListCLSChoose(String listCLSChoose) {
		this.listCLSChoose = listCLSChoose;
	}

	public String getCheckThanhtoan() {
		return checkThanhtoan;
	}

	public void setCheckThanhtoan(String checkThanhtoan) {
		this.checkThanhtoan = checkThanhtoan;
	}

	public HsThtoan getHsthtoan() {
		return hsthtoan;
	}

	public void setHsthtoan(HsThtoan hsthtoan) {
		this.hsthtoan = hsthtoan;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public Double getDonGiaBH() {
		return donGiaBH;
	}

	public void setDonGiaBH(Double donGiaBH) {
		this.donGiaBH = donGiaBH;
	}

	public Double getPhanDV() {
		return phanDV;
	}

	public void setPhanDV(Double phanDV) {
		this.phanDV = phanDV;
	}

	public String getMaDoituong() {
		return maDoituong;
	}

	public void setMaDoituong(String maDoituong) {
		this.maDoituong = maDoituong;
	}

	public String getStrNgayBatDauBh() {
		return strNgayBatDauBh;
	}

	public void setStrNgayBatDauBh(String strNgayBatDauBh) {
		this.strNgayBatDauBh = strNgayBatDauBh;
	}

	public String getStrNgayHetHanBh() {
		return strNgayHetHanBh;
	}

	public void setStrNgayHetHanBh(String strNgayHetHanBh) {
		this.strNgayHetHanBh = strNgayHetHanBh;
	}

	public String getNgayVv() {
		return ngayVv;
	}

	public void setNgayVv(String ngayVv) {
		this.ngayVv = ngayVv;
	}

	public boolean isCheckLoad() {
		return checkLoad;
	}

	public void setCheckLoad(boolean checkLoad) {
		this.checkLoad = checkLoad;
	}

	public DmTang getTangChuyenDen() {
		return tangChuyenDen;
	}

	public void setTangChuyenDen(DmTang tangChuyenDen) {
		this.tangChuyenDen = tangChuyenDen;
	}

	public List<SelectItem> getListDmTangs() {
		return listDmTangs;
	}

	public void setListDmTangs(List<SelectItem> listDmTangs) {
		this.listDmTangs = listDmTangs;
	}

	public DmKhoa getKhoa() {
		return khoa;
	}

	public void setKhoa(DmKhoa khoa) {
		this.khoa = khoa;
	}

	public ClsMo connectLabconn(ClsMo clsMo) {
		try {
			if (IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES")) {
				log.info("ConnectLabconn");
				String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				// String url =
				// "jdbc:sqlserver://127.0.0.1\\test:1433;databaseName=test";
				// String username = "sa";
				// String password = "123456";
				// ThamKham thamkham_temp = thamkham;
				Hsba hsbaTemp = hoSoBenhAn;
				String temp = "";
				String url = "jdbc:sqlserver://" + IConstantsRes.LABCONN_SERVER
						+ "\\" + IConstantsRes.LABCONN_DATABASE + ":"
						+ IConstantsRes.LABCONN_PORT + ";databaseName="
						+ IConstantsRes.LABCONN_DATABASE;
				String username = IConstantsRes.LABCONN_USERNAME;
				String password = IConstantsRes.LABCONN_PASSWORD;
				boolean daThucHien = false;
				String ghichu = "";
				// String kqCLS = "";
				String ketluan = "";
				try {
					Class.forName(driver);
					Connection conn = DriverManager.getConnection(url,
							username, password);
					List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate
							.getInstance().findByAllConditions("ClsKetQua",
									"clsmoMaso", "clskqTen",
									clsMo.getClsmoMa() + "", "");
					for (int j = 0; j < listClsKetQua.size(); j++) {
						String query = " Select * from ["
								+ IConstantsRes.LABCONN_DATABASE
								+ "].[dbo].[Tbl_Result_Upload]";
						query += " where 1 = 1 ";
						query += " and OrderID = '"
								+ hsbaTemp.getHsbaSovaovien().replaceFirst(
										"BD", "NO") + "' ";
						query += " and TestCodeHIS = '"
								+ listClsKetQua.get(j).getClskqMa() + "' ";
						PreparedStatement stmt = conn.prepareStatement(query,
								Statement.RETURN_GENERATED_KEYS);
						ResultSet rs = stmt.executeQuery();
						if (rs != null) {
							while (rs.next()) {
								daThucHien = true;
								ResultSetMetaData rsMetaData = rs.getMetaData();
								temp += listClsKetQua.get(j).getClskqTen()
										+ ": " + rs.getString("Result") + "\n";
								ketluan = rs.getString("Comment");
								listClsKetQua.get(j).setResult(
										rs.getString("Result"));
								DieuTriUtilDelegate.getInstance().edit(
										listClsKetQua.get(j));
							}
							DateFormat formatter = new SimpleDateFormat(
									"yyyy/MM/dd");
							String today = formatter.format(Calendar
									.getInstance().getTime());
							query = "UPDATE ["
									+ IConstantsRes.LABCONN_DATABASE
									+ "].[dbo].[Tbl_Result_Upload] SET TimeOut = '"
									+ today + "' ";
							query += " where 1 = 1 ";
							query += " and OrderID = '"
									+ hsbaTemp.getHsbaSovaovien() + "' ";
							query += " and TestCodeHIS = '"
									+ listClsKetQua.get(j).getClskqMa() + "' ";
							stmt = conn.prepareStatement(query,
									Statement.RETURN_GENERATED_KEYS);
							int num = stmt.executeUpdate();
							if (num == 1) {
								rs = stmt.getGeneratedKeys();
								log.info("ResultSet: " + rs.toString());
								if (rs != null) {
									while (rs.next()) {
										ResultSetMetaData rsMetaData = rs
												.getMetaData();
										int columnCount = rsMetaData
												.getColumnCount();

										for (int k = 1; k <= columnCount; k++) {
											String key = rs.getString(k);
											log.info("key " + k + " is " + key);
										}
									}
								}
							}
						}

					}
					ghichu = temp;
					conn.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // load sqlserver driver
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (daThucHien) {
					clsMo.setClsmoTh(daThucHien);
					clsMo.setClsmoGhichu(ghichu);
					clsMo.setClsmoKetqua(ketluan);
				}

			}
		} catch (Exception e) {
		}
		return clsMo;
	}

	public ClsMo connectPACS(ClsMo clsMo) {
		try {
			if (IConstantsRes.KET_NOI_SERVER_PACS.equals("YES")) {
				log.info("ConnectPACS");
				// ThamKham thamkham_temp = thamkham;
				Hsba hsbaTemp = hoSoBenhAn;
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://" + IConstantsRes.PACS_SERVER + ":"
						+ IConstantsRes.PACS_PORT + "/"
						+ IConstantsRes.PACS_DATABASE;
				String username = IConstantsRes.PACS_USERNAME;
				String password = IConstantsRes.PACS_PASSWORD;
				boolean daThucHien = false;
				// String ghichu = "";
				String kqCLS = "";
				String ketluan = "";
				try {
					Class.forName(driver);
					Connection conn = DriverManager.getConnection(url,
							username, password);
					String query = " SELECT patient.*, study.*, series.* FROM patient, study, series ";
					query += " where 1 = 1 ";
					query += " and patient.pat_id = '"
							+ hsbaTemp.getHsbaSovaovien().replaceFirst("BD",
									"NO") + "' ";
					query += " and study.ma_cls = '"
							+ clsMo.getClsmoMahang(true).getDtdmclsbgMa()
							+ "' ";
					query += " AND patient.pk = study.patient_fk AND study.pk = series.study_fk";
					PreparedStatement stmt = conn.prepareStatement(query,
							Statement.RETURN_GENERATED_KEYS);
					ResultSet rs = stmt.executeQuery();
					if (rs.first()) {
						daThucHien = true;
						ketluan = rs.getString("ket_qua");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (daThucHien) {
					clsMo.setClsmoTh(daThucHien);
					clsMo.setClsmoKetqua(kqCLS);
					clsMo.setClsmoKetqua(ketluan);
				}
			}
		} catch (Exception e) {
		}
		return clsMo;
	}

	public String getKhoaCDHA() {
		return khoaCDHA;
	}

	public void setKhoaCDHA(String khoaCDHA) {
		this.khoaCDHA = khoaCDHA;
	}

	public String thuchienAction() {
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}

	/**
	 * xuat report
	 */
	public void XuatReport() {
		loaiBCDT = "CanLamSanPhauThuat";
		String baocao1 = null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE
					+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
					+ "tiepdon/phieuchidinhclsNT.jrxml";
			log.info("da thay file templete " + pathTemplate);

			JasperReport jasperReport = JasperCompileManager
					.compileReport(pathTemplate);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("HOTEN", hoSoBenhAn.getBenhnhanMa(true)
					.getBenhnhanHoten());

			HsbaChuyenMon hsbaChuyenMon = HsbaChuyenMonDelegate.getInstance()
					.findBySoVaoVien_LastHSBACM(hoSoBenhAn.getHsbaSovaovien());
			if (hsbaChuyenMon != null) {
				String bacsi = hsbaChuyenMon.getHsbacmBacsi(true)
						.getDtdmnhanvienTen();
				params.put("BACSIDIEUTRI", bacsi);
			}
			String diachistr = "";
			if (hoSoBenhAn.getBenhnhanMa(true).getBenhnhanDiachi() != null
					&& !hoSoBenhAn.getBenhnhanMa(true).getBenhnhanDiachi()
							.equals(""))
				diachistr += hoSoBenhAn.getBenhnhanMa(true).getBenhnhanDiachi()
						+ ", ";
			if (hoSoBenhAn.getBenhnhanMa(true).getXaMa(true).getDmxaTen() != null
					&& !hoSoBenhAn.getBenhnhanMa(true).getXaMa(true)
							.getDmxaTen().equals(""))
				diachistr += hoSoBenhAn.getBenhnhanMa(true).getXaMa(true)
						.getDmxaTen()
						+ ", ";
			if (hoSoBenhAn.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen() != null
					&& !hoSoBenhAn.getBenhnhanMa(true).getHuyenMa(true)
							.getDmhuyenTen().equals(""))
				diachistr += hoSoBenhAn.getBenhnhanMa(true).getHuyenMa(true)
						.getDmhuyenTen()
						+ ", ";
			if (hoSoBenhAn.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen() != null
					&& !hoSoBenhAn.getBenhnhanMa(true).getTinhMa(true)
							.getDmtinhTen().equals(""))
				diachistr += hoSoBenhAn.getBenhnhanMa(true).getTinhMa(true)
						.getDmtinhTen();
			params.put("DIACHI", diachistr);

			// 20110510 bao.ttc: them thong tin Doi tuong, so the BHYT - Binh
			// Duong
			if (hoSoBenhAn.getDoituongMa() != null
					&& !hoSoBenhAn.getDoituongMa().equals("")) {
				params.put("DOITUONG", hoSoBenhAn.getDoituongMa(true)
						.getDmdoituongTen());
			}
			params.put("SOTHEBHYT", hsbaBHYT.getHsbabhytSothebh());

			params.put("GIOI", hoSoBenhAn.getBenhnhanMa(true).getDmgtMaso(true)
					.getDmgtTen());
			params.put("KHOA", hsbaKhoa.getKhoaMa().getDmkhoaTen());
			params.put("MATIEPDON", hoSoBenhAn.getHsbaSovaovien().replaceFirst(
					"BD", "NO"));
			params.put("HSBAKHOAMASO", hsbaKhoa.getHsbakhoaMaso());
			// phuc.lc 22-08-2011 : Fix bug 3853
			if (maKhoaCLS.trim().length() > 0) {
				DmKhoa khoa = (DmKhoa) DieuTriUtilDelegate.getInstance()
						.findByMa(maKhoaCLS, "DmKhoa", "dmkhoaMa");
				params.put("TENKHOACLS", khoa.getDmkhoaTen());
				// params.put("KHOACLS", " = " + maKhoa.toUpperCase());
				params.put("KHOACLS", " = " + khoa.getDmkhoaMaso());
			} else {
				params.put("TENKHOACLS", "");
				params.put("KHOACLS", " IS NULL ");
			}
			if (maKhoaCLS.toUpperCase().equals("CDH")) {
				params.put("KHOACDHA", khoaCDHA != "" ? khoaCDHA : null);
			}

			String sDonViTuoi = "";
			if (hoSoBenhAn.getBenhnhanMa(true).getBenhnhanDonvituoi() == 2)
				sDonViTuoi = IConstantsRes.THANG;
			else if (hoSoBenhAn.getBenhnhanMa(true).getBenhnhanDonvituoi() == 3)
				sDonViTuoi = IConstantsRes.NGAY;
			params.put("TUOI", hoSoBenhAn.getBenhnhanMa(true).getBenhnhanTuoi()
					+ " " + sDonViTuoi);

			String chanDoan = "";
			// log.info(thamkham.getBenhicd10());
			if (hoSoBenhAn.getHsbaMachdoanbd() != null) {
				chanDoan += hoSoBenhAn.getHsbaMachdoanbd(true).getDmbenhicdMa()
						+ " - "
						+ hoSoBenhAn.getHsbaMachdoanbd(true).getDmbenhicdTen();
			}
			if (dsIn != "" && dsIn != null) {
				params.put("dsIn", " in (" + dsIn.replaceFirst(",", "") + ")");
			} else {
				params.put("dsIn", " IS  NULL");
			}
			// if (hsbaChuyenMon.getHsbacmBenhchinh() != null &&
			// hsbaChuyenMon.getHsbacmBenhchinh().getDmbenhicdMaso() !=null){
			// DmBenhIcd benh =
			// (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhchinh().getDmbenhicdMaso(),
			// "DmBenhIcd", "dmbenhicdMaso");
			// if (benh != null){
			// maChanDoan = benh.getDmbenhicdMa();
			// tenChanDoan = benh.getDmbenhicdTen();
			// chanDoan += " , "+ maChanDoan + " " + tenChanDoan;
			// }
			//				
			// }
			//			
			//			
			// //tiep tuc them benh phu.
			// if (hsbaChuyenMon.getHsbacmBenhphu() != null &&
			// hsbaChuyenMon.getHsbacmBenhphu().getDmbenhicdMaso() !=null){
			// DmBenhIcd benh =
			// (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhphu().getDmbenhicdMaso(),
			// "DmBenhIcd", "dmbenhicdMaso");
			// if (benh != null){
			// maChanDoan = benh.getDmbenhicdMa();
			// tenChanDoan = benh.getDmbenhicdTen();
			// chanDoan += " , "+ maChanDoan + " " + tenChanDoan;
			// }
			//				
			// }
			// if (hsbaChuyenMon.getHsbacmBenhphu2() != null &&
			// hsbaChuyenMon.getHsbacmBenhphu2().getDmbenhicdMaso() !=null){
			// DmBenhIcd benh =
			// (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhphu2().getDmbenhicdMaso(),
			// "DmBenhIcd", "dmbenhicdMaso");
			// if (benh != null){
			// maChanDoan = benh.getDmbenhicdMa();
			// tenChanDoan = benh.getDmbenhicdTen();
			// chanDoan += " , "+ maChanDoan + " " + tenChanDoan;
			// }
			//				
			// }
			// if (hsbaChuyenMon.getHsbacmBenhphu3() != null &&
			// hsbaChuyenMon.getHsbacmBenhphu3().getDmbenhicdMaso() !=null){
			// DmBenhIcd benh =
			// (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhphu3().getDmbenhicdMaso(),
			// "DmBenhIcd", "dmbenhicdMaso");
			// if (benh != null){
			// maChanDoan = benh.getDmbenhicdMa();
			// tenChanDoan = benh.getDmbenhicdTen();
			// chanDoan += " , "+ maChanDoan + " " + tenChanDoan;
			// }
			//				
			// }
			// if (hsbaChuyenMon.getHsbacmBenhphu4() != null &&
			// hsbaChuyenMon.getHsbacmBenhphu4().getDmbenhicdMaso() !=null){
			// DmBenhIcd benh =
			// (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhphu4().getDmbenhicdMaso(),
			// "DmBenhIcd", "dmbenhicdMaso");
			// if (benh != null){
			// maChanDoan = benh.getDmbenhicdMa();
			// tenChanDoan = benh.getDmbenhicdTen();
			// chanDoan += " , "+ maChanDoan + " " + tenChanDoan;
			// }
			//				
			// }
			// if (hsbaChuyenMon.getHsbacmBenhphu5() != null &&
			// hsbaChuyenMon.getHsbacmBenhphu5().getDmbenhicdMaso() !=null){
			// DmBenhIcd benh =
			// (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhphu5().getDmbenhicdMaso(),
			// "DmBenhIcd", "dmbenhicdMaso");
			// if (benh != null){
			// maChanDoan = benh.getDmbenhicdMa();
			// tenChanDoan = benh.getDmbenhicdTen();
			// chanDoan += " , "+ maChanDoan + " " + tenChanDoan;
			// }
			//				
			// }
			params.put("CHANDOAN", chanDoan);

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
			jasperPrintDT = JasperFillManager.fillReport(jasperReport, params,
					conn);
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintDT, index,
					IConstantsRes.PATH_BASE
							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
							+ "tiepdon/", "pdf", "CanLamSanPhauThuat");
			duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + duongdanStrDT);
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

	public void displayInforFromMenu() throws Exception {

	}

	public Map<ClsMo, Boolean> getMapSelect() {
		return mapSelect;
	}

	public void setMapSelect(Map<ClsMo, Boolean> mapSelect) {
		this.mapSelect = mapSelect;
	}

}
