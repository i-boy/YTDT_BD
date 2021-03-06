package com.iesvn.dieutridanhmuc.entity;

// Generated Aug 3, 2011 2:01:45 PM by Hibernate Tools 3.2.4.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * DmBenhVn generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_BENH_VN", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMBENHVN_MA"))
public class DmBenhVn implements java.io.Serializable {

	private Integer dmbenhvnMaso;
	private DmChuongBenh dmChuongBenh;
	private String dmbenhvnMa;
	private String dmbenhvnMachung;
	private String dmbenhvnMaicd10;
	private short dmbenhvnMabyt;
	private String dmbenhvnTen;
	private String dmbenhvnMaicd10b;
	private String dmbenhvnBaogom;
	private Double dmbenhvnNgaygiocn;
	private Boolean dmbenhvnQl;
	private Boolean dmbenhvnDt;
	private Boolean dmbenhvnDp;
	private Set<DmBenhIcd> dmBenhIcds = new HashSet<DmBenhIcd>(0);
	private Set<DmBenhTruyenNhiem> dmBenhTruyenNhiems = new HashSet<DmBenhTruyenNhiem>(
			0);

	public DmBenhVn() {
	}

	public DmBenhVn(String dmbenhvnMaicd10, short dmbenhvnMabyt,
			String dmbenhvnTen) {
		this.dmbenhvnMaicd10 = dmbenhvnMaicd10;
		this.dmbenhvnMabyt = dmbenhvnMabyt;
		this.dmbenhvnTen = dmbenhvnTen;
	}

