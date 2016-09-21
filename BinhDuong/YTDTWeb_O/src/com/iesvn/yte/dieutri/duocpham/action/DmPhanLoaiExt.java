package com.iesvn.yte.dieutri.duocpham.action;

import java.io.Serializable;

import com.iesvn.yte.entity.DmPhanLoaiThuoc;

public class DmPhanLoaiExt implements Serializable {
	private DmPhanLoaiThuoc dmPhanLoai;
	private boolean checked;
	
	public DmPhanLoaiExt() {
		
	}
	
	public void setDmPhanLoai(DmPhanLoaiThuoc dmPhanLoai) {
		this.dmPhanLoai = dmPhanLoai;
	}
	
	public DmPhanLoaiThuoc getDmPhanLoai() {
		return dmPhanLoai;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public boolean isChecked() {
		return checked;
	}
}


