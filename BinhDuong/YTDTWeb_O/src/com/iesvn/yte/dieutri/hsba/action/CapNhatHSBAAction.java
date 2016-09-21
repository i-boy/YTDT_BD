package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.AuthorizationException;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaNopDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaNop;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmKetQuaDieuTri;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;


@Scope(CONVERSATION)
@Name("B211_Capnhathosobenhan")
@Synchronized(timeout = 6000000)
public class CapNhatHSBAAction  implements Serializable {
	
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
	String userLogedIn = "";
	private static final long serialVersionUID = 10L;
	
	private static Logger log = Logger.getLogger(CapNhatHSBAAction.class);
	
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	private String chonloaiba;
	
	@DataModel
	private List<DmBenhIcd> benhICDList;

	@Begin(join = true)
	public String init() {
		log.info("begin init()");
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		ngayHienTai = formatter.format(new Date());
		resetValue();
		
		log.info("end init()");
		tenChuongTrinh = MyMenuYTDTAction.dieuTri;		
		return "DieuTri_CapNhat_CapNhatThongTinChung";
	}
	
	@End
	public void end(){
		
	}
	
	@In(required = false)
	@Out(required = false)
	private String soBenhAn;
	
	@In(required = false)
	@Out(required = false)
	private String khoaMa;
	
	@In(required = false)
	@Out(required = false)
	private String goToB211_1 = null;
	
	@In(required = false)
	@Out(required = false)
	private String goToB211_2 = null;
	
	@In(required = false)
	@Out(required = false)
	private String goToB211_3 = null;
	
	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
    private YteLog yteLog;
    private String listDataLog;

	
	@In(required = false)
	@Out(required = false)
	Identity identity;	
	
	public String  goToHSBAChiTiet(){
		log.info("Begin goToHSBAChiTiet(), chonloaiba = " + chonloaiba);
		soBenhAn = hoSoBenhAn.getHsbaSovaovien();
		khoaMa = hoSoBenhAnCM.getKhoaMa().getDmkhoaMa();
		
		HsbaChuyenMonDelegate hsbacmDel = HsbaChuyenMonDelegate.getInstance();
		HsbaChuyenMon	hsbaChuyenMon = hsbacmDel.findBySoVaoVien_MaKhoa(soBenhAn, khoaMa);
		
		
		log.info("hsbaChuyenMon = " + hsbaChuyenMon);
		if (hsbaChuyenMon == null){			
			FacesMessages.instance().add( IConstantsRes.ghi_nhan_truoc_ghi_nhap_benh_an_chi_tiet );
			return "";
		}
		
		log.info("Truoc if, chonloaiba = " + chonloaiba);
		
		if ("benhannoi".equals(chonloaiba)){
			goToB211_3 = null;
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBANoi";
		} 
		else if ("benhantmh".equals(chonloaiba)) {			
			log.info("Da chon benhantmh = " + chonloaiba);
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBATmh";
		} 
		else if ("benhanmat".equals(chonloaiba)) {	
			log.info("Da chon benhanmat = " + chonloaiba);
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBAMat";
		}
		else if ("benhanrhm".equals(chonloaiba)) {	
			log.info("Da chon benhanrhm = " + chonloaiba);
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBARhm";
		}
		else if ("benhannhikhoa".equals(chonloaiba)) {	
			log.info("Da chon benhannhikhoa = " + chonloaiba);
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBANhikhoa";
		}
		else if ("benhansosinh".equals(chonloaiba)) {	
			log.info("Da chon benhansosinh = " + chonloaiba);
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBASosinh";
		}
		else if ("benhansankhoa".equals(chonloaiba)) {	
			log.info("Da chon benhansankhoa = " + chonloaiba);
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBASankhoa";
		}
		else if ("benhanphukhoa".equals(chonloaiba)) {	
			log.info("Da chon benhanphukhoa = " + chonloaiba);
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBAPhukhoa";
		}
		else if ("benhanngoaitru_yhoccotruyen".equals(chonloaiba)) {	
			log.info("Da chon benhanngoaitru_yhoccotruyen = " + chonloaiba);
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBANgoaitruYhct";
		}
		else if ("benhannaophathai".equals(chonloaiba)) {	
			log.info("Da chon benhannaophathai = " + chonloaiba);
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBANaophathai";
		}
		else if ("benhannoitru_yhoccotruyen".equals(chonloaiba)) {	
			log.info("Da chon benhannoitru_yhoccotruyen = " + chonloaiba);
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBANoitruYhct";
		}
		else if ("benhanngoai".equals(chonloaiba)) {	
			log.info("Da chon benh an ngoai = " + chonloaiba);
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBANgoai";
		}
		
		return "";
	}
	public String  goToHSBAMo(){
		
		soBenhAn = hoSoBenhAn.getHsbaSovaovien();
		khoaMa = hoSoBenhAnCM.getKhoaMa().getDmkhoaMa();
		
		goToB211_1 = null;
		return "DieuTri_CapNhat_CapNhatThongTinMo";
	}
	
