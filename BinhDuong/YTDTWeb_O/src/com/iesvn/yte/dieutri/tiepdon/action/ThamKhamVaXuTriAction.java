package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.rmi.RemoteException;
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

import javax.xml.rpc.ServiceException;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.ThamKhamUtil;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B121_Thamkhamvaxutri")
@Synchronized(timeout = 6000000)
public class ThamKhamVaXuTriAction  implements Serializable {
	private ThamKham thamkham;
	
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	@In(required = false)
	@Out(required = false)
	Identity identity;

	@In(required = false)
	@Out(required = false)
	private String goToPhieuKhamBenhVaoVien;
	
	private static final long serialVersionUID = 10L;
	private String gioRa;
	private String ngayRa;
	//private Boolean chuyenVaoNT;
	private boolean bChuyenVaoNT;  // phuc.lc
	
	private Boolean incls = true; //bao.ttc
	private Boolean inToaThuocBK = true;

	private Boolean bThuThuatPhauThuat;
	private Boolean bSinh;
	private BenhNhan benhNhan;
	
	private TiepDon tiepdon;
	private String gioThamKham;
	private String ngayThamKham;
	private String tuoi;
	private String ngaySinh;
	private String gioVaoVien;
	private String ngayVaoVien;
	private String gioVaoKhoa;
	private String ngayVaoKhoa;
	private String gioRaVien;
	private String ngayRaVien;
	private String gioRaKhoa;
	private String ngayRaKhoa;
	private String gioTuVong;
	private String ngayTuVong;
	private String gioi;
	private String sotheTEorBHYT;
	
	private Integer maSoBacSi;
	private String maBacSi; 
	
	private String sNgayTaiKham;
	private Integer ngheNghiepMaSo;
	private String ngheNghiepMa;
	
	private boolean hasChKhoa;
	private boolean hasChBanKham;
	private boolean daThtoankham;
	private boolean notBNbaohiem;
	private boolean notDisplayKTQBV;
	
	DmBenhIcd phu1_temp = new DmBenhIcd(); // 20110701 bao.ttc: luu benh ICD Phu1 de in report (vi phai xoa tren giao dien)
	
	/*
	 * Thanh add 20/05 cho phan quang bao
	 */
	private TiepDon tiepDonForCall;
	private String tenBenhNhanForCall;
	//end
	
	
	@Out(required=false)
	@In(required=false)
	private String reportPathTD=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeTD=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintTD = null;
	
	private String chonloaiba;
	
	private int index=0;
	
	String userLogedIn = "";
	
	private String resultHidden ="";

	private static Logger log = Logger.getLogger(ThamKhamVaXuTriAction.class);
	
	
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String goToThamKhamVaXuTri;
	
	@In(required = false)
	@Out(required = false)
	private String goToPhieuKhamChuyenKhoa;
	
	
	@Out(required=false)
	@In(required=false)
	String fromMenu ;
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	@In(required = false)
	@Out(required = false)
	private String goToThamKham;
	
	@In(required = false)
	@Out(required = false)
	private String goToCLSThuThuat;
	

	@In(required = false)
	@Out(required = false)
	private String initB121_3_Xutrithuoctaibankham;
	
	
	@In(required = false)
	@Out(required = false)
	private String loaiToaThuocThamKhamVaXuTri ;
	
	
	@In(required = false)
	@Out(required = false)
	private String goToLapBANgoaiTru;
	
	@In(required = false)
	@Out(required = false)
	private String goToBANgoaiTruYHCT;
	
	@In(required = false)
	@Out(required = false)
	private String goToGiayChungNhan;
	

	@In(required = false)
	@Out(required = false)
	private String goToChuyenVienNguoiBenhCoTheBHYT;
	
	@In(required = false)
	@Out(required = false)
	private String goToThamKhamDieuTriNgoaiTru;
	
	@In(required = false)
	@Out(required = false)
	private String goToGiayChungNhanSucKhoe;
	
	@In(required = false)
	@Out(required = false)
	private String goToGiayChuyenVienSotXuatHuyet;
	

	@In(required = false)
	@Out(required = false)
	private String bacSiKCB;
	
	@In(required = false)
	@Out(required = false)
	private String portName;
	
	@In(required = false)
	@Out(required = false)
	private String goToLapBATuVongTruocKhiVaoVien;
	
	@In(required = false)
	@Out(required = false)
	private String goToGiayChungThuong;
	
	  public String getGoToGiayChungThuong() {
	        return goToGiayChungThuong;
	    }

	    public void setGoToGiayChungThuong(String goToGiayChungThuong) {
	        this.goToGiayChungThuong = goToGiayChungThuong;
	    }
	   

	 public String getGoToLapBATuvongtruockhivaovien() {
	        return goToLapBATuVongTruocKhiVaoVien;
	    }

	    public void setGoToLapBATuvongtruockhivaovien(String goToLapBATuVongTruocKhiVaoVien) {
	        this.goToLapBATuVongTruocKhiVaoVien = goToLapBATuVongTruocKhiVaoVien;
	    }
	    
	public String phieukhambenhvaovien() throws ServiceException, RemoteException {
		
		//log.info("*****Begin PhieuBenhVaoVien()");
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

		goToPhieuKhamBenhVaoVien = null;
		
		//log.info("*****End PhieuBenhVaoVien()");
		//TODO : GOTO SOME BA
		return "PhieuKhamBenhVaoVien";
	}

	
	public String getInitB121_3_Xutrithuoctaibankham() {
		return initB121_3_Xutrithuoctaibankham;
	}

	public void setInitB121_3_Xutrithuoctaibankham(String initB121_3_Xutrithuoctaibankham) {
		this.initB121_3_Xutrithuoctaibankham = initB121_3_Xutrithuoctaibankham;
	}

	public ThamKhamVaXuTriAction() {
		gioi = "";
	}
	
	// 20101216 bao.ttc: dua phan nay: comeFromTiepDon() -> vao init de tranh chay @begin nhieu lan
	//@Factory("goToThamKhamVaXuTri")	
	//@Begin(nested = true)
	public void comeFromTiepDon() {
		//log.info("Begin comeFromTiepDon(), maBanKhamOut = " + maBanKhamOut);
		if (maBanKhamOut != null && maBanKhamOut.trim().length() > 0) { 
			goToThamKhamVaXuTri = "gone";
			resetObjectValue();
			resetValue();
			
			thamkham.getThamkhamBankham(true).setDtdmbankhamMa(maBanKhamOut);
			thamkham.getTiepdonMa(true).setTiepdonMa(maTiepDonOut);
		}
		//log.info("End comeFromTiepDon()");
	}

	/*public String getGoToThamKhamVaXuTri() {
		return goToThamKhamVaXuTri;
	}


	public void setGoToThamKhamVaXuTri(String goToThamKhamVaXuTri) {
		this.goToThamKhamVaXuTri = goToThamKhamVaXuTri;
	}*/

	
	@Begin(join = true)
	public String init(String loaikham, String strPortName) { // loaikham == CCL || CCN or ''      bao.ttc
		// strPortName = COM1, ....
		//log.info("Begin init() ... loaikham = " + loaikham.toUpperCase() + " <> tiepdon = " + maTiepDonOut);
		resetObjectValue();
		resetValue();
		
		if ("CCL".equals(loaikham.toUpperCase()) || "CCN".equals(loaikham.toUpperCase())){
			thamkham.getThamkhamBankham(true).setDtdmbankhamMa(loaikham.toUpperCase());
		} 
		//else if (maBanKhamOut != null && maBanKhamOut.trim().length() > 0){
		//	thamkham.getThamkhamBankham(true).setDtdmbankhamMa(maBanKhamOut);
		//	thamkham.getTiepdonMa(true).setTiepdonMa(maTiepDonOut);
		//}
		//else {
		//	thamkham.getThamkhamBankham(true).setDtdmbankhamMa("");
		//}

		portName = strPortName;
		//log.info("***** portName *****: " + portName + ", maBanKham = " + thamkham.getThamkhamBankham(true).getDtdmbankhamMa());
		loaiToaThuocThamKhamVaXuTri = Utils.KE_TOA_QUAY_BENH_VIEN;
		
		initB121_3_Xutrithuoctaibankham = null;
		fromMenu = null;
		goToThamKhamVaXuTri = "";
		maTiepDonOut = "";
		//log.info("End init()");
		return "TiepDon_KhamBenh_ThamKhamXuTri";
	}
	@End 
	public void end(){
		
	}

	public void notFound(){
		resetValue();
		resetObjectValue();
	}
	
	
	@DataModel
	private List<DmBenhIcd> benhICDList;
	
	@DataModel
	private List<DmBenhIcd> benhICDTVList;
	
	
	
