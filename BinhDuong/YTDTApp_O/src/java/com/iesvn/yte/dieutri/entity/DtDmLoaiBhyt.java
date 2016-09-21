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
@Table(name = "DT_DM_LOAI_BHYT")
@NamedQueries({})
public class DtDmLoaiBhyt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_LOAI_BHYT")
    @SequenceGenerator(name = "DT_DM_LOAI_BHYT", sequenceName = "DT_DM_LOAI_BHYT_DTDMLOAIBHYT_M", allocationSize = 1)
    @Column(name = "DTDMLOAIBHYT_MASO", nullable = false)
    private Integer dtdmloaibhytMaso;
    @Column(name = "DTDMLOAIBHYT_MA", nullable = false)
    private String dtdmloaibhytMa;
    @Column(name = "DTDMLOAIBHYT_TEN")
    private String dtdmloaibhytTen;
    @Column(name = "DTDMLOAIBHYT_GHICHU")
    private String dtdmloaibhytGhichu;
    @Column(name = "DTDMLOAIBHYT_NGAYGIOCN")
    private Double dtdmloaibhytNgaygiocn;
    @Column(name = "DTDMLOAIBHYT_CHON")
    private Boolean dtdmloaibhytChon;
//    @OneToMany(mappedBy = "dtdmkhoibhytLoai")
//    private Collection<DtDmKhoiBhyt> dtDmKhoiBhytCollection;
//    @OneToMany(mappedBy = "dtdmkhoibhytLoai1")
//    private Collection<DtDmKhoiBhyt> dtDmKhoiBhytCollection1;
    public DtDmLoaiBhyt() {
    }

    public DtDmLoaiBhyt(Integer dtdmloaibhytMaso) {
        this.dtdmloaibhytMaso = dtdmloaibhytMaso;
    }

    public DtDmLoaiBhyt(Integer dtdmloaibhytMaso, String dtdmloaibhytMa) {
        this.dtdmloaibhytMaso = dtdmloaibhytMaso;
        this.dtdmloaibhytMa = dtdmloaibhytMa;
    }

    public Integer getDtdmloaibhytMaso() {
        return dtdmloaibhytMaso;
    }

    public void setDtdmloaibhytMaso(Integer dtdmloaibhytMaso) {
        this.dtdmloaibhytMaso = dtdmloaibhytMaso;
    }

    public String getDtdmloaibhytMa() {
        return dtdmloaibhytMa;
    }

    public void setDtdmloaibhytMa(String dtdmloaibhytMa) {
        this.dtdmloaibhytMa = dtdmloaibhytMa;
    }

    public String getDtdmloaibhytTen() {
        return dtdmloaibhytTen;
    }

    public void setDtdmloaibhytTen(String dtdmloaibhytTen) {
        this.dtdmloaibhytTen = dtdmloaibhytTen;
    }

    public String getDtdmloaibhytGhichu() {
        return dtdmloaibhytGhichu;
    }

    public void setDtdmloaibhytGhichu(String dtdmloaibhytGhichu) {
        this.dtdmloaibhytGhichu = dtdmloaibhytGhichu;
    }

    public Double getDtdmloaibhytNgaygiocn() {
        return dtdmloaibhytNgaygiocn;
    }

    public void setDtdmloaibhytNgaygiocn(Double dtdmloaibhytNgaygiocn) {
        this.dtdmloaibhytNgaygiocn = dtdmloaibhytNgaygiocn;
    }

    public Boolean getDtdmloaibhytChon() {
        return dtdmloaibhytChon;
    }

    public void setDtdmloaibhytChon(Boolean dtdmloaibhytChon) {
        this.dtdmloaibhytChon = dtdmloaibhytChon;
    }

//    public Collection<DtDmKhoiBhyt> getDtDmKhoiBhytCollection() {
//        return dtDmKhoiBhytCollection;
//    }
//
//    public void setDtDmKhoiBhytCollection(Collection<DtDmKhoiBhyt> dtDmKhoiBhytCollection) {
//        this.dtDmKhoiBhytCollection = dtDmKhoiBhytCollection;
//    }

//    public Collection<DtDmKhoiBhyt> getDtDmKhoiBhytCollection1() {
//        return dtDmKhoiBhytCollection1;
//    }
//
//    public void setDtDmKhoiBhytCollection1(Collection<DtDmKhoiBhyt> dtDmKhoiBhytCollection1) {
//        this.dtDmKhoiBhytCollection1 = dtDmKhoiBhytCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmloaibhytMaso != null ? dtdmloaibhytMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmLoaiBhyt)) {
            return false;
        }
        DtDmLoaiBhyt other = (DtDmLoaiBhyt) object;
        if ((this.dtdmloaibhytMaso == null && other.dtdmloaibhytMaso != null) || (this.dtdmloaibhytMaso != null && !this.dtdmloaibhytMaso.equals(other.dtdmloaibhytMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmLoaiBhyt[dtdmloaibhytMaso=" + dtdmloaibhytMaso + "]";
    }
}


