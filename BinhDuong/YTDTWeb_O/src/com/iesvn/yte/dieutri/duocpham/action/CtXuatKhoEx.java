package com.iesvn.yte.dieutri.duocpham.action;

import java.io.Serializable;

import com.iesvn.yte.dieutri.entity.CtXuatKho;
import com.iesvn.yte.dieutri.entity.TonKho;

public class CtXuatKhoEx implements Serializable {
	private static final long serialVersionUID = 10L;
	private CtXuatKho ctXuatKho;
	private TonKho tonkhoXuat;
	private TonKho tonkhoNhan;
	
	public CtXuatKhoEx() {
		
	}
	
	public void setCtXuatKho(CtXuatKho ctXuatKho) {
		this.ctXuatKho = ctXuatKho;
	}
	public CtXuatKho getCtXuatKho() {
		return ctXuatKho;
	}

	public void setTonkhoXuat(TonKho tonkhoXuat) {
		this.tonkhoXuat = tonkhoXuat;
	}

	public TonKho getTonkhoXuat() {
		return tonkhoXuat;
	}

	public void setTonkhoNhan(TonKho tonkhoNhan) {
		this.tonkhoNhan = tonkhoNhan;
	}

	public TonKho getTonkhoNhan() {
		return tonkhoNhan;
	}

}


