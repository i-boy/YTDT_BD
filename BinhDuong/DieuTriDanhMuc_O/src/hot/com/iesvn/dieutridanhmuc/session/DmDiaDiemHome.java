package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmDiaDiemHome")
public class DmDiaDiemHome extends EntityHome<DmDiaDiem> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmDiaDiemDmdiadiemMaso(Integer id) {
		setId(id);
	}

	public Integer getDmDiaDiemDmdiadiemMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmDiaDiem createInstance() {
		DmDiaDiem dmDiaDiem = new DmDiaDiem();
		return dmDiaDiem;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmDiaDiem> temp = new DmDiaDiemList("").getResultList();
		for(DmDiaDiem each : temp){
			listTemp.add(each.getDmdiadiemTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmdiadiemTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmDiaDiem getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmdiadiemTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmdiadiemTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmdiadiemTen("");
					break;
				}
			}						
		}		
	}

}
