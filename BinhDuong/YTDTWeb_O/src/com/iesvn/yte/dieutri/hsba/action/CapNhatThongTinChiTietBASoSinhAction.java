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
import com.iesvn.yte.dieutri.delegate.HsbaChiTietSosinhDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChiTietSosinh;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B211_3_CapNhatThongTinSoSinh")
@Synchronized(timeout = 600000)
public class CapNhatThongTinChiTietBASoSinhAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(CapNhatThongTinChiTietBASoSinhAction.class);

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
	private HsbaChiTietSosinh hsbaCTSosinh;
	private Hsba hosobenhan;
	private TiepDon tiepDon;
	private BenhNhan benhnhan;
	
	private String gioVoOi;
	private String ngayVoOi;
	private String gioSinh;
	private String ngaySinh;
	private String ngaysinhBo;
	private String ngaysinhMe;
	private int index = 0;

	@Create
	@Begin(nested = true)
	public String init() throws Exception {
		log.info("***Starting init Chi tiet BA So sinh ***");

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

				HsbaChiTietSosinh hsbaCTSosinhTemp = null;
				try {
					hsbaCTSosinhTemp = HsbaChiTietSosinhDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
				} catch (Exception e) {
					log.info("error Xuat Report HsbaChiTietSosinhDelegate.getInstance().findByHsbaCM: " + e);
				}
				
				if (hsbaCTSosinhTemp != null) {
					hsbaCTSosinh = hsbaCTSosinhTemp;
					
					SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
					if(hsbaCTSosinh.getHsbactsosinhVooiluc() != null){
						gioVoOi = Utils.getGioPhut(hsbaCTSosinh.getHsbactsosinhVooiluc());
						ngayVoOi = sdf.format(hsbaCTSosinh.getHsbactsosinhVooiluc());
					}
					if(hsbaCTSosinh.getHsbactsosinhDeluc() != null){
						gioSinh = Utils.getGioPhut(hsbaCTSosinh.getHsbactsosinhDeluc());
						ngaySinh = sdf.format(hsbaCTSosinh.getHsbactsosinhDeluc());
					}
					if(hsbaCTSosinh.getHsbactsosinhNgaysinhBo() != null)
						ngaysinhBo = sdf.format(hsbaCTSosinh.getHsbactsosinhNgaysinhBo());
					if(hsbaCTSosinh.getHsbactsosinhNgaysinhMe() != null)
						ngaysinhMe = sdf.format(hsbaCTSosinh.getHsbactsosinhNgaysinhMe());
				} else {
					hsbaCTSosinh = new HsbaChiTietSosinh();
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
		return "DieuTri_CapNhat_CapNhatThongTinChiTietBASosinh";
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
		log.info("*** Starting ghinhan Chi tiet BA So Sinh ***");
		ghiNhanException = null;
		try {
			hsbaCTSosinh.setHsbacmMa(hsbaChuyenMon);
			
			Calendar vooiluc = Utils.getDBDate(gioVoOi, ngayVoOi);
			if(vooiluc != null)
				hsbaCTSosinh.setHsbactsosinhVooiluc(vooiluc.getTime());
			Calendar deluc = Utils.getDBDate(gioSinh, ngaySinh);
			if(deluc != null)
				hsbaCTSosinh.setHsbactsosinhDeluc(deluc.getTime());
			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
			if(ngaysinhBo!=null && !ngaysinhBo.trim().equals(""))
				hsbaCTSosinh.setHsbactsosinhNgaysinhBo(sdf.parse(ngaysinhBo));
			if(ngaysinhMe!=null && !ngaysinhMe.trim().equals(""))
				hsbaCTSosinh.setHsbactsosinhNgaysinhMe(sdf.parse(ngaysinhMe));
			
		} catch (ParseException e) {}
		
		RemoveUtil.removeAllNullFromHSBACTSosinh(hsbaCTSosinh);
		
		if (hsbaCTSosinh.getHsbactsosinhMa() == null) {
			HsbaChiTietSosinhDelegate.getInstance().create(hsbaCTSosinh);
		} else {
			HsbaChiTietSosinhDelegate.getInstance().edit(hsbaCTSosinh);
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
		loaiBCDT = "CapNhatThongTinChiTietBASosinh";
		log.info("Vao Method XuatReport cap nhat thong tin chi tiet benh an So sinh");
		String baocao1 = null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhansosinh_N06.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhansosinh_N06_subreport0.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhansosinh_N06_subreport1.jrxml";
			String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhansosinh_N06_subreport2.jrxml";
			
			log.info("da thay file templete " + pathTemplate);
			log.info("da thay file sub 1 templete " + sub1Template);
			log.info("da thay file sub 2 templete " + sub2Template);
			log.info("da thay file sub 3 templete " + sub3Template);
			
			JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
			JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
			JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);

			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
			
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI",IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("GIAMDOCBENHVIEN", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			
			params.put("sub0", sub1Report);
			params.put("sub1", sub2Report);
			params.put("sub2", sub3Report);

//			String sMaTiepDon = "";
//			String sHoTenBN = "";
//			String sNgaySinh = "";
//			String sSosinhLamViec = "";
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
//			
//			Date dVaoVien = new Date();
//			Date dRaVien = new Date();

			HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();
			//HsbaChuyenMon objHSBAChuyenMon = hsbadel.findBySoVaoVien_MaKhoa(soBenhAn, khoaMa);
			
			HsbaChiTietSosinh hsbaCTSosinhTemp = null;
			try {
				hsbaCTSosinhTemp = HsbaChiTietSosinhDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
			} catch (Exception e) {
				log.info("error Xuat Report HsbaChiTietSosinhDelegate.getInstance().findByHsbaCM: " + e);
			}
			
			if (hsbaCTSosinhTemp != null) {
				hsbaCTSosinh = hsbaCTSosinhTemp;
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
			} else {
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
			
			if(hsbaChuyenMon.getHsbacmLathuthuat() != null )
				params.put("CHUANDOAN_COTHUTHUAT", "X");
			if(hsbaChuyenMon.getHsbacmLaphauthuat() != null )
				params.put("CHUANDOAN_COPHAUTHUAT", "X");
			if(hsbaChuyenMon.getHsbacmBienchung() != null && !hsbaChuyenMon.getHsbacmBienchung().trim().equals(""))
				params.put("CHUANDOAN_COBIENCHUNG", "X");
			if(hsbaChuyenMon.getKetquaMa(true).getDmkqdtMaso() != null && hsbaChuyenMon.getKetquaMa(true).getDmkqdtMaso() == 5){
				if (hsbaChuyenMon.getHsbaSovaovien(true).getHsbaTuvvong24g()){
					params.put("TUVONGTRONG24H", "X");
				} else {
					params.put("TUVONGSAU24H", "X");
				}
			}
			
			
			params.put("HOTENBO", hsbaCTSosinh.getHsbactsosinhHotenBo());
			params.put("HOTENME", hsbaCTSosinh.getHsbactsosinhHotenMe());
			params.put("NGHENGHIEPBO", hsbaCTSosinh.getHsbactsosinhNghenghiepBo());
			params.put("NGHENGHIEPME", hsbaCTSosinh.getHsbactsosinhNghenghiepMe());
			
			if(hsbaCTSosinh.getHsbactsosinhNgaysinhMe() != null)
				params.put("NGAYSINHME", sdf.format(hsbaCTSosinh.getHsbactsosinhNgaysinhMe()));
			if(hsbaCTSosinh.getHsbactsosinhNgaysinhBo() != null)
				params.put("NGAYSINHBO", sdf.format(hsbaCTSosinh.getHsbactsosinhNgaysinhBo()));
			
			params.put("DELANMAY", hsbaCTSosinh.getHsbactsosinhDelanmay());
			params.put("NHOMMAUME", hsbaCTSosinh.getHsbactsosinhNhommaume());
			
			if(hsbaCTSosinh.getHsbactsosinhSinhduthang() != null && hsbaCTSosinh.getHsbactsosinhSinhduthang() == true)
				params.put("SINHDUTHANG", "X");
			if(hsbaCTSosinh.getHsbactsosinhSinhdenon() != null && hsbaCTSosinh.getHsbactsosinhSinhdenon() == true)
				params.put("SOMDENON", "X");
			if(hsbaCTSosinh.getHsbactsosinhSinhnaohut() != null && hsbaCTSosinh.getHsbactsosinhSinhnaohut() == true)
				params.put("SAYNAOHUT", "X");
			if(hsbaCTSosinh.getHsbactsosinhSinhsong() != null && hsbaCTSosinh.getHsbactsosinhSinhsong() == true)
				params.put("SONG", "X");
			
			
			// *************PAGE 2************//

			if (hsbaCTSosinh.getHsbactsosinhLydovaov() != null)
				params.put("LYDOVAOVIEN", hsbaCTSosinh.getHsbactsosinhLydovaov());
			if (hsbaCTSosinh.getHsbactsosinhQtbenhly() != null)
				params.put("HOIBENH", hsbaCTSosinh.getHsbactsosinhQtbenhly());
			if (hsbaCTSosinh.getHsbactsosinhVooiluc() != null)
				params.put("VOOILUC", Utils.getGioPhutNgay(hsbaCTSosinh.getHsbactsosinhVooiluc()));
			if (hsbaCTSosinh.getHsbactsosinhMausac() != null)
				params.put("MAUSAC", hsbaCTSosinh.getHsbactsosinhMausac());
			if(hsbaCTSosinh.getHsbactsosinhDethuong() != null && hsbaCTSosinh.getHsbactsosinhDethuong() == true)
				params.put("DETHUONG", "X");
			if(hsbaCTSosinh.getHsbactsosinhCanthiep() != null && hsbaCTSosinh.getHsbactsosinhCanthiep() == true)
				params.put("DECANTHIEP", "X");
			if (hsbaCTSosinh.getHsbactsosinhDeluc() != null)
				params.put("DELUC", Utils.getGioPhutNgay(hsbaCTSosinh.getHsbactsosinhDeluc()));
			params.put("LYDOCANTHIEP", hsbaCTSosinh.getHsbactsosinhLydocanthiep());
			if(hsbaCTSosinh.getHsbactsosinhTinhtrangKhocngay() != null && hsbaCTSosinh.getHsbactsosinhTinhtrangKhocngay() == true)
				params.put("TTSS_KHOCNGAY", "X");
			if(hsbaCTSosinh.getHsbactsosinhTinhtrangNgat() != null && hsbaCTSosinh.getHsbactsosinhTinhtrangNgat() == true)
				params.put("TTSS_NGAT", "X");
			if(hsbaCTSosinh.getHsbactsosinhTinhtrangKhac() != null && hsbaCTSosinh.getHsbactsosinhTinhtrangKhac() == true)
				params.put("TTSS_KHAC", "X");
			params.put("TENNGUOIDODE", hsbaCTSosinh.getHsbactsosinhHotennguoidode());
			params.put("APGAR1PHUT", hsbaCTSosinh.getHsbactsosinhApgar1phut());
			params.put("APGAR5PHUT", hsbaCTSosinh.getHsbactsosinhApgar5phut());
			params.put("APGAR10PHUT", hsbaCTSosinh.getHsbactsosinhApgar10phut());
			params.put("TTDDSAUSINH", hsbaCTSosinh.getHsbactsosinhTinhtrangdinhduong());
			params.put("CANNANGLUCSINH", hsbaCTSosinh.getHsbactsosinhCannanglucsinh());
			if(hsbaCTSosinh.getHsbactsosinhHoisinhHutdich() != null && hsbaCTSosinh.getHsbactsosinhHoisinhHutdich() == true)
				params.put("HOISINH_HUTDICH", "X");
			if(hsbaCTSosinh.getHsbactsosinhHoisinhXoaboptim() != null && hsbaCTSosinh.getHsbactsosinhHoisinhXoaboptim() == true)
				params.put("HOISINH_XOABOPTIM", "X");
			if(hsbaCTSosinh.getHsbactsosinhHoisinhThoo2() != null && hsbaCTSosinh.getHsbactsosinhHoisinhThoo2() == true)
				params.put("HOISINH_THOO2", "X");
			if(hsbaCTSosinh.getHsbactsosinhHoisinhDatnoikhiquan() != null && hsbaCTSosinh.getHsbactsosinhHoisinhDatnoikhiquan() == true)
				params.put("HOISINH_DATNOIKHIQUAN", "X");
			if(hsbaCTSosinh.getHsbactsosinhHoisinhBopbongo2() != null && hsbaCTSosinh.getHsbactsosinhHoisinhBopbongo2() == true)
				params.put("HOISINH_BOPBONGO2", "X");
			if(hsbaCTSosinh.getHsbactsosinhHoisinhKhac() != null && hsbaCTSosinh.getHsbactsosinhHoisinhKhac() == true)
				params.put("HOISINH_KHAC", "X");
			params.put("HOTENCHUYENSOSINH", hsbaCTSosinh.getHsbactsosinhHotennguoichuyensosinh());
			if(hsbaCTSosinh.getHsbactsosinhDitatbamsinh() != null && hsbaCTSosinh.getHsbactsosinhDitatbamsinh() == true)
				params.put("TT_DITATBAMSINH", "X");
			if(hsbaCTSosinh.getHsbactsosinhCohaumon() != null && hsbaCTSosinh.getHsbactsosinhCohaumon() == true)
				params.put("TT_COHAUMON", "X");
			params.put("CUTHEDITAT", hsbaCTSosinh.getHsbactsosinhCutheditat());
			params.put("TINHHINHSOSINHVAOKHOA", hsbaCTSosinh.getHsbactsosinhTinhhinhsosinhvaokhoa());
			params.put("TINHTRANGTOANTHAN", hsbaCTSosinh.getHsbactsosinhTinhtrangtoanthan());
			params.put("THSS_CANNANG", hsbaCTSosinh.getHsbactsosinhCannang());
			params.put("THSS_CHIEUDAI", hsbaCTSosinh.getHsbactsosinhChieudai());
			params.put("THSS_VONGDAU", hsbaCTSosinh.getHsbactsosinhVongdau());
			params.put("TTTT_NHIETDO", hsbaCTSosinh.getHsbactsosinhToanthanNhietdo());
			params.put("TTTT_NHIPTHO", hsbaCTSosinh.getHsbactsosinhToanthanNhiptho());
			if(hsbaCTSosinh.getHsbactsosinhMausacdaHonghao() != null && hsbaCTSosinh.getHsbactsosinhMausacdaHonghao() == true)
				params.put("MAUSACDA_HONGHAO", "X");
			if(hsbaCTSosinh.getHsbactsosinhMausacdaXanhtai() != null && hsbaCTSosinh.getHsbactsosinhMausacdaXanhtai() == true)
				params.put("MAUSACDA_XANHTAI", "X");
			if(hsbaCTSosinh.getHsbactsosinhMausacdaVang() != null && hsbaCTSosinh.getHsbactsosinhMausacdaVang() == true)
				params.put("MAUSACDA_VANG", "X");
			if(hsbaCTSosinh.getHsbactsosinhMausacdaTim() != null && hsbaCTSosinh.getHsbactsosinhMausacdaTim() == true)
				params.put("MAUSACDA_TIM", "X");
			if(hsbaCTSosinh.getHsbactsosinhMausacdaKhac() != null && hsbaCTSosinh.getHsbactsosinhMausacdaKhac() == true)
				params.put("MAUSACDA_KHAC", "X");
			params.put("HOHAP_NHIPTHO", hsbaCTSosinh.getHsbactsosinhCoquankhacNhiptho());
			params.put("HOHAP_NGHEPHOI", hsbaCTSosinh.getHsbactsosinhCoquankhacNghephoi());
			params.put("HOHAP_SILVERMAN", hsbaCTSosinh.getHsbactsosinhCoquankhacSilverman());
			params.put("TIMMACH_NHIPTIM", hsbaCTSosinh.getHsbactsosinhCoquankhacNhiptim());
			params.put("BUNG", hsbaCTSosinh.getHsbactsosinhCoquankhacBung());
			params.put("COQUANSINHDUCNGOAI", hsbaCTSosinh.getHsbactsosinhCoquankhacSinhducngoai());
			
			
			// *****PAGE 3*******//
			
			params.put("XUONGKHOP", hsbaCTSosinh.getHsbactsosinhCoquankhacXuongkhop());
			params.put("THANKINH_PHANXA", hsbaCTSosinh.getHsbactsosinhCoquankhacThankinhPhanxa());
			params.put("THANKINH_TRUONGLUCCO", hsbaCTSosinh.getHsbactsosinhCoquankhacThankinhTruonglucco());
			
			if (hsbaCTSosinh.getHsbactsosinhBslamba() != null)
				params.put("BASILAMBA", hsbaCTSosinh.getHsbactsosinhBslamba(true).getDtdmnhanvienTen());
			
			params.put("TOMTATBENHAN", hsbaCTSosinh.getHsbactsosinhTtba());
			params.put("XETNGHIEMCLS", hsbaCTSosinh.getHsbactsosinhXetnghiemcanlamsan());
			params.put("CHIDINHTHEODOI", hsbaCTSosinh.getHsbactsosinhChidinhtheodoi());

			if (hsbaCTSosinh.getHsbactsosinhQtblDbls() != null)
				params.put("QTBLDBLS", hsbaCTSosinh.getHsbactsosinhQtblDbls());
			if (hsbaCTSosinh.getHsbactsosinhTtkqxncls() != null)
				params.put("TTKQCLSCD", hsbaCTSosinh.getHsbactsosinhTtkqxncls());
			if (hsbaCTSosinh.getHsbactsosinhPpdieutri() != null)
				params.put("PPDT", hsbaCTSosinh.getHsbactsosinhPpdieutri());
			if (hsbaCTSosinh.getHsbactsosinhTtnguoibenhrav() != null)
				params.put("TTNBRV", hsbaCTSosinh.getHsbactsosinhTtnguoibenhrav());
			if (hsbaCTSosinh.getHsbactsosinhHuongdtCdtt() != null)
				params.put("HDTCCDTT", hsbaCTSosinh.getHsbactsosinhHuongdtCdtt());
			if (hsbaCTSosinh.getHsbactsosinhSotoxquang() != null)
				params.put("XQUANG", hsbaCTSosinh.getHsbactsosinhSotoxquang());
			if (hsbaCTSosinh.getHsbactsosinhSotoctscanner() != null)
				params.put("SCANNER", hsbaCTSosinh.getHsbactsosinhSotoctscanner());
			if (hsbaCTSosinh.getHsbactsosinhSotosieuam() != null)
				params.put("SIEUAM", hsbaCTSosinh.getHsbactsosinhSotosieuam());
			if (hsbaCTSosinh.getHsbactsosinhSotoxn() != null)
				params.put("XETNGHIEM", hsbaCTSosinh.getHsbactsosinhSotoxn());
			if (hsbaCTSosinh.getHsbactsosinhSotokhac() != null)
				params.put("HSKHAC", hsbaCTSosinh.getHsbactsosinhSotokhac());
			log.info("### TONGSOTO: " + hsbaCTSosinh.getHsbactsosinhTongsoto());
			if (hsbaCTSosinh.getHsbactsosinhTongsoto() != null)
				params.put("TONGHOSO", hsbaCTSosinh.getHsbactsosinhTongsoto());
			if (hsbaCTSosinh.getHsbactsosinhNguoigiaoba() != null)
				params.put("NGUOIGIAOBA", hsbaCTSosinh.getHsbactsosinhNguoigiaoba(true).getDtdmnhanvienTen());
			if (hsbaCTSosinh.getHsbactsosinhNguoinhanba() != null)
				params.put("NGUOINHANBA", hsbaCTSosinh.getHsbactsosinhNguoinhanba(true).getDtdmnhanvienTen());
			if (hsbaCTSosinh.getHsbactsosinhBsdieutri() != null)
				params.put("BASIDT", hsbaCTSosinh.getHsbactsosinhBsdieutri(true).getDtdmnhanvienTen());

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
							+ "hsba/", "pdf", "capNhatThongTinChiTietBASosinh");
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

	public HsbaChiTietSosinh getHsbaCTSosinh() {
		return hsbaCTSosinh;
	}

	public void setHsbaCTSosinh(HsbaChiTietSosinh hsbaCTSosinh) {
		this.hsbaCTSosinh = hsbaCTSosinh;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
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

	public String getGioSinh() {
		return gioSinh;
	}

	public void setGioSinh(String gioSinh) {
		this.gioSinh = gioSinh;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getNgaysinhBo() {
		return ngaysinhBo;
	}

	public void setNgaysinhBo(String ngaysinhBo) {
		this.ngaysinhBo = ngaysinhBo;
	}

	public String getNgaysinhMe() {
		return ngaysinhMe;
	}

	public void setNgaysinhMe(String ngaysinhMe) {
		this.ngaysinhMe = ngaysinhMe;
	}
	
}