	public String  goToHSBASan(){
		
		soBenhAn = hoSoBenhAn.getHsbaSovaovien();
		khoaMa = hoSoBenhAnCM.getKhoaMa().getDmkhoaMa();
		
		goToB211_2 = null;
		return "DieuTri_CapNhat_CapNhatThongTinSan";
	}
	
	public String getUserLogedIn() {
		return userLogedIn;
	}

	public void setUserLogedIn(String userLogedIn) {
		this.userLogedIn = userLogedIn;
	}

	public String getSoBenhAn() {
		return soBenhAn;
	}

	public void setSoBenhAn(String soBenhAn) {
		this.soBenhAn = soBenhAn;
	}

	public String getKhoaMa() {
		return khoaMa;
	}

	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}
	
	public boolean ableToSeeSomePage(){
		return false;
	}
	
	private boolean ableToSeeSomePage = true;
	
	

	public boolean isAbleToSeeSomePage() {
		return ableToSeeSomePage;
	}

	public void setAbleToSeeSomePage(boolean ableToSeeSomePage) {
		this.ableToSeeSomePage = ableToSeeSomePage;
	}

	/**
	 * 
	 */
	private void resetValue(){
		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);

		hoSoBenhAn = new Hsba();
		SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);

		tiepdon = new TiepDon();
		
		hoSoBenhAnCM = new HsbaChuyenMon();
		SetInforUtil.setInforIfNullForHSBACM(hoSoBenhAnCM);
		
		hoSoBenhAnCV = new HsbaChuyenVien();
		SetInforUtil.setInforIfNullForHsbaChuyenVien(hoSoBenhAnCV);
		
		hoSoBenhAnNop = new HsbaNop();
		SetInforUtil.setInforIfNullForHSBANop(hoSoBenhAnNop);

		tuoi = "";
		ngaySinh="";
		
		gioVaoVien="";
		ngayVaoVien="";
		
		gioVaoKhoa="";
		ngayVaoKhoa="";
		

		gioRaVien="";
		ngayRaVien="";
		
		gioRaKhoa="";
		ngayRaKhoa="";
		
		gioTuVong="";
		ngayTuVong="";
		
		ngayNopBA="";
				
		resultHidden="";
		benhICDList = null;
		
	}
	private Boolean thuThuatPhauThuat;
	private Boolean sinh;
	
	private BenhNhan benhNhan;
	private String tuoi;
	private String ngaySinh;

	private Hsba hoSoBenhAn;
	private TiepDon tiepdon;

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
	
	private String resultHidden ="";
	private String gioi ="";
	
	private HsbaChuyenMon hoSoBenhAnCM;
	private HsbaChuyenVien hoSoBenhAnCV; // ho so benh an chuyen vien
	private HsbaNop hoSoBenhAnNop;
	
	private String ngayNopBA;
	
	//Ly them vo
	private String ngayHienTai;
	//========
	
	/**
	 * 
	 * @throws Exception
	 */
	public void nhaplai() throws Exception {		
		resetValue();		
	}
	
	/**
	 * 
	 */
	private void setOtherValue(){
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat("dd/MM/yyyy"); 
        
        Date ngayGioVaoVien = hoSoBenhAn.getHsbaNgaygiovaov();
        if (ngayGioVaoVien!=null){
        	 gioVaoVien =  Utils.getGioPhut(ngayGioVaoVien);//String.valueOf(ngayGioVaoVien.get(Calendar.HOUR_OF_DAY));
		     ngayVaoVien = formatter.format(ngayGioVaoVien);					        
        }
        
        Date ngayGioRaVien = hoSoBenhAn.getHsbaNgaygiorav();
        if (ngayGioRaVien!=null){
        	 gioRaVien =  Utils.getGioPhut(ngayGioRaVien);//String.valueOf(ngayGioRaVien.get(Calendar.HOUR_OF_DAY)); 12:12
		     ngayRaVien = formatter.format(ngayGioRaVien);					        
        }
        
        Date ngayGioVaoKhoa = hoSoBenhAnCM.getHsbacmNgaygiovaok();
        if (ngayGioVaoKhoa!=null){
          	 gioVaoKhoa = Utils.getGioPhut(ngayGioVaoKhoa) ;//String.valueOf(ngayGioVaoKhoa.get(Calendar.HOUR_OF_DAY));
   		     ngayVaoKhoa = formatter.format(ngayGioVaoKhoa);					        
          } 
        
        Date ngayGioTV = hoSoBenhAn.getHsbaNgaygiotv();
        if (ngayGioTV!=null){
        	 gioTuVong = Utils.getGioPhut(ngayGioTV);//String.valueOf(ngayGioTV.get(Calendar.HOUR_OF_DAY));
		     ngayTuVong = formatter.format(ngayGioTV);					        
        }
                  
        if (benhNhan.getBenhnhanNgaysinh() != null && !benhNhan.getBenhnhanNgaysinh().equals("")){
			ngaySinh = formatter.format(benhNhan.getBenhnhanNgaysinh());
			
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
        //System.out.println("sinh():"+hoSoBenhAnCM.getHsbacmSinh());        
        
        Date ngayGioNop = hoSoBenhAnNop.getHsbanopNgaygionop();
        if (ngayGioNop!=null){
       	   this.ngayNopBA = formatter.format(ngayGioNop);		  					        
       }		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void displayInfor() throws Exception {
			
			log.info("############### hoSoBenhAnCM.getKhoaMa() : " + hoSoBenhAnCM.getKhoaMa());
			if (hoSoBenhAnCM.getKhoaMa() != null){
				log.info("############### hoSoBenhAnCM.getKhoaMa(true).getDmkhoaMa() : " + hoSoBenhAnCM.getKhoaMa(true).getDmkhoaMa());
			}
			
			if (hoSoBenhAn.getHsbaSovaovien() == null || hoSoBenhAn.getHsbaSovaovien().equals("") ||
					
					hoSoBenhAnCM.getKhoaMa().getDmkhoaMa() ==null ||
					hoSoBenhAnCM.getKhoaMa().getDmkhoaMa().equals("")){
				
				resetValue();
				return;
			}
			
			String khoa_tmp = "";
			if(hoSoBenhAnCM.getKhoaMa() != null && hoSoBenhAnCM.getKhoaMa().getDmkhoaMa() != null)
				khoa_tmp = hoSoBenhAnCM.getKhoaMa().getDmkhoaMa();
			String hsba_tmp = "";
			if(hoSoBenhAn.getHsbaSovaovien() != null)
				hsba_tmp = hoSoBenhAn.getHsbaSovaovien();
			
			HsbaChuyenMon hoSoBenhAnCM_temp = null;
			if (!"".equals(Utils.reFactorString(hoSoBenhAnCM.getKhoaMa().getDmkhoaMa()))){
//				HsbaChuyenMonWSService hsbaCmService = new HsbaChuyenMonWSServiceLocator();
//				HsbaChuyenMonWS hsbaCmWS = hsbaCmService.getHsbaChuyenMonWSPort();
				
				HsbaChuyenMonDelegate delegate = HsbaChuyenMonDelegate.getInstance();
				
				
				
				hoSoBenhAnCM_temp = delegate.findBySoVaoVien_MaKhoa(hoSoBenhAn.getHsbaSovaovien(), hoSoBenhAnCM.getKhoaMa().getDmkhoaMa());
				
			}
			
			TiepDonDelegate tdDele = TiepDonDelegate.getInstance();
			
			if (hoSoBenhAnCM_temp == null){
				if (!"".equals(Utils.reFactorString(hoSoBenhAnCM.getKhoaMa().getDmkhoaMa()))){
					// find ti`m hsba voi khoa dang dieu tri
					
					HsbaDelegate hsbadelegate = HsbaDelegate.getInstance();
					Hsba hsba_temp = hsbadelegate.findByHsbaAndKhoaDangDieuTri(hsba_tmp, khoa_tmp);
					if (hsba_temp != null){
						hoSoBenhAn = hsba_temp;
						SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);
						
						benhNhan = hoSoBenhAn.getBenhnhanMa();
						SetInforUtil.setInforIfNullForBN(benhNhan);
						
						if (hoSoBenhAn.getTiepdonMa() != null && !hoSoBenhAn.getTiepdonMa().equals("")){
							tiepdon = tdDele.find(hoSoBenhAn.getTiepdonMa());
							if (tiepdon == null){
								tiepdon = new TiepDon();
							}
						}
						
						setOtherValue();
						//Ly Them Vao
						SimpleDateFormat formatter;	    
				        formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
						if(gioVaoVien=="" && ngayVaoVien == ""){
							Calendar acc = Calendar.getInstance();
							gioVaoVien = Utils.getGioPhut(acc.getTime());
							ngayVaoVien = formatter.format(acc.getTime());
						}
						if(gioVaoKhoa=="" && ngayVaoKhoa == ""){
							Calendar acc = Calendar.getInstance();
							gioVaoKhoa = Utils.getGioPhut(acc.getTime());
							ngayVaoKhoa = formatter.format(acc.getTime());
						}
						return;
					}else{ //20101110 bao.ttc:    hsba_temp == null
						resetValue();
						FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL , hsba_tmp  , khoa_tmp );
						resultHidden="fail";				
						return;
					}
				}else{
					resetValue();
					FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL , hsba_tmp  , khoa_tmp );
					resultHidden="fail";				
					return;
				}
				
			
			}else if (hoSoBenhAnCM_temp != null) {
				
				hoSoBenhAnCM = hoSoBenhAnCM_temp;
				
				// 20111120 bao.ttc: cho phep cap nhat nhieu benh phu
				if (benhICDList != null) {
					if (benhICDList.size() > 0) benhICDList.clear();
				}
				if (hoSoBenhAnCM.getHsbacmBenhphu() != null){
					if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
					benhICDList.add(hoSoBenhAnCM.getHsbacmBenhphu());
					//thamkham.setBenhicd10phu1(null); // 20110701 bao.ttc: remove de In benh phu len Phieu Thanh toan, da set null khi ghi nhan
				}
				if (hoSoBenhAnCM.getHsbacmBenhphu2() != null){
					if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
					benhICDList.add(hoSoBenhAnCM.getHsbacmBenhphu2());
					//thamkham.setBenhicd10phu2(null);
				}
				if (hoSoBenhAnCM.getHsbacmBenhphu3() != null){
					if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
					benhICDList.add(hoSoBenhAnCM.getHsbacmBenhphu3());
					//thamkham.setBenhicd10phu3(null);
				}
				if (hoSoBenhAnCM.getHsbacmBenhphu4() != null){
					if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
					benhICDList.add(hoSoBenhAnCM.getHsbacmBenhphu4());
					//thamkham.setBenhicd10phu4(null);
				}
				if (hoSoBenhAnCM.getHsbacmBenhphu5() != null){
					if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
					benhICDList.add(hoSoBenhAnCM.getHsbacmBenhphu5());
					//thamkham.setBenhicd10phu5(null);
				}
				
				SetInforUtil.setInforIfNullForHSBACM(hoSoBenhAnCM);
				hoSoBenhAn = hoSoBenhAnCM.getHsbaSovaovien();
				SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);
				
				benhNhan = hoSoBenhAn.getBenhnhanMa();
				SetInforUtil.setInforIfNullForBN(benhNhan);
				
				if (hoSoBenhAn.getTiepdonMa() != null && !hoSoBenhAn.getTiepdonMa().equals("")){
					tiepdon = tdDele.find(hoSoBenhAn.getTiepdonMa());
					if (tiepdon == null){
						tiepdon = new TiepDon();
					}
				}
				
				
