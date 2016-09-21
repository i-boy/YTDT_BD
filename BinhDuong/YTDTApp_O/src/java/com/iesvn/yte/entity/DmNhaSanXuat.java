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
@Table(name = "DM_NHA_SAN_XUAT")
@NamedQueries({})
public class DmNhaSanXuat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NHA_SAN_XUAT")
    @SequenceGenerator(name = "DM_NHA_SAN_XUAT", sequenceName = "DM_NHA_SAN_XUAT_DMNHASANXUAT_M", allocationSize = 1)
    @Column(name = "DMNHASANXUAT_MASO", nullable = false)
    private Integer dmnhasanxuatMaso;
    @Column(name = "DMNHASANXUAT_MA", nullable = false)
    private String dmnhasanxuatMa;
    @Column(name = "DMNHASANXUAT_TEN", nullable = false)
    private String dmnhasanxuatTen;
    @Column(name = "DMNHASANXUAT_TENVN")
    private String dmnhasanxuatTenvn;
    @Column(name = "DMNHASANXUAT_PHANBIET")
    private String dmnhasanxuatPhanbiet;
    @Column(name = "DMNHASANXUAT_PHANLOAI")
    private String dmnhasanxuatPhanloai;
    @Column(name = "DMNHASANXUAT_HANG")
    private Boolean dmnhasanxuatHang;
    @Column(name = "DMNHASANXUAT_NGAYGIOCN")
    private Double dmnhasanxuatNgaygiocn;
    @Column(name = "DMNHASANXUAT_DT")
    private Boolean dmnhasanxuatDt;
    @Column(name = "DMNHASANXUAT_QL")
    private Boolean dmnhasanxuatQl;
    @Column(name = "DMNHASANXUAT_DP")
    private Boolean dmnhasanxuatDp;
