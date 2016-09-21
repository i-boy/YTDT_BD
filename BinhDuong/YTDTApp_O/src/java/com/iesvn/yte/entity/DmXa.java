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
@Table(name = "DM_XA")
@NamedQueries({})
public class DmXa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_XA")
    @SequenceGenerator(name = "DM_XA", sequenceName = "DM_XA_DMXA_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMXA_MASO", nullable = false)
    private Integer dmxaMaso;
    @Column(name = "DMXA_MA", nullable = false)
    private String dmxaMa;
    @Column(name = "DMXA_TEN", nullable = false)
    private String dmxaTen;
    @Column(name = "DMXA_DEFA", nullable = false)
    private Boolean dmxaDefa;
    @Column(name = "DMXA_NGAYGIOCN")
    private Double dmxaNgaygiocn;
    @Column(name = "DMXA_CHON")
    private Boolean dmxaChon;
    @JoinColumn(name = "DMHUYEN_MASO", referencedColumnName = "DMHUYEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmHuyen dmhuyenMaso;

//    @OneToMany(mappedBy = "dmxaMaso")
//    private Collection<DmThon> dmThonCollection;
//    @OneToMany(mappedBy = "dmxaMaso1")
//    private Collection<DmThon> dmThonCollection1;
//    @OneToMany(mappedBy = "dmxaMaso")
//    private Collection<DmDonVi> dmDonViCollection;
//    @OneToMany(mappedBy = "dmxaMaso1")
//    private Collection<DmDonVi> dmDonViCollection1;
//    @OneToMany(mappedBy = "xaMa")
//    private Collection<BenhNhan> benhNhanCollection;
//    @OneToMany(mappedBy = "xaMa1")
//    private Collection<BenhNhan> benhNhanCollection1;
    public DmXa() {
    }

    public DmXa(Integer dmxaMaso) {
        this.dmxaMaso = dmxaMaso;
    }

    public DmXa(Integer dmxaMaso, String dmxaMa, String dmxaTen) {
        this.dmxaMaso = dmxaMaso;
        this.dmxaMa = dmxaMa;
        this.dmxaTen = dmxaTen;
    }

    public Integer getDmxaMaso() {
        return dmxaMaso;
    }

    public void setDmxaMaso(Integer dmxaMaso) {
        this.dmxaMaso = dmxaMaso;
    }

    public String getDmxaMa() {
        return dmxaMa;
    }

    public void setDmxaMa(String dmxaMa) {
        this.dmxaMa = dmxaMa;
    }

    public String getDmxaTen() {
        return dmxaTen;
    }

    public void setDmxaTen(String dmxaTen) {
        this.dmxaTen = dmxaTen;
    }

    public Double getDmxaNgaygiocn() {
        return dmxaNgaygiocn;
    }

    public void setDmxaNgaygiocn(Double dmxaNgaygiocn) {
        this.dmxaNgaygiocn = dmxaNgaygiocn;
    }

    public Boolean getDmxaChon() {
        return dmxaChon;
    }

    public void setDmxaChon(Boolean dmxaChon) {
        this.dmxaChon = dmxaChon;
    }

    public DmHuyen getDmhuyenMaso(boolean create) {
        if (create && dmhuyenMaso == null) {
            dmhuyenMaso = new DmHuyen();
        }
        return dmhuyenMaso;
    }

    public DmHuyen getDmhuyenMaso() {
        return dmhuyenMaso;
    }

    public void setDmhuyenMaso(DmHuyen dmhuyenMaso) {
        this.dmhuyenMaso = dmhuyenMaso;
    }
//    public Collection<DmThon> getDmThonCollection() {
//        return dmThonCollection;
//    }
//
//    public void setDmThonCollection(Collection<DmThon> dmThonCollection) {
//        this.dmThonCollection = dmThonCollection;
//    }

//    public Collection<DmThon> getDmThonCollection1() {
//        return dmThonCollection1;
//    }
//
//    public void setDmThonCollection1(Collection<DmThon> dmThonCollection1) {
//        this.dmThonCollection1 = dmThonCollection1;
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
        hash += (dmxaMaso != null ? dmxaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmXa)) {
            return false;
        }
        DmXa other = (DmXa) object;
        if ((this.dmxaMaso == null && other.dmxaMaso != null) || (this.dmxaMaso != null && !this.dmxaMaso.equals(other.dmxaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmXa[dmxaMaso=" + dmxaMaso + "]";
    }

    public Boolean getDmxaDefa() {
        return dmxaDefa;
    }

    public void setDmxaDefa(Boolean dmxaDefa) {
        this.dmxaDefa = dmxaDefa;
    }
}


