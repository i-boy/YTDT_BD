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
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_CAP_CUU_PHIEN")
@NamedQueries({})
public class DtDmCapCuuPhien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_CAP_CUU_PHIEN_DTDMCCP")
    @SequenceGenerator(name = "DT_DM_CAP_CUU_PHIEN_DTDMCCP", sequenceName = "DT_DM_CAP_CUU_PHIEN_DTDMCCP_MA", allocationSize = 1)
    @Column(name = "DTDMCCP_MASO", nullable = false)
    private Integer dtdmccpMaso;
    @Column(name = "DTDMCCP_MA", nullable = false)
    private String dtdmccpMa;
    @Column(name = "DTDMCCP_TEN", nullable = false)
    private String dtdmccpTen;
    @Column(name = "DTDMCCP_NGAYGIOCN")
    private Double dtdmccpNgaygiocn;
    @Column(name = "DTDMCCP_CHON")
    private Boolean dtdmccpChon;
//    @OneToMany(mappedBy = "hsbamoCcphien")
//    private Collection<HsbaMo> hsbaMoCollection;
//    @OneToMany(mappedBy = "hsbamoCcphien1")
//    private Collection<HsbaMo> hsbaMoCollection1;
    
    public DtDmCapCuuPhien() {
    }

    public DtDmCapCuuPhien(Integer dtdmccpMaso) {
        this.dtdmccpMaso = dtdmccpMaso;
    }

    public DtDmCapCuuPhien(Integer dtdmccpMaso, String dtdmccpMa, String dtdmccpTen) {
        this.dtdmccpMaso = dtdmccpMaso;
        this.dtdmccpMa = dtdmccpMa;
        this.dtdmccpTen = dtdmccpTen;
    }

    public Integer getDtdmccpMaso() {
        return dtdmccpMaso;
    }

    public void setDtdmccpMaso(Integer dtdmccpMaso) {
        this.dtdmccpMaso = dtdmccpMaso;
    }

    public String getDtdmccpMa() {
        return dtdmccpMa;
    }

    public void setDtdmccpMa(String dtdmccpMa) {
        this.dtdmccpMa = dtdmccpMa;
    }

    public String getDtdmccpTen() {
        return dtdmccpTen;
    }

    public void setDtdmccpTen(String dtdmccpTen) {
        this.dtdmccpTen = dtdmccpTen;
    }

    public Double getDtdmccpNgaygiocn() {
        return dtdmccpNgaygiocn;
    }

    public void setDtdmccpNgaygiocn(Double dtdmccpNgaygiocn) {
        this.dtdmccpNgaygiocn = dtdmccpNgaygiocn;
    }

    public Boolean getDtdmccpChon() {
        return dtdmccpChon;
    }

    public void setDtdmccpChon(Boolean dtdmccpChon) {
        this.dtdmccpChon = dtdmccpChon;
    }

//    public Collection<HsbaMo> getHsbaMoCollection() {
//        return hsbaMoCollection;
//    }
//
//    public void setHsbaMoCollection(Collection<HsbaMo> hsbaMoCollection) {
//        this.hsbaMoCollection = hsbaMoCollection;
//    }

//    public Collection<HsbaMo> getHsbaMoCollection1() {
//        return hsbaMoCollection1;
//    }
//
//    public void setHsbaMoCollection1(Collection<HsbaMo> hsbaMoCollection1) {
//        this.hsbaMoCollection1 = hsbaMoCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmccpMaso != null ? dtdmccpMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmCapCuuPhien)) {
            return false;
        }
        DtDmCapCuuPhien other = (DtDmCapCuuPhien) object;
        if ((this.dtdmccpMaso == null && other.dtdmccpMaso != null) || (this.dtdmccpMaso != null && !this.dtdmccpMaso.equals(other.dtdmccpMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmCapCuuPhien[dtdmccpMaso=" + dtdmccpMaso + "]";
    }
}


