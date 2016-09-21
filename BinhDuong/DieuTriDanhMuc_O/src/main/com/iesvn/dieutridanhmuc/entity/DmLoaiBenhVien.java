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
 * DmLoaiBenhVien generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)

@Table(name = "DM_LOAI_BENH_VIEN", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMLOAIBV_MA"))
public class DmLoaiBenhVien implements java.io.Serializable {

	private Integer dmloaibvMaso;
	private String dmloaibvMa;
	private String dmloaibvTen;
	private Boolean dmloaibvDt;
	private Boolean dmloaibvQl;
	private Boolean dmloaibvDp;
	private Double dmloaibvNgaygiocn;
	private Set<DmBenhVien> dmBenhViens = new HashSet<DmBenhVien>(0);
	private Set<DmBenhVien> dmBenhViens_1 = new HashSet<DmBenhVien>(0);

	public DmLoaiBenhVien() {
	}

	public DmLoaiBenhVien(String dmloaibvMa, String dmloaibvTen) {
		this.dmloaibvMa = dmloaibvMa;
		this.dmloaibvTen = dmloaibvTen;
	}

	public DmLoaiBenhVien(String dmloaibvMa, String dmloaibvTen,
			Boolean dmloaibvDt, Boolean dmloaibvQl, Boolean dmloaibvDp,
			Double dmloaibvNgaygiocn, Set<DmBenhVien> dmBenhViens,
			Set<DmBenhVien> dmBenhViens_1) {
		this.dmloaibvMa = dmloaibvMa;
		this.dmloaibvTen = dmloaibvTen;
		this.dmloaibvDt = dmloaibvDt;
		this.dmloaibvQl = dmloaibvQl;
		this.dmloaibvDp = dmloaibvDp;
		this.dmloaibvNgaygiocn = dmloaibvNgaygiocn;
		this.dmBenhViens = dmBenhViens;
		this.dmBenhViens_1 = dmBenhViens_1;
	}

	@Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_BENH_VIEN")
	  @SequenceGenerator(name = "DM_LOAI_BENH_VIEN", sequenceName = "DM_LOAI_BENH_VIEN_DMLOAIBV_MAS", allocationSize = 1)
	@Column(name = "DMLOAIBV_MASO", unique = true, nullable = false)
	public Integer getDmloaibvMaso() {
		return this.dmloaibvMaso;
	}

	public void setDmloaibvMaso(Integer dmloaibvMaso) {
		this.dmloaibvMaso = dmloaibvMaso;
	}

	@Column(name = "DMLOAIBV_MA", unique = true, nullable = false, length = 10)
	@NotNull
	@Length(max = 10)
	public String getDmloaibvMa() {
		return this.dmloaibvMa;
	}

	public void setDmloaibvMa(String dmloaibvMa) {
		this.dmloaibvMa = dmloaibvMa;
	}

	@Column(name = "DMLOAIBV_TEN", nullable = false, length = 250)
	@NotNull
	@Length(max = 250)
	public String getDmloaibvTen() {
		return this.dmloaibvTen;
	}

	public void setDmloaibvTen(String dmloaibvTen) {
		this.dmloaibvTen = dmloaibvTen;
	}

	@Column(name = "DMLOAIBV_DT")
	public Boolean getDmloaibvDt() {
		return this.dmloaibvDt;
	}

	public void setDmloaibvDt(Boolean dmloaibvDt) {
		this.dmloaibvDt = dmloaibvDt;
	}

	@Column(name = "DMLOAIBV_QL")
	public Boolean getDmloaibvQl() {
		return this.dmloaibvQl;
	}

	public void setDmloaibvQl(Boolean dmloaibvQl) {
		this.dmloaibvQl = dmloaibvQl;
	}

	@Column(name = "DMLOAIBV_DP")
	public Boolean getDmloaibvDp() {
		return this.dmloaibvDp;
	}

	public void setDmloaibvDp(Boolean dmloaibvDp) {
		this.dmloaibvDp = dmloaibvDp;
	}

	@Column(name = "DMLOAIBV_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmloaibvNgaygiocn() {
		return this.dmloaibvNgaygiocn;
	}

	public void setDmloaibvNgaygiocn(Double dmloaibvNgaygiocn) {
		this.dmloaibvNgaygiocn = dmloaibvNgaygiocn;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmLoaiBenhVien")
	public Set<DmBenhVien> getDmBenhViens() {
		return this.dmBenhViens;
	}

	public void setDmBenhViens(Set<DmBenhVien> dmBenhViens) {
		this.dmBenhViens = dmBenhViens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmLoaiBenhVien")
	public Set<DmBenhVien> getDmBenhViens_1() {
		return this.dmBenhViens_1;
	}

	public void setDmBenhViens_1(Set<DmBenhVien> dmBenhViens_1) {
		this.dmBenhViens_1 = dmBenhViens_1;
	}

}