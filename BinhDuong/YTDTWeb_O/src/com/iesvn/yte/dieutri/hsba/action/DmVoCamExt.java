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
import com.iesvn.yte.dieutri.entity.DtDmVoCam;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmVoCam")
@Scope(SESSION)
public class DmVoCamExt implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DtDmVoCam  vocam;

	@DataModel
	private List<DtDmVoCam> listDmVoCam;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmVoCamExt....");
		
		resetValue();
		
		return "/B2_Dieutri/B261_Vocam";
	}

	public void search() {
		log.info("Search DmVoCamExt....");
		
		this.listDmVoCam = DieuTriUtilDelegate.getInstance()
									.findByAllConditions("DtDmVoCam", "dtdmvocamMa", "dtdmvocamDiengiai", "dtdmvocamChon"
											, this.vocam.getDtdmvocamMa(), this.vocam.getDtdmvocamDiengiai(), true);
		
		
		if (this.listDmVoCam.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmVoCamExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDmVoCam.get(rowIndex).getDtdmvocamMa();
		
			try {						
				DtDmVoCam be = this.listDmVoCam.get(rowIndex);
				
				be.setDtdmvocamChon(false);
				be.setDtdmvocamNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDmVoCam.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.vocam_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.vocam_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmVoCamExt....");

		resetValue();
	}
	
	public String goback() {
		log.info("Goback DmVoCamExt....");

		return "/MyMainForm";
	}
	
	private void resetValue() {
		this.vocam = new DtDmVoCam();
		this.listDmVoCam = new ArrayList<DtDmVoCam>();
	}

	public DtDmVoCam getVocam() {
		return vocam;
	}

	public void setVocam(DtDmVoCam vocam) {
		this.vocam = vocam;
	}

	public List<DtDmVoCam> getListDmVoCam() {
		return listDmVoCam;
	}

	public void setListDmVoCam(List<DtDmVoCam> listDmVoCam) {
		this.listDmVoCam = listDmVoCam;
	}

	
}