//				HsbaChuyenVienWSService hsbaChuyenVienService = new HsbaChuyenVienWSServiceLocator();
//				HsbaChuyenVienWS hsbaChuyenVienWS = hsbaChuyenVienService.getHsbaChuyenVienWSPort();
				HsbaChuyenVienDelegate delegate = HsbaChuyenVienDelegate.getInstance();
				
				hoSoBenhAnCV = delegate.findBySoVaoVien(hoSoBenhAn.getHsbaSovaovien());
				if (hoSoBenhAnCV == null){
					hoSoBenhAnCV = new HsbaChuyenVien();
				}
				SetInforUtil.setInforIfNullForHsbaChuyenVien(hoSoBenhAnCV);
				
//				HsbaNopWSService hsbaNopService = new HsbaNopWSServiceLocator();
//				HsbaNopWS hsbaNopWS = hsbaNopService.getHsbaNopWSPort();
				
				HsbaNopDelegate hsbaNopDelegate = HsbaNopDelegate.getInstance();
				
				hoSoBenhAnNop = hsbaNopDelegate.findBySoVaoVien(hoSoBenhAn.getHsbaSovaovien());
				
				if (hoSoBenhAnNop== null){
					hoSoBenhAnNop = new HsbaNop();
				}
				SetInforUtil.setInforIfNullForHSBANop(hoSoBenhAnNop);
				setOtherValue();
				//Ly Them Vao
				SimpleDateFormat formatter;	    
		        formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
				if(gioVaoVien=="" && ngayVaoVien == ""){
					Calendar acc = Calendar.getInstance();
					gioVaoVien = Utils.getGioPhut(acc.getTime());
					ngayVaoVien = formatter.format(acc.getTime());
				}
				if(gioVaoKhoa=="" && ngayVaoKhoa == ""){
					Calendar acc = Calendar.getInstance();
					gioVaoKhoa = Utils.getGioPhut(acc.getTime());
					ngayVaoKhoa = formatter.format(acc.getTime());
				}
				//Ket Thuc
				//System.out.println("----------------------------------------------------");				
			}	
			if(hoSoBenhAnCM.getHsbacmLaphauthuat() != null && hoSoBenhAnCM.getHsbacmLaphauthuat().booleanValue() == true){
				
				this.thuThuatPhauThuat = true;
			}
			
			if(hoSoBenhAnCM.getHsbacmLathuthuat() != null && hoSoBenhAnCM.getHsbacmLathuthuat().booleanValue() == true){
				this.thuThuatPhauThuat = true;
			}
			
			if(hoSoBenhAnCM.getHsbacmLasinh() != null && hoSoBenhAnCM.getHsbacmLasinh().booleanValue() == true){
				this.sinh = true;
			}
			
			
	}
	
	/**
	 * 
	 * @throws Exception 
	 * @throws Exception
	 */
	public void ghiNhan() throws Exception {
		
        
//		if ( hoSoBenhAn.getHsbaSovaovien() != null && !hoSoBenhAn.getHsbaSovaovien().equals("")
//			 &&	hoSoBenhAnCM.getHsbacmNgaygiocn() != null
//		){
//			String userLogedIn = getUsername();
//			ArrayList<String> result = com.iesvn.yte.util.LDAPUtils.getRolesByUser(userLogedIn);
//			
//			boolean hasRightUpdate = false;
//			if (result == null || result.size() == 0){
//				//not in portal
//			}else{
//				for (String role: result){
//					if ( "DT_QT_KhoaNoiTru".equals(role)){
//						hasRightUpdate = true;
//					}
//				}			
//			}
//			if (hasRightUpdate == false){
//				
//				FacesMessages.instance().add(IConstantsRes.COMMON_HAS_NO_ROLE);
//				return;
//			}				
//		}
		
//		try {
		
			log.info("############### hoSoBenhAnCM.getKhoaMa() : " + hoSoBenhAnCM.getKhoaMa());
			if (hoSoBenhAnCM.getKhoaMa() != null){
				log.info("############### hoSoBenhAnCM.getKhoaMa(true).getDmkhoaMa() : " + hoSoBenhAnCM.getKhoaMa(true).getDmkhoaMa());
			}
			
			if (hoSoBenhAn.getHsbaSovaovien() == null || hoSoBenhAn.getHsbaSovaovien().equals("") ||					
					hoSoBenhAnCM.getKhoaMa().getDmkhoaMa() ==null ||
					hoSoBenhAnCM.getKhoaMa().getDmkhoaMa().equals("")){				
				resetValue();
				return;
			}
			
			
			// chuan bi danh muc benh phu
			// xoa cac benh phu dang co'
			hoSoBenhAnCM.setHsbacmBenhphu(null);
			hoSoBenhAnCM.setHsbacmBenhphu2(null);
			hoSoBenhAnCM.setHsbacmBenhphu3(null);
			hoSoBenhAnCM.setHsbacmBenhphu4(null);
			hoSoBenhAnCM.setHsbacmBenhphu5(null);

			yteLog = new YteLog();
			listDataLog="";
			String dsBenhPhu = "";
			// them benh phu tu danh sach
			if (benhICDList != null && benhICDList.size() > 0 ){
				for (DmBenhIcd benh: benhICDList){
					dsBenhPhu+= " \n "+ (benh == null ? " NULL ": benh.getDmbenhicdMa());
					log.info("benh phu "+ benh.getDmbenhicdMa());
					if (hoSoBenhAnCM.getHsbacmBenhphu() == null){
						hoSoBenhAnCM.setHsbacmBenhphu(benh);
						continue;
					}
					if (benh.getDmbenhicdMaso().intValue() == hoSoBenhAnCM.getHsbacmBenhphu(true).getDmbenhicdMaso().intValue()){
						continue;
					}
					if (hoSoBenhAnCM.getHsbacmBenhphu2() == null){
						hoSoBenhAnCM.setHsbacmBenhphu2(benh);
						continue;
					}
					if (hoSoBenhAnCM.getHsbacmBenhphu3() == null){
						hoSoBenhAnCM.setHsbacmBenhphu3(benh);
						continue;
					}
					if (hoSoBenhAnCM.getHsbacmBenhphu4() == null){
						hoSoBenhAnCM.setHsbacmBenhphu4(benh);
						continue;
					}
					if (hoSoBenhAnCM.getHsbacmBenhphu5() == null){
						hoSoBenhAnCM.setHsbacmBenhphu5(benh);
						continue;
					}		
				}
			}


			
			HsbaChuyenMonDelegate hsbaCMDelegate = HsbaChuyenMonDelegate.getInstance();
			
			RemoveUtil.removeAllNullFromBN(benhNhan);
			RemoveUtil.removeAllNullFromHSBA(hoSoBenhAn);
			RemoveUtil.removeAllNullFromHSBACM(hoSoBenhAnCM);
			RemoveUtil.removeAllNullFromHSBACV(hoSoBenhAnCV);
			RemoveUtil.removeAllNullFromHSBANop(hoSoBenhAnNop);
			
			if (gioVaoKhoa != null && !gioVaoKhoa.equals("")  && ngayVaoKhoa != null
					&& !ngayVaoKhoa.equals("")) {
				Calendar hsbaNgaygiovaok = Utils.getDBDate(gioVaoKhoa, ngayVaoKhoa);
				hoSoBenhAnCM.setHsbacmNgaygiovaok(hsbaNgaygiovaok.getTime());
			}else{
				hoSoBenhAnCM.setHsbacmNgaygiovaok(null);
			}
			
			
			// 20101122 bao.ttc: xu ly them cho phan Huong dieu tri
			if(hoSoBenhAnCM.getHsbacmHuongdieutri() != null){
				
				if( hoSoBenhAnCM.getHsbacmHuongdieutri().equals("3") || hoSoBenhAnCM.getHsbacmHuongdieutri().equals("2") ){ // 3: Ra vien, 2: chuyen tuyen tren
					
					if (gioRaVien != null && !gioRaVien.equals("")  && ngayRaVien != null && !ngayRaVien.equals("")) {
						Calendar hsbaNgaygiorav = Utils.getDBDate(gioRaVien, ngayRaVien);
						hoSoBenhAn.setHsbaNgaygiorav(hsbaNgaygiorav.getTime());
						hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiorav.getTime());
						log.info("Co nhap hsbaNgaygiorav");
					}else{ // Truong hop user khong nhap ngay gio ra vien thi mac dinh dung ngay gio hien tai cua he thong
						ngayRaVien = Utils.getCurrentDate();
						gioRaVien = Utils.getGioPhut(new Date());
						Calendar hsbaNgaygiorav = Utils.getDBDate(gioRaVien, ngayRaVien);
						hoSoBenhAn.setHsbaNgaygiorav(hsbaNgaygiorav.getTime());
						hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiorav.getTime());
						log.info("Lay ngay gio hien tai la hsbaNgaygiorav");
					}
					
					if(hoSoBenhAn.getHsbaKhoadangdt() != null)
						hoSoBenhAn.setHsbaKhoarav(hoSoBenhAn.getHsbaKhoadangdt());
					if(hoSoBenhAnCM.getHsbacmBenhchinh() != null)
						hoSoBenhAn.setHsbaMachdravien(hoSoBenhAnCM.getHsbacmBenhchinh());
					
				} // 3: Ra vien, 2: chuyen tuyen tren - END
				
				if(hoSoBenhAnCM.getHsbacmHuongdieutri().equals("1")){ // 1: Chuyen khoa
					
					// 20101104 bao.ttc: neu khong nhap ngay gio chuyen khoa thi set mac dinh la ngay gio he thong
					String ngayChuyenKhoa = Utils.getCurrentDate();
					String gioChuyenKhoa = Utils.getGioPhut(new Date());
					Calendar hsbaNgaygiorak = Utils.getDBDate(gioChuyenKhoa, ngayChuyenKhoa);
					hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiorak.getTime());
					log.info("Huong dieu tri = 1");
				} // 1: Chuyen khoa - END
				
			}
			
			// 20101122 bao.ttc: xu ly them cho phan Huong dieu tri - END
			
			// 20101211 bao.ttc: xu ly cho phan ket qua dieu tri Ma so = 5: Tu vong
			if(hoSoBenhAnCM.getKetquaMa() != null && hoSoBenhAnCM.getKetquaMa().getDmkqdtMaso() == 5){
				
				DmKetQuaDieuTri kqdt = new DmKetQuaDieuTri(5);
				hoSoBenhAn.setHsbaKetqua(kqdt);
				
				if (gioTuVong != null && !gioTuVong.equals("") && ngayTuVong != null && !ngayTuVong.equals("")) {
					Calendar hsbaNgaygiotuvong = Utils.getDBDate(gioTuVong, ngayTuVong);
					hoSoBenhAn.setHsbaNgaygiotv(hsbaNgaygiotuvong.getTime());
					hoSoBenhAn.setHsbaNgaygiorav(hsbaNgaygiotuvong.getTime());
					hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiotuvong.getTime());
				}else{ // Truong hop user khong nhap ngay gio tu vong thi mac dinh dung ngay gio hien tai cua he thong
					ngayTuVong = Utils.getCurrentDate();
					gioTuVong = Utils.getGioPhut(new Date());
					Calendar hsbaNgaygiotuvong = Utils.getDBDate(gioTuVong, ngayTuVong);
					hoSoBenhAn.setHsbaNgaygiotv(hsbaNgaygiotuvong.getTime());
					hoSoBenhAn.setHsbaNgaygiorav(hsbaNgaygiotuvong.getTime());
					hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiotuvong.getTime());
				}
				
				if(hoSoBenhAn.getHsbaNgaygiovaov() != null){
					long diff = ( hoSoBenhAn.getHsbaNgaygiotv().getTime() - hoSoBenhAn.getHsbaNgaygiovaov().getTime() ) / (1000 * 60 * 60);
					if(diff >=0 && diff <= 24){
						hoSoBenhAn.setHsbaTuvvong24g(true);
						log.info("### Tu vong 24G ### - Tu vong sau khi nhap vien (gio): " + diff);
					}
				}
				
				if(hoSoBenhAnCM.getKhoaMa() != null)
					hoSoBenhAn.setHsbaKhoarav(hoSoBenhAnCM.getKhoaMa());
				
				if(hoSoBenhAnCM.getHsbacmBenhchinh() != null){
					hoSoBenhAn.setHsbaMachdravien(hoSoBenhAnCM.getHsbacmBenhchinh());
					// Neu khong nhap ma ICD Tu vong thi lay ICD benh chinh set vao
					if(hoSoBenhAn.getHsbaTuvong() == null || hoSoBenhAn.getHsbaTuvong().getDmbenhicdMaso().equals(""))
						hoSoBenhAn.setHsbaTuvong(hoSoBenhAnCM.getHsbacmBenhchinh());
				}
			}
			// 20101211 bao.ttc: xu ly cho phan ket qua dieu tri Ma so = 5: Tu vong -- END
			
			SimpleDateFormat formatter;	    
	        formatter = new SimpleDateFormat(Utils.FORMAT_DATE); 
	       
	        if (ngayNopBA!=null && !ngayNopBA.equals("")){
	           Date d =	formatter.parse(ngayNopBA);
	           Calendar dCalendar = Calendar.getInstance();
	           dCalendar.setTime(d);
	           hoSoBenhAnNop.setHsbanopNgaygionop(dCalendar.getTime());
	       }else{
	    	   hoSoBenhAnNop.setHsbanopNgaygionop(null);
	       }
			
	        // 20111221 bao.ttc: remove phan nay cho NV_KhoaNoiTru co the cap nhat
	        // nhan vien khoa noi tru' ko duoc phep cap nhat lan 2
