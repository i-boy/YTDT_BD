package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmLoaiSinhHome")
public class DmLoaiSinhHome extends EntityHome<DmLoaiSinh> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmLoaiSinhDmloaisinhMaso(Integer id) {
		setId(id);
	}

	public Integer getDmLoaiSinhDmloaisinhMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmLoaiSinh createInstance() {
		DmLoaiSinh dmLoaiSinh = new DmLoaiSinh();
		return dmLoaiSinh;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmLoaiSinh> temp = new DmLoaiSinhList("").getResultList();
		for(DmLoaiSinh each : temp){
			listTemp.add(each.getDmloaisinhTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmloaisinhTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmLoaiSinh getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmloaisinhTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmloaisinhTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmloaisinhTen("");
					break;
				}
			}						
		}		
	}

}
