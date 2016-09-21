package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaNopDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaNop;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;


@Scope(CONVERSATION)
@Name("B215_Luutrubenhan")
@Synchronized(timeout = 6000000)
public class LuuTruBenhAn  implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
	String userLogedIn = "";
	private static final long serialVersionUID = 10L;
	private String ngayhientai = "";	
	private static Logger log = Logger.getLogger(LuuTruBenhAn.class);
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
	@Create
	@Begin(join = true)
	public String init() {
		log.info("begin init()");
		//Ly THEM VO ===========
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		ngayHienTai = formatter.format(new Date());		
		
		//Ket Thuc =======
		resetValue();

		log.info("end init()");
		return "DieuTri_CapNhat_LuuTruBenhAn";
	}
	
	@End
	public void end(){
		
	}
	

	private String soBenhAn;
	private String ngayLuuTru;
	
	private String tuoi;
	private String ngaySinh;
	
	
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

		
	
	/**
	 * 
	 */
	private void resetValue(){
		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);

		hoSoBenhAn = new Hsba();
		SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);

		hsbaChuyenMon = new HsbaChuyenMon();
		SetInforUtil.setInforIfNullForHSBACM(hsbaChuyenMon);
		
		hsbaNop = new HsbaNop();
		SetInforUtil.setInforIfNullForHSBANop(hsbaNop);
		
		hsbaCV = new HsbaChuyenVien();
		SetInforUtil.setInforIfNullForHsbaChuyenVien(hsbaCV);
		
		
		gioVaoVien="";
		ngayVaoVien="";
		
		gioVaoKhoa="";
		ngayVaoKhoa="";
		

		gioRaVien="";
		ngayRaVien="";
		
				
		resultHidden="";
		
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat(FORMAT_DATE);         
		ngayhientai = formatter.format(new Date());
		
		ngayLuuTru = ngayhientai;
		
		ngaySinh = "";
		
	}
	
	private BenhNhan benhNhan;
	
	private Hsba hoSoBenhAn;
	
	private HsbaChuyenMon hsbaChuyenMon;
	
	private HsbaNop hsbaNop;
	private HsbaChuyenVien hsbaCV;

	private String gioVaoVien;
	private String ngayVaoVien;	

	private String gioVaoKhoa;
	private String ngayVaoKhoa;

	private String gioRaVien;
	private String ngayRaVien;		

	private String gioi ="";
	
	private String resultHidden ="";
	
	
	//Ly them vo
	private String ngayHienTai;
	//========
	
	private int soLuongBenhAnTrongNgay;
	
	public int getSoLuongBenhAnTrongNgay() {
		return soLuongBenhAnTrongNgay;
	}

	public void setSoLuongBenhAnTrongNgay(int soLuongBenhAnTrongNgay) {
		this.soLuongBenhAnTrongNgay = soLuongBenhAnTrongNgay;
	}

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
        formatter = new SimpleDateFormat(FORMAT_DATE); 
        
        Date ngayGioVaoVien = hoSoBenhAn.getHsbaNgaygiovaov();
        if (ngayGioVaoVien!=null){
        	 gioVaoVien =  Utils.getGioPhut(ngayGioVaoVien);//String.valueOf(ngayGioVaoVien.get(Calendar.HOUR_OF_DAY));
		     ngayVaoVien = formatter.format(ngayGioVaoVien.getTime());					        
        }
        
        
       
        Date ngayGioRaVien = hoSoBenhAn.getHsbaNgaygiorav();
        if (ngayGioRaVien!=null){
        	 gioRaVien =  Utils.getGioPhut(ngayGioRaVien);//String.valueOf(ngayGioRaVien.get(Calendar.HOUR_OF_DAY)); 12:12
		     ngayRaVien = formatter.format(ngayGioRaVien.getTime());					        
        }
        
        if (benhNhan.getBenhnhanNgaysinh() != null && !benhNhan.getBenhnhanNgaysinh().equals("")){
			ngaySinh = formatter.format(benhNhan.getBenhnhanNgaysinh().getTime());
			
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
        
        Date ngayGioLuuTru = hsbaNop.getHsbanopNgaygioluutru();
        if (ngayGioLuuTru!=null){       	
        	ngayLuuTru = formatter.format(ngayGioLuuTru.getTime());					        
       }
         
                  
       	
	}
	
	
	
	/**
	 * 
	 * @throws Exception
	 */
	public void displayInfor() throws Exception {
			
			
			if (hoSoBenhAn.getHsbaSovaovien() == null || hoSoBenhAn.getHsbaSovaovien().equals("") ){
				log.info("hoSoBenhAn.getHsbaSovaovien() == null");
				resetValue();
				return;
			}
			
//			String khoa_tmp = hoSoBenhAnCM.getKhoaMa().getDmkhoaMa();
//			String hsba_tmp = hoSoBenhAn.getHsbaSovaovien();
			
			HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
			Hsba hsbaCur = hsbaDelegate.find(hoSoBenhAn.getHsbaSovaovien());
		    if  (hsbaCur == null){
		    	FacesMessages.instance().add(IConstantsRes.SOBENHAN_NOTFOUND);
				log.info("khong tim thay sobenhan");
				return ;
		    }
		    log.info(" tim thay sobenhan");
		    
		    // hsba da ton tai
		    hoSoBenhAn = hsbaCur;
		    
		    HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();
		    
		    hsbaChuyenMon = hsbaChuyenMonDelegate.findBySoVaoVien_MaKhoa(hoSoBenhAn.getHsbaSovaovien(), hoSoBenhAn.getHsbaKhoadangdtCm(true).getDmkhoaMa());
		    
		    SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);
		    
		   
		    benhNhan = hoSoBenhAn.getBenhnhanMa();
			
			SetInforUtil.setInforIfNullForBN(benhNhan);
			
			HsbaNop hoSoBenhAnNop_temp = null;
			HsbaNopDelegate delegate = HsbaNopDelegate.getInstance();
			
	        // 20101202 bao.ttc: load so luong HSBA nop trong ngay
			soLuongBenhAnTrongNgay = delegate.soBAtrongngay(new Date());
			hoSoBenhAnNop_temp = delegate.findBySoVaoVien(hoSoBenhAn.getHsbaSovaovien());
			
			SimpleDateFormat formatter;	    
	        formatter = new SimpleDateFormat(FORMAT_DATE);
	        
			if (hoSoBenhAnNop_temp != null) {
				hsbaNop = hoSoBenhAnNop_temp;
				SetInforUtil.setInforIfNullForHSBANop(hsbaNop);
				
				  Date dNgayGioLuuTru = hsbaNop.getHsbanopNgaygioluutru();
			        if (dNgayGioLuuTru!=null){
			        	 ngayLuuTru = formatter.format(dNgayGioLuuTru.getTime());					        
			        }
			        
				System.out.println("---- hoSoBenhAnNop_temp != null ----");				
			}else{
				hsbaNop.setHsbanopNgaygioluutru(new Date());
			}
			
			HsbaChuyenVien hoSoBenhAnCV_temp = null;
			
			HsbaChuyenVienDelegate delegateCV = HsbaChuyenVienDelegate.getInstance();
			hoSoBenhAnCV_temp = delegateCV.findBySoVaoVien(hoSoBenhAn.getHsbaSovaovien());
			
			
			if (hoSoBenhAnCV_temp != null) {
				hsbaCV = hoSoBenhAnCV_temp;
				SetInforUtil.setInforIfNullForHsbaChuyenVien(hsbaCV);
				System.out.println("---- hoSoBenhAnCV_temp != null ----");				
			}	
			
			setOtherValue();
			
	}
	
	/**
	 * 
	 * @throws ServiceException 
	 * @throws ParseException 
	 * @throws RemoteException 
	 * @throws Exception
	 */
	public void ghiNhan() throws ServiceException, ParseException, RemoteException {
		
		 
				
			if (hoSoBenhAn.getHsbaSovaovien() == null || hoSoBenhAn.getHsbaSovaovien().equals("") ){				
				resetValue();
				return;
			}
	
			if (hsbaNop.getHsbanopSoluutru() !=null && !hsbaNop.getHsbanopSoluutru().equals("") ){
				return;
			}
			
			
			RemoveUtil.removeAllNullFromHSBA(hoSoBenhAn);
			RemoveUtil.removeAllNullFromHSBACV(hsbaCV);
			RemoveUtil.removeAllNullFromHSBANop(hsbaNop);
			RemoveUtil.removeAllNullFromHSBACM(hsbaChuyenMon);
			
			
			// 20101202 bao.ttc: khong set lai ngay gio vao vien / ra vien vi o day chi load lai thong tin de xem 
			
//			if (gioVaoVien != null && !gioVaoVien.equals("")  && ngayVaoVien != null
//					&& !ngayVaoVien.equals("")) {
//				Calendar hsbaNgaygiovaov = Utils.getDBDate(gioVaoVien, ngayVaoVien);
//				hoSoBenhAn.setHsbaNgaygiovaov(hsbaNgaygiovaov.getTime());
//			}else{
//				hoSoBenhAn.setHsbaNgaygiovaov(null);
//			}
//			
//			if (gioRaVien != null && !gioRaVien.equals("")  && ngayRaVien != null
//					&& !ngayRaVien.equals("")) {
//				Calendar hsbaNgaygiorav = Utils.getDBDate(gioRaVien, ngayRaVien);
//				hoSoBenhAn.setHsbaNgaygiorav(hsbaNgaygiorav.getTime());
//			}else{
//				hoSoBenhAn.setHsbaNgaygiorav(null);
//			}
			
			if (ngayLuuTru != null && !ngayLuuTru.equals("") ) {
				Calendar hsbaNopLuuTru = Utils.getDBDate("00:00", ngayLuuTru);
				hsbaNop.setHsbanopNgaygioluutru(hsbaNopLuuTru.getTime());
				hoSoBenhAn.setHsbaNgaynop(hsbaNopLuuTru.getTime());		// 20101203 bao.ttc: set HSBA ngaynop de lam bao cao
			}else{
				hsbaNop.setHsbanopNgaygioluutru(new Date());
				hoSoBenhAn.setHsbaNgaynop(new Date());		// 20101203 bao.ttc: set HSBA ngaynop de lam bao cao
			}
			
	        
	        HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
	        
	        
	        // 20101218 bao.ttc: ko truyen hsbaCV vao vi form nay chi xem, khong sua thong tin chuyen vien
			String hsbanopSoluutru = hsbaDelegate.capNhatThongTinHSBA(hoSoBenhAn,  hsbaNop, null);
			hsbaNop.setHsbanopSoluutru(hsbanopSoluutru);
			
//			Luu log he thong
			 YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
	         YteLog yteLog = new YteLog();

	         yteLog.setForm("B215_Luutrubenhan");
	         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
	         yteLog.setObjectId(hoSoBenhAn.getHsbaSovaovien());
	         yteLog.setLogString("So luu tru: "+ (hsbaNop == null ? "NULL" : hsbaNop.getHsbanopSoluutru())+ "\n"+
	        		 			 "Ngay luu tru: " + hsbaNop.getHsbanopNgaygioluutru());
	         yteLog.setDateTime(new Date());

	         yteLogDele.create(yteLog);
	         

			
			//resetValue();
			FacesMessages.instance().add(IConstantsRes.SUCCESS + ":" + hsbanopSoluutru);
			resultHidden="success";
			
	        // 20101202 bao.ttc: load so luong HSBA nop trong ngay
			HsbaNopDelegate delegate = HsbaNopDelegate.getInstance();
			soLuongBenhAnTrongNgay = delegate.soBAtrongngay(new Date());
		        
		
	}

	

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

	


	public BenhNhan getBenhNhan() {
		return benhNhan;
	}
	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}
	
	public Hsba getHoSoBenhAn() {
		return hoSoBenhAn;
	}
	public void setHoSoBenhAn(Hsba hoSoBenhAn) {
		this.hoSoBenhAn = hoSoBenhAn;
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
	
	public String getResultHidden() {
		return resultHidden;
	}
	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNgayHienTai() {
		return ngayHienTai;
	}

	public void setNgayHienTai(String ngayHienTai) {
		this.ngayHienTai = ngayHienTai;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		LuuTruBenhAn.log = log;
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

	public HsbaNop getHsbaNop() {
		return hsbaNop;
	}

	public void setHsbaNop(HsbaNop hsbaNop) {
		this.hsbaNop = hsbaNop;
	}

	public String getGioi() {
		return gioi;
	}

	public void setGioi(String gioi) {
		this.gioi = gioi;
	}

	public HsbaChuyenVien getHsbaCV() {
		return hsbaCV;
	}

	public void setHsbaCV(HsbaChuyenVien hsbaCV) {
		this.hsbaCV = hsbaCV;
	}

	public String getNgayLuuTru() {
		return ngayLuuTru;
	}

	public void setNgayLuuTru(String ngayLuuTru) {
		this.ngayLuuTru = ngayLuuTru;
	}

	public HsbaChuyenMon getHsbaChuyenMon() {
		return hsbaChuyenMon;
	}

	public void setHsbaChuyenMon(HsbaChuyenMon hsbaChuyenMon) {
		this.hsbaChuyenMon = hsbaChuyenMon;
	}

	
}


