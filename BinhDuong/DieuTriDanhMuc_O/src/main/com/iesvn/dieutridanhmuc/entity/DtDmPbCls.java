package com.iesvn.dieutridanhmuc.entity;

// Generated Aug 3, 2011 2:01:45 PM by Hibernate Tools 3.2.4.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * DtDmPbCls generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DT_DM_PB_CLS", catalog = "DB_YTDT_BD")
public class DtDmPbCls implements java.io.Serializable {

	private int dtdmpbclsMaso;
	private String dtdmpbclsMa;
	private String dtdmpbclsTen;
	private Double dtdmpbclsNgaygiocn;
	private Boolean dtdmpbclsChon;
	private Set<DtDmCls> dtDmClses = new HashSet<DtDmCls>(0);
	private Set<DtDmCls> dtDmClses_1 = new HashSet<DtDmCls>(0);

	public DtDmPbCls() {
	}

	public DtDmPbCls(int dtdmpbclsMaso, String dtdmpbclsMa, String dtdmpbclsTen) {
		this.dtdmpbclsMaso = dtdmpbclsMaso;
		this.dtdmpbclsMa = dtdmpbclsMa;
		this.dtdmpbclsTen = dtdmpbclsTen;
	}

	public DtDmPbCls(int dtdmpbclsMaso, String dtdmpbclsMa,
			String dtdmpbclsTen, Double dtdmpbclsNgaygiocn,
			Boolean dtdmpbclsChon, Set<DtDmCls> dtDmClses,
			Set<DtDmCls> dtDmClses_1) {
		this.dtdmpbclsMaso = dtdmpbclsMaso;
		this.dtdmpbclsMa = dtdmpbclsMa;
		this.dtdmpbclsTen = dtdmpbclsTen;
		this.dtdmpbclsNgaygiocn = dtdmpbclsNgaygiocn;
		this.dtdmpbclsChon = dtdmpbclsChon;
		this.dtDmClses = dtDmClses;
		this.dtDmClses_1 = dtDmClses_1;
	}

	@Id
	@Column(name = "DTDMPBCLS_MASO", unique = true, nullable = false)
	@NotNull
	public int getDtdmpbclsMaso() {
		return this.dtdmpbclsMaso;
	}

	public void setDtdmpbclsMaso(int dtdmpbclsMaso) {
		this.dtdmpbclsMaso = dtdmpbclsMaso;
	}

	@Column(name = "DTDMPBCLS_MA", nullable = false, length = 3)
	@NotNull
	@Length(max = 3)
	public String getDtdmpbclsMa() {
		return this.dtdmpbclsMa;
	}

	public void setDtdmpbclsMa(String dtdmpbclsMa) {
		this.dtdmpbclsMa = dtdmpbclsMa;
	}

	@Column(name = "DTDMPBCLS_TEN", nullable = false, length = 250)
	@NotNull
	@Length(max = 250)
	public String getDtdmpbclsTen() {
		return this.dtdmpbclsTen;
	}

	public void setDtdmpbclsTen(String dtdmpbclsTen) {
		this.dtdmpbclsTen = dtdmpbclsTen;
	}

	@Column(name = "DTDMPBCLS_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDtdmpbclsNgaygiocn() {
		return this.dtdmpbclsNgaygiocn;
	}

	public void setDtdmpbclsNgaygiocn(Double dtdmpbclsNgaygiocn) {
		this.dtdmpbclsNgaygiocn = dtdmpbclsNgaygiocn;
	}

	@Column(name = "DTDMPBCLS_CHON")
	public Boolean getDtdmpbclsChon() {
		return this.dtdmpbclsChon;
	}

	public void setDtdmpbclsChon(Boolean dtdmpbclsChon) {
		this.dtdmpbclsChon = dtdmpbclsChon;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dtDmPbCls")
	public Set<DtDmCls> getDtDmClses() {
		return this.dtDmClses;
	}

	public void setDtDmClses(Set<DtDmCls> dtDmClses) {
		this.dtDmClses = dtDmClses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dtDmPbCls")
	public Set<DtDmCls> getDtDmClses_1() {
		return this.dtDmClses_1;
	}

	public void setDtDmClses_1(Set<DtDmCls> dtDmClses_1) {
		this.dtDmClses_1 = dtDmClses_1;
	}

}
