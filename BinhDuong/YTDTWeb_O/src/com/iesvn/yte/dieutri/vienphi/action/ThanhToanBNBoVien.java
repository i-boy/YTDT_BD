package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.util.HoSoThanhToanUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(SESSION)
@Name("B3242_ThanhtoanbenhanBNbovien")
@Synchronized(timeout = 6000000)
public class ThanhToanBNBoVien implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger log = Logger.getLogger(ThanhToanPhongCapCuuAction.class);

	
	private BenhNhan benhNhan;
	private HsbaBhyt hsbaBhyt;
	private Hsba hsba;
	private HsbaChuyenVien hsbaChuyenvien;
//	private HsbaChuyenMon hsbaChuyenMon;
	private HsThtoan hsThtoan;
	private String gioi;
	private String tuoi;
	private String soThe;
	private String ngayNhapvien;
	
	private String ngayTtoan;
	private String cacKhoadt;
	private boolean dtNoi;
	private boolean dtNgoai;
	private boolean dtDongy;
	
	private String hienThiGhiNhan="";
	private String hienThiInPhieu="";
	
	private Double ungTra;
	
	private DtDmCum cum = null;
	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
	
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	public void refreshNhanVienThuNgan()
	{
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienThungan = nvDelegate.findByNd(identity.getUsername());
		if (nhanVienThungan == null) {
			nhanVienThungan = new DtDmNhanVien();
		}
	}
	@Create
	public void init() {
		log.info("-----init()-----");
		refreshNhanVienThuNgan();
		log.info("nhanVienThungan1"+nhanVienThungan);
		PcCumThuPhiDelegate pcCumThuPhiDelegate  = PcCumThuPhiDelegate.getInstance();
		PcCumThuPhi pc =  pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), nhanVienThungan.getDtdmnhanvienMa());
		
		log.info("PcCumThuPhi"+pc);
		
		
		if (pc!=null && pc.getDtdmnhanvienMa() != null){
			
			//nhanVienThungan = pc.getDtdmnhanvienMa();
			log.info("nhanVienThungan:"+nhanVienThungan);
			cum = pc.getDtdmcumMa();
		}
		emtyData();
	}
	
	private void emtyData(){
		log.info("begin emptyData()");
		benhNhan = new BenhNhan();
//		SetInforUtil.setInforIfNullForBN(benhNhan);
		hsbaBhyt = new HsbaBhyt();
//		SetInforUtil.setInforIfNullForHSBABHYT(hsbaBhyt);
		hsba = new Hsba();
//		SetInforUtil.setInforIfNullForHSBA(hsba);
		hsbaChuyenvien = new HsbaChuyenVien();
//		SetInforUtil.setInforIfNullForHsbaChuyenVien(hsbaChuyenvien);
//		hsbaChuyenMon = new HsbaChuyenMon();
		
//		SetInforUtil.setInforIfNullForHSBACM(hsbaChuyenMon);
//		hsbaChuyenMon.setHsbaSovaovien(hsba);
		
		hsThtoan = new HsThtoan();
//		SetInforUtil.setInforIfNullForHsThtoan(hsThtoan);
	
		tuoi = "";
		soThe = "";
		ngayNhapvien = "";
		
		
		
		cacKhoadt = "";
		dtNoi = false;
		dtNgoai = false;
		dtDongy = false;
		
		log.info("end emptyData()");
		
	
		
	}
	// Ham reset form 
	private void ResetForm() {
		log.info("Begining ResetForm(): ");
		benhNhan = new BenhNhan();
//		SetInforUtil.setInforIfNullForBN(benhNhan);
		hsba = new Hsba();
//		SetInforUtil.setInforIfNullForHSBA(hsba);
		hsbaBhyt = new HsbaBhyt();
//		SetInforUtil.setInforIfNullForHSBABHYT(hsbaBhyt);
//		hsbaChuyenMon = new HsbaChuyenMon();
//		SetInforUtil.setInforIfNullForHSBACM(hsbaChuyenMon);
		
//		hsbaChuyenMon.setHsbaSovaovien(hsba);
		
		hsbaChuyenvien = new HsbaChuyenVien();
//		SetInforUtil.setInforIfNullForHsbaChuyenVien(hsbaChuyenvien);
		hsThtoan = new HsThtoan();
//		SetInforUtil.setInforIfNullForHsThtoan(hsThtoan);
		ngayNhapvien = "";
		gioi = "";
		tuoi = "";
		soThe = "";
		
		ungTra = new Double(0);
		
		dtNoi = new Boolean(false);
		dtNgoai = new Boolean(false);
		dtNoi = new Boolean(false);
		
		this.cacKhoadt = "";
		log.info("End ResetForm(): ");
	}
	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() throws Exception {
		
	}
	// Ham khi nhan nut nhap lai
	public void nhaplai() throws Exception {
		try {
			ResetForm();
			
			 hienThiGhiNhan="";		
				hienThiInPhieu="";
			
		} catch (Exception e) {
			log.error("Loi reset form : " + e.toString());
		}
	}

	public String inphieu(String loaiFile){
		
		return null;
	}
	private void hasNoKhoaOrSoVaoVien(){
		emtyData();
	}
	
	private void tinhToanChoHSTT(HsThtoan hstt){
		HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
		hsthtoanUtil.tinhToanChoHSTT(hstt);
		 
	}
	private Integer khoaMaso;
	private String khoaMa;
	// Hien thi thong tin 
	// Hien thi thong tin
	public void displayInfor() throws Exception {
		try {

			if (khoaMa == null || khoaMa.equals("")
					|| hsba.getHsbaSovaovien() == null
					|| hsba.getHsbaSovaovien().equals("")) {
				hasNoKhoaOrSoVaoVien();
				return;

			}

			HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
			
			// 20120312 bao.ttc: load truc tiep HSBA -- Giao dien: disable Khoa
			HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienLastHsbaKhoa(hsba.getHsbaSovaovien());
			if (hsbaKhoa == null) {
				log.info("hsbaKhoa1:" + hsbaKhoa);
				FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, "", "");
				ResetForm();
				return;
			}
			
			// kiem tra khoaMa co' phai la khoa cuoi cung ko
			log.info("maKhoa:" + khoaMa);
			if (!khoaMa.equals(hsbaKhoa.getKhoaMa().getDmkhoaMa())) {
				log.info("hsbaKhoa1:" + hsbaKhoa);
				FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, "", "");
				ResetForm();
				return;
			}

			log.info("Begining displayInfor()");
			
			// 20120312 bao.ttc: tim list cac khoa DT can in ca Tang de phan biet
			List<HsbaKhoa> lstHsbaKhoa = hsbaKhoaDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
				for (HsbaKhoa hK : lstHsbaKhoa) {
					cacKhoadt += hK.getKhoaMa().getDmkhoaMa() + "   ";
				}

				hsba = hsbaKhoa.getHsbaSovaovien();

