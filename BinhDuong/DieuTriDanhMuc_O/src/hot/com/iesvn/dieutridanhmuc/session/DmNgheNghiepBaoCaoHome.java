package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmNgheNghiepBaoCaoHome")
public class DmNgheNghiepBaoCaoHome extends EntityHome<DmNgheNghiepBaoCao> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmNgheNghiepBaoCaoDmnghenghiepbcMaso(Integer id) {
		setId(id);
	}

	public Integer getDmNgheNghiepBaoCaoDmnghenghiepbcMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmNgheNghiepBaoCao createInstance() {
		DmNgheNghiepBaoCao dmNgheNghiepBaoCao = new DmNgheNghiepBaoCao();
		return dmNgheNghiepBaoCao;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmNgheNghiepBaoCao> temp = new DmNgheNghiepBaoCaoList("").getResultList();
		for(DmNgheNghiepBaoCao each : temp){
			listTemp.add(each.getDmnghenghiepbcTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmnghenghiepbcTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmNgheNghiepBaoCao getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmNgheNghiep> getDmNgheNghieps() {
		return getInstance() == null ? null : new ArrayList<DmNgheNghiep>(
				getInstance().getDmNgheNghieps());
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmnghenghiepbcTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmnghenghiepbcTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmnghenghiepbcTen("");
					break;
				}
			}						
		}		
	}

}
