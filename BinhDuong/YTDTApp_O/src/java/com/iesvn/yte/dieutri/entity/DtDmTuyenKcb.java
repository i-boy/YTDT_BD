/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

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
@Table(name = "DT_DM_TUYEN_KCB")
@NamedQueries({})
public class DtDmTuyenKcb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_TUYEN_KCB")
    @SequenceGenerator(name = "DT_DM_TUYEN_KCB", sequenceName = "DT_DM_TUYEN_KCB_DTDMTUYENKCB_M", allocationSize = 1)
    @Column(name = "DTDMTUYENKCB_MASO", nullable = false)
    private Integer dtdmtuyenkcbMaso;
    @Column(name = "DTDMTUYENKCB_MA")
    private String dtdmtuyenkcbMa;
    @Column(name = "DTDMTUYENKCB_TEN", nullable = false)
    private String dtdmtuyenkcbTen;
    @Column(name = "DTDMTUYENKCB_NGAYGIOCN")
    private Double dtdmtuyenkcbNgaygiocn;
    @Column(name = "DTDMTUYENKCB_CHON")
    private Boolean dtdmtuyenkcbChon;
//    @OneToMany(mappedBy = "dtdmtuyenkcbMaso")
//    private Collection<DtDmKcbBhyt> dtDmKcbBhytCollection;
//    @OneToMany(mappedBy = "dtdmtuyenkcbMaso1")
//    private Collection<DtDmKcbBhyt> dtDmKcbBhytCollection1;
    public DtDmTuyenKcb() {
    }

    public DtDmTuyenKcb(Integer dtdmtuyenkcbMaso) {
        this.dtdmtuyenkcbMaso = dtdmtuyenkcbMaso;
    }

    public DtDmTuyenKcb(Integer dtdmtuyenkcbMaso, String dtdmtuyenkcbTen) {
        this.dtdmtuyenkcbMaso = dtdmtuyenkcbMaso;
        this.dtdmtuyenkcbTen = dtdmtuyenkcbTen;
    }

    public Integer getDtdmtuyenkcbMaso() {
        return dtdmtuyenkcbMaso;
    }

    public void setDtdmtuyenkcbMaso(Integer dtdmtuyenkcbMaso) {
        this.dtdmtuyenkcbMaso = dtdmtuyenkcbMaso;
    }

    public String getDtdmtuyenkcbMa() {
        return dtdmtuyenkcbMa;
    }

    public void setDtdmtuyenkcbMa(String dtdmtuyenkcbMa) {
        this.dtdmtuyenkcbMa = dtdmtuyenkcbMa;
    }

    public String getDtdmtuyenkcbTen() {
        return dtdmtuyenkcbTen;
    }

    public void setDtdmtuyenkcbTen(String dtdmtuyenkcbTen) {
        this.dtdmtuyenkcbTen = dtdmtuyenkcbTen;
    }

    public Double getDtdmtuyenkcbNgaygiocn() {
        return dtdmtuyenkcbNgaygiocn;
    }

    public void setDtdmtuyenkcbNgaygiocn(Double dtdmtuyenkcbNgaygiocn) {
        this.dtdmtuyenkcbNgaygiocn = dtdmtuyenkcbNgaygiocn;
    }

    public Boolean getDtdmtuyenkcbChon() {
        return dtdmtuyenkcbChon;
    }

    public void setDtdmtuyenkcbChon(Boolean dtdmtuyenkcbChon) {
        this.dtdmtuyenkcbChon = dtdmtuyenkcbChon;
    }

//    public Collection<DtDmKcbBhyt> getDtDmKcbBhytCollection() {
//        return dtDmKcbBhytCollection;
//    }
//
//    public void setDtDmKcbBhytCollection(Collection<DtDmKcbBhyt> dtDmKcbBhytCollection) {
//        this.dtDmKcbBhytCollection = dtDmKcbBhytCollection;
//    }

//    public Collection<DtDmKcbBhyt> getDtDmKcbBhytCollection1() {
//        return dtDmKcbBhytCollection1;
//    }
//
//    public void setDtDmKcbBhytCollection1(Collection<DtDmKcbBhyt> dtDmKcbBhytCollection1) {
//        this.dtDmKcbBhytCollection1 = dtDmKcbBhytCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmtuyenkcbMaso != null ? dtdmtuyenkcbMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmTuyenKcb)) {
            return false;
        }
        DtDmTuyenKcb other = (DtDmTuyenKcb) object;
        if ((this.dtdmtuyenkcbMaso == null && other.dtdmtuyenkcbMaso != null) || (this.dtdmtuyenkcbMaso != null && !this.dtdmtuyenkcbMaso.equals(other.dtdmtuyenkcbMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmTuyenKcb[dtdmtuyenkcbMaso=" + dtdmtuyenkcbMaso + "]";
    }
}


