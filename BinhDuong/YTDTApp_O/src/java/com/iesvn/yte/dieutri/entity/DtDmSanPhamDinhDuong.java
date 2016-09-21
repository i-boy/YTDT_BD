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
@Table(name = "DT_DM_SAN_PHAM_DINH_DUONG")
@NamedQueries({})
public class DtDmSanPhamDinhDuong implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_SAN_PHAM_DINH_DUONG")
//    @SequenceGenerator(name = "DT_DM_SAN_PHAM_DINH_DUONG", sequenceName = "DT_DM_SAN_PHAM_DINH_DUONG_SEQ", allocationSize = 1)
    @Column(name = "DTDMSPDD_MASO", nullable = false)
    private Integer dtdmspddMaso;
    @Column(name = "DTDMSPDD_MA", nullable = false)
    private String dtdmspddMa;
    @Column(name = "DTDMSPDD_TEN")
    private String dtdmspddTen;
    @Column(name = "DTDMSPDD_NGAYGIOCN")
    private Double dtdmspddNgaygiocn;
    @Column(name = "DTDMSPDD_CHON")
    private Boolean dtdmspddChon;

    public DtDmSanPhamDinhDuong() {
    }

    public DtDmSanPhamDinhDuong(Integer dtdmspddMaso) {
        this.dtdmspddMaso = dtdmspddMaso;
    }

    public DtDmSanPhamDinhDuong(Integer dtdmspddMaso, String dtdmspddMa) {
        this.dtdmspddMaso = dtdmspddMaso;
        this.dtdmspddMa = dtdmspddMa;
    }

    public Integer getDtdmspddMaso() {
        return dtdmspddMaso;
    }

    public void setDtdmspddMaso(Integer dtdmspddMaso) {
        this.dtdmspddMaso = dtdmspddMaso;
    }

    public String getDtdmspddMa() {
        return dtdmspddMa;
    }

    public void setDtdmspddMa(String dtdmspddMa) {
        this.dtdmspddMa = dtdmspddMa;
    }

    public String getDtdmspddTen() {
        return dtdmspddTen;
    }

    public void setDtdmspddTen(String dtdmspddTen) {
        this.dtdmspddTen = dtdmspddTen;
    }

    public Double getDtdmspddNgaygiocn() {
        return dtdmspddNgaygiocn;
    }

    public void setDtdmspddNgaygiocn(Double dtdmspddNgaygiocn) {
        this.dtdmspddNgaygiocn = dtdmspddNgaygiocn;
    }

    public Boolean getDtdmspddChon() {
        return dtdmspddChon;
    }

    public void setDtdmspddChon(Boolean dtdmspddChon) {
        this.dtdmspddChon = dtdmspddChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmspddMaso != null ? dtdmspddMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmSanPhamDinhDuong)) {
            return false;
        }
        DtDmSanPhamDinhDuong other = (DtDmSanPhamDinhDuong) object;
        if ((this.dtdmspddMaso == null && other.dtdmspddMaso != null) || (this.dtdmspddMaso != null && !this.dtdmspddMaso.equals(other.dtdmspddMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmSanPhamDinhDuong[dtdmspddMaso=" + dtdmspddMaso + "]";
    }

}
