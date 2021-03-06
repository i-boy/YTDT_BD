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
 * DmHocHam generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_HOC_HAM", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMHOCHAM_MA"))
public class DmHocHam implements java.io.Serializable {

	private Integer dmhochamMaso;
	private String dmhochamMa;
	private String dmhochamTen;
	private Double dmhochamNgaygiocn;
	private Boolean dmhochamQl;
	private Boolean dmhochamDt;
	private Boolean dmhochamDp;

	public DmHocHam() {
	}

	public DmHocHam(String dmhochamTen) {
		this.dmhochamTen = dmhochamTen;
	}

	public DmHocHam(String dmhochamMa, String dmhochamTen,
			Double dmhochamNgaygiocn, Boolean dmhochamQl, Boolean dmhochamDt,
			Boolean dmhochamDp) {
		this.dmhochamMa = dmhochamMa;
		this.dmhochamTen = dmhochamTen;
		this.dmhochamNgaygiocn = dmhochamNgaygiocn;
		this.dmhochamQl = dmhochamQl;
		this.dmhochamDt = dmhochamDt;
		this.dmhochamDp = dmhochamDp;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_HOC_HAM")
	@SequenceGenerator(name = "DM_HOC_HAM", sequenceName = "DM_HOC_HAM_DMHOCHAM_MASO_SEQ", allocationSize = 1)
	@Column(name = "DMHOCHAM_MASO", unique = true, nullable = false)
	public Integer getDmhochamMaso() {
		return this.dmhochamMaso;
	}

	public void setDmhochamMaso(Integer dmhochamMaso) {
		this.dmhochamMaso = dmhochamMaso;
	}

	@Column(name = "DMHOCHAM_MA", unique = true, length = 15)
	@Length(max = 15)
	public String getDmhochamMa() {
		return this.dmhochamMa;
	}

	public void setDmhochamMa(String dmhochamMa) {
		this.dmhochamMa = dmhochamMa;
	}

	@Column(name = "DMHOCHAM_TEN", nullable = false, length = 150)
	@NotNull
	@Length(max = 150)
	public String getDmhochamTen() {
		return this.dmhochamTen;
	}

	public void setDmhochamTen(String dmhochamTen) {
		this.dmhochamTen = dmhochamTen;
	}

	@Column(name = "DMHOCHAM_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmhochamNgaygiocn() {
		return this.dmhochamNgaygiocn;
	}

	public void setDmhochamNgaygiocn(Double dmhochamNgaygiocn) {
		this.dmhochamNgaygiocn = dmhochamNgaygiocn;
	}

	@Column(name = "DMHOCHAM_QL")
	public Boolean getDmhochamQl() {
		return this.dmhochamQl;
	}

	public void setDmhochamQl(Boolean dmhochamQl) {
		this.dmhochamQl = dmhochamQl;
	}

	@Column(name = "DMHOCHAM_DT")
	public Boolean getDmhochamDt() {
		return this.dmhochamDt;
	}

	public void setDmhochamDt(Boolean dmhochamDt) {
		this.dmhochamDt = dmhochamDt;
	}

	@Column(name = "DMHOCHAM_DP")
	public Boolean getDmhochamDp() {
		return this.dmhochamDp;
	}

	public void setDmhochamDp(Boolean dmhochamDp) {
		this.dmhochamDp = dmhochamDp;
	}

}
