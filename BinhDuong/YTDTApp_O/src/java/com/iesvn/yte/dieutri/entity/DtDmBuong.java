/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
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
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_BUONG")
@NamedQueries({})
public class DtDmBuong implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_BUONG_DTDMB_MASO")
    @SequenceGenerator(name = "DT_DM_BUONG_DTDMB_MASO", sequenceName = "DT_DM_BUONG_DTDMB_MASO_SEQ", allocationSize = 1)
    @Column(name = "DTDMB_MASO", nullable = false)
    private Integer dtdmbMaso;
    @Column(name = "DTDMB_MA", nullable = false)
    private String dtdmbMa;
    @Column(name = "DTDMB_TEN")
    private String dtdmbTen;
    @Column(name = "DTDMB_NGAYGIOCN")
    private Double dtdmbNgaygiocn;
    @Column(name = "DTDMB_CHON")
    private Boolean dtdmbChon;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;

    public DtDmBuong() {
    }

    public DtDmBuong(Integer dtdmbMaso) {
        this.dtdmbMaso = dtdmbMaso;
    }

    public DtDmBuong(Integer dtdmbMaso, String dtdmbMa) {
        this.dtdmbMaso = dtdmbMaso;
        this.dtdmbMa = dtdmbMa;
    }

    public Integer getDtdmbMaso() {
        return dtdmbMaso;
    }

    public void setDtdmbMaso(Integer dtdmbMaso) {
        this.dtdmbMaso = dtdmbMaso;
    }

    public String getDtdmbMa() {
        return dtdmbMa;
    }

    public void setDtdmbMa(String dtdmbMa) {
        this.dtdmbMa = dtdmbMa;
    }

    public String getDtdmbTen() {
        return dtdmbTen;
    }

    public void setDtdmbTen(String dtdmbTen) {
        this.dtdmbTen = dtdmbTen;
    }

    public Double getDtdmbNgaygiocn() {
        return dtdmbNgaygiocn;
    }

    public void setDtdmbNgaygiocn(Double dtdmbNgaygiocn) {
        this.dtdmbNgaygiocn = dtdmbNgaygiocn;
    }

    public Boolean getDtdmbChon() {
        return dtdmbChon;
    }

    public void setDtdmbChon(Boolean dtdmbChon) {
        this.dtdmbChon = dtdmbChon;
    }

    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }
    public DmKhoa getDmkhoaMaso(boolean create) {
        if (create && dmkhoaMaso == null) {
            dmkhoaMaso = new DmKhoa();
        }
        return dmkhoaMaso;
    }
    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmbMaso != null ? dtdmbMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmBuong)) {
            return false;
        }
        DtDmBuong other = (DtDmBuong) object;
        if ((this.dtdmbMaso == null && other.dtdmbMaso != null) || (this.dtdmbMaso != null && !this.dtdmbMaso.equals(other.dtdmbMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmBuong[dtdmbMaso=" + dtdmbMaso + "]";
    }

}
