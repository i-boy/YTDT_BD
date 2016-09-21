package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.jsf.ListDataModel;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DtDmClsDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmPbClsDelegate;
import com.iesvn.yte.dieutri.entity.DtDmCls;
import com.iesvn.yte.dieutri.entity.DtDmPbCls;
import com.iesvn.yte.util.IConstantsRes;

@Name("B3251_Danhsachcanlamsan_edit")
@Scope(SESSION)
public class B3251_Danhsachcanlamsan_edit implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private DtDmCls cls;

	@Logger
	private Log log;
	
	private List<SelectItem> listPhanbiet;
	
	private DtDmPbClsDelegate phanbietDAO;
	
	//------------------------
	public List<SelectItem> getListPhanbiet() {
		return listPhanbiet;
	}

	public void setListPhanbiet(List<SelectItem> listPhanbiet) {
		this.listPhanbiet = listPhanbiet;
	}

	public DtDmCls getCls() {
		return cls;
	}

	public void setCls(DtDmCls cls) {
		this.cls = cls;
	}
	
	public void initListPhanbiet(){
		listPhanbiet = new ArrayList<SelectItem>();
		for(DtDmPbCls each : phanbietDAO.findAll())
			listPhanbiet.add(new SelectItem(each.getDtdmpbclsMaso(),each.getDtdmpbclsTen()));
	}
	
	@Create
	public String init() {
		log.info("Init canlamsan_edit...");
		phanbietDAO = DtDmPbClsDelegate.getInstance();
		resetValue();
		setValueForForm();
		initListPhanbiet();
		
		return "/B3_Vienphi/ThuVienPhi/B3251_Danhsachcanlamsan_edit";
	}

	public void save() {
		log.info("Save canlamsan...");
		
		String ma = "";
		
		ma = this.cls.getDtdmclsMa();
		setValueDmCls();
		
		try {
			DtDmClsDelegate.getInstance().edit(this.cls);
			FacesMessages.instance().add(IConstantsRes.canlamsan_up_su, ma);
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.canlamsan_up_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack Danhsachcanlamsan...");
		
		return "/B3_Vienphi/ThuVienPhi/B3251_Danhsachcanlamsan";
	}
	
	public void setValueDmCls() {
		Date date = new Date();
		this.cls.setDtdmclsNgaygiocn((double)date.getTime());
	}
	
	private void setValueForForm() {
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listCls");
		rowIndex = lsDataModel.getRowIndex();
		this.cls = ((List<DtDmCls>)lsDataModel.getWrappedData()).get(rowIndex); 
	}

	public void reset() {
		log.info("Reset canlamsan...");

		resetValue();
	}

	private void resetValue() {
		this.cls = new DtDmCls();
	}
}
