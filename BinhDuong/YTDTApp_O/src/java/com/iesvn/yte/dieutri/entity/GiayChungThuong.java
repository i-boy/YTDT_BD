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
@Table(name = "GIAY_CHUNG_THUONG")
@NamedQueries({
    @NamedQuery(name = "GiayChungThuong.findAll", query = "SELECT g FROM GiayChungThuong g"),
    @NamedQuery(name = "GiayChungThuong.findByGctMa", query = "SELECT g FROM GiayChungThuong g WHERE g.gctMa = :gctMa"),
    @NamedQuery(name = "GiayChungThuong.findByGctNgaygio", query = "SELECT g FROM GiayChungThuong g WHERE g.gctNgaygio = :gctNgaygio"),
    @NamedQuery(name = "GiayChungThuong.findByGctThuongtichvaovien", query = "SELECT g FROM GiayChungThuong g WHERE g.gctThuongtichvaovien = :gctThuongtichvaovien"),
    @NamedQuery(name = "GiayChungThuong.findByGctThuongtichravien", query = "SELECT g FROM GiayChungThuong g WHERE g.gctThuongtichravien = :gctThuongtichravien"),
    @NamedQuery(name = "GiayChungThuong.findByGctDieutri", query = "SELECT g FROM GiayChungThuong g WHERE g.gctDieutri = :gctDieutri")})
public class GiayChungThuong implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GIAY_CHUNG_THUONG")
    @SequenceGenerator(name = "GIAY_CHUNG_THUONG", sequenceName = "GIAY_CHUNG_THUONG_GCT_MA_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "GCT_MA")
    private Integer gctMa;
    @Column(name = "GCT_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gctNgaygio;
    @Column(name = "GCT_THUONGTICHVAOVIEN")
    private String gctThuongtichvaovien;
    @Column(name = "GCT_THUONGTICHRAVIEN")
    private String gctThuongtichravien;
    @Column(name = "GCT_DIEUTRI")
    private String gctDieutri;
    @JoinColumn(name = "GCT_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham gctThamkham;

    public GiayChungThuong() {
    }

    public GiayChungThuong(Integer gctMa) {
        this.gctMa = gctMa;
    }

    public Integer getGctMa() {
        return gctMa;
    }

    public void setGctMa(Integer gctMa) {
        this.gctMa = gctMa;
    }

    public Date getGctNgaygio() {
        return gctNgaygio;
    }

    public void setGctNgaygio(Date gctNgaygio) {
        this.gctNgaygio = gctNgaygio;
    }

    public String getGctThuongtichvaovien() {
        return gctThuongtichvaovien;
    }

    public void setGctThuongtichvaovien(String gctThuongtichvaovien) {
        this.gctThuongtichvaovien = gctThuongtichvaovien;
    }

    public String getGctThuongtichravien() {
        return gctThuongtichravien;
    }

    public void setGctThuongtichravien(String gctThuongtichravien) {
        this.gctThuongtichravien = gctThuongtichravien;
    }

    public String getGctDieutri() {
        return gctDieutri;
    }

    public void setGctDieutri(String gctDieutri) {
        this.gctDieutri = gctDieutri;
    }

    public ThamKham getGctThamkham() {
        return gctThamkham;
    }

    public void setGctThamkham(ThamKham gctThamkham) {
        this.gctThamkham = gctThamkham;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gctMa != null ? gctMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GiayChungThuong)) {
            return false;
        }
        GiayChungThuong other = (GiayChungThuong) object;
        if ((this.gctMa == null && other.gctMa != null) || (this.gctMa != null && !this.gctMa.equals(other.gctMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
            return "com.iesvn.yte.dieutri.entity.GiayChungThuong[gctMa=" + gctMa + "]";
    }

}
