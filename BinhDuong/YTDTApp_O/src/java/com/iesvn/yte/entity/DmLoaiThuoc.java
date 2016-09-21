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
@Table(name = "DM_LOAI_THUOC")
@NamedQueries({})
public class DmLoaiThuoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_THUOC")
    @SequenceGenerator(name = "DM_LOAI_THUOC", sequenceName = "DM_LOAI_THUOC_DMLOAITHUOC_MASO", allocationSize = 1)
    @Column(name = "DMLOAITHUOC_MASO", nullable = false)
    private Integer dmloaithuocMaso;
    @Column(name = "DMLOAITHUOC_MA", nullable = false)
    private String dmloaithuocMa;
    @Column(name = "DMLOAITHUOC_TEN", nullable = false)
    private String dmloaithuocTen;
    @Column(name = "DMLOAITHUOC_QUYEN")
    private String dmloaithuocQuyen;
    @Column(name = "DMLOAITHUOC_NGAYGIOCN")
    private Double dmloaithuocNgaygiocn;
    @Column(name = "DMLOAITHUOC_DT")
    private Boolean dmloaithuocDt;
    @Column(name = "DMLOAITHUOC_QL")
    private Boolean dmloaithuocQl;
    @Column(name = "DMLOAITHUOC_DP")
    private Boolean dmloaithuocDp;
//    @OneToMany(mappedBy = "dmloaithuocMaso")
//    private Collection<DmPhanLoaiThuoc> dmPhanLoaiThuocCollection;
//    @OneToMany(mappedBy = "dmloaithuocMaso1")
//    private Collection<DmPhanLoaiThuoc> dmPhanLoaiThuocCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmloaithuocMaso")
//    private Collection<DmNhomBaoCaoLoaiThuoc> dmNhomBaoCaoLoaiThuocCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmloaithuocMaso")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmloaithuocMaso1")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmloaithuocMaso")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmloaithuocMaso1")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection1;
//    @OneToMany(mappedBy = "dmloaithuocMaso")
//    private Collection<PhieuDuTru> phieuDuTruCollection;
//    @OneToMany(mappedBy = "dmloaithuocMaso1")
//    private Collection<PhieuDuTru> phieuDuTruCollection1;
    public DmLoaiThuoc() {
    }

    public DmLoaiThuoc(Integer dmloaithuocMaso) {
        this.dmloaithuocMaso = dmloaithuocMaso;
    }

    public DmLoaiThuoc(Integer dmloaithuocMaso, String dmloaithuocMa, String dmloaithuocTen) {
        this.dmloaithuocMaso = dmloaithuocMaso;
        this.dmloaithuocMa = dmloaithuocMa;
        this.dmloaithuocTen = dmloaithuocTen;
    }

    public Integer getDmloaithuocMaso() {
        return dmloaithuocMaso;
    }

    public void setDmloaithuocMaso(Integer dmloaithuocMaso) {
        this.dmloaithuocMaso = dmloaithuocMaso;
    }

    public String getDmloaithuocMa() {
        return dmloaithuocMa;
    }

    public void setDmloaithuocMa(String dmloaithuocMa) {
        this.dmloaithuocMa = dmloaithuocMa;
    }

    public String getDmloaithuocTen() {
        return dmloaithuocTen;
    }

    public void setDmloaithuocTen(String dmloaithuocTen) {
        this.dmloaithuocTen = dmloaithuocTen;
    }

    public String getDmloaithuocQuyen() {
        return dmloaithuocQuyen;
    }

    public void setDmloaithuocQuyen(String dmloaithuocQuyen) {
        this.dmloaithuocQuyen = dmloaithuocQuyen;
    }

    public Double getDmloaithuocNgaygiocn() {
        return dmloaithuocNgaygiocn;
    }

    public void setDmloaithuocNgaygiocn(Double dmloaithuocNgaygiocn) {
        this.dmloaithuocNgaygiocn = dmloaithuocNgaygiocn;
    }

    public Boolean getDmloaithuocDt() {
        return dmloaithuocDt;
    }

    public void setDmloaithuocDt(Boolean dmloaithuocDt) {
        this.dmloaithuocDt = dmloaithuocDt;
    }

    public Boolean getDmloaithuocQl() {
        return dmloaithuocQl;
    }

    public void setDmloaithuocQl(Boolean dmloaithuocQl) {
        this.dmloaithuocQl = dmloaithuocQl;
    }

    public Boolean getDmloaithuocDp() {
        return dmloaithuocDp;
    }

    public void setDmloaithuocDp(Boolean dmloaithuocDp) {
        this.dmloaithuocDp = dmloaithuocDp;
    }

//    public Collection<DmPhanLoaiThuoc> getDmPhanLoaiThuocCollection() {
//        return dmPhanLoaiThuocCollection;
//    }
//
//    public void setDmPhanLoaiThuocCollection(Collection<DmPhanLoaiThuoc> dmPhanLoaiThuocCollection) {
//        this.dmPhanLoaiThuocCollection = dmPhanLoaiThuocCollection;
//    }

//    public Collection<DmPhanLoaiThuoc> getDmPhanLoaiThuocCollection1() {
//        return dmPhanLoaiThuocCollection1;
//    }
//
//    public void setDmPhanLoaiThuocCollection1(Collection<DmPhanLoaiThuoc> dmPhanLoaiThuocCollection1) {
//        this.dmPhanLoaiThuocCollection1 = dmPhanLoaiThuocCollection1;
//    }

//    public Collection<DmNhomBaoCaoLoaiThuoc> getDmNhomBaoCaoLoaiThuocCollection() {
//        return dmNhomBaoCaoLoaiThuocCollection;
//    }
//
//    public void setDmNhomBaoCaoLoaiThuocCollection(Collection<DmNhomBaoCaoLoaiThuoc> dmNhomBaoCaoLoaiThuocCollection) {
//        this.dmNhomBaoCaoLoaiThuocCollection = dmNhomBaoCaoLoaiThuocCollection;
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

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection() {
//        return phieuNhapKhoCollection;
//    }
//
//    public void setPhieuNhapKhoCollection(Collection<PhieuNhapKho> phieuNhapKhoCollection) {
//        this.phieuNhapKhoCollection = phieuNhapKhoCollection;
//    }

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection1() {
//        return phieuNhapKhoCollection1;
//    }
//
//    public void setPhieuNhapKhoCollection1(Collection<PhieuNhapKho> phieuNhapKhoCollection1) {
//        this.phieuNhapKhoCollection1 = phieuNhapKhoCollection1;
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
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmloaithuocMaso != null ? dmloaithuocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmLoaiThuoc)) {
            return false;
        }
        DmLoaiThuoc other = (DmLoaiThuoc) object;
        if ((this.dmloaithuocMaso == null && other.dmloaithuocMaso != null) || (this.dmloaithuocMaso != null && !this.dmloaithuocMaso.equals(other.dmloaithuocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmLoaiThuoc[dmloaithuocMaso=" + dmloaithuocMaso + "]";
    }
}


