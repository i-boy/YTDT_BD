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
import com.iesvn.yte.dieutri.delegate.HsbaChiTietNgoaitruYhctDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChiTietNgoaitruYhct;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B211_3_CapNhatThongTinNgoaiTruYhct")
@Synchronized(timeout = 600000)
public class CapNhatThongTinChiTietBANgoaiTruYhctAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(CapNhatThongTinChiTietBANgoaiTruYhctAction.class);

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
	private HsbaChiTietNgoaitruYhct hsbaCTNgoaitruYhct;
	private Hsba hosobenhan;
	private TiepDon tiepDon;
	private BenhNhan benhnhan;
	
	private int index = 0;

	@Create
	@Begin(nested = true)
	public String init() throws Exception {
		log.info("***Starting init Chi tiet BA Ngoai Tru YHCT ***");

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

				HsbaChiTietNgoaitruYhct hsbaCTNgoaitruYhctTemp = null;
				try {
					hsbaCTNgoaitruYhctTemp = HsbaChiTietNgoaitruYhctDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
				} catch (Exception e) {
					log.info("error:" + e);
				}
				
				if (hsbaCTNgoaitruYhctTemp != null) {
					hsbaCTNgoaitruYhct = hsbaCTNgoaitruYhctTemp;
				} else {
					hsbaCTNgoaitruYhct = new HsbaChiTietNgoaitruYhct();
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
		return "DieuTri_CapNhat_CapNhatThongTinChiTietBANgoaitruYhct";
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
		log.info("***Starting ghinhan Chi tiet BA Ngoai Tru YHCT **");
		ghiNhanException = null;
		hsbaCTNgoaitruYhct.setHsbacmMa(hsbaChuyenMon);
		RemoveUtil.removeAllNullFromHSBACTNgoaitruYhct(hsbaCTNgoaitruYhct);
		if (hsbaCTNgoaitruYhct.getHsbactngoaitruYhctMa() == null) {
			HsbaChiTietNgoaitruYhctDelegate.getInstance().create(hsbaCTNgoaitruYhct);
		} else {
			HsbaChiTietNgoaitruYhctDelegate.getInstance().edit(hsbaCTNgoaitruYhct);
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
		loaiBCDT = "CapNhatThongTinChiTietBANgoaitruYhct";
		log.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an ngoaitru Yhct");
		String baocao1 = null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct_subreport0.jrxml";
			String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct_subreport1.jrxml";
			String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct_subreport2.jrxml";

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
			
			params.put("sub1", sub1Report);
			params.put("sub2", sub2Report);
			params.put("sub3", sub3Report);

			//HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();
			//HsbaChuyenMon objHSBAChuyenMon = hsbadel.findBySoVaoVien_MaKhoa(soBenhAn, khoaMa);
			
			HsbaChiTietNgoaitruYhct hsbaCTNgoaitruYhctTemp = null;
			try {
				hsbaCTNgoaitruYhctTemp = HsbaChiTietNgoaitruYhctDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
			} catch (Exception e) {
				log.info("error:" + e);
			}
			
			if (hsbaCTNgoaitruYhctTemp != null) {
				hsbaCTNgoaitruYhct = hsbaCTNgoaitruYhctTemp;
			}
			
			
			// ************* PAGE 1 ************//

			String sHoTenBN = "";
			if (benhnhan.getBenhnhanHoten() != null){
				sHoTenBN = benhnhan.getBenhnhanHoten();
			}
			params.put("HOTENBN", sHoTenBN);
			
			int iTuoi = benhnhan.getBenhnhanTuoi();
			int iDonviTuoi = benhnhan.getBenhnhanDonvituoi();
			String sDonViTuoi = "";
			
			if (iDonviTuoi == 2){
				sDonViTuoi = IConstantsRes.THANG;// "Tháng";
			} else if (iDonviTuoi == 3)
				sDonViTuoi = IConstantsRes.NGAY; // "Ngày";

			params.put("TUOI", iTuoi + " " + sDonViTuoi);
			
			params.put("GIOITINH", benhnhan.getDmgtMaso(true).getDmgtTen());
			
			String sNgheNghiep = "";
			if (benhnhan.getBenhnhanNghe() != null){
				sNgheNghiep = benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen();
				params.put("NGHENGHIEP", sNgheNghiep);
			}
			
			String sDanToc = "";
			if (benhnhan.getDantocMa() != null){
				sDanToc = benhnhan.getDantocMa(true).getDmdantocTen();
				params.put("DANTOC", sDanToc);
			}
			
			String sNgoaiKieu = "";
			if (benhnhan.getQuocgiaMa(true).getDmquocgiaMa() != null)
				sNgoaiKieu = benhnhan.getQuocgiaMa(true).getDmquocgiaMa();
			params.put("NGOAIKIEU", sNgoaiKieu);
			
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
			
			String sNgaySinh = "";
			if (benhnhan.getBenhnhanNgaysinh() != null) {
				sNgaySinh = sdf.format(benhnhan.getBenhnhanNgaysinh());
			} else if (benhnhan.getBenhnhanNamsinh() != null){
				sNgaySinh = benhnhan.getBenhnhanNamsinh();
			}
			params.put("NGAYSINH", sNgaySinh);
			
			if (hosobenhan.getDoituongMa() != null){
				params.put("DOITUONG", hosobenhan.getDoituongMa(true).getDmdoituongTen());
			}
			
			String sSoVaoVien = "";
			if (hosobenhan.getHsbaSovaovien() != null){
				sSoVaoVien = hosobenhan.getHsbaSovaovien();
			}
			params.put("SOVAOVIEN", sSoVaoVien);

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
			
			String sKhiCanBaoTin = "";
			if (hosobenhan.getHsbaBaotin() != null)
				sKhiCanBaoTin = hosobenhan.getHsbaBaotin();
			params.put("BAOTIN", sKhiCanBaoTin);
			
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
			
			
			String sDonViGoi = "";
			if (hosobenhan.getHsbaDonvigoi() != null){
				sDonViGoi = hosobenhan.getHsbaDonvigoi(true).getDmbenhvienTen();
			}
			params.put("DONVIGOI", sDonViGoi);
			
			String chuandoan_noichuyenden = "";
			if ( hosobenhan.getHsbaMachdoantuyent() != null ) {
				chuandoan_noichuyenden = hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdMa() + " - ";
				chuandoan_noichuyenden += hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdTen();
			}
			params.put("CHUANDOAN_NOICHUYENDEN", chuandoan_noichuyenden);
			
			params.put("LYDOVAOVIEN", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctLydovaov());
			params.put("QUATRINHBENHLY", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctQtbenhly());
			params.put("TIEUSUBENHBANTHAN", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctTiensubenhbt());
			params.put("TIEUSUBENHGIADINH", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctTiensubenhgd());
			
			params.put("MACH", tiepDon.getTiepdonMach());
			params.put("NHIETDO", tiepDon.getTiepdonNhietdo());
			params.put("HUYETAPMAX", tiepDon.getTiepdonHamax());
			params.put("HUYETAPMIN", tiepDon.getTiepdonHamin());
			params.put("NHIPTHO", tiepDon.getTiepdonNhiptho());
			params.put("CANNANG", tiepDon.getTiepdonTrluong());
			
			params.put("BOPHANBIBENH", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctBophanbibenh());
			params.put("XETNGHIEMCANTHIET", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctXetnghiemcanthiet());
			
			
			// *************PAGE 2************//
			
			if (hsbaChuyenMon.getKetquaMa() != null){
				params.put("KETQUA", hsbaChuyenMon.getKetquaMa(true).getDmkqdtTen());
			}
			
			String sBenhChinh = "";
			String sBenhKemTheo = "";
				
			if (hsbaChuyenMon.getHsbacmBenhchinh() != null) {
				sBenhChinh = hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
				params.put("BENHCHINH_MA", hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa());
			}
			params.put("BENHCHINH", sBenhChinh);
			if (hsbaChuyenMon.getHsbacmBenhphu() != null) {
				sBenhKemTheo = hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
				params.put("BENHPHU_MA", hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdMa());
			}
			params.put("BENHPHU", sBenhKemTheo);
			
			params.put("VONGCHAN", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctVongchan());
			params.put("VANCHAN", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctVanchan());
			params.put("VANCHAN2", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctVanchan2());
			params.put("THIETCHAN", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctThietchan());
			params.put("CHANDOAN_BENHDANH", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanBenhdanh());
			params.put("CHANDOAN_BATCUONG", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanBatcuong());
			params.put("CHANDOAN_TANGPHU", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanTangphu());
			params.put("CHANDOAN_NGUYENNHAN", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanNguyennhan());
			params.put("DIEUTRI_PHEPCHUA", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriPhepchua());
			params.put("DIEUTRI_PHUONGTHUOC", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriPhuongthuoc());
			params.put("DIEUTRI_PHUONGHUYET", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriPhuonghuyet());
			params.put("DIEUTRI_XOABOP", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriXoabop());
			params.put("CHEDOANTAINHA", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriChedoantainha());
			params.put("CHEDOHOLYTAINHA", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriChedoholytainha());
			params.put("TIENLUONG", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctTienluong());
			
			// *****PAGE 3*******//
			params.put("KETQUACLSCHINH", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctKetquaclschinh());
			params.put("KETQUAGIAIPHAUBENH", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctKetquagiaiphaubenh());			
			params.put("PHAPTRI_YHHD", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctPhaptriYhhd());
			params.put("PHAPTRI_YHCT", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctPhaptriYhct());
			params.put("CHANDOANRAVIEN_YHHD", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanravienYhhd());
			params.put("CHANDOANRAVIEN_YHCT", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanravienYhct());
			params.put("HDTCCDTT", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctHuongdtCdtt());
			
			if (hsbaCTNgoaitruYhct.getHsbactngoaitruYhctBsdieutri() != null)
				params.put("BACSIDT", hsbaCTNgoaitruYhct.getHsbactngoaitruYhctBsdieutri(true).getDtdmnhanvienTen());
			
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
							+ "hsba/", "pdf", "capNhatThongTinChiTietBANgoaitruYhct");
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

	public HsbaChiTietNgoaitruYhct getHsbaCTNgoaitruYhct() {
		return hsbaCTNgoaitruYhct;
	}

	public void setHsbaCTNgoaitruYhct(HsbaChiTietNgoaitruYhct hsbaCTNgoaitruYhct) {
		this.hsbaCTNgoaitruYhct = hsbaCTNgoaitruYhct;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	
}
