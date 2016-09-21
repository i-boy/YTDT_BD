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
import com.iesvn.yte.entity.DmTaiNan;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmTaiNan")
@Scope(SESSION)
public class DmTaiNanExt implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmTaiNan  tainan;

	@DataModel
	private List<DmTaiNan> listDmTaiNan;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmTaiNanExt....");
		
		resetValue();
		
		return "/B2_Dieutri/B2611_Tainan";
	}

	public void search() {
		log.info("Search DmTaiNanExt....");
		
		this.listDmTaiNan = DieuTriUtilDelegate.getInstance()
									.findByAllConditions("DmTaiNan", "dmtainanMa", "dmtainanTen", "dmtainanDt"
											, this.tainan.getDmtainanMa(), this.tainan.getDmtainanTen(), true);
		
		
		if (this.listDmTaiNan.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmTaiNanExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDmTaiNan.get(rowIndex).getDmtainanMa();
		
			try {						
				DmTaiNan be = this.listDmTaiNan.get(rowIndex);
				
				be.setDmtainanDt(false);
				be.setDmtainanNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDmTaiNan.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.tainan_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.tainan_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmTaiNanExt....");

		resetValue();
	}
	
	public String goback() {
		log.info("Goback DmTaiNanExt....");

		return "/MyMainForm";
	}
	
	private void resetValue() {
		this.tainan = new DmTaiNan();
		this.listDmTaiNan = new ArrayList<DmTaiNan>();
	}

	public DmTaiNan getTainan() {
		return tainan;
	}

	public void setTainan(DmTaiNan tainan) {
		this.tainan = tainan;
	}

	public List<DmTaiNan> getListDmTaiNan() {
		return listDmTaiNan;
	}

	public void setListDmTaiNan(List<DmTaiNan> listDmTaiNan) {
		this.listDmTaiNan = listDmTaiNan;
	}

	
}
