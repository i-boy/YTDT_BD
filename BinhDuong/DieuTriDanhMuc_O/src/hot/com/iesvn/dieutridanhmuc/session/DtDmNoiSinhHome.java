package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmNoiSinhHome")
public class DtDmNoiSinhHome extends EntityHome<DtDmNoiSinh> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDtDmNoiSinhDtdmnoisinhMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmNoiSinhDtdmnoisinhMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmNoiSinh createInstance() {
		DtDmNoiSinh dtDmNoiSinh = new DtDmNoiSinh();
		return dtDmNoiSinh;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmNoiSinh> temp = new DtDmNoiSinhList("").getResultList();
		for(DtDmNoiSinh each : temp){
			listTemp.add(each.getDtdmnoisinhTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmnoisinhTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmNoiSinh getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmnoisinhTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmnoisinhTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmnoisinhTen("");
					break;
				}
			}
						
		}		
	}

}
