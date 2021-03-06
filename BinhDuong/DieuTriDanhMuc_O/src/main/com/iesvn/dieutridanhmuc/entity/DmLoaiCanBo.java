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
 * DmLoaiCanBo generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_LOAI_CAN_BO", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMLOAICANBO_MA"))
public class DmLoaiCanBo implements java.io.Serializable {

	private Integer dmloaicanboMaso;
	private String dmloaicanboMa;
	private String dmloaicanboTen;
	private Double dmloaicanboNgaygiocn;
	private Boolean dmloaicanboDt;
	private Boolean dmloaicanboQl;
	private Boolean dmloaicanboDp;

	public DmLoaiCanBo() {
	}

	public DmLoaiCanBo(String dmloaicanboMa, String dmloaicanboTen,
			Double dmloaicanboNgaygiocn, Boolean dmloaicanboDt,
			Boolean dmloaicanboQl, Boolean dmloaicanboDp) {
		this.dmloaicanboMa = dmloaicanboMa;
		this.dmloaicanboTen = dmloaicanboTen;
		this.dmloaicanboNgaygiocn = dmloaicanboNgaygiocn;
		this.dmloaicanboDt = dmloaicanboDt;
		this.dmloaicanboQl = dmloaicanboQl;
		this.dmloaicanboDp = dmloaicanboDp;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_CAN_BO")
	@SequenceGenerator(name = "DM_LOAI_CAN_BO", sequenceName = "DM_LOAI_CAN_BO_DMLOAICANBO_MAS", allocationSize = 1)
	@Column(name = "DMLOAICANBO_MASO", unique = true, nullable = false)
	public Integer getDmloaicanboMaso() {
		return this.dmloaicanboMaso;
	}

	public void setDmloaicanboMaso(Integer dmloaicanboMaso) {
		this.dmloaicanboMaso = dmloaicanboMaso;
	}

	@Column(name = "DMLOAICANBO_MA", unique = true, length = 10)
	@Length(max = 10)
	public String getDmloaicanboMa() {
		return this.dmloaicanboMa;
	}

	public void setDmloaicanboMa(String dmloaicanboMa) {
		this.dmloaicanboMa = dmloaicanboMa;
	}

	@Column(name = "DMLOAICANBO_TEN", length = 100)
	@Length(max = 100)
	public String getDmloaicanboTen() {
		return this.dmloaicanboTen;
	}

	public void setDmloaicanboTen(String dmloaicanboTen) {
		this.dmloaicanboTen = dmloaicanboTen;
	}

	@Column(name = "DMLOAICANBO_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmloaicanboNgaygiocn() {
		return this.dmloaicanboNgaygiocn;
	}

	public void setDmloaicanboNgaygiocn(Double dmloaicanboNgaygiocn) {
		this.dmloaicanboNgaygiocn = dmloaicanboNgaygiocn;
	}

	@Column(name = "DMLOAICANBO_DT")
	public Boolean getDmloaicanboDt() {
		return this.dmloaicanboDt;
	}

	public void setDmloaicanboDt(Boolean dmloaicanboDt) {
		this.dmloaicanboDt = dmloaicanboDt;
	}

	@Column(name = "DMLOAICANBO_QL")
	public Boolean getDmloaicanboQl() {
		return this.dmloaicanboQl;
	}

	public void setDmloaicanboQl(Boolean dmloaicanboQl) {
		this.dmloaicanboQl = dmloaicanboQl;
	}

	@Column(name = "DMLOAICANBO_DP")
	public Boolean getDmloaicanboDp() {
		return this.dmloaicanboDp;
	}

	public void setDmloaicanboDp(Boolean dmloaicanboDp) {
		this.dmloaicanboDp = dmloaicanboDp;
	}

}
