package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmPlBhytHome")
public class DtDmPlBhytHome extends EntityHome<DtDmPlBhyt> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDtDmPlBhytDtdmphloaibhytMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmPlBhytDtdmphloaibhytMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmPlBhyt createInstance() {
		DtDmPlBhyt dtDmPlBhyt = new DtDmPlBhyt();
		return dtDmPlBhyt;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmPlBhyt> temp = new DtDmPlBhytList("").getResultList();
		for(DtDmPlBhyt each : temp){
			listTemp.add(each.getDtdmphloaibhytTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmphloaibhytTen();
			countChange = 1;
		}		
	}
	
	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmPlBhyt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmKhoiBhyt> getDtDmKhoiBhyts() {
		return getInstance() == null ? null : new ArrayList<DtDmKhoiBhyt>(
				getInstance().getDtDmKhoiBhyts());
	}

	public List<DtDmKhoiBhyt> getDtDmKhoiBhyts_1() {
		return getInstance() == null ? null : new ArrayList<DtDmKhoiBhyt>(
				getInstance().getDtDmKhoiBhyts_1());
	}

	public List<DtDmNhomBhyt> getDtDmNhomBhyts() {
		return getInstance() == null ? null : new ArrayList<DtDmNhomBhyt>(
				getInstance().getDtDmNhomBhyts());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmphloaibhytTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmphloaibhytTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmphloaibhytTen("");
					break;
				}
			}
						
		}		
	}

}
