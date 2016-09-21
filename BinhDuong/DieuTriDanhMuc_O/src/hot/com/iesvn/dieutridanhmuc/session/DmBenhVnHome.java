package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmBenhVnHome")
public class DmBenhVnHome extends EntityHome<DmBenhVn> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmChuongBenhHome dmChuongBenhHome;

	public void setDmBenhVnDmbenhvnMaso(Integer id) {
		setId(id);
	}

	public Integer getDmBenhVnDmbenhvnMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmBenhVn createInstance() {
		DmBenhVn dmBenhVn = new DmBenhVn();
		return dmBenhVn;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmBenhVn> temp = new DmBenhVnList("").getResultList();
		for(DmBenhVn each : temp){
			listTemp.add(each.getDmbenhvnTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmbenhvnTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmChuongBenh dmChuongBenh = dmChuongBenhHome.getDefinedInstance();
		if (dmChuongBenh != null) {
			getInstance().setDmChuongBenh(dmChuongBenh);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmBenhVn getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmBenhIcd> getDmBenhIcds() {
		return getInstance() == null ? null : new ArrayList<DmBenhIcd>(
				getInstance().getDmBenhIcds());
	}

	public List<DmBenhTruyenNhiem> getDmBenhTruyenNhiems() {
		return getInstance() == null ? null : new ArrayList<DmBenhTruyenNhiem>(
				getInstance().getDmBenhTruyenNhiems());
	}
	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmbenhvnTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmbenhvnTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmbenhvnTen("");
					break;
				}
			}						
		}		
	}

}
