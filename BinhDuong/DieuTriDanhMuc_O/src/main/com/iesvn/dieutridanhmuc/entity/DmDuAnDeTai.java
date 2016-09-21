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

/**
 * DmDuAnDeTai generated by hbm2java
 */

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_DU_AN_DE_TAI", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMDUANDETAI_MA"))
public class DmDuAnDeTai implements java.io.Serializable {

	private Integer dmduandetaiMaso;
	private String dmduandetaiMa;
	private String dmduandetaiTen;
	private Double dmduandetaiNgaygiocn;
	private Boolean dmduandetaiDt;
	private Boolean dmduandetaiDp;
	private Boolean dmduandetaiQl;

	public DmDuAnDeTai() {
	}

	public DmDuAnDeTai(String dmduandetaiMa, String dmduandetaiTen,
			Double dmduandetaiNgaygiocn, Boolean dmduandetaiDt,
			Boolean dmduandetaiDp, Boolean dmduandetaiQl) {
		this.dmduandetaiMa = dmduandetaiMa;
		this.dmduandetaiTen = dmduandetaiTen;
		this.dmduandetaiNgaygiocn = dmduandetaiNgaygiocn;
		this.dmduandetaiDt = dmduandetaiDt;
		this.dmduandetaiDp = dmduandetaiDp;
		this.dmduandetaiQl = dmduandetaiQl;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_DU_AN_DE_TAI")
	@SequenceGenerator(name = "DM_DU_AN_DE_TAI", sequenceName = "DM_DU_AN_DE_TAI_DMDUANDETAI_MA", allocationSize = 1)
	@Column(name = "DMDUANDETAI_MASO", unique = true, nullable = false)
	public Integer getDmduandetaiMaso() {
		return this.dmduandetaiMaso;
	}

	public void setDmduandetaiMaso(Integer dmduandetaiMaso) {
		this.dmduandetaiMaso = dmduandetaiMaso;
	}

	@Column(name = "DMDUANDETAI_MA", unique = true, length = 15)
	@Length(max = 15)
	public String getDmduandetaiMa() {
		return this.dmduandetaiMa;
	}

	public void setDmduandetaiMa(String dmduandetaiMa) {
		this.dmduandetaiMa = dmduandetaiMa;
	}

	@Column(name = "DMDUANDETAI_TEN", length = 100)
	@Length(max = 100)
	public String getDmduandetaiTen() {
		return this.dmduandetaiTen;
	}

	public void setDmduandetaiTen(String dmduandetaiTen) {
		this.dmduandetaiTen = dmduandetaiTen;
	}

	@Column(name = "DMDUANDETAI_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmduandetaiNgaygiocn() {
		return this.dmduandetaiNgaygiocn;
	}

	public void setDmduandetaiNgaygiocn(Double dmduandetaiNgaygiocn) {
		this.dmduandetaiNgaygiocn = dmduandetaiNgaygiocn;
	}

	@Column(name = "DMDUANDETAI_DT")
	public Boolean getDmduandetaiDt() {
		return this.dmduandetaiDt;
	}

	public void setDmduandetaiDt(Boolean dmduandetaiDt) {
		this.dmduandetaiDt = dmduandetaiDt;
	}

	@Column(name = "DMDUANDETAI_DP")
	public Boolean getDmduandetaiDp() {
		return this.dmduandetaiDp;
	}

	public void setDmduandetaiDp(Boolean dmduandetaiDp) {
		this.dmduandetaiDp = dmduandetaiDp;
	}

	@Column(name = "DMDUANDETAI_QL")
	public Boolean getDmduandetaiQl() {
		return this.dmduandetaiQl;
	}

	public void setDmduandetaiQl(Boolean dmduandetaiQl) {
		this.dmduandetaiQl = dmduandetaiQl;
	}

}
