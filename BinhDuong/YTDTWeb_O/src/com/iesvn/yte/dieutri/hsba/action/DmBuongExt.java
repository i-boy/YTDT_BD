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
import com.iesvn.yte.dieutri.entity.DtDmBuong;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmBuong")
@Scope(SESSION)
public class DmBuongExt implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DtDmBuong  dmbuong;

	@DataModel
	private List<DtDmBuong> listDmBuong;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmBuongExt....");
		
		resetValue();
		
		return "/B2_Dieutri/B273_DmBuong";
	}

	public void search() {
		log.info("Search DmBuongExt....");
		
		this.listDmBuong = DieuTriUtilDelegate.getInstance()
									.findByAllConditions("DtDmBuong", "dtdmbMa", "dtdmbTen", "dtdmbChon"
											, this.dmbuong.getDtdmbMa(), this.dmbuong.getDtdmbTen(), true);
		
		
		if (this.listDmBuong.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmBuongExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDmBuong.get(rowIndex).getDtdmbMa();
		
			try {						
				DtDmBuong be = this.listDmBuong.get(rowIndex);
				
				be.setDtdmbChon(false);
				be.setDtdmbNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDmBuong.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.buong_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.buong_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmBuongExt....");

		resetValue();
	}
	
	public String goback() {
		log.info("Goback DmKhoaExt....");

		return "/MyMainForm";
	}
	
	private void resetValue() {
		this.dmbuong = new DtDmBuong();
		this.listDmBuong = new ArrayList<DtDmBuong>();
	}

	public DtDmBuong getDmbuong() {
		return dmbuong;
	}

	public void setDmbuong(DtDmBuong dmbuong) {
		this.dmbuong = dmbuong;
	}

	public List<DtDmBuong> getListDmBuong() {
		return listDmBuong;
	}

	public void setListDmBuong(List<DtDmBuong> listDmBuong) {
		this.listDmBuong = listDmBuong;
	}
	
		
}
