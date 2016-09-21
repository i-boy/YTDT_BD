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
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_BAC_SI_BAN_KHAM")
@NamedQueries({})
public class DtDmBacSiBanKham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_BAC_SI_BAN_KHAM")
    @SequenceGenerator(name = "DT_DM_BAC_SI_BAN_KHAM", sequenceName = "DT_DM_BAC_SI_BAN_KHAM_DTDMBACS", allocationSize = 1)
    @Column(name = "DTDMBACSIBANKHAM_MASO", nullable = false)
    private Integer dtdmbacsibankhamMaso;
    @JoinColumn(name = "DTDMBANKHAM_MASO", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham dtdmbankhamMaso;
    @JoinColumn(name = "DTDMNHANVIEN_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienMaso;
    
     @Column(name = "DTDMNHANVIEN_CHON")
    private Boolean dtdmbankhamChon;
     
      @Column(name = "DTDMNHANVIEN_NGAYGIOCN")
    private Double dtdmbankhamNgaygiocn;
      
//    @Column(name = "DTDMBACSIBANKHAM_NGAYGIOCN")
//    private Double dtdmbacsibankhamNgaygiocn;
//    @Column(name = "DTDMBACSIBANKHAM_CHON")
//    private Boolean dtdmbacsibankhamChon;

    public DtDmBacSiBanKham() {
    }

    public DtDmBacSiBanKham(Integer dtdmbacsibankhamMaso) {
        this.dtdmbacsibankhamMaso = dtdmbacsibankhamMaso;
    }

    public Integer getDtdmbacsibankhamMaso() {
        return dtdmbacsibankhamMaso;
    }

    public void setDtdmbacsibankhamMaso(Integer dtdmbacsibankhamMaso) {
        this.dtdmbacsibankhamMaso = dtdmbacsibankhamMaso;
    }

    public DtDmBanKham getDtdmbankhamMaso(boolean create) {
if(create && dtdmbankhamMaso == null) dtdmbankhamMaso = new DtDmBanKham();
return dtdmbankhamMaso;
}
    public DtDmBanKham getDtdmbankhamMaso() {
        return dtdmbankhamMaso;
    }

    public void setDtdmbankhamMaso(DtDmBanKham dtdmbankhamMaso) {
        this.dtdmbankhamMaso = dtdmbankhamMaso;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmbacsibankhamMaso != null ? dtdmbacsibankhamMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmBacSiBanKham)) {
            return false;
        }
        DtDmBacSiBanKham other = (DtDmBacSiBanKham) object;
        if ((this.dtdmbacsibankhamMaso == null && other.dtdmbacsibankhamMaso != null) || (this.dtdmbacsibankhamMaso != null && !this.dtdmbacsibankhamMaso.equals(other.dtdmbacsibankhamMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmBacSiBanKham[dtdmbacsibankhamMaso=" + dtdmbacsibankhamMaso + "]";
    }

    public Boolean getDtdmbankhamChon() {
        return dtdmbankhamChon;
    }

    public void setDtdmbankhamChon(Boolean dtdmbankhamChon) {
        this.dtdmbankhamChon = dtdmbankhamChon;
    }

    public Double getDtdmbankhamNgaygiocn() {
        return dtdmbankhamNgaygiocn;
    }

    public void setDtdmbankhamNgaygiocn(Double dtdmbankhamNgaygiocn) {
        this.dtdmbankhamNgaygiocn = dtdmbankhamNgaygiocn;
    }
}


