/*
 * author : Bao
 */
package com.iesvn.yte.dieutri.vienphi.action;

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

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3218_XacNhanTreEm")
@Synchronized(timeout = 6000000)
public class XacNhanTreEmAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(XacNhanTreEmAction.class);
	private HsbaBhyt hsbaBhyt;	
	private String ghiNhanException;
	private String donViTuoi;
	private boolean isNew = true;
	
	@Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
	@Create
	@Begin(join=true)
	public void init() throws Exception {
		log.info("***Starting init ***");
		this.getHsbaBhyt(true).setHsbabhytNgaygiocn(new java.util.Date());
		//this.maPhu = new java.util.Date().getTime() + "";	
		log.info("***Finished init **");
		
	}
	@End
	public String back() throws Exception {
		log.info("***Starting back ***");
		
		log.info("***Finished back **");
		return "MyMainForm";
		
	}
	public void layTheoSoVaoVien() throws Exception {
		if (getHsbaBhyt() == null) return;
		log.info("***Starting layTheoSoVaoVien ***");
		String khoa = "null";
		String soVaoVien = "null";
		if(getHsbaBhyt().getHsbaSovaovien() != null && getHsbaBhyt().getHsbaSovaovien().getHsbaSovaovien() != null
				&& getHsbaBhyt().getHsbaSovaovien().getHsbaKhoadangdt() != null 
				&& getHsbaBhyt().getHsbaSovaovien().getHsbaKhoadangdt().getDmkhoaMa() != null) {
			try {
				khoa = getHsbaBhyt().getHsbaSovaovien().getHsbaKhoadangdt().getDmkhoaMa();
				soVaoVien =  getHsbaBhyt().getHsbaSovaovien().getHsbaSovaovien();
				qryBenhNhanBhtyThanhToan(khoa, soVaoVien);
				if (getHsbaBhyt() == null) {
					FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, khoa, IConstantsRes.SO_BENH_AN, soVaoVien);
				}
			} catch(Exception e) {
				FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, khoa, IConstantsRes.SO_BENH_AN, soVaoVien);
				log.error(e.toString());
			}	
			
		}
		log.info("***Finished layTheoSoVaoVien **");
		
	}
	// hsbaBhyt luon luon != null;
	private void qryBenhNhanBhtyThanhToan(String khoa, String soVaoVien) {
		HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
		hsbaBhyt = hsbaBhytDelegate.findBySoVaoVienKhoadangdtLastHsbaBhyt(soVaoVien, khoa);
		isNew = true;
		if (hsbaBhyt != null) {
			isNew = false;
		} else {
			HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();
			HsbaChuyenMon hsbaChuyenMon = hsbaChuyenMonDelegate.findBySoVaoVien_MaKhoa(soVaoVien, khoa);
			getHsbaBhyt(true).setHsbaSovaovien(hsbaChuyenMon.getHsbaSovaovien());
			getHsbaBhyt().setHsbabhytNgaygiocn(new java.util.Date());
		}
		if (getHsbaBhyt() != null && getHsbaBhyt().getHsbaSovaovien() != null 
				&& getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa() != null) {
			int dvt = getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getBenhnhanDonvituoi();
			donViTuoi = com.iesvn.yte.util.Utils.taoDonViTuoi(dvt);
			if (getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getDantocMa() != null) {
				log.info("benhNhan.getDantocMa().getDmdantocMa()=" + getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getDantocMa().getDmdantocMa());
				log.info("benhNhan.getDantocMa().getDmdantocTen()=" + getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getDantocMa().getDmdantocTen());
			}
			if (getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getDmgtMaso() != null) {
				log.info("benhNhan.getDmgtMaso()=" + getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getDmgtMaso().getDmgtMaso());
			}
		}			
	}
	
	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() {
		log.info("***Starting ghinhan **");
		ghiNhanException = null;
		
		HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
		if (getHsbaBhyt() != null) {
			if (getHsbaBhyt().getHsbabhytMakcb() != null && getHsbaBhyt().getHsbabhytMakcb().getDmbenhvienMaso() == null) {
				getHsbaBhyt().setHsbabhytMakcb(null);
			}
			//getHsbaBhyt().setHsbabhytKhoibh(new DtDmKhoiBhyt(88));
			try {	
				DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
				DmDoiTuong dt = (DmDoiTuong)dieuTriUtilDelegate.findByMa(Utils.maDoiTuongTreEm(), "DmDoiTuong", "dmdoituongMa");
				if (dt != null) {
					getHsbaBhyt().getHsbaSovaovien().setDoituongMa(dt);
				}
				if (isNew) {
					this.hsbaBhyt = hsbaBhytDelegate.create(getHsbaBhyt());
				} else {
					hsbaBhytDelegate.edit(getHsbaBhyt());
				}
				FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, getHsbaBhyt().getHsbaSovaovien().getHsbaKhoadangdt().getDmkhoaMa()
						, IConstantsRes.SO_BENH_AN, getHsbaBhyt().getHsbaSovaovien().getHsbaSovaovien());
			}catch(Exception e) {
				FacesMessages.instance().add(IConstantsRes.FAIL);
				log.error(e.toString());
				ghiNhanException = e.getClass().getName();
				log.error("Ghi nhan khong thanh cong");
			}	
		}
			
		log.info("***Finished ghinhan **");
	}

	// Ham khi nhan nut nhap lai
	public void nhaplai() throws Exception {
		try {
			resetForm();			
		} catch (Exception e) {
			log.error("Loi reset form : " + e.toString());
		}
	}
	// Ham reset form 
	private void resetForm() {
		log.info("Begining ResetForm(): ");
		this.hsbaBhyt = null;
		this.getHsbaBhyt(true).setHsbabhytNgaygiocn(new java.util.Date());
		this.donViTuoi = null;
		log.info("End ResetForm(): ");
	}
	
	public HsbaBhyt getHsbaBhyt() {
		return hsbaBhyt;
	}
	public HsbaBhyt getHsbaBhyt(boolean create) {
		if(create && hsbaBhyt == null) {
			hsbaBhyt = new HsbaBhyt();
		}
		return hsbaBhyt;
	}

	public void setHsbaBhyt(HsbaBhyt hsbaBhyt) {
		this.hsbaBhyt = hsbaBhyt;
	}

	
	public String getGhiNhanException() {	
		return ghiNhanException;
	}

	public void setGhiNhanException(String ghiNhanException) {
		this.ghiNhanException = ghiNhanException;
	}
/*	
	@Observer("org.jboss.seam.validationFailed")
	public void _validationFailed() {
		setGhiNhanException("_validationFailed");
		log.info("_validationFailed ----------11");
	}
*/
	public String getDonViTuoi() {
		return donViTuoi;
	}
	public void setDonViTuoi(String donViTuoi) {
		this.donViTuoi = donViTuoi;
	}
	
	
}
