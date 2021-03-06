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
 * DmLoaiBenhTruyenNhiem generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_LOAI_BENH_TRUYEN_NHIEM", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMLBTN_MA"))
public class DmLoaiBenhTruyenNhiem implements java.io.Serializable {

	private Integer dmlbtnMaso;
	private String dmlbtnMa;
	private String dmlbtnTen;
	private String dmlbtnGhichu;
	private Double dmlbtnNgaygiocn;
	private Boolean dmlbtnDt;
	private Boolean dmlbtnQl;
	private Boolean dmlbtnDp;
	private Set<DmBenhTruyenNhiem> dmBenhTruyenNhiems = new HashSet<DmBenhTruyenNhiem>(
			0);

	public DmLoaiBenhTruyenNhiem() {
	}

	public DmLoaiBenhTruyenNhiem(String dmlbtnTen) {
		this.dmlbtnTen = dmlbtnTen;
	}

	public DmLoaiBenhTruyenNhiem(String dmlbtnMa, String dmlbtnTen,
			String dmlbtnGhichu, Double dmlbtnNgaygiocn, Boolean dmlbtnDt,
			Boolean dmlbtnQl, Boolean dmlbtnDp,
			Set<DmBenhTruyenNhiem> dmBenhTruyenNhiems) {
		this.dmlbtnMa = dmlbtnMa;
		this.dmlbtnTen = dmlbtnTen;
		this.dmlbtnGhichu = dmlbtnGhichu;
		this.dmlbtnNgaygiocn = dmlbtnNgaygiocn;
		this.dmlbtnDt = dmlbtnDt;
		this.dmlbtnQl = dmlbtnQl;
		this.dmlbtnDp = dmlbtnDp;
		this.dmBenhTruyenNhiems = dmBenhTruyenNhiems;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_BENH_TRUYEN_NHIEM")
	@SequenceGenerator(name = "DM_LOAI_BENH_TRUYEN_NHIEM", sequenceName = "DM_LOAI_BENH_TRUYEN_NHIEM_DMLB", allocationSize = 1)
	@Column(name = "DMLBTN_MASO", unique = true, nullable = false)
	public Integer getDmlbtnMaso() {
		return this.dmlbtnMaso;
	}

	public void setDmlbtnMaso(Integer dmlbtnMaso) {
		this.dmlbtnMaso = dmlbtnMaso;
	}

	@Column(name = "DMLBTN_MA", unique = true, length = 15)
	@Length(max = 15)
	public String getDmlbtnMa() {
		return this.dmlbtnMa;
	}

	public void setDmlbtnMa(String dmlbtnMa) {
		this.dmlbtnMa = dmlbtnMa;
	}

	@Column(name = "DMLBTN_TEN", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	public String getDmlbtnTen() {
		return this.dmlbtnTen;
	}

	public void setDmlbtnTen(String dmlbtnTen) {
		this.dmlbtnTen = dmlbtnTen;
	}

	@Column(name = "DMLBTN_GHICHU", length = 100)
	@Length(max = 100)
	public String getDmlbtnGhichu() {
		return this.dmlbtnGhichu;
	}

	public void setDmlbtnGhichu(String dmlbtnGhichu) {
		this.dmlbtnGhichu = dmlbtnGhichu;
	}

	@Column(name = "DMLBTN_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmlbtnNgaygiocn() {
		return this.dmlbtnNgaygiocn;
	}

	public void setDmlbtnNgaygiocn(Double dmlbtnNgaygiocn) {
		this.dmlbtnNgaygiocn = dmlbtnNgaygiocn;
	}

	@Column(name = "DMLBTN_DT")
	public Boolean getDmlbtnDt() {
		return this.dmlbtnDt;
	}

	public void setDmlbtnDt(Boolean dmlbtnDt) {
		this.dmlbtnDt = dmlbtnDt;
	}

	@Column(name = "DMLBTN_QL")
	public Boolean getDmlbtnQl() {
		return this.dmlbtnQl;
	}

	public void setDmlbtnQl(Boolean dmlbtnQl) {
		this.dmlbtnQl = dmlbtnQl;
	}

	@Column(name = "DMLBTN_DP")
	public Boolean getDmlbtnDp() {
		return this.dmlbtnDp;
	}

	public void setDmlbtnDp(Boolean dmlbtnDp) {
		this.dmlbtnDp = dmlbtnDp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmLoaiBenhTruyenNhiem")
	public Set<DmBenhTruyenNhiem> getDmBenhTruyenNhiems() {
		return this.dmBenhTruyenNhiems;
	}

	public void setDmBenhTruyenNhiems(Set<DmBenhTruyenNhiem> dmBenhTruyenNhiems) {
		this.dmBenhTruyenNhiems = dmBenhTruyenNhiems;
	}

}
