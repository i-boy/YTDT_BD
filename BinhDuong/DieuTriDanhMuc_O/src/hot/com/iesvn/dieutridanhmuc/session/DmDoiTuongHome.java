package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmDoiTuongHome")
public class DmDoiTuongHome extends EntityHome<DmDoiTuong> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmDoiTuongDmdoituongMaso(Integer id) {
		setId(id);
	}

	public Integer getDmDoiTuongDmdoituongMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmDoiTuong createInstance() {
		DmDoiTuong dmDoiTuong = new DmDoiTuong();
		return dmDoiTuong;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmDoiTuong> temp = new DmDoiTuongList("").getResultList();
		for(DmDoiTuong each : temp){
			listTemp.add(each.getDmdoituongTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmdoituongTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmDoiTuong getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmdoituongTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmdoituongTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmdoituongTen("");
					break;
				}
			}						
		}		
	}

}
