package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;

import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.GiayCvNbBhytDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.GiayCvNbBhyt;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B117_Giaychuyenviennguoibenhcothebhyt")
@Synchronized(timeout = 6000000)
public class GiayChuyenVienNguoiBenhCoTheBHYTAction implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String sMaGiayChuyenVien;
	private String ngaySinh = "";
	private String gioi = "";
	private String sCoSoKCB = "";

	private Boolean bDaKCT;
	private Boolean bDtNgoaiTru;
	private Boolean bDtNoiTru;
	private GiayCvNbBhyt giayChuyenVien = new GiayCvNbBhyt();
	private String sShowDel = "false";
	private String sShowPrint = "false";
	private String giatrithe_tungay;
	private String giatrithe_denngay;
	private String dieutri1Tungay;
	private String dieutri1Denngay;

	private static Logger log = Logger
			.getLogger(GiayChuyenVienNguoiBenhCoTheBHYTAction.class);

	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;

	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;

	@In(required = false)
	@Out(required = false)
	private String goToChuyenVienNguoiBenhCoTheBHYT;

	@Out(required = false)
	private String bacSiKCB;

	private BenhNhan benhNhan;
	private ThamKham thamkham;

	private List<ThuocPhongKham> listTPK;
	private String thuocdadung = "";

	private List<ClsKham> listCLS;
	private String cacxetnghiem = "";

	public void resetValue() {

	}

	private String resultHidden = "";

	@Begin(nested = true)
	@Factory("goToChuyenVienNguoiBenhCoTheBHYT")
	public void init() throws Exception {
		log.info("***Starting init ***");
		try {
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();

			thamkham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,
					maTiepDonOut);
			if (thamkham != null) {
				benhNhan = thamkham.getTiepdonMa(true).getBenhnhanMa();
				GiayCvNbBhytDelegate objGiayCvNbBhytDel = GiayCvNbBhytDelegate
						.getInstance();
				giayChuyenVien = objGiayCvNbBhytDel.findByMaThamKham(thamkham
						.getThamkhamMa());

				if (giayChuyenVien == null) {
					giayChuyenVien = new GiayCvNbBhyt();

					// bao.ttc
					if (thamkham.getThamkhamBacsi() != null)
						giayChuyenVien.setGcvbhytBskham(thamkham
								.getThamkhamBacsi());

					if (thamkham.getTiepdonMa(true).getTiepdonLydochvi(true)
							.getDtdmlydocvTen() != null) {
						giayChuyenVien.setGcvbhytLidochuyenvien(thamkham
								.getTiepdonMa(true).getTiepdonLydochvi(true)
								.getDtdmlydocvTen());
					}

					if (thamkham.getTiepdonMa(true).getTiepdonChvien(true)
							.getDmbenhvienTen() != null) {
						giayChuyenVien.setGcvbhytNoichuyenden(thamkham
								.getTiepdonMa(true).getTiepdonChvien(true)
								.getDmbenhvienTen());
					}

					// 20110125 bao.ttc: lay thong tin CLS va Thuoc
					ThuocPhongKhamDelegate tpkDele = ThuocPhongKhamDelegate.getInstance();
					listTPK = tpkDele.findByThamKham(thamkham.getThamkhamMa(), Utils.KE_TOA_QUAY_BENH_VIEN);
					thuocdadung = "";

					if (listTPK != null && listTPK.size() > 0) {
						
						for (ThuocPhongKham tpk : listTPK) {
							thuocdadung += tpk.getThuocphongkhamMathuoc(true).getDmthuocTen() + "; ";
						}
						
						if (thuocdadung.length() > 2040){
							thuocdadung = thuocdadung.substring(0,2039) + " ...";
						}
						
					} else {
						thuocdadung = "Kh\u00F4ng c\u00F3"; // chu "Khong co"
					}
					giayChuyenVien.setGcvbhytTemp2(thuocdadung);

					ClsKhamDelegate clskhamDele = ClsKhamDelegate.getInstance();
					listCLS = clskhamDele.findByBanKhamVaMaTiepDon(thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), thamkham.getTiepdonMa(true).getTiepdonMa());
					cacxetnghiem = "";

					if (listCLS != null && listCLS.size() > 0) {
						
						for (ClsKham cls : listCLS) {
							if (cls.getClskhamMaloai() != null && ((cls.getClskhamMaloai().getDtdmclsMaso() == 4) || (cls.getClskhamMaloai().getDtdmclsMaso() == 5))){
								cacxetnghiem += cls.getClskhamMahang(true).getDtdmclsbgDiengiai() + "; ";
							}
						}
						
						if(cacxetnghiem.equals("")){
							cacxetnghiem = "Kh\u00F4ng c\u00F3"; // chu "Khong co"
						} else if(cacxetnghiem.length() > 2040){
							cacxetnghiem = cacxetnghiem.substring(0, 2039) + " ...";
						}
						
					} else {
						cacxetnghiem = "Kh\u00F4ng c\u00F3"; // chu "Khong co"
					}
					giayChuyenVien.setGcvbhytTemp1(cacxetnghiem);

					setDaKCT(false);
					setDtNgoaiTru(false);
					setDtNoiTru(false);
					log.info("***KHONG THAY****");
					sMaGiayChuyenVien = "";
					sShowDel = "false";
					sShowPrint = "false";
				} else {
					log.info("***TIM THAY****");
					sMaGiayChuyenVien = giayChuyenVien.getGcvbhytMa();
					setDaKCT(giayChuyenVien.getGcvbhytKct());
					setDtNgoaiTru(giayChuyenVien.getGcvbhytDadtngoaitru());
					setDtNoiTru(giayChuyenVien.getGcvbhytDadtnoitru());

					// 20110120 bao.ttc: TH chua co BS Kham thi hien thi BS tham
					// kham hien tai, da co BS kham thi load len
					if (giayChuyenVien.getGcvbhytBskham() == null) {
						if (thamkham.getThamkhamBacsi() != null)
							giayChuyenVien.setGcvbhytBskham(thamkham
									.getThamkhamBacsi());
					}

					// 20110113 bao.ttc: set lai cho TH co thay doi thong tin
					if (giayChuyenVien.getGcvbhytLidochuyenvien() == null
							|| giayChuyenVien.getGcvbhytLidochuyenvien()
									.equals("")) {
						if (thamkham.getTiepdonMa(true)
								.getTiepdonLydochvi(true).getDtdmlydocvTen() != null) {
							giayChuyenVien.setGcvbhytLidochuyenvien(thamkham
									.getTiepdonMa(true)
									.getTiepdonLydochvi(true)
									.getDtdmlydocvTen());
						}
					}

					if (giayChuyenVien.getGcvbhytNoichuyenden() == null
							|| giayChuyenVien.getGcvbhytNoichuyenden().equals(
									"")) {
						if (thamkham.getTiepdonMa(true).getTiepdonChvien(true)
								.getDmbenhvienTen() != null) {
							giayChuyenVien.setGcvbhytNoichuyenden(thamkham
									.getTiepdonMa(true).getTiepdonChvien(true)
									.getDmbenhvienTen());
						}
					}

					sShowDel = "true";
					sShowPrint = "false";
				}

				System.out.println("sShowDel=" + sShowDel);
				System.out.println("sShowPrint=" + sShowPrint);

				if (benhNhan.getDmgtMaso() != null) {
					if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())) {
						gioi = "r1"; // 1 : Name
					} else {
						gioi = "r2";
					}
				} else {
					gioi = null;
				}

				setOtherValue();
			}
			destroyService();
		} catch (Exception e) {
			log.info("***init Exception = " + e);
		}
		System.out.println("sShowDel=" + sShowDel);
		System.out.println("sShowPrint=" + sShowPrint);
		log.info("***Finished init ***");
	}

	@End
	public void end() {

	}

	// ***********************************************************************************
	// Ham ghi nhan
	// Xu ly cho nut ghi nhan
	public String ghiNhan() throws Exception {
		log.info("*****Begin ghiNhan() *****");

		try {
			GiayCvNbBhytDelegate GiayCvNbBhytDel = GiayCvNbBhytDelegate
					.getInstance();

			if (getDaKCT())
				giayChuyenVien.setGcvbhytKct(true);
			else
				giayChuyenVien.setGcvbhytKct(false);

			if (getDtNgoaiTru())
				giayChuyenVien.setGcvbhytDadtngoaitru(true);
			else
				giayChuyenVien.setGcvbhytDadtngoaitru(false);

			if (getDtNoiTru())
				giayChuyenVien.setGcvbhytDadtnoitru(true);
			else
				giayChuyenVien.setGcvbhytDadtnoitru(false);

			giayChuyenVien.setGcvbhytThamkham(thamkham);
			
			SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
			if (dieutri1Tungay != null && !dieutri1Tungay.equals("")) {
				Date dDieutri1Tungay = formatter.parse(dieutri1Tungay);
				giayChuyenVien.setDieutri1Tungay(dDieutri1Tungay);
			} else {
				giayChuyenVien.setDieutri1Tungay(null);
			}
			
			if (dieutri1Denngay != null && !dieutri1Denngay.equals("")) {
				Date dDieutri1Denngay = formatter.parse(dieutri1Denngay);
				giayChuyenVien.setDieutri1Denngay(dDieutri1Denngay);
			} else {
				giayChuyenVien.setDieutri1Denngay(null);
			}
			
			RemoveUtil.removeIfNullForGiayCvNbBhyt(giayChuyenVien);

			sMaGiayChuyenVien = GiayCvNbBhytDel.capNhatGiayCvNbBhyt(
					giayChuyenVien, sMaGiayChuyenVien);
			
			FacesMessages.instance().add(IConstantsRes.RPGCV_INSERT_SUCCESS,
					sMaGiayChuyenVien);
			log
					.info("*****CAP NHAT THANH CONG  GiayChuyenVienNguoiBenhCoTheBHYTAction *****");
			sShowDel = "true";
			sShowPrint = "true";
			// init(); 20110401: bao.ttc: Khong can init lai
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			log.error("*************loi***********" + e.toString());
			return null;
		}
		log.info("*****End ghiNhan() *****");
		return null;
	}

	public void huyPhieu() {
		log.info("***** start  huyPhieu() *****");
		if (sMaGiayChuyenVien == null || sMaGiayChuyenVien.equals("")) {
			return;
		}
		GiayCvNbBhytDelegate GiayCvNbBhytDel = GiayCvNbBhytDelegate
				.getInstance();
		GiayCvNbBhyt obj = GiayCvNbBhytDel.find(sMaGiayChuyenVien);
		if (obj == null)
			return;

		GiayCvNbBhytDel.remove(obj);
		FacesMessages.instance().add(IConstantsRes.RPGCV_DELETE_SUCCESS,
				sMaGiayChuyenVien);
		log.info("***** XOA THANH CONG *****");
		giayChuyenVien = new GiayCvNbBhyt();
		sMaGiayChuyenVien = "";
		sShowDel = "false";
		sShowPrint = "false";
		log.info("***** end  huyPhieu() *****");
	}

	// Xu ly cho nut quay lai
	public String quayLai() throws Exception {
		// log.info("*****Begin quayLai() *****");
		goToChuyenVienNguoiBenhCoTheBHYT = null;
		// log.info("*****End quayLai() *****");
		return "ghinhan";
	}

	@Out(required = false)
	@In(required = false)
	private String reportPathTD = null;

	@Out(required = false)
	@In(required = false)
	private String reportTypeTD = null;

	@Out(required = false)
	@In(required = false)
	JasperPrint jasperPrintTD = null;

	public String thuchienAction() {
		XuatReport();
		return "B160_Chonmenuxuattaptin";
	}

	public void XuatReport() {
		reportTypeTD = "giaychuyenviennguoibenhcobhyt";
		log.info("Vao Method XuatReport benhanvaovien");
		String baocao1 = null;
		String pathTemplate = null;
		try {
			// System.out.println("Ma= "+giayChuyenVien.getGcvbhytMa());
			if (sMaGiayChuyenVien != null && !sMaGiayChuyenVien.equals("")) {
				
				GiayCvNbBhyt giayChuyenVienTemp = GiayCvNbBhytDelegate.getInstance().find(sMaGiayChuyenVien);
				if (giayChuyenVienTemp != null){
					giayChuyenVien = giayChuyenVienTemp;
				}
			}
			
			pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/giaychuyenviennguoibenhcobhyt.jrxml";
			log.info("Da thay file template: " + pathTemplate);

			JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN_HEADER", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);

			if (giayChuyenVien.getGcvbhytNoichuyenden() != null) {
				if (IConstantsRes.TINH_DEFAULT.equals("511")) // 20110127 bao.ttc: bo chu "Ban Giam doc theo yeu cau cua Khanh Hoa"
					params.put("KINHCHUYENDEN", giayChuyenVien.getGcvbhytNoichuyenden());
				else
					params.put("KINHCHUYENDEN", "Ban Gi\u00E1m \u0111\u1ED1c " + giayChuyenVien.getGcvbhytNoichuyenden()); // chu "Ban Giam doc "
			}

			params.put("HOTENBN", benhNhan.getBenhnhanHoten());
			if (benhNhan.getBenhnhanNgaysinh() != null) {
				params.put("NGAYSINH", sdf.format(benhNhan.getBenhnhanNgaysinh()));
			} else {
				params.put("NGAYSINH", benhNhan.getBenhnhanNamsinh());
			}
			params.put("GIOITINH", benhNhan.getDmgtMaso(true).getDmgtTen());
			params.put("DANTOC", benhNhan.getDantocMa(true).getDmdantocTen());
			params.put("NGHENGHIEP", benhNhan.getBenhnhanNghe(true)
					.getDmnghenghiepTen());

			String diachistr = "";
			if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null)
				diachistr += thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi();
			if (thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() != null)
				diachistr += ", " + thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen();
			if (thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += ", " + thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen();
			if (thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null)
				diachistr += ", " + thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr);

			String so_bhyt = "";
			if (thamkham.getTiepdonMa().getTiepdonSothebh() != null && !thamkham.getTiepdonMa().getTiepdonSothebh().equals("")) {
				so_bhyt = thamkham.getTiepdonMa().getTiepdonSothebh();
				params.put("COQUAN", thamkham.getTiepdonMa().getTiepdonMacoquan()); // 20111013 bao.ttc: them thong tin Co quan 
				
				if(so_bhyt.length() >= 12){
					params.put("khoiBhytMa", so_bhyt.substring(0, 2));
					params.put("khoiBhytMa1", so_bhyt.substring(2, 3));
					params.put("tinhBhytMa", so_bhyt.substring(3, 5));
					params.put("namBhyt", so_bhyt.substring(5, 7));
					params.put("maCoQuan", so_bhyt.substring(7, 10));
					if (thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa() != null) {
						params.put("THEBHYT", so_bhyt.substring(10) + " - " + thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa());
					}
				}
			}

			// if
			// (thamkham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa()
			// != null &&
			// !thamkham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa().equals("")){
			// log.info("===================== getDtdmkhoibhytMa: " +
			// thamkham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa());
			// }

			Date giaTri1 = thamkham.getTiepdonMa(true).getTiepdonGiatri1();
			String sGiaTri1 = "";
			if (giaTri1 != null) {
				sGiaTri1 = sdf.format(giaTri1);
			}

			Date giaTri2 = thamkham.getTiepdonMa(true).getTiepdonGiatri2();
			String sGiaTri2 = "";
			if (giaTri2 != null) {
				sGiaTri2 = sdf.format(giaTri2);
			}

			params.put("GIATRISUDUNG", sGiaTri1 + " \u0111\u1EBFn " + sGiaTri2);
			params.put("NOICAP", thamkham.getTiepdonMa(true).getTinhbhytMa(true).getDmtinhTen());

			if (thamkham.getTiepdonMa(true).getTiepdonNgaygio() != null) {
				params.put("NGAYVAOVIEN", sdf.format(thamkham.getTiepdonMa(true).getTiepdonNgaygio()));
				if (thamkham.getTiepdonMa(true).getTiepdonNgaygiora() != null)
					params.put("NGAYRAVIEN", sdf.format(thamkham.getTiepdonMa(true).getTiepdonNgaygiora()));
				else
					params.put("NGAYRAVIEN", sdf.format(thamkham.getTiepdonMa(true).getTiepdonNgaygio()));

				// log.info("===================== Tu ngay: " +
				// params.get("NGAYVAOVIEN") + " - Den ngay: " +
				// params.get("NGAYRAVIEN"));
			}
			params.put("NOIDIEUTRI", thamkham.getThamkhamBankham(true)
					.getDtdmbankhamTen());
			if (giayChuyenVien.getGcvbhytLidochuyenvien() != null)
				params.put("LIDOCHUYENVIEN", giayChuyenVien
						.getGcvbhytLidochuyenvien());

			// bao.ttc: them cac truong moi
			if (giayChuyenVien.getGcvbhytDauhieulamsang() != null)
				params.put("DAUHIEULAMSANG", giayChuyenVien
						.getGcvbhytDauhieulamsang());
			if (giayChuyenVien.getGcvbhytTinhtrangnguoibenh() != null)
				params.put("TINHTRANGNGUOIBENH", giayChuyenVien
						.getGcvbhytTinhtrangnguoibenh());
			if (giayChuyenVien.getGcvbhytPhuongtienvanchuyen() != null)
				params.put("PHUONGTIENVANCHUYEN", giayChuyenVien
						.getGcvbhytPhuongtienvanchuyen());

			// 20110127 bao.ttc: them cac truong moi
			if (giayChuyenVien.getGcvbhytTemp1() != null)
				params.put("CACXETNGHIEM", giayChuyenVien.getGcvbhytTemp1());
			if (giayChuyenVien.getGcvbhytTemp2() != null)
				params.put("THUOCDADUNG", giayChuyenVien.getGcvbhytTemp2());

			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate
					.getInstance();
			String maChanDoan = "";
			String tenChanDoan = "";

			if (thamkham.getBenhicd10() != null
					&& thamkham.getBenhicd10().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
				}

			}

			String chanDoan = maChanDoan + " " + tenChanDoan;

			// bao.ttc: them thamkham.ghichu
			if (thamkham.getThamkhamGhichu() != null
					&& !thamkham.getThamkhamGhichu().equals("")) {
				chanDoan += " , " + thamkham.getThamkhamGhichu();
			}

			// tiep tuc them benh phu.
			if (thamkham.getBenhicd10phu1() != null
					&& thamkham.getBenhicd10phu1().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10phu1().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
				}

			}
			if (thamkham.getBenhicd10phu2() != null
					&& thamkham.getBenhicd10phu2().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10phu2().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
				}

			}
			if (thamkham.getBenhicd10phu3() != null
					&& thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10phu3().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
				}

			}
			if (thamkham.getBenhicd10phu4() != null
					&& thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10phu4().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
				}

			}
			if (thamkham.getBenhicd10phu5() != null
					&& thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null) {
				DmBenhIcd benh = (DmBenhIcd) dieuTriUtilDelegate.findByMaSo(
						thamkham.getBenhicd10phu5().getDmbenhicdMaso(),
						"DmBenhIcd", "dmbenhicdMaso");
				if (benh != null) {
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , " + maChanDoan + " " + tenChanDoan;
				}

			}
			params.put("CDCNG", chanDoan);

			long iNgayVaoVien = 0;
			if (thamkham.getTiepdonMa(true).getTiepdonNgaygio() != null
					&& thamkham.getTiepdonMa(true).getTiepdonNgaygiora() != null)
				iNgayVaoVien = daysBetween(thamkham.getTiepdonMa(true)
						.getTiepdonNgaygio(), thamkham.getTiepdonMa(true)
						.getTiepdonNgaygiora()) + 1;
			params.put("SONGAYDIEUTRI", iNgayVaoVien + "");

			if (getDaKCT())
				params.put("KCT", "X");
			if (getDtNgoaiTru())
				params.put("DTNGOAITRU", "X");
			if (getDtNoiTru())
				params.put("DTNOITRU", "X");

			// 20110401 bao.ttc: cap nhat lai entity vi tren giao dien khong get
			// duoc ten BS

			 if(giayChuyenVien.getGcvbhytGiamdoc(true).getDtdmnhanvienMaso()!= null){
				 DtDmNhanVien giamdoc =  (DtDmNhanVien)dieuTriUtilDelegate.findByMaSo(giayChuyenVien.getGcvbhytGiamdoc(true).getDtdmnhanvienMaso(),"DtDmNhanVien", "dtdmnhanvienMaso");
				 params.put("GIAMDOC", giamdoc.getDtdmnhanvienTen());
				//params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			 //log.info("***** giamdoc ***** " + giamdoc.getDtdmnhanvienTen());
			 }
			if (giayChuyenVien.getGcvbhytBskham(true).getDtdmnhanvienMaso() != null) {
				DtDmNhanVien bskham = (DtDmNhanVien) dieuTriUtilDelegate
						.findByMaSo(giayChuyenVien.getGcvbhytBskham(true)
								.getDtdmnhanvienMaso(), "DtDmNhanVien",
								"dtdmnhanvienMaso");
				params.put("BSKHAM", bskham.getDtdmnhanvienTen());
				// log.info("***** bskham ***** " +
				// bskham.getDtdmnhanvienTen());
			}

			if (giayChuyenVien.getGcvbhytCBBHXH() != null)
				params.put("CANBO", giayChuyenVien.getGcvbhytCBBHXH());

			// bao.ttc
			if (thamkham.getTiepdonMa(true).getTiepdonMa() != null) {
				params.put("MATIEPDON", thamkham.getTiepdonMa(true).getTiepdonMa());
			}
			
			if (giayChuyenVien.getDieutri1Donvi() != null && giayChuyenVien.getDieutri1Donvi(true).getDmbenhvienMaso() != null) {
				params.put("DIEUTRI1DONVI", giayChuyenVien.getDieutri1Donvi(true).getDmbenhvienTen() + " " + giayChuyenVien.getDieutri1Donvi(true).getDmbenhvienMa());
			}
			
			if (dieutri1Tungay != null && !dieutri1Tungay.equals("")) {
				params.put("DIEUTRI1TUNGAY", dieutri1Tungay);
			}
			
			if (dieutri1Denngay != null && !dieutri1Denngay.equals("")) {
				params.put("DIEUTRI1DENNGAY", dieutri1Denngay);
			}
			
			if (giayChuyenVien.getGcvbhytHuongdieutri() != null) {
				params.put("HUONGDIEUTRI", giayChuyenVien.getGcvbhytHuongdieutri());
			}
			

			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
						IConstantsRes.DATABASE_USER,
						IConstantsRes.DATABASE_PASS);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			jasperPrintTD = JasperFillManager.fillReport(jasperReport, params,
					conn);
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintTD, index,
					IConstantsRes.PATH_BASE
							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
							+ "tiepdon/", "pdf",
					"giaychuyenviennguoibenhcobhyt");
			reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			// log.info("duong dan file xuat report :" + baocao1);
			// log.info("duong dan -------------------- :"+reportPathTD);
			index += 1;
			// log.info("Index :" + index);
			if (conn != null)
				conn.close();
			sShowPrint = "false";
		} catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		log.info("Thoat Method XuatReport");
	}

	private int index = 0;

	// ***********************************************************************************

	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null
				&& !thamkham.getTiepdonMa().getBenhnhanMa()
						.getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa().getBenhnhanMa()
					.getBenhnhanNgaysinh().getTime());
		} else if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh() != null){
			ngaySinh = thamkham.getTiepdonMa().getBenhnhanMa()
					.getBenhnhanNamsinh();
		}
		
		if (thamkham.getTiepdonMa().getTiepdonGiatri1()!= null){
			giatrithe_tungay = formatter.format(thamkham.getTiepdonMa().getTiepdonGiatri1());
			log.info("giatrithe_tungay "+ giatrithe_tungay);
		}
		
		if (thamkham.getTiepdonMa().getTiepdonGiatri2()!= null){
			giatrithe_denngay = formatter.format(thamkham.getTiepdonMa().getTiepdonGiatri2());
			log.info("giatrithe_denngay "+ giatrithe_denngay);
		}
		
		if (giayChuyenVien != null) {
			Date dDieutri1 = giayChuyenVien.getDieutri1Tungay();
            if (dDieutri1 != null){
    	        dieutri1Tungay = formatter.format(dDieutri1);
    	    }else{
    	    	dieutri1Tungay = "";
    	    }
            
            dDieutri1 = giayChuyenVien.getDieutri1Denngay();
            if (dDieutri1 != null){
    	        dieutri1Denngay = formatter.format(dDieutri1);
    	    }else{
    	    	dieutri1Denngay = "";
    	    }
		}
		
	}

	public void displayInfor() throws Exception {
		try {

			if (sMaGiayChuyenVien == null || sMaGiayChuyenVien.equals(""))
				return;

			GiayCvNbBhytDelegate GiayCvNbBhytDel = GiayCvNbBhytDelegate
					.getInstance();
			giayChuyenVien = new GiayCvNbBhyt();
			List<GiayCvNbBhyt> ls = GiayCvNbBhytDel
					.findByGiayCvNbBhyt(getMaGiayChuyenVien());

			if (ls == null || ls.size() == 0) {
				FacesMessages.instance().add(IConstantsRes.RPGCV_NOT_EXIST);
				sMaGiayChuyenVien = "";
				sShowDel = "false";
				sShowPrint = "false";
				setDaKCT(false);
				setDtNgoaiTru(false);
				setDtNoiTru(false);
				return;
			}
			sShowDel = "true";
			sShowPrint = "true";
			giayChuyenVien = ls.get(0);
			sMaGiayChuyenVien = giayChuyenVien.getGcvbhytMa();
			setDaKCT(giayChuyenVien.getGcvbhytKct());
			setDtNgoaiTru(giayChuyenVien.getGcvbhytDadtngoaitru());
			setDtNoiTru(giayChuyenVien.getGcvbhytDadtnoitru());
			log.info("*****sMaGiayChuyenVien: " + sMaGiayChuyenVien);

		} catch (Exception e) {
			System.out.println("error on function displayInfor" + e);
		}
	}

	// /////////////////////
	// /////////////////
	// Ham huy cac service da khoi tao
	public void destroyService() {
		try {
			// log.debug("===== begin destroyService() method");
			// thamKhamWS = null;
			log.debug("***** End destroyService() Giay chuyen vien");
		} catch (Exception ex) {
			log.debug("*****destroyService Exception: " + ex);
		}
	}

	// Ham se duoc goi khi het session (session timeout cau hinh trong file
	// web.xml)
	@Destroy
	public void destroy() {
		log
				.info("************************* destroy GiayChuyenVienNguoiBenhCoTheBHYTAction");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public ThamKham getThamkham() {
		return thamkham;
	}

	public void setThamkham(ThamKham thamkham) {
		this.thamkham = thamkham;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public String getMaBanKhamOut() {
		return maBanKhamOut;
	}

	public void setMaBanKhamOut(String maBanKhamOut) {
		this.maBanKhamOut = maBanKhamOut;
	}

	public String getMaTiepDonOut() {
		return maTiepDonOut;
	}

	public void setMaTiepDonOut(String maTiepDonOut) {
		this.maTiepDonOut = maTiepDonOut;
	}

	public String getGioi() {
		return gioi;
	}

	public void setGioi(String gioi) {
		this.gioi = gioi;
	}

	public String getBacSiKCB() {
		return bacSiKCB;
	}

	public void setBacSiKCB(String bacSiKCB) {
		this.bacSiKCB = bacSiKCB;
	}

	public String getGoToChuyenVienNguoiBenhCoTheBHYT() {
		return goToChuyenVienNguoiBenhCoTheBHYT;
	}

	public void setGoToChuyenVienNguoiBenhCoTheBHYT(String str) {
		this.goToChuyenVienNguoiBenhCoTheBHYT = str;
	}

	public String getCoSoKCB() {
		return sCoSoKCB;
	}

	public void setCoSoKCB(String coSoKCB) {
		sCoSoKCB = coSoKCB;
	}

	public Boolean getDaKCT() {
		return bDaKCT;
	}

	public void setDaKCT(Boolean daKCT) {
		bDaKCT = daKCT;
	}

	public Boolean getDtNgoaiTru() {
		return bDtNgoaiTru;
	}

	public void setDtNgoaiTru(Boolean dtNgoaiTru) {
		bDtNgoaiTru = dtNgoaiTru;
	}

	public Boolean getDtNoiTru() {
		return bDtNoiTru;
	}

	public void setDtNoiTru(Boolean dtNoiTru) {
		bDtNoiTru = dtNoiTru;
	}

	public String getMaGiayChuyenVien() {
		return sMaGiayChuyenVien;
	}

	public void setMaGiayChuyenVien(String maGiayChuyenVien) {
		sMaGiayChuyenVien = maGiayChuyenVien;
	}

	public GiayCvNbBhyt getGiayChuyenVien() {
		return giayChuyenVien;
	}

	public void setGiayChuyenVien(GiayCvNbBhyt giayChuyenVien) {
		this.giayChuyenVien = giayChuyenVien;
	}

	public String getsShowDel() {
		return sShowDel;
	}

	public void setsShowDel(String sShowDel) {
		this.sShowDel = sShowDel;
	}

	public String getsShowPrint() {
		return sShowPrint;
	}

	public void setsShowPrint(String sShowPrint) {
		this.sShowPrint = sShowPrint;
	}

	public String getGiatrithe_tungay() {
		return giatrithe_tungay;
	}

	public void setGiatrithe_tungay(String giatrithe_tungay) {
		this.giatrithe_tungay = giatrithe_tungay;
	}

	public String getGiatrithe_denngay() {
		return giatrithe_denngay;
	}

	public void setGiatrithe_denngay(String giatrithe_denngay) {
		this.giatrithe_denngay = giatrithe_denngay;
	}

	public String getDieutri1Tungay() {
		return dieutri1Tungay;
	}

	public void setDieutri1Tungay(String dieutri1Tungay) {
		this.dieutri1Tungay = dieutri1Tungay;
	}

	public String getDieutri1Denngay() {
		return dieutri1Denngay;
	}

	public void setDieutri1Denngay(String dieutri1Denngay) {
		this.dieutri1Denngay = dieutri1Denngay;
	}

	private static final long ONE_HOUR = 60 * 60 * 1000L;

	private static long daysBetween(Date d1, Date d2) {
		if (d1 == null)
			d1 = new Date();
		if (d2 == null)
			d2 = new Date();
		return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
	}

}
