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
 * DmLoaiKhoa generated by hbm2java
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DM_LOAI_KHOA", catalog = "DB_YTDT_BD", uniqueConstraints = @UniqueConstraint(columnNames = "DMLOAIKHOA_MA"))
public class DmLoaiKhoa implements java.io.Serializable {

	private Integer dmloaikhoaMaso;
	private String dmloaikhoaMa;
	private String dmloaikhoaTen;
	private Short dmloaikhoaThutucbc;
	private Double dmloaikhoaNgaygiocn;
	private Boolean dmloaikhoaDt;
	private Boolean dmloaikhoaQl;
	private Boolean dmloaikhoaDp;
	private Set<DmKhoa> dmKhoas = new HashSet<DmKhoa>(0);
	private Set<DmKhoa> dmKhoas_1 = new HashSet<DmKhoa>(0);

	public DmLoaiKhoa() {
	}

	public DmLoaiKhoa(String dmloaikhoaMa, String dmloaikhoaTen) {
		this.dmloaikhoaMa = dmloaikhoaMa;
		this.dmloaikhoaTen = dmloaikhoaTen;
	}

	public DmLoaiKhoa(String dmloaikhoaMa, String dmloaikhoaTen,
			Short dmloaikhoaThutucbc, Double dmloaikhoaNgaygiocn,
			Boolean dmloaikhoaDt, Boolean dmloaikhoaQl, Boolean dmloaikhoaDp,
			Set<DmKhoa> dmKhoas, Set<DmKhoa> dmKhoas_1) {
		this.dmloaikhoaMa = dmloaikhoaMa;
		this.dmloaikhoaTen = dmloaikhoaTen;
		this.dmloaikhoaThutucbc = dmloaikhoaThutucbc;
		this.dmloaikhoaNgaygiocn = dmloaikhoaNgaygiocn;
		this.dmloaikhoaDt = dmloaikhoaDt;
		this.dmloaikhoaQl = dmloaikhoaQl;
		this.dmloaikhoaDp = dmloaikhoaDp;
		this.dmKhoas = dmKhoas;
		this.dmKhoas_1 = dmKhoas_1;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_KHOA")
	@SequenceGenerator(name = "DM_LOAI_KHOA", sequenceName = "DM_LOAI_KHOA_DMLOAIKHOA_MASO_S", allocationSize = 1)
	@Column(name = "DMLOAIKHOA_MASO", unique = true, nullable = false)
	public Integer getDmloaikhoaMaso() {
		return this.dmloaikhoaMaso;
	}

	public void setDmloaikhoaMaso(Integer dmloaikhoaMaso) {
		this.dmloaikhoaMaso = dmloaikhoaMaso;
	}

	@Column(name = "DMLOAIKHOA_MA", unique = true, nullable = false, length = 3)
	@NotNull
	@Length(max = 3)
	public String getDmloaikhoaMa() {
		return this.dmloaikhoaMa;
	}

	public void setDmloaikhoaMa(String dmloaikhoaMa) {
		this.dmloaikhoaMa = dmloaikhoaMa;
	}

	@Column(name = "DMLOAIKHOA_TEN", nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getDmloaikhoaTen() {
		return this.dmloaikhoaTen;
	}

	public void setDmloaikhoaTen(String dmloaikhoaTen) {
		this.dmloaikhoaTen = dmloaikhoaTen;
	}

	@Column(name = "DMLOAIKHOA_THUTUCBC")
	public Short getDmloaikhoaThutucbc() {
		return this.dmloaikhoaThutucbc;
	}

	public void setDmloaikhoaThutucbc(Short dmloaikhoaThutucbc) {
		this.dmloaikhoaThutucbc = dmloaikhoaThutucbc;
	}

	@Column(name = "DMLOAIKHOA_NGAYGIOCN", precision = 22, scale = 0)
	public Double getDmloaikhoaNgaygiocn() {
		return this.dmloaikhoaNgaygiocn;
	}

	public void setDmloaikhoaNgaygiocn(Double dmloaikhoaNgaygiocn) {
		this.dmloaikhoaNgaygiocn = dmloaikhoaNgaygiocn;
	}

	@Column(name = "DMLOAIKHOA_DT")
	public Boolean getDmloaikhoaDt() {
		return this.dmloaikhoaDt;
	}

	public void setDmloaikhoaDt(Boolean dmloaikhoaDt) {
		this.dmloaikhoaDt = dmloaikhoaDt;
	}

	@Column(name = "DMLOAIKHOA_QL")
	public Boolean getDmloaikhoaQl() {
		return this.dmloaikhoaQl;
	}

	public void setDmloaikhoaQl(Boolean dmloaikhoaQl) {
		this.dmloaikhoaQl = dmloaikhoaQl;
	}

	@Column(name = "DMLOAIKHOA_DP")
	public Boolean getDmloaikhoaDp() {
		return this.dmloaikhoaDp;
	}

	public void setDmloaikhoaDp(Boolean dmloaikhoaDp) {
		this.dmloaikhoaDp = dmloaikhoaDp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmLoaiKhoa")
	public Set<DmKhoa> getDmKhoas() {
		return this.dmKhoas;
	}

	public void setDmKhoas(Set<DmKhoa> dmKhoas) {
		this.dmKhoas = dmKhoas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmLoaiKhoa")
	public Set<DmKhoa> getDmKhoas_1() {
		return this.dmKhoas_1;
	}

	public void setDmKhoas_1(Set<DmKhoa> dmKhoas_1) {
		this.dmKhoas_1 = dmKhoas_1;
	}

}
