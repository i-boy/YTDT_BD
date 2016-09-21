package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B216_Capnhatthongtinhanhchanhbenhnhan")
@Synchronized(timeout = 6000000)
public class CapNhatThongTinHanhChanhBenhNhan  implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
	private String msgFail = "";
	private String msgSuccess = "";
	

	private static Logger log = Logger.getLogger(CapNhatThongTinHanhChanhBenhNhan.class);
	
	private static final long serialVersionUID = 10L;
	
	private String resultHidden ="";	
	
	private String ngayhientai = "";	
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@Begin (join = true)
	public String init(String loaiCT) {	
		if ("DieuTri".equals(loaiCT)){
			tenChuongTrinh = MyMenuYTDTAction.dieuTri;
		}else { //ThuVienPhi
			tenChuongTrinh = MyMenuYTDTAction.thuVienPhi;
		}
		resetValue();	
		return "DieuTri_CapNhat_CapNhatThongTinHanhChinhBenhNhan";
	}
	
	@End
	public void end(){
		
	}
	
	private void setOtherInfor (){
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat(FORMAT_DATE);
        
        if (hsbaBHYT != null){
        	Date dGiaTri1 = hsbaBHYT.getHsbabhytGiatri0();
            if (dGiaTri1 != null){
    	        giatri1 = formatter.format(dGiaTri1);
    	    }else{
    	    	giatri1 = "";
    	    }
            
            Date dGiaTri2 = hsbaBHYT.getHsbabhytGiatri1();
            if (dGiaTri2 != null){
    	        giatri2 = formatter.format(dGiaTri2);
    	    }else{
    	    	giatri2 = "";
    	    }
            
            Date dGiaTri3 = hsbaBHYT.getHsbabhytGiatri2();
            if (dGiaTri3 != null){
    	        giatri3 = formatter.format(dGiaTri3);
    	    }else{
    	    	giatri3 = "";
    	    }
            
            Date dGiaTri4 = hsbaBHYT.getHsbabhytGiatri3();
            if (dGiaTri4 != null){
            	giatri4 = formatter.format(dGiaTri4);
    	    }else{
    	    	giatri4 = "";
    	    }
            
            Date dmoc1 = hsbaBHYT.getHsbabhytMoc1();
            if (dmoc1 != null){
            	moc1 = formatter.format(dmoc1);
    	    }else{
    	    	moc1 = "";
    	    }
            
            Date dmoc2 = hsbaBHYT.getHsbabhytMoc2();
            if (dmoc2 != null){
            	moc2 = formatter.format(dmoc2);
    	    }else{
    	    	moc2 = "";
    	    }
            
            Date dmoc3 = hsbaBHYT.getHsbabhytMoc3();
            if (dmoc3 != null){
            	moc3 = formatter.format(dmoc3);
    	    }else{
    	    	moc3 = "";
    	    }
        }else{
        	giatri1 = "";
        	giatri2 = "";
        	giatri3 = "";
        	giatri4 = "";
        	
        	moc1="";
        	moc2="";
        	moc3="";
        }
        
        
        Date ngayGioVaoVien = hoSoBenhAn.getHsbaNgaygiovaov();
        Calendar dngayGioVaoVien = Calendar.getInstance();
        if (ngayGioVaoVien !=null){
        	dngayGioVaoVien.setTime(ngayGioVaoVien);
        }
        
        if (ngayGioVaoVien!=null){
        	 String hourstr = String.valueOf(dngayGioVaoVien.get(Calendar.HOUR_OF_DAY));
        	 if (hourstr.length() == 1){
        		 hourstr = "0" + hourstr;
        	 }
        	 String minstr = String.valueOf(dngayGioVaoVien.get(Calendar.MINUTE));
        	 if (minstr.length() == 1){
        		 minstr = "0" + minstr;
        	 }
        	 
        	 gioVaoVien = hourstr + ":" +     	 minstr;
		     ngayVaoVien = formatter.format(ngayGioVaoVien);					        
        }
        
        Date ngayGioRaVien = hoSoBenhAn.getHsbaNgaygiorav();
        Calendar dngayGioRaVien = Calendar.getInstance();
        if (ngayGioRaVien != null){
        	  dngayGioRaVien.setTime(ngayGioRaVien);
        }
      
        if (ngayGioRaVien!=null){
        	String hourstr = String.valueOf(dngayGioVaoVien.get(Calendar.HOUR_OF_DAY));
       	 if (hourstr.length() == 1){
       		 hourstr = "0" + hourstr;
       	 }
       	 String minstr = String.valueOf(dngayGioVaoVien.get(Calendar.MINUTE));
       	 if (minstr.length() == 1){
       		 minstr = "0" + minstr;
       	 }
       	 gioVaoVien = hourstr + ":" +     	 minstr;
        	 System.out.println("gioRaVien:"+gioRaVien);
		     ngayRaVien = formatter.format(ngayGioRaVien.getTime());					        
        }
		
	}
	
	public void resetValue(){
		log.debug("init() ");
		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);
		
		hoSoBenhAn = new Hsba();
		hoSoBenhAn.setBenhnhanMa(benhNhan);

		SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);
	
		hsbaBHYT = new HsbaBhyt();
		SetInforUtil.setInforIfNullForHSBABHYT(hsbaBHYT);
		checkBHYT = "none";
		giatri1 = "";
		giatri2 = "";
		giatri3 = "";
		giatri4 = "";
		moc1 = "";
		moc2 = "";
		moc3="";
		
		//tuoi = "";
		ngaySinh = "";		
		gioVaoVien = "";
		ngayVaoVien = "";
		gioRaVien = "";
		ngayRaVien = "";		
		resultHidden="";		
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat(FORMAT_DATE);         
		ngayhientai = formatter.format(new Date());
		
		
	}	

	private BenhNhan benhNhan;
	//private String tuoi;
	private String ngaySinh;
	private Hsba hoSoBenhAn;	
	private String gioVaoVien;
	private String ngayVaoVien;
	private String gioRaVien;
	private String ngayRaVien;
	private HsbaBhyt hsbaBHYT;  // 20101115 bao.ttc
	private String checkBHYT = "none";
	private String giatri1;
	private String giatri2;
	private String giatri3;
	private String giatri4;
	private String moc1;
	private String moc2;
	private String moc3;


	public void displayInfor() throws Exception {
		log.info("begin displayInfo=======");
		try {

//			HsbaWSService hsbaService = new HsbaWSServiceLocator();
//			HsbaWS hsbaWS = hsbaService.getHsbaWSPort();
			
			HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
			
			String sba = hoSoBenhAn.getHsbaSovaovien();
			if (sba != null && !sba.trim().equals("")) {
				Hsba hoSoBenhAn_temp = hsbaDelegate.find(sba);
				if (hoSoBenhAn_temp != null) {

					hoSoBenhAn = hoSoBenhAn_temp;
					SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);

					benhNhan = hoSoBenhAn.getBenhnhanMa();

					SimpleDateFormat formatter;
					formatter = new SimpleDateFormat(FORMAT_DATE);

					if (benhNhan.getBenhnhanNgaysinh() != null
							&& !benhNhan.getBenhnhanNgaysinh().equals("")) {
						ngaySinh = formatter.format(benhNhan
								.getBenhnhanNgaysinh().getTime());
					}

					SetInforUtil.setInforIfNullForBN(benhNhan);
					
					if (!hoSoBenhAn.getDoituongMa(true).getDmdoituongMa().equals("BH")){
						hsbaBHYT = new HsbaBhyt();
						SetInforUtil.setInforIfNullForHSBABHYT(hsbaBHYT);
						checkBHYT = "none";
					} else {
						checkBHYT = "block";
						HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
						HsbaBhyt hsbaBhytLast = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hoSoBenhAn.getHsbaSovaovien());

						if (hsbaBhytLast != null){
							hsbaBHYT = hsbaBhytLast;
						} else {
							hsbaBHYT = new HsbaBhyt();
							SetInforUtil.setInforIfNullForHSBABHYT(hsbaBHYT);
						}
					}

					setOtherInfor();
				}
				else {
					hoSoBenhAn.setHsbaSovaovien("");
					FacesMessages.instance().add(IConstantsRes.SOBENHAN_NOTFOUND);
					log.info("khong tim thay sobenhan");
				}
				log.info("----hoSoBenhAn_temp-:" + hoSoBenhAn_temp);
			}else{
				log.info("----so vao vien empty" );
			}
			
			
			log.info("benhNhan.getBenhnhanDonvituoi():"+benhNhan.getBenhnhanDonvituoi());

		} catch (Exception e) {
			e.printStackTrace();
			log.info("ERROR displayInfo=======" + e);
		}
		log.info("End displayInfo=======");
	}

	
	/**
	 * 
	 * @throws Exception
	 */
	public void ghiNhan() {
		
		
		log.debug("ghiNhan()");
		try {

			
			HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
			
			String sba = hoSoBenhAn.getHsbaSovaovien();
			log.debug("sobenhan: " + hoSoBenhAn);
			if (gioVaoVien != null && !gioVaoVien.equals("")  && ngayVaoVien != null && !ngayVaoVien.equals("")) {
				
				Calendar hsbaNgaygiovaov = Utils.getDBDate( gioVaoVien , ngayVaoVien);
				
				if (hsbaNgaygiovaov != null){
					hoSoBenhAn.setHsbaNgaygiovaov(hsbaNgaygiovaov.getTime());
				}
				
			}

//			if (gioRaVien != null && !gioRaVien.equals("") && ngayRaVien != null && !ngayRaVien.equals("")) {
//				Calendar hsbaNgaygiorav = Utils.getDBDate(gioRaVien , ngayRaVien);
//				hoSoBenhAn.setHsbaNgaygiorav(hsbaNgaygiorav.getTime());
//			}else{
//				hoSoBenhAn.setHsbaNgaygiorav(null);
//			}
			
			if (ngaySinh != null && !ngaySinh.equals("")) {
				benhNhan.setBenhnhanNgaysinh(Utils.getDBDate("00:00", ngaySinh).getTime());
			}
			
			DmGioiTinh gioiTinh = new DmGioiTinh();
			if (benhNhan.getDmgtMaso(true).getDmgtMa().equals("1")) { //nam
				gioiTinh.setDmgtMaso(2);
				gioiTinh.setDmgtMa("1");
				benhNhan.setDmgtMaso(gioiTinh);
			} else { //nu
				gioiTinh.setDmgtMaso(1);
				gioiTinh.setDmgtMa("0");
				benhNhan.setDmgtMaso(gioiTinh);
			}

			// 20101115 bao.ttc: them phan HSBA BHYT (neu co)
//			HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
//			HsbaBhyt hsbaBhytLast = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hoSoBenhAn.getHsbaSovaovien());
//
//			if (hsbaBhytLast != null)
//				hsbaBHYT = hsbaBhytLast;
//			else
//				hsbaBHYT = null;
			
			SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
			
			if (giatri1 != null && !giatri1.equals("")){
				Date dGiaTri1 = formatter.parse(giatri1);
				hsbaBHYT.setHsbabhytGiatri0(dGiaTri1);
			}else{
				hsbaBHYT.setHsbabhytGiatri0(null);
			}
			if (giatri2 != null && !giatri2.equals("")){
				Date dGiaTri2 = formatter.parse(giatri2);
				hsbaBHYT.setHsbabhytGiatri1(dGiaTri2);
			}else{
				hsbaBHYT.setHsbabhytGiatri1(null);
			}
			if (giatri3 != null && !giatri3.equals("")){
				Date dGiaTri3 = formatter.parse(giatri3);
				hsbaBHYT.setHsbabhytGiatri2(dGiaTri3);
			}else{
				hsbaBHYT.setHsbabhytGiatri2(null);
			}
			if (giatri4 != null && !giatri4.equals("")){
				Date dGiaTri4 = formatter.parse(giatri4);
				hsbaBHYT.setHsbabhytGiatri3(dGiaTri4);
			}else{
				hsbaBHYT.setHsbabhytGiatri3(null);
			}			
			
			if (moc1 != null && !moc1.equals("")){
				Date dmoc1 = formatter.parse(moc1);
				hsbaBHYT.setHsbabhytMoc1(dmoc1);
			}else{
				hsbaBHYT.setHsbabhytMoc1(null);
			}
			
			if (moc2 != null && !moc2.equals("")){
				Date dmoc2 = formatter.parse(moc2);
				hsbaBHYT.setHsbabhytMoc2(dmoc2);
			}else{
				hsbaBHYT.setHsbabhytMoc2(null);
			}
			
			if (moc3 != null && !moc3.equals("")){
				Date dmoc3 = formatter.parse(moc3);
				hsbaBHYT.setHsbabhytMoc3(dmoc3);
			}else{
				hsbaBHYT.setHsbabhytMoc3(null);
			}
			
			RemoveUtil.removeAllNullFromHSBA(hoSoBenhAn);
			RemoveUtil.removeAllNullFromBN(benhNhan);
			RemoveUtil.removeAllNullFromHSBABHYT(hsbaBHYT);
			
			if (hsbaBHYT.getHsbabhytMa() == null){
				hsbaBHYT = null;
			}
			
			hoSoBenhAn.setHsbaNgaygiocn(Calendar.getInstance().getTime());
			
			String sKetQua = hsbaDelegate.capNhatThongTinHanhChinhBN(hoSoBenhAn, hsbaBHYT, benhNhan);

			
			
			if(sKetQua != null && sKetQua.equals("error")){
				FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL);
				return;
			}

			
//			Luu log he thong
			 YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
	         YteLog yteLog = new YteLog();

	         yteLog.setForm("B216_Capnhatthongtinhanhchanhbenhnhan");
	         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
	         yteLog.setObjectId(hoSoBenhAn.getHsbaSovaovien());
	         String logString = "Ho ten: "+ (benhNhan.getBenhnhanHoten() == null ? "NULL" : benhNhan.getBenhnhanHoten()) + 
		 			 " Gioi tinh: " + (benhNhan.getDmgtMaso(true).getDmgtTen() == null ? "NULL" : benhNhan.getDmgtMaso(true).getDmgtTen())+
		 			 " Nam sinh: "+ (benhNhan.getBenhnhanNamsinh() == null ? "NULL" : benhNhan.getBenhnhanNamsinh())+
		 			 " Dia chi: "+(benhNhan.getXaMa(true).getDmxaTen() == null ? " " : benhNhan.getXaMa(true).getDmxaTen())
		 			 +" - "+(benhNhan.getHuyenMa(true).getDmhuyenTen()== null ? "" : benhNhan.getHuyenMa(true).getDmhuyenTen())
		 			 +" - "+(benhNhan.getTinhMa(true).getDmtinhTen()== null ? " " : benhNhan.getTinhMa(true).getDmtinhTen())+
		 			 " Doi tuong: "+hoSoBenhAn.getDoituongMa(true).getDmdoituongMa();
	         if(hsbaBHYT != null){
	        	 logString+=" So bao hiem: "+ hsbaBHYT.getHsbabhytSothebh()+
			 			 " Khoi: "+ hsbaBHYT.getHsbabhytKhoibh(true).getDtdmkhoibhytMa()+
			 			 " Tinh cap: "+hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhMa()+
			 			 " Noi DK KCB: "+ hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa()+
			 			 " Gia tri the tu ngay: " + hsbaBHYT.getHsbabhytGiatri0()+
			 			 " den ngay: " + hsbaBHYT.getHsbabhytGiatri1();
	         }
		 			 
	         yteLog.setDateTime(new Date());
			 yteLog.setLogString(logString);
			 
			 yteLogDele.create(yteLog);
			 
				
			if(sba != null && sba.trim().equals("")){
				FacesMessages.instance().add(IConstantsRes.SUCCESS + ":" + sKetQua);
			}
			else{
				FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS + ":" + sKetQua);
				}
			resetValue();
			resultHidden="success";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessages.instance().add(IConstantsRes.FAIL);
			resultHidden="fail";
		}
	}

	public String getMsgFail() {
		return msgFail;
	}

	public void setMsgFail(String msgFail) {
		this.msgFail = msgFail;
	}

	public String getMsgSuccess() {
		return msgSuccess;
	}

	public void setMsgSuccess(String msgSuccess) {
		this.msgSuccess = msgSuccess;
	}

	public void nhaplai() throws Exception {
		log.debug("nhaplai()");
		resetValue();
	}	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
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

	public HsbaBhyt getHsbaBHYT() {
		return hsbaBHYT;
	}

	public void setHsbaBHYT(HsbaBhyt hsbaBHYT) {
		this.hsbaBHYT = hsbaBHYT;
	}

	public String getCheckBHYT() {
		return checkBHYT;
	}

	public void setCheckBHYT(String checkBHYT) {
		this.checkBHYT = checkBHYT;
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

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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
		CapNhatThongTinHanhChanhBenhNhan.log = log;
	}
}


