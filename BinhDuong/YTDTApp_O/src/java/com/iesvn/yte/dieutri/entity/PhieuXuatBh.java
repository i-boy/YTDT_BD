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
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PHIEU_XUAT_BH")
@NamedQueries({})
public class PhieuXuatBh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PHIEUXUATBH_MA", nullable = false)
    private String phieuxuatbhMa;
    @Column(name = "PHIEUXUATBH_MAPHIEUK")
    private String phieuxuatbhMaphieuk;
    @Column(name = "PHIEUXUATBH_NGAYLAP", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date phieuxuatbhNgaylap;
    @Column(name = "PHIEUXUATBH_TIENKHAM")
    private Double phieuxuatbhTienkham;
    @Column(name = "PHIEUXUATBH_KYHIEU")
    private String phieuxuatbhKyhieu;
    @Column(name = "PHIEUXUATBH_QUYEN")
    private String phieuxuatbhQuyen;
    @Column(name = "PHIEUXUATBH_BIENLAI")
    private String phieuxuatbhBienlai;
    @Column(name = "PHIEUXUATBH_THANHTIEN")
    private Double phieuxuatbhThanhtien;
    @Column(name = "PHIEUXUATBH_THANHTIENB")
    private Double phieuxuatbhThanhtienb;
    @Column(name = "PHIEUXUATBH_MIENGIAM")
    private Double phieuxuatbhMiengiam;
    @Column(name = "PHIEUXUATBH_MADUYET")
    private String phieuxuatbhMaduyet;
    @Column(name = "PHIEUXUATBH_THATTHU")
    private Double phieuxuatbhThatthu;
    @Column(name = "PHIEUXUATBH_CHEDO")
    private String phieuxuatbhChedo;
    @Column(name = "PHIEUXUATBH_TAIKHOAN")
    private String phieuxuatbhTaikhoan;
    @Column(name = "PHIEUXUATBH_DOIUNG")
    private String phieuxuatbhDoiung;
    @Column(name = "PHIEUXUATBH_TRONSO")
    private Double phieuxuatbhTronso;
    @Column(name = "PHIEUXUATBH_NGAYDIEUTRI")
    @Temporal(TemporalType.DATE)
    private Date phieuxuatbhNgaydieutri;
    @Column(name = "PHIEUXUATBH_PHIEUDIEUTRI")
    private String phieuxuatbhPhieudieutri;
    @Column(name = "PHIEUXUATBH_PHANBIET")
    private String phieuxuatbhPhanbiet;
    @Column(name = "PHIEUXUATBH_SOTHANG")
    private Integer phieuxuatbhSothang;
    @Column(name = "PHIEUXUATBH_NGAYGIOCN", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieuxuatbhNgaygiocn;
    @Column(name = "PHIEUXUATBH_NGAYGIOPHAT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieuxuatbhNgaygiophat;
    @Column(name = "PHIEUXUATBH_NOIDUNGTHU")
    private String phieuxuatbhNoidungthu;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;
    @JoinColumn(name = "DTDMNHANVIEN_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienBacsi;
    @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;
    @JoinColumn(name = "DTDMNHANVIEN_PHAT", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienPhat;
    @JoinColumn(name = "TIEPDON_MA", referencedColumnName = "TIEPDON_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private TiepDon tiepdonMa;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phieuxuatbhMa")
//    private Collection<CtXuatBh> ctXuatBhCollection;

    public PhieuXuatBh() {
    }

    public PhieuXuatBh(String phieuxuatbhMa) {
        this.phieuxuatbhMa = phieuxuatbhMa;
    }

    public PhieuXuatBh(String phieuxuatbhMa, Date phieuxuatbhNgaylap, Date phieuxuatbhNgaygiocn, Date phieuxuatbhNgaygiophat) {
        this.phieuxuatbhMa = phieuxuatbhMa;
        this.phieuxuatbhNgaylap = phieuxuatbhNgaylap;
        this.phieuxuatbhNgaygiocn = phieuxuatbhNgaygiocn;
        this.phieuxuatbhNgaygiophat = phieuxuatbhNgaygiophat;
    }

    public String getPhieuxuatbhMa() {
        return phieuxuatbhMa;
    }

    public void setPhieuxuatbhMa(String phieuxuatbhMa) {
        this.phieuxuatbhMa = phieuxuatbhMa;
    }

    public String getPhieuxuatbhMaphieuk() {
        return phieuxuatbhMaphieuk;
    }

    public void setPhieuxuatbhMaphieuk(String phieuxuatbhMaphieuk) {
        this.phieuxuatbhMaphieuk = phieuxuatbhMaphieuk;
    }

    public Date getPhieuxuatbhNgaylap() {
        return phieuxuatbhNgaylap;
    }

    public void setPhieuxuatbhNgaylap(Date phieuxuatbhNgaylap) {
        this.phieuxuatbhNgaylap = phieuxuatbhNgaylap;
    }

    public Double getPhieuxuatbhTienkham() {
        return phieuxuatbhTienkham;
    }

    public void setPhieuxuatbhTienkham(Double phieuxuatbhTienkham) {
        this.phieuxuatbhTienkham = phieuxuatbhTienkham;
    }

    public String getPhieuxuatbhKyhieu() {
        return phieuxuatbhKyhieu;
    }

    public void setPhieuxuatbhKyhieu(String phieuxuatbhKyhieu) {
        this.phieuxuatbhKyhieu = phieuxuatbhKyhieu;
    }

    public String getPhieuxuatbhQuyen() {
        return phieuxuatbhQuyen;
    }

    public void setPhieuxuatbhQuyen(String phieuxuatbhQuyen) {
        this.phieuxuatbhQuyen = phieuxuatbhQuyen;
    }

    public String getPhieuxuatbhBienlai() {
        return phieuxuatbhBienlai;
    }

    public void setPhieuxuatbhBienlai(String phieuxuatbhBienlai) {
        this.phieuxuatbhBienlai = phieuxuatbhBienlai;
    }

    public Double getPhieuxuatbhThanhtien() {
        return phieuxuatbhThanhtien;
    }

    public void setPhieuxuatbhThanhtien(Double phieuxuatbhThanhtien) {
        this.phieuxuatbhThanhtien = phieuxuatbhThanhtien;
    }

    public Double getPhieuxuatbhThanhtienb() {
        return phieuxuatbhThanhtienb;
    }

    public void setPhieuxuatbhThanhtienb(Double phieuxuatbhThanhtienb) {
        this.phieuxuatbhThanhtienb = phieuxuatbhThanhtienb;
    }

    public Double getPhieuxuatbhMiengiam() {
        return phieuxuatbhMiengiam;
    }

    public void setPhieuxuatbhMiengiam(Double phieuxuatbhMiengiam) {
        this.phieuxuatbhMiengiam = phieuxuatbhMiengiam;
    }

    public String getPhieuxuatbhMaduyet() {
        return phieuxuatbhMaduyet;
    }

    public void setPhieuxuatbhMaduyet(String phieuxuatbhMaduyet) {
        this.phieuxuatbhMaduyet = phieuxuatbhMaduyet;
    }

    public Double getPhieuxuatbhThatthu() {
        return phieuxuatbhThatthu;
    }

    public void setPhieuxuatbhThatthu(Double phieuxuatbhThatthu) {
        this.phieuxuatbhThatthu = phieuxuatbhThatthu;
    }

    public String getPhieuxuatbhChedo() {
        return phieuxuatbhChedo;
    }

    public void setPhieuxuatbhChedo(String phieuxuatbhChedo) {
        this.phieuxuatbhChedo = phieuxuatbhChedo;
    }

    public String getPhieuxuatbhTaikhoan() {
        return phieuxuatbhTaikhoan;
    }

    public void setPhieuxuatbhTaikhoan(String phieuxuatbhTaikhoan) {
        this.phieuxuatbhTaikhoan = phieuxuatbhTaikhoan;
    }

    public String getPhieuxuatbhDoiung() {
        return phieuxuatbhDoiung;
    }

    public void setPhieuxuatbhDoiung(String phieuxuatbhDoiung) {
        this.phieuxuatbhDoiung = phieuxuatbhDoiung;
    }

    public Double getPhieuxuatbhTronso() {
        return phieuxuatbhTronso;
    }

    public void setPhieuxuatbhTronso(Double phieuxuatbhTronso) {
        this.phieuxuatbhTronso = phieuxuatbhTronso;
    }

    public Date getPhieuxuatbhNgaydieutri() {
        return phieuxuatbhNgaydieutri;
    }

    public void setPhieuxuatbhNgaydieutri(Date phieuxuatbhNgaydieutri) {
        this.phieuxuatbhNgaydieutri = phieuxuatbhNgaydieutri;
    }

    public String getPhieuxuatbhPhieudieutri() {
        return phieuxuatbhPhieudieutri;
    }

    public void setPhieuxuatbhPhieudieutri(String phieuxuatbhPhieudieutri) {
        this.phieuxuatbhPhieudieutri = phieuxuatbhPhieudieutri;
    }

    public String getPhieuxuatbhPhanbiet() {
        return phieuxuatbhPhanbiet;
    }

    public void setPhieuxuatbhPhanbiet(String phieuxuatbhPhanbiet) {
        this.phieuxuatbhPhanbiet = phieuxuatbhPhanbiet;
    }

    public Integer getPhieuxuatbhSothang() {
        return phieuxuatbhSothang;
    }

    public void setPhieuxuatbhSothang(Integer phieuxuatbhSothang) {
        this.phieuxuatbhSothang = phieuxuatbhSothang;
    }

    public Date getPhieuxuatbhNgaygiocn() {
        return phieuxuatbhNgaygiocn;
    }

    public void setPhieuxuatbhNgaygiocn(Date phieuxuatbhNgaygiocn) {
        this.phieuxuatbhNgaygiocn = phieuxuatbhNgaygiocn;
    }

    public Date getPhieuxuatbhNgaygiophat() {
        return phieuxuatbhNgaygiophat;
    }

    public void setPhieuxuatbhNgaygiophat(Date phieuxuatbhNgaygiophat) {
        this.phieuxuatbhNgaygiophat = phieuxuatbhNgaygiophat;
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

    public TiepDon getTiepdonMa(boolean create) {
        if (create && tiepdonMa == null) {
            tiepdonMa = new TiepDon();
        }
        return tiepdonMa;
    }

    public TiepDon getTiepdonMa() {
        return tiepdonMa;
    }

    public void setTiepdonMa(TiepDon tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

//    public Collection<CtXuatBh> getCtXuatBhCollection() {
//        return ctXuatBhCollection;
//    }
//
//    public void setCtXuatBhCollection(Collection<CtXuatBh> ctXuatBhCollection) {
//        this.ctXuatBhCollection = ctXuatBhCollection;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phieuxuatbhMa != null ? phieuxuatbhMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuXuatBh)) {
            return false;
        }
        PhieuXuatBh other = (PhieuXuatBh) object;
        if ((this.phieuxuatbhMa == null && other.phieuxuatbhMa != null) || (this.phieuxuatbhMa != null && !this.phieuxuatbhMa.equals(other.phieuxuatbhMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.PhieuXuatBh[phieuxuatbhMa=" + phieuxuatbhMa + "]";
    }

    /**
     * @return the phieuxuatbhNoidungthu
     */
    public String getPhieuxuatbhNoidungthu() {
        return phieuxuatbhNoidungthu;
    }

    /**
     * @param phieuxuatbhNoidungthu the phieuxuatbhNoidungthu to set
     */
    public void setPhieuxuatbhNoidungthu(String phieuxuatbhNoidungthu) {
        this.phieuxuatbhNoidungthu = phieuxuatbhNoidungthu;
    }

    
}


