package com.iesvn.yte.dieutri.duocpham.action;

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
import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmBaiThuoc")
@Scope(SESSION)
public class DmBaiThuocExt implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmBaiThuoc dmBaiThuoc;

	@DataModel
	private List<DmBaiThuoc> listDmBaiThuoc;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmBaiThuocExt....");
		
		resetValue();
		
		return "/B4_Duocpham/KhoChinh/B4155_Baithuocdongy";
	}

	public void search() {
		log.info("Search DmBaiThuocExt....");
		
		this.listDmBaiThuoc = DieuTriUtilDelegate.getInstance()
									.findByAllConditions("DmBaiThuoc", "dmbaithuocMa", "dmbaithuocTen", "dmbaithuocDt"
											, this.dmBaiThuoc.getDmbaithuocMa(), this.dmBaiThuoc.getDmbaithuocTen(), true);
		
		
		if (this.listDmBaiThuoc.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmBaiThuocExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDmBaiThuoc.get(rowIndex).getDmbaithuocMa();
		
			try {						
				DmBaiThuoc be = this.listDmBaiThuoc.get(rowIndex);
				
				be.setDmbaithuocDt(false);
				be.setDmbaithuocNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDmBaiThuoc.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.baithuoc_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.baithuoc_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmBaiThuocExt....");

		resetValue();
	}
	
	public String goback() {
		log.info("Goback DmBaiThuocExt....");

		return "/MyMainForm";
	}

	private void resetValue() {
		this.dmBaiThuoc = new DmBaiThuoc();
		this.listDmBaiThuoc = new ArrayList<DmBaiThuoc>();
	}

	public DmBaiThuoc getDmBaiThuoc() {
		return dmBaiThuoc;
	}

	public void setDmBaiThuoc(DmBaiThuoc dmBaiThuoc) {
		this.dmBaiThuoc = dmBaiThuoc;
	}

	public List<DmBaiThuoc> getListDmBaiThuoc() {
		return listDmBaiThuoc;
	}

	public void setListDmBaiThuoc(List<DmBaiThuoc> listDmBaiThuoc) {
		this.listDmBaiThuoc = listDmBaiThuoc;
	}	
}
