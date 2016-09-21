package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmPbClsHome")
public class DtDmPbClsHome extends EntityHome<DtDmPbCls> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDtDmPbClsDtdmpbclsMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmPbClsDtdmpbclsMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmPbCls createInstance() {
		DtDmPbCls dtDmPbCls = new DtDmPbCls();
		return dtDmPbCls;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmPbCls> temp = new DtDmPbClsList("").getResultList();
		for(DtDmPbCls each : temp){
			listTemp.add(each.getDtdmpbclsTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmpbclsTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmPbCls getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmCls> getDtDmClses() {
		return getInstance() == null ? null : new ArrayList<DtDmCls>(
				getInstance().getDtDmClses());
	}

	public List<DtDmCls> getDtDmClses_1() {
		return getInstance() == null ? null : new ArrayList<DtDmCls>(
				getInstance().getDtDmClses_1());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmpbclsTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmpbclsTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmpbclsTen("");
					break;
				}
			}
						
		}		
	}

}
