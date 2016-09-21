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
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.AuthorizationException;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;


@Scope(CONVERSATION)
@Name("B213_Capnhatthongtinchuyenkhoa")
@Synchronized(timeout = 6000000)
public class CapNhatThongTinChuyenKhoa  implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
	String userLogedIn = "";
	private static final long serialVersionUID = 10L;
	private String ngayhientai = "";	
	private String maChuyenKhoa;
	private Integer masoChuyenKhoa;
	private static Logger log = Logger.getLogger(CapNhatThongTinChuyenKhoa.class);
	
	@Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
	@Create
	@Begin(join = true)
	public String init() {
		log.info("begin init()");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		ngayHienTai = formatter.format(new Date());		
		
		resetValue();

		log.info("end init()");
		
		return "DieuTri_CapNhat_CapNhatThongTinChuyenKhoa";
	}
	

	@End
	public void end(){
		
	}
	

	private String soBenhAn;
	
	
	
	
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
		
		
		gioVaoVien="";
		ngayVaoVien="";
		
		gioVaoKhoa="";
		ngayVaoKhoa="";
		
		Date date = new Date();
		gioChuyenKhoa = Utils.getGioPhut(date);
		ngayChuyenKhoa = Utils.getCurrentDate();
		
		khoaVaoVien = khoaDangDT = "";
				
		resultHidden="";
		
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat(FORMAT_DATE);         
		ngayhientai = formatter.format(new Date());
		maChuyenKhoa = "";
		masoChuyenKhoa = null;
	}
	
	private BenhNhan benhNhan;
	
	private Hsba hoSoBenhAn;	

	private String gioVaoVien;
	private String ngayVaoVien;	

	private String gioVaoKhoa;
	private String ngayVaoKhoa;

	private String gioChuyenKhoa;
	private String ngayChuyenKhoa;	
	
	private String khoaVaoVien;
	private String khoaDangDT;
	
	
	private String resultHidden ="";
	
	private HsbaChuyenMon hoSoBenhAnCM;
	
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
		     // phuc.lc add code below
		     // Khi chua chuyen khoa thi ngay gio vao khoa chinh la ngay gio vao vien
		     gioVaoKhoa = Utils.getGioPhut(ngayGioVaoVien) ;
   		     ngayVaoKhoa = formatter.format(ngayGioVaoVien.getTime());	
   		     // phuc.lc END
        }
        
        if (hoSoBenhAnCM != null) {
        	Date ngayGioVaoKhoa = hoSoBenhAnCM.getHsbacmNgaygiovaok();
        	
	        if (ngayGioVaoKhoa!=null){
	          	 gioVaoKhoa = Utils.getGioPhut(ngayGioVaoKhoa) ;
	   		     ngayVaoKhoa = formatter.format(ngayGioVaoKhoa.getTime());					        
	          }
	     
        }          
       	
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void displayInfor() throws Exception {
			
			System.out.println("----------00----------------------------"+hoSoBenhAnCM.getHsbacmChuyenkhoa());
			
			if (hoSoBenhAn.getHsbaSovaovien() == null || hoSoBenhAn.getHsbaSovaovien().equals("") ){
				log.info("hoSoBenhAn.getHsbaSovaovien() == null");
				resetValue();
				return;
			}			
			
			HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
			Hsba hsbaCur = hsbaDelegate.find(hoSoBenhAn.getHsbaSovaovien());
		    if  (hsbaCur == null){
		    	FacesMessages.instance().add(IConstantsRes.SOBENHAN_NOTFOUND);
				log.info("khong tim thay sobenhan");
				return ;
		    }
		    log.info(" tim thay sobenhan");
		    
		    // hsba da ton tai
		    hoSoBenhAn= hsbaCur;
		    SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);
		    
		    DmKhoa khoaDangDieuTri = hoSoBenhAn.getHsbaKhoadangdt();
		    if (khoaDangDieuTri == null){
		    	log.info("khoaDangDieuTri == null");
		    	return;
		    // phuc.lc BEGIN add code	
		    } else {
		    	khoaDangDT = khoaDangDieuTri.getDmkhoaTen();
		    }
		    khoaVaoVien = (hoSoBenhAn.getHsbaKhoavaov() == null ? "" : hoSoBenhAn.getHsbaKhoavaov().getDmkhoaTen());
		    // phuc.lc END
		    log.info("khoaDangDieuTri != null");
		    benhNhan = hoSoBenhAn.getBenhnhanMa();
			
			SetInforUtil.setInforIfNullForBN(benhNhan);
			
		    String khoaDangDieuTriMa = khoaDangDieuTri.getDmkhoaMa();
		    log.info("khoaDangDieuTriMa:"+khoaDangDieuTriMa);
		    
		    if (khoaDangDieuTriMa == null || khoaDangDieuTriMa.equals("")){
		    	log.info("khoaDangDieuTri == null or ''");
		    	return;
		    }
		    		 		
			HsbaChuyenMon hoSoBenhAnCM_temp = null;
				
			HsbaChuyenMonDelegate delegate = HsbaChuyenMonDelegate.getInstance();
			hoSoBenhAnCM_temp = delegate.findBySoVaoVien_MaKhoa(hoSoBenhAn.getHsbaSovaovien(), khoaDangDieuTriMa);
			
			if (hoSoBenhAnCM_temp != null) {
				hoSoBenhAnCM = hoSoBenhAnCM_temp;
				SetInforUtil.setInforIfNullForHSBACM(hoSoBenhAnCM);
			} 
			log.info("ma Chuyen khoa = " + maChuyenKhoa + ", ma so = " + masoChuyenKhoa);
			//phuc.lc add line below
			setOtherValue();
			log.info("khoaDangDieuTriMa--------:"+khoaDangDieuTriMa);
			
	}
	
	public String getKhoaVaoVien() {
		return khoaVaoVien;
	}


	


	/**
	 * 
	 * @throws ServiceException 
	 * @throws ParseException 
	 * @throws RemoteException 
	 * @throws Exception
	 */
	public void ghiNhan()  {
		
			if (hoSoBenhAn.getHsbaSovaovien() == null || hoSoBenhAn.getHsbaSovaovien().equals("") ||					
					maChuyenKhoa.equals("") || masoChuyenKhoa == null){
				log.info("Begin ghinhan(), hoSoBenhAn.getHsbaSovaovien() = " + hoSoBenhAn.getHsbaSovaovien() + ", ma chuyen khoa = " + maChuyenKhoa + ", ma so = " + masoChuyenKhoa);
				resetValue();
				return;
			}
	

			HsbaChuyenMonDelegate hsbaCMDelegate = HsbaChuyenMonDelegate.getInstance();
			
			RemoveUtil.removeAllNullFromBN(benhNhan);
			RemoveUtil.removeAllNullFromHSBA(hoSoBenhAn);
			RemoveUtil.removeAllNullFromHSBACM(hoSoBenhAnCM);
			
			if (gioVaoKhoa != null && !gioVaoKhoa.equals("")  && ngayVaoKhoa != null
					&& !ngayVaoKhoa.equals("")) {
				Calendar hsbaNgaygiovaok = Utils.getDBDate(gioVaoKhoa, ngayVaoKhoa);
				hoSoBenhAnCM.setHsbacmNgaygiovaok(hsbaNgaygiovaok.getTime());
			}else{
				hoSoBenhAnCM.setHsbacmNgaygiovaok(null);
			}

			if (gioVaoVien != null && !gioVaoVien.equals("")  && ngayVaoVien != null
					&& !ngayVaoVien.equals("")) {
				Calendar hsbaNgaygiovaov = Utils.getDBDate(gioVaoVien, ngayVaoVien);
				hoSoBenhAn.setHsbaNgaygiovaov(hsbaNgaygiovaov.getTime());
			}else{
				hoSoBenhAn.setHsbaNgaygiovaov(null);
			}
			//log.info("gioChuyenKhoa = " + gioChuyenKhoa + ", ngayChuyenKhoa = " + ngayChuyenKhoa);
			if (gioChuyenKhoa != null && !gioChuyenKhoa.equals("")  && ngayChuyenKhoa != null
					&& !ngayChuyenKhoa.equals("")) {
				Calendar hsbaNgaygiorak = Utils.getDBDate(gioChuyenKhoa, ngayChuyenKhoa);
				hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiorak.getTime());				
			}else{
				// 20101104 bao.ttc: neu khong nhap ngay gio chuyen khoa thi set mac dinh la ngay gio he thong
				ngayChuyenKhoa = Utils.getCurrentDate();
				Date date = new Date();
				gioChuyenKhoa = Utils.getGioPhut(date);
				Calendar hsbaNgaygiorak = Utils.getDBDate(gioChuyenKhoa, ngayChuyenKhoa);
				hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiorak.getTime());
			}
			
			
			if (masoChuyenKhoa != null){
				// get thong tin chuyen khoa 
				DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();				
				DmKhoa dmkhoa = (DmKhoa)dtDele.findByMaSo(masoChuyenKhoa, "DmKhoa", "dmkhoaMaso");
				log.info("dmkhoa = " +dmkhoa);
				hoSoBenhAnCM.setHsbacmChuyenkhoa(dmkhoa);
				
			}
	        
	        hoSoBenhAnCM.setHsbacmNgaygiocn(Calendar.getInstance().getTime());
	        
	        
			String soVaoVienInfor = hsbaCMDelegate.capNhatHoSoBenhAn(hoSoBenhAn, hoSoBenhAnCM, null, null, benhNhan);
			
			FacesMessages.instance().add(IConstantsRes.SUCCESS + ":" + soVaoVienInfor );
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
	public void setKhoaVaoVien(String khoaVaoVien) {
		this.khoaVaoVien = khoaVaoVien;
	}


	public String getKhoaDangDT() {
		return khoaDangDT;
	}

	public void setKhoaDangDT(String khoaDangDT) {
		this.khoaDangDT = khoaDangDT;
	}


	public String getMaChuyenKhoa() {
		return maChuyenKhoa;
	}


	public void setMaChuyenKhoa(String maChuyenKhoa) {
		this.maChuyenKhoa = maChuyenKhoa;
	}


	public Integer getMasoChuyenKhoa() {
		return masoChuyenKhoa;
	}


	public void setMasoChuyenKhoa(Integer masoChuyenKhoa) {
		this.masoChuyenKhoa = masoChuyenKhoa;
	}
	
}


