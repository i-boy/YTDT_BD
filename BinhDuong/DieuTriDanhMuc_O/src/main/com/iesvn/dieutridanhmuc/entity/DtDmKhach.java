package com.iesvn.dieutridanhmuc.entity;

// Generated Aug 3, 2011 2:01:45 PM by Hibernate Tools 3.2.4.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * DtDmKhach generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DT_DM_KHACH", catalog = "DB_YTDT_BD")
public class DtDmKhach implements java.io.Serializable {

	private String dtdmkhachMa;
	private DmTinh dmTinh;
	private DmNguonKinhPhi dmNguonKinhPhi;
	private DmKhoa dmKhoa;
	private DmNguonChuongTrinh dmNguonChuongTrinh;
	private String dtdmkhachTen;
	private String dtdmkhachDiachi;
	private String dtdmkhachMabophan;
	private String dtdmkhachDienthoai;
	private String dtdmkhachMasothue;
	private String dtdmkhachFax;
	private String dtdmkhachPhanbiet;
	private Date dtdmkhachNgaylv;
	private Double dtdmkhachNgaygiocn;
	private Boolean dtdmkhachChon;

	public DtDmKhach() {
	}

	public DtDmKhach(String dtdmkhachMa, DmTinh dmTinh,
			DmNguonKinhPhi dmNguonKinhPhi, String dtdmkhachMabophan,
			String dtdmkhachDienthoai, String dtdmkhachMasothue,
			String dtdmkhachFax, String dtdmkhachPhanbiet) {
		this.dtdmkhachMa = dtdmkhachMa;
		this.dmTinh = dmTinh;
		this.dmNguonKinhPhi = dmNguonKinhPhi;
		this.dtdmkhachMabophan = dtdmkhachMabophan;
		this.dtdmkhachDienthoai = dtdmkhachDienthoai;
		this.dtdmkhachMasothue = dtdmkhachMasothue;
		this.dtdmkhachFax = dtdmkhachFax;
		this.dtdmkhachPhanbiet = dtdmkhachPhanbiet;
	}

	public DtDmKhach(String dtdmkhachMa, DmTinh dmTinh,
			DmNguonKinhPhi dmNguonKinhPhi, DmKhoa dmKhoa,
			DmNguonChuongTrinh dmNguonChuongTrinh, String dtdmkhachTen,
			String dtdmkhachDiachi, String dtdmkhachMabophan,
			String dtdmkhachDienthoai, String dtdmkhachMasothue,
			String dtdmkhachFax, String dtdmkhachPhanbiet,
			Date dtdmkhachNgaylv, Double dtdmkhachNgaygiocn,
			Boolean dtdmkhachChon) {
		this.dtdmkhachMa = dtdmkhachMa;
		this.dmTinh = dmTinh;
		this.dmNguonKinhPhi = dmNguonKinhPhi;
		this.dmKhoa = dmKhoa;
		this.dmNguonChuongTrinh = dmNguonChuongTrinh;
		this.dtdmkhachTen = dtdmkhachTen;
		this.dtdmkhachDiachi = dtdmkhachDiachi;
		this.dtdmkhachMabophan = dtdmkhachMabophan;
		this.dtdmkhachDienthoai = dtdmkhachDienthoai;
		this.dtdmkhachMasothue = dtdmkhachMasothue;
		this.dtdmkhachFax = dtdmkhachFax;
		this.dtdmkhachPhanbiet = dtdmkhachPhanbiet;
		this.dtdmkhachNgaylv = dtdmkhachNgaylv;
		this.dtdmkhachNgaygiocn = dtdmkhachNgaygiocn;
		this.dtdmkhachChon = dtdmkhachChon;
	}

	@Id
	@Column(name = "DTDMKHACH_MA", unique = true, nullable = false, length = 10)
	@NotNull
	@Length(max = 10)
	public String getDtdmkhachMa() {
		return this.dtdmkhachMa;
	}

	public void setDtdmkhachMa(String dtdmkhachMa) {
		this.dtdmkhachMa = dtdmkhachMa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DMTINH_MASO", nullable = false)
	@NotNull
	public DmTinh getDmTinh() {
		return this.dmTinh;
	}

	public void setDmTinh(DmTinh dmTinh) {
		this.dmTinh = dmTinh;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DMNGUONKINHPHI_MASO", nullable = false)
	@NotNull
	public DmNguonKinhPhi getDmNguonKinhPhi() {
		return this.dmNguonKinhPhi;
	}

	public void setDmNguonKinhPhi(DmNguonKinhPhi dmNguonKinhPhi) {
		this.dmNguonKinhPhi = dmNguonKinhPhi;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DMKHOA_MASO")
	public DmKhoa getDmKhoa() {
		return this.dmKhoa;
	}

	public void setDmKhoa(DmKhoa dmKhoa) {
		this.dmKhoa = dmKhoa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DMNCT_MASO")
	public DmNguonChuongTrinh getDmNguonChuongTrinh() {
		return this.dmNguonChuongTrinh;
	}

	public void setDmNguonChuongTrinh(DmNguonChuongTrinh dmNguonChuongTrinh) {
		this.dmNguonChuongTrinh = dmNguonChuongTrinh;
	}

	@Column(name = "DTDMKHACH_TEN", length = 250)
	@Length(max = 250)
	public String getDtdmkhachTen() {
		return this.dtdmkhachTen;
	}

	public void setDtdmkhachTen(String dtdmkhachTen) {
		this.dtdmkhachTen = dtdmkhachTen;
	}

	@Column(name = "DTDMKHACH_DIACHI", length = 30)
	@Length(max = 30)
	public String getDtdmkhachDiachi() {
		return this.dtdmkhachDiachi;
	}

	public void setDtdmkhachDiachi(String dtdmkhachDiachi) {
		this.dtdmkhachDiachi = dtdmkhachDiachi;
	}

	@Column(name = "DTDMKHACH_MABOPHAN", nullable = false, length = 3)
	@NotNull
	@Length(max = 3)
	public String getDtdmkhachMabophan() {
		return this.dtdmkhachMabophan;
	}

	public void setDtdmkhachMabophan(String dtdmkhachMabophan) {
		this.dtdmkhachMabophan = dtdmkhachMabophan;
	}

	@Column(name = "DTDMKHACH_DIENTHOAI", nullable = false, length = 18)
	@NotNull
	@Length(max = 18)
	public String getDtdmkhachDienthoai() {
		return this.dtdmkhachDienthoai;
	}

	public void setDtdmkhachDienthoai(String dtdmkhachDienthoai) {
		this.dtdmkhachDienthoai = dtdmkhachDienthoai;
	}

	@Column(name = "DTDMKHACH_MASOTHUE", nullable = false, length = 25)
	@NotNull
	@Length(max = 25)
	public String getDtdmkhachMasothue() {
		return this.dtdmkhachMasothue;
	}

	public void setDtdmkhachMasothue(String dtdmkhachMasothue) {
		this.dtdmkhachMasothue = dtdmkhachMasothue;
	}

	@Column(name = "DTDMKHACH_FAX", nullable = false, length = 18)
	@NotNull
	@Length(max = 18)
	public String getDtdmkhachFax() {
		return this.dtdmkhachFax;
	}

	public void setDtdmkhachFax(String dtdmkhachFax) {
		this.dtdmkhachFax = dtdmkhachFax;
	}

	@Column(name = "DTDMKHACH_PHANBIET", nullable = false, length = 4)
	@NotNull
	@Length(max = 4)
	public String getDtdmkhachPhanbiet() {
		return this.dtdmkhachPhanbiet;
	}

	public void setDtdmkhachPhanbiet(String dtdmkhachPhanbiet) {
		this.dtdmkhachPhanbiet = dtdmkhachPhanbiet;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTDMKHACH_NGAYLV", length = 0)
	public Date getDtdmkhachNgaylv() {
		return this.dtdmkhachNgaylv;
	}

	public void setDtdmkhachNgaylv(Date dtdmkhachNgaylv) {
		this.dtdmkhachNgaylv = dtdmkhachNgaylv;
	}

	@Column(name = "DTDMKHACH_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDtdmkhachNgaygiocn() {
		return this.dtdmkhachNgaygiocn;
	}

	public void setDtdmkhachNgaygiocn(Double dtdmkhachNgaygiocn) {
		this.dtdmkhachNgaygiocn = dtdmkhachNgaygiocn;
	}

	@Column(name = "DTDMKHACH_CHON")
	public Boolean getDtdmkhachChon() {
		return this.dtdmkhachChon;
	}

	public void setDtdmkhachChon(Boolean dtdmkhachChon) {
		this.dtdmkhachChon = dtdmkhachChon;
	}

}
