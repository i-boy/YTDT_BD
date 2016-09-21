package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmLoaiPhauThuatHome")
public class DtDmLoaiPhauThuatHome extends EntityHome<DtDmLoaiPhauThuat> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDtDmLoaiPhauThuatDtdmloaiptMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmLoaiPhauThuatDtdmloaiptMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmLoaiPhauThuat createInstance() {
		DtDmLoaiPhauThuat dtDmLoaiPhauThuat = new DtDmLoaiPhauThuat();
		return dtDmLoaiPhauThuat;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmLoaiPhauThuat> temp = new DtDmLoaiPhauThuatList("").getResultList();
		for(DtDmLoaiPhauThuat each : temp){
			listTemp.add(each.getDtdmloaiptTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmloaiptTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmLoaiPhauThuat getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmClsBangGia> getDtDmClsBangGias() {
		return getInstance() == null ? null : new ArrayList<DtDmClsBangGia>(
				getInstance().getDtDmClsBangGias());
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
		String instantTen = this.instance.getDtdmloaiptTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmloaiptTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmloaiptTen("");
					break;
				}
			}
						
		}		
	}
}
