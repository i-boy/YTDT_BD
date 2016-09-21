package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmBenhVienHome")
public class DmBenhVienHome extends EntityHome<DmBenhVien> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmHuyenHome dmHuyenHome;
	@In(create = true)
	DmLoaiBenhVienHome dmLoaiBenhVienHome;
	@In(create = true)
	DmTinhHome dmTinhHome;
	@In(create = true)
	DmTuyenHome dmTuyenHome;
	@In(create = true)
	DmVungMienHome dmVungMienHome;

	public void setDmBenhVienDmbenhvienMaso(Integer id) {
		setId(id);
	}

	public Integer getDmBenhVienDmbenhvienMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmBenhVien createInstance() {
		DmBenhVien dmBenhVien = new DmBenhVien();
		return dmBenhVien;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmBenhVien> temp = new DmBenhVienList("").getResultList();
		for(DmBenhVien each : temp){
			listTemp.add(each.getDmbenhvienTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmbenhvienTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmHuyen dmHuyen = dmHuyenHome.getDefinedInstance();
		if (dmHuyen != null) {
			getInstance().setDmHuyen(dmHuyen);
		}
		DmLoaiBenhVien dmLoaiBenhVien = dmLoaiBenhVienHome.getDefinedInstance();
		if (dmLoaiBenhVien != null) {
			getInstance().setDmLoaiBenhVien(dmLoaiBenhVien);
		}
		DmTinh dmTinh = dmTinhHome.getDefinedInstance();
		if (dmTinh != null) {
			getInstance().setDmTinh(dmTinh);
		}
		DmTuyen dmTuyen = dmTuyenHome.getDefinedInstance();
		if (dmTuyen != null) {
			getInstance().setDmTuyen(dmTuyen);
		}
		DmVungMien dmVungMien = dmVungMienHome.getDefinedInstance();
		if (dmVungMien != null) {
			getInstance().setDmVungMien(dmVungMien);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmBenhVien getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmbenhvienTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmbenhvienTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmbenhvienTen("");
					break;
				}
			}						
		}		
	}

}