//    @OneToMany(mappedBy = "thuocnoitruHangsx")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection;
//    @OneToMany(mappedBy = "thuocnoitruHangsx1")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection1;
//    @OneToMany(mappedBy = "dmnhasanxuatMaso")
//    private Collection<CtTraKho> ctTraKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmnhasanxuatMaso")
//    private Collection<CtXuatKho> ctXuatKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmnhasanxuatMaso")
//    private Collection<CtNhapKho> ctNhapKhoCollection;
//    @OneToMany(mappedBy = "dmnhasanxuatMaso")
//    private Collection<DmThuoc> dmThuocCollection;
//    @OneToMany(mappedBy = "dmnhasanxuatMaso1")
//    private Collection<DmThuoc> dmThuocCollection1;
//    @OneToMany(mappedBy = "dmnhasanxuatMaso")
//    private Collection<CtPhieuDt> ctPhieuDtCollection;
//    @OneToMany(mappedBy = "dmnhasanxuatMaso")
//    private Collection<CtToaLinhk> ctToaLinhkCollection;
//    @OneToMany(mappedBy = "dmnhasanxuatMaso1")
//    private Collection<CtToaLinhk> ctToaLinhkCollection1;
//    @OneToMany(mappedBy = "dmnhasanxuatMaso")
//    private Collection<CtXuatBh> ctXuatBhCollection;
//    @OneToMany(mappedBy = "thuocphongkhamHangsx")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection;
//    @OneToMany(mappedBy = "thuocphongkhamHangsx1")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection1;
//    @OneToMany(mappedBy = "dmnhasanxuatMaso")
//    private Collection<TonKho> tonKhoCollection;
//    @OneToMany(mappedBy = "dmnhasanxuatMaso")
//    private Collection<CtToaThuock> ctToaThuockCollection;
//    @OneToMany(mappedBy = "dmnhasanxuatMaso1")
//    private Collection<CtToaThuock> ctToaThuockCollection1;

    public DmNhaSanXuat() {
    }

    public DmNhaSanXuat(Integer dmnhasanxuatMaso) {
        this.dmnhasanxuatMaso = dmnhasanxuatMaso;
    }

    public DmNhaSanXuat(Integer dmnhasanxuatMaso, String dmnhasanxuatMa, String dmnhasanxuatTen) {
        this.dmnhasanxuatMaso = dmnhasanxuatMaso;
        this.dmnhasanxuatMa = dmnhasanxuatMa;
        this.dmnhasanxuatTen = dmnhasanxuatTen;
    }

    public Integer getDmnhasanxuatMaso() {
        return dmnhasanxuatMaso;
    }

    public void setDmnhasanxuatMaso(Integer dmnhasanxuatMaso) {
        this.dmnhasanxuatMaso = dmnhasanxuatMaso;
    }

    public String getDmnhasanxuatMa() {
        return dmnhasanxuatMa;
    }

    public void setDmnhasanxuatMa(String dmnhasanxuatMa) {
        this.dmnhasanxuatMa = dmnhasanxuatMa;
    }

    public String getDmnhasanxuatTen() {
        return dmnhasanxuatTen;
    }

    public void setDmnhasanxuatTen(String dmnhasanxuatTen) {
        this.dmnhasanxuatTen = dmnhasanxuatTen;
    }

    public String getDmnhasanxuatTenvn() {
        return dmnhasanxuatTenvn;
    }

    public void setDmnhasanxuatTenvn(String dmnhasanxuatTenvn) {
        this.dmnhasanxuatTenvn = dmnhasanxuatTenvn;
    }

    public String getDmnhasanxuatPhanbiet() {
        return dmnhasanxuatPhanbiet;
    }

    public void setDmnhasanxuatPhanbiet(String dmnhasanxuatPhanbiet) {
        this.dmnhasanxuatPhanbiet = dmnhasanxuatPhanbiet;
    }

    public String getDmnhasanxuatPhanloai() {
        return dmnhasanxuatPhanloai;
    }

    public void setDmnhasanxuatPhanloai(String dmnhasanxuatPhanloai) {
        this.dmnhasanxuatPhanloai = dmnhasanxuatPhanloai;
    }

    public Boolean getDmnhasanxuatHang() {
        return dmnhasanxuatHang;
    }

    public void setDmnhasanxuatHang(Boolean dmnhasanxuatHang) {
        this.dmnhasanxuatHang = dmnhasanxuatHang;
    }

    public Double getDmnhasanxuatNgaygiocn() {
        return dmnhasanxuatNgaygiocn;
    }

    public void setDmnhasanxuatNgaygiocn(Double dmnhasanxuatNgaygiocn) {
        this.dmnhasanxuatNgaygiocn = dmnhasanxuatNgaygiocn;
    }

    public Boolean getDmnhasanxuatDt() {
        return dmnhasanxuatDt;
    }

    public void setDmnhasanxuatDt(Boolean dmnhasanxuatDt) {
        this.dmnhasanxuatDt = dmnhasanxuatDt;
    }

    public Boolean getDmnhasanxuatQl() {
        return dmnhasanxuatQl;
    }

    public void setDmnhasanxuatQl(Boolean dmnhasanxuatQl) {
        this.dmnhasanxuatQl = dmnhasanxuatQl;
    }

    public Boolean getDmnhasanxuatDp() {
        return dmnhasanxuatDp;
    }

    public void setDmnhasanxuatDp(Boolean dmnhasanxuatDp) {
        this.dmnhasanxuatDp = dmnhasanxuatDp;
    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection() {
//        return thuocNoiTruCollection;
//    }
//
//    public void setThuocNoiTruCollection(Collection<ThuocNoiTru> thuocNoiTruCollection) {
//        this.thuocNoiTruCollection = thuocNoiTruCollection;
//    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection1() {
//        return thuocNoiTruCollection1;
//    }
//
//    public void setThuocNoiTruCollection1(Collection<ThuocNoiTru> thuocNoiTruCollection1) {
//        this.thuocNoiTruCollection1 = thuocNoiTruCollection1;
//    }

//    public Collection<CtTraKho> getCtTraKhoCollection() {
//        return ctTraKhoCollection;
//    }
//
//    public void setCtTraKhoCollection(Collection<CtTraKho> ctTraKhoCollection) {
//        this.ctTraKhoCollection = ctTraKhoCollection;
//    }

//    public Collection<CtXuatKho> getCtXuatKhoCollection() {
//        return ctXuatKhoCollection;
//    }
//
//    public void setCtXuatKhoCollection(Collection<CtXuatKho> ctXuatKhoCollection) {
//        this.ctXuatKhoCollection = ctXuatKhoCollection;
//    }

//    public Collection<CtNhapKho> getCtNhapKhoCollection() {
//        return ctNhapKhoCollection;
//    }
//
//    public void setCtNhapKhoCollection(Collection<CtNhapKho> ctNhapKhoCollection) {
//        this.ctNhapKhoCollection = ctNhapKhoCollection;
//    }

//    public Collection<DmThuoc> getDmThuocCollection() {
//        return dmThuocCollection;
//    }
//
//    public void setDmThuocCollection(Collection<DmThuoc> dmThuocCollection) {
//        this.dmThuocCollection = dmThuocCollection;
//    }

//    public Collection<DmThuoc> getDmThuocCollection1() {
//        return dmThuocCollection1;
//    }
//
//    public void setDmThuocCollection1(Collection<DmThuoc> dmThuocCollection1) {
//        this.dmThuocCollection1 = dmThuocCollection1;
//    }

//    public Collection<CtPhieuDt> getCtPhieuDtCollection() {
//        return ctPhieuDtCollection;
//    }
//
//    public void setCtPhieuDtCollection(Collection<CtPhieuDt> ctPhieuDtCollection) {
//        this.ctPhieuDtCollection = ctPhieuDtCollection;
//    }

//    public Collection<CtToaLinhk> getCtToaLinhkCollection() {
//        return ctToaLinhkCollection;
//    }
//
//    public void setCtToaLinhkCollection(Collection<CtToaLinhk> ctToaLinhkCollection) {
//        this.ctToaLinhkCollection = ctToaLinhkCollection;
//    }

//    public Collection<CtToaLinhk> getCtToaLinhkCollection1() {
//        return ctToaLinhkCollection1;
//    }
//
//    public void setCtToaLinhkCollection1(Collection<CtToaLinhk> ctToaLinhkCollection1) {
//        this.ctToaLinhkCollection1 = ctToaLinhkCollection1;
//    }

//    public Collection<CtXuatBh> getCtXuatBhCollection() {
//        return ctXuatBhCollection;
//    }
//
//    public void setCtXuatBhCollection(Collection<CtXuatBh> ctXuatBhCollection) {
//        this.ctXuatBhCollection = ctXuatBhCollection;
//    }

//    public Collection<ThuocPhongKham> getThuocPhongKhamCollection() {
//        return thuocPhongKhamCollection;
//    }
//
//    public void setThuocPhongKhamCollection(Collection<ThuocPhongKham> thuocPhongKhamCollection) {
//        this.thuocPhongKhamCollection = thuocPhongKhamCollection;
//    }

//    public Collection<ThuocPhongKham> getThuocPhongKhamCollection1() {
//        return thuocPhongKhamCollection1;
//    }
//
//    public void setThuocPhongKhamCollection1(Collection<ThuocPhongKham> thuocPhongKhamCollection1) {
//        this.thuocPhongKhamCollection1 = thuocPhongKhamCollection1;
//    }

//    public Collection<TonKho> getTonKhoCollection() {
//        return tonKhoCollection;
//    }
//
//    public void setTonKhoCollection(Collection<TonKho> tonKhoCollection) {
//        this.tonKhoCollection = tonKhoCollection;
//    }

//    public Collection<CtToaThuock> getCtToaThuockCollection() {
//        return ctToaThuockCollection;
//    }
//
//    public void setCtToaThuockCollection(Collection<CtToaThuock> ctToaThuockCollection) {
//        this.ctToaThuockCollection = ctToaThuockCollection;
//    }

//    public Collection<CtToaThuock> getCtToaThuockCollection1() {
//        return ctToaThuockCollection1;
//    }
//
//    public void setCtToaThuockCollection1(Collection<CtToaThuock> ctToaThuockCollection1) {
//        this.ctToaThuockCollection1 = ctToaThuockCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmnhasanxuatMaso != null ? dmnhasanxuatMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmNhaSanXuat)) {
            return false;
        }
        DmNhaSanXuat other = (DmNhaSanXuat) object;
        if ((this.dmnhasanxuatMaso == null && other.dmnhasanxuatMaso != null) || (this.dmnhasanxuatMaso != null && !this.dmnhasanxuatMaso.equals(other.dmnhasanxuatMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmNhaSanXuat[dmnhasanxuatMaso=" + dmnhasanxuatMaso + "]";
    }

}


