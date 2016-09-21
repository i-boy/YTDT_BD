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
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmNhaSanXuat")
@Scope(SESSION)
public class DmNhaSanXuatExt implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmNhaSanXuat nhasanxuat;

	@DataModel
	private List<DmNhaSanXuat> listDmNhaSanXuat;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmNhaSanXuatExt....");
		
		resetValue();
		
		return "/B4_Duocpham/KhoChinh/B4154_Nhasanxuat";
	}

	public void search() {
		log.info("Search DmNhaSanXuatExt....");
		
		this.listDmNhaSanXuat = DieuTriUtilDelegate.getInstance()
									.findByAllConditions("DmNhaSanXuat", "dmnhasanxuatMa", "dmnhasanxuatTen", "dmnhasanxuatDt"
											, this.nhasanxuat.getDmnhasanxuatMa(), this.nhasanxuat.getDmnhasanxuatTen(), true);
		
		
		if (this.listDmNhaSanXuat.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmNhaSanXuatExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDmNhaSanXuat.get(rowIndex).getDmnhasanxuatMa();
		
			try {						
				DmNhaSanXuat be = this.listDmNhaSanXuat.get(rowIndex);
				
				be.setDmnhasanxuatDt(false);
				be.setDmnhasanxuatNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDmNhaSanXuat.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.nhasanxuat_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.nhasanxuat_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmNhaSanXuatExt....");

		resetValue();
	}
	
	public String goback() {
		log.info("Goback DmNhaSanXuatExt....");

		return "/MyMainForm";
	}

	private void resetValue() {
		this.nhasanxuat = new DmNhaSanXuat();
		this.listDmNhaSanXuat = new ArrayList<DmNhaSanXuat>();
	}

	public DmNhaSanXuat getNhasanxuat() {
		return nhasanxuat;
	}

	public void setNhasanxuat(DmNhaSanXuat nhasanxuat) {
		this.nhasanxuat = nhasanxuat;
	}

	public List<DmNhaSanXuat> getListDmNhaSanXuat() {
		return listDmNhaSanXuat;
	}

	public void setListDmNhaSanXuat(List<DmNhaSanXuat> listDmNhaSanXuat) {
		this.listDmNhaSanXuat = listDmNhaSanXuat;
	}

	

	
}
