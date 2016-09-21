package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmChuongBenhHome")
public class DmChuongBenhHome extends EntityHome<DmChuongBenh> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmChuongBenhDmchuongbenhMaso(Integer id) {
		setId(id);
	}

	public Integer getDmChuongBenhDmchuongbenhMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmChuongBenh createInstance() {
		DmChuongBenh dmChuongBenh = new DmChuongBenh();
		return dmChuongBenh;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmChuongBenh> temp = new DmChuongBenhList("").getResultList();
		for(DmChuongBenh each : temp){
			listTemp.add(each.getDmchuongbenhTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmchuongbenhTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmChuongBenh getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmBenhIcd> getDmBenhIcds() {
		return getInstance() == null ? null : new ArrayList<DmBenhIcd>(
				getInstance().getDmBenhIcds());
	}

	public List<DmBenhVn> getDmBenhVns() {
		return getInstance() == null ? null : new ArrayList<DmBenhVn>(
				getInstance().getDmBenhVns());
	}

	public List<DmBenhVn> getDmBenhVns_1() {
		return getInstance() == null ? null : new ArrayList<DmBenhVn>(
				getInstance().getDmBenhVns_1());
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmchuongbenhTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmchuongbenhTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmchuongbenhTen("");
					break;
				}
			}						
		}		
	}

}
