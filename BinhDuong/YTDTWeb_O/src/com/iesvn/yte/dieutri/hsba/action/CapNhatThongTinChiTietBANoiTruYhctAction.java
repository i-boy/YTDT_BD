/*
 * author : Ly
 */
package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.*;

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
import com.iesvn.yte.dieutri.delegate.HsbaChiTietNoitruYhctDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChiTietNoitruYhct;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B211_3_CapNhatThongTinNoiTruYhct")
@Synchronized(timeout = 600000)
public class CapNhatThongTinChiTietBANoiTruYhctAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(CapNhatThongTinChiTietBANoiTruYhctAction.class);

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
	private HsbaChiTietNoitruYhct hsbaCTNoitruYhct;
	private Hsba hosobenhan;
	private TiepDon tiepDon;
	private BenhNhan benhnhan;
	
	private int index = 0;

	@Create
	@Begin(nested = true)
	public String init() throws Exception {
		log.info("*** Starting init Chi tiet BA Noi tru YHCT ***");

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
				
				HsbaChiTietNoitruYhct hsbaCTNoitruYhctTemp = null;
				try {
					hsbaCTNoitruYhctTemp = HsbaChiTietNoitruYhctDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
				} catch (Exception e) {
					log.info("error:" + e);
				}
				
				if (hsbaCTNoitruYhctTemp != null) {
					hsbaCTNoitruYhct = hsbaCTNoitruYhctTemp;
				} else {
					hsbaCTNoitruYhct = new HsbaChiTietNoitruYhct();
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

		log.info("*** Finished init **");
		return "DieuTri_CapNhat_CapNhatThongTinChiTietBANoitruYhct";
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
		log.info("***Starting ghinhan **");
		ghiNhanException = null;
		hsbaCTNoitruYhct.setHsbacmMa(hsbaChuyenMon);
		RemoveUtil.removeAllNullFromHSBACTNoitruYhct(hsbaCTNoitruYhct);
		if (hsbaCTNoitruYhct.getHsbactnoitruYhctMa() == null) {
			HsbaChiTietNoitruYhctDelegate.getInstance().create(hsbaCTNoitruYhct);
		} else {
			HsbaChiTietNoitruYhctDelegate.getInstance().edit(hsbaCTNoitruYhct);
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
		loaiBCDT = "CapNhatThongTinChiTietBANoitruYhct";
		log.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an noitruYhct");
		String baocao1 = null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport0.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport1.jrxml";
			String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport2.jrxml";
			String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport3.jrxml";
			String sub5Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport4.jrxml";
			String sub6Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport5.jrxml";
			String sub7Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport6.jrxml";
			log.info("da thay file templete " + pathTemplate);

			JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
			JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
			JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);
			JasperReport sub4Report = JasperCompileManager.compileReport(sub4Template);
			JasperReport sub5Report = JasperCompileManager.compileReport(sub5Template);
			JasperReport sub6Report = JasperCompileManager.compileReport(sub6Template);
			JasperReport sub7Report = JasperCompileManager.compileReport(sub7Template);

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
			params.put("sub5", sub5Report);
			params.put("sub6", sub6Report);
			params.put("sub7", sub7Report);


			HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();
			
			HsbaChiTietNoitruYhct hsbaCTNoitruYhctTemp = null;
			try {
				hsbaCTNoitruYhctTemp = HsbaChiTietNoitruYhctDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
			} catch (Exception e) {
				log.info("error Xuat Report HsbaChiTietNoitruYhctDelegate.getInstance().findByHsbaCM: " + e);
			}
			
			if (hsbaCTNoitruYhctTemp != null) {
				hsbaCTNoitruYhct = hsbaCTNoitruYhctTemp;
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
			}
			params.put("NGHENGHIEP", sNgheNghiep);
			
			
			String sDanToc = "";
			if (benhnhan.getDantocMa(true).getDmdantocTen() != null){
				sDanToc = benhnhan.getDantocMa(true).getDmdantocTen();
			}
			params.put("DANTOC", sDanToc);
			
			
			String sDoiTuong = "";
			if (hosobenhan.getDoituongMa(true).getDmdoituongTen() != null){
				sDoiTuong = hosobenhan.getDoituongMa(true).getDmdoituongTen();
			}
			params.put("DOITUONG", sDoiTuong);
			
			String sNgoaiKieu = "";
			if (benhnhan.getQuocgiaMa(true).getDmquocgiaTen() != null){
				sNgoaiKieu = benhnhan.getQuocgiaMa(true).getDmquocgiaTen();
			}
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
			
			String sVaoKhoa = "";
			if (hosobenhan.getHsbaKhoavaov() != null)
				sVaoKhoa = hosobenhan.getHsbaKhoavaov(true).getDmkhoaMa();
			params.put("VAOKHOA", sVaoKhoa); // -------------------------------------
			
			String sVaoKhoaLuc = "";
			if (hsbaChuyenMon.getHsbacmNgaygiovaok() != null){
				sVaoKhoaLuc = Utils.getGioPhutNgay(hsbaChuyenMon.getHsbacmNgaygiovaok());
				params.put("VAOKHOALUC", sVaoKhoaLuc);
			}
			
			List<HsbaChuyenMon> listHSBAChuyenKhoa = hsbadel.findBySoVaoVien(soBenhAn);
			if (listHSBAChuyenKhoa != null && listHSBAChuyenKhoa.size() > 1) {
				for (int i = 1; i < listHSBAChuyenKhoa.size() && i <= 3; i++) {
					String makhoa = "";
					String ngaygiovaok = "";
					int stt = i - 1;
					if (listHSBAChuyenKhoa.get(i).getKhoaMa() != null){
						makhoa = listHSBAChuyenKhoa.get(i).getKhoaMa(true).getDmkhoaMa();
						params.put("CHUYENKHOA_" + stt, makhoa); // -------------------------------------
					}
					if (listHSBAChuyenKhoa.get(i).getHsbacmNgaygiovaok() != null){
						ngaygiovaok = Utils.getGioPhutNgay(listHSBAChuyenKhoa.get(i).getHsbacmNgaygiovaok());
						params.put("CHUYENKHOALUC_" + stt, ngaygiovaok);
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

			
			// Chan doan tuyen truoc
			String tenTuyenduoi = "";
			if ( hosobenhan.getHsbaMachdoantuyent() != null ) {
				tenTuyenduoi = hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdTen();
				params.put("CHUANDOAN_NOICHUYENDEN_MA", hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdMa());
			}
			params.put("CHUANDOAN_NOICHUYENDEN", tenTuyenduoi);
	
			// Chan doan Cap cuu, Khoa Kham benh
			String tenChandoanCC = "";
			if ( hosobenhan.getHsbaMachdoanbd() != null ) {
				tenChandoanCC = hosobenhan.getHsbaMachdoanbd(true).getDmbenhicdTen();
				params.put("CHUANDOAN_CAPCUU_MA", hosobenhan.getHsbaMachdoanbd(true).getDmbenhicdMa());
			}
			params.put("CHUANDOAN_CAPCUU", tenChandoanCC);
			
			if (hosobenhan.getHsbaMachdravien() != null) {
				params.put("CHUANDOAN_RAVIEN", hosobenhan.getHsbaMachdravien(true).getDmbenhicdTen());
				params.put("CHUANDOAN_RAVIEN_MA", hosobenhan.getHsbaMachdravien(true).getDmbenhicdMa());
			}

			// Chan doan Vao Khoa
			String tenChuanDoanVaoKhoa = "";
			if (hsbaChuyenMon.getHsbacmBenhchinh() != null) {
				tenChuanDoanVaoKhoa = hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
			}
			params.put("CHUANDOAN_VAOKHOA", tenChuanDoanVaoKhoa);
			
			String sBenhChinh = "";
			String sBenhKemTheo = "";
			if (hsbaChuyenMon.getHsbacmBenhchinh() != null) {
				sBenhChinh = hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
				params.put("BENHCHINH_MA", hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa());
			}
			if (hsbaChuyenMon.getHsbacmBenhphu() != null) {
				sBenhKemTheo = hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
				params.put("BENHPHU_MA", hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdMa());
			}
			params.put("BENHCHINH", sBenhChinh);
			params.put("BENHPHU", sBenhKemTheo);
			
			String sKetQua = "";
			if (hsbaChuyenMon.getKetquaMa(true).getDmkqdtTen() != null)
				sKetQua = hsbaChuyenMon.getKetquaMa(true).getDmkqdtTen();
			params.put("KETQUA", sKetQua);
			
			params.put("MACH", tiepDon.getTiepdonMach());
			params.put("NHIETDO", tiepDon.getTiepdonNhietdo());
			params.put("HUYETAPMAX", tiepDon.getTiepdonHamax());
			params.put("HUYETAPMIN", tiepDon.getTiepdonHamin());
			params.put("NHIPTHO", tiepDon.getTiepdonNhiptho());
			params.put("CANNANG", tiepDon.getTiepdonTrluong());
			
			params.put("YHCT_KHOAKHAMBENH", hsbaChuyenMon.getKhoaMa(true).getDmkhoaTen());
			params.put("CHANDOANBATCUONG", hsbaCTNoitruYhct.getHsbactnoitruYhctChandoanbatchuong());
			params.put("CHANDOANNGUYENNHAN", hsbaCTNoitruYhct.getHsbactnoitruYhctChandoannguyennhan());
			
			if (hsbaCTNoitruYhct.getHsbactnoitruYhctBsdieutri() != null)
				params.put("BACSIDT", hsbaCTNoitruYhct.getHsbactnoitruYhctBsdieutri(true).getDtdmnhanvienTen());
			
			
			// *************PAGE 2************//

			if (hsbaCTNoitruYhct.getHsbactnoitruYhctLydovaov() != null)
				params.put("LYDOVAOVIEN", hsbaCTNoitruYhct.getHsbactnoitruYhctLydovaov());
			if (hsbaCTNoitruYhct.getHsbactnoitruYhctQtbenhly() != null)
				params.put("QUATRINHBENHLY", hsbaCTNoitruYhct.getHsbactnoitruYhctQtbenhly());
			if (hsbaCTNoitruYhct.getHsbactnoitruYhctTiensubenhbt() != null)
				params.put("TIEUSUBENHBANTHAN", hsbaCTNoitruYhct.getHsbactnoitruYhctTiensubenhbt());
			if (hsbaCTNoitruYhct.getHsbactnoitruYhctTiensubenhgd() != null)
				params.put("TIEUSUBENHGIADINH", hsbaCTNoitruYhct.getHsbactnoitruYhctTiensubenhgd());
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctThuocla() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctThuocla() == true)
				params.put("THUOCLA", "X");
			else params.put("THUOCLA", "");
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctRuou() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctRuou() == true)
				params.put("RUOU", "X");
			else params.put("RUOU", "");
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctMatuy() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctMatuy() == true)
				params.put("MATUY", "X");
			else params.put("MATUY", "");
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctDiung() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctDiung() == true)
				params.put("DIUNG", "X");
			else params.put("DIUNG", "");
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctKhac() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctKhac() == true)
				params.put("KHAC", "X");
			else params.put("KHAC", "");
			params.put("NOITIET", hsbaCTNoitruYhct.getHsbactnoitruYhctNoitiet());
			params.put("DINHDUONG", hsbaCTNoitruYhct.getHsbactnoitruYhctDinhduong());
			params.put("THANKINH", hsbaCTNoitruYhct.getHsbactnoitruYhctThankinh());
			params.put("MAT", hsbaCTNoitruYhct.getHsbactnoitruYhctMat());
			params.put("TMH", hsbaCTNoitruYhct.getHsbactnoitruYhctTmh());
			params.put("RHM", hsbaCTNoitruYhct.getHsbactnoitruYhctRhm());
			params.put("TUANHOAN", hsbaCTNoitruYhct.getHsbactnoitruYhctTuanhoan());
			params.put("HOHAP", hsbaCTNoitruYhct.getHsbactnoitruYhctHohap());
			params.put("TIEUHOA", hsbaCTNoitruYhct.getHsbactnoitruYhctTieuhoa());
			params.put("DAVAMO", hsbaCTNoitruYhct.getHsbactnoitruYhctDavamo());
			params.put("COXUONGKHOP", hsbaCTNoitruYhct.getHsbactnoitruYhctCoxuongkhop());
			params.put("TIETNIEU", hsbaCTNoitruYhct.getHsbactnoitruYhctTietnieu());
			params.put("SINHDUC", hsbaCTNoitruYhct.getHsbactnoitruYhctSinhduc());
			params.put("COQUANKHAC", hsbaCTNoitruYhct.getHsbactnoitruYhctCoquankhac());
			params.put("CHITIETBENHLY", hsbaCTNoitruYhct.getHsbactnoitruYhctChitietbenhly());
			params.put("HUYETHOC", hsbaCTNoitruYhct.getHsbactnoitruYhctHuyethoc());
			params.put("HUYETHOCKQ", hsbaCTNoitruYhct.getHsbactnoitruYhctHuyethockq());
			params.put("HOASINH", hsbaCTNoitruYhct.getHsbactnoitruYhctHoasinh());
			params.put("HOASINHKQ", hsbaCTNoitruYhct.getHsbactnoitruYhctHoasinhkq());
			params.put("VISINH", hsbaCTNoitruYhct.getHsbactnoitruYhctVisinh());
			params.put("VISINHKQ", hsbaCTNoitruYhct.getHsbactnoitruYhctVisinhkq());
			params.put("XQUANG", hsbaCTNoitruYhct.getHsbactnoitruYhctXquang());
			params.put("XQUANGKQ", hsbaCTNoitruYhct.getHsbactnoitruYhctXquangkq());
			params.put("SIEUAM", hsbaCTNoitruYhct.getHsbactnoitruYhctSieuam());
			params.put("SIEUAMKQ", hsbaCTNoitruYhct.getHsbactnoitruYhctSieuamkq());
			params.put("DIENTIM", hsbaCTNoitruYhct.getHsbactnoitruYhctDientim());
			params.put("DIENTIMKQ", hsbaCTNoitruYhct.getHsbactnoitruYhctDientimkq());
			params.put("NOISOI", hsbaCTNoitruYhct.getHsbactnoitruYhctNoisoi());
			params.put("NOISOIKQ", hsbaCTNoitruYhct.getHsbactnoitruYhctNoisoikq());
			params.put("GIAIPHAUBENH", hsbaCTNoitruYhct.getHsbactnoitruYhctGiaiphaubenh());
			params.put("GIAIPHAUBENHKQ", hsbaCTNoitruYhct.getHsbactnoitruYhctGiaiphaubenhkq());
			params.put("XETNGHIEMKHAC", hsbaCTNoitruYhct.getHsbactnoitruYhctXetnghiemkhac());
			params.put("XETNGHIEMKHACKQ", hsbaCTNoitruYhct.getHsbactnoitruYhctXetnghiemkhackq());
			
			// *************PAGE 3************//

			params.put("TOMTATTRIEUCHUNG", hsbaCTNoitruYhct.getHsbactnoitruYhctTomtattrieuchung());
			params.put("VONGCHAN_HINHTHAI", hsbaCTNoitruYhct.getHsbactnoitruYhctVongchanHinhthai());
			params.put("VONGCHAN_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctVongchanMota());
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctThansacTinhtao() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctThansacTinhtao() == true)
				params.put("THANSAC_TINHTAO", "X");
			else params.put("THANSAC_TINHTAO", "");
			params.put("THANSAC_SAC", hsbaCTNoitruYhct.getHsbactnoitruYhctThansacSac());
			params.put("THANSAC_TRACH", hsbaCTNoitruYhct.getHsbactnoitruYhctThansacTrach());
			params.put("THANSAC_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctThansacMota());
			params.put("LUOI_CHATLUOI", hsbaCTNoitruYhct.getHsbactnoitruYhctLuoiChatluoi());
			params.put("LUOI_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctLuoiMota());
			params.put("LUOI_SAC", hsbaCTNoitruYhct.getHsbactnoitruYhctLuoiSac());
			params.put("SACLUOI_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctSacluoiMota());
			params.put("REULUOI", hsbaCTNoitruYhct.getHsbactnoitruYhctReuluoi());
			params.put("REULUOI_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctReuluoiMota());
			params.put("BOPHANBIBENH", hsbaCTNoitruYhct.getHsbactnoitruYhctBophanbibenh());
			params.put("TIENGNOI", hsbaCTNoitruYhct.getHsbactnoitruYhctTiengnoi());
			params.put("TIENGNOI_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctTiengnoiMota());
			params.put("HOITHO", hsbaCTNoitruYhct.getHsbactnoitruYhctHoitho());
			params.put("HOITHO_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctHoithoMota());
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctHo() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctHo() == true)
				params.put("HO", "X");
			else params.put("HO", "");
			params.put("HO_TINHCHAT", hsbaCTNoitruYhct.getHsbactnoitruYhctHoTinhchat());
			
			// *************PAGE 4************//
			
			params.put("HO_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctHoMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctOnac() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctOnac() == true)
				params.put("ONAC", "X");
			else params.put("ONAC", "");
			params.put("ONAC_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctOnacMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctMui() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctMui() == true)
				params.put("MUI", "X");
			else params.put("MUI", "");
			params.put("MUI_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctMuiTc());
			params.put("MUI_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctMuiMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctHoithocomui() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctHoithocomui() == true)
				params.put("HOITHOCOMUI", "X");
			else params.put("HOITHOCOMUI", "");
			params.put("HOITHOCOMUI_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctHoithocomuiTc());
			params.put("HOITHOCOMUI_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctHoithocomuiMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctCohannhiet() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctCohannhiet() == true)
				params.put("COHANNHIET", "X");
			else params.put("COHANNHIET", "");
			params.put("HANNHIET", hsbaCTNoitruYhct.getHsbactnoitruYhctHannhiet());
			params.put("HANNHIET_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctHannhietMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctCobenhthaydoitheomua() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctCobenhthaydoitheomua() == true)
				params.put("COBENHTHAYDOITHEOMUA", "X");
			else params.put("COBENHTHAYDOITHEOMUA", "");
			params.put("BENHTHAYDOITHEOMUA_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctBenhthaydoitheomuaMota());
			params.put("MOHOI", hsbaCTNoitruYhct.getHsbactnoitruYhctMohoi());
			params.put("MOHOI_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctMohoiMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctDaumatcobenhly() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctDaumatcobenhly() == true)
				params.put("DAUMATCOBENHLY", "X");
			else params.put("DAUMATCOBENHLY", "");
			params.put("DAUMATBENHLY", hsbaCTNoitruYhct.getHsbactnoitruYhctDaumatbenhly());
			params.put("DAUMAT_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctDaumatMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctLungcobenhly() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctLungcobenhly() == true)
				params.put("LUNGCOBENHLY", "X");
			else params.put("LUNGCOBENHLY", "");
			params.put("LUNG_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctLungMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctBungnguccobenhly() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctBungnguccobenhly() == true)
				params.put("BUNGNGUCCOBENHLY", "X");
			else params.put("BUNGNGUCCOBENHLY", "");
			params.put("BUNGNGUC", hsbaCTNoitruYhct.getHsbactnoitruYhctBungnguc());
			params.put("BUNGNGUC_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctBungngucMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctChantaycobenhly() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctChantaycobenhly() == true)
				params.put("CHANTAYCOBENHLY", "X");
			else params.put("CHANTAYCOBENHLY", "");
			params.put("CHANTAY_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctChantayMota());
			
			// *************PAGE 5************//
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctAncobenhly() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctAncobenhly() == true)
				params.put("ANCOBENHLY", "X");
			else params.put("ANCOBENHLY", "");
			params.put("AN_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctAnTc());
			params.put("AN_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctAnMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctUongcobenhly() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctUongcobenhly() == true)
				params.put("UONGCOBENHLY", "X");
			else params.put("UONGCOBENHLY", "");
			params.put("UONG_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctUongTc());
			params.put("UONG_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctUongMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctDaitieutiencobenhly() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctDaitieutiencobenhly() == true)
				params.put("DAITIEUTIENCOBENHLY", "X");
			else params.put("DAITIEUTIENCOBENHLY", "");
			params.put("TIEUTIEN_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctTieutienTc());
			params.put("DAITIEN_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctDaitienTc());
			params.put("DAITIEUTIEN_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctDaitieutienMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctNgucobenhly() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctNgucobenhly() == true)
				params.put("NGUCOBENHLY", "X");
			else params.put("NGUCOBENHLY", "");
			params.put("NGU_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctNguTc());
			params.put("NGU_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctNguMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctKinhnguyetcobenhly() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctKinhnguyetcobenhly() == true)
				params.put("KINHNGUYETCOBENHLY", "X");
			else params.put("KINHNGUYETCOBENHLY", "");
			params.put("ROILOANKINHNGUYET", hsbaCTNoitruYhct.getHsbactnoitruYhctRoiloankinhnguyet());
			params.put("DAUBUNGKINH", hsbaCTNoitruYhct.getHsbactnoitruYhctDaubungkinh());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctDoihacobenhly() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctDoihacobenhly() == true)
				params.put("DOIHACOBENHLY", "X");
			else params.put("DOIHACOBENHLY", "");
			params.put("DOIHA_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctDoihaTc());
			params.put("DOIHA_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctDoihaMota());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctSinhduccoroiloan() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctSinhduccoroiloan() == true)
				params.put("SINHDUCCOROILOAN", "X");
			else params.put("SINHDUCCOROILOAN", "");
			params.put("SINHDUC_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctSinhducTc());
			params.put("SINHDUC_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctSinhducMota());
			
			params.put("DIEUKIENXUATHIEN", hsbaCTNoitruYhct.getHsbactnoitruYhctDieukienxuathien());
			params.put("DIEUKIENXUATHIEN_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctDieukienxuathienMota());
			
			// *************PAGE 6************//
			
			params.put("XUCCHAN_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctXucchanTc());
			params.put("XUCCHAN_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctXucchanMota());
			params.put("CONHUC_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctConhucTc());
			params.put("BUNG_TC", hsbaCTNoitruYhct.getHsbactnoitruYhctBungTc());
			params.put("MACHTAYTRAI_THON", hsbaCTNoitruYhct.getHsbactnoitruYhctMachtaytraiThon());
			params.put("MACHTAYTRAI_QUAN", hsbaCTNoitruYhct.getHsbactnoitruYhctMachtaytraiQuan());
			params.put("MACHTAYTRAI_XICH", hsbaCTNoitruYhct.getHsbactnoitruYhctMachtaytraiXich());
			params.put("MACHTAYPHAI_THON", hsbaCTNoitruYhct.getHsbactnoitruYhctMachtayphaiThon());
			params.put("MACHTAYPHAI_QUAN", hsbaCTNoitruYhct.getHsbactnoitruYhctMachtayphaiQuan());
			params.put("MACHTAYPHAI_XICH", hsbaCTNoitruYhct.getHsbactnoitruYhctMachtayphaiXich());
			params.put("TONGKHAMBENPHAI", hsbaCTNoitruYhct.getHsbactnoitruYhctTongkhambenphai());
			params.put("TONGKHAMBENTRAI", hsbaCTNoitruYhct.getHsbactnoitruYhctTongkhambentrai());
			params.put("MACHCHAN_MOTA", hsbaCTNoitruYhct.getHsbactnoitruYhctMachchanMota());
			params.put("BIENCHUNGLUANTRI", hsbaCTNoitruYhct.getHsbactnoitruYhctBienchungluantri());
			params.put("BENHDANH", hsbaCTNoitruYhct.getHsbactnoitruYhctBenhdanh());
			params.put("BATCUONG", hsbaCTNoitruYhct.getHsbactnoitruYhctBatcuong());
			params.put("TANGPHU", hsbaCTNoitruYhct.getHsbactnoitruYhctTangphu());
			params.put("NGUYENNHAN", hsbaCTNoitruYhct.getHsbactnoitruYhctNguyennhan());
			
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctDieutridonthuanyhct() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctDieutridonthuanyhct() == true)
				params.put("DIEUTRIDONTHUANYHCT", "X");
			if(hsbaCTNoitruYhct.getHsbactnoitruYhctDieutrikethopyhhd() != null && hsbaCTNoitruYhct.getHsbactnoitruYhctDieutrikethopyhhd() == true)
				params.put("DIEUTRIKETHOPYHHD", "X");
			params.put("PHEPCHUA", hsbaCTNoitruYhct.getHsbactnoitruYhctPhepchua());
			params.put("PHUONGTHUOC", hsbaCTNoitruYhct.getHsbactnoitruYhctPhuongthuoc());
			params.put("PHUONGHUYET", hsbaCTNoitruYhct.getHsbactnoitruYhctPhuonghuyet());
			params.put("XOABOP", hsbaCTNoitruYhct.getHsbactnoitruYhctXoabop());
			params.put("CHEDOAN", hsbaCTNoitruYhct.getHsbactnoitruYhctChedoan());
			params.put("CHEDOHOLY", hsbaCTNoitruYhct.getHsbactnoitruYhctChedoholy());
			params.put("TIENLUONG", hsbaCTNoitruYhct.getHsbactnoitruYhctTienluong());
			
			// *************PAGE 7************//
			params.put("QTBLDBCLS", hsbaCTNoitruYhct.getHsbactnoitruYhctQtbldbcls());
			params.put("KETQUACLSCHINH", hsbaCTNoitruYhct.getHsbactnoitruYhctKetquaclschinh());
			params.put("KETQUAGIAIPHAUBENH", hsbaCTNoitruYhct.getHsbactnoitruYhctKetquagiaiphaubenh());			
			params.put("DIEUTRI_YHHD", hsbaCTNoitruYhct.getHsbactnoitruYhctDieutriYhhd());
			params.put("DIEUTRI_YHCT", hsbaCTNoitruYhct.getHsbactnoitruYhctDieutriYhct());
			params.put("CHANDOANRAVIEN_YHHD", hsbaCTNoitruYhct.getHsbactnoitruYhctChandoanravienYhhd());
			params.put("CHANDOANRAVIEN_YHCT", hsbaCTNoitruYhct.getHsbactnoitruYhctChandoanravienYhct());
			params.put("TINHTRANGNBRAVIEN", hsbaCTNoitruYhct.getHsbactnoitruYhctTinhtrangnbravien());
			params.put("HDTCCDTT", hsbaCTNoitruYhct.getHsbactnoitruYhctHdtccdtt());
			
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
							+ "hsba/", "pdf", "capNhatThongTinChiTietBANoitruYhct");
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
		return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
	}

	public HsbaChiTietNoitruYhct getHsbaCTNoitruYhct() {
		return hsbaCTNoitruYhct;
	}

	public void setHsbaCTNoitruYhct(HsbaChiTietNoitruYhct hsbaCTNoitruYhct) {
		this.hsbaCTNoitruYhct = hsbaCTNoitruYhct;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	
}
