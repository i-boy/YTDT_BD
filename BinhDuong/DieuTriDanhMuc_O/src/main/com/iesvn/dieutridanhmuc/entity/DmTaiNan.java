package com.iesvn.dieutridanhmuc.entity;

// Generated Aug 3, 2011 2:01:45 PM by Hibernate Tools 3.2.4.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * DmTaiNan generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_TAI_NAN", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMTAINAN_MA"))
public class DmTaiNan implements java.io.Serializable {

	private Integer dmtainanMaso;
	private String dmtainanMa;
	private String dmtainanTen;
	private Double dmtainanNgaygiocn;
	private Boolean dmtainanDt;
	private Boolean dmtainanQl;
	private Boolean dmtainanDp;
	private Set<DmPhanLoaiTaiNan> dmPhanLoaiTaiNans = new HashSet<DmPhanLoaiTaiNan>(
			0);
	private Set<DmPhuongThucGayTaiNan> dmPhuongThucGayTaiNans = new HashSet<DmPhuongThucGayTaiNan>(
			0);
	private Set<DmPhanLoaiTaiNan> dmPhanLoaiTaiNans_1 = new HashSet<DmPhanLoaiTaiNan>(
			0);

	public DmTaiNan() {
	}

	public DmTaiNan(String dmtainanMa, String dmtainanTen) {
		this.dmtainanMa = dmtainanMa;
		this.dmtainanTen = dmtainanTen;
	}

	public DmTaiNan(String dmtainanMa, String dmtainanTen,
			Double dmtainanNgaygiocn, Boolean dmtainanDt, Boolean dmtainanQl,
			Boolean dmtainanDp, Set<DmPhanLoaiTaiNan> dmPhanLoaiTaiNans,
			Set<DmPhuongThucGayTaiNan> dmPhuongThucGayTaiNans,
			Set<DmPhanLoaiTaiNan> dmPhanLoaiTaiNans_1) {
		this.dmtainanMa = dmtainanMa;
		this.dmtainanTen = dmtainanTen;
		this.dmtainanNgaygiocn = dmtainanNgaygiocn;
		this.dmtainanDt = dmtainanDt;
		this.dmtainanQl = dmtainanQl;
		this.dmtainanDp = dmtainanDp;
		this.dmPhanLoaiTaiNans = dmPhanLoaiTaiNans;
		this.dmPhuongThucGayTaiNans = dmPhuongThucGayTaiNans;
		this.dmPhanLoaiTaiNans_1 = dmPhanLoaiTaiNans_1;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_TAI_NAN")
	@SequenceGenerator(name = "DM_TAI_NAN", sequenceName = "DM_TAI_NAN_DMTAINAN_MASO_SEQ", allocationSize = 1)
	@Column(name = "DMTAINAN_MASO", unique = true, nullable = false)
	public Integer getDmtainanMaso() {
		return this.dmtainanMaso;
	}

	public void setDmtainanMaso(Integer dmtainanMaso) {
		this.dmtainanMaso = dmtainanMaso;
	}

	@Column(name = "DMTAINAN_MA", unique = true, nullable = false, length = 15)
	@NotNull
	@Length(max = 15)
	public String getDmtainanMa() {
		return this.dmtainanMa;
	}

	public void setDmtainanMa(String dmtainanMa) {
		this.dmtainanMa = dmtainanMa;
	}

	@Column(name = "DMTAINAN_TEN", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	public String getDmtainanTen() {
		return this.dmtainanTen;
	}

	public void setDmtainanTen(String dmtainanTen) {
		this.dmtainanTen = dmtainanTen;
	}

	@Column(name = "DMTAINAN_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmtainanNgaygiocn() {
		return this.dmtainanNgaygiocn;
	}

	public void setDmtainanNgaygiocn(Double dmtainanNgaygiocn) {
		this.dmtainanNgaygiocn = dmtainanNgaygiocn;
	}

	@Column(name = "DMTAINAN_DT")
	public Boolean getDmtainanDt() {
		return this.dmtainanDt;
	}

	public void setDmtainanDt(Boolean dmtainanDt) {
		this.dmtainanDt = dmtainanDt;
	}

	@Column(name = "DMTAINAN_QL")
	public Boolean getDmtainanQl() {
		return this.dmtainanQl;
	}

	public void setDmtainanQl(Boolean dmtainanQl) {
		this.dmtainanQl = dmtainanQl;
	}

	@Column(name = "DMTAINAN_DP")
	public Boolean getDmtainanDp() {
		return this.dmtainanDp;
	}

	public void setDmtainanDp(Boolean dmtainanDp) {
		this.dmtainanDp = dmtainanDp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmTaiNan")
	public Set<DmPhanLoaiTaiNan> getDmPhanLoaiTaiNans() {
		return this.dmPhanLoaiTaiNans;
	}

	public void setDmPhanLoaiTaiNans(Set<DmPhanLoaiTaiNan> dmPhanLoaiTaiNans) {
		this.dmPhanLoaiTaiNans = dmPhanLoaiTaiNans;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmTaiNan")
	public Set<DmPhuongThucGayTaiNan> getDmPhuongThucGayTaiNans() {
		return this.dmPhuongThucGayTaiNans;
	}

	public void setDmPhuongThucGayTaiNans(
			Set<DmPhuongThucGayTaiNan> dmPhuongThucGayTaiNans) {
		this.dmPhuongThucGayTaiNans = dmPhuongThucGayTaiNans;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmTaiNan")
	public Set<DmPhanLoaiTaiNan> getDmPhanLoaiTaiNans_1() {
		return this.dmPhanLoaiTaiNans_1;
	}

	public void setDmPhanLoaiTaiNans_1(Set<DmPhanLoaiTaiNan> dmPhanLoaiTaiNans_1) {
		this.dmPhanLoaiTaiNans_1 = dmPhanLoaiTaiNans_1;
	}

}
