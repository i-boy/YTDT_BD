/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmNhaCungCap;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author thanh
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PHIEU_TRA_NHA_CUNG_CAP")
@NamedQueries({@NamedQuery(name = "PhieuTraNhaCungCap.findAll", query = "SELECT p FROM PhieuTraNhaCungCap p"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapMa", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapMa = :phieutranhacungcapMa"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapNgaylap", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapNgaylap = :phieutranhacungcapNgaylap"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapThanhtien", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapThanhtien = :phieutranhacungcapThanhtien"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapThanhtienb", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapThanhtienb = :phieutranhacungcapThanhtienb"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapTaikhoan", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapTaikhoan = :phieutranhacungcapTaikhoan"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapDoiung", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapDoiung = :phieutranhacungcapDoiung"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapTronso", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapTronso = :phieutranhacungcapTronso"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapLydo", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapLydo = :phieutranhacungcapLydo"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapPhanbiet", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapPhanbiet = :phieutranhacungcapPhanbiet"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapSothang", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapSothang = :phieutranhacungcapSothang"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapNgaygiocn", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapNgaygiocn = :phieutranhacungcapNgaygiocn"), @NamedQuery(name = "PhieuTraNhaCungCap.findByPhieutranhacungcapNgaygiophat", query = "SELECT p FROM PhieuTraNhaCungCap p WHERE p.phieutranhacungcapNgaygiophat = :phieutranhacungcapNgaygiophat")})
public class PhieuTraNhaCungCap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PHIEUTRANHACUNGCAP_MA")
    private String phieutranhacungcapMa;
    @Column(name = "PHIEUTRANHACUNGCAP_NGAYLAP")
    @Temporal(TemporalType.DATE)
    private Date phieutranhacungcapNgaylap;
    @Column(name = "PHIEUTRANHACUNGCAP_THANHTIEN")
    private Double phieutranhacungcapThanhtien;
    @Column(name = "PHIEUTRANHACUNGCAP_THANHTIENB")
    private Double phieutranhacungcapThanhtienb;
    @Column(name = "PHIEUTRANHACUNGCAP_TAIKHOAN")
    private String phieutranhacungcapTaikhoan;
    @Column(name = "PHIEUTRANHACUNGCAP_DOIUNG")
    private String phieutranhacungcapDoiung;
    @Column(name = "PHIEUTRANHACUNGCAP_TRONSO")
    private Double phieutranhacungcapTronso;
    @Column(name = "PHIEUTRANHACUNGCAP_LYDO")
    private String phieutranhacungcapLydo;
    @Column(name = "PHIEUTRANHACUNGCAP_PHANBIET")
    private String phieutranhacungcapPhanbiet;
    @Column(name = "PHIEUTRANHACUNGCAP_SOTHANG")
    private Short phieutranhacungcapSothang;
    @Column(name = "PHIEUTRANHACUNGCAP_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieutranhacungcapNgaygiocn;
    @Column(name = "PHIEUTRANHACUNGCAP_NGAYGIOPHAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieutranhacungcapNgaygiophat;
    @JoinColumn(name = "DMDOITUONG_MASO", referencedColumnName = "DMDOITUONG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDoiTuong dmdoituongMaso;
    @JoinColumn(name = "NHACUNGCAP", referencedColumnName = "DMNHACUNGCAP_MASO")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private DmNhaCungCap nhacungcap;
    @JoinColumn(name = "DMKHOA_XUAT", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private DmKhoa dmkhoaXuat;
    @JoinColumn(name = "DMLOAITHUOC_MASO", referencedColumnName = "DMLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiThuoc dmloaithuocMaso;
    @JoinColumn(name = "DTDMNHANVIEN_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienBacsi;
    @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;
    @JoinColumn(name = "DTDMNHANVIEN_NHAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienNhan;
    @JoinColumn(name = "DTDMNHANVIEN_PHAT", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienPhat;
    @JoinColumn(name = "DMNCT_MASO", referencedColumnName = "DMNCT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonChuongTrinh dmnctMaso;
    @JoinColumn(name = "DMNGUONKINHPHI_MASO", referencedColumnName = "DMNGUONKINHPHI_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonKinhPhi dmnguonkinhphiMaso;

    @Column(name = "PHIEUTRANHACUNGCAP_LOAIPHIEU")
    private String phieutranhacungcapLoaiPhieu;

    public PhieuTraNhaCungCap() {
    }

    public PhieuTraNhaCungCap(String phieutranhacungcapMa) {
        this.phieutranhacungcapMa = phieutranhacungcapMa;
    }

    public String getPhieutranhacungcapMa() {
        return phieutranhacungcapMa;
    }

    public void setPhieutranhacungcapMa(String phieutranhacungcapMa) {
        this.phieutranhacungcapMa = phieutranhacungcapMa;
    }

    public Date getPhieutranhacungcapNgaylap() {
        return phieutranhacungcapNgaylap;
    }

    public void setPhieutranhacungcapNgaylap(Date phieutranhacungcapNgaylap) {
        this.phieutranhacungcapNgaylap = phieutranhacungcapNgaylap;
    }

    public Double getPhieutranhacungcapThanhtien() {
        return phieutranhacungcapThanhtien;
    }

    public void setPhieutranhacungcapThanhtien(Double phieutranhacungcapThanhtien) {
        this.phieutranhacungcapThanhtien = phieutranhacungcapThanhtien;
    }

    public Double getPhieutranhacungcapThanhtienb() {
        return phieutranhacungcapThanhtienb;
    }

    public void setPhieutranhacungcapThanhtienb(Double phieutranhacungcapThanhtienb) {
        this.phieutranhacungcapThanhtienb = phieutranhacungcapThanhtienb;
    }

    public String getPhieutranhacungcapTaikhoan() {
        return phieutranhacungcapTaikhoan;
    }

    public void setPhieutranhacungcapTaikhoan(String phieutranhacungcapTaikhoan) {
        this.phieutranhacungcapTaikhoan = phieutranhacungcapTaikhoan;
    }

    public String getPhieutranhacungcapDoiung() {
        return phieutranhacungcapDoiung;
    }

    public void setPhieutranhacungcapDoiung(String phieutranhacungcapDoiung) {
        this.phieutranhacungcapDoiung = phieutranhacungcapDoiung;
    }

    public Double getPhieutranhacungcapTronso() {
        return phieutranhacungcapTronso;
    }

    public void setPhieutranhacungcapTronso(Double phieutranhacungcapTronso) {
        this.phieutranhacungcapTronso = phieutranhacungcapTronso;
    }

    public String getPhieutranhacungcapLydo() {
        return phieutranhacungcapLydo;
    }

    public void setPhieutranhacungcapLydo(String phieutranhacungcapLydo) {
        this.phieutranhacungcapLydo = phieutranhacungcapLydo;
    }

    public String getPhieutranhacungcapPhanbiet() {
        return phieutranhacungcapPhanbiet;
    }

    public void setPhieutranhacungcapPhanbiet(String phieutranhacungcapPhanbiet) {
        this.phieutranhacungcapPhanbiet = phieutranhacungcapPhanbiet;
    }

    public Short getPhieutranhacungcapSothang() {
        return phieutranhacungcapSothang;
    }

    public void setPhieutranhacungcapSothang(Short phieutranhacungcapSothang) {
        this.phieutranhacungcapSothang = phieutranhacungcapSothang;
    }

    public Date getPhieutranhacungcapNgaygiocn() {
        return phieutranhacungcapNgaygiocn;
    }

    public void setPhieutranhacungcapNgaygiocn(Date phieutranhacungcapNgaygiocn) {
        this.phieutranhacungcapNgaygiocn = phieutranhacungcapNgaygiocn;
    }

    public Date getPhieutranhacungcapNgaygiophat() {
        return phieutranhacungcapNgaygiophat;
    }

    public void setPhieutranhacungcapNgaygiophat(Date phieutranhacungcapNgaygiophat) {
        this.phieutranhacungcapNgaygiophat = phieutranhacungcapNgaygiophat;
    }

    public DmDoiTuong getDmdoituongMaso() {
        return dmdoituongMaso;
    }

    public void setDmdoituongMaso(DmDoiTuong dmdoituongMaso) {
        this.dmdoituongMaso = dmdoituongMaso;
    }

    public DmNhaCungCap getNhacungcap() {
        return nhacungcap;
    }

    public DmNhaCungCap getNhacungcap(Boolean create) {
        if (create && nhacungcap == null) {
            nhacungcap = new DmNhaCungCap();
        }
        return nhacungcap;
    }

    public void setNhacungcap(DmNhaCungCap nhacungcap) {
        this.nhacungcap = nhacungcap;
    }

    public DmKhoa getDmkhoaXuat() {
        return dmkhoaXuat;
    }

    public DmKhoa getDmkhoaXuat(Boolean created) {
        if (created && dmkhoaXuat == null) {
            dmkhoaXuat = new DmKhoa();
        }
        return dmkhoaXuat;
    }

    public void setDmkhoaXuat(DmKhoa dmkhoaXuat) {
        this.dmkhoaXuat = dmkhoaXuat;
    }

    public DmLoaiThuoc getDmloaithuocMaso() {
        return dmloaithuocMaso;
    }

    public DmLoaiThuoc getDmloaithuocMaso(Boolean create) {
        if (create && dmloaithuocMaso == null) {
            dmloaithuocMaso = new DmLoaiThuoc();
        }
        return dmloaithuocMaso;
    }

    public void setDmloaithuocMaso(DmLoaiThuoc dmloaithuocMaso) {
        this.dmloaithuocMaso = dmloaithuocMaso;
    }

    public DtDmNhanVien getDtdmnhanvienBacsi() {
        return dtdmnhanvienBacsi;
    }

    public void setDtdmnhanvienBacsi(DtDmNhanVien dtdmnhanvienBacsi) {
        this.dtdmnhanvienBacsi = dtdmnhanvienBacsi;
    }

    public DtDmNhanVien getDtdmnhanvienCn() {
        return dtdmnhanvienCn;
    }

    public DtDmNhanVien getDtdmnhanvienCn(Boolean create) {
        if (create && dtdmnhanvienCn == null) {
            dtdmnhanvienCn = new DtDmNhanVien();
        }
        return dtdmnhanvienCn;
    }

    public void setDtdmnhanvienCn(DtDmNhanVien dtdmnhanvienCn) {
        this.dtdmnhanvienCn = dtdmnhanvienCn;
    }

    public DtDmNhanVien getDtdmnhanvienNhan() {
        return dtdmnhanvienNhan;
    }

    public void setDtdmnhanvienNhan(DtDmNhanVien dtdmnhanvienNhan) {
        this.dtdmnhanvienNhan = dtdmnhanvienNhan;
    }

    public DtDmNhanVien getDtdmnhanvienPhat() {
        return dtdmnhanvienPhat;
    }

    public void setDtdmnhanvienPhat(DtDmNhanVien dtdmnhanvienPhat) {
        this.dtdmnhanvienPhat = dtdmnhanvienPhat;
    }

    /**
     * @return the dmnctMaso
     */
    public DmNguonChuongTrinh getDmnctMaso(Boolean create) {
        if (create && dmnctMaso == null) {
            dmnctMaso = new DmNguonChuongTrinh();
        }
        return dmnctMaso;
    }

    /**
     * @param dmnctMaso the dmnctMaso to set
     */
    public void setDmnctMaso(DmNguonChuongTrinh dmnctMaso) {
        this.dmnctMaso = dmnctMaso;
    }

    /**
     * @return the dmnguonkinhphiMaso
     */
    public DmNguonKinhPhi getDmnguonkinhphiMaso() {
        return dmnguonkinhphiMaso;
    }

     public DmNguonKinhPhi getDmnguonkinhphiMaso(Boolean create) {
        if(create && dmnguonkinhphiMaso==null) dmnguonkinhphiMaso=new  DmNguonKinhPhi();
         return dmnguonkinhphiMaso;
    }

     public String getPhieutranhacungcapLoaiPhieu() {
        return phieutranhacungcapLoaiPhieu;
    }

    public void setPhieutranhacungcapLoaiPhieu(String phieutranhacungcapLoaiPhieu) {
        this.phieutranhacungcapLoaiPhieu = phieutranhacungcapLoaiPhieu;
    }

    /**
     * @param dmnguonkinhphiMaso the dmnguonkinhphiMaso to set
     */
    public void setDmnguonkinhphiMaso(DmNguonKinhPhi dmnguonkinhphiMaso) {
        this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phieutranhacungcapMa != null ? phieutranhacungcapMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuTraNhaCungCap)) {
            return false;
        }
        PhieuTraNhaCungCap other = (PhieuTraNhaCungCap) object;
        if ((this.phieutranhacungcapMa == null && other.phieutranhacungcapMa != null) || (this.phieutranhacungcapMa != null && !this.phieutranhacungcapMa.equals(other.phieutranhacungcapMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.PhieuTraNhaCungCap[phieutranhacungcapMa=" + phieutranhacungcapMa + "]";
    }

    

    
}
