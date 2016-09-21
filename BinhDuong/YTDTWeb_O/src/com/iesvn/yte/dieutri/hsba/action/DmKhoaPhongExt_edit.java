package com.iesvn.yte.dieutri.hsba.action;

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
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiKhoa;
import com.iesvn.yte.entity.DmNhomKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmKhoaPhong_edit")
@Scope(SESSION)
public class DmKhoaPhongExt_edit implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int emptyValueCbo = -1;
	private static final String emptyLabelCbo = "...";

	@In(required = false)
	@Out(required = false)
	private DmKhoa khoaphong;
	
	private List<SelectItem> listLoaiKhoa;
	private List<SelectItem> listNhomKhoa;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmKhoaPhongExt_edit....");
		
		resetValue();
		initComboxLoaiKhoa();
		initComboxNhomKhoa();
		setValueForForm();
	
		return "/B2_Dieutri/B262_Khoaphong_edit";
	}

	public void save(int loaiKhoaId, int nhomKhoaId) {
		log.info("Save DmKhoaPhongExt_edit....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.khoaphong.getDmkhoaMa();
		this.khoaphong.setDmkhoaNgaygiocn((double)date.getTime());
		
		if (loaiKhoaId == this.emptyValueCbo) {
			this.khoaphong.setDmloaikhoaMa(null);
		}
		
		if (nhomKhoaId == this.emptyValueCbo) {
			this.khoaphong.setDmnhomkhoaMaso(null);
		}
		
		try {
			DieuTriUtilDelegate.getInstance().edit(this.khoaphong);
			FacesMessages.instance().add(IConstantsRes.khoaphong_up_su, ma);
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.khoaphong_up_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmKhoaPhongExt_edit....");
		
		return "/B2_Dieutri/B262_Khoaphong";
	}
	
	private void setValueForForm() {
		log.info("Set value for form DmKhoaPhongExt_edit....");
		
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmKhoa");
		rowIndex = lsDataModel.getRowIndex();
		
		if (rowIndex != -1) {
			this.khoaphong = ((List<DmKhoa>)lsDataModel.getWrappedData()).get(rowIndex);
				
		}	
	}

	private void resetValue() {
		this.khoaphong = new DmKhoa();
		this.khoaphong.setDmloaikhoaMa(new DmLoaiKhoa());
		this.khoaphong.setDmnhomkhoaMaso(new DmNhomKhoa());
	}
	
	private void initComboxLoaiKhoa() {	
		SelectItem item ;
		
		this.listLoaiKhoa = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listLoaiKhoa.add(item);
		
		List<DmLoaiKhoa> lstempLoaiKhoa = DieuTriUtilDelegate.getInstance().findAll("DmLoaiKhoa", "dmloaikhoaDt", true);
		
		for (DmLoaiKhoa dmLoaiKhoa : lstempLoaiKhoa) {
			item = new SelectItem(dmLoaiKhoa.getDmloaikhoaMaso(),dmLoaiKhoa.getDmloaikhoaTen());
			this.listLoaiKhoa.add(item);
		}
		
	}
	
	private void initComboxNhomKhoa() {	
		SelectItem item ;
		
		this.listNhomKhoa = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listNhomKhoa.add(item);
		
		List<DmNhomKhoa> lstempNhomKhoa = DieuTriUtilDelegate.getInstance().findAll("DmNhomKhoa", "dmnhomkhoaDt", true);
		
		for (DmNhomKhoa dmNhomKhoa : lstempNhomKhoa) {
			item = new SelectItem(dmNhomKhoa.getDmnhomkhoaMaso(),dmNhomKhoa.getDmnhomkhoaTen());
			this.listNhomKhoa.add(item);
		}
	
	}

	public DmKhoa getKhoaphong() {
		return khoaphong;
	}

	public void setKhoaphong(DmKhoa khoaphong) {
		this.khoaphong = khoaphong;
	}

	public List<SelectItem> getListLoaiKhoa() {
		return listLoaiKhoa;
	}

	public void setListLoaiKhoa(List<SelectItem> listLoaiKhoa) {
		this.listLoaiKhoa = listLoaiKhoa;
	}

	public List<SelectItem> getListNhomKhoa() {
		return listNhomKhoa;
	}

	public void setListNhomKhoa(List<SelectItem> listNhomKhoa) {
		this.listNhomKhoa = listNhomKhoa;
	}
}
