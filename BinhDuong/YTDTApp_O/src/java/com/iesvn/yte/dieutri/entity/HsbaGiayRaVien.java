/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_GIAY_RA_VIEN")
@NamedQueries({})
public class HsbaGiayRaVien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_GIAY_RA_VIEN")
    @SequenceGenerator(name = "HSBA_GIAY_RA_VIEN", sequenceName = "HSBA_GIAY_RA_VIEN_HSBAGRV_MASO", allocationSize = 1)
    @Column(name = "HSBAGRV_MASO", nullable = false)
    private Integer hsbagrvMaso;
    @Column(name = "HSBAGRV_CHANDOAN")
    private String hsbagrvChandoan;
    @Column(name = "HSBAGRV_PPDIEUTRI")
    private String hsbagrvPpdieutri;
    @Column(name = "HSBAGRV_TTRV")
    private String hsbagrvTtrv;
    @Column(name = "HSBAGRV_LOIDAN")
    private String hsbagrvLoidan;
    @Column(name = "HSBAGRV_TAIKHAM")
    private Integer hsbagrvTaikham;
    @Column(name = "HSBAGRV_NGAYCAP")
    @Temporal(TemporalType.DATE)
    private Date hsbagrvNgaycap;
    @Column(name = "HSBAGRV_NGAYTAIKHAM")
    @Temporal(TemporalType.DATE)
    private Date hsbagrvNgaytaikham;
    @Column(name = "HSBAGRV_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbagrvNgaygiocn;
    @Column(name = "HSBAGRV_MA")
    private String hsbagrvMa;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;


    public HsbaGiayRaVien() {
    }

    public HsbaGiayRaVien(Integer hsbagrvMaso) {
        this.hsbagrvMaso = hsbagrvMaso;
    }

    public Integer getHsbagrvMaso() {
        return hsbagrvMaso;
    }

    public void setHsbagrvMaso(Integer hsbagrvMaso) {
        this.hsbagrvMaso = hsbagrvMaso;
    }

    public String getHsbagrvChandoan() {
        return hsbagrvChandoan;
    }

    public void setHsbagrvChandoan(String hsbagrvChandoan) {
        this.hsbagrvChandoan = hsbagrvChandoan;
    }

    public String getHsbagrvPpdieutri() {
        return hsbagrvPpdieutri;
    }

    public void setHsbagrvPpdieutri(String hsbagrvPpdieutri) {
        this.hsbagrvPpdieutri = hsbagrvPpdieutri;
    }

    public String getHsbagrvLoidan() {
        return hsbagrvLoidan;
    }

    public void setHsbagrvLoidan(String hsbagrvLoidan) {
        this.hsbagrvLoidan = hsbagrvLoidan;
    }

    public Integer getHsbagrvTaikham() {
        return hsbagrvTaikham;
    }

    public void setHsbagrvTaikham(Integer hsbagrvTaikham) {
        this.hsbagrvTaikham = hsbagrvTaikham;
    }

    public Date getHsbagrvNgaycap() {
        return hsbagrvNgaycap;
    }

    public void setHsbagrvNgaycap(Date hsbagrvNgaycap) {
        this.hsbagrvNgaycap = hsbagrvNgaycap;
    }

    public Date getHsbagrvNgaygiocn() {
        return hsbagrvNgaygiocn;
    }

    public void setHsbagrvNgaygiocn(Date hsbagrvNgaygiocn) {
        this.hsbagrvNgaygiocn = hsbagrvNgaygiocn;
    }

    public String getHsbagrvMa() {
        return hsbagrvMa;
    }

    public void setHsbagrvMa(String hsbagrvMa) {
        this.hsbagrvMa = hsbagrvMa;
    }

    public Hsba getHsbaSovaovien(boolean create) {
if(create && hsbaSovaovien == null) hsbaSovaovien = new Hsba();
return hsbaSovaovien;
}
    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DtDmNhanVien getNhanvienMa(boolean create) {
if(create && nhanvienMa == null) nhanvienMa = new DtDmNhanVien();
return nhanvienMa;
}
    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    public Date getHsbagrvNgaytaikham() {
        return hsbagrvNgaytaikham;
    }

    public void setHsbagrvNgaytaikham(Date hsbagrvNgaytaikham) {
        this.hsbagrvNgaytaikham = hsbagrvNgaytaikham;
    }

    public String getHsbagrvTtrv() {
        return hsbagrvTtrv;
    }

    public void setHsbagrvTtrv(String hsbagrvTtrv) {
        this.hsbagrvTtrv = hsbagrvTtrv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbagrvMaso != null ? hsbagrvMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaGiayRaVien)) {
            return false;
        }
        HsbaGiayRaVien other = (HsbaGiayRaVien) object;
        if ((this.hsbagrvMaso == null && other.hsbagrvMaso != null) || (this.hsbagrvMaso != null && !this.hsbagrvMaso.equals(other.hsbagrvMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsbaGiayRaVien[hsbagrvMaso=" + hsbagrvMaso + "]";
    }

}


