package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmCumHome")
public class DtDmCumHome extends EntityHome<DtDmCum> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDtDmCumDtdmcumMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmCumDtdmcumMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmCum createInstance() {
		DtDmCum dtDmCum = new DtDmCum();
		return dtDmCum;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmCum> temp = new DtDmCumList("").getResultList();
		for(DtDmCum each : temp){
			listTemp.add(each.getDtdmcumTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmcumTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmCum getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmcumTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmcumTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmcumTen("");
					break;
				}
			}						
		}		
	}

}
