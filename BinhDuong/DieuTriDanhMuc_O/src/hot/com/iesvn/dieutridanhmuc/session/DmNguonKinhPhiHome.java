package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmNguonKinhPhiHome")
public class DmNguonKinhPhiHome extends EntityHome<DmNguonKinhPhi> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmNguonKinhPhiDmnguonkinhphiMaso(Integer id) {
		setId(id);
	}

	public Integer getDmNguonKinhPhiDmnguonkinhphiMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmNguonKinhPhi createInstance() {
		DmNguonKinhPhi dmNguonKinhPhi = new DmNguonKinhPhi();
		return dmNguonKinhPhi;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmNguonKinhPhi> temp = new DmNguonKinhPhiList("").getResultList();
		for(DmNguonKinhPhi each : temp){
			listTemp.add(each.getDmnguonkinhphiTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmnguonkinhphiTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmNguonKinhPhi getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmnguonkinhphiTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmnguonkinhphiTen("");
					break;
}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmnguonkinhphiTen("");
					break;
				}
			}						
		}		
	}
}
