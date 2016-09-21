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
@Table(name = "DM_GIOI_TINH")
@NamedQueries({})
public class DmGioiTinh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_GIOI_TINH")
    @SequenceGenerator(name = "DM_GIOI_TINH", sequenceName = "DM_GIOI_TINH_DMGT_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMGT_MASO", nullable = false)
    private Integer dmgtMaso;
    @Column(name = "DMGT_MA", nullable = false)
    private String dmgtMa;
    @Column(name = "DMGT_TEN", nullable = false)
    private String dmgtTen;
    @Column(name = "DMGT_DEFA", nullable = false)
    private Boolean dmgtDefa;
    @Column(name = "DMGT_GHICHU")
    private String dmgtGhichu;
    @Column(name = "DMGT_CHON", nullable = false)
    private boolean dmgtChon;
    @Column(name = "DMGT_NGAYGIOCN")
    private Double dmgtNgaygiocn;
//    @OneToMany(mappedBy = "dmgtMaso")
//    private Collection<BenhNhan> benhNhanCollection;
//    @OneToMany(mappedBy = "dmgtMaso1")
//    private Collection<BenhNhan> benhNhanCollection1;
    public DmGioiTinh() {
    }

    public DmGioiTinh(Integer dmgtMaso) {
        this.dmgtMaso = dmgtMaso;
    }

    public DmGioiTinh(Integer dmgtMaso, String dmgtMa, String dmgtTen, boolean dmgtChon) {
        this.dmgtMaso = dmgtMaso;
        this.dmgtMa = dmgtMa;
        this.dmgtTen = dmgtTen;
        this.dmgtChon = dmgtChon;
    }

    public Integer getDmgtMaso() {
        return dmgtMaso;
    }

    public void setDmgtMaso(Integer dmgtMaso) {
        this.dmgtMaso = dmgtMaso;
    }

    public String getDmgtMa() {
        return dmgtMa;
    }

    public void setDmgtMa(String dmgtMa) {
        this.dmgtMa = dmgtMa;
    }

    public String getDmgtTen() {
        return dmgtTen;
    }

    public void setDmgtTen(String dmgtTen) {
        this.dmgtTen = dmgtTen;
    }

    public String getDmgtGhichu() {
        return dmgtGhichu;
    }

    public void setDmgtGhichu(String dmgtGhichu) {
        this.dmgtGhichu = dmgtGhichu;
    }

    public boolean getDmgtChon() {
        return dmgtChon;
    }

    public void setDmgtChon(boolean dmgtChon) {
        this.dmgtChon = dmgtChon;
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
        hash += (dmgtMaso != null ? dmgtMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmGioiTinh)) {
            return false;
        }
        DmGioiTinh other = (DmGioiTinh) object;
        if ((this.dmgtMaso == null && other.dmgtMaso != null) || (this.dmgtMaso != null && !this.dmgtMaso.equals(other.dmgtMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmGioiTinh[dmgtMaso=" + dmgtMaso + "]";
    }

    public Boolean getDmgtDefa() {
        return dmgtDefa;
    }

    public void setDmgtDefa(Boolean dmgtDefa) {
        this.dmgtDefa = dmgtDefa;
    }

    public Double getDmgtNgaygiocn() {
        return dmgtNgaygiocn;
    }

    public void setDmgtNgaygiocn(Double dmgtNgaygiocn) {
        this.dmgtNgaygiocn = dmgtNgaygiocn;
    }
}


