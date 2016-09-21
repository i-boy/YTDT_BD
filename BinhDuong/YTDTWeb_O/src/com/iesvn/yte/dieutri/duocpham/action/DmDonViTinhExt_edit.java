package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.jsf.ListDataModel;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmDonViTinh_edit")
@Scope(SESSION)
public class DmDonViTinhExt_edit implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmDonViTinh donvitinh;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmDonViTinhExt_edit....");
		
		resetValue();
		setValueForForm();
	
		return "/B4_Duocpham/KhoChinh/B4153_Donvitinh_edit";
	}

	public void save() {
		log.info("Save DmDonViTinhExt_edit....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.donvitinh.getDmdonvitinhMa();
		this.donvitinh.setDmdonvitinhNgaygiocn((double)date.getTime());
		
		try {
			DieuTriUtilDelegate.getInstance().edit(this.donvitinh);
			FacesMessages.instance().add(IConstantsRes.donvitinh_up_su, ma);
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.donvitinh_up_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmDonViTinhExt_edit....");
		
		return "/B4_Duocpham/KhoChinh/B4153_Donvitinh";
	}
	
	private void setValueForForm() {
		log.info("Set value for form DmDonViTinhExt_edit....");
		
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmDonViTinh");
		rowIndex = lsDataModel.getRowIndex();
		
		if (rowIndex != -1) {
			this.donvitinh = ((List<DmDonViTinh>)lsDataModel.getWrappedData()).get(rowIndex);
				
		}	
	}

	private void resetValue() {
		this.donvitinh = new DmDonViTinh();
	}

	public DmDonViTinh getDonvitinh() {
		return donvitinh;
	}

	public void setDonvitinh(DmDonViTinh donvitinh) {
		this.donvitinh = donvitinh;
	}

	
}
