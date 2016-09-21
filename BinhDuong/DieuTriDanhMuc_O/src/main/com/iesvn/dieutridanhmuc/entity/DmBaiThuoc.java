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
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * DmBaiThuoc generated by hbm2java
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_BAI_THUOC", catalog = "DB_YTDT_BD")
public class DmBaiThuoc implements java.io.Serializable {

	private Integer dmbaithuocMaso;
	private String dmbaithuocMa;
	private String dmbaithuocTen;
	private Double dmbaithuocNgaygiocn;
	private Boolean dmbaithuocDt;
	private Boolean dmbaithuocQl;
	private Boolean dmbaithuocDp;
	private String dmbaithuocGhichu;

	public DmBaiThuoc() {
	}

	public DmBaiThuoc(String dmbaithuocMa, String dmbaithuocTen) {
		this.dmbaithuocMa = dmbaithuocMa;
		this.dmbaithuocTen = dmbaithuocTen;
	}

	public DmBaiThuoc(String dmbaithuocMa, String dmbaithuocTen,
			Double dmbaithuocNgaygiocn, Boolean dmbaithuocDt,
			Boolean dmbaithuocQl, Boolean dmbaithuocDp, String dmbaithuocGhichu) {
		this.dmbaithuocMa = dmbaithuocMa;
		this.dmbaithuocTen = dmbaithuocTen;
		this.dmbaithuocNgaygiocn = dmbaithuocNgaygiocn;
		this.dmbaithuocDt = dmbaithuocDt;
		this.dmbaithuocQl = dmbaithuocQl;
		this.dmbaithuocDp = dmbaithuocDp;
		this.dmbaithuocGhichu = dmbaithuocGhichu;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_BAI_THUOC_DMBAITHUOC_MASO")
    @SequenceGenerator(name = "DM_BAI_THUOC_DMBAITHUOC_MASO", sequenceName = "DM_BAI_THUOC_DMBAITHUOC_MASO_S", allocationSize = 1)
	@Column(name = "DMBAITHUOC_MASO", unique = true, nullable = false)
	public Integer getDmbaithuocMaso() {
		return this.dmbaithuocMaso;
	}

	public void setDmbaithuocMaso(Integer dmbaithuocMaso) {
		this.dmbaithuocMaso = dmbaithuocMaso;
	}

	@Column(name = "DMBAITHUOC_MA", nullable = false, length = 10)
	@NotNull
	@Length(max = 10)
	public String getDmbaithuocMa() {
		return this.dmbaithuocMa;
	}

	public void setDmbaithuocMa(String dmbaithuocMa) {
		this.dmbaithuocMa = dmbaithuocMa;
	}

	@Column(name = "DMBAITHUOC_TEN", nullable = false, length = 36)
	@NotNull
	@Length(max = 36)
	public String getDmbaithuocTen() {
		return this.dmbaithuocTen;
	}

	public void setDmbaithuocTen(String dmbaithuocTen) {
		this.dmbaithuocTen = dmbaithuocTen;
	}

	@Column(name = "DMBAITHUOC_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmbaithuocNgaygiocn() {
		return this.dmbaithuocNgaygiocn;
	}

	public void setDmbaithuocNgaygiocn(Double dmbaithuocNgaygiocn) {
		this.dmbaithuocNgaygiocn = dmbaithuocNgaygiocn;
	}

	@Column(name = "DMBAITHUOC_DT")
	public Boolean getDmbaithuocDt() {
		return this.dmbaithuocDt;
	}

	public void setDmbaithuocDt(Boolean dmbaithuocDt) {
		this.dmbaithuocDt = dmbaithuocDt;
	}

	@Column(name = "DMBAITHUOC_QL")
	public Boolean getDmbaithuocQl() {
		return this.dmbaithuocQl;
	}

	public void setDmbaithuocQl(Boolean dmbaithuocQl) {
		this.dmbaithuocQl = dmbaithuocQl;
	}

	@Column(name = "DMBAITHUOC_DP")
	public Boolean getDmbaithuocDp() {
		return this.dmbaithuocDp;
	}

	public void setDmbaithuocDp(Boolean dmbaithuocDp) {
		this.dmbaithuocDp = dmbaithuocDp;
	}

	@Column(name = "DMBAITHUOC_GHICHU")
	public String getDmbaithuocGhichu() {
		return this.dmbaithuocGhichu;
	}

	public void setDmbaithuocGhichu(String dmbaithuocGhichu) {
		this.dmbaithuocGhichu = dmbaithuocGhichu;
	}

}
