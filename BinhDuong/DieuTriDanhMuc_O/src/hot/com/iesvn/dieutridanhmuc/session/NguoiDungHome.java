package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("nguoiDungHome")
public class NguoiDungHome extends EntityHome<NguoiDung> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setNguoiDungNdMaso(Integer id) {
		setId(id);
	}

	public Integer getNguoiDungNdMaso() {
		return (Integer) getId();
	}

	@Override
	protected NguoiDung createInstance() {
		NguoiDung nguoiDung = new NguoiDung();
		return nguoiDung;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<NguoiDung> temp = new NguoiDungList("").getResultList();
		for(NguoiDung each : temp){
			listTemp.add(each.getNdTendangnhap());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getNdTendangnhap();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public NguoiDung getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmNhanVien> getDtDmNhanViens() {
		return getInstance() == null ? null : new ArrayList<DtDmNhanVien>(
				getInstance().getDtDmNhanViens());
	}

	public List<NguoiDungVaiTro> getNguoiDungVaiTros() {
		return getInstance() == null ? null : new ArrayList<NguoiDungVaiTro>(
				getInstance().getNguoiDungVaiTros());
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getNdTendangnhap();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setNdTendangnhap("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setNdTendangnhap("");
					break;
				}
			}
						
		}		
	}

}
