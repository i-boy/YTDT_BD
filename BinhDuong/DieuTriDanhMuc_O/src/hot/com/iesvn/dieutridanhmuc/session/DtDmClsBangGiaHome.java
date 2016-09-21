package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmClsBangGiaHome")
public class DtDmClsBangGiaHome extends EntityHome<DtDmClsBangGia> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DtDmClsHome dtDmClsHome;
	@In(create = true)
	DtDmLoaiPhauThuatHome dtDmLoaiPhauThuatHome;

	public void setDtDmClsBangGiaDtdmclsbgMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmClsBangGiaDtdmclsbgMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmClsBangGia createInstance() {
		DtDmClsBangGia dtDmClsBangGia = new DtDmClsBangGia();
		return dtDmClsBangGia;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmClsBangGia> temp = new DtDmClsBangGiaList("").getResultList();
		for(DtDmClsBangGia each : temp){
			listTemp.add(each.getDtdmclsbgDiengiai());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmclsbgDiengiai();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DtDmCls dtDmClsByDtdmclsbgMaloai = dtDmClsHome.getDefinedInstance();
		if (dtDmClsByDtdmclsbgMaloai != null) {
			getInstance().setDtDmClsByDtdmclsbgMaloai(dtDmClsByDtdmclsbgMaloai);
		}
		DtDmCls dtDmClsByDtdmclsbgPhanloai = dtDmClsHome.getDefinedInstance();
		if (dtDmClsByDtdmclsbgPhanloai != null) {
			getInstance().setDtDmClsByDtdmclsbgPhanloai(
					dtDmClsByDtdmclsbgPhanloai);
		}
		DtDmLoaiPhauThuat dtDmLoaiPhauThuat = dtDmLoaiPhauThuatHome
				.getDefinedInstance();
		if (dtDmLoaiPhauThuat != null) {
			getInstance().setDtDmLoaiPhauThuat(dtDmLoaiPhauThuat);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmClsBangGia getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmClsKetQua> getDtDmClsKetQuas() {
		return getInstance() == null ? null : new ArrayList<DtDmClsKetQua>(
				getInstance().getDtDmClsKetQuas());
	}

	public List<DtDmClsKhoa> getDtDmClsKhoas() {
		return getInstance() == null ? null : new ArrayList<DtDmClsKhoa>(
				getInstance().getDtDmClsKhoas());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmclsbgDiengiai();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmclsbgDiengiai("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmclsbgDiengiai("");
					break;
				}
			}						
		}		
	}

}
