package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmVoCamHome")
public class DtDmVoCamHome extends EntityHome<DtDmVoCam> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDtDmVoCamDtdmvocamMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmVoCamDtdmvocamMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmVoCam createInstance() {
		DtDmVoCam dtDmVoCam = new DtDmVoCam();
		return dtDmVoCam;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmVoCam> temp = new DtDmVoCamList("").getResultList();
		for(DtDmVoCam each : temp){
			listTemp.add(each.getDtdmvocamDiengiai());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmvocamDiengiai();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmVoCam getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmvocamDiengiai();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmvocamDiengiai("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmvocamDiengiai("");
					break;
				}
			}
						
		}		
	}
}
