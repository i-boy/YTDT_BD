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
 * DmQuy generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_QUY", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMQUY_MA"))
public class DmQuy implements java.io.Serializable {

	private Byte dmquyMaso;
	private String dmquyMa;
	private String dmquyTen;
	private Double dmquyNgaygiocn;
	private Boolean dmquyChon;

	public DmQuy() {
	}

	public DmQuy(String dmquyMa) {
		this.dmquyMa = dmquyMa;
	}

	public DmQuy(String dmquyMa, String dmquyTen, Double dmquyNgaygiocn,
			Boolean dmquyChon) {
		this.dmquyMa = dmquyMa;
		this.dmquyTen = dmquyTen;
		this.dmquyNgaygiocn = dmquyNgaygiocn;
		this.dmquyChon = dmquyChon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_QUY")
	@SequenceGenerator(name = "DM_QUY", sequenceName = "DM_QUY_DMQUY_MASO_SEQ", allocationSize = 1)
	@Column(name = "DMQUY_MASO", unique = true, nullable = false)
	public Byte getDmquyMaso() {
		return this.dmquyMaso;
	}

	public void setDmquyMaso(Byte dmquyMaso) {
		this.dmquyMaso = dmquyMaso;
	}

	@Column(name = "DMQUY_MA", unique = true, nullable = false, length = 4)
	@NotNull
	@Length(max = 4)
	public String getDmquyMa() {
		return this.dmquyMa;
	}

	public void setDmquyMa(String dmquyMa) {
		this.dmquyMa = dmquyMa;
	}

	@Column(name = "DMQUY_TEN", length = 50)
	@Length(max = 50)
	public String getDmquyTen() {
		return this.dmquyTen;
	}

	public void setDmquyTen(String dmquyTen) {
		this.dmquyTen = dmquyTen;
	}

	@Column(name = "DMQUY_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmquyNgaygiocn() {
		return this.dmquyNgaygiocn;
	}

	public void setDmquyNgaygiocn(Double dmquyNgaygiocn) {
		this.dmquyNgaygiocn = dmquyNgaygiocn;
	}

	@Column(name = "DMQUY_CHON")
	public Boolean getDmquyChon() {
		return this.dmquyChon;
	}

	public void setDmquyChon(Boolean dmquyChon) {
		this.dmquyChon = dmquyChon;
	}

}
