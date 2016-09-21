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
@Table(name = "DM_TAI_NAN")
@NamedQueries({})
public class DmTaiNan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_TAI_NAN")
    @SequenceGenerator(name = "DM_TAI_NAN", sequenceName = "DM_TAI_NAN_DMTAINAN_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMTAINAN_MASO", nullable = false)
    private Integer dmtainanMaso;
    @Column(name = "DMTAINAN_MA")
    private String dmtainanMa;
    @Column(name = "DMTAINAN_TEN")
    private String dmtainanTen;
    @Column(name = "DMTAINAN_NGAYGIOCN")
    private Double dmtainanNgaygiocn;
    @Column(name = "DMTAINAN_DT")
    private Boolean dmtainanDt;
    @Column(name = "DMTAINAN_QL")
    private Boolean dmtainanQl;
    @Column(name = "DMTAINAN_DP")
    private Boolean dmtainanDp;
//    @OneToMany(mappedBy = "dmtainanMaso")
//    private Collection<DmPhanLoaiTaiNan> dmPhanLoaiTaiNanCollection;
//    @OneToMany(mappedBy = "dmtainanMaso1")
//    private Collection<DmPhanLoaiTaiNan> dmPhanLoaiTaiNanCollection1;
//    @OneToMany(mappedBy = "tainanMa")
//    private Collection<TiepDon> tiepDonCollection;
//    @OneToMany(mappedBy = "dmtainanMaso")
//    private Collection<DmPhuongThucGayTaiNan> dmPhuongThucGayTaiNanCollection;
//    @OneToMany(mappedBy = "dmtainanMaso1")
//    private Collection<DmPhuongThucGayTaiNan> dmPhuongThucGayTaiNanCollection1;

    public DmTaiNan() {
    }

    public DmTaiNan(Integer dmtainanMaso) {
        this.dmtainanMaso = dmtainanMaso;
    }

    public Integer getDmtainanMaso() {
        return dmtainanMaso;
    }

    public void setDmtainanMaso(Integer dmtainanMaso) {
        this.dmtainanMaso = dmtainanMaso;
    }

    public String getDmtainanMa() {
        return dmtainanMa;
    }

    public void setDmtainanMa(String dmtainanMa) {
        this.dmtainanMa = dmtainanMa;
    }

    public String getDmtainanTen() {
        return dmtainanTen;
    }

    public void setDmtainanTen(String dmtainanTen) {
        this.dmtainanTen = dmtainanTen;
    }

    public Double getDmtainanNgaygiocn() {
        return dmtainanNgaygiocn;
    }

    public void setDmtainanNgaygiocn(Double dmtainanNgaygiocn) {
        this.dmtainanNgaygiocn = dmtainanNgaygiocn;
    }

    public Boolean getDmtainanDt() {
        return dmtainanDt;
    }

    public void setDmtainanDt(Boolean dmtainanDt) {
        this.dmtainanDt = dmtainanDt;
    }

    public Boolean getDmtainanQl() {
        return dmtainanQl;
    }

    public void setDmtainanQl(Boolean dmtainanQl) {
        this.dmtainanQl = dmtainanQl;
    }

    public Boolean getDmtainanDp() {
        return dmtainanDp;
    }

    public void setDmtainanDp(Boolean dmtainanDp) {
        this.dmtainanDp = dmtainanDp;
    }

//    public Collection<DmPhanLoaiTaiNan> getDmPhanLoaiTaiNanCollection() {
//        return dmPhanLoaiTaiNanCollection;
//    }
//
//    public void setDmPhanLoaiTaiNanCollection(Collection<DmPhanLoaiTaiNan> dmPhanLoaiTaiNanCollection) {
//        this.dmPhanLoaiTaiNanCollection = dmPhanLoaiTaiNanCollection;
//    }

//    public Collection<DmPhanLoaiTaiNan> getDmPhanLoaiTaiNanCollection1() {
//        return dmPhanLoaiTaiNanCollection1;
//    }
//
//    public void setDmPhanLoaiTaiNanCollection1(Collection<DmPhanLoaiTaiNan> dmPhanLoaiTaiNanCollection1) {
//        this.dmPhanLoaiTaiNanCollection1 = dmPhanLoaiTaiNanCollection1;
//    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }

//    public Collection<DmPhuongThucGayTaiNan> getDmPhuongThucGayTaiNanCollection() {
//        return dmPhuongThucGayTaiNanCollection;
//    }
//
//    public void setDmPhuongThucGayTaiNanCollection(Collection<DmPhuongThucGayTaiNan> dmPhuongThucGayTaiNanCollection) {
//        this.dmPhuongThucGayTaiNanCollection = dmPhuongThucGayTaiNanCollection;
//    }

//    public Collection<DmPhuongThucGayTaiNan> getDmPhuongThucGayTaiNanCollection1() {
//        return dmPhuongThucGayTaiNanCollection1;
//    }
//
//    public void setDmPhuongThucGayTaiNanCollection1(Collection<DmPhuongThucGayTaiNan> dmPhuongThucGayTaiNanCollection1) {
//        this.dmPhuongThucGayTaiNanCollection1 = dmPhuongThucGayTaiNanCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmtainanMaso != null ? dmtainanMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmTaiNan)) {
            return false;
        }
        DmTaiNan other = (DmTaiNan) object;
        if ((this.dmtainanMaso == null && other.dmtainanMaso != null) || (this.dmtainanMaso != null && !this.dmtainanMaso.equals(other.dmtainanMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmTaiNan[dmtainanMaso=" + dmtainanMaso + "]";
    }

}


