package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmKhoiBhytHome")
public class DtDmKhoiBhytHome extends EntityHome<DtDmKhoiBhyt> {
private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;
	
	@In(create = true)
	DtDmLoaiBhytHome dtDmLoaiBhytHome;
	@In(create = true)
	DtDmNhomBhytHome dtDmNhomBhytHome;
	@In(create = true)
	DtDmNhomBhyt2Home dtDmNhomBhyt2Home;
	@In(create = true)
	DtDmPlBhytHome dtDmPlBhytHome;

	public void setDtDmKhoiBhytDtdmkhoibhytMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmKhoiBhytDtdmkhoibhytMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmKhoiBhyt createInstance() {
		DtDmKhoiBhyt dtDmKhoiBhyt = new DtDmKhoiBhyt();
		return dtDmKhoiBhyt;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DtDmKhoiBhyt> temp = new DtDmKhoiBhytList("").getResultList();
		for(DtDmKhoiBhyt each : temp){
			listTemp.add(each.getDtdmkhoibhytTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDtdmkhoibhytTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DtDmLoaiBhyt dtDmLoaiBhyt = dtDmLoaiBhytHome.getDefinedInstance();
		if (dtDmLoaiBhyt != null) {
			getInstance().setDtDmLoaiBhyt(dtDmLoaiBhyt);
		}
		DtDmNhomBhyt dtDmNhomBhyt = dtDmNhomBhytHome.getDefinedInstance();
		if (dtDmNhomBhyt != null) {
			getInstance().setDtDmNhomBhyt(dtDmNhomBhyt);
		}
		DtDmNhomBhyt2 dtDmNhomBhyt2 = dtDmNhomBhyt2Home.getDefinedInstance();
		if (dtDmNhomBhyt2 != null) {
			getInstance().setDtDmNhomBhyt2(dtDmNhomBhyt2);
		}
		DtDmPlBhyt dtDmPlBhyt = dtDmPlBhytHome.getDefinedInstance();
		if (dtDmPlBhyt != null) {
			getInstance().setDtDmPlBhyt(dtDmPlBhyt);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmKhoiBhyt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDtdmkhoibhytTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && !instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDtdmkhoibhytTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDtdmkhoibhytTen("");
					break;
				}
			}
						
		}		
	}

}
