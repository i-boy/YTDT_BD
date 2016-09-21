/*
 * author : Mai Van Manh
 */
package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankBackupDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.ClsMo;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.HsThtoankBackup;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmKetQuaDieuTri;
import com.iesvn.yte.util.HoSoThanhToanUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.ThamKhamUtil;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3241_Thanhtoanravien")
@Synchronized(timeout = 6000000)
public class ThanhToanRaVienAction implements Serializable {
	// private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	// private String position =
	// com.iesvn.yte.util.IConstantsRes.WEB_PATH+"/"+com.iesvn.yte.util.IConstantsRes.WEB_NAME
	// +com.iesvn.yte.util.IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI +
	// "vienphi/";

	private static final long serialVersionUID = 10L;
	private static Logger log = Logger.getLogger(ThanhToanRaVienAction.class);
	private BenhNhan benhNhan;
	private HsbaBhyt hsbaBhyt;
	private Hsba hsba;
	private HsbaChuyenVien hsbaChuyenvien;
	// private HsbaChuyenMon hsbaChuyenMon;
	private HsThtoan hsThtoan;
	private String gioi;
	private String tuoi;
	private String soThe;
	private String ngayNhapvien;
	private String ngayRavien;
	private String maFinish;
	private String sLiDoTT;

	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();

	private Double ungTra;

	private String ngayTtoan;
	private String cacKhoadt;

	private String loaiFileXuat;

	private String resultHidden = "";
	private String reportFileNameHid = "";
	private String reportFileName;
	private String resultReportName;
	private String resultReportFileName;

	private String reportFinished = "";
	private boolean updateNhapct = false;
	
	private String gioThanhToan;
	private List<ClsMo> listClsMo;
	private List<ThuocNoiTru> listThuocNT;
	@Out(required = false)
	private String isReport = "false";

	// Thong tin can thiet cho ho so thanh toan kham
	private Double thuocTrongDM = new Double(0);
	private Double thuocNDM = new Double(0);
	private Double vTTHTrongDM = new Double(0);
	private Double vTTHNDM = new Double(0);
	private Double cLSMauTrongDM = new Double(0);
	private Double clsMauNDM = new Double(0);
	private Double tongTrongDM = new Double(0);
	private Double tongNDM = new Double(0);
	private Double nSKhongThu = new Double(0);

	private Double ung = new Double(0);
	private Double tra = new Double(0);
	private Double soDu = new Double(0); // = bntra - ung + tra
	private Double benhnhanTra = new Double(0);
	private Double conlai = new Double(0);
	private HsThtoank hsttk;
	private ChuyenVaoNoiTru cvnt;
	// Phan danh cho tinh toan
	private int permiengiam = 0;
	private Double miengiam = new Double(0);
	private Double thatthu = new Double(0);
	private int perbhytchi = 0;
	private Double bhytchi = new Double(0);
	private Double thanhtien1 = new Double(0);
	private int perbntra = 0;
	private Double bntra = new Double(0);

	@In(required = false)
	@Out(required = false)
	private String ten3241_3242;

	private Boolean hienThiGhiNhan = false;
	private Boolean hienThiInPhieu = false;
	private Boolean hienThiHuyPhieu = false;
	private Boolean hienThiInPhieuNgT = false;
	private String role = "";
	
	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
    private YteLog yteLog ;

	private void tinhToanChoHSTT(HsThtoan hstt) {
		HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
		hsthtoanUtil.tinhToanChoHSTT(hstt);

	}

	private Integer khoaMaso;
	private String khoaMa;

	private DtDmCum cum = null;

	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	private int index=0;

	public void refreshNhanVienThuNgan()
	{
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienThungan = nvDelegate.findByNd(identity.getUsername());
		if (nhanVienThungan == null) {
			nhanVienThungan = new DtDmNhanVien();
		}
	}
	
	@Begin (join = true)
	public String init(String maForm, String fromRole) throws Exception {
		//log.info("***Starting init ***");

		ten3241_3242 = maForm;//"3241";
		role = fromRole; // Role xem chi phi, NT or VP 
		refreshNhanVienThuNgan();
		
		
		PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate
				.getInstance();
		PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(
				new Date(), nhanVienThungan.getDtdmnhanvienMa());

		

		if (pc != null && pc.getDtdmnhanvienMa() != null) {

			//nhanVienThungan = pc.getDtdmnhanvienMa();
			
			cum = pc.getDtdmcumMa();
		}
		
		emtyData();
		//log.info("***Finished init ***");
		
		tenChuongTrinh = MyMenuYTDTAction.thuVienPhi;
		
		return "ThuVienPhi_ThanhToanRaVien_ThanhToanRaVien";
	}
	@End
	public void endFuct(){
		
	}

	private void emtyData() {
		//log.info("begin emptyData()");
		benhNhan = new BenhNhan();
//		SetInforUtil.setInforIfNullForBN(benhNhan);
		hsbaBhyt = new HsbaBhyt();
//		SetInforUtil.setInforIfNullForHSBABHYT(hsbaBhyt);
		hsba = new Hsba();
//		SetInforUtil.setInforIfNullForHSBA(hsba);
		hsbaChuyenvien = new HsbaChuyenVien();
//		SetInforUtil.setInforIfNullForHsbaChuyenVien(hsbaChuyenvien);
		// hsbaChuyenMon = new HsbaChuyenMon();

		// SetInforUtil.setInforIfNullForHSBACM(hsbaChuyenMon);
		// hsbaChuyenMon.setHsbaSovaovien(hsba);

		hsThtoan = new HsThtoan();
//		SetInforUtil.setInforIfNullForHsThtoan(hsThtoan);

		tuoi = "";
		soThe = "";
		ngayNhapvien = "";
		ngayRavien = "";
		maFinish = "";

		//Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		Date now = new Date();
		ngayTtoan = formatter.format(now);
		cacKhoadt = "";
		gioThanhToan = Utils.getGioPhut(now) ;
		

		hienThiGhiNhan = false;
		hienThiInPhieu = false; // 20101204 bao.ttc: chua co thong tin gi nen ko hien thi In phieu
		hienThiHuyPhieu = false;
		hienThiInPhieuNgT = false;
		khoaMa = "";
		ungTra = null;
		sLiDoTT = "";
		listClsMo = new ArrayList<ClsMo>();
		listThuocNT = new ArrayList<ThuocNoiTru>();
		/*
		 * set gia tri null cho hsba2
		 */
		//log.info("end emptyData()");
	}

