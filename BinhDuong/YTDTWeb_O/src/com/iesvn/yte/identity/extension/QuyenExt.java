package com.iesvn.yte.identity.extension;

import java.io.Serializable;

import com.iesvn.yte.entity.VaiTro;

public class QuyenExt implements Serializable{
	private VaiTro vaitro = null;
	private Boolean chon = false;
	
	public VaiTro getVaitro() {
		if (vaitro == null){
			vaitro = new VaiTro();
		}
		return vaitro;
	}
	public void setVaitro(VaiTro vaitro) {
		this.vaitro = vaitro;
	}
	public Boolean getChon() {
		return chon;
	}
	public void setChon(Boolean chon) {
		this.chon = chon;
	}
	
	
} 
