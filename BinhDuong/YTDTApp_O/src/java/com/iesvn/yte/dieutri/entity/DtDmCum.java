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
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_CUM")
@NamedQueries({})
public class DtDmCum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_CUM_DTDMCUM_MASO")
    @SequenceGenerator(name = "DT_DM_CUM_DTDMCUM_MASO", sequenceName = "DT_DM_CUM_DTDMCUM_MASO_SEQ", allocationSize = 1)
    @Column(name = "DTDMCUM_MASO", nullable = false)
    private Integer dtdmcumMaso;
    @Column(name = "DTDMCUM_MA", nullable = false)
    private String dtdmcumMa;
    @Column(name = "DTDMCUM_TEN", nullable = false)
    private String dtdmcumTen;
    @Column(name = "DTDMCUM_GHICHU")
    private String dtdmcumGhichu;
    @Column(name = "DTDMCUM_CHON")
    private Boolean dtdmcumChon;
    @Column(name = "DTDMCUM_NGAYGIOCN")
    private Double dtdmcumNgaygiocn;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmcumMa")
//    private Collection<PcCumThuPhi> pcCumThuPhiCollection;

    public DtDmCum() {
    }

    public DtDmCum(Integer dtdmcumMaso) {
        this.dtdmcumMaso = dtdmcumMaso;
    }

    public DtDmCum(Integer dtdmcumMaso, String dtdmcumTen) {
        this.dtdmcumMaso = dtdmcumMaso;
        this.dtdmcumTen = dtdmcumTen;
    }

    public Integer getDtdmcumMaso() {
        return dtdmcumMaso;
    }

    public void setDtdmcumMaso(Integer dtdmcumMaso) {
        this.dtdmcumMaso = dtdmcumMaso;
    }

    public String getDtdmcumTen() {
        return dtdmcumTen;
    }

    public void setDtdmcumTen(String dtdmcumTen) {
        this.dtdmcumTen = dtdmcumTen;
    }

    public String getDtdmcumGhichu() {
        return dtdmcumGhichu;
    }

    public void setDtdmcumGhichu(String dtdmcumGhichu) {
        this.dtdmcumGhichu = dtdmcumGhichu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmcumMaso != null ? dtdmcumMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmCum)) {
            return false;
        }
        DtDmCum other = (DtDmCum) object;
        if ((this.dtdmcumMaso == null && other.dtdmcumMaso != null) || (this.dtdmcumMaso != null && !this.dtdmcumMaso.equals(other.dtdmcumMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmCum[dtdmcumMaso=" + dtdmcumMaso + "]";
    }

    public String getDtdmcumMa() {
        return dtdmcumMa;
    }

    public void setDtdmcumMa(String dtdmcumMa) {
        this.dtdmcumMa = dtdmcumMa;
    }

    public Boolean getDtdmcumChon() {
        return dtdmcumChon;
    }

    public void setDtdmcumChon(Boolean dtdmcumChon) {
        this.dtdmcumChon = dtdmcumChon;
    }

    public Double getDtdmcumNgaygiocn() {
        return dtdmcumNgaygiocn;
    }

    public void setDtdmcumNgaygiocn(Double dtdmcumNgaygiocn) {
        this.dtdmcumNgaygiocn = dtdmcumNgaygiocn;
    }

}
