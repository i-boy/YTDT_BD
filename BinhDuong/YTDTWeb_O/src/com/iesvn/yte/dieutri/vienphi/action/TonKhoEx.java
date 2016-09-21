package com.iesvn.yte.dieutri.vienphi.action;

import java.io.Serializable;

import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.TonKho;

public class TonKhoEx implements Serializable {
	private static final long serialVersionUID = 10L;
	private TonKho tk;
	private ThuocNoiTru tnt;
	private Integer thutu;
	private Boolean isAlowedUpdate;
	
	public TonKhoEx() {

	}
	
	public TonKho getTk() {
		return tk;
	}
	
	public TonKho getTk(Boolean create) {
		if (create && tk == null) {
			tk = new TonKho();
        }
		return tk;
	}
	
	public void setTk(TonKho tk) {
		this.tk = tk;
	}
	
	public ThuocNoiTru getTnt() {
		return tnt;
	}
	
	public ThuocNoiTru getTnt(Boolean create) {
		if (create && tnt == null) {
				tnt = new ThuocNoiTru();
		}
		return tnt;		
	}

	public void setTnt(ThuocNoiTru tnt) {
		this.tnt = tnt;		
	}
	
	public Integer getThutu() {
		return thutu;
	}

	public void setThutu(Integer thutu) {
		this.thutu = thutu;
	}

	public Boolean getIsAllowedUpdate() {
		return isAlowedUpdate;
	}

	public void setIsAllowedUpdate(Boolean isAlowedUpdate) {
		this.isAlowedUpdate = isAlowedUpdate;
	}
}
