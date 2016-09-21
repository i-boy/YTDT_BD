package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmNhaSanXuat_add")
@Scope(SESSION)
public class DmNhaSanXuatExt_add implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmNhaSanXuat nhasanxuat;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmNhaSanXuatExt_add....");
		
		resetValue();
	
		return "/B4_Duocpham/KhoChinh/B4154_Nhasanxuat_add";
	}

	public void save() {
		log.info("Save DmNhaSanXuatExt_add....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.nhasanxuat.getDmnhasanxuatMa();
		this.nhasanxuat.setDmnhasanxuatNgaygiocn((double)date.getTime());
		this.nhasanxuat.setDmnhasanxuatDt(true);
		setMaFromTen();
		
		try {
			DieuTriUtilDelegate.getInstance().create(this.nhasanxuat);
			FacesMessages.instance().add(IConstantsRes.nhasanxuat_cr_su, ma);
			reset();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.nhasanxuat_cr_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmNhaSanXuatExt_add....");
		
		return "/B4_Duocpham/KhoChinh/B4154_Nhasanxuat";
	}
	
	private void setMaFromTen() {
		log.info("setMaFromTen DmNhaSanXuatExt_add....");
		
		int count = 0;
		String maTemp = this.nhasanxuat.getDmnhasanxuatMa();
		List<DmNhaSanXuat> ls = new ArrayList<DmNhaSanXuat>();
				
		ls = DieuTriUtilDelegate.getInstance().findMaLike("DmNhaSanXuat", "dmnhasanxuatMa", "dmnhasanxuatDt"
													, this.nhasanxuat.getDmnhasanxuatMa(), true);
	
		for (DmNhaSanXuat dmNhaSanXuat : ls) {
			String temp = dmNhaSanXuat.getDmnhasanxuatMa().substring(6);
			int counTemp = Integer.parseInt(temp);
			
			if (count < counTemp) {
				count = counTemp;
			}
		}
		
		count++;
		
		if ((count+"").length()==1) {
			
			if (count == 0) {
				maTemp += "00001";
			} else {
				maTemp += "0000" + count;
			}
			
		} else if ((count+"").length()==2) {
			
			maTemp += "000" + count;
			
		} else if ((count+"").length()==3) {
			
			maTemp += "00" + count;
			
		} else if ((count+"").length()==4) {
			
			maTemp += "0" + count;
			
		} else if ((count+"").length()==5) {
			
			maTemp += count;
			
		} else if ((count+"").length()==4) {
			
			maTemp += "99999" + count;
		}
		
		this.nhasanxuat.setDmnhasanxuatMa(maTemp);
	}
	
	public void reset() {
		log.info("Reset DmNhaSanXuatExt_add....");

		resetValue();
	}

	private void resetValue() {
		this.nhasanxuat = new DmNhaSanXuat();
	}

	public DmNhaSanXuat getNhasanxuat() {
		return nhasanxuat;
	}

	public void setNhasanxuat(DmNhaSanXuat nhasanxuat) {
		this.nhasanxuat = nhasanxuat;
	}
}
