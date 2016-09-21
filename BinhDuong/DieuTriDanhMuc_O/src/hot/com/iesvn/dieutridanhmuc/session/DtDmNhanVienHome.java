package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmNhanVienHome")
public class DtDmNhanVienHome extends EntityHome<DtDmNhanVien> {
private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;


	@In(create = true)
	DmHocViHome dmHocViHome;
	@In(create = true)
	NguoiDungHome nguoiDungHome;

	public void setDtDmNhanVienDtdmnhanvienMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmNhanVienDtdmnhanvienMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmNhanVien createInstance() {
		DtDmNhanVien dtDmNhanVien = new DtDmNhanVien();
		return dtDmNhanVien;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmNhanVien> temp = new DtDmNhanVienList("").getResultList();
		for(DtDmNhanVien each : temp){
			listTemp.add(each.getDtdmnhanvienTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmnhanvienTen();
			countChange = 1;
		}		
	}

	@Override
	public String persist() {
		/*Edit Ma Part 2*/
		
		String nvMaTemp = this.instance.getDtdmnhanvienMa();
		int count1 = 0;
		DtDmNhanVienList dtDmNhanVienList = new DtDmNhanVienList("");
				
		for (DtDmNhanVien each : dtDmNhanVienList.getResultList()) {
			if(nvMaTemp.equals((each.getDtdmnhanvienMa()).substring(0, 3))){
				int count1_temp = Integer.parseInt(each.getDtdmnhanvienMa().substring(3, 6));
				if(count1 <= count1_temp){
					count1 = count1_temp;
				}			
			}
		}
		count1 = count1 + 1;
		if((count1+"").length() == 1){
			if(count1 == 0){
				nvMaTemp = nvMaTemp + "001";
			}
			else{
				nvMaTemp = nvMaTemp + "00" + count1 + "";
			}	
		}
		else if((count1+"").length() == 2){
			nvMaTemp = nvMaTemp + "0" + count1 + "";
		}
		else if((count1+"").length() == 3){
			nvMaTemp = nvMaTemp + count1 + "";
		}
		else{
			nvMaTemp = nvMaTemp + "999";
		}		
		this.instance.setDtdmnhanvienMa(nvMaTemp);
		
		/*End edit Ma Part 2*/
		
		
		return super.persist();
	}

	public void wire() {
		getInstance();
		DmHocVi dmHocVi = dmHocViHome.getDefinedInstance();
		if (dmHocVi != null) {
			getInstance().setDmHocVi(dmHocVi);
		}
		NguoiDung nguoiDung = nguoiDungHome.getDefinedInstance();
		if (nguoiDung != null) {
			getInstance().setNguoiDung(nguoiDung);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmNhanVien getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DonMien> getDonMiens() {
		return getInstance() == null ? null : new ArrayList<DonMien>(
				getInstance().getDonMiens());
	}

	public List<DtDmBacSiBanKham> getDtDmBacSiBanKhams() {
		return getInstance() == null ? null : new ArrayList<DtDmBacSiBanKham>(
				getInstance().getDtDmBacSiBanKhams());
	}

	public List<DtDmBacSiBanKham> getDtDmBacSiBanKhams_1() {
		return getInstance() == null ? null : new ArrayList<DtDmBacSiBanKham>(
				getInstance().getDtDmBacSiBanKhams_1());
	}

	public List<DtDmBanKham> getDtDmBanKhamsForDtdmnhanvienBacsi1() {
		return getInstance() == null ? null : new ArrayList<DtDmBanKham>(
				getInstance().getDtDmBanKhamsForDtdmnhanvienBacsi1());
	}

	public List<DtDmBanKham> getDtDmBanKhamsForDtdmnhanvienBacsi1_1() {
		return getInstance() == null ? null : new ArrayList<DtDmBanKham>(
				getInstance().getDtDmBanKhamsForDtdmnhanvienBacsi1_1());
	}

	public List<DtDmBanKham> getDtDmBanKhamsForDtdmnhanvienBacsi2() {
		return getInstance() == null ? null : new ArrayList<DtDmBanKham>(
				getInstance().getDtDmBanKhamsForDtdmnhanvienBacsi2());
	}

	public List<DtDmBanKham> getDtDmBanKhamsForDtdmnhanvienBacsi2_1() {
		return getInstance() == null ? null : new ArrayList<DtDmBanKham>(
				getInstance().getDtDmBanKhamsForDtdmnhanvienBacsi2_1());
	}

	public List<DtDmBanKham> getDtDmBanKhamsForDtdmnhanvienBacsi3() {
		return getInstance() == null ? null : new ArrayList<DtDmBanKham>(
				getInstance().getDtDmBanKhamsForDtdmnhanvienBacsi3());
	}

	public List<DtDmBanKham> getDtDmBanKhamsForDtdmnhanvienBacsi3_1() {
		return getInstance() == null ? null : new ArrayList<DtDmBanKham>(
				getInstance().getDtDmBanKhamsForDtdmnhanvienBacsi3_1());
	}

	public List<DtDmKho> getDtDmKhos() {
		return getInstance() == null ? null : new ArrayList<DtDmKho>(
				getInstance().getDtDmKhos());
	}

	public List<DtDmKho> getDtDmKhos_1() {
		return getInstance() == null ? null : new ArrayList<DtDmKho>(
				getInstance().getDtDmKhos_1());
	}

	public List<DtDmNhanvienKhoa> getDtDmNhanvienKhoas() {
		return getInstance() == null ? null : new ArrayList<DtDmNhanvienKhoa>(
				getInstance().getDtDmNhanvienKhoas());
	}

	public List<DtDmNhanvienKhoa> getDtDmNhanvienKhoas_1() {
		return getInstance() == null ? null : new ArrayList<DtDmNhanvienKhoa>(
				getInstance().getDtDmNhanvienKhoas_1());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmnhanvienTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmnhanvienTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmnhanvienTen("");
					break;
				}
			}
						
		}		
	}

}
