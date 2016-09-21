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
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNhaCungCap;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmNhaCungCap_add")
@Scope(SESSION)
public class DmNhaCungCapExt_add implements Serializable{

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
		log.info("Init DmNhaCungCapExt_add....");
		
		resetValue();
		initComboxTinh();
		initComboxNguonChuongTrinh();
	
		return "/B4_Duocpham/KhoChinh/B4152_Nhacungcap_add";
	}

	public void save(int tinhId, int nguonCTId ) {
		log.info("Save DmNhaCungCapExt_add....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.nhacungcap.getDmnhacungcapMa();
		this.nhacungcap.setDmnhacungcapNgaygiocn((double)date.getTime());
		this.nhacungcap.setDmnhacungcapDt(true);
		setMaFromTen();
		
		if (tinhId == this.emptyValueCbo) {
			this.nhacungcap.setDmtinhMaso(null);
		}
		
		if (nguonCTId == this.emptyValueCbo) {
			this.nhacungcap.setDmnctMaso(null);
		}
		
		try {
			DieuTriUtilDelegate.getInstance().create(this.nhacungcap);
			FacesMessages.instance().add(IConstantsRes.nhacungcap_cr_su, "");
			reset();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.nhacungcap_cr_fa, "");
		}
	}

	public String goBack() {
		log.info("GoBack DmNhaCungCapExt_add....");
		
		return "/B4_Duocpham/KhoChinh/B4152_Nhacungcap";
	}
	
	private void setMaFromTen() {
		log.info("setMaFromTen DmNhaCungCapExt_add....");
		
		int count = 0;
		String maTemp = this.nhacungcap.getDmnhacungcapMa();
		List<DmNhaCungCap> ls = new ArrayList<DmNhaCungCap>();
		
		ls = DieuTriUtilDelegate.getInstance().findMaLike("DmNhaCungCap", "dmnhacungcapMa", "dmnhacungcapDt"
													, this.nhacungcap.getDmnhacungcapMa(), true);
		
		for (DmNhaCungCap dmNhaCungCap : ls) {
			String temp = dmNhaCungCap.getDmnhacungcapMa().substring(6);
			int counTemp = Integer.parseInt(temp);
			
			if (count < counTemp) {
				count = counTemp;
			}
		}
		
		count++;
		
		if ((count+"").length()==1) {
			
			if (count == 0) {
				maTemp += "00001";
			} else {
				maTemp += "0000" + count;
			}
			
		} else if ((count+"").length()==2) {
			
			maTemp += "000" + count;
			
		} else if ((count+"").length()==3) {
			
			maTemp += "00" + count;
			
		} else if ((count+"").length()==4) {
			
			maTemp += "0" + count;
			
		} else if ((count+"").length()==5) {
			
			maTemp += count;
			
		} else if ((count+"").length()==4) {
			
			maTemp += "99999" + count;
		}
		
		this.nhacungcap.setDmnhacungcapMa(maTemp);
	}
	
	public void reset() {
		log.info("Reset DmNhaCungCapExt_add....");

		resetValue();
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
