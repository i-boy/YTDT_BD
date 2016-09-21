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
 * DtDmKetQua generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DT_DM_KET_QUA", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DTDMKETQUA_MA"))
public class DtDmKetQua implements java.io.Serializable {

	private Integer dtdmketquaMaso;
	private String dtdmketquaMa;
	private String dtdmketquaDiengiai;
	private Double dtdmketquaNgaygiocn;
	private Boolean dtdmketquaChon;

	public DtDmKetQua() {
	}

	public DtDmKetQua(String dtdmketquaMa, String dtdmketquaDiengiai) {
		this.dtdmketquaMa = dtdmketquaMa;
		this.dtdmketquaDiengiai = dtdmketquaDiengiai;
	}

	public DtDmKetQua(String dtdmketquaMa, String dtdmketquaDiengiai,
			Double dtdmketquaNgaygiocn, Boolean dtdmketquaChon) {
		this.dtdmketquaMa = dtdmketquaMa;
		this.dtdmketquaDiengiai = dtdmketquaDiengiai;
		this.dtdmketquaNgaygiocn = dtdmketquaNgaygiocn;
		this.dtdmketquaChon = dtdmketquaChon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_KET_QUA")
	@SequenceGenerator(name = "DT_DM_KET_QUA", sequenceName = "DT_DM_KET_QUA_DTDMKETQUA_MASO_", allocationSize = 1)
	@Column(name = "DTDMKETQUA_MASO", unique = true, nullable = false)
	public Integer getDtdmketquaMaso() {
		return this.dtdmketquaMaso;
	}

	public void setDtdmketquaMaso(Integer dtdmketquaMaso) {
		this.dtdmketquaMaso = dtdmketquaMaso;
	}

	@Column(name = "DTDMKETQUA_MA", unique = true, nullable = false, length = 1)
	@NotNull
	@Length(max = 1)
	public String getDtdmketquaMa() {
		return this.dtdmketquaMa;
	}

	public void setDtdmketquaMa(String dtdmketquaMa) {
		this.dtdmketquaMa = dtdmketquaMa;
	}

	@Column(name = "DTDMKETQUA_DIENGIAI", nullable = false, length = 250)
	@NotNull
	@Length(max = 250)
	public String getDtdmketquaDiengiai() {
		return this.dtdmketquaDiengiai;
	}

	public void setDtdmketquaDiengiai(String dtdmketquaDiengiai) {
		this.dtdmketquaDiengiai = dtdmketquaDiengiai;
	}

	@Column(name = "DTDMKETQUA_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDtdmketquaNgaygiocn() {
		return this.dtdmketquaNgaygiocn;
	}

	public void setDtdmketquaNgaygiocn(Double dtdmketquaNgaygiocn) {
		this.dtdmketquaNgaygiocn = dtdmketquaNgaygiocn;
	}

	@Column(name = "DTDMKETQUA_CHON")
	public Boolean getDtdmketquaChon() {
		return this.dtdmketquaChon;
	}

	public void setDtdmketquaChon(Boolean dtdmketquaChon) {
		this.dtdmketquaChon = dtdmketquaChon;
	}

}
