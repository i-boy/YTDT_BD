/*
 * author : Ly
 */
package com.iesvn.yte.dieutri.hsba.action;

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

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChiTietNhikhoaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChiTietNhikhoa;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B211_3_CapNhatThongTinNhikhoa")
@Synchronized(timeout = 600000)
public class CapNhatThongTinChiTietBANhikhoaAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(CapNhatThongTinChiTietBANhikhoaAction.class);

	@Out(required = false)
	@In(required = false)
	private String duongdanStrDT = null;

	@Out(required = false)
	@In(required = false)
	private String loaiBCDT = null;

	@Out(required = false)
	@In(required = false)
	JasperPrint jasperPrintDT = null;

	@In(required = false)
	@Out(required = false)
	private String soBenhAn;

	@In(required = false)
	@Out(required = false)
	private String khoaMa;

	private String ghiNhanException;
	private String hoTen;
	
	private HsbaChuyenMon hsbaChuyenMon;
	private HsbaChiTietNhikhoa hsbaCTNhikhoa;
	private Hsba hosobenhan;
	private TiepDon tiepDon;
	private BenhNhan benhnhan;

	private int index = 0;

	@Create
	@Begin(nested = true)
	public String init() throws Exception {
		log.info("*** Starting init Chi tiet BA Nhi khoa ***");

		HsbaChuyenMonDelegate hsbacmDel = HsbaChuyenMonDelegate.getInstance();
		hsbaChuyenMon = hsbacmDel.findBySoVaoVien_MaKhoa(soBenhAn, khoaMa);

		if (hsbaChuyenMon != null) {
			
			if (hsbaChuyenMon.getHsbaSovaovien() != null){
				hosobenhan = hsbaChuyenMon.getHsbaSovaovien();
				
				if (hosobenhan.getTiepdonMa() != null && !hosobenhan.getTiepdonMa().equals("")){
					TiepDonDelegate tiepdonDel = TiepDonDelegate.getInstance();
					tiepDon = tiepdonDel.find(hosobenhan.getTiepdonMa());
					if (tiepDon == null){
						tiepDon = new TiepDon();
					}
					
				} else{
					tiepDon = new TiepDon();
				}
				
				if (hosobenhan.getBenhnhanMa() != null){
					benhnhan = hosobenhan.getBenhnhanMa();
				} else{
					benhnhan = new BenhNhan();
				}
				
				hoTen = benhnhan.getBenhnhanHoten();

				HsbaChiTietNhikhoa hsbaCTNhikhoaTemp = null;
				try {
					hsbaCTNhikhoaTemp = HsbaChiTietNhikhoaDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
				} catch (Exception e) {
					log.info("error:" + e);
				}
				
				if (hsbaCTNhikhoaTemp != null) {
					hsbaCTNhikhoa = hsbaCTNhikhoaTemp;
				} else {
					hsbaCTNhikhoa = new HsbaChiTietNhikhoa();
				}
		
			} else {
				hosobenhan = new Hsba();
				tiepDon = new TiepDon();
				benhnhan = new BenhNhan();
			}
			
			
		} else {
			// chua ghi nhan
			return "DieuTri_CapNhat_CapNhatThongTinChung";
		}

		log.info("***Finished init **");
		return "DieuTri_CapNhat_CapNhatThongTinChiTietBANhikhoa";
	}

	@End
	public String back() throws Exception {
		log.info("***Starting back ***");
		log.info("***Finished back **");
		return "DieuTri_CapNhat_CapNhatThongTinChung";
		// return "MyMainForm";
	}

	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() {
		log.info("*** Starting ghinhan Chi tiet BA Nhi khoa ***");
		ghiNhanException = null;

		hsbaCTNhikhoa.setHsbacmMa(hsbaChuyenMon);
		RemoveUtil.removeAllNullFromHSBACTNhikhoa(hsbaCTNhikhoa);
		
		if (hsbaCTNhikhoa.getHsbactnhikhoaMa() == null) {
			HsbaChiTietNhikhoaDelegate.getInstance().create(hsbaCTNhikhoa);
		} else {
			HsbaChiTietNhikhoaDelegate.getInstance().edit(hsbaCTNhikhoa);
		}
		FacesMessages.instance().add(IConstantsRes.SUCCESS);
		log.info("***Finished ghinhan **");
	}

	// Ham khi nhan nut nhap lai
	public void nhaplai() throws Exception {
		try {
			resetForm();
		} catch (Exception e) {
			log.error("Loi reset form : " + e.toString());
		}
	}

	// Ham reset form
	private void resetForm() {
		log.info("Begining ResetForm(): ");
		ghiNhanException = null;
		log.info("End ResetForm(): ");
	}

	public String thuchienAction() {
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}

	public void XuatReport() {
		loaiBCDT = "CapNhatThongTinChiTietBANhikhoa";
		log.info("Vao Method XuatReport bao cao cap nhat thong tin chi tiet benh an Nhi khoa");
		String baocao1 = null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N05_benhannhikhoa_main.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N05_benhannhikhoa_subreport1.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N05_benhannhikhoa_subreport2.jrxml";
			String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N05_benhannhikhoa_subreport3.jrxml";
			String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N05_benhannhikhoa_subreport4.jrxml";

			log.info("da thay file templete " + pathTemplate);
			log.info("da thay file sub 1 templete " + sub1Template);
			log.info("da thay file sub 2 templete " + sub2Template);
			log.info("da thay file sub 3 templete " + sub3Template);
			log.info("da thay file sub 4 templete " + sub4Template);

			JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
			JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
			JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);
			JasperReport sub4Report = JasperCompileManager.compileReport(sub4Template);

			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
			
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI",IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("GIAMDOCBENHVIEN", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			
			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			params.put("sub3", sub3Report);
			params.put("sub4", sub4Report);


			HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();
			//HsbaChuyenMon objHSBAChuyenMon = hsbadel.findBySoVaoVien_MaKhoa(soBenhAn, khoaMa);
			
			HsbaChiTietNhikhoa hsbaCTNhikhoaTemp = null;
			try {
				hsbaCTNhikhoaTemp = HsbaChiTietNhikhoaDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
			} catch (Exception e) {
				log.info("error Xuat Report HsbaChiTietNhikhoaDelegate.getInstance().findByHsbaCM: " + e);
			}
			
			if (hsbaCTNhikhoaTemp != null) {
				hsbaCTNhikhoa = hsbaCTNhikhoaTemp;
			}
			
			// ============================ bao.ttc: thong tin Hanh Chinh ============================

			String sMaTiepDon = "";
			if (hosobenhan.getTiepdonMa() != null)
				sMaTiepDon = hosobenhan.getTiepdonMa();
			
			params.put("MATIEPDON", sMaTiepDon);
			
			String sHoTenBN = "";
			if (benhnhan.getBenhnhanHoten() != null)
				sHoTenBN = benhnhan.getBenhnhanHoten();
			
			params.put("HOTENBN", sHoTenBN);
			
			params.put("BUONG",	hsbaChuyenMon.getHsbacmSobuong());
			params.put("GIUONG", hsbaChuyenMon.getHsbacmSogiuong());

			String diachistr = "";
			if (benhnhan.getBenhnhanDiachi() != null)
				diachistr += benhnhan.getBenhnhanDiachi();
			if (benhnhan.getXaMa(true).getDmxaTen() != null)
				diachistr += ", " + benhnhan.getXaMa(true).getDmxaTen();
			if (benhnhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += ", " + benhnhan.getHuyenMa(true).getDmhuyenTen();
			if (benhnhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += ", " + benhnhan.getTinhMa(true).getDmtinhTen();
			
			params.put("DIACHI", diachistr);
			
			params.put("SOVAOVIEN", hosobenhan.getHsbaSovaovien());

			int iTuoi = benhnhan.getBenhnhanTuoi();
			int iDonviTuoi = benhnhan.getBenhnhanDonvituoi();
			String sDonViTuoi = "";
			if (iDonviTuoi == 1)
				sDonViTuoi = "";
			else if (iDonviTuoi == 2)
				sDonViTuoi = IConstantsRes.THANG;// "Tháng";
			else if (iDonviTuoi == 3)
				sDonViTuoi = IConstantsRes.NGAY; // "Ngày";

			params.put("TUOI", iTuoi + " " + sDonViTuoi);

			params.put("GIOITINH", benhnhan.getDmgtMaso(true).getDmgtTen());

			String sNgaySinh = "";
			if (benhnhan.getBenhnhanNgaysinh() != null) {
				sNgaySinh = sdf.format(benhnhan.getBenhnhanNgaysinh());
			} else if (benhnhan.getBenhnhanNamsinh() != null){
				sNgaySinh = benhnhan.getBenhnhanNamsinh();
			}
			params.put("NGAYSINH", sNgaySinh);
			
			String sNgheNghiep = "";
			if (benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen() != null){
				sNgheNghiep = benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen();
				params.put("NGHENGHIEP", sNgheNghiep);
			}
			
			String sDanToc = "";
			if (benhnhan.getDantocMa(true).getDmdantocTen() != null){
				sDanToc = benhnhan.getDantocMa(true).getDmdantocTen();
				params.put("DANTOC", sDanToc);
				params.put("DANTOCMA", benhnhan.getDantocMa(true).getDmdantocMa());
			}
			
			String sDoiTuong = "";
			if (hosobenhan.getDoituongMa() != null)
				sDoiTuong = hosobenhan.getDoituongMa(true).getDmdoituongTen();
			params.put("DOITUONG", sDoiTuong);
			
			String sNgoaiKieu = "";
			if (benhnhan.getQuocgiaMa(true).getDmquocgiaMa() != null)
				sNgoaiKieu = benhnhan.getQuocgiaMa(true).getDmquocgiaMa();
			params.put("NGOAIKIEU", sNgoaiKieu);
			
			String sKhiCanBaoTin = "";
			if (hosobenhan.getHsbaBaotin() != null)
				sKhiCanBaoTin = hosobenhan.getHsbaBaotin();
			params.put("BAOTIN", sKhiCanBaoTin);
			
			// ============================ END
			
			
			// ============================ bao.ttc: thong tin BHYT ============================
			
			String sSoVaoVien = "";
			if (hosobenhan.getHsbaSovaovien() != null)
				sSoVaoVien = hosobenhan.getHsbaSovaovien();

			HsbaBhytDelegate hsbabhyt = HsbaBhytDelegate.getInstance();
			HsbaBhyt objBHYT = hsbabhyt.findBySoVaoVienLastHsbaBhyt(sSoVaoVien);

			String sGiaTriTu = "";
			String sGiaTriDen = "";
			String sMaTheBHYT = "";
			String sNoiLamViec = "";

			if (objBHYT != null) {
				if (objBHYT.getHsbabhytGiatri0() != null)
					sGiaTriTu = sdf.format(objBHYT.getHsbabhytGiatri0());
				if (objBHYT.getHsbabhytGiatri1() != null)
					sGiaTriDen = sdf.format(objBHYT.getHsbabhytGiatri1());
				if (objBHYT.getHsbabhytSothebh() != null)
					sMaTheBHYT = objBHYT.getHsbabhytSothebh();
				if (objBHYT.getHsbabhytMakcb(true).getDmbenhvienMa() != null)
					sMaTheBHYT += " - " + objBHYT.getHsbabhytMakcb(true).getDmbenhvienMa();
				if (objBHYT.getHsbabhytCoquanbh() != null)
					sNoiLamViec = objBHYT.getHsbabhytCoquanbh();
			}
			params.put("GTTU", sGiaTriTu);
			params.put("GTDEN", sGiaTriDen);
			params.put("MATHEBHYT", sMaTheBHYT);
			params.put("NOILAMVIEC", sNoiLamViec);
			
			// ============================ END
			
			
			Date dVaoVien = new Date();
			Date dRaVien = new Date();
			String sVaoVienLuc = "";
			
			if (hosobenhan.getHsbaNgaygiovaov() != null) {
				dVaoVien = hosobenhan.getHsbaNgaygiovaov();
				sVaoVienLuc = Utils.getGioPhutNgay(hosobenhan.getHsbaNgaygiovaov());
			}
			params.put("NGAYGIOVAOVIEN", sVaoVienLuc);
			// bao.ttc: so ngay dieu tri
			
			if (hosobenhan.getHsbaNgaygiorav() != null) {
				dRaVien = hosobenhan.getHsbaNgaygiorav();
				params.put("GIORAVIEN", Utils.getGioPhutNgay(dRaVien));
				params.put("SONGAYDT", daysBetween(dVaoVien, dRaVien));
			} else{
				params.put("SONGAYDT", daysBetween(dVaoVien, new Date()));
			}
			
			String sTiepnhantai = "2";
			if (tiepDon.getTiepdonBankham() != null) {
				sTiepnhantai = tiepDon.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC") ? "1" : "2";
			} else {
				sTiepnhantai = hosobenhan.getHsbaKhoavaov(true).getDmkhoaMa().startsWith("CC") ? "1" : "2";
			}
			params.put("TIEPNHANTAI", sTiepnhantai);
			
//			String sVaoKhoa = "";
//			if (hosobenhan.getHsbaKhoavaov() != null)
//				sVaoKhoa = hosobenhan.getHsbaKhoavaov(true).getDmkhoaMa();
//			params.put("VAOKHOA", sVaoKhoa);
//			
//			String sVaoKhoaLuc = "";
//			if (hsbaChuyenMon.getHsbacmNgaygiovaok() != null){
//				sVaoKhoaLuc = Utils.getGioPhutNgay(hsbaChuyenMon.getHsbacmNgaygiovaok());
//				params.put("VAOKHOALUC", sVaoKhoaLuc);
//			}
//			
//			List<HsbaChuyenMon> listHSBAChuyenKhoa = hsbadel.findBySoVaoVien(soBenhAn);
//			if (listHSBAChuyenKhoa != null && listHSBAChuyenKhoa.size() > 1) {
//				for (int i = 1; i < listHSBAChuyenKhoa.size() && i <= 3; i++) {
//					String makhoa = "";
//					String ngaygiovaok = "";
//					int stt = i - 1;
//					if (listHSBAChuyenKhoa.get(i).getKhoaMa() != null){
//						makhoa = listHSBAChuyenKhoa.get(i).getKhoaMa(true).getDmkhoaMa();
//						params.put("CHUYENKHOA_" + stt, makhoa);
//					}
//					if (listHSBAChuyenKhoa.get(i).getHsbacmNgaygiovaok() != null){
//						ngaygiovaok = Utils.getGioPhutNgay(listHSBAChuyenKhoa.get(i).getHsbacmNgaygiovaok());
//						params.put("CHUYENKHOALUC_" + stt, ngaygiovaok);
//					}
//				}
//			}
			List<HsbaChuyenMon> listHSBAChuyenKhoa = hsbadel.findBySoVaoVien(soBenhAn);
			if (listHSBAChuyenKhoa != null && listHSBAChuyenKhoa.size() > 0) {
				for (int i = 0; i < listHSBAChuyenKhoa.size() && i <= 3; i++) {
					String makhoa = "";
					String ngaygiovaok = "";
					
					if (listHSBAChuyenKhoa.get(i).getKhoaMa() != null){
						makhoa = listHSBAChuyenKhoa.get(i).getKhoaMa(true).getDmkhoaMa();
						params.put("CHUYENKHOA_" + i, makhoa);
					}
					
					if (listHSBAChuyenKhoa.get(i).getHsbacmNgaygiovaok() != null){
						ngaygiovaok = Utils.getGioPhutNgay(listHSBAChuyenKhoa.get(i).getHsbacmNgaygiovaok());
						params.put("CHUYENKHOALUC_" + i, ngaygiovaok);
						
						if (listHSBAChuyenKhoa.get(i).getHsbacmNgaygiorak() != null){
							params.put("SONGAYDTKHOA_" + i, daysBetween(listHSBAChuyenKhoa.get(i).getHsbacmNgaygiovaok(),listHSBAChuyenKhoa.get(i).getHsbacmNgaygiorak()));
						}
					}
				}
			}
			
			String sDonViGoi = "";
			if (hosobenhan.getHsbaDonvigoi() != null){
				sDonViGoi = hosobenhan.getHsbaDonvigoi(true).getDmbenhvienTen();
				params.put("NOIGIOITHIEU", "1");
			} else {
				params.put("NOIGIOITHIEU", "2");
			}
			params.put("DONVIGOI", sDonViGoi);
			
			if (getHsbaChuyenVien() != null) {
				HsbaChuyenVien hsbacv = getHsbaChuyenVien();
				params.put("CHUYENVIEN", hsbacv.getHsbacvChvienden(true).getDmbenhvienTen());
			} else
				params.put("CHUYENVIEN", "");
			
			
			// ============================ Cac loai chan doan ============================
			
			// Chan doan tuyen truoc
			String maTuyenduoi = "";
			String tenTuyenduoi = "";
			if ( hosobenhan.getHsbaMachdoantuyent() != null ) {
				maTuyenduoi = hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdMa();
				tenTuyenduoi = hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdTen();
			}
			params.put("CHUANDOAN_NOICHUYENDEN_MA", maTuyenduoi);
			params.put("CHUANDOAN_NOICHUYENDEN", tenTuyenduoi);
			
			// Chan doan Cap cuu, Khoa Kham benh
			String maChandoanCC = "";
			String tenChandoanCC = "";
			if ( hosobenhan.getHsbaMachdoanbd() != null ) {
				maChandoanCC = hosobenhan.getHsbaMachdoanbd(true).getDmbenhicdMa();
				tenChandoanCC = hosobenhan.getHsbaMachdoanbd(true).getDmbenhicdTen();
			}
			params.put("CHUANDOAN_CAPCUU", maChandoanCC);
			params.put("CHUANDOAN_CAPCUU_TEN", tenChandoanCC);
			
			// Chan doan Vao Khoa
			String maChuanDoanVaoKhoa = "";
			String tenChuanDoanVaoKhoa = "";
			if (hsbaChuyenMon.getHsbacmBenhchinh() != null) {
				maChuanDoanVaoKhoa = hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa();
				tenChuanDoanVaoKhoa = hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
			}
			params.put("CHUANDOAN_KHIVAOKHOA", maChuanDoanVaoKhoa);
			params.put("CHUANDOAN_KHIVAOKHOA_TEN", tenChuanDoanVaoKhoa);
			
			// Ra vien - Benh chinh: dung HSBACM hien tai, nen dung HSBACM khoa ra vien
			// Ra vien - Benh phu
			
			String sBenhChinhMa = "";
			String sBenhChinh = "";
			String sBenhPhuMa = "";
			String sBenhKemTheo = "";
			
			if (hosobenhan.getHsbaKhoarav() != null){
				
				if (hsbaChuyenMon.getHsbacmBenhchinh() != null) {
					sBenhChinh = hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
					sBenhChinhMa = hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa();
				}
				if (hsbaChuyenMon.getHsbacmBenhphu() != null) {
					sBenhKemTheo = hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
					sBenhPhuMa = hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdMa();
				}
			}
			params.put("BENHCHINH", sBenhChinh);
			params.put("BENHCHINHMA", sBenhChinhMa);
			params.put("BENHPHU", sBenhKemTheo);
			params.put("BENHPHUMA", sBenhPhuMa);
			
			// ============================ END - Cac loai chan doan ============================
			
			
			// ============================ Tinh trang ra vien ============================
			
			
			params.put("MACH", tiepDon.getTiepdonMach());
			params.put("NHIETDO", tiepDon.getTiepdonNhietdo());
			params.put("HUYETAPMAX", tiepDon.getTiepdonHamax());
			params.put("HUYETAPMIN", tiepDon.getTiepdonHamin());
			params.put("NHIPTHO", tiepDon.getTiepdonNhiptho());
			params.put("CANNANG", tiepDon.getTiepdonTrluong());
			
			
			if (hsbaChuyenMon.getKetquaMa() != null)
				params.put("KETQUA", hsbaChuyenMon.getKetquaMa(true).getDmkqdtTen());
			
			String sNguyenNhanTuVong = "";
			String sNguyenNhanTuVongMa = "";
			if (hosobenhan.getHsbaTuvong() != null) {
				sNguyenNhanTuVong = hosobenhan.getHsbaTuvong(true).getDmbenhicdTen();
				sNguyenNhanTuVongMa = hosobenhan.getHsbaTuvong(true).getDmbenhicdMa();
				params.put("NGUYENNHANTUVONG", sNguyenNhanTuVong);
				params.put("NGUYENNHANTUVONGMA", sNguyenNhanTuVongMa);
			}
			
			String sNgayGioTuVong = "";
			if (hosobenhan.getHsbaNgaygiotv() != null){
				sNgayGioTuVong = Utils.getGioPhutNgay(hosobenhan.getHsbaNgaygiotv());
				params.put("NGAYGIOTUVONG", sNgayGioTuVong);
			}
			

			params.put("HOTENBO", hsbaCTNhikhoa.getHsbactnhikhoaHotenBo());
			params.put("HOTENME", hsbaCTNhikhoa.getHsbactnhikhoaHotenMe());
			params.put("NGHENGHIEPBO", hsbaCTNhikhoa.getHsbactnhikhoaNghenghiepBo());
			params.put("NGHENGHIEPME", hsbaCTNhikhoa.getHsbactnhikhoaNghenghiepMe());
			params.put("TRINHDOVHBO", hsbaCTNhikhoa.getHsbactnhikhoaTdvhBo());
			params.put("TRINHDOVHME", hsbaCTNhikhoa.getHsbactnhikhoaTdvhMe());
			
			
			if(hsbaChuyenMon.getHsbacmLathuthuat() != null )
				params.put("CHUANDOAN_COTHUTHUAT", "X");
			if(hsbaChuyenMon.getHsbacmLaphauthuat() != null )
				params.put("CHUANDOAN_COPHAUTHUAT", "X");
			if(hsbaChuyenMon.getHsbacmBienchung() != null && !hsbaChuyenMon.getHsbacmBienchung().trim().equals(""))
				params.put("CHUANDOAN_COBIENCHUNG", "X");
			if(hsbaChuyenMon.getKetquaMa(true).getDmkqdtMaso() != null && hsbaChuyenMon.getKetquaMa(true).getDmkqdtMaso() == 5){
				if (hsbaChuyenMon.getHsbaSovaovien(true).getHsbaTuvvong24g()){
					params.put("TUVONGTRONG24H", "X");
				}
				else{
					params.put("TUVONGSAU24H", "X");
				}
			}


			if (hsbaCTNhikhoa.getHsbactnhikhoaLydovaov() != null)
				params.put("LYDOVAOVIEN", hsbaCTNhikhoa.getHsbactnhikhoaLydovaov());
			if (hsbaCTNhikhoa.getHsbactnhikhoaNgaybenhthu() != null)
				params.put("VAONGAYTHU", hsbaCTNhikhoa.getHsbactnhikhoaNgaybenhthu());
			if (hsbaCTNhikhoa.getHsbactnhikhoaQtbenhly() != null)
				params.put("QTBENHLY", hsbaCTNhikhoa.getHsbactnhikhoaQtbenhly());
			if (hsbaCTNhikhoa.getHsbactnhikhoaTiensubenhbt() != null)
				params.put("TSBBANTHAN", hsbaCTNhikhoa.getHsbactnhikhoaTiensubenhbt());
			if (hsbaCTNhikhoa.getHsbactnhikhoaTiensubenhgd() != null)
				params.put("GIADINH", hsbaCTNhikhoa.getHsbactnhikhoaTiensubenhgd());
			
			params.put("CONTHU", hsbaCTNhikhoa.getHsbactnhikhoaConthumay());
			if(hsbaCTNhikhoa.getHsbactnhikhoaCannanglucsinh() != null)
				params.put("CANNANGLUCSINH", hsbaCTNhikhoa.getHsbactnhikhoaCannanglucsinh() + " kg");
			params.put("TATBAMSINH", hsbaCTNhikhoa.getHsbactnhikhoaTatbamsinh());
			params.put("PHATTRIENTINHTHAN", hsbaCTNhikhoa.getHsbactnhikhoaPtTinhthan());
			params.put("PHATTRIENVANDONG", hsbaCTNhikhoa.getHsbactnhikhoaPtVandong());
			params.put("CACBENHLYKHAC", hsbaCTNhikhoa.getHsbactnhikhoaSinhtruongBlkhac());
			params.put("CAISUATHANGTHU", hsbaCTNhikhoa.getHsbactnhikhoaCaisuathangthu());
			params.put("BENHKHACDUOCTIEMCHUNG", hsbaCTNhikhoa.getHsbactnhikhoaBenhkhacduoctiemchung());
			if(hsbaCTNhikhoa.getHsbactnhikhoaChieucao() != null)
				params.put("CHIEUCAO", hsbaCTNhikhoa.getHsbactnhikhoaChieucao() + " cm	");
			if(hsbaCTNhikhoa.getHsbactnhikhoaVongnguc() != null)
				params.put("VONGNGUC", hsbaCTNhikhoa.getHsbactnhikhoaVongnguc() + " cm	");
			if(hsbaCTNhikhoa.getHsbactnhikhoaVongdau() != null)
				params.put("VONGDAU", hsbaCTNhikhoa.getHsbactnhikhoaVongdau() + " cm	");
			
			if(hsbaCTNhikhoa.getHsbactnhikhoaSinhduthang() != null)
				params.put("SINHDUTHANG", hsbaCTNhikhoa.getHsbactnhikhoaSinhduthang());
			if(hsbaCTNhikhoa.getHsbactnhikhoaSinhdenon() != null)
				params.put("SOMDENON", hsbaCTNhikhoa.getHsbactnhikhoaSinhdenon());
			if(hsbaCTNhikhoa.getHsbactnhikhoaSinhnaohut() != null)
				params.put("SAYNAOHUT", hsbaCTNhikhoa.getHsbactnhikhoaSinhnaohut());
			if(hsbaCTNhikhoa.getHsbactnhikhoaSinhsong() != null)
				params.put("SONG", hsbaCTNhikhoa.getHsbactnhikhoaSinhsong());
			
			if(hsbaCTNhikhoa.getHsbactnhikhoaDethuong() != null && hsbaCTNhikhoa.getHsbactnhikhoaDethuong() == true)
				params.put("DETHUONG", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaDechihuy() != null && hsbaCTNhikhoa.getHsbactnhikhoaDechihuy() == true)
				params.put("DECHIHUY", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaForceps() != null && hsbaCTNhikhoa.getHsbactnhikhoaForceps() == true)
				params.put("FORCEPS", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaDekhac() != null && hsbaCTNhikhoa.getHsbactnhikhoaDekhac() == true)
				params.put("TTKHAC", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaDephauthuat() != null && hsbaCTNhikhoa.getHsbactnhikhoaDephauthuat() == true)
				params.put("DEPHAUTHUAT", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaGiachut() != null && hsbaCTNhikhoa.getHsbactnhikhoaGiachut() == true)
				params.put("GIACHUT", "X");
			
			if(hsbaCTNhikhoa.getHsbactnhikhoaDitatbamsinh() != null && hsbaCTNhikhoa.getHsbactnhikhoaDitatbamsinh() == true)
				params.put("CODITATBAMSINH", "X");
			
			if(hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongSuame() != null && hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongSuame() == true)
				params.put("NUOISUAME", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongHonhop() != null && hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongHonhop() == true)
				params.put("NUOIHONHOP", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongNhantao() != null && hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongNhantao() == true)
				params.put("NUOINHANTAO", "X");
			
			if(hsbaCTNhikhoa.getHsbactnhikhoaChamsocTainhatre() != null && hsbaCTNhikhoa.getHsbactnhikhoaChamsocTainhatre() == true)
				params.put("CHAMSOCTAINHATRE", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaChamsocTainha() != null && hsbaCTNhikhoa.getHsbactnhikhoaChamsocTainha() == true)
				params.put("CHAMSOCTAINHA", "X");
			
			if(hsbaCTNhikhoa.getHsbactnhikhoaTiemchungLao() != null && hsbaCTNhikhoa.getHsbactnhikhoaTiemchungLao() == true)
				params.put("TIEMCHUNGLAO", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaTiemchungBailiet() != null && hsbaCTNhikhoa.getHsbactnhikhoaTiemchungBailiet() == true)
				params.put("TIEMCHUNGBAILIET", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaTiemchungSoi() != null && hsbaCTNhikhoa.getHsbactnhikhoaTiemchungSoi() == true)
				params.put("TIEMCHUNGSOI", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaTiemchungHoga() != null && hsbaCTNhikhoa.getHsbactnhikhoaTiemchungHoga() == true)
				params.put("TIEMCHUNGHOGA", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaTiemchungUonvan() != null && hsbaCTNhikhoa.getHsbactnhikhoaTiemchungUonvan() == true)
				params.put("TIEMCHUNGUONVAN", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaTiemchungBachhau() != null && hsbaCTNhikhoa.getHsbactnhikhoaTiemchungBachhau() == true)
				params.put("TIEMCHUNGBACHHAU", "X");
			if(hsbaCTNhikhoa.getHsbactnhikhoaTiemchungKhac() != null && hsbaCTNhikhoa.getHsbactnhikhoaTiemchungKhac() == true)
				params.put("TIEMCHUNGKHAC", "X");
			
			if (hsbaCTNhikhoa.getHsbactnhikhoaToanthan() != null)
				params.put("TOANTHAN", hsbaCTNhikhoa.getHsbactnhikhoaToanthan());
			if (hsbaCTNhikhoa.getHsbactnhikhoaTuanhoan() != null)
				params.put("TUANHOAN", hsbaCTNhikhoa.getHsbactnhikhoaTuanhoan());
			

			// *****PAGE 3*******//
			if (hsbaCTNhikhoa.getHsbactnhikhoaBslamba() != null)
				params.put("BASILAMBA", hsbaCTNhikhoa.getHsbactnhikhoaBslamba(true).getDtdmnhanvienTen());
			if (hsbaCTNhikhoa.getHsbactnhikhoaHohap() != null)
				params.put("HOHAP", hsbaCTNhikhoa.getHsbactnhikhoaHohap());
			if (hsbaCTNhikhoa.getHsbactnhikhoaTieuhoa() != null)
				params.put("TIEUHOA", hsbaCTNhikhoa.getHsbactnhikhoaTieuhoa());
			if (hsbaCTNhikhoa.getHsbactnhikhoaThantietnieusinhhoc() != null)
				params.put("THANTNSD", hsbaCTNhikhoa.getHsbactnhikhoaThantietnieusinhhoc());
			if (hsbaCTNhikhoa.getHsbactnhikhoaThankinh() != null)
				params.put("THANKINH", hsbaCTNhikhoa.getHsbactnhikhoaThankinh());
			if (hsbaCTNhikhoa.getHsbactnhikhoaCoxuongkhop() != null)
				params.put("COXUONGKHOP", hsbaCTNhikhoa.getHsbactnhikhoaCoxuongkhop());
			if (hsbaCTNhikhoa.getHsbactnhikhoaTmhRhmMatDinhduongKhac() != null)
				params.put("RHM-TMH-MAT-BENHLYKHAC", hsbaCTNhikhoa.getHsbactnhikhoaTmhRhmMatDinhduongKhac());
			if (hsbaCTNhikhoa.getHsbactnhikhoaTtba() != null)
				params.put("TTBA", hsbaCTNhikhoa.getHsbactnhikhoaTtba());
			params.put("HUONGDIEUTRI", hsbaCTNhikhoa.getHsbactnhikhoaHuongdieutri());
			params.put("XETNGHIEMCANLAM", hsbaCTNhikhoa.getHsbactnhikhoaXetnghiemcanlam());
			params.put("TIENLUONG", hsbaCTNhikhoa.getHsbactnhikhoaTienluong());

			// *****PAGE 4*******//
			if (hsbaCTNhikhoa.getHsbactnhikhoaQtblDbls() != null)
				params.put("QTBLDBLS", hsbaCTNhikhoa.getHsbactnhikhoaQtblDbls());
			if (hsbaCTNhikhoa.getHsbactnhikhoaTtkqxncls() != null)
				params.put("TTKQCLSCD", hsbaCTNhikhoa.getHsbactnhikhoaTtkqxncls());
			if (hsbaCTNhikhoa.getHsbactnhikhoaPpdieutri() != null)
				params.put("PPDT", hsbaCTNhikhoa.getHsbactnhikhoaPpdieutri());
			if (hsbaCTNhikhoa.getHsbactnhikhoaTtnguoibenhrav() != null)
				params.put("TTNBRV", hsbaCTNhikhoa.getHsbactnhikhoaTtnguoibenhrav());
			if (hsbaCTNhikhoa.getHsbactnhikhoaHuongdtCdtt() != null)
				params.put("HDTCCDTT", hsbaCTNhikhoa.getHsbactnhikhoaHuongdtCdtt());
			if (hsbaCTNhikhoa.getHsbactnhikhoaSotoxquang() != null)
				params.put("XQUANG", hsbaCTNhikhoa.getHsbactnhikhoaSotoxquang());
			if (hsbaCTNhikhoa.getHsbactnhikhoaSotoctscanner() != null)
				params.put("SCANNER", hsbaCTNhikhoa.getHsbactnhikhoaSotoctscanner());
			if (hsbaCTNhikhoa.getHsbactnhikhoaSotosieuam() != null)
				params.put("SIEUAM", hsbaCTNhikhoa.getHsbactnhikhoaSotosieuam());
			if (hsbaCTNhikhoa.getHsbactnhikhoaSotoxn() != null)
				params.put("XETNGHIEM", hsbaCTNhikhoa.getHsbactnhikhoaSotoxn());
			if (hsbaCTNhikhoa.getHsbactnhikhoaSotokhac() != null)
				params.put("HSKHAC", hsbaCTNhikhoa.getHsbactnhikhoaSotokhac());
			if (hsbaCTNhikhoa.getHsbactnhikhoaTongsoto() != null)
				params.put("TONGHOSO", hsbaCTNhikhoa.getHsbactnhikhoaTongsoto());
			if (hsbaCTNhikhoa.getHsbactnhikhoaNguoigiaoba() != null)
				params.put("NGUOIGIAOBA", hsbaCTNhikhoa.getHsbactnhikhoaNguoigiaoba(true).getDtdmnhanvienTen());
			if (hsbaCTNhikhoa.getHsbactnhikhoaNguoinhanba() != null)
				params.put("NGUOINHANBA", hsbaCTNhikhoa.getHsbactnhikhoaNguoinhanba(true).getDtdmnhanvienTen());
			if (hsbaCTNhikhoa.getHsbactnhikhoaBsdieutri() != null)
				params.put("BASIDT", hsbaCTNhikhoa.getHsbactnhikhoaBsdieutri(true).getDtdmnhanvienTen());

			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
						IConstantsRes.DATABASE_USER,
						IConstantsRes.DATABASE_PASS);
				jasperPrintDT = JasperFillManager.fillReport(jasperReport, params,conn);
				log.info("fillReport THANHCONG");
			} catch (Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
			}
			
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintDT, index, IConstantsRes.PATH_BASE
							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
							+ "hsba/", "pdf", "capNhatThongTinChiTietBANhikhoa");
			duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
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

	public Integer getSeconds(java.util.Date d) {
		long seconds = DateUtils.getFragmentInHours(d, java.util.Calendar.DATE)
				* 60
				* 60
				+ DateUtils.getFragmentInMinutes(d,
						java.util.Calendar.HOUR_OF_DAY) * 60;
		return Integer.valueOf(seconds + "");
	}

	public String getGhiNhanException() {
		return ghiNhanException;
	}

	public void setGhiNhanException(String ghiNhanException) {
		this.ghiNhanException = ghiNhanException;
	}

	public String getReportPathTD() {
		return duongdanStrDT;
	}

	public void setReportPathTD(String reportPathTD) {
		this.duongdanStrDT = reportPathTD;
	}

	public String getReportTypeTD() {
		return loaiBCDT;
	}

	public void setReportTypeTD(String reportTypeTD) {
		this.loaiBCDT = reportTypeTD;
	}

	public JasperPrint getJasperPrintTD() {
		return jasperPrintDT;
	}

	public void setJasperPrintTD(JasperPrint jasperPrintTD) {
		this.jasperPrintDT = jasperPrintTD;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private HsbaChuyenVien getHsbaChuyenVien() {
		HsbaChuyenVienDelegate hsbaChuyenVienDel = HsbaChuyenVienDelegate
				.getInstance();
		HsbaChuyenVien objhsbaChuyenVien = hsbaChuyenVienDel
				.findBySoVaoVien(soBenhAn);
		return objhsbaChuyenVien;
	}

	private static final long ONE_HOUR = 60 * 60 * 1000L;

	private static long daysBetween(Date d1, Date d2) {
		return ( ((d2.getTime() - d1.getTime()) / (ONE_HOUR * 24)) + 1 );
	}

	public HsbaChiTietNhikhoa getHsbaCTNhikhoa() {
		return hsbaCTNhikhoa;
	}

	public void setHsbaCTNhikhoa(HsbaChiTietNhikhoa hsbaCTNhikhoa) {
		this.hsbaCTNhikhoa = hsbaCTNhikhoa;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	/*
	 * testing
	 */
	/*
	 * private static void main(String[] args) { java.text.SimpleDateFormat sdf
	 * = new java.text.SimpleDateFormat("yyyyMMdd"); Calendar first =
	 * Calendar.getInstance(); first.set(2009, Calendar.NOVEMBER, 1); Calendar
	 * second = Calendar.getInstance();
	 * 
	 * System.out.println (daysBetween(first.getTime(),second.getTime()) +
	 * " day(s) between " + sdf.format(first.getTime()) + " and " +
	 * sdf.format(second.getTime()));
	 * 
	 * output : 21 day(s) between 20080801 and 20080822
	 * 
	 * }
	 */

}
