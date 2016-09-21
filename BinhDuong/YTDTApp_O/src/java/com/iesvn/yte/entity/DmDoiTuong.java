/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_DOI_TUONG")
@NamedQueries({})
public class DmDoiTuong implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_DOI_TUONG")
    @SequenceGenerator(name = "DM_DOI_TUONG", sequenceName = "DM_DOI_TUONG_DMDOITUONG_MASO_S", allocationSize = 1)
    @Column(name = "DMDOITUONG_MASO", nullable = false)
    private Integer dmdoituongMaso;
    @Column(name = "DMDOITUONG_MA", nullable = false)
    private String dmdoituongMa;
    @Column(name = "DMDOITUONG_MAGOM")
    private String dmdoituongMagom;
    @Column(name = "DMDOITUONG_THUTUBC")
    private Integer dmdoituongThutubc;
    @Column(name = "DMDOITUONG_TEN", nullable = false)
    private String dmdoituongTen;
    @Column(name = "DMDOITUONG_CHON1")
    private Boolean dmdoituongChon1;
    @Column(name = "DMDOITUONG_CHON2")
    private Boolean dmdoituongChon2;
    @Column(name = "DMDOITUONG_KHOBHYT")
    private Boolean dmdoituongKhobhyt;
    @Column(name = "DMDOITUONG_KHOLE")
    private Boolean dmdoituongKhole;
    @Column(name = "DMDOITUONG_KHOCHINH")
    private Boolean dmdoituongKhochinh;
    @Column(name = "DMDOITUONG_BENHAN")
    private String dmdoituongBenhan;
    @Column(name = "DMDOITUONG_THUPHI")
    private Boolean dmdoituongThuphi;
    @Column(name = "DMDOITUONG_TIEUHAO")
    private Boolean dmdoituongTieuhao;
    @Column(name = "DMDOITUONG_DINHDUONG")
    private Boolean dmdoituongDinhduong;
    @Column(name = "DMDOITUONG_TYLEMIEN")
    private Float dmdoituongTylemien;
    @Column(name = "DMDOITUONG_TENBC")
    private String dmdoituongTenbc;
    @Column(name = "DMDOITUONG_MAUPHIEU")
    private String dmdoituongMauphieu;
    @Column(name = "DMDOITUONG_NGAYGIOCN")
    private Double dmdoituongNgaygiocn;
    @Column(name = "DMDOITUONG_DT")
    private Boolean dmdoituongDt;
    @Column(name = "DMDOITUONG_QL")
    private Boolean dmdoituongQl;
    @Column(name = "DMDOITUONG_DP")
    private Boolean dmdoituongDp;
