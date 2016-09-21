package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmPhuongThucGayTaiNanHome")
public class DmPhuongThucGayTaiNanHome extends
		EntityHome<DmPhuongThucGayTaiNan> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmTaiNanHome dmTaiNanHome;

	public void setDmPhuongThucGayTaiNanDmptgtnMaso(Integer id) {
		setId(id);
	}

	public Integer getDmPhuongThucGayTaiNanDmptgtnMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmPhuongThucGayTaiNan createInstance() {
		DmPhuongThucGayTaiNan dmPhuongThucGayTaiNan = new DmPhuongThucGayTaiNan();
		return dmPhuongThucGayTaiNan;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmPhuongThucGayTaiNan> temp = new DmPhuongThucGayTaiNanList("").getResultList();
		for(DmPhuongThucGayTaiNan each : temp){
			listTemp.add(each.getDmptgtnTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmptgtnTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmTaiNan dmTaiNan = dmTaiNanHome.getDefinedInstance();
		if (dmTaiNan != null) {
			getInstance().setDmTaiNan(dmTaiNan);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmPhuongThucGayTaiNan getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmptgtnTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmptgtnTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmptgtnTen("");
					break;
				}
			}						
		}		
	}

}
