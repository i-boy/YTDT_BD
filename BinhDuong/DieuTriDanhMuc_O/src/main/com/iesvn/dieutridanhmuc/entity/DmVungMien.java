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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * DmVungMien generated by hbm2java
 */

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_VUNG_MIEN", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMVUNGMIEN_MA"))
public class DmVungMien implements java.io.Serializable {

	private Integer dmvungmienMaso;
	private String dmvungmienMa;
	private String dmvungmienTen;
	private Double dmvungmienNgaygiocn;
	private Boolean dmvungmienChon;
	private Set<DmBenhVien> dmBenhViens = new HashSet<DmBenhVien>(0);
	private Set<DmBenhVien> dmBenhViens_1 = new HashSet<DmBenhVien>(0);

	public DmVungMien() {
	}

	public DmVungMien(String dmvungmienMa, String dmvungmienTen) {
		this.dmvungmienMa = dmvungmienMa;
		this.dmvungmienTen = dmvungmienTen;
	}

	public DmVungMien(String dmvungmienMa, String dmvungmienTen,
			Double dmvungmienNgaygiocn, Boolean dmvungmienChon,
			Set<DmBenhVien> dmBenhViens, Set<DmBenhVien> dmBenhViens_1) {
		this.dmvungmienMa = dmvungmienMa;
		this.dmvungmienTen = dmvungmienTen;
		this.dmvungmienNgaygiocn = dmvungmienNgaygiocn;
		this.dmvungmienChon = dmvungmienChon;
		this.dmBenhViens = dmBenhViens;
		this.dmBenhViens_1 = dmBenhViens_1;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_VUNG_MIEN")
	@SequenceGenerator(name = "DM_VUNG_MIEN", sequenceName = "DM_VUNG_MIEN_DMVUNGMIEN_MASO_S", allocationSize = 1)
	@Column(name = "DMVUNGMIEN_MASO", unique = true, nullable = false)
	public Integer getDmvungmienMaso() {
		return this.dmvungmienMaso;
	}

	public void setDmvungmienMaso(Integer dmvungmienMaso) {
		this.dmvungmienMaso = dmvungmienMaso;
	}

	@Column(name = "DMVUNGMIEN_MA", unique = true, nullable = false, length = 10)
	@NotNull
	@Length(max = 10)
	public String getDmvungmienMa() {
		return this.dmvungmienMa;
	}

	public void setDmvungmienMa(String dmvungmienMa) {
		this.dmvungmienMa = dmvungmienMa;
	}

	@Column(name = "DMVUNGMIEN_TEN", nullable = false, length = 250)
	@NotNull
	@Length(max = 250)
	public String getDmvungmienTen() {
		return this.dmvungmienTen;
	}

	public void setDmvungmienTen(String dmvungmienTen) {
		this.dmvungmienTen = dmvungmienTen;
	}

	@Column(name = "DMVUNGMIEN_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmvungmienNgaygiocn() {
		return this.dmvungmienNgaygiocn;
	}

	public void setDmvungmienNgaygiocn(Double dmvungmienNgaygiocn) {
		this.dmvungmienNgaygiocn = dmvungmienNgaygiocn;
	}

	@Column(name = "DMVUNGMIEN_CHON")
	public Boolean getDmvungmienChon() {
		return this.dmvungmienChon;
	}

	public void setDmvungmienChon(Boolean dmvungmienChon) {
		this.dmvungmienChon = dmvungmienChon;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmVungMien")
	public Set<DmBenhVien> getDmBenhViens() {
		return this.dmBenhViens;
	}

	public void setDmBenhViens(Set<DmBenhVien> dmBenhViens) {
		this.dmBenhViens = dmBenhViens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmVungMien")
	public Set<DmBenhVien> getDmBenhViens_1() {
		return this.dmBenhViens_1;
	}

	public void setDmBenhViens_1(Set<DmBenhVien> dmBenhViens_1) {
		this.dmBenhViens_1 = dmBenhViens_1;
	}

}