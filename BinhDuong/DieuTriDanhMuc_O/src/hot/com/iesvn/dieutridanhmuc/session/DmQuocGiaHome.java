package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmQuocGiaHome")
public class DmQuocGiaHome extends EntityHome<DmQuocGia> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmQuocGiaDmquocgiaMaso(Integer id) {
		setId(id);
	}

	public Integer getDmQuocGiaDmquocgiaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmQuocGia createInstance() {
		DmQuocGia dmQuocGia = new DmQuocGia();
		return dmQuocGia;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmQuocGia> temp = new DmQuocGiaList("").getResultList();
		for(DmQuocGia each : temp){
			listTemp.add(each.getDmquocgiaTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmquocgiaTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmQuocGia getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmThuoc> getDmThuocs() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocs());
	}

	public List<DmThuoc> getDmThuocs_1() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocs_1());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmquocgiaTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmquocgiaTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmquocgiaTen("");
					break;
				}
			}						
		}		
	}

}
