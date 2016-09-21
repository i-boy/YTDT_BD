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
@Table(name = "HSBA_PHIEU_PHAU_THUAT_TT_BACSI")
@NamedQueries({
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuatBacsi.findAll", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuatBacsi h"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuatBacsi.findByHsbapptttbsMaso", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuatBacsi h WHERE h.hsbapptttbsMaso = :hsbapptttbsMaso")})
public class HsbaPhieuPhauThuatThuThuatBacsi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_PHIEU_PTTT_BACSI")
    @SequenceGenerator(name = "HSBA_PHIEU_PTTT_BACSI", sequenceName = "HSBA_PHIEU_PHAU_THUAT_TT_BAC_1", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBAPPTTTBS_MASO")
    private Integer hsbapptttbsMaso;
    @JoinColumn(name = "DTDMNHANVIEN_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienMaso;
    @JoinColumn(name = "HSBAPPTTT_MASO", referencedColumnName = "HSBAPPTTT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaPhieuPhauThuatThuThuat hsbapptttMaso;

    public HsbaPhieuPhauThuatThuThuatBacsi() {
    }

    public HsbaPhieuPhauThuatThuThuatBacsi(Integer hsbapptttbsMaso) {
        this.hsbapptttbsMaso = hsbapptttbsMaso;
    }

    public Integer getHsbapptttbsMaso() {
        return hsbapptttbsMaso;
    }

    public void setHsbapptttbsMaso(Integer hsbapptttbsMaso) {
        this.hsbapptttbsMaso = hsbapptttbsMaso;
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
        hash += (hsbapptttbsMaso != null ? hsbapptttbsMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaPhieuPhauThuatThuThuatBacsi)) {
            return false;
        }
        HsbaPhieuPhauThuatThuThuatBacsi other = (HsbaPhieuPhauThuatThuThuatBacsi) object;
        if ((this.hsbapptttbsMaso == null && other.hsbapptttbsMaso != null) || (this.hsbapptttbsMaso != null && !this.hsbapptttbsMaso.equals(other.hsbapptttbsMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuatBacsi[hsbapptttbsMaso=" + hsbapptttbsMaso + "]";
    }

}
