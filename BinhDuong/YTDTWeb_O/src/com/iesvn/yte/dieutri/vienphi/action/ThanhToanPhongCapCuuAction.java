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

import javax.swing.JApplet;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

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
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.delegate.TamUngKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.dieutri.entity.TamUngKham;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.ThamKhamUtil;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3235_Thanhtoanphongcapcuu")
@Synchronized(timeout = 6000000)
public class ThanhToanPhongCapCuuAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(ThanhToanPhongCapCuuAction.class);

	private HsThtoank hsttk;
	private TiepDon tiepDon;
	private String maTiepDon;
	private String ngayHt;
	private String gioHt;
	private String ngaySinh;
	//private String ktns;
	private Double ungTra;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	private String formTitle;
	List<ClsKham> listCLSKham;
	List<ThuocPhongKham> listTPK;
	@Out(required = false)
	private String isReport = "false";
	
	private String isUpdate;
	//private Double tienBnTra;
	
	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
	DmBenhIcd phu1_temp = new DmBenhIcd(); // 20110701 bao.ttc: luu benh ICD Phu1 de in report (vi phai xoa tren giao dien)
	
	private DtDmCum cum = null;
	
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
    private YteLog yteLog ;
    private String listDataLog = "";
	
	//@Create
	@Begin(join = true)
	public String init(String strTmp) {
		logger.info("-----init()-----");
		
		refreshnhanvienthungan();
		logger.info("nhanVienThungan1"+nhanVienThungan);
		PcCumThuPhiDelegate pcCumThuPhiDelegate  = PcCumThuPhiDelegate.getInstance();
		PcCumThuPhi pc =  pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), nhanVienThungan.getDtdmnhanvienMa());
		
		logger.info("PcCumThuPhi"+pc);
		
		
		if (pc!=null && pc.getDtdmnhanvienMa() != null){
			
			//nhanVienThungan = pc.getDtdmnhanvienMa();
			logger.info("nhanVienThungan:"+nhanVienThungan);
			cum = pc.getDtdmcumMa();
		}
		formTitle = strTmp;
		
		reset();
		return "TiepDon_KhamBenh_XemChiPhiDieuTri";
	}
	
	
	@End
	public void endConversation(){
		
	}
	
	// Thong tin can thiet cho ho so thanh toan kham
	private Double thuocTrongDM =  new Double(0);
	private Double thuocNDM = new Double(0);
	private Double vTTHTrongDM = new Double(0);
	private Double vTTHNDM = new Double(0);
	private Double cLSMauTrongDM = new Double(0);
	private Double clsMauNDM = new Double(0);
	private Double pTTTTrongDM = new Double(0);
	private Double pTTTNDM = new Double(0);
	private Double nSKhongThu = new Double(0);
	
	private Double ung = new Double(0);
	private Double tra = new Double(0);
	private Double soDu = new Double(0); // = bntra - ung + tra	
	
	// Phan danh cho tinh toan
	private int permiengiam = 0;
	private Double miengiam = new Double(0);
	private Double thatthu = new Double(0);
	private int perbhytchi = 0;
	private Double bhytchi = new Double(0);
	private Double thanhtien1 = new Double(0);
	private int perbntra = 0;
	private Double bntra = new Double(0);
	
	private void resetPhanTinhToan(){
		 thuocTrongDM =  new Double(0);
		 thuocNDM = new Double(0);
		 vTTHTrongDM = new Double(0);
		 vTTHNDM = new Double(0);
		 cLSMauTrongDM = new Double(0);
		 clsMauNDM = new Double(0);
		 pTTTTrongDM = new Double(0);
		 pTTTNDM = new Double(0);
		 nSKhongThu = new Double(0);
		
		 ung = new Double(0);
		 tra = new Double(0);
		 soDu = new Double(0); // = bntra - ung + tra	
		
		// Phan danh cho tinh toan
		permiengiam = 0;
		 miengiam = new Double(0);
		 thatthu = new Double(0);
		 perbhytchi = 0;
		 bhytchi = new Double(0);
		 thanhtien1 = new Double(0);
		 perbntra = 0;
		 bntra = new Double(0);
	}
	
	private List<ClsKham> clslist = null;
	private void tinhToanChoHSTKKham(HsThtoank hsttk){
		HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(tiepDon);
		hsthtoankUtil.tinhToanChoHSTKKham(hsttk);
		clslist = hsthtoankUtil.getListCtkq_temp();
		 
	}
	
	//
	
	public void reset() {
		refreshnhanvienthungan();
		logger.info("-----reset()-----");
		hsttk = new HsThtoank();
		//setInforIfNullForHsThtoank();
		tiepDon = new TiepDon();
		//SetInforUtil.setInforIfNullForTiepDon(tiepDon);
		maTiepDon = "";
		ngaySinh = "";
		ngayHt = Utils.getCurrentDate();
		gioHt = Utils.getGioPhut(new Date());
		ungTra = null;
//		ktns = "0";
		isUpdate = "0";
		listCLSKham = new ArrayList<ClsKham>();
		listTPK = new ArrayList<ThuocPhongKham>();
		phu1_temp = new DmBenhIcd();
//		tienBnTra = new Double("0");
		
	}
	public void loadMaPhieu() {
		logger.info("-----loadMaPhieu()----- " + hsttk.getHsthtoankMa());
		String maPhieu = hsttk.getHsthtoankMa();
		if (maPhieu.trim().length() > 0) {
			HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
			hsttk = hsttkDelegate.findByMaPhieu(maPhieu);
			if (hsttk == null || hsttk.equals("") 
					|| hsttk.getHsthtoankDatt() == null) {										
					FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, maPhieu);
					reset();
					
			} else {
				logger.info("-----Tim thay hsttk-----");
				//TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
				tiepDon = hsttk.getTiepdonMa();
				if (tiepDon != null) {
					maTiepDon = tiepDon.getTiepdonMa();
					ThamKham tk = ThamKhamDelegate.getInstance().findByMaTiepDon(maTiepDon);
					if (tk != null) {
						if(formTitle.equals("ThuVienPhi")) { 
							if(!tk.getThamkhamBankham().getDtdmbankhamMa().equalsIgnoreCase("CCL") &&
									!tk.getThamkhamBankham().getDtdmbankhamMa().equalsIgnoreCase("CCN") 	) {
								FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_DOI_TUONG_CAP_CUU + ", " + IConstantsRes.KHONG_THANH_TOAN_O_DAY);
								reset();
								return;
							}
						}
						// 20110701 bao.ttc: luu benh ICD Phu1 de in report (vi phai xoa tren giao dien)
						if (tk.getBenhicd10phu1() != null){
							phu1_temp = tk.getBenhicd10phu1();
						}
					} else {
						FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + maTiepDon);
						reset();
						return;
					}
					// phuc.lc : 11-01-2011 : End fix bug 1991
					Date d = tiepDon.getBenhnhanMa(true).getBenhnhanNgaysinh();
					if (d != null) {
						Calendar cal = Calendar.getInstance();
						cal.setTime(d);
						ngaySinh = Utils.convertCalendar2Str(cal);
					}
					
					// 20101102 bao.ttc: tim xem da Thanh toan hay chua
					hsttk = hsttkDelegate.findBytiepdonMa(maTiepDon);
					//logger.info("-----hsttk-----" + hsttk);
					
					if (hsttk != null) {
						logger.info("-----Tim thay hsttk-----");
						// phuc.lc 09-03-2011 : begin
						// Set field Thtoan bang 0 de hien thi len giao dien
						hsttk.setHsthtoankThtoan(new Double(0.0));
						// phuc.lc 09-03-2011 : end
						FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
						isUpdate = "1";					
					} else {
						//reset();
						logger.info("-----Khong tim thay hsttk, tao moi-----");
						FacesMessages.instance().clear();
						hsttk = new HsThtoank();
						hsttk.setTiepdonMa(tiepDon);
						isUpdate = "0";
						//tinhToanChoHSTKKham(hsttk);
						//ungTra = "" + ( hsttk.getHsthtoankTamung() - hsttk.getHsthtoankHoanung() );
						tinhToanChoHSTKKham(hsttk);
						Utils.setInforForHsThToan(hsttk);
						ungTra =  hsttk.getHsthtoankTamung() - hsttk.getHsthtoankHoanung() ;
					}
					ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();				
					listCLSKham = clsKhamDelegate.findByTiepdonma(tiepDon.getTiepdonMa());
					ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
					listTPK = thuocDel.find2LoaiByMaTiepDon(tiepDon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);					
				}	
				
			} 
		} else {
			FacesMessages.instance().clear();
			reset();
		}
		if(nhanVienThungan==null){
			refreshnhanvienthungan();
		}
	}
	public void loadTiepDon() {
		logger.info("-----loadTiepDon()-----, formTitle = " + formTitle);
		if (!maTiepDon.equals("")) {
			TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
			HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
			tiepDon = tdDelegate.find(maTiepDon);
			//logger.info("-----tiepDon-----" + tiepDon);
			if (tiepDon != null) {
				maTiepDon = tiepDon.getTiepdonMa();
				// phuc.lc : 11-01-2011 : Begin fix bug 1991
				// Kiem tra co phai la benh nhan cap cuu hay khong
				ThamKham tk = ThamKhamDelegate.getInstance().findByMaTiepDon(maTiepDon);
				if (tk != null) {
					if(formTitle.equals("ThuVienPhi")) { 
						ChuyenVaoNoiTru cvnt = ChuyenVaoNoiTruDelegate.getInstance().findByMaTiepDon(maTiepDon);
						if (cvnt != null) {																
							FacesMessages.instance().add(IConstantsRes.BN_DA_CHUYEN_VAO_NOI_TRU);
							reset();
							return;
						} else if (!"CCL".equals( tiepDon.getTiepdonBankham(true).getDtdmbankhamMa())
								&& !"CCN".equals( tiepDon.getTiepdonBankham(true).getDtdmbankhamMa())) {
							FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_DOI_TUONG_CAP_CUU + ", " + IConstantsRes.KHONG_THANH_TOAN_O_DAY);
							reset();
							return;
						}
					}
					// 20110701 bao.ttc: luu benh ICD Phu1 de in report (vi phai xoa tren giao dien)
					if (tk.getBenhicd10phu1() != null){
						phu1_temp = tk.getBenhicd10phu1();
					}
				} else {
					FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + maTiepDon);
					reset();
					return;
				}
				// phuc.lc : 11-01-2011 : End fix bug 1991
				Date d = tiepDon.getBenhnhanMa(true).getBenhnhanNgaysinh();
				if (d != null) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(d);
					ngaySinh = Utils.convertCalendar2Str(cal);
				}
				
				// 20101102 bao.ttc: tim xem da Thanh toan hay chua
				hsttk = hsttkDelegate.findBytiepdonMa(maTiepDon);
				
				
				if (hsttk != null && (!tiepDon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("TP"))) {					
					logger.info("-----Tim thay hsttk-----, ma doi tuong = " + tiepDon.getDoituongMa().getDmdoituongMa());
					if (formTitle.equals("TiepNhanKhamBenh") && 
						tiepDon.getDoituongMa().getDmdoituongMa().equals("TP")) {
							tinhToanChoHSTKKham(hsttk);
							Utils.setInforForHsThToan(hsttk);
							ungTra =  hsttk.getHsthtoankTamung() - hsttk.getHsthtoankHoanung() ;	
							
					} 
					// phuc.lc 09-03-2011 : begin
					// Set field Thtoan bang 0 de hien thi len giao dien
					hsttk.setHsthtoankThtoan(new Double(0.0));
					// phuc.lc 09-03-2011 : end					
					FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
					isUpdate = "1";					
				} else {
					//reset();
					logger.info("-----Khong tim thay hsttk, tao moi-----");
					// Kiem tra xem co BA luu hay khong
					Hsba hsba = HsbaDelegate.getInstance().findByTiepDonMa(tiepDon.getTiepdonMa());
					if (hsba != null) {
						HsThtoan hstt = HsThtoanDelegate.getInstance().findBySovaovien(hsba.getHsbaSovaovien());
						if(hstt != null) {
							FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU,
									"(" + (hstt.getHsthtoanNgaytt() != null ? IConstantsRes.DA_THANH_TOAN :IConstantsRes.CHUA_THANH_TOAN) + ")",
									hsba.getHsbaSovaovien());
						} else {
							FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU,
									"(" + IConstantsRes.CHUA_THANH_TOAN + ")",
									hsba.getHsbaSovaovien());
						}
					}
					//FacesMessages.instance().clear();
					hsttk = new HsThtoank();
					hsttk.setTiepdonMa(tiepDon);
					isUpdate = "0";
					//tinhToanChoHSTKKham(hsttk);
					//ungTra = "" + ( hsttk.getHsthtoankTamung() - hsttk.getHsthtoankHoanung() );
					tinhToanChoHSTKKham(hsttk);
					Utils.setInforForHsThToan(hsttk);
					ungTra =  hsttk.getHsthtoankTamung() - hsttk.getHsthtoankHoanung() ;
				}

				
				//bao.ttc: Khong can tim hsttk ma tinh toan chi phi Dieu tri de view
				//hsttk = new HsThtoank();
				//hsttk.setTiepdonMa(tiepDon);
				//tinhToanChoHSTKKham(hsttk);
				//Utils.setInforForHsThToan(hsttk);
				//ungTra =  hsttk.getHsthtoankTamung() - hsttk.getHsthtoankHoanung() ;
				// Fix bug 3566
				
				ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();				
				listCLSKham = clsKhamDelegate.findByTiepdonma(tiepDon.getTiepdonMa());
				ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
				listTPK = thuocDel.find2LoaiByMaTiepDon(tiepDon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);
				
			} else {
				FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + maTiepDon);
				reset();
			}
			logger.info("hsttk = " + hsttk );
		}
	}
	public void refreshnhanvienthungan(){
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienThungan = nvDelegate.findByNd(identity.getUsername());
		if (nhanVienThungan == null) {
			nhanVienThungan = new DtDmNhanVien();
		}
	}
	public void end() {
		refreshnhanvienthungan();
		logger.info("-----end()-----nhanVienThungan = " + nhanVienThungan);
		if (hsttk != null) {
			
			
			if (hsttk.getHsthtoankMa() != null && !hsttk.getHsthtoankMa().equals("")) {
				FacesMessages.instance().add(IConstantsRes.HOSODATHTOAN);
			} else {
				HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
				removeInfoForHsThtoank();
				try {
					Date d = Utils.getDBDate(gioHt, ngayHt).getTime();
					hsttk.setHsthtoankNgaygiott(d);
					hsttk.setHsthtoankDatt(true);
					logger.info("-----ngay gio tt: " + d);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error(e);
				}
				
				Double bnTra = hsttk.getHsthtoankBntra();
				// phuc.lc 25-01-2011 : Begin fix bug 1998
				// Doi tuong bao hiem chi thanh toan 1 lan, nen set gia tri truong thtoan = 0
				//if (hsttk.getTiepdonMa() != null) {
				//	if (hsttk.getTiepdonMa().getDoituongMa() != null) {
				//		if(hsttk.getTiepdonMa().getDoituongMa().getDmdoituongMa().equals("BH")) {							
				//			hsttk.setHsthtoankThtoan(new Double(0.0));
				//		}
				//	}
				//}
				// phuc.lc 25-01-2011 : End fix bug 1998
				//Double thToan = hsttk.getHsthtoankThtoan();
				logger.info("-----bnTra: " + bnTra);
				//logger.info("-----thToan: " + thToan);				
				hsttk.setHsthtoankThungan(nhanVienThungan.getDtdmnhanvienMaso() == null ? null : nhanVienThungan);
				hsttk.setHsthtoankCum(cum);

				
				try{
					Utils.setInforForHsThToan(hsttk);
					String maPhieuTTK = hsttkDelegate.capNhatTTHsttk(hsttk,clslist,null, false);
					hsttk.setHsthtoankMa(maPhieuTTK);
					// phuc.lc 09-03-2011 : Begin fix bug 2221
					// Sau khi da cap nhat thong tin thanh toan, set field Thtoan = 0, chi de hien thi lai tren giao dien, khong luu vao database
					hsttk.setHsthtoankThtoan(new Double(0.0));
					// phuc.lc 09-03-2011 : End fix bug 2221
					//20101210 bao.ttc: set status cac Tam Ung & Hoan Ung da Thanh Toan ve trang thai "TT" de khong tinh vao cac lan sau nua
					TamUngKhamDelegate tamUngKhamDele = TamUngKhamDelegate.getInstance();
					
					List<TamUngKham> lstTamUngKham = tamUngKhamDele.getListTamUngChuaTT(maTiepDon);
					
					if (lstTamUngKham != null && lstTamUngKham.size() > 0){
			            for (TamUngKham tamUngKham: lstTamUngKham){
			                tamUngKham.setTamungkhamStatus("TT");
			                tamUngKhamDele.edit(tamUngKham);
			            }
			        }
					// lu thong tin log
					String listDataLog= "";
					for (ThuocPhongKham thuocPhongKham : listTPK) {
						//luu log thong tin thuoc
						listDataLog += " Thuoc Ma LK:"+ thuocPhongKham.getThuocphongkhamMalk()+
								" Don gia: "+  thuocPhongKham.getThuocphongkhamDongia()+ " Don gia ban: "+ thuocPhongKham.getThuocphongkhamDongiaban()+ 
								" So luong: "+ thuocPhongKham.getThuocphongkhamSoluong()+
								" Thanh tien: "+thuocPhongKham.getThuocphongkhamThanhtien()+
								" Nam SX: " + thuocPhongKham.getThuocphongkhamNamnhap()+
								" Nam HD: " + thuocPhongKham.getThuocphongkhamNamhd()+ " \n";	
					
					}
					for(ClsKham cls : listCLSKham){
						listDataLog+= "CLS ma: "+ cls.getClskhamMahang(true).getDtdmclsbgMa() + 
								" Don gia: "+cls.getClskhamDongia()+"\n";
					}
					 yteLog.setForm("B3235_Thanhtoanphongcapcuu");
			         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
			         yteLog.setObjectId(tiepDon.getTiepdonMa());
			         yteLog.setLogString("Ngoai DM \t Danh muc TT \n"+
			        		 			 "Thuoc: "+(hsttk.getHsthtoankThuocndm() == null ? 0: hsttk.getHsthtoankThuocndm())+"\t"+(hsttk.getHsthtoankThuoc() == null ? 0 : hsttk.getHsthtoankThuoc())+"\n"+
			        		 			 "VTTH: "+ (hsttk.getHsthtoankVtthndm() == null ? 0 : hsttk.getHsthtoankVtthndm())+"\t"+(hsttk.getHsthtoankVtth() == null ? 0 : hsttk.getHsthtoankVtth())+"\n"+
			        		 			 "CLS: "+(hsttk.getHsthtoankClsndm()== null ? 0 : hsttk.getHsthtoankClsndm())+"\t"+(hsttk.getHsthtoankCls() == null ? 0 :hsttk.getHsthtoankCls())+"\n"+
			        		 			 "Tong chi: "+ (hsttk.getHsthtoankNdm()== null ? 0 : hsttk.getHsthtoankNdm())+ "\t"+(hsttk.getHsthtoankDm()== null ? 0 : hsttk.getHsthtoankDm())+"\n"+
			        		  			 "BN tra: "+ (hsttk.getHsthtoankBntra() == null ? 0 : hsttk.getHsthtoankBntra())+"\n"+
			        		  			 "Ngan sach: "+ (hsttk.getHsthtoankNgansach()== null ? 0 : hsttk.getHsthtoankNgansach())+"\n"+
			        		  			 "Khong thu: "+ (hsttk.getHsthtoankKhongthu()== null ? 0 : hsttk.getHsthtoankKhongthu())+"\n"+
			        		  			 "Con lai: "+(hsttk.getHsthtoankThtoan()== null ? 0 : hsttk.getHsthtoankThtoan())+"\n"+
			        		  			 "That thu:"+ (hsttk.getHsthtoankThatthu()== null ? 0 : hsttk.getHsthtoankThatthu())+"\n"+
			        		  			 "Mien giam: "+ (hsttk.getHsthtoankXetgiam()== null ? 0 : hsttk.getHsthtoankXetgiam())
			        		  			 ); 
			         yteLog.setDateTime(new Date());
			         yteLog.setListData(listDataLog);
			         yteLogDele.create(yteLog);
					
					FacesMessages.instance().add(IConstantsRes.SUCCESS);
					isUpdate = "1";					
					
				}catch(Exception e){
					logger.info(e);
					FacesMessages.instance().add(IConstantsRes.FAIL);
					isUpdate = "0";
				}
					
				
			}
		}
	}
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	public String inPhieu(){
		logger.info("Begin inPhieu(), tiepdon = " + tiepDon + ", Ma TD = " + tiepDon.getTiepdonMa());
		String tdMa = tiepDon.getTiepdonMa();
		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		hsttk = hsttkDelegate.findBytiepdonMa(tdMa);
		if(hsttk == null){
			FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU);
			logger.info("End InPhieu(): Khong tim thay phieu !");
			return "";
		}
		
		Double thtoan = 0.0;
		// phuc.lc 25-01-2011 : Begin fix bug 2091
		if(tiepDon.getDoituongMa(true).getDmdoituongMa().equals("BH")) {
			if(hsttk.getHsthtoankBntra() == null){
				thtoan = new Double(0);
			} else{
				thtoan = hsttk.getHsthtoankBntra();
			}
		} else {
			if(hsttk.getHsthtoankThtoan() == null){
				thtoan = new Double(0);
			} else{
				thtoan = hsttk.getHsthtoankThtoan();
			}
		}
		// phuc.lc 25-01-2011 : End fix bug 2091
		Double ndm = 0.0;
		if(hsttk.getHsthtoankNdm() == null){
			ndm = new Double(0);
		} else{
			ndm = hsttk.getHsthtoankNdm();
		}
		if(thtoan == 0 && ndm == 0){		//bao.ttc: BHYT tra 100% chi phi
			FacesMessages.instance().add(IConstantsRes.KHONG_IN_BIEN_LAI);
			logger.info("End InPhieu(): Khong in bien lai !");
			return "";
		}
		
		XuatReport();
		logger.info("End inPhieu() ---------------------------");
		if(nhanVienThungan==null){
			refreshnhanvienthungan();
		}
		return "B3360_Chonmenuxuattaptin";
		
		
		//XuatReport();
		//return "B3360_Chonmenuxuattaptin";
	}
	int index = 0;
	/**
	 * xuat report 
	 */
	
	public void XuatReport() {
		reportTypeVP="thanhtoanphongcapcuu";
		String baocao1 = null;
		ClsKhamDelegate clsKhamDelegate  = ClsKhamDelegate.getInstance();
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
		// Lay hsttk
		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		hsttk = hsttkDelegate.findBytiepdonMa(tiepDon.getTiepdonMa());
		if (hsttk != null) {
			miengiam = hsttk.getHsthtoankXetgiam() == null ? 0.0 : hsttk.getHsthtoankXetgiam(); 
		}
		// lay CLS
		List<ClsKham> listClsKham = clsKhamDelegate.findByTiepdonma(tiepDon.getTiepdonMa());
		
		for (ClsKham eachCls : listClsKham) {
			
			curGia = (eachCls.getClskhamDongiabntra() == null ? 0.0 : eachCls.getClskhamDongiabntra());			
			curGiaDV = (eachCls.getClskhamPhandv() == null ? 0.0 : eachCls.getClskhamPhandv());
			
			// Kiem tra cls co phai la dich vu hay khong
			if (eachCls.getClskhamYeucau() != null && eachCls.getClskhamYeucau().booleanValue() == true) {				
				hasDV = true;
				if (tiepDon.getDoituongMa() != null) {
					if (tiepDon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("MP")) {
						// Neu la doi tuong mien phi thi tinh lai gia dich vu
						curGia = (eachCls.getClskhamMahang().getDtdmclsbgDongiamp() == null ? 0.0 : eachCls.getClskhamMahang().getDtdmclsbgDongiamp());
						// Doi tuong mien phi yeu cau thuc hien CLS thi : gia dich vu = gia yeu cau - gia mien phi
						curGiaDV = (eachCls.getClskhamDongia() == null ? 0.0 : eachCls.getClskhamDongiabntra()) - curGia;
					}
				}
			}
			tongtien += curGia;
			tongtienDV += curGiaDV;
			// Lay gia benh nhan tra cho tung CLS, gia nay da tru % bao hiem va duoc tinh va luu san trong database	
			if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("MA")) {	// Mau
				mau += curGia;				
				mauDV += curGiaDV;
			} else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("XN")) {	// Xet nghiem
				xntdcn += curGia;				
				xntdcnDV += curGiaDV;
			} else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("CD")) {	// Chan doan hinh anh
				cdha += curGia;				
				cdhaDV += curGiaDV;
			} else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("PT") ||  // Phau thuat
					eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("TT")) {	// Thu thuat
				pttt += curGia;				
				ptttDV += curGiaDV;
			}else if(eachCls.getClskhamKtcao() != null && eachCls.getClskhamKtcao().booleanValue() == true) {	// Dich vu ky thuat cao
				ktc += curGia;				
				ktcDV += curGiaDV;	
			}else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("GI")) {	// Tien giuong(phong)
				dvp += curGia;				
				dvpDV += curGiaDV;	
			} else if (eachCls.getClskhamMaloai() != null && eachCls.getClskhamMaloai().getDtdmclsMaso().intValue() == 12){ // 12 la ma so cua can lam san van chuyen
				vc += curGia;				
				vcDV += curGiaDV;
			
			}else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("KH")) {	// Cong kham
				ck += curGia;
				ckDV += curGiaDV;
			} else {
				clskhac += curGia;
				clskhacDV += curGiaDV;
			}
			
			
		}
		
		// lay thuoc ban` khoam
		ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
		Double thuocDV = 0.0;   	// Tong tien thuoc 
		Double vtthDV = 0.0;		// Tong tien Vat tu tieu hao
		Double thuocBN = 0.0;   	// Thuoc benh nhan tra
		Double vtthBN = 0.0;		// Vat tu tieu hao benh nhan tra
		List<ThuocPhongKham> listTpk_Bankham = tpkDelegate.findByMaTiepDon(tiepDon.getTiepdonMa(), "1"); // thuoc ban kham
		for(ThuocPhongKham eachTpk : listTpk_Bankham) {
						
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
		
		// ke toa quay benh vien
		List<ThuocPhongKham>  listTpk_BHYT = tpkDelegate.findByMaTiepDon(tiepDon.getTiepdonMa(), "3"); // ke toa BHYT
		for(ThuocPhongKham eachTpk : listTpk_BHYT) {
						
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
		// Copy code
		String diachistr = "";
		if(tiepDon.getBenhnhanMa().getBenhnhanDiachi() != null)
			diachistr += tiepDon.getBenhnhanMa().getBenhnhanDiachi()+",";
		if(tiepDon.getBenhnhanMa().getXaMa(true).getDmxaTen() !=null)
			diachistr += tiepDon.getBenhnhanMa().getXaMa(true).getDmxaTen()+",";
		if(tiepDon.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null)
			diachistr += tiepDon.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen()+",";
		if(tiepDon.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null)
			diachistr += tiepDon.getBenhnhanMa().getTinhMa(true).getDmtinhTen();				
		
		String thungan = "";
		if(hsttk.getHsthtoankThungan() != null){
			if (hsttk.getHsthtoankThungan().getDtdmnhanvienTen() != null)
				thungan = hsttk.getHsthtoankThungan().getDtdmnhanvienTen();
			else thungan = "";
		} else thungan = "";
		String sNoiDungThu = "";
		if (tiepDon.getDoituongMa().getDmdoituongMa().equals("BH")) {
			if (hsttk != null) {
				sNoiDungThu = "Thu " + (100 - (hsttk.getHsthtoankTylebh() == null ? 0 : hsttk.getHsthtoankTylebh())) + IConstantsRes.NOI_DUNG_THU_BHYT;
			}
		} else {
			sNoiDungThu = "Thu tiền của bệnh nhân";
		}
		logger.info("hasDV = " + hasDV);
		tongtien = Utils.rounDoubleForReport(tongtien);
		tongtienDV = Utils.rounDoubleForReport(tongtienDV);
		// phuc.lc 22-04-2011 : Fix bug 2655
		int lanIn = hsttk.getHsthtoankLanin() == null ? 1 : (Integer.valueOf(hsttk.getHsthtoankLanin()).intValue() + 1);
		hsttk.setHsthtoankLanin("" + lanIn);
		if (hasDV && tongtienDV > 0) {
			// Neu co su dung dich vu thi in 3 lien bien lai thu tien va 3 lien hoa don
			try{
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon.jrxml";
				String pathTemplate_sub1 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub1.jrxml";
				String pathTemplate_sub2 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub2.jrxml";
				String pathTemplate_sub3 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub3.jrxml";
				String pathTemplate_sub4 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport0.jrxml";
				String pathTemplate_sub5 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport1.jrxml";
				String pathTemplate_sub6 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_hoadon_subreport2.jrxml";
				logger.info("da thay file templete bienlaithulephi_hoadon " + pathTemplate);
				
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport jasperSub1 =JasperCompileManager.compileReport(pathTemplate_sub1);
				JasperReport jasperSub2 =JasperCompileManager.compileReport(pathTemplate_sub2);
				JasperReport jasperSub3 =JasperCompileManager.compileReport(pathTemplate_sub3);
				
				JasperReport jasperSub4 =JasperCompileManager.compileReport(pathTemplate_sub4);
				JasperReport jasperSub5 =JasperCompileManager.compileReport(pathTemplate_sub5);
				JasperReport jasperSub6 =JasperCompileManager.compileReport(pathTemplate_sub6);
				logger.info("da thay file template ");
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("sub1", jasperSub1 );
				params.put("sub2", jasperSub2 );	
				params.put("sub3", jasperSub3 );
				params.put("sub4", jasperSub4 );
				params.put("sub5", jasperSub5 );
				params.put("sub6", jasperSub6 );
				// Cac param chung
				//params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
				params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
				params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
				params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
				params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
				params.put("HOTEN", tiepDon.getBenhnhanMa().getBenhnhanHoten());				
				params.put("DIACHI", diachistr);
				params.put("THUNGAN", thungan);
				
				// Cac param cho mau bien lai thu vien phi
				
				params.put("NOIDUNG", sNoiDungThu );												
				params.put("SOTIEN", Utils.formatNumberWithSeprator(tongtien - miengiam));
				params.put("TIENBANGCHU", Utils.NumberToString(tongtien - miengiam));				
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
				
				params.put("LANIN", "" + lanIn);
				
				Class.forName("oracle.jdbc.OracleDriver");
			    logger.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    } catch(Exception e){
			    	logger.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/","pdf","thanhtoanphongcapcuu");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    logger.info("duong dan file xuat report :" + baocao1);
			    logger.info("duong dan -------------------- :"+reportPathVP);
			    index+=1;			    
			    if(conn != null) conn.close();
			 // phuc.lc 22-04-2011 : Fix bug 2655
			    hsttkDelegate.edit(hsttk);
	            
			} catch(Exception e) {
			    logger.info("ERROR Method XuatReport!!!");
				e.printStackTrace();
			}
		} else {
			// Neu khong su dung dich vu thi chi in 3 lien bien lai thu tien
			try{
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1.jrxml";
				String pathTemplate_sub1 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub1.jrxml";
				String pathTemplate_sub2 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub2.jrxml";
				String pathTemplate_sub3 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/bienlaithulephi_mau1_sub3.jrxml";
				logger.info("da thay file templete bienlaithulephi_mau1" + pathTemplate);
				
				JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport jasperSub1 =JasperCompileManager.compileReport(pathTemplate_sub1);
				JasperReport jasperSub2 =JasperCompileManager.compileReport(pathTemplate_sub2);
				JasperReport jasperSub3 =JasperCompileManager.compileReport(pathTemplate_sub3);
				logger.info("da thay file template ");
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("sub1", jasperSub1 );
				params.put("sub2", jasperSub2 );
				params.put("sub3", jasperSub3 );
				
				
				//params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
				params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
				params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
				params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
				params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
				params.put("HOTEN", tiepDon.getBenhnhanMa().getBenhnhanHoten());
				
				params.put("DIACHI", diachistr);
				params.put("NOIDUNG", sNoiDungThu );
												
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
				
				params.put("LANIN", "" + lanIn);
				
				Class.forName("oracle.jdbc.OracleDriver");
			    logger.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    } catch(Exception e){
			    	logger.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/","pdf","thanhtoanphongcapcuu");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    logger.info("duong dan file xuat report :" + baocao1);
			    logger.info("duong dan -------------------- :"+reportPathVP);
			    index+=1;			    
			    if(conn != null) conn.close();
			 // phuc.lc 22-04-2011 : Fix bug 2655
			    hsttkDelegate.edit(hsttk);
	            
			} catch(Exception e) {
			    logger.info("ERROR Method XuatReport!!!");
				e.printStackTrace();
			}
		}
	}
	
	public String inPhieuThanhToan() {
		return XuatPhieuThanhToan();
		//return "B4160_Chonmenuxuattaptin";
	}
	public String XuatPhieuThanhToan() {
		logger.info("begin XuatPhieuThanhToan() tiepDon = " + tiepDon);
		if (tiepDon == null) return null;
		reportTypeVP="thanhtoanphongcapcuu";
		//reportTypeKC="PhieuThanhToanKCB";
		
		String baocao1=null;
		try {
			logger.info("Vao Method XuatPhieuThanhToan() kham chua benh cap cuu ngoai tru");
			
			SimpleDateFormat sdf=new SimpleDateFormat(Utils.FORMAT_DATE);			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			//String matiepDon = thamkham.getTiepdonMa().getTiepdonMa();
			params.put("MATIEPDON", tiepDon.getTiepdonMa() );
			params.put("HOTENBN", tiepDon.getBenhnhanMa().getBenhnhanHoten()  );
			
			String diachistr="";
			if(tiepDon.getBenhnhanMa().getBenhnhanDiachi() != null)
				diachistr += tiepDon.getBenhnhanMa().getBenhnhanDiachi()+",";
			if(tiepDon.getBenhnhanMa().getXaMa(true).getDmxaTen() !=null)
				diachistr += tiepDon.getBenhnhanMa().getXaMa(true).getDmxaTen()+",";
			if(tiepDon.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += tiepDon.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen()+",";
			if(tiepDon.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null)
				diachistr += tiepDon.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			if(tiepDon.getDoituongMa(true).getDmdoituongMa().equals("BH")){
				params.put("BHYT_CO", "X" );
				
				if( (tiepDon.getTiepdonTuyen() != null && tiepDon.getTiepdonTuyen().toString().equals("1"))
						|| (tiepDon.getTiepdonCoGiayGioiThieu() != null && tiepDon.getTiepdonCoGiayGioiThieu()) ){
					params.put("DUNGTUYEN","X");
				} else {
					params.put("TRAITUYEN","X");
				}
				
			} else {
				params.put("BHYT_KO", "X" );
			}
			
			if(tiepDon.getTiepdonGiatri1() != null){
				params.put("GTTU", sdf.format(tiepDon.getTiepdonGiatri1()));
			}else{
				params.put("GTTU", "");
			}
				
			if(tiepDon.getTiepdonGiatri2() != null){
				params.put("GTDEN", sdf.format(tiepDon.getTiepdonGiatri2()));
			}else{
				params.put("GTDEN", "");
			}
			if (tiepDon.getTiepdonSothebh() != null && !tiepDon.getTiepdonSothebh().equals("") &&
					tiepDon.getKhoibhytMa(true).getDtdmkhoibhytMa() != null && !tiepDon.getKhoibhytMa(true).getDtdmkhoibhytMa().equals("") &&
					tiepDon.getKcbbhytMa(true).getDmbenhvienMa()!=null
			){
				
				params.put("MATHEBHYT", tiepDon.getTiepdonSothebh());
				params.put("MABENHVIEN", tiepDon.getKcbbhytMa(true).getDmbenhvienMa().replace(".", "-"));
				
				
			}else{
				params.put("MABENHVIEN","");
				params.put("MATHEBHYT","");
			}
			
			if(tiepDon.getKcbbhytMa(true).getDmbenhvienTen()!=null)
				params.put("NOIDKKCBBANDAU", tiepDon.getKcbbhytMa(true).getDmbenhvienTen());
			if(tiepDon.getTinhbhytMa(true).getDmtinhTen()!=null)
				params.put("NOICAPTHE", tiepDon.getTinhbhytMa(true).getDmtinhTen());
			
			if(tiepDon.getTiepdonDonvigoi(true).getDmbenhvienMa()!=null)
				params.put("NOIGIOITHIEU", tiepDon.getTiepdonDonvigoi(true).getDmbenhvienTen());
			params.put("GIUONG", tiepDon.getTiepdonGiuong());
			ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
			ThamKham thamkham = thamkhamDelegate.findByBanKhamVaMaTiepDon(tiepDon.getTiepdonBankham().getDtdmbankhamMa(), tiepDon.getTiepdonMa());
			Calendar cal = Calendar.getInstance();
			cal.setTime(thamkham.getThamkhamNgaygio());
			params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));
			
			params.put("LYDOVAOVIEN", tiepDon.getTiepdonLydovaov());
			
			HsThtoankDelegate thanhToanDel = HsThtoankDelegate.getInstance();
			HsThtoank hsttk = new HsThtoank();
			hsttk = (HsThtoank)thanhToanDel.findBytiepdonMaLast(tiepDon.getTiepdonMa()); //20101109 bao.ttc: tim HSTTK cuoi cung 
			if (hsttk == null || (hsttk.getHsthtoankDatt() != null && !hsttk.getHsthtoankDatt())) {
				hsttk = new HsThtoank();
				hsttk.setTiepdonMa(tiepDon);			
				
				ThamKhamUtil tkUtil = new ThamKhamUtil();
				tkUtil.tinhToanChoHSTKKham(thamkham, hsttk, false, null, null);//tinhToanChoHSTKKham(hsttk);
				Utils.setInforForHsThToan(hsttk);
			}			
			
			params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt() );
			params.put("NGUOIBENHTRA", hsttk.getHsthtoankBntra());
			
			// 20110701 bao.ttc: them cac thong tin cho mau moi dung tu ngay 1/7
			if(hsttk.getHsthtoankNgaygiott() != null){
				params.put("NGAYTHANHTOAN", hsttk.getHsthtoankNgaygiott());
				if(thamkham.getThamkhamNgaygio() != null){
					params.put("SONGAYDT", Utils.getDaysBetween(thamkham.getThamkhamNgaygio(), hsttk.getHsthtoankNgaygiott()));
				}
			}
			
			if(tiepDon.getTiepdonBankham() != null){
				if(tiepDon.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC")){
					params.put("CAPCUU","X");
					params.put("DUNGTUYEN","");
					params.put("TRAITUYEN","");
				}
			}
			
			params.put("PHIEUSO", hsttk.getHsthtoankMa());
			logger.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
			params.put("TYLEBH",hsttk.getHsthtoankTylebh());
			String tyleBNtra = ""+ (100 - hsttk.getHsthtoankTylebh() );
			
			if ("MP".equals( tiepDon.getDoituongMa(true).getDmdoituongMa())){
				tyleBNtra = "0";
			}
			params.put("PHANTRAMBNTRA",tyleBNtra);
			
			params.put("BIENLAISO", ""  );			
			params.put("namsinh", tiepDon.getBenhnhanMa().getBenhnhanNamsinh());
						
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
				
			}else { // 20110701 bao.ttc: load benh ICD Phu1 de in report neu co (vi da xoa tren giao dien)
				if (phu1_temp != null && phu1_temp.getDmbenhicdMaso() != null){
					maChanDoan = phu1_temp.getDmbenhicdMa();
					tenChanDoan = phu1_temp.getDmbenhicdTen();
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
			List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(tiepDon.getTiepdonMa());
			List<ClsKham> listClsTmp;
			List<ThuocPhongKham> listTpk;
			StringBuffer bufStr = new StringBuffer();
			Double tongTienDV = 0.0;
			if (listTk != null && listTk.size() > 0) {
				
				for (ThamKham each : listTk) {
					if (bufStr.length() > 0) {
						bufStr.append(", " + each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
					} else {
						bufStr.append(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
					}
				}
			}		
					// Kiem tra co su dung cls yeu cau khong
					listClsTmp = ClsKhamDelegate.getInstance().findByTiepdonma(tiepDon.getTiepdonMa());
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
					
					listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepDon.getTiepdonMa(), "1");
					if (listTpk != null && listTpk.size() > 0) {
						for (ThuocPhongKham eachTpk : listTpk) {
							if ((eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true)
								|| (eachTpk.getThuocphongkhamNdm() != null && eachTpk.getThuocphongkhamNdm().booleanValue() == true))	{
								tongTienDV += eachTpk.getThuocphongkhamDongia(); 
							}
						}
					}
					listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepDon.getTiepdonMa(), "3");
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
				if (tiepDon.getTiepdonKhaisinh() == null ||
						tiepDon.getTiepdonKhaisinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh = tiepDon.getTiepdonKhaisinh();
				}
				
			}else{
				if (tiepDon.getTiepdonKhaisinh() == null ||
						tiepDon.getTiepdonKhaisinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh += "/" + tiepDon.getTiepdonKhaisinh();
				}				
			}
			// them vao chung sinh
			
			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")){
				if (tiepDon.getTiepdonChungsinh() == null ||
						tiepDon.getTiepdonChungsinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh = tiepDon.getTiepdonChungsinh();
				}
				
			}else{
				if (tiepDon.getTiepdonChungsinh() == null ||
						tiepDon.getTiepdonChungsinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh += "/" + tiepDon.getTiepdonChungsinh();
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
			String soTheNgheo = tiepDon.getTiepdonThengheo();
			
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
			
			DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(tiepDon.getBenhnhanMa(true).getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh","dmgtMaso" );
			if (gioi != null){

				params.put("GIOI", gioi.getDmgtTen());
						
			}
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			logger.info("tongTienDV = " + tongTienDV);
			JasperReport jasperReport;
			if (tongTienDV > 0) {
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_DV.jrxml";
				// String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
				// String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
				// 20110725 bao.ttc: load template Cap cuu
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBcapcuu_subreport0.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBcapcuu_subreport1.jrxml";
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
				String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBcapcuu.jrxml";
				String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBcapcuu_subreport0.jrxml";
				String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBcapcuu_subreport1.jrxml";
				
				jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
			}
			logger.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    logger.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	logger.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","PhieuThanhToanKCBcapcuu");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    logger.info("duong dan file xuat report :" + baocao1);
			    logger.info("duong dan -------------------- :"+reportPathVP);
			    
			    if(conn != null) {
			    	conn.close();
			    	conn = null;
			    }
		}catch (Exception e) {
		    logger.info("ERROR Method XuatPhieuThanhToan!!!");
			e.printStackTrace();
			return null;
		}
		logger.info("Thoat Method XuatPhieuThanhToan");
		if(nhanVienThungan==null){
			refreshnhanvienthungan();
		}
		return "B3360_Chonmenuxuattaptin";
	    
	}
	public void huyPhieu() {
		logger.info("Begin huyPhieu(), hsttk = " + hsttk + ", maPhieu = " + hsttk.getHsthtoankMa());
		if (hsttk != null) {
			
			if (hsttk.getHsthtoankDatt() != null && hsttk.getHsthtoankDatt().booleanValue() == true) {
				 
				try {
					// Lay danh sach CLS theo ma tiep don de cap nhat lai
					ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
					List<ClsKham> listCls = clsKhamDel.findByTiepdonma(hsttk.getTiepdonMa().getTiepdonMa());
					if (listCls != null && listCls.size() > 0) {
						for(ClsKham eachCls : listCls) {
							eachCls.setClskhamDatt(null);
							eachCls.setClskhamNgaygiott(null);
							eachCls.setClskhamMaphieu(null);
							clsKhamDel.edit(eachCls);
						}
					}
					// Lay danh sach Thuoc phong kham (thuoc ban kham) theo ma tiep don de cap nhat lai
					ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
					List<ThuocPhongKham> listTpk = tpkDelegate.findByMaTiepDon(hsttk.getTiepdonMa().getTiepdonMa(), "1"); // thuoc ban kham
					if (listTpk != null && listTpk.size() > 0) {
						for(ThuocPhongKham eachTpk : listTpk) {
							eachTpk.setThuocphongkhamDatt(null);
							eachTpk.setThuocphongkhamNgaygiott(null);
							eachTpk.setThuocphongkhamMaphieud(null);
							tpkDelegate.edit(eachTpk);
						}
					}
					// Lay danh sach Thuoc phong kham (ke toa quay benh vien) theo ma tiep don de cap nhat lai
					listTpk = tpkDelegate.findByMaTiepDon(hsttk.getTiepdonMa().getTiepdonMa(), "3"); // thuoc ban kham
					if (listTpk != null && listTpk.size() > 0) {
						for(ThuocPhongKham eachTpk : listTpk) {
							eachTpk.setThuocphongkhamDatt(null);
							eachTpk.setThuocphongkhamNgaygiott(null);
							eachTpk.setThuocphongkhamMaphieud(null);
							tpkDelegate.edit(eachTpk);
						}
					}
					// Cap nhat hsttk 
					hsttk.setHsthtoankDatt(null);
					hsttk.setHsthtoankNgaygiott(null);
					hsttk.setHsthtoankLanin(null);
					HsThtoankDelegate.getInstance().edit(hsttk);
					//set status cac Tam Ung & Hoan Ung da Thanh Toan ve trang thai Null 
					TamUngKhamDelegate tamUngKhamDele = TamUngKhamDelegate.getInstance();
					
					List<TamUngKham> lstTamUngKham = tamUngKhamDele.getListTamUngDaTT(maTiepDon);
					
					if (lstTamUngKham != null && lstTamUngKham.size() > 0){
			            for (TamUngKham tamUngKham: lstTamUngKham){
			                tamUngKham.setTamungkhamStatus(null);
			                tamUngKhamDele.edit(tamUngKham);
			            }
			        }
					FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, hsttk.getHsthtoankMa());
					reset();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				// Thong bao chua thanh toan
			}
		}
		logger.info("End huyPhieu()");
		if(nhanVienThungan==null)
		{
			refreshnhanvienthungan();
		}
	}
	public String troVe(){
		try {
			logger.info("***** troVe()");
			return "/B3_Vienphi/ThuVienPhi/B3235_Thanhtoanphongcapcuu.xhtml";
		} 		
		catch (Exception e) {
			logger.info("***** End exception = " + e);    	
		} 
		return null;
	}

	private void removeInfoForHsThtoank() {
		logger.info("Begin removeInfoForHsThtoank() ");
		if (hsttk != null) {
			if (hsttk.getHsthtoankMabenh() != null) {
				if ("".equals(Utils.reFactorString(hsttk.getHsthtoankMabenh().getDmbenhicdMa()) == null)) {
					hsttk.setHsthtoankMabenh(null);
				}
			}
			if (hsttk.getHsthtoankNhanviencn() != null) {
				if ("".equals(Utils.reFactorString(hsttk.getHsthtoankNhanviencn().getDtdmnhanvienMa()) == null)) {
					hsttk.setHsthtoankNhanviencn(null);
				}
			}
			if (hsttk.getHsthtoankThungan() != null) {
				if ("".equals(Utils.reFactorString(hsttk.getHsthtoankThungan().getDtdmnhanvienMa()) == null)) {
					hsttk.setHsthtoankThungan(null);
				}
			}
		}
	}
	
	public void setMaTiepDon(String maTiepDon) {
		this.maTiepDon = maTiepDon;
	}

	public String getMaTiepDon() {
		return maTiepDon;
	}

	public void setHsttk(HsThtoank hsttk) {
		this.hsttk = hsttk;
	}

	public HsThtoank getHsttk() {
		return hsttk;
	}

	public void setTiepDon(TiepDon tiepDon) {
		this.tiepDon = tiepDon;
	}

	public TiepDon getTiepDon() {
		return tiepDon;
	}

	public void setNgayHt(String ngayHt) {
		this.ngayHt = ngayHt;
	}

	public String getNgayHt() {
		return ngayHt;
	}

	public void setGioHt(String gioHt) {
		this.gioHt = gioHt;
	}

	public String getGioHt() {
		return gioHt;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

//	public void setKtns(String ktns) {
//		this.ktns = ktns;
//	}
//
//	public String getKtns() {
//		return ktns;
//	}


	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public Double getUngTra() {
		return ungTra;
	}


	public void setUngTra(Double ungTra) {
		this.ungTra = ungTra;
	}


	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

//	public void setTienBnTra(Double tienBnTra) {
//		this.tienBnTra = tienBnTra;
//	}
//
//	public Double getTienBnTra() {
//		return tienBnTra;
//	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		ThanhToanPhongCapCuuAction.logger = logger;
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

	public Double getPTTTTrongDM() {
		return pTTTTrongDM;
	}

	public void setPTTTTrongDM(Double trongDM) {
		pTTTTrongDM = trongDM;
	}

	public Double getPTTTNDM() {
		return pTTTNDM;
	}

	public void setPTTTNDM(Double ptttndm) {
		pTTTNDM = ptttndm;
	}

	public Double getNSKhongThu() {
		return nSKhongThu;
	}

	public void setNSKhongThu(Double khongThu) {
		nSKhongThu = khongThu;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public DtDmNhanVien getNhanVienThungan() {
		return nhanVienThungan;
	}

	public void setNhanVienThungan(DtDmNhanVien nhanVienThungan) {
		this.nhanVienThungan = nhanVienThungan;
	}

	public DtDmCum getCum() {
		return cum;
	}

	public void setCum(DtDmCum cum) {
		this.cum = cum;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}


	public String getFormTitle() {
		return formTitle;
	}


	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}


	public List<ClsKham> getListCLSKham() {
		return listCLSKham;
	}


	public void setListCLSKham(List<ClsKham> listCLSKham) {
		this.listCLSKham = listCLSKham;
	}


	public List<ThuocPhongKham> getListTPK() {
		return listTPK;
	}


	public void setListTPK(List<ThuocPhongKham> listTPK) {
		this.listTPK = listTPK;
	}
	
}
