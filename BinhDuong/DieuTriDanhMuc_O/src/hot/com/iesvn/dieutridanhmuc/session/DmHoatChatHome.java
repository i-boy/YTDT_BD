package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmHoatChatHome")
public class DmHoatChatHome extends EntityHome<DmHoatChat> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	public void setDmHoatChatDmhoatchatMaso(Integer id) {
		setId(id);
	}

	public Integer getDmHoatChatDmhoatchatMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmHoatChat createInstance() {
		DmHoatChat dmHoatChat = new DmHoatChat();
		return dmHoatChat;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmHoatChat> temp = new DmHoatChatList("").getResultList();
		for(DmHoatChat each : temp){
			listTemp.add(each.getDmhoatchatTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmhoatchatTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmHoatChat getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmThuoc> getDmThuocs() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocs());
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmhoatchatTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmhoatchatTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmhoatchatTen("");
					break;
				}
			}						
		}		
	}

}
