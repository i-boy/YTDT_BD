package com.iesvn.dieutridanhmuc.entity;

// Generated Aug 3, 2011 2:01:45 PM by Hibernate Tools 3.2.4.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * DtDmClsBangGia generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DT_DM_CLS_BANG_GIA", catalog = "DB_YTDT_BD")
public class DtDmClsBangGia implements java.io.Serializable {

	private Integer dtdmclsbgMaso;
	private DtDmCls dtDmClsByDtdmclsbgPhanloai;
	private DtDmLoaiPhauThuat dtDmLoaiPhauThuat;
	private DtDmCls dtDmClsByDtdmclsbgMaloai;
	private String dtdmclsbgMa;
	private String dtdmclsbgMa2;
	private String dtdmclsbgDiengiai;
	private Double dtdmclsbgPhandv;
	private Double dtdmclsbgDongia;
	private Double dtdmclsbgDongiamp;
	private Double dtdmclsbgDongiabh;
	private Double dtdmclsbgDongia2;
	private Double dtdmclsbgDongia3;
	private Double dtdmclsbgDongiayc;
	private Double dtdmclsbgDongiann;
	private Short dtdmclsbgKhactuyen;
	private Short dtdmclsbgVattu;
	private Short dtdmclsbgBaotri;
	private Short dtdmclsbgDiennuoc;
	private Short dtdmclsbgCong;
	private Short dtdmclsbgOxy;
	private Double dtdmclsbgNgaygiocn;
	private Boolean dtdmclsbgChon;
	private String dtdmclsbgPhanbiet;
	private String dmclsbgLoai;
	private Boolean dmclsbgNdm;
	private Boolean dmclsbgMien;
	private Boolean dmclsbgXn;
	private String dtdmclsbgCdha;
	private Set<DtDmClsKhoa> dtDmClsKhoas = new HashSet<DtDmClsKhoa>(0);
	private Set<DtDmClsKetQua> dtDmClsKetQuas = new HashSet<DtDmClsKetQua>(0);

	public DtDmClsBangGia() {
	}

	public DtDmClsBangGia(String dtdmclsbgMa, String dtdmclsbgDiengiai) {
		this.dtdmclsbgMa = dtdmclsbgMa;
		this.dtdmclsbgDiengiai = dtdmclsbgDiengiai;
	}

