package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmPlPhauThuatHome")
public class DtDmPlPhauThuatHome extends EntityHome<DtDmPlPhauThuat> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDtDmPlPhauThuatDtdmplptMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmPlPhauThuatDtdmplptMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmPlPhauThuat createInstance() {
		DtDmPlPhauThuat dtDmPlPhauThuat = new DtDmPlPhauThuat();
		return dtDmPlPhauThuat;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmPlPhauThuat> temp = new DtDmPlPhauThuatList("").getResultList();
		for(DtDmPlPhauThuat each : temp){
			listTemp.add(each.getDtdmplptTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmplptTen();
			countChange = 1;
		}		
	}
	
	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmPlPhauThuat getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmPhauThuat> getDtDmPhauThuats() {
		return getInstance() == null ? null : new ArrayList<DtDmPhauThuat>(
				getInstance().getDtDmPhauThuats());
	}

	public List<DtDmPhauThuat> getDtDmPhauThuats_1() {
		return getInstance() == null ? null : new ArrayList<DtDmPhauThuat>(
				getInstance().getDtDmPhauThuats_1());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmplptTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmplptTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmplptTen("");
					break;
				}
			}
						
		}		
	}

}
