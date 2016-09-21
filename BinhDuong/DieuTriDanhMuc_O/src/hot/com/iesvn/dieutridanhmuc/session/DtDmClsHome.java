package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmClsHome")
public class DtDmClsHome extends EntityHome<DtDmCls> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DtDmPbClsHome dtDmPbClsHome;

	public void setDtDmClsDtdmclsMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmClsDtdmclsMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmCls createInstance() {
		DtDmCls dtDmCls = new DtDmCls();
		return dtDmCls;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmCls> temp = new DtDmClsList("").getResultList();
		for(DtDmCls each : temp){
			listTemp.add(each.getDtdmclsTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmclsTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DtDmPbCls dtDmPbCls = dtDmPbClsHome.getDefinedInstance();
		if (dtDmPbCls != null) {
			getInstance().setDtDmPbCls(dtDmPbCls);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmCls getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmClsBangGia> getDtDmClsBangGiasForDtdmclsbgMaloai() {
		return getInstance() == null ? null : new ArrayList<DtDmClsBangGia>(
				getInstance().getDtDmClsBangGiasForDtdmclsbgMaloai());
	}

	public List<DtDmClsBangGia> getDtDmClsBangGiasForDtdmclsbgPhanloai() {
		return getInstance() == null ? null : new ArrayList<DtDmClsBangGia>(
				getInstance().getDtDmClsBangGiasForDtdmclsbgPhanloai());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmclsTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmclsTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmclsTen("");
					break;
				}
			}						
		}		
	}

}
