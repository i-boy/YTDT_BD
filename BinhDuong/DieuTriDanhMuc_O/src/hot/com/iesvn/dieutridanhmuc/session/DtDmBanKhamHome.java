package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmBanKhamHome")
public class DtDmBanKhamHome extends EntityHome<DtDmBanKham> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmKhoaHome dmKhoaHome;
	@In(create = true)
	DtDmNhanVienHome dtDmNhanVienHome;

	public void setDtDmBanKhamDtdmbankhamMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmBanKhamDtdmbankhamMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmBanKham createInstance() {
		DtDmBanKham dtDmBanKham = new DtDmBanKham();
		return dtDmBanKham;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmBanKham> temp = new DtDmBanKhamList("").getResultList();
		for(DtDmBanKham each : temp){
			listTemp.add(each.getDtdmbankhamTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmbankhamTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmKhoa dmKhoa = dmKhoaHome.getDefinedInstance();
		if (dmKhoa != null) {
			getInstance().setDmKhoa(dmKhoa);
		}
		DtDmNhanVien dtDmNhanVienByDtdmnhanvienBacsi1 = dtDmNhanVienHome
				.getDefinedInstance();
		if (dtDmNhanVienByDtdmnhanvienBacsi1 != null) {
			getInstance().setDtDmNhanVienByDtdmnhanvienBacsi1(
					dtDmNhanVienByDtdmnhanvienBacsi1);
		}
		DtDmNhanVien dtDmNhanVienByDtdmnhanvienBacsi2 = dtDmNhanVienHome
				.getDefinedInstance();
		if (dtDmNhanVienByDtdmnhanvienBacsi2 != null) {
			getInstance().setDtDmNhanVienByDtdmnhanvienBacsi2(
					dtDmNhanVienByDtdmnhanvienBacsi2);
		}
		DtDmNhanVien dtDmNhanVienByDtdmnhanvienBacsi3 = dtDmNhanVienHome
				.getDefinedInstance();
		if (dtDmNhanVienByDtdmnhanvienBacsi3 != null) {
			getInstance().setDtDmNhanVienByDtdmnhanvienBacsi3(
					dtDmNhanVienByDtdmnhanvienBacsi3);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmBanKham getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmBacSiBanKham> getDtDmBacSiBanKhams() {
		return getInstance() == null ? null : new ArrayList<DtDmBacSiBanKham>(
				getInstance().getDtDmBacSiBanKhams());
	}

	public List<DtDmBacSiBanKham> getDtDmBacSiBanKhams_1() {
		return getInstance() == null ? null : new ArrayList<DtDmBacSiBanKham>(
				getInstance().getDtDmBacSiBanKhams_1());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmbankhamTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmbankhamTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmbankhamTen("");
					break;
				}
			}						
		}		
	}

}
