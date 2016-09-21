package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmKhoaBytHome")
public class DmKhoaBytHome extends EntityHome<DmKhoaByt> {

	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;
	
	public void setDmKhoaBytDmkhoabytMaso(Integer id) {
		setId(id);
	}

	public Integer getDmKhoaBytDmkhoabytMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmKhoaByt createInstance() {
		DmKhoaByt dmKhoaByt = new DmKhoaByt();
		return dmKhoaByt;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmKhoaByt> temp = new DmKhoaBytList("").getResultList();
		for(DmKhoaByt each : temp){
			listTemp.add(each.getDmkhoabytTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmkhoabytTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmKhoaByt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmKhoa> getDmKhoasForDmkhoaMabyt() {
		return getInstance() == null ? null : new ArrayList<DmKhoa>(
				getInstance().getDmKhoasForDmkhoaMabyt());
	}

	public List<DmKhoa> getDmKhoasForDmkhoaMabyt3() {
		return getInstance() == null ? null : new ArrayList<DmKhoa>(
				getInstance().getDmKhoasForDmkhoaMabyt3());
	}
	
	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmkhoabytTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmkhoabytTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmkhoabytTen("");
					break;
				}
			}						
		}		
	}

}
