package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import org.jboss.seam.security.AuthorizationException;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;


@Scope(CONVERSATION)
@Name("B214_Xoathongtinbenhan")
@Synchronized(timeout = 6000000)
public class XoaThongTinBenhAn  implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
	String userLogedIn = "";
	private static final long serialVersionUID = 10L;
	private String ngayhientai = "";	
	private static Logger log = Logger.getLogger(XoaThongTinBenhAn.class);
	
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
		return "DieuTri_CapNhat_XoaThongTinBenhAn";
	}
	
	@End
	public void end(){
		
	}
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	

	private String soBenhAn;
	
	private String gioRaVien;
	private String ngayRaVien;	
	
	private String loaiXoaThongTinBenhAn = "";
	
	
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

		hoSoBenhAnCM = new HsbaChuyenMon();
		SetInforUtil.setInforIfNullForHSBACM(hoSoBenhAnCM);
		
		hoSoBenhAnCMdangDT = new HsbaChuyenMon();
		SetInforUtil.setInforIfNullForHSBACM(hoSoBenhAnCMdangDT);
		
		
		gioVaoVien="";
		ngayVaoVien="";
		
		gioVaoKhoa="";
		ngayVaoKhoa="";
		

		gioChuyenKhoa="";
		ngayChuyenKhoa="";
		
		gioRaVien="";
		ngayRaVien="";
		
		loaiXoaThongTinBenhAn= "";
				
		resultHidden="";
		
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat(FORMAT_DATE);         
		ngayhientai = formatter.format(new Date());
		
	}
	
	private BenhNhan benhNhan;
	
	private Hsba hoSoBenhAn;	

	private String gioVaoVien;
	private String ngayVaoVien;	

	private String gioVaoKhoa;
	private String ngayVaoKhoa;

	private String gioChuyenKhoa;
	private String ngayChuyenKhoa;	

	
	
	private String resultHidden ="";
	
	private HsbaChuyenMon hoSoBenhAnCM;
	private HsbaChuyenMon hoSoBenhAnCMdangDT;
	
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
        formatter = new SimpleDateFormat(FORMAT_DATE); 
        
        Date ngayGioVaoVien = hoSoBenhAn.getHsbaNgaygiovaov();
        if (ngayGioVaoVien!=null){
        	 gioVaoVien =  Utils.getGioPhut(ngayGioVaoVien);//String.valueOf(ngayGioVaoVien.get(Calendar.HOUR_OF_DAY));
		     ngayVaoVien = formatter.format(ngayGioVaoVien.getTime());					        
        }
        
        // 20101130 bao.ttc: Them vao hoSoBenhAnCMdangDT, ngay gio vao khoa dang dieu tri
        Date ngayGioVaoKhoa = hoSoBenhAnCMdangDT.getHsbacmNgaygiovaok();
        if (ngayGioVaoKhoa!=null){
          	 gioVaoKhoa = Utils.getGioPhut(ngayGioVaoKhoa) ;//String.valueOf(ngayGioVaoKhoa.get(Calendar.HOUR_OF_DAY));
   		     ngayVaoKhoa = formatter.format(ngayGioVaoKhoa.getTime());					        
          } 
        
        Date ngayGioCK = hoSoBenhAnCM.getHsbacmNgaygiorak();
        if (ngayGioCK!=null){
        	 gioChuyenKhoa = Utils.getGioPhut(ngayGioCK);//String.valueOf(ngayGioTV.get(Calendar.HOUR_OF_DAY));
		     ngayChuyenKhoa = formatter.format(ngayGioCK.getTime());					        
        }
        
        Date ngayGioRaVien = hoSoBenhAn.getHsbaNgaygiorav();
        
        if (ngayGioRaVien!=null){
        	 gioRaVien =  Utils.getGioPhut(ngayGioRaVien);//String.valueOf(ngayGioRaVien.get(Calendar.HOUR_OF_DAY)); 12:12
		     ngayRaVien = formatter.format(ngayGioRaVien.getTime());	
		     log.info("gioRaVien  :"+gioRaVien);
			 log.info("ngayRaVien :"+ngayRaVien);
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
		    //SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);
		    
		    DmKhoa khoaDangDieuTri = hoSoBenhAn.getHsbaKhoadangdt();
		    if (khoaDangDieuTri == null){
		    	log.info("khoaDangDieuTri == null");
		    	return;
		    }
		    log.info("khoaDangDieuTri != null");
		    benhNhan = hoSoBenhAn.getBenhnhanMa();
			
			//SetInforUtil.setInforIfNullForBN(benhNhan);
			
		    String khoaDangDieuTriMa = khoaDangDieuTri.getDmkhoaMa();
		    log.info("khoaDangDieuTriMa: " + khoaDangDieuTriMa);
		    
		    if (khoaDangDieuTriMa == null || khoaDangDieuTriMa.equals("")){
		    	log.info("khoaDangDieuTri == null or ''");
		    	return;
		    }
		    
		    
		    // 20101130 bao.ttc: tim trong list HSBACM de load HS co chuyen khoa = khoa dang dieu tri
		    HsbaChuyenMonDelegate hsbacmdele = HsbaChuyenMonDelegate.getInstance();
    		List<HsbaChuyenMon> listCM = hsbacmdele.findBySoVaoVien(hoSoBenhAn.getHsbaSovaovien());
    		if (listCM != null){
    			System.out.println("----- List HSBACM Size ----- : " + listCM.size());
    			
    			for (HsbaChuyenMon hsbacm : listCM){
    				//phuc.lc 18-03-2011 : Begin fix bug 2211
    				hoSoBenhAnCM = hsbacm;
    				//phuc.lc 18-03-2011 : End fix bug 2211
                    if(hsbacm.getHsbacmChuyenkhoa() != null){
                    	if(hsbacm.getHsbacmChuyenkhoa().getDmkhoaMa().equals(khoaDangDieuTriMa)){
                    		//hoSoBenhAnCM = hsbacm;
                    		System.out.println("HSBACM Chuyen khoa: " + hsbacm.getHsbacmChuyenkhoa().getDmkhoaMa());
                    		System.out.println("Khoa dang Dieu tri: " + khoaDangDieuTriMa);
                    		//SetInforUtil.setInforIfNullForHSBACM(hoSoBenhAnCM);
                    		break;
                    	} else
                    		System.out.println("Khoa dang Dieu tri: " + khoaDangDieuTriMa + " ### HSBACM Chuyen khoa: " + hsbacm.getHsbacmChuyenkhoa().getDmkhoaMa());
                    }
                }
    		}
    		
		    // bao.ttc: remove ----- load HSBACM cua khoa dang Dieu tri
			HsbaChuyenMon hoSoBenhAnCM_temp = null;
			HsbaChuyenMonDelegate delegate = HsbaChuyenMonDelegate.getInstance();
			hoSoBenhAnCM_temp = delegate.findBySoVaoVien_MaKhoa(hoSoBenhAn.getHsbaSovaovien(), khoaDangDieuTriMa);
			
			if (hoSoBenhAnCM_temp != null) {
				hoSoBenhAnCMdangDT = hoSoBenhAnCM_temp;
				//SetInforUtil.setInforIfNullForHSBACM(hoSoBenhAnCMdangDT);
			}
			// bao.ttc: remove ----- load HSBACM cua khoa dang Dieu tri thi khong co thong tin chuyen khoa
			
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
		
		 
		int hsbadel= 0;
		if (hoSoBenhAn.getHsbaSovaovien() == null || hoSoBenhAn.getHsbaSovaovien().equals("") ||					
				hoSoBenhAnCMdangDT.getKhoaMa().getDmkhoaMa() ==null ||
				hoSoBenhAnCMdangDT.getKhoaMa().getDmkhoaMa().equals("")){				
			resetValue();
			return;
		}


		HsbaChuyenMonDelegate hsbaCMDelegate = HsbaChuyenMonDelegate.getInstance();
		HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
		
		RemoveUtil.removeAllNullFromBN(benhNhan);
		RemoveUtil.removeAllNullFromHSBA(hoSoBenhAn);
		RemoveUtil.removeAllNullFromHSBACM(hoSoBenhAnCM);
		RemoveUtil.removeAllNullFromHSBACM(hoSoBenhAnCMdangDT);
		
        if (loaiXoaThongTinBenhAn != null && !loaiXoaThongTinBenhAn.equals("")){
        	if(identity.hasRole("QT_HeThong")){
	        	if (loaiXoaThongTinBenhAn.equals("1")){ //xoa ngay gio chuyen khoa
		        		/*List<HsbaChuyenMon> lst = hsbaCMDelegate.findBySoVaoVien(hoSoBenhAn.getHsbaSovaovien());
		        		if (lst != null && lst.size() > 1){
		        			hsbaCMDelegate.remove(hoSoBenhAnCMdangDT);
			        		
			        		//set lai khoa dang dieu tri hien hanh trong hsbaCM
		        			// 20110708 bao.ttc: Khoa dang dieu tri dung cho HSBA Chuyen mon, phan biet voi khoa dang DT that su
			        		if (hoSoBenhAnCM.getHsbaSovaovien() != null){
			        			hoSoBenhAn.setHsbaKhoadangdtCm(hoSoBenhAnCM.getKhoaMa());
			        			hsbaDelegate.edit(hoSoBenhAn);
			        			
			        			hoSoBenhAnCM.setHsbacmChuyenkhoa(null);
			        			hoSoBenhAnCM.setHsbacmNgaygiocn(Calendar.getInstance().getTime());
			        			hsbaCMDelegate.edit(hoSoBenhAnCM);
			        		}
	        			
		        		}*/
	        		if(hsbaDelegate.deleteHsbacmCuoi(hoSoBenhAn.getHsbaSovaovien())){
	        			hsbadel = 4;
	        		}else
	        		{
	        			hsbadel = 5;
	        		}
	        		
	        	}else if (loaiXoaThongTinBenhAn.equals("2")){ // xoa ngay xuat vien
	        		/*hoSoBenhAn.setHsbaNgaygiorav(null);
	        		hoSoBenhAn.setHsbaKhoarav(null);
	        		hoSoBenhAn.setHsbaChandoanrav("");
	        		hoSoBenhAn.setHsbaMachdravien(null);
	        		hsbaDelegate.edit(hoSoBenhAn);
	        		*/
	        		if(hsbaDelegate.deleteHsbarv(hoSoBenhAn.getHsbaSovaovien())){
	        			hsbadel = 6;
	        		}else
	        		{
	        			hsbadel = 7;
	        		}
	        		// 20110707 bao.ttc: ko dung ham nay vi ko can thay doi cac entities khac
	        		//soVaoVienInfor = hsbaCMDelegate.capNhatHoSoBenhAn(hoSoBenhAn, hoSoBenhAnCM, null, null, benhNhan);
	        		
	        	}else if (loaiXoaThongTinBenhAn.equals("3")){ // xoa ho so benh an
	        		System.out.println("Xoa ho so benh an");
	        		//hsbaCMDelegate.xoaHSBAChuyenMon(hoSoBenhAn.getHsbaSovaovien());
	        		
		        		if(hsbaDelegate.deleteHsba(hoSoBenhAn.getHsbaSovaovien())){
		        			hsbadel = 1;
		        		}else
		        		{
		        			hsbadel = 2;
		        		}
	        		
	        	}
        	} else {
    			hsbadel = 3;
    		}
        }
		boolean checkSaveLog = false;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
        YteLog yteLog = new YteLog();

   
        yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
        yteLog.setObjectId(hoSoBenhAn.getHsbaSovaovien());
        yteLog.setDateTime(new Date());
        yteLog.setLogString("Khoa nhap vien: "+ (hoSoBenhAn == null ? "NULL" : hoSoBenhAn.getHsbaKhoarav(true).getDmkhoaTen())+
        					" Ngay vao vien: "+ sdf.format(hoSoBenhAn.getHsbaNgaygiorav()) + 
        					" Khoa dang dieu tri: " + (hoSoBenhAn== null ? "NULL" : hoSoBenhAn.getHsbaKhoadangdt(true).getDmkhoaTen())+
        					" Ngay vao khoa: "+ sdf.format(hoSoBenhAnCMdangDT.getHsbacmNgaygiovaok())+
        					" Khoa chuyen den: " + (hoSoBenhAnCM == null ? "NULL" : hoSoBenhAnCM.getKhoaMa(true).getDmkhoaTen())+
        					" Ngay chuyen khoa: "+ sdf.format(hoSoBenhAnCM.getHsbacmNgaygiorak())+
        					" Khoa ra vien: "+(hoSoBenhAn == null ? "NULL" : hoSoBenhAn.getHsbaKhoarav(true).getDmkhoaTen())+
        					" Ngay ra vien: "+ sdf.format(hoSoBenhAnCM.getHsbacmNgaygiorak())
        					);
		
	
		if(hsbadel<1){
		FacesMessages.instance().add(IConstantsRes.PCCTP_DEL_SUC);
		}else	if(hsbadel ==1){
			checkSaveLog = true;
		    //yteLog.setForm("B214_Xoathongtinbenhan");
			FacesMessages.instance().add(IConstantsRes.DELSUCESS_HSBA);
		}else if(hsbadel==2)
		{
			checkSaveLog = false;
			FacesMessages.instance().add(IConstantsRes.DELFAIL_HSBA);
		}else if(hsbadel==3){
			checkSaveLog = false;
			FacesMessages.instance().add(IConstantsRes.DELFAILADMIN_HSBA);
		}else if(hsbadel==4){
			checkSaveLog = true;
			//yteLog.setForm("Xoa_ngay_gio_chuyen_khoa");
			FacesMessages.instance().add(IConstantsRes.DELSUCESS_HSBACHUYENKHOA);
		}else if(hsbadel==5){
			checkSaveLog = false;
			FacesMessages.instance().add(IConstantsRes.DELFAIL_HSBACHUYENKHOA);
		}else if(hsbadel==6){
			checkSaveLog = true;
			//yteLog.setForm("Xoa_ngay_xuat_vien");
			FacesMessages.instance().add(IConstantsRes.DELSUCESS_HSBARAV);
		}else if(hsbadel==7){
			checkSaveLog = false;
			FacesMessages.instance().add(IConstantsRes.DELFAIL_HSBARAV);
		}
		
		if(checkSaveLog){
			yteLog.setForm("B214_Xoathongtinbenhan");
			yteLogDele.create(yteLog);	
		}
		
		resetValue();
		resultHidden="success";

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
	
	public HsbaChuyenMon getHoSoBenhAnCM() {
		return hoSoBenhAnCM;
	}
	public void setHoSoBenhAnCM(HsbaChuyenMon hoSoBenhAnCM) {
		this.hoSoBenhAnCM = hoSoBenhAnCM;
	}
	
	
	public String getGioChuyenKhoa() {
		return gioChuyenKhoa;
	}

	public void setGioChuyenKhoa(String gioChuyenKhoa) {
		this.gioChuyenKhoa = gioChuyenKhoa;
	}

	public String getNgayChuyenKhoa() {
		return ngayChuyenKhoa;
	}

	public void setNgayChuyenKhoa(String ngayChuyenKhoa) {
		this.ngayChuyenKhoa = ngayChuyenKhoa;
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
		XoaThongTinBenhAn.log = log;
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

	public String getLoaiXoaThongTinBenhAn() {
		return loaiXoaThongTinBenhAn;
	}

	public void setLoaiXoaThongTinBenhAn(String loaiXoaThongTinBenhAn) {
		this.loaiXoaThongTinBenhAn = loaiXoaThongTinBenhAn;
	}

	
}


