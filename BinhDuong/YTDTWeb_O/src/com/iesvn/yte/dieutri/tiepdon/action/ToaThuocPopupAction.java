package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.PAGE;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;

import com.iesvn.yte.dieutri.entity.ThuocPhongKham;

@Scope(PAGE)
@Name("ToaThuocPopup")
@Synchronized(timeout = 6000000)
public class ToaThuocPopupAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -493331384169670628L;
	private static Logger log = Logger.getLogger(ToaThuocPopupAction.class);	

	public static String maTiepDon;
	public static String hoTenBN;
	@DataModel
	public static java.util.List<ThuocPhongKham> ctThuocPhongKhamsPO;
	
	public String getMaTiepDon() {
		return maTiepDon;
	}
	public void setMaTiepDon(String maTiepDon) {
		this.maTiepDon = maTiepDon;
	}
	public String getHoTenBN() {
		return hoTenBN;
	}
	public void setHoTenBN(String hoTenBN) {
		this.hoTenBN = hoTenBN;
	}
	public java.util.List<ThuocPhongKham> getctThuocPhongKhamsPO() {
		return ctThuocPhongKhamsPO;
	}
	public void setctThuocPhongKhamsPO(
			java.util.List<ThuocPhongKham> ctThuocPhongKhamsPO) {
		this.ctThuocPhongKhamsPO = ctThuocPhongKhamsPO;
	}
	
	

}