	public DmBenhVn(DmChuongBenh dmChuongBenh, String dmbenhvnMa,
			String dmbenhvnMachung, String dmbenhvnMaicd10,
			short dmbenhvnMabyt, String dmbenhvnTen, String dmbenhvnMaicd10b,
			String dmbenhvnBaogom, Double dmbenhvnNgaygiocn,
			Boolean dmbenhvnQl, Boolean dmbenhvnDt, Boolean dmbenhvnDp,
			Set<DmBenhIcd> dmBenhIcds, Set<DmBenhTruyenNhiem> dmBenhTruyenNhiems) {
		this.dmChuongBenh = dmChuongBenh;
		this.dmbenhvnMa = dmbenhvnMa;
		this.dmbenhvnMachung = dmbenhvnMachung;
		this.dmbenhvnMaicd10 = dmbenhvnMaicd10;
		this.dmbenhvnMabyt = dmbenhvnMabyt;
		this.dmbenhvnTen = dmbenhvnTen;
		this.dmbenhvnMaicd10b = dmbenhvnMaicd10b;
		this.dmbenhvnBaogom = dmbenhvnBaogom;
		this.dmbenhvnNgaygiocn = dmbenhvnNgaygiocn;
		this.dmbenhvnQl = dmbenhvnQl;
		this.dmbenhvnDt = dmbenhvnDt;
		this.dmbenhvnDp = dmbenhvnDp;
		this.dmBenhIcds = dmBenhIcds;
		this.dmBenhTruyenNhiems = dmBenhTruyenNhiems;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_BENH_VN_DMBENHVN_MASO")
	@SequenceGenerator(name = "DM_BENH_VN_DMBENHVN_MASO", sequenceName = "DM_BENH_VN_DMBENHVN_MASO_SEQ", allocationSize = 1)
	@Column(name = "DMBENHVN_MASO", unique = true, nullable = false)
	public Integer getDmbenhvnMaso() {
		return this.dmbenhvnMaso;
	}

	public void setDmbenhvnMaso(Integer dmbenhvnMaso) {
		this.dmbenhvnMaso = dmbenhvnMaso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DMCHUONGBENH_MASO")
	public DmChuongBenh getDmChuongBenh() {
		return this.dmChuongBenh;
	}

	public void setDmChuongBenh(DmChuongBenh dmChuongBenh) {
		this.dmChuongBenh = dmChuongBenh;
	}

	@Column(name = "DMBENHVN_MA", unique = true, length = 3)
	@Length(max = 3)
	public String getDmbenhvnMa() {
		return this.dmbenhvnMa;
	}

	public void setDmbenhvnMa(String dmbenhvnMa) {
		this.dmbenhvnMa = dmbenhvnMa;
	}

	@Column(name = "DMBENHVN_MACHUNG", length = 50)
	@Length(max = 50)
	public String getDmbenhvnMachung() {
		return this.dmbenhvnMachung;
	}

	public void setDmbenhvnMachung(String dmbenhvnMachung) {
		this.dmbenhvnMachung = dmbenhvnMachung;
	}

	@Column(name = "DMBENHVN_MAICD10", nullable = false, length = 7)
	@NotNull
	@Length(max = 7)
	public String getDmbenhvnMaicd10() {
		return this.dmbenhvnMaicd10;
	}

	public void setDmbenhvnMaicd10(String dmbenhvnMaicd10) {
		this.dmbenhvnMaicd10 = dmbenhvnMaicd10;
	}

	@Column(name = "DMBENHVN_MABYT", nullable = false)
	@NotNull
	public short getDmbenhvnMabyt() {
		return this.dmbenhvnMabyt;
	}

	public void setDmbenhvnMabyt(short dmbenhvnMabyt) {
		this.dmbenhvnMabyt = dmbenhvnMabyt;
	}

	@Column(name = "DMBENHVN_TEN", nullable = false, length = 250)
	@NotNull
	@Length(max = 250)
	public String getDmbenhvnTen() {
		return this.dmbenhvnTen;
	}

	public void setDmbenhvnTen(String dmbenhvnTen) {
		this.dmbenhvnTen = dmbenhvnTen;
	}

	@Column(name = "DMBENHVN_MAICD10B", length = 250)
	@Length(max = 250)
	public String getDmbenhvnMaicd10b() {
		return this.dmbenhvnMaicd10b;
	}

	public void setDmbenhvnMaicd10b(String dmbenhvnMaicd10b) {
		this.dmbenhvnMaicd10b = dmbenhvnMaicd10b;
	}

	@Column(name = "DMBENHVN_BAOGOM", length = 5)
	@Length(max = 5)
	public String getDmbenhvnBaogom() {
		return this.dmbenhvnBaogom;
	}

	public void setDmbenhvnBaogom(String dmbenhvnBaogom) {
		this.dmbenhvnBaogom = dmbenhvnBaogom;
	}

	@Column(name = "DMBENHVN_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmbenhvnNgaygiocn() {
		return this.dmbenhvnNgaygiocn;
	}

	public void setDmbenhvnNgaygiocn(Double dmbenhvnNgaygiocn) {
		this.dmbenhvnNgaygiocn = dmbenhvnNgaygiocn;
	}

	@Column(name = "DMBENHVN_QL")
	public Boolean getDmbenhvnQl() {
		return this.dmbenhvnQl;
	}

	public void setDmbenhvnQl(Boolean dmbenhvnQl) {
		this.dmbenhvnQl = dmbenhvnQl;
	}

	@Column(name = "DMBENHVN_DT")
	public Boolean getDmbenhvnDt() {
		return this.dmbenhvnDt;
	}

	public void setDmbenhvnDt(Boolean dmbenhvnDt) {
		this.dmbenhvnDt = dmbenhvnDt;
	}

	@Column(name = "DMBENHVN_DP")
	public Boolean getDmbenhvnDp() {
		return this.dmbenhvnDp;
	}

	public void setDmbenhvnDp(Boolean dmbenhvnDp) {
		this.dmbenhvnDp = dmbenhvnDp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmBenhVn")
	public Set<DmBenhIcd> getDmBenhIcds() {
		return this.dmBenhIcds;
	}

	public void setDmBenhIcds(Set<DmBenhIcd> dmBenhIcds) {
		this.dmBenhIcds = dmBenhIcds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmBenhVn")
	public Set<DmBenhTruyenNhiem> getDmBenhTruyenNhiems() {
		return this.dmBenhTruyenNhiems;
	}

	public void setDmBenhTruyenNhiems(Set<DmBenhTruyenNhiem> dmBenhTruyenNhiems) {
		this.dmBenhTruyenNhiems = dmBenhTruyenNhiems;
	}

}
