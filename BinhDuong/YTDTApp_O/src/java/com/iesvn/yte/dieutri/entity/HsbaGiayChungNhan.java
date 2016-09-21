/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author quang
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_GIAY_CHUNG_NHAN")
@NamedQueries({
    @NamedQuery(name = "HsbaGiayChungNhan.findAll", query = "SELECT h FROM HsbaGiayChungNhan h"),
    @NamedQuery(name = "HsbaGiayChungNhan.findByHsbagcnMaso", query = "SELECT h FROM HsbaGiayChungNhan h WHERE h.hsbagcnMaso = :hsbagcnMaso"),
    @NamedQuery(name = "HsbaGiayChungNhan.findByHsbagcnNgaygiocn", query = "SELECT h FROM HsbaGiayChungNhan h WHERE h.hsbagcnNgaygiocn = :hsbagcnNgaygiocn"),
    @NamedQuery(name = "HsbaGiayChungNhan.findByHsbagcnMa", query = "SELECT h FROM HsbaGiayChungNhan h WHERE h.hsbagcnMa = :hsbagcnMa"),
    @NamedQuery(name = "HsbaGiayChungNhan.findByHsbagcnNgaycmt", query = "SELECT h FROM HsbaGiayChungNhan h WHERE h.hsbagcnNgaycmt = :hsbagcnNgaycmt"),
    @NamedQuery(name = "HsbaGiayChungNhan.findByHsbagcnNoicmt", query = "SELECT h FROM HsbaGiayChungNhan h WHERE h.hsbagcnNoicmt = :hsbagcnNoicmt")})
public class HsbaGiayChungNhan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_GIAY_CHUNG_NHAN")
    @SequenceGenerator(name = "HSBA_GIAY_CHUNG_NHAN", sequenceName = "HSBA_GIAY_CHUNG_NHAN_HSBAGCN_M", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBAGCN_MASO")
    private Integer hsbagcnMaso;
    @Column(name = "HSBAGCN_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbagcnNgaygiocn;
    @Column(name = "HSBAGCN_MA")
    private String hsbagcnMa;
    @Column(name = "HSBAGCN_NGAYCMT")
    @Temporal(TemporalType.DATE)
    private Date hsbagcnNgaycmt;
    @Column(name = "HSBAGCN_NGAYCAP")
    @Temporal(TemporalType.DATE)
    private Date hsbagcnNgaycap;
    @Column(name = "HSBAGCN_NOICMT")
    private String hsbagcnNoicmt;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "HSBAGCN_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbagcnBacsi;

    public HsbaGiayChungNhan() {
    }

    public HsbaGiayChungNhan(Integer hsbagcnMaso) {
        this.hsbagcnMaso = hsbagcnMaso;
    }

    public Integer getHsbagcnMaso() {
        return hsbagcnMaso;
    }

    public void setHsbagcnMaso(Integer hsbagcnMaso) {
        this.hsbagcnMaso = hsbagcnMaso;
    }

    public Date getHsbagcnNgaygiocn() {
        return hsbagcnNgaygiocn;
    }

    public void setHsbagcnNgaygiocn(Date hsbagcnNgaygiocn) {
        this.hsbagcnNgaygiocn = hsbagcnNgaygiocn;
    }

    public String getHsbagcnMa() {
        return hsbagcnMa;
    }

    public void setHsbagcnMa(String hsbagcnMa) {
        this.hsbagcnMa = hsbagcnMa;
    }

    public Date getHsbagcnNgaycmt() {
        return hsbagcnNgaycmt;
    }

    public void setHsbagcnNgaycmt(Date hsbagcnNgaycmt) {
        this.hsbagcnNgaycmt = hsbagcnNgaycmt;
    }

    public String getHsbagcnNoicmt() {
        return hsbagcnNoicmt;
    }

    public void setHsbagcnNoicmt(String hsbagcnNoicmt) {
        this.hsbagcnNoicmt = hsbagcnNoicmt;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public Hsba getHsbaSovaovien(boolean create) {
        if(create && hsbaSovaovien == null) hsbaSovaovien = new Hsba();
        return hsbaSovaovien;
    }

    public DtDmNhanVien getHsbagcnBacsi() {
        return hsbagcnBacsi;
    }

    public DtDmNhanVien getHsbagcnBacsi(boolean create) {
        if(create && hsbagcnBacsi == null) hsbagcnBacsi = new DtDmNhanVien();
        return hsbagcnBacsi;
    }

    public void setHsbagcnBacsi(DtDmNhanVien hsbagcnBacsi) {
        this.hsbagcnBacsi = hsbagcnBacsi;
    }

    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public DtDmNhanVien getNhanvienMa(boolean create) {
        if(create && nhanvienMa == null) nhanvienMa = new DtDmNhanVien();
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    public Date getHsbagcnNgaycap() {
        return hsbagcnNgaycap;
    }

    public void setHsbagcnNgaycap(Date hsbagcnNgaycap) {
        this.hsbagcnNgaycap = hsbagcnNgaycap;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbagcnMaso != null ? hsbagcnMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaGiayChungNhan)) {
            return false;
        }
        HsbaGiayChungNhan other = (HsbaGiayChungNhan) object;
        if ((this.hsbagcnMaso == null && other.hsbagcnMaso != null) || (this.hsbagcnMaso != null && !this.hsbagcnMaso.equals(other.hsbagcnMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaGiayChungNhan[hsbagcnMaso=" + hsbagcnMaso + "]";
    }

}
