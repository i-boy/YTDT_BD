/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "HSBA_BIEN_BAN_HOI_CHAN_TV")
@NamedQueries({})
public class HsbaBienBanHoiChanThanhVien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_BBHCPT_THANHVIEN")
    @SequenceGenerator(name = "HSBA_BBHCPT_THANHVIEN", sequenceName = "HSBA_BIEN_BAN_HOI_CHAN_TV_DTDM", allocationSize = 1)
    @Column(name = "DTDMNHANVIENHSBABBHC_MASO", nullable = false)
    private Integer dtdmnhanvienhsbabbhcMaso;
    @JoinColumn(name = "DTDMNHANVIEN_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienMaso;
    @JoinColumn(name = "HSBABBHC_MASO", referencedColumnName = "HSBABBHC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaBienBanHoiChan hsbabbhcMaso;

    public HsbaBienBanHoiChanThanhVien() {
    }

    public HsbaBienBanHoiChanThanhVien(Integer dtdmnhanvienhsbabbhcMaso) {
        this.dtdmnhanvienhsbabbhcMaso = dtdmnhanvienhsbabbhcMaso;
    }

    public Integer getDtdmnhanvienhsbabbhcMaso() {
        return dtdmnhanvienhsbabbhcMaso;
    }

    public void setDtdmnhanvienhsbabbhcMaso(Integer dtdmnhanvienhsbabbhcMaso) {
        this.dtdmnhanvienhsbabbhcMaso = dtdmnhanvienhsbabbhcMaso;
    }

    public DtDmNhanVien getDtdmnhanvienMaso(boolean create) {
if(create && dtdmnhanvienMaso == null) dtdmnhanvienMaso = new DtDmNhanVien();
return dtdmnhanvienMaso;
}
    public DtDmNhanVien getDtdmnhanvienMaso() {
        return dtdmnhanvienMaso;
    }

    public void setDtdmnhanvienMaso(DtDmNhanVien dtdmnhanvienMaso) {
        this.dtdmnhanvienMaso = dtdmnhanvienMaso;
    }

    public HsbaBienBanHoiChan getHsbabbhcMaso(boolean create) {
if(create && hsbabbhcMaso == null) hsbabbhcMaso = new HsbaBienBanHoiChan();
return hsbabbhcMaso;
}
    public HsbaBienBanHoiChan getHsbabbhcMaso() {
        return hsbabbhcMaso;
    }

    public void setHsbabbhcMaso(HsbaBienBanHoiChan hsbabbhcMaso) {
        this.hsbabbhcMaso = hsbabbhcMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmnhanvienhsbabbhcMaso != null ? dtdmnhanvienhsbabbhcMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaBienBanHoiChanThanhVien)) {
            return false;
        }
        HsbaBienBanHoiChanThanhVien other = (HsbaBienBanHoiChanThanhVien) object;
        if ((this.dtdmnhanvienhsbabbhcMaso == null && other.dtdmnhanvienhsbabbhcMaso != null) || (this.dtdmnhanvienhsbabbhcMaso != null && !this.dtdmnhanvienhsbabbhcMaso.equals(other.dtdmnhanvienhsbabbhcMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsbaBienBanHoiChanThanhVien[dtdmnhanvienhsbabbhcMaso=" + dtdmnhanvienhsbabbhcMaso + "]";
    }

}


