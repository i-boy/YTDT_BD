package com.iesvn.yte.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.HsThtoank;

public class Utils {
	private static Log log = LogFactory.getLog(Utils.class);

	
	
	public static String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm";
	public static String FORMAT_DATE_TIME_HOUR_FIRST = "HH:mm dd/MM/yyyy";
	public static String FORMAT_DATE = "dd/MM/yyyy";
	public static String FORMAT_DATE_TIME_HOUR = "dd/MM/yyyy HH:mm:ss";
	
	
	public static String XU_TRI_THUOC_TAI_BAN_KHAM = "1";	
	public static String KE_TOA_BENH_NHAN_TU_MUA = "2";
	public static String KE_TOA_QUAY_BENH_VIEN = "3";
	
	public static String formatNumber(Double d, String pattern) {
		if (d == null){
			return "";
		}
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(d);
    }
	
	/**
	 * 
	 * @param cal
	 * @return
	 */
	public static String getGioPhut(Date d){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String gioTmp =  ""+cal.get(Calendar.HOUR_OF_DAY);
		if (gioTmp.length() == 1){
			gioTmp = "0" + gioTmp;
		}
		
		String phutTmp = ""+cal.get(Calendar.MINUTE);
		if (phutTmp.length() == 1){
			phutTmp = "0" + phutTmp;
		}
		return gioTmp + ":" + phutTmp;
	}
	
	/**
	 * 
	 * @param key
	 * @param resourcePath
	 * @return
	 */
 	public static String getResource(String key, String resourcePath) {		
		ResourceBundle bundle = null;
		if (resourcePath == null) {
			bundle = ResourceBundle.getBundle("com.iesvn.yte.ApplicationResources");
		} else {
			bundle = ResourceBundle.getBundle(resourcePath);
		}
		String result = "";
		if ( bundle != null) {
			result = bundle.getString(key);
		}
		return result;
	}
 	
 	/**
 	 * 
 	 * @author thanhdo
 	 * @date 27-09-2008
 	 * @param str
 	 * @return
 	 */
 	public static String reFactorString(String value){
 		if (value == null){
 			return "";
 		}else{
 			return value.trim(); 			
 		}
 	}
 	

 	/**
 	 * 
 	 * @author thanhdo
 	 * @date 27-09-2008
 	 * @param str
 	 * @return
 	 */
 	public static String reFactorString(Integer value){
 		if (value == null){
 			return "";
 		}else{
 			return String.valueOf(value); 			
 		}
 	}
 	
 	public static String reFactorString(Float value){
 		if (value == null){
 			return "";
 		}else{
 			return String.valueOf(value); 			
 		}
 	}
 	
 	/**
 	 * 
 	 * @author thanhdo
 	 * @date 27-09-2008
 	 * @param str
 	 * @return
 	 */
 	public static String reFactorString(Double value){
 		if (value == null){
 			return "";
 		}else{
 			return String.valueOf(value); 			
 		}
 	}
 	
 	/**
 	 * 
 	 * @author thanhdo
 	 * @date 27-09-2008
 	 * @param str
 	 * @return
 	 */
 	public static String reFactorString(Boolean value){
 		if (value == null || value.booleanValue() == false){
 			return "0";
 		}else{
 			return "1"; 			
 		}
 	}
 	/**
 	 * 
 	 * @author thanhdo
 	 * @date 27-09-2008
 	 * @param str
 	 * @return
 	 */
 	public static String reFactorString(Calendar value){
 		if (value == null ){
 			return "";
 		}else{
 			 SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             
             return df.format(value.getTime());
 		}
 	}
 	
 	public static String reFactorString(Date value){
 		if (value == null ){
 			return "";
 		}else{
 			 SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             
             return df.format(value);
 		}
 	}
 	
