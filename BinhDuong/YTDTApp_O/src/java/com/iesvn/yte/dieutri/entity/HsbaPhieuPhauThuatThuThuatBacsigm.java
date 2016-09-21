/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author quang
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_PHIEU_PHAU_THUAT_TT_BSIGM")
@NamedQueries({
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuatBacsigm.findAll", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuatBacsigm h"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuatBacsigm.findByHsbapptttbsgmMaso", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuatBacsigm h WHERE h.hsbapptttbsgmMaso = :hsbapptttbsgmMaso")})
public class HsbaPhieuPhauThuatThuThuatBacsigm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_PHIEU_PTTT_BACSIGM")
    @SequenceGenerator(name = "HSBA_PHIEU_PTTT_BACSIGM", sequenceName = "HSBA_PHIEU_PHAU_THUAT_TT_BSI_1", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBAPPTTTBSGM_MASO")
    private Integer hsbapptttbsgmMaso;
    @JoinColumn(name = "HSBAPPTTT_MASO", referencedColumnName = "HSBAPPTTT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaPhieuPhauThuatThuThuat hsbapptttMaso;
    @JoinColumn(name = "DTDMNHANVIEN_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienMaso;

    public HsbaPhieuPhauThuatThuThuatBacsigm() {
    }

    public HsbaPhieuPhauThuatThuThuatBacsigm(Integer hsbapptttbsgmMaso) {
        this.hsbapptttbsgmMaso = hsbapptttbsgmMaso;
    }

    public Integer getHsbapptttbsgmMaso() {
        return hsbapptttbsgmMaso;
    }

    public void setHsbapptttbsgmMaso(Integer hsbapptttbsgmMaso) {
        this.hsbapptttbsgmMaso = hsbapptttbsgmMaso;
    }

    public DtDmNhanVien getDtdmnhanvienMaso() {
        return dtdmnhanvienMaso;
    }

    public void setDtdmnhanvienMaso(DtDmNhanVien dtdmnhanvienMaso) {
        this.dtdmnhanvienMaso = dtdmnhanvienMaso;
    }

    public HsbaPhieuPhauThuatThuThuat getHsbapptttMaso() {
        return hsbapptttMaso;
    }

    public void setHsbapptttMaso(HsbaPhieuPhauThuatThuThuat hsbapptttMaso) {
        this.hsbapptttMaso = hsbapptttMaso;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbapptttbsgmMaso != null ? hsbapptttbsgmMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaPhieuPhauThuatThuThuatBacsigm)) {
            return false;
        }
        HsbaPhieuPhauThuatThuThuatBacsigm other = (HsbaPhieuPhauThuatThuThuatBacsigm) object;
        if ((this.hsbapptttbsgmMaso == null && other.hsbapptttbsgmMaso != null) || (this.hsbapptttbsgmMaso != null && !this.hsbapptttbsgmMaso.equals(other.hsbapptttbsgmMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuatBacsigm[hsbapptttbsgmMaso=" + hsbapptttbsgmMaso + "]";
    }

}
