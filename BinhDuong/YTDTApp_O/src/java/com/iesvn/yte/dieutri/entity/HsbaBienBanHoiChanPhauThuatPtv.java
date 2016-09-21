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
@Table(name = "HSBA_BIEN_BAN_HOI_CHAN_PT_PTV")
@NamedQueries({
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuatPtv.findAll", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuatPtv h"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuatPtv.findByHsbabbhcptptvMaso", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuatPtv h WHERE h.hsbabbhcptptvMaso = :hsbabbhcptptvMaso")})
public class HsbaBienBanHoiChanPhauThuatPtv implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_BBHCPT_PTV")
    @SequenceGenerator(name = "HSBA_BBHCPT_PTV", sequenceName = "HSBA_BIEN_BAN_HOI_CHAN_PT_PTV_", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBABBHCPTPTV_MASO")
    private Integer hsbabbhcptptvMaso;
   @JoinColumn(name = "HSBABBHCPT_MASO", referencedColumnName = "HSBABBHCPT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaBienBanHoiChanPhauThuat hsbabbhcptMaso;
    @JoinColumn(name = "DTDMNHANVIEN_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienMaso;

    public HsbaBienBanHoiChanPhauThuatPtv() {
    }

    public HsbaBienBanHoiChanPhauThuatPtv(Integer hsbabbhcptptvMaso) {
        this.hsbabbhcptptvMaso = hsbabbhcptptvMaso;
    }

    public Integer getHsbabbhcptptvMaso() {
        return hsbabbhcptptvMaso;
    }

    public void setHsbabbhcptptvMaso(Integer hsbabbhcptptvMaso) {
        this.hsbabbhcptptvMaso = hsbabbhcptptvMaso;
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
        hash += (hsbabbhcptptvMaso != null ? hsbabbhcptptvMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaBienBanHoiChanPhauThuatPtv)) {
            return false;
        }
        HsbaBienBanHoiChanPhauThuatPtv other = (HsbaBienBanHoiChanPhauThuatPtv) object;
        if ((this.hsbabbhcptptvMaso == null && other.hsbabbhcptptvMaso != null) || (this.hsbabbhcptptvMaso != null && !this.hsbabbhcptptvMaso.equals(other.hsbabbhcptptvMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuatPtv[hsbabbhcptptvMaso=" + hsbabbhcptptvMaso + "]";
    }

}
