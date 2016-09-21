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
@Table(name = "PHIEU_CHAM_SOC")
@NamedQueries({@NamedQuery(name = "PhieuChamSoc.findByPhieuchamsocMa", query = "SELECT p FROM PhieuChamSoc p WHERE p.phieuchamsocMa = :phieuchamsocMa"), @NamedQuery(name = "PhieuChamSoc.findByPhieuchamsocNgaygio", query = "SELECT p FROM PhieuChamSoc p WHERE p.phieuchamsocNgaygio = :phieuchamsocNgaygio"), @NamedQuery(name = "PhieuChamSoc.findByPhieuchamsocTheodoidienbien", query = "SELECT p FROM PhieuChamSoc p WHERE p.phieuchamsocTheodoidienbien = :phieuchamsocTheodoidienbien"), @NamedQuery(name = "PhieuChamSoc.findByPhieuchamsocThuchienchamsoc", query = "SELECT p FROM PhieuChamSoc p WHERE p.phieuchamsocThuchienchamsoc = :phieuchamsocThuchienchamsoc"), @NamedQuery(name = "PhieuChamSoc.findByPhieuchamsocMaso", query = "SELECT p FROM PhieuChamSoc p WHERE p.phieuchamsocMaso = :phieuchamsocMaso")})
public class PhieuChamSoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "PHIEUCHAMSOC_MA")
    private String phieuchamsocMa;
    @Column(name = "PHIEUCHAMSOC_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieuchamsocNgaygio;
    @Column(name = "PHIEUCHAMSOC_THEODOIDIENBIEN")
    private String phieuchamsocTheodoidienbien;
    @Column(name = "PHIEUCHAMSOC_TENBS")
    private String phieuchamsocTenbs;
    @Column(name = "PHIEUCHAMSOC_THUCHIENCHAMSOC")
    private String phieuchamsocThuchienchamsoc;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHIEU_CHAM_SOC_PHIEUCHAMSOC")
    @SequenceGenerator(name = "PHIEU_CHAM_SOC_PHIEUCHAMSOC", sequenceName = "PHIEU_CHAM_SOC_PHIEUCHAMSOC_MA", allocationSize = 1)
    @Column(name = "PHIEUCHAMSOC_MASO", nullable = false)
    private Integer phieuchamsocMaso;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien bacsi;

    public String getPhieuchamsocTenbs() {
        return phieuchamsocTenbs;
    }

    public void setPhieuchamsocTenbs(String phieuchamsocTenbs) {
        this.phieuchamsocTenbs = phieuchamsocTenbs;
    }

    
    public PhieuChamSoc() {
    }

    public PhieuChamSoc(Integer phieuchamsocMaso) {
        this.phieuchamsocMaso = phieuchamsocMaso;
    }

    public String getPhieuchamsocMa() {
        return phieuchamsocMa;
    }

    public void setPhieuchamsocMa(String phieuchamsocMa) {
        this.phieuchamsocMa = phieuchamsocMa;
    }

    public Date getPhieuchamsocNgaygio() {
        return phieuchamsocNgaygio;
    }

    public void setPhieuchamsocNgaygio(Date phieuchamsocNgaygio) {
        this.phieuchamsocNgaygio = phieuchamsocNgaygio;
    }

    public String getPhieuchamsocTheodoidienbien() {
        return phieuchamsocTheodoidienbien;
    }

    public void setPhieuchamsocTheodoidienbien(String phieuchamsocTheodoidienbien) {
        this.phieuchamsocTheodoidienbien = phieuchamsocTheodoidienbien;
    }

    public String getPhieuchamsocThuchienchamsoc() {
        return phieuchamsocThuchienchamsoc;
    }

    public void setPhieuchamsocThuchienchamsoc(String phieuchamsocThuchienchamsoc) {
        this.phieuchamsocThuchienchamsoc = phieuchamsocThuchienchamsoc;
    }

    public Integer getPhieuchamsocMaso() {
        return phieuchamsocMaso;
    }

    public void setPhieuchamsocMaso(Integer phieuchamsocMaso) {
        this.phieuchamsocMaso = phieuchamsocMaso;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DtDmNhanVien getBacsi() {
        return bacsi;
    }

    public void setBacsi(DtDmNhanVien bacsi) {
        this.bacsi = bacsi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phieuchamsocMaso != null ? phieuchamsocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuChamSoc)) {
            return false;
        }
        PhieuChamSoc other = (PhieuChamSoc) object;
        if ((this.phieuchamsocMaso == null && other.phieuchamsocMaso != null) || (this.phieuchamsocMaso != null && !this.phieuchamsocMaso.equals(other.phieuchamsocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.PhieuChamSoc[phieuchamsocMaso=" + phieuchamsocMaso + "]";
    }
}
