/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_DOI_TUONG_AN")
@NamedQueries({})
public class DtDmDoiTuongAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_DOI_TUONG_AN")
    @SequenceGenerator(name = "DT_DM_DOI_TUONG_AN", sequenceName = "DT_DM_DOI_TUONG_AN_DTDMDTA_MAS", allocationSize = 1)
    @Column(name = "DTDMDTA_MASO", nullable = false)
    private Integer dtdmdtaMaso;
    @Column(name = "DTDMDTA_MA", nullable = false)
    private String dtdmdtaMa;
    @Column(name = "DTDMDTA_TEN")
    private String dtdmdtaTen;
    @Column(name = "DTDMDTA_NGAYGIOCN")
    private Double dtdmdtaNgaygiocn;
    @Column(name = "DTDMDTA_CHON")
    private Boolean dtdmdtaChon;

    public DtDmDoiTuongAn() {
    }

    public DtDmDoiTuongAn(Integer dtdmdtaMaso) {
        this.dtdmdtaMaso = dtdmdtaMaso;
    }

    public DtDmDoiTuongAn(Integer dtdmdtaMaso, String dtdmdtaMa) {
        this.dtdmdtaMaso = dtdmdtaMaso;
        this.dtdmdtaMa = dtdmdtaMa;
    }

    public Integer getDtdmdtaMaso() {
        return dtdmdtaMaso;
    }

    public void setDtdmdtaMaso(Integer dtdmdtaMaso) {
        this.dtdmdtaMaso = dtdmdtaMaso;
    }

    public String getDtdmdtaMa() {
        return dtdmdtaMa;
    }

    public void setDtdmdtaMa(String dtdmdtaMa) {
        this.dtdmdtaMa = dtdmdtaMa;
    }

    public String getDtdmdtaTen() {
        return dtdmdtaTen;
    }

    public void setDtdmdtaTen(String dtdmdtaTen) {
        this.dtdmdtaTen = dtdmdtaTen;
    }

    public Double getDtdmdtaNgaygiocn() {
        return dtdmdtaNgaygiocn;
    }

    public void setDtdmdtaNgaygiocn(Double dtdmdtaNgaygiocn) {
        this.dtdmdtaNgaygiocn = dtdmdtaNgaygiocn;
    }

    public Boolean getDtdmdtaChon() {
        return dtdmdtaChon;
    }

    public void setDtdmdtaChon(Boolean dtdmdtaChon) {
        this.dtdmdtaChon = dtdmdtaChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmdtaMaso != null ? dtdmdtaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmDoiTuongAn)) {
            return false;
        }
        DtDmDoiTuongAn other = (DtDmDoiTuongAn) object;
        if ((this.dtdmdtaMaso == null && other.dtdmdtaMaso != null) || (this.dtdmdtaMaso != null && !this.dtdmdtaMaso.equals(other.dtdmdtaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmDoiTuongAn[dtdmdtaMaso=" + dtdmdtaMaso + "]";
    }

}
