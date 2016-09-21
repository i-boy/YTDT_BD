package com.iesvn.utils;

import java.text.NumberFormat;
import java.util.ResourceBundle;

public class Utils {
	
	public static String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm";
	public static String FORMAT_DATE = "dd/MM/yyyy";
	public static String FORMAT_DATE_TIME_HOUR = "dd/MM/yyyy HH:mm:ss";
	
	
	public static String XU_TRI_THUOC_TAI_BAN_KHAM = "1";	
	public static String KE_TOA_BENH_NHAN_TU_MUA = "2";
	public static String KE_TOA_QUAY_BENH_VIEN = "3";
	
	
	
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
	    	//if(number==null) return "";
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
			return sReturn;
		}
	    
	    public static String NumberToString(Double number){
	    	if(number==null) return "";
	    	String sNumber = formatNumberForRead(number.doubleValue());
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
			return sReturn;
		}
	    
		//Khoi tao ham Read
		public static String Read(String sNumber,int iPo){
			//Tao mot bien tra ve
			String sReturn = "";
			//Tao mot bien so
			String sPo[] = {"",IConstantsRes.ngan+" ",IConstantsRes.trieu+" ",IConstantsRes.ty+" "};
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
							if (Integer.parseInt(""+sNumber.charAt(0))!=0 || Integer.parseInt(""+sNumber.charAt(1))!=0){
								sRead = IConstantsRes.khong_tam+" ";
							}
							break;
						} 
					}
				}else if (iTemp==1){
					switch (iRe){
					//case 1: sRead=IConstantsRes.muoi+" ";break;
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
		 * @return 21 gio` 12 phut, ngay 22/03/2009
		 * 
		 **/
		
		
		
		
		
		
}