//    @OneToMany(mappedBy = "clskhamChedott")
//    private Collection<ClsKham> clsKhamCollection;
//    @OneToMany(mappedBy = "clskhamChedott1")
//    private Collection<ClsKham> clsKhamCollection1;
//    @OneToMany(mappedBy = "doituongMa")
//    private Collection<TiepDon> tiepDonCollection;
//    @OneToMany(mappedBy = "dmdoituongMaso")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection;
//    @OneToMany(mappedBy = "dmdoituongMaso1")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection1;
//    @OneToMany(mappedBy = "dmdoituongMaso")
//    private Collection<CtPhieuDt> ctPhieuDtCollection;
//    @OneToMany(mappedBy = "miengiamDoituong")
//    private Collection<MienGiam> mienGiamCollection;
//    @OneToMany(mappedBy = "miengiamDoituong1")
//    private Collection<MienGiam> mienGiamCollection1;
//    @OneToMany(mappedBy = "dmdoituongMaso")
//    private Collection<PhieuDuTru> phieuDuTruCollection;
//    @OneToMany(mappedBy = "dmdoituongMaso1")
//    private Collection<PhieuDuTru> phieuDuTruCollection1;
//    @OneToMany(mappedBy = "dmdoituongMaso")
//    private Collection<PhieuTraKho> phieuTraKhoCollection;
//    @OneToMany(mappedBy = "doituongMa")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "doituongMa1")
//    private Collection<Hsba> hsbaCollection1;

    public DmDoiTuong() {
    }

    public DmDoiTuong(Integer dmdoituongMaso) {
        this.dmdoituongMaso = dmdoituongMaso;
    }

    public DmDoiTuong(Integer dmdoituongMaso, String dmdoituongMa, String dmdoituongTen) {
        this.dmdoituongMaso = dmdoituongMaso;
        this.dmdoituongMa = dmdoituongMa;
        this.dmdoituongTen = dmdoituongTen;
    }

    public Integer getDmdoituongMaso() {
        return dmdoituongMaso;
    }

    public void setDmdoituongMaso(Integer dmdoituongMaso) {
        this.dmdoituongMaso = dmdoituongMaso;
    }

    public String getDmdoituongMa() {
        return dmdoituongMa;
    }

    public void setDmdoituongMa(String dmdoituongMa) {
        this.dmdoituongMa = dmdoituongMa;
    }

    public String getDmdoituongMagom() {
        return dmdoituongMagom;
    }

    public void setDmdoituongMagom(String dmdoituongMagom) {
        this.dmdoituongMagom = dmdoituongMagom;
    }

    public Integer getDmdoituongThutubc() {
        return dmdoituongThutubc;
    }

    public void setDmdoituongThutubc(Integer dmdoituongThutubc) {
        this.dmdoituongThutubc = dmdoituongThutubc;
    }

    public String getDmdoituongTen() {
        return dmdoituongTen;
    }

    public void setDmdoituongTen(String dmdoituongTen) {
        this.dmdoituongTen = dmdoituongTen;
    }

    public Boolean getDmdoituongChon1() {
        return dmdoituongChon1;
    }

    public void setDmdoituongChon1(Boolean dmdoituongChon1) {
        this.dmdoituongChon1 = dmdoituongChon1;
    }

    public Boolean getDmdoituongChon2() {
        return dmdoituongChon2;
    }

    public void setDmdoituongChon2(Boolean dmdoituongChon2) {
        this.dmdoituongChon2 = dmdoituongChon2;
    }

    public Boolean getDmdoituongKhobhyt() {
        return dmdoituongKhobhyt;
    }

    public void setDmdoituongKhobhyt(Boolean dmdoituongKhobhyt) {
        this.dmdoituongKhobhyt = dmdoituongKhobhyt;
    }

    public Boolean getDmdoituongKhole() {
        return dmdoituongKhole;
    }

    public void setDmdoituongKhole(Boolean dmdoituongKhole) {
        this.dmdoituongKhole = dmdoituongKhole;
    }

    public Boolean getDmdoituongKhochinh() {
        return dmdoituongKhochinh;
    }

    public void setDmdoituongKhochinh(Boolean dmdoituongKhochinh) {
        this.dmdoituongKhochinh = dmdoituongKhochinh;
    }

    public String getDmdoituongBenhan() {
        return dmdoituongBenhan;
    }

    public void setDmdoituongBenhan(String dmdoituongBenhan) {
        this.dmdoituongBenhan = dmdoituongBenhan;
    }

    public Boolean getDmdoituongThuphi() {
        return dmdoituongThuphi;
    }

    public void setDmdoituongThuphi(Boolean dmdoituongThuphi) {
        this.dmdoituongThuphi = dmdoituongThuphi;
    }

    public Boolean getDmdoituongTieuhao() {
        return dmdoituongTieuhao;
    }

    public void setDmdoituongTieuhao(Boolean dmdoituongTieuhao) {
        this.dmdoituongTieuhao = dmdoituongTieuhao;
    }

    public Boolean getDmdoituongDinhduong() {
        return dmdoituongDinhduong;
    }

    public void setDmdoituongDinhduong(Boolean dmdoituongDinhduong) {
        this.dmdoituongDinhduong = dmdoituongDinhduong;
    }

    public Float getDmdoituongTylemien() {
        return dmdoituongTylemien;
    }

    public void setDmdoituongTylemien(Float dmdoituongTylemien) {
        this.dmdoituongTylemien = dmdoituongTylemien;
    }

    public String getDmdoituongTenbc() {
        return dmdoituongTenbc;
    }

    public void setDmdoituongTenbc(String dmdoituongTenbc) {
        this.dmdoituongTenbc = dmdoituongTenbc;
    }

    public String getDmdoituongMauphieu() {
        return dmdoituongMauphieu;
    }

    public void setDmdoituongMauphieu(String dmdoituongMauphieu) {
        this.dmdoituongMauphieu = dmdoituongMauphieu;
    }

    public Double getDmdoituongNgaygiocn() {
        return dmdoituongNgaygiocn;
    }

    public void setDmdoituongNgaygiocn(Double dmdoituongNgaygiocn) {
        this.dmdoituongNgaygiocn = dmdoituongNgaygiocn;
    }

    public Boolean getDmdoituongDt() {
        return dmdoituongDt;
    }

    public void setDmdoituongDt(Boolean dmdoituongDt) {
        this.dmdoituongDt = dmdoituongDt;
    }

    public Boolean getDmdoituongQl() {
        return dmdoituongQl;
    }

    public void setDmdoituongQl(Boolean dmdoituongQl) {
        this.dmdoituongQl = dmdoituongQl;
    }

    public Boolean getDmdoituongDp() {
        return dmdoituongDp;
    }

    public void setDmdoituongDp(Boolean dmdoituongDp) {
        this.dmdoituongDp = dmdoituongDp;
    }

