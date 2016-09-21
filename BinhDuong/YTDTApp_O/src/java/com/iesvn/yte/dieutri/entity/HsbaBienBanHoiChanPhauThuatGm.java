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
@Table(name = "HSBA_BIEN_BAN_HOI_CHAN_PT_GM")
@NamedQueries({
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuatGm.findAll", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuatGm h"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuatGm.findByHsbabbhcptgmMaso", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuatGm h WHERE h.hsbabbhcptgmMaso = :hsbabbhcptgmMaso")})
public class HsbaBienBanHoiChanPhauThuatGm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_BBHCPT_GAYME")
    @SequenceGenerator(name = "HSBA_BBHCPT_GAYME", sequenceName = "HSBA_BIEN_BAN_HOI_CHAN_PT_GM_H", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBABBHCPTGM_MASO")
    private Integer hsbabbhcptgmMaso;
    @JoinColumn(name = "HSBABBHCPT_MASO", referencedColumnName = "HSBABBHCPT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaBienBanHoiChanPhauThuat hsbabbhcptMaso;
    @JoinColumn(name = "DTDMNHANVIEN_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienMaso;

    public HsbaBienBanHoiChanPhauThuatGm() {
    }

    public HsbaBienBanHoiChanPhauThuatGm(Integer hsbabbhcptgmMaso) {
        this.hsbabbhcptgmMaso = hsbabbhcptgmMaso;
    }

    public Integer getHsbabbhcptgmMaso() {
        return hsbabbhcptgmMaso;
    }

    public void setHsbabbhcptgmMaso(Integer hsbabbhcptgmMaso) {
        this.hsbabbhcptgmMaso = hsbabbhcptgmMaso;
    }

    public DtDmNhanVien getDtdmnhanvienMaso() {
        return dtdmnhanvienMaso;
    }

    public void setDtdmnhanvienMaso(DtDmNhanVien dtdmnhanvienMaso) {
        this.dtdmnhanvienMaso = dtdmnhanvienMaso;
    }

   

    public HsbaBienBanHoiChanPhauThuat getHsbabbhcptMaso() {
        return hsbabbhcptMaso;
    }

    public void setHsbabbhcptMaso(HsbaBienBanHoiChanPhauThuat hsbabbhcptMaso) {
        this.hsbabbhcptMaso = hsbabbhcptMaso;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbabbhcptgmMaso != null ? hsbabbhcptgmMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaBienBanHoiChanPhauThuatGm)) {
            return false;
        }
        HsbaBienBanHoiChanPhauThuatGm other = (HsbaBienBanHoiChanPhauThuatGm) object;
        if ((this.hsbabbhcptgmMaso == null && other.hsbabbhcptgmMaso != null) || (this.hsbabbhcptgmMaso != null && !this.hsbabbhcptgmMaso.equals(other.hsbabbhcptgmMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuatGm[hsbabbhcptgmMaso=" + hsbabbhcptgmMaso + "]";
    }

}
