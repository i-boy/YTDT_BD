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
@Table(name = "DT_DM_NHOM_BHYT2")
@NamedQueries({})
public class DtDmNhomBHYT2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_NHOM_BHYT2")
    @SequenceGenerator(name = "DT_DM_NHOM_BHYT2", sequenceName = "DT_DM_NHOM_BHYT2_DTDMNHOMBHYT2", allocationSize = 1)
    @Column(name = "DTDMNHOMBHYT2_MASO", nullable = false)
    private Integer dtdmnhombhyt2Maso;
    @Column(name = "DTDMNHOMBHYT2_MA", nullable = false)
    private String dtdmnhombhyt2Ma;
   
    @Column(name = "DTDMNHOMBHYT2_TEN")
    private String dtdmnhombhyt2Ten;
    @Column(name = "DTDMNHOMBHYT2_NGAYGIOCN")
    private Double dtdmnhombhyt2Ngaygiocn;
    @Column(name = "DTDMNHOMBHYT2_CHON")
    private Boolean dtdmnhombhyt2Chon;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmchidanMaso")
//    private Collection<DtDmChiDanDvt> dtDmChiDanDvtCollection;
    public DtDmNhomBHYT2() {
    }

    public DtDmNhomBHYT2(Integer dtdmnhombhyt2Maso) {
        this.dtdmnhombhyt2Maso = dtdmnhombhyt2Maso;
    }

    public DtDmNhomBHYT2(Integer dtdmnhombhyt2Maso, String dtdmnhombhyt2Ma) {
        this.dtdmnhombhyt2Maso = dtdmnhombhyt2Maso;
        this.dtdmnhombhyt2Ma = dtdmnhombhyt2Ma;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getDtdmnhombhyt2Maso() != null ? getDtdmnhombhyt2Maso().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmNhomBHYT2)) {
            return false;
        }
        DtDmNhomBHYT2 other = (DtDmNhomBHYT2) object;
        if ((this.getDtdmnhombhyt2Maso() == null && other.getDtdmnhombhyt2Maso() != null) || (this.getDtdmnhombhyt2Maso() != null && !this.dtdmnhombhyt2Maso.equals(other.dtdmnhombhyt2Maso))) {
            return false;
        }
        return true;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return the dtdmnhombhyt2Maso
     */
    public Integer getDtdmnhombhyt2Maso() {
        return dtdmnhombhyt2Maso;
    }

    /**
     * @param dtdmnhombhyt2Maso the dtdmnhombhyt2Maso to set
     */
    public void setDtdmnhombhyt2Maso(Integer dtdmnhombhyt2Maso) {
        this.dtdmnhombhyt2Maso = dtdmnhombhyt2Maso;
    }

    /**
     * @return the dtdmnhombhyt2Ma
     */
    public String getDtdmnhombhyt2Ma() {
        return dtdmnhombhyt2Ma;
    }

    /**
     * @param dtdmnhombhyt2Ma the dtdmnhombhyt2Ma to set
     */
    public void setDtdmnhombhyt2Ma(String dtdmnhombhyt2Ma) {
        this.dtdmnhombhyt2Ma = dtdmnhombhyt2Ma;
    }

    /**
     * @return the dtdmnhombhyt2Ten
     */
    public String getDtdmnhombhyt2Ten() {
        return dtdmnhombhyt2Ten;
    }

    /**
     * @param dtdmnhombhyt2Ten the dtdmnhombhyt2Ten to set
     */
    public void setDtdmnhombhyt2Ten(String dtdmnhombhyt2Ten) {
        this.dtdmnhombhyt2Ten = dtdmnhombhyt2Ten;
    }

    /**
     * @return the dtdmnhombhyt2Ngaygiocn
     */
    public Double getDtdmnhombhyt2Ngaygiocn() {
        return dtdmnhombhyt2Ngaygiocn;
    }

    /**
     * @param dtdmnhombhyt2Ngaygiocn the dtdmnhombhyt2Ngaygiocn to set
     */
    public void setDtdmnhombhyt2Ngaygiocn(Double dtdmnhombhyt2Ngaygiocn) {
        this.dtdmnhombhyt2Ngaygiocn = dtdmnhombhyt2Ngaygiocn;
    }

    /**
     * @return the dtdmnhombhyt2Chon
     */
    public Boolean getDtdmnhombhyt2Chon() {
        return dtdmnhombhyt2Chon;
    }

    /**
     * @param dtdmnhombhyt2Chon the dtdmnhombhyt2Chon to set
     */
    public void setDtdmnhombhyt2Chon(Boolean dtdmnhombhyt2Chon) {
        this.dtdmnhombhyt2Chon = dtdmnhombhyt2Chon;
    }

    @Override
    public String toString() {
        return "temp.DtDmNhomBHYT2[dtdmnhombhyt2Maso=" + getDtdmnhombhyt2Maso() + "]";
    }
}


