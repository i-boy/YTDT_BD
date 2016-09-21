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
 * DtDmChiDan generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DT_DM_CHI_DAN", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DTDMCHIDAN_MA"))
public class DtDmChiDan implements java.io.Serializable {

	private Integer dtdmchidanMaso;
	private String dtdmchidanMa;
	private String dtdmchidanMaphu;
	private String dtdmchidanTen;
	private Double dtdmchidanNgaygiocn;
	private Boolean dtdmchidanChon;
	private Set<DtDmChiDanDvt> dtDmChiDanDvts = new HashSet<DtDmChiDanDvt>(0);
	private Set<DtDmChiDanDvt> dtDmChiDanDvts_1 = new HashSet<DtDmChiDanDvt>(0);

	public DtDmChiDan() {
	}

	public DtDmChiDan(String dtdmchidanMa) {
		this.dtdmchidanMa = dtdmchidanMa;
	}

	public DtDmChiDan(String dtdmchidanMa, String dtdmchidanMaphu,
			String dtdmchidanTen, Double dtdmchidanNgaygiocn,
			Boolean dtdmchidanChon, Set<DtDmChiDanDvt> dtDmChiDanDvts,
			Set<DtDmChiDanDvt> dtDmChiDanDvts_1) {
		this.dtdmchidanMa = dtdmchidanMa;
		this.dtdmchidanMaphu = dtdmchidanMaphu;
		this.dtdmchidanTen = dtdmchidanTen;
		this.dtdmchidanNgaygiocn = dtdmchidanNgaygiocn;
		this.dtdmchidanChon = dtdmchidanChon;
		this.dtDmChiDanDvts = dtDmChiDanDvts;
		this.dtDmChiDanDvts_1 = dtDmChiDanDvts_1;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_CHI_DAN")
	@SequenceGenerator(name = "DT_DM_CHI_DAN", sequenceName = "DT_DM_CHI_DAN_DTDMCHIDAN_MASO_", allocationSize = 1)
	@Column(name = "DTDMCHIDAN_MASO", unique = true, nullable = false)
	public Integer getDtdmchidanMaso() {
		return this.dtdmchidanMaso;
	}

	public void setDtdmchidanMaso(Integer dtdmchidanMaso) {
		this.dtdmchidanMaso = dtdmchidanMaso;
	}

	@Column(name = "DTDMCHIDAN_MA", unique = true, nullable = false, length = 4)
	@NotNull
	@Length(max = 4)
	public String getDtdmchidanMa() {
		return this.dtdmchidanMa;
	}

	public void setDtdmchidanMa(String dtdmchidanMa) {
		this.dtdmchidanMa = dtdmchidanMa;
	}

	@Column(name = "DTDMCHIDAN_MAPHU", length = 12)
	@Length(max = 12)
	public String getDtdmchidanMaphu() {
		return this.dtdmchidanMaphu;
	}

	public void setDtdmchidanMaphu(String dtdmchidanMaphu) {
		this.dtdmchidanMaphu = dtdmchidanMaphu;
	}

	@Column(name = "DTDMCHIDAN_TEN", length = 250)
	@Length(max = 250)
	public String getDtdmchidanTen() {
		return this.dtdmchidanTen;
	}

	public void setDtdmchidanTen(String dtdmchidanTen) {
		this.dtdmchidanTen = dtdmchidanTen;
	}

	@Column(name = "DTDMCHIDAN_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDtdmchidanNgaygiocn() {
		return this.dtdmchidanNgaygiocn;
	}

	public void setDtdmchidanNgaygiocn(Double dtdmchidanNgaygiocn) {
		this.dtdmchidanNgaygiocn = dtdmchidanNgaygiocn;
	}

	@Column(name = "DTDMCHIDAN_CHON")
	public Boolean getDtdmchidanChon() {
		return this.dtdmchidanChon;
	}

	public void setDtdmchidanChon(Boolean dtdmchidanChon) {
		this.dtdmchidanChon = dtdmchidanChon;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dtDmChiDan")
	public Set<DtDmChiDanDvt> getDtDmChiDanDvts() {
		return this.dtDmChiDanDvts;
	}

	public void setDtDmChiDanDvts(Set<DtDmChiDanDvt> dtDmChiDanDvts) {
		this.dtDmChiDanDvts = dtDmChiDanDvts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dtDmChiDan")
	public Set<DtDmChiDanDvt> getDtDmChiDanDvts_1() {
		return this.dtDmChiDanDvts_1;
	}

	public void setDtDmChiDanDvts_1(Set<DtDmChiDanDvt> dtDmChiDanDvts_1) {
		this.dtDmChiDanDvts_1 = dtDmChiDanDvts_1;
	}

}
