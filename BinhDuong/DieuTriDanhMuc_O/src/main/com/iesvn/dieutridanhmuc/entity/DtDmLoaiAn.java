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
 * DtDmLoaiAn generated by hbm2java
 */

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DT_DM_LOAI_AN", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DTDMLA_MA"))
public class DtDmLoaiAn implements java.io.Serializable {

	private Integer dtdmlaMaso;
	private String dtdmlaMa;
	private String dtdmlaTen;
	private Double dtdmlaNgaygiocn;
	private Boolean dtdmlaChon;
	private Set<DtDmLoaiAn2> dtDmLoaiAn2s = new HashSet<DtDmLoaiAn2>(0);

	public DtDmLoaiAn() {
	}

	public DtDmLoaiAn(String dtdmlaMa) {
		this.dtdmlaMa = dtdmlaMa;
	}

	public DtDmLoaiAn(String dtdmlaMa, String dtdmlaTen,
			Double dtdmlaNgaygiocn, Boolean dtdmlaChon,
			Set<DtDmLoaiAn2> dtDmLoaiAn2s) {
		this.dtdmlaMa = dtdmlaMa;
		this.dtdmlaTen = dtdmlaTen;
		this.dtdmlaNgaygiocn = dtdmlaNgaygiocn;
		this.dtdmlaChon = dtdmlaChon;
		this.dtDmLoaiAn2s = dtDmLoaiAn2s;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_LOAI_AN")
	@SequenceGenerator(name = "DT_DM_LOAI_AN", sequenceName = "DT_DM_LOAI_AN_DTDMLA_MASO_SEQ", allocationSize = 1)
	@Column(name = "DTDMLA_MASO", unique = true, nullable = false)
	public Integer getDtdmlaMaso() {
		return this.dtdmlaMaso;
	}

	public void setDtdmlaMaso(Integer dtdmlaMaso) {
		this.dtdmlaMaso = dtdmlaMaso;
	}

	@Column(name = "DTDMLA_MA", unique = true, nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getDtdmlaMa() {
		return this.dtdmlaMa;
	}

	public void setDtdmlaMa(String dtdmlaMa) {
		this.dtdmlaMa = dtdmlaMa;
	}

	@Column(name = "DTDMLA_TEN", length = 100)
	@Length(max = 100)
	public String getDtdmlaTen() {
		return this.dtdmlaTen;
	}

	public void setDtdmlaTen(String dtdmlaTen) {
		this.dtdmlaTen = dtdmlaTen;
	}

	@Column(name = "DTDMLA_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDtdmlaNgaygiocn() {
		return this.dtdmlaNgaygiocn;
	}

	public void setDtdmlaNgaygiocn(Double dtdmlaNgaygiocn) {
		this.dtdmlaNgaygiocn = dtdmlaNgaygiocn;
	}

	@Column(name = "DTDMLA_CHON")
	public Boolean getDtdmlaChon() {
		return this.dtdmlaChon;
	}

	public void setDtdmlaChon(Boolean dtdmlaChon) {
		this.dtdmlaChon = dtdmlaChon;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dtDmLoaiAn")
	public Set<DtDmLoaiAn2> getDtDmLoaiAn2s() {
		return this.dtDmLoaiAn2s;
	}

	public void setDtDmLoaiAn2s(Set<DtDmLoaiAn2> dtDmLoaiAn2s) {
		this.dtDmLoaiAn2s = dtDmLoaiAn2s;
	}

}
