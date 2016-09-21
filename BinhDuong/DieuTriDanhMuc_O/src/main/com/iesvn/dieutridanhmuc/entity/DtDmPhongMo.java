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
 * DtDmPhongMo generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DT_DM_PHONG_MO", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DTDMPHONGMO_MA"))
public class DtDmPhongMo implements java.io.Serializable {

	private Integer dtdmphongmoMaso;
	private DmKhoa dmKhoa;
	private String dtdmphongmoMa;
	private String dtdmphongmoTenphong;
	private String dtdmphongmoGhichu;
	private Double dtdmphongmoNgaygiocn;
	private Boolean dtdmphongmoChon;

	public DtDmPhongMo() {
	}

	public DtDmPhongMo(String dtdmphongmoMa, String dtdmphongmoTenphong) {
		this.dtdmphongmoMa = dtdmphongmoMa;
		this.dtdmphongmoTenphong = dtdmphongmoTenphong;
	}

	public DtDmPhongMo(DmKhoa dmKhoa, String dtdmphongmoMa,
			String dtdmphongmoTenphong, String dtdmphongmoGhichu,
			Double dtdmphongmoNgaygiocn, Boolean dtdmphongmoChon) {
		this.dmKhoa = dmKhoa;
		this.dtdmphongmoMa = dtdmphongmoMa;
		this.dtdmphongmoTenphong = dtdmphongmoTenphong;
		this.dtdmphongmoGhichu = dtdmphongmoGhichu;
		this.dtdmphongmoNgaygiocn = dtdmphongmoNgaygiocn;
		this.dtdmphongmoChon = dtdmphongmoChon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_PHONG_MO")
	@SequenceGenerator(name = "DT_DM_PHONG_MO", sequenceName = "DT_DM_PHONG_MO_DTDMPHONGMO_MAS", allocationSize = 1)
	@Column(name = "DTDMPHONGMO_MASO", unique = true, nullable = false)
	public Integer getDtdmphongmoMaso() {
		return this.dtdmphongmoMaso;
	}

	public void setDtdmphongmoMaso(Integer dtdmphongmoMaso) {
		this.dtdmphongmoMaso = dtdmphongmoMaso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "KHOA_LIENHE")
	public DmKhoa getDmKhoa() {
		return this.dmKhoa;
	}

	public void setDmKhoa(DmKhoa dmKhoa) {
		this.dmKhoa = dmKhoa;
	}

	@Column(name = "DTDMPHONGMO_MA", unique = true, nullable = false, length = 10)
	@NotNull
	@Length(max = 10)
	public String getDtdmphongmoMa() {
		return this.dtdmphongmoMa;
	}

	public void setDtdmphongmoMa(String dtdmphongmoMa) {
		this.dtdmphongmoMa = dtdmphongmoMa;
	}

	@Column(name = "DTDMPHONGMO_TENPHONG", nullable = false, length = 250)
	@NotNull
	@Length(max = 250)
	public String getDtdmphongmoTenphong() {
		return this.dtdmphongmoTenphong;
	}

	public void setDtdmphongmoTenphong(String dtdmphongmoTenphong) {
		this.dtdmphongmoTenphong = dtdmphongmoTenphong;
	}

	@Column(name = "DTDMPHONGMO_GHICHU", length = 250)
	@Length(max = 250)
	public String getDtdmphongmoGhichu() {
		return this.dtdmphongmoGhichu;
	}

	public void setDtdmphongmoGhichu(String dtdmphongmoGhichu) {
		this.dtdmphongmoGhichu = dtdmphongmoGhichu;
	}

	@Column(name = "DTDMPHONGMO_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDtdmphongmoNgaygiocn() {
		return this.dtdmphongmoNgaygiocn;
	}

	public void setDtdmphongmoNgaygiocn(Double dtdmphongmoNgaygiocn) {
		this.dtdmphongmoNgaygiocn = dtdmphongmoNgaygiocn;
	}

	@Column(name = "DTDMPHONGMO_CHON")
	public Boolean getDtdmphongmoChon() {
		return this.dtdmphongmoChon;
	}

	public void setDtdmphongmoChon(Boolean dtdmphongmoChon) {
		this.dtdmphongmoChon = dtdmphongmoChon;
	}

}
