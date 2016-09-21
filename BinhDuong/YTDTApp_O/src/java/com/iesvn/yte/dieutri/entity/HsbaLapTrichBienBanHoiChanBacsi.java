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
@Table(name = "HSBA_LAP_TRICH_BIEN_BAN_HC_BSI")
@NamedQueries({
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChanBacsi.findAll", query = "SELECT h FROM HsbaLapTrichBienBanHoiChanBacsi h"),
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChanBacsi.findByHsbaltbbhcbsMaso", query = "SELECT h FROM HsbaLapTrichBienBanHoiChanBacsi h WHERE h.hsbaltbbhcbsMaso = :hsbaltbbhcbsMaso")})
public class HsbaLapTrichBienBanHoiChanBacsi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_LAP_TRICH_BBHC_BACSI")
    @SequenceGenerator(name = "HSBA_LAP_TRICH_BBHC_BACSI", sequenceName = "HSBA_LAP_TRICH_BIEN_BAN_HC_B_1", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBALTBBHCBS_MASO")
    private Integer hsbaltbbhcbsMaso;
    @JoinColumn(name = "DTDMNHANVIEN_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienMaso;
    @JoinColumn(name = "HSBALTBBHC_MASO", referencedColumnName = "HSBALTBBHC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaLapTrichBienBanHoiChan hsbaltbbhcMaso;

    public HsbaLapTrichBienBanHoiChanBacsi() {
    }

    public HsbaLapTrichBienBanHoiChanBacsi(Integer hsbaltbbhcbsMaso) {
        this.hsbaltbbhcbsMaso = hsbaltbbhcbsMaso;
    }

    public Integer getHsbaltbbhcbsMaso() {
        return hsbaltbbhcbsMaso;
    }

    public void setHsbaltbbhcbsMaso(Integer hsbaltbbhcbsMaso) {
        this.hsbaltbbhcbsMaso = hsbaltbbhcbsMaso;
    }

    public DtDmNhanVien getDtdmnhanvienMaso() {
        return dtdmnhanvienMaso;
    }

    public void setDtdmnhanvienMaso(DtDmNhanVien dtdmnhanvienMaso) {
        this.dtdmnhanvienMaso = dtdmnhanvienMaso;
    }

    public HsbaLapTrichBienBanHoiChan getHsbaltbbhcMaso() {
        return hsbaltbbhcMaso;
    }

    public void setHsbaltbbhcMaso(HsbaLapTrichBienBanHoiChan hsbaltbbhcMaso) {
        this.hsbaltbbhcMaso = hsbaltbbhcMaso;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbaltbbhcbsMaso != null ? hsbaltbbhcbsMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaLapTrichBienBanHoiChanBacsi)) {
            return false;
        }
        HsbaLapTrichBienBanHoiChanBacsi other = (HsbaLapTrichBienBanHoiChanBacsi) object;
        if ((this.hsbaltbbhcbsMaso == null && other.hsbaltbbhcbsMaso != null) || (this.hsbaltbbhcbsMaso != null && !this.hsbaltbbhcbsMaso.equals(other.hsbaltbbhcbsMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaLapTrichBienBanHoiChanBacsi[hsbaltbbhcbsMaso=" + hsbaltbbhcbsMaso + "]";
    }

}
