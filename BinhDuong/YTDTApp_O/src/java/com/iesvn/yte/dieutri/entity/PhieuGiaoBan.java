/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author james
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PHIEU_GIAO_BAN")
@NamedQueries({})
public class PhieuGiaoBan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PGB_MA")
    private String pgbMa;
    @Column(name = "PGB_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pgbNgaygio;
    @Column(name = "PGB_KETLUAN")
    private String pgbKetluan;
    @Column(name = "PGB_NGAYGIOCN")
    private Double pgbNgaygiocn;
    @Column(name = "PGB_CHON")
    private Boolean pgbChon;
    @OneToMany(mappedBy = "pgbMa")
    private Collection<PhieuGiaoBanThanhPhanThamDu> phieuGiaoBanThanhPhanThamDuCollection;
    @JoinColumn(name = "KHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa khoaMaso;
    @JoinColumn(name = "PGB_THUKI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien pgbThuki;
    @JoinColumn(name = "PGB_NGUOILAP", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien pgbNguoilap;
    @JoinColumn(name = "PGB_CHUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien pgbChutri;
   // @OneToMany(mappedBy = "ctpgbPgbMa")
   // private Collection<CtPhieuGiaoBan> ctPhieuGiaoBanCollection;

    public PhieuGiaoBan() {
    }

    public PhieuGiaoBan(String pgbMa) {
        this.pgbMa = pgbMa;
    }

    public String getPgbMa() {
        return pgbMa;
    }

    public void setPgbMa(String pgbMa) {
        this.pgbMa = pgbMa;
    }

    public Date getPgbNgaygio() {
        return pgbNgaygio;
    }

    public void setPgbNgaygio(Date pgbNgaygio) {
        this.pgbNgaygio = pgbNgaygio;
    }

    public String getPgbKetluan() {
        return pgbKetluan;
    }

    public void setPgbKetluan(String pgbKetluan) {
        this.pgbKetluan = pgbKetluan;
    }

    public Double getPgbNgaygiocn() {
        return pgbNgaygiocn;
    }

    public void setPgbNgaygiocn(Double pgbNgaygiocn) {
        this.pgbNgaygiocn = pgbNgaygiocn;
    }

    public Boolean getPgbChon() {
        return pgbChon;
    }

    public void setPgbChon(Boolean pgbChon) {
        this.pgbChon = pgbChon;
    }

    public Collection<PhieuGiaoBanThanhPhanThamDu> getPhieuGiaoBanThanhPhanThamDuCollection() {
        return phieuGiaoBanThanhPhanThamDuCollection;
    }

    public void setPhieuGiaoBanThanhPhanThamDuCollection(Collection<PhieuGiaoBanThanhPhanThamDu> phieuGiaoBanThanhPhanThamDuCollection) {
        this.phieuGiaoBanThanhPhanThamDuCollection = phieuGiaoBanThanhPhanThamDuCollection;
    }

    public DmKhoa getKhoaMaso() {
        return khoaMaso;
    }

    public void setKhoaMaso(DmKhoa khoaMaso) {
        this.khoaMaso = khoaMaso;
    }

    public DtDmNhanVien getPgbThuki() {
        return pgbThuki;
    }

    public void setPgbThuki(DtDmNhanVien pgbThuki) {
        this.pgbThuki = pgbThuki;
    }

    public DtDmNhanVien getPgbNguoilap() {
        return pgbNguoilap;
    }

    public void setPgbNguoilap(DtDmNhanVien pgbNguoilap) {
        this.pgbNguoilap = pgbNguoilap;
    }

    public DtDmNhanVien getPgbChutri() {
        return pgbChutri;
    }

    public void setPgbChutri(DtDmNhanVien pgbChutri) {
        this.pgbChutri = pgbChutri;
    }

   // public Collection<CtPhieuGiaoBan> getCtPhieuGiaoBanCollection() {
   //     return ctPhieuGiaoBanCollection;
   // }

   // public void setCtPhieuGiaoBanCollection(Collection<CtPhieuGiaoBan> ctPhieuGiaoBanCollection) {
   //     this.ctPhieuGiaoBanCollection = ctPhieuGiaoBanCollection;
   // }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pgbMa != null ? pgbMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuGiaoBan)) {
            return false;
        }
        PhieuGiaoBan other = (PhieuGiaoBan) object;
        if ((this.pgbMa == null && other.pgbMa != null) || (this.pgbMa != null && !this.pgbMa.equals(other.pgbMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.PhieuGiaoBan[pgbMa=" + pgbMa + "]";
    }

}
