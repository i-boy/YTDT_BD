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
 * DmHoatChat generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_HOAT_CHAT", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMHOATCHAT_MA"))
public class DmHoatChat implements java.io.Serializable {

	private Integer dmhoatchatMaso;
	private String dmhoatchatMa;
	private String dmhoatchatAtccode;
	private String dmhoatchatTen;
	private String dmhoatchatMaphu;
	private String dmhoatchatNhom;
	private String dmhoatchatMaloai;
	private String dmhoatchatPhloai;
	private String dmhoatchatGhichu;
	private String nhanvienCn;
	private Double dmhoatchatNgaygiocn;
	private Boolean dmhoatchatDt;
	private Boolean dmhoatchatQl;
	private Boolean dmhoatchatDp;
	private Set<DmThuoc> dmThuocs = new HashSet<DmThuoc>(0);
	private Set<DmThuoc> dmThuocs_1 = new HashSet<DmThuoc>(0);

	public DmHoatChat() {
	}

	public DmHoatChat(String dmhoatchatMa, String dmhoatchatTen) {
		this.dmhoatchatMa = dmhoatchatMa;
		this.dmhoatchatTen = dmhoatchatTen;
	}

	public DmHoatChat(String dmhoatchatMa, String dmhoatchatAtccode,
			String dmhoatchatTen, String dmhoatchatMaphu,
			String dmhoatchatNhom, String dmhoatchatMaloai,
			String dmhoatchatPhloai, String dmhoatchatGhichu,
			String nhanvienCn, Double dmhoatchatNgaygiocn,
			Boolean dmhoatchatDt, Boolean dmhoatchatQl, Boolean dmhoatchatDp,
			Set<DmThuoc> dmThuocs, Set<DmThuoc> dmThuocs_1) {
		this.dmhoatchatMa = dmhoatchatMa;
		this.dmhoatchatAtccode = dmhoatchatAtccode;
		this.dmhoatchatTen = dmhoatchatTen;
		this.dmhoatchatMaphu = dmhoatchatMaphu;
		this.dmhoatchatNhom = dmhoatchatNhom;
		this.dmhoatchatMaloai = dmhoatchatMaloai;
		this.dmhoatchatPhloai = dmhoatchatPhloai;
		this.dmhoatchatGhichu = dmhoatchatGhichu;
		this.nhanvienCn = nhanvienCn;
		this.dmhoatchatNgaygiocn = dmhoatchatNgaygiocn;
		this.dmhoatchatDt = dmhoatchatDt;
		this.dmhoatchatQl = dmhoatchatQl;
		this.dmhoatchatDp = dmhoatchatDp;
		this.dmThuocs = dmThuocs;
		this.dmThuocs_1 = dmThuocs_1;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_HOAT_CHAT")
	@SequenceGenerator(name = "DM_HOAT_CHAT", sequenceName = "DM_HOAT_CHAT_DMHOATCHAT_MASO_S", allocationSize = 1)
	@Column(name = "DMHOATCHAT_MASO", unique = true, nullable = false)
	public Integer getDmhoatchatMaso() {
		return this.dmhoatchatMaso;
	}

	public void setDmhoatchatMaso(Integer dmhoatchatMaso) {
		this.dmhoatchatMaso = dmhoatchatMaso;
	}

	@Column(name = "DMHOATCHAT_MA", unique = true, nullable = false, length = 4)
	@NotNull
	@Length(max = 4)
	public String getDmhoatchatMa() {
		return this.dmhoatchatMa;
	}

	public void setDmhoatchatMa(String dmhoatchatMa) {
		this.dmhoatchatMa = dmhoatchatMa;
	}

	@Column(name = "DMHOATCHAT_ATCCODE", length = 4)
	@Length(max = 4)
	public String getDmhoatchatAtccode() {
		return this.dmhoatchatAtccode;
	}

	public void setDmhoatchatAtccode(String dmhoatchatAtccode) {
		this.dmhoatchatAtccode = dmhoatchatAtccode;
	}

	@Column(name = "DMHOATCHAT_TEN", nullable = false, length = 36)
	@NotNull
	@Length(max = 36)
	public String getDmhoatchatTen() {
		return this.dmhoatchatTen;
	}

	public void setDmhoatchatTen(String dmhoatchatTen) {
		this.dmhoatchatTen = dmhoatchatTen;
	}

	@Column(name = "DMHOATCHAT_MAPHU", length = 4)
	@Length(max = 4)
	public String getDmhoatchatMaphu() {
		return this.dmhoatchatMaphu;
	}

	public void setDmhoatchatMaphu(String dmhoatchatMaphu) {
		this.dmhoatchatMaphu = dmhoatchatMaphu;
	}

	@Column(name = "DMHOATCHAT_NHOM", length = 4)
	@Length(max = 4)
	public String getDmhoatchatNhom() {
		return this.dmhoatchatNhom;
	}

	public void setDmhoatchatNhom(String dmhoatchatNhom) {
		this.dmhoatchatNhom = dmhoatchatNhom;
	}

	@Column(name = "DMHOATCHAT_MALOAI", length = 4)
	@Length(max = 4)
	public String getDmhoatchatMaloai() {
		return this.dmhoatchatMaloai;
	}

	public void setDmhoatchatMaloai(String dmhoatchatMaloai) {
		this.dmhoatchatMaloai = dmhoatchatMaloai;
	}

	@Column(name = "DMHOATCHAT_PHLOAI", length = 4)
	@Length(max = 4)
	public String getDmhoatchatPhloai() {
		return this.dmhoatchatPhloai;
	}

	public void setDmhoatchatPhloai(String dmhoatchatPhloai) {
		this.dmhoatchatPhloai = dmhoatchatPhloai;
	}

	@Column(name = "DMHOATCHAT_GHICHU", length = 60)
	@Length(max = 60)
	public String getDmhoatchatGhichu() {
		return this.dmhoatchatGhichu;
	}

	public void setDmhoatchatGhichu(String dmhoatchatGhichu) {
		this.dmhoatchatGhichu = dmhoatchatGhichu;
	}

	@Column(name = "NHANVIEN_CN", length = 8)
	@Length(max = 8)
	public String getNhanvienCn() {
		return this.nhanvienCn;
	}

	public void setNhanvienCn(String nhanvienCn) {
		this.nhanvienCn = nhanvienCn;
	}

	@Column(name = "DMHOATCHAT_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmhoatchatNgaygiocn() {
		return this.dmhoatchatNgaygiocn;
	}

	public void setDmhoatchatNgaygiocn(Double dmhoatchatNgaygiocn) {
		this.dmhoatchatNgaygiocn = dmhoatchatNgaygiocn;
	}

	@Column(name = "DMHOATCHAT_DT")
	public Boolean getDmhoatchatDt() {
		return this.dmhoatchatDt;
	}

	public void setDmhoatchatDt(Boolean dmhoatchatDt) {
		this.dmhoatchatDt = dmhoatchatDt;
	}

	@Column(name = "DMHOATCHAT_QL")
	public Boolean getDmhoatchatQl() {
		return this.dmhoatchatQl;
	}

	public void setDmhoatchatQl(Boolean dmhoatchatQl) {
		this.dmhoatchatQl = dmhoatchatQl;
	}

	@Column(name = "DMHOATCHAT_DP")
	public Boolean getDmhoatchatDp() {
		return this.dmhoatchatDp;
	}

	public void setDmhoatchatDp(Boolean dmhoatchatDp) {
		this.dmhoatchatDp = dmhoatchatDp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmHoatChat")
	public Set<DmThuoc> getDmThuocs() {
		return this.dmThuocs;
	}

	public void setDmThuocs(Set<DmThuoc> dmThuocs) {
		this.dmThuocs = dmThuocs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmHoatChat")
	public Set<DmThuoc> getDmThuocs_1() {
		return this.dmThuocs_1;
	}

	public void setDmThuocs_1(Set<DmThuoc> dmThuocs_1) {
		this.dmThuocs_1 = dmThuocs_1;
	}

}
