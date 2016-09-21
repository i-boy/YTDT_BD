package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

// MYSQL DB - QUERY
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankBackupDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.HsThtoankBackup;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.BenhNhanTrungTheBhytDTO;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmXa;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;


@Scope(SESSION)
@Name("B110_Dangkykhambenh")
@Synchronized(timeout = 6000000)
public class B110_DangKyKhamBenhAction implements Serializable {

	
	private static final long serialVersionUID = 4201303779918089532L;
	private static Logger log = Logger.getLogger(B110_DangKyKhamBenhAction.class);
	//private static final String urlMySQL = "jdbc:mysql://10.0.99.99:3306/healthonline_db";
	private static final String urlMySQL = IConstantsRes.DK_ONLINE_DB_URL;
	private static final String userName = IConstantsRes.DK_ONLINE_DB_USER;
	private static final String password = IConstantsRes.DK_ONLINE_DB_PASS;
	Connection conn;
	
	private TiepDon tiepdon;
	private String gioTD = "";
	private String ngayTD = "";
	private String gioi = "1";	
	private String giatri1 = "";
	private String giatri2 = "";
	private String giatri3 = "";
	private String giatri4 = "";
	private String moc1 = "";
	private String moc2 = "";
	private String moc3 = "";
	private String ngaysinh = "";
	private String maBacSiThamKham = "";
	private String hosoDaTT = "false";
	private List<BenhNhanTrungTheBhytDTO> listBN; 
	private boolean showListBn;
	private boolean lockDoituong;
	private String doituongHientai;
	//Phan thong tin dang ky online
	private String maTD_online = "";
	private String ngaygioDK = "";
	private String ngayhientai = "";
	private String listMaTinhBhyt;
	private String hid_maTD = "";
	private String hid_maBN = "";
	private String hid_mess = "";
	@In(required = false)
	@Out(required = false)
	Identity identity;


	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
    private YteLog yteLog ;
    private String listDataLog = "";
	
	
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strDKyKhamBenh;
	@Factory("strDKyKhamBenh")
	public void init() {
		log.info(" init() #####=====#####===== strDKyKhamBenh = :" + strDKyKhamBenh);
		resetValue();		
		//return "/B1_Tiepdon/B110_Dangkykhambenh.xhtml";
	}
	
	
	public void resetValue() {		
		tiepdon = new TiepDon();
		SetInforUtil.setInforIfNullForTiepDon(tiepdon);
		
		BenhNhan benhnhan = new BenhNhan();		
		SetInforUtil.setInforIfNullForBN(benhnhan);
		
		tiepdon.setBenhnhanMa(benhnhan);			
		
		gioTD = Utils.getGioPhut(new Date());
		ngayTD = Utils.getCurrentDate();
		gioi = "1"; // Defaut la Nam
		
		giatri1 = "";
		giatri2 = "";
		giatri3 = "";
		giatri4 = "";
		moc1 = "";
		moc2 = "";
		moc3 = "";
		ngaysinh = "";
		maBacSiThamKham = "";
		hosoDaTT = "false";
		listBN = new ArrayList<BenhNhanTrungTheBhytDTO>();
		showListBn = false;
		lockDoituong = false;
		doituongHientai = "";
		maTD_online = "";
		ngaygioDK = "";
		ngayhientai = Utils.getCurrentDate();
		// Lay danh muc tinh de tao listMaTinhBhyt
		List<DmTinh> listDmTinh = DieuTriUtilDelegate.getInstance().findAll("DmTinh");		
		listMaTinhBhyt = "";
		for(DmTinh each : listDmTinh) {
			listMaTinhBhyt += each.getDmtinhBHYT() + ",";
		}
		hid_maTD = "";
		hid_maBN = "";
		hid_mess = "";
		strDKyKhamBenh = "";
	}
	public void loadTiepdon() {
		String maTD = tiepdon.getTiepdonMa();
		TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
		tiepdon = tdDelegate.find(tiepdon.getTiepdonMa());
		if (tiepdon == null) {
			resetValue();
			FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + maTD);
		// phuc.lc 24-10/2011 : Neu thong tin ban kham la Null, thi co the tiep don do cap nhat thong tin nhap vien noi tru tao ra
		// khong cho chinh sua o ngoai tru
		} else if (tiepdon.getTiepdonBankham() == null) {
			maTD = tiepdon.getTiepdonMa();
			Hsba hsbaTmp = HsbaDelegate.getInstance().findByTiepDonMa(maTD);
			if (hsbaTmp != null) {
				FacesMessages.instance().add(IConstantsRes.TIEP_DON_DUOC_TAO_TU_NOI_TRU, maTD, hsbaTmp.getHsbaSovaovien());
			} else {
				ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
				ThamKham tkTemp = tkDelegate.findThamKhamByMaTiepDonFirst(maTD);
				if (tkTemp != null && tkTemp.getThamkhamBankham() != null) {
					tiepdon.setTiepdonBankham(tkTemp.getThamkhamBankham());
					tdDelegate.edit(tiepdon);
					log.info("loadTiepdon #####=====#####===== Cap nhat lai ban kham cho tiepdon !");
					FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS + " " + maTD);
				}
			}
			resetValue();
		} else {
			listBN = new ArrayList<BenhNhanTrungTheBhytDTO>();
			showListBn = false;
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			gioTD = Utils.getGioPhut(tiepdon.getTiepdonNgaygio());
			ngayTD = Utils.reFactorString(tiepdon.getTiepdonNgaygio());
			BenhNhan benhnhan = tiepdon.getBenhnhanMa();
			if(benhnhan == null) {
				benhnhan = new BenhNhan();
			}
			SetInforUtil.setInforIfNullForBN(benhnhan);			
			tiepdon.setBenhnhanMa(benhnhan);
			if (tiepdon.getBenhnhanMa() != null) {
				gioi = "" + tiepdon.getBenhnhanMa().getDmgtMaso().getDmgtMa();
				if (tiepdon.getBenhnhanMa().getBenhnhanNgaysinh() != null) {
					ngaysinh = df.format(tiepdon.getBenhnhanMa().getBenhnhanNgaysinh());
				}else {
					ngaysinh = "";
				}
			} 
			giatri1 = Utils.reFactorString(tiepdon.getTiepdonGiatri1());
			giatri2 = Utils.reFactorString(tiepdon.getTiepdonGiatri2());
			giatri3 = Utils.reFactorString(tiepdon.getTiepdonGiatri3());
			giatri4 = Utils.reFactorString(tiepdon.getTiepdonGiatri4());
			moc1 = Utils.reFactorString(tiepdon.getTiepdonMoc1());
			moc2 = Utils.reFactorString(tiepdon.getTiepdonMoc2());
			moc3 = Utils.reFactorString(tiepdon.getTiepdonMoc3());
			//ThamKhamDelegate tkDel = ThamKhamDelegate.getInstance();
			/*ThamKham thamkham = tkDel.findByMaTiepDon(tiepdon.getTiepdonMa());			
			if (thamkham != null) {
				maBacSiThamKham = (thamkham.getThamkhamBacsi() == null ? "" : "" + thamkham.getThamkhamBacsi().getDtdmnhanvienMaso());
			} else {
				maBacSiThamKham = "";
			}
			*/
			// Kiem tra neu co nhieu tham kham thi khong cho doi ban kham
			checkBacsiThamkham(tiepdon.getTiepdonMa());
			
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
			SetInforUtil.setInforIfNullForTiepDon(tiepdon);
			lockDoituong = false;
			doituongHientai = tiepdon.getDoituongMa().getDmdoituongMa();
			//Kiem tra CLS va Thuoc, neu da co su dung thi khong cho thay doi doi tuong kham benh
			List<ClsKham> listClsKham = ClsKhamDelegate.getInstance().findByTiepdonma(tiepdon.getTiepdonMa());
			for (ClsKham cls : listClsKham) {
				if(cls.getClskhamLoai().equalsIgnoreCase("KH")) {
					listClsKham.remove(cls);
					break;
				}
			}
			List<ThuocPhongKham> listTpk = ThuocPhongKhamDelegate.getInstance().find2LoaiByMaTiepDon(tiepdon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);		
			if((listClsKham != null && listClsKham.size() > 0) || (listTpk != null && listTpk.size() > 0)) {
				lockDoituong = true;
			}
		}
		hid_maTD = "";
		hid_maBN = "";
		hid_mess = "";
	}
	public void loadBenhNhan() {
		
		String bnMa = tiepdon.getBenhnhanMa().getBenhnhanMa();
		BenhNhan bn = BenhNhanDelegate.getInstance().find(bnMa);
		if(bn != null) {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			gioi = "" + bn.getDmgtMaso().getDmgtMa();
			if (bn.getBenhnhanNgaysinh() != null) {
				ngaysinh = df.format(bn.getBenhnhanNgaysinh());
			}else {
				ngaysinh = "";
			}
			tiepdon.setBenhnhanMa(bn);						
		} else {
			tiepdon.setBenhnhanMa(new BenhNhan());
			SetInforUtil.setInforIfNullForBN(tiepdon.getBenhnhanMa());
			ngaysinh = "";
			gioi = "1";
			FacesMessages.instance().add(IConstantsRes.NOT_FOUND_TIEPDON ,"m\u00E3 b\u1EC7nh nh\u00E2n", bnMa);
		}
		
	}
	private void tinhToanChoHSTKKham(TiepDon td, HsThtoank hsttk){
		HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(td);
		hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
		Utils.setInforForHsThToan(hsttk);	
	}
	public void ghinhan() {
		yteLog = new YteLog();
		listDataLog = "";
		String logBHYTT = "";
		SimpleDateFormat dateFormatFull = new SimpleDateFormat(Utils.FORMAT_DATE_TIME_HOUR_FIRST);
		//log.info("Begin ghinhan, maTD = " + tiepdon.getTiepdonMa() +  ", identity.getUsername():"+identity.getUsername());
		
		//phuc.lc 28/10/2010 : Khi database gears khong dong bo voi server
		// co the se xay ra truong hop cac danh muc chon duoc tren giao dien nhung luu xuong lai bi null		
		// duoi day la kiem tra truong hop tinh cap the BHYT va noi dang ky KCB bi null (bug 4064)
		DieuTriUtilDelegate delegate = DieuTriUtilDelegate.getInstance();
		
		// 20120601 bao.ttc: Kiem tra lai ban kham
		if(tiepdon.getTiepdonBankham().getDtdmbankhamMa() != null) {
			tiepdon.setTiepdonBankham((DtDmBanKham) delegate.findByMa(tiepdon.getTiepdonBankham().getDtdmbankhamMa(), "DtDmBanKham", "dtdmbankhamMa"));
			listDataLog+= " BK: "+ ( tiepdon.getTiepdonBankham() == null ? "NULL" : tiepdon.getTiepdonBankham().getDtdmbankhamMa() );
		} else {
			tiepdon.setTiepdonBankham(null);
		}
		
		if (tiepdon.getTiepdonBankham() == null) {
			FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, IConstantsRes.BAN_KHAM);
			return;
		}
		
		// Kiem tra tinh cap BHYT
		if(tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH")) {
			logBHYTT += tiepdon.getTiepdonSothebh();
			if (tiepdon.getTinhbhytMa(true).getDmtinhMaso() != null) {
				DmTinh tinhBhyt = (DmTinh) delegate.findByMaSo(tiepdon.getTinhbhytMa(true).getDmtinhMaso(), "DmTinh", "dmtinhMaso");
				if(tinhBhyt == null) {
					FacesMessages.instance().add(IConstantsRes.NOT_FOUND, IConstantsRes.TINH_CAP_BHYT, tiepdon.getTinhbhytMa(true).getDmtinhBHYT());
					return;
				} else {
					tiepdon.setTinhbhytMa(tinhBhyt);
					logBHYTT+=" Tinh: "+ tiepdon.getTinhbhytMa(true).getDmtinhMa();
				}
			} else {
				FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, IConstantsRes.TINH_CAP_BHYT);
				return;
			}
			// Kiem tra noi DK Kham chua benh		
			if (tiepdon.getKcbbhytMa(true).getDmbenhvienMa() != null) {
				DmBenhVien noiKCB = (DmBenhVien) delegate.findByMa(tiepdon.getKcbbhytMa(true).getDmbenhvienMa(), "DmBenhVien", "dmbenhvienMa");
				if(noiKCB == null) {
					FacesMessages.instance().add(IConstantsRes.NOT_FOUND, IConstantsRes.NOI_DK_KCB, tiepdon.getKcbbhytMa(true).getDmbenhvienMa());
					return;
				} else {
					tiepdon.setKcbbhytMa(noiKCB);
					logBHYTT+=" KCB: "+ tiepdon.getKcbbhytMa(true).getDmbenhvienMa();
				}
			} else {
				FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, IConstantsRes.NOI_DK_KCB);
				return;
			}
			
			// 20120531 bao.ttc: da kiem tra o tren, ko can kiem tra lai
