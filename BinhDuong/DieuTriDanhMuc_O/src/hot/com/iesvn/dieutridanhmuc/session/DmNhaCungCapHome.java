package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmNhaCungCapHome")
public class DmNhaCungCapHome extends EntityHome<DmNhaCungCap> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmNguonChuongTrinhHome dmNguonChuongTrinhHome;
	@In(create = true)
	DmTinhHome dmTinhHome;

	public void setDmNhaCungCapDmnhacungcapMaso(Integer id) {
		setId(id);
	}

	public Integer getDmNhaCungCapDmnhacungcapMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmNhaCungCap createInstance() {
		DmNhaCungCap dmNhaCungCap = new DmNhaCungCap();
		return dmNhaCungCap;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmNhaCungCap> temp = new DmNhaCungCapList("").getResultList();
		for(DmNhaCungCap each : temp){
			listTemp.add(each.getDmnhacungcapTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmnhacungcapTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmNguonChuongTrinh dmNguonChuongTrinh = dmNguonChuongTrinhHome
				.getDefinedInstance();
		if (dmNguonChuongTrinh != null) {
			getInstance().setDmNguonChuongTrinh(dmNguonChuongTrinh);
		}
		DmTinh dmTinh = dmTinhHome.getDefinedInstance();
		if (dmTinh != null) {
			getInstance().setDmTinh(dmTinh);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmNhaCungCap getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmnhacungcapTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmnhacungcapTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmnhacungcapTen("");
					break;
				}
			}						
		}		
	}
}
