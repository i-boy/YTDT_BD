package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("vaiTroHome")
public class VaiTroHome extends EntityHome<VaiTro> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setVaiTroVaitroMaso(Integer id) {
		setId(id);
	}

	public Integer getVaiTroVaitroMaso() {
		return (Integer) getId();
	}

	@Override
	protected VaiTro createInstance() {
		VaiTro vaiTro = new VaiTro();
		return vaiTro;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<VaiTro> temp = new VaiTroList("").getResultList();
		for(VaiTro each : temp){
			listTemp.add(each.getVaitroTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getVaitroTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public VaiTro getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<NguoiDungVaiTro> getNguoiDungVaiTros() {
		return getInstance() == null ? null : new ArrayList<NguoiDungVaiTro>(
				getInstance().getNguoiDungVaiTros());
	}

	public List<NguoiDungVaiTro> getNguoiDungVaiTros_1() {
		return getInstance() == null ? null : new ArrayList<NguoiDungVaiTro>(
				getInstance().getNguoiDungVaiTros_1());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getVaitroTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setVaitroTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setVaitroTen("");
					break;
				}
			}
						
		}		
	}

}
