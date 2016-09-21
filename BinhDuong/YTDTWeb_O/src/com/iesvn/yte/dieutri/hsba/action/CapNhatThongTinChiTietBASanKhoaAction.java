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
import java.util.Calendar;
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
import com.iesvn.yte.dieutri.delegate.HsbaChiTietSankhoaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaPhieuPhauThuatThuThuatDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChiTietSankhoa;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B211_3_CapNhatThongTinSanKhoa")
@Synchronized(timeout = 600000)
public class CapNhatThongTinChiTietBASanKhoaAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(CapNhatThongTinChiTietBASanKhoaAction.class);

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
	private HsbaChiTietSankhoa hsbaCTSankhoa;
	private Hsba hosobenhan;
	private TiepDon tiepDon;
	private BenhNhan benhnhan;
	
	private String kinhcuoitungay;
	private String kinhcuoidenngay;
	private String chuandoan_ngayde;
	private String gioVoOi;
	private String ngayVoOi;
	private String gioChuyenda;
	private String ngayChuyenda;
	private String gioVaobuong;
	private String ngayVaobuong;
	private String gioDe;
	private String ngayDe;
	private String gioRauso;
	private String ngayRauso;
	private int index = 0;

	@Create
	@Begin(nested = true)
	public String init() throws Exception {
		log.info("***Starting init chi tiet BA San khoa ***");

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

				HsbaChiTietSankhoa hsbaCTSankhoaTemp = null;
				try {
					hsbaCTSankhoaTemp = HsbaChiTietSankhoaDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
				} catch (Exception e) {
					log.info("error:" + e);
				}
				
				if (hsbaCTSankhoaTemp != null) {
					hsbaCTSankhoa = hsbaCTSankhoaTemp;
					SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
					if(hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungtungay() != null)
						kinhcuoitungay = sdf.format(hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungtungay());
					if(hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungdenngay() != null)
						kinhcuoidenngay = sdf.format(hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungdenngay());
					if(hsbaCTSankhoa.getHsbactsankhoaChuandoanngayde() != null)
						chuandoan_ngayde = sdf.format(hsbaCTSankhoa.getHsbactsankhoaChuandoanngayde());
					if(hsbaCTSankhoa.getHsbactsankhoaOivoluc() != null){
						gioVoOi = Utils.getGioPhut(hsbaCTSankhoa.getHsbactsankhoaOivoluc());
						ngayVoOi = sdf.format(hsbaCTSankhoa.getHsbactsankhoaOivoluc());
					}
					if(hsbaCTSankhoa.getHsbactsankhoaChuyendaluc() != null){
						gioChuyenda = Utils.getGioPhut(hsbaCTSankhoa.getHsbactsankhoaChuyendaluc());
						ngayChuyenda = sdf.format(hsbaCTSankhoa.getHsbactsankhoaChuyendaluc());
					}
					if(hsbaCTSankhoa.getHsbactsankhoaVaobuongdeluc() != null){
						gioVaobuong = Utils.getGioPhut(hsbaCTSankhoa.getHsbactsankhoaVaobuongdeluc());
						ngayVaobuong = sdf.format(hsbaCTSankhoa.getHsbactsankhoaVaobuongdeluc());
					}
					if(hsbaCTSankhoa.getHsbactsankhoaDeluc() != null){
						gioDe = Utils.getGioPhut(hsbaCTSankhoa.getHsbactsankhoaDeluc());
						ngayDe = sdf.format(hsbaCTSankhoa.getHsbactsankhoaDeluc());
					}
					if(hsbaCTSankhoa.getHsbactsankhoaRausoluc() != null){
						gioRauso = Utils.getGioPhut(hsbaCTSankhoa.getHsbactsankhoaRausoluc());
						ngayRauso = sdf.format(hsbaCTSankhoa.getHsbactsankhoaRausoluc());
					}
				} else {
					hsbaCTSankhoa = new HsbaChiTietSankhoa();
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
		return "DieuTri_CapNhat_CapNhatThongTinChiTietBASankhoa";
	}

	@End
	public String back() throws Exception {
		log.info("***Starting back ***");
		log.info("***Finished back **");
		return "DieuTri_CapNhat_CapNhatThongTinChung";
	}

	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() {
		log.info("***Starting ghinhan Chi tiet BA San khoa ***");
		ghiNhanException = null;
		
		hsbaCTSankhoa.setHsbacmMa(hsbaChuyenMon);
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
		
		try {
			if(kinhcuoitungay != null && !kinhcuoitungay.trim().equals(""))
				hsbaCTSankhoa.setHsbactsankhoaKinhcuoicungtungay(sdf.parse(kinhcuoitungay));
			if(kinhcuoidenngay != null && !kinhcuoidenngay.trim().equals(""))	
				hsbaCTSankhoa.setHsbactsankhoaKinhcuoicungdenngay(sdf.parse(kinhcuoidenngay));
			if(chuandoan_ngayde != null && !chuandoan_ngayde.trim().equals(""))	
				hsbaCTSankhoa.setHsbactsankhoaChuandoanngayde(sdf.parse(chuandoan_ngayde));
			
			Calendar vooiluc = Utils.getDBDate(gioVoOi, ngayVoOi);
			if(vooiluc != null)
				hsbaCTSankhoa.setHsbactsankhoaOivoluc(vooiluc.getTime());
			Calendar chuyendaluc = Utils.getDBDate(gioChuyenda, ngayChuyenda);
			if(chuyendaluc != null)
				hsbaCTSankhoa.setHsbactsankhoaChuyendaluc(chuyendaluc.getTime());
			Calendar vaobuongluc = Utils.getDBDate(gioVaobuong, ngayVaobuong);
			if(vaobuongluc != null)
				hsbaCTSankhoa.setHsbactsankhoaVaobuongdeluc(vaobuongluc.getTime());
			Calendar deluc = Utils.getDBDate(gioDe, ngayDe);
			if(deluc != null)
				hsbaCTSankhoa.setHsbactsankhoaDeluc(deluc.getTime());
			Calendar rausoluc = Utils.getDBDate(gioRauso, ngayRauso);
			if(rausoluc != null)
				hsbaCTSankhoa.setHsbactsankhoaRausoluc(rausoluc.getTime());
			
		} catch (ParseException e) {log.info("*** PARSE NGAY THANG BI ERROR **"); e.printStackTrace();}
		
		RemoveUtil.removeAllNullFromHSBACTSankhoa(hsbaCTSankhoa);
		
		if (hsbaCTSankhoa.getHsbactsankhoaMa() == null) {
			HsbaChiTietSankhoaDelegate.getInstance().create(hsbaCTSankhoa);
		} else {
			HsbaChiTietSankhoaDelegate.getInstance().edit(hsbaCTSankhoa);
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
		loaiBCDT = "CapNhatThongTinChiTietBASankhoa";
		log.info("Vao Method XuatReport bao cao cap nhat thong tin chi tiet benh an sankhoa");
		String baocao1 = null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N07_benhansankhoa.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N07_benhansankhoa_subreport0.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N07_benhansankhoa_subreport1.jrxml";
			String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N07_benhansankhoa_subreport2.jrxml";
			String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N07_benhansankhoa_subreport3.jrxml";

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

			
			HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();
			//HsbaChuyenMon objHSBAChuyenMon = hsbadel.findBySoVaoVien_MaKhoa(soBenhAn, khoaMa);
			
			HsbaChiTietSankhoa hsbaCTSankhoaTemp = null;
			try {
				hsbaCTSankhoaTemp = HsbaChiTietSankhoaDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
			} catch (Exception e) {
				log.info("error Xuat Report HsbaChiTietSankhoaDelegate.getInstance().findByHsbaCM: " + e);
			}
			
			if (hsbaCTSankhoaTemp != null) {
				hsbaCTSankhoa = hsbaCTSankhoaTemp;
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
			
			
			params.put("CHUANDOAN_NGAYDE", chuandoan_ngayde);
			params.put("CHUANDOAN_NGOITHAI", hsbaCTSankhoa.getHsbactsankhoaChuandoanngoithai());
			params.put("CHUANDOAN_CACHTHUCDE", hsbaCTSankhoa.getHsbactsankhoaChuandoancachthucde());
			params.put("CHUANDOAN_KIEMSOATTUCUNG", hsbaCTSankhoa.getHsbactsankhoaChuandoankiemsoattucung());
			params.put("CHUANDOAN_DITAT", hsbaCTSankhoa.getHsbactsankhoaChuandoanditat());
			params.put("CHUANDOAN_CANNANG", hsbaCTSankhoa.getHsbactsankhoaChuandoancannang());
			
			if(hsbaCTSankhoa.getHsbactsankhoaChuandoanphauthuatcapcuu() != null && hsbaCTSankhoa.getHsbactsankhoaChuandoanphauthuatcapcuu() == true)
				params.put("CHUANDOAN_PHAUTHUATCAPCUU", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaChuandoanphauthuatchudong() != null && hsbaCTSankhoa.getHsbactsankhoaChuandoanphauthuatchudong() == true)
				params.put("CHUANDOAN_PHAUTHUATCHUDONG", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaChuandoandonthai() != null && hsbaCTSankhoa.getHsbactsankhoaChuandoandonthai() == true)
				params.put("CHUANDOAN_DONTHAI", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaChuandoandathai() != null && hsbaCTSankhoa.getHsbactsankhoaChuandoandathai() == true)
				params.put("CHUANDOAN_DATHAI", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaChuandoantrai() != null && hsbaCTSankhoa.getHsbactsankhoaChuandoantrai() == true)
				params.put("CHUANDOAN_TRAI", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaChuandoangai() != null && hsbaCTSankhoa.getHsbactsankhoaChuandoangai() == true)
				params.put("CHUANDOAN_GAI", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaChuandoansong() != null && hsbaCTSankhoa.getHsbactsankhoaChuandoansong() == true)
				params.put("CHUANDOAN_SONG", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaChuandoanchet() != null && hsbaCTSankhoa.getHsbactsankhoaChuandoanchet() == true)
				params.put("CHUANDOAN_CHET", "X");

			// *************PAGE 2************//
			
			if (hsbaCTSankhoa.getHsbactsankhoaLydovaov() != null)
				params.put("LYDOVAOVIEN", hsbaCTSankhoa.getHsbactsankhoaLydovaov());
			params.put("NGAYTHUBENH", hsbaCTSankhoa.getHsbactsankhoaNgaythubenh());
			if(hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungtungay() != null)
				params.put("KINHCUOITUNGAY", sdf.format(hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungtungay()));
			if(hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungdenngay() != null)
				params.put("KINHCUOIDENNGAY", sdf.format(hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungdenngay()));
			params.put("TUOITHAITUAN", hsbaCTSankhoa.getHsbactsankhoaTuoithaituan());
			params.put("KHAMTHAITAI", hsbaCTSankhoa.getHsbactsankhoaKhamthaitai());
			if(hsbaCTSankhoa.getHsbactsankhoaTiemphonguonvan() != null && hsbaCTSankhoa.getHsbactsankhoaTiemphonguonvan() == true)
				params.put("COTIEMPHONGUONGVAN", "X");
			params.put("SOLANTIEMUONGVAN", hsbaCTSankhoa.getHsbactsankhoaSolantiemuonvan());
			if(hsbaCTSankhoa.getHsbactsankhoaChuyendaluc() != null)
				params.put("LUCCHUYENDA", Utils.getGioPhutNgay(hsbaCTSankhoa.getHsbactsankhoaChuyendaluc()));
			params.put("DAUHIEULUCDAU", hsbaCTSankhoa.getHsbactsankhoaDauhieulucdau());
			params.put("BIENCHUYEN", hsbaCTSankhoa.getHsbactsankhoaChuyenbien());
			
			if (hsbaCTSankhoa.getHsbactsankhoaTiensubenhbt() != null)
				params.put("TSBBANTHAN", hsbaCTSankhoa.getHsbactsankhoaTiensubenhbt());
			if (hsbaCTSankhoa.getHsbactsankhoaTiensubenhgd() != null)
				params.put("GIADINH", hsbaCTSankhoa.getHsbactsankhoaTiensubenhgd());
			params.put("THAYKINHNAM", hsbaCTSankhoa.getHsbactsankhoaThaykinhnam());
			params.put("THAYKINHTUOI", hsbaCTSankhoa.getHsbactsankhoaThaykinhtuoi());
			params.put("TINHCHATKINHNGUYET", hsbaCTSankhoa.getHsbactsankhoaTinhchatkinhnguyet());
			params.put("CHUKY", hsbaCTSankhoa.getHsbactsankhoaChukykinh());
			params.put("LUONGKINH", hsbaCTSankhoa.getHsbactsankhoaLuongkinh());			
			params.put("LAYCHONGNAM", hsbaCTSankhoa.getHsbactsankhoaLaychongnam());
			params.put("LAYCHONGTUOI", hsbaCTSankhoa.getHsbactsankhoaLaychongtuoi());
			params.put("BENHPHUKHOADADIEUTRI", hsbaCTSankhoa.getHsbactsankhoaBenhsankhoadadieutri());
			params.put("TINHTRANG", hsbaCTSankhoa.getHsbactsankhoaTinhtrang());
			if(hsbaCTSankhoa.getHsbactsankhoaTinhtrangphu() != null && hsbaCTSankhoa.getHsbactsankhoaTinhtrangphu() == true)
				params.put("COPHU", "X");
			params.put("TUANHOAN", hsbaCTSankhoa.getHsbactsankhoaTuanhoan());
			params.put("HOHAP", hsbaCTSankhoa.getHsbactsankhoaHohap());			
			params.put("TIEUHOA", hsbaCTSankhoa.getHsbactsankhoaTieuhoa());
			params.put("TIETNIEU", hsbaCTSankhoa.getHsbactsankhoaThantietnieusinhhoc());
			params.put("BOPHANKHAC", hsbaCTSankhoa.getHsbactsankhoaCoquankhac());
			
			// *****PAGE 3*******//
			if(hsbaCTSankhoa.getHsbactsankhoaBungcoseophauthuatcu() != null && hsbaCTSankhoa.getHsbactsankhoaBungcoseophauthuatcu() == true)
				params.put("COSEOPHAUTHUATCU", "X");
			params.put("HINHDANGTUCUNG", hsbaCTSankhoa.getHsbactsankhoaHinhdangtc());
			params.put("TUTHE", hsbaCTSankhoa.getHsbactsankhoaTuthetc());
			params.put("CHIEUCAOTC", hsbaCTSankhoa.getHsbactsankhoaChieucaotc());
			params.put("VONGBUNG", hsbaCTSankhoa.getHsbactsankhoaVongbung());
			params.put("CONCOTC", hsbaCTSankhoa.getHsbactsankhoaConcotc());
			params.put("TIMTHAI", hsbaCTSankhoa.getHsbactsankhoaTimthai());
			params.put("VU", hsbaCTSankhoa.getHsbactsankhoaVu());
			params.put("DUONGKINHNGOAIKHUNGCHAU", hsbaCTSankhoa.getHsbactsankhoaDkngoaikhungchau());
			params.put("BISHOP", hsbaCTSankhoa.getHsbactsankhoaBishop());
			params.put("AMHO", hsbaCTSankhoa.getHsbactsankhoaAmho());
			params.put("TANGSINHMON", hsbaCTSankhoa.getHsbactsankhoaTangsinhmon());
			params.put("AMDAO", hsbaCTSankhoa.getHsbactsankhoaAmdao());
			params.put("COTUCUNG", hsbaCTSankhoa.getHsbactsankhoaCotucung());
			params.put("PHANPHU", hsbaCTSankhoa.getHsbactsankhoaPhanphu());
			if(hsbaCTSankhoa.getHsbactsankhoaOiphong() != null && hsbaCTSankhoa.getHsbactsankhoaOiphong() == true)
				params.put("OIPHONG", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaOidet() != null && hsbaCTSankhoa.getHsbactsankhoaOidet() == true)
				params.put("OIDET", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaOiquale() != null && hsbaCTSankhoa.getHsbactsankhoaOiquale() == true)
				params.put("OIQUALE", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaOivotunhien() != null && hsbaCTSankhoa.getHsbactsankhoaOivotunhien() == true)
				params.put("OIVOTUNHIEN", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaOivobamoi() != null  && hsbaCTSankhoa.getHsbactsankhoaOivobamoi() == true)
				params.put("OIVOBAMOI", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaOivoluc() != null)
				params.put("VOOILUC", Utils.getGioPhutNgay(hsbaCTSankhoa.getHsbactsankhoaOivoluc()));
			params.put("MAUSACNUOCOI", hsbaCTSankhoa.getHsbactsankhoaMausacnuocoi());
			params.put("NUOCOINHIEUHAYIT", hsbaCTSankhoa.getHsbactsankhoaNuocoinhieuhayit());
			params.put("NGOI", hsbaCTSankhoa.getHsbactsankhoaNgoi());
			params.put("THE", hsbaCTSankhoa.getHsbactsankhoaThe());
			params.put("KIEUTHE", hsbaCTSankhoa.getHsbactsankhoaKieuthe());
			params.put("DUONGKINHNHOHAVE", hsbaCTSankhoa.getHsbactsankhoaDknhohave());
			if(hsbaCTSankhoa.getHsbactsankhoaDolotcao() != null && hsbaCTSankhoa.getHsbactsankhoaDolotcao() == true)
				params.put("DOLOT_CAO", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaDolotchuc() != null && hsbaCTSankhoa.getHsbactsankhoaDolotchuc() == true)
				params.put("DOLOT_CHUC", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaDolotchat() != null && hsbaCTSankhoa.getHsbactsankhoaDolotchat() == true)
				params.put("DOLOT_CHAT", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaDolotlot() != null && hsbaCTSankhoa.getHsbactsankhoaDolotlot() == true)
				params.put("DOLOT_LOT", "X");
			params.put("XETNGHIEMCLS", hsbaCTSankhoa.getHsbactsankhoaXetnghiemcanlam());
			params.put("TIENLUONG", hsbaCTSankhoa.getHsbactsankhoaTienluong());
			params.put("HUONGDIEUTRI", hsbaCTSankhoa.getHsbactsankhoaHuongdieutri());
			
			// *****PAGE 4 *******//
			if(hsbaCTSankhoa.getHsbactsankhoaVaobuongdeluc() != null)
				params.put("VAOBUONGDELUC", Utils.getGioPhutNgay(hsbaCTSankhoa.getHsbactsankhoaVaobuongdeluc()));
			params.put("TENNGUOITHEODOI", hsbaCTSankhoa.getHsbactsankhoaTennguoitheodoi());
			params.put("CHUCDANH", hsbaCTSankhoa.getHsbactsankhoaChucdanhnguoitheodoi());
			if(hsbaCTSankhoa.getHsbactsankhoaDeluc() != null)
				params.put("DELUC", Utils.getGioPhutNgay(hsbaCTSankhoa.getHsbactsankhoaDeluc()));
			params.put("APGAR1PHUT", hsbaCTSankhoa.getHsbactsankhoaApgar1phut());
			params.put("APGAR5PHUT", hsbaCTSankhoa.getHsbactsankhoaApgar5phut());
			params.put("APGAR10PHUT", hsbaCTSankhoa.getHsbactsankhoaApgar10phut());
			params.put("CANNANGTHAI", hsbaCTSankhoa.getHsbactsankhoaCannang());
			params.put("CAO", hsbaCTSankhoa.getHsbactsankhoaCao());
			params.put("VONGDAU", hsbaCTSankhoa.getHsbactsankhoaVongdau());
			if(hsbaCTSankhoa.getHsbactsankhoaDonthaitrai() != null && hsbaCTSankhoa.getHsbactsankhoaDonthaitrai() == true)
				params.put("DONTHAITRAI", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaDonthaigai() != null && hsbaCTSankhoa.getHsbactsankhoaDonthaigai() == true)
				params.put("DONTHAIGAI", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaDathaitrai() != null && hsbaCTSankhoa.getHsbactsankhoaDathaitrai() == true)
				params.put("DATHAITRAI", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaDathaigai() != null && hsbaCTSankhoa.getHsbactsankhoaDathaigai() == true)
				params.put("DATHAIGAI", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaTatbamsinh() != null && hsbaCTSankhoa.getHsbactsankhoaTatbamsinh() == true)
				params.put("TATBAMSINH", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaCohaumon() != null && hsbaCTSankhoa.getHsbactsankhoaCohaumon() == true)
				params.put("COHAUMON", "X");
			params.put("CUTHETATBAMSINH", hsbaCTSankhoa.getHsbactsankhoaCuthetatbamsinh());
			params.put("TTSS_SAUDE", hsbaCTSankhoa.getHsbactsankhoaTtsssaude());
			params.put("XULY_KETQUA", hsbaCTSankhoa.getHsbactsankhoaXulyketqua());
			if(hsbaCTSankhoa.getHsbactsankhoaRauboc() != null && hsbaCTSankhoa.getHsbactsankhoaRauboc() == true)
				params.put("RAU_BOC", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaRauso() != null && hsbaCTSankhoa.getHsbactsankhoaRauso() == true)
				params.put("RAU_SO", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaRausoluc() != null)
				params.put("RAUSOLUC", Utils.getGioPhutNgay(hsbaCTSankhoa.getHsbactsankhoaRausoluc()));
			params.put("CACHSORAU", hsbaCTSankhoa.getHsbactsankhoaCachsorau());
			params.put("MATMANG", hsbaCTSankhoa.getHsbactsankhoaMatmang());
			params.put("MATMUI", hsbaCTSankhoa.getHsbactsankhoaMatmui());
			params.put("BANHRAU", hsbaCTSankhoa.getHsbactsankhoaBanhrau());
			params.put("BANHRAU_CANNANG", hsbaCTSankhoa.getHsbactsankhoaBanhraucannang());
			params.put("CUONGRAUDAI", hsbaCTSankhoa.getHsbactsankhoaCuongraudai());
			params.put("LUONGMAUMAT", hsbaCTSankhoa.getHsbactsankhoaLuongmaumat());
			if(hsbaCTSankhoa.getHsbactsankhoaRaucuonco() != null && hsbaCTSankhoa.getHsbactsankhoaRaucuonco() == true)
				params.put("RAUCUONCO", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaChaymausauso() != null && hsbaCTSankhoa.getHsbactsankhoaChaymausauso() == true)
				params.put("COCHAYMAUSAUSO", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaKiemsoattucung() != null && hsbaCTSankhoa.getHsbactsankhoaKiemsoattucung() == true)
				params.put("KIEMSOATTUCUNG", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaDethuong() != null && hsbaCTSankhoa.getHsbactsankhoaDethuong() == true)
				params.put("DETHUONG", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaDechihuy() != null && hsbaCTSankhoa.getHsbactsankhoaDechihuy() == true)
				params.put("DECHIHUY", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaForceps() != null && hsbaCTSankhoa.getHsbactsankhoaForceps() == true)
				params.put("FORCEPS", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaDekhac() != null && hsbaCTSankhoa.getHsbactsankhoaDekhac() == true)
				params.put("TTKHAC", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaDephauthuat() != null && hsbaCTSankhoa.getHsbactsankhoaDephauthuat() == true)
				params.put("DEPHAUTHUAT", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaGiachut() != null && hsbaCTSankhoa.getHsbactsankhoaGiachut() == true)
				params.put("GIACHUT", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaTangsinhmonKhongrach() != null && hsbaCTSankhoa.getHsbactsankhoaTangsinhmonKhongrach() == true)
				params.put("TANGSINHMON_KHONGRACH", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaTangsinhmonRach() != null && hsbaCTSankhoa.getHsbactsankhoaTangsinhmonRach() == true)
				params.put("TANGSINHMON_RACH", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaTangsinhmonCat() != null && hsbaCTSankhoa.getHsbactsankhoaTangsinhmonCat() == true)
				params.put("TANGSINHMON_CAT", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaCotucungKhongrach() != null && hsbaCTSankhoa.getHsbactsankhoaCotucungKhongrach() == true)
				params.put("COTUCUNG_KHONGRACH", "X");
			if(hsbaCTSankhoa.getHsbactsankhoaCotucungRach() != null && hsbaCTSankhoa.getHsbactsankhoaCotucungRach() == true)
				params.put("COTUCUNG_RACH", "X");
			params.put("RAU_XULY_KETQUA", hsbaCTSankhoa.getHsbactsankhoaRauXulyketqua());
			params.put("DANIEMMAC", hsbaCTSankhoa.getHsbactsankhoaDaniemmac());
			params.put("LYDOCANTHIEP", hsbaCTSankhoa.getHsbactsankhoaLydocanthiep());
			params.put("PPKHAU_LOAICHI", hsbaCTSankhoa.getHsbactsankhoaPpkhauvaloaichi());
			params.put("SOMUIKHAU", hsbaCTSankhoa.getHsbactsankhoaSomuikhau());
			if(hsbaChuyenMon.getHsbacmBienchung() != null && !hsbaChuyenMon.getHsbacmBienchung().trim().equals(""))
				params.put("COBIENCHUNG", "X");
			
			if (hsbaCTSankhoa.getHsbactsankhoaBslamba() != null)
				params.put("BACSILAMBA", hsbaCTSankhoa.getHsbactsankhoaBslamba(true).getDtdmnhanvienTen());
			if (hsbaCTSankhoa.getHsbactsankhoaHuongdtCdtt() != null)
				params.put("HDTCCDTT", hsbaCTSankhoa.getHsbactsankhoaHuongdtCdtt());
			if (hsbaCTSankhoa.getHsbactsankhoaSotoxquang() != null)
				params.put("XQUANG", hsbaCTSankhoa.getHsbactsankhoaSotoxquang());
			if (hsbaCTSankhoa.getHsbactsankhoaSotoctscanner() != null)
				params.put("SCANNER", hsbaCTSankhoa.getHsbactsankhoaSotoctscanner());
			if (hsbaCTSankhoa.getHsbactsankhoaSotosieuam() != null)
				params.put("SIEUAM", hsbaCTSankhoa.getHsbactsankhoaSotosieuam());
			if (hsbaCTSankhoa.getHsbactsankhoaSotoxn() != null)
				params.put("XETNGHIEM", hsbaCTSankhoa.getHsbactsankhoaSotoxn());
			if (hsbaCTSankhoa.getHsbactsankhoaSotokhac() != null)
				params.put("HSKHAC", hsbaCTSankhoa.getHsbactsankhoaSotokhac());
			if (hsbaCTSankhoa.getHsbactsankhoaTongsoto() != null)
				params.put("TONGHOSO", hsbaCTSankhoa.getHsbactsankhoaTongsoto());
			if (hsbaCTSankhoa.getHsbactsankhoaNguoigiaoba() != null)
				params.put("NGUOIGIAOBA", hsbaCTSankhoa.getHsbactsankhoaNguoigiaoba(true).getDtdmnhanvienTen());
			if (hsbaCTSankhoa.getHsbactsankhoaNguoinhanba() != null)
				params.put("NGUOINHANBA", hsbaCTSankhoa.getHsbactsankhoaNguoinhanba(true).getDtdmnhanvienTen());
			if (hsbaCTSankhoa.getHsbactsankhoaBsdieutri() != null)
				params.put("BASIDT", hsbaCTSankhoa.getHsbactsankhoaBsdieutri(true).getDtdmnhanvienTen());

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
							+ "hsba/", "pdf", "capNhatThongTinChiTietBASankhoa");
			duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + duongdanStrDT);
			index += 1;
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

	public HsbaChiTietSankhoa getHsbaCTSankhoa() {
		return hsbaCTSankhoa;
	}

	public void setHsbaCTSankhoa(HsbaChiTietSankhoa hsbaCTSankhoa) {
		this.hsbaCTSankhoa = hsbaCTSankhoa;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getKinhcuoitungay() {
		return kinhcuoitungay;
	}

	public void setKinhcuoitungay(String kinhcuoitungay) {
		this.kinhcuoitungay = kinhcuoitungay;
	}

	public String getKinhcuoidenngay() {
		return kinhcuoidenngay;
	}

	public void setKinhcuoidenngay(String kinhcuoidenngay) {
		this.kinhcuoidenngay = kinhcuoidenngay;
	}

	public String getGioVoOi() {
		return gioVoOi;
	}

	public void setGioVoOi(String gioVoOi) {
		this.gioVoOi = gioVoOi;
	}

	public String getNgayVoOi() {
		return ngayVoOi;
	}

	public void setNgayVoOi(String ngayVoOi) {
		this.ngayVoOi = ngayVoOi;
	}

	public String getGioChuyenda() {
		return gioChuyenda;
	}

	public void setGioChuyenda(String gioChuyenda) {
		this.gioChuyenda = gioChuyenda;
	}

	public String getNgayChuyenda() {
		return ngayChuyenda;
	}

	public void setNgayChuyenda(String ngayChuyenda) {
		this.ngayChuyenda = ngayChuyenda;
	}

	public String getGioVaobuong() {
		return gioVaobuong;
	}

	public void setGioVaobuong(String gioVaobuong) {
		this.gioVaobuong = gioVaobuong;
	}

	public String getNgayVaobuong() {
		return ngayVaobuong;
	}

	public void setNgayVaobuong(String ngayVaobuong) {
		this.ngayVaobuong = ngayVaobuong;
	}

	public String getGioDe() {
		return gioDe;
	}

	public void setGioDe(String gioDe) {
		this.gioDe = gioDe;
	}

	public String getNgayDe() {
		return ngayDe;
	}

	public void setNgayDe(String ngayDe) {
		this.ngayDe = ngayDe;
	}

	public String getGioRauso() {
		return gioRauso;
	}

	public void setGioRauso(String gioRauso) {
		this.gioRauso = gioRauso;
	}

	public String getNgayRauso() {
		return ngayRauso;
	}

	public void setNgayRauso(String ngayRauso) {
		this.ngayRauso = ngayRauso;
	}

	public String getChuandoan_ngayde() {
		return chuandoan_ngayde;
	}

	public void setChuandoan_ngayde(String chuandoan_ngayde) {
		this.chuandoan_ngayde = chuandoan_ngayde;
	}

	
}
