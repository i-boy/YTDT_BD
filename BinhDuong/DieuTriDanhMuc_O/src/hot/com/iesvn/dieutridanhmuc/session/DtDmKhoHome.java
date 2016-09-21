package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmKhoHome")
public class DtDmKhoHome extends EntityHome<DtDmKho> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmKhoaHome dmKhoaHome;
	@In(create = true)
	DtDmNhanVienHome dtDmNhanVienHome;

	public void setDtDmKhoDtdmkhoMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmKhoDtdmkhoMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmKho createInstance() {
		DtDmKho dtDmKho = new DtDmKho();
		return dtDmKho;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmKho> temp = new DtDmKhoList("").getResultList();
		for(DtDmKho each : temp){
			listTemp.add(each.getDtdmkhoTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmkhoTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmKhoa dmKhoa = dmKhoaHome.getDefinedInstance();
		if (dmKhoa != null) {
			getInstance().setDmKhoa(dmKhoa);
		}
		DtDmNhanVien dtDmNhanVien = dtDmNhanVienHome.getDefinedInstance();
		if (dtDmNhanVien != null) {
			getInstance().setDtDmNhanVien(dtDmNhanVien);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmKho getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmkhoTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmkhoTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmkhoTen("");
					break;
				}
			}
						
		}		
	}

}
