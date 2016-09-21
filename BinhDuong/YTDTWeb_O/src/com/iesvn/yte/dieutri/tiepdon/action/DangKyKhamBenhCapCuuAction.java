package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.entity.BenhNhanTrungTheBhytDTO;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(SESSION)
@Name("B131_Dangkykhambenhcapcuu")
@Synchronized(timeout = 6000000)
public class DangKyKhamBenhCapCuuAction implements Serializable {

	private static final long serialVersionUID = 5156076455688325728L;
	private static Logger log = Logger.getLogger(DangKyKhamBenhCapCuuAction.class);
	
	@In(required = false)
	@Out(required = false)
	Identity identity;

	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
    private YteLog yteLog ;
    private String listDataLog = "";
	
	private TiepDon tiepdon;
	private String gioTiepDon;
	private String ngayTiepDon;
	private String gioi;
	private String ngaysinh;
	private String bhytGiatri1;
	private String bhytGiatri2;
	private String bhytGiatri3;
	private String bhytGiatri4;
	//20110119 bao.ttc: them Moc 1-2-3
	private String bhytMoc1;
	private String bhytMoc2;
	private String bhytMoc3;
	
	private String donvituoi;
	private String nofound;
	private List<BenhNhanTrungTheBhytDTO> listBN; 
	private boolean showListBn;
	private boolean lockDoituong;
	private boolean lockBanKham;
	private String doituongHientai;
	private String ngayhientai = "";
	private String listMaTinhBhyt;
	private String hosoDaTT = "false";
	// 20101228 bao.ttc: remove vi da Restrict tai pages.xml
	// @Restrict("#{s:hasRole('NV_PhongKham') or s:hasRole('QT_PhongKham')}")
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strDKyCapCuu;
	@Factory("strDKyCapCuu")
	public void init() {
		resetValue();
	}

	public void resetValue() {
		log.info("---resetValue");
		tiepdon = new TiepDon();
		//tiepdon.setTiepdonBacsi(DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername()));
		String phongkhamccDefault = IConstantsRes.PHONGKHAMCC_DEFAULT;
		if (phongkhamccDefault != null && !phongkhamccDefault.equals("")) {
			tiepdon.getTiepdonBankham(true).setDtdmbankhamMa(phongkhamccDefault);
		}
		gioTiepDon = Utils.getGioPhut(new Date());
		ngayTiepDon = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		String donvituoiDefault = IConstantsRes.DON_VI_TUOI_DEFAULT;
		if (donvituoiDefault != null && !donvituoiDefault.equals("")){
			donvituoi = donvituoiDefault;	
		}
		bhytGiatri1 = "";
		bhytGiatri2 = "";
		bhytGiatri3 = "";
		bhytGiatri4 = "";
		bhytMoc1 = "";
		bhytMoc2 = "";
		bhytMoc3 = "";
		
		gioi = "true";
		
		nofound="false";
		listBN = new ArrayList<BenhNhanTrungTheBhytDTO>();
		showListBn = false;
		lockDoituong = false;
		lockBanKham = false;
		doituongHientai = "";
		ngayhientai = Utils.getCurrentDate();
		strDKyCapCuu = "";
		// Lay danh muc tinh de tao listMaTinhBhyt
		List<DmTinh> listDmTinh = DieuTriUtilDelegate.getInstance().findAll("DmTinh");		
		listMaTinhBhyt = "";
		for(DmTinh each : listDmTinh) {
			listMaTinhBhyt += each.getDmtinhBHYT() + ",";
		}
		hosoDaTT = "false";
	}
	private boolean checkTrungMaBnInListBn(String maBN, List<BenhNhanTrungTheBhytDTO> listBN) {		
		if (listBN == null) return false;
		if (listBN.size() < 1 ) return false;
		for(BenhNhanTrungTheBhytDTO eachBN : listBN) {			
			if(eachBN.getBnMa().equals(maBN)) {
				return true;
			}
		}
		return false;
	}
	// 20110329 bao.ttc: neu co nhap ten BN thi kiem tra xem so the BH da ton tai hay chua; TH ko nhap ten BN thi search lai thong tin cua the BH 
	public void checkSoTheBHYT() {
		String sothe = tiepdon.getTiepdonSothebh().trim();
		if (sothe.equals("")){
			return;
		}
		//phuc.lc 09/08/2011
		// Kiem tra so the bao hiem da co hay chua
		// Neu so the bao hiem da co va thong so cau hinh CHO_PHEP_TRUNG_SO_THE_BHYT = NO thi hien thi thong tin tim duoc theo so the len giao dien
		// Neu so the bao hiem da co va thong so cau hinh CHO_PHEP_TRUNG_SO_THE_BHYT = YES thi hien thi danh sach cac benh nhan trung so the bao hiem
		// Nguoi dung neu chon nhap moi thi cho phep nhan them benh nhan moi co cung so the bao hiem
		// Neu nguoi dung chon 1 benh nhan trong danh sach thi load lai thong tin cua benh nhan do de tao tiep don moi
		showListBn = false;
		List<TiepDon> listTD = TiepDonDelegate.getInstance().findBySoTheBHYT(sothe);
		if (listTD != null && listTD.size() > 0) {
			if (IConstantsRes.CHO_PHEP_TRUNG_SO_THE_BHYT.equals("NO")) {
				// Neu he thong khong cho phep trung so the, thi hien thi thong tin benh nhan cu va cho phep nhap tiep don moi
				tiepdon = listTD.get(0);
				tiepdon.setTiepdonNgaygio(null); // 20120530: set NULL truoc khi display de van giu ngay gio hien tai
				displayTiepDon(tiepdon);
				tiepdon.setTiepdonMa(null);
				tiepdon.setTainanMa(null); tiepdon.setTiepdonDonvigoi(null);
				tiepdon.setTiepdonThutu(null); tiepdon.setTiepdonBntra(null); tiepdon.setTiepdonLoaikham(null);
				
				tiepdon.setTiepdonTylebh(null); tiepdon.setTiepdonBacsi(null);
				tiepdon.setTiepdonKyhieu(null); tiepdon.setTiepdonQuyen(null); tiepdon.setTiepdonBienlai(null);
				tiepdon.setTiepdonNhanviencn(null); tiepdon.setTiepdonThungan(null);
				
				tiepdon.setDmptgtnMaso(null); tiepdon.setNguyenhanMa(null); tiepdon.setDiadiemMa(null);
				tiepdon.setTiepdonRuoubia(null); tiepdon.setTiepdonLydovaov(null);
				tiepdon.setTiepdonMach(null); tiepdon.setTiepdonMachdoanbd(null);
				
				tiepdon.setTiepdonCodoimu(null);
				tiepdon.setTiepdonMuvo(null);
				tiepdon.setTiepdonKhongcaiday(null);
				tiepdon.setTiepdonKhongronguongoc(null);
				
				tiepdon.setTiepdonTrluong(null); tiepdon.setTiepdonChieucao(null); tiepdon.setTiepdonNhommau(null);
				tiepdon.setTiepdonNhietdo(null); tiepdon.setTiepdonNhiptho(null); tiepdon.setTiepdonHamax(null);
				tiepdon.setTiepdonHamin(null); tiepdon.setTiepdonRh(null); tiepdon.setTiepdonBaotin(null);
				
				tiepdon.setKetquaMa(null); tiepdon.setDieutriMa(null); tiepdon.setTiepdonNghe(null);
				tiepdon.setTiepdonChkhoa(null); tiepdon.setTiepdonChvien(null); tiepdon.setTiepdonBschuyen(null);
				tiepdon.setTiepdonLydochvi(null); tiepdon.setTiepdonTuvong(null); tiepdon.setTiepdonCum(null);
				
				tiepdon.setTiepdonSoTT(null); tiepdon.setTiepdonCoGiayGioiThieu(false); tiepdon.setTiepdonKhamDaLieu(false);
				
				DtDmBanKham bkcc = new DtDmBanKham(tiepdon.getTiepdonBankham(true).getDtdmbankhamMaso());
				if (bkcc != null) {
					tiepdon.setTiepdonBankham(bkcc);
				} else {
					tiepdon.setTiepdonBankham(new DtDmBanKham(Integer.parseInt(IConstantsRes.MA_BAN_KHAM_CAP_CUU_1)));
				}
			} else {				
				// Neu cho phep trung so the, thi hien thi danh sach benh nhan trung so the de lay thong tin benh nhan cu hoac nhap benh nhan moi
				if(listBN == null) {
					listBN = new ArrayList<BenhNhanTrungTheBhytDTO>();
				} else {
					listBN.clear();
				}
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");				
				for(TiepDon eachTD : listTD) {
					if (eachTD.getBenhnhanMa() != null) {
						// Kiem tra ma benh nhan da co trong listBN hay chua?
						// Neu chua co thi add benh nhan vao list, neu co roi thi khong add nua
						if (!checkTrungMaBnInListBn(eachTD.getBenhnhanMa().getBenhnhanMa(), listBN)) {
							BenhNhanTrungTheBhytDTO bn = new BenhNhanTrungTheBhytDTO();
							bn.setTiepdonMa(eachTD.getTiepdonMa());
							bn.setBnMa(eachTD.getBenhnhanMa().getBenhnhanMa());
							bn.setBnHoTen(eachTD.getBenhnhanMa().getBenhnhanHoten());
							bn.setBnGioiTinh(eachTD.getBenhnhanMa().getDmgtMaso().getDmgtTen());
							bn.setBnNamSinh(eachTD.getBenhnhanMa().getBenhnhanNamsinh());
							log.info("eachTD.getKcbbhytMa() = " + eachTD.getKcbbhytMa());
							bn.setNoiDkKcb(eachTD.getKcbbhytMa() == null ? "" : eachTD.getKcbbhytMa().getDmbenhvienTen());
							bn.setThoiHanThe((eachTD.getTiepdonGiatri1() == null ? "" : formatter.format(eachTD.getTiepdonGiatri1())) + " - " + (eachTD.getTiepdonGiatri2() == null ? "" : formatter.format(eachTD.getTiepdonGiatri2())));
							listBN.add(bn);
						}
					}
				}
				if(listBN.size() > 0) {
					showListBn = true;
				}
				
			}
			FacesMessages.instance().add(IConstantsRes.SOTHEBHYT_DATONTAI, sothe);
		}
		/* ################################### */
	    /*
		
		TiepDon td = BenhNhanDelegate.getInstance().findBySoTheBHYT(sothe);
		
		if ( tiepdon.getBenhnhanMa(true).getBenhnhanHoten() != null && !tiepdon.getBenhnhanMa(true).getBenhnhanHoten().equals("") ) {
			if (td == null) {
				return;
			} else {
				FacesMessages.instance().add(IConstantsRes.SOTHEBHYT_DATONTAI, sothe);
				tiepdon.setTiepdonSothebh("");
			}
			
		} else { // TH nguoi dung muon search so the BH
			if (td == null) {
				FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_SO_THE_BHYT, sothe);
			} else {
				
				td.setTiepdonMa(null); td.setTiepdonNgaygio(null);
				td.setTainanMa(null); td.setTiepdonDonvigoi(null);
				td.setTiepdonThutu(null); td.setTiepdonBntra(null); td.setTiepdonLoaikham(null);
				
				td.setTiepdonTylebh(null); td.setTiepdonBacsi(null);
				td.setTiepdonKyhieu(null); td.setTiepdonQuyen(null); td.setTiepdonBienlai(null);
				td.setTiepdonNhanviencn(null); td.setTiepdonThungan(null);
				
				td.setDmptgtnMaso(null); td.setNguyenhanMa(null); td.setDiadiemMa(null);
				td.setTiepdonRuoubia(null); td.setTiepdonLydovaov(null);
				td.setTiepdonMach(null); td.setTiepdonMachdoanbd(null);
				
				td.setTiepdonTrluong(null); td.setTiepdonChieucao(null); td.setTiepdonNhommau(null);
				td.setTiepdonNhietdo(null); td.setTiepdonNhiptho(null); td.setTiepdonHamax(null);
				td.setTiepdonHamin(null); td.setTiepdonRh(null); td.setTiepdonBaotin(null);
				
				td.setKetquaMa(null); td.setDieutriMa(null); td.setTiepdonNghe(null);
				td.setTiepdonChkhoa(null); td.setTiepdonChvien(null); td.setTiepdonBschuyen(null);
				td.setTiepdonLydochvi(null); td.setTiepdonTuvong(null); td.setTiepdonCum(null);
				
				td.setTiepdonSoTT(null); td.setTiepdonCoGiayGioiThieu(false); td.setTiepdonKhamDaLieu(false);
				
				DtDmBanKham bkcc = new DtDmBanKham(tiepdon.getTiepdonBankham(true).getDtdmbankhamMaso());
				if (bkcc != null) {
					td.setTiepdonBankham(bkcc);
				} else {
					td.setTiepdonBankham(new DtDmBanKham(Integer.parseInt(IConstantsRes.MA_BAN_KHAM_CAP_CUU_1)));
				}
				
				tiepdon = td;
				displayTiepDon(tiepdon);
				
			}
			
		}
		*/
		
	}
	public void nhapBnMoi() {				
		FacesMessages.instance().clear();
		tiepdon.getBenhnhanMa().setBenhnhanMa("");
		showListBn = false;
		lockDoituong = false;
		lockBanKham = false;
	}
	public void layThongTinBnCu(int index) {
		log.info("Begin layThongTinBnCu, index = " + index);
		tiepdon = TiepDonDelegate.getInstance().findBenhNhanByTiepdonMa(listBN.get(index).getTiepdonMa());
		displayTiepDon(tiepdon);
		gioTiepDon = Utils.getGioPhut(new Date());
		ngayTiepDon = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		tiepdon.setTiepdonMa(null); tiepdon.setTiepdonNgaygio(null);
		tiepdon.setTainanMa(null); tiepdon.setTiepdonDonvigoi(null);
		tiepdon.setTiepdonThutu(null); tiepdon.setTiepdonBntra(null); tiepdon.setTiepdonLoaikham(null);
		
		tiepdon.setTiepdonTylebh(null); tiepdon.setTiepdonBacsi(null);
		tiepdon.setTiepdonKyhieu(null); tiepdon.setTiepdonQuyen(null); tiepdon.setTiepdonBienlai(null);
		tiepdon.setTiepdonNhanviencn(null); tiepdon.setTiepdonThungan(null);
		
		tiepdon.setDmptgtnMaso(null); tiepdon.setNguyenhanMa(null); tiepdon.setDiadiemMa(null);
		tiepdon.setTiepdonRuoubia(null); tiepdon.setTiepdonLydovaov(null);
		tiepdon.setTiepdonMach(null); tiepdon.setTiepdonMachdoanbd(null);
		
		tiepdon.setTiepdonCodoimu(null);
		tiepdon.setTiepdonMuvo(null);
		tiepdon.setTiepdonKhongcaiday(null);
		tiepdon.setTiepdonKhongronguongoc(null);
		
		tiepdon.setTiepdonTrluong(null); tiepdon.setTiepdonChieucao(null); tiepdon.setTiepdonNhommau(null);
		tiepdon.setTiepdonNhietdo(null); tiepdon.setTiepdonNhiptho(null); tiepdon.setTiepdonHamax(null);
		tiepdon.setTiepdonHamin(null); tiepdon.setTiepdonRh(null); tiepdon.setTiepdonBaotin(null);
		
		tiepdon.setKetquaMa(null); tiepdon.setDieutriMa(null); tiepdon.setTiepdonNghe(null);
		tiepdon.setTiepdonChkhoa(null); tiepdon.setTiepdonChvien(null); tiepdon.setTiepdonBschuyen(null);
		tiepdon.setTiepdonLydochvi(null); tiepdon.setTiepdonTuvong(null); tiepdon.setTiepdonCum(null);
		
		tiepdon.setTiepdonSoTT(null); tiepdon.setTiepdonCoGiayGioiThieu(false); tiepdon.setTiepdonKhamDaLieu(false);
		
		DtDmBanKham bkcc = new DtDmBanKham(tiepdon.getTiepdonBankham(true).getDtdmbankhamMaso());
		if (bkcc != null) {
			tiepdon.setTiepdonBankham(bkcc);
		} else {
			tiepdon.setTiepdonBankham(new DtDmBanKham(Integer.parseInt(IConstantsRes.MA_BAN_KHAM_CAP_CUU_1)));
		}
		nofound = "false";
		FacesMessages.instance().clear();
		showListBn = false;
		lockDoituong = false;
		lockBanKham = false;
	}
	private void displayTiepDon(TiepDon td){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date ngayGio = tiepdon.getTiepdonNgaygio();
		if (ngayGio != null) {
			ngayTiepDon = formatter.format(ngayGio.getTime());
			gioTiepDon =  Utils.getGioPhut(ngayGio) ;
		}
		
		Date dateBhytGiatri1 = tiepdon.getTiepdonGiatri1();
		if (dateBhytGiatri1 != null){
			bhytGiatri1 = formatter.format(dateBhytGiatri1.getTime());
		}
		
		Date dateBhytGiatri2 = tiepdon.getTiepdonGiatri2();
		if (dateBhytGiatri2 != null){
			bhytGiatri2 = formatter.format(dateBhytGiatri2.getTime());
		}
		
		Date dateBhytGiatri3 = tiepdon.getTiepdonGiatri3();
		if (dateBhytGiatri3 != null){
			bhytGiatri3 = formatter.format(dateBhytGiatri3.getTime());
		}
		
		Date dateBhytGiatri4 = tiepdon.getTiepdonGiatri4();
		if (dateBhytGiatri4 != null){
			bhytGiatri4 = formatter.format(dateBhytGiatri4.getTime());
		}
		
		if (tiepdon.getTiepdonMoc1() != null){
			bhytMoc1 = formatter.format(tiepdon.getTiepdonMoc1());
		}
		
		if (tiepdon.getTiepdonMoc2() != null){
			bhytMoc2 = formatter.format(tiepdon.getTiepdonMoc2());
		}
		
		if (tiepdon.getTiepdonMoc3() != null){
			bhytMoc3 = formatter.format(tiepdon.getTiepdonMoc3());
		}
		
		
		BenhNhan bn_tmp = tiepdon.getBenhnhanMa();
		if (bn_tmp!=null){
			if (bn_tmp.getDmgtMaso() != null) {
				gioi = "1".equals(bn_tmp.getDmgtMaso().getDmgtMa()) ? "true" : "false";
			} else {
				gioi = null;
			}
			Short dvt = bn_tmp.getBenhnhanDonvituoi(); 
			donvituoi = (dvt != null)? String.valueOf(dvt):null; 
			ngaysinh = bn_tmp.getBenhnhanNgaysinh() != null && !bn_tmp.getBenhnhanNgaysinh().equals("") ? formatter.format(bn_tmp.getBenhnhanNgaysinh().getTime()): null;
		}
		
		
	}
	private void checkThamkham(String maTD) {
		ThamKhamDelegate tkDel = ThamKhamDelegate.getInstance();
		List<ThamKham> listTk = tkDel.findAllByMaTiepDon(maTD);
		lockBanKham = (listTk != null && listTk.size() > 1);		
	}
	public void displayTiepDonCC(boolean flag) {
		log.info("---displayTiepDonCC");
		String ma = tiepdon.getTiepdonMa();
		String mabn = tiepdon.getBenhnhanMa(true).getBenhnhanMa();
		String maBanKham = tiepdon.getTiepdonBankham(true).getDtdmbankhamMa();
		if (ma != null && maBanKham !=null) {
			
			TiepDon tiepdon_tmp = null;
			if (flag) {
				tiepdon_tmp = TiepDonDelegate.getInstance().findTiepDonCCByMaTiepDon(ma, maBanKham);
			}else{
				tiepdon_tmp = TiepDonDelegate.getInstance().findTiepDonCCByMaBenhNhan(mabn, maBanKham);
			}
			if (tiepdon_tmp != null) {				
				tiepdon = tiepdon_tmp;
				
				displayTiepDon(tiepdon);
				
				HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
				// Ham findBytiepdonMa tra ve null hoac ho so da thanh toan
				HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(tiepdon.getTiepdonMa());			
				hosoDaTT = "false";
				if(hsttk != null) {
					hosoDaTT = "true";
					FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
				} else if (tiepdon.getTiepdonChkhoa() != null && !tiepdon.getTiepdonChkhoa().equals("")){
					FacesMessages.instance().add(IConstantsRes.DA_CHUYEN_VAO_NOI_TRU);
					hosoDaTT = "true";
				}
			}else{
				nofound="true";
				if (flag){
					FacesMessages.instance().add(IConstantsRes.TDCC_NULL_MATD, ma );
				}else{
					FacesMessages.instance().add(IConstantsRes.TDCC_NULL_MABN, mabn);
				}
			}
		}
		showListBn = false;
		listBN = new ArrayList<BenhNhanTrungTheBhytDTO>();
		lockDoituong = false;
		doituongHientai = tiepdon.getDoituongMa().getDmdoituongMa();
		//Kiem tra CLS va Thuoc, neu da co su dung thi khong cho thay doi doi tuong kham benh
		List<ClsKham> listClsKham = ClsKhamDelegate.getInstance().findByTiepdonma(ma);
		List<ThuocPhongKham> listTpk = ThuocPhongKhamDelegate.getInstance().find2LoaiByMaTiepDon(tiepdon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);		
		if((listClsKham != null && listClsKham.size() > 0) || (listTpk != null && listTpk.size() > 0)) {
			lockDoituong = true;
		}
		// Kiem tra neu co nhieu tham kham thi khong cho doi ban kham
		checkThamkham(tiepdon.getTiepdonMa());
		log.info("End displayTiepDonCC, lockDoituong = " + lockDoituong + ", lockBanKham = " + lockBanKham);
	}
	
