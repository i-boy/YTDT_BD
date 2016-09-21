package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.iesvn.yte.dieutri.entity.Hsba;
@Scope(CONVERSATION)
@Name("B3243_Dieuchinhthongtinhanhchinhbenhnhan")
@Synchronized(timeout = 6000000)
public class DieuChinhThongTinHanhChinhBenhNhan implements Serializable {
	private Hsba hsba;
	private String ngaySinh;
	
	public void displayInfor(){
		
	}

}