	private void hasNoKhoaOrSoVaoVien() {
		emtyData();
	}

	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() throws Exception {

		refreshNhanVienThuNgan();
		//log.info("*****Begin Ghi nhan() , nhanVienThungan = " + nhanVienThungan);
		
		
		try {
			if (hsThtoan.getHsthtoanMa() != null
					&& !hsThtoan.getHsthtoanMa().equals("")) {
				FacesMessages.instance().add(
						IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
				hienThiGhiNhan = false;
				hienThiInPhieu = false;
				hienThiHuyPhieu = true;
				hienThiInPhieuNgT = false;
				return;
			}
			
			if (hsba.getHsbaYeuCau() == null || hsba.getHsbaYeuCau().equals("")) {
				FacesMessages.instance().add(
						IConstantsRes.GIAYXACNHANRAVIEN_REQUIRED);
				hienThiGhiNhan = false;
				hienThiInPhieu = false;
				hienThiHuyPhieu = false;
				hienThiInPhieuNgT = false;
				return;
			}
			if (MyMenuYTDTAction.vienPhiTaiKhoa.equals(tenChuongTrinh)){
				if ( hsba.getDoituongMa() != null && 
						! ( hsba.getDoituongMa().getDmdoituongMa().equals("TE") || hsba.getDoituongMa().getDmdoituongMa().equals("BH")
								|| hsba.getDoituongMa().getDmdoituongMa().equals("MP")) 
						
				){
					FacesMessages.instance().add(IConstantsRes.benh_nhan_phai_thuoc_doi_tuong_tre_em_hoac_bhyt);
					hienThiGhiNhan = false;
					hienThiInPhieu = false;
					hienThiHuyPhieu = false;
					hienThiInPhieuNgT = false;
					return;
				}
				if (hsThtoan.getHsthtoanThtoan() != null && hsThtoan.getHsthtoanThtoan() > 0){
					FacesMessages.instance().add(IConstantsRes.benh_nhan_phai_thu_vien_phi);
					hienThiGhiNhan = false;
					hienThiInPhieu = false;
					hienThiHuyPhieu = false;
					hienThiInPhieuNgT = false;
					return;
				}
			}
			HsThtoanDelegate hsttDelegate = HsThtoanDelegate.getInstance();

			RemoveUtil.removeIfNullForHsThtoan(hsThtoan);
			RemoveUtil.removeAllNullFromHSBA(hsba);
			// RemoveUtil.removeAllNullFromHSBACM(hsbaChuyenMon);
			RemoveUtil.removeAllNullFromHSBACV(hsbaChuyenvien);

			// hsbaChuyenvien.setHsbacmMa(hsbaChuyenMon);

			removeIfNullForHsbaChuyenVien(hsbaChuyenvien);
			Date dNgayGioTT = Calendar.getInstance().getTime();
			if (hsThtoan.getHsthtoanNgaytt() == null) {
				Calendar dTemp = Utils.getDBDate(gioThanhToan, ngayTtoan);
				
				
				if (dTemp != null){
					dNgayGioTT = dTemp.getTime();
					
				}
				hsThtoan.setHsthtoanNgaytt(dNgayGioTT);
			}

			//hsThtoan.setHsthtoanThungan(nhanVienThungan);
			hsThtoan.setHsthtoanThungan(nhanVienThungan.getDtdmnhanvienMaso() == null ? null : nhanVienThungan);
			hsThtoan.setHsthtoanCum(cum);
			hsThtoan.setHsthtoanLidothanhtoan(sLiDoTT);
			//phuc.lc 09-11-2010
			hsThtoan.setHsthtoanDatt(true);
			if(hsba.getHsbaNgaygiorav() == null) {
				hsba.setHsbaNgaygiorav(hsThtoan.getHsthtoanNgaytt());
			}
			// phuc.lc 22-11-2010 BEGIN :  fix bug 1383 
			// Kiem tra co phai thanh toan benh nhan bo vien hay khong
			// neu la benh nhan bo vien thi cap nhat lai truong bo vien va that thu tron bang hs_thtoan			
			if (ten3241_3242.equals("3242")) {	// 
				hsThtoan.setHsthtoanBovien(true);
				hsThtoan.setHsthtoanThatthu(hsThtoan.getHsthtoanBntra());
			}
			// phuc.lc 22-11-2010 END
			Utils.setInforForHsThToan(hsThtoan);
			//if (hsThtoan.getHsthtoanKhoa() == null){
				hsThtoan.setHsthtoanKhoa(hsba.getHsbaKhoadangdt());
			//}
			
			maFinish = hsttDelegate.capNhatTTRaVien(hsThtoan, hsba, hsbaChuyenvien);
			hsThtoan.setHsthtoanThtoan(new Double(0.0));
			
			// Cap nhat chi phi ngoai tru (neu co)
			if(cvnt != null) {
				//log.info("ma tiep don = " + hsttk.getTiepdonMa().getTiepdonMa());
				hsttk.setHsthtoankMa(maFinish);		// Set ma hsttk = ma ho so thanh toan (noi tru)
				hsttk.setHsthtoankThatthu(hsThtoan.getHsthtoanThatthu());
				hsttk.setHsthtoankKyhieu(hsThtoan.getHsthtoanKyhieu());
				hsttk.setHsthtoankQuyen(hsThtoan.getHsthtoanQuyen());
				hsttk.setHsthtoankBienlai(hsThtoan.getHsthtoanBienlai());
				hsttk.setHsthtoankThungan(hsThtoan.getHsthtoanThungan());
				//hsttk.setHsthtoankThtoan(new Double(0.0));
				hsttk.setHsthtoankNgaygiott(dNgayGioTT);
				ClsKhamDelegate clsKhamDelegate  = ClsKhamDelegate.getInstance();
				// lay CLS
				List<ClsKham> clslist = clsKhamDelegate.findByTiepdonma(hsttk.getTiepdonMa().getTiepdonMa());
				
				// lay thuoc ban` khoam
				ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
				List<ThuocPhongKham> listCtTPK_temp = tpkDelegate.findByMaTiepDon(hsttk.getTiepdonMa().getTiepdonMa(), "1"); // thuoc ban kham
				
				// ke toa quay benh vien
				List<ThuocPhongKham>  listCtTPK_tempBHYT = tpkDelegate.findByMaTiepDon(hsttk.getTiepdonMa().getTiepdonMa(), "3"); // ke toa BHYT
				listCtTPK_temp.addAll(listCtTPK_tempBHYT);
				HsThtoankDelegate hsttkDel = HsThtoankDelegate.getInstance();
				hsttkDel.capNhatTTHsttk(hsttk,clslist,listCtTPK_temp, false);
				// Neu la doi tuong thu phi thi cap nhat thong tin vao ban ho so thanh toan backup
				if (hsttk.getTiepdonMa().getDoituongMa().getDmdoituongMa().equals("TP")) {
					HsThtoankBackup hsBK = new HsThtoankBackup();
					// Copy thong tin tu hsttk vao HsThtoankBackup
					hsBK.setHsthtoankBhxh(hsttk.getHsthtoankBhxh());
					hsBK.setHsthtoankBhyt(hsttk.getHsthtoankBhyt());
					hsBK.setHsthtoankBienlai(hsttk.getHsthtoankBienlai());
					hsBK.setHsthtoankBntra(hsttk.getHsthtoankBntra());
					hsBK.setHsthtoankBovien(hsttk.getHsthtoankBovien());
					hsBK.setHsthtoankCdha(hsttk.getHsthtoankCdha());
					hsBK.setHsthtoankCls(hsttk.getHsthtoankCls());
					hsBK.setHsthtoankClsndm(hsttk.getHsthtoankClsndm());
					hsBK.setHsthtoankCongkham(hsttk.getHsthtoankCongkham());
					hsBK.setHsthtoankCpVc(hsttk.getHsthtoankCpvc());
					hsBK.setHsthtoankCum(hsttk.getHsthtoankCum());
					hsBK.setHsthtoankDatt(hsttk.getHsthtoankDatt());
					//hsBK.setHsthtoankDichtruyen(hsttk.getHsthtoankDichtruyen());
					hsBK.setHsthtoankDienmien(hsttk.getHsthtoankDienmien());
					hsBK.setHsthtoankDm(hsttk.getHsthtoankDm());
					hsBK.setHsthtoankDvKtc(hsttk.getHsthtoankDvktc());
					hsBK.setHsthtoankDvktTt(hsttk.getHsthtoankDvkttt());
					hsBK.setHsthtoankHoanthu(hsttk.getHsthtoankHoanthu());
					hsBK.setHsthtoankHoanung(hsttk.getHsthtoankHoanung());
					hsBK.setHsthtoankKhongthu(hsttk.getHsthtoankKhongthu());
					hsBK.setHsthtoankKyhieu(hsttk.getHsthtoankKyhieu());
					hsBK.setHsthtoankLydott(hsttk.getHsthtoankLydott());
					//hsBK.setHsthtoankMa(hsttk.getHsthtoankMa());
					hsBK.setHsthtoankMabenh(hsttk.getHsthtoankMabenh());
					hsBK.setHsthtoankMau(hsttk.getHsthtoankMau());
					hsBK.setHsthtoankMiendt(hsttk.getHsthtoankMiendt());
					hsBK.setHsthtoankMienmau(hsttk.getHsthtoankMienmau());
					hsBK.setHsthtoankMienphong(hsttk.getHsthtoankMienphong());
					hsBK.setHsthtoankMiente(hsttk.getHsthtoankMiente());
					hsBK.setHsthtoankMienthuoc(hsttk.getHsthtoankMienthuoc());
					hsBK.setHsthtoankMienthuoclao(hsttk.getHsthtoankMienthuoclao());
					hsBK.setHsthtoankNdm(hsttk.getHsthtoankNdm());
					hsBK.setHsthtoankNgansach(hsttk.getHsthtoankNgansach());
					hsBK.setHsthtoankNgayc(hsttk.getHsthtoankNgayc());
					hsBK.setHsthtoankNgayd(hsttk.getHsthtoankNgayd());
					hsBK.setHsthtoankNgaygiocn(hsttk.getHsthtoankNgaygiocn());
					hsBK.setHsthtoankNgaygiott(hsttk.getHsthtoankNgaygiott());
					hsBK.setHsthtoankNhanviencn(hsttk.getHsthtoankNhanviencn());
					hsBK.setHsthtoankPhauthuat(hsttk.getHsthtoankPhauthuat());
					hsBK.setHsthtoankPhauthuatndm(hsttk.getHsthtoankPhauthuatndm());
					hsBK.setHsthtoankPhong(hsttk.getHsthtoankPhong());
					hsBK.setHsthtoankPhongndm(hsttk.getHsthtoankPhongndm());
					hsBK.setHsthtoankQuyen(hsttk.getHsthtoankQuyen());
					hsBK.setHsthtoankSokhoa(hsttk.getHsthtoankSokhoa());
					hsBK.setHsthtoankTamung(hsttk.getHsthtoankTamung());
					hsBK.setHsthtoankThatthu(hsttk.getHsthtoankThatthu());
					hsBK.setHsthtoankThtoan(hsttk.getHsthtoankThtoan());
					hsBK.setHsthtoankThungan(hsttk.getHsthtoankThungan());
					hsBK.setHsthtoankThuoc(hsttk.getHsthtoankThuoc());
					hsBK.setHsthtoankThuocndm(hsttk.getHsthtoankThuocndm());
					hsBK.setHsthtoankTongchi(hsttk.getHsthtoankTongchi());
					hsBK.setHsthtoankTylebh(hsttk.getHsthtoankTylebh());
					hsBK.setHsthtoankVtth(hsttk.getHsthtoankVtth());
					hsBK.setHsthtoankVtthndm(hsttk.getHsthtoankVtthndm());
					hsBK.setHsthtoankXetgiam(hsttk.getHsthtoankXetgiam());
					hsBK.setHsthtoankXntdcn(hsttk.getHsthtoankXntdcn());
					hsBK.setTiepdonMa(hsttk.getTiepdonMa());
					hsBK.setHsthtoankMa(hsttk.getHsthtoankMa());
					HsThtoankBackupDelegate.getInstance().create(hsBK);										
				}
			}
			conlai = new Double(0.0);
			if (maFinish != null && !maFinish.equals("")) {
				FacesMessages.instance().add(IConstantsRes.SUCCESS);
				hienThiGhiNhan = false;
				hienThiInPhieu = true;
				hienThiHuyPhieu = true;
				hienThiInPhieuNgT = (cvnt != null );
				hsThtoan.setHsthtoanMa(maFinish);
			} else if (maFinish != null && maFinish.equals("")) {
				FacesMessages.instance().add(
						IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
				hienThiGhiNhan = false;
				hienThiInPhieu = true;
				hienThiHuyPhieu = true;
				hienThiInPhieuNgT = (cvnt != null );
			} else {
				FacesMessages.instance().add(IConstantsRes.FAIL);
				hienThiGhiNhan = false;
				hienThiInPhieu = false;
				hienThiHuyPhieu = false;
				hienThiInPhieuNgT = false;
			}
			
			// luu thong tin log
			yteLog = new YteLog();
			String listDataLog= "";
			for (ThuocNoiTru thuocNoiTru : listThuocNT) {
				//luu log thong tin thuoc
				listDataLog += " Thuoc Ma LK:"+ thuocNoiTru.getThuocnoitruMalk()+
						" Don gia: "+  thuocNoiTru.getThuocnoitruDongia()+ " Don gia ban: "+ thuocNoiTru.getThuocnoitruDongiaban() + 
						" So luong: "+ thuocNoiTru.getThuocnoitruSoluong()+
						" Thanh tien: "+thuocNoiTru.getThuocnoitruThanhtien()+
						" Nam SX: " + thuocNoiTru.getThuocnoitruNamnhap()+
						" Nam HD: " + thuocNoiTru.getThuocnoitruNamhd()+ " \n";	
			
			}
			for(ClsMo cls : listClsMo){
				listDataLog+= "CLS ma: "+ cls.getClsmoMahang(true).getDtdmclsbgMa() + 
						" Don gia: "+cls.getClsmoDongia()+"\n";
			}
			 yteLog.setForm("B3241_Thanhtoanravien");
	         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
	         yteLog.setObjectId(hsba.getHsbaSovaovien());
	         yteLog.setLogString("Ngoai DM \t Danh muc TT \n"+
	        		 			 "Thuoc: "+thuocNDM + "\t"+thuocTrongDM+"\n"+
	        		 			 "VTTH: "+vTTHNDM + "\t"+vTTHTrongDM+"\n"+
	        		 			 "CLS: "+clsMauNDM+"\t"+ cLSMauTrongDM+"\n"+
	        		 			 "Tong chi: "+ tongNDM + "\t"+ tongTrongDM+"\n"+
	        		  			 "Ung tra: "+ ungTra + "\n"+
	        		 			 "BN tra: "+ benhnhanTra+"\n"+
	        		  			 "Ngan sach: "+ (hsThtoan.getHsthtoanNgansach()== null ? 0 : hsThtoan.getHsthtoanNgansach())+"\n"+
	        		  			 "Khong thu: "+ (nSKhongThu)+"\n"+
	        		  			 "Con lai: "+conlai+"\n"+
	        		  			 "That thu:"+ (hsThtoan.getHsthtoanThatthu()== null ? 0 : hsThtoan.getHsthtoanThatthu())+"\n"+
	        		  			 "Mien giam: "+ miengiam
	        		  			 ); 
	         yteLog.setDateTime(new Date());
	         yteLog.setListData(listDataLog);
	         yteLogDele.create(yteLog);
			
			
			resultHidden = "success";
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			resultHidden = "fail";
			log.error(" ***** ERROR **** B3241_Thanhtoanravien - ghinhan(): " + e.toString());
			e.printStackTrace();
		}

		
		//log.info("*****End Ghi nhan() *****");
		this.reportFinished = "";
	}

	/**
	 * 
	 * @param hsbaChuyenvien2
	 */
	private void removeIfNullForHsbaChuyenVien(HsbaChuyenVien hsbaChuyenvien2) {
		//log.info("Begin removeIfNullForHsbaChuyenVien: " + hsbaChuyenvien2);
		try {
			if ("".equals(Utils.reFactorString(hsbaChuyenvien2
					.getHsbacvBschuyen(true).getDtdmnhanvienMa()))) {
				hsbaChuyenvien2.setHsbacvBschuyen(null);
			}
			if ("".equals(Utils.reFactorString(hsbaChuyenvien2
					.getHsbacvChvienden(true).getDmbenhvienMa()))) {
				hsbaChuyenvien2.setHsbacvChvienden(null);
			}
			if ("".equals(Utils.reFactorString(hsbaChuyenvien2
					.getHsbacvLydochuyenv(true).getDtdmlydocvMa()))) {
				hsbaChuyenvien2.setHsbacvLydochuyenv(null);
			}
			if ("".equals(Utils.reFactorString(hsbaChuyenvien2.getNhanvienMa(true)
					.getDtdmnhanvienMa()))) {
				hsbaChuyenvien2.setNhanvienMa(null);
			}
		} catch (Exception e) {
			log.error("Loi trong removeIfNullForHsbaChuyenVien: " + e);
		}
		//log.info("End removeIfNullForHsbaChuyenVien");
	}

	// Ham khi nhan nut nhap lai
	public void nhaplai() throws Exception {
		try {
			ResetForm();
			resultHidden = "";
			maFinish = "";
			hienThiGhiNhan = false;
			hienThiInPhieu = false;
			hienThiHuyPhieu = false;
			hienThiInPhieuNgT = false;
			this.reportFinished = "";
		} catch (Exception e) {
			log.error("Loi reset form : " + e.toString());
		}
	}

	
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="ThanhToanRaVien";
		//log.info("Vao Method Bao cao thanh toan ra vien");
		String baocao1=null;
		try {
			//log.info("bat dau method XuatReport ");
			
			Map<String, Object> params = new HashMap<String, Object>();
			JasperReport jasperReport;
			if (hsba.getDoituongMa() != null && "TE".equals(hsba.getDoituongMa().getDmdoituongMa())){
			
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport0.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport1.jrxml";
				String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport2.jrxml";
				String sub3Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport3.jrxml";
				String sub4Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport4.jrxml";
				String sub5Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport5.jrxml";
				String sub6Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport6.jrxml";
								
//				log.info("da thay file templete " + pathTemplate);
//				log.info("da thay file sub 0 templete " + sub0Template);
//				log.info("da thay file sub 1 templete " + sub1Template);
//				log.info("da thay file sub 2 templete " + sub2Template);
//				log.info("da thay file sub 3 templete " + sub3Template);
//				log.info("da thay file sub 4 templete " + sub4Template);
//				log.info("da thay file sub 5 templete " + sub5Template);
//				log.info("da thay file sub 6 templete " + sub6Template);
				
				jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
				JasperReport sub3Report =JasperCompileManager.compileReport(sub3Template);
				JasperReport sub4Report =JasperCompileManager.compileReport(sub4Template);
				JasperReport sub5Report =JasperCompileManager.compileReport(sub5Template);
				JasperReport sub6Report =JasperCompileManager.compileReport(sub6Template);
				
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
				params.put("sub2", sub2Report);
				params.put("sub3", sub3Report);
				params.put("sub4", sub4Report);
				params.put("sub5", sub5Report);
				params.put("sub6", sub6Report);
			
			}else{
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport0.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport_THUOC.jrxml";
				String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport_VTTH.jrxml";
				String sub3Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport_MAU.jrxml";
				String sub4Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport_XN_TDCN.jrxml";
				String sub5Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport_PT.jrxml";
				String sub6Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport_GIUONG.jrxml";
				String sub7Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport_TT.jrxml";
				String sub8Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport_DVKTC.jrxml";
				String sub9Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport_CDHA.jrxml";
				String sub10Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport_CPVC.jrxml";
				String sub11Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanRVnoitru_subreport_TONGKET.jrxml";
				
				String subBienLai1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/HoaDon_sub1.jrxml";
				String subBienLai2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/HoaDon_sub2.jrxml";
				
//				log.info("da thay file templete " + pathTemplate);
//				log.info("da thay file sub 0 templete " + sub0Template);
//				log.info("da thay file sub 1 templete " + sub1Template);
//				log.info("da thay file sub 2 templete " + sub2Template);
//				log.info("da thay file sub 3 templete " + sub3Template);
//				log.info("da thay file sub 4 templete " + sub4Template);
//				log.info("da thay file sub 5 templete " + sub5Template);
//				log.info("da thay file sub 6 templete " + sub6Template);
//				log.info("da thay file sub 7 templete " + sub7Template);
//				log.info("da thay file sub 8 templete " + sub8Template);
//				log.info("da thay file sub 9 templete " + sub9Template);
//				log.info("da thay file sub 10 templete " + sub10Template);
//				log.info("da thay file sub 11 templete " + sub11Template);
//				log.info("da thay file sub subBienLai1Template " + subBienLai1Template);
//				log.info("da thay file sub subBienLai2Template " + subBienLai2Template);
				
				jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
				JasperReport sub3Report =JasperCompileManager.compileReport(sub3Template);
				
				JasperReport sub4Report =JasperCompileManager.compileReport(sub4Template);
				JasperReport sub5Report =JasperCompileManager.compileReport(sub5Template);
				JasperReport sub6Report =JasperCompileManager.compileReport(sub6Template);
				JasperReport sub7Report =JasperCompileManager.compileReport(sub7Template);
				JasperReport sub8Report =JasperCompileManager.compileReport(sub8Template);
				JasperReport sub9Report =JasperCompileManager.compileReport(sub9Template);
				JasperReport sub10Report =JasperCompileManager.compileReport(sub10Template);
				JasperReport sub11Report =JasperCompileManager.compileReport(sub11Template);
				
				
				JasperReport subBienLai1Report =JasperCompileManager.compileReport(subBienLai1Template);
				JasperReport subBienLai2Report =JasperCompileManager.compileReport(subBienLai2Template);
				
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
				params.put("sub2", sub2Report);
				params.put("sub3", sub3Report);
				
				params.put("sub4", sub4Report);
				params.put("sub5", sub5Report);
				params.put("sub6", sub6Report);
				params.put("sub7", sub7Report);
				params.put("sub8", sub8Report);
				params.put("sub9", sub9Report);
				params.put("sub10", sub10Report);
				params.put("sub11", sub11Report);
				
				params.put("subBienLai1Report", subBienLai1Report);
				params.put("subBienLai2Report", subBienLai2Report);
				
			}
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			//log.info("da thay file template ");
			
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("REPORT_HOADON", IConstantsRes.REPORT_HOADON);
			
			
			params.put("SOHOSOBA", hsba.getHsbaSovaovien() );
			params.put("HOTENBN", benhNhan.getBenhnhanHoten());
			params.put("SOPHIEU", hsThtoan.getHsthtoanMa());
			if(hsThtoan.getHsthtoanLidothanhtoan()!=null);
			params.put("LIDOTHANHTOAN", hsThtoan.getHsthtoanLidothanhtoan());
			
			
			if (hsba.getDoituongMa() != null && "TE".equals(hsba.getDoituongMa().getDmdoituongMa())){
				//TE
				if (hsba.getHsbaMachdravien()!= null){
					DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
					DmBenhIcd benh = (DmBenhIcd)dele.findByMa(hsba.getHsbaMachdravien().getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
					if (benh != null){
						params.put("CHANDOAN", benh.getDmbenhicdTen());
						params.put("MA_CHANDOAN", benh.getDmbenhicdMa());
							
					}else{
						params.put("CHANDOAN", "");
						params.put("MA_CHANDOAN", "");
					}
					
				}else{
					params.put("CHANDOAN", "");
					params.put("MA_CHANDOAN", "");
				}
				
				if (hsba.getHsbaKetqua() != null && !hsba.getHsbaKetqua().getDmkqdtMa().equals("")){
					DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
					DmKetQuaDieuTri ketQuaDieuTri = (DmKetQuaDieuTri)dele.findByMa(hsba.getHsbaKetqua().getDmkqdtMa() , "DmKetQuaDieuTri", "dmkqdtMa");
					params.put("KETQUADIEUTRI", ketQuaDieuTri.getDmkqdtTen());
				}
				
				params.put("Tuoi", benhNhan.getBenhnhanTuoi());
				
				params.put("DonViTuoi",  benhNhan.getBenhnhanDonvituoi()==null?new Integer(1):new Integer(benhNhan.getBenhnhanDonvituoi()) );
				
//				HsbaBhytDelegate hsbaBHYTDele = HsbaBhytDelegate.getInstance();
//				HsbaBhyt hsbaBhyt = hsbaBHYTDele.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
				
				//log.info("hsbaBhyt:"+hsbaBhyt);
				if (hsbaBhyt != null){
					params.put("MATHEKCB", hsbaBhyt.getHsbabhytSothete()==null?"":hsbaBhyt.getHsbabhytSothete());
					params.put("SOGIAYKHAISINH", hsbaBhyt.getHsbabhytKhaisinh()==null?"":hsbaBhyt.getHsbabhytKhaisinh());
					params.put("SOGIAYCHUNGSINH", hsbaBhyt.getHsbabhytChungsinh()==null?"":hsbaBhyt.getHsbabhytChungsinh());
					params.put("GIAMHO", hsbaBhyt.getHsbabhytGiamho()==null?"":hsbaBhyt.getHsbabhytGiamho());
					params.put("GIAYCHUNGNHANUBNNXA", hsbaBhyt.getHsbabhytCnxa()==null?"":hsbaBhyt.getHsbabhytCnxa());		
					
//					log.info("hsbaBhyt.getHsbabhytSothete():"+hsbaBhyt.getHsbabhytSothete());
//					log.info("hsbaBhyt.getHsbabhytKhaisinh():"+hsbaBhyt.getHsbabhytKhaisinh());
//					log.info("hsbaBhyt.getHsbabhytChungsinh():"+hsbaBhyt.getHsbabhytChungsinh());
//					log.info("hsbaBhyt.getHsbabhytGiamho():"+hsbaBhyt.getHsbabhytGiamho());
//					log.info("hsbaBhyt.getHsbabhytCnxa():"+hsbaBhyt.getHsbabhytCnxa());
				}
				//log.info("_______________________________________________________");
				params.put("NgoaiTru", "");
				params.put("NoiTru", "");
				params.put("NoiKhoa", "");
				params.put("NgoaiKhoa", "");
				params.put("YHDT", "");
				
			}else{
				if (hsba.getHsbaMachdravien()!= null && !hsba.getHsbaMachdravien().getDmbenhicdMa().equals("")){
					DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
					DmBenhIcd benh = (DmBenhIcd)dele.findByMa(hsba.getHsbaMachdravien().getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
					params.put("CHANDOAN", benh.getDmbenhicdMa() + " - " + benh.getDmbenhicdTen());
				}else{
					params.put("CHANDOAN", "");
				}
			}
			
			
			if (hsbaBhyt.getHsbabhytSothebh() != null	&& !hsbaBhyt.getHsbabhytSothebh().equals("")){
				String maBV = hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienMa();
				if (maBV == null){
					maBV = "";
				}
				params.put("MATHEBHYT",  hsbaBhyt.getHsbabhytSothebh()+ "-"+  maBV.replace(".", "-"));
				
				//log.info("MATHEBHYT:"+params.get("MATHEBHYT"));
			}
			else{
				params.put("MATHEBHYT","");
			}
			
			double tongchi = ((hsThtoan.getHsthtoanNdm() == null) ? 0 : hsThtoan.getHsthtoanNdm()) + ((hsThtoan.getHsthtoanDm() == null) ? 0 : hsThtoan.getHsthtoanDm());
			//log.info("tong chi " + tongchi);
			params.put("TONGCHIPHI" , tongchi );
			params.put("BANGCHU1", Utils.NumberToString(tongchi));
			params.put("BHCHTHANHTOAN" , hsThtoan.getHsthtoanBhyt() );
			//log.info("BHXH thanh toan  " + hsThtoan.getHsthtoanBhxh());
			params.put("BANGCHU3", Utils.NumberToString((hsThtoan.getHsthtoanBhyt() == null) ? 0 : hsThtoan.getHsthtoanBhyt()));
			params.put("NGUOIBENHTRA" ,hsThtoan.getHsthtoanThtoan());
			double nguoibenhtra = (hsThtoan.getHsthtoanThtoan() == null) ? 0 : hsThtoan.getHsthtoanThtoan() ;
			//log.info("nguoi benh tra  " + nguoibenhtra);
			if(hsThtoan.getHsthtoanThtoan() < 0){
				nguoibenhtra = -nguoibenhtra;
				params.put("BANGCHU2", "("+Utils.NumberToString(nguoibenhtra)+")");
			}else{
				params.put("BANGCHU2", Utils.NumberToString(nguoibenhtra));
			}
			
			params.put("TAMUNG", hsThtoan.getHsthtoanTamung());
			params.put("HOANUNG", hsThtoan.getHsthtoanHoanung());
			// phuc.lc 29-10-2010
			params.put("KYHIEU", hsThtoan.getHsthtoanKyhieu());
			params.put("QUYEN", hsThtoan.getHsthtoanQuyen());
			
//			Date currentDate = new Date();
//			int ns ;
//			if((benhNhan.getBenhnhanTuoi() != null) && (benhNhan.getBenhnhanDonvituoi() != null)){
//				if(benhNhan.getBenhnhanDonvituoi() == 1){
//					ns = (currentDate.getYear() + 1900 ) - benhNhan.getBenhnhanTuoi().intValue();
//				}else{ 
//					ns = (currentDate.getYear() + 1900 );
//				}
//				params.put("namsinh", String.valueOf(ns));
//			}
			params.put("namsinh", benhNhan.getBenhnhanNamsinh() == null ? "" : benhNhan.getBenhnhanNamsinh() );
			
			params.put("GIOITINH", benhNhan.getDmgtMaso(true).getDmgtTen());
			String diachistr="";
			if(benhNhan.getBenhnhanDiachi() != null)
				diachistr += benhNhan.getBenhnhanDiachi()+",";
			if(benhNhan.getXaMa(true).getDmxaTen() !=null)
				diachistr += benhNhan.getXaMa(true).getDmxaTen()+",";
			if(benhNhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhNhan.getHuyenMa(true).getDmhuyenTen()+",";
			if(benhNhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhNhan.getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			
//			if (hsba.getDoituongMa() != null && "TE".equals(hsba.getDoituongMa().getDmdoituongMa())){
				//TE
				if (hsbaBhyt != null){
					if (hsbaBhyt.getHsbabhytGiatri0() != null){
						params.put("GTTU", sdf.format(hsbaBhyt.getHsbabhytGiatri0()));
					}
					if (hsbaBhyt.getHsbabhytGiatri1() != null){
						params.put("GTDEN", sdf.format(hsbaBhyt.getHsbabhytGiatri1()));
					}
				}
//			}else{
//				//log.info("bat dau tim TD Tiep don ");
//				TiepDon tdEntiy = new TiepDon();
//				TiepDonDelegate tdDAO = TiepDonDelegate.getInstance();
//				tdEntiy = tdDAO.find(hsba.getTiepdonMa());
//				//log.info("Tiep don " + tdEntiy);
//				if(tdEntiy != null){
//					if(tdEntiy.getTiepdonGiatri3() != null)
//						params.put("GTTU", sdf.format(tdEntiy.getTiepdonGiatri3()));
//					if(tdEntiy.getTiepdonGiatri4() != null)
//					params.put("GTDEN", sdf.format(tdEntiy.getTiepdonGiatri4()));
//				}
//			}
			params.put("NOIDANGKY", hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienTen());
			//log.info("NOIDANGKY:"+params.get("NOIDANGKY"));
			
			params.put("NOICAPTHE", hsbaBhyt.getHsbabhytTinhbh(true).getDmtinhTen());
			params.put("NOIGIOITHIEU", hsba.getHsbaDonvigoi(true).getDmbenhvienTen());
			params.put("KHOA", hsba.getHsbaKhoadangdt(true).getDmkhoaTen());
			
			if(hsba.getHsbaNgaygiovaov() !=null)
				params.put("NGAYVAOVIEN",hsba.getHsbaNgaygiovaov());
			if(hsba.getHsbaNgaygiorav() !=null)
				params.put("NGAYRAVIEN", hsba.getHsbaNgaygiorav());
			
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			String bienlai = "";
			if (hsThtoan.getHsthtoanKyhieu() != null){
				bienlai +=hsThtoan.getHsthtoanKyhieu(); 
			}
			if (hsThtoan.getHsthtoanQuyen() != null){
				if (!"".equals(bienlai)){
					bienlai +="/"+hsThtoan.getHsthtoanQuyen(); 
				}else{
					bienlai +=hsThtoan.getHsthtoanQuyen(); 
				}
				
			}
			if (hsThtoan.getHsthtoanBienlai() != null){
				if (!"".equals(bienlai)){
					bienlai +="/"+hsThtoan.getHsthtoanBienlai(); 
				}else{
					bienlai +=hsThtoan.getHsthtoanBienlai(); 
				}
				
			}
			params.put("BIENLAI", bienlai);
			if (hsba.getDoituongMa() != null && "TP".equals(hsba.getDoituongMa().getDmdoituongMa() )){
				params.put("DOITUONG_MA", "TP");
			}
			
			//log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    //log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,getIndex(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","ThanhToanRaVien");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    //log.info("duong dan file xuat report :" + baocao1);
			    //log.info("duong dan -------------------- :"+reportPathVP);
			    setIndex(getIndex() + 1);
			    //log.info("Index :" + getIndex());
			    if(conn != null) {
			    	conn.close();
			    	conn = null;
			    }
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    //log.info("Thoat Method XuatReport");
	}
	
	public String thuchienAction(int type){
		//XuatReport();
		//return "B3360_Chonmenuxuattaptin";		
		return XuatPhieuThanhToan(type);
	}

	public String XuatPhieuThanhToanNgoaiTru() {
		//log.info("begin XuatPhieuThanhToanNgoaiTru() cvnt = " + cvnt);
		TiepDon tiepdon = cvnt.getTiepDon();		
		if (tiepdon == null) return null;
		BenhNhan benhnhan = tiepdon.getBenhnhanMa();
		ThamKham thamkham = new ThamKham();
		if(tiepdon.getTiepdonBankham() != null && tiepdon.getTiepdonBankham().getDtdmbankhamMa() != null && tiepdon.getTiepdonMa() != null){
			ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
			thamkham = thamkhamDelegate.findByBanKhamVaMaTiepDon(tiepdon.getTiepdonBankham().getDtdmbankhamMa(), tiepdon.getTiepdonMa());
		}
		if(benhnhan == null || thamkham == null) return null;
		reportTypeVP="PhieuThanhToanKCBNgoaiTru_2";
		
		String baocao1=null;
		try {
			//log.info("Vao Method XuatPhieuThanhToan() kham chua benh ngoai tru");
			
			SimpleDateFormat sdf=new SimpleDateFormat(Utils.FORMAT_DATE);			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			//String matiepDon = thamkham.getTiepdonMa().getTiepdonMa();
			params.put("MATIEPDON", tiepdon.getTiepdonMa() );
			params.put("HOTENBN", benhnhan.getBenhnhanHoten()  );
			
			String diachistr="";
			if(benhnhan.getBenhnhanDiachi() != null)
				diachistr += benhnhan.getBenhnhanDiachi()+", ";
			if(benhnhan.getXaMa(true).getDmxaTen() !=null)
				diachistr += benhnhan.getXaMa(true).getDmxaTen()+", ";
			if(benhnhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhnhan.getHuyenMa(true).getDmhuyenTen()+", ";
			if(benhnhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhnhan.getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			if(tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH")){
				params.put("BHYT_CO", "X" );
				
				if( (tiepdon.getTiepdonTuyen() != null && tiepdon.getTiepdonTuyen().toString().equals("1"))
						|| (tiepdon.getTiepdonCoGiayGioiThieu() != null && tiepdon.getTiepdonCoGiayGioiThieu()) ){
					params.put("DUNGTUYEN","X");
				} else {
					params.put("TRAITUYEN","X");
				}
				
			} else {
				params.put("BHYT_KO", "X" );
			}
			
			if(tiepdon.getTiepdonGiatri1() != null){
				params.put("GTTU", sdf.format(tiepdon.getTiepdonGiatri1()));
			}else{
				params.put("GTTU", "");
			}
				
			if(tiepdon.getTiepdonGiatri2() != null){
				params.put("GTDEN", sdf.format(tiepdon.getTiepdonGiatri2()));
			}else{
				params.put("GTDEN", "");
			}
			if (tiepdon.getTiepdonSothebh() != null && !tiepdon.getTiepdonSothebh().equals("") &&
					tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMa() != null && !tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMa().equals("") &&
					tiepdon.getKcbbhytMa(true).getDmbenhvienMa()!=null
			){
				
				params.put("MATHEBHYT", tiepdon.getTiepdonSothebh());
				params.put("MABENHVIEN", tiepdon.getKcbbhytMa(true).getDmbenhvienMa().replace(".", "-"));
				
				
			}else{
				params.put("MABENHVIEN","");
				params.put("MATHEBHYT","");
			}
			
			if(tiepdon.getKcbbhytMa(true).getDmbenhvienTen()!=null)
				params.put("NOIDKKCBBANDAU", tiepdon.getKcbbhytMa(true).getDmbenhvienTen());
			if(tiepdon.getTinhbhytMa(true).getDmtinhTen()!=null)
				params.put("NOICAPTHE", tiepdon.getTinhbhytMa(true).getDmtinhTen());
			
			if(tiepdon.getTiepdonDonvigoi(true).getDmbenhvienMa()!=null)
				params.put("NOIGIOITHIEU", tiepdon.getTiepdonDonvigoi(true).getDmbenhvienTen());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(thamkham.getThamkhamNgaygio());
			params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));
			
			params.put("LYDOVAOVIEN", tiepdon.getTiepdonLydovaov());
			
			HsThtoankDelegate thanhToanDel = HsThtoankDelegate.getInstance();
			HsThtoank hsttk = new HsThtoank();
			hsttk = (HsThtoank)thanhToanDel.findBytiepdonMaLast(tiepdon.getTiepdonMa()); //20101109 bao.ttc: tim HSTTK cuoi cung 
			if (hsttk == null || (hsttk.getHsthtoankDatt() != null && !hsttk.getHsthtoankDatt())) {
				hsttk = new HsThtoank();
				hsttk.setTiepdonMa(tiepdon);			
				
				ThamKhamUtil tkUtil = new ThamKhamUtil();
				tkUtil.tinhToanChoHSTKKham(thamkham, hsttk, false, null, null);//tinhToanChoHSTKKham(hsttk);
				Utils.setInforForHsThToan(hsttk);
			}
			//log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
			
			params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt() );
			params.put("NGUOIBENHTRA", hsttk.getHsthtoankBntra());
			
			// 20110701 bao.ttc: them cac thong tin cho mau moi dung tu ngay 1/7
			if(hsttk.getHsthtoankNgaygiott() != null){
				params.put("NGAYTHANHTOAN", hsttk.getHsthtoankNgaygiott());
				if(thamkham.getThamkhamNgaygio() != null){
					params.put("SONGAYDT", Utils.getSoNgayDieuTri(thamkham.getThamkhamNgaygio(), hsttk.getHsthtoankNgaygiott()));
				}
			}
			
			if(tiepdon.getTiepdonBankham() != null){
				if(tiepdon.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC")){
					params.put("CAPCUU","X");
					params.put("DUNGTUYEN","");
					params.put("TRAITUYEN","");
				}
			}
			
			params.put("PHIEUSO", hsttk.getHsthtoankMa());
			//log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
			params.put("TYLEBH",hsttk.getHsthtoankTylebh());
			String tyleBNtra = ""+ (100 - hsttk.getHsthtoankTylebh() );
			
			if ("MP".equals( tiepdon.getDoituongMa(true).getDmdoituongMa())){
				tyleBNtra = "0";
			}
			params.put("PHANTRAMBNTRA",tyleBNtra);
			
			params.put("BIENLAISO", ""  );
			
			String namsinh = "";
			if (tiepdon.getBenhnhanMa(true).getBenhnhanNgaysinh() != null){
				namsinh = sdf.format(tiepdon.getBenhnhanMa(true).getBenhnhanNgaysinh());
			} else {
				namsinh = tiepdon.getBenhnhanMa(true).getBenhnhanNamsinh();
			}
			params.put("namsinh", namsinh);
						
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			String maChanDoan = "";
			String tenChanDoan ="";					
			
			if (thamkham.getBenhicd10() != null && thamkham.getBenhicd10().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					params.put("MABENHICD", maChanDoan);
					tenChanDoan = benh.getDmbenhicdTen();
				}
				
			}
			
			String chanDoan = maChanDoan + " " +  tenChanDoan;
			
			// bao.ttc: them thamkham.ghichu
			if (thamkham.getThamkhamGhichu() != null && !thamkham.getThamkhamGhichu().equals("")) {
				chanDoan += " , " + thamkham.getThamkhamGhichu();
			}
			
			//tiep tuc them benh phu.
			if (thamkham.getBenhicd10phu1() != null && thamkham.getBenhicd10phu1().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			if (thamkham.getBenhicd10phu2() != null && thamkham.getBenhicd10phu2().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					if (chanDoan.equals("")) {
						chanDoan += maChanDoan + " " +  tenChanDoan;
					} else {
						chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
					}
				}
				
			}
			if (thamkham.getBenhicd10phu3() != null && thamkham.getBenhicd10phu3().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					if (chanDoan.equals("")) {
						chanDoan += maChanDoan + " " +  tenChanDoan;
					} else {
						chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
					}
				}
				
			}
			if (thamkham.getBenhicd10phu4() != null && thamkham.getBenhicd10phu4().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					if (chanDoan.equals("")) {
						chanDoan += maChanDoan + " " +  tenChanDoan;
					} else {
						chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
					}
				}
				
			}
			if (thamkham.getBenhicd10phu5() != null && thamkham.getBenhicd10phu5().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					if (chanDoan.equals("")) {
						chanDoan +=maChanDoan + " " +  tenChanDoan;
					} else {
						chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
					}
				}
				
			}
			params.put("CHANDOAN", chanDoan );
			
			params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamTen() );
			//Lay danh sach khoa da kham
			List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(tiepdon.getTiepdonMa());
			List<ClsKham> listClsTmp;
			List<ThuocPhongKham> listTpk;
			StringBuffer bufStr = new StringBuffer();
			Double tongTienDV = 0.0;
			if (listTk != null && listTk.size() > 0) {
				List<Integer> listMasoKhoa = new ArrayList<Integer>(listTk.size());
				for (ThamKham each : listTk) {
					if(each.getThamkhamBankham().getDmkhoaMaso() != null) {
						if(listMasoKhoa.contains(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaMaso())) {
							continue;
						} else {
							listMasoKhoa.add(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaMaso());
						}
										
						if (bufStr.length() > 0) {
							bufStr.append(", " + each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
						} else {
							bufStr.append(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
						}
					}
				}
			}		
					// Kiem tra co su dung cls yeu cau khong
					listClsTmp = ClsKhamDelegate.getInstance().findByTiepdonma(tiepdon.getTiepdonMa());
					if (listClsTmp != null && listClsTmp.size() > 0) {
						for (ClsKham eachCls : listClsTmp) {
							if (((eachCls.getClskhamYeucau() != null && eachCls.getClskhamYeucau().booleanValue() == true)
									|| (eachCls.getClskhamNdm() != null && eachCls.getClskhamNdm().booleanValue() == true))
									&& eachCls.getClskhamPhandv() != null) {
								tongTienDV += eachCls.getClskhamPhandv();
							}
						}
					}
					// Kiem tra co su dung thuoc yeu cau khong
					
					listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepdon.getTiepdonMa(), "1");
					if (listTpk != null && listTpk.size() > 0) {
						for (ThuocPhongKham eachTpk : listTpk) {
							if ((eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true)
									|| (eachTpk.getThuocphongkhamNdm() != null && eachTpk.getThuocphongkhamNdm().booleanValue() == true))	{
								tongTienDV += eachTpk.getThuocphongkhamDongia(); 
							}
						}
					}
					listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepdon.getTiepdonMa(), "3");
					if (listTpk != null && listTpk.size() > 0) {
						for (ThuocPhongKham eachTpk : listTpk) {
							if ((eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true)
									|| (eachTpk.getThuocphongkhamNdm() != null && eachTpk.getThuocphongkhamNdm().booleanValue() == true))	{
								tongTienDV += eachTpk.getThuocphongkhamDongia(); 
							}
						}
					}
				
			
			params.put("KHOA",bufStr.toString());
			String soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonSothete();
			
			
			//them vao khai sinh
			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")){
				if (tiepdon.getTiepdonKhaisinh() == null ||
						tiepdon.getTiepdonKhaisinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh = tiepdon.getTiepdonKhaisinh();
				}
				
			}else{
				if (tiepdon.getTiepdonKhaisinh() == null ||
						tiepdon.getTiepdonKhaisinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh += "/" + tiepdon.getTiepdonKhaisinh();
				}				
			}
			// them vao chung sinh
			
			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")){
				if (tiepdon.getTiepdonChungsinh() == null ||
						tiepdon.getTiepdonChungsinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh = tiepdon.getTiepdonChungsinh();
				}
				
			}else{
				if (tiepdon.getTiepdonChungsinh() == null ||
						tiepdon.getTiepdonChungsinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh += "/" + tiepdon.getTiepdonChungsinh();
				}				
			}
			///
			