	public void themBenhphu() {
		
		if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
		
		if (benhICDList.size() >= 5){
			return;
		}
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		
		
		DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMa(thamkham.getBenhicd10phu1(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
		
		if (benh == null){
			return;
		}
		
		if (!isContain(benhICDList, benh)){
			this.benhICDList.add(benh);
			thamkham.setBenhicd10phu1(new DmBenhIcd());
		}
	}
	public void themBenhtuvong() {
		
		if (benhICDTVList == null) benhICDTVList = new ArrayList<DmBenhIcd>();
		
		if (benhICDTVList.size() >= 5){
			return;
		}
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		
		
		DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMa(tiepdon.getTiepdonTuvong(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
		
		//log.info("benh:"+benh);
		if (benh == null){
			return;
		}
		//log.info(benh.getDmbenhicdMa());
		//log.info(benh.getDmbenhicdTen());
		
		if (!isContain( benhICDTVList,benh)){
			this.benhICDTVList.add(benh);
			
		}
	}
	private boolean isContain( List<DmBenhIcd> lstBenh,DmBenhIcd benhtest){
		boolean ketqua = false;
		if (lstBenh != null){
			//log.info("lstBenh:"+lstBenh);
			
			for (DmBenhIcd benh: lstBenh){
				if (benhtest.getDmbenhicdMaso().intValue() == benh.getDmbenhicdMaso().intValue()){
					return true;
				}
			}
		}
		return ketqua;
	}
	
	/**
	 * 
	 */
	public void resetValue() {
		//log.info("Begin resetValue(), benhICDList = " + benhICDList);
		tuoi = "";
		ngaySinh = "";
		gioVaoVien = "";
		ngayVaoVien = "";
		gioVaoKhoa = "";
		ngayVaoKhoa = "";
		gioRaVien = "";
		ngayRaVien = "";
		gioRaKhoa = "";
		ngayRaKhoa = "";
		gioTuVong = "";
		ngayTuVong = "";
		ngheNghiepMaSo= null;
		ngheNghiepMa="";
		sNgayTaiKham ="";
		maBacSi = "";
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		Calendar cal = Calendar.getInstance();
		
//		String gioThamKhamTmp =  ""+cal.get(Calendar.HOUR_OF_DAY);
//		if (gioThamKhamTmp.length() == 1){
//			gioThamKhamTmp = "0" + gioThamKhamTmp;
//		}
//		
//		String phutThamKhamTmp = ""+cal.get(Calendar.MINUTE);
//		if (phutThamKhamTmp.length() == 1){
//			phutThamKhamTmp = "0" + phutThamKhamTmp;
//		}
		
		gioThamKham = Utils.getGioPhut(cal.getTime()) ;
		ngayThamKham = formatter.format(cal.getTime());
		
		//chuyenVaoNT = new Boolean(Boolean.FALSE.booleanValue());
		bChuyenVaoNT = false; // phuc.lc
		gioRa = "";
		ngayRa = "";
		sotheTEorBHYT = "";
		resultHidden="";
		soBenhAn = "";
		benhICDList = null;
		benhICDTVList = null;
		hasChKhoa = false;
		hasChBanKham = false;
		daThtoankham = false;
		notBNbaohiem = false;
		notDisplayKTQBV = false;
		//log.info("End resetValue(), size = " + benhICDList.size());
		tenBenhNhanForCall = "";
	}
	
	//Ham  se duoc goi khi het session (session timeout cau hinh trong file web.xml)
	@Destroy 
	public void destroy() {
		log.info("************************* destroy ThamKhamXuTri");
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeTD="ThamKhamVaXuTri";
		//log.info("Report Thamkham - Phieu Thanh toan KCB Ngoai Tru");
		
		TiepDon tiepdonTK = null;
		BenhNhan benhnhanTK = null;
		
		if ( thamkham != null ) {
			tiepdonTK = thamkham.getTiepdonMa();
			if ( tiepdonTK != null ) {
				benhnhanTK = tiepdonTK.getBenhnhanMa();
			}
		}
		
		if ( thamkham == null || tiepdonTK == null || benhnhanTK == null ) {
			//log.info("Report Thamkham - Phieu Thanh toan KCB Ngoai Tru ### thamkham == null || tiepdonTK == null || benhnhanTK == null");
			return;
		}
		
		String baocao1=null;
		try {
			//log.info("bat dau method XuatReport ");
			
			SimpleDateFormat sdf=new SimpleDateFormat(Utils.FORMAT_DATE);
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			String matiepDon = tiepdonTK.getTiepdonMa();
			params.put("MATIEPDON", matiepDon );
			params.put("HOTENBN", benhnhanTK.getBenhnhanHoten()  );
			
			String diachistr="";
			if(benhnhanTK.getBenhnhanDiachi() != null)
				diachistr += benhnhanTK.getBenhnhanDiachi()+", ";
			if(benhnhanTK.getXaMa(true).getDmxaTen() !=null)
				diachistr += benhnhanTK.getXaMa(true).getDmxaTen()+", ";
			if(benhnhanTK.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhnhanTK.getHuyenMa(true).getDmhuyenTen()+", ";
			if(benhnhanTK.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhnhanTK.getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			if(tiepdonTK.getDoituongMa(true).getDmdoituongMa().equals("BH")){
				params.put("BHYT_CO", "X" );
				
				if( (tiepdonTK.getTiepdonTuyen() != null && tiepdonTK.getTiepdonTuyen().toString().equals("1"))
						|| (tiepdonTK.getTiepdonCoGiayGioiThieu() != null && tiepdonTK.getTiepdonCoGiayGioiThieu()) ){
					params.put("DUNGTUYEN","X");
				} else {
					params.put("TRAITUYEN","X");
				}
				
			} else {
				params.put("BHYT_KO", "X" );
			}
			
			if(tiepdonTK.getTiepdonGiatri1() != null){
				params.put("GTTU", sdf.format(tiepdonTK.getTiepdonGiatri1()));
			}else{
				params.put("GTTU", "");
			}
				
			if(tiepdonTK.getTiepdonGiatri2() != null){
				params.put("GTDEN", sdf.format(tiepdonTK.getTiepdonGiatri2()));
			}else{
				params.put("GTDEN", "");
			}
			if (tiepdonTK.getTiepdonSothebh() != null && !tiepdonTK.getTiepdonSothebh().equals("") &&
					tiepdonTK.getKhoibhytMa(true).getDtdmkhoibhytMa() != null && !tiepdonTK.getKhoibhytMa(true).getDtdmkhoibhytMa().equals("") &&
					tiepdonTK.getKcbbhytMa(true).getDmbenhvienMa()!=null
			){
				
				params.put("MATHEBHYT", tiepdonTK.getTiepdonSothebh());
				params.put("MABENHVIEN", tiepdonTK.getKcbbhytMa(true).getDmbenhvienMa().replace(".", "-"));
				
				
			}else{
				params.put("MABENHVIEN","");
				params.put("MATHEBHYT","");
			}
			
			if(tiepdonTK.getKcbbhytMa(true).getDmbenhvienTen()!=null)
				params.put("NOIDKKCBBANDAU", tiepdonTK.getKcbbhytMa(true).getDmbenhvienTen());
			if(tiepdonTK.getTinhbhytMa(true).getDmtinhTen()!=null)
				params.put("NOICAPTHE", tiepdonTK.getTinhbhytMa(true).getDmtinhTen());
			
			if(tiepdonTK.getTiepdonDonvigoi(true).getDmbenhvienMa()!=null)
				params.put("NOIGIOITHIEU", tiepdonTK.getTiepdonDonvigoi(true).getDmbenhvienTen());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(thamkham.getThamkhamNgaygio());
			params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));
			
			params.put("LYDOVAOVIEN", tiepdonTK.getTiepdonLydovaov());
			HsThtoankDelegate thanhToanDel = HsThtoankDelegate.getInstance();
			HsThtoank hsttk = new HsThtoank();
			hsttk = (HsThtoank)thanhToanDel.findBytiepdonMaLast(matiepDon);
			if (hsttk == null || hsttk.getHsthtoankDatt() == null 
					|| (hsttk.getHsthtoankDatt() != null && !hsttk.getHsthtoankDatt().booleanValue())) {
				hsttk = new HsThtoank();
				hsttk.setTiepdonMa(tiepdonTK);			
				
				ThamKhamUtil tkUtil = new ThamKhamUtil();
				tkUtil.tinhToanChoHSTKKham(thamkham, hsttk, false, null, null);//tinhToanChoHSTKKham(hsttk);
				Utils.setInforForHsThToan(hsttk);
			}
			params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt() );
			params.put("NGUOIBENHTRA", hsttk.getHsthtoankThtoan());
			
			// 20110701 bao.ttc: them cac thong tin cho mau moi dung tu ngay 1/7
			if(hsttk.getHsthtoankNgaygiott() != null){
				params.put("NGAYTHANHTOAN", hsttk.getHsthtoankNgaygiott());
				if(thamkham.getThamkhamNgaygio() != null){
					params.put("SONGAYDT", Utils.getDaysBetween(thamkham.getThamkhamNgaygio(), hsttk.getHsthtoankNgaygiott()));
				}
			}			
			if(tiepdonTK.getTiepdonBankham(true).getDtdmbankhamMa() != null) {
				if(tiepdonTK.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC")){
					params.put("CAPCUU","X");
				}
			}
			params.put("PHIEUSO", hsttk.getHsthtoankMa());
			//log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
			params.put("TYLEBH",hsttk.getHsthtoankTylebh());
			String tyleBNtra = ""+ (100 - hsttk.getHsthtoankTylebh() );
			
			if ("MP".equals( tiepdonTK.getDoituongMa(true).getDmdoituongMa())){
				tyleBNtra = "0";
			}
			params.put("PHANTRAMBNTRA",tyleBNtra);
			
			params.put("BIENLAISO", ""  );
			
			//params.put("namsinh", benhnhanTK.getBenhnhanNamsinh());			
			String namsinh = "";
			if (benhnhanTK.getBenhnhanNgaysinh() != null){
				namsinh = sdf.format(benhnhanTK.getBenhnhanNgaysinh());
			} else {
				namsinh = benhnhanTK.getBenhnhanNamsinh();
			}
			params.put("namsinh", namsinh);
			
			String maChanDoan = "";
			String tenChanDoan = "";
			String chanDoan = "";
			
			if (thamkham.getBenhicd10() != null	&& thamkham.getBenhicd10(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10(true).getDmbenhicdMa();
				params.put("MABENHICD", maChanDoan);
				tenChanDoan = thamkham.getBenhicd10(true).getDmbenhicdTen();
				chanDoan += maChanDoan + " " + tenChanDoan;
			}
			
			// bao.ttc: them thamkham.ghichu
			if (thamkham.getThamkhamGhichu() != null && !thamkham.getThamkhamGhichu().equals("")) {
				chanDoan += ", " + thamkham.getThamkhamGhichu();
			}
			
			//tiep tuc them benh phu.
			// tiep tuc them benh phu.
			if (thamkham.getBenhicd10phu1() != null	&& thamkham.getBenhicd10phu1(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10phu1(true).getDmbenhicdMa();
				tenChanDoan = thamkham.getBenhicd10phu1(true).getDmbenhicdTen();
				chanDoan += ", " + maChanDoan + " " + tenChanDoan;
			} else { // 20110701 bao.ttc: load benh ICD Phu1 de in report neu co (vi da xoa tren giao dien)
				if (phu1_temp != null && phu1_temp.getDmbenhicdMaso() != null){
					maChanDoan = phu1_temp.getDmbenhicdMa();
					tenChanDoan = phu1_temp.getDmbenhicdTen();
					chanDoan += ", " + maChanDoan + " " +  tenChanDoan;
				}
			}
			
			if (thamkham.getBenhicd10phu2() != null && thamkham.getBenhicd10phu2(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10phu2(true).getDmbenhicdMa();
				tenChanDoan = thamkham.getBenhicd10phu2(true).getDmbenhicdTen();
				chanDoan += ", " + maChanDoan + " " + tenChanDoan;
			}
			if (thamkham.getBenhicd10phu3() != null && thamkham.getBenhicd10phu3(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10phu3(true).getDmbenhicdMa();
				tenChanDoan = thamkham.getBenhicd10phu3(true).getDmbenhicdTen();
				chanDoan += ", " + maChanDoan + " " + tenChanDoan;
			}
			if (thamkham.getBenhicd10phu4() != null && thamkham.getBenhicd10phu4(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10phu4(true).getDmbenhicdMa();
				tenChanDoan = thamkham.getBenhicd10phu4(true).getDmbenhicdTen();
				chanDoan += ", " + maChanDoan + " " + tenChanDoan;
			}
			if (thamkham.getBenhicd10phu5() != null && thamkham.getBenhicd10phu5(true).getDmbenhicdMaso() != null) {
				maChanDoan = thamkham.getBenhicd10phu5(true).getDmbenhicdMa();
				tenChanDoan = thamkham.getBenhicd10phu5(true).getDmbenhicdTen();
				chanDoan += ", " + maChanDoan + " " + tenChanDoan;
			}
			
			params.put("CHANDOAN", chanDoan );
			
			params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamTen() );
			//Lay danh sach khoa da kham
			List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(matiepDon);
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
					listClsTmp = ClsKhamDelegate.getInstance().findByTiepdonma(tiepdonTK.getTiepdonMa());
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
					
					listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepdonTK.getTiepdonMa(), "1");
					if (listTpk != null && listTpk.size() > 0) {
						for (ThuocPhongKham eachTpk : listTpk) {
							if ((eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true)
								|| (eachTpk.getThuocphongkhamNdm() != null && eachTpk.getThuocphongkhamNdm().booleanValue() == true))	{
								tongTienDV += eachTpk.getThuocphongkhamDongia(); 
							}
						}
					}
					listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepdonTK.getTiepdonMa(), "3");
					if (listTpk != null && listTpk.size() > 0) {
						for (ThuocPhongKham eachTpk : listTpk) {
							if ((eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true)
									|| (eachTpk.getThuocphongkhamNdm() != null && eachTpk.getThuocphongkhamNdm().booleanValue() == true)) {
								tongTienDV += eachTpk.getThuocphongkhamDongia(); 
							}
						}
					}				
			
			params.put("KHOA",bufStr.toString());
			String soTheTe_KhaiSinh_ChungSinh = tiepdonTK.getTiepdonSothete();
			
			
			//them vao khai sinh
			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")){
				if (tiepdonTK.getTiepdonKhaisinh() == null ||
						tiepdonTK.getTiepdonKhaisinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh = tiepdonTK.getTiepdonKhaisinh();
				}
				
			}else{
				if (tiepdonTK.getTiepdonKhaisinh() == null ||
						tiepdonTK.getTiepdonKhaisinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh += "/" + tiepdonTK.getTiepdonKhaisinh();
				}				
			}
			// them vao chung sinh
			
			if (soTheTe_KhaiSinh_ChungSinh == null || soTheTe_KhaiSinh_ChungSinh.equals("")){
				if (tiepdonTK.getTiepdonChungsinh() == null ||
						tiepdonTK.getTiepdonChungsinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh = tiepdonTK.getTiepdonChungsinh();
				}
				
			}else{
				if (tiepdonTK.getTiepdonChungsinh() == null ||
						tiepdonTK.getTiepdonChungsinh().equals("")){
					
				}else{
					soTheTe_KhaiSinh_ChungSinh += "/" + tiepdonTK.getTiepdonChungsinh();
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
			String soTheNgheo = tiepdonTK.getTiepdonThengheo();
			
			//log.info("soTheNgheo"+soTheNgheo);
			
			if (soTheNgheo != null && !soTheNgheo.equals("")){
				params.put("SOTHENGHEO", soTheNgheo);
			}else{
				params.put("SOTHENGHEO", null);
			}
			
			//SUB REPORT 3
			params.put("TONGCHIPHI", hsttk.getHsthtoankTongchi());
			params.put("BANGCHU1", Utils.NumberToString(hsttk.getHsthtoankTongchi()));
			params.put("BANGCHU2", Utils.NumberToString(hsttk.getHsthtoankThtoan()));
			params.put("BANGCHU3", Utils.NumberToString(hsttk.getHsthtoankBhyt()));
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			if(hsttk.getHsthtoankThtoan() >= 0){
				params.put("SNGUOIBENHTRA", "0");
			}else{
				params.put("SNGUOIBENHTRA", "-1");
			}
			
			DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(tiepdonTK.getBenhnhanMa(true).getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh","dmgtMaso" );
			if (gioi != null){

				params.put("GIOI", gioi.getDmgtTen());
						
			}
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			//log.info("tongTienDV = " + tongTienDV);
			JasperReport jasperReport;
			String pathTemplate = "";
			String sub0Template = "";
			String sub1Template = "";
			/*
			if (! IConstantsRes.TINH_BHYT_DEFAULT.equals("74")) {
				// Neu noi trien khai khong phai Binh Duong thi khong in cac mau "Xa hoi hoa"
				
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru.jrxml";
				sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
				sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
				
				jasperReport =JasperCompileManager.compileReport(pathTemplate);
				JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
				JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
				
				params.put("sub0", sub0Report);
				params.put("sub1", sub1Report);
			}else 
			*/
			if (tongTienDV > 0) {
				
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_DV.jrxml";
				sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
				sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
				String sub2Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport0.jrxml";
				String sub3Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport1.jrxml";
				
				// 20110726 bao.ttc: load report cho BN Cap cuu
				if(thamkham.getThamkhamBankham(true).getDtdmbankhamMa().startsWith("CC")) {
					sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBcapcuu_subreport0.jrxml";
					sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBcapcuu_subreport1.jrxml";
				}
				
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
				
				if(thamkham.getThamkhamBankham(true).getDtdmbankhamMa().startsWith("CC")) {
					// Xuat mau report 38/BV-01b cho benh nhan cap cuu
					params.put("GIUONG", tiepdonTK.getTiepdonGiuong());
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBcapcuu.jrxml";
					sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBcapcuu_subreport0.jrxml";
					sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieuthanhtoanKCBcapcuu_subreport1.jrxml";
				} else {
					// Xuat mau report 38/BV-01a cho benh nhan khong phai cap cuu
				
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru.jrxml";
					sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
					sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
				}
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
			    jasperPrintTD =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,getIndex(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","ThamKhamVaXuTri");
			    reportPathTD=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    //log.info("duong dan file xuat report :" + baocao1);
			    //log.info("duong dan -------------------- :"+reportPathTD);
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
	
	public String thuchienAction(){
		XuatReport();
		return "B160_Chonmenuxuattaptin";
	}
	
public String phieukhamchuyenkhoa() throws ServiceException, RemoteException {
		
		//log.info("*****Begin phieukhamchuyenkhoa()");
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

		goToPhieuKhamChuyenKhoa = null;
		
		//log.info("*****End phieukhamchuyenkhoa()");
		//TODO : GOTO SOME BA
		return "PhieuKhamChuyenKhoa";
	}

	public String thuchienToaThuocAction(){
		
		// 20110919 bao.ttc: Phai nhap Chan doan khi in Giay chuyen vien
		ThamKhamDelegate thamkhamDele = ThamKhamDelegate.getInstance();
		ThamKham thamkham_tmp = thamkhamDele.find(thamkham.getThamkhamMa());

		if (thamkham_tmp != null) {
			if ( thamkham_tmp.getBenhicd10() == null
					|| thamkham_tmp.getBenhicd10(true).getDmbenhicdMaso() == null
					|| thamkham_tmp.getBenhicd10(true).getDmbenhicdMa() == null ) {
					FacesMessages.instance().add(IConstantsRes.PHAI_NHAP_CHAN_DOAN);
					return "";
				}
		} else {
			FacesMessages.instance().add(IConstantsRes.PHAI_NHAP_CHAN_DOAN);
			return "";
		}
		
		
		if ( "TP".equals(thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa()) ){
			//XuatReportBNVe();
			reportTypeTD = "ThamKhamVaXuTri";
			ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
			
			//bao.ttc: Benh nhan TP co the dung Thuoc Ban Kham va Ke toa ve
			//20110121 bao.ttc: Fix bug --> Chi in Toa thuoc ve, Thuoc ban kham khong in ra toa
			java.util.List<ThuocPhongKham> ctThuocPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(thamkham.getTiepdonMa().getTiepdonMa(), thamkham.getThamkhamBankham(true).getDtdmbankhamMa(),"2");
			//java.util.List<ThuocPhongKham> ctThuocPhongKhams = thuocPKDele.find2LoaiByMaTiepDonAndMaBanKham(thamkham.getTiepdonMa().getTiepdonMa(), thamkham.getThamkhamBankham(true).getDtdmbankhamMa(),"1", "2");
			ThamKhamUtil tkUtil = new ThamKhamUtil();
			tkUtil.reportTypeTD = reportTypeTD;
			String fileName = tkUtil.XuatReportBNVe(log,  thamkham, null, null, ctThuocPhongKhams,  index);
			if(fileName.equals("")){
				FacesMessages.instance().add(IConstantsRes.IN_TOA_THUOC_ERROR);
				return "";
			}
			
			jasperPrintTD = tkUtil.jasperPrintTD;
			reportPathTD=tkUtil.reportPathTD;
			reportTypeTD=tkUtil.reportTypeTD;
			
			index = index + 1;
		}else if ( "BH".equals(thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa())
				|| "MP".equals(thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa()) ){
			//XuatReport_don_thuoc_ketoa_quay();
			reportTypeTD = "ThamKhamVaXuTri";
			ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
			
			//bao.ttc: Benh nhan BH co the dung Thuoc Ban Kham va Ke toa quay benh vien
			//20110313 bao.ttc: Fix bug --> Chi in Toa thuoc Quay BV, Thuoc ban kham khong in ra toa
			java.util.List<ThuocPhongKham> ctThuocPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(thamkham.getTiepdonMa().getTiepdonMa(), thamkham.getThamkhamBankham(true).getDtdmbankhamMa(),"3");
			//java.util.List<ThuocPhongKham> ctThuocPhongKhams = thuocPKDele.find2LoaiByMaTiepDonAndMaBanKham(thamkham.getTiepdonMa().getTiepdonMa(), thamkham.getThamkhamBankham(true).getDtdmbankhamMa(),"3","1");
			ThamKhamUtil tkUtil = new ThamKhamUtil();
			tkUtil.reportTypeTD = reportTypeTD;
			
//			if(incls){
//				tkUtil.XuatReport_don_thuoc_ketoa_quay(log, thamkham, null, null, ctThuocPhongKhams, index);
//			} else{
//				tkUtil.XuatReport_don_thuoc_ketoa_quay_nocls(log, thamkham, null, null, ctThuocPhongKhams, index);
//			}			
			String fileName = tkUtil.XuatReport_don_thuoc_ketoa_quay(log, thamkham, null, null, ctThuocPhongKhams, inToaThuocBK, incls, index);
			if(fileName.equals("")){
				FacesMessages.instance().add(IConstantsRes.IN_TOA_THUOC_ERROR);
				return "";
			}
//			if(incls && inToaThuocBK){//chon ca 2: in cls va in toa thuoc tai ban kham hien tai
//				inTheoBK = true;
//				tkUtil.XuatReport_don_thuoc_ketoa_quay(log, thamkham, null, null, ctThuocPhongKhams, inTheoBK, index);
//			}else if(incls && !inToaThuocBK){ //chon in cls, in tat ca cac thuoc cua BN
//				tkUtil.XuatReport_don_thuoc_ketoa_quay(log, thamkham, null, null, ctThuocPhongKhams, inTheoBK, index);
//			}else if(!incls && inToaThuocBK){// khong in cls, in thuoc tai ban kham hien tai
//				inTheoBK = true;
//				tkUtil.XuatReport_don_thuoc_ketoa_quay_nocls(log, thamkham, null, null, ctThuocPhongKhams, inTheoBK, index);
//			}else if(!incls && !inToaThuocBK){//khong in cls, in tat ca cac thuoc cua BN
//				tkUtil.XuatReport_don_thuoc_ketoa_quay_nocls(log, thamkham, null, null, ctThuocPhongKhams, inTheoBK, index);
//			}
			jasperPrintTD = tkUtil.jasperPrintTD;
			reportPathTD=tkUtil.reportPathTD;
			reportTypeTD=tkUtil.reportTypeTD;
			index = index + 1;
			
		}else{
			return "";
		}
		return "B160_Chonmenuxuattaptin";
	}


	/**
	 * 
	 */
	public void nhaplai()  {

		resetObjectValue();
		resetValue();
	}
	
	private  void resetObjectValue(){
		//log.info("Begin resetObjectValue()");
		benhNhan = new BenhNhan();
//		SetInforUtil.setInforIfNullForBN(benhNhan);
		tiepdon = new TiepDon();
		//log.info("Ma tiep don = " + tiepdon.getTiepdonMa());
		tiepdon.setBenhnhanMa(benhNhan);
//		SetInforUtil.setInforIfNullForTiepDon(tiepdon);
		thamkham = new ThamKham();
		thamkham.setBenhicd10phu1(new DmBenhIcd());
		phu1_temp = new DmBenhIcd();
		thamkham.setTiepdonMa(tiepdon);
		
		/*
		 * Thanh add 20/05 cho phan quang bao
		 */
		tiepDonForCall = new TiepDon();
		//end
		
		//log.info("End resetObjectValue()");
//		SetInforUtil.setInforIfNullForThamKham(thamkham);
		
	}
	
	/**
	 * // Ham khi nhan nut cls thu thuat
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	
	public String clsthuthat() throws ServiceException, RemoteException {
		// log.info(hosoCBSelected.getSoHS());
		
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null || thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null || thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
//		DtDmBanKham dtdmbankham = new DtDmBanKham();
//		
//		dtdmbankham.setDtdmbankhamMa(bankham);
//		thamkham.setThamkhamBankham(dtdmbankham);
		
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
		goToThamKham = null;
		
		
		goToCLSThuThuat = null;
		
//		ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
//		
//		listCtkqTmp = new ArrayList<ClsKham>();
//		log.info("Doi tuong tiep don:" + thamkham.getTiepdonMa());
//		log.info("Ma tiep don" + thamkham.getTiepdonMa().getTiepdonMa());
//		 List<ClsKham> clsKhamArray = clsKhamDelegate
//				.findByBanKhamVaMaTiepDon(bankham, thamkham.getTiepdonMa().getTiepdonMa());
//		System.out.println("clsKhamArray:" + clsKhamArray);
//		if (clsKhamArray != null) {
//			for (ClsKham cls : clsKhamArray) {
//				com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(cls);
//				listCtkqTmp.add(cls);
//			}
//		}
//		
//		benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
		
		fromMenu = null;
		return "clsthuthat";
	}

	//******************************************************************************
	//Xu ly nut tham kham
	public String thamKham() throws ServiceException, RemoteException {
		//log.info("*****Begin thamKham()");
		
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null || thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null || thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);			
			return "";
		}
		/*DtDmBanKham dtdmbankham = new DtDmBanKham();
		dtdmbankham.setDtdmbankhamMaso(maBanKham);
		dtdmbankham.setDtdmbankhamMa(bankham);
		thamkham.setThamkhamBankham(dtdmbankham);*/
		
		//log.info("Doi tuong tiep don:" + thamkham.getTiepdonMa());
		//log.info("Ma tiep don" + thamkham.getTiepdonMa(true).getTiepdonMa());
		
		//log.info("*****End thamKham()");
		
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
		goToThamKham = null;
		
		
		
		return "thamkham";
	}
	
	public String lapbenhan() throws ServiceException, RemoteException {
		
		//log.info("*****Begin lapbenhanngoaitru()");
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
		
		goToLapBANgoaiTru = null;
		
		//log.info("*****End lapbenhanngoaitru()");
		//TODO : GOTO SOME BA
		return "lapbenhanngoaitru";
	}
	
	public String lapbenhanyhct() throws ServiceException, RemoteException {
		
		//log.info("*****Begin lapbenhanYHCT()");
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			return "";
		}
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
		
		goToBANgoaiTruYHCT = null;
		
		//log.info("*****End lapbenhanYHCT()");
		//TODO : GOTO SOME BA
		return "BenhanngoaitruYHCT";
	}
	
	public String lapbatuvongtruockhivaovien() throws ServiceException, RemoteException {
		
		//log.info("*****Begin lapbatuvongtruockhivaovien()");
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
		
		goToLapBATuVongTruocKhiVaoVien = null;
		
		//log.info("*****End lapbatuvongtruockhivaovien()");
		//TODO : GOTO SOME BA
		return "lapbatuvongtruockhivaovien";
	}
	
	public String giaychungthuong() throws ServiceException, RemoteException {
		
		//log.info("*****Begin giaychungthuong()");
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
		
		goToGiayChungNhan = null;
		
		//log.info("*****End giaychungthuong()");
		//TODO : GOTO SOME BA
		return "giaychungthuong";
	}
	
	public String giaychungnhan() throws ServiceException, RemoteException {
		
		//log.info("*****Begin giaychungnhan()");
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
		if(thamkham.getThamkhamBacsi() != null)
			bacSiKCB = thamkham.getThamkhamBacsi().getDtdmnhanvienTen();
		
		

		goToGiayChungNhan = null;
		
		//log.info("*****End giaychungnhan()");
		//TODO : GOTO SOME BA
		return "giaychungnhan";
	}
	
	public String giaychuyenvien() throws ServiceException, RemoteException {
		
		//log.info("*****Begin giaychuyenvien()");
		
		if (thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equals("TP")) {
			FacesMessages.instance().add(IConstantsRes.DOITUONG_THUPHI_CNBHYT);
			return ""; // 20110308 bao.ttc: Khong cho vao trang Cap nhat thong tin giay chuyen vien
		}
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
		
		// 20110919 bao.ttc: Phai nhap Chan doan khi in Giay chuyen vien
		if ( thamkham.getBenhicd10() == null
			|| thamkham.getBenhicd10(true).getDmbenhicdMaso() == null
			|| thamkham.getBenhicd10(true).getDmbenhicdMa() == null
			|| thamkham.getBenhicd10(true).getDmbenhicdMa().equals("") ) {
			FacesMessages.instance().add(IConstantsRes.PHAI_NHAP_CHAN_DOAN);
			return "";
		}
		
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

		goToChuyenVienNguoiBenhCoTheBHYT = null;
		
		//log.info("*****End giaychuyenvien()");
		//TODO : GOTO SOME BA
		return "ChuyenVienNguoiBenhCoTheBHYT";
	}
	
	public String thamkhamdieutringoaitru() throws ServiceException, RemoteException {
		
		//log.info("*****Begin thamkhamdieutringoaitru()");
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

		goToThamKhamDieuTriNgoaiTru = null;
		
		//log.info("*****End thamkhamdieutringoaitru()");
		//TODO : GOTO SOME BA
		return "ThamKhamDieuTriNgoaiTru";
	}
	
	public String giaychungnhansuckhoe() throws ServiceException, RemoteException {
		
		//log.info("*****Begin GiayChungNhanSucKhoe()");
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

		goToGiayChungNhanSucKhoe = null;
		
		//log.info("*****End GiayChungNhanSucKhoe()");
		//TODO : GOTO SOME BA
		return "GiayChungNhanSucKhoe";
	}
		
	public String giaychuyenviensotxuathuyet() throws ServiceException, RemoteException {
		
		//log.info("*****Begin giaychuyenviensotxuathuyet()");
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

		goToGiayChuyenVienSotXuatHuyet= null;
		
		//log.info("*****End giaychuyenviensotxuathuyet()");
		//TODO : GOTO SOME BA
		return "GiayChuyenVienSotXuatHuyet";
	}

	public void lapBAluuBNCapcuu() {
		//log.info("*****Begin lapBAluuBNCapcuu()");
		if (thamkham.getThamkhamBankham(true).getDtdmbankhamMa().startsWith("CC")){
			
			DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();
			DmKhoa khoaCapcuu = (DmKhoa)dtDele.findByMa("CCL", "DmKhoa", "dmkhoaMa");
			
			if (khoaCapcuu != null){
				RemoveUtil.removeAllNullFromTiepDon(tiepdon);
				RemoveUtil.removeAllNullFromThamKham(thamkham);
				ThamKhamDelegate thamkhamDel = ThamKhamDelegate.getInstance();
				soBenhAn = thamkhamDel.vaoNoiTru_Temp(thamkham, tiepdon, "BA_LUU", khoaCapcuu);
				if (soBenhAn != ""){
					FacesMessages.instance().add(IConstantsRes.BA_LUU_THANH_CONG, soBenhAn);
				} else {
					FacesMessages.instance().add(IConstantsRes.BA_LUU_THAT_BAI);
				}
			} else {
				//log.info("lapBAluuBNCapcuu(): khoaCapcuu NULL");
				FacesMessages.instance().add(IConstantsRes.BA_LUU_THAT_BAI);
			}
		} else {
			//log.info("lapBAluuBNCapcuu(): Khong phai BN cap cuu");
			FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_DOI_TUONG_CAP_CUU);
		}
		//log.info("*****End lapBAluuBNCapcuu()");
	}

	////////////
	// Xu ly nut ke toa ve
	public String keToaVe() throws ServiceException, RemoteException {
		//log.info("*****Begin keToaVe()");
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}

		
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
		
		loaiToaThuocThamKhamVaXuTri = Utils.KE_TOA_BENH_NHAN_TU_MUA;
		
		//initB121_3_Xutrithuoctaibankham = null; // 20120531 bao.ttc: remove vi form Ke toa ve la form rieng 
		fromMenu = null;
		//log.info("*****End keToaVe()");
		return "ketoave";
	}
	
	// Xu ly nut xu tri thuoc
	public String xuTriThuocTaiBanKham() throws ServiceException, RemoteException {
		//log.info("*****Begin xuTriThuocTaiBanKham()");
		
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null || thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null || thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}
		
		
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
		
		loaiToaThuocThamKhamVaXuTri = Utils.XU_TRI_THUOC_TAI_BAN_KHAM;
		initB121_3_Xutrithuoctaibankham = null;
		
		fromMenu = null;
		//log.info("*****End xuTriThuocTaiBanKham()");
		return "xutrithuoctaibankham";
	}
	
	// Xu ly nut ke toa quay benh vien
	public String keToaQuayBenhVien() throws ServiceException, RemoteException {
		//log.info("*****Begin keToaQuayBenhVien()");
		
		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
		
			return "";
		}
		
		if (thamkham.getThamkhamBankham() == null || thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null || thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
			
			return "";
		}

		
		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
		
		loaiToaThuocThamKhamVaXuTri = Utils.KE_TOA_QUAY_BENH_VIEN;
		initB121_3_Xutrithuoctaibankham = null;
		
		fromMenu = null;
		//log.info("*****End keToaQuayBenhVien()");
		return "xutrithuoctaibankham";
	}
	
	//********************************************************************************
	/**
	 * 
	 */
	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
//		if (benhNhan.getBenhnhanNgaysinh() != null && !benhNhan.getBenhnhanNgaysinh().equals("")) {
//			ngaySinh = formatter.format(benhNhan.getBenhnhanNgaysinh().getTime());
//		}
		if (benhNhan.getBenhnhanNamsinh() != null && !benhNhan.getBenhnhanNamsinh().equals("")) {
			ngaySinh = benhNhan.getBenhnhanNamsinh();
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
		 
		// 20110401 bao.ttc: load so the BH, so the TE hoac la set "" doi voi BN Thu phi
		sotheTEorBHYT = "";
		if (tiepdon.getTiepdonSothebh() != null && !tiepdon.getTiepdonSothebh().equals("")) {
			sotheTEorBHYT = tiepdon.getTiepdonSothebh();
		} else if (tiepdon.getTiepdonSothete() != null && !tiepdon.getTiepdonSothete().equals("")) {
			sotheTEorBHYT = tiepdon.getTiepdonSothete();
		}
		
		Date ngayGio = thamkham.getThamkhamNgaygiocn();
		if (ngayGio != null) {
			ngayRa = formatter.format(ngayGio.getTime());
			gioRa =  Utils.getGioPhut(ngayGio) ;
		}
		
		Date ngayGioThamKham = thamkham.getThamkhamNgaygio();
		if (ngayGioThamKham != null){
			ngayThamKham = formatter.format(ngayGioThamKham.getTime());
			gioThamKham =  Utils.getGioPhut(ngayGioThamKham) ; 
		}
		
		if (thamkham.getThamkhamBacsi() != null){
			maSoBacSi = thamkham.getThamkhamBacsi().getDtdmnhanvienMaso();
			maBacSi = thamkham.getThamkhamBacsi().getDtdmnhanvienMa();
		} else {
			maSoBacSi = null;
			maBacSi = "";
		}
		
		//log.info("*****MA BS"+maSoBacSi);
		//log.info("*****MA So BS"+maBacSi);
		if(maSoBacSi!=null && maBacSi!=null && !maBacSi.equals("") && tiepdon.getTiepdonBschuyen()==null)
		{
			DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();
			DtDmNhanVien bacsi = (DtDmNhanVien)dtDele.findByMa(maBacSi, "DtDmNhanVien", "dtdmnhanvienMa");
			tiepdon.setTiepdonBschuyen(bacsi);
			//tiepdon.getTiepdonBschuyen(true).setDtdmnhanvienMaso(maSoBacSi);
			//log.info("*****MA BAC SI CHUYEN: "+ tiepdon.getTiepdonBschuyen(true).getDtdmnhanvienMa());
			//log.info("*****MA SO BAC SI CHUYEN: "+tiepdon.getTiepdonBschuyen(true).getDtdmnhanvienMaso());
		}
		
		//log.info("leave setOtherValue()");

	}

	/**
	 * 
	 * @throws Exception
	 */
	public void displayInfor() throws Exception {
		
		
		try {
			//TiepDonDelegate tiepdonDelegate = TiepDonDelegate.getInstance();
			
			if (tiepdon.getTiepdonMa() == null 
					|| tiepdon.getTiepdonMa().equals("") 
					|| thamkham.getThamkhamBankham() == null 
					|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null 
					|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("") ){
				
				FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tiepdon.getTiepdonMa());
				notFound();
				return;
			}
			
//			ThamKhamWSService thamkhamService = new ThamKhamWSServiceLocator();
//			ThamKhamWS thamkhamWS = thamkhamService.getThamKhamWSPort();
			
			ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
			ThamKham thamkham_tmp = thamkhamDelegate.findByBanKhamVaMaTiepDon( thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), tiepdon.getTiepdonMa());

			if (thamkham_tmp == null) {
				
				// --------------------------------------------------------------------------------------------------------------
				// 20110815 bao.ttc: TH nay co Tiepdon nhung khong co bat cu Thamkham nao cho Tiepdon nay
				// Code ben duoi de tao ra 1 Thamkham tuong ung --> remove de tranh bug co nhieu Thamkham cung Tiepdon va Bankham 
				// --------------------------------------------------------------------------------------------------------------
				FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tiepdon.getTiepdonMa());
				notFound();
				return;
				
//				thamkham_tmp = thamkhamDelegate.findByMaTiepDon(tiepdon.getTiepdonMa());
//				if (thamkham_tmp == null){
//					TiepDon tiepdon_tmp = tiepdonDelegate.find(tiepdon.getTiepdonMa());
//					if (tiepdon_tmp != null) {
//						
//						if(tiepdon_tmp.getTiepdonBankham() == null ||
//								tiepdon_tmp.getTiepdonBankham(true).getDtdmbankhamMa() == null ||
//								!tiepdon_tmp.getTiepdonBankham(true).getDtdmbankhamMa().equals(thamkham.getThamkhamBankham(true).getDtdmbankhamMa()) ){
//							log.info(" ko co bat ky tham kham nao. co' tiep don nhung ko phai ban kham nay");
//							FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tiepdon.getTiepdonMa());
//							notFound();				
//							return;
//						}
//						
//						tiepdon = tiepdon_tmp;
//						thamkham.setTiepdonMa(tiepdon);
//						
//						// phuc.lc 
//						hasChKhoa = (tiepdon.getTiepdonChkhoa() != null);
//						hasChBanKham = (thamkham.getThamkhamChbankham() != null);
//						
//						// 20101229 bao.ttc: check de on/off phan Ke toa quay BV
//						notBNbaohiem = !tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH");
//						
//						daThtoankham = false;
//						
//						// 20110520 bao.ttc: check BN neu da chuyen vao noi tru thi khong cho chinh sua (dung chung bien "daThtoankham" de khong cho nhan Ghi nhan tren giao dien)
//						if (tiepdon.getTiepdonChkhoa() != null && !tiepdon.getTiepdonChkhoa().equals("")){
//							FacesMessages.instance().add(IConstantsRes.DA_CHUYEN_VAO_NOI_TRU);
//							daThtoankham = true;
//						}
//						
//						if (tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH")){
//							
//							// 20110324 bao.ttc: Doi voi BN Bao Hiem, kiem tra xem da TT hay chua, neu da TT thi khong cho nhan "Cap nhat"
//							HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
//							HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(tiepdon.getTiepdonMa());
//							if (hsttk != null){
//								FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
//								daThtoankham = true;
//							}
//							
//							// 20110310 bao.ttc: set currentDate ko chua Gio:phut de so sanh chinh xac hon
//							SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//							String dateString = dateformat.format(new Date());
//							Date currentDate = dateformat.parse(dateString);
//							
//							Date giaTriTheBH_From = tiepdon.getTiepdonGiatri1();
//							Date giaTriTheBH_To = tiepdon.getTiepdonGiatri4();
//							
//							if( giaTriTheBH_To.before(currentDate) || giaTriTheBH_From.after(currentDate))
//								notDisplayKTQBV = true;
//							else
//								notDisplayKTQBV = false;
//						}
//						
//						benhNhan = tiepdon.getBenhnhanMa();
//						ngheNghiepMa = benhNhan.getBenhnhanNghe(true).getDmnghenghiepMa();
//						ngheNghiepMaSo = benhNhan.getBenhnhanNghe(true).getDmnghenghiepMaso();
//						setOtherValue();
//					
//						
//					}else{
//						log.info("ko co tiep don o bat ky ban kham nao. BN chua tiep don");
//						FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tiepdon.getTiepdonMa());
//						notFound();
//						return;
//					}
//				}else{
//					//chua chuyen ban kham
//					log.info("co tham kham o ban kham nao do'. tuy nhien chua chuyen vao ban kham nay");
//					FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tiepdon.getTiepdonMa());
//					notFound();
//					return;
//					
//				}
				
			}
			
			// --------------------------------------------------------------------------------------------------------------
			// 20110815 bao.ttc: TH nay co Tiepdon nhung khong co bat cu Thamkham nao cho Tiepdon nay
			// Code tren de tao ra 1 Thamkham tuong ung --> remove de tranh bug co nhieu Thamkham cung Tiepdon va Bankham 
			// --------------------------------------------------------------------------------------------------------------
			
			else {
				
				thamkham = thamkham_tmp;
				tiepdon = thamkham.getTiepdonMa();
				
				// phuc.lc
				hasChKhoa = (tiepdon.getTiepdonChkhoa() != null);
				hasChBanKham = (thamkham.getThamkhamChbankham() != null);
				
				// 20101229 bao.ttc: check de on/off phan Ke toa quay BV
				notBNbaohiem = !tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH");
				
				daThtoankham = false;
				
				// bao.ttc: comment vi da kiem tra chuyen vao Noi tru o tiepdon (tong quat hon cho TH co nhieu thamkham)
//				if ("NT".equals(thamkham.getThamkhamStatus())){
//					bChuyenVaoNT = true; // phuc.lc
//					FacesMessages.instance().add(IConstantsRes.BN_DA_CHUYEN_VAO_NOI_TRU);
//				}else{
//					bChuyenVaoNT = false; // phuc.lc
//				}
				
				// 20110520 bao.ttc: check BN neu da chuyen vao noi tru thi khong cho chinh sua (dung chung bien "daThtoankham" de khong cho nhan Ghi nhan tren giao dien)
				if (tiepdon.getTiepdonChkhoa() != null && !tiepdon.getTiepdonChkhoa().equals("")){
					FacesMessages.instance().add(IConstantsRes.DA_CHUYEN_VAO_NOI_TRU);
					daThtoankham = true;
				}
				
				if (tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH")){
					
					// 20110324 bao.ttc: Doi voi BN Bao Hiem, kiem tra xem da TT hay chua, neu da TT thi khong cho nhan "Cap nhat"
					HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
					HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(tiepdon.getTiepdonMa());
					if (hsttk != null){
						FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
						daThtoankham = true;
					}
					
					// 20110310 bao.ttc: set currentDate ko chua Gio:phut de so sanh chinh xac hon
					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
					String dateString = dateformat.format(new Date());
					Date currentDate = dateformat.parse(dateString);
					
					Date giaTriTheBH_From = tiepdon.getTiepdonGiatri1();
					Date giaTriTheBH_To = tiepdon.getTiepdonGiatri4();
					
					if( giaTriTheBH_To.before(currentDate) || giaTriTheBH_From.after(currentDate))
						notDisplayKTQBV = true;
					else
						notDisplayKTQBV = false;
				}
				
				// 20110728 bao.ttc: neu la doi tuong Mien phi cung cho ke toa quay BV
				if (tiepdon.getDoituongMa(true).getDmdoituongMa().equals("MP")){
					notBNbaohiem = false;
					notDisplayKTQBV = false;
				}
				
				benhNhan = tiepdon.getBenhnhanMa();
				setOtherValue();
				
				//gan' cac tham kham vao` list
				// neu > 1
				
				ngheNghiepMa = benhNhan.getBenhnhanNghe(true).getDmnghenghiepMa();
				ngheNghiepMaSo = benhNhan.getBenhnhanNghe(true).getDmnghenghiepMaso();
				
				if (benhICDList != null) {
					//log.info("before add item to list, size = " + benhICDList.size());
					if (benhICDList.size() > 0) benhICDList.clear();
				}
				if (thamkham.getBenhicd10phu1() != null){
					if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
					benhICDList.add(thamkham.getBenhicd10phu1());
					//thamkham.setBenhicd10phu1(null); // 20110701 bao.ttc: remove de In benh phu len Phieu Thanh toan, da set null khi ghi nhan
				}
				if (thamkham.getBenhicd10phu2() != null){
					if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
					benhICDList.add(thamkham.getBenhicd10phu2());
					//thamkham.setBenhicd10phu2(null);
				}
				if (thamkham.getBenhicd10phu3() != null){
					if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
					benhICDList.add(thamkham.getBenhicd10phu3());
					//thamkham.setBenhicd10phu3(null);
				}
				if (thamkham.getBenhicd10phu4() != null){
					if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
					benhICDList.add(thamkham.getBenhicd10phu4());
					//thamkham.setBenhicd10phu4(null);
				}
				if (thamkham.getBenhicd10phu5() != null){
					if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
					benhICDList.add(thamkham.getBenhicd10phu5());
					//thamkham.setBenhicd10phu5(null);
				}
				
				DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
				
				if (tiepdon.getTiepdonTuvongphu1() != null){
					if (benhICDTVList == null) benhICDTVList = new ArrayList<DmBenhIcd>();

					DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(tiepdon.getTiepdonTuvongphu1(), "DmBenhIcd", "dmbenhicdMaso");
					
					if (benh != null){
						benhICDTVList.add(benh);
						tiepdon.setTiepdonTuvongphu1(null);
					}
					
				}
				
				if (tiepdon.getTiepdonTuvongphu2() != null){
					if (benhICDTVList == null) benhICDTVList = new ArrayList<DmBenhIcd>();

					DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(tiepdon.getTiepdonTuvongphu2(), "DmBenhIcd", "dmbenhicdMaso");
					
					if (benh != null){
						benhICDTVList.add(benh);
						tiepdon.setTiepdonTuvongphu2(null);
					}
					
				}
				
				if (tiepdon.getTiepdonTuvongphu3() != null){
					if (benhICDTVList == null) benhICDTVList = new ArrayList<DmBenhIcd>();

					DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(tiepdon.getTiepdonTuvongphu3(), "DmBenhIcd", "dmbenhicdMaso");
					
					if (benh != null){
						benhICDTVList.add(benh);
						tiepdon.setTiepdonTuvongphu3(null);
					}
					
				}
				
				if (tiepdon.getTiepdonTuvongphu4() != null){
					if (benhICDTVList == null) benhICDTVList = new ArrayList<DmBenhIcd>();

					DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(tiepdon.getTiepdonTuvongphu4(), "DmBenhIcd", "dmbenhicdMaso");
					
					if (benh != null){
						benhICDTVList.add(benh);
						tiepdon.setTiepdonTuvongphu4(null);
					}
					
				}
				
				if (tiepdon.getTiepdonTuvongphu5() != null){
					if (benhICDTVList == null) benhICDTVList = new ArrayList<DmBenhIcd>();

					DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(tiepdon.getTiepdonTuvongphu5(), "DmBenhIcd", "dmbenhicdMaso");
					
					if (benh != null){
						benhICDTVList.add(benh);
						tiepdon.setTiepdonTuvongphu5(null);
					}
					
				}
				SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);

				if(thamkham.getThamkhamNgaytaikham()!=null)
					sNgayTaiKham=formatter.format(thamkham.getThamkhamNgaytaikham());
				
			
			}

			if (tiepdon != null && tiepdon.getTiepdonMa() != null && !tiepdon.getTiepdonMa().equals("")) {
				
				HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
				Hsba hsba = hsbaDelegate.findByTiepDonMa(tiepdon.getTiepdonMa());
				//log.info("hsba:" + hsba);
				if (hsba != null) {
					soBenhAn = hsba.getHsbaSovaovien();
				} else {
					soBenhAn = "";
				}
				
			}
			
			// 20110701 bao.ttc: luu benh ICD Phu1 de in report (vi phai xoa tren giao dien)
			if (thamkham.getBenhicd10phu1() != null){
				phu1_temp = thamkham.getBenhicd10phu1();
			}
			thamkham.setBenhicd10phu1(new DmBenhIcd());
			
		} catch (Exception e) {
			log.info("error on function displayInfor" + e);
		}
	}

	


	public void deleteCurrentRow(int vitri) {
		if (this.benhICDList == null || this.benhICDList.size() == 0) {
			return;
		}		
		this.benhICDList.remove(vitri);
	}
	public void deleteCurrentRowTV(int vitri) {
		if (this.benhICDTVList == null || this.benhICDTVList.size() == 0) {
			return;
		}		
		this.benhICDTVList.remove(vitri);
	}
	private String soBenhAn;

	/**
	 * 
	 * @throws Exception
	 */
	public void ghiNhan() throws Exception {

		// 20120704 bao.ttc: save user action log to database
		YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
		YteLog yteLog = new YteLog();
		String yteLogString = "";
		String yteListData = "";

		yteLog.setForm("B121_Thamkhamvaxutri");
		yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
		
		yteLog.setObjectId(tiepdon.getTiepdonMa() == null ? "NULL" : tiepdon.getTiepdonMa());
		yteLogString += "TK:" + thamkham.getThamkhamMa();
		yteListData += "BN:" + (benhNhan.getBenhnhanMa() == null ? "NULL" : benhNhan.getBenhnhanMa())
						+ " Ten:" + benhNhan.getBenhnhanHoten()
						+ " BH:" + tiepdon.getTiepdonSothebh();
		
		try {
			//log.info("========== vao ghinhan");
			DtDmBanKham dtdmbankham = new DtDmBanKham();
			dtdmbankham.setDtdmbankhamMaso(thamkham.getThamkhamBankham(true).getDtdmbankhamMaso()) ;
			dtdmbankham.setDtdmbankhamMa(thamkham.getThamkhamBankham(true).getDtdmbankhamMa());
			thamkham.setThamkhamBankham(dtdmbankham);
			yteLogString += " BK:" + thamkham.getThamkhamBankham().getDtdmbankhamMa();
			
			// phuc.lc begin						
			ThamKham thamkhamTmp = ThamKhamDelegate.getInstance().findByBanKhamVaMaTiepDon(thamkham.getThamkhamBankham().getDtdmbankhamMa(), tiepdon.getTiepdonMa());
			if (thamkhamTmp != null) {
				//log.info("thamkhamTmp ma so = " + thamkhamTmp.getThamkhamMa());
				if (thamkhamTmp.getThamkhamMa().intValue() != thamkham.getThamkhamMa().intValue()) {
					// Trung ban kham theo ma tiep don
					FacesMessages.instance().add(IConstantsRes.THAM_KHAM_TRUNG_BAN_KHAM, thamkhamTmp.getThamkhamBankham().getDtdmbankhamTen());
					return;
				}
			}
			// phuc.lc end
			
			//
			if (maBacSi != null && !maBacSi.equals("")){
				thamkham.getThamkhamBacsi(true).setDtdmnhanvienMaso(maSoBacSi);
				thamkham.getThamkhamBacsi(true).setDtdmnhanvienMa(maBacSi);
				yteLogString += " BSi:" + (thamkham.getThamkhamBacsi() == null ? "NULL" : thamkham.getThamkhamBacsi(true).getDtdmnhanvienMa());
			}
			
			RemoveUtil.removeAllNullFromBN(benhNhan);
			//log.info("tiep don ban kham 222= " + tiepdon.getTiepdonBankham());
			RemoveUtil.removeAllNullFromTiepDon(tiepdon);
			RemoveUtil.removeAllNullFromThamKham(thamkham);
			if (ngayThamKham != null && !ngayThamKham.equals("")) {
				thamkham.setThamkhamNgaygio(Utils.getDBDate(gioThamKham, ngayThamKham).getTime());
			}
			if (ngayRa != null && !ngayRa.equals("")) {
				//thamkham.setThamkhamNgaygiocn(Utils.getDBDate(gioRa, ngayRa).getTime());
			}
			thamkham.setThamkhamNgaygiocn(new Date());
			SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
			
			Date ngayGio = thamkham.getThamkhamNgaygiocn();
			if (ngayGio != null) {
				ngayRa = formatter.format(ngayGio.getTime());
				gioRa =  Utils.getGioPhut(ngayGio) ;
			}
			
			//** INSERT NHAN VIEN CAP NHAT
			DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
			DtDmNhanVien nhanVienCN = nvDelegate.findByNd(identity.getUsername());
			thamkham.setThamkhamNhanviencn(nhanVienCN);
			
//			ThamKhamWSService service = new ThamKhamWSServiceLocator();
//			ThamKhamWS ws = service.getThamKhamWSPort();
			
			HsThtoank hsttk = new HsThtoank();
			boolean chuyenVaoNT = false;
			
			if (thamkham.getThamkhamChuyenSoLieuVaoNoiTru() != null && thamkham.getThamkhamChuyenSoLieuVaoNoiTru() == true){
				chuyenVaoNT = true;
				bChuyenVaoNT = true; // phuc.lc
				yteLogString += " ChuyenSL";
				
				hsttk.setTiepdonMa(thamkham.getTiepdonMa());
				//ThamKhamUtil tkUtil = new ThamKhamUtil();
				//tkUtil.tinhToanChoHSTKKham(thamkham, hsttk, false, null, null);//tinhToanChoHSTKKham(hsttk);
				hsttk.setHsthtoankThtoan(new Double(0.0)); // 20120514 bao.ttc: set gia tri tam
				
				thamkham.setThamkhamStatus("NT");
			}else{
				thamkham.setThamkhamStatus("");
			}
			
			// chuan bi danh muc benh phu
			// xoa cac benh phu dang co'
			
			thamkham.setBenhicd10phu1(null);
			thamkham.setBenhicd10phu2(null);
			thamkham.setBenhicd10phu3(null);
			thamkham.setBenhicd10phu4(null);
			thamkham.setBenhicd10phu5(null);
			if(sNgayTaiKham!=null && !sNgayTaiKham.equals(""))
			thamkham.setThamkhamNgaytaikham(Utils.getDBDate("00:00", sNgayTaiKham).getTime());
			// them benh phu tu danh sach
			
			if (benhICDList != null && benhICDList.size() > 0 ){
				for (DmBenhIcd benh: benhICDList){
					if (thamkham.getBenhicd10phu1() == null){
						thamkham.setBenhicd10phu1(benh);
						//yteListData += " Phu1:" + (thamkham.getBenhicd10phu1() == null ? "NULL" : thamkham.getBenhicd10phu1(true).getDmbenhicdMa());
						continue;
					}
					if (benh.getDmbenhicdMaso().intValue() == thamkham.getBenhicd10phu1().getDmbenhicdMaso().intValue()){
						continue;
					}
					if (thamkham.getBenhicd10phu2() == null){
						thamkham.setBenhicd10phu2(benh);
						continue;
					}
					if (thamkham.getBenhicd10phu3() == null){
						thamkham.setBenhicd10phu3(benh);
						continue;
					}
					if (thamkham.getBenhicd10phu4() == null){
						thamkham.setBenhicd10phu4(benh);
						continue;
					}
					if (thamkham.getBenhicd10phu5() == null){
						thamkham.setBenhicd10phu5(benh);
						continue;
					}					
				}
			}
			
			tiepdon.setTiepdonTuvongphu1(null);
			tiepdon.setTiepdonTuvongphu2(null);
			tiepdon.setTiepdonTuvongphu3(null);
			tiepdon.setTiepdonTuvongphu4(null);
			tiepdon.setTiepdonTuvongphu5(null);
			
			
			if (benhICDTVList != null && benhICDTVList.size() > 0 ){
				for (DmBenhIcd benh: benhICDTVList){
					
					if (tiepdon.getTiepdonTuvong() == null){
						tiepdon.setTiepdonTuvong(benh);
						continue;
					}
					if (tiepdon.getTiepdonTuvong() != null &&
							tiepdon.getTiepdonTuvong().getDmbenhicdMaso().intValue() == benh.getDmbenhicdMaso().intValue() 	){
						
						continue;
					}
					if (tiepdon.getTiepdonTuvongphu1() == null){
						tiepdon.setTiepdonTuvongphu1(benh.getDmbenhicdMaso());
						continue;
					}
					if (tiepdon.getTiepdonTuvongphu2() == null){
						tiepdon.setTiepdonTuvongphu2(benh.getDmbenhicdMaso());
						continue;
					}
					if (tiepdon.getTiepdonTuvongphu3() == null){
						tiepdon.setTiepdonTuvongphu3(benh.getDmbenhicdMaso());
						continue;
					}
					if (tiepdon.getTiepdonTuvongphu4() == null){
						tiepdon.setTiepdonTuvongphu4(benh.getDmbenhicdMaso());
						continue;
					}
					if (tiepdon.getTiepdonTuvongphu5() == null){
						tiepdon.setTiepdonTuvongphu5(benh.getDmbenhicdMaso());
						continue;
					}
				}
			}
			ThamKhamDelegate thamKhamDelegate = ThamKhamDelegate.getInstance();
			
			//log.info("========== tiepdon: " + tiepdon.getTiepdonChkhoa().getDmkhoaMa());
			
			if(ngheNghiepMaSo!=null)
				benhNhan.getBenhnhanNghe(true).setDmnghenghiepMaso(ngheNghiepMaSo);
			//log.info("***** NGHE NGHIEP MA SO: "+ngheNghiepMaSo);
			//log.info("***** NGHE NGHIEP MA: "+ngheNghiepMa);
			//log.info("tiepdon.getTiepdonChvien():"+tiepdon.getTiepdonChvien());
			//log.info("tiepdon.getTiepdonChvien():"+tiepdon.getTiepdonChvien());
			
			DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();
			// get thong tin chuyen khoa (khi chuyen vao noi tru)
			if (tiepdon.getTiepdonChkhoa() != null && tiepdon.getTiepdonChkhoa(true).getDmkhoaMaso() != null){
				// 20110908 bao.ttc: check BN neu da chuyen vao noi tru thi khong cho chinh sua (dung chung bien "daThtoankham" de khong cho nhan Ghi nhan tren giao dien)
				FacesMessages.instance().add(IConstantsRes.DA_CHUYEN_VAO_NOI_TRU);
				daThtoankham = true;
				bChuyenVaoNT = true;
				yteListData += " KhoaNoitru:" + tiepdon.getTiepdonChkhoa(true).getDmkhoaMa();
				
				DmKhoa dmkhoa = (DmKhoa)dtDele.findByMaSo(tiepdon.getTiepdonChkhoa().getDmkhoaMaso(), "DmKhoa", "dmkhoaMaso");
				tiepdon.setTiepdonChkhoa(dmkhoa);
			}
			
			// get thong tin chuyen vien
			if (tiepdon.getTiepdonChvien() != null && tiepdon.getTiepdonChvien().getDmbenhvienMaso() != null){
				yteListData += " ChVien:" + tiepdon.getTiepdonChvien().getDmbenhvienMaso();
				DmBenhVien bv = (DmBenhVien)dtDele.findByMaSo(tiepdon.getTiepdonChvien().getDmbenhvienMaso(), "DmBenhVien", "dmbenhvienMaso");
				tiepdon.setTiepdonChvien(bv);
			}
			
			// 20110303 bao.ttc: set Ma Dieu tri tu Thamkham vao Tiepdon
			if (thamkham.getThamkhamDieutri() != null){
				if (thamkham.getThamkhamDieutri().getDmdieutriMaso() != null){
					yteListData += " DieuTri:" + thamkham.getThamkhamDieutri().getDmdieutriMaso();
					tiepdon.setDieutriMa(thamkham.getThamkhamDieutri());
				}
			}
			
			// phuc.lc 24-12-2010 : begin
			// Cap nhat ma chan doan ban dau cho tiep don, muc dich de hien thi ma benh tre report
			if (thamkham.getBenhicd10() != null) {
				if (thamkham.getBenhicd10().getDmbenhicdMaso() != null) {
					yteListData += " ICD10:" + thamkham.getBenhicd10().getDmbenhicdMaso();
					if (tiepdon.getTiepdonMachdoanbd() == null || tiepdon.getTiepdonMachdoanbd().getDmbenhicdMaso() == null) {
						tiepdon.setTiepdonMachdoanbd(thamkham.getBenhicd10());
					}
				}
			}
			// phuc.lc 24-12-2010 : end
			String soVaoVien = thamKhamDelegate.thamKhamVaXuTri(hsttk,thamkham, tiepdon, benhNhan, chuyenVaoNT, IConstantsRes.CHUYEN_VAO_NOI_TRU_OPTION);
			
			
			this.soBenhAn = soVaoVien;
			yteLogString += " HSBA:" + soVaoVien;
			
			// 20120514 bao.ttc: tinh toan lai hsttk cho TH chuyen so lieu vao Noi tru
			if (chuyenVaoNT){
				hsttk.setTiepdonMa(thamkham.getTiepdonMa());
				ThamKhamUtil tkUtil = new ThamKhamUtil();
				tkUtil.tinhToanChoHSTKKham(thamkham, hsttk, false, null, null);
			}
			
			//reset all value
			//nhaplai();
			if (soVaoVien != null && !soVaoVien.equals("")){
				FacesMessages.instance().add(IConstantsRes.GHI_NHAN_THANH_CONG_VOI_SO_VAO_VIEN,soVaoVien);
			}else{
				 FacesMessages.instance().add(IConstantsRes.SUCCESS);
			}
			
			if (thamkham.getBenhicd10phu1() == null) {
				thamkham.setBenhicd10phu1(new DmBenhIcd());
			}
			
			resultHidden="success";
			
			// 20120704 bao.ttc: save user action log to database
			yteLog.setLogString(yteLogString);
			yteLog.setListData(yteListData);
			yteLog.setDateTime(new Date());
			yteLogDele.create(yteLog);
			
//			SetInforUtil.setInforIfNullForBN(benhNhan);
//			SetInforUtil.setInforIfNullForTiepDon(tiepdon);
//			SetInforUtil.setInforIfNullForThamKham(thamkham);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
			FacesMessages.instance().add(IConstantsRes.FAIL);
			resultHidden="fail";
		}
	}
	
	public String msg="";
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ThamKham getThamkham() {
		return thamkham;
	}

	public void setThamkham(ThamKham thamkham) {
		this.thamkham = thamkham;
	}

//	public java.util.ArrayList<ClsKham> getListCtkqTmp() {
//		return listCtkqTmp;
//	}
//
//	public void setListCtkqTmp(java.util.ArrayList<ClsKham> listCtkqTmp) {
//		this.listCtkqTmp = listCtkqTmp;
//	}

	public static String getFORMAT_DATE() {
		return FORMAT_DATE;
	}

	public static void setFORMAT_DATE(String format_date) {
		FORMAT_DATE = format_date;
	}

	public static String getFORMAT_DATE_TIME() {
		return FORMAT_DATE_TIME;
	}

	public static void setFORMAT_DATE_TIME(String format_date_time) {
		FORMAT_DATE_TIME = format_date_time;
	}



	public String getNgayRa() {
		return ngayRa;
	}

	public void setNgayRa(String ngayRa) {
		this.ngayRa = ngayRa;
	}

	public Boolean getBThuThuatPhauThuat() {
		return bThuThuatPhauThuat;
	}

	public void setBThuThuatPhauThuat(Boolean thuThuatPhauThuat) {
		bThuThuatPhauThuat = thuThuatPhauThuat;
	}

	public Boolean getBSinh() {
		return bSinh;
	}

	public void setBSinh(Boolean sinh) {
		bSinh = sinh;
	}

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

	public TiepDon getTiepdon() {
		return tiepdon;
	}

	public void setTiepdon(TiepDon tiepdon) {
		this.tiepdon = tiepdon;
	}

	

	public String getNgayThamKham() {
		return ngayThamKham;
	}

	public void setNgayThamKham(String ngayThamKham) {
		this.ngayThamKham = ngayThamKham;
	}

	public String getTuoi() {
		return tuoi;
	}

	public void setTuoi(String tuoi) {
		this.tuoi = tuoi;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	

	public String getNgayVaoVien() {
		return ngayVaoVien;
	}

	public void setNgayVaoVien(String ngayVaoVien) {
		this.ngayVaoVien = ngayVaoVien;
	}

	

	public String getNgayVaoKhoa() {
		return ngayVaoKhoa;
	}

	public void setNgayVaoKhoa(String ngayVaoKhoa) {
		this.ngayVaoKhoa = ngayVaoKhoa;
	}

	

	public String getNgayRaVien() {
		return ngayRaVien;
	}

	public void setNgayRaVien(String ngayRaVien) {
		this.ngayRaVien = ngayRaVien;
	}

	
	public String getNgayTuVong() {
		return ngayTuVong;
	}

	public void setNgayTuVong(String ngayTuVong) {
		this.ngayTuVong = ngayTuVong;
	}

	public String getGioi() {
		return gioi;
	}

	public void setGioi(String gioi) {
		this.gioi = gioi;
	}

	public String getUserLogedIn() {
		return userLogedIn;
	}

	public void setUserLogedIn(String userLogedIn) {
		this.userLogedIn = userLogedIn;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public org.apache.log4j.Logger getLog() {
		return log;
	}

	public void setLog(org.apache.log4j.Logger log) {
		this.log = log;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getSotheTEorBHYT() {
		return sotheTEorBHYT;
	}

	public void setSotheTEorBHYT(String sotheTEorBHYT) {
		this.sotheTEorBHYT = sotheTEorBHYT;
	}

	

	public String getNgayRaKhoa() {
		return ngayRaKhoa;
	}

	public void setNgayRaKhoa(String ngayRaKhoa) {
		this.ngayRaKhoa = ngayRaKhoa;
	}
	public String getSoBenhAn() {
		return soBenhAn;
	}

	public void setSoBenhAn(String soBenhAn) {
		this.soBenhAn = soBenhAn;
	}

//	public java.util.ArrayList<ClsKham> getListCtkq() {
//		return listCtkqTmp;
//	}
//
//	public void setListCtkq(java.util.ArrayList<ClsKham> listCtkq) {
//		this.listCtkqTmp = listCtkq;
//	}

	public String getGioRa() {
		return gioRa;
	}

	public void setGioRa(String gioRa) {
		this.gioRa = gioRa;
	}

	public String getGioThamKham() {
		return gioThamKham;
	}

	public void setGioThamKham(String gioThamKham) {
		this.gioThamKham = gioThamKham;
	}

	public String getGioVaoVien() {
		return gioVaoVien;
	}

	public void setGioVaoVien(String gioVaoVien) {
		this.gioVaoVien = gioVaoVien;
	}

	public String getGioVaoKhoa() {
		return gioVaoKhoa;
	}

	public void setGioVaoKhoa(String gioVaoKhoa) {
		this.gioVaoKhoa = gioVaoKhoa;
	}

	public String getGioRaVien() {
		return gioRaVien;
	}

	public void setGioRaVien(String gioRaVien) {
		this.gioRaVien = gioRaVien;
	}

	public String getGioRaKhoa() {
		return gioRaKhoa;
	}

	public void setGioRaKhoa(String gioRaKhoa) {
		this.gioRaKhoa = gioRaKhoa;
	}

	public String getGioTuVong() {
		return gioTuVong;
	}

	public void setGioTuVong(String gioTuVong) {
		this.gioTuVong = gioTuVong;
	}

	

	public String getMaBanKhamOut() {
		return maBanKhamOut;
	}

	public void setMaBanKhamOut(String maBanKhamOut) {
		this.maBanKhamOut = maBanKhamOut;
	}

	public String getMaTiepDonOut() {
		return maTiepDonOut;
	}

	public void setMaTiepDonOut(String maTiepDonOut) {
		this.maTiepDonOut = maTiepDonOut;
	}

	public String getGoToThamKham() {
		return goToThamKham;
	}

	public void setGoToThamKham(String goToThamKham) {
		this.goToThamKham = goToThamKham;
	}

	public String getGoToCLSThuThuat() {
		return goToCLSThuThuat;
	}

	public void setGoToCLSThuThuat(String goToCLSThuThuat) {
		this.goToCLSThuThuat = goToCLSThuThuat;
	}

	public String getLoaiToaThuocThamKhamVaXuTri() {
		return loaiToaThuocThamKhamVaXuTri;
	}

	public void setLoaiToaThuocThamKhamVaXuTri(String loaiToaThuocThamKhamVaXuTri) {
		this.loaiToaThuocThamKhamVaXuTri = loaiToaThuocThamKhamVaXuTri;
	}

	public Integer getMaSoBacSi() {
		return maSoBacSi;
	}

	public void setMaSoBacSi(Integer maSoBacSi) {
		this.maSoBacSi = maSoBacSi;
	}

	public String getMaBacSi() {
		return maBacSi;
	}

	public void setMaBacSi(String maBacSi) {
		this.maBacSi = maBacSi;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public String getChonloaiba() {
		return chonloaiba;
	}

	public void setChonloaiba(String chonloaiba) {
		this.chonloaiba = chonloaiba;
	}

	public String getGoToLapBANgoaiTru() {
		return goToLapBANgoaiTru;
	}

	public void setGoToLapBANgoaiTru(String goToLapBANgoaiTru) {
		this.goToLapBANgoaiTru = goToLapBANgoaiTru;
	}

	public String getGoToGiayChungNhan() {
		return goToGiayChungNhan;
	}

	public void setGoToGiayChungNhan(String goToGiayChungNhan) {
		this.goToGiayChungNhan = goToGiayChungNhan;
	}


	public String getGoToChuyenVienNguoiBenhCoTheBHYT()
	{
		return goToChuyenVienNguoiBenhCoTheBHYT;
	}

	public void setGoToChuyenVienNguoiBenhCoTheBHYT(
			String goToChuyenVienNguoiBenhCoTheBHYT)
	{
		this.goToChuyenVienNguoiBenhCoTheBHYT = goToChuyenVienNguoiBenhCoTheBHYT;
	}
	
	public String getGoToThamKhamDieuTriNgoaiTru()
	{
		return goToThamKhamDieuTriNgoaiTru;
	}

	public void setGoToThamKhamDieuTriNgoaiTru(String goToThamKhamDieuTriNgoaiTru)
	{
		this.goToThamKhamDieuTriNgoaiTru = goToThamKhamDieuTriNgoaiTru;
	}

	public String getBacSiKCB() {
		return bacSiKCB;
	}

	public void setBacSiKCB(String bacSiKCB) {
		this.bacSiKCB = bacSiKCB;
	}

	public String getGoToPhieuKhamChuyenKhoa() {
		return goToPhieuKhamChuyenKhoa;
	}

	public void setGoToPhieuKhamChuyenKhoa(String goToPhieuKhamChuyenKhoa) {
		this.goToPhieuKhamChuyenKhoa = goToPhieuKhamChuyenKhoa;
	}
	
	public String getGoToGiayChungNhanSucKhoe() {
		return goToGiayChungNhanSucKhoe;
	}


	public void setGoToGiayChungNhanSucKhoe(String goToGiayChungNhanSucKhoe) {
		this.goToGiayChungNhanSucKhoe = goToGiayChungNhanSucKhoe;
	}


	public String getGoToPhieuKhamBenhVaoVien() {
		return goToPhieuKhamBenhVaoVien;
	}


	public void setGoToPhieuKhamBenhVaoVien(String goToPhieuKhamBenhVaoVien) {
		this.goToPhieuKhamBenhVaoVien = goToPhieuKhamBenhVaoVien;
	}


	public String getGoToGiayChuyenVienSotXuatHuyet() {
		return goToGiayChuyenVienSotXuatHuyet;
	}


	public void setGoToGiayChuyenVienSotXuatHuyet(
			String goToGiayChuyenVienSotXuatHuyet) {
		this.goToGiayChuyenVienSotXuatHuyet = goToGiayChuyenVienSotXuatHuyet;
	}


	public String getNgayTaiKham()
	{
		return sNgayTaiKham;
	}


	public void setNgayTaiKham(String dateNgayTaiKham)
	{
		this.sNgayTaiKham = dateNgayTaiKham;
	}


	public Integer getNgheNghiepMaSo()
	{
		return ngheNghiepMaSo;
	}


	public void setNgheNghiepMaSo(Integer ngheNghiepMaSo)
	{
		this.ngheNghiepMaSo = ngheNghiepMaSo;
	}


	public String getNgheNghiepMa()
	{
		return ngheNghiepMa;
	}


	public void setNgheNghiepMa(String ngheNghiepMa)
	{
		this.ngheNghiepMa = ngheNghiepMa;
	}


	public Boolean getIncls() {
		return incls;
	}


	public void setIncls(Boolean incls) {
		this.incls = incls;
	}

	public Boolean getInToaThuocBK() {
		return inToaThuocBK;
	}


	public void setInToaThuocBK(Boolean inToaThuocBK) {
		this.inToaThuocBK = inToaThuocBK;
	}

	public boolean isbChuyenVaoNT() {
		return bChuyenVaoNT;
	}


	public void setbChuyenVaoNT(boolean bChuyenVaoNT) {
		this.bChuyenVaoNT = bChuyenVaoNT;
	}
	
	public boolean isHasChKhoa() {
		return hasChKhoa;
	}


	public void setHasChKhoa(boolean hasChKhoa) {
		this.hasChKhoa = hasChKhoa;
	}


	public boolean isHasChBanKham() {
		return hasChBanKham;
	}


	public void setHasChBanKham(boolean hasChBanKham) {
		this.hasChBanKham = hasChBanKham;
	}


	public boolean isDaThtoankham() {
		return daThtoankham;
	}


	public void setDaThtoankham(boolean daThtoankham) {
		this.daThtoankham = daThtoankham;
	}
	
	public boolean isNotBNbaohiem() {
		return notBNbaohiem;
	}


	public void setNotBNbaohiem(boolean notBNbaohiem) {
		this.notBNbaohiem = notBNbaohiem;
	}
	
	public boolean isNotDisplayKTQBV() {
		return notDisplayKTQBV;
	}


	public void setNotDisplayKTQBV(boolean notDisplayKTQBV) {
		this.notDisplayKTQBV = notDisplayKTQBV;
	}
	
//	public String getInitB121_4_KeToaQuayBenhVien() {
//		return initB121_4_KeToaQuayBenhVien;
//	}
//
//	public void setInitB121_4_KeToaQuayBenhVien(String initB121_4_KeToaQuayBenhVien) {
//		this.initB121_4_KeToaQuayBenhVien = initB121_4_KeToaQuayBenhVien;
//	}
	
	/*
	 * Thanh add 20/05 cho phan quang bao
	 */
	public void displayInforBenhNhanForCall(){
		this.tenBenhNhanForCall = "";
		if (tiepDonForCall.getTiepdonMa() == null 
				|| tiepDonForCall.getTiepdonMa().equals("") 
				|| thamkham.getThamkhamBankham() == null 
				|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null 
				|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("") ){		
			FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tiepDonForCall.getTiepdonMa());
			return;
		}
		
		
		ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
		ThamKham thamkham_tmp = thamkhamDelegate.findByBanKhamVaMaTiepDon( thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), tiepDonForCall.getTiepdonMa());
		if(thamkham_tmp == null){
			FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tiepDonForCall.getTiepdonMa());
			return;
		}
		tiepDonForCall = thamkham_tmp.getTiepdonMa();
		
		if (tiepDonForCall.getBenhnhanMa() != null) {
			this.tenBenhNhanForCall = tiepDonForCall.getBenhnhanMa(true).getBenhnhanHoten();
		}
		
		// 20110718 bao.ttc: khong can search lai tiepdon theo ID vi ban kham cua tiepdon ban dau se khac voi ban kham cua thamkham (TH co chuyen ban kham)
		
//		TiepDonDelegate tiepdonDelegate = TiepDonDelegate.getInstance();
//		TiepDon tiepdon_tmp = tiepdonDelegate.find(tiepDonForCall.getTiepdonMa());
//		if (tiepdon_tmp != null) {
//			if(tiepdon_tmp.getTiepdonBankham() == null ||
//					tiepdon_tmp.getTiepdonBankham(true).getDtdmbankhamMa() == null ||
//					!tiepdon_tmp.getTiepdonBankham(true).getDtdmbankhamMa().equals(thamkham_tmp.getThamkhamBankham(true).getDtdmbankhamMa()) ){						
//				FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tiepDonForCall.getTiepdonMa());
//				return;
//			}
//			tiepDonForCall = tiepdon_tmp;
//			
//			this.tenBenhNhanForCall = tiepdon_tmp.getBenhnhanMa(true).getBenhnhanHoten();
//		}
		
	}
	public TiepDon getTiepDonForCall() {
		return tiepDonForCall;
	}


	public void setTiepDonForCall(TiepDon tiepDonForCall) {
		this.tiepDonForCall = tiepDonForCall;
	}


	public String getTenBenhNhanForCall() {
		return tenBenhNhanForCall;
	}


	public void setTenBenhNhanForCall(String tenBenhNhanForCall) {
		this.tenBenhNhanForCall = tenBenhNhanForCall;
	}
	//end	
}