/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_LOAI_AN_2")
@NamedQueries({})
public class DtDmLoaiAn2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dt_dm_loai_an_2")
    @SequenceGenerator(name = "dt_dm_loai_an_2", sequenceName = "DT_DM_LOAI_AN_2_DTDMLA2_MASO_S", allocationSize = 1)
    @Column(name = "DTDMLA2_MASO", nullable = false)
    private Integer dtdmla2Maso;
    @Column(name = "DTDMLA2_MA", nullable = false)
    private String dtdmla2Ma;
    @Column(name = "DTDMLA2_TEN")
    private String dtdmla2Ten;
    @Column(name = "DTDMLA2_NGAYGIOCN")
    private Double dtdmla2Ngaygiocn;
    @Column(name = "DTDMLA2_CHON")
    private Boolean dtdmla2Chon;
    @JoinColumn(name = "DTDMLA_MASO", referencedColumnName = "DTDMLA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiAn dtdmlaMaso;

    public DtDmLoaiAn2() {
    }

    public DtDmLoaiAn2(Integer dtdmla2Maso) {
        this.dtdmla2Maso = dtdmla2Maso;
    }

    public DtDmLoaiAn2(Integer dtdmla2Maso, String dtdmla2Ma) {
        this.dtdmla2Maso = dtdmla2Maso;
        this.dtdmla2Ma = dtdmla2Ma;
    }

    public Integer getDtdmla2Maso() {
        return dtdmla2Maso;
    }

    public void setDtdmla2Maso(Integer dtdmla2Maso) {
        this.dtdmla2Maso = dtdmla2Maso;
    }

    public String getDtdmla2Ma() {
        return dtdmla2Ma;
    }

    public void setDtdmla2Ma(String dtdmla2Ma) {
        this.dtdmla2Ma = dtdmla2Ma;
    }

    public String getDtdmla2Ten() {
        return dtdmla2Ten;
    }

    public void setDtdmla2Ten(String dtdmla2Ten) {
        this.dtdmla2Ten = dtdmla2Ten;
    }

    public Double getDtdmla2Ngaygiocn() {
        return dtdmla2Ngaygiocn;
    }

    public void setDtdmla2Ngaygiocn(Double dtdmla2Ngaygiocn) {
        this.dtdmla2Ngaygiocn = dtdmla2Ngaygiocn;
    }

    public Boolean getDtdmla2Chon() {
        return dtdmla2Chon;
    }

    public void setDtdmla2Chon(Boolean dtdmla2Chon) {
        this.dtdmla2Chon = dtdmla2Chon;
    }

    public DtDmLoaiAn getDtdmlaMaso() {
        return dtdmlaMaso;
    }

    public void setDtdmlaMaso(DtDmLoaiAn dtdmlaMaso) {
        this.dtdmlaMaso = dtdmlaMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmla2Maso != null ? dtdmla2Maso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmLoaiAn2)) {
            return false;
        }
        DtDmLoaiAn2 other = (DtDmLoaiAn2) object;
        if ((this.dtdmla2Maso == null && other.dtdmla2Maso != null) || (this.dtdmla2Maso != null && !this.dtdmla2Maso.equals(other.dtdmla2Maso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmLoaiAn2[dtdmla2Maso=" + dtdmla2Maso + "]";
    }

}
