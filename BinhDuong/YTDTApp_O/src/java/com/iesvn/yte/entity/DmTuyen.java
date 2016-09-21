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
@Table(name = "DM_TUYEN")
@NamedQueries({})
public class DmTuyen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_TUYEN_DMTUYEN_MASO")
    @SequenceGenerator(name = "DM_TUYEN_DMTUYEN_MASO", sequenceName = "DM_TUYEN_DMTUYEN_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMTUYEN_MASO", nullable = false)
    private Integer dmtuyenMaso;
    @Column(name = "DMTUYEN_MA")
    private String dmtuyenMa;
    @Column(name = "DMTUYEN_TEN")
    private String dmtuyenTen;
    @Column(name = "DMTUYEN_NGAYGIOCN")
    private Double dmtuyenNgaygiocn;
    @Column(name = "DMTUYEN_QL")
    private Boolean dmtuyenQl;
    @Column(name = "DMTUYEN_DT")
    private Boolean dmtuyenDt;
    @Column(name = "DMTUYEN_DP")
    private Boolean dmtuyenDp;
//    @OneToMany(mappedBy = "dmtuyenMaso")
//    private Collection<DmBenhVien> dmBenhVienCollection;
//    @OneToMany(mappedBy = "dmtuyenMaso1")
//    private Collection<DmBenhVien> dmBenhVienCollection1;
//    @OneToMany(mappedBy = "dmtuyenMaso")
//    private Collection<DmDonVi> dmDonViCollection;
//    @OneToMany(mappedBy = "dmtuyenMaso1")
//    private Collection<DmDonVi> dmDonViCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmTuyen")
//    private Collection<DmTuyenDonVi> dmTuyenDonViCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmTuyen1")
//    private Collection<DmTuyenDonVi> dmTuyenDonViCollection1;

    public DmTuyen() {
    }

    public DmTuyen(Integer dmtuyenMaso) {
        this.dmtuyenMaso = dmtuyenMaso;
    }

    public Integer getDmtuyenMaso() {
        return dmtuyenMaso;
    }

    public void setDmtuyenMaso(Integer dmtuyenMaso) {
        this.dmtuyenMaso = dmtuyenMaso;
    }

    public String getDmtuyenMa() {
        return dmtuyenMa;
    }

    public void setDmtuyenMa(String dmtuyenMa) {
        this.dmtuyenMa = dmtuyenMa;
    }

    public String getDmtuyenTen() {
        return dmtuyenTen;
    }

    public void setDmtuyenTen(String dmtuyenTen) {
        this.dmtuyenTen = dmtuyenTen;
    }

    public Double getDmtuyenNgaygiocn() {
        return dmtuyenNgaygiocn;
    }

    public void setDmtuyenNgaygiocn(Double dmtuyenNgaygiocn) {
        this.dmtuyenNgaygiocn = dmtuyenNgaygiocn;
    }

    public Boolean getDmtuyenQl() {
        return dmtuyenQl;
    }

    public void setDmtuyenQl(Boolean dmtuyenQl) {
        this.dmtuyenQl = dmtuyenQl;
    }

    public Boolean getDmtuyenDt() {
        return dmtuyenDt;
    }

    public void setDmtuyenDt(Boolean dmtuyenDt) {
        this.dmtuyenDt = dmtuyenDt;
    }

    public Boolean getDmtuyenDp() {
        return dmtuyenDp;
    }

    public void setDmtuyenDp(Boolean dmtuyenDp) {
        this.dmtuyenDp = dmtuyenDp;
    }

//    public Collection<DmBenhVien> getDmBenhVienCollection() {
//        return dmBenhVienCollection;
//    }
//
//    public void setDmBenhVienCollection(Collection<DmBenhVien> dmBenhVienCollection) {
//        this.dmBenhVienCollection = dmBenhVienCollection;
//    }

//    public Collection<DmBenhVien> getDmBenhVienCollection1() {
//        return dmBenhVienCollection1;
//    }
//
//    public void setDmBenhVienCollection1(Collection<DmBenhVien> dmBenhVienCollection1) {
//        this.dmBenhVienCollection1 = dmBenhVienCollection1;
//    }

//    public Collection<DmDonVi> getDmDonViCollection() {
//        return dmDonViCollection;
//    }
//
//    public void setDmDonViCollection(Collection<DmDonVi> dmDonViCollection) {
//        this.dmDonViCollection = dmDonViCollection;
//    }

//    public Collection<DmDonVi> getDmDonViCollection1() {
//        return dmDonViCollection1;
//    }
//
//    public void setDmDonViCollection1(Collection<DmDonVi> dmDonViCollection1) {
//        this.dmDonViCollection1 = dmDonViCollection1;
//    }

//    public Collection<DmTuyenDonVi> getDmTuyenDonViCollection() {
//        return dmTuyenDonViCollection;
//    }
//
//    public void setDmTuyenDonViCollection(Collection<DmTuyenDonVi> dmTuyenDonViCollection) {
//        this.dmTuyenDonViCollection = dmTuyenDonViCollection;
//    }

//    public Collection<DmTuyenDonVi> getDmTuyenDonViCollection1() {
//        return dmTuyenDonViCollection1;
//    }
//
//    public void setDmTuyenDonViCollection1(Collection<DmTuyenDonVi> dmTuyenDonViCollection1) {
//        this.dmTuyenDonViCollection1 = dmTuyenDonViCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmtuyenMaso != null ? dmtuyenMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmTuyen)) {
            return false;
        }
        DmTuyen other = (DmTuyen) object;
        if ((this.dmtuyenMaso == null && other.dmtuyenMaso != null) || (this.dmtuyenMaso != null && !this.dmtuyenMaso.equals(other.dmtuyenMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmTuyen[dmtuyenMaso=" + dmtuyenMaso + "]";
    }

}


