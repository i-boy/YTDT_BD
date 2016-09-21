package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmTaiNanHome")
public class DmTaiNanHome extends EntityHome<DmTaiNan> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmTaiNanDmtainanMaso(Integer id) {
		setId(id);
	}

	public Integer getDmTaiNanDmtainanMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmTaiNan createInstance() {
		DmTaiNan dmTaiNan = new DmTaiNan();
		return dmTaiNan;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmTaiNan> temp = new DmTaiNanList("").getResultList();
		for(DmTaiNan each : temp){
			listTemp.add(each.getDmtainanTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmtainanTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmTaiNan getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmPhanLoaiTaiNan> getDmPhanLoaiTaiNans() {
		return getInstance() == null ? null : new ArrayList<DmPhanLoaiTaiNan>(
				getInstance().getDmPhanLoaiTaiNans());
	}

	public List<DmPhanLoaiTaiNan> getDmPhanLoaiTaiNans_1() {
		return getInstance() == null ? null : new ArrayList<DmPhanLoaiTaiNan>(
				getInstance().getDmPhanLoaiTaiNans_1());
	}

	public List<DmPhuongThucGayTaiNan> getDmPhuongThucGayTaiNans() {
		return getInstance() == null ? null
				: new ArrayList<DmPhuongThucGayTaiNan>(getInstance()
						.getDmPhuongThucGayTaiNans());
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmtainanTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmtainanTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmtainanTen("");
					break;
				}
			}						
		}		
	}

}