//	        if (hoSoBenhAnCM.getHsbacmMa() != null){
//	        	 System.out.println("Identity.instance().hasRole('QT_KhoaNoiTru'):"+Identity.instance().hasRole("QT_KhoaNoiTru"));
//	        	 
//	        	   if (!Identity.instance().hasRole("QT_KhoaNoiTru")){
//						// isThrowExceptionToPages = true;
//	  			     throw new AuthorizationException("");
//	        	   }
//
//	        }
	        
	        hoSoBenhAnCM.setHsbacmNgaygiocn(Calendar.getInstance().getTime());
	        
	        if (tiepdon.getTiepdonMa() != null && !tiepdon.getTiepdonMa().equals("")){
	        	TiepDonDelegate.getInstance().edit(tiepdon);
	        }
	        
			String soVaoVienInfor = hsbaCMDelegate.capNhatHoSoBenhAn(hoSoBenhAn, hoSoBenhAnCM, hoSoBenhAnCV, hoSoBenhAnNop, benhNhan);
			
//			resetValue();
			FacesMessages.instance().add(IConstantsRes.SUCCESS + ":" + soVaoVienInfor );
			resultHidden="success";
			
//			Luu log he thong
	         yteLog.setForm("B211_Capnhathosobenhan");
	         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
	         yteLog.setObjectId(hoSoBenhAn.getHsbaSovaovien());
	        
	         yteLog.setLogString(" Ma benh nhan: "+ benhNhan.getBenhnhanMa() +
	        		 			" Ho ten BN: " + benhNhan.getBenhnhanHoten()+
	        		 			" Khoa: "+ hoSoBenhAnCM.getKhoaMa(true).getDmkhoaMa()+
	        		 			" Vao khoa luc: "+ gioVaoKhoa + " " + ngayVaoKhoa +
	        		 			" Benh Chinh: "+ hoSoBenhAnCM.getHsbacmBenhchinh(true).getDmbenhicdMa()+
	        		 			" Benh phu: "+ dsBenhPhu+
	        		 			" Dieu tri: "+ hoSoBenhAnCM.getHsbacmDieutri()+
	        		 			" Ket qua: "+ hoSoBenhAnCM.getKetquaMa(true).getDmkqdtMa()+
	        		 			" Huong DT: "+ hoSoBenhAnCM.getHsbacmHuongdieutri()+
	        		 			" Chuyen khoa: "+ hoSoBenhAnCM.getHsbacmChuyenkhoa(true).getDmkhoaMa()+
	        		 			" Chuyen vien: "+ hoSoBenhAnCV.getHsbacvChvienden(true).getDmbenhvienMa()+
	        		 			" Ly do chuyen: "+ hoSoBenhAnCV.getHsbacvLydochuyenv(true).getDtdmlydocvMa()+
	        		 			" Ra vien luc: "+ gioRaVien + " "+ ngayRaVien
	         					);
	         yteLog.setDateTime(new Date());
	         yteLog.setListData(listDataLog);
	         
	         yteLogDele.create(yteLog);
		
			
			/**
			 * 
			 *  lam moi he thong, do ko reset
			 * 
			 * 
			 */
			displayInfor() ;
			
			
