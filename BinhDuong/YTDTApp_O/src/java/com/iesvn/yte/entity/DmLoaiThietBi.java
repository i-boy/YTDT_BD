/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_LOAI_THIET_BI")
@NamedQueries({})
public class DmLoaiThietBi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_THIET_BI")
    @SequenceGenerator(name = "DM_LOAI_THIET_BI", sequenceName = "DM_LOAI_THIET_BI_DMLOAITHIETBI", allocationSize = 1)
    @Column(name = "DMLOAITHIETBI_MASO", nullable = false)
    private Integer dmloaithietbiMaso;
    @Column(name = "DMLOAITHIETBI_MA")
    private String dmloaithietbiMa;
    @Column(name = "DMLOAITHIETBI_TEN", nullable = false)
    private String dmloaithietbiTen;
    @Column(name = "DMLOAITHIETBI_NGAYGIOCN")
    private Double dmloaithietbiNgaygiocn;
    @Column(name = "DMLOAITHIETBI_DT")
    private Boolean dmloaithietbiDt;
    @Column(name = "DMLOAITHIETBI_QL")
    private Boolean dmloaithietbiQl;
    @Column(name = "DMLOAITHIETBI_DP")
    private Boolean dmloaithietbiDp;

    public DmLoaiThietBi() {
    }

    public DmLoaiThietBi(Integer dmloaithietbiMaso) {
        this.dmloaithietbiMaso = dmloaithietbiMaso;
    }

    public DmLoaiThietBi(Integer dmloaithietbiMaso, String dmloaithietbiTen) {
        this.dmloaithietbiMaso = dmloaithietbiMaso;
        this.dmloaithietbiTen = dmloaithietbiTen;
    }

    public Integer getDmloaithietbiMaso() {
        return dmloaithietbiMaso;
    }

    public void setDmloaithietbiMaso(Integer dmloaithietbiMaso) {
        this.dmloaithietbiMaso = dmloaithietbiMaso;
    }

    public String getDmloaithietbiMa() {
        return dmloaithietbiMa;
    }

    public void setDmloaithietbiMa(String dmloaithietbiMa) {
        this.dmloaithietbiMa = dmloaithietbiMa;
    }

    public String getDmloaithietbiTen() {
        return dmloaithietbiTen;
    }

    public void setDmloaithietbiTen(String dmloaithietbiTen) {
        this.dmloaithietbiTen = dmloaithietbiTen;
    }

    public Double getDmloaithietbiNgaygiocn() {
        return dmloaithietbiNgaygiocn;
    }

    public void setDmloaithietbiNgaygiocn(Double dmloaithietbiNgaygiocn) {
        this.dmloaithietbiNgaygiocn = dmloaithietbiNgaygiocn;
    }

    public Boolean getDmloaithietbiDt() {
        return dmloaithietbiDt;
    }

    public void setDmloaithietbiDt(Boolean dmloaithietbiDt) {
        this.dmloaithietbiDt = dmloaithietbiDt;
    }

    public Boolean getDmloaithietbiQl() {
        return dmloaithietbiQl;
    }

    public void setDmloaithietbiQl(Boolean dmloaithietbiQl) {
        this.dmloaithietbiQl = dmloaithietbiQl;
    }

    public Boolean getDmloaithietbiDp() {
        return dmloaithietbiDp;
    }

    public void setDmloaithietbiDp(Boolean dmloaithietbiDp) {
        this.dmloaithietbiDp = dmloaithietbiDp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmloaithietbiMaso != null ? dmloaithietbiMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmLoaiThietBi)) {
            return false;
        }
        DmLoaiThietBi other = (DmLoaiThietBi) object;
        if ((this.dmloaithietbiMaso == null && other.dmloaithietbiMaso != null) || (this.dmloaithietbiMaso != null && !this.dmloaithietbiMaso.equals(other.dmloaithietbiMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmLoaiThietBi[dmloaithietbiMaso=" + dmloaithietbiMaso + "]";
    }

}


