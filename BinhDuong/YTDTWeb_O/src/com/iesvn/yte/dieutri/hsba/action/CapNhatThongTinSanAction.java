/*
 * author : Bao
 */
package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaSanDelegate;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaSan;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("B211_2_CapNhatThongTinSan")
@Synchronized(timeout = 6000000)
public class CapNhatThongTinSanAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(CapNhatThongTinSanAction.class);
	
	private HsbaSan hsbaSan;
	
	
	@In(required = false)
	@Out(required = false)
	private String soBenhAn;
	
	@In(required = false)
	@Out(required = false)
	private String khoaMa;
	
	@In(required = false)
	@Out(required = false)
	private String goToB211_2 = null;
	
	
	
	private Integer giosinh; //theo giay 
	private Integer giochet; //theo giay
	private String donViTuoi;
	private String ghiNhanException;
	
	
	
	@Factory("goToB211_2")
	@Create
	@Begin(nested=true)
	public String init() throws Exception {
		log.info("***Starting init ***");
//		soBenhAn = "511.3.10900000100";
//		khoaMa = "GMH";
		log.info("soBenhAn = " + soBenhAn);
		log.info("khoaMa = " + khoaMa);
		HsbaChuyenMon hsbaChuyenMon = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(this.soBenhAn, this.khoaMa);
		log.info("hsbaChuyenMon = " + hsbaChuyenMon);
		if (hsbaChuyenMon != null) {
			getHsbaSan(true).setHsbacmMa(hsbaChuyenMon);
		}
		if (getHsbaSan() != null && getHsbaSan().getHsbasanNgaygiosinh() != null) {
			giosinh = getSeconds(getHsbaSan().getHsbasanNgaygiosinh());
		} else {
			giosinh = Integer.valueOf(0);
		}
		if (getHsbaSan() != null && getHsbaSan().getHsbasanNgaygiochet() != null) {
			giochet = getSeconds(getHsbaSan().getHsbasanNgaygiochet());
		} else {
			giochet = Integer.valueOf(0);
		}
		log.info("***Finished init **");
		return "DieuTri_CapNhat_CapNhatThongTinSan";
	}
	@End
	public String back() throws Exception {
		log.info("***Starting back ***");
		log.info("***Finished back **");
		return "DieuTri_CapNhat_CapNhatThongTinChung";
		//return "MyMainForm";
	}
	/*
	public void layTheoSoVaoVien() throws Exception {
		log.info("***Starting layTheoSoVaoVien ***");
		log.info("--hsbaSan.getHsbacmMa()=" + hsbaSan.getHsbacmMa());
		if (hsbaSan != null && hsbaSan.getHsbacmMa() != null) {
			log.info("hsbaSan.getHsbacmMa().getKhoaMa()=" + hsbaSan.getHsbacmMa().getKhoaMa());
			if(hsbaSan.getHsbacmMa().getKhoaMa() != null) {
				String khoa = hsbaSan.getHsbacmMa().getKhoaMa().getDmkhoaMa();
				log.info("khoa=" + khoa);				
				if(StringUtils.isNotBlank(khoa)) {
					log.info("hsbaSan.getHsbacmMa().getHsbaSovaovien()=" + hsbaSan.getHsbacmMa().getHsbaSovaovien());
					if(hsbaSan.getHsbacmMa().getHsbaSovaovien() != null) {
						String soVaoVien = hsbaSan.getHsbacmMa().getHsbaSovaovien().getHsbaSovaovien();
						if(StringUtils.isNotBlank(soVaoVien)) {
							try {
								HsbaChuyenMon hsbaChuyenMon = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(soVaoVien, khoa);
								if (hsbaChuyenMon != null) {
									int dvt = hsbaChuyenMon.getHsbaSovaovien().getBenhnhanMa().getBenhnhanDonvituoi();
									donViTuoi = com.iesvn.yte.util.Utils.taoDonViTuoi(dvt);
									hsbaSan.setHsbacmMa(hsbaChuyenMon);
								}
							}catch(Exception e) {
								FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, khoa, IConstantsRes.SO_BENH_AN, soVaoVien);
								log.error(e.toString());
							}
						}
					}	
				}
			}
		}
		log.info("---- Ho ten=" + hsbaSan.getHsbacmMa().getHsbaSovaovien().getBenhnhanMa().getBenhnhanHoten());
		
		log.info("***Finished layTheoSoVaoVien **");
		
	}
	*/
	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() {
		log.info("***Starting ghinhan **");
		ghiNhanException = null;
		HsbaSanDelegate hsbaSanDelegate = HsbaSanDelegate.getInstance();
			
		if (hsbaSan != null) {	
			if (hsbaSan.getDmloaisinhMaso() != null && hsbaSan.getDmloaisinhMaso().getDmloaisinhMaso() == null) {
				hsbaSan.setDmloaisinhMaso(null);
			}
			if (hsbaSan.getHsbasanMabenh() != null && hsbaSan.getHsbasanMabenh().getDmbenhicdMaso() == null) {
				hsbaSan.setHsbasanMabenh(null);
			}
			if (hsbaSan.getHsbasanMatuvong() != null && hsbaSan.getHsbasanMatuvong().getDmbenhicdMaso() == null) {
				hsbaSan.setHsbasanMatuvong(null);
			}
			if (hsbaSan.getHsbasanChuyenkhoa() != null && hsbaSan.getHsbasanChuyenkhoa().getDmkhoaMaso() == null) {
				hsbaSan.setHsbasanChuyenkhoa(null);
			}
			try {
				if (getHsbaSan() != null && getHsbaSan().getHsbasanNgaygiosinh() != null) {
					getHsbaSan().setHsbasanNgaygiosinh(DateUtils.addSeconds(DateUtils.truncate(getHsbaSan().getHsbasanNgaygiosinh(), java.util.Calendar.DAY_OF_MONTH), giosinh));
				}
				if (getHsbaSan() != null && getHsbaSan().getHsbasanNgaygiochet() != null) {
					getHsbaSan().setHsbasanNgaygiochet(DateUtils.addSeconds(DateUtils.truncate(getHsbaSan().getHsbasanNgaygiochet(), java.util.Calendar.DAY_OF_MONTH), giochet));
				}
				if(hsbaSan.getHsbasanMa() == null) {					
					log.info("create HsbaSan ...");
					
					hsbaSan = hsbaSanDelegate.create(hsbaSan);
					log.info("create HsbaSan ... end.");
					FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", IConstantsRes.SO_BENH_AN, hsbaSan.getHsbacmMa().getHsbaSovaovien().getHsbaSovaovien());
				} else {
					log.info("edit HsbaSan ...");
					hsbaSanDelegate.edit(hsbaSan);
					log.info("edit HsbaSan ... end");
					FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", IConstantsRes.SO_BENH_AN, hsbaSan.getHsbacmMa().getHsbaSovaovien().getHsbaSovaovien());
				}
			} catch(Exception e) {
				FacesMessages.instance().add(IConstantsRes.FAIL);
				ghiNhanException = e.getClass().getName();
				log.error("Ghi nhan khong thanh cong");
				log.error("Loi : " + e);
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
		hsbaSan = null;
		ghiNhanException = null;
		this.donViTuoi = null;
		log.info("End ResetForm(): ");
	}
	public Integer getSeconds(java.util.Date d) {
		long seconds = DateUtils.getFragmentInHours(d, java.util.Calendar.DATE)  * 60 * 60  + DateUtils.getFragmentInMinutes(d, java.util.Calendar.HOUR_OF_DAY) * 60;
		return Integer.valueOf(seconds + "");
	}
/*	
	@Observer("org.jboss.seam.validationFailed")
	public void _validationFailed() {
		ghiNhanException = "_validationFailed";
		log.info("_validationFailed ----------11");
	}
	*/
	public HsbaSan getHsbaSan(boolean create) {
		if (create && hsbaSan == null) {
			hsbaSan = new HsbaSan();
		}
		return hsbaSan;
	}
	public HsbaSan getHsbaSan() {
		return hsbaSan;
	}
	public void setHsbaSan(HsbaSan hsbaSan) {
		this.hsbaSan = hsbaSan;
	}
	public String getGhiNhanException() {
		return ghiNhanException;
	}
	public void setGhiNhanException(String ghiNhanException) {
		this.ghiNhanException = ghiNhanException;
	}
	
	public String getDonViTuoi() {
		return donViTuoi;
	}
	public void setDonViTuoi(String donViTuoi) {
		this.donViTuoi = donViTuoi;
	}
	public Integer getGiosinh() {
		return giosinh;
	}
	public void setGiosinh(Integer giosinh) {
		this.giosinh = giosinh;
	}
	
	/*
	public String getSoBenhAn() {
		return soBenhAn;
	}
	public void setSoBenhAn(String soBenhAn) {
		this.soBenhAn = soBenhAn;
	}
	public String getKhoaMa() {
		return khoaMa;
	}
	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}
	*/
	public String getGoToB211_2() {
		return goToB211_2;
	}
	public void setGoToB211_2(String goToB211_2) {
		this.goToB211_2 = goToB211_2;
	}
	public Integer getGiochet() {
		return giochet;
	}
	public void setGiochet(Integer giochet) {
		this.giochet = giochet;
	}
	
}
