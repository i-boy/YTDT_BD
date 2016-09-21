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
@Table(name = "DT_DM_PB_CLS")
@NamedQueries({})
public class DtDmPbCls implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_PB_CLS")
    @SequenceGenerator(name = "DT_DM_PB_CLS", sequenceName = "DT_DM_PB_CLS_SEQ", allocationSize = 1)
    @Column(name = "DTDMPBCLS_MASO", nullable = false)
    private Integer dtdmpbclsMaso;
    @Column(name = "DTDMPBCLS_MA", nullable = false)
    private String dtdmpbclsMa;
    @Column(name = "DTDMPBCLS_TEN")
    private String dtdmpbclsTen;
    @Column(name = "DTDMPBCLS_NGAYGIOCN")
    private Double dtdmpbclsNgaygiocn;
    @Column(name = "DTDMPBCLS_CHON")
    private Boolean dtdmpbclsChon;
//    @OneToMany(mappedBy = "dtdmclsPhanbiet")
//    private Collection<DtDmCls> dtDmClsCollection;
//    @OneToMany(mappedBy = "dtdmclsPhanbiet1")
//    private Collection<DtDmCls> dtDmClsCollection1;
    public DtDmPbCls() {
    }

    public DtDmPbCls(Integer dtdmpbclsMaso) {
        this.dtdmpbclsMaso = dtdmpbclsMaso;
    }

    public DtDmPbCls(Integer dtdmpbclsMaso, String dtdmpbclsMa) {
        this.dtdmpbclsMaso = dtdmpbclsMaso;
        this.dtdmpbclsMa = dtdmpbclsMa;
    }

    public Integer getDtdmpbclsMaso() {
        return dtdmpbclsMaso;
    }

    public void setDtdmpbclsMaso(Integer dtdmpbclsMaso) {
        this.dtdmpbclsMaso = dtdmpbclsMaso;
    }

    public String getDtdmpbclsMa() {
        return dtdmpbclsMa;
    }

    public void setDtdmpbclsMa(String dtdmpbclsMa) {
        this.dtdmpbclsMa = dtdmpbclsMa;
    }

    public String getDtdmpbclsTen() {
        return dtdmpbclsTen;
    }

    public void setDtdmpbclsTen(String dtdmpbclsTen) {
        this.dtdmpbclsTen = dtdmpbclsTen;
    }

    public Double getDtdmpbclsNgaygiocn() {
        return dtdmpbclsNgaygiocn;
    }

    public void setDtdmpbclsNgaygiocn(Double dtdmpbclsNgaygiocn) {
        this.dtdmpbclsNgaygiocn = dtdmpbclsNgaygiocn;
    }

    public Boolean getDtdmpbclsChon() {
        return dtdmpbclsChon;
    }

    public void setDtdmpbclsChon(Boolean dtdmpbclsChon) {
        this.dtdmpbclsChon = dtdmpbclsChon;
    }

//    public Collection<DtDmCls> getDtDmClsCollection() {
//        return dtDmClsCollection;
//    }
//
//    public void setDtDmClsCollection(Collection<DtDmCls> dtDmClsCollection) {
//        this.dtDmClsCollection = dtDmClsCollection;
//    }

//    public Collection<DtDmCls> getDtDmClsCollection1() {
//        return dtDmClsCollection1;
//    }
//
//    public void setDtDmClsCollection1(Collection<DtDmCls> dtDmClsCollection1) {
//        this.dtDmClsCollection1 = dtDmClsCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmpbclsMaso != null ? dtdmpbclsMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmPbCls)) {
            return false;
        }
        DtDmPbCls other = (DtDmPbCls) object;
        if ((this.dtdmpbclsMaso == null && other.dtdmpbclsMaso != null) || (this.dtdmpbclsMaso != null && !this.dtdmpbclsMaso.equals(other.dtdmpbclsMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmPbCls[dtdmpbclsMaso=" + dtdmpbclsMaso + "]";
    }
}


