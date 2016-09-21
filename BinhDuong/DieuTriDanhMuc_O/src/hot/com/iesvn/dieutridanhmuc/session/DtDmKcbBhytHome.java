package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmKcbBhytHome")
public class DtDmKcbBhytHome extends EntityHome<DtDmKcbBhyt> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DtDmTinhBhytHome dtDmTinhBhytHome;
	@In(create = true)
	DtDmTuyenKcbHome dtDmTuyenKcbHome;

	public void setDtDmKcbBhytDtdmkcbbhytMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmKcbBhytDtdmkcbbhytMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmKcbBhyt createInstance() {
		DtDmKcbBhyt dtDmKcbBhyt = new DtDmKcbBhyt();
		return dtDmKcbBhyt;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmKcbBhyt> temp = new DtDmKcbBhytList("").getResultList();
		for(DtDmKcbBhyt each : temp){
			listTemp.add(each.getDtdmkcbbhytTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmkcbbhytTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DtDmTinhBhyt dtDmTinhBhyt = dtDmTinhBhytHome.getDefinedInstance();
		if (dtDmTinhBhyt != null) {
			getInstance().setDtDmTinhBhyt(dtDmTinhBhyt);
		}
		DtDmTuyenKcb dtDmTuyenKcb = dtDmTuyenKcbHome.getDefinedInstance();
		if (dtDmTuyenKcb != null) {
			getInstance().setDtDmTuyenKcb(dtDmTuyenKcb);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmKcbBhyt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmkcbbhytTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmkcbbhytTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmkcbbhytTen("");
					break;
				}
			}						
		}		
	}

}
