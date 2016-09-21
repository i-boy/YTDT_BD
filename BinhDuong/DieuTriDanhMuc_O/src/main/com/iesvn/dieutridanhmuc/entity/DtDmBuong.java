package com.iesvn.dieutridanhmuc.entity;

// Generated Aug 3, 2011 2:01:45 PM by Hibernate Tools 3.2.4.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * DtDmBuong generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DT_DM_BUONG", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DTDMB_MA"))
public class DtDmBuong implements java.io.Serializable {

	private Integer dtdmbMaso;
	private DmKhoa dmKhoa;
	private String dtdmbMa;
	private String dtdmbTen;
	private Double dtdmbNgaygiocn;
	private Boolean dtdmbChon;

	public DtDmBuong() {
	}

	public DtDmBuong(String dtdmbMa) {
		this.dtdmbMa = dtdmbMa;
	}

	public DtDmBuong(DmKhoa dmKhoa, String dtdmbMa, String dtdmbTen,
			Double dtdmbNgaygiocn, Boolean dtdmbChon) {
		this.dmKhoa = dmKhoa;
		this.dtdmbMa = dtdmbMa;
		this.dtdmbTen = dtdmbTen;
		this.dtdmbNgaygiocn = dtdmbNgaygiocn;
		this.dtdmbChon = dtdmbChon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_BUONG")
	@SequenceGenerator(name = "DT_DM_BUONG", sequenceName = "DT_DM_BUONG_DTDMB_MASO_SEQ", allocationSize = 1)
	@Column(name = "DTDMB_MASO", unique = true, nullable = false)
	public Integer getDtdmbMaso() {
		return this.dtdmbMaso;
	}

	public void setDtdmbMaso(Integer dtdmbMaso) {
		this.dtdmbMaso = dtdmbMaso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DMKHOA_MASO")
	public DmKhoa getDmKhoa() {
		return this.dmKhoa;
	}

	public void setDmKhoa(DmKhoa dmKhoa) {
		this.dmKhoa = dmKhoa;
	}

	@Column(name = "DTDMB_MA", unique = true, nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getDtdmbMa() {
		return this.dtdmbMa;
	}

	public void setDtdmbMa(String dtdmbMa) {
		this.dtdmbMa = dtdmbMa;
	}

	@Column(name = "DTDMB_TEN", length = 100)
	@Length(max = 100)
	public String getDtdmbTen() {
		return this.dtdmbTen;
	}

	public void setDtdmbTen(String dtdmbTen) {
		this.dtdmbTen = dtdmbTen;
	}

	@Column(name = "DTDMB_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDtdmbNgaygiocn() {
		return this.dtdmbNgaygiocn;
	}

	public void setDtdmbNgaygiocn(Double dtdmbNgaygiocn) {
		this.dtdmbNgaygiocn = dtdmbNgaygiocn;
	}

	@Column(name = "DTDMB_CHON")
	public Boolean getDtdmbChon() {
		return this.dtdmbChon;
	}

	public void setDtdmbChon(Boolean dtdmbChon) {
		this.dtdmbChon = dtdmbChon;
	}

}
