/*
 * author : i-boy
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
import com.iesvn.yte.dieutri.delegate.HsbaChiTietNgoaiDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaPhieuPhauThuatThuThuatDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChiTietNgoai;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B211_3_CapNhatThongTinNgoai")
@Synchronized(timeout = 600000)
public class CapNhatThongTinChiTietBANgoaiAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger
			.getLogger(CapNhatThongTinChiTietBANgoaiAction.class);

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
	private HsbaChiTietNgoai hsbaCTNgoai;
	private Hsba hosobenhan;
	private TiepDon tiepDon;
	private BenhNhan benhnhan;

	private int index = 0;

	@Create
	@Begin(nested = true)
	public String init() throws Exception {
		log.info("***Starting init chi tiet BA Ngoai Khoa ***");

		HsbaChuyenMonDelegate hsbacmDel = HsbaChuyenMonDelegate.getInstance();
		hsbaChuyenMon = hsbacmDel.findBySoVaoVien_MaKhoa(soBenhAn, khoaMa);
		log.info("hsbaChuyenMon = " + hsbaChuyenMon);

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

				HsbaChiTietNgoai hsbaCTNgoaiTemp = null;
				try {
					hsbaCTNgoaiTemp = HsbaChiTietNgoaiDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
				} catch (Exception e) {
					log.info("error:" + e);
				}
				
				if (hsbaCTNgoaiTemp != null) {
					hsbaCTNgoai = hsbaCTNgoaiTemp;
				} else {
					hsbaCTNgoai = new HsbaChiTietNgoai();
				}
		
			} else {
				hosobenhan = new Hsba();
				tiepDon = new TiepDon();
				benhnhan = new BenhNhan();
			}
			
			
		} else { // hsbaChuyenMon == null
			// chua ghi nhan
			return "DieuTri_CapNhat_CapNhatThongTinChung";
		}

		log.info("***Finished init **");
		return "DieuTri_CapNhat_CapNhatThongTinChiTietBANgoai";
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
		log.info("***Starting ghinhan chi tiet BA Ngoai **");
		ghiNhanException = null;

		hsbaCTNgoai.setHsbacmMa(hsbaChuyenMon);

		RemoveUtil.removeAllNullFromHSBACTNgoai(hsbaCTNgoai);

		if (hsbaCTNgoai.getHsbactngoaiMa() == null) {
			HsbaChiTietNgoaiDelegate.getInstance().create(hsbaCTNgoai);
		} else {
			HsbaChiTietNgoaiDelegate.getInstance().edit(hsbaCTNgoai);
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
		loaiBCDT = "CapNhatThongTinChiTietBANgoai";
		log
				.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an Ngoai Khoa");
		String baocao1 = null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI	+ "hsba/benhanngoaikhoa_main.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaikhoa_sub1.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaikhoa_sub2.jrxml";
			String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaikhoa_sub3.jrxml";
			String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaikhoa_sub4.jrxml";

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
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("GIAMDOCBENHVIEN", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);

			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			params.put("sub3", sub3Report);
			params.put("sub4", sub4Report);
			
			params.put("CHITIET", hsbaCTNgoai.getHsbactngoaiMa());


			HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();
			//HsbaChuyenMon objHSBAChuyenMon = hsbadel.findBySoVaoVien_MaKhoa(soBenhAn, khoaMa);
			
			HsbaChiTietNgoai hsbaCTNgoaiTemp = null;
			try {
				hsbaCTNgoaiTemp = HsbaChiTietNgoaiDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
			} catch (Exception e) {
				log.info("error Xuat Report HsbaChiTietNgoaiDelegate.getInstance().findByHsbaCM: " + e);
			}
			
			if (hsbaCTNgoaiTemp != null) {
				hsbaCTNgoai = hsbaCTNgoaiTemp;
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

			params.put("GIOITINH", benhnhan.getDmgtMaso(true).getDmgtMa().equals("0") ? "1" : "2");
			
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
				params.put("NGHENGHIEPMA", benhnhan.getBenhnhanNghe(true).getDmnghenghiepMa());
			}
			
			String sDanToc = "";
			if (benhnhan.getDantocMa(true).getDmdantocTen() != null){
				sDanToc = benhnhan.getDantocMa(true).getDmdantocTen();
				params.put("DANTOC", sDanToc);
				params.put("DANTOCMA", benhnhan.getDantocMa(true).getDmdantocMa());
			}
			
			String sDoiTuong = "";
			if (hosobenhan.getDoituongMa(true).getDmdoituongMa() != null) {
				sDoiTuong = hosobenhan.getDoituongMa(true).getDmdoituongMa();
				if (sDoiTuong.equals("BH"))
					sDoiTuong = "1";
				else if (sDoiTuong.equals("MP"))
					sDoiTuong = "3";
				else
					sDoiTuong = "2";
			}
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
			params.put("TUYENDUOI", maTuyenduoi);
			params.put("TUYENDUOI_TEN", tenTuyenduoi);
			
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
			params.put("CHUANDOAN_VAOKHOA", maChuanDoanVaoKhoa);
			params.put("CHUANDOAN_VAOKHOA_TEN", tenChuanDoanVaoKhoa);
			
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
			
//			if (sBenhChinhMa.equals(""))
//				params.put("GIAIPHAUBENH", "2");
//			else
//				params.put("GIAIPHAUBENH", "1");
			
			// ============================ END - Cac loai chan doan ============================
			
			
			// ============================ Tinh trang ra vien ============================
			
			String maKetQua = "";
			String tenKetQua = "";
			int iKetqua = 0;
			
			if (hsbaChuyenMon.getKetquaMa() != null) {
				iKetqua = hsbaChuyenMon.getKetquaMa(true).getDmkqdtMaso();
				maKetQua = "" + iKetqua;
				tenKetQua = hsbaChuyenMon.getKetquaMa(true).getDmkqdtTen();
				params.put("KETQUA", maKetQua);
				params.put("KETQUA_TEN", tenKetQua);
			}
			
			String sTmp = "";
			String sBienChung = "2";
			if (hsbaChuyenMon.getHsbacmBienchung() != null) {
				sTmp = hsbaChuyenMon.getHsbacmBienchung();
				if (!(sTmp.equals(""))) {
					sBienChung = "1    ";
					if (sTmp.toLowerCase().indexOf("gây mê") != -1)
						sBienChung += "a";
					else if (sTmp.toLowerCase().indexOf("nhiễm khuẩn") != -1)
						sBienChung += "b";
					else
						sBienChung += "c";
				}
			}
			params.put("BIENCHUNG", sBienChung);
			
			String sNguyenNhanTuVong = "";
			String sNguyenNhanTuVongMa = "";
			if (hosobenhan.getHsbaTuvong() != null) {
				sNguyenNhanTuVong = hosobenhan.getHsbaTuvong(true).getDmbenhicdTen();
				sNguyenNhanTuVongMa = hosobenhan.getHsbaTuvong(true).getDmbenhicdMa();
				params.put("NGUYENNHANTUVONG", sNguyenNhanTuVong);
				params.put("NGUYENNHANTUVONGMA", sNguyenNhanTuVongMa);
			}
			
			String sTuVongDo = "";
			String sTuVongDoChiTiet = "";
			if (iKetqua == 5) {
				if (sNguyenNhanTuVong.equals("")) {
					sTuVongDo = "2";
				} else
					sTuVongDo = "1";
				if (hosobenhan.getHsbaTuvvong24g())
					sTuVongDoChiTiet = "a";
				params.put("TUVONGDO", sTuVongDo);
				params.put("TUVONGDOCHITIET", sTuVongDoChiTiet);
			}
			
			String sNgayGioTuVong = "";
			if (hosobenhan.getHsbaNgaygiotv() != null){
				sNgayGioTuVong = Utils.getGioPhutNgay(hosobenhan.getHsbaNgaygiotv());
				params.put("NGAYGIOTUVONG", sNgayGioTuVong);
			}
			
			// ============================ END - Tinh trang ra vien ============================
			
			
			// Thong tin Phau thuat - Thu thuat - Co thong tin o sub4
			HsbaPhieuPhauThuatThuThuat ppttt = null;
			HsbaPhieuPhauThuatThuThuatDelegate hsbaPhieuPhauThuatThuThuatDelegate =  HsbaPhieuPhauThuatThuThuatDelegate.getInstance();
			ppttt = hsbaPhieuPhauThuatThuThuatDelegate.findByHsba(hosobenhan.getHsbaSovaovien());
			if (ppttt != null) {
				
				params.put("LAN_PHAUTHUAT", "1");
				
				if (ppttt.getHsbapptttChuandoanmaTruoc() != null){
					params.put("CHUANDOAN_TRUOCPT", ppttt.getHsbapptttChuandoanmaTruoc().getDmbenhicdMa());
					params.put("CHUANDOAN_TRUOCPT_TEN", ppttt.getHsbapptttChuandoanmaTruoc().getDmbenhicdTen());
				}
				
				if (ppttt.getHsbapptttChuandoanmaSau() != null){
					params.put("CHUANDOAN_SAUPT", ppttt.getHsbapptttChuandoanmaSau().getDmbenhicdMa());
					params.put("CHUANDOAN_SAUPT_TEN", ppttt.getHsbapptttChuandoanmaSau().getDmbenhicdTen());
				}
				
				if (ppttt.getHsbapptttNgaypttt() != null){
					// param type DATE: vi can hien thi ca ngay, gio
					params.put("NGAY_PHAUTHUAT", ppttt.getHsbapptttNgaypttt());
					if (hosobenhan.getHsbaNgaygiovaov() != null) {
						// param type: LONG
						params.put("NGAYDT_TRUOC_PHAUTHUAT", daysBetween(hosobenhan.getHsbaNgaygiovaov(), ppttt.getHsbapptttNgaypttt()));
					}
				}
				
				if (ppttt.getHsbapptttPhuongphap() != null){
					params.put("PHUONGPHAP_PHAUTHUAT", ppttt.getHsbapptttPhuongphap().getDtdmclsbgDiengiai());
				}
				
				if (ppttt.getHsbapptttPhuongphapVocam() != null){  // thong tin sub4
					params.put("PHUONGPHAP_VOCAM", ppttt.getHsbapptttPhuongphapVocam().getDtdmvocamDiengiai());
				}
				
				List<DtDmNhanVien> bacsigaymeList;  // thong tin sub4
				bacsigaymeList = hsbaPhieuPhauThuatThuThuatDelegate.findBacsigmByHsbapptttMa(ppttt.getHsbapptttMa());
				if (bacsigaymeList != null && bacsigaymeList.size() > 0) {
					params.put("BACSI_GAYME", bacsigaymeList.get(0).getDtdmnhanvienTen());
				}
				
				List<DtDmNhanVien> bacsiList;
				bacsiList = hsbaPhieuPhauThuatThuThuatDelegate.findBacsiByHsbapptttMa(ppttt.getHsbapptttMa());
				if (bacsiList != null && bacsiList.size() > 0) {
					params.put("PHAUTHUATVIEN", bacsiList.get(0).getDtdmnhanvienTen());
				}
				
			} else {
				params.put("LAN_PHAUTHUAT", "0");
			}
			
			// END -- Thong tin Phau thuat - Thu thuat
			
			
			// *************PAGE 2************//
			
			params.put("MACH", tiepDon.getTiepdonMach());
			params.put("NHIETDO", tiepDon.getTiepdonNhietdo());
			params.put("HUYETAPMAX", tiepDon.getTiepdonHamax());
			params.put("HUYETAPMIN", tiepDon.getTiepdonHamin());
			params.put("NHIPTHO", tiepDon.getTiepdonNhiptho());
			params.put("CANNANG", tiepDon.getTiepdonTrluong());

			
			
			// TT
			if (hsbaCTNgoai.getHsbactngoaiLydovaov() != null)
				params.put("LYDOVAOVIEN", hsbaCTNgoai.getHsbactngoaiLydovaov());
			if (hsbaCTNgoai.getHsbactngoaiNgaybenhthu() != null)
				params.put("VAONGAYTHU", hsbaCTNgoai.getHsbactngoaiNgaybenhthu());
			if (hsbaCTNgoai.getHsbactngoaiQtbenhly() != null)
				params.put("QTBENHLY", hsbaCTNgoai.getHsbactngoaiQtbenhly());
			if (hsbaCTNgoai.getHsbactngoaiTiensubenhbt() != null)
				params.put("TSBBANTHAN", hsbaCTNgoai.getHsbactngoaiTiensubenhbt());
			if (hsbaCTNgoai.getHsbactngoaiTiensubenhgd() != null)
				params.put("GIADINH", hsbaCTNgoai.getHsbactngoaiTiensubenhgd());
			
			
			if (hsbaCTNgoai.getHsbactngoaiDdDiung() != null && hsbaCTNgoai.getHsbactngoaiDdDiung() == true)
				params.put("DIUNG", "X");
			else
				params.put("DIUNG", "");
			if (hsbaCTNgoai.getHsbactngoaiDdMatuy() != null && hsbaCTNgoai.getHsbactngoaiDdMatuy() == true)
				params.put("MATUY", "X");
			else
				params.put("MATUY", "");
			if (hsbaCTNgoai.getHsbactngoaiDdRuoubia() != null && hsbaCTNgoai.getHsbactngoaiDdRuoubia() == true)
				params.put("RUOUBIA", "X");
			else
				params.put("RUOUBIA", "");
			if (hsbaCTNgoai.getHsbactngoaiDdThuocla() != null && hsbaCTNgoai.getHsbactngoaiDdThuocla() == true)
				params.put("THUOCLA", "X");
			else
				params.put("THUOCLA", "");
			if (hsbaCTNgoai.getHsbactngoaiDdThuoclao() != null && hsbaCTNgoai.getHsbactngoaiDdThuoclao() == true)
				params.put("THUOCLAO", "X");
			else
				params.put("THUOCLAO", "");
			if (hsbaCTNgoai.getHsbactngoaiDdKhac() != null && hsbaCTNgoai.getHsbactngoaiDdKhac() == true)
				params.put("KHAC", "X");
			else
				params.put("KHAC", "");

			// *** THOI GIAN
			if (hsbaCTNgoai.getHsbactngoaiDdDiungTg() != null)
				params.put("DIUNGTG", hsbaCTNgoai.getHsbactngoaiDdDiungTg());
			if (hsbaCTNgoai.getHsbactngoaiDdMatuyTg() != null)
				params.put("MATUYTG", hsbaCTNgoai.getHsbactngoaiDdMatuyTg());
			if (hsbaCTNgoai.getHsbactngoaiDdRuoubiaTg() != null)
				params.put("RUOUBIATG", hsbaCTNgoai.getHsbactngoaiDdRuoubiaTg());
			if (hsbaCTNgoai.getHsbactngoaiDdThuoclaTg() != null)
				params.put("THUOCLATG", hsbaCTNgoai.getHsbactngoaiDdThuoclaTg());
			if (hsbaCTNgoai.getHsbactngoaiDdThuoclaoTg() != null)
				params.put("THUOCLAOTG", hsbaCTNgoai.getHsbactngoaiDdThuoclaoTg());
			if (hsbaCTNgoai.getHsbactngoaiDdKhacTg() != null)
				params.put("KHACTG", hsbaCTNgoai.getHsbactngoaiDdKhacTg());

			
			if (hsbaCTNgoai.getHsbactngoaiToanthan() != null)
				params.put("TOANTHAN", hsbaCTNgoai.getHsbactngoaiToanthan());
			if (hsbaCTNgoai.getHsbactngoaiTuanhoan() != null)
				params.put("TUANHOAN", hsbaCTNgoai.getHsbactngoaiTuanhoan());
			if (hsbaCTNgoai.getHsbactngoaiHohap() != null)
				params.put("HOHAP", hsbaCTNgoai.getHsbactngoaiHohap());
			if (hsbaCTNgoai.getHsbactngoaiTieuhoa() != null)
				params.put("TIEUHOA", hsbaCTNgoai.getHsbactngoaiTieuhoa());
			
			// MACH, NHIP THO, CAN NANG
			if (hsbaChuyenMon.getHsbacmHamax() != null && hsbaChuyenMon.getHsbacmHamin() != null)
				params.put("HUYETAP", hsbaChuyenMon.getHsbacmHamax() + " / " + hsbaChuyenMon.getHsbacmHamin());

			
			// *****PAGE 3*******//

			if (hsbaCTNgoai.getHsbactngoaiThantietnieusinhhoc() != null)
				params.put("THANTNSD", hsbaCTNgoai.getHsbactngoaiThantietnieusinhhoc());
			if (hsbaCTNgoai.getHsbactngoaiThankinh() != null)
				params.put("THANKINH", hsbaCTNgoai.getHsbactngoaiThankinh());
			if (hsbaCTNgoai.getHsbactngoaiCoxuongkhop() != null)
				params.put("COXUONGKHOP", hsbaCTNgoai.getHsbactngoaiCoxuongkhop());
			if (hsbaCTNgoai.getHsbactngoaiTmh() != null)
				params.put("TMH", hsbaCTNgoai.getHsbactngoaiTmh());
			if (hsbaCTNgoai.getHsbactngoaiRhm() != null)
				params.put("RHM", hsbaCTNgoai.getHsbactngoaiRhm());
			if (hsbaCTNgoai.getHsbactngoaiMat() != null)
				params.put("MAT", hsbaCTNgoai.getHsbactngoaiMat());
			if (hsbaCTNgoai.getHsbactngoaiNtDdBlk() != null)
				params.put("NTDDBLK", hsbaCTNgoai.getHsbactngoaiNtDdBlk());
			if (hsbaCTNgoai.getHsbactngoaiTtba() != null)
				params.put("TTBA", hsbaCTNgoai.getHsbactngoaiTtba());
			if (hsbaChuyenMon.getHsbacmHuongdieutri() != null){
				params.put("HUONGDT", hsbaChuyenMon.getHsbacmHuongdieutri());
			}
			
			if (hsbaCTNgoai.getHsbactngoaiBSlamba() != null)
				params.put("BASILAMBA", hsbaCTNgoai.getHsbactngoaiBSlamba(true).getDtdmnhanvienTen());

			
			// *****PAGE 4*******//
			
			if (hsbaCTNgoai.getHsbactngoaiQtblDbls() != null)
				params.put("QTBLDBLS", hsbaCTNgoai.getHsbactngoaiQtblDbls());
			if (hsbaCTNgoai.getHsbactngoaiTtkqxncls() != null)
				params.put("TTKQCLSCD", hsbaCTNgoai.getHsbactngoaiTtkqxncls());
			if (hsbaCTNgoai.getHsbactngoaiPpdieutri() != null)
				params.put("PPDT", hsbaCTNgoai.getHsbactngoaiPpdieutri());
			if (hsbaCTNgoai.getHsbactngoaiTtnguoibenhrav() != null)
				params.put("TTNBRV", hsbaCTNgoai.getHsbactngoaiTtnguoibenhrav());
			if (hsbaCTNgoai.getHsbactngoaiHuongdtCdtt() != null)
				params.put("HDTCCDTT", hsbaCTNgoai.getHsbactngoaiHuongdtCdtt());
			
			if (hsbaCTNgoai.getHsbactngoaiSotoxquang() != null)
				params.put("XQUANG", hsbaCTNgoai.getHsbactngoaiSotoxquang());
			if (hsbaCTNgoai.getHsbactngoaiSotoctscanner() != null)
				params.put("SCANNER", hsbaCTNgoai.getHsbactngoaiSotoctscanner());
			if (hsbaCTNgoai.getHsbactngoaiSotosieuam() != null)
				params.put("SIEUAM", hsbaCTNgoai.getHsbactngoaiSotosieuam());
			if (hsbaCTNgoai.getHsbactngoaiSotoxn() != null)
				params.put("XETNGHIEM", hsbaCTNgoai.getHsbactngoaiSotoxn());
			if (hsbaCTNgoai.getHsbactngoaiSotokhac() != null)
				params.put("HSKHAC", hsbaCTNgoai.getHsbactngoaiSotokhac());
			if (hsbaCTNgoai.getHsbactngoaiTongsoto() != null)
				params.put("TONGHOSO", hsbaCTNgoai.getHsbactngoaiTongsoto());
			
			if (hsbaCTNgoai.getHsbactngoaiNguoigiaoba() != null)
				params.put("NGUOIGIAOHS", hsbaCTNgoai.getHsbactngoaiNguoigiaoba(true).getDtdmnhanvienTen());
			if (hsbaCTNgoai.getHsbactngoaiNguoinhanba() != null)
				params.put("NGUOINHANHS", hsbaCTNgoai.getHsbactngoaiNguoinhanba(true).getDtdmnhanvienTen());
			if (hsbaCTNgoai.getHsbactngoaiBSdieutri() != null)
				params.put("BASIDT", hsbaCTNgoai.getHsbactngoaiBSdieutri(true).getDtdmnhanvienTen());

			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			log.info("da thay driver mysql");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintDT, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "CapNhatThongTinChiTietBANgoai");
			duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + duongdanStrDT);
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
		HsbaChuyenVienDelegate hsbaChuyenVienDel = HsbaChuyenVienDelegate.getInstance();
		HsbaChuyenVien objhsbaChuyenVien = hsbaChuyenVienDel.findBySoVaoVien(soBenhAn);
		return objhsbaChuyenVien;
	}

	private static final long ONE_HOUR = 60 * 60 * 1000L;

	private static long daysBetween(Date d1, Date d2) {
		return ( ((d2.getTime() - d1.getTime()) / (ONE_HOUR * 24)) + 1 );
	}

	public HsbaChiTietNgoai getHsbaCTNgoai() {
		return hsbaCTNgoai;
	}

	public void setHsbaCTNgoai(HsbaChiTietNgoai hsbaCTNgoai) {
		this.hsbaCTNgoai = hsbaCTNgoai;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}


}