//			if(tiepdon.getTinhbhytMa().getDmtinhBHYT() != null && !"".equals(tiepdon.getTinhbhytMa().getDmtinhBHYT())) {
//				tiepdon.setTinhbhytMa((DmTinh) delegate.findByMaSo(tiepdon.getTinhbhytMa().getDmtinhMaso(), "DmTinh", "dmtinhMaso"));
//				listDataLog+= " Tinh BHYT: "+(tiepdon.getTinhbhytMa() == null? "NULL" : tiepdon.getTinhbhytMa(true).getDmtinhBHYT());
//			}else {
//				tiepdon.setTinhbhytMa(null);
//				listDataLog+= " Tinh BHYT: NULL";
//			}
//			
//			if(tiepdon.getKcbbhytMa().getDmbenhvienMa() != null && !"".equals(tiepdon.getKcbbhytMa().getDmbenhvienMa())) {
//				tiepdon.setKcbbhytMa((DmBenhVien) delegate.findByMa(tiepdon.getKcbbhytMa().getDmbenhvienMa(), "DmBenhVien", "dmbenhvienMa"));
//				listDataLog+= " KCB BHYT: "+ (tiepdon.getKcbbhytMa() == null ? "NULL" : tiepdon.getKcbbhytMa(true).getDmbenhvienMa());
//			}else {
//				tiepdon.setKcbbhytMa(null);
//				listDataLog+= " KCB BHYT: NULL";
//			}
			
			if( tiepdon.getKhoibhytMa().getDtdmkhoibhytMa() != null && !"".equals(tiepdon.getKhoibhytMa().getDtdmkhoibhytMa())) {
				tiepdon.setKhoibhytMa((DtDmKhoiBhyt) delegate.findByMa(tiepdon.getKhoibhytMa().getDtdmkhoibhytMa(), "DtDmKhoiBhyt", "dtdmkhoibhytMa"));
				logBHYTT += " Khoi: " + (tiepdon.getKhoibhytMa() == null? "NULL" : tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMa());
			} else {
				tiepdon.setKhoibhytMa(null);
				logBHYTT += " Khoi: NULL";
			}
			
		}
		//phuc.lc 28/10/2010 : End
		
		//DtDmNhanVien nhanvienThuNgan = (DtDmNhanVien)delegate.findByMa(IConstantsRes.THU_NGAN_MA, "DtDmNhanVien", "dtdmnhanvienMa");		
		tiepdon.setTiepdonCum(IConstantsRes.CUM_TIEP_DON);
		
		tiepdon.getBenhnhanMa().setDmgtMaso((DmGioiTinh) delegate.findByMa(gioi, "DmGioiTinh", "dmgtMa"));
		if(tiepdon.getBenhnhanMa().getDantocMa().getDmdantocMa() != null && !"".equals(tiepdon.getBenhnhanMa().getDantocMa().getDmdantocMa())) {
			tiepdon.getBenhnhanMa().setDantocMa((DmDanToc) delegate.findByMa(tiepdon.getBenhnhanMa().getDantocMa().getDmdantocMa(), "DmDanToc", "dmdantocMa"));
			listDataLog+= " DT: "+ tiepdon.getBenhnhanMa(true).getDantocMa().getDmdantocMa();
		} else {
			tiepdon.getBenhnhanMa().setDantocMa(null);
			listDataLog+= " DT: NULL ";
		}
		if(tiepdon.getBenhnhanMa().getTinhMa().getDmtinhMa() != null && !"".equals(tiepdon.getBenhnhanMa().getTinhMa().getDmtinhMa())) {
			tiepdon.getBenhnhanMa().setTinhMa((DmTinh) delegate.findByMa(tiepdon.getBenhnhanMa().getTinhMa().getDmtinhMa(), "DmTinh", "dmtinhMa"));
			listDataLog+= " Tinh: "+(tiepdon.getBenhnhanMa().getTinhMa() == null ? "NULL" : tiepdon.getBenhnhanMa(true).getTinhMa(true).getDmtinhMa());
		}else {
			tiepdon.getBenhnhanMa().setTinhMa(null);
			listDataLog+= " Tinh: NULL";
		}
		if(tiepdon.getBenhnhanMa().getHuyenMa().getDmhuyenMa() != null && !"".equals(tiepdon.getBenhnhanMa().getHuyenMa().getDmhuyenMa())) {
			tiepdon.getBenhnhanMa().setHuyenMa((DmHuyen) delegate.findByMa(tiepdon.getBenhnhanMa().getHuyenMa().getDmhuyenMa(), "DmHuyen", "dmhuyenMa"));
			listDataLog+= " Huyen: "+ (tiepdon.getBenhnhanMa().getHuyenMa() == null ? "NULL" :  tiepdon.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenMa());
		}else {
			tiepdon.getBenhnhanMa().setHuyenMa(null);
			listDataLog+= " Huyen: NULL";
		}
		if(tiepdon.getBenhnhanMa().getXaMa().getDmxaMa() != null && !"".equals(tiepdon.getBenhnhanMa().getXaMa().getDmxaMa())) {
			tiepdon.getBenhnhanMa().setXaMa((DmXa) delegate.findByMa(tiepdon.getBenhnhanMa().getXaMa().getDmxaMa(), "DmXa", "dmxaMa"));
			listDataLog+= " Xa: "+ (tiepdon.getBenhnhanMa().getXaMa() == null ? "NULL" : tiepdon.getBenhnhanMa(true).getXaMa(true).getDmxaMa());
		}else {
			tiepdon.getBenhnhanMa().setXaMa(null);
			listDataLog+= " Xa: NULL";
		}
		tiepdon.getBenhnhanMa().setBenhnhanNghe(null);		
		if(tiepdon.getDoituongMa().getDmdoituongMa() != null && !"".equals(tiepdon.getDoituongMa().getDmdoituongMa())) {
			tiepdon.setDoituongMa((DmDoiTuong) delegate.findByMa(tiepdon.getDoituongMa().getDmdoituongMa(), "DmDoiTuong", "dmdoituongMa"));
			listDataLog+= " Doi Tuong: "+(tiepdon.getDoituongMa() == null ? "NULL" : tiepdon.getDoituongMa(true).getDmdoituongMa());
		}else {
			tiepdon.setDoituongMa(null);
			listDataLog+= " Doi Tuong: NULL";
		}
		
		/*tiepdon.setTainanMa(null);
		tiepdon.setDmptgtnMaso(null);
		tiepdon.setTiepdonDonvigoi(null);
		tiepdon.setTiepdonMachdoanb0(null);
		tiepdon.setTiepdonTuvong(null);
		tiepdon.setTiepdonChvien(null);
		tiepdon.setTiepdonBschuyen(null);
		tiepdon.setTiepdonLydochvi(null);
		tiepdon.setTiepdonChkhoa(null);
		tiepdon.setDiadiemMa(null);
		*/
		
		tiepdon.setTiepdonBacsi(null);
		tiepdon.setDiadiemMa(null);
		
		if (gioTD != null && !gioTD.equals("") && ngayTD != null && !ngayTD.equals("")) {
			Calendar ngaygiotiepdon = Utils.getDBDate(gioTD, ngayTD);
			if (ngaygiotiepdon != null){
				tiepdon.setTiepdonNgaygio(ngaygiotiepdon.getTime());
				listDataLog+= " Gio TD: "+ dateFormatFull.format(tiepdon.getTiepdonNgaygio());
			} else {
				tiepdon.setTiepdonNgaygio(new Date()); // 20110409 bao.ttc: check lai de dam bao truong TiepdonNgaygio NOT NULL
				listDataLog+= " Gio TD: New "+ dateFormatFull.format(tiepdon.getTiepdonNgaygio());
			}
			
		} else {
			tiepdon.setTiepdonNgaygio(new Date());
			listDataLog+= " Gio TD: New "+ dateFormatFull.format(tiepdon.getTiepdonNgaygio());
		}
		
		// phuc.lc Fix bug 3995
		DtDmNhanVien nhanvien =  DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername());
		tiepdon.setTiepdonNhanviencn(nhanvien);
		//listDataLog += " NV: "+ (tiepdon.getTiepdonNhanviencn() == null ? "NULL" : tiepdon.getTiepdonNhanviencn(true).getDtdmnhanvienMa());
		tiepdon.setTiepdonNgaygiocn(new Date());
		
		if(tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH") ){
			// Cap nhat tuyen BH
					
			Short tuyenBH = new Short("1");
			// neu co giay chuyen vien thi set Tuyen = 1, khong thi giu nguyen Tuyen set o giao dien
			if (tiepdon.getTiepdonCoGiayGioiThieu() != null && tiepdon.getTiepdonCoGiayGioiThieu()){
				tuyenBH = new Short("1");
				
			} else if(tiepdon.getKcbbhytMa().getDmbenhvienMa().equalsIgnoreCase(IConstantsRes.MA_BENH_VIEN)) {
				tuyenBH = new Short("1");
			
			} else if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("TINH")){
	
				if(tiepdon.getKcbbhytMa().getDmbenhvienMa().startsWith(IConstantsRes.TINH_BHYT_DEFAULT)) {
					tuyenBH = new Short("2");
				} else {
					tuyenBH = new Short("3");				
				}
				
			} else if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("HUYEN")){
				if (tiepdon.getKcbbhytMa().getDmhuyenMaso() != null 
						&& tiepdon.getKcbbhytMa().getDmhuyenMaso(true).getDmhuyenMaso().toString().equals(IConstantsRes.MASO_HUYEN_TRIEN_KHAI)){
					tuyenBH = new Short("2");
				} else {
					tuyenBH = new Short("3");
				}
			} else {
				tuyenBH = new Short("3");
			}
			tiepdon.setTiepdonTuyen(tuyenBH);
			logBHYTT += " Tuyen BH: " + tiepdon.getTiepdonTuyen();
		}
		
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			tiepdon.getBenhnhanMa().setBenhnhanNgaysinh(null);
			if(ngaysinh.trim().length() > 0) {
				tiepdon.getBenhnhanMa().setBenhnhanNgaysinh(df.parse(ngaysinh));
				listDataLog+= " Ngay Sinh: " + df.format(tiepdon.getBenhnhanMa().getBenhnhanNgaysinh());
			} 
			
			tiepdon.setTiepdonGiatri1(null);
			if(giatri1.trim().length() > 0) {
				tiepdon.setTiepdonGiatri1(df.parse(giatri1));
				logBHYTT+= " GT1: "+ df.format(tiepdon.getTiepdonGiatri1());
			}
			tiepdon.setTiepdonGiatri2(null);
			if(giatri2.trim().length() > 0) {
				tiepdon.setTiepdonGiatri2(df.parse(giatri2));
				logBHYTT+= " GT2: "+ df.format(tiepdon.getTiepdonGiatri2());
			}
			tiepdon.setTiepdonGiatri3(null);
			if(giatri3.trim().length() > 0) {
				tiepdon.setTiepdonGiatri3(df.parse(giatri3));
				logBHYTT+= " GT3: "+ df.format(tiepdon.getTiepdonGiatri3());
			}
			tiepdon.setTiepdonGiatri4(null);
			if(giatri4.trim().length() > 0) {
				tiepdon.setTiepdonGiatri4(df.parse(giatri4));
				logBHYTT+= " GT4: "+ df.format(tiepdon.getTiepdonGiatri4());
			}
			tiepdon.setTiepdonMoc1(null);
			if(moc1.trim().length() > 0) {
				tiepdon.setTiepdonMoc1(df.parse(moc1));
				logBHYTT+= " Moc1: "+ df.format(tiepdon.getTiepdonMoc1());
			}
			tiepdon.setTiepdonMoc2(null);
			if(moc1.trim().length() > 0) {
				tiepdon.setTiepdonMoc2(df.parse(moc2));
				logBHYTT+= " Moc2: "+ df.format(tiepdon.getTiepdonMoc2());
			}
			tiepdon.setTiepdonMoc3(null);
			if(moc3.trim().length() > 0) {
				tiepdon.setTiepdonMoc3(df.parse(moc3));
				logBHYTT+= " Moc3: "+ df.format(tiepdon.getTiepdonMoc3());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		// Cap nhat cls kham cho truong hop thay doi doi tuong kham benh
		if((!tiepdon.getTiepdonMa().equals("")) && (! doituongHientai.equals("")) && (!doituongHientai.equals(tiepdon.getDoituongMa().getDmdoituongMa()))) {
			listDataLog+= "Chuyen Doi Tuong: "+ doituongHientai + " --> "+tiepdon.getDoituongMa(true).getDmdoituongMa(); 
			ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
			List<ClsKham> listClsKham = clsKhamDel.findByTiepdonma(tiepdon.getTiepdonMa());
			for (ClsKham cls : listClsKham) {
				if(cls.getClskhamLoai().equalsIgnoreCase("KH")) {
					try{
						if (tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH")){
							SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
							Date ngayChiDinhCls = formatter.parse(formatter.format(cls.getClskhamNgaygio()));
							Date ngayBatDauBh = (tiepdon.getTiepdonGiatri3() == null ? tiepdon.getTiepdonGiatri1() : tiepdon.getTiepdonGiatri3());
							Date ngayHetHanBh = (tiepdon.getTiepdonGiatri4() == null ? tiepdon.getTiepdonGiatri2() : tiepdon.getTiepdonGiatri4());
							
							if(ngayBatDauBh != null) ngayBatDauBh = formatter.parse(formatter.format(ngayBatDauBh));
							if(ngayHetHanBh != null) ngayHetHanBh = formatter.parse(formatter.format(ngayHetHanBh));
							if (cls.getClskhamMien()){
								// CLS mien phi ==> lay gia mien phi
								cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp()); 
							} else if ((cls.getClskhamNdm() != null && cls.getClskhamNdm().booleanValue() == true)
									|| ngayBatDauBh == null || ngayHetHanBh == null 
									|| ngayChiDinhCls.before(ngayBatDauBh) || ngayChiDinhCls.after(ngayHetHanBh)){
								// CLS ngoai danh muc hoac het han bao hiem ==> lay gia thuong
								cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongia());
							} else {
								// Cac truong hop con lai ==> lay gia bao hiem
								cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiabh());
							}
						} else if (tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("MP")) {
							// Doi tuong mien phi neu khong yeu cau thi luon luon lay gia mien phi
							cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp());
						} else if (tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("TP")) {
							if (cls.getClskhamMahang().getDtdmclsbgMien()){
								// CLS mien phi ==> lay gia mien phi
								cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp()); 
							} else {
								cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongia());
							}
						}
											
						cls.setClskhamDatt(tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("TP"));
						clsKhamDel.edit(cls);
						break;
					} catch(Exception e) {}
				}
			}
			
		}
		RemoveUtil.removeAllNullFromTiepDon(tiepdon);
		String maTD = TiepDonDelegate.getInstance().createTiepDon(tiepdon);
		//log.info("After createTiepDon, maTD = " + maTD);
		yteLog.setObjectId(maTD);
		if (!("".equals(maTD))){
			
				tiepdon.setTiepdonMa(maTD);
				HsThtoank hsttk = new HsThtoank();
				hsttk.setTiepdonMa(tiepdon);			
				tinhToanChoHSTKKham(tiepdon,hsttk);
				// phuc.lc 17/10/2011 : Fix bug 4069 : Doi voi benh nhan thu phi thi luu thong tin thanh toan tien kham vao ho so backup
				HsThtoankBackup hsBK = new HsThtoankBackup();
				// Kiem tra da co hs_thtoank_backup chua, neu da co thi cap nhat, neu chua thi them moi
				HsThtoankBackupDelegate hsBKDel = HsThtoankBackupDelegate.getInstance();
				//HsThtoankBackup hsBKTemp = hsBKDel.findBytiepdonMa(hsBK.getTiepdonMa().getTiepdonMa(), 0);
				HsThtoankBackup hsBKTemp = hsBKDel.findBytiepdonMa(maTD, 0);
				if(tiepdon.getDoituongMa().getDmdoituongMa().equals("TP")) {
					// Doi voi benh nhan thu phi, ngay khi tiep don phai tra tien cong kham
					// do do phai luu thong tin thanh toan tien kham vao bang hs_thtoank_backup
					
					// Copy thong tin tu hsttk vao HsThtoankBackup				
					hsBK.setHsthtoankBhxh(hsttk.getHsthtoankBhxh());
					hsBK.setHsthtoankBhyt(hsttk.getHsthtoankBhyt());
					//hsBK.setHsthtoankBienlai(hsttk.getHsthtoankBienlai());
					hsBK.setHsthtoankBienlai(tiepdon.getTiepdonBienlai());
					
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
					//hsBK.setHsthtoankKyhieu(hsttk.getHsthtoankKyhieu());
					hsBK.setHsthtoankKyhieu(tiepdon.getTiepdonKyhieu());
					
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
					//hsBK.setHsthtoankQuyen(hsttk.getHsthtoankQuyen());
					hsBK.setHsthtoankQuyen(tiepdon.getTiepdonQuyen());
					
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
					hsBK.setHsthtoankNgaygiott(tiepdon.getTiepdonNgaygio());
					
					if (hsBKTemp != null) {
						hsBK.setHsthtoankMa(hsBKTemp.getHsthtoankMa());
						hsBKDel.edit(hsBK);
					} else {
						hsBKDel.create(hsBK);
					}
				} else if (hsBKTemp != null){	// Neu khong phai la doi tuong Thu phi, thi xoa thong tin trong hs thanh toan backup
					hsBKDel.remove(hsBKTemp);
				}
				// phuc.lc 17/10/2011 : End fix bug 4069
		}
		
		if (!("".equals(maTD))) {
			//FacesMessages.instance().add(IConstantsRes.SUCCESS + ":" + maTD);
			FacesMessages.instance().add(IConstantsRes.dangkykb_thanhcong, tiepdon.getBenhnhanMa().getBenhnhanHoten() , maTD);
			
			resetValue();
		
		} else {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			listBN = new ArrayList<BenhNhanTrungTheBhytDTO>();
			showListBn = false;
		}
		yteLog.setUserId(identity.getUsername());
		yteLog.setForm("B110_Dangkykhambenh");
		yteLog.setLogString(logBHYTT);
		yteLog.setListData(listDataLog);
		yteLog.setDateTime(new Date());
		
		yteLogDele.create(yteLog);
		//log.info("End ghinhan");
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
	private void checkBacsiThamkham(String maTD) {
		ThamKhamDelegate tkDel = ThamKhamDelegate.getInstance();
		List<ThamKham> listTk = tkDel.findAllByMaTiepDon(maTD);
		maBacSiThamKham = "";
		if (listTk != null && listTk.size() > 1) {
			for (ThamKham eachTk : listTk) {
				if(maBacSiThamKham.trim().length() == 0) {
					maBacSiThamKham = (eachTk.getThamkhamBacsi() == null ? "" : "" + eachTk.getThamkhamBacsi().getDtdmnhanvienMaso());
				} else {
					break;
				}
			}
		}
	}
	private void checkDKTrongNgay(String sothe, String maTD, TiepDonDelegate tdDel, SimpleDateFormat df, boolean chopheptrungsothe) {
		// phuc.lc 27/10/2011 : Kiem tra tiep don nhieu lan trong ngay theo so the bao hiem
		hid_maTD = "";
		hid_maBN = "";
		hid_mess = "";
		try {
			Date dNgaytiepdon = df.parse(ngayTD);			
			df = new SimpleDateFormat(Utils.FORMAT_DATE_TIME_HOUR_FIRST);
			String sNgaygio = IConstantsRes.NGAY_GIO;
			String sBankham = IConstantsRes.BAN_KHAM.toUpperCase();
			if (sBankham.length() > 2) {
				sBankham = sBankham.substring(0,1) + sBankham.substring(1).toLowerCase() + ": ";
			}
			TiepDon tdTmp;
			if(!chopheptrungsothe) {				
				tdTmp = tdDel.testBHYTTrungTrongNgay(sothe, dNgaytiepdon);								
				if (tdTmp != null) {
					
					hid_maTD = tdTmp.getTiepdonMa();
					hid_maBN = tdTmp.getBenhnhanMa().getBenhnhanMa();
					
					hid_mess = IConstantsRes.DANGKY_BHYT_TRONGNGAY + hid_maTD + ". " + sNgaygio + df.format(tdTmp.getTiepdonNgaygio()) +". " + sBankham + " " +  tdTmp.getTiepdonBankham(true).getDtdmbankhamTen();
										
				} else {
					// truong hop nay xet tiep den ngay tai kham
					tdTmp = tdDel.getTiepDonWithSoTheBHYTLast(sothe);
					if (tdTmp != null){
						hid_maTD = tdTmp.getTiepdonMa();
						hid_maBN = tdTmp.getBenhnhanMa().getBenhnhanMa();
						ThamKhamDelegate tkDele = ThamKhamDelegate.getInstance();
						List<ThamKham> lstTK =  tkDele.findAllByMaTiepDon(tdTmp.getTiepdonMa());
						String ngayTaiKham = "";
						Date dNgayTaiKham = null;
						if (lstTK != null){
							for (ThamKham tk:lstTK){
								dNgayTaiKham = tk.getThamkhamNgaytaikham();
								if (dNgayTaiKham != null){
									ngayTaiKham = df.format(dNgayTaiKham);
									break;
								}
							}
						}
						
						// kiem tra ngay tai kham chua den han
						if (dNgayTaiKham != null){
							//test
							if (dNgayTaiKham.after(dNgaytiepdon)){
								// bn chua den han tai kham
								hid_mess = IConstantsRes.DANGKY_CHUATOINGAY_TAIKHAM + ngayTaiKham;
							}
						}
						
					}
				}
			} else { // truong hop cho phep trung so the 
				tdTmp = tdDel.find(maTD);
				if(tdTmp != null) {
					// Kiem tra ngay tiep don
					//dNgaytiepdon = Utils.removeHourFromDate(dNgaytiepdon);
					Date dNgayDaTiepdon = Utils.removeHourFromDate(tdTmp.getTiepdonNgaygio());					
					if (dNgayDaTiepdon != null) {
						hid_maTD = tdTmp.getTiepdonMa();
						hid_maBN = tdTmp.getBenhnhanMa().getBenhnhanMa();						
						if (dNgaytiepdon.compareTo(dNgayDaTiepdon) == 0) { // trung ngay tiep don													
							hid_mess = IConstantsRes.DANGKY_BHYT_TRONGNGAY + hid_maTD + ". " + sNgaygio + df.format(tdTmp.getTiepdonNgaygio()) +". " + sBankham + " " +  tdTmp.getTiepdonBankham(true).getDtdmbankhamTen();
						} else { // Kiem tra ngay tai kham
							ThamKhamDelegate tkDele = ThamKhamDelegate.getInstance();
							List<ThamKham> lstTK =  tkDele.findAllByMaTiepDon(tdTmp.getTiepdonMa());
							String ngayTaiKham = "";
							Date dNgayTaiKham = null;
							if (lstTK != null){
								for (ThamKham tk:lstTK){
									dNgayTaiKham = tk.getThamkhamNgaytaikham();
									if (dNgayTaiKham != null){
										ngayTaiKham = df.format(dNgayTaiKham);
										break;
									}
								}
							}
							
							// kiem tra ngay tai kham chua den han
							if (dNgayTaiKham != null){
								//test
								if (dNgayTaiKham.after(dNgaytiepdon)){
									// bn chua den han tai kham
									hid_mess = IConstantsRes.DANGKY_CHUATOINGAY_TAIKHAM + ngayTaiKham;
								}
							}
							
						}						
					}
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
/*
 * DuyenLP
 * Kiem tra so the BHYT nhap vao co dang dieu tri noi tru khong?
 * Chi kiem tra so the BHYT co HSBA ma chua co ngay ra vien
 */
	
	private void checkBHYTDieuTriNoiTru(String soTheBHYT){
		HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
//		hid_mess = "";
		List<Hsba> listHsba = hsbaDelegate.findBySoTheBHYT(soTheBHYT);
		if(listHsba != null && listHsba.size() > 0) {
			for (Hsba eachHsba : listHsba) {
				if(eachHsba.getHsbaNgaygiorav() == null) {
					hid_mess += " - " + IConstantsRes.TIEPDON_YTBH_DANGDIEUTRI_NOITRU;
					break;
				}
			}
		}
		
	}
	
	public void checkSoTheBHYT() {
		//log.info("Begin checkSoTheBHYT(), so the = " + tiepdon.getTiepdonSothebh() + ", ngayTD  = " + ngayTD);
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
		FacesMessages.instance().clear();
		TiepDonDelegate tdDel = TiepDonDelegate.getInstance();
		List<TiepDon> listTD = tdDel.findBySoTheBHYT(sothe);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//log.info("listTD.size() = " + listTD.size());
		hid_maTD = "";
		hid_maBN = "";
		hid_mess = "";
		if (listTD != null && listTD.size() > 0) {
			if (IConstantsRes.CHO_PHEP_TRUNG_SO_THE_BHYT.equals("NO")) {
				// Neu he thong khong cho phep trung so the, thi hien thi thong tin benh nhan cu va cho phep nhap tiep don moi
				TiepDon td = listTD.get(0);				
				
				
				if (td.getBenhnhanMa() != null) {
					gioi = "" + td.getBenhnhanMa().getDmgtMaso().getDmgtMa();
					if (td.getBenhnhanMa().getBenhnhanNgaysinh() != null) {
						ngaysinh = df.format(td.getBenhnhanMa().getBenhnhanNgaysinh());
					}else {
						ngaysinh = "";
					}
				}
				giatri1 = Utils.reFactorString(td.getTiepdonGiatri1());
				giatri2 = Utils.reFactorString(td.getTiepdonGiatri2());
				giatri3 = Utils.reFactorString(td.getTiepdonGiatri3());
				giatri4 = Utils.reFactorString(td.getTiepdonGiatri4());
				moc1 = Utils.reFactorString(td.getTiepdonMoc1());
				moc2 = Utils.reFactorString(td.getTiepdonMoc2());
				moc3 = Utils.reFactorString(td.getTiepdonMoc3());
				
				BenhNhan benhnhan = new BenhNhan();
				benhnhan = td.getBenhnhanMa();				
				SetInforUtil.setInforIfNullForBN(benhnhan);
				
				tiepdon = new TiepDon();
				tiepdon.setDoituongMa(td.getDoituongMa());
				tiepdon.setKhoibhytMa(td.getKhoibhytMa());
				tiepdon.setTinhbhytMa(td.getTinhbhytMa());
				tiepdon.setKcbbhytMa(td.getKcbbhytMa());
				tiepdon.setTiepdonMacoquan(td.getTiepdonMacoquan());
				SetInforUtil.setInforIfNullForTiepDon(tiepdon);
												
				tiepdon.setBenhnhanMa(benhnhan);
				
				//checkBacsiThamkham(tiepdon.getTiepdonMa());
				// phuc.lc 27/10/2011 : Kiem tra tiep don nhieu lan trong ngay theo so the bao hiem
				checkDKTrongNgay(sothe, "", tdDel, df, false);
				checkBHYTDieuTriNoiTru(sothe);
			} else {				
				// Neu cho phep trung so the, thi hien thi danh sach benh nhan trung so the de lay thong tin benh nhan cu hoac nhap benh nhan moi
				if(listBN == null) {
					listBN = new ArrayList<BenhNhanTrungTheBhytDTO>();
				} else {
					listBN.clear();
				}				
				String strNgayHientai = df.format(new Date());
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
							bn.setNoiDkKcb(eachTD.getKcbbhytMa() == null ? "" : eachTD.getKcbbhytMa().getDmbenhvienTen());							
							bn.setThoiHanThe((eachTD.getTiepdonGiatri1() == null ? "" : df.format(eachTD.getTiepdonGiatri1())) + " - " + (eachTD.getTiepdonGiatri2() == null ? "" : df.format(eachTD.getTiepdonGiatri2())));
							bn.setNgayTiepdon(eachTD.getTiepdonNgaygio() == null ? "" : df.format(eachTD.getTiepdonNgaygio()));
							bn.setTiepdonTrongNgay(bn.getNgayTiepdon().equals(strNgayHientai));							
							listBN.add(bn);
						}
					}
				}				
				if(listBN.size() > 0) {
					showListBn = true;
				}
				
			}
			FacesMessages.instance().add(IConstantsRes.SOTHEBHYT_DATONTAI, sothe);
		} else {
			if( !tiepdon.getTiepdonMa().equals("") || !maTD_online.equals("") ) {	// Truong hop thay doi doi tuong kham benh sang bao hiem			
				// 20111018 bao.ttc: TH maTD_online khac "" thi giu nguyen thong tin da load tren form
				
				// phuc.lc 25-10-2011 : Truong hop thay doi doi tuong da co tiep don thanh bao hiem,
				// thuc hien kiem tra da co nhieu tham kham hay chua
				// neu da co nhieu hon 1 tham kham thi khong cho thay doi ban kham	
				if(!tiepdon.getTiepdonMa().equals("")) {
					checkBacsiThamkham(tiepdon.getTiepdonMa());
				}
			} else {
				
				DmDoiTuong dmDT = tiepdon.getDoituongMa();
				DtDmKhoiBhyt khoiBHYT = tiepdon.getKhoibhytMa();
				resetValue();
				tiepdon.setDoituongMa(dmDT);
				tiepdon.setKhoibhytMa(khoiBHYT);				
			}
		}
		
	}
	public void layThongTinBnCu(int index) {
		//log.info("Begin layThongTinBnCu, index = " + index + ", ngayTD = " + ngayTD);
		TiepDonDelegate tdDel = TiepDonDelegate.getInstance();
		TiepDon td = TiepDonDelegate.getInstance().findBenhNhanByTiepdonMa(listBN.get(index).getTiepdonMa());
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if(td != null) {
			checkDKTrongNgay(td.getTiepdonSothebh(), td.getTiepdonMa(), tdDel, df, true);			
		}
		gioTD = Utils.getGioPhut(new Date());
		//ngayTD = Utils.getCurrentDate();
		if (td.getBenhnhanMa() != null) {
			gioi = "" + td.getBenhnhanMa().getDmgtMaso().getDmgtMa();
			if (td.getBenhnhanMa().getBenhnhanNgaysinh() != null) {
				ngaysinh = df.format(td.getBenhnhanMa().getBenhnhanNgaysinh());
			}else {
				ngaysinh = "";
			}
		}
		giatri1 = Utils.reFactorString(td.getTiepdonGiatri1());
		giatri2 = Utils.reFactorString(td.getTiepdonGiatri2());
		giatri3 = Utils.reFactorString(td.getTiepdonGiatri3());
		giatri4 = Utils.reFactorString(td.getTiepdonGiatri4());
		moc1 = Utils.reFactorString(td.getTiepdonMoc1());
		moc2 = Utils.reFactorString(td.getTiepdonMoc2());
		moc3 = Utils.reFactorString(td.getTiepdonMoc3());
		
		BenhNhan benhnhan = new BenhNhan();
		benhnhan = td.getBenhnhanMa();				
		SetInforUtil.setInforIfNullForBN(benhnhan);
		
		tiepdon = new TiepDon();
		tiepdon.setTiepdonSothebh(td.getTiepdonSothebh());
		tiepdon.setDoituongMa(td.getDoituongMa());
		tiepdon.setKhoibhytMa(td.getKhoibhytMa());
		tiepdon.setTinhbhytMa(td.getTinhbhytMa());
		tiepdon.setKcbbhytMa(td.getKcbbhytMa());
		tiepdon.setTiepdonMacoquan(td.getTiepdonMacoquan());
		SetInforUtil.setInforIfNullForTiepDon(tiepdon);
										
		tiepdon.setBenhnhanMa(benhnhan);
						
		FacesMessages.instance().clear();
		showListBn = false;		
	}
	public void nhapBnMoi() {				
		FacesMessages.instance().clear();
		tiepdon.getBenhnhanMa().setBenhnhanMa("");
		tiepdon.setTiepdonMa("");
		showListBn = false;
		//lockDoituong = false;
	}
	public void huyKham() {
		//log.info("Begin huyKham(), ma tiep don = " + tiepdon.getTiepdonMa());
		if (tiepdon.getTiepdonMa().trim().length() > 0) {
			//kiem tra co can lam sang hay ko - neu co thi ko cho xoa
			ClsKhamDelegate ClsKhamDel = ClsKhamDelegate.getInstance();
			List<ClsKham> list = ClsKhamDel.findByTiepdonMaVaLoaiClsKham(tiepdon.getTiepdonMa());
			if(list!=null && list.size()>0) {
				FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB);
				return;
			}
			
			//kiem tra co ho so benh an hay ko - neu co thi ko cho xoa
			HsbaDelegate hsbaDel = HsbaDelegate.getInstance();
			Hsba objHSBA = hsbaDel.findByTiepDonMa(tiepdon.getTiepdonMa());
			if(objHSBA!=null){
				FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_HSBA);
				return;
			}
			// kiem tra truoc co hay khong
			ThuocPhongKhamDelegate tpkDel = ThuocPhongKhamDelegate.getInstance();
			List<ThuocPhongKham> lstTPK = tpkDel.findByMaTiepDon(tiepdon.getTiepdonMa(), "1");

	        if (lstTPK != null && lstTPK.size() > 0) {
	        	FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB);
	            return;
	        }

	        lstTPK = tpkDel.findByMaTiepDon(tiepdon.getTiepdonMa(), "2");

	        if (lstTPK != null && lstTPK.size() > 0) {
	        	FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB);
	            return;
	        }

	        lstTPK = tpkDel.findByMaTiepDon(tiepdon.getTiepdonMa(), "3");

	        if (lstTPK != null && lstTPK.size() > 0) {
	        	FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB);
	            return;
	        }
	        TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
	        String maBN = tiepdon.getBenhnhanMa().getBenhnhanMa();	        
			String tdID = tdDelegate.delHuyKham(tiepdon);
			if(tdID.trim().length() > 0) {
				FacesMessages.instance().add(IConstantsRes.HUY_KHAM_THANH_CONG, maBN, tdID);
				resetValue();
			}
		} else {
			FacesMessages.instance().add(IConstantsRes.TIEP_DON_UNAVAILABLE);
		}
	}
	public TiepDon getTiepdon() {
		return tiepdon;
	}
	public void setTiepdon(TiepDon tiepdon) {
		this.tiepdon = tiepdon;
	}
	public String getGioTD() {
		return gioTD;
	}
	public void setGioTD(String gioTD) {
		this.gioTD = gioTD;
	}
	public String getNgayTD() {
		return ngayTD;
	}
	public void setNgayTD(String ngayTD) {
		this.ngayTD = ngayTD;
	}

	public String getGioi() {
		return gioi;
	}

	public void setGioi(String gioi) {
		this.gioi = gioi;
	}

	public String getGiatri1() {
		return giatri1;
	}

	public void setGiatri1(String giatri1) {
		this.giatri1 = giatri1;
	}

	public String getGiatri2() {
		return giatri2;
	}

	public void setGiatri2(String giatri2) {
		this.giatri2 = giatri2;
	}
	public String getGiatri3() {
		return giatri3;
	}

	public void setGiatri3(String giatri3) {
		this.giatri3 = giatri3;
	}

	public String getGiatri4() {
		return giatri4;
	}

	public void setGiatri4(String giatri4) {
		this.giatri4 = giatri4;
	}

	public String getMoc1() {
		return moc1;
	}

	public void setMoc1(String moc1) {
		this.moc1 = moc1;
	}

	public String getMoc2() {
		return moc2;
	}

	public void setMoc2(String moc2) {
		this.moc2 = moc2;
	}

	public String getMoc3() {
		return moc3;
	}

	public void setMoc3(String moc3) {
		this.moc3 = moc3;
	}
	
	public String getNgaysinh() {
		return ngaysinh;
	}


	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}


	public String getMaBacSiThamKham() {
		return maBacSiThamKham;
	}
	public void setMaBacSiThamKham(String maBacSiThamKham) {
		this.maBacSiThamKham = maBacSiThamKham;
	}


	public String getHosoDaTT() {
		return hosoDaTT;
	}


	public void setHosoDaTT(String hosoDaTT) {
		this.hosoDaTT = hosoDaTT;
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


public String getMaTD_online() {
		return maTD_online;
	}


	public void setMaTD_online(String maTDOnline) {
		maTD_online = maTDOnline;
	}


	public String getNgaygioDK() {
		return ngaygioDK;
	}


	public void setNgaygioDK(String ngaygioDK) {
		this.ngaygioDK = ngaygioDK;
	}
	

	public String getListMaTinhBhyt() {
		return listMaTinhBhyt;
	}


	public void setListMaTinhBhyt(String listMaTinhBhyt) {
		this.listMaTinhBhyt = listMaTinhBhyt;
	}
	

	public String getHid_maTD() {
		return hid_maTD;
	}


	public void setHid_maTD(String hidMaTD) {
		hid_maTD = hidMaTD;
	}


	public String getHid_maBN() {
		return hid_maBN;
	}


	public void setHid_maBN(String hidMaBN) {
		hid_maBN = hidMaBN;
	}


	public String getHid_mess() {
		return hid_mess;
	}


	public void setHid_mess(String hidMess) {
		hid_mess = hidMess;
	}


	public void search_maTDonline() {
		String maTD_online_temp = maTD_online;
		resetValue();
		maTD_online = maTD_online_temp;
		
		if (maTD_online.trim().length() > 0) {
			
			String tenTD_online = "";
			String tuoi_online = "";
			String donvituoi = "";
			String diachi_online = "";
			String maBH_online = "";
			String ngayTDDK = "";
			String gioTDDK = "";
			
			if(!urlMySQL.startsWith("jdbc")){
				//log.info("### Khong co server Dang ky online");
				resetValue();
				return;
			}
			
			if(maTD_online.length() < 5){
				int add_zero = 5 - maTD_online.length();
				for(int i =0; i < add_zero; i++ ){
					maTD_online = "0" + maTD_online;
				}
			}
				
			String query = "SELECT * FROM healthonline_db.regonline r WHERE r.regno LIKE " + "'%" + maTD_online + "'" + " order by r.regid desc";
			//log.info("### Query ### " + query);
			
			try{
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				conn = DriverManager.getConnection(urlMySQL, userName, password);
			
				ResultSet rs;
				
				try{
		            PreparedStatement stmt = conn.prepareStatement(query);
		            rs =  stmt.executeQuery();
		            
		            if(rs.first()){
		            	tenTD_online = rs.getString("pname");
		            	maTD_online = rs.getString("regno");
		            	tuoi_online = rs.getString("page");
		            	donvituoi = rs.getString("agetype");
		            	diachi_online = rs.getString("paddress");
		            	maBH_online = rs.getString("pcardno");
		            	gioi = rs.getString("psex");
		            	ngayTDDK = rs.getString("regdate");
		            	gioTDDK = rs.getString("regtime");
		            	
//		            	log.info("### SQL Query OK !! ### Ma: " + maTD_online
//		            			+ " <> Tuoi: " + tuoi_online + " <> DV Tuoi: " + donvituoi + " <> Dia chi: " + diachi_online
//		            			+ " <> Ma BH: " + maBH_online + " <> Ngay DK: " + ngayTDDK + " <> Gio DK: " + gioTDDK
//		            			+ " <> Gioi tinh: " + gioi );
		            	
		            } else{
		            	FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND);
		            	resetValue();
		            	//log.info("### SQL Query OK !! ### No result !! ");
		            }
		            
		            
				} catch(SQLException er){
					resetValue();
		            //log.info("### SQL Querry Error !! ### " + er.getMessage());
		        }
			
			} catch(SQLException er){
				resetValue();
				//log.info("### SQL Connection Error !! ### " + er.getMessage());
			}
			
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					//log.info("### SQL Connection Close Error !! ### " + e.getMessage());
				}
			}
			
			
			// Xu ly cho Don vi tuoi, Gioi tinh
			if(donvituoi.equals("DAY")){
				donvituoi = "3";
			} else if(donvituoi.equals("MONTH")){
				donvituoi = "2";
			} else {
				donvituoi = "1";
			}
			tiepdon.getBenhnhanMa().setBenhnhanDonvituoi(new Short(donvituoi));
			
			if(tenTD_online != null && !tenTD_online.equals("")){
				tiepdon.getBenhnhanMa().setBenhnhanHoten(tenTD_online.toUpperCase());
			}
			
			if(tuoi_online != null && !tuoi_online.equals("")){
				tiepdon.getBenhnhanMa().setBenhnhanTuoi(Integer.parseInt(tuoi_online));
			}
			
			if(diachi_online != null && !diachi_online.equals("")){
				tiepdon.getBenhnhanMa().setBenhnhanDiachi(diachi_online);
			}
			
			if(gioi == null || gioi.equals("")){
				gioi = "1";
			} else {
				gioi = "0";
			}
			
			// Xu ly cho ma BHYT
			if ( (maBH_online != null) && !("".equals(maBH_online)) ) {
				tiepdon.setTiepdonSothebh(maBH_online.toUpperCase());
				tiepdon.getDoituongMa().setDmdoituongMa("BH");			
			} else {
				tiepdon.getDoituongMa().setDmdoituongMa("TP");
			}
			
			
			// Xu ly cho ngay gio DK
			if ( (ngayTDDK != null) && !("".equals(ngayTDDK)) ) {
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat df_view = new SimpleDateFormat("dd/MM/yyyy");
				String today = df.format(new Date());
				
				try {
					Date ngayDK = df.parse(ngayTDDK);
					Date ngayHT = df.parse(today);
					ngaygioDK = df_view.format(ngayDK);
					
					if(ngayDK.before(ngayHT)){
						FacesMessages.instance().add(IConstantsRes.DANGKY_ONLINE_DENTRE, ngaygioDK);
					}
					if(ngayDK.after(ngayHT)){
						FacesMessages.instance().add(IConstantsRes.DANGKY_ONLINE_DENSOM, ngaygioDK);
					}
					
					if( gioTDDK != null && !("".equals(gioTDDK)) ){
						ngaygioDK = gioTDDK + ":00 " + IConstantsRes.NGAY + " " + ngaygioDK;
					}
					
	
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}	// Xu ly cho ngay gio DK -- END 
		}
		
    }
	public boolean checkKhamTrungNgay(String ngayTD) {
		//log.info("checkKhamTrungNgay, ngayTD = " + ngayTD);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date ngayTiepDon = df.parse(ngayTD);
			Date ngayHienTai = df.parse(df.format(new Date()));
			return ngayTiepDon.equals(ngayHienTai);
		}catch (Exception e) {			
		}
		return false;
	}
	@In(required=false)
	@Out(required=false)
	private String benhNhan_ma;
	
	@In(required=false)
	@Out(required=false)
	private String tiepDon_ma;
	public String xemLichSuKB(String maTD, String maBN) {
		//log.info("xemLichSuKB, maTD = " + maTD + ", maBN = " + maBN);
		tiepDon_ma = maTD;		
		benhNhan_ma = maBN;
		return "/B1_Tiepdon/B115_Lichsubenhnhan.xhtml";
	}
	
}


