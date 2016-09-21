/*
 * author : Ly
 */
package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
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
import com.iesvn.yte.dieutri.delegate.HsbaChiTietPhukhoaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaPhieuPhauThuatThuThuatDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChiTietPhukhoa;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B211_3_CapNhatThongTinPhuKhoa")
@Synchronized(timeout = 600000)
public class CapNhatThongTinChiTietBAPhuKhoaAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(CapNhatThongTinChiTietBAPhuKhoaAction.class);

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
	private HsbaChiTietPhukhoa hsbaCTPhukhoa;
	private Hsba hosobenhan;
	private TiepDon tiepDon;
	private BenhNhan benhnhan;
	
	
	private String kinhlancuoingay;
	private int index = 0;

	@Create
	@Begin(nested = true)
	public String init() throws Exception {
		log.info("*** Starting init BA Phu khoa ***");
		
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

				HsbaChiTietPhukhoa hsbaCTPhukhoaTemp = null;
				try {
					hsbaCTPhukhoaTemp = HsbaChiTietPhukhoaDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
				} catch (Exception e) {
					log.info("error:" + e);
				}
				
				if (hsbaCTPhukhoaTemp != null) {
					hsbaCTPhukhoa = hsbaCTPhukhoaTemp;
					
					SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
					if(hsbaCTPhukhoa.getHsbactphukhoaKinhlancuoingay() != null){
						kinhlancuoingay = sdf.format(hsbaCTPhukhoa.getHsbactphukhoaKinhlancuoingay());
					}
				} else {
					hsbaCTPhukhoa = new HsbaChiTietPhukhoa();
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
		return "DieuTri_CapNhat_CapNhatThongTinChiTietBAPhukhoa";
	}

	@End
	public String back() throws Exception {
		log.info("***Starting back ***");
		log.info("***Finished back **");
		return "DieuTri_CapNhat_CapNhatThongTinChung";
	}

	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() {
		log.info("***Starting ghinhan Chi tiet BA Phu khoa ***");
		ghiNhanException = null;
		
		hsbaCTPhukhoa.setHsbacmMa(hsbaChuyenMon);
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
		
		try {
			hsbaCTPhukhoa.setHsbactphukhoaKinhlancuoingay(sdf.parse(kinhlancuoingay));
		} catch (ParseException e) {}
		
		RemoveUtil.removeAllNullFromHSBACTPhukhoa(hsbaCTPhukhoa);
		
		if (hsbaCTPhukhoa.getHsbactphukhoaMa() == null) {
			HsbaChiTietPhukhoaDelegate.getInstance().create(hsbaCTPhukhoa);
		} else {
			HsbaChiTietPhukhoaDelegate.getInstance().edit(hsbaCTPhukhoa);
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
		loaiBCDT = "CapNhatThongTinChiTietBAPhukhoa";
		log.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an phukhoa");
		String baocao1 = null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanphukhoa.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanphukhoa_sub1.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanphukhoa_sub2.jrxml";
			String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanphukhoa_sub3.jrxml";
			String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanphukhoa_sub4.jrxml";

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

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI",IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("GIAMDOCBENHVIEN", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			
			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			params.put("sub3", sub3Report);
			params.put("sub4", sub4Report);

//			String sHoTenBN = "";
//			String sNgaySinh = "";
//			String sPhukhoaLamViec = "";
//			String sDanToc = "";
//			String sNgoaiKieu = "";
//			String sKhiCanBaoTin = "";
//			String sVaoVienLuc = "";
//			String sVaoKhoa = "";
//			String sVaoKhoaLuc = "";
//			String sDonViGoi = "";
//			String sBenhChinh = "";
//			String sBenhKemTheo = "";
//			String sKetQua = "";
//			String sNguyenNhanTuVong = "";
//			String sNgayGioTuVong = "";
//			String sDoiTuong = "";
//			String sNgheNghiep = "";
//			
//			Date dVaoVien = new Date();
//			Date dRaVien = new Date();

			HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();
			//HsbaChuyenMon objHSBAChuyenMon = hsbadel.findBySoVaoVien_MaKhoa(soBenhAn, khoaMa);
			
			HsbaChiTietPhukhoa hsbaCTPhukhoaTemp = null;
			try {
				hsbaCTPhukhoaTemp = HsbaChiTietPhukhoaDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
			} catch (Exception e) {
				log.info("error Xuat Report HsbaChiTietPhukhoaDelegate.getInstance().findByHsbaCM: " + e);
			}
			
			if (hsbaCTPhukhoaTemp != null) {
				hsbaCTPhukhoa = hsbaCTPhukhoaTemp;
			}
			
			
			// ============================ bao.ttc: thong tin Hanh Chinh ============================
			
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
			
			String sDanToc = "";
			if (benhnhan.getDantocMa(true).getDmdantocTen() != null){
				sDanToc = benhnhan.getDantocMa(true).getDmdantocTen();
				params.put("DANTOC", sDanToc);
				params.put("DANTOCMA", benhnhan.getDantocMa(true).getDmdantocMa());
			}
			
			String sDoiTuong = "";
			if (hosobenhan.getDoituongMa(true).getDmdoituongMa() != null) {
				sDoiTuong = hosobenhan.getDoituongMa(true).getDmdoituongTen();
			}
			params.put("DOITUONG", sDoiTuong);
			
			String sNgheNghiep = "";
			if (benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen() != null){
				sNgheNghiep = benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen();
				params.put("NGHENGHIEP", sNgheNghiep);
				params.put("NGHENGHIEPMA", benhnhan.getBenhnhanNghe(true).getDmnghenghiepMa());
			}
			
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
			params.put("CHUANDOAN_CAPCUU_MA", maChandoanCC);
			params.put("CHUANDOAN_CAPCUU", tenChandoanCC);
			
			// Chan doan Vao Khoa
			String maChuanDoanVaoKhoa = "";
			String tenChuanDoanVaoKhoa = "";
			if (hsbaChuyenMon.getHsbacmBenhchinh() != null) {
				maChuanDoanVaoKhoa = hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa();
				tenChuanDoanVaoKhoa = hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
			}
			params.put("CHUANDOAN_VAOKHOA_MA", maChuanDoanVaoKhoa);
			params.put("CHUANDOAN_VAOKHOA", tenChuanDoanVaoKhoa);
			
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
			
			
			params.put("MACH", tiepDon.getTiepdonMach());
			params.put("NHIETDO", tiepDon.getTiepdonNhietdo());
			params.put("HUYETAPMAX", tiepDon.getTiepdonHamax());
			params.put("HUYETAPMIN", tiepDon.getTiepdonHamin());
			params.put("NHIPTHO", tiepDon.getTiepdonNhiptho());
			params.put("CANNANG", tiepDon.getTiepdonTrluong());
			
			
			if(hsbaCTPhukhoa.getHsbactphukhoaSinhduthang() != null)
				params.put("SINHDUTHANG", hsbaCTPhukhoa.getHsbactphukhoaSinhduthang());
			if(hsbaCTPhukhoa.getHsbactphukhoaSinhdenon() != null)
				params.put("SOMDENON", hsbaCTPhukhoa.getHsbactphukhoaSinhdenon());
			if(hsbaCTPhukhoa.getHsbactphukhoaSinhnaohut() != null)
				params.put("SAYNAOHUT", hsbaCTPhukhoa.getHsbactphukhoaSinhnaohut());
			if(hsbaCTPhukhoa.getHsbactphukhoaSinhsong() != null)
				params.put("SONG", hsbaCTPhukhoa.getHsbactphukhoaSinhsong());
			
			if (hsbaCTPhukhoa.getHsbactphukhoaLydovaov() != null)
				params.put("LYDOVAOVIEN", hsbaCTPhukhoa.getHsbactphukhoaLydovaov());
			if (hsbaCTPhukhoa.getHsbactphukhoaQtbenhly() != null)
				params.put("HOIBENH", hsbaCTPhukhoa.getHsbactphukhoaQtbenhly());
			if (hsbaCTPhukhoa.getHsbactphukhoaQtbenhly() != null)
				params.put("QTBENHLY", hsbaCTPhukhoa.getHsbactphukhoaQtbenhly());
			if (hsbaCTPhukhoa.getHsbactphukhoaTiensubenhbt() != null)
				params.put("TSBBANTHAN", hsbaCTPhukhoa.getHsbactphukhoaTiensubenhbt());
			if (hsbaCTPhukhoa.getHsbactphukhoaTiensubenhgd() != null)
				params.put("GIADINH", hsbaCTPhukhoa.getHsbactphukhoaTiensubenhgd());
			
			params.put("THAYKINHNAM", hsbaCTPhukhoa.getHsbactphukhoaThaykinhnam());
			params.put("THAYKINHTUOI", hsbaCTPhukhoa.getHsbactphukhoaThaykinhtuoi());
			params.put("TINHCHATKINHNGUYET", hsbaCTPhukhoa.getHsbactphukhoaTinhchatkinhnguyet());
			params.put("CHUKY", hsbaCTPhukhoa.getHsbactphukhoaChukykinh());
			params.put("SONGAYTHAYKINH", hsbaCTPhukhoa.getHsbactphukhoaSongaythaykinh());
			params.put("LUONGKINH", hsbaCTPhukhoa.getHsbactphukhoaLuongkinh());
			
			if(hsbaCTPhukhoa.getHsbactphukhoaKinhlancuoingay() != null)
				params.put("KINHLANCUOINGAY", sdf.format(hsbaCTPhukhoa.getHsbactphukhoaKinhlancuoingay()));
			if(hsbaCTPhukhoa.getHsbactphukhoaCodaubung() != null && hsbaCTPhukhoa.getHsbactphukhoaCodaubung() == true)
				params.put("CODAUBUNG", "X");
			if(hsbaCTPhukhoa.getHsbactphukhoaThoigiantruoc() != null && hsbaCTPhukhoa.getHsbactphukhoaThoigiantruoc() == true)
				params.put("TGTRUOC", "X");
			if(hsbaCTPhukhoa.getHsbactphukhoaThoigiantrong() != null && hsbaCTPhukhoa.getHsbactphukhoaThoigiantrong() == true)
				params.put("TGTRONG", "X");
			if(hsbaCTPhukhoa.getHsbactphukhoaThoigiansau() != null && hsbaCTPhukhoa.getHsbactphukhoaThoigiansau() == true)
				params.put("TGSAU", "X");
			
			params.put("LAYCHONGNAM", hsbaCTPhukhoa.getHsbactphukhoaLaychongnam());
			params.put("LAYCHONGTUOI", hsbaCTPhukhoa.getHsbactphukhoaLaychongtuoi());
			params.put("HETKINHNAM", hsbaCTPhukhoa.getHsbactphukhoaHetkinhnam());
			params.put("HETKINHTUOI", hsbaCTPhukhoa.getHsbactphukhoaKetkinhtuoi());
			params.put("BENHPHUKHOADADIEUTRI", hsbaCTPhukhoa.getHsbactphukhoaBenhphukhoadadieutri());
			params.put("DANIEMMAC", hsbaCTPhukhoa.getHsbactphukhoaDaniemmac());
			params.put("HACH", hsbaCTPhukhoa.getHsbactphukhoaHach());
			params.put("VU", hsbaCTPhukhoa.getHsbactphukhoaVu());
			params.put("TUANHOAN", hsbaCTPhukhoa.getHsbactphukhoaTuanhoan());
			params.put("HOHAP", hsbaCTPhukhoa.getHsbactphukhoaHohap());			
			
			
			// *****PAGE 3*******//
			params.put("TIEUHOA", hsbaCTPhukhoa.getHsbactphukhoaTieuhoa());
			params.put("THANKINH", hsbaCTPhukhoa.getHsbactphukhoaThankinh());			
			params.put("COXUONGKHOP", hsbaCTPhukhoa.getHsbactphukhoaCoxuongkhop());
			params.put("THANTIETNIEU", hsbaCTPhukhoa.getHsbactphukhoaThantietnieusinhhoc());
			params.put("COQUANKHAC", hsbaCTPhukhoa.getHsbactphukhoaCoquankhac());
			params.put("DAUHIEUSINHDUCTHUPHAT", hsbaCTPhukhoa.getHsbactphukhoaDauhieusinhducthuphat());
			params.put("MOILON", hsbaCTPhukhoa.getHsbactphukhoaMoilon());
			params.put("MOIBE", hsbaCTPhukhoa.getHsbactphukhoaMoibe());
			params.put("AMVAT", hsbaCTPhukhoa.getHsbactphukhoaAmvat());
			params.put("AMHO", hsbaCTPhukhoa.getHsbactphukhoaAmho());
			params.put("MANTRINH", hsbaCTPhukhoa.getHsbactphukhoaMantrinh());
			params.put("TANGSINHMON", hsbaCTPhukhoa.getHsbactphukhoaTangsinhmon());
			params.put("AMDAO", hsbaCTPhukhoa.getHsbactphukhoaAmdao());
			params.put("COTUCUNG", hsbaCTPhukhoa.getHsbactphukhoaCotucung());
			params.put("THANTUCUNG", hsbaCTPhukhoa.getHsbactphukhoaThantucung());
			params.put("PHANPHU", hsbaCTPhukhoa.getHsbactphukhoaPhanphu());
			params.put("CACTUICUNG", hsbaCTPhukhoa.getHsbactphukhoaCactuicung());
			params.put("TOMTATBENHAN", hsbaCTPhukhoa.getHsbactphukhoaTtba());
			params.put("XETNGHIEMCLS", hsbaCTPhukhoa.getHsbactphukhoaXetnghiemcanlam());
			
			// *****PAGE 4 *******//
			params.put("TIENLUONG", hsbaCTPhukhoa.getHsbactphukhoaTienluong());
			params.put("HUONGDIEUTRI", hsbaCTPhukhoa.getHsbactphukhoaHuongdieutri());
			
			
			if (hsbaCTPhukhoa.getHsbactphukhoaQtblDbls() != null)
				params.put("QTBLDBLS", hsbaCTPhukhoa.getHsbactphukhoaQtblDbls());
			if (hsbaCTPhukhoa.getHsbactphukhoaTtkqxncls() != null)
				params.put("TTKQCLSCD", hsbaCTPhukhoa.getHsbactphukhoaTtkqxncls());
			if (hsbaCTPhukhoa.getHsbactphukhoaPpdieutri() != null)
				params.put("PPDT", hsbaCTPhukhoa.getHsbactphukhoaPpdieutri());
			if (hsbaCTPhukhoa.getHsbactphukhoaTtnguoibenhrav() != null)
				params.put("TTNBRV", hsbaCTPhukhoa.getHsbactphukhoaTtnguoibenhrav());
			if (hsbaCTPhukhoa.getHsbactphukhoaHuongdtCdtt() != null)
				params.put("HDTCCDTT", hsbaCTPhukhoa.getHsbactphukhoaHuongdtCdtt());
			if (hsbaCTPhukhoa.getHsbactphukhoaSotoxquang() != null)
				params.put("XQUANG", hsbaCTPhukhoa.getHsbactphukhoaSotoxquang());
			if (hsbaCTPhukhoa.getHsbactphukhoaSotoctscanner() != null)
				params.put("SCANNER", hsbaCTPhukhoa.getHsbactphukhoaSotoctscanner());
			if (hsbaCTPhukhoa.getHsbactphukhoaSotosieuam() != null)
				params.put("SIEUAM", hsbaCTPhukhoa.getHsbactphukhoaSotosieuam());
			if (hsbaCTPhukhoa.getHsbactphukhoaSotoxn() != null)
				params.put("XETNGHIEM", hsbaCTPhukhoa.getHsbactphukhoaSotoxn());
			if (hsbaCTPhukhoa.getHsbactphukhoaSotokhac() != null)
				params.put("HSKHAC", hsbaCTPhukhoa.getHsbactphukhoaSotokhac());
			if (hsbaCTPhukhoa.getHsbactphukhoaTongsoto() != null)
				params.put("TONGHOSO", hsbaCTPhukhoa.getHsbactphukhoaTongsoto());
			
			if (hsbaCTPhukhoa.getHsbactphukhoaBslamba() != null)
				params.put("BACSILAMBA", hsbaCTPhukhoa.getHsbactphukhoaBslamba(true).getDtdmnhanvienTen());
			if (hsbaCTPhukhoa.getHsbactphukhoaNguoigiaoba() != null)
				params.put("NGUOIGIAOBA", hsbaCTPhukhoa.getHsbactphukhoaNguoigiaoba(true).getDtdmnhanvienTen());
			if (hsbaCTPhukhoa.getHsbactphukhoaNguoinhanba() != null)
				params.put("NGUOINHANBA", hsbaCTPhukhoa.getHsbactphukhoaNguoinhanba(true).getDtdmnhanvienTen());
			if (hsbaCTPhukhoa.getHsbactphukhoaBsdieutri() != null)
				params.put("BASIDT", hsbaCTPhukhoa.getHsbactphukhoaBsdieutri(true).getDtdmnhanvienTen());

			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			log.info("da thay driver mysql");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
						IConstantsRes.DATABASE_USER,
						IConstantsRes.DATABASE_PASS);
				log.info("Da connect DATABASE");
				jasperPrintDT = JasperFillManager.fillReport(jasperReport, params,conn);
				log.info("fillReport THANHCONG");
			} catch (Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
			}
			
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintDT, index, IConstantsRes.PATH_BASE
							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
							+ "hsba/", "pdf", "capNhatThongTinChiTietBAPhukhoa");
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

	public HsbaChiTietPhukhoa getHsbaCTPhukhoa() {
		return hsbaCTPhukhoa;
	}

	public void setHsbaCTPhukhoa(HsbaChiTietPhukhoa hsbaCTPhukhoa) {
		this.hsbaCTPhukhoa = hsbaCTPhukhoa;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getKinhlancuoingay() {
		return kinhlancuoingay;
	}

	public void setKinhlancuoingay(String kinhlancuoingay) {
		this.kinhlancuoingay = kinhlancuoingay;
	}
	
}
