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
@Table(name = "DM_DAN_TOC")
@NamedQueries({})
public class DmDanToc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_DAN_TOC")
    @SequenceGenerator(name = "DM_DAN_TOC", sequenceName = "DM_DAN_TOC_DMDANTOC_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMDANTOC_MASO", nullable = false)
    private Integer dmdantocMaso;
    @Column(name = "DMDANTOC_MA")
    private String dmdantocMa;
    @Column(name = "DMDANTOC_TEN")
    private String dmdantocTen;
    @Column(name = "DMDANTOC_DEFA")
    private Boolean dmdantocDefa;
    @Column(name = "DMDANTOC_MIENPHI")
    private Boolean dmdantocMienphi;
    @Column(name = "DMDANTOC_NGAYGIOCN")
    private Double dmdantocNgaygiocn;
    @Column(name = "DMDANTOC_CHON")
    private Boolean dmdantocChon;
//    @OneToMany(mappedBy = "dantocMa")
//    private Collection<BenhNhan> benhNhanCollection;
//    @OneToMany(mappedBy = "dantocMa1")
//    private Collection<BenhNhan> benhNhanCollection1;

    public DmDanToc() {
    }

    public DmDanToc(Integer dmdantocMaso) {
        this.dmdantocMaso = dmdantocMaso;
    }

    public Integer getDmdantocMaso() {
        return dmdantocMaso;
    }

    public void setDmdantocMaso(Integer dmdantocMaso) {
        this.dmdantocMaso = dmdantocMaso;
    }

    public String getDmdantocMa() {
        return dmdantocMa;
    }

    public void setDmdantocMa(String dmdantocMa) {
        this.dmdantocMa = dmdantocMa;
    }

    public String getDmdantocTen() {
        return dmdantocTen;
    }

    public void setDmdantocTen(String dmdantocTen) {
        this.dmdantocTen = dmdantocTen;
    }

    public Boolean getDmdantocDefa() {
        return dmdantocDefa;
    }

    public void setDmdantocDefa(Boolean dmdantocDefa) {
        this.dmdantocDefa = dmdantocDefa;
    }

    public Boolean getDmdantocMienphi() {
        return dmdantocMienphi;
    }

    public void setDmdantocMienphi(Boolean dmdantocMienphi) {
        this.dmdantocMienphi = dmdantocMienphi;
    }

    public Double getDmdantocNgaygiocn() {
        return dmdantocNgaygiocn;
    }

    public void setDmdantocNgaygiocn(Double dmdantocNgaygiocn) {
        this.dmdantocNgaygiocn = dmdantocNgaygiocn;
    }

    public Boolean getDmdantocChon() {
        return dmdantocChon;
    }

    public void setDmdantocChon(Boolean dmdantocChon) {
        this.dmdantocChon = dmdantocChon;
    }

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
        hash += (dmdantocMaso != null ? dmdantocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmDanToc)) {
            return false;
        }
        DmDanToc other = (DmDanToc) object;
        if ((this.dmdantocMaso == null && other.dmdantocMaso != null) || (this.dmdantocMaso != null && !this.dmdantocMaso.equals(other.dmdantocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmDanToc[dmdantocMaso=" + dmdantocMaso + "]";
    }

}


