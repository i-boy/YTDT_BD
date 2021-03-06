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
 * DtDmGioAn generated by hbm2java
 */

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DT_DM_GIO_AN", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DTDMGA_MA"))
public class DtDmGioAn implements java.io.Serializable {

	private Integer dtdmgaMaso;
	private String dtdmgaMa;
	private String dtdmgaTen;
	private Double dtdmgaNgaygiocn;
	private Boolean dtdmgaChon;

	public DtDmGioAn() {
	}

	public DtDmGioAn(String dtdmgaMa) {
		this.dtdmgaMa = dtdmgaMa;
	}

	public DtDmGioAn(String dtdmgaMa, String dtdmgaTen, Double dtdmgaNgaygiocn,
			Boolean dtdmgaChon) {
		this.dtdmgaMa = dtdmgaMa;
		this.dtdmgaTen = dtdmgaTen;
		this.dtdmgaNgaygiocn = dtdmgaNgaygiocn;
		this.dtdmgaChon = dtdmgaChon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_GIO_AN")
	@SequenceGenerator(name = "DT_DM_GIO_AN", sequenceName = "DT_DM_GIO_AN_DTDMGA_MASO_SEQ", allocationSize = 1)
	@Column(name = "DTDMGA_MASO", unique = true, nullable = false)
	public Integer getDtdmgaMaso() {
		return this.dtdmgaMaso;
	}

	public void setDtdmgaMaso(Integer dtdmgaMaso) {
		this.dtdmgaMaso = dtdmgaMaso;
	}

	@Column(name = "DTDMGA_MA", unique = true, nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getDtdmgaMa() {
		return this.dtdmgaMa;
	}

	public void setDtdmgaMa(String dtdmgaMa) {
		this.dtdmgaMa = dtdmgaMa;
	}

	@Column(name = "DTDMGA_TEN", length = 100)
	@Length(max = 100)
	public String getDtdmgaTen() {
		return this.dtdmgaTen;
	}

	public void setDtdmgaTen(String dtdmgaTen) {
		this.dtdmgaTen = dtdmgaTen;
	}

	@Column(name = "DTDMGA_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDtdmgaNgaygiocn() {
		return this.dtdmgaNgaygiocn;
	}

	public void setDtdmgaNgaygiocn(Double dtdmgaNgaygiocn) {
		this.dtdmgaNgaygiocn = dtdmgaNgaygiocn;
	}

	@Column(name = "DTDMGA_CHON")
	public Boolean getDtdmgaChon() {
		return this.dtdmgaChon;
	}

	public void setDtdmgaChon(Boolean dtdmgaChon) {
		this.dtdmgaChon = dtdmgaChon;
	}

}
