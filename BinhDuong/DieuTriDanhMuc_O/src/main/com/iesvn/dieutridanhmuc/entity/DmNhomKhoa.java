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
 * DmNhomKhoa generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_NHOM_KHOA", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMNHOMKHOA_MA"))
public class DmNhomKhoa implements java.io.Serializable {

	private Integer dmnhomkhoaMaso;
	private String dmnhomkhoaMa;
	private String dmnhomkhoaTen;
	private Double dmnhomkhoaNgaygiocn;
	private Boolean dmnhomkhoaDt;
	private Boolean dmnhomkhoaQl;
	private Boolean dmnhomkhoaDp;
	private Set<DmKhoa> dmKhoas = new HashSet<DmKhoa>(0);
	private Set<DmKhoa> dmKhoas_1 = new HashSet<DmKhoa>(0);

	public DmNhomKhoa() {
	}

	public DmNhomKhoa(String dmnhomkhoaMa, String dmnhomkhoaTen) {
		this.dmnhomkhoaMa = dmnhomkhoaMa;
		this.dmnhomkhoaTen = dmnhomkhoaTen;
	}

	public DmNhomKhoa(String dmnhomkhoaMa, String dmnhomkhoaTen,
			Double dmnhomkhoaNgaygiocn, Boolean dmnhomkhoaDt,
			Boolean dmnhomkhoaQl, Boolean dmnhomkhoaDp, Set<DmKhoa> dmKhoas,
			Set<DmKhoa> dmKhoas_1) {
		this.dmnhomkhoaMa = dmnhomkhoaMa;
		this.dmnhomkhoaTen = dmnhomkhoaTen;
		this.dmnhomkhoaNgaygiocn = dmnhomkhoaNgaygiocn;
		this.dmnhomkhoaDt = dmnhomkhoaDt;
		this.dmnhomkhoaQl = dmnhomkhoaQl;
		this.dmnhomkhoaDp = dmnhomkhoaDp;
		this.dmKhoas = dmKhoas;
		this.dmKhoas_1 = dmKhoas_1;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NHOM_KHOA")
	@SequenceGenerator(name = "DM_NHOM_KHOA", sequenceName = "DM_NHOM_KHOA_DMNHOMKHOA_MASO_S", allocationSize = 1)
	@Column(name = "DMNHOMKHOA_MASO", unique = true, nullable = false)
	public Integer getDmnhomkhoaMaso() {
		return this.dmnhomkhoaMaso;
	}

	public void setDmnhomkhoaMaso(Integer dmnhomkhoaMaso) {
		this.dmnhomkhoaMaso = dmnhomkhoaMaso;
	}

	@Column(name = "DMNHOMKHOA_MA", unique = true, nullable = false, length = 3)
	@NotNull
	@Length(max = 3)
	public String getDmnhomkhoaMa() {
		return this.dmnhomkhoaMa;
	}

	public void setDmnhomkhoaMa(String dmnhomkhoaMa) {
		this.dmnhomkhoaMa = dmnhomkhoaMa;
	}

	@Column(name = "DMNHOMKHOA_TEN", nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getDmnhomkhoaTen() {
		return this.dmnhomkhoaTen;
	}

	public void setDmnhomkhoaTen(String dmnhomkhoaTen) {
		this.dmnhomkhoaTen = dmnhomkhoaTen;
	}

	@Column(name = "DMNHOMKHOA_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmnhomkhoaNgaygiocn() {
		return this.dmnhomkhoaNgaygiocn;
	}

	public void setDmnhomkhoaNgaygiocn(Double dmnhomkhoaNgaygiocn) {
		this.dmnhomkhoaNgaygiocn = dmnhomkhoaNgaygiocn;
	}

	@Column(name = "DMNHOMKHOA_DT")
	public Boolean getDmnhomkhoaDt() {
		return this.dmnhomkhoaDt;
	}

	public void setDmnhomkhoaDt(Boolean dmnhomkhoaDt) {
		this.dmnhomkhoaDt = dmnhomkhoaDt;
	}

	@Column(name = "DMNHOMKHOA_QL")
	public Boolean getDmnhomkhoaQl() {
		return this.dmnhomkhoaQl;
	}

	public void setDmnhomkhoaQl(Boolean dmnhomkhoaQl) {
		this.dmnhomkhoaQl = dmnhomkhoaQl;
	}

	@Column(name = "DMNHOMKHOA_DP")
	public Boolean getDmnhomkhoaDp() {
		return this.dmnhomkhoaDp;
	}

	public void setDmnhomkhoaDp(Boolean dmnhomkhoaDp) {
		this.dmnhomkhoaDp = dmnhomkhoaDp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmNhomKhoa")
	public Set<DmKhoa> getDmKhoas() {
		return this.dmKhoas;
	}

	public void setDmKhoas(Set<DmKhoa> dmKhoas) {
		this.dmKhoas = dmKhoas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmNhomKhoa")
	public Set<DmKhoa> getDmKhoas_1() {
		return this.dmKhoas_1;
	}

	public void setDmKhoas_1(Set<DmKhoa> dmKhoas_1) {
		this.dmKhoas_1 = dmKhoas_1;
	}

}
