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
@Table(name = "HSBA_BIEN_BAN_HOI_CHAN_PT_TPK")
@NamedQueries({
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuatTpk.findAll", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuatTpk h"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuatTpk.findByHsbabbhcpttpkMaso", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuatTpk h WHERE h.hsbabbhcpttpkMaso = :hsbabbhcpttpkMaso")})
public class HsbaBienBanHoiChanPhauThuatTpk implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_BBHCPT_TPK")
    @SequenceGenerator(name = "HSBA_BBHCPT_TPK", sequenceName = "HSBA_BIEN_BAN_HOI_CHAN_PT_TPK_", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBABBHCPTTPK_MASO")
    private Integer hsbabbhcpttpkMaso;
    @JoinColumn(name = "HSBABBHCPT_MASO", referencedColumnName = "HSBABBHCPT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaBienBanHoiChanPhauThuat hsbabbhcptMaso;
    @JoinColumn(name = "DTDMNHANVIEN_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienMaso;

    public HsbaBienBanHoiChanPhauThuatTpk() {
    }

    public HsbaBienBanHoiChanPhauThuatTpk(Integer hsbabbhcpttpkMaso) {
        this.hsbabbhcpttpkMaso = hsbabbhcpttpkMaso;
    }

    public Integer getHsbabbhcpttpkMaso() {
        return hsbabbhcpttpkMaso;
    }

    public void setHsbabbhcpttpkMaso(Integer hsbabbhcpttpkMaso) {
        this.hsbabbhcpttpkMaso = hsbabbhcpttpkMaso;
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
        hash += (hsbabbhcpttpkMaso != null ? hsbabbhcpttpkMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaBienBanHoiChanPhauThuatTpk)) {
            return false;
        }
        HsbaBienBanHoiChanPhauThuatTpk other = (HsbaBienBanHoiChanPhauThuatTpk) object;
        if ((this.hsbabbhcpttpkMaso == null && other.hsbabbhcpttpkMaso != null) || (this.hsbabbhcpttpkMaso != null && !this.hsbabbhcpttpkMaso.equals(other.hsbabbhcpttpkMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuatTpk[hsbabbhcpttpkMaso=" + hsbabbhcpttpkMaso + "]";
    }

}
