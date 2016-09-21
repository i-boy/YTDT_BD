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
 * @author HP
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "TO_DIEU_TRI")
@NamedQueries({@NamedQuery(name = "ToDieuTri.findByTodieutriMa", query = "SELECT t FROM ToDieuTri t WHERE t.todieutriMa = :todieutriMa"), @NamedQuery(name = "ToDieuTri.findByTodieutriNgaygio", query = "SELECT t FROM ToDieuTri t WHERE t.todieutriNgaygio = :todieutriNgaygio"), @NamedQuery(name = "ToDieuTri.findByTodieutriTheodoidienbien", query = "SELECT t FROM ToDieuTri t WHERE t.todieutriTheodoidienbien = :todieutriTheodoidienbien"), @NamedQuery(name = "ToDieuTri.findByTodieutriThuchienchamsoc", query = "SELECT t FROM ToDieuTri t WHERE t.todieutriThuchienchamsoc = :todieutriThuchienchamsoc"), @NamedQuery(name = "ToDieuTri.findByTodieutriMaso", query = "SELECT t FROM ToDieuTri t WHERE t.todieutriMaso = :todieutriMaso")})
public class ToDieuTri implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "TODIEUTRI_MA")
    private String todieutriMa;
    @Column(name = "TODIEUTRI_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date todieutriNgaygio;
    @Column(name = "TODIEUTRI_THEODOIDIENBIEN")
    private String todieutriTheodoidienbien;
    @Column(name = "TODIEUTRI_TENBS")
    private String todieutriTenbs;
    @Column(name = "TODIEUTRI_THUCHIENCHAMSOC")
    private String todieutriThuchienchamsoc;
    @Id    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TO_DIEU_TRI_TODIEUTRI")
    @SequenceGenerator(name = "TO_DIEU_TRI_TODIEUTRI", sequenceName = "TO_DIEU_TRI_TODIEUTRI_MASO_SEQ", allocationSize = 1)
    @Column(name = "TODIEUTRI_MASO", nullable = false)
    private Integer todieutriMaso;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien bacsi;

    public String getTodieutriTenbs() {
        return todieutriTenbs;
    }

    public void setTodieutriTenbs(String todieutriTenbs) {
        this.todieutriTenbs = todieutriTenbs;
    }

    
    public ToDieuTri() {
    }

    public ToDieuTri(Integer todieutriMaso) {
        this.todieutriMaso = todieutriMaso;
    }

    public String getTodieutriMa() {
        return todieutriMa;
    }

    public void setTodieutriMa(String todieutriMa) {
        this.todieutriMa = todieutriMa;
    }

    public Date getTodieutriNgaygio() {
        return todieutriNgaygio;
    }

    public void setTodieutriNgaygio(Date todieutriNgaygio) {
        this.todieutriNgaygio = todieutriNgaygio;
    }

    public String getTodieutriTheodoidienbien() {
        return todieutriTheodoidienbien;
    }

    public void setTodieutriTheodoidienbien(String todieutriTheodoidienbien) {
        this.todieutriTheodoidienbien = todieutriTheodoidienbien;
    }

    public String getTodieutriThuchienchamsoc() {
        return todieutriThuchienchamsoc;
    }

    public void setTodieutriThuchienchamsoc(String todieutriThuchienchamsoc) {
        this.todieutriThuchienchamsoc = todieutriThuchienchamsoc;
    }

    public Integer getTodieutriMaso() {
        return todieutriMaso;
    }

    public void setTodieutriMaso(Integer todieutriMaso) {
        this.todieutriMaso = todieutriMaso;
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
        hash += (todieutriMaso != null ? todieutriMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ToDieuTri)) {
            return false;
        }
        ToDieuTri other = (ToDieuTri) object;
        if ((this.todieutriMaso == null && other.todieutriMaso != null) || (this.todieutriMaso != null && !this.todieutriMaso.equals(other.todieutriMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.tmp.ToDieuTri[todieutriMaso=" + todieutriMaso + "]";
    }

}