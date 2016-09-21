package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmNhomBaoCaoThuocHome")
public class DmNhomBaoCaoThuocHome extends EntityHome<DmNhomBaoCaoThuoc> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmNhomBaoCaoThuocDmnhombcthuocMaso(Integer id) {
		setId(id);
	}

	public Integer getDmNhomBaoCaoThuocDmnhombcthuocMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmNhomBaoCaoThuoc createInstance() {
		DmNhomBaoCaoThuoc dmNhomBaoCaoThuoc = new DmNhomBaoCaoThuoc();
		return dmNhomBaoCaoThuoc;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmNhomBaoCaoThuoc> temp = new DmNhomBaoCaoThuocList("").getResultList();
		for(DmNhomBaoCaoThuoc each : temp){
			listTemp.add(each.getDmnhombcthuocTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmnhombcthuocTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmNhomBaoCaoThuoc getDefinedInstance() {
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

	public List<DmPhanNhomThuoc> getDmPhanNhomThuocs() {
		return getInstance() == null ? null : new ArrayList<DmPhanNhomThuoc>(
				getInstance().getDmPhanNhomThuocs());
	}

	public List<DmPhanNhomThuoc> getDmPhanNhomThuocs_1() {
		return getInstance() == null ? null : new ArrayList<DmPhanNhomThuoc>(
				getInstance().getDmPhanNhomThuocs_1());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmnhombcthuocTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmnhombcthuocTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmnhombcthuocTen("");
					break;
				}
			}						
		}		
	}

}
