package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmNguonChuongTrinhHome")
public class DmNguonChuongTrinhHome extends EntityHome<DmNguonChuongTrinh> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmLoaiNguonChuongTrinhHome dmLoaiNguonChuongTrinhHome;
	
	public void setDmNguonChuongTrinhDmnctMaso(Integer id) {
		setId(id);
	}

	public Integer getDmNguonChuongTrinhDmnctMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmNguonChuongTrinh createInstance() {
		DmNguonChuongTrinh dmNguonChuongTrinh = new DmNguonChuongTrinh();
		return dmNguonChuongTrinh;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmNguonChuongTrinh> temp = new DmNguonChuongTrinhList("").getResultList();
		for(DmNguonChuongTrinh each : temp){
			listTemp.add(each.getDmnctTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmnctTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh = dmLoaiNguonChuongTrinhHome.getDefinedInstance();
		if (dmLoaiNguonChuongTrinh != null) {
			getInstance().setDmLoaiNguonChuongTrinh(dmLoaiNguonChuongTrinh);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmNguonChuongTrinh getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmNhaCungCap> getDmNhaCungCaps() {
		return getInstance() == null ? null : new ArrayList<DmNhaCungCap>(
				getInstance().getDmNhaCungCaps());
	}

	public List<DmNhaCungCap> getDmNhaCungCaps_1() {
		return getInstance() == null ? null : new ArrayList<DmNhaCungCap>(
				getInstance().getDmNhaCungCaps_1());
	}

	public List<DtDmKhach> getDtDmKhachs() {
		return getInstance() == null ? null : new ArrayList<DtDmKhach>(
				getInstance().getDtDmKhachs());
	}

	public List<DtDmKhach> getDtDmKhachs_1() {
		return getInstance() == null ? null : new ArrayList<DtDmKhach>(
				getInstance().getDtDmKhachs_1());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmnctTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmnctTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmnctTen("");
					break;
				}
			}						
		}		
	}

}