//    public Collection<ClsKham> getClsKhamCollection() {
//        return clsKhamCollection;
//    }
//
//    public void setClsKhamCollection(Collection<ClsKham> clsKhamCollection) {
//        this.clsKhamCollection = clsKhamCollection;
//    }

//    public Collection<ClsKham> getClsKhamCollection1() {
//        return clsKhamCollection1;
//    }
//
//    public void setClsKhamCollection1(Collection<ClsKham> clsKhamCollection1) {
//        this.clsKhamCollection1 = clsKhamCollection1;
//    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection() {
//        return phieuXuatKhoCollection;
//    }
//
//    public void setPhieuXuatKhoCollection(Collection<PhieuXuatKho> phieuXuatKhoCollection) {
//        this.phieuXuatKhoCollection = phieuXuatKhoCollection;
//    }

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection1() {
//        return phieuXuatKhoCollection1;
//    }
//
//    public void setPhieuXuatKhoCollection1(Collection<PhieuXuatKho> phieuXuatKhoCollection1) {
//        this.phieuXuatKhoCollection1 = phieuXuatKhoCollection1;
//    }

//    public Collection<CtPhieuDt> getCtPhieuDtCollection() {
//        return ctPhieuDtCollection;
//    }
//
//    public void setCtPhieuDtCollection(Collection<CtPhieuDt> ctPhieuDtCollection) {
//        this.ctPhieuDtCollection = ctPhieuDtCollection;
//    }

//    public Collection<MienGiam> getMienGiamCollection() {
//        return mienGiamCollection;
//    }
//
//    public void setMienGiamCollection(Collection<MienGiam> mienGiamCollection) {
//        this.mienGiamCollection = mienGiamCollection;
//    }

//    public Collection<MienGiam> getMienGiamCollection1() {
//        return mienGiamCollection1;
//    }
//
//    public void setMienGiamCollection1(Collection<MienGiam> mienGiamCollection1) {
//        this.mienGiamCollection1 = mienGiamCollection1;
//    }

//    public Collection<PhieuDuTru> getPhieuDuTruCollection() {
//        return phieuDuTruCollection;
//    }
//
//    public void setPhieuDuTruCollection(Collection<PhieuDuTru> phieuDuTruCollection) {
//        this.phieuDuTruCollection = phieuDuTruCollection;
//    }

//    public Collection<PhieuDuTru> getPhieuDuTruCollection1() {
//        return phieuDuTruCollection1;
//    }
//
//    public void setPhieuDuTruCollection1(Collection<PhieuDuTru> phieuDuTruCollection1) {
//        this.phieuDuTruCollection1 = phieuDuTruCollection1;
//    }

//    public Collection<PhieuTraKho> getPhieuTraKhoCollection() {
//        return phieuTraKhoCollection;
//    }
//
//    public void setPhieuTraKhoCollection(Collection<PhieuTraKho> phieuTraKhoCollection) {
//        this.phieuTraKhoCollection = phieuTraKhoCollection;
//    }

//    public Collection<Hsba> getHsbaCollection() {
//        return hsbaCollection;
//    }
//
//    public void setHsbaCollection(Collection<Hsba> hsbaCollection) {
//        this.hsbaCollection = hsbaCollection;
//    }

//    public Collection<Hsba> getHsbaCollection1() {
//        return hsbaCollection1;
//    }
//
//    public void setHsbaCollection1(Collection<Hsba> hsbaCollection1) {
//        this.hsbaCollection1 = hsbaCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmdoituongMaso != null ? dmdoituongMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmDoiTuong)) {
            return false;
        }
        DmDoiTuong other = (DmDoiTuong) object;
        if ((this.dmdoituongMaso == null && other.dmdoituongMaso != null) || (this.dmdoituongMaso != null && !this.dmdoituongMaso.equals(other.dmdoituongMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmDoiTuong[dmdoituongMaso=" + dmdoituongMaso + "]";
    }

}


