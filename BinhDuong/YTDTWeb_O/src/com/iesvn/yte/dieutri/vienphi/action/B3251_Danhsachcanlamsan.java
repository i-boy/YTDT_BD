package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.iesvn.yte.dieutri.entity.DtDmCls;
import com.iesvn.yte.util.IConstantsRes;

@Name("B3251_Danhsachcanlamsan")
@Scope(SESSION)
public class B3251_Danhsachcanlamsan implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DtDmCls cls;
	
	@DataModel
	private List<DtDmCls> listCls;

	@Logger
	private Log log;

	public DtDmCls getCls() {
		return cls;
	}

	public void setCls(DtDmCls cls) {
		this.cls = cls;
	}

	public List<DtDmCls> getListCls() {
		return listCls;
	}

	public void setListCls(List<DtDmCls> listCls) {
		this.listCls = listCls;
	}
	
	@Create
	public String init() {
		log.info("Init canlamsan...");
		
		resetValue();
		
		return "/B3_Vienphi/ThuVienPhi/B3251_Danhsachcanlamsan";
	}

	public void search() {
		log.info("Search canlamsan...");
		
		this.listCls = DieuTriUtilDelegate.getInstance().findByAllConditions("DtDmCls", "dtdmclsMa", "dtdmclsTen", "dtdmclsChon", this.cls.getDtdmclsMa(), this.cls.getDtdmclsTen(), true);
		
		if (this.listCls.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
	}

	public void delete(int rowIndex) {
		log.info("Delete canlamsan...");

		if (rowIndex != -1) {
			String ma = "";
			ma = this.listCls.get(rowIndex).getDtdmclsMa();
			
			try {						
				DtDmCls be = this.listCls.get(rowIndex);
				
				be.setDtdmclsChon(false);
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listCls.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.canlamsan_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.canlamsan_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset canlamsan....");

		resetValue();
	}
	
	public String goMainPage() {
		log.info("Go main page...");
		
		return "/MyMainForm";
	}

	private void resetValue() {
		this.cls = new DtDmCls();
		this.listCls = new ArrayList<DtDmCls>();
	}
}
