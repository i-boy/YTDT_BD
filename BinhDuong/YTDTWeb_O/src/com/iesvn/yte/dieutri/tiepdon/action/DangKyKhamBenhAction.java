package com.iesvn.yte.dieutri.tiepdon.action;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.util.IConstantsRes;

@SuppressWarnings("serial")
@Scope(ScopeType.SESSION)
@Name("B111_Kiemtrangaytaikham")
@Synchronized(timeout = 6000000)
public class DangKyKhamBenhAction  implements Serializable {

	@RequestParameter("messg")
	private String messg;
	
	@RequestParameter("mabn")
	public String maBN;
	
	@RequestParameter("matd")
	public String maTD;
	
	@In(required=false)
	@Out(required=false)
	private String benhNhan_ma;
	
	@In(required=false)
	@Out(required=false)
	private String tiepDon_ma;
	
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strInit;
	
	private boolean kiemtra = true;
	
	@In(required=false)
	@Out(required=false)
	private String maTiepDonOut;
	
	@In(required=false)
	@Out(required=false)
	private String maBanKhamOut;
	
	@In(required=false)
	@Out(required=false)
	private String goToThamKhamVaXuTri;
	
	public String getGoToThamKhamVaXuTri() {
		return goToThamKhamVaXuTri;
	}
	public void setGoToThamKhamVaXuTri(String goToThamKhamVaXuTri) {
		this.goToThamKhamVaXuTri = goToThamKhamVaXuTri;
	}
	public String getMaBanKhamOut() {
		return maBanKhamOut;
	}
	public void setMaBanKhamOut(String maBanKhamOut) {
		this.maBanKhamOut = maBanKhamOut;
	}
	@Factory("strInit")
	public void init() {
		benhNhan_ma = maBN;
		tiepDon_ma = maTD;
		strInit = "";
	}
	public String getMessg() {
		return messg;
	}

	public void setMessg(String messg) {
		this.messg = messg;
	}
	
	public String getMaBN() {
		return maBN;
	}

	public void setMaBN(String maBN) {
		this.maBN = maBN;
	}

	public String getMaTD() {
		return maTD;
	}

	public void setMaTD(String maTD) {
		this.maTD = maTD;
	}

	public boolean isKiemtra() {
		return kiemtra;
	}

	public void setKiemtra(boolean kiemtra) {
		this.kiemtra = kiemtra;
	}

	public String getBenhNhan_ma() {
		return benhNhan_ma;
	}

	public void setBenhNhan_ma(String benhNhanMa) {
		benhNhan_ma = benhNhanMa;
	}

	public String getTiepDon_ma() {
		return tiepDon_ma;
	}

	public void setTiepDon_ma(String tiepdonMa) {
		tiepDon_ma = tiepdonMa;
	}

	public String linkLichSuKB() {
		kiemtra = false;
		return "/B1_Tiepdon/B115_Lichsubenhnhan.xhtml";
	}
	
	public String thamKham() throws ServiceException, RemoteException {
		System.out.println("DangKyKhamBenhAction.thamKham()*****************matd=" + maTiepDonOut);
		goToThamKhamVaXuTri = null;
		return "TiepDon_KhamBenh_ThamKhamXuTri";
	}
	public String getMaTiepDonOut() {
		return maTiepDonOut;
	}
	public void setMaTiepDonOut(String maTiepDonOut) {
		this.maTiepDonOut = maTiepDonOut;
	}
	
}