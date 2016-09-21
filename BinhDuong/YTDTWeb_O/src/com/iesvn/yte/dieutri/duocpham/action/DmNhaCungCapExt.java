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
import com.iesvn.yte.entity.DmNhaCungCap;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmNhaCungCap")
@Scope(SESSION)
public class DmNhaCungCapExt implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmNhaCungCap nhacungcap;

	@DataModel
	private List<DmNhaCungCap> listDmNhaCungCap;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmNhaCungCapExt....");
		
		resetValue();
		
		return "/B4_Duocpham/KhoChinh/B4152_Nhacungcap";
	}

	public void search() {
		log.info("Search DmNhaCungCapExt....");
		
		this.listDmNhaCungCap = DieuTriUtilDelegate.getInstance()
									.findByAllConditions("DmNhaCungCap", "dmnhacungcapMa", "dmnhacungcapTen", "dmnhacungcapDt"
											, this.nhacungcap.getDmnhacungcapMa(), this.nhacungcap.getDmnhacungcapTen(), true);
		
		
		if (this.listDmNhaCungCap.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmNhaCungCapExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDmNhaCungCap.get(rowIndex).getDmnhacungcapMa();
						
			try {						
				DmNhaCungCap be = this.listDmNhaCungCap.get(rowIndex);
				
				be.setDmnhacungcapDt(false);
				be.setDmnhacungcapNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDmNhaCungCap.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.nhacungcap_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.nhacungcap_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmNhaCungCapExt....");

		resetValue();
	}

	public String goback() {
		log.info("Goback DmNhaCungCapExt....");

		return "/MyMainForm";
	}
	
	private void resetValue() {
		this.nhacungcap = new DmNhaCungCap();
		this.listDmNhaCungCap = new ArrayList<DmNhaCungCap>();
	}

	public DmNhaCungCap getNhacungcap() {
		return nhacungcap;
	}

	public void setNhacungcap(DmNhaCungCap nhacungcap) {
		this.nhacungcap = nhacungcap;
	}

	public List<DmNhaCungCap> getListDmNhaCungCap() {
		return listDmNhaCungCap;
	}

	public void setListDmNhaCungCap(List<DmNhaCungCap> listDmNhaCungCap) {
		this.listDmNhaCungCap = listDmNhaCungCap;
	}

	
}
