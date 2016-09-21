/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "DM_PHAN_LOAI_THUOC")
@NamedQueries({})
public class DmPhanLoaiThuoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_PHAN_LOAI_THUOC")
    @SequenceGenerator(name = "DM_PHAN_LOAI_THUOC", sequenceName = "DM_PHAN_LOAI_THUOC_DMPHANLOAIT", allocationSize = 1)
    @Column(name = "DMPHANLOAITHUOC_MASO", nullable = false)
    private Integer dmphanloaithuocMaso;
    @Column(name = "DMPHANLOAITHUOC_MA")
    private String dmphanloaithuocMa;
    @Column(name = "DMPHANLOAITHUOC_TEN", nullable = false)
    private String dmphanloaithuocTen;
    @Column(name = "DMPHANLOAITHUOC_NHOM2")
    private String dmphanloaithuocNhom2;
    @Column(name = "DMPHANLOAITHUOC_NHOM3")
    private String dmphanloaithuocNhom3;
    @Column(name = "DMPHANLOAITHUOC_DUNGTICH")
    private Boolean dmphanloaithuocDungtich;
    @Column(name = "DMPHANLOAITHUOC_GHICHU")
    private String dmphanloaithuocGhichu;
    @Column(name = "DMPHANLOAITHUOC_THUTUBC")
    private Integer dmphanloaithuocThutubc;
    @Column(name = "DMPHANLOAITHUOC_LOAI")
    private Integer dmphanloaithuocLoai;
    @Column(name = "DMPHANLOAITHUOC_DT")
    private Boolean dmphanloaithuocDt;
    @Column(name = "DMPHANLOAITHUOC_QL")
    private Boolean dmphanloaithuocQl;
    @Column(name = "DMPHANLOAITHUOC_DP")
    private Boolean dmphanloaithuocDp;
    @Column(name = "DMPHANLOAITHUOC_NGAYGIOCN")
    private Double dmphanloaithuocNgaygiocn;
