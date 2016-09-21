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
 * DmTuyen generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_TUYEN", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMTUYEN_MA"))
public class DmTuyen implements java.io.Serializable {

	private Integer dmtuyenMaso;
	private String dmtuyenMa;
	private String dmtuyenTen;
	private Double dmtuyenNgaygiocn;
	private Boolean dmtuyenQl;
	private Boolean dmtuyenDt;
	private Boolean dmtuyenDp;
	private Set<DmBenhVien> dmBenhViens = new HashSet<DmBenhVien>(0);
	private Set<DmTuyenDonVi> dmTuyenDonVis = new HashSet<DmTuyenDonVi>(0);
	private Set<DmDonVi> dmDonVis = new HashSet<DmDonVi>(0);
	private Set<DmDonVi> dmDonVis_1 = new HashSet<DmDonVi>(0);
	private Set<DmTuyenDonVi> dmTuyenDonVis_1 = new HashSet<DmTuyenDonVi>(0);
	private Set<DmBenhVien> dmBenhViens_1 = new HashSet<DmBenhVien>(0);

	public DmTuyen() {
	}

	public DmTuyen(String dmtuyenMa, String dmtuyenTen) {
		this.dmtuyenMa = dmtuyenMa;
		this.dmtuyenTen = dmtuyenTen;
	}

	public DmTuyen(String dmtuyenMa, String dmtuyenTen,
			Double dmtuyenNgaygiocn, Boolean dmtuyenQl, Boolean dmtuyenDt,
			Boolean dmtuyenDp, Set<DmBenhVien> dmBenhViens,
			Set<DmTuyenDonVi> dmTuyenDonVis, Set<DmDonVi> dmDonVis,
			Set<DmDonVi> dmDonVis_1, Set<DmTuyenDonVi> dmTuyenDonVis_1,
			Set<DmBenhVien> dmBenhViens_1) {
		this.dmtuyenMa = dmtuyenMa;
		this.dmtuyenTen = dmtuyenTen;
		this.dmtuyenNgaygiocn = dmtuyenNgaygiocn;
		this.dmtuyenQl = dmtuyenQl;
		this.dmtuyenDt = dmtuyenDt;
		this.dmtuyenDp = dmtuyenDp;
		this.dmBenhViens = dmBenhViens;
		this.dmTuyenDonVis = dmTuyenDonVis;
		this.dmDonVis = dmDonVis;
		this.dmDonVis_1 = dmDonVis_1;
		this.dmTuyenDonVis_1 = dmTuyenDonVis_1;
		this.dmBenhViens_1 = dmBenhViens_1;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_TUYEN")
	@SequenceGenerator(name = "DM_TUYEN", sequenceName = "DM_TUYEN_DMTUYEN_MASO_SEQ", allocationSize = 1)
	@Column(name = "DMTUYEN_MASO", unique = true, nullable = false)
	public Integer getDmtuyenMaso() {
		return this.dmtuyenMaso;
	}

	public void setDmtuyenMaso(Integer dmtuyenMaso) {
		this.dmtuyenMaso = dmtuyenMaso;
	}

	@Column(name = "DMTUYEN_MA", unique = true, nullable = false, length = 10)
	@NotNull
	@Length(max = 10)
	public String getDmtuyenMa() {
		return this.dmtuyenMa;
	}

	public void setDmtuyenMa(String dmtuyenMa) {
		this.dmtuyenMa = dmtuyenMa;
	}

	@Column(name = "DMTUYEN_TEN", nullable = false, length = 250)
	@NotNull
	@Length(max = 250)
	public String getDmtuyenTen() {
		return this.dmtuyenTen;
	}

	public void setDmtuyenTen(String dmtuyenTen) {
		this.dmtuyenTen = dmtuyenTen;
	}

	@Column(name = "DMTUYEN_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmtuyenNgaygiocn() {
		return this.dmtuyenNgaygiocn;
	}

	public void setDmtuyenNgaygiocn(Double dmtuyenNgaygiocn) {
		this.dmtuyenNgaygiocn = dmtuyenNgaygiocn;
	}

	@Column(name = "DMTUYEN_QL")
	public Boolean getDmtuyenQl() {
		return this.dmtuyenQl;
	}

	public void setDmtuyenQl(Boolean dmtuyenQl) {
		this.dmtuyenQl = dmtuyenQl;
	}

	@Column(name = "DMTUYEN_DT")
	public Boolean getDmtuyenDt() {
		return this.dmtuyenDt;
	}

	public void setDmtuyenDt(Boolean dmtuyenDt) {
		this.dmtuyenDt = dmtuyenDt;
	}

	@Column(name = "DMTUYEN_DP")
	public Boolean getDmtuyenDp() {
		return this.dmtuyenDp;
	}

	public void setDmtuyenDp(Boolean dmtuyenDp) {
		this.dmtuyenDp = dmtuyenDp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmTuyen")
	public Set<DmBenhVien> getDmBenhViens() {
		return this.dmBenhViens;
	}

	public void setDmBenhViens(Set<DmBenhVien> dmBenhViens) {
		this.dmBenhViens = dmBenhViens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmTuyen")
	public Set<DmTuyenDonVi> getDmTuyenDonVis() {
		return this.dmTuyenDonVis;
	}

	public void setDmTuyenDonVis(Set<DmTuyenDonVi> dmTuyenDonVis) {
		this.dmTuyenDonVis = dmTuyenDonVis;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmTuyen")
	public Set<DmDonVi> getDmDonVis() {
		return this.dmDonVis;
	}

	public void setDmDonVis(Set<DmDonVi> dmDonVis) {
		this.dmDonVis = dmDonVis;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmTuyen")
	public Set<DmDonVi> getDmDonVis_1() {
		return this.dmDonVis_1;
	}

	public void setDmDonVis_1(Set<DmDonVi> dmDonVis_1) {
		this.dmDonVis_1 = dmDonVis_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmTuyen")
	public Set<DmTuyenDonVi> getDmTuyenDonVis_1() {
		return this.dmTuyenDonVis_1;
	}

	public void setDmTuyenDonVis_1(Set<DmTuyenDonVi> dmTuyenDonVis_1) {
		this.dmTuyenDonVis_1 = dmTuyenDonVis_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmTuyen")
	public Set<DmBenhVien> getDmBenhViens_1() {
		return this.dmBenhViens_1;
	}

	public void setDmBenhViens_1(Set<DmBenhVien> dmBenhViens_1) {
		this.dmBenhViens_1 = dmBenhViens_1;
	}

}