	public DtDmClsBangGia(DtDmCls dtDmClsByDtdmclsbgPhanloai,
			DtDmLoaiPhauThuat dtDmLoaiPhauThuat,
			DtDmCls dtDmClsByDtdmclsbgMaloai, String dtdmclsbgMa,
			String dtdmclsbgMa2, String dtdmclsbgDiengiai,
			Double dtdmclsbgPhandv, Double dtdmclsbgDongia,
			Double dtdmclsbgDongiamp, Double dtdmclsbgDongiabh,
			Double dtdmclsbgDongia2, Double dtdmclsbgDongia3,
			Double dtdmclsbgDongiayc, Double dtdmclsbgDongiann,
			Short dtdmclsbgKhactuyen, Short dtdmclsbgVattu,
			Short dtdmclsbgBaotri, Short dtdmclsbgDiennuoc,
			Short dtdmclsbgCong, Short dtdmclsbgOxy, Double dtdmclsbgNgaygiocn,
			Boolean dtdmclsbgChon, String dtdmclsbgPhanbiet,
			String dmclsbgLoai, Boolean dmclsbgNdm, Boolean dmclsbgMien,
			Boolean dmclsbgXn, String dtdmclsbgCdha,
			Set<DtDmClsKhoa> dtDmClsKhoas, Set<DtDmClsKetQua> dtDmClsKetQuas) {
		this.dtDmClsByDtdmclsbgPhanloai = dtDmClsByDtdmclsbgPhanloai;
		this.dtDmLoaiPhauThuat = dtDmLoaiPhauThuat;
		this.dtDmClsByDtdmclsbgMaloai = dtDmClsByDtdmclsbgMaloai;
		this.dtdmclsbgMa = dtdmclsbgMa;
		this.dtdmclsbgMa2 = dtdmclsbgMa2;
		this.dtdmclsbgDiengiai = dtdmclsbgDiengiai;
		this.dtdmclsbgPhandv = dtdmclsbgPhandv;
		this.dtdmclsbgDongia = dtdmclsbgDongia;
		this.dtdmclsbgDongiamp = dtdmclsbgDongiamp;
		this.dtdmclsbgDongiabh = dtdmclsbgDongiabh;
		this.dtdmclsbgDongia2 = dtdmclsbgDongia2;
		this.dtdmclsbgDongia3 = dtdmclsbgDongia3;
		this.dtdmclsbgDongiayc = dtdmclsbgDongiayc;
		this.dtdmclsbgDongiann = dtdmclsbgDongiann;
		this.dtdmclsbgKhactuyen = dtdmclsbgKhactuyen;
		this.dtdmclsbgVattu = dtdmclsbgVattu;
		this.dtdmclsbgBaotri = dtdmclsbgBaotri;
		this.dtdmclsbgDiennuoc = dtdmclsbgDiennuoc;
		this.dtdmclsbgCong = dtdmclsbgCong;
		this.dtdmclsbgOxy = dtdmclsbgOxy;
		this.dtdmclsbgNgaygiocn = dtdmclsbgNgaygiocn;
		this.dtdmclsbgChon = dtdmclsbgChon;
		this.dtdmclsbgPhanbiet = dtdmclsbgPhanbiet;
		this.dmclsbgLoai = dmclsbgLoai;
		this.dmclsbgNdm = dmclsbgNdm;
		this.dmclsbgMien = dmclsbgMien;
		this.dmclsbgXn = dmclsbgXn;
		this.dtdmclsbgCdha = dtdmclsbgCdha;
		this.dtDmClsKhoas = dtDmClsKhoas;
		this.dtDmClsKetQuas = dtDmClsKetQuas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_CLS_BANG_GIA")
	@SequenceGenerator(name = "DT_DM_CLS_BANG_GIA", sequenceName = "DT_DM_CLS_BANG_GIA_DTDMCLSBG_M", allocationSize = 1)
	@Column(name = "DTDMCLSBG_MASO", unique = true, nullable = false)
	public Integer getDtdmclsbgMaso() {
		return this.dtdmclsbgMaso;
	}

	public void setDtdmclsbgMaso(Integer dtdmclsbgMaso) {
		this.dtdmclsbgMaso = dtdmclsbgMaso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DTDMCLSBG_PHANLOAI")
	public DtDmCls getDtDmClsByDtdmclsbgPhanloai() {
		return this.dtDmClsByDtdmclsbgPhanloai;
	}

	public void setDtDmClsByDtdmclsbgPhanloai(DtDmCls dtDmClsByDtdmclsbgPhanloai) {
		this.dtDmClsByDtdmclsbgPhanloai = dtDmClsByDtdmclsbgPhanloai;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DMCLSBG_LOAIPTTT")
	public DtDmLoaiPhauThuat getDtDmLoaiPhauThuat() {
		return this.dtDmLoaiPhauThuat;
	}

	public void setDtDmLoaiPhauThuat(DtDmLoaiPhauThuat dtDmLoaiPhauThuat) {
		this.dtDmLoaiPhauThuat = dtDmLoaiPhauThuat;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DTDMCLSBG_MALOAI")
	public DtDmCls getDtDmClsByDtdmclsbgMaloai() {
		return this.dtDmClsByDtdmclsbgMaloai;
	}

	public void setDtDmClsByDtdmclsbgMaloai(DtDmCls dtDmClsByDtdmclsbgMaloai) {
		this.dtDmClsByDtdmclsbgMaloai = dtDmClsByDtdmclsbgMaloai;
	}

	@Column(name = "DTDMCLSBG_MA", nullable = false, length = 15)
	@NotNull
	@Length(max = 15)
	public String getDtdmclsbgMa() {
		return this.dtdmclsbgMa;
	}

	public void setDtdmclsbgMa(String dtdmclsbgMa) {
		this.dtdmclsbgMa = dtdmclsbgMa;
	}

	@Column(name = "DTDMCLSBG_MA2", length = 4)
	@Length(max = 4)
	public String getDtdmclsbgMa2() {
		return this.dtdmclsbgMa2;
	}

	public void setDtdmclsbgMa2(String dtdmclsbgMa2) {
		this.dtdmclsbgMa2 = dtdmclsbgMa2;
	}

	@Column(name = "DTDMCLSBG_DIENGIAI", nullable = false, length = 250)
	@NotNull
	@Length(max = 250)
	public String getDtdmclsbgDiengiai() {
		return this.dtdmclsbgDiengiai;
	}

	public void setDtdmclsbgDiengiai(String dtdmclsbgDiengiai) {
		this.dtdmclsbgDiengiai = dtdmclsbgDiengiai;
	}

	@Column(name = "DTDMCLSBG_PHANDV", precision = 22, scale = 0)
	public Double getDtdmclsbgPhandv() {
		return this.dtdmclsbgPhandv;
	}

	public void setDtdmclsbgPhandv(Double dtdmclsbgPhandv) {
		this.dtdmclsbgPhandv = dtdmclsbgPhandv;
	}

	@Column(name = "DTDMCLSBG_DONGIA", precision = 22, scale = 0)
	public Double getDtdmclsbgDongia() {
		return this.dtdmclsbgDongia;
	}

	public void setDtdmclsbgDongia(Double dtdmclsbgDongia) {
		this.dtdmclsbgDongia = dtdmclsbgDongia;
	}

	@Column(name = "DTDMCLSBG_DONGIAMP", precision = 22, scale = 0)
	public Double getDtdmclsbgDongiamp() {
		return this.dtdmclsbgDongiamp;
	}

	public void setDtdmclsbgDongiamp(Double dtdmclsbgDongiamp) {
		this.dtdmclsbgDongiamp = dtdmclsbgDongiamp;
	}

	@Column(name = "DTDMCLSBG_DONGIABH", precision = 22, scale = 0)
	public Double getDtdmclsbgDongiabh() {
		return this.dtdmclsbgDongiabh;
	}

	public void setDtdmclsbgDongiabh(Double dtdmclsbgDongiabh) {
		this.dtdmclsbgDongiabh = dtdmclsbgDongiabh;
	}

	@Column(name = "DTDMCLSBG_DONGIA2", precision = 22, scale = 0)
	public Double getDtdmclsbgDongia2() {
		return this.dtdmclsbgDongia2;
	}

	public void setDtdmclsbgDongia2(Double dtdmclsbgDongia2) {
		this.dtdmclsbgDongia2 = dtdmclsbgDongia2;
	}

	@Column(name = "DTDMCLSBG_DONGIA3", precision = 22, scale = 0)
	public Double getDtdmclsbgDongia3() {
		return this.dtdmclsbgDongia3;
	}

	public void setDtdmclsbgDongia3(Double dtdmclsbgDongia3) {
		this.dtdmclsbgDongia3 = dtdmclsbgDongia3;
	}

	@Column(name = "DTDMCLSBG_DONGIAYC", precision = 22, scale = 0)
	public Double getDtdmclsbgDongiayc() {
		return this.dtdmclsbgDongiayc;
	}

	public void setDtdmclsbgDongiayc(Double dtdmclsbgDongiayc) {
		this.dtdmclsbgDongiayc = dtdmclsbgDongiayc;
	}

	@Column(name = "DTDMCLSBG_DONGIANN", precision = 22, scale = 0)
	public Double getDtdmclsbgDongiann() {
		return this.dtdmclsbgDongiann;
	}

	public void setDtdmclsbgDongiann(Double dtdmclsbgDongiann) {
		this.dtdmclsbgDongiann = dtdmclsbgDongiann;
	}

	@Column(name = "DTDMCLSBG_KHACTUYEN")
	public Short getDtdmclsbgKhactuyen() {
		return this.dtdmclsbgKhactuyen;
	}

	public void setDtdmclsbgKhactuyen(Short dtdmclsbgKhactuyen) {
		this.dtdmclsbgKhactuyen = dtdmclsbgKhactuyen;
	}

	@Column(name = "DTDMCLSBG_VATTU")
	public Short getDtdmclsbgVattu() {
		return this.dtdmclsbgVattu;
	}

	public void setDtdmclsbgVattu(Short dtdmclsbgVattu) {
		this.dtdmclsbgVattu = dtdmclsbgVattu;
	}

	@Column(name = "DTDMCLSBG_BAOTRI")
	public Short getDtdmclsbgBaotri() {
		return this.dtdmclsbgBaotri;
	}

	public void setDtdmclsbgBaotri(Short dtdmclsbgBaotri) {
		this.dtdmclsbgBaotri = dtdmclsbgBaotri;
	}

	@Column(name = "DTDMCLSBG_DIENNUOC")
	public Short getDtdmclsbgDiennuoc() {
		return this.dtdmclsbgDiennuoc;
	}

	public void setDtdmclsbgDiennuoc(Short dtdmclsbgDiennuoc) {
		this.dtdmclsbgDiennuoc = dtdmclsbgDiennuoc;
	}

	@Column(name = "DTDMCLSBG_CONG")
	public Short getDtdmclsbgCong() {
		return this.dtdmclsbgCong;
	}

	public void setDtdmclsbgCong(Short dtdmclsbgCong) {
		this.dtdmclsbgCong = dtdmclsbgCong;
	}

	@Column(name = "DTDMCLSBG_OXY")
	public Short getDtdmclsbgOxy() {
		return this.dtdmclsbgOxy;
	}

	public void setDtdmclsbgOxy(Short dtdmclsbgOxy) {
		this.dtdmclsbgOxy = dtdmclsbgOxy;
	}

	@Column(name = "DTDMCLSBG_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDtdmclsbgNgaygiocn() {
		return this.dtdmclsbgNgaygiocn;
	}

	public void setDtdmclsbgNgaygiocn(Double dtdmclsbgNgaygiocn) {
		this.dtdmclsbgNgaygiocn = dtdmclsbgNgaygiocn;
	}

	@Column(name = "DTDMCLSBG_CHON")
	public Boolean getDtdmclsbgChon() {
		return this.dtdmclsbgChon;
	}

	public void setDtdmclsbgChon(Boolean dtdmclsbgChon) {
		this.dtdmclsbgChon = dtdmclsbgChon;
	}

	@Column(name = "DTDMCLSBG_PHANBIET", length = 256)
	@Length(max = 256)
	public String getDtdmclsbgPhanbiet() {
		return this.dtdmclsbgPhanbiet;
	}

	public void setDtdmclsbgPhanbiet(String dtdmclsbgPhanbiet) {
		this.dtdmclsbgPhanbiet = dtdmclsbgPhanbiet;
	}

	@Column(name = "DMCLSBG_LOAI", length = 1)
	@Length(max = 1)
	public String getDmclsbgLoai() {
		return this.dmclsbgLoai;
	}

	public void setDmclsbgLoai(String dmclsbgLoai) {
		this.dmclsbgLoai = dmclsbgLoai;
	}

	@Column(name = "DMCLSBG_NDM")
	public Boolean getDmclsbgNdm() {
		return this.dmclsbgNdm;
	}

	public void setDmclsbgNdm(Boolean dmclsbgNdm) {
		this.dmclsbgNdm = dmclsbgNdm;
	}

	@Column(name = "DMCLSBG_MIEN")
	public Boolean getDmclsbgMien() {
		return this.dmclsbgMien;
	}

	public void setDmclsbgMien(Boolean dmclsbgMien) {
		this.dmclsbgMien = dmclsbgMien;
	}

	@Column(name = "DMCLSBG_XN")
	public Boolean getDmclsbgXn() {
		return this.dmclsbgXn;
	}

	public void setDmclsbgXn(Boolean dmclsbgXn) {
		this.dmclsbgXn = dmclsbgXn;
	}

	@Column(name = "DTDMCLSBG_CDHA", length = 2)
	@Length(max = 2)
	public String getDtdmclsbgCdha() {
		return this.dtdmclsbgCdha;
	}

	public void setDtdmclsbgCdha(String dtdmclsbgCdha) {
		this.dtdmclsbgCdha = dtdmclsbgCdha;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dtDmClsBangGia")
	public Set<DtDmClsKhoa> getDtDmClsKhoas() {
		return this.dtDmClsKhoas;
	}

	public void setDtDmClsKhoas(Set<DtDmClsKhoa> dtDmClsKhoas) {
		this.dtDmClsKhoas = dtDmClsKhoas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dtDmClsBangGia")
	public Set<DtDmClsKetQua> getDtDmClsKetQuas() {
		return this.dtDmClsKetQuas;
	}

	public void setDtDmClsKetQuas(Set<DtDmClsKetQua> dtDmClsKetQuas) {
		this.dtDmClsKetQuas = dtDmClsKetQuas;
	}

}
