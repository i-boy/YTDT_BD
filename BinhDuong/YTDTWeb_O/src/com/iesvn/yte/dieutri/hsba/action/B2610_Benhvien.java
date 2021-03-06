package com.iesvn.yte.dieutri.hsba.action;

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
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmLoaiBenhVien;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmTuyen;
import com.iesvn.yte.entity.DmVungMien;
import com.iesvn.yte.util.IConstantsRes;

@Name("B2610_Benhvien")
@Scope(SESSION)
public class B2610_Benhvien implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmBenhVien benhvien;

	@DataModel
	private List<DmBenhVien> listB2610;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init B2610_Benh vien....");
		
		resetValue();
		
		return "/B2_Dieutri/B2610_benhvien";
	}

	@SuppressWarnings("unchecked")
	public void search() {
		log.info("Search B2610_Benh vien....");
		
		this.listB2610 = DieuTriUtilDelegate.getInstance()
									.findByAllConditions("DmBenhVien", "dmbenhvienMa", "dmbenhvienTen", "dmbenhvienDt"
											, this.benhvien.getDmbenhvienMa(), this.benhvien.getDmbenhvienTen(), true);
		
		
		if (this.listB2610.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete B2610 Benh vien....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listB2610.get(rowIndex).getDmbenhvienMa();
						
			try {						
				DmBenhVien be = this.listB2610.get(rowIndex);
				
				be.setDmbenhvienDt(false);
				be.setDmbenhvienNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listB2610.remove(rowIndex);					//Cap nhat lai listB2610
				//this.search();

				FacesMessages.instance().add(IConstantsRes.benhvien_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.benhvien_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset B2610 Benh vien....");

		resetValue();
	}

	public String goback() {
		log.info("Goback B2610 Benh vien....");

		return "/MyMainForm";
	}
	
	private void resetValue() {
		
		this.benhvien = new DmBenhVien();
		this.benhvien.setDmhuyenMaso(new DmHuyen());
		this.benhvien.setDmloaibvMaso(new DmLoaiBenhVien());
		this.benhvien.setDmtinhMaso(new DmTinh());
		this.benhvien.setDmtuyenMaso(new DmTuyen());
		this.benhvien.setDmvungmienMaso(new DmVungMien());
		this.listB2610 = new ArrayList<DmBenhVien>();
	}

	public DmBenhVien getBenhvien() {
		return benhvien;
	}

	public void setBenhvien(DmBenhVien benhvien) {
		this.benhvien = benhvien;
	}

	public List<DmBenhVien> getListB2610() {
		return listB2610;
	}

	public void setListB2610(List<DmBenhVien> listB2610) {
		this.listB2610 = listB2610;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
}
