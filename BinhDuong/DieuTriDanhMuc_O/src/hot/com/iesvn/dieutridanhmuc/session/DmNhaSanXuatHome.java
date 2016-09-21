package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmNhaSanXuatHome")
public class DmNhaSanXuatHome extends EntityHome<DmNhaSanXuat> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmNhaSanXuatDmnhasanxuatMaso(Integer id) {
		setId(id);
	}

	public Integer getDmNhaSanXuatDmnhasanxuatMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmNhaSanXuat createInstance() {
		DmNhaSanXuat dmNhaSanXuat = new DmNhaSanXuat();
		return dmNhaSanXuat;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmNhaSanXuat> temp = new DmNhaSanXuatList("").getResultList();
		for(DmNhaSanXuat each : temp){
			listTemp.add(each.getDmnhasanxuatTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmnhasanxuatTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmNhaSanXuat getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmThuoc> getDmThuocs() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocs());
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmnhasanxuatTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmnhasanxuatTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmnhasanxuatTen("");
					break;
				}
			}						
		}		
	}
}
