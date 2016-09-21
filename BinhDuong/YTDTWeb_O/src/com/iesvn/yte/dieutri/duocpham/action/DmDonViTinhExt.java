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
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmDonViTinh")
@Scope(SESSION)
public class DmDonViTinhExt implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmDonViTinh donvitinh;

	@DataModel
	private List<DmDonViTinh> listDmDonViTinh;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmDonViTinhExt....");
		
		resetValue();
		
		return "/B4_Duocpham/KhoChinh/B4153_Donvitinh";
	}

	public void search() {
		log.info("Search DmDonViTinhExt....");
		
		this.listDmDonViTinh = DieuTriUtilDelegate.getInstance()
									.findByAllConditions("DmDonViTinh", "dmdonvitinhMa", "dmdonvitinhTen", "dmdonvitinhDt"
											, this.donvitinh.getDmdonvitinhMa(), this.donvitinh.getDmdonvitinhTen(), true);
		
		
		if (this.listDmDonViTinh.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmDonViTinhExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDmDonViTinh.get(rowIndex).getDmdonvitinhMa();
		
			try {						
				DmDonViTinh be = this.listDmDonViTinh.get(rowIndex);
				
				be.setDmdonvitinhDt(false);
				be.setDmdonvitinhNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDmDonViTinh.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.donvitinh_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.donvitinh_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmDonViTinhExt....");

		resetValue();
	}
	
	public String goback() {
		log.info("Goback DmDonViTinhExt....");

		return "/MyMainForm";
	}

	private void resetValue() {
		this.donvitinh = new DmDonViTinh();
		this.listDmDonViTinh = new ArrayList<DmDonViTinh>();
	}

	public DmDonViTinh getDonvitinh() {
		return donvitinh;
	}

	public void setDonvitinh(DmDonViTinh donvitinh) {
		this.donvitinh = donvitinh;
	}

	public List<DmDonViTinh> getListDmDonViTinh() {
		return listDmDonViTinh;
	}

	public void setListDmDonViTinh(List<DmDonViTinh> listDmDonViTinh) {
		this.listDmDonViTinh = listDmDonViTinh;
	}

	
	
}
