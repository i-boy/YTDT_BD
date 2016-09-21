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
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmNhaSanXuat_edit")
@Scope(SESSION)
public class DmNhaSanXuatExt_edit implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmNhaSanXuat nhasanxuat;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmNhaSanXuatExt_edit....");
		
		resetValue();
		setValueForForm();
	
		return "/B4_Duocpham/KhoChinh/B4154_Nhasanxuat_edit";
	}

	public void save() {
		log.info("Save DmNhaSanXuatExt_edit....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.nhasanxuat.getDmnhasanxuatMa();
		this.nhasanxuat.setDmnhasanxuatNgaygiocn((double)date.getTime());
		
		try {
			DieuTriUtilDelegate.getInstance().edit(this.nhasanxuat);
			FacesMessages.instance().add(IConstantsRes.nhasanxuat_up_su, ma);
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.nhasanxuat_up_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmNhaSanXuatExt_edit....");
		
		return "/B4_Duocpham/KhoChinh/B4154_Nhasanxuat";
	}
	
	private void setValueForForm() {
		log.info("Set value for form DmNhaSanXuatExt_edit....");
		
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmNhaSanXuat");
		rowIndex = lsDataModel.getRowIndex();
		
		if (rowIndex != -1) {
			this.nhasanxuat = ((List<DmNhaSanXuat>)lsDataModel.getWrappedData()).get(rowIndex);
				
		}	
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
