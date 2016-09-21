/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "DM_HUYEN")
@NamedQueries({})
public class DmHuyen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_HUYEN_DMHUYEN")
    @SequenceGenerator(name = "DM_HUYEN_DMHUYEN", sequenceName = "DM_HUYEN_DMHUYEN_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMHUYEN_MASO", nullable = false)
    private Integer dmhuyenMaso;
    @Column(name = "DMHUYEN_MA", nullable = false)
    private String dmhuyenMa;
    @Column(name = "DMHUYEN_TEN", nullable = false)
    private String dmhuyenTen;
    @Column(name = "DMHUYEN_DEFA", nullable = false)
    private Boolean dmhuyenDefa;
    @Column(name = "DMHUYEN_NGAYGIOCN")
    private Double dmhuyenNgaygiocn;
    @Column(name = "DMHUYEN_CHON")
    private Boolean dmhuyenChon;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmhuyenMaso")
//    private Collection<DmXa> dmXaCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmhuyenMaso1")
//    private Collection<DmXa> dmXaCollection1;
//    @OneToMany(mappedBy = "dmhuyenMaso")
//    private Collection<DmBenhVien> dmBenhVienCollection;
//    @OneToMany(mappedBy = "dmhuyenMaso1")
//    private Collection<DmBenhVien> dmBenhVienCollection1;
    @JoinColumn(name = "DMTINH_MASO", referencedColumnName = "DMTINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTinh dmtinhMaso;

//    @OneToMany(mappedBy = "dmhuyenMaso")
//    private Collection<DmDonVi> dmDonViCollection;
//    @OneToMany(mappedBy = "dmhuyenMaso1")
//    private Collection<DmDonVi> dmDonViCollection1;
//    @OneToMany(mappedBy = "huyenMa")
//    private Collection<BenhNhan> benhNhanCollection;
//    @OneToMany(mappedBy = "huyenMa1")
//    private Collection<BenhNhan> benhNhanCollection1;
    public DmHuyen() {
    }

    public DmHuyen(Integer dmhuyenMaso) {
        this.dmhuyenMaso = dmhuyenMaso;
    }

    public DmHuyen(Integer dmhuyenMaso, String dmhuyenMa, String dmhuyenTen) {
        this.dmhuyenMaso = dmhuyenMaso;
        this.dmhuyenMa = dmhuyenMa;
        this.dmhuyenTen = dmhuyenTen;
    }

    public Integer getDmhuyenMaso() {
        return dmhuyenMaso;
    }

    public void setDmhuyenMaso(Integer dmhuyenMaso) {
        this.dmhuyenMaso = dmhuyenMaso;
    }

    public String getDmhuyenMa() {
        return dmhuyenMa;
    }

    public void setDmhuyenMa(String dmhuyenMa) {
        this.dmhuyenMa = dmhuyenMa;
    }

    public String getDmhuyenTen() {
        return dmhuyenTen;
    }

    public void setDmhuyenTen(String dmhuyenTen) {
        this.dmhuyenTen = dmhuyenTen;
    }

    public Double getDmhuyenNgaygiocn() {
        return dmhuyenNgaygiocn;
    }

    public void setDmhuyenNgaygiocn(Double dmhuyenNgaygiocn) {
        this.dmhuyenNgaygiocn = dmhuyenNgaygiocn;
    }

    public Boolean getDmhuyenChon() {
        return dmhuyenChon;
    }

    public void setDmhuyenChon(Boolean dmhuyenChon) {
        this.dmhuyenChon = dmhuyenChon;
    }

//    public Collection<DmXa> getDmXaCollection() {
//        return dmXaCollection;
//    }
//
//    public void setDmXaCollection(Collection<DmXa> dmXaCollection) {
//        this.dmXaCollection = dmXaCollection;
//    }

//    public Collection<DmXa> getDmXaCollection1() {
//        return dmXaCollection1;
//    }
//
//    public void setDmXaCollection1(Collection<DmXa> dmXaCollection1) {
//        this.dmXaCollection1 = dmXaCollection1;
//    }

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
    public DmTinh getDmtinhMaso(boolean create) {
        if (create && dmtinhMaso == null) {
            dmtinhMaso = new DmTinh();
        }
        return dmtinhMaso;
    }

    public DmTinh getDmtinhMaso() {
        return dmtinhMaso;
    }

    public void setDmtinhMaso(DmTinh dmtinhMaso) {
        this.dmtinhMaso = dmtinhMaso;
    }
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

//    public Collection<BenhNhan> getBenhNhanCollection() {
//        return benhNhanCollection;
//    }
//
//    public void setBenhNhanCollection(Collection<BenhNhan> benhNhanCollection) {
//        this.benhNhanCollection = benhNhanCollection;
//    }

//    public Collection<BenhNhan> getBenhNhanCollection1() {
//        return benhNhanCollection1;
//    }
//
//    public void setBenhNhanCollection1(Collection<BenhNhan> benhNhanCollection1) {
//        this.benhNhanCollection1 = benhNhanCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmhuyenMaso != null ? dmhuyenMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmHuyen)) {
            return false;
        }
        DmHuyen other = (DmHuyen) object;
        if ((this.dmhuyenMaso == null && other.dmhuyenMaso != null) || (this.dmhuyenMaso != null && !this.dmhuyenMaso.equals(other.dmhuyenMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmHuyen[dmhuyenMaso=" + dmhuyenMaso + "]";
    }

    public Boolean getDmhuyenDefa() {
        return dmhuyenDefa;
    }

    public void setDmhuyenDefa(Boolean dmhuyenDefa) {
        this.dmhuyenDefa = dmhuyenDefa;
    }
}


