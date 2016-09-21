/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_LOAI_KHOA")
@NamedQueries({})
public class DmLoaiKhoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_KHOA")
    @SequenceGenerator(name = "DM_LOAI_KHOA", sequenceName = "DM_LOAI_KHOA_DMLOAIKHOA_MASO_S", allocationSize = 1)
    @Column(name = "DMLOAIKHOA_MASO", nullable = false)
    private Integer dmloaikhoaMaso;
    @Column(name = "DMLOAIKHOA_MA")
    private String dmloaikhoaMa;
    @Column(name = "DMLOAIKHOA_TEN", nullable = false)
    private String dmloaikhoaTen;
    @Column(name = "DMLOAIKHOA_THUTUCBC")
    private Short dmloaikhoaThutucbc;
    @Column(name = "DMLOAIKHOA_NGAYGIOCN")
    private Double dmloaikhoaNgaygiocn;
    @Column(name = "DMLOAIKHOA_DT")
    private Boolean dmloaikhoaDt;
    @Column(name = "DMLOAIKHOA_QL")
    private Boolean dmloaikhoaQl;
    @Column(name = "DMLOAIKHOA_DP")
    private Boolean dmloaikhoaDp;
//    @OneToMany(mappedBy = "dmloaikhoaMa")
//    private Collection<DmKhoa> dmKhoaCollection;
//    @OneToMany(mappedBy = "dmloaikhoaMa1")
//    private Collection<DmKhoa> dmKhoaCollection1;
//    @OneToMany(mappedBy = "dmloaikhoaMaso")
//    private Collection<DtDmBanKham> dtDmBanKhamCollection;
//    @OneToMany(mappedBy = "dmloaikhoaMaso1")
//    private Collection<DtDmBanKham> dtDmBanKhamCollection1;

    public DmLoaiKhoa() {
    }

    public DmLoaiKhoa(Integer dmloaikhoaMaso) {
        this.dmloaikhoaMaso = dmloaikhoaMaso;
    }

    public DmLoaiKhoa(Integer dmloaikhoaMaso, String dmloaikhoaTen) {
        this.dmloaikhoaMaso = dmloaikhoaMaso;
        this.dmloaikhoaTen = dmloaikhoaTen;
    }

    public Integer getDmloaikhoaMaso() {
        return dmloaikhoaMaso;
    }

    public void setDmloaikhoaMaso(Integer dmloaikhoaMaso) {
        this.dmloaikhoaMaso = dmloaikhoaMaso;
    }

    public String getDmloaikhoaMa() {
        return dmloaikhoaMa;
    }

    public void setDmloaikhoaMa(String dmloaikhoaMa) {
        this.dmloaikhoaMa = dmloaikhoaMa;
    }

    public String getDmloaikhoaTen() {
        return dmloaikhoaTen;
    }

    public void setDmloaikhoaTen(String dmloaikhoaTen) {
        this.dmloaikhoaTen = dmloaikhoaTen;
    }

    public Short getDmloaikhoaThutucbc() {
        return dmloaikhoaThutucbc;
    }

    public void setDmloaikhoaThutucbc(Short dmloaikhoaThutucbc) {
        this.dmloaikhoaThutucbc = dmloaikhoaThutucbc;
    }

    public Double getDmloaikhoaNgaygiocn() {
        return dmloaikhoaNgaygiocn;
    }

    public void setDmloaikhoaNgaygiocn(Double dmloaikhoaNgaygiocn) {
        this.dmloaikhoaNgaygiocn = dmloaikhoaNgaygiocn;
    }

    public Boolean getDmloaikhoaDt() {
        return dmloaikhoaDt;
    }

    public void setDmloaikhoaDt(Boolean dmloaikhoaDt) {
        this.dmloaikhoaDt = dmloaikhoaDt;
    }

    public Boolean getDmloaikhoaQl() {
        return dmloaikhoaQl;
    }

    public void setDmloaikhoaQl(Boolean dmloaikhoaQl) {
        this.dmloaikhoaQl = dmloaikhoaQl;
    }

    public Boolean getDmloaikhoaDp() {
        return dmloaikhoaDp;
    }

    public void setDmloaikhoaDp(Boolean dmloaikhoaDp) {
        this.dmloaikhoaDp = dmloaikhoaDp;
    }

//    public Collection<DmKhoa> getDmKhoaCollection() {
//        return dmKhoaCollection;
//    }
//
//    public void setDmKhoaCollection(Collection<DmKhoa> dmKhoaCollection) {
//        this.dmKhoaCollection = dmKhoaCollection;
//    }

//    public Collection<DmKhoa> getDmKhoaCollection1() {
//        return dmKhoaCollection1;
//    }
//
//    public void setDmKhoaCollection1(Collection<DmKhoa> dmKhoaCollection1) {
//        this.dmKhoaCollection1 = dmKhoaCollection1;
//    }

//    public Collection<DtDmBanKham> getDtDmBanKhamCollection() {
//        return dtDmBanKhamCollection;
//    }
//
//    public void setDtDmBanKhamCollection(Collection<DtDmBanKham> dtDmBanKhamCollection) {
//        this.dtDmBanKhamCollection = dtDmBanKhamCollection;
//    }

//    public Collection<DtDmBanKham> getDtDmBanKhamCollection1() {
//        return dtDmBanKhamCollection1;
//    }
//
//    public void setDtDmBanKhamCollection1(Collection<DtDmBanKham> dtDmBanKhamCollection1) {
//        this.dtDmBanKhamCollection1 = dtDmBanKhamCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmloaikhoaMaso != null ? dmloaikhoaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmLoaiKhoa)) {
            return false;
        }
        DmLoaiKhoa other = (DmLoaiKhoa) object;
        if ((this.dmloaikhoaMaso == null && other.dmloaikhoaMaso != null) || (this.dmloaikhoaMaso != null && !this.dmloaikhoaMaso.equals(other.dmloaikhoaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmLoaiKhoa[dmloaikhoaMaso=" + dmloaikhoaMaso + "]";
    }

}