//		}catch(Exception e){
//			if (isThrowExceptionToPages){
//				 throw new AuthorizationException("Must be admin to perform this action");
//			}
//			System.out.println(e);
//			FacesMessages.instance().add(IConstantsRes.FAIL  );
//			resultHidden="fail";
//		}
		
		//System.out.println("Ghi nhan entered"+hoSoBenhAnCM.getKhoaMa().getDmkhoaMa());
	}
//	private boolean isThrowExceptionToPages = false;
	
	
	public void themBenhphu() {
		
		if (benhICDList == null) benhICDList = new ArrayList<DmBenhIcd>();
		
		if (benhICDList.size() >= 5){
			return;
		}
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		
		
		DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMa(hoSoBenhAnCM.getHsbacmBenhphu(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
		
		if (benh == null){
			return;
		}
		
		if (!isContain(benhICDList, benh)){
			this.benhICDList.add(benh);
			hoSoBenhAnCM.setHsbacmBenhphu(new DmBenhIcd());
		}
	}
	
	
	private boolean isContain( List<DmBenhIcd> lstBenh,DmBenhIcd benhtest){
		boolean ketqua = false;
		if (lstBenh != null){
			for (DmBenhIcd benh: lstBenh){
				if (benhtest.getDmbenhicdMaso().intValue() == benh.getDmbenhicdMaso().intValue()){
					return true;
				}
			}
		}
		return ketqua;
	}
	
	
	public void deleteCurrentRow(int vitri) {
		if (this.benhICDList == null || this.benhICDList.size() == 0) {
			return;
		}		
		this.benhICDList.remove(vitri);
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String getFORMAT_DATE_TIME() {
		return FORMAT_DATE_TIME;
	}
	public static void setFORMAT_DATE_TIME(String format_date_time) {
		FORMAT_DATE_TIME = format_date_time;
	}

	
	public Boolean getThuThuatPhauThuat() {
		return thuThuatPhauThuat;
	}

	public void setThuThuatPhauThuat(Boolean thuThuatPhauThuat) {
		this.thuThuatPhauThuat = thuThuatPhauThuat;
	}

	public Boolean getSinh() {
		return sinh;
	}

	public void setSinh(Boolean sinh) {
		this.sinh = sinh;
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
	
	public String getNgaySinh() {
		return ngaySinh;
	}
	
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	
	public Hsba getHoSoBenhAn() {
		return hoSoBenhAn;
	}
	
	public void setHoSoBenhAn(Hsba hoSoBenhAn) {
		this.hoSoBenhAn = hoSoBenhAn;
	}
	
	public TiepDon getTiepdon() {
		return tiepdon;
	}

	public void setTiepdon(TiepDon tiepdon) {
		this.tiepdon = tiepdon;
	}

	public String getGioVaoVien() {
		return gioVaoVien;
	}
	
	public void setGioVaoVien(String gioVaoVien) {
		this.gioVaoVien = gioVaoVien;
	}
	
	public String getNgayVaoVien() {
		return ngayVaoVien;
	}
	
	public void setNgayVaoVien(String ngayVaoVien) {
		this.ngayVaoVien = ngayVaoVien;
	}
	
	public String getGioVaoKhoa() {
		return gioVaoKhoa;
	}
	
	public void setGioVaoKhoa(String gioVaoKhoa) {
		this.gioVaoKhoa = gioVaoKhoa;
	}
	
	public String getNgayVaoKhoa() {
		return ngayVaoKhoa;
	}
	
	public void setNgayVaoKhoa(String ngayVaoKhoa) {
		this.ngayVaoKhoa = ngayVaoKhoa;
	}
	
	public String getGioRaVien() {
		return gioRaVien;
	}
	
	public void setGioRaVien(String gioRaVien) {
		this.gioRaVien = gioRaVien;
	}
	
	public String getNgayRaVien() {
		return ngayRaVien;
	}
	
	public void setNgayRaVien(String ngayRaVien) {
		this.ngayRaVien = ngayRaVien;
	}
	
	public String getGioRaKhoa() {
		return gioRaKhoa;
	}
	
	public void setGioRaKhoa(String gioRaKhoa) {
		this.gioRaKhoa = gioRaKhoa;
	}
	
	public String getNgayRaKhoa() {
		return ngayRaKhoa;
	}
	
	public void setNgayRaKhoa(String ngayRaKhoa) {
		this.ngayRaKhoa = ngayRaKhoa;
	}
	
	public String getGioTuVong() {
		return gioTuVong;
	}
	
	public void setGioTuVong(String gioTuVong) {
		this.gioTuVong = gioTuVong;
	}
	
	public String getNgayTuVong() {
		return ngayTuVong;
	}
	
	public void setNgayTuVong(String ngayTuVong) {
		this.ngayTuVong = ngayTuVong;
	}
	
	public String getResultHidden() {
		return resultHidden;
	}
	
	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}
	
	public String getGioi() {
		return gioi;
	}
	
	public void setGioi(String gioi) {
		this.gioi = gioi;
	}
	
	public HsbaChuyenMon getHoSoBenhAnCM() {
		return hoSoBenhAnCM;
	}
	
	public void setHoSoBenhAnCM(HsbaChuyenMon hoSoBenhAnCM) {
		this.hoSoBenhAnCM = hoSoBenhAnCM;
	}
	
	public HsbaChuyenVien getHoSoBenhAnCV() {
		return hoSoBenhAnCV;
	}
	
	public void setHoSoBenhAnCV(HsbaChuyenVien hoSoBenhAnCV) {
		this.hoSoBenhAnCV = hoSoBenhAnCV;
	}
	
	public HsbaNop getHoSoBenhAnNop() {
		return hoSoBenhAnNop;
	}
	
	public void setHoSoBenhAnNop(HsbaNop hoSoBenhAnNop) {
		this.hoSoBenhAnNop = hoSoBenhAnNop;
	}
	
	public String getNgayNopBA() {
		return ngayNopBA;
	}
	
	public void setNgayNopBA(String ngayNopBA) {
		this.ngayNopBA = ngayNopBA;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNgayHienTai() {
		return ngayHienTai;
	}

	public void setNgayHienTai(String ngayHienTai) {
		this.ngayHienTai = ngayHienTai;
	}

	public String getGoToB211_1() {
		return goToB211_1;
	}

	public void setGoToB211_1(String goToB211_1) {
		this.goToB211_1 = goToB211_1;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		CapNhatHSBAAction.log = log;
	}

	public String getGoToB211_2() {
		return goToB211_2;
	}

	public void setGoToB211_2(String goToB211_2) {
		this.goToB211_2 = goToB211_2;
	}

	public String getChonloaiba() {
		return chonloaiba;
	}

	public void setChonloaiba(String chonloaiba) {
		this.chonloaiba = chonloaiba;
	}
	
}


