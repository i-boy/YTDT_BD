package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmTinhHome")
public class DmTinhHome extends EntityHome<DmTinh> {

	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;
	public void setDmTinhDmtinhMaso(Integer id) {
		setId(id);
	}

	public Integer getDmTinhDmtinhMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmTinh createInstance() {
		DmTinh dmTinh = new DmTinh();
		return dmTinh;
		
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmTinh> temp = new DmTinhList("").getResultList();
		for(DmTinh each : temp){
			listTemp.add(each.getDmtinhTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmtinhTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmTinh getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmBenhVien> getDmBenhViens() {
		return getInstance() == null ? null : new ArrayList<DmBenhVien>(
				getInstance().getDmBenhViens());
	}

	public List<DmBenhVien> getDmBenhViens_1() {
		return getInstance() == null ? null : new ArrayList<DmBenhVien>(
				getInstance().getDmBenhViens_1());
	}

	public List<DmDonVi> getDmDonVis() {
		return getInstance() == null ? null : new ArrayList<DmDonVi>(
				getInstance().getDmDonVis());
	}

	public List<DmDonVi> getDmDonVis_1() {
		return getInstance() == null ? null : new ArrayList<DmDonVi>(
				getInstance().getDmDonVis_1());
	}

	public List<DmHuyen> getDmHuyens() {
		return getInstance() == null ? null : new ArrayList<DmHuyen>(
				getInstance().getDmHuyens());
	}

	public List<DmHuyen> getDmHuyens_1() {
		return getInstance() == null ? null : new ArrayList<DmHuyen>(
				getInstance().getDmHuyens_1());
	}

	public List<DmNhaCungCap> getDmNhaCungCaps() {
		return getInstance() == null ? null : new ArrayList<DmNhaCungCap>(
				getInstance().getDmNhaCungCaps());
	}

	public List<DmNhaCungCap> getDmNhaCungCaps_1() {
		return getInstance() == null ? null : new ArrayList<DmNhaCungCap>(
				getInstance().getDmNhaCungCaps_1());
	}

	public List<DtDmKhach> getDtDmKhachs() {
		return getInstance() == null ? null : new ArrayList<DtDmKhach>(
				getInstance().getDtDmKhachs());
	}

	public List<DtDmKhach> getDtDmKhachs_1() {
		return getInstance() == null ? null : new ArrayList<DtDmKhach>(
				getInstance().getDtDmKhachs_1());
	}

public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmtinhTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmtinhTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmtinhTen("");
					break;
				}
			}
						
		}		
	}
}
