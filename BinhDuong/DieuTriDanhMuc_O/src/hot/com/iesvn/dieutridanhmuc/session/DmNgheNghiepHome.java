package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmNgheNghiepHome")
public class DmNgheNghiepHome extends EntityHome<DmNgheNghiep> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmNgheNghiepBaoCaoHome dmNgheNghiepBaoCaoHome;

	public void setDmNgheNghiepDmnghenghiepMaso(Integer id) {
		setId(id);
	}

	public Integer getDmNgheNghiepDmnghenghiepMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmNgheNghiep createInstance() {
		DmNgheNghiep dmNgheNghiep = new DmNgheNghiep();
		return dmNgheNghiep;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmNgheNghiep> temp = new DmNgheNghiepList("").getResultList();
		for(DmNgheNghiep each : temp){
			listTemp.add(each.getDmnghenghiepTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmnghenghiepTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmNgheNghiepBaoCao dmNgheNghiepBaoCao = dmNgheNghiepBaoCaoHome
				.getDefinedInstance();
		if (dmNgheNghiepBaoCao != null) {
			getInstance().setDmNgheNghiepBaoCao(dmNgheNghiepBaoCao);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmNgheNghiep getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmnghenghiepTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmnghenghiepTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmnghenghiepTen("");
					break;
				}
			}						
		}		
	}

}
