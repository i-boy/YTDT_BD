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
 * DtDmLoaiAn2 generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DT_DM_LOAI_AN_2", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DTDMLA2_MA"))
public class DtDmLoaiAn2 implements java.io.Serializable {

	private Integer dtdmla2Maso;
	private DtDmLoaiAn dtDmLoaiAn;
	private String dtdmla2Ma;
	private String dtdmla2Ten;
	private Double dtdmla2Ngaygiocn;
	private Boolean dtdmla2Chon;

	public DtDmLoaiAn2() {
	}

	public DtDmLoaiAn2(String dtdmla2Ma) {
		this.dtdmla2Ma = dtdmla2Ma;
	}

	public DtDmLoaiAn2(DtDmLoaiAn dtDmLoaiAn, String dtdmla2Ma,
			String dtdmla2Ten, Double dtdmla2Ngaygiocn, Boolean dtdmla2Chon) {
		this.dtDmLoaiAn = dtDmLoaiAn;
		this.dtdmla2Ma = dtdmla2Ma;
		this.dtdmla2Ten = dtdmla2Ten;
		this.dtdmla2Ngaygiocn = dtdmla2Ngaygiocn;
		this.dtdmla2Chon = dtdmla2Chon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_LOAI_AN_2")
	@SequenceGenerator(name = "DT_DM_LOAI_AN_2", sequenceName = "DT_DM_LOAI_AN_2_DTDMLA2_MASO_S", allocationSize = 1)
	@Column(name = "DTDMLA2_MASO", unique = true, nullable = false)
	public Integer getDtdmla2Maso() {
		return this.dtdmla2Maso;
	}

	public void setDtdmla2Maso(Integer dtdmla2Maso) {
		this.dtdmla2Maso = dtdmla2Maso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DTDMLA_MASO")
	public DtDmLoaiAn getDtDmLoaiAn() {
		return this.dtDmLoaiAn;
	}

	public void setDtDmLoaiAn(DtDmLoaiAn dtDmLoaiAn) {
		this.dtDmLoaiAn = dtDmLoaiAn;
	}

	@Column(name = "DTDMLA2_MA", unique = true, nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getDtdmla2Ma() {
		return this.dtdmla2Ma;
	}

	public void setDtdmla2Ma(String dtdmla2Ma) {
		this.dtdmla2Ma = dtdmla2Ma;
	}

	@Column(name = "DTDMLA2_TEN", length = 100)
	@Length(max = 100)
	public String getDtdmla2Ten() {
		return this.dtdmla2Ten;
	}

	public void setDtdmla2Ten(String dtdmla2Ten) {
		this.dtdmla2Ten = dtdmla2Ten;
	}

	@Column(name = "DTDMLA2_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDtdmla2Ngaygiocn() {
		return this.dtdmla2Ngaygiocn;
	}

	public void setDtdmla2Ngaygiocn(Double dtdmla2Ngaygiocn) {
		this.dtdmla2Ngaygiocn = dtdmla2Ngaygiocn;
	}

	@Column(name = "DTDMLA2_CHON")
	public Boolean getDtdmla2Chon() {
		return this.dtdmla2Chon;
	}

	public void setDtdmla2Chon(Boolean dtdmla2Chon) {
		this.dtdmla2Chon = dtdmla2Chon;
	}

}
