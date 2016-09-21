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
 * NguoiDung generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "NGUOI_DUNG", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "ND_TENDANGNHAP"))
public class NguoiDung implements java.io.Serializable {

	private Integer ndMaso;
	private String ndTendangnhap;
	private String ndMadangnhap;
	private Set<DtDmNhanVien> dtDmNhanViens = new HashSet<DtDmNhanVien>(0);
	private Set<NguoiDungVaiTro> nguoiDungVaiTros = new HashSet<NguoiDungVaiTro>(
			0);

	public NguoiDung() {
	}

	public NguoiDung(String ndTendangnhap) {
		this.ndTendangnhap = ndTendangnhap;
	}

	public NguoiDung(String ndTendangnhap, String ndMadangnhap,
			Set<DtDmNhanVien> dtDmNhanViens,
			Set<NguoiDungVaiTro> nguoiDungVaiTros) {
		this.ndTendangnhap = ndTendangnhap;
		this.ndMadangnhap = ndMadangnhap;
		this.dtDmNhanViens = dtDmNhanViens;
		this.nguoiDungVaiTros = nguoiDungVaiTros;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NGUOI_DUNG")
	@SequenceGenerator(name = "NGUOI_DUNG", sequenceName = "NGUOI_DUNG_ND_MASO_SEQ", allocationSize = 1)
	@Column(name = "ND_MASO", unique = true, nullable = false)
	public Integer getNdMaso() {
		return this.ndMaso;
	}

	public void setNdMaso(Integer ndMaso) {
		this.ndMaso = ndMaso;
	}

	@Column(name = "ND_TENDANGNHAP", unique = true, nullable = false, length = 128)
	@NotNull
	@Length(max = 128)
	public String getNdTendangnhap() {
		return this.ndTendangnhap;
	}

	public void setNdTendangnhap(String ndTendangnhap) {
		this.ndTendangnhap = ndTendangnhap;
	}

	@Column(name = "ND_MADANGNHAP", length = 128)
	@Length(max = 128)
	public String getNdMadangnhap() {
		return this.ndMadangnhap;
	}

	public void setNdMadangnhap(String ndMadangnhap) {
		this.ndMadangnhap = ndMadangnhap;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nguoiDung")
	public Set<DtDmNhanVien> getDtDmNhanViens() {
		return this.dtDmNhanViens;
	}

	public void setDtDmNhanViens(Set<DtDmNhanVien> dtDmNhanViens) {
		this.dtDmNhanViens = dtDmNhanViens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nguoiDung")
	public Set<NguoiDungVaiTro> getNguoiDungVaiTros() {
		return this.nguoiDungVaiTros;
	}

	public void setNguoiDungVaiTros(Set<NguoiDungVaiTro> nguoiDungVaiTros) {
		this.nguoiDungVaiTros = nguoiDungVaiTros;
	}

}
