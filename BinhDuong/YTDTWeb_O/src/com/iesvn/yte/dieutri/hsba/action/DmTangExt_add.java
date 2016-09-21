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
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmTang_add")
@Scope(SESSION)
public class DmTangExt_add implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int emptyValueCbo = -1;
	private static final String emptyLabelCbo = "...";

	@In(required = false)
	@Out(required = false)
	private DmTang dmtang;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;

	private List<SelectItem> listDmKhoa;
	
	
	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmTangExt_add....");
		
		resetValue();
		initComboxKhoa();
		
		
		return "/B2_Dieutri/B282_DmTang_add";
	}

	public void save(int khoaId) {
		log.info("Save  DmTangExt_add....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.dmtang.getDmtangTen();
		this.dmtang.setDmtangNgaygiocn(date);
		this.dmtang.setDmtangChon(true);
		DieuTriUtilDelegate dtDel = DieuTriUtilDelegate.getInstance();
		DmKhoa dmKhoa = (DmKhoa)dtDel.findByMaSo(khoaId, "DmKhoa", "dmkhoaMaso");
		this.dmtang.setDmkhoaMaso(dmKhoa);
		if(this.dmtang.getDmtangDefault().booleanValue() == true){
			//kiem tra xem, neu da co 1 tang thuoc cung khoa da set default = true roi thi không cho set true o day nua
			List<DmTang> listDmTang = DieuTriUtilDelegate.getInstance().findByAllConditions("DmTang", "dmtangMa", "dmtangTen", "dmtangChon", "", "", true);
			for(DmTang t:listDmTang){
				if(t.getDmtangDefault().booleanValue() == true && t.getDmkhoaMaso().getDmkhoaMaso().intValue() == khoaId){
					FacesMessages.instance().add(IConstantsRes.tang_up_duplicate, t.getDmtangTen(), this.dmtang.getDmkhoaMaso().getDmkhoaTen(), this.dmtang.getDmtangTen());
					return;
				}
			}
		}
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
		if (nv != null) {
			this.dmtang.setDmtangNhanviencn(nv);			
		}
		
		try {
			DieuTriUtilDelegate.getInstance().create(this.dmtang);
			FacesMessages.instance().add(IConstantsRes.tang_cr_su, ma);
			reset();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.tang_cr_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack  DmTangExt_add....");
		
		return "/B2_Dieutri/B282_DmTang";
	}
	
	public void reset() {
		log.info("Reset DmTangExt_add....");
		resetValue();
	}

	private void resetValue() {
		this.dmtang = new DmTang();
		this.dmtang.setDmkhoaMaso(new DmKhoa());		
		
	}
	
	private void initComboxKhoa() {	
		SelectItem item ;
		
		this.listDmKhoa = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listDmKhoa.add(item);
		DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();		
		List<DmKhoa> lstempKhoa = dmKhoaDel.getKhoaNT();
		
		for (DmKhoa dmKhoa : lstempKhoa) {
			item = new SelectItem(dmKhoa.getDmkhoaMaso(),dmKhoa.getDmkhoaTen());
			this.listDmKhoa.add(item);
		}
	
	}

	public DmTang getDmtang() {
		return dmtang;
	}

	public void setDmtang(DmTang dmtang) {
		this.dmtang = dmtang;
	}

	public List<SelectItem> getListDmKhoa() {
		return listDmKhoa;
	}

	public void setListDmKhoa(List<SelectItem> listDmKhoa) {
		this.listDmKhoa = listDmKhoa;
	}
	
	
}
