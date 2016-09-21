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
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmTang")
@Scope(SESSION)
public class DmTangExt implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmTang  dmtang;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;

	@DataModel
	private List<DmTang> listDmTang;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmTangExt....");
		
		resetValue();
		
		return "/B2_Dieutri/B282_DmTang";
	}

	public void search() {
		log.info("Search DmTangExt....");
		
		this.listDmTang = DieuTriUtilDelegate.getInstance().findByAllConditions("DmTang", "dmtangMa", "dmtangTen", "dmtangChon", this.dmtang.getDmtangMa(), this.dmtang.getDmtangTen(), true);		
		
		if (this.listDmTang.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmTangExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDmTang.get(rowIndex).getDmtangMa();
		
			try {						
				DmTang be = this.listDmTang.get(rowIndex);
				
				be.setDmtangDefault(false);
				be.setDmtangNgaygiocn(date);
				be.setDmtangChon(false);
				
				DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
				DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
				if (nv != null) {
					this.dmtang.setDmtangNhanviencn(nv);
				}
				
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDmTang.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.tang_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.tang_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmTangExt....");

		resetValue();
	}
	
	public String goback() {
		log.info("Goback DmKhoaExt....");

		return "/MyMainForm";
	}
	
	private void resetValue() {
		this.dmtang = new DmTang();
		this.listDmTang = new ArrayList<DmTang>();
	}

	public DmTang getDmtang() {
		return dmtang;
	}

	public void setDmtang(DmTang dmtang) {
		this.dmtang = dmtang;
	}

	public List<DmTang> getListDmTang() {
		return listDmTang;
	}

	public void setListDmTang(List<DmTang> listDmTang) {
		this.listDmTang = listDmTang;
	}
	
		
}
