package com.iesvn.dieutridanhmuc.entity;

// Generated Aug 3, 2011 2:01:45 PM by Hibernate Tools 3.2.4.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * DmDiaDiem generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_DIA_DIEM", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMDIADIEM_MA"))
public class DmDiaDiem implements java.io.Serializable {

	private Integer dmdiadiemMaso;
	private String dmdiadiemMa;
	private String dmdiadiemTen;
	private Double dmdiadiemNgaygiocn;
	private Boolean dmdiadiemQl;
	private Boolean dmdiadiemDt;
	private Boolean dmdiadiemDp;

	public DmDiaDiem() {
	}

	public DmDiaDiem(String dmdiadiemMa, String dmdiadiemTen) {
		this.dmdiadiemMa = dmdiadiemMa;
		this.dmdiadiemTen = dmdiadiemTen;
	}

	public DmDiaDiem(String dmdiadiemMa, String dmdiadiemTen,
			Double dmdiadiemNgaygiocn, Boolean dmdiadiemQl,
			Boolean dmdiadiemDt, Boolean dmdiadiemDp) {
		this.dmdiadiemMa = dmdiadiemMa;
		this.dmdiadiemTen = dmdiadiemTen;
		this.dmdiadiemNgaygiocn = dmdiadiemNgaygiocn;
		this.dmdiadiemQl = dmdiadiemQl;
		this.dmdiadiemDt = dmdiadiemDt;
		this.dmdiadiemDp = dmdiadiemDp;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_DIA_DIEM")
	@SequenceGenerator(name = "DM_DIA_DIEM", sequenceName = "DM_DIA_DIEM_DMDIADIEM_MASO_SEQ", allocationSize = 1)
	@Column(name = "DMDIADIEM_MASO", unique = true, nullable = false)
	public Integer getDmdiadiemMaso() {
		return this.dmdiadiemMaso;
	}

	public void setDmdiadiemMaso(Integer dmdiadiemMaso) {
		this.dmdiadiemMaso = dmdiadiemMaso;
	}

	@Column(name = "DMDIADIEM_MA", unique = true, nullable = false, length = 5)
	@NotNull
	@Length(max = 5)
	public String getDmdiadiemMa() {
		return this.dmdiadiemMa;
	}

	public void setDmdiadiemMa(String dmdiadiemMa) {
		this.dmdiadiemMa = dmdiadiemMa;
	}

	@Column(name = "DMDIADIEM_TEN", nullable = false, length = 250)
	@NotNull
	@Length(max = 250)
	public String getDmdiadiemTen() {
		return this.dmdiadiemTen;
	}

	public void setDmdiadiemTen(String dmdiadiemTen) {
		this.dmdiadiemTen = dmdiadiemTen;
	}

	@Column(name = "DMDIADIEM_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmdiadiemNgaygiocn() {
		return this.dmdiadiemNgaygiocn;
	}

	public void setDmdiadiemNgaygiocn(Double dmdiadiemNgaygiocn) {
		this.dmdiadiemNgaygiocn = dmdiadiemNgaygiocn;
	}

	@Column(name = "DMDIADIEM_QL")
	public Boolean getDmdiadiemQl() {
		return this.dmdiadiemQl;
	}

	public void setDmdiadiemQl(Boolean dmdiadiemQl) {
		this.dmdiadiemQl = dmdiadiemQl;
	}

	@Column(name = "DMDIADIEM_DT")
	public Boolean getDmdiadiemDt() {
		return this.dmdiadiemDt;
	}

	public void setDmdiadiemDt(Boolean dmdiadiemDt) {
		this.dmdiadiemDt = dmdiadiemDt;
	}

	@Column(name = "DMDIADIEM_DP")
	public Boolean getDmdiadiemDp() {
		return this.dmdiadiemDp;
	}

	public void setDmdiadiemDp(Boolean dmdiadiemDp) {
		this.dmdiadiemDp = dmdiadiemDp;
	}

}