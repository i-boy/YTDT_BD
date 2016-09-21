package com.iesvn.yte.identity.extension;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.NguoiDungVaiTroDelegate;
import com.iesvn.yte.dieutri.delegate.VaiTroDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.entity.NguoiDung;
import com.iesvn.yte.entity.NguoiDungVaiTro;
import com.iesvn.yte.entity.VaiTro;
import com.iesvn.yte.util.IConstantsRes;


@Scope(ScopeType.SESSION)
@Name("PhanQuyenAction")
public class PhanQuyenAction implements Serializable{
	private static final long serialVersionUID = 1L;
	@DataModel
	private java.util.List<QuyenExt> ctQuyenExts;
	private static Logger log = Logger.getLogger(PhanQuyenAction.class);
	
	private Integer nhanVienMaso;
	private String nhanVienMa;
	

	public void displayInfor(){
		log.info("-----displayInfor()-----");
		NguoiDungVaiTroDelegate ndVtDelegate = NguoiDungVaiTroDelegate.getInstance();
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.find(nhanVienMaso);
		
		log.info("-----nhanVienMaso:"+nhanVienMaso);
		NguoiDung nd = nv.getNdMaso();
		if (nd != null){
			List<NguoiDungVaiTro> lstNdVt = ndVtDelegate.findByNguoiDung(nd.getNdMaso());
			
			if (lstNdVt != null) {
				log.info("-----lstNdVt is not null");
				log.info("-----lstNdVt.size(): " + lstNdVt.size());
				for (NguoiDungVaiTro ndvt: lstNdVt) {
					if (ctQuyenExts != null) {
						
						log.info("-----ctQuyenExts is not null--ctQuyenExts:"+ndvt.getNdvaitroMaso());
						for (QuyenExt quyen: ctQuyenExts) {
							log.info("-----ctQuyenExts is not null");
							if (quyen != null && quyen.getVaitro() !=null) {
								
								if (quyen.getVaitro().getVaitroMaso().intValue() == ndvt.getVaitroMaso().getVaitroMaso().intValue()){
									quyen.setChon(new Boolean(true));
								}
							}
						}
						
					}
				}
			}
		}
	}
	
	@Create
	public void init() {
		log.info("begin init()");
		ctQuyenExts = new ArrayList<QuyenExt>();
		VaiTroDelegate vtDelegate = VaiTroDelegate.getInstance();
		java.util.List<VaiTro> lstVaiTro  = vtDelegate.findAll();
		if (lstVaiTro != null){
			for (VaiTro vt: lstVaiTro){
				QuyenExt quyenext = new QuyenExt();
				quyenext.setVaitro(vt);
				quyenext.setChon(new Boolean(false));
				ctQuyenExts.add(quyenext);
			}
		}
		//LY THEM CODE============
		dtDmNhanVienDelegate = DtDmNhanVienDelegate.getInstance();
		for(DtDmNhanVien each : dtDmNhanVienDelegate.findAll()){
			listDtDmNhanViens.add(new SelectItem(each.getDtdmnhanvienTen()));
		}
		//END=========================
		log.info("end init()");
	}
	public java.util.List<QuyenExt> getCtQuyenExts() {
		return ctQuyenExts;
	}

	public void setCtQuyenExts(java.util.List<QuyenExt> ctQuyenExts) {
		this.ctQuyenExts = ctQuyenExts;
	}

	public Integer getNhanVienMaso() {
		return nhanVienMaso;
	}

	public void setNhanVienMaso(Integer nhanVienMaso) {
		this.nhanVienMaso = nhanVienMaso;
	}

	public String getNhanVienMa() {
		return nhanVienMa;
	}

	public void setNhanVienMa(String nhanVienMa) {
		this.nhanVienMa = nhanVienMa;
	}

	public void ghiNhan(){
		log.info("-----ghiNhan()-----");
		VaiTroDelegate vtDelegate = VaiTroDelegate.getInstance();
		DtDmNhanVienDelegate dtdmNVDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nhanvien = dtdmNVDelegate.find(nhanVienMaso);
		NguoiDung nd = null;
		if (nhanvien != null){
			log.info("-----nhanvien is not null: " + nhanvien);
			if(nhanvien.getNdMaso() != null){
				nd = nhanvien.getNdMaso();
			} else {
				FacesMessages.instance().add(IConstantsRes.CAP_QUYEN_THAT_BAI);
				return;
			}
		}
		List<VaiTro> lstVaiTro = new ArrayList<VaiTro>();
		if (ctQuyenExts != null) {
			log.info("-----ctQuyenExts is not null: " + ctQuyenExts);
			for (QuyenExt quyen: ctQuyenExts) {
				log.info("-----quyen: " + quyen);
				if (quyen.getChon() != null && quyen.getChon().booleanValue() == true){
					lstVaiTro.add(quyen.getVaitro());
				}
			}
		}
		
		vtDelegate.capNhatVaiTro(nhanVienMaso,nd , lstVaiTro);
		FacesMessages.instance().add(IConstantsRes.CAP_QUYEN_THANH_CONG);
		
	}
	
	public void reset(){
		log.info("-----reset()-----");
		nhanVienMaso = new Integer("0");
		nhanVienMa = "";
		nhanVienTen = "";
		for (QuyenExt quyen: ctQuyenExts) {
			quyen.setChon(false);
		}
	}
	
//LY THEM CODE======================
	private String nhanVienTen;
	private DtDmNhanVienDelegate dtDmNhanVienDelegate;
	private List<SelectItem> listDtDmNhanViens = new ArrayList<SelectItem>();
	
	public List<SelectItem> getListDtDmNhanViens() {
		return listDtDmNhanViens;
	}

	public void setListDtDmNhanViens(List<SelectItem> listDtDmNhanViens) {
		this.listDtDmNhanViens = listDtDmNhanViens;
	}
	public String getNhanVienTen() {
		return nhanVienTen;
	}

	public void setNhanVienTen(String nhanVienTen) {
		this.nhanVienTen = nhanVienTen;
	}
	
	public void onblurTenNhanVienAction(){
		log.info("-----BEGIN onblurTenNhanVienAction()-----");
		DtDmNhanVien nhanvien = dtDmNhanVienDelegate.findByTenNhanVien(nhanVienTen);
		//Tùy logic form mà set cho đúng
		if(nhanvien != null) {
			nhanVienMaso = nhanvien.getDtdmnhanvienMaso();
			nhanVienMa = nhanvien.getDtdmnhanvienMa();
			log.info("-----DA THAY DTDMNHANVIEN-----");
		}
	}
	
	public void onblurMaNhanVienAction(){
		log.info("-----BEGIN onblurMaNhanVienAction()-----");
		DtDmNhanVien nhanvien = dtDmNhanVienDelegate.findByMaNhanVien(nhanVienMa);
		//Tùy logic form mà set cho đúng
		if(nhanvien != null) {
			nhanVienMaso = nhanvien.getDtdmnhanvienMaso();
			nhanVienTen = nhanvien.getDtdmnhanvienTen();
			log.info("-----DA THAY DTDMNHANVIEN-----");
		}
	}

	
	
		
}
