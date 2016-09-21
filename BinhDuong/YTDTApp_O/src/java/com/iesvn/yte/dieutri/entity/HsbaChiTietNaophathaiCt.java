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
 * @author halylam
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_CHI_TIET_NAOPHATHAI_CT")
@NamedQueries({
    @NamedQuery(name = "HsbaChiTietNaophathaiCt.findAll", query = "SELECT h FROM HsbaChiTietNaophathaiCt h"),
    @NamedQuery(name = "HsbaChiTietNaophathaiCt.findByHsbactnaophathaictMaso", query = "SELECT h FROM HsbaChiTietNaophathaiCt h WHERE h.hsbactnaophathaictMaso = :hsbactnaophathaictMaso"),
    @NamedQuery(name = "HsbaChiTietNaophathaiCt.findByHsbactnaophathaictNgay", query = "SELECT h FROM HsbaChiTietNaophathaiCt h WHERE h.hsbactnaophathaictNgay = :hsbactnaophathaictNgay"),
    @NamedQuery(name = "HsbaChiTietNaophathaiCt.findByHsbactnaophathaictDienbien", query = "SELECT h FROM HsbaChiTietNaophathaiCt h WHERE h.hsbactnaophathaictDienbien = :hsbactnaophathaictDienbien"),
    @NamedQuery(name = "HsbaChiTietNaophathaiCt.findByHsbactnaophathaictThuoc", query = "SELECT h FROM HsbaChiTietNaophathaiCt h WHERE h.hsbactnaophathaictThuoc = :hsbactnaophathaictThuoc"),
    @NamedQuery(name = "HsbaChiTietNaophathaiCt.findByHsbactnaophathaictLieuluong", query = "SELECT h FROM HsbaChiTietNaophathaiCt h WHERE h.hsbactnaophathaictLieuluong = :hsbactnaophathaictLieuluong"),
    @NamedQuery(name = "HsbaChiTietNaophathaiCt.findByHsbactnaophathaictCachtiem", query = "SELECT h FROM HsbaChiTietNaophathaiCt h WHERE h.hsbactnaophathaictCachtiem = :hsbactnaophathaictCachtiem"),
    @NamedQuery(name = "HsbaChiTietNaophathaiCt.findByHsbactnaophathaictAnuongholy", query = "SELECT h FROM HsbaChiTietNaophathaiCt h WHERE h.hsbactnaophathaictAnuongholy = :hsbactnaophathaictAnuongholy")})
public class HsbaChiTietNaophathaiCt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_NAOPHATHAI_CT")
    @SequenceGenerator(name = "HSBA_CHI_TIET_NAOPHATHAI_CT", sequenceName = "HSBA_CHI_TIET_NAOPHATHAI_CT_HS", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTNAOPHATHAICT_MASO")
    private Integer hsbactnaophathaictMaso;
    @Column(name = "HSBACTNAOPHATHAICT_NGAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbactnaophathaictNgay;
    @Column(name = "HSBACTNAOPHATHAICT_DIENBIEN")
    private String hsbactnaophathaictDienbien;
    @Column(name = "HSBACTNAOPHATHAICT_THUOC")
    private String hsbactnaophathaictThuoc;
    @Column(name = "HSBACTNAOPHATHAICT_LIEULUONG")
    private String hsbactnaophathaictLieuluong;
    @Column(name = "HSBACTNAOPHATHAICT_CACHTIEM")
    private String hsbactnaophathaictCachtiem;
    @Column(name = "HSBACTNAOPHATHAICT_ANUONGHOLY")
    private String hsbactnaophathaictAnuongholy;
    @JoinColumn(name = "HSBACTNAOPHATHAI_MA", referencedColumnName = "HSBACTNAOPHATHAI_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChiTietNaophathai hsbactnaophathaiMa;

    public HsbaChiTietNaophathaiCt() {
    }

    public HsbaChiTietNaophathaiCt(Integer hsbactnaophathaictMaso) {
        this.hsbactnaophathaictMaso = hsbactnaophathaictMaso;
    }

    public Integer getHsbactnaophathaictMaso() {
        return hsbactnaophathaictMaso;
    }

    public void setHsbactnaophathaictMaso(Integer hsbactnaophathaictMaso) {
        this.hsbactnaophathaictMaso = hsbactnaophathaictMaso;
    }

    public Date getHsbactnaophathaictNgay() {
        return hsbactnaophathaictNgay;
    }

    public void setHsbactnaophathaictNgay(Date hsbactnaophathaictNgay) {
        this.hsbactnaophathaictNgay = hsbactnaophathaictNgay;
    }

    public String getHsbactnaophathaictDienbien() {
        return hsbactnaophathaictDienbien;
    }

    public void setHsbactnaophathaictDienbien(String hsbactnaophathaictDienbien) {
        this.hsbactnaophathaictDienbien = hsbactnaophathaictDienbien;
    }

    public String getHsbactnaophathaictThuoc() {
        return hsbactnaophathaictThuoc;
    }

    public void setHsbactnaophathaictThuoc(String hsbactnaophathaictThuoc) {
        this.hsbactnaophathaictThuoc = hsbactnaophathaictThuoc;
    }

    public String getHsbactnaophathaictLieuluong() {
        return hsbactnaophathaictLieuluong;
    }

    public void setHsbactnaophathaictLieuluong(String hsbactnaophathaictLieuluong) {
        this.hsbactnaophathaictLieuluong = hsbactnaophathaictLieuluong;
    }

    public String getHsbactnaophathaictCachtiem() {
        return hsbactnaophathaictCachtiem;
    }

    public void setHsbactnaophathaictCachtiem(String hsbactnaophathaictCachtiem) {
        this.hsbactnaophathaictCachtiem = hsbactnaophathaictCachtiem;
    }

    public String getHsbactnaophathaictAnuongholy() {
        return hsbactnaophathaictAnuongholy;
    }

    public void setHsbactnaophathaictAnuongholy(String hsbactnaophathaictAnuongholy) {
        this.hsbactnaophathaictAnuongholy = hsbactnaophathaictAnuongholy;
    }

    public HsbaChiTietNaophathai getHsbactnaophathaiMa() {
        return hsbactnaophathaiMa;
    }

    public void setHsbactnaophathaiMa(HsbaChiTietNaophathai hsbactnaophathaiMa) {
        this.hsbactnaophathaiMa = hsbactnaophathaiMa;
    }

    public HsbaChiTietNaophathai getHsbactnaophathaiMa(boolean create) {
        if (create && getHsbactnaophathaiMa() == null) {
            setHsbactnaophathaiMa(new HsbaChiTietNaophathai());
        }
        return getHsbactnaophathaiMa();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbactnaophathaictMaso != null ? hsbactnaophathaictMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietNaophathaiCt)) {
            return false;
        }
        HsbaChiTietNaophathaiCt other = (HsbaChiTietNaophathaiCt) object;
        if ((this.hsbactnaophathaictMaso == null && other.hsbactnaophathaictMaso != null) || (this.hsbactnaophathaictMaso != null && !this.hsbactnaophathaictMaso.equals(other.hsbactnaophathaictMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.test.HsbaChiTietNaophathaiCt[hsbactnaophathaictMaso=" + hsbactnaophathaictMaso + "]";
    }

}
