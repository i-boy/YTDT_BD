package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmPhanLoaiTaiNanHome")
public class DmPhanLoaiTaiNanHome extends EntityHome<DmPhanLoaiTaiNan> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmTaiNanHome dmTaiNanHome;

	public void setDmPhanLoaiTaiNanDmpltainanMaso(Integer id) {
		setId(id);
	}

	public Integer getDmPhanLoaiTaiNanDmpltainanMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmPhanLoaiTaiNan createInstance() {
		DmPhanLoaiTaiNan dmPhanLoaiTaiNan = new DmPhanLoaiTaiNan();
		return dmPhanLoaiTaiNan;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmPhanLoaiTaiNan> temp = new DmPhanLoaiTaiNanList("").getResultList();
		for(DmPhanLoaiTaiNan each : temp){
			listTemp.add(each.getDmpltainanTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmpltainanTen();
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

	public DmPhanLoaiTaiNan getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmpltainanTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmpltainanTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmpltainanTen("");
					break;
				}
			}						
		}		
	}

}
