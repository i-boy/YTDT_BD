package com.iesvn.yte.entity;

public class BenhNhanTrungTheBhytDTO {
	private String tiepdonMa;	
	private String bnMa;
	private String bnHoTen;
	private String bnGioiTinh;
	private String bnNamSinh;
	private String noiDkKcb;
	private String thoiHanThe;
	private String ngayTiepdon;
	private boolean tiepdonTrongNgay;
	
	public BenhNhanTrungTheBhytDTO() {
		
	}
	public String getTiepdonMa() {
		return tiepdonMa;
	}

	public void setTiepdonMa(String tiepdonMa) {
		this.tiepdonMa = tiepdonMa;
	}
		
	public String getBnMa() {
		return bnMa;
	}
		
	public void setBnMa(String bnMa) {
		this.bnMa = bnMa;
	}

	public String getBnHoTen() {
		return bnHoTen;
	}
	public void setBnHoTen(String bnHoTen) {
		this.bnHoTen = bnHoTen;
	}
	public String getBnGioiTinh() {
		return bnGioiTinh;
	}
	public void setBnGioiTinh(String bnGioiTinh) {
		this.bnGioiTinh = bnGioiTinh;
	}
	
	public String getBnNamSinh() {
		return bnNamSinh;
	}

	public void setBnNamSinh(String bnNamSinh) {
		this.bnNamSinh = bnNamSinh;
	}

	public String getNoiDkKcb() {
		return noiDkKcb;
	}
	public void setNoiDkKcb(String noiDkKcb) {
		this.noiDkKcb = noiDkKcb;
	}
	public String getThoiHanThe() {
		return thoiHanThe;
	}
	public void setThoiHanThe(String thoiHanThe) {
		this.thoiHanThe = thoiHanThe;
	}
	
	public String getNgayTiepdon() {
		return ngayTiepdon;
	}
	public void setNgayTiepdon(String ngayTiepdon) {
		this.ngayTiepdon = ngayTiepdon;
	}
	public boolean isTiepdonTrongNgay() {
		return tiepdonTrongNgay;
	}
	public void setTiepdonTrongNgay(boolean tiepdonTrongNgay) {
		this.tiepdonTrongNgay = tiepdonTrongNgay;
	}
	
}
