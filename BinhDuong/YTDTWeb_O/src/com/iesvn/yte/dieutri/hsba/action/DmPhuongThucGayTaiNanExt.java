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
import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmPhuongThucGayTaiNan")
@Scope(SESSION)
public class DmPhuongThucGayTaiNanExt implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmPhuongThucGayTaiNan  phuongthucgaytainan;

	@DataModel
	private List<DmPhuongThucGayTaiNan> listDmPhuongThucGayTaiNan;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmPhuongThucGayTaiNanExt....");
		
		resetValue();
		
		return "/B2_Dieutri/B267_Phuongthucgaytainan";
	}

	public void search() {
		log.info("Search DmPhuongThucGayTaiNanExt....");
		
		this.listDmPhuongThucGayTaiNan = DieuTriUtilDelegate.getInstance()
									.findByAllConditions("DmPhuongThucGayTaiNan", "dmptgtnMa", "dmptgtnTen", "dmptgtnDt"
											, this.phuongthucgaytainan.getDmptgtnMa(), this.phuongthucgaytainan.getDmptgtnTen(), true);
		
		
		if (this.listDmPhuongThucGayTaiNan.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmPhuongThucGayTaiNanExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDmPhuongThucGayTaiNan.get(rowIndex).getDmptgtnMa();
		
			try {						
				DmPhuongThucGayTaiNan be = this.listDmPhuongThucGayTaiNan.get(rowIndex);
				
				be.setDmptgtnDt(false);
				be.setDmptgtnNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDmPhuongThucGayTaiNan.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmPhuongThucGayTaiNanExt....");

		resetValue();
	}
	
	public String goback() {
		log.info("Goback DmPhuongThucGayTaiNanExt....");

		return "/MyMainForm";
	}
	
	private void resetValue() {
		this.phuongthucgaytainan = new DmPhuongThucGayTaiNan();
		this.listDmPhuongThucGayTaiNan = new ArrayList<DmPhuongThucGayTaiNan>();
	}

	public DmPhuongThucGayTaiNan getPhuongthucgaytainan() {
		return phuongthucgaytainan;
	}

	public void setPhuongthucgaytainan(DmPhuongThucGayTaiNan phuongthucgaytainan) {
		this.phuongthucgaytainan = phuongthucgaytainan;
	}

	public List<DmPhuongThucGayTaiNan> getListDmPhuongThucGayTaiNan() {
		return listDmPhuongThucGayTaiNan;
	}

	public void setListDmPhuongThucGayTaiNan(
			List<DmPhuongThucGayTaiNan> listDmPhuongThucGayTaiNan) {
		this.listDmPhuongThucGayTaiNan = listDmPhuongThucGayTaiNan;
	}

	
	
}
