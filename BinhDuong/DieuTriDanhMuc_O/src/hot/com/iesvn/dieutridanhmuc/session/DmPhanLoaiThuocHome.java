package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmPhanLoaiThuocHome")
public class DmPhanLoaiThuocHome extends EntityHome<DmPhanLoaiThuoc> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmLoaiThuocHome dmLoaiThuocHome;

	public void setDmPhanLoaiThuocDmphanloaithuocMaso(Integer id) {
		setId(id);
	}

	public Integer getDmPhanLoaiThuocDmphanloaithuocMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmPhanLoaiThuoc createInstance() {
		DmPhanLoaiThuoc dmPhanLoaiThuoc = new DmPhanLoaiThuoc();
		return dmPhanLoaiThuoc;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmPhanLoaiThuoc> temp = new DmPhanLoaiThuocList("").getResultList();
		for(DmPhanLoaiThuoc each : temp){
			listTemp.add(each.getDmphanloaithuocTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmphanloaithuocTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmLoaiThuoc dmLoaiThuoc = dmLoaiThuocHome.getDefinedInstance();
		if (dmLoaiThuoc != null) {
			getInstance().setDmLoaiThuoc(dmLoaiThuoc);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmPhanLoaiThuoc getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmThuoc> getDmThuocsForDmphanloaithuocMaso() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocsForDmphanloaithuocMaso());
	}

	public List<DmThuoc> getDmThuocsForDmphanloaithuocMaso2() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocsForDmphanloaithuocMaso2());
	}

	public List<DmThuoc> getDmThuocsForDmphanloaithuocMaso2_1() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocsForDmphanloaithuocMaso2_1());
	}

	public List<DmThuoc> getDmThuocsForDmphanloaithuocMaso_1() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocsForDmphanloaithuocMaso_1());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmphanloaithuocTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmphanloaithuocTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmphanloaithuocTen("");
					break;
				}
			}						
		}		
	}

}
