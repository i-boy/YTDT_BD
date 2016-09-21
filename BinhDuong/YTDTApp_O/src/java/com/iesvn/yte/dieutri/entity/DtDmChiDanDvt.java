/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmDonViTinh;
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
@Table(name = "DT_DM_CHI_DAN_DVT")
@NamedQueries({})
public class DtDmChiDanDvt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_CHI_DAN_DVT")
    @SequenceGenerator(name = "DT_DM_CHI_DAN_DVT", sequenceName = "DT_DM_CHI_DAN_DVT_DTDMCHIDANDV", allocationSize = 1)
    @Column(name = "DTDMCHIDANDVT_MASO", nullable = false)
    private Integer dtdmchidandvtMaso;
    @JoinColumn(name = "DTDMCHIDAN_MASO", referencedColumnName = "DTDMCHIDAN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmChiDan dtdmchidanMaso;
    @JoinColumn(name = "DMDONVITINH_MASO", referencedColumnName = "DMDONVITINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDonViTinh dmdonvitinhMaso;
    @Column(name = "DTDMCHIDANDVT_NGAYGIOCN")
    private Double dtdmchidandvtNgaygiocn;
    @Column(name = "DTDMCHIDANDVT_CHON")
    private Boolean dtdmchidandvtChon;
    
    public DtDmChiDanDvt() {
    }

    public DtDmChiDanDvt(Integer dtdmchidandvtMaso) {
        this.dtdmchidandvtMaso = dtdmchidandvtMaso;
    }

    public Integer getDtdmchidandvtMaso() {
        return dtdmchidandvtMaso;
    }

    public void setDtdmchidandvtMaso(Integer dtdmchidandvtMaso) {
        this.dtdmchidandvtMaso = dtdmchidandvtMaso;
    }

    public DtDmChiDan getDtdmchidanMaso(boolean create) {
if(create && dtdmchidanMaso == null) dtdmchidanMaso = new DtDmChiDan();
return dtdmchidanMaso;
}
    public DtDmChiDan getDtdmchidanMaso() {
        return dtdmchidanMaso;
    }

    public void setDtdmchidanMaso(DtDmChiDan dtdmchidanMaso) {
        this.dtdmchidanMaso = dtdmchidanMaso;
    }

    public DmDonViTinh getDmdonvitinhMaso(boolean create) {
if(create && dmdonvitinhMaso == null) dmdonvitinhMaso = new DmDonViTinh();
return dmdonvitinhMaso;
}
    public DmDonViTinh getDmdonvitinhMaso() {
        return dmdonvitinhMaso;
    }

    public void setDmdonvitinhMaso(DmDonViTinh dmdonvitinhMaso) {
        this.dmdonvitinhMaso = dmdonvitinhMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmchidandvtMaso != null ? dtdmchidandvtMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmChiDanDvt)) {
            return false;
        }
        DtDmChiDanDvt other = (DtDmChiDanDvt) object;
        if ((this.dtdmchidandvtMaso == null && other.dtdmchidandvtMaso != null) || (this.dtdmchidandvtMaso != null && !this.dtdmchidandvtMaso.equals(other.dtdmchidandvtMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmChiDanDvt[dtdmchidandvtMaso=" + dtdmchidandvtMaso + "]";
    }

    public Double getDtdmchidandvtNgaygiocn() {
        return dtdmchidandvtNgaygiocn;
    }

    public void setDtdmchidandvtNgaygiocn(Double dtdmchidandvtNgaygiocn) {
        this.dtdmchidandvtNgaygiocn = dtdmchidandvtNgaygiocn;
    }

    public Boolean getDtdmchidandvtChon() {
        return dtdmchidandvtChon;
    }

    public void setDtdmchidandvtChon(Boolean dtdmchidandvtChon) {
        this.dtdmchidandvtChon = dtdmchidandvtChon;
    }
}


