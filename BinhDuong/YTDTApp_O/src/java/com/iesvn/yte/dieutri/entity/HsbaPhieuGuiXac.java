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
@Table(name = "HSBA_PHIEU_GUI_XAC")
@NamedQueries({
    @NamedQuery(name = "HsbaPhieuGuiXac.findAll", query = "SELECT h FROM HsbaPhieuGuiXac h"),
    @NamedQuery(name = "HsbaPhieuGuiXac.findByHsbapgxMaso", query = "SELECT h FROM HsbaPhieuGuiXac h WHERE h.hsbapgxMaso = :hsbapgxMaso"),
    @NamedQuery(name = "HsbaPhieuGuiXac.findByHsbapgxChandoan", query = "SELECT h FROM HsbaPhieuGuiXac h WHERE h.hsbapgxChandoan = :hsbapgxChandoan"),
    @NamedQuery(name = "HsbaPhieuGuiXac.findByHsbapgxNgaycap", query = "SELECT h FROM HsbaPhieuGuiXac h WHERE h.hsbapgxNgaycap = :hsbapgxNgaycap"),
    @NamedQuery(name = "HsbaPhieuGuiXac.findByHsbapgxNgaygiocn", query = "SELECT h FROM HsbaPhieuGuiXac h WHERE h.hsbapgxNgaygiocn = :hsbapgxNgaygiocn"),
    @NamedQuery(name = "HsbaPhieuGuiXac.findByHsbapgxMa", query = "SELECT h FROM HsbaPhieuGuiXac h WHERE h.hsbapgxMa = :hsbapgxMa"),
    @NamedQuery(name = "HsbaPhieuGuiXac.findByHsbapgxNgaycx", query = "SELECT h FROM HsbaPhieuGuiXac h WHERE h.hsbapgxNgaycx = :hsbapgxNgaycx"),
    @NamedQuery(name = "HsbaPhieuGuiXac.findByHsbapgxNgayvaovien", query = "SELECT h FROM HsbaPhieuGuiXac h WHERE h.hsbapgxNgayvaovien = :hsbapgxNgayvaovien")})
public class HsbaPhieuGuiXac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_PHIEU_GUI_XAC")
    @SequenceGenerator(name = "HSBA_PHIEU_GUI_XAC", sequenceName = "HSBA_PHIEU_GUI_XAC_HSBAPGX_MAS", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBAPGX_MASO")
    private Integer hsbapgxMaso;
    @Column(name = "HSBAPGX_CHANDOAN")
    private String hsbapgxChandoan;
    @Column(name = "HSBAPGX_NGAYCAP")
    @Temporal(TemporalType.DATE)
    private Date hsbapgxNgaycap;
    @Column(name = "HSBAPGX_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbapgxNgaygiocn;
    @Column(name = "HSBAPGX_MA")
    private String hsbapgxMa;
    @Column(name = "HSBAPGX_NGAYCX")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbapgxNgaycx;
    @Column(name = "HSBAPGX_NGAYVAOVIEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbapgxNgayvaovien;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "HSBAPGX_CHUANDOANMA", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private DmBenhIcd hsbapgxChuandoanma;

    public HsbaPhieuGuiXac() {
    }

    public HsbaPhieuGuiXac(Integer hsbapgxMaso) {
        this.hsbapgxMaso = hsbapgxMaso;
    }

    public Integer getHsbapgxMaso() {
        return hsbapgxMaso;
    }

    public void setHsbapgxMaso(Integer hsbapgxMaso) {
        this.hsbapgxMaso = hsbapgxMaso;
    }

    public String getHsbapgxChandoan() {
        return hsbapgxChandoan;
    }

    public void setHsbapgxChandoan(String hsbapgxChandoan) {
        this.hsbapgxChandoan = hsbapgxChandoan;
    }

    public Date getHsbapgxNgaycap() {
        return hsbapgxNgaycap;
    }

    public void setHsbapgxNgaycap(Date hsbapgxNgaycap) {
        this.hsbapgxNgaycap = hsbapgxNgaycap;
    }

    public Date getHsbapgxNgaygiocn() {
        return hsbapgxNgaygiocn;
    }

    public void setHsbapgxNgaygiocn(Date hsbapgxNgaygiocn) {
        this.hsbapgxNgaygiocn = hsbapgxNgaygiocn;
    }

    public String getHsbapgxMa() {
        return hsbapgxMa;
    }

    public void setHsbapgxMa(String hsbapgxMa) {
        this.hsbapgxMa = hsbapgxMa;
    }

    public Date getHsbapgxNgaycx() {
        return hsbapgxNgaycx;
    }

    public void setHsbapgxNgaycx(Date hsbapgxNgaycx) {
        this.hsbapgxNgaycx = hsbapgxNgaycx;
    }

    public Date getHsbapgxNgayvaovien() {
        return hsbapgxNgayvaovien;
    }

    public void setHsbapgxNgayvaovien(Date hsbapgxNgayvaovien) {
        this.hsbapgxNgayvaovien = hsbapgxNgayvaovien;
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

    public DmBenhIcd getHsbapgxChuandoanma() {
        return hsbapgxChuandoanma;
    }

    public void setHsbapgxChuandoanma(DmBenhIcd hsbapgxChuandoanma) {
        this.hsbapgxChuandoanma = hsbapgxChuandoanma;
    }

    public DmBenhIcd getHsbapgxChuandoanma(boolean create) {
        if(create && hsbapgxChuandoanma == null) hsbapgxChuandoanma = new DmBenhIcd();
        return hsbapgxChuandoanma;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbapgxMaso != null ? hsbapgxMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaPhieuGuiXac)) {
            return false;
        }
        HsbaPhieuGuiXac other = (HsbaPhieuGuiXac) object;
        if ((this.hsbapgxMaso == null && other.hsbapgxMaso != null) || (this.hsbapgxMaso != null && !this.hsbapgxMaso.equals(other.hsbapgxMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaPhieuGuiXac[hsbapgxMaso=" + hsbapgxMaso + "]";
    }

}
