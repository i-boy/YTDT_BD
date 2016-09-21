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
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmKhoaPhong")
@Scope(SESSION)
public class DmKhoaPhongExt implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmKhoa  khoaphong;

	@DataModel
	private List<DmKhoa> listDmKhoa;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmKhoaExt....");
		
		resetValue();
		
		return "/B2_Dieutri/B262_Khoaphong";
	}

	public void search() {
		log.info("Search DmKhoaExt....");
		
		this.listDmKhoa = DieuTriUtilDelegate.getInstance()
									.findByAllConditions("DmKhoa", "dmkhoaMa", "dmkhoaTen", "dmkhoaDt"
											, this.khoaphong.getDmkhoaMa(), this.khoaphong.getDmkhoaTen(), true);
		
		
		if (this.listDmKhoa.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmKhoaExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDmKhoa.get(rowIndex).getDmkhoaMa();
		
			try {						
				DmKhoa be = this.listDmKhoa.get(rowIndex);
				
				be.setDmkhoaDt(false);
				be.setDmkhoaNgaygiocn((double)date.getTime());
				DieuTriUtilDelegate.getInstance().edit(be);
				this.listDmKhoa.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.khoaphong_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.khoaphong_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmKhoaExt....");

		resetValue();
	}
	
	public String goback() {
		log.info("Goback DmKhoaExt....");

		return "/MyMainForm";
	}
	
	private void resetValue() {
		this.khoaphong = new DmKhoa();
		this.listDmKhoa = new ArrayList<DmKhoa>();
	}

	public DmKhoa getKhoaphong() {
		return khoaphong;
	}

	public void setKhoaphong(DmKhoa khoaphong) {
		this.khoaphong = khoaphong;
	}

	public List<DmKhoa> getListDmKhoa() {
		return listDmKhoa;
	}

	public void setListDmKhoa(List<DmKhoa> listDmKhoa) {
		this.listDmKhoa = listDmKhoa;
	}

		
}
