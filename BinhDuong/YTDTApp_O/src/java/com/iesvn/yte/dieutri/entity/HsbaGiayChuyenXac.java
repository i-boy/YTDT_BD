/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmBenhIcd;
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
@Table(name = "HSBA_GIAY_CHUYEN_XAC")
@NamedQueries({
    @NamedQuery(name = "HsbaGiayChuyenXac.findAll", query = "SELECT h FROM HsbaGiayChuyenXac h"),
    @NamedQuery(name = "HsbaGiayChuyenXac.findByHsbagcxMaso", query = "SELECT h FROM HsbaGiayChuyenXac h WHERE h.hsbagcxMaso = :hsbagcxMaso"),
    @NamedQuery(name = "HsbaGiayChuyenXac.findByHsbagcxChandoan", query = "SELECT h FROM HsbaGiayChuyenXac h WHERE h.hsbagcxChandoan = :hsbagcxChandoan"),
    @NamedQuery(name = "HsbaGiayChuyenXac.findByHsbagcxNgaycap", query = "SELECT h FROM HsbaGiayChuyenXac h WHERE h.hsbagcxNgaycap = :hsbagcxNgaycap"),
    @NamedQuery(name = "HsbaGiayChuyenXac.findByHsbagcxNgaygiocn", query = "SELECT h FROM HsbaGiayChuyenXac h WHERE h.hsbagcxNgaygiocn = :hsbagcxNgaygiocn"),
    @NamedQuery(name = "HsbaGiayChuyenXac.findByHsbagcxMa", query = "SELECT h FROM HsbaGiayChuyenXac h WHERE h.hsbagcxMa = :hsbagcxMa")})
public class HsbaGiayChuyenXac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_GIAY_CHUYEN_XAC")
    @SequenceGenerator(name = "HSBA_GIAY_CHUYEN_XAC", sequenceName = "HSBA_GIAY_CHUYEN_XAC_HSBAGCX_M", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBAGCX_MASO", nullable = false)
    private Integer hsbagcxMaso;
    @Column(name = "HSBAGCX_CHANDOAN", length = 512)
    private String hsbagcxChandoan;
    @Column(name = "HSBAGCX_NGAYCAP")
    @Temporal(TemporalType.DATE)
    private Date hsbagcxNgaycap;
    @Column(name = "HSBAGCX_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbagcxNgaygiocn;
    @Column(name = "HSBAGCX_MA", length = 11)
    private String hsbagcxMa;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN", nullable = false)
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private Hsba hsbaSovaovien;

    @Column(name = "HSBAGCX_NGAYCX")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbagcxNgaycx;

    @Column(name = "HSBAGCX_NGAYVAOVIEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbagcxNgayvaovien;

    @JoinColumn(name = "HSBAGCX_CHUANDOANMA", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private DmBenhIcd hsbagcxChuandoanma;

    public DmBenhIcd getHsbagcxChuandoanma() {
        return hsbagcxChuandoanma;
    }

    public void setHsbagcxChuandoanma(DmBenhIcd hsbagcxChuandoanma) {
        this.hsbagcxChuandoanma = hsbagcxChuandoanma;
    }

    public DmBenhIcd getHsbagcxChuandoanma(boolean create) {
        if(create && hsbagcxChuandoanma == null) hsbagcxChuandoanma = new DmBenhIcd();
        return hsbagcxChuandoanma;
    }

    public Date getHsbagcxNgayvaovien() {
        return hsbagcxNgayvaovien;
    }

    public void setHsbagcxNgayvaovien(Date hsbagcxNgayvaovien) {
        this.hsbagcxNgayvaovien = hsbagcxNgayvaovien;
    }

    
    public HsbaGiayChuyenXac() {
    }

    public HsbaGiayChuyenXac(Integer hsbagcxMaso) {
        this.hsbagcxMaso = hsbagcxMaso;
    }

    public Integer getHsbagcxMaso() {
        return hsbagcxMaso;
    }

    public void setHsbagcxMaso(Integer hsbagcxMaso) {
        this.hsbagcxMaso = hsbagcxMaso;
    }

    public String getHsbagcxChandoan() {
        return hsbagcxChandoan;
    }

    public void setHsbagcxChandoan(String hsbagcxChandoan) {
        this.hsbagcxChandoan = hsbagcxChandoan;
    }

    public Date getHsbagcxNgaycap() {
        return hsbagcxNgaycap;
    }

    public void setHsbagcxNgaycap(Date hsbagcxNgaycap) {
        this.hsbagcxNgaycap = hsbagcxNgaycap;
    }

    public Date getHsbagcxNgaygiocn() {
        return hsbagcxNgaygiocn;
    }

    public void setHsbagcxNgaygiocn(Date hsbagcxNgaygiocn) {
        this.hsbagcxNgaygiocn = hsbagcxNgaygiocn;
    }

    public String getHsbagcxMa() {
        return hsbagcxMa;
    }

    public void setHsbagcxMa(String hsbagcxMa) {
        this.hsbagcxMa = hsbagcxMa;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public Hsba getHsbaSovaovien(boolean create) {
        if(create && hsbaSovaovien == null) hsbaSovaovien = new Hsba();
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
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

    public Date getHsbagcxNgaycx() {
        return hsbagcxNgaycx;
    }

    public void setHsbagcxNgaycx(Date hsbagcxNgaycx) {
        this.hsbagcxNgaycx = hsbagcxNgaycx;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbagcxMaso != null ? hsbagcxMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaGiayChuyenXac)) {
            return false;
        }
        HsbaGiayChuyenXac other = (HsbaGiayChuyenXac) object;
        if ((this.hsbagcxMaso == null && other.hsbagcxMaso != null) || (this.hsbagcxMaso != null && !this.hsbagcxMaso.equals(other.hsbagcxMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaGiayChuyenXac[hsbagcxMaso=" + hsbagcxMaso + "]";
    }

}
