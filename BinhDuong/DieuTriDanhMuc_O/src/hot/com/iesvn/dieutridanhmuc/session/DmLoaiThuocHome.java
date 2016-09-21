package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmLoaiThuocHome")
public class DmLoaiThuocHome extends EntityHome<DmLoaiThuoc> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmLoaiThuocDmloaithuocMaso(Integer id) {
		setId(id);
	}

	public Integer getDmLoaiThuocDmloaithuocMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmLoaiThuoc createInstance() {
		DmLoaiThuoc dmLoaiThuoc = new DmLoaiThuoc();
		return dmLoaiThuoc;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmLoaiThuoc> temp = new DmLoaiThuocList("").getResultList();
		for(DmLoaiThuoc each : temp){
			listTemp.add(each.getDmloaithuocTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmloaithuocTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmLoaiThuoc getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmNhomBaoCaoLoaiThuoc> getDmNhomBaoCaoLoaiThuocs() {
		return getInstance() == null ? null
				: new ArrayList<DmNhomBaoCaoLoaiThuoc>(getInstance()
						.getDmNhomBaoCaoLoaiThuocs());
	}

	public List<DmNhomBaoCaoLoaiThuoc> getDmNhomBaoCaoLoaiThuocs_1() {
		return getInstance() == null ? null
				: new ArrayList<DmNhomBaoCaoLoaiThuoc>(getInstance()
						.getDmNhomBaoCaoLoaiThuocs_1());
	}

	public List<DmPhanLoaiThuoc> getDmPhanLoaiThuocs() {
		return getInstance() == null ? null : new ArrayList<DmPhanLoaiThuoc>(
				getInstance().getDmPhanLoaiThuocs());
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmloaithuocTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmloaithuocTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmloaithuocTen("");
					break;
				}
			}						
		}		
	}

}
