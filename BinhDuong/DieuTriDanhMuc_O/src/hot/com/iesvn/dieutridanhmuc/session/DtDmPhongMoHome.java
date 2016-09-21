package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmPhongMoHome")
public class DtDmPhongMoHome extends EntityHome<DtDmPhongMo> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmKhoaHome dmKhoaHome;

	public void setDtDmPhongMoDtdmphongmoMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmPhongMoDtdmphongmoMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmPhongMo createInstance() {
		DtDmPhongMo dtDmPhongMo = new DtDmPhongMo();
		return dtDmPhongMo;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmPhongMo> temp = new DtDmPhongMoList("").getResultList();
		for(DtDmPhongMo each : temp){
			listTemp.add(each.getDtdmphongmoTenphong());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmphongmoTenphong();
			countChange = 1;
		}		
	}
	
	public void wire() {
		getInstance();
		DmKhoa dmKhoa = dmKhoaHome.getDefinedInstance();
		if (dmKhoa != null) {
			getInstance().setDmKhoa(dmKhoa);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmPhongMo getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmphongmoTenphong();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmphongmoTenphong("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmphongmoTenphong("");
					break;
				}
			}
						
		}		
	}

}
