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
@Table(name = "PHIEU_TRA_KHO")
@NamedQueries({})
public class PhieuTraKho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PHIEUTRAKHO_MA", nullable = false)
    private String phieutrakhoMa;
    @Column(name = "PHIEUTRAKHO_NGAY")
    @Temporal(TemporalType.DATE)
    private Date phieutrakhoNgay;
    @Column(name = "PHIEUTRAKHO_THANHTIEN")
    private Double phieutrakhoThanhtien;
    @Column(name = "PHIEUTRAKHO_THANHTIENB")
    private Double phieutrakhoThanhtienb;
    @Column(name = "PHIEUTRAKHO_TAIKHOAN")
    private String phieutrakhoTaikhoan;
    @Column(name = "PHIEUTRAKHO_DOIUNG")
    private String phieutrakhoDoiung;
    @Column(name = "PHIEUTRAKHO_TRONSO")
    private Double phieutrakhoTronso;
    @Column(name = "PHIEUTRAKHO_LYDO")
    private String phieutrakhoLydo;
    @Column(name = "PHIEUTRAKHO_PHANBIET")
    private String phieutrakhoPhanbiet;
    @Column(name = "PHIEUTRAKHO_SOTHANG")
    private Integer phieutrakhoSothang;
    @Column(name = "PHIEUTRAKHO_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieutrakhoNgaygiocn;
    @Column(name = "PHIEUTRAKHO_NGAYGIOPHAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieutrakhoNgaygiophat;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phieutrakhoMa")
//    private Collection<CtTraKho> ctTraKhoCollection;
    @JoinColumn(name = "DMDOITUONG_MASO", referencedColumnName = "DMDOITUONG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDoiTuong dmdoituongMaso;
    @JoinColumn(name = "DMKHOA_NHAN", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaNhan;
    @JoinColumn(name = "DMKHOA_TRA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaTra;
    @JoinColumn(name = "DTDMNHANVIEN_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienBacsi;
    @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;
    @JoinColumn(name = "DTDMNHANVIEN_LAPPHIEU", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienLapphieu;
    @JoinColumn(name = "DTDMNHANVIEN_PHAT", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienPhat;
    @JoinColumn(name = "PHIEUDT_MA", referencedColumnName = "PHIEUDT_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuDuTru phieudtMa;
    @JoinColumn(name = "DMLOAITHUOC_MASO", referencedColumnName = "DMLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiThuoc dmloaithuocMaso;
    @Column(name = "PHIEUTRAKHO_LOAIPHIEU")
    private String phieutrakhoLoaiPhieu;

    

    public PhieuTraKho() {
    }

    public PhieuTraKho(String phieutrakhoMa) {
        this.phieutrakhoMa = phieutrakhoMa;
    }

    public String getPhieutrakhoMa() {
        return phieutrakhoMa;
    }

    public void setPhieutrakhoMa(String phieutrakhoMa) {
        this.phieutrakhoMa = phieutrakhoMa;
    }

    public Date getPhieutrakhoNgay() {
        return phieutrakhoNgay;
    }

    public void setPhieutrakhoNgay(Date phieutrakhoNgay) {
        this.phieutrakhoNgay = phieutrakhoNgay;
    }

    public Double getPhieutrakhoThanhtien() {
        return phieutrakhoThanhtien;
    }

    public void setPhieutrakhoThanhtien(Double phieutrakhoThanhtien) {
        this.phieutrakhoThanhtien = phieutrakhoThanhtien;
    }

    public Double getPhieutrakhoThanhtienb() {
        return phieutrakhoThanhtienb;
    }

    public void setPhieutrakhoThanhtienb(Double phieutrakhoThanhtienb) {
        this.phieutrakhoThanhtienb = phieutrakhoThanhtienb;
    }

    public String getPhieutrakhoTaikhoan() {
        return phieutrakhoTaikhoan;
    }

    public void setPhieutrakhoTaikhoan(String phieutrakhoTaikhoan) {
        this.phieutrakhoTaikhoan = phieutrakhoTaikhoan;
    }

    public String getPhieutrakhoDoiung() {
        return phieutrakhoDoiung;
    }

    public void setPhieutrakhoDoiung(String phieutrakhoDoiung) {
        this.phieutrakhoDoiung = phieutrakhoDoiung;
    }

    public Double getPhieutrakhoTronso() {
        return phieutrakhoTronso;
    }

    public void setPhieutrakhoTronso(Double phieutrakhoTronso) {
        this.phieutrakhoTronso = phieutrakhoTronso;
    }

    public String getPhieutrakhoLydo() {
        return phieutrakhoLydo;
    }

    public void setPhieutrakhoLydo(String phieutrakhoLydo) {
        this.phieutrakhoLydo = phieutrakhoLydo;
    }

    public String getPhieutrakhoPhanbiet() {
        return phieutrakhoPhanbiet;
    }

    public void setPhieutrakhoPhanbiet(String phieutrakhoPhanbiet) {
        this.phieutrakhoPhanbiet = phieutrakhoPhanbiet;
    }

    public Integer getPhieutrakhoSothang() {
        return phieutrakhoSothang;
    }

    public void setPhieutrakhoSothang(Integer phieutrakhoSothang) {
        this.phieutrakhoSothang = phieutrakhoSothang;
    }

    public Date getPhieutrakhoNgaygiocn() {
        return phieutrakhoNgaygiocn;
    }

    public void setPhieutrakhoNgaygiocn(Date phieutrakhoNgaygiocn) {
        this.phieutrakhoNgaygiocn = phieutrakhoNgaygiocn;
    }

    public Date getPhieutrakhoNgaygiophat() {
        return phieutrakhoNgaygiophat;
    }

    public void setPhieutrakhoNgaygiophat(Date phieutrakhoNgaygiophat) {
        this.phieutrakhoNgaygiophat = phieutrakhoNgaygiophat;
    }

//    public Collection<CtTraKho> getCtTraKhoCollection() {
//        return ctTraKhoCollection;
//    }
//
//    public void setCtTraKhoCollection(Collection<CtTraKho> ctTraKhoCollection) {
//        this.ctTraKhoCollection = ctTraKhoCollection;
//    }
    public DmDoiTuong getDmdoituongMaso(boolean create) {
        if (create && dmdoituongMaso == null) {
            dmdoituongMaso = new DmDoiTuong();
        }
        return dmdoituongMaso;
    }

    public DmDoiTuong getDmdoituongMaso() {
        return dmdoituongMaso;
    }

    public void setDmdoituongMaso(DmDoiTuong dmdoituongMaso) {
        this.dmdoituongMaso = dmdoituongMaso;
    }

    public DmKhoa getDmkhoaNhan(boolean create) {
        if (create && dmkhoaNhan == null) {
            dmkhoaNhan = new DmKhoa();
        }
        return dmkhoaNhan;
    }

    public DmKhoa getDmkhoaNhan() {
        return dmkhoaNhan;
    }

    public void setDmkhoaNhan(DmKhoa dmkhoaNhan) {
        this.dmkhoaNhan = dmkhoaNhan;
    }

    public DmKhoa getDmkhoaTra(boolean create) {
        if (create && dmkhoaTra == null) {
            dmkhoaTra = new DmKhoa();
        }
        return dmkhoaTra;
    }

    public DmKhoa getDmkhoaTra() {
        return dmkhoaTra;
    }

    public void setDmkhoaTra(DmKhoa dmkhoaTra) {
        this.dmkhoaTra = dmkhoaTra;
    }

    public DtDmNhanVien getDtdmnhanvienBacsi(boolean create) {
        if (create && dtdmnhanvienBacsi == null) {
            dtdmnhanvienBacsi = new DtDmNhanVien();
        }
        return dtdmnhanvienBacsi;
    }

    public DtDmNhanVien getDtdmnhanvienBacsi() {
        return dtdmnhanvienBacsi;
    }

    public void setDtdmnhanvienBacsi(DtDmNhanVien dtdmnhanvienBacsi) {
        this.dtdmnhanvienBacsi = dtdmnhanvienBacsi;
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

    public DtDmNhanVien getDtdmnhanvienLapphieu(boolean create) {
        if (create && dtdmnhanvienLapphieu == null) {
            dtdmnhanvienLapphieu = new DtDmNhanVien();
        }
        return dtdmnhanvienLapphieu;
    }

    public DtDmNhanVien getDtdmnhanvienLapphieu() {
        return dtdmnhanvienLapphieu;
    }

    public void setDtdmnhanvienLapphieu(DtDmNhanVien dtdmnhanvienLapphieu) {
        this.dtdmnhanvienLapphieu = dtdmnhanvienLapphieu;
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

    public PhieuDuTru getPhieudtMa(boolean create) {
        if (create && phieudtMa == null) {
            phieudtMa = new PhieuDuTru();
        }
        return phieudtMa;
    }

    public PhieuDuTru getPhieudtMa() {
        return phieudtMa;
    }

    public void setPhieudtMa(PhieuDuTru phieudtMa) {
        this.phieudtMa = phieudtMa;
    }
    
    public DmLoaiThuoc getDmloaithuocMaso(boolean create) {
        if (create && dmloaithuocMaso == null) {
            dmloaithuocMaso = new DmLoaiThuoc();
        }
        return dmloaithuocMaso;
    }

    public DmLoaiThuoc getDmloaithuocMaso() {
        return dmloaithuocMaso;
    }

    public void setDmloaithuocMaso(DmLoaiThuoc dmloaithuocMaso) {
        this.dmloaithuocMaso = dmloaithuocMaso;
    }

    public String getPhieutrakhoLoaiPhieu() {
        return phieutrakhoLoaiPhieu;
    }

    public void setPhieutrakhoLoaiPhieu(String phieutrakhoLoaiPhieu) {
        this.phieutrakhoLoaiPhieu = phieutrakhoLoaiPhieu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phieutrakhoMa != null ? phieutrakhoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuTraKho)) {
            return false;
        }
        PhieuTraKho other = (PhieuTraKho) object;
        if ((this.phieutrakhoMa == null && other.phieutrakhoMa != null) || (this.phieutrakhoMa != null && !this.phieutrakhoMa.equals(other.phieutrakhoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.PhieuTraKho[phieutrakhoMa=" + phieutrakhoMa + "]";
    }

    
    

  

    

    
}


