package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

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
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNhaCungCap;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmNhaCungCap_edit")
@Scope(SESSION)
public class DmNhaCungCapExt_edit implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int emptyValueCbo = -1;
	private static final String emptyLabelCbo = "...";

	@In(required = false)
	@Out(required = false)
	private DmNhaCungCap nhacungcap;

	private List<SelectItem> listTinh;
	private List<SelectItem> listNguonChuongTrinh;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmNhaCungCapExt_edit....");
		
		resetValue();
		initComboxTinh();
		initComboxNguonChuongTrinh();
		setValueForForm();
		
		return "/B4_Duocpham/KhoChinh/B4152_Nhacungcap_edit";
	}

	public void save(int tinhId, int nguonCTId) {
		log.info("Save DmNhaCungCapExt_edit....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.nhacungcap.getDmnhacungcapMa();
		this.nhacungcap.setDmnhacungcapNgaygiocn((double)date.getTime());
	
		if (tinhId == this.emptyValueCbo) {
			this.nhacungcap.setDmtinhMaso(null);
		}
		
		if (nguonCTId == this.emptyValueCbo) {
			this.nhacungcap.setDmnctMaso(null);
		}
		
		try {
			DieuTriUtilDelegate.getInstance().edit(this.nhacungcap);
			FacesMessages.instance().add(IConstantsRes.nhacungcap_up_su, ma);
			
			if (tinhId == this.emptyValueCbo) {
				this.nhacungcap.setDmtinhMaso(new DmTinh());
			}
			
			if (nguonCTId == this.emptyValueCbo) {
				this.nhacungcap.setDmnctMaso(new DmNguonChuongTrinh());
			}
			
//			resetValue();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.nhacungcap_up_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmNhaCungCapExt_edit....");
		
		return "/B4_Duocpham/KhoChinh/B4152_Nhacungcap";
	}
	
	private void setValueForForm() {
		log.info("Set value for form DmNhaCungCapExt_edit....");
		
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmNhaCungCap");
		rowIndex = lsDataModel.getRowIndex();
		
		if (rowIndex != -1) {
			this.nhacungcap = ((List<DmNhaCungCap>)lsDataModel.getWrappedData()).get(rowIndex);
		}	
	}

	private void resetValue() {
		this.nhacungcap = new DmNhaCungCap();
		this.nhacungcap.setDmtinhMaso(new DmTinh());
		this.nhacungcap.setDmnctMaso(new DmNguonChuongTrinh());

	}
	
	private void initComboxTinh() {	
		SelectItem item ;
		
		this.listTinh = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listTinh.add(item);
		
		List<DmTinh> lstempTinh = DieuTriUtilDelegate.getInstance().findAll("DmTinh", "dmtinhChon", true);
		
		for (DmTinh dmTinh : lstempTinh) {
			item =new SelectItem(dmTinh.getDmtinhMaso(), dmTinh.getDmtinhTen());
			this.listTinh.add(item);
		}
	
	}
	
	private void initComboxNguonChuongTrinh() {	
		SelectItem item ;
		
		this.listNguonChuongTrinh = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listNguonChuongTrinh.add(item);
		
		List<DmNguonChuongTrinh> lstempNguonChuongtrinh = DieuTriUtilDelegate.getInstance().findAll("DmNguonChuongTrinh", "dmnctDt", true);
		
		for (DmNguonChuongTrinh dmNguonChuongTrinh : lstempNguonChuongtrinh) {
			item =new SelectItem(dmNguonChuongTrinh.getDmnctMaso(), dmNguonChuongTrinh.getDmnctTen());
			this.listNguonChuongTrinh.add(item);
		}
	
	}

	public DmNhaCungCap getNhacungcap() {
		return nhacungcap;
	}

	public void setNhacungcap(DmNhaCungCap nhacungcap) {
		this.nhacungcap = nhacungcap;
	}

	public List<SelectItem> getListTinh() {
		return listTinh;
	}

	public void setListTinh(List<SelectItem> listTinh) {
		this.listTinh = listTinh;
	}

	public List<SelectItem> getListNguonChuongTrinh() {
		return listNguonChuongTrinh;
	}

	public void setListNguonChuongTrinh(List<SelectItem> listNguonChuongTrinh) {
		this.listNguonChuongTrinh = listNguonChuongTrinh;
	}
	
	
	
}
