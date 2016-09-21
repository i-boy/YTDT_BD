package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.HsbaNopDelegate;
import com.iesvn.yte.dieutri.entity.HsbaNop;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;


@Scope(CONVERSATION)
@Name("B251_Timsobenhansoluutru")
@Synchronized(timeout = 6000000)
public class TimSoBenhAnSoLuuTru  implements Serializable {
	
	private static Logger log = Logger.getLogger(TimSoBenhAnSoLuuTru.class);
	
	@Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
	@Create
	@Begin(join = true)
	public String init() {
		log.info("begin init()");
		
		resetValue();

		log.info("end init()");
		
		return "DieuTri_DichVuTienIch_TimBenhAnSoLuuTru";
	}
	

	@End
	public void end(){
		
	}
	


		
	
	/**
	 * 
	 */
	private void resetValue(){
		
		 sovaovien = "";	
		 soluutru="";
		 maKhoa="";
		 maSoKhoa=null;
	}
	

	private String sovaovien;	
	private String soluutru;
	private String maKhoa;
	private Integer maSoKhoa;
	
	
	/**
	 * 
	 * @throws Exception
	 */
	public void nhaplai() throws Exception {		
		resetValue();		
	}
	
	
	
	/**
	 * 
	 * @throws Exception
	 */
	public void displayInforSoLuuTru() throws Exception {
			if (soluutru ==null || soluutru.equals("")){
				resetValue();
				
				return;
			}
			HsbaNopDelegate hsbanopdele = HsbaNopDelegate.getInstance();
			HsbaNop hsbaNop = hsbanopdele.findBySoLuuTru(soluutru);
			if (hsbaNop != null){
				soluutru = hsbaNop.getHsbanopSoluutru();
				if(soluutru==null || soluutru.equals(""))
				{
					FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, soluutru);
					resetValue();
					return;
				}
				sovaovien = hsbaNop.getHsbaSovaovien(true).getHsbaSovaovien();
				DmKhoa khoa = hsbaNop.getHsbaSovaovien(true).getHsbaKhoarav();
				if (khoa != null){
					maKhoa = khoa.getDmkhoaMa();
					maSoKhoa = khoa.getDmkhoaMaso();
				}
			}else{
				FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, soluutru);
				resetValue();
			}
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void displayInforSoVaoVien() throws Exception {
		if (sovaovien ==null || sovaovien.equals("")){
			resetValue();
			return;
		}
		HsbaNopDelegate hsbanopdele = HsbaNopDelegate.getInstance();
		HsbaNop hsbaNop = hsbanopdele.findBySoVaoVien(sovaovien);
		if (hsbaNop != null){
			soluutru = hsbaNop.getHsbanopSoluutru();
			if(soluutru==null || soluutru.equals(""))
			{
				FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, soluutru);
				resetValue();
				return;
			}
			sovaovien = hsbaNop.getHsbaSovaovien(true).getHsbaSovaovien();
			DmKhoa khoa = hsbaNop.getHsbaSovaovien(true).getHsbaKhoarav();
			if (khoa != null){
				maKhoa = khoa.getDmkhoaMa();
				maSoKhoa = khoa.getDmkhoaMaso();
			}
		}else{
			FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, soluutru);
			resetValue();
		}
	}
	public static Logger getLog() {
		return log;
	}


	public static void setLog(Logger log) {
		TimSoBenhAnSoLuuTru.log = log;
	}



	public String getSovaovien() {
		return sovaovien;
	}


	public void setSovaovien(String sovaovien) {
		this.sovaovien = sovaovien;
	}


	public String getSoluutru() {
		return soluutru;
	}


	public void setSoluutru(String soluutru) {
		this.soluutru = soluutru;
	}


	public String getMaKhoa() {
		return maKhoa;
	}


	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}


	public Integer getMaSoKhoa() {
		return maSoKhoa;
	}


	public void setMaSoKhoa(Integer maSoKhoa) {
		this.maSoKhoa = maSoKhoa;
	}
	
	
}