//    @OneToMany(mappedBy = "thuocnoitruPhanloai")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection;
//    @OneToMany(mappedBy = "thuocnoitruPhanloai1")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection1;
    @JoinColumn(name = "DMLOAITHUOC_MASO", referencedColumnName = "DMLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiThuoc dmloaithuocMaso;

//    @OneToMany(mappedBy = "dmphanloaithuocMaso")
//    private Collection<DmThuoc> dmThuocCollection;
//    @OneToMany(mappedBy = "dmphanloaithuocMaso2")
//    private Collection<DmThuoc> dmThuocCollection1;
//    @OneToMany(mappedBy = "dmphanloaithuocMaso1")
//    private Collection<DmThuoc> dmThuocCollection2;
//    @OneToMany(mappedBy = "dmphanloaithuocMaso21")
//    private Collection<DmThuoc> dmThuocCollection3;
//    @OneToMany(mappedBy = "thuocphongkhamPhanloai")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection;
//    @OneToMany(mappedBy = "thuocphongkhamPhanloai1")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection1;
//    @OneToMany(mappedBy = "dmphanloaithuocMaso")
//    private Collection<PhieuDuTru> phieuDuTruCollection;
//    @OneToMany(mappedBy = "dmphanloaithuocMaso1")
//    private Collection<PhieuDuTru> phieuDuTruCollection1;
    public DmPhanLoaiThuoc() {
    }

    public DmPhanLoaiThuoc(Integer dmphanloaithuocMaso) {
        this.dmphanloaithuocMaso = dmphanloaithuocMaso;
    }

    public DmPhanLoaiThuoc(Integer dmphanloaithuocMaso, String dmphanloaithuocTen) {
        this.dmphanloaithuocMaso = dmphanloaithuocMaso;
        this.dmphanloaithuocTen = dmphanloaithuocTen;
    }

    public Integer getDmphanloaithuocMaso() {
        return dmphanloaithuocMaso;
    }

    public void setDmphanloaithuocMaso(Integer dmphanloaithuocMaso) {
        this.dmphanloaithuocMaso = dmphanloaithuocMaso;
    }

    public String getDmphanloaithuocMa() {
        return dmphanloaithuocMa;
    }

    public void setDmphanloaithuocMa(String dmphanloaithuocMa) {
        this.dmphanloaithuocMa = dmphanloaithuocMa;
    }

    public String getDmphanloaithuocTen() {
        return dmphanloaithuocTen;
    }

    public void setDmphanloaithuocTen(String dmphanloaithuocTen) {
        this.dmphanloaithuocTen = dmphanloaithuocTen;
    }

    public String getDmphanloaithuocNhom2() {
        return dmphanloaithuocNhom2;
    }

    public void setDmphanloaithuocNhom2(String dmphanloaithuocNhom2) {
        this.dmphanloaithuocNhom2 = dmphanloaithuocNhom2;
    }

    public String getDmphanloaithuocNhom3() {
        return dmphanloaithuocNhom3;
    }

    public void setDmphanloaithuocNhom3(String dmphanloaithuocNhom3) {
        this.dmphanloaithuocNhom3 = dmphanloaithuocNhom3;
    }

    public Boolean getDmphanloaithuocDungtich() {
        return dmphanloaithuocDungtich;
    }

    public void setDmphanloaithuocDungtich(Boolean dmphanloaithuocDungtich) {
        this.dmphanloaithuocDungtich = dmphanloaithuocDungtich;
    }

    public String getDmphanloaithuocGhichu() {
        return dmphanloaithuocGhichu;
    }

    public void setDmphanloaithuocGhichu(String dmphanloaithuocGhichu) {
        this.dmphanloaithuocGhichu = dmphanloaithuocGhichu;
    }

    public Integer getDmphanloaithuocThutubc() {
        return dmphanloaithuocThutubc;
    }

    public void setDmphanloaithuocThutubc(Integer dmphanloaithuocThutubc) {
        this.dmphanloaithuocThutubc = dmphanloaithuocThutubc;
    }

    public Integer getDmphanloaithuocLoai() {
        return dmphanloaithuocLoai;
    }

    public void setDmphanloaithuocLoai(Integer dmphanloaithuocLoai) {
        this.dmphanloaithuocLoai = dmphanloaithuocLoai;
    }

    public Boolean getDmphanloaithuocDt() {
        return dmphanloaithuocDt;
    }

    public void setDmphanloaithuocDt(Boolean dmphanloaithuocDt) {
        this.dmphanloaithuocDt = dmphanloaithuocDt;
    }

    public Boolean getDmphanloaithuocQl() {
        return dmphanloaithuocQl;
    }

    public void setDmphanloaithuocQl(Boolean dmphanloaithuocQl) {
        this.dmphanloaithuocQl = dmphanloaithuocQl;
    }

    public Boolean getDmphanloaithuocDp() {
        return dmphanloaithuocDp;
    }

    public void setDmphanloaithuocDp(Boolean dmphanloaithuocDp) {
        this.dmphanloaithuocDp = dmphanloaithuocDp;
    }

    public Double getDmphanloaithuocNgaygiocn() {
        return dmphanloaithuocNgaygiocn;
    }

    public void setDmphanloaithuocNgaygiocn(Double dmphanloaithuocNgaygiocn) {
        this.dmphanloaithuocNgaygiocn = dmphanloaithuocNgaygiocn;
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

//    public Collection<DmThuoc> getDmThuocCollection2() {
//        return dmThuocCollection2;
//    }
//
//    public void setDmThuocCollection2(Collection<DmThuoc> dmThuocCollection2) {
//        this.dmThuocCollection2 = dmThuocCollection2;
//    }

//    public Collection<DmThuoc> getDmThuocCollection3() {
//        return dmThuocCollection3;
//    }
//
//    public void setDmThuocCollection3(Collection<DmThuoc> dmThuocCollection3) {
//        this.dmThuocCollection3 = dmThuocCollection3;
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
        hash += (dmphanloaithuocMaso != null ? dmphanloaithuocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmPhanLoaiThuoc)) {
            return false;
        }
        DmPhanLoaiThuoc other = (DmPhanLoaiThuoc) object;
        if ((this.dmphanloaithuocMaso == null && other.dmphanloaithuocMaso != null) || (this.dmphanloaithuocMaso != null && !this.dmphanloaithuocMaso.equals(other.dmphanloaithuocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmPhanLoaiThuoc[dmphanloaithuocMaso=" + dmphanloaithuocMaso + "]";
    }
}


