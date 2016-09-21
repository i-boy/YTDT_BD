package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmPhauThuatHome")
public class DtDmPhauThuatHome extends EntityHome<DtDmPhauThuat> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DtDmLoaiPhauThuatHome dtDmLoaiPhauThuatHome;
	@In(create = true)
	DtDmPlPhauThuatHome dtDmPlPhauThuatHome;

	public void setDtDmPhauThuatDtdmphauthuatMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmPhauThuatDtdmphauthuatMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmPhauThuat createInstance() {
		DtDmPhauThuat dtDmPhauThuat = new DtDmPhauThuat();
		return dtDmPhauThuat;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmPhauThuat> temp = new DtDmPhauThuatList("").getResultList();
		for(DtDmPhauThuat each : temp){
			listTemp.add(each.getDtdmphauthuatTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmphauthuatTen();
			countChange = 1;
		}		
	}
	
	public void wire() {
		getInstance();
		DtDmLoaiPhauThuat dtDmLoaiPhauThuat = dtDmLoaiPhauThuatHome
				.getDefinedInstance();
		if (dtDmLoaiPhauThuat != null) {
			getInstance().setDtDmLoaiPhauThuat(dtDmLoaiPhauThuat);
		}
		DtDmPlPhauThuat dtDmPlPhauThuat = dtDmPlPhauThuatHome
				.getDefinedInstance();
		if (dtDmPlPhauThuat != null) {
			getInstance().setDtDmPlPhauThuat(dtDmPlPhauThuat);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmPhauThuat getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmphauthuatTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmphauthuatTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmphauthuatTen("");
					break;
				}
			}
						
		}		
	}

}
