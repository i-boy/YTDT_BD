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
 * DmTonGiao generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_TON_GIAO", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "TONGIAO_MA"))
public class DmTonGiao implements java.io.Serializable {

	private Integer tongiaoMaso;
	private String tongiaoMa;
	private String tongiaoTen;
	private Double tongiaoNgaygiocn;
	private Boolean tongiaoChon;

	public DmTonGiao() {
	}

	public DmTonGiao(String tongiaoMa) {
		this.tongiaoMa = tongiaoMa;
	}

	public DmTonGiao(String tongiaoMa, String tongiaoTen,
			Double tongiaoNgaygiocn, Boolean tongiaoChon) {
		this.tongiaoMa = tongiaoMa;
		this.tongiaoTen = tongiaoTen;
		this.tongiaoNgaygiocn = tongiaoNgaygiocn;
		this.tongiaoChon = tongiaoChon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_TON_GIAO")
	@SequenceGenerator(name = "DM_TON_GIAO", sequenceName = "DM_TON_GIAO_TONGIAO_MASO_SEQ", allocationSize = 1)
	@Column(name = "TONGIAO_MASO", unique = true, nullable = false)
	public Integer getTongiaoMaso() {
		return this.tongiaoMaso;
	}

	public void setTongiaoMaso(Integer tongiaoMaso) {
		this.tongiaoMaso = tongiaoMaso;
	}

	@Column(name = "TONGIAO_MA", unique = true, nullable = false, length = 10)
	@NotNull
	@Length(max = 10)
	public String getTongiaoMa() {
		return this.tongiaoMa;
	}

	public void setTongiaoMa(String tongiaoMa) {
		this.tongiaoMa = tongiaoMa;
	}

	@Column(name = "TONGIAO_TEN", length = 100)
	@Length(max = 100)
	public String getTongiaoTen() {
		return this.tongiaoTen;
	}

	public void setTongiaoTen(String tongiaoTen) {
		this.tongiaoTen = tongiaoTen;
	}

	@Column(name = "TONGIAO_NGAYGIOCN", precision = 22, scale = 0)
	public Double getTongiaoNgaygiocn() {
		return this.tongiaoNgaygiocn;
	}

	public void setTongiaoNgaygiocn(Double tongiaoNgaygiocn) {
		this.tongiaoNgaygiocn = tongiaoNgaygiocn;
	}

	@Column(name = "TONGIAO_CHON")
	public Boolean getTongiaoChon() {
		return this.tongiaoChon;
	}

	public void setTongiaoChon(Boolean tongiaoChon) {
		this.tongiaoChon = tongiaoChon;
	}

}