			//log.info("soTheTe_KhaiSinh_ChungSinh"+soTheTe_KhaiSinh_ChungSinh);
			
			
			if (soTheTe_KhaiSinh_ChungSinh != null && !soTheTe_KhaiSinh_ChungSinh.equals("")){
				params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
			}else{
				params.put("SOTHETEKSCS", null);
			}
			//
			String soTheNgheo = tiepdon.getTiepdonThengheo();
			
			//log.info("soTheNgheo"+soTheNgheo);
			
			if (soTheNgheo != null && !soTheNgheo.equals("")){
				params.put("SOTHENGHEO", soTheNgheo);
			}else{
				params.put("SOTHENGHEO", null);
			}
			
			//SUB REPORT 3
			params.put("TONGCHIPHI", hsttk.getHsthtoankTongchi());
			params.put("BANGCHU1", Utils.NumberToString(hsttk.getHsthtoankTongchi()));
			params.put("BANGCHU2", Utils.NumberToString(hsttk.getHsthtoankBntra()));
			params.put("BANGCHU3", Utils.NumberToString(hsttk.getHsthtoankBhyt()));
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			if(hsttk.getHsthtoankBntra() >= 0){
				params.put("SNGUOIBENHTRA", "0");
			}else{
				params.put("SNGUOIBENHTRA", "-1");
			}
			
			DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(tiepdon.getBenhnhanMa(true).getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh","dmgtMaso" );
			if (gioi != null){

				params.put("GIOI", gioi.getDmgtTen());
						
			}
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			//log.info("tongTienDV = " + tongTienDV);
			JasperReport jasperReport;
			if (tongTienDV > 0) {
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_DV.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
				String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport0.jrxml";
				String sub3Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport1.jrxml";
				
				jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				JasperReport sub2Report =JasperCompileManager.compileReport(sub2Template);
				JasperReport sub3Report =JasperCompileManager.compileReport(sub3Template);
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
				params.put("sub2", sub2Report);
				params.put("sub3", sub3Report);
			} else {
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
				
				jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
			}
			//log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    //log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,getIndex(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","PhieuThanhToanKCB");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    //log.info("duong dan file xuat report :" + baocao1);
			    //log.info("duong dan -------------------- :"+reportPathVP);
			    setIndex(getIndex() + 1);
			    //log.info("Index :" + getIndex());
			    if(conn != null) {
			    	conn.close();
			    	conn = null;
			    }
		}catch (Exception e) {
		    log.info("ERROR Method XuatPhieuThanhToan!!!");
			e.printStackTrace();
			return null;
		}
		//log.info("Thoat Method XuatPhieuThanhToan");
		if(nhanVienThungan==null){
			refreshNhanVienThuNgan();
		}
		return "B3360_Chonmenuxuattaptin";
		
	    
	}	
	public String troVe() {
		try {
			//log.info("***** troVe()");
			return "B3241_Thanhtoanravien";
		} catch (Exception e) {
			log.info("***** End exception = " + e);
		}
		return null;
	}
	
	private void setOtherValue() {
		//log.info("Begining setOtherValue()");
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
//		if (benhNhan.getBenhnhanNgaysinh() != null
//				&& !benhNhan.getBenhnhanNgaysinh().equals("")) {
//			Calendar cal = Calendar.getInstance();
//			Integer tuoi_temp = cal.getTime().getYear()
//					- benhNhan.getBenhnhanNgaysinh().getYear();
//			tuoi = tuoi_temp.toString();
//			//log.info("Tuoi  :" + tuoi);
//		}
		tuoi = String.valueOf( benhNhan.getBenhnhanTuoi());
		if (benhNhan.getDmgtMaso() != null) {
			if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())) {
				gioi = "r1"; // 1 : Name
			} else {
				gioi = "r2";
			}
		} else {
			gioi = null;
		}
		if (hsbaBhyt.getHsbabhytSothebh() != null
				|| hsbaBhyt.getHsbabhytSothengheo() != null) {
			if (hsbaBhyt.getHsbabhytSothebh() != null) {
				soThe = hsbaBhyt.getHsbabhytSothebh();
			} else {
				soThe = hsbaBhyt.getHsbabhytSothengheo();
			}
		} else {
			soThe = "";
		}
		if (hsba.getHsbaNgaygiovaov() != null) {
			ngayNhapvien = formatter
					.format(hsba.getHsbaNgaygiovaov().getTime());
		} else {
			ngayNhapvien = "";
		}
		if (hsba.getHsbaNgaygiorav() != null) {
			ngayRavien = formatter
					.format(hsba.getHsbaNgaygiorav().getTime());
		} else {
			ngayRavien = "";
		}

		if (hsThtoan != null && hsThtoan.getHsthtoanNgaytt() != null){
			Date ngayGioTT = hsThtoan.getHsthtoanNgaytt();
			gioThanhToan = Utils.getGioPhut(ngayGioTT);
			ngayTtoan = formatter.format(ngayGioTT);
		}
		
		//log.info("End setOtherValue()");
	}

	// Hien thi thong tin
	public void displayInfor() throws Exception {
		try {

			if ( hsba.getHsbaSovaovien() == null || hsba.getHsbaSovaovien().equals("")) {
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsba.getHsbaSovaovien());
				hasNoKhoaOrSoVaoVien();
				return;
			}

			HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
			Hsba hsbaCur = hsbaDelegate.find(hsba.getHsbaSovaovien());
		    if  (hsbaCur == null){
		    	FacesMessages.instance().add(IConstantsRes.HSBA_NULL, hsba.getHsbaSovaovien());
				ResetForm();
				return ;
		    }
		    
		    // hsba da ton tai
		    hsba = hsbaCur;
			
		    if (hsba.getHsbaKhoadangdt() != null){
		    	khoaMa = hsba.getHsbaKhoadangdt(true).getDmkhoaMa();
		    }
		    
			
			HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
			
			//log.info("Begining displayInfor()");
			
			// 20120312 bao.ttc: tim list cac khoa DT can in ca Tang de phan biet
			List<HsbaKhoa> lstHsbaKhoa = hsbaKhoaDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
			cacKhoadt = "";
				for (HsbaKhoa hK : lstHsbaKhoa) {
					cacKhoadt += hK.getKhoaMa().getDmkhoaMa() + "   ";
				}

//				SetInforUtil.setInforIfNullForHSBA(hsba);
				if (hsba.getBenhnhanMa() != null) {
					benhNhan = hsba.getBenhnhanMa();
				} else {
					benhNhan = new BenhNhan();
				}

//				SetInforUtil.setInforIfNullForBN(benhNhan);
				if (hsba.getHsbaSovaovien() != null	&& !hsba.getHsbaSovaovien().equals("")) {
					try {

						HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
						HsbaBhyt hsbaBhyt_temp = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
						if (hsbaBhyt_temp != null) {
							hsbaBhyt = hsbaBhyt_temp;
						} else {
							hsbaBhyt = new HsbaBhyt();
						}
//						SetInforUtil.setInforIfNullForHSBABHYT(hsbaBhyt);
					} catch (Exception ex) {
						log.error("Loi trong khi load HSBABHYT : " + ex.toString());
					}
					
					try {

						HsbaChuyenVienDelegate hsbacvDelegate = HsbaChuyenVienDelegate.getInstance();

						HsbaChuyenVien hsbaChuyenVien_temp = hsbacvDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
						if (hsbaChuyenVien_temp != null) {
							hsbaChuyenvien = hsbaChuyenVien_temp;
						} else {
							hsbaChuyenvien = new HsbaChuyenVien();
						}
//						SetInforUtil.setInforIfNullForHsbaChuyenVien(hsbaChuyenvien);
					} catch (Exception ex) {
						log.error("Loi trong khi load Hsba chuyen vien : " + ex.toString());
					}

				} else {
					hsbaBhyt = new HsbaBhyt();
//					SetInforUtil.setInforIfNullForHSBABHYT(hsbaBhyt);
					hsbaChuyenvien = new HsbaChuyenVien();
//					SetInforUtil.setInforIfNullForHsbaChuyenVien(hsbaChuyenvien);
				}

				
				try {

					HsThtoanDelegate hsttDelegate = HsThtoanDelegate
							.getInstance();
					HsThtoan hsbaHsThtoan_temp = hsttDelegate
							.findBySovaovien(hsba.getHsbaSovaovien());
					cvnt = ChuyenVaoNoiTruDelegate.getInstance().findBySoVaoVien(hsba.getHsbaSovaovien());
					if (hsbaHsThtoan_temp != null && hsbaHsThtoan_temp.getHsthtoanNgaytt() != null) {
						hsThtoan = hsbaHsThtoan_temp;
						
						ungTra = ( hsThtoan.getHsthtoanTamung() == null ? 0 : hsThtoan.getHsthtoanTamung() )
								- ( hsThtoan.getHsthtoanHoanung() == null ? 0 : hsThtoan.getHsthtoanHoanung() );
						
						if(hsThtoan.getHsthtoanLidothanhtoan()==null || hsThtoan.getHsthtoanLidothanhtoan().equals(""))
							sLiDoTT = IConstantsRes.VIEN_PHI;
						else
							sLiDoTT = hsThtoan.getHsthtoanLidothanhtoan();
						if(hsThtoan.getHsthtoanThungan()!=null) {
							nhanVienThungan = hsThtoan.getHsthtoanThungan();
						} else {
							nhanVienThungan = new DtDmNhanVien();
						}
						// hienThiGhiNhan="";
						// hienThiInPhieu="";
						// FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
						//log.error("Tim thay ho so thanh toan : " + hsThtoan);
						if (cvnt != null) {
							hsttk = HsThtoankDelegate.getInstance().findBytiepdonMaLast(cvnt.getTiepDon().getTiepdonMa());	
							ungTra	+= ((hsttk.getHsthtoankTamung() == null ? 0 : hsttk.getHsthtoankTamung())
									+ (hsttk.getHsthtoankHoanung() == null ? 0 : hsttk.getHsthtoankHoanung()));
						}
						conlai = new Double(0.0);
					} else {
						hsThtoan = new HsThtoan();
						hsThtoan.setHsbaSovaovien(hsba);
						sLiDoTT = IConstantsRes.VIEN_PHI;
						//tinhToanChoHSTT(hsThtoan);
						HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
						hsthtoanUtil.tinhToanChoHSTT(hsThtoan);

						Utils.setInforForHsThToan(hsThtoan);
						
						hsttk = hsthtoanUtil.getHsttk();
						if (hsttk != null) {
							Utils.setInforForHsThToan(hsttk);
						}
						
						ungTra = hsThtoan.getHsthtoanTamung()
								- hsThtoan.getHsthtoanHoanung();
						if (cvnt != null) {
							ungTra	+= ((hsttk.getHsthtoankTamung() == null ? 0 : hsttk.getHsthtoankTamung())
								+ (hsttk.getHsthtoankHoanung() == null ? 0 : hsttk.getHsthtoankHoanung()));
						}
						//log.info("ungTra:" + ungTra);
						conlai = hsThtoan.getHsthtoanThtoan() + (cvnt == null ? 0 :hsttk.getHsthtoankThtoan());					
					}	
						thuocNDM = (hsThtoan.getHsthtoanThuocndm() == null ? 0 : hsThtoan.getHsthtoanThuocndm()) + (cvnt == null ? 0 :(hsttk.getHsthtoankThuocndm() == null ? 0 : hsttk.getHsthtoankThuocndm()));
						thuocTrongDM = (hsThtoan.getHsthtoanThuoc() == null ? 0 : hsThtoan.getHsthtoanThuoc()) + (cvnt == null ? 0 :(hsttk.getHsthtoankThuoc() == null ? 0 : hsttk.getHsthtoankThuoc()));
						vTTHTrongDM = (hsThtoan.getHsthtoanVtth() == null ? 0 : hsThtoan.getHsthtoanVtth()) + (cvnt == null ? 0 :(hsttk.getHsthtoankVtth() == null ? 0 : hsttk.getHsthtoankVtth()));
						vTTHNDM = (hsThtoan.getHsthtoanVtthndm() == null ? 0 : hsThtoan.getHsthtoanVtthndm()) + (cvnt == null ? 0 :(hsttk.getHsthtoankVtthndm() == null ? 0 : hsttk.getHsthtoankVtthndm()));
						cLSMauTrongDM = (hsThtoan.getHsthtoanCls() == null ? 0 : hsThtoan.getHsthtoanCls()) + (cvnt == null ? 0 :(hsttk.getHsthtoankCls() == null ? 0 : hsttk.getHsthtoankCls()));
						clsMauNDM = (hsThtoan.getHsthtoanClsndm() == null ? 0 : hsThtoan.getHsthtoanClsndm()) + (cvnt == null ? 0 :(hsttk.getHsthtoankClsndm() == null ? 0 : hsttk.getHsthtoankClsndm()));
						tongTrongDM = hsThtoan.getHsthtoanDm()
									+ (cvnt == null ? 0 :(hsttk.getHsthtoankThuoc() == null ? 0 : hsttk.getHsthtoankThuoc()))
									+ (cvnt == null ? 0 :(hsttk.getHsthtoankVtth() == null ? 0 : hsttk.getHsthtoankVtth()))
									+ (cvnt == null ? 0 :(hsttk.getHsthtoankCls() == null ? 0 : hsttk.getHsthtoankCls()));
						tongNDM = hsThtoan.getHsthtoanNdm()
									+ (cvnt == null ? 0 :(hsttk.getHsthtoankThuocndm() == null ? 0 : hsttk.getHsthtoankThuocndm()))
									+ (cvnt == null ? 0 :(hsttk.getHsthtoankVtthndm() == null ? 0 : hsttk.getHsthtoankVtthndm()))
									+ (cvnt == null ? 0 :(hsttk.getHsthtoankClsndm() == null ? 0 : hsttk.getHsthtoankClsndm()));
						
						benhnhanTra = hsThtoan.getHsthtoanBntra() + (cvnt == null ? 0 :hsttk.getHsthtoankBntra()) - (hsThtoan.getHsthtoanMiendt() + (cvnt == null ? 0 :hsttk.getHsthtoankXetgiam()));
						// Chua xac dinh chi phi that thu
						miengiam = hsThtoan.getHsthtoanMiendt() + (cvnt == null ? 0 :hsttk.getHsthtoankXetgiam());
						nSKhongThu = hsThtoan.getHsthtoanKhongthu() + (cvnt == null ? 0 :hsttk.getHsthtoankKhongthu());
						
						
					//}
//					SetInforUtil.setInforIfNullForHsThtoan(hsThtoan);
					
					//log.info("hsThtoan.getHsthtoanMa():"+hsThtoan.getHsthtoanMa());
					if (hsThtoan != null && hsThtoan.getHsthtoanMa() != null && !hsThtoan.getHsthtoanMa().equals("")){
						hienThiHuyPhieu = true;
						hienThiGhiNhan = false;
						hienThiInPhieu = true;
						hienThiInPhieuNgT = (cvnt != null);
					}else{
						hienThiHuyPhieu = false;
						hienThiGhiNhan = true;
						hienThiInPhieu = false;
						hienThiInPhieuNgT = false;
					}
					
					
					//hienThiInPhieu = false;
					// Fix bug 3566
					ClsMoDelegate clsMoDelegate  = ClsMoDelegate.getInstance();
					listClsMo = clsMoDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
					
					List<ClsMo> listClsMoTmp = new ArrayList<ClsMo>();
					int trungIndex;
					//log.info("Begin check trung ClsMo ");
					for(ClsMo eachClsMo : listClsMo) {
						trungIndex = checkTrungClsMo(listClsMoTmp, eachClsMo);
						//log.info("Ma cls = " + eachClsMo.getClsmoMahang().getDtdmclsbgMa() + ", trungIndex = " + trungIndex);
						if (trungIndex < 0) {
							// Khong trung
							listClsMoTmp.add(eachClsMo);
						}else {
							listClsMoTmp.get(trungIndex).setClsmoLan(new Short("" + (listClsMoTmp.get(trungIndex).getClsmoLan() + eachClsMo.getClsmoLan())));							
						}
					}
					//log.info("End check trung ClsMo listClsMoTmp.size() = " + listClsMoTmp.size());
					ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();						
					listThuocNT = tntDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
					List<ThuocNoiTru> listTntTmp = new ArrayList<ThuocNoiTru>();
					//log.info("Begin check trung Thuoc noi tru ");
					for(ThuocNoiTru eachTnt : listThuocNT) {
						trungIndex = checkTrungThuocNT(listTntTmp, eachTnt);						
						if (trungIndex < 0) {
							// Khong trung
							eachTnt.setThuocnoitruSoluong(eachTnt.getThuocnoitruSoluong() - (eachTnt.getThuocnoitruTra() == null ? 0 : eachTnt.getThuocnoitruTra()));
							//eachTnt.setThuocnoitruThanhtien(new Double(eachTnt.getThuocnoitruSoluong() * eachTnt.getThuocnoitruDongiatt()).intValue());
							//phuc.lc 22/09/2011 : Fix bug 4021 : Chi lay nhung thuoc co so luong > 0
							if (eachTnt.getThuocnoitruSoluong().doubleValue() > 0 ) {
								listTntTmp.add(eachTnt);							
							}
						}else {							
							listTntTmp.get(trungIndex).setThuocnoitruSoluong(new Double("" + (listTntTmp.get(trungIndex).getThuocnoitruSoluong() + (eachTnt.getThuocnoitruSoluong() - (eachTnt.getThuocnoitruTra() == null ? 0 : eachTnt.getThuocnoitruTra())))));							
							//listTntTmp.get(trungIndex).setThuocnoitruThanhtien(listTntTmp.get(trungIndex).getThuocnoitruThanhtien() + new Double(eachTnt.getThuocnoitruSoluong() * eachTnt.getThuocnoitruDongiatt()).intValue());
							listTntTmp.get(trungIndex).setThuocnoitruThanhtien(listTntTmp.get(trungIndex).getThuocnoitruThanhtien() + eachTnt.getThuocnoitruThanhtien());
						}
					}
					//log.info("End check trung ThuocNT listTntTmp.size() = " + listTntTmp.size());
					listClsMo.clear();
					listThuocNT.clear();
					listClsMo = listClsMoTmp;
					listThuocNT = listTntTmp;
					
					
					if (cvnt != null) {
						// Lay CLS ngoai tru
						//ClsKhamDelegate clsKhamDelegate  = ClsKhamDelegate.getInstance();
						//listClsKham = clsKhamDelegate.findByTiepdonma(cvnt.getTiepDon().getTiepdonMa());
					}	
				} catch (Exception ex) {
					log.error("Loi trong khi load ho so thanh toan : "
							+ ex.toString());
					ex.printStackTrace();
				}
				//log.info( "-----------------hsba.getHsbaNgaygiovaov222222222222():"+hsba.getHsbaNgaygiovaov());
				setOtherValue();
				
				if ("xemchiphidieutri".equals(ten3241_3242)){
					hienThiGhiNhan = false;
					hienThiInPhieu = false;
					hienThiHuyPhieu = false;
					hienThiInPhieuNgT = false;
				}
				
				
				if (!hienThiGhiNhan) {					
					if ("xemchiphidieutri".equals(ten3241_3242)){
						// 20101209 bao.ttc: neu la form "Xem chi phi dieu tri" thi khong hien cau thong bao nay vi khong hop ly
						//phuc.lc 14-07-2011 : fix bug 3683, Cho phep in phieu khi xem chi phi dieu tri
						hienThiInPhieu = true;
						hienThiInPhieuNgT = (cvnt != null);
						// Truong hop chuyen vao noi tru nhung khong chuyen so lieu thi hien thi thong bao nay
						/*if(cvnt == null && hsba.getHsbaType() != null) {
							if(!hsba.getHsbaType().equals("")) {
								FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THONG_TIN_NGOAI_TRU, hsba.getTiepdonMa());
							}
						}*/
					}else {
						//hsThtoan.setHsthtoanThtoan(new Double(0.0));
						conlai = new Double(0.0);						
						FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
					}
				} /*else if (!"xemchiphidieutri".equals(ten3241_3242)){					
					if(hsba.getHsbaType() != null) {
						if(!hsba.getHsbaType().equals("")) {
							FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THONG_TIN_NGOAI_TRU, hsba.getTiepdonMa());
						}
					}
				}*/
				// Truong hop chuyen vao noi tru nhung khong chuyen so lieu thi hien thi thong bao nay
				if(hsba.getHsbaType() != null) {
					if(!hsba.getHsbaType().equals("")) {
						HsThtoank hsttk = HsThtoankDelegate.getInstance().findBytiepdonMaLast(hsba.getTiepdonMa());						
						TiepDon td = TiepDonDelegate.getInstance().find(hsba.getTiepdonMa());
						String capcuu = "";
						if (td != null) {
							if (td.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC")){
								capcuu = IConstantsRes.CAP_CUU;										
							}
							
						}
						if (hsttk != null) {
							FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THONG_TIN_NGOAI_TRU,
									capcuu + "(" + (hsttk.getHsthtoankNgaygiott() != null ? IConstantsRes.DA_THANH_TOAN : IConstantsRes.CHUA_THANH_TOAN) + ")" ,
									hsba.getTiepdonMa());
						} else {
							FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THONG_TIN_NGOAI_TRU,
									capcuu + "(" + IConstantsRes.CHUA_THANH_TOAN + ")" ,
									hsba.getTiepdonMa());
						}
					}
				}
				//log.info( "End displayInfor(), hienThiInPhieuNgT = " + hienThiInPhieuNgT);
		} catch (Exception e) {
			log.error((new StringBuilder()).append(
					"error on function displayInfor").append(e).toString());

		}
		//log.info("End displayInfor()");
	}
	private int checkTrungClsMo(List<ClsMo> listClsMoTmp, ClsMo eachClsMo){
		if (listClsMoTmp.size() < 1) return -1;
		for (ClsMo clsMoTmp : listClsMoTmp) {
			if (clsMoTmp.getClsmoMahang().getDtdmclsbgMa().equalsIgnoreCase(eachClsMo.getClsmoMahang().getDtdmclsbgMa())){
				if(clsMoTmp.getClsmoDongia().equals(eachClsMo.getClsmoDongia())
					&& ((clsMoTmp.getClsmoMien() == null && eachClsMo.getClsmoMien() == null) || clsMoTmp.getClsmoMien().equals(eachClsMo.getClsmoMien()))
					&& ((clsMoTmp.getClsmoYeucau() == null && eachClsMo.getClsmoYeucau() == null) || clsMoTmp.getClsmoYeucau().equals(eachClsMo.getClsmoYeucau()))
					&& ((clsMoTmp.getClsmoKtcao() == null && eachClsMo.getClsmoKtcao() == null) || clsMoTmp.getClsmoKtcao().equals(eachClsMo.getClsmoKtcao()))
					) {
					return listClsMoTmp.indexOf(clsMoTmp);
				}				
			}
		}
		return -1;
	}
	private int checkTrungThuocNT(List<ThuocNoiTru> listTntTmp, ThuocNoiTru eachTnt) {
		if (listTntTmp.size() < 1) return -1;
		for (ThuocNoiTru tntTmp : listTntTmp) {
			if (tntTmp.getThuocnoitruMathuoc().getDmthuocMa().equalsIgnoreCase(eachTnt.getThuocnoitruMathuoc().getDmthuocMa())
					&& tntTmp.getThuocnoitruDongia().equals(eachTnt.getThuocnoitruDongia())) {				
					return listTntTmp.indexOf(tntTmp);
				}							
		}
		return -1;
	}
	public void huyPhieu(){
		log.info("Begin huyPhieu, hsttk = " + hsttk + ", cvnt = " + cvnt);
		HsThtoanDelegate hsTTDelegate = HsThtoanDelegate.getInstance();
		String maPhieu = hsThtoan.getHsthtoanMa();
		
		//hsTTDelegate.remove(hsThtoan);
		// phuc.lc 31-10/2011 : khi huy phieu thanh toan, chi cap nhat ngay TT thanh null, khong xoa toan bo thong tin nua
		hsThtoan.setHsthtoanNgaytt(null);
		hsThtoan.setHsthtoanDatt(null);
		hsTTDelegate.edit(hsThtoan);
		// Lay danh sach CLS theo ma tiep don de cap nhat lai
		if(cvnt != null) {
			hsttk = HsThtoankDelegate.getInstance().findBytiepdonMaLast(cvnt.getTiepDon().getTiepdonMa());
		}
		//log.info("Ma hsttk = " + hsttk.getHsthtoankMa());
		if (hsttk != null ) {
			ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
			List<ClsKham> listCls = clsKhamDel.findByMaPhieu(hsttk.getHsthtoankMa());
			//log.info("listCls.size() = " + listCls.size());
			if (listCls != null && listCls.size() > 0) {
				for(ClsKham eachCls : listCls) {
					eachCls.setClskhamDatt(null);
					eachCls.setClskhamNgaygiott(null);
					eachCls.setClskhamMaphieu(null);
					clsKhamDel.edit(eachCls);
				}
			}
			// Lay danh sach Thuoc phong kham theo ma phieu thanh toan de cap nhat lai
			ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
			List<ThuocPhongKham> listTpk = tpkDelegate.findByMaPhieu(hsttk.getHsthtoankMa());
			if (listTpk != null && listTpk.size() > 0) {
				for(ThuocPhongKham eachTpk : listTpk) {
					eachTpk.setThuocphongkhamDatt(null);
					eachTpk.setThuocphongkhamNgaygiott(null);
					eachTpk.setThuocphongkhamMaphieud(null);
					tpkDelegate.edit(eachTpk);
				}
			}
			// Cap nhat ho so thanh toan kham
			hsttk.setHsthtoankDatt(null);
			hsttk.setHsthtoankNgaygiott(null);
			hsttk.setHsthtoankLanin(null);
			HsThtoankDelegate.getInstance().edit(hsttk);
		}
		ResetForm();
		FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, maPhieu);
		if(nhanVienThungan==null){
			refreshNhanVienThuNgan();
		}
	}
	public void findBySoPhieu() {
		//log.info("Begin findBySoPhieu, phieu so = " + hsThtoan.getHsthtoanMa());
		String sophieu = hsThtoan.getHsthtoanMa();
		if (hsThtoan.getHsthtoanMa() != null && hsThtoan.getHsthtoanMa().trim().length() > 0) {
			hsThtoan = HsThtoanDelegate.getInstance().find(hsThtoan.getHsthtoanMa());
			if (hsThtoan != null) {
				if (hsThtoan.getHsthtoanNgaytt() == null) {
					ResetForm();
					FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, sophieu);
					return;
				}
				khoaMa = (hsThtoan.getHsthtoanKhoa() != null ? hsThtoan.getHsthtoanKhoa().getDmkhoaMa() : "");
				hsba = hsThtoan.getHsbaSovaovien();
				benhNhan = hsba.getBenhnhanMa();				
				nhanVienThungan = hsThtoan.getHsthtoanThungan();
				if (nhanVienThungan == null) {
					nhanVienThungan = new DtDmNhanVien();
				}
				HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
		        HsbaBhyt hsbaBhyt_temp = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
				if (hsbaBhyt_temp != null) {
					hsbaBhyt = hsbaBhyt_temp;
				} else {
					hsbaBhyt = new HsbaBhyt();
				}
				
				// 20120312 bao.ttc: tim list cac khoa DT can in ca Tang de phan biet
				HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
				List<HsbaKhoa> lstHsbaKhoa = hsbaKhoaDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
				cacKhoadt = "";
				for (HsbaKhoa hK : lstHsbaKhoa) {
					cacKhoadt += hK.getKhoaMa().getDmkhoaMa() + "   ";
				}
				
				HsbaChuyenVienDelegate hsbacvDelegate = HsbaChuyenVienDelegate.getInstance();
				HsbaChuyenVien hsbaChuyenVien_temp = hsbacvDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
				if (hsbaChuyenVien_temp != null) {
					hsbaChuyenvien = hsbaChuyenVien_temp;
				} else {
					hsbaChuyenvien = new HsbaChuyenVien();
				}
				ungTra = ( hsThtoan.getHsthtoanTamung() == null ? 0 : hsThtoan.getHsthtoanTamung() )
						- ( hsThtoan.getHsthtoanHoanung() == null ? 0 : hsThtoan.getHsthtoanHoanung() );
				// Kiem tra so lieu ngoai tru chuyen vao				
				cvnt = ChuyenVaoNoiTruDelegate.getInstance().findBySoVaoVien(hsba.getHsbaSovaovien());								
				if (cvnt != null) {
					hsttk = HsThtoankDelegate.getInstance().findBytiepdonMaLast(cvnt.getTiepDon().getTiepdonMa());	
					ungTra	+= ((hsttk.getHsthtoankTamung() == null ? 0 : hsttk.getHsthtoankTamung())
							+ (hsttk.getHsthtoankHoanung() == null ? 0 : hsttk.getHsthtoankHoanung()));
				}
				thuocNDM = (hsThtoan.getHsthtoanThuocndm() == null ? 0 : hsThtoan.getHsthtoanThuocndm()) + (cvnt == null ? 0 :(hsttk.getHsthtoankThuocndm() == null ? 0 : hsttk.getHsthtoankThuocndm()));
				thuocTrongDM = (hsThtoan.getHsthtoanThuoc() == null ? 0 : hsThtoan.getHsthtoanThuoc()) + (cvnt == null ? 0 :(hsttk.getHsthtoankThuoc() == null ? 0 : hsttk.getHsthtoankThuoc()));
				vTTHTrongDM = (hsThtoan.getHsthtoanVtth() == null ? 0 : hsThtoan.getHsthtoanVtth()) + (cvnt == null ? 0 :(hsttk.getHsthtoankVtth() == null ? 0 : hsttk.getHsthtoankVtth()));
				vTTHNDM = (hsThtoan.getHsthtoanVtthndm() == null ? 0 : hsThtoan.getHsthtoanVtthndm()) + (cvnt == null ? 0 :(hsttk.getHsthtoankVtthndm() == null ? 0 : hsttk.getHsthtoankVtthndm()));
				cLSMauTrongDM = (hsThtoan.getHsthtoanCls() == null ? 0 : hsThtoan.getHsthtoanCls()) + (cvnt == null ? 0 :(hsttk.getHsthtoankCls() == null ? 0 : hsttk.getHsthtoankCls()));
				clsMauNDM = (hsThtoan.getHsthtoanClsndm() == null ? 0 : hsThtoan.getHsthtoanClsndm()) + (cvnt == null ? 0 :(hsttk.getHsthtoankClsndm() == null ? 0 : hsttk.getHsthtoankClsndm()));
				tongTrongDM = hsThtoan.getHsthtoanDm()
							+ (cvnt == null ? 0 :(hsttk.getHsthtoankThuoc() == null ? 0 : hsttk.getHsthtoankThuoc()))
							+ (cvnt == null ? 0 :(hsttk.getHsthtoankVtth() == null ? 0 : hsttk.getHsthtoankVtth()))
							+ (cvnt == null ? 0 :(hsttk.getHsthtoankCls() == null ? 0 : hsttk.getHsthtoankCls()));
				tongNDM = hsThtoan.getHsthtoanNdm()
							+ (cvnt == null ? 0 :(hsttk.getHsthtoankThuocndm() == null ? 0 : hsttk.getHsthtoankThuocndm()))
							+ (cvnt == null ? 0 :(hsttk.getHsthtoankVtthndm() == null ? 0 : hsttk.getHsthtoankVtthndm()))
							+ (cvnt == null ? 0 :(hsttk.getHsthtoankClsndm() == null ? 0 : hsttk.getHsthtoankClsndm()));
				
				benhnhanTra = hsThtoan.getHsthtoanBntra() + (cvnt == null ? 0 :hsttk.getHsthtoankBntra()) - (hsThtoan.getHsthtoanMiendt() + (cvnt == null ? 0 :hsttk.getHsthtoankXetgiam()));
				// Chua xac dinh chi phi that thu
				miengiam = hsThtoan.getHsthtoanMiendt() + (cvnt == null ? 0 :hsttk.getHsthtoankXetgiam());
				nSKhongThu = hsThtoan.getHsthtoanKhongthu() + (cvnt == null ? 0 :hsttk.getHsthtoankKhongthu());
				conlai = new Double(0.0);
				
				sLiDoTT = hsThtoan.getHsthtoanLidothanhtoan();	
				setOtherValue();				
				hienThiGhiNhan = false;				
				hienThiHuyPhieu = true;
				hienThiInPhieu = true;
				hienThiInPhieuNgT = (cvnt != null);
				if ("xemchiphidieutri".equals(ten3241_3242)){					
					hienThiInPhieu = false;
					hienThiHuyPhieu = false;
					hienThiInPhieuNgT = false;
				}
				if (!hienThiGhiNhan) {
					if ("xemchiphidieutri".equals(ten3241_3242)){
						// 20101209 bao.ttc: neu la form "Xem chi phi dieu tri" thi khong hien cau thong bao nay vi khong hop ly
					}else {
						hsThtoan.setHsthtoanThtoan(new Double(0.0));
						FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
					}
				}
				// Fix bug 3566
				//log.info("In findBySophieu(), hsba = " + hsba);
				if(hsba != null) { 
					ClsMoDelegate clsMoDelegate  = ClsMoDelegate.getInstance();
					listClsMo = clsMoDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
					
					ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();															
					listThuocNT = tntDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
					//log.info("In findBySophieu(), listClsMo.size = " + listClsMo.size() + ", listThuocNT.size = " + listThuocNT.size());
				}
			} else {				
				ResetForm();
				FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, sophieu);
				
			}
		}
	}
	// Ham reset form
	private void ResetForm() {
		//log.info("Begining ResetForm(): ");
		benhNhan = new BenhNhan();
//		SetInforUtil.setInforIfNullForBN(benhNhan);
		hsba = new Hsba();
//		SetInforUtil.setInforIfNullForHSBA(hsba);
		hsbaBhyt = new HsbaBhyt();
//		SetInforUtil.setInforIfNullForHSBABHYT(hsbaBhyt);

		hsbaChuyenvien = new HsbaChuyenVien();
//		SetInforUtil.setInforIfNullForHsbaChuyenVien(hsbaChuyenvien);
		hsThtoan = new HsThtoan();
//		SetInforUtil.setInforIfNullForHsThtoan(hsThtoan);
		ngayNhapvien = "";
		ngayRavien = "";
		gioi = "";
		tuoi = "";
		soThe = "";
		sLiDoTT = "";
		ungTra = new Double(0);
		
		this.cacKhoadt = "";
		
		hienThiGhiNhan= false;
		hienThiHuyPhieu = false;
		hienThiInPhieu = false;
		
		thuocTrongDM = new Double(0);
		thuocNDM = new Double(0);
		vTTHTrongDM = new Double(0);
		vTTHNDM = new Double(0);
		cLSMauTrongDM = new Double(0);
		clsMauNDM = new Double(0);
		tongTrongDM = new Double(0);
		tongNDM = new Double(0);
		nSKhongThu = new Double(0);

		benhnhanTra = new Double(0);
		conlai = new Double(0);				
		miengiam = new Double(0);
		listClsMo = new ArrayList<ClsMo>();
		listThuocNT = new ArrayList<ThuocNoiTru>();
		//log.info("End ResetForm(): ");
		refreshNhanVienThuNgan();
	}
	public String XuatPhieuThanhToan(int type) {
		//log.info("begin XuatPhieuThanhToan() hsba = " + hsba);
		int IN_BIEN_LAI = 0;
		int IN_PHIEU_THANH_TOAN = 1;
		if (hsba == null) return null;
		reportTypeVP="ThanhToanRaVien";
		//log.info("Vao Method in phieu thanh toan ra vien");
		String baocao1=null;
		ClsMoDelegate clsMoDelegate  = ClsMoDelegate.getInstance();
		// Cac bien luu gia benh nha tra cho tung can lam sang sau  khi da tru % bao hiem
		
		Double mau = 0.0;		//Mau
		Double xntdcn = 0.0;	// XN- TDCN		
		Double cdha = 0.0;		// Chan doan hinh anh
		Double pttt = 0.0;		// PT -TT
		Double ktc = 0.0;		// Dich vu ky thuat cao		
		Double dvp = 0.0;		// Dich vu phong
		Double vc = 0.0;		// Van chuyen (Chuyen vien)
		Double ck = 0.0;		// Cong kham
		Double clskhac = 0.0; 	// Clskhac
		// Cac bien luu gia dich vu cua can lam sang
		
		Double mauDV = 0.0;		//Mau
		Double xntdcnDV = 0.0;	// XN- TDCN		
		Double cdhaDV = 0.0;		// Chan doan hinh anh
		Double ptttDV = 0.0;		// PT -TT
		Double ktcDV = 0.0;		// Dich vu ky thuat cao		
		Double dvpDV = 0.0;		// Dich vu phong
		Double vcDV = 0.0;		// Van chuyen (Chuyen vien)
		Double ckDV = 0.0;		// Cong kham
		Double clskhacDV = 0.0;
		
		boolean hasDV = false;	// bien cho biet co su dung dich vu hay khong
		Double curGia = 0.0;
		Double curGiaDV = 0.0;
		Double tongtien = 0.0;
		Double tongtienDV = 0.0;
		Double miengiam = 0.0;
		// Lay hstt
		HsThtoanDelegate hsttDelegate = HsThtoanDelegate.getInstance();
		//phuc.lc : 19/10/2011 : Fix bug 4071		
		HsThtoan hsThtoan_tmp = hsttDelegate.findBySovaovien(hsba.getHsbaSovaovien());
		// Neu da thanh toan thi lay thong tin theo ket qua tim duoc
		// neu chua thanh toan thi su dung thong tin da set trong HoSoThanhToanUtil
		// de hien thi dung ty le bao hiem tren bao cao (fix bug 4018)
		if (hsThtoan_tmp != null && hsThtoan_tmp.getHsthtoanNgaytt() != null) {
			hsThtoan = hsThtoan_tmp;
		}
		//phuc.lc : 19/10/2011 : End fix bug 4071
		 
		if (hsThtoan != null) {
			//miengiam = hsThtoan.getHsthtoanXetgiam() == null ? 0.0 : hsThtoan.getHsthtoanXetgiam(); 
			miengiam = hsThtoan.getHsthtoanMiendt() == null ? 0.0 : hsThtoan.getHsthtoanMiendt();
		}
		//TiepDon tiepdon = TiepDonDelegate.getInstance().findBenhNhanByTiepdonMa(hsba.getHsbaSovaovien());
		TiepDon tiepdon = TiepDonDelegate.getInstance().findBenhNhanByTiepdonMa(hsba.getTiepdonMa());
		//log.info("hsba.tiepdon = " + hsba.getTiepdonMa() + ", tiepdon = " + tiepdon);
		// lay CLS mo
		listClsMo = clsMoDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
		
		for (ClsMo eachCls : listClsMo) {
			
			curGia = (eachCls.getClsmoDongiabntra() == null ? 0.0 : eachCls.getClsmoDongiabntra());			
			curGiaDV = (eachCls.getClsmoPhandv() == null ? 0.0 : eachCls.getClsmoPhandv());
			
			// Kiem tra cls co phai la dich vu hay khong
			if (eachCls.getClsmoYeucau() != null && eachCls.getClsmoYeucau().booleanValue() == true) {				
				hasDV = true;
				if (tiepdon.getDoituongMa() != null) {
					if (tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("MP")) {
						// Neu la doi tuong mien phi thi tinh lai gia dich vu
						curGia = (eachCls.getClsmoMahang().getDtdmclsbgDongiamp() == null ? 0.0 : eachCls.getClsmoMahang().getDtdmclsbgDongiamp());
						// Doi tuong mien phi yeu cau thuc hien CLS thi : gia dich vu = gia yeu cau - gia mien phi
						curGiaDV = (eachCls.getClsmoDongia() == null ? 0.0 : eachCls.getClsmoDongiabntra()) - curGia;
					}
				}
			}
			tongtien += curGia;
			tongtienDV += curGiaDV;
			// Lay gia benh nhan tra cho tung CLS, gia nay da tru % bao hiem , duoc tinh va luu san trong database	
			if (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("MA")) {	// Mau
				mau += curGia;				
				mauDV += curGiaDV;
			} else if (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("XN")) {	// Xet nghiem
				xntdcn += curGia;				
				xntdcnDV += curGiaDV;
			} else if (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("CD")) {	// Chan doan hinh anh
				cdha += curGia;				
				cdhaDV += curGiaDV;
			} else if (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("PT") ||  // Phau thuat
					eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("TT")) {	// Thu thuat
				pttt += curGia;				
				ptttDV += curGiaDV;
			}else if(eachCls.getClsmoKtcao() != null && eachCls.getClsmoKtcao().booleanValue() == true) {	// Dich vu ky thuat cao
				ktc += curGia;				
				ktcDV += curGiaDV;	
			}else if (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("GI")) {	// Tien giuong(phong)
				dvp += curGia;				
				dvpDV += curGiaDV;	
			} else if (eachCls.getClsmoLoaicls() != null && eachCls.getClsmoLoaicls().getDtdmclsMaso().intValue() == 12){ // 12 la ma so cua can lam san van chuyen
				vc += curGia;				
				vcDV += curGiaDV;
			
			}else if (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("KH")) {	// Cong kham
				ck += curGia;
				ckDV += curGiaDV;
			} else {
				clskhac += curGia;
				clskhacDV += curGiaDV;
			}
			
			
		}
		//log.info("After tinh tong tien CLS noi tru, tongtien = " + tongtien);
		// Chi phi CLS ngoai tru
		if (hsttk != null) {
			ClsKhamDelegate clsKhamDelegate  = ClsKhamDelegate.getInstance();
			// lay CLS kham
			List<ClsKham> listClsKham = clsKhamDelegate.findByMaPhieu(hsttk.getHsthtoankMa());
			for (ClsKham eachClsKham : listClsKham) {
				curGia = (eachClsKham.getClskhamDongiabntra() == null ? 0.0 : eachClsKham.getClskhamDongiabntra());			
				curGiaDV = (eachClsKham.getClskhamPhandv() == null ? 0.0 : eachClsKham.getClskhamPhandv());
				// Kiem tra cls co phai la dich vu hay khong
				if (eachClsKham.getClskhamYeucau() != null && eachClsKham.getClskhamYeucau().booleanValue() == true) {				
					hasDV = true;
					if (cvnt.getTiepDon().getDoituongMa() != null) {
						if (cvnt.getTiepDon().getDoituongMa().getDmdoituongMa().equalsIgnoreCase("MP")) {
							// Neu la doi tuong mien phi thi tinh lai gia dich vu
							curGia = (eachClsKham.getClskhamMahang().getDtdmclsbgDongiamp() == null ? 0.0 : eachClsKham.getClskhamMahang().getDtdmclsbgDongiamp());
							// Doi tuong mien phi yeu cau thuc hien CLS thi : gia dich vu = gia yeu cau - gia mien phi
							curGiaDV = (eachClsKham.getClskhamDongia() == null ? 0.0 : eachClsKham.getClskhamDongiabntra()) - curGia;
						}
					}
				}
				tongtien += curGia;
				tongtienDV += curGiaDV;
				// Lay gia benh nhan tra cho tung CLS, gia nay da tru % bao hiem , duoc tinh va luu san trong database	
				if (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("MA")) {	// Mau
					mau += curGia;				
					mauDV += curGiaDV;
				} else if (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("XN")) {	// Xet nghiem
					xntdcn += curGia;				
					xntdcnDV += curGiaDV;
				} else if (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("CD")) {	// Chan doan hinh anh
					cdha += curGia;				
					cdhaDV += curGiaDV;
				} else if (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("PT") ||  // Phau thuat
						eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("TT")) {	// Thu thuat
					pttt += curGia;				
					ptttDV += curGiaDV;
				}else if(eachClsKham.getClskhamKtcao() != null && eachClsKham.getClskhamKtcao().booleanValue() == true) {	// Dich vu ky thuat cao
					ktc += curGia;				
					ktcDV += curGiaDV;	
				}else if (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("GI")) {	// Tien giuong(phong)
					dvp += curGia;				
					dvpDV += curGiaDV;	
				} else if (eachClsKham.getClskhamMaloai()!= null && eachClsKham.getClskhamMaloai().getDtdmclsMaso().intValue() == 12){ // 12 la ma so cua can lam san van chuyen
					vc += curGia;				
					vcDV += curGiaDV;
				
				}else if (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("KH")) {	// Cong kham
					ck += curGia;
					ckDV += curGiaDV;
				} else {
					clskhac += curGia;
					clskhacDV += curGiaDV;
				}
			}
		}
		//log.info("After tinh tong tien CLS ngoai tru, tongtien = " + tongtien);
		
		
		
		
		// lay thuoc noi tru
		ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
		Double thuocDV = 0.0;   	// Tong tien thuoc 
		Double vtthDV = 0.0;		// Tong tien Vat tu tieu hao
		Double thuocBN = 0.0;   	// Thuoc benh nhan tra
		Double vtthBN = 0.0;		// Vat tu tieu hao benh nhan tra
		List<ThuocNoiTru> lstThuocNT = tntDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
		for(ThuocNoiTru eachTnt : lstThuocNT) {
						
			curGia = (eachTnt.getThuocnoitruTienbntra() == null ? 0.0 : eachTnt.getThuocnoitruTienbntra());
			
			// Kiem tra Thuoc co phai la dich vu hay khong
			if (eachTnt.getThuocnoitruYeucau() != null && eachTnt.getThuocnoitruYeucau().booleanValue() == true) {				
				hasDV = true;
			}
			if (eachTnt.getThuocnoitruMathuoc()!=null 
					&& eachTnt.getThuocnoitruMathuoc().getDmthuocPlbhyt()!=null 
					&& eachTnt.getThuocnoitruMathuoc().getDmthuocPlbhyt().intValue() == 10 ){
			
				if (eachTnt.getThuocnoitruYeucau() != null && eachTnt.getThuocnoitruYeucau().booleanValue() == true) {
					vtthDV += curGia;
					tongtienDV += curGia;
				} else {				
					vtthBN += curGia;
					tongtien += curGia;
				}
			} else {
				if (eachTnt.getThuocnoitruYeucau() != null && eachTnt.getThuocnoitruYeucau().booleanValue() == true) {
					thuocDV += curGia;
					tongtienDV += curGia;
				} else {
					thuocBN += curGia;
					tongtien += curGia;
				}
			}
		}
		//log.info("After tinh tong tien Thuoc noi tru, tongtien = " + tongtien);
		// Chi phi thuoc ngoai tru
		if (hsttk != null) {
			// lay thuoc ban` khoam
			ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
			List<ThuocPhongKham> listTPK = tpkDelegate.findByMaPhieu(hsttk.getHsthtoankMa());
			for(ThuocPhongKham eachTpk : listTPK) {
				
				curGia = (eachTpk.getThuocphongkhamTienbntra() == null ? 0.0 : eachTpk.getThuocphongkhamTienbntra());
				
				// Kiem tra Thuoc co phai la dich vu hay khong
				if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {				
					hasDV = true;
				}
				if (eachTpk.getThuocphongkhamMathuoc()!=null 
						&& eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt() != null
						&& eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt().intValue() == 10 ){  // 10 : nhom vat tu tieu hao
					if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {
						vtthDV += curGia;
						tongtienDV += curGia;
					} else {				
						vtthBN += curGia;
						tongtien += curGia;
					}
				} else {
					if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {
						thuocDV += curGia;
						tongtienDV += curGia;
					} else {
						thuocBN += curGia;
						tongtien += curGia;
					}
				}
			}
		}
		//log.info("After tinh tong tien Thuoc ngoai tru, tongtien = " + tongtien);
		String diachistr = "";
		if(benhNhan.getBenhnhanDiachi() != null)
			diachistr += benhNhan.getBenhnhanDiachi()+", ";
		if(benhNhan.getXaMa(true).getDmxaTen() !=null)
			diachistr += benhNhan.getXaMa(true).getDmxaTen()+", ";
		if(benhNhan.getHuyenMa(true).getDmhuyenTen() != null)
			diachistr += benhNhan.getHuyenMa(true).getDmhuyenTen()+", ";
		if(benhNhan.getTinhMa(true).getDmtinhTen() != null)
			diachistr += benhNhan.getTinhMa(true).getDmtinhTen();
		
		String thungan = "";
		if (hsThtoan != null) {
			if(hsThtoan.getHsthtoanThungan() != null){
				if (hsThtoan.getHsthtoanThungan().getDtdmnhanvienTen() != null)
					thungan = hsThtoan.getHsthtoanThungan().getDtdmnhanvienTen();
				else thungan = "";
			} else thungan = "";
		}
		//log.info("hasDV = " + hasDV + ", tongtienDV = " + tongtienDV + ", tong tien = " + tongtien);
		
		tongtien = Utils.rounDoubleForReport(tongtien);
		//log.info("After lam tron tong tien, tongtien = " + tongtien);
		if ((tongtien - miengiam ) < 1  && type == IN_BIEN_LAI) {
			FacesMessages.instance().add(IConstantsRes.KHONG_IN_BIEN_LAI);
			//log.info("End InPhieu(): Khong in bien lai !");
			return null;
		}
		tongtienDV = Utils.rounDoubleForReport(tongtienDV);
		// Tinh toan so lieu cho phieu thanh toan ra vien
		SimpleDateFormat sdf=new SimpleDateFormat(Utils.FORMAT_DATE);			
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
		params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
		params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
		
		//String matiepDon = thamkham.getTiepdonMa().getTiepdonMa();
		params.put("MATIEPDON", tiepdon.getTiepdonMa() );
		params.put("SOBENHAN", hsba.getHsbaSovaovien());
		params.put("HOTENBN", benhNhan.getBenhnhanHoten()  );
				
		params.put("DIACHI", diachistr );
		
		// 20110705 bao.ttc: them cac thong tin cho mau moi dung tu ngay 1/7			
		if(hsba.getDoituongMa(true).getDmdoituongMa().equals("BH")){			
			params.put("BHYT_CO", "X" );
			
			if( (hsbaBhyt.getHsbabhytTuyen() != null && hsbaBhyt.getHsbabhytTuyen().toString().equals("1"))
					|| (hsbaBhyt.getHsbabhytCoGiayChuyenVien() != null && hsbaBhyt.getHsbabhytCoGiayChuyenVien() ) ){
					params.put("DUNGTUYEN","X");
			} else {
				params.put("TRAITUYEN","X");
			}								
			params.put("MATHEBHYT", hsbaBhyt.getHsbabhytSothebh() == null ? "" : hsbaBhyt.getHsbabhytSothebh());
			params.put("MABENHVIEN", hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienMa() == null ? "" : hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienMa().replace(".", "-"));
			params.put("NOIDKKCBBANDAU", hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienTen() == null ? "" : hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienTen());
			params.put("NOICAPTHE", hsbaBhyt.getHsbabhytTinhbh(true).getDmtinhTen() == null ? "" : hsbaBhyt.getHsbabhytTinhbh(true).getDmtinhTen());
			params.put("GTTU", hsbaBhyt.getHsbabhytGiatri0() == null ? "" : sdf.format(hsbaBhyt.getHsbabhytGiatri0()));
			params.put("GTDEN", hsbaBhyt.getHsbabhytGiatri1() == null ? "" : sdf.format(hsbaBhyt.getHsbabhytGiatri1()));
			params.put("TYLEBH", (hsThtoan == null ? new Short("0") : hsThtoan.getHsthtoanTylebh()) );			
			params.put("PHANTRAMBNTRA","" + ( 100 - (hsThtoan == null ? 0 : hsThtoan.getHsthtoanTylebh())));
		} else {
			params.put("BHYT_KO", "X" );
			params.put("MATHEBHYT", "");
			params.put("MABENHVIEN", "");
			params.put("NOIDKKCBBANDAU", "");
			params.put("NOICAPTHE", "");
			params.put("GTTU", "");
			params.put("GTDEN", "");
			params.put("DUNGTUYEN","");
			params.put("TRAITUYEN","");
			params.put("TYLEBH", new Short("0"));
			params.put("PHANTRAMBNTRA","0");
		}
		
		
		if(hsba.getHsbaCapcuu() != null && hsba.getHsbaCapcuu() ){
			params.put("CAPCUU","X");
			params.put("DUNGTUYEN","");
			params.put("TRAITUYEN","");
		} else if(hsba.getHsbaKhoavaov() != null){
			if(hsba.getHsbaKhoavaov(true).getDmkhoaMa().startsWith("CC")){
				params.put("CAPCUU","X");
				params.put("DUNGTUYEN","");
				params.put("TRAITUYEN","");
			}
		}
		/*
		params.put("GTTU", "");
		if(tiepdon.getTiepdonGiatri1() != null){
			if(tiepdon.getDoituongMa() != null)	{
				params.put("GTTU", tiepdon.getDoituongMa().getDmdoituongMa() == null ? "" : (tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH") ? sdf.format(tiepdon.getTiepdonGiatri1()) : ""));
			}
		}
		params.put("GTDEN", "");	
		if(tiepdon.getTiepdonGiatri2() != null){
			params.put("GTDEN", tiepdon.getDoituongMa().getDmdoituongMa() == null ? "" : (tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH") ? sdf.format(tiepdon.getTiepdonGiatri2()) : ""));
		}
		//params.put("MATHEBHYT","");
		
		
		if (tiepdon.getTiepdonSothebh() != null && !tiepdon.getTiepdonSothebh().equals("") &&
				tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMa() != null && !tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMa().equals("") &&
				tiepdon.getKcbbhytMa(true).getDmbenhvienMa()!=null
		){
			if(tiepdon.getDoituongMa() != null)	{		
				params.put("MATHEBHYT", tiepdon.getDoituongMa().getDmdoituongMa() == null ? "" : (tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH") ? tiepdon.getTiepdonSothebh() : "") );
			}
			params.put("MABENHVIEN", tiepdon.getKcbbhytMa(true).getDmbenhvienMa().replace(".", "-"));
			
			
		}else{
			params.put("MABENHVIEN","");
			
		}
		
		if(tiepdon.getKcbbhytMa(true).getDmbenhvienTen()!=null)
			params.put("NOIDKKCBBANDAU", tiepdon.getKcbbhytMa(true).getDmbenhvienTen());
		if(tiepdon.getTinhbhytMa(true).getDmtinhTen()!=null)
			params.put("NOICAPTHE", tiepdon.getTinhbhytMa(true).getDmtinhTen());
		
		if(tiepdon.getTiepdonDonvigoi(true).getDmbenhvienMa()!=null)
			params.put("NOIGIOITHIEU", tiepdon.getTiepdonDonvigoi(true).getDmbenhvienTen());
		*/
		HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(hsba.getHsbaSovaovien());
		params.put("GIUONG", (hsbaCm == null ? "" : hsbaCm.getHsbacmSogiuong()));
		params.put("BUONG", (hsbaCm == null ? "" : hsbaCm.getHsbacmSogiuong()));
		
		Calendar cal = Calendar.getInstance();
		
		if(hsba.getHsbaNgaygiovaov() != null){
			cal.setTime(hsba.getHsbaNgaygiovaov());
			params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));
			
			if(hsba.getHsbaNgaygiorav() != null){
				cal.setTime(hsba.getHsbaNgaygiorav());
				params.put("NGAYRAVIEN", new Timestamp(cal.getTimeInMillis()));
				params.put("SONGAYDT", Utils.getSoNgayDieuTri(hsba.getHsbaNgaygiovaov(), hsba.getHsbaNgaygiorav()));
			}
			
		}
		
		params.put("LYDOVAOVIEN", tiepdon.getTiepdonLydovaov());
				
		params.put("BHXHTHANHTOAN", ((hsThtoan == null ? 0 : hsThtoan.getHsthtoanBhyt()) + (hsttk == null ? 0 : (hsttk.getHsthtoankBhyt() == null ? 0 : hsttk.getHsthtoankBhyt()))) );
		params.put("NGUOIBENHTRA", (hsThtoan == null ? 0 : hsThtoan.getHsthtoanBntra()) + (hsttk == null ? 0 : (hsttk.getHsthtoankBntra() == null ? 0 : hsttk.getHsthtoankBntra())));
		
		//if (thanhToan == null) {
		//	params.put("PHIEUSO", "");
		//} else {
		//	params.put("PHIEUSO", thanhToan.getHsthtoankMa());
		//}
		params.put("PHIEUSO", (hsThtoan == null ?  "" : hsThtoan.getHsthtoanMa()));		
		/*params.put("TYLEBH", (hsThtoan == null ? new Short("0") : hsThtoan.getHsthtoanTylebh()) );
		String tyleBNtra = "" + ( 100 - (hsThtoan == null ? 0 : hsThtoan.getHsthtoanTylebh()) );
		if ("MP".equals( tiepdon.getDoituongMa(true).getDmdoituongMa()) ||
				"TP".equals( tiepdon.getDoituongMa(true).getDmdoituongMa())){
			tyleBNtra = "0";
			params.put("TYLEBH",new Short("0"));
		}
		params.put("PHANTRAMBNTRA",tyleBNtra);
		*/
		String soBienlai = "";
		if(hsThtoan.getHsthtoanKyhieu() != null){
			soBienlai = hsThtoan.getHsthtoanKyhieu();
		}
		if(hsThtoan.getHsthtoanQuyen() != null){
			soBienlai += " - " + hsThtoan.getHsthtoanQuyen();
		}
		if(hsThtoan.getHsthtoanBienlai() != null){
			soBienlai += " - " + hsThtoan.getHsthtoanBienlai();
		}
		params.put("BIENLAISO", soBienlai); // 20111007 bao.ttc: them thong tin Ky hieu - Quyen - Bien lai
		
		String namsinh = "";
		if (benhNhan.getBenhnhanNgaysinh() != null){
			namsinh = sdf.format(benhNhan.getBenhnhanNgaysinh());
		} else {
			namsinh = benhNhan.getBenhnhanNamsinh();
		}
		params.put("namsinh", namsinh);
					
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		String maChanDoan = "";
		String tenChanDoan ="";					
		
		if (hsba.getHsbaMachdravien() != null && hsba.getHsbaMachdravien(true).getDmbenhicdMaso() !=null){
			DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsba.getHsbaMachdravien(true).getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
			if (benh != null){
				maChanDoan = benh.getDmbenhicdMa();
				tenChanDoan = benh.getDmbenhicdTen();
			}
		} else if (hsba.getHsbaMachdoanbd() != null && hsba.getHsbaMachdoanbd().getDmbenhicdMaso() !=null){
			DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsba.getHsbaMachdoanbd().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
			if (benh != null){
				maChanDoan = benh.getDmbenhicdMa();
				tenChanDoan = benh.getDmbenhicdTen();
			}
		}
		
		String chanDoan = maChanDoan + " " +  tenChanDoan;
		
		params.put("MABENHICD", maChanDoan);
		params.put("CHANDOAN", chanDoan );
		
		params.put("PHONGKHAM", null ); // Kiem tra lai
		
		// Lay danh sach khoa da kham
		// 20120312 bao.ttc: tim list cac khoa DT can in ca Tang de phan biet
		List<HsbaKhoa> listHsbaKhoa = HsbaKhoaDelegate.getInstance().findBySoVaoVien(hsba.getHsbaSovaovien());
		
		StringBuffer bufStr = new StringBuffer();		
		if (listHsbaKhoa != null && listHsbaKhoa.size() > 0) {
			
			for (HsbaKhoa each : listHsbaKhoa) {
				if (bufStr.length() > 0) {
					bufStr.append(", " + each.getKhoaMa().getDmkhoaTen());
				} else {
					bufStr.append(each.getKhoaMa().getDmkhoaTen());
				}
			}
		}
		
	/*			// Kiem tra co su dung cls yeu cau khong
				listClsTmp = ClsKhamDelegate.getInstance().findByTiepdonma(tiepDon.getTiepdonMa());
				if (listClsTmp != null && listClsTmp.size() > 0) {
					for (ClsKham eachCls : listClsTmp) {
						if (eachCls.getClskhamPhandv() != null) {
							tongTienDV += eachCls.getClskhamPhandv();
						}
					}
				}
				// Kiem tra co su dung thuoc yeu cau khong
				
				listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepDon.getTiepdonMa(), "1");
				if (listTpk != null && listTpk.size() > 0) {
					for (ThuocPhongKham eachTpk : listTpk) {
						if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {
							tongTienDV += eachTpk.getThuocphongkhamDongia(); 
						}
					}
				}
				listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepDon.getTiepdonMa(), "3");
				if (listTpk != null && listTpk.size() > 0) {
					for (ThuocPhongKham eachTpk : listTpk) {
						if (eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {
							tongTienDV += eachTpk.getThuocphongkhamDongia(); 
						}
					}
				}
			
		*/
		params.put("KHOA",bufStr.toString());
		String soTheTe_KhaiSinh_ChungSinh = ""; //thamkham.getTiepdonMa(true).getTiepdonSothete();
		
		
		
		
		
		if (soTheTe_KhaiSinh_ChungSinh != null && !soTheTe_KhaiSinh_ChungSinh.equals("")){
			params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
		}else{
			params.put("SOTHETEKSCS", null);
		}
		//
		String soTheNgheo = ""; //tiepDon.getTiepdonThengheo();
		
		//log.info("soTheNgheo"+soTheNgheo);
		
		if (soTheNgheo != null && !soTheNgheo.equals("")){
			params.put("SOTHENGHEO", soTheNgheo);
		}else{
			params.put("SOTHENGHEO", null);
		}
		
		//SUB REPORT 3
		params.put("TONGCHIPHI", (hsThtoan == null ? 0 : hsThtoan.getHsthtoanTongchi()) + (hsttk == null ? 0 : (hsttk.getHsthtoankTongchi() == null ? 0 : hsttk.getHsthtoankTongchi())));
		params.put("BANGCHU1", Utils.NumberToString((hsThtoan == null ? 0 : hsThtoan.getHsthtoanTongchi()) + (hsttk == null ? 0 : (hsttk.getHsthtoankTongchi() == null ? 0 : hsttk.getHsthtoankTongchi()))));
		params.put("BANGCHU2", Utils.NumberToString((hsThtoan == null ? 0 :hsThtoan.getHsthtoanBntra()) + (hsttk == null ? 0 : (hsttk.getHsthtoankBntra() == null ? 0 : hsttk.getHsthtoankBntra()))));
		params.put("BANGCHU3", Utils.NumberToString((hsThtoan == null ? 0 :hsThtoan.getHsthtoanBhyt()) + (hsttk == null ? 0 : (hsttk.getHsthtoankBhyt() == null ? 0 : hsttk.getHsthtoankBhyt()))));
		params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
		
		if(((hsThtoan == null ? 0 : hsThtoan.getHsthtoanBntra()) + (hsttk == null ? 0 : (hsttk.getHsthtoankBntra() == null ? 0 : hsttk.getHsthtoankBntra()))) >= 0){
			params.put("SNGUOIBENHTRA", "0");
		}else{
			params.put("SNGUOIBENHTRA", "-1");
		}
		
		DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(benhNhan.getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh","dmgtMaso" );
		if (gioi != null){

			params.put("GIOI", gioi.getDmgtTen());
					
		}
		params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
		// phuc.lc 21-04-2011 : Fix bug 2655
		if (type == IN_BIEN_LAI) {
			if (hsThtoan != null) {
				int lanIn = hsThtoan.getHsthtoanLanin() == null ? 1 : (Integer.valueOf(hsThtoan.getHsthtoanLanin()).intValue() + 1);
				hsThtoan.setHsthtoanLanin("" + lanIn);
				params.put("LANIN", "" + lanIn); 
			}	
		}
			try{
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBnoitru_kobienlailephi.jrxml";
				String pathTemplate_sub1 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub1.jrxml";
				String pathTemplate_sub2 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub2.jrxml";
				String pathTemplate_sub3 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub3.jrxml";
				String pathTemplate_sub4 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBnoitru_subreport0.jrxml";
				String pathTemplate_sub5 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBnoitru_subreport1.jrxml";
				
				if (hasDV && tongtienDV > 0) {
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBnoitru_DV.jrxml";
				} else if ((tongtien - miengiam ) > 0 ) {
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBnoitru.jrxml";
				}
				//log.info("da thay file templete :" + pathTemplate);
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
				if ((tongtien - miengiam ) > 0  && type == IN_BIEN_LAI) {
					JasperReport jasperSub1 =JasperCompileManager.compileReport(pathTemplate_sub1);
					JasperReport jasperSub2 =JasperCompileManager.compileReport(pathTemplate_sub2);
					JasperReport jasperSub3 =JasperCompileManager.compileReport(pathTemplate_sub3);
					params.put("sub1", jasperSub1 );
					params.put("sub2", jasperSub2 );
					params.put("sub3", jasperSub3 );
				}
				if (hasDV && tongtienDV > 0) {
					String pathTemplate_sub6 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport0.jrxml";
					String pathTemplate_sub7 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport1.jrxml";
					String pathTemplate_sub8 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport2.jrxml";
					String pathTemplate_sub9 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBnoitru_DV_subreport0.jrxml";
					String pathTemplate_sub10 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBnoitru_DV_subreport1.jrxml";
					JasperReport jasperSub6 =JasperCompileManager.compileReport(pathTemplate_sub6);
					JasperReport jasperSub7 =JasperCompileManager.compileReport(pathTemplate_sub7);
					JasperReport jasperSub8 =JasperCompileManager.compileReport(pathTemplate_sub8);
					JasperReport jasperSub9 =JasperCompileManager.compileReport(pathTemplate_sub9);
					JasperReport jasperSub10 =JasperCompileManager.compileReport(pathTemplate_sub10);
					if (type == IN_BIEN_LAI) {
						params.put("sub6", jasperSub6 );
						params.put("sub7", jasperSub7 );
						params.put("sub8", jasperSub8 );
					}
					if (type == IN_PHIEU_THANH_TOAN) {
						params.put("sub9", jasperSub9 );
						params.put("sub10", jasperSub10 );
					}
				}
				JasperReport jasperSub4 =JasperCompileManager.compileReport(pathTemplate_sub4);
				JasperReport jasperSub5 =JasperCompileManager.compileReport(pathTemplate_sub5);
				//log.info("da thay file template ");								
				
				//if (type == IN_PHIEU_THANH_TOAN || type == IN_BIEN_LAI) {
				//phuc.lc 10/10/2011 : Fix bug 4033 
				if (type == IN_PHIEU_THANH_TOAN) {
					params.put("sub4", jasperSub4 );
					params.put("sub5", jasperSub5 );
				}
				
				// Set param cho bien lai thu le phi
				params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
				params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
				params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
				params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
				params.put("HOTEN", benhNhan.getBenhnhanHoten());
				
				//params.put("DIACHI", diachistr);
				params.put("NOIDUNG", (hsThtoan == null ? "" : hsThtoan.getHsthtoanLidothanhtoan() ));
												
				params.put("SOTIEN", Utils.formatNumberWithSeprator(tongtien - miengiam));
				params.put("TIENBANGCHU", Utils.NumberToString(tongtien - miengiam));
				//params.put("NDM", Utils.formatNumberWithSeprator(ndm));
				
				//bao.ttc: kiem tra nghiep vu tinh tien cac loai dich vu
				params.put("CLS", Utils.formatNumberWithSeprator(clskhac));
				params.put("THUOC", Utils.formatNumberWithSeprator(thuocBN));
				params.put("MAU", Utils.formatNumberWithSeprator(mau));
				params.put("XNTDCN", Utils.formatNumberWithSeprator(xntdcn));
				params.put("CDHA", Utils.formatNumberWithSeprator(cdha));
				params.put("PTTT", Utils.formatNumberWithSeprator(pttt));
				params.put("DVKTC", Utils.formatNumberWithSeprator(ktc));
				params.put("VTTH", Utils.formatNumberWithSeprator(vtthBN));
				params.put("DVP", Utils.formatNumberWithSeprator(dvp + dvpDV));
				params.put("CV", Utils.formatNumberWithSeprator(vc));
				params.put("CONGKHAM", Utils.formatNumberWithSeprator(ck));
				params.put("MIENGIAM", Utils.formatNumberWithSeprator(miengiam));
				params.put("THUNGAN", thungan);
				
				// Cac param cho mau bien lai thu tien (hoa don)
				params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
				params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
				params.put("THUOCDV", Utils.formatNumberWithSeprator(thuocDV));
				params.put("VTTHDV", Utils.formatNumberWithSeprator(vtthDV));
				params.put("CLSDV", Utils.formatNumberWithSeprator(clskhacDV));
				params.put("MAUDV", Utils.formatNumberWithSeprator(mauDV));
				params.put("XNTDCNDV", Utils.formatNumberWithSeprator(xntdcnDV));
				params.put("CDHADV", Utils.formatNumberWithSeprator(cdhaDV));
				params.put("PTTTDV", Utils.formatNumberWithSeprator(ptttDV));
				params.put("DVKTCDV", Utils.formatNumberWithSeprator(ktcDV));
				params.put("DVPDV", Utils.formatNumberWithSeprator(new Double(0.0)));
				params.put("CVDV", Utils.formatNumberWithSeprator(vcDV));
				params.put("CONGKHAMDV", Utils.formatNumberWithSeprator(ckDV));
				params.put("SOTIENDV", Utils.formatNumberWithSeprator(tongtienDV));
				params.put("TIENBANGCHUDV", Utils.NumberToString(tongtienDV));
				
				Class.forName("oracle.jdbc.OracleDriver");
			    //log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    } catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/","pdf","ThanhToanRaVien");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    //log.info("duong dan file xuat report :" + baocao1);
			    //log.info("duong dan -------------------- :"+reportPathVP);
			    index+=1;
			    //log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
			 // phuc.lc 21-04-2011 : Fix bug 2655
			    if (hsThtoan != null && hsThtoan.getHsthtoanMa() != null) {
			    	if(hsThtoan.getHsthtoanMa().trim().length() > 0) {
			    		hsttDelegate.edit(hsThtoan);
			    	}
			    }else {
			    	hsThtoan = new HsThtoan();
			    }
	            
			} catch(Exception e) {
			    log.info("ERROR Method XuatReport!!!");
				e.printStackTrace();
				return null;
			}
		
		//log.info("Thoat Method XuatPhieuThanhToan");
		if(nhanVienThungan==null){
			refreshNhanVienThuNgan();
		}
		return "B3360_Chonmenuxuattaptin";
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

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

	public String getTuoi() {
		return tuoi;
	}

	public void setTuoi(String tuoi) {
		this.tuoi = tuoi;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public boolean isUpdateNhapct() {
		return updateNhapct;
	}

	public void setUpdateNhapct(boolean updateNhapct) {
		this.updateNhapct = updateNhapct;
	}

	public String getGioi() {
		return gioi;
	}

	public void setGioi(String gioi) {
		this.gioi = gioi;
	}

	// public String getPosition() {
	// return position;
	// }
	// public void setPosition(String position) {
	// this.position = position;
	// }
	public String getReportFinished() {
		return reportFinished;
	}

	public void setReportFinished(String reportFinished) {
		this.reportFinished = reportFinished;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		ThanhToanRaVienAction.log = log;
	}

	public HsbaBhyt getHsbaBhyt() {
		return hsbaBhyt;
	}

	public void setHsbaBhyt(HsbaBhyt hsbaBhyt) {
		this.hsbaBhyt = hsbaBhyt;
	}

	public String getSoThe() {
		return soThe;
	}

	public void setSoThe(String soThe) {
		this.soThe = soThe;
	}

	public Hsba getHsba() {
		return hsba;
	}

	public void setHsba(Hsba hsba) {
		this.hsba = hsba;
	}

	public String getNgayNhapvien() {
		return ngayNhapvien;
	}

	public void setNgayNhapvien(String ngayNhapvien) {
		this.ngayNhapvien = ngayNhapvien;
	}

	public HsbaChuyenVien getHsbaChuyenvien() {
		return hsbaChuyenvien;
	}

	public void setHsbaChuyenvien(HsbaChuyenVien hsbaChuyenvien) {
		this.hsbaChuyenvien = hsbaChuyenvien;
	}

	

	public HsThtoan getHsThtoan() {
		return hsThtoan;
	}

	public void setHsThtoan(HsThtoan hsThtoan) {
		this.hsThtoan = hsThtoan;
	}

	public String getMaFinish() {
		return maFinish;
	}

	public void setMaFinish(String maFinish) {
		this.maFinish = maFinish;
	}

	public String getReportFileNameHid() {
		return reportFileNameHid;
	}

	public void setReportFileNameHid(String reportFileNameHid) {
		this.reportFileNameHid = reportFileNameHid;
	}

	public String getNgayTtoan() {
		return ngayTtoan;
	}

	public void setNgayTtoan(String ngayTtoan) {
		this.ngayTtoan = ngayTtoan;
	}

	public String getCacKhoadt() {
		return cacKhoadt;
	}

	public void setCacKhoadt(String cacKhoadt) {
		this.cacKhoadt = cacKhoadt;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public Double getThuocTrongDM() {
		return thuocTrongDM;
	}

	public void setThuocTrongDM(Double thuocTrongDM) {
		this.thuocTrongDM = thuocTrongDM;
	}

	public Double getThuocNDM() {
		return thuocNDM;
	}

	public void setThuocNDM(Double thuocNDM) {
		this.thuocNDM = thuocNDM;
	}

	public Double getVTTHTrongDM() {
		return vTTHTrongDM;
	}

	public void setVTTHTrongDM(Double trongDM) {
		vTTHTrongDM = trongDM;
	}

	public Double getVTTHNDM() {
		return vTTHNDM;
	}

	public void setVTTHNDM(Double vtthndm) {
		vTTHNDM = vtthndm;
	}

	public Double getCLSMauTrongDM() {
		return cLSMauTrongDM;
	}

	public void setCLSMauTrongDM(Double mauTrongDM) {
		cLSMauTrongDM = mauTrongDM;
	}

	public Double getClsMauNDM() {
		return clsMauNDM;
	}

	public void setClsMauNDM(Double clsMauNDM) {
		this.clsMauNDM = clsMauNDM;
	}

	

	public Double getTongTrongDM() {
		return tongTrongDM;
	}
	public void setTongTrongDM(Double tongTrongDM) {
		this.tongTrongDM = tongTrongDM;
	}
	public Double getTongNDM() {
		return tongNDM;
	}
	public void setTongNDM(Double tongNDM) {
		this.tongNDM = tongNDM;
	}
	

	public Double getnSKhongThu() {
		return nSKhongThu;
	}
	public void setnSKhongThu(Double nSKhongThu) {
		this.nSKhongThu = nSKhongThu;
	}
	public Double getUng() {
		return ung;
	}

	public void setUng(Double ung) {
		this.ung = ung;
	}

	public Double getTra() {
		return tra;
	}

	public void setTra(Double tra) {
		this.tra = tra;
	}

	public Double getSoDu() {
		return soDu;
	}

	public void setSoDu(Double soDu) {
		this.soDu = soDu;
	}

	public int getPermiengiam() {
		return permiengiam;
	}

	public void setPermiengiam(int permiengiam) {
		this.permiengiam = permiengiam;
	}

	public Double getMiengiam() {
		return miengiam;
	}

	public void setMiengiam(Double miengiam) {
		this.miengiam = miengiam;
	}

	public Double getThatthu() {
		return thatthu;
	}

	public void setThatthu(Double thatthu) {
		this.thatthu = thatthu;
	}

	public int getPerbhytchi() {
		return perbhytchi;
	}

	public void setPerbhytchi(int perbhytchi) {
		this.perbhytchi = perbhytchi;
	}

	public Double getBhytchi() {
		return bhytchi;
	}

	public void setBhytchi(Double bhytchi) {
		this.bhytchi = bhytchi;
	}

	public Double getThanhtien1() {
		return thanhtien1;
	}

	public void setThanhtien1(Double thanhtien1) {
		this.thanhtien1 = thanhtien1;
	}

	public int getPerbntra() {
		return perbntra;
	}

	public void setPerbntra(int perbntra) {
		this.perbntra = perbntra;
	}

	public Double getBntra() {
		return bntra;
	}

	public void setBntra(Double bntra) {
		this.bntra = bntra;
	}

	public void setUngTra(Double ungTra) {
		this.ungTra = ungTra;
	}

	

	public Double getUngTra() {
		return ungTra;
	}

	public DtDmNhanVien getNhanVienThungan() {
		return nhanVienThungan;
	}

	public void setNhanVienThungan(DtDmNhanVien nhanVienThungan) {
		this.nhanVienThungan = nhanVienThungan;
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

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public Boolean getHienThiGhiNhan() {
		return hienThiGhiNhan;
	}

	public void setHienThiGhiNhan(Boolean hienThiGhiNhan) {
		this.hienThiGhiNhan = hienThiGhiNhan;
	}

	public Boolean getHienThiInPhieu() {
		return hienThiInPhieu;
	}

	public void setHienThiInPhieu(Boolean hienThiInPhieu) {
		this.hienThiInPhieu = hienThiInPhieu;
	}
	public String getGioThanhToan() {
		return gioThanhToan;
	}
	public void setGioThanhToan(String gioThanhToan) {
		this.gioThanhToan = gioThanhToan;
	}
	public Boolean getHienThiHuyPhieu() {
		return hienThiHuyPhieu;
	}
	public void setHienThiHuyPhieu(Boolean hienThiHuyPhieu) {
		this.hienThiHuyPhieu = hienThiHuyPhieu;
	}
	public String getLiDoTT()
	{
		return sLiDoTT;
	}
	public void setLiDoTT(String liDoTT)
	{
		sLiDoTT = liDoTT;
	}
	public Double getBenhnhanTra() {
		return benhnhanTra;
	}
	public void setBenhnhanTra(Double benhnhanTra) {
		this.benhnhanTra = benhnhanTra;
	}
	public Double getConlai() {
		return conlai;
	}
	public void setConlai(Double conlai) {
		this.conlai = conlai;
	}
	public List<ClsMo> getListClsMo() {
		return listClsMo;
	}
	public void setListClsMo(List<ClsMo> listClsMo) {
		this.listClsMo = listClsMo;
	}
	public List<ThuocNoiTru> getListThuocNT() {
		return listThuocNT;
	}
	public void setListThuocNT(List<ThuocNoiTru> listThuocNT) {
		this.listThuocNT = listThuocNT;
	}
	public Boolean getHienThiInPhieuNgT() {
		return hienThiInPhieuNgT;
	}
	public void setHienThiInPhieuNgT(Boolean hienThiInPhieuNgT) {
		this.hienThiInPhieuNgT = hienThiInPhieuNgT;
	}
	public ChuyenVaoNoiTru getCvnt() {
		return cvnt;
	}
	public void setCvnt(ChuyenVaoNoiTru cvnt) {
		this.cvnt = cvnt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNgayRavien() {
		return ngayRavien;
	}

	public void setNgayRavien(String ngayRavien) {
		this.ngayRavien = ngayRavien;
	}
	
	

}
