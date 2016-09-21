/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PHIEU_XUAT_KHO")
@NamedQueries({})
public class PhieuXuatKho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PHIEUXUATKHO_MA", nullable = false)
    private String phieuxuatkhoMa;
    @Column(name = "PHIEUXUATKHO_NGAYLAP", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date phieuxuatkhoNgaylap;
    @Column(name = "PHIEUXUATKHO_THANHTIEN")
    private Double phieuxuatkhoThanhtien;
    @Column(name = "PHIEUXUATKHO_THANHTIENB")
    private Double phieuxuatkhoThanhtienb;
    @Column(name = "PHIEUXUATKHO_TAIKHOAN")
    private String phieuxuatkhoTaikhoan;
    @Column(name = "PHIEUXUATKHO_DOIUNG")
    private String phieuxuatkhoDoiung;
    @Column(name = "PHIEUXUATKHO_TRONSO")
    private Double phieuxuatkhoTronso;
    @Column(name = "PHIEUXUATKHO_LYDO")
    private String phieuxuatkhoLydo;
    @Column(name = "PHIEUXUATKHO_PHANBIET")
    private String phieuxuatkhoPhanbiet;
    @Column(name = "PHIEUXUATKHO_SOTHANG")
    private Short phieuxuatkhoSothang;
    @Column(name = "PHIEUXUATKHO_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieuxuatkhoNgaygiocn;
    @Column(name = "PHIEUXUATKHO_NGAYGIOPHAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieuxuatkhoNgaygiophat;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phieuxuatkhoMa")
//    private Collection<CtXuatKho> ctXuatKhoCollection;
    @JoinColumn(name = "DMDOITUONG_MASO", referencedColumnName = "DMDOITUONG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDoiTuong dmdoituongMaso;
    @JoinColumn(name = "DMKHOA_XUAT", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaXuat;
    @JoinColumn(name = "DMKHOA_NHAN", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaNhan;
    @JoinColumn(name = "DMLOAITHUOC_MASO", referencedColumnName = "DMLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiThuoc dmloaithuocMaso;
    @JoinColumn(name = "DTDMNHANVIEN_NHAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienNhan;
    @JoinColumn(name = "DTDMNHANVIEN_PHAT", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienPhat;
    @JoinColumn(name = "DTDMNHANVIEN_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienBacsi;
    @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;
    @JoinColumn(name = "PHIEUDT_MA", referencedColumnName = "PHIEUDT_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuDuTru phieudtMa;
    @Column(name = "PHIEUXUATKHO_LOAIPHIEU")
    private String phieuxuatkhoLoaiPhieu;

    public PhieuXuatKho() {
    }

    public PhieuXuatKho(String phieuxuatkhoMa) {
        this.phieuxuatkhoMa = phieuxuatkhoMa;
    }

    public PhieuXuatKho(String phieuxuatkhoMa, Date phieuxuatkhoNgaylap) {
        this.phieuxuatkhoMa = phieuxuatkhoMa;
        this.phieuxuatkhoNgaylap = phieuxuatkhoNgaylap;
    }

    public String getPhieuxuatkhoMa() {
        return phieuxuatkhoMa;
    }

    public void setPhieuxuatkhoMa(String phieuxuatkhoMa) {
        this.phieuxuatkhoMa = phieuxuatkhoMa;
    }

    public Date getPhieuxuatkhoNgaylap() {
        return phieuxuatkhoNgaylap;
    }

    public void setPhieuxuatkhoNgaylap(Date phieuxuatkhoNgaylap) {
        this.phieuxuatkhoNgaylap = phieuxuatkhoNgaylap;
    }

    public Double getPhieuxuatkhoThanhtien() {
        return phieuxuatkhoThanhtien;
    }

    public void setPhieuxuatkhoThanhtien(Double phieuxuatkhoThanhtien) {
        this.phieuxuatkhoThanhtien = phieuxuatkhoThanhtien;
    }

    public Double getPhieuxuatkhoThanhtienb() {
        return phieuxuatkhoThanhtienb;
    }

    public void setPhieuxuatkhoThanhtienb(Double phieuxuatkhoThanhtienb) {
        this.phieuxuatkhoThanhtienb = phieuxuatkhoThanhtienb;
    }

    public String getPhieuxuatkhoTaikhoan() {
        return phieuxuatkhoTaikhoan;
    }

    public void setPhieuxuatkhoTaikhoan(String phieuxuatkhoTaikhoan) {
        this.phieuxuatkhoTaikhoan = phieuxuatkhoTaikhoan;
    }

    public String getPhieuxuatkhoDoiung() {
        return phieuxuatkhoDoiung;
    }

    public void setPhieuxuatkhoDoiung(String phieuxuatkhoDoiung) {
        this.phieuxuatkhoDoiung = phieuxuatkhoDoiung;
    }

    public Double getPhieuxuatkhoTronso() {
        return phieuxuatkhoTronso;
    }

    public void setPhieuxuatkhoTronso(Double phieuxuatkhoTronso) {
        this.phieuxuatkhoTronso = phieuxuatkhoTronso;
    }

    public String getPhieuxuatkhoLydo() {
        return phieuxuatkhoLydo;
    }

    public void setPhieuxuatkhoLydo(String phieuxuatkhoLydo) {
        this.phieuxuatkhoLydo = phieuxuatkhoLydo;
    }

    public String getPhieuxuatkhoPhanbiet() {
        return phieuxuatkhoPhanbiet;
    }

    public void setPhieuxuatkhoPhanbiet(String phieuxuatkhoPhanbiet) {
        this.phieuxuatkhoPhanbiet = phieuxuatkhoPhanbiet;
    }

    public Short getPhieuxuatkhoSothang() {
        return phieuxuatkhoSothang;
    }

    public void setPhieuxuatkhoSothang(Short phieuxuatkhoSothang) {
        this.phieuxuatkhoSothang = phieuxuatkhoSothang;
    }

    public Date getPhieuxuatkhoNgaygiocn() {
        return phieuxuatkhoNgaygiocn;
    }

    public void setPhieuxuatkhoNgaygiocn(Date phieuxuatkhoNgaygiocn) {
        this.phieuxuatkhoNgaygiocn = phieuxuatkhoNgaygiocn;
    }

    public Date getPhieuxuatkhoNgaygiophat() {
        return phieuxuatkhoNgaygiophat;
    }

    public void setPhieuxuatkhoNgaygiophat(Date phieuxuatkhoNgaygiophat) {
        this.phieuxuatkhoNgaygiophat = phieuxuatkhoNgaygiophat;
    }

//    public Collection<CtXuatKho> getCtXuatKhoCollection() {
//        return ctXuatKhoCollection;
//    }
//
//    public void setCtXuatKhoCollection(Collection<CtXuatKho> ctXuatKhoCollection) {
//        this.ctXuatKhoCollection = ctXuatKhoCollection;
//    }
    public DmDoiTuong getDmdoituongMaso(boolean create) {
if(create && dmdoituongMaso == null) dmdoituongMaso = new DmDoiTuong();
return dmdoituongMaso;
}
    public DmDoiTuong getDmdoituongMaso() {
        return dmdoituongMaso;
    }

    public void setDmdoituongMaso(DmDoiTuong dmdoituongMaso) {
        this.dmdoituongMaso = dmdoituongMaso;
    }

    public DmKhoa getDmkhoaXuat(boolean create) {
if(create && dmkhoaXuat == null) dmkhoaXuat = new DmKhoa();
return dmkhoaXuat;
}
    public DmKhoa getDmkhoaXuat() {
        return dmkhoaXuat;
    }

    public void setDmkhoaXuat(DmKhoa dmkhoaXuat) {
        this.dmkhoaXuat = dmkhoaXuat;
    }

    public DmKhoa getDmkhoaNhan(boolean create) {
if(create && dmkhoaNhan == null) dmkhoaNhan = new DmKhoa();
return dmkhoaNhan;
}
    public DmKhoa getDmkhoaNhan() {
        return dmkhoaNhan;
    }

    public void setDmkhoaNhan(DmKhoa dmkhoaNhan) {
        this.dmkhoaNhan = dmkhoaNhan;
    }

    public DmLoaiThuoc getDmloaithuocMaso(boolean create) {
if(create && dmloaithuocMaso == null) dmloaithuocMaso = new DmLoaiThuoc();
return dmloaithuocMaso;
}
    public DmLoaiThuoc getDmloaithuocMaso() {
        return dmloaithuocMaso;
    }

    public void setDmloaithuocMaso(DmLoaiThuoc dmloaithuocMaso) {
        this.dmloaithuocMaso = dmloaithuocMaso;
    }

    public DtDmNhanVien getDtdmnhanvienNhan(boolean create) {
if(create && dtdmnhanvienNhan == null) dtdmnhanvienNhan = new DtDmNhanVien();
return dtdmnhanvienNhan;
}
    public DtDmNhanVien getDtdmnhanvienNhan() {
        return dtdmnhanvienNhan;
    }

    public void setDtdmnhanvienNhan(DtDmNhanVien dtdmnhanvienNhan) {
        this.dtdmnhanvienNhan = dtdmnhanvienNhan;
    }

    public DtDmNhanVien getDtdmnhanvienPhat(boolean create) {
if(create && dtdmnhanvienPhat == null) dtdmnhanvienPhat = new DtDmNhanVien();
return dtdmnhanvienPhat;
}
    public DtDmNhanVien getDtdmnhanvienPhat() {
        return dtdmnhanvienPhat;
    }

    public void setDtdmnhanvienPhat(DtDmNhanVien dtdmnhanvienPhat) {
        this.dtdmnhanvienPhat = dtdmnhanvienPhat;
    }

    public DtDmNhanVien getDtdmnhanvienBacsi(boolean create) {
if(create && dtdmnhanvienBacsi == null) dtdmnhanvienBacsi = new DtDmNhanVien();
return dtdmnhanvienBacsi;
}
    public DtDmNhanVien getDtdmnhanvienBacsi() {
        return dtdmnhanvienBacsi;
    }

    public void setDtdmnhanvienBacsi(DtDmNhanVien dtdmnhanvienBacsi) {
        this.dtdmnhanvienBacsi = dtdmnhanvienBacsi;
    }

    public DtDmNhanVien getDtdmnhanvienCn(boolean create) {
if(create && dtdmnhanvienCn == null) dtdmnhanvienCn = new DtDmNhanVien();
return dtdmnhanvienCn;
}
    public DtDmNhanVien getDtdmnhanvienCn() {
        return dtdmnhanvienCn;
    }

    public void setDtdmnhanvienCn(DtDmNhanVien dtdmnhanvienCn) {
        this.dtdmnhanvienCn = dtdmnhanvienCn;
    }

    public PhieuDuTru getPhieudtMa(boolean create) {
if(create && phieudtMa == null) phieudtMa = new PhieuDuTru();
return phieudtMa;
}
    public PhieuDuTru getPhieudtMa() {
        return phieudtMa;
    }

    public void setPhieudtMa(PhieuDuTru phieudtMa) {
        this.phieudtMa = phieudtMa;
    }

    public String getPhieuxuatkhoLoaiPhieu() {
        return phieuxuatkhoLoaiPhieu;
    }

    public void setPhieuxuatkhoLoaiPhieu(String phieuxuatkhoLoaiPhieu) {
        this.phieuxuatkhoLoaiPhieu = phieuxuatkhoLoaiPhieu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phieuxuatkhoMa != null ? phieuxuatkhoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuXuatKho)) {
            return false;
        }
        PhieuXuatKho other = (PhieuXuatKho) object;
        if ((this.phieuxuatkhoMa == null && other.phieuxuatkhoMa != null) || (this.phieuxuatkhoMa != null && !this.phieuxuatkhoMa.equals(other.phieuxuatkhoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.PhieuXuatKho[phieuxuatkhoMa=" + phieuxuatkhoMa + "]";
    }
}


