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
 * DmNguonChuongTrinh generated by hbm2java
 */

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_NGUON_CHUONG_TRINH", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMNCT_MA"))
public class DmNguonChuongTrinh implements java.io.Serializable {

	private Integer dmnctMaso;
	private DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh;
	private String dmnctMa;
	private String dmnctTen;
	private Short dmnctThutucbc;
	private Boolean dmnctDefa;
	private Double dmnctNgaygiocn;
	private Boolean dmnctDt;
	private Boolean dmnctQl;
	private Boolean dmnctDp;
	private Set<DmNhaCungCap> dmNhaCungCaps = new HashSet<DmNhaCungCap>(0);
	private Set<DtDmKhach> dtDmKhachs = new HashSet<DtDmKhach>(0);
	private Set<DmNhaCungCap> dmNhaCungCaps_1 = new HashSet<DmNhaCungCap>(0);
	private Set<DtDmKhach> dtDmKhachs_1 = new HashSet<DtDmKhach>(0);

	public DmNguonChuongTrinh() {
	}

	public DmNguonChuongTrinh(String dmnctMa, String dmnctTen) {
		this.dmnctMa = dmnctMa;
		this.dmnctTen = dmnctTen;
	}

	public DmNguonChuongTrinh(DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh,
			String dmnctMa, String dmnctTen, Short dmnctThutucbc,
			Boolean dmnctDefa, Double dmnctNgaygiocn, Boolean dmnctDt,
			Boolean dmnctQl, Boolean dmnctDp, Set<DmNhaCungCap> dmNhaCungCaps,
			Set<DtDmKhach> dtDmKhachs, Set<DmNhaCungCap> dmNhaCungCaps_1,
			Set<DtDmKhach> dtDmKhachs_1) {
		this.dmLoaiNguonChuongTrinh = dmLoaiNguonChuongTrinh;
		this.dmnctMa = dmnctMa;
		this.dmnctTen = dmnctTen;
		this.dmnctThutucbc = dmnctThutucbc;
		this.dmnctDefa = dmnctDefa;
		this.dmnctNgaygiocn = dmnctNgaygiocn;
		this.dmnctDt = dmnctDt;
		this.dmnctQl = dmnctQl;
		this.dmnctDp = dmnctDp;
		this.dmNhaCungCaps = dmNhaCungCaps;
		this.dtDmKhachs = dtDmKhachs;
		this.dmNhaCungCaps_1 = dmNhaCungCaps_1;
		this.dtDmKhachs_1 = dtDmKhachs_1;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NGUON_CHUONG_TRINH")
	@SequenceGenerator(name = "DM_NGUON_CHUONG_TRINH", sequenceName = "DM_NGUON_CHUONG_TRINH_DMNCT_MA", allocationSize = 1)
	@Column(name = "DMNCT_MASO", unique = true, nullable = false)
	public Integer getDmnctMaso() {
		return this.dmnctMaso;
	}

	public void setDmnctMaso(Integer dmnctMaso) {
		this.dmnctMaso = dmnctMaso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DMLNCT_MASO")
	public DmLoaiNguonChuongTrinh getDmLoaiNguonChuongTrinh() {
		return this.dmLoaiNguonChuongTrinh;
	}

	public void setDmLoaiNguonChuongTrinh(
			DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh) {
		this.dmLoaiNguonChuongTrinh = dmLoaiNguonChuongTrinh;
	}

	@Column(name = "DMNCT_MA", unique = true, nullable = false, length = 15)
	@NotNull
	@Length(max = 15)
	public String getDmnctMa() {
		return this.dmnctMa;
	}

	public void setDmnctMa(String dmnctMa) {
		this.dmnctMa = dmnctMa;
	}

	@Column(name = "DMNCT_TEN", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	public String getDmnctTen() {
		return this.dmnctTen;
	}

	public void setDmnctTen(String dmnctTen) {
		this.dmnctTen = dmnctTen;
	}

	@Column(name = "DMNCT_THUTUCBC")
	public Short getDmnctThutucbc() {
		return this.dmnctThutucbc;
	}

	public void setDmnctThutucbc(Short dmnctThutucbc) {
		this.dmnctThutucbc = dmnctThutucbc;
	}

	@Column(name = "DMNCT_DEFA")
	public Boolean getDmnctDefa() {
		return this.dmnctDefa;
	}

	public void setDmnctDefa(Boolean dmnctDefa) {
		this.dmnctDefa = dmnctDefa;
	}

	@Column(name = "DMNCT_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmnctNgaygiocn() {
		return this.dmnctNgaygiocn;
	}

	public void setDmnctNgaygiocn(Double dmnctNgaygiocn) {
		this.dmnctNgaygiocn = dmnctNgaygiocn;
	}

	@Column(name = "DMNCT_DT")
	public Boolean getDmnctDt() {
		return this.dmnctDt;
	}

	public void setDmnctDt(Boolean dmnctDt) {
		this.dmnctDt = dmnctDt;
	}

	@Column(name = "DMNCT_QL")
	public Boolean getDmnctQl() {
		return this.dmnctQl;
	}

	public void setDmnctQl(Boolean dmnctQl) {
		this.dmnctQl = dmnctQl;
	}

	@Column(name = "DMNCT_DP")
	public Boolean getDmnctDp() {
		return this.dmnctDp;
	}

	public void setDmnctDp(Boolean dmnctDp) {
		this.dmnctDp = dmnctDp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmNguonChuongTrinh")
	public Set<DmNhaCungCap> getDmNhaCungCaps() {
		return this.dmNhaCungCaps;
	}

	public void setDmNhaCungCaps(Set<DmNhaCungCap> dmNhaCungCaps) {
		this.dmNhaCungCaps = dmNhaCungCaps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmNguonChuongTrinh")
	public Set<DtDmKhach> getDtDmKhachs() {
		return this.dtDmKhachs;
	}

	public void setDtDmKhachs(Set<DtDmKhach> dtDmKhachs) {
		this.dtDmKhachs = dtDmKhachs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmNguonChuongTrinh")
	public Set<DmNhaCungCap> getDmNhaCungCaps_1() {
		return this.dmNhaCungCaps_1;
	}

	public void setDmNhaCungCaps_1(Set<DmNhaCungCap> dmNhaCungCaps_1) {
		this.dmNhaCungCaps_1 = dmNhaCungCaps_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmNguonChuongTrinh")
	public Set<DtDmKhach> getDtDmKhachs_1() {
		return this.dtDmKhachs_1;
	}

	public void setDtDmKhachs_1(Set<DtDmKhach> dtDmKhachs_1) {
		this.dtDmKhachs_1 = dtDmKhachs_1;
	}

}