//				SetInforUtil.setInforIfNullForHSBA(hsba);
				if (hsba.getBenhnhanMa() != null) {
					benhNhan = hsba.getBenhnhanMa();
				} else {
					benhNhan = new BenhNhan();
				}

//				SetInforUtil.setInforIfNullForBN(benhNhan);
				if (hsba.getHsbaSovaovien() != null
						&& !hsba.getHsbaSovaovien().equals("")) {
					try {
						// HsbaBhytWSService hsbabhytService = new
						// HsbaBhytWSServiceLocator();
						// HsbaBhytWS hsbabhytWS =
						// hsbabhytService.gethsbaBhytWSPort();

						HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate
								.getInstance();
						HsbaBhyt hsbaBhyt_temp = hsbaBhytDelegate
								.findBySoVaoVienLastHsbaBhyt(hsba
										.getHsbaSovaovien());
						if (hsbaBhyt_temp != null) {
							hsbaBhyt = hsbaBhyt_temp;
						} else {
							hsbaBhyt = new HsbaBhyt();
						}
//						SetInforUtil.setInforIfNullForHSBABHYT(hsbaBhyt);
					} catch (Exception ex) {
						log.error("Loi trong khi load HSBABHYT : "
								+ ex.toString());
					}
					try {
						// HsbaChuyenVienWSService hsbacvService = new
						// HsbaChuyenVienWSServiceLocator();
						// HsbaChuyenVienWS hsbacvWS =
						// hsbacvService.getHsbaChuyenVienWSPort();

						HsbaChuyenVienDelegate hsbacvDelegate = HsbaChuyenVienDelegate
								.getInstance();

						HsbaChuyenVien hsbaChuyenVien_temp = hsbacvDelegate
								.findBySoVaoVien(hsba.getHsbaSovaovien());
						if (hsbaChuyenVien_temp != null) {
							hsbaChuyenvien = hsbaChuyenVien_temp;
						} else {
							hsbaChuyenvien = new HsbaChuyenVien();
						}
//						SetInforUtil
//								.setInforIfNullForHsbaChuyenVien(hsbaChuyenvien);
					} catch (Exception ex) {
						log.error("Loi trong khi load Hsba chuyen vien : "
								+ ex.toString());
					}

				} else {
					hsbaBhyt = new HsbaBhyt();
//					SetInforUtil.setInforIfNullForHSBABHYT(hsbaBhyt);
					hsbaChuyenvien = new HsbaChuyenVien();
//					SetInforUtil
//							.setInforIfNullForHsbaChuyenVien(hsbaChuyenvien);
				}

				try {

					HsThtoanDelegate hsttDelegate = HsThtoanDelegate
							.getInstance();
					HsThtoan hsbaHsThtoan_temp = hsttDelegate
							.findBySovaovien(hsba.getHsbaSovaovien());
					if (hsbaHsThtoan_temp != null) {
						hsThtoan = hsbaHsThtoan_temp;

						// hienThiGhiNhan="";
						// hienThiInPhieu="";
						// FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
						log.error("Tim thay ho so thanh toan : " + hsThtoan);
					} else {
						hsThtoan = new HsThtoan();
						hsThtoan.setHsbaSovaovien(hsba);

						tinhToanChoHSTT(hsThtoan);

						Utils.setInforForHsThToan(hsThtoan);

						log.info("hsThtoan.getHsthtoanTamung():"
								+ hsThtoan.getHsthtoanTamung());
						log.info("hsThtoan.getHsthtoanHoanung():"
								+ hsThtoan.getHsthtoanHoanung());

						ungTra = hsThtoan.getHsthtoanTamung()
								- hsThtoan.getHsthtoanHoanung();
						log.info("ungTra:" + ungTra);
					}
//					SetInforUtil.setInforIfNullForHsThtoan(hsThtoan);
					hienThiGhiNhan = "true";
					hienThiInPhieu = "";
				} catch (Exception ex) {
					log.error("Loi trong khi load ho so thanh toan : "
							+ ex.toString());
					ex.printStackTrace();
				}

				setOtherValue();
			
		} catch (Exception e) {
			log.error((new StringBuilder()).append(
					"error on function displayInfor").append(e).toString());

		}
		log.info("End displayInfor()");
	}
	
	private void setOtherValue() {
		log.info("Begining setOtherValue()");
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		if (benhNhan.getBenhnhanNgaysinh() !=  null && !benhNhan.getBenhnhanNgaysinh().equals("")) {
			Calendar cal = Calendar.getInstance();
			Integer tuoi_temp = cal.getTime().getYear() - benhNhan.getBenhnhanNgaysinh().getYear();
			tuoi = tuoi_temp.toString();
			log.info("Tuoi  :" + tuoi);
		}
		 if (benhNhan.getDmgtMaso() != null){
				if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())){
					gioi = "r1";  //1 : Name
				}else{
					gioi = "r2";
				}					
			}else{
				gioi = null;
			}
		if (hsbaBhyt.getHsbabhytSothebh() != null || hsbaBhyt.getHsbabhytSothengheo() != null) {
			if (hsbaBhyt.getHsbabhytSothebh() != null ) {
				soThe = hsbaBhyt.getHsbabhytSothebh();
			}
			else {
				soThe = hsbaBhyt.getHsbabhytSothengheo();
			}
		}
		else {
			soThe = "";
		}
		if (hsba.getHsbaNgaygiovaov() != null ) {
			ngayNhapvien = formatter.format(hsba.getHsbaNgaygiovaov().getTime());
		}
		else {
			ngayNhapvien= "";
		}
		
		
		
		
		log.info("End setOtherValue()");
}
	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

	public HsbaBhyt getHsbaBhyt() {
		return hsbaBhyt;
	}

	public void setHsbaBhyt(HsbaBhyt hsbaBhyt) {
		this.hsbaBhyt = hsbaBhyt;
	}

	public Hsba getHsba() {
		return hsba;
	}

	public void setHsba(Hsba hsba) {
		this.hsba = hsba;
	}

	public HsbaChuyenVien getHsbaChuyenvien() {
		return hsbaChuyenvien;
	}

	public void setHsbaChuyenvien(HsbaChuyenVien hsbaChuyenvien) {
		this.hsbaChuyenvien = hsbaChuyenvien;
	}

