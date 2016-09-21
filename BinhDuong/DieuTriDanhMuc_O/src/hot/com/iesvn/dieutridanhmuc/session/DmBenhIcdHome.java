package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmBenhIcdHome")
public class DmBenhIcdHome extends EntityHome<DmBenhIcd> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmBenhVnHome dmBenhVnHome;
	@In(create = true)
	DmChuongBenhHome dmChuongBenhHome;

	public void setDmBenhIcdDmbenhicdMaso(Integer id) {
		setId(id);
	}

	public Integer getDmBenhIcdDmbenhicdMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmBenhIcd createInstance() {
		DmBenhIcd dmBenhIcd = new DmBenhIcd();
		return dmBenhIcd;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmBenhIcd> temp = new DmBenhIcdList("").getResultList();
		for(DmBenhIcd each : temp){
			listTemp.add(each.getDmbenhicdTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmbenhicdTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmBenhVn dmBenhVn = dmBenhVnHome.getDefinedInstance();
		if (dmBenhVn != null) {
			getInstance().setDmBenhVn(dmBenhVn);
		}
		DmChuongBenh dmChuongBenh = dmChuongBenhHome.getDefinedInstance();
		if (dmChuongBenh != null) {
			getInstance().setDmChuongBenh(dmChuongBenh);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmBenhIcd getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmbenhicdTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmbenhicdTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmbenhicdTen("");
					break;
				}
			}						
		}		
	}

}