	public void ghiNhan() {
		log.info("ghiNhan");
		yteLog = new YteLog();
		listDataLog = "";
		String logBHYTT = "";
		SimpleDateFormat dateFormatFull = new SimpleDateFormat(Utils.FORMAT_DATE_TIME_HOUR_FIRST);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		//phuc.lc 28/10/2010 : Khi database gears khong dong bo voi server
		// co the se xay ra truong hop cac danh muc chon duoc tren giao dien nhung luu xuong lai bi null		
		// duoi day la kiem tra truong hop tinh cap the BHYT va noi dang ky KCB bi null (bug 4064)
		DieuTriUtilDelegate utilDel = DieuTriUtilDelegate.getInstance();
		if(tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH")) {
			
			logBHYTT += tiepdon.getTiepdonSothebh();
			// Kiem tra tinh cap BHYT
			if (tiepdon.getTinhbhytMa(true).getDmtinhMaso() != null) {
				DmTinh tinhBhyt = (DmTinh) utilDel.findByMaSo(tiepdon.getTinhbhytMa(true).getDmtinhMaso(), "DmTinh", "dmtinhMaso");
				if(tinhBhyt == null) {
					FacesMessages.instance().add(IConstantsRes.NOT_FOUND, IConstantsRes.TINH_CAP_BHYT, tiepdon.getTinhbhytMa(true).getDmtinhBHYT());
					return;
				} else {
					tiepdon.setTinhbhytMa(tinhBhyt);
					logBHYTT += " Tinh BHYT: "+ tiepdon.getTinhbhytMa(true).getDmtinhMa();
				}
			} else {
				FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, IConstantsRes.TINH_CAP_BHYT);
				return;
			}
			// Kiem tra noi DK Kham chua benh
			if (tiepdon.getKcbbhytMa(true).getDmbenhvienMaso() != null) {
				DmBenhVien noiKCB = (DmBenhVien) utilDel.findByMa(tiepdon.getKcbbhytMa(true).getDmbenhvienMa(), "DmBenhVien", "dmbenhvienMa");
				if(noiKCB == null) {
					FacesMessages.instance().add(IConstantsRes.NOT_FOUND, IConstantsRes.NOI_DK_KCB, tiepdon.getKcbbhytMa(true).getDmbenhvienMa());
					return;
				} else {
					tiepdon.setKcbbhytMa(noiKCB);
					logBHYTT+=" KCB BHYT: "+ tiepdon.getKcbbhytMa(true).getDmbenhvienMa();
				}
			} else {
				FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, IConstantsRes.NOI_DK_KCB);
				return;
			}
		}
		//phuc.lc 28/10/2010 : End
		TiepDonDelegate delegate = TiepDonDelegate.getInstance();
		
			
		if (gioi == null || gioi.equals("")) {
			tiepdon.getBenhnhanMa(true).setDmgtMaso(null);
		} else if ("true".equals(gioi)) { // Nam
			DmGioiTinh gioiTinh = new DmGioiTinh();
			gioiTinh.setDmgtMaso(2);
			gioiTinh.setDmgtMa("1");
			tiepdon.getBenhnhanMa(true).setDmgtMaso(gioiTinh);
		} else { // Nu
			DmGioiTinh gioiTinh = new DmGioiTinh();
			gioiTinh.setDmgtMaso(1);
			gioiTinh.setDmgtMa("0");
			tiepdon.getBenhnhanMa(true).setDmgtMaso(gioiTinh);
		}
		listDataLog+= " GT: "+ tiepdon.getBenhnhanMa(true).getDmgtMaso(true).getDmgtMa();
		if (donvituoi == null || donvituoi.equals("")){
			tiepdon.getBenhnhanMa(true).setBenhnhanDonvituoi(null);
		}else{
			tiepdon.getBenhnhanMa(true).setBenhnhanDonvituoi(new Short(donvituoi));
		}
		listDataLog+= " DV Tuoi: "+ tiepdon.getBenhnhanMa(true).getBenhnhanDonvituoi();
		if (ngaysinh != null && !ngaysinh.equals("")) {
			tiepdon.getBenhnhanMa(true).setBenhnhanNgaysinh(Utils.getDBDate("00:00", ngaysinh).getTime());
			listDataLog+= " Ngay sinh: "+ formatter.format(tiepdon.getBenhnhanMa(true).getBenhnhanNgaysinh());
		} else {
			tiepdon.getBenhnhanMa(true).setBenhnhanNgaysinh(null);
		}
		
		removeAllNullForBN(tiepdon.getBenhnhanMa(true));
		if (gioTiepDon != null && !gioTiepDon.equals("") && ngayTiepDon != null	&& !ngayTiepDon.equals("")) {
			Calendar ngaygiotiepdon = Utils.getDBDate(gioTiepDon, ngayTiepDon);
			if (ngaygiotiepdon != null){
				tiepdon.setTiepdonNgaygio(ngaygiotiepdon.getTime());
			} else {
				tiepdon.setTiepdonNgaygio(new Date()); // 20110409 bao.ttc: check lai de dam bao truong TiepdonNgaygio NOT NULL
			}
			
		}
		listDataLog+= " Gio TD: "+ dateFormatFull.format(tiepdon.getTiepdonNgaygio());
		
		tiepdon.setTiepdonNgaygiocn(new Date());
		tiepdon.setTiepdonGiatri1(null);
		tiepdon.setTiepdonGiatri2(null);
		tiepdon.setTiepdonGiatri3(null);
		tiepdon.setTiepdonGiatri4(null);
		
		tiepdon.setTiepdonMoc1(null);
		tiepdon.setTiepdonMoc2(null);
		tiepdon.setTiepdonMoc3(null);
		if(tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH")) {
			if (bhytGiatri1 != null && !bhytGiatri1.equals("")) {
				tiepdon.setTiepdonGiatri1(Utils.getDBDate("00:00", bhytGiatri1).getTime());
				logBHYTT+= " GT1: "+ formatter.format(tiepdon.getTiepdonGiatri1());
			} 
			if (bhytGiatri2 != null && !bhytGiatri2.equals("")) {
				tiepdon.setTiepdonGiatri2(Utils.getDBDate("00:00", bhytGiatri2).getTime());
				logBHYTT+= " GT2: "+ formatter.format(tiepdon.getTiepdonGiatri2());
			} 
			
			if (bhytGiatri3 != null && !bhytGiatri3.equals("")) {
				tiepdon.setTiepdonGiatri3(Utils.getDBDate("00:00", bhytGiatri3).getTime());
				logBHYTT+= " GT3: "+ formatter.format(tiepdon.getTiepdonGiatri3());
			} 
			if (bhytGiatri4 != null && !bhytGiatri4.equals("")) {
				tiepdon.setTiepdonGiatri4(Utils.getDBDate("00:00", bhytGiatri4).getTime());
				logBHYTT+= " GT4: "+ formatter.format(tiepdon.getTiepdonGiatri4());
			} 
			try {
				if ( bhytMoc1 != null && !bhytMoc1.equals("") ){
					tiepdon.setTiepdonMoc1(formatter.parse(bhytMoc1));
					logBHYTT+= " Moc 1: "+ tiepdon.getTiepdonMoc1();
				}
				if ( bhytMoc2 != null && !bhytMoc2.equals("") ){
					tiepdon.setTiepdonMoc2(formatter.parse(bhytMoc2));
					logBHYTT+= " Moc 2: "+ tiepdon.getTiepdonMoc2();
				}
				if ( bhytMoc3 != null && !bhytMoc3.equals("") ){
					tiepdon.setTiepdonMoc3(formatter.parse(bhytMoc3));
					logBHYTT+= " Moc 3: "+ tiepdon.getTiepdonMoc3();
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else {
			tiepdon.setTiepdonSothebh(null);
			tiepdon.setKhoibhytMa(new DtDmKhoiBhyt());
			tiepdon.setTinhbhytMa(new DmTinh());
			tiepdon.setKcbbhytMa(new DmBenhVien());
		}
		
					
		// --- 20110119 bao.ttc: set Tiepdon tuyen ---
		// 1: Cùng tuyến - nơi DK KCB trùng với mã bệnh viện khám.
		// 2: Khác tuyến - BH cùng tỉnh khác nơi DK KCB.
		// 3: Tuyến khác - BH khác tỉnh với nhau (default: tương tự form ĐK khám B111)
		if (tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH")){
			
			Short tiepdonTuyen = 1;
//			if ( tiepdon.getKcbbhytMa(true).getDmbenhvienMa().equals(IConstantsRes.MASO_CO_SO_KCB) ){
//			  	tiepdonTuyen = 1;
//			} else {
//				if ( tiepdon.getKcbbhytMa(true).getDmbenhvienMa().startsWith(IConstantsRes.TINH_BHYT_DEFAULT) ){
//				  	tiepdonTuyen = 2;
//				}else{
//				  	tiepdonTuyen = 3;
//				}
//			}
			// 20110211 bao.ttc: Tiep don Cap cuu nen khong xet Tuyen, set Tuyen = 1 de BN duoc tra dung ty le BH da dang ky
			tiepdon.setTiepdonTuyen(tiepdonTuyen);
			listDataLog+= " Tuyen BH: "+ tiepdon.getTiepdonTuyen();
		}else {
			tiepdon.setTiepdonTuyen(null);
		}
		// --- 20110119 bao.ttc: set Tiepdon tuyen --- END
		
		// 20110712 bao.ttc: set thong tin cho field tiepdon_co_giay_gioi_thieu
		tiepdon.setTiepdonCoGiayGioiThieu(false);
		
		
		removeAllNullForTD(tiepdon);		
		String rs = delegate.dangkyTiepDonCapCuu(tiepdon, null);
		doituongHientai = tiepdon.getDoituongMa(true).getDmdoituongMa();		
		// phuc.lc 28-05-2011 : cap nhat hsttk sau khi da cap nhat don gia CLS
		if (tiepdon.getTiepdonMa() != null && !tiepdon.getTiepdonMa().equals("")){
			
			HsThtoank hsttk = new HsThtoank();
			hsttk.setTiepdonMa(tiepdon);
			HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(tiepdon);
			hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
			
		}
		if (rs != null) {
			FacesMessages.instance().add(IConstantsRes.SUCCESS + ":" + rs);
			
			yteLog.setObjectId(rs);
			yteLog.setUserId(identity.getUsername());
			yteLog.setForm("B131_Dangkykhambenhcapcuu");
			yteLog.setLogString(logBHYTT);
			yteLog.setListData(listDataLog);
			yteLog.setDateTime(new Date());
			yteLogDele.create(yteLog);
			
			resetValue();
		
		} else {
			FacesMessages.instance().add(IConstantsRes.FAIL);
		}				
		listBN = new ArrayList<BenhNhanTrungTheBhytDTO>();
		showListBn = false;
		log.info("End ghinhan");
		//resetValue();
	}
	
	private void removeAllNullForBN(BenhNhan bn){
		if ("".equals(Utils.reFactorString(bn.getDantocMa().getDmdantocMaso()))) {
			bn.setDantocMa(null);
		}
		if ("".equals(Utils.reFactorString(bn.getTinhMa().getDmtinhMaso()))) {
			bn.setTinhMa(null);
		}
		if ("".equals(Utils.reFactorString(bn.getHuyenMa().getDmhuyenMaso()))) {
			bn.setHuyenMa(null);
		}
		if ("".equals(Utils.reFactorString(bn.getXaMa().getDmxaMaso()))) {
			bn.setXaMa(null);
		}
		if ("".equals(Utils.reFactorString(bn.getBenhnhanNghe().getDmnghenghiepMaso()))) {
			bn.setBenhnhanNghe(null);
		}
	}
	
	private void removeAllNullForTD(TiepDon td){
		if ("".equals(Utils.reFactorString(td.getDoituongMa().getDmdoituongMaso()))) {
			td.setDoituongMa(null);
		}
		if ("".equals(Utils.reFactorString(td.getTinhbhytMa().getDmtinhMaso()))) {
			td.setTinhbhytMa(null);
		}
		if ("".equals(Utils.reFactorString(td.getKhoibhytMa().getDtdmkhoibhytMaso()))) {
			td.setKhoibhytMa(null);
		}
		if ("".equals(Utils.reFactorString(td.getKcbbhytMa().getDmbenhvienMaso()))) {
			td.setKcbbhytMa(null);
		}
		if ("".equals(Utils.reFactorString(td.getTainanMa().getDmtainanMaso()))) {
			td.setTainanMa(null);
		}
		if ("".equals(Utils.reFactorString(td.getDmptgtnMaso().getDmptgtnMaso()))) {
			td.setDmptgtnMaso(null);
		}
		if ("".equals(Utils.reFactorString(td.getDiadiemMa().getDmdiadiemMaso()))) {
			td.setDiadiemMa(null);
		}
		if ("".equals(Utils.reFactorString(td.getTiepdonDonvigoi().getDmbenhvienMaso()))) {
			td.setTiepdonDonvigoi(null);
		}
		if ("".equals(Utils.reFactorString(td.getTiepdonMachdoanbd().getDmbenhicdMaso()))) {
			td.setTiepdonMachdoanbd(null);
		}
	}

	public TiepDon getTiepdon() {
		return tiepdon;
	}

	public void setTiepdon(TiepDon tiepdon) {
		this.tiepdon = tiepdon;
	}

	public String getGioTiepDon() {
		return gioTiepDon;
	}

	public void setGioTiepDon(String gioTiepDon) {
		this.gioTiepDon = gioTiepDon;
	}

	public String getNgayTiepDon() {
		return ngayTiepDon;
	}

	public void setNgayTiepDon(String ngayTiepDon) {
		this.ngayTiepDon = ngayTiepDon;
	}

	public String getBhytGiatri1() {
		return bhytGiatri1;
	}

	public void setBhytGiatri1(String bhytGiatri1) {
		this.bhytGiatri1 = bhytGiatri1;
	}

	public String getBhytGiatri2() {
		return bhytGiatri2;
	}

	public void setBhytGiatri2(String bhytGiatri2) {
		this.bhytGiatri2 = bhytGiatri2;
	}
	
	public String getDonvituoi() {
		return donvituoi;
	}

	public void setDonvituoi(String donvituoi) {
		this.donvituoi = donvituoi;
	}
	
	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getGioi() {
		return gioi;
	}

	public void setGioi(String gioi) {
		this.gioi = gioi;
	}

	public String getNofound() {
		return nofound;
	}

	public void setNofound(String nofound) {
		this.nofound = nofound;
	}

	public String getBhytGiatri3() {
		return bhytGiatri3;
	}

	public void setBhytGiatri3(String bhytGiatri3) {
		this.bhytGiatri3 = bhytGiatri3;
	}

	public String getBhytGiatri4() {
		return bhytGiatri4;
	}

	public void setBhytGiatri4(String bhytGiatri4) {
		this.bhytGiatri4 = bhytGiatri4;
	}

	public String getBhytMoc1() {
		return bhytMoc1;
	}

	public void setBhytMoc1(String bhytMoc1) {
		this.bhytMoc1 = bhytMoc1;
	}

	public String getBhytMoc2() {
		return bhytMoc2;
	}

	public void setBhytMoc2(String bhytMoc2) {
		this.bhytMoc2 = bhytMoc2;
	}

	public String getBhytMoc3() {
		return bhytMoc3;
	}

	public void setBhytMoc3(String bhytMoc3) {
		this.bhytMoc3 = bhytMoc3;
	}

	public List<BenhNhanTrungTheBhytDTO> getListBN() {
		return listBN;
	}

	public void setListBN(List<BenhNhanTrungTheBhytDTO> listBN) {
		this.listBN = listBN;
	}

	public boolean isShowListBn() {
		return showListBn;
	}

	public void setShowListBn(boolean showListBn) {
		this.showListBn = showListBn;
	}

	public boolean isLockDoituong() {
		return lockDoituong;
	}

	public void setLockDoituong(boolean lockDoituong) {
		this.lockDoituong = lockDoituong;
	}

	public String getDoituongHientai() {
		return doituongHientai;
	}

	public void setDoituongHientai(String doituongHientai) {
		this.doituongHientai = doituongHientai;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public String getListMaTinhBhyt() {
		return listMaTinhBhyt;
	}

	public void setListMaTinhBhyt(String listMaTinhBhyt) {
		this.listMaTinhBhyt = listMaTinhBhyt;
	}

	public String getHosoDaTT() {
		return hosoDaTT;
	}

	public void setHosoDaTT(String hosoDaTT) {
		this.hosoDaTT = hosoDaTT;
	}

	public boolean isLockBanKham() {
		return lockBanKham;
	}

	public void setLockBanKham(boolean lockBanKham) {
		this.lockBanKham = lockBanKham;
	}
	
	
		
}