//	public HsbaChuyenMon getHsbaChuyenMon() {
//		return hsbaChuyenMon;
//	}
//
//	public void setHsbaChuyenMon(HsbaChuyenMon hsbaChuyenMon) {
//		this.hsbaChuyenMon = hsbaChuyenMon;
//	}

	public HsThtoan getHsThtoan() {
		return hsThtoan;
	}

	public void setHsThtoan(HsThtoan hsThtoan) {
		this.hsThtoan = hsThtoan;
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

	public String getSoThe() {
		return soThe;
	}

	public void setSoThe(String soThe) {
		this.soThe = soThe;
	}

	public String getNgayNhapvien() {
		return ngayNhapvien;
	}

	public void setNgayNhapvien(String ngayNhapvien) {
		this.ngayNhapvien = ngayNhapvien;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		ThanhToanBNBoVien.log = log;
	}

	public String getCacKhoadt() {
		return cacKhoadt;
	}

	public void setCacKhoadt(String cacKhoadt) {
		this.cacKhoadt = cacKhoadt;
	}

	public boolean isDtNoi() {
		return dtNoi;
	}

	public void setDtNoi(boolean dtNoi) {
		this.dtNoi = dtNoi;
	}

	public boolean isDtNgoai() {
		return dtNgoai;
	}

	public void setDtNgoai(boolean dtNgoai) {
		this.dtNgoai = dtNgoai;
	}

	public boolean isDtDongy() {
		return dtDongy;
	}

	public void setDtDongy(boolean dtDongy) {
		this.dtDongy = dtDongy;
	}

	public String getHienThiGhiNhan() {
		return hienThiGhiNhan;
	}

	public void setHienThiGhiNhan(String hienThiGhiNhan) {
		this.hienThiGhiNhan = hienThiGhiNhan;
	}

	public String getHienThiInPhieu() {
		return hienThiInPhieu;
	}

	public void setHienThiInPhieu(String hienThiInPhieu) {
		this.hienThiInPhieu = hienThiInPhieu;
	}

	public String getNgayTtoan() {
		return ngayTtoan;
	}

	public void setNgayTtoan(String ngayTtoan) {
		this.ngayTtoan = ngayTtoan;
	}

	public Double getUngTra() {
		return ungTra;
	}

	public void setUngTra(Double ungTra) {
		this.ungTra = ungTra;
	}

	public DtDmCum getCum() {
		return cum;
	}

	public void setCum(DtDmCum cum) {
		this.cum = cum;
	}

	public DtDmNhanVien getNhanVienThungan() {
		return nhanVienThungan;
	}

	public void setNhanVienThungan(DtDmNhanVien nhanVienThungan) {
		this.nhanVienThungan = nhanVienThungan;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public Integer getKhoaMaso() {
		return khoaMaso;
	}

	public void setKhoaMaso(Integer khoaMaso) {
		this.khoaMaso = khoaMaso;
	}

	public String getKhoaMa() {
		return khoaMa;
	}

	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}



}
