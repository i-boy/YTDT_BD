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
@Table(name = "HSBA_GIAY_TOM_TAT")
@NamedQueries({})
public class HsbaGiayTomTat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_GIAY_TOM_TAT")
    @SequenceGenerator(name = "HSBA_GIAY_TOM_TAT", sequenceName = "HSBA_GIAY_TOM_TAT_HSBAGIAYTOMT", allocationSize = 1)
    @Column(name = "HSBAGIAYTOMTAT_MASO", nullable = false)
    private Integer hsbagiaytomtatMaso;
    @Column(name = "HSBAGTT_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbagttNgaygiocn;
    @Column(name = "HSBAGTT_NOIYEUCAU")
    private String hsbagttNoiyeucau;
    @Column(name = "HSBAGTT_NGAYYC")
    @Temporal(TemporalType.DATE)
    private Date hsbagttNgayyc;
    @Column(name = "HSBAGTT_NOIDUNG")
    private String hsbagttNoidung;
    @Column(name = "HSBAGIAYTOMTAT_MA")
    private String hsbagiaytomtatMa;
    @Column(name = "HSBAGTT_DBLS")
    private String hsbagttDbls;
    @Column(name = "HSBAGTT_XNCLS")
    private String hsbagttXncls;
    @Column(name = "HSBAGTT_QTDT")
    private String hsbagttQtdt;
    @Column(name = "HSBAGTT_DGKQ")
    private String hsbagttDgkq;
    @Column(name = "HSBAGTT_HDTT")
    private String hsbagttHdtt;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;


    public HsbaGiayTomTat() {
    }

    public HsbaGiayTomTat(Integer hsbagiaytomtatMaso) {
        this.hsbagiaytomtatMaso = hsbagiaytomtatMaso;
    }

    public Integer getHsbagiaytomtatMaso() {
        return hsbagiaytomtatMaso;
    }

    public void setHsbagiaytomtatMaso(Integer hsbagiaytomtatMaso) {
        this.hsbagiaytomtatMaso = hsbagiaytomtatMaso;
    }

    public Date getHsbagttNgaygiocn() {
        return hsbagttNgaygiocn;
    }

    public void setHsbagttNgaygiocn(Date hsbagttNgaygiocn) {
        this.hsbagttNgaygiocn = hsbagttNgaygiocn;
    }

    public String getHsbagttNoiyeucau() {
        return hsbagttNoiyeucau;
    }

    public void setHsbagttNoiyeucau(String hsbagttNoiyeucau) {
        this.hsbagttNoiyeucau = hsbagttNoiyeucau;
    }

    public Date getHsbagttNgayyc() {
        return hsbagttNgayyc;
    }

    public void setHsbagttNgayyc(Date hsbagttNgayyc) {
        this.hsbagttNgayyc = hsbagttNgayyc;
    }

    public String getHsbagttNoidung() {
        return hsbagttNoidung;
    }

    public void setHsbagttNoidung(String hsbagttNoidung) {
        this.hsbagttNoidung = hsbagttNoidung;
    }

    public String getHsbagiaytomtatMa() {
        return hsbagiaytomtatMa;
    }

    public void setHsbagiaytomtatMa(String hsbagiaytomtatMa) {
        this.hsbagiaytomtatMa = hsbagiaytomtatMa;
    }
    
    public String getHsbagttDbls() {
        return hsbagttDbls;
    }

    public void setHsbagttDbls(String hsbagttDbls) {
        this.hsbagttDbls = hsbagttDbls;
    }

    public String getHsbagttXncls() {
        return hsbagttXncls;
    }

    public void setHsbagttXncls(String hsbagttXncls) {
        this.hsbagttXncls = hsbagttXncls;
    }

    public String getHsbagttQtdt() {
        return hsbagttQtdt;
    }

    public void setHsbagttQtdt(String hsbagttQtdt) {
        this.hsbagttQtdt = hsbagttQtdt;
    }

    public String getHsbagttDgkq() {
        return hsbagttDgkq;
    }

    public void setHsbagttDgkq(String hsbagttDgkq) {
        this.hsbagttDgkq = hsbagttDgkq;
    }

    public String getHsbagttHdtt() {
        return hsbagttHdtt;
    }

    public void setHsbagttHdtt(String hsbagttHdtt) {
        this.hsbagttHdtt = hsbagttHdtt;
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbagiaytomtatMaso != null ? hsbagiaytomtatMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaGiayTomTat)) {
            return false;
        }
        HsbaGiayTomTat other = (HsbaGiayTomTat) object;
        if ((this.hsbagiaytomtatMaso == null && other.hsbagiaytomtatMaso != null) || (this.hsbagiaytomtatMaso != null && !this.hsbagiaytomtatMaso.equals(other.hsbagiaytomtatMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaGiayTomTat[hsbagiaytomtatMaso=" + hsbagiaytomtatMaso + "]";
    }

}


