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
@Table(name = "DM_QUOC_GIA")
@NamedQueries({})
public class DmQuocGia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_QUOC_GIA_DMQUOCGIA")
    @SequenceGenerator(name = "DM_QUOC_GIA_DMQUOCGIA", sequenceName = "DM_QUOC_GIA_DMQUOCGIA_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMQUOCGIA_MASO", nullable = false)
    private Integer dmquocgiaMaso;
    @Column(name = "DMQUOCGIA_MA", nullable = false)
    private String dmquocgiaMa;
    @Column(name = "DMQUOCGIA_TEN", nullable = false)
    private String dmquocgiaTen;
    @Column(name = "DMQUOCGIA_TENVN")
    private String dmquocgiaTenvn;
    @Column(name = "DMQUOCGIA_NGAYGIOCN")
    private Double dmquocgiaNgaygiocn;
    @Column(name = "DMQUOCGIA_CHON")
    private Boolean dmquocgiaChon;
//    @OneToMany(mappedBy = "thuocnoitruQuocgia")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection;
//    @OneToMany(mappedBy = "thuocnoitruQuocgia1")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection1;
//    @OneToMany(mappedBy = "dmquocgiaMaso")
//    private Collection<CtTraKho> ctTraKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmquocgiaMaso")
//    private Collection<CtXuatKho> ctXuatKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmquocgiaMaso")
//    private Collection<CtNhapKho> ctNhapKhoCollection;
//    @OneToMany(mappedBy = "dmthuocNuocdefa")
//    private Collection<DmThuoc> dmThuocCollection;
//    @OneToMany(mappedBy = "dmthuocNuocdefa1")
//    private Collection<DmThuoc> dmThuocCollection1;
//    @OneToMany(mappedBy = "dmquocgiaMaso")
//    private Collection<CtPhieuDt> ctPhieuDtCollection;
//    @OneToMany(mappedBy = "cttoalinhkQuocgia")
//    private Collection<CtToaLinhk> ctToaLinhkCollection;
//    @OneToMany(mappedBy = "cttoalinhkQuocgia1")
//    private Collection<CtToaLinhk> ctToaLinhkCollection1;
//    @OneToMany(mappedBy = "dmquocgiaMaso")
//    private Collection<CtXuatBh> ctXuatBhCollection;
//    @OneToMany(mappedBy = "thuocphongkhamQuocgia")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection;
//    @OneToMany(mappedBy = "thuocphongkhamQuocgia1")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection1;
//    @OneToMany(mappedBy = "dmquocgiaMaso")
//    private Collection<TonKho> tonKhoCollection;
//    @OneToMany(mappedBy = "cttoathuockQuocgia")
//    private Collection<CtToaThuock> ctToaThuockCollection;
//    @OneToMany(mappedBy = "cttoathuockQuocgia1")
//    private Collection<CtToaThuock> ctToaThuockCollection1;
//    @OneToMany(mappedBy = "quocgiaMa")
//    private Collection<BenhNhan> benhNhanCollection;
//    @OneToMany(mappedBy = "quocgiaMa1")
//    private Collection<BenhNhan> benhNhanCollection1;

    public DmQuocGia() {
    }

    public DmQuocGia(Integer dmquocgiaMaso) {
        this.dmquocgiaMaso = dmquocgiaMaso;
    }

    public DmQuocGia(Integer dmquocgiaMaso, String dmquocgiaMa, String dmquocgiaTen) {
        this.dmquocgiaMaso = dmquocgiaMaso;
        this.dmquocgiaMa = dmquocgiaMa;
        this.dmquocgiaTen = dmquocgiaTen;
    }

    public Integer getDmquocgiaMaso() {
        return dmquocgiaMaso;
    }

    public void setDmquocgiaMaso(Integer dmquocgiaMaso) {
        this.dmquocgiaMaso = dmquocgiaMaso;
    }

    public String getDmquocgiaMa() {
        return dmquocgiaMa;
    }

    public void setDmquocgiaMa(String dmquocgiaMa) {
        this.dmquocgiaMa = dmquocgiaMa;
    }

    public String getDmquocgiaTen() {
        return dmquocgiaTen;
    }

    public void setDmquocgiaTen(String dmquocgiaTen) {
        this.dmquocgiaTen = dmquocgiaTen;
    }

    public String getDmquocgiaTenvn() {
        return dmquocgiaTenvn;
    }

    public void setDmquocgiaTenvn(String dmquocgiaTenvn) {
        this.dmquocgiaTenvn = dmquocgiaTenvn;
    }

    public Double getDmquocgiaNgaygiocn() {
        return dmquocgiaNgaygiocn;
    }

    public void setDmquocgiaNgaygiocn(Double dmquocgiaNgaygiocn) {
        this.dmquocgiaNgaygiocn = dmquocgiaNgaygiocn;
    }

    public Boolean getDmquocgiaChon() {
        return dmquocgiaChon;
    }

    public void setDmquocgiaChon(Boolean dmquocgiaChon) {
        this.dmquocgiaChon = dmquocgiaChon;
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

//    public Collection<BenhNhan> getBenhNhanCollection() {
//        return benhNhanCollection;
//    }
//
//    public void setBenhNhanCollection(Collection<BenhNhan> benhNhanCollection) {
//        this.benhNhanCollection = benhNhanCollection;
//    }

//    public Collection<BenhNhan> getBenhNhanCollection1() {
//        return benhNhanCollection1;
//    }
//
//    public void setBenhNhanCollection1(Collection<BenhNhan> benhNhanCollection1) {
//        this.benhNhanCollection1 = benhNhanCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmquocgiaMaso != null ? dmquocgiaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmQuocGia)) {
            return false;
        }
        DmQuocGia other = (DmQuocGia) object;
        if ((this.dmquocgiaMaso == null && other.dmquocgiaMaso != null) || (this.dmquocgiaMaso != null && !this.dmquocgiaMaso.equals(other.dmquocgiaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmQuocGia[dmquocgiaMaso=" + dmquocgiaMaso + "]";
    }

}


