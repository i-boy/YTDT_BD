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
import com.iesvn.yte.entity.DmPhanLoaiTaiNan;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmPhanLoaiTaiNan")
@Scope(SESSION)
public class DmPhanLoaiTaiNanExt implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmPhanLoaiTaiNan  phanloaitainan;

	@DataModel
	private List<DmPhanLoaiTaiNan> listDmPhanLoaiTaiNan;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmPhanLoaiTaiNanExt....");
		
		resetValue();
		
		return "/B2_Dieutri/B266_Phanloaitainan";
	}

	public void search() {
		log.info("Search DmPhanLoaiTaiNanExt....");
		
		this.listDmPhanLoaiTaiNan = DieuTriUtilDelegate.getInstance()
									.findByAllConditions("DmPhanLoaiTaiNan", "dmpltainanMa", "dmpltainanTen", "dmpltainanDt"
											, this.phanloaitainan.getDmpltainanMa(), this.phanloaitainan.getDmpltainanTen(), true);
		
		
		if (this.listDmPhanLoaiTaiNan.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmPhanLoaiTaiNanExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDmPhanLoaiTaiNan.get(rowIndex).getDmpltainanMa();
		
			try {						
				DmPhanLoaiTaiNan be = this.listDmPhanLoaiTaiNan.get(rowIndex);
				
				be.setDmpltainanDt(false);
				be.setDmpltainanNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDmPhanLoaiTaiNan.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.phanloaitainan_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.phanloaitainan_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmPhanLoaiTaiNanExt....");

		resetValue();
	}
	
	public String goback() {
		log.info("Goback DmPhanLoaiTaiNanExt....");

		return "/MyMainForm";
	}
	
	private void resetValue() {
		this.phanloaitainan = new DmPhanLoaiTaiNan();
		this.listDmPhanLoaiTaiNan = new ArrayList<DmPhanLoaiTaiNan>();
	}

	
	public DmPhanLoaiTaiNan getPhanloaitainan() {
		return phanloaitainan;
	}

	public void setPhanloaitainan(DmPhanLoaiTaiNan phanloaitainan) {
		this.phanloaitainan = phanloaitainan;
	}

	public List<DmPhanLoaiTaiNan> getListDmPhanLoaiTaiNan() {
		return listDmPhanLoaiTaiNan;
	}

	public void setListDmPhanLoaiTaiNan(List<DmPhanLoaiTaiNan> listDmPhanLoaiTaiNan) {
		this.listDmPhanLoaiTaiNan = listDmPhanLoaiTaiNan;
	}
}
