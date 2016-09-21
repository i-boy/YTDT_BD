/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PHIEU_XUAT_BH_XUAT_VIEN")
@NamedQueries({})
public class PhieuXuatBhXuatVien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PHIEUXUATBHXV_MA", nullable = false)
    private String phieuxuatbhxvMa;
    @Column(name = "PHIEUXUATBHXV_NGAYLAP", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date phieuxuatbhxvNgaylap;
    @Column(name = "PHIEUXUATBHXV_THANHTIEN")
    private Double phieuxuatbhxvThanhtien;
    @Column(name = "PHIEUXUATBHXV_NGAYGIOCN", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieuxuatbhxvNgaygiocn;
    @Column(name = "PHIEUXUATBHXV_NGAYGIOPHAT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieuxuatbhxvNgaygiophat;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;
    @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;
    @JoinColumn(name = "DTDMNHANVIEN_PHAT", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienPhat;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsba;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phieuxuatbhxvMa")
//    private Collection<CtXuatBhXuatVien> ctXuatBhXuatVienCollection;

    public PhieuXuatBhXuatVien() {
    }

    public PhieuXuatBhXuatVien(String phieuxuatbhxvMa) {
        this.phieuxuatbhxvMa = phieuxuatbhxvMa;
    }

    public PhieuXuatBhXuatVien(String phieuxuatbhxvMa, Date phieuxuatbhxvNgaylap) {
        this.phieuxuatbhxvMa = phieuxuatbhxvMa;
        this.phieuxuatbhxvNgaylap = phieuxuatbhxvNgaylap;
    }

    public String getPhieuxuatbhxvMa() {
        return phieuxuatbhxvMa;
    }

    public void setPhieuxuatbhxvMa(String phieuxuatbhxvMa) {
        this.phieuxuatbhxvMa = phieuxuatbhxvMa;
    }

    public Date getPhieuxuatbhxvNgaylap() {
        return phieuxuatbhxvNgaylap;
    }

    public void setPhieuxuatbhxvNgaylap(Date phieuxuatbhxvNgaylap) {
        this.phieuxuatbhxvNgaylap = phieuxuatbhxvNgaylap;
    }

    public Double getPhieuxuatbhxvThanhtien() {
        return phieuxuatbhxvThanhtien;
    }

    public void setPhieuxuatbhxvThanhtien(Double phieuxuatbhxvThanhtien) {
        this.phieuxuatbhxvThanhtien = phieuxuatbhxvThanhtien;
    }

    public Date getPhieuxuatbhxvNgaygiocn() {
        return phieuxuatbhxvNgaygiocn;
    }

    public void setPhieuxuatbhxvNgaygiocn(Date phieuxuatbhxvNgaygiocn) {
        this.phieuxuatbhxvNgaygiocn = phieuxuatbhxvNgaygiocn;
    }

    public Date getPhieuxuatbhxvNgaygiophat() {
        return phieuxuatbhxvNgaygiophat;
    }

    public void setPhieuxuatbhxvNgaygiophat(Date phieuxuatbhxvNgaygiophat) {
        this.phieuxuatbhxvNgaygiophat = phieuxuatbhxvNgaygiophat;
    }

    public DmKhoa getDmkhoaMaso(boolean create) {
        if (create && dmkhoaMaso == null) {
            dmkhoaMaso = new DmKhoa();
        }
        return dmkhoaMaso;
    }

    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    public DtDmNhanVien getDtdmnhanvienCn(boolean create) {
        if (create && dtdmnhanvienCn == null) {
            dtdmnhanvienCn = new DtDmNhanVien();
        }
        return dtdmnhanvienCn;
    }

    public DtDmNhanVien getDtdmnhanvienCn() {
        return dtdmnhanvienCn;
    }

    public void setDtdmnhanvienCn(DtDmNhanVien dtdmnhanvienCn) {
        this.dtdmnhanvienCn = dtdmnhanvienCn;
    }

    public DtDmNhanVien getDtdmnhanvienPhat(boolean create) {
        if (create && dtdmnhanvienPhat == null) {
            dtdmnhanvienPhat = new DtDmNhanVien();
        }
        return dtdmnhanvienPhat;
    }

    public DtDmNhanVien getDtdmnhanvienPhat() {
        return dtdmnhanvienPhat;
    }

    public void setDtdmnhanvienPhat(DtDmNhanVien dtdmnhanvienPhat) {
        this.dtdmnhanvienPhat = dtdmnhanvienPhat;
    }

     public Hsba getHsba() {
        return hsba;
    }

    public void setHsba(Hsba hsba) {
        this.hsba = hsba;
    }

//    public Collection<CtXuatBhXuatVien> getCtXuatBhXuatVienCollection() {
//        return ctXuatBhXuatVienCollection;
//    }
//
//    public void setCtXuatBhXuatVienCollection(Collection<CtXuatBhXuatVien> ctXuatBhXuatVienCollection) {
//        this.ctXuatBhXuatVienCollection = ctXuatBhXuatVienCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phieuxuatbhxvMa != null ? phieuxuatbhxvMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuXuatBhXuatVien)) {
            return false;
        }
        PhieuXuatBhXuatVien other = (PhieuXuatBhXuatVien) object;
        if ((this.phieuxuatbhxvMa == null && other.phieuxuatbhxvMa != null) || (this.phieuxuatbhxvMa != null && !this.phieuxuatbhxvMa.equals(other.phieuxuatbhxvMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.PhieuXuatBhXuatVien[phieuxuatbhxvMa=" + phieuxuatbhxvMa + "]";
    }

}
