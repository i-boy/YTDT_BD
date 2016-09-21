package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

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
import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsKhoaDelegate;
import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmClsKetQua;
import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;


@Name("DmCanlamsanKetqua_add")
@Scope(SESSION)
public class DmCanlamsanKetquaExt_add {
	
	@In(required = false)
	@Out(required = false)
	private DtDmClsKetQua dtDmClsKetQua;
	
	private Boolean isUpdate = false;
	
	@Logger
	private Log log;

	private List<SelectItem> listKhoaCLS;
	
	private List<SelectItem> listClsBanggia;
	
	private Integer dmkhoaMaso;
	
	private Integer dtdmclsbgMaso;
	
	@Create
	public String init() {
		log.info("Init DmCanlamsanKetqua_add....");
		
		resetValue();
		
		return "/B3_Vienphi/ThuVienPhi/B3254_Canlamsanketqua_add";
	}
	
	public void resetValue() {
		this.dtDmClsKetQua = new DtDmClsKetQua();	
		this.listKhoaCLS = new ArrayList<SelectItem>();
		this.listClsBanggia = new ArrayList<SelectItem>();
		SelectItem itemTemp = new SelectItem(null,"---");
		listKhoaCLS.add(itemTemp);
		List<DmKhoa> listKhoaCLSTemp = DieuTriUtilDelegate.getInstance().findAll("DmKhoa", "dmkhoaDt", true);
		for (DmKhoa dmKhoa : listKhoaCLSTemp) {
			if(dmKhoa.getDmkhoaCls() != null && dmKhoa.getDmkhoaCls() == true){
				SelectItem item = new SelectItem(dmKhoa.getDmkhoaMaso(),dmKhoa.getDmkhoaTen());
				listKhoaCLS.add(item);
			}
		}
	}
	
	public void displayCLS(){
		log.info("Init displayCLS....");
		listClsBanggia = new ArrayList<SelectItem>();
		DtDmClsBangGiaDelegate dtDmClsBangGiaDelegate = DtDmClsBangGiaDelegate.getInstance();		
		List<DtDmClsBangGia> temp = (ArrayList<DtDmClsBangGia>) dtDmClsBangGiaDelegate.getDtDmClsBangGiaByMaSoKhoa((Integer)dmkhoaMaso);
		SelectItem itemTemp = new SelectItem(null,"---");
		listClsBanggia.add(itemTemp);		
		if(temp != null){
			for (DtDmClsBangGia dtDmClsBangGia : temp) {	
				if(dtDmClsBangGia.getDtdmclsbgXn() != null && dtDmClsBangGia.getDtdmclsbgXn() == true){
					SelectItem item = new SelectItem(dtDmClsBangGia.getDtdmclsbgMaso(),dtDmClsBangGia.getDtdmclsbgDiengiai());
					listClsBanggia.add(item);			
				}
			}
		}
	}
	
	
	public String update() {
		log.info("Init DmCanlamsanKetqua_add_update....");
		resetValue();
		
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDtDmClsKetQua");
		rowIndex = lsDataModel.getRowIndex();
		
		if (rowIndex != -1) {
			this.dtDmClsKetQua = ((List<DtDmClsKetQua>)lsDataModel.getWrappedData()).get(rowIndex);	
			if(this.dtDmClsKetQua.getDtdmclsbgMaso() != null){
				this.dtdmclsbgMaso = this.dtDmClsKetQua.getDtdmclsbgMaso().getDtdmclsbgMaso();
				this.dmkhoaMaso = DtDmClsKhoaDelegate.getInstance().findByMaSoCLS(this.dtdmclsbgMaso).get(0).getDmkhoaMaso().getDmkhoaMaso();
				displayCLS();
			}
		}
		
		return "/B3_Vienphi/ThuVienPhi/B3254_Canlamsanketqua_add";
	}

	public void save() {
		log.info("Save DmCanlamsanKetqua_add....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.dtDmClsKetQua.getDtdmclskqMa();
		this.dtDmClsKetQua.setDtdmclskqNgaygiocn((double)date.getTime());
		DtDmClsBangGiaDelegate dtDmClsBangGiaDelegate = DtDmClsBangGiaDelegate.getInstance();
		this.dtDmClsKetQua.setDtdmclsbgMaso(dtDmClsBangGiaDelegate.find(dtdmclsbgMaso));
		try {
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			if(this.dtDmClsKetQua.getDtdtclskqMaso() == null){
				dtUtil.create(this.dtDmClsKetQua);
			}else{				
				dtUtil.edit(this.dtDmClsKetQua);
			}			
			FacesMessages.instance().add(IConstantsRes.dtdmclskq_cr_su, this.dtDmClsKetQua.getDtdmclskqMa());
			resetValue();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.dtdmclskq_cr_fa, this.dtDmClsKetQua.getDtdmclskqMa());
		}
		
	}

	public String goBack() {
		log.info("GoBack DmCanlamsanKetqua_add....");
		
		return "/B3_Vienphi/ThuVienPhi/B3254_Canlamsanketqua";
	}

	public DtDmClsKetQua getDtDmClsKetQua() {
		return dtDmClsKetQua;
	}

	public void setDtDmClsKetQua(DtDmClsKetQua dtDmClsKetQua) {
		this.dtDmClsKetQua = dtDmClsKetQua;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public List<SelectItem> getListKhoaCLS() {
		return listKhoaCLS;
	}

	public void setListKhoaCLS(List<SelectItem> listKhoaCLS) {
		this.listKhoaCLS = listKhoaCLS;
	}

	public List<SelectItem> getListClsBanggia() {
		return listClsBanggia;
	}

	public void setListClsBanggia(List<SelectItem> listClsBanggia) {
		this.listClsBanggia = listClsBanggia;
	}

	public Integer getDmkhoaMaso() {
		return dmkhoaMaso;
	}

	public void setDmkhoaMaso(Integer dmkhoaMaso) {
		this.dmkhoaMaso = dmkhoaMaso;
	}

	public Integer getDtdmclsbgMaso() {
		return dtdmclsbgMaso;
	}

	public void setDtdmclsbgMaso(Integer dtdmclsbgMaso) {
		this.dtdmclsbgMaso = dtdmclsbgMaso;
	}
	
	

}
