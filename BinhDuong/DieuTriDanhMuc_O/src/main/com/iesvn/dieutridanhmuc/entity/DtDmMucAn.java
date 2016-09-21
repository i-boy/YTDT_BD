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
 * DtDmMucAn generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DT_DM_MUC_AN", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DTDMMA_MA"))
public class DtDmMucAn implements java.io.Serializable {

	private Integer dtdmmaMaso;
	private String dtdmmaMa;
	private String dtdmmaTen;
	private Double dtdmmaNgaygiocn;
	private Boolean dtdmmaChon;

	public DtDmMucAn() {
	}

	public DtDmMucAn(String dtdmmaMa) {
		this.dtdmmaMa = dtdmmaMa;
	}

	public DtDmMucAn(String dtdmmaMa, String dtdmmaTen, Double dtdmmaNgaygiocn,
			Boolean dtdmmaChon) {
		this.dtdmmaMa = dtdmmaMa;
		this.dtdmmaTen = dtdmmaTen;
		this.dtdmmaNgaygiocn = dtdmmaNgaygiocn;
		this.dtdmmaChon = dtdmmaChon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_MUC_AN")
	@SequenceGenerator(name = "DT_DM_MUC_AN", sequenceName = "DT_DM_MUC_AN_DTDMMA_MASO_SEQ", allocationSize = 1)
	@Column(name = "DTDMMA_MASO", unique = true, nullable = false)
	public Integer getDtdmmaMaso() {
		return this.dtdmmaMaso;
	}

	public void setDtdmmaMaso(Integer dtdmmaMaso) {
		this.dtdmmaMaso = dtdmmaMaso;
	}

	@Column(name = "DTDMMA_MA", unique = true, nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getDtdmmaMa() {
		return this.dtdmmaMa;
	}

	public void setDtdmmaMa(String dtdmmaMa) {
		this.dtdmmaMa = dtdmmaMa;
	}

	@Column(name = "DTDMMA_TEN", length = 100)
	@Length(max = 100)
	public String getDtdmmaTen() {
		return this.dtdmmaTen;
	}

	public void setDtdmmaTen(String dtdmmaTen) {
		this.dtdmmaTen = dtdmmaTen;
	}

	@Column(name = "DTDMMA_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDtdmmaNgaygiocn() {
		return this.dtdmmaNgaygiocn;
	}

	public void setDtdmmaNgaygiocn(Double dtdmmaNgaygiocn) {
		this.dtdmmaNgaygiocn = dtdmmaNgaygiocn;
	}

	@Column(name = "DTDMMA_CHON")
	public Boolean getDtdmmaChon() {
		return this.dtdmmaChon;
	}

	public void setDtdmmaChon(Boolean dtdmmaChon) {
		this.dtdmmaChon = dtdmmaChon;
	}

}