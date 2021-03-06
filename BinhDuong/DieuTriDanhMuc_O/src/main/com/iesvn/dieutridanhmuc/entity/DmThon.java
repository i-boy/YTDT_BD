package com.iesvn.dieutridanhmuc.entity;

// Generated Aug 3, 2011 2:01:45 PM by Hibernate Tools 3.2.4.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.Length;

/**
 * DmThon generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_THON", catalog = "DB_YTDT_BD")
public class DmThon implements java.io.Serializable {

	private Integer dmthonMaso;
	private DmXa dmXa;
	private String dmthonMa;
	private String dmthonTen;
	private Double dmthonNgaygiocn;
	private Boolean dmthonChon;

	public DmThon() {
	}

	public DmThon(DmXa dmXa, String dmthonMa, String dmthonTen,
			Double dmthonNgaygiocn, Boolean dmthonChon) {
		this.dmXa = dmXa;
		this.dmthonMa = dmthonMa;
		this.dmthonTen = dmthonTen;
		this.dmthonNgaygiocn = dmthonNgaygiocn;
		this.dmthonChon = dmthonChon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_THON")
	@SequenceGenerator(name = "DM_THON", sequenceName = "DM_THON_DMTHON_MASO_SEQ", allocationSize = 1)
	@Column(name = "DMTHON_MASO", unique = true, nullable = false)
	public Integer getDmthonMaso() {
		return this.dmthonMaso;
	}

	public void setDmthonMaso(Integer dmthonMaso) {
		this.dmthonMaso = dmthonMaso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DMXA_MASO")
	public DmXa getDmXa() {
		return this.dmXa;
	}

	public void setDmXa(DmXa dmXa) {
		this.dmXa = dmXa;
	}

	@Column(name = "DMTHON_MA", length = 15)
	@Length(max = 15)
	public String getDmthonMa() {
		return this.dmthonMa;
	}

	public void setDmthonMa(String dmthonMa) {
		this.dmthonMa = dmthonMa;
	}

	@Column(name = "DMTHON_TEN", length = 100)
	@Length(max = 100)
	public String getDmthonTen() {
		return this.dmthonTen;
	}

	public void setDmthonTen(String dmthonTen) {
		this.dmthonTen = dmthonTen;
	}

	@Column(name = "DMTHON_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmthonNgaygiocn() {
		return this.dmthonNgaygiocn;
	}

	public void setDmthonNgaygiocn(Double dmthonNgaygiocn) {
		this.dmthonNgaygiocn = dmthonNgaygiocn;
	}

	@Column(name = "DMTHON_CHON")
	public Boolean getDmthonChon() {
		return this.dmthonChon;
	}

	public void setDmthonChon(Boolean dmthonChon) {
		this.dmthonChon = dmthonChon;
	}

}