 	public static String reFactorString(Short value){
 		if (value == null ){
 			return "";
 		}else{
 			return String.valueOf(value); 		
 		}
 	}
 	public static String findAndreplace(String str) {
 		if (str == null){
 			return "";
 		}
		String result = "";
		int i;
		for (i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '<') {
				result += "&lt;";
			}
			else if (c == '>') {
				result += "&gt;";
			}
			else if (c == '\'') {
				result += "%27";
			}
			else if (c == '&') {
				result += "&amp;";
			}
			else {
				result +=c;
			}
		}
		return result;
	}
 	
 	public static String getCurrentDate() {
 		Date currentDate; 		
 		String curDate;
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		currentDate=new Date();
		curDate=sdf.format(currentDate);
		return curDate;
 	}
 	
 	
 	/**
	 * @author thanhdo
	 * @param gioVaoVien
	 * @param ngayVaoVien
	 * @ex: input: 11:11 12/11/2008 
	 * @return
	 */
	public static Calendar getDBDate(String gio, String ngay) {//gio  :  11:22

		Date tmp_date;
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(FORMAT_DATE_TIME);

		String tmpGio = gio;
		if (tmpGio.length() != 5) {
			return null;
		}

		try {
			tmp_date = formatter.parse(ngay + " " + tmpGio);
		} catch (ParseException e) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tmp_date);

		return calendar;
	}
	public static Calendar getDBDateWithHour(int gio, String ngay, boolean dauNgay) {

		Date tmp_date;
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(FORMAT_DATE_TIME_HOUR);

		String tmpGio = String.valueOf(gio);
		while (tmpGio.length() < 2) {
			tmpGio = "0" + tmpGio;
		}
		
		if (dauNgay){
			tmpGio = tmpGio +  ":00:00";
		}else{
			tmpGio = tmpGio +  ":59:59";
		}

		try {
			tmp_date = formatter.parse(ngay + " " + tmpGio);
		} catch (ParseException e) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tmp_date);

		return calendar;
	}
 	public static String createNextIndex(String strMSHS) {
 		/*
 		 *  Dau vao la ma ho so lon nhat cua ho so
 		 *  Ma ho so co dang 0012008 (001 : la so thu tu cua ho so; 2008 : la so nam)
 		 *  Neu nam hien tai va nam trong ma ho so giong nhau 
 		 *  thi tra ve ma so moi voi so thu tu tang len 1 (vi du : 0022008)
 		 *  Neu name hien tai khac nam trong ma ho so 
 		 *  thi tra ve ma so moi bat dau tu 1 (vi du : 0012009)
 		 */
		String newMHS = "";
		Date d = new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(d);
		// Lay nam hien tai
		String curYear = new String("" + calendar.get(Calendar.YEAR));
		
		// Lay chieu dai cua nam theo qui dinh trong file cau hinh
		int yearLen = new Integer(IConstantsRes.YEAR_NUM_LENGTH).intValue();
		
		// Lay chieu dai cua so thu tu ho so theo qui dinh trong file cau hinh
		int orderLen = new Integer(IConstantsRes.ORDER_NUM_LENGTH).intValue();
		
		// Dinh danh nam hien tai theo chieu dai qui dinh
		curYear = curYear.substring(curYear.length() - yearLen);
		String zeroFill = "";
		
		if (strMSHS.equals("")) {
			for (int i = 0; i< orderLen - 1; i ++){
				zeroFill += "0";
			}
			newMHS = zeroFill + 1 + curYear;
		} else {
			// Lay nam cua ma ho so duoc truyen vao
			String keyYear = strMSHS.substring(strMSHS.length() - yearLen);
			
			// Kiem tra nam hien tai va nam trong ma ho so
			// Neu cung nam thi tang so thu tu len 1 don vi
			if (curYear.endsWith(keyYear)) {
				Integer keyOrder = new Integer(strMSHS.substring(0,strMSHS.indexOf(keyYear)));			
				int newNum = keyOrder.intValue() + 1;
				// Neu so thu tu co chi dai it hon qui dinh thi them cac so 0 phia truoc				
				for (int i = 0; i< orderLen - ("" + newNum).length(); i ++){
					zeroFill += "0";
				}
				newMHS = zeroFill + newNum + curYear;
			} else { 
				// Neu nam hien tai khac voi nam trong ma ho so
				// thi so ho bat dau lai tu 1 
				int newNum = 1;				
				for (int i = 0; i< orderLen - ("" + newNum).length(); i ++){
					zeroFill += "0";
				}
				newMHS = zeroFill + newNum + curYear;
			}	
		}
		return newMHS;
	}
 	
 	public static String convertCalendar2Str(Calendar cal) {
		//log.info("***** Begin convertCalendar2Str() *****");
		String strReturn = "";
		if (cal != null) {
			String strDay = "" + (cal.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + cal.get(Calendar.DAY_OF_MONTH): cal.get(Calendar.DAY_OF_MONTH));
			String strMonth = "" + ((cal.get(Calendar.MONTH) +1) < 10 ? "0" + (cal.get(Calendar.MONTH) + 1): (cal.get(Calendar.MONTH) +1));			
			strReturn = strDay + "/" + strMonth + "/" + cal.get(Calendar.YEAR);
		}
		//log.info("*****End convertCalendar2Str(), strReturn = " + strReturn);
		return strReturn;
	}
 	
 	public static int compareDate(Calendar c1, Calendar c2) {
        int result = 0;
        if (c1 == null || c2 == null) {
        	result = -2;
        } else if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
            result = 1;
        } else if (c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR)) {
            result = -1;
        } else if (c1.get(Calendar.MONTH) > c2.get(Calendar.MONTH)) {
            result = 1;
        } else if (c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH)) {
            result = -1;
        } else if (c1.get(Calendar.DAY_OF_MONTH) > c2.get(Calendar.DAY_OF_MONTH)) {
            result = 1;
        } else if (c1.get(Calendar.DAY_OF_MONTH) < c2.get(Calendar.DAY_OF_MONTH)) {
            result = -1;
        }
        
        return result;
    }
 	
 	public static String formatLongDouble(Double value) {
 		if (value == null) return "";
	 	try {
			NumberFormat formatter = NumberFormat.getNumberInstance();
			formatter.setGroupingUsed(true);
			formatter.setMaximumFractionDigits(2);			
			//return (Double.toString(((Number) value).doubleValue()));
			return (formatter.format(((Number) value).doubleValue()));
		} catch (Exception e) {
			log.info("ERROR -- formatLongDouble : value = " + value);
			log.info(e.toString());
		}
		return "";
 	}

	public static String taoDonViTuoi(int dvt) {
		String strDvt = null;  
		switch (dvt) {
		case 1: strDvt = IConstantsRes.NAM; break;
		case 2: strDvt = IConstantsRes.THANG; break;
		case 3: strDvt = IConstantsRes.NGAY; break;
		}
		//log.info("dvt = " + dvt);
		
		if (StringUtils.isNotBlank(strDvt)) strDvt = "(" + strDvt + ")";
		//log.info("donViTuoi = " + strDvt);
		return strDvt;
	}
	
	 private static double getTongChi(
	            double thuocTrongDM, double thuocNDM,
	            double VTTHTrongDM, double VTTHGNDM,
	            double CLSTrongDM, double CLSNDM,
	            double PhauThuatTrongDM, double PhauThuatNDM,
	            double PhongTrongDM, double PhongNDM,
	            double congKham) {
	        double ketqua = 0;
	        
	        // bao.ttc: Tong chi bao gom Thuoc + VTTH + CLS (CLS bao gom toan bo cac loai chi phi khac) 
	        // ketqua = thuocTrongDM + thuocNDM + VTTHTrongDM + VTTHGNDM + CLSTrongDM + CLSNDM + PhauThuatTrongDM + PhauThuatNDM + PhongTrongDM + PhongNDM + congKham;
	        ketqua = thuocTrongDM + thuocNDM + VTTHTrongDM + VTTHGNDM + CLSTrongDM + CLSNDM;
	        return ketqua;
	    }

	    private static double getTrongDanhMuc(
	            double thuocTrongDM,
	            double VTTHTrongDM,
	            double CLSTrongDM,
	            double PhauThuatTrongDM,
	            double PhongTrongDM) {
	        double ketqua = 0;

	        // ketqua = thuocTrongDM + VTTHTrongDM + CLSTrongDM + PhauThuatTrongDM + PhongTrongDM;
	        // bao.ttc: Tong tien Trong Danh muc chi bao gom Thuoc trong DM + VTTH trong DM + CLS trong DM (CLS bao gom toan bo cac loai chi phi khac) 
	        ketqua = thuocTrongDM + VTTHTrongDM + CLSTrongDM;
	        return ketqua;

	    }

	    private static double getNgoaiDanhMuc(
	            double thuocNDM,
	            double VTTHGNDM,
	            double CLSNDM,
	            double PhauThuatNDM,
	            double PhongNDM
                ) {
	        double ketqua = 0;

	        // bao.ttc: Tong tien Ngoai Danh muc chi bao gom Thuoc ngoai DM + VTTH ngoai DM + CLS ngoai DM (CLS bao gom toan bo cac loai chi phi khac) 
	        // ketqua = thuocNDM + VTTHGNDM + CLSNDM + PhauThuatNDM + PhongNDM ;
	        ketqua = thuocNDM + VTTHGNDM + CLSNDM;
	        return ketqua;

	    }

//	    private static double getThanhToan(
//	            double tongChi,
//	            double mienPhong,
//	            double mienThuoc,
//	            double nganSach,
//	            double OThu,
//	            double mienThuocLao,
//	            double mienTreEm,
//	            double mienDanToc,
//	            double mienMau,
//	            double xetGiam,
//	            double bhxh,
//	            double bhyt,
//	            double tamUng,
//	            double hoanUng,
//	            double hoanThu) {
//	        double ketqua = 0;
//	        double varA = tongChi -
//	                -mienPhong - mienThuoc - nganSach - OThu - mienThuocLao - mienTreEm - mienDanToc - mienMau - xetGiam;
//	        double varB = varA - bhxh - bhyt;
	//
//	       ketqua = varB - tamUng + hoanUng - hoanThu;
	//
//	       
	//
//	        return ketqua;
	//
//	    }

	    public static void setInforForHsThToan(HsThtoank hsThanhToan) {
	        double thuocTrongDM = (hsThanhToan.getHsthtoankThuoc() == null) ? 0 : hsThanhToan.getHsthtoankThuoc().doubleValue();
	        double thuocNDM = (hsThanhToan.getHsthtoankThuocndm() == null) ? 0 : hsThanhToan.getHsthtoankThuocndm().doubleValue();
	        double VTTHTrongDM = (hsThanhToan.getHsthtoankVtth() == null) ? 0 : hsThanhToan.getHsthtoankVtth().doubleValue();
	        double VTTHGNDM = (hsThanhToan.getHsthtoankVtthndm() == null) ? 0 : hsThanhToan.getHsthtoankVtthndm().doubleValue();
	        double CLSTrongDM = (hsThanhToan.getHsthtoankCls() == null) ? 0 : hsThanhToan.getHsthtoankCls().doubleValue();
	        double CLSNDM = (hsThanhToan.getHsthtoankClsndm() == null) ? 0 : hsThanhToan.getHsthtoankClsndm().doubleValue();
	        double PhauThuatTrongDM = (hsThanhToan.getHsthtoankPhauthuat() == null) ? 0 : hsThanhToan.getHsthtoankPhauthuat().doubleValue();
	        double PhauThuatNDM = (hsThanhToan.getHsthtoankPhauthuatndm() == null) ? 0 : hsThanhToan.getHsthtoankPhauthuatndm().doubleValue();
	        double PhongTrongDM = (hsThanhToan.getHsthtoankPhong() == null) ? 0 : hsThanhToan.getHsthtoankPhong().doubleValue();
	        double PhongNDM = (hsThanhToan.getHsthtoankPhongndm() == null) ? 0 : hsThanhToan.getHsthtoankPhongndm().doubleValue();
	        //double congKham = (hsThanhToan.getHsthtoankCongkham() == null) ? 0 : hsThanhToan.getHsthtoankCongkham().doubleValue();

	        //double mienPhong = (hsThanhToan.getHsthtoankMienphong() == null) ? 0 : hsThanhToan.getHsthtoankMienphong().doubleValue();
	        //double mienThuoc = (hsThanhToan.getHsthtoankMienthuoc() == null) ? 0 : hsThanhToan.getHsthtoankMienthuoc().doubleValue();
	        //double nganSach = (hsThanhToan.getHsthtoankNgansach() == null) ? 0 : hsThanhToan.getHsthtoankNgansach().doubleValue();
	        //double OThu = (hsThanhToan.getHsthtoankKhongthu() == null) ? 0 : hsThanhToan.getHsthtoankKhongthu().doubleValue();
	        //double mienThuocLao = (hsThanhToan.getHsthtoankMienthuoclao() == null) ? 0 : hsThanhToan.getHsthtoankMienthuoclao().doubleValue();
	        //double mienTreEm = (hsThanhToan.getHsthtoankMiente() == null) ? 0 : hsThanhToan.getHsthtoankMiente().doubleValue();
	        //double mienDanToc = (hsThanhToan.getHsthtoankMiendt() == null) ? 0 : hsThanhToan.getHsthtoankMiendt().doubleValue();
	        //double mienMau = (hsThanhToan.getHsthtoankMienmau() == null) ? 0 : hsThanhToan.getHsthtoankMienmau().doubleValue();
	        //double xetGiam = (hsThanhToan.getHsthtoankXetgiam() == null) ? 0 : hsThanhToan.getHsthtoankXetgiam().doubleValue();

	        //double bhxh = (hsThanhToan.getHsthtoankBhxh() == null) ? 0 : hsThanhToan.getHsthtoankBhxh().doubleValue();
	        //double bhyt = (hsThanhToan.getHsthtoankBhyt() == null) ? 0 : hsThanhToan.getHsthtoankBhyt().doubleValue();

	        //double tamUng = (hsThanhToan.getHsthtoankTamung() == null) ? 0 : hsThanhToan.getHsthtoankTamung().doubleValue();
	        //double hoanUng = (hsThanhToan.getHsthtoankHoanung() == null) ? 0 : hsThanhToan.getHsthtoankHoanung().doubleValue();
	        //double hoanThu = (hsThanhToan.getHsthtoankHoanthu() == null) ? 0 : hsThanhToan.getHsthtoankHoanthu().doubleValue();

	       
	        //log.info("=============PhongTrongDM:"+PhongTrongDM);
	        //log.info("=============PhongNDM:"+PhongNDM);
	        
	        
	       /* hsThanhToan.setHsthtoankTongchi(Utils.getTongChi(
	                thuocTrongDM, thuocNDM,
	                VTTHTrongDM, VTTHGNDM,
	                CLSTrongDM, CLSNDM,
	                PhauThuatTrongDM, PhauThuatNDM,
	                PhongTrongDM, PhongNDM,
	                congKham));*/

	        hsThanhToan.setHsthtoankDm(Utils.getTrongDanhMuc(
	                thuocTrongDM,
	                VTTHTrongDM,
	                CLSTrongDM,
	                PhauThuatTrongDM,
	                PhongTrongDM));

	        hsThanhToan.setHsthtoankNdm(Utils.getNgoaiDanhMuc(
	                thuocNDM,
	                VTTHGNDM,
	                CLSNDM,
	                PhauThuatNDM,
	                PhongNDM));

//	        hsThanhToan.setHsthtoankThtoan(Utils.getThanhToan(
//	                hsThanhToan.getHsthtoankTongchi(),
//	                mienPhong,
//	                mienThuoc,
//	                nganSach,
//	                OThu,
//	                mienThuocLao,
//	                mienTreEm,
//	                mienDanToc,
//	                mienMau,
//	                xetGiam,
//	                bhxh,
//	                bhyt,
//	                tamUng,
//	                hoanUng,
//	                hoanThu));
	    }
	    
	    public static void setInforForHsThToan(HsThtoan hsThanhToan) {
	        double thuocTrongDM = (hsThanhToan.getHsthtoanThuoc() == null) ? 0 : hsThanhToan.getHsthtoanThuoc().doubleValue();
	        double thuocNDM = (hsThanhToan.getHsthtoanThuocndm() == null) ? 0 : hsThanhToan.getHsthtoanThuocndm().doubleValue();
	        double VTTHTrongDM = (hsThanhToan.getHsthtoanVtth() == null) ? 0 : hsThanhToan.getHsthtoanVtth().doubleValue();
	        double VTTHGNDM = (hsThanhToan.getHsthtoanVtthndm() == null) ? 0 : hsThanhToan.getHsthtoanVtthndm().doubleValue();
	        double CLSTrongDM = (hsThanhToan.getHsthtoanCls() == null) ? 0 : hsThanhToan.getHsthtoanCls().doubleValue();
	        double CLSNDM = (hsThanhToan.getHsthtoanClsndm() == null) ? 0 : hsThanhToan.getHsthtoanClsndm().doubleValue();
	        double PhauThuatTrongDM = (hsThanhToan.getHsthtoanPhauthuat() == null) ? 0 : hsThanhToan.getHsthtoanPhauthuat().doubleValue();
	        double PhauThuatNDM = (hsThanhToan.getHsthtoanPhauthuatndm() == null) ? 0 : hsThanhToan.getHsthtoanPhauthuatndm().doubleValue();
	        double PhongTrongDM = (hsThanhToan.getHsthtoanPhong() == null) ? 0 : hsThanhToan.getHsthtoanPhong().doubleValue();
	        double PhongNDM = (hsThanhToan.getHsthtoanPhongndm() == null) ? 0 : hsThanhToan.getHsthtoanPhongndm().doubleValue();
	        double congKham = (hsThanhToan.getHsthtoanCongkham() == null) ? 0 : hsThanhToan.getHsthtoanCongkham().doubleValue();

	       

	        //double mienPhong = (hsThanhToan.getHsthtoanMienphong() == null) ? 0 : hsThanhToan.getHsthtoanMienphong().doubleValue();
	        //double mienThuoc = (hsThanhToan.getHsthtoanMienthuoc() == null) ? 0 : hsThanhToan.getHsthtoanMienthuoc().doubleValue();
	        //double nganSach = (hsThanhToan.getHsthtoanNgansach() == null) ? 0 : hsThanhToan.getHsthtoanNgansach().doubleValue();
	        //double OThu = (hsThanhToan.getHsthtoanKhongthu() == null) ? 0 : hsThanhToan.getHsthtoanKhongthu().doubleValue();
	        //double mienThuocLao = (hsThanhToan.getHsthtoanMienthuoclao() == null) ? 0 : hsThanhToan.getHsthtoanMienthuoclao().doubleValue();
	        //double mienTreEm = (hsThanhToan.getHsthtoanMiente() == null) ? 0 : hsThanhToan.getHsthtoanMiente().doubleValue();
	        //double mienDanToc = (hsThanhToan.getHsthtoanMiendt() == null) ? 0 : hsThanhToan.getHsthtoanMiendt().doubleValue();
	        //double mienMau = (hsThanhToan.getHsthtoanMienmau() == null) ? 0 : hsThanhToan.getHsthtoanMienmau().doubleValue();
	        //double xetGiam = (hsThanhToan.getHsthtoanXetgiam() == null) ? 0 : hsThanhToan.getHsthtoanXetgiam().doubleValue();

	        //double bhxh = (hsThanhToan.getHsthtoanBhxh() == null) ? 0 : hsThanhToan.getHsthtoanBhxh().doubleValue();
	        //double bhyt = (hsThanhToan.getHsthtoanBhyt() == null) ? 0 : hsThanhToan.getHsthtoanBhyt().doubleValue();

	        //double tamUng = (hsThanhToan.getHsthtoanTamung() == null) ? 0 : hsThanhToan.getHsthtoanTamung().doubleValue();
	        //double hoanUng = (hsThanhToan.getHsthtoanHoanung() == null) ? 0 : hsThanhToan.getHsthtoanHoanung().doubleValue();
	        //double hoanThu = (hsThanhToan.getHsthtoanHoanthu() == null) ? 0 : hsThanhToan.getHsthtoanHoanthu().doubleValue();

	        //double BNDaTra = (hsThanhToan.getHsthtoanBntra() == null) ? 0 : hsThanhToan.getHsthtoanBntra().doubleValue();

	        
	        
	        hsThanhToan.setHsthtoanTongchi(Utils.getTongChi(
	                thuocTrongDM, thuocNDM,
	                VTTHTrongDM, VTTHGNDM,
	                CLSTrongDM, CLSNDM,
	                PhauThuatTrongDM, PhauThuatNDM,
	                PhongTrongDM, PhongNDM,
	                congKham));

	        hsThanhToan.setHsthtoanDm(Utils.getTrongDanhMuc(
	                thuocTrongDM,
	                VTTHTrongDM,
	                CLSTrongDM,
	                PhauThuatTrongDM,
	                PhongTrongDM
	        ));

	        hsThanhToan.setHsthtoanNdm(Utils.getNgoaiDanhMuc(
	                thuocNDM,
	                VTTHGNDM,
	                CLSNDM,
	                PhauThuatNDM,
	                PhongNDM
	                ));

//	        hsThanhToan.setHsthtoanThtoan(Utils.getThanhToan(
//	                hsThanhToan.getHsthtoanTongchi(),
//	                mienPhong,
//	                mienThuoc,
//	                nganSach,
//	                OThu,
//	                mienThuocLao,
//	                mienTreEm,
//	                mienDanToc,
//	                mienMau,
//	                xetGiam,
//	                bhxh,
//	                bhyt,
//	                tamUng,
//	                hoanUng,
//	                hoanThu));
	    }
	    public static String maDoiTuongTreEm() {
	    	return "TE";
	    }
	    
	   /**
	    * for mat mot so truoc khi doc
	    * @param number
	    * @return
	    */
		public static String formatNumberForRead (double number){
	    	NumberFormat nf = NumberFormat.getInstance();
			String temp = nf.format(number);
	    	String strReturn = "";
	    	int slen = temp.length();
	        for (int i=0; i < slen; i++) {
	            if(String.valueOf(temp.charAt(i)).equals("."))
	            	break;
	            else if(Character.isDigit(temp.charAt(i))){
		             strReturn+=String.valueOf(temp.charAt(i));
	            }
	        }
	    	return strReturn;
	    }
		
		/**
	     * Hàm đọc một số
	     * @param sNumber
	     * @return
	     */
	    public static String NumberToString(double number){
	    	String sNumber = formatNumberForRead(number);
	    	//Tao mot bien tra ve
			String sReturn="";
			//Tim chieu dai cua chuoi
			int iLen = sNumber.length();
			//Lat nguoc chuoi nay lai
			String sNumber1 = "";
			for (int i=iLen-1; i>=0; i--){
				sNumber1 += sNumber.charAt(i);
			}
			//Tao mot vong lap de doc so
			//Tao mot bien nho vi tri cua sNumber
			int iRe = 0;
			do{
				//Tao mot bien cat tam
				String sCut = "";
				if (iLen>3){
					sCut = sNumber1.substring((iRe*3),(iRe*3)+3);
					sReturn = Read(sCut,iRe)+sReturn;
					iRe++;
					iLen -= 3;
				}else{
					sCut = sNumber1.substring((iRe*3),(iRe*3)+iLen);
					sReturn = Read(sCut,iRe)+sReturn;
					break;
				}
			}
			while (true);
			//bao.ttc: viet hoa chu cai dau tien
			if(number == 0)
				sReturn = IConstantsRes.khong;
			
			String upper_first = sReturn.substring(0,1).toUpperCase() + sReturn.substring(1);
			return upper_first;
		}
		//Khoi tao ham Read
		public static String Read(String sNumber,int iPo){
			//Tao mot bien tra ve
			String sReturn = "";
			//Tao mot bien so
			String sPo[] = {"",IConstantsRes.ngan+" ",IConstantsRes.trieu+" ",IConstantsRes.ty+" ",IConstantsRes.nganty+" "};
			String sSo[] = {IConstantsRes.khong +" ",IConstantsRes.mot+" ",IConstantsRes.hai+" ",IConstantsRes.ba+" ",IConstantsRes.bon+" ",IConstantsRes.nam+" ",IConstantsRes.sau+" ",IConstantsRes.bay+" ",IConstantsRes.tam+" ",IConstantsRes.chin+" "};
			String sDonvi[] = {"",IConstantsRes.muoi+" ",IConstantsRes.tram+" "};
			//Tim chieu dai cua chuoi
			int iLen = sNumber.length();
			//Tao mot bien nho vi tri doc
			int iRe=0;
			//Tao mot vong lap de doc so
			for (int i=0; i<iLen; i++){
				String sTemp = ""+sNumber.charAt(i);
				int iTemp = Integer.parseInt(sTemp);
				//Tao mot bien ket qua
				String sRead="";
				//Kiem tra xem so nhan vao co = 0 hay ko
				if (iTemp==0){
					switch (iRe){
						case 0:break;//Khong lam gi ca
						case 1: {
							if (Integer.parseInt(""+sNumber.charAt(0))!=0){
								sRead = IConstantsRes.le+" ";
							}
							break;
						}
						case 2:{
							//if (Integer.parseInt(""+sNumber.charAt(0))!=0 && Integer.parseInt(""+sNumber.charAt(1))!=0){
							//phuc.lc 08/08/2011 : Fix bug 3697
							if (Integer.parseInt(""+sNumber.charAt(0))!=0 || Integer.parseInt(""+sNumber.charAt(1))!=0){	
								sRead = IConstantsRes.khong_tam+" ";
							}
							break;
						} 
					}
				}else if (iTemp==1){
					switch (iRe){
					//case 1: sRead=IConstantsRes.muoi+" ";break;
					//phuc.lc 08/08/2011 : Fix bug 3697
					case 1: sRead=IConstantsRes.muoi_10+" ";break;
					default: sRead = IConstantsRes.mot+" "+sDonvi[iRe];break;				
					}
				}else if (iTemp==5){
					switch (iRe){
					case 0:	{
							if(sNumber.length() <= 1){
								sRead = IConstantsRes.nam+" ";
							}else if (Integer.parseInt(""+sNumber.charAt(1))!=0){
								sRead = IConstantsRes.lam+" ";
							}else sRead = IConstantsRes.nam+" ";
							break;
						}
					default: sRead = sSo[iTemp]+sDonvi[iRe];
					}
				}else{
					sRead = sSo[iTemp]+sDonvi[iRe];
				}
				
				sReturn = sRead+sReturn;
				iRe++;
			}
			if (sReturn.length()>0){
				sReturn += sPo[iPo];
			}

			return sReturn;
		}
		
		/**
		 * @author james
		 * @param date
		 * @return 21 gio` 12, ngay 22/03/2009
		 * 
		 **/
		public static String getGioPhutNgay(Date date)
		{
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat sdf=new SimpleDateFormat(Utils.FORMAT_DATE);
			cal.setTime(date);
			String sngay=IConstantsRes.NGAY;
			//String sphut= "ph\u00FAt";
			String sgio="gi\u1EDD";
			int iGio=cal.get(Calendar.HOUR_OF_DAY);
			int iPhut=cal.get(Calendar.MINUTE);
			String full=iGio+" "+sgio+" "+iPhut+", "+sngay+" "+sdf.format(cal.getTime());
			return full;
		}
		
		/**
		 * @author James
		 * @param dNumber Double 
		 * @Input 123456789.005
		 * @return 123.456.789,005
		 **/
		public static String formatNumberWithSeprator(Double dNumber)
		{
			String sNum;
			NumberFormat nf=NumberFormat.getInstance();
			sNum=nf.format(dNumber);
			sNum=sNum.replace(".", "_");
			sNum=sNum.replaceAll(",", ".");
			sNum=sNum.replaceAll("_", ",");
			return sNum;
		}
		
		private static final long ONE_HOUR = 60 * 60 * 1000L;
		/**
		 * @param d1 Date
		 * @param d2 Date
		 * @return amount days between 2 days
		 **/
		public static long getDaysBetween(Date d1, Date d2)
		{
			return ( ((d2.getTime() - d1.getTime()) / (ONE_HOUR * 24)) + 1 );
		}
		
		// 20111103 bao.ttc: tinh so ngay dieu tri theo ApplicationResources (+1 ngay / Khong +)
		// Ngay dieu tro khong xet Gio phut
		public static long getSoNgayDieuTri(Date vaovien, Date ravien) {
			
			Date ngayVaovien = removeHourFromDate(vaovien);
			Date ngayRavien = removeHourFromDate(ravien);
			
			if (ngayVaovien != null && ngayRavien != null){
				if (ngayRavien.equals(ngayVaovien)){
					return new Long("1");
				} else {
					Long soNgayDT = (ngayRavien.getTime() - ngayVaovien.getTime()) / (ONE_HOUR * 24);
					Long ngayCongthem = new Long(IConstantsRes.CONG_VAO_SO_NGAY_DIEU_TRI);
					return (soNgayDT + ngayCongthem);
				}
			} else {
				return new Long("0");
			}
			
		}
		
		
		/**
		 * @param dnum Double
		 * Example : 		 
		 * if dnum  = 120.0 ==> return  120.0
		 * if dnum from 121.0 -> 129.0 ==> return 130.0
		 * if dnum  = 120.012 OR dnum form 121.0 -> 120.9  ==> return  130.0		 
		 * 
		 **/
		public static Double rounDoubleForReport(Double dnum){
			long ly = dnum.longValue();
			double dz1 = dnum - ly;
			Double dRoundNum = dnum;
			if (dz1 > 0) {
				dRoundNum = new Double(ly +1);				
			}
			Double dReturn = dRoundNum ;
			double mod = dRoundNum % 10;			
			if (mod > 0) {
				dReturn = dReturn - mod + 10;
			}			
			return dReturn;
		}
		/**
		 * 
		 * @param dDate : Date with hour, minute, second ...
		 * @return The Date without hour, minute, second ... (hour = 0, minute = 0, ...)
		 */
		public static Date removeHourFromDate(Date dDate) {
			if (dDate != null) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				//Calendar cal1 = Calendar.getInstance();
				try {
					String strDate = df.format(dDate);
					return df.parse(strDate);
					//strDate = df.format(dDate); // strDate = "23/05/2012"
					//cal1.setTime(df.parse(strDate));
					//cal1.set(Calendar.HOUR, 0);
					//cal1.set(Calendar.MINUTE, 0);
					//cal1.set(Calendar.SECOND, 0);
					//cal1.set(Calendar.MILLISECOND, 0);
					//return cal1.getTime();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return null;
			}
		}
		/**
		 * 
		 * @param number số lượng
		 * @param type kiểu = 3 
		 * @return
		 */
		public static String numberThuocToString(double number, long type){
			try{
				if(type ==3){
					return NumberToString(number);
				}else
				{
					DecimalFormat df = new DecimalFormat("###,###.00");
					return(df.format(number));
				}
			}catch(Exception e)
			{
				return "";
			}
		}
		
		public static int roundDoubleNumber(Double dNum) {
			if (dNum == null) return 0;
			double dVal = dNum.doubleValue();
			int iVal = dNum.intValue();		
			if(dVal - iVal > 0) {
				iVal = iVal + 1;
			}
			return iVal;
		}
		
		public static Double roundDoubleTwoDecimals(Double dNum) {
			if (dNum != null) {
				DecimalFormat twoDForm = new DecimalFormat("###.##");
				return Double.valueOf(twoDForm.format(dNum));
			}
			return new Double(0.0);
		}


}

