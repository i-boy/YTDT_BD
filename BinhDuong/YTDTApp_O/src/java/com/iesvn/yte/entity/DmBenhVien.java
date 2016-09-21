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
@Table(name = "DM_BENH_VIEN")
@NamedQueries({})
public class DmBenhVien implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_BENH_VIEN")
    @SequenceGenerator(name = "DM_BENH_VIEN", sequenceName = "DM_BENH_VIEN_DMBENHVIEN_MASO_S", allocationSize = 1)
    @Column(name = "DMBENHVIEN_MASO", nullable = false)
    private Integer dmbenhvienMaso;
    @Column(name = "DMBENHVIEN_MA")
    private String dmbenhvienMa;
    @Column(name = "DMBENHVIEN_TEN")
    private String dmbenhvienTen;
    @Column(name = "DMBENHVIEN_DIENTHOAI")
    private String dmbenhvienDienthoai;
    @Column(name = "DMBENHVIEN_DIACHI")
    private String dmbenhvienDiachi;
    @Column(name = "DMBENHVIEN_CHUYENDI")
    private Boolean dmbenhvienChuyendi;
    @Column(name = "DMBENHVIEN_CHUYENDEN")
    private Boolean dmbenhvienChuyenden;
    @Column(name = "DMBENHVIEN_NGAYGIOCN")
    private Double dmbenhvienNgaygiocn;
    @Column(name = "DMBENHVIEN_QL")
    private Boolean dmbenhvienQl;
    @Column(name = "DMBENHVIEN_DT")
    private Boolean dmbenhvienDt;
    @Column(name = "DMBENHVIEN_DP")
    private Boolean dmbenhvienDp;
    @Column(name = "DM_BENHVIEN_PREFIXMA")
    private String dmbenhvienPrefixMa;

     @Column(name = "DMBENHVIEN_DEFAULT")
    private String dmbenhvienDefault;
    
   
//    @OneToMany(mappedBy = "hsbacvChvienden")
//    private Collection<HsbaChuyenVien> hsbaChuyenVienCollection;
//    @OneToMany(mappedBy = "hsbacvChvienden1")
//    private Collection<HsbaChuyenVien> hsbaChuyenVienCollection1;
//    @OneToMany(mappedBy = "tiepdonChvien")
//    private Collection<TiepDon> tiepDonCollection;
//    @OneToMany(mappedBy = "tiepdonDonvigoi")
//    private Collection<TiepDon> tiepDonCollection1;
    @JoinColumn(name = "DMLOAIBV_MASO", referencedColumnName = "DMLOAIBV_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiBenhVien dmloaibvMaso;
    @JoinColumn(name = "DMVUNGMIEN_MASO", referencedColumnName = "DMVUNGMIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmVungMien dmvungmienMaso;
    @JoinColumn(name = "DMTINH_MASO", referencedColumnName = "DMTINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTinh dmtinhMaso;
    @JoinColumn(name = "DMHUYEN_MASO", referencedColumnName = "DMHUYEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmHuyen dmhuyenMaso;
    @JoinColumn(name = "DMTUYEN_MASO", referencedColumnName = "DMTUYEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTuyen dmtuyenMaso;

//    @OneToMany(mappedBy = "hsbaDonvigoi")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "hsbaDonvigoi1")
//    private Collection<Hsba> hsbaCollection1;
    public DmBenhVien() {
    }

    public DmBenhVien(Integer dmbenhvienMaso) {
        this.dmbenhvienMaso = dmbenhvienMaso;
    }

    public Integer getDmbenhvienMaso() {
        return dmbenhvienMaso;
    }

    public void setDmbenhvienMaso(Integer dmbenhvienMaso) {
        this.dmbenhvienMaso = dmbenhvienMaso;
    }

    public String getDmbenhvienMa() {
        return dmbenhvienMa;
    }

    public void setDmbenhvienMa(String dmbenhvienMa) {
        this.dmbenhvienMa = dmbenhvienMa;
    }

    public String getDmbenhvienTen() {
        return dmbenhvienTen;
    }

    public void setDmbenhvienTen(String dmbenhvienTen) {
        this.dmbenhvienTen = dmbenhvienTen;
    }

    public String getDmbenhvienDienthoai() {
        return dmbenhvienDienthoai;
    }

    public void setDmbenhvienDienthoai(String dmbenhvienDienthoai) {
        this.dmbenhvienDienthoai = dmbenhvienDienthoai;
    }

    public String getDmbenhvienDiachi() {
        return dmbenhvienDiachi;
    }

    public void setDmbenhvienDiachi(String dmbenhvienDiachi) {
        this.dmbenhvienDiachi = dmbenhvienDiachi;
    }
    
    public void setDmbenhvienPrefixMa(String dmbenhvienPrefixMa){
        this.dmbenhvienPrefixMa = dmbenhvienPrefixMa;
    }
    
    public String getDmbenhvienPrefixMa() {
        return dmbenhvienPrefixMa;
    }
    

    public Boolean getDmbenhvienChuyendi() {
        return dmbenhvienChuyendi;
    }

    public void setDmbenhvienChuyendi(Boolean dmbenhvienChuyendi) {
        this.dmbenhvienChuyendi = dmbenhvienChuyendi;
    }

    public Boolean getDmbenhvienChuyenden() {
        return dmbenhvienChuyenden;
    }

    public void setDmbenhvienChuyenden(Boolean dmbenhvienChuyenden) {
        this.dmbenhvienChuyenden = dmbenhvienChuyenden;
    }

    public Double getDmbenhvienNgaygiocn() {
        return dmbenhvienNgaygiocn;
    }

    public void setDmbenhvienNgaygiocn(Double dmbenhvienNgaygiocn) {
        this.dmbenhvienNgaygiocn = dmbenhvienNgaygiocn;
    }

    public Boolean getDmbenhvienQl() {
        return dmbenhvienQl;
    }

    public void setDmbenhvienQl(Boolean dmbenhvienQl) {
        this.dmbenhvienQl = dmbenhvienQl;
    }

    public Boolean getDmbenhvienDt() {
        return dmbenhvienDt;
    }

    public void setDmbenhvienDt(Boolean dmbenhvienDt) {
        this.dmbenhvienDt = dmbenhvienDt;
    }

    public Boolean getDmbenhvienDp() {
        return dmbenhvienDp;
    }

    public void setDmbenhvienDp(Boolean dmbenhvienDp) {
        this.dmbenhvienDp = dmbenhvienDp;
    }

//    public Collection<HsbaChuyenVien> getHsbaChuyenVienCollection() {
//        return hsbaChuyenVienCollection;
//    }
//
//    public void setHsbaChuyenVienCollection(Collection<HsbaChuyenVien> hsbaChuyenVienCollection) {
//        this.hsbaChuyenVienCollection = hsbaChuyenVienCollection;
//    }

//    public Collection<HsbaChuyenVien> getHsbaChuyenVienCollection1() {
//        return hsbaChuyenVienCollection1;
//    }
//
//    public void setHsbaChuyenVienCollection1(Collection<HsbaChuyenVien> hsbaChuyenVienCollection1) {
//        this.hsbaChuyenVienCollection1 = hsbaChuyenVienCollection1;
//    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }

//    public Collection<TiepDon> getTiepDonCollection1() {
//        return tiepDonCollection1;
//    }
//
//    public void setTiepDonCollection1(Collection<TiepDon> tiepDonCollection1) {
//        this.tiepDonCollection1 = tiepDonCollection1;
//    }
    public DmLoaiBenhVien getDmloaibvMaso(boolean create) {
        if (create && getDmloaibvMaso() == null) {
            setDmloaibvMaso(new DmLoaiBenhVien());
        }
        return getDmloaibvMaso();
    }

    public DmLoaiBenhVien getDmloaibvMaso() {
        return dmloaibvMaso;
    }

    public void setDmloaibvMaso(DmLoaiBenhVien dmloaibvMaso) {
        this.dmloaibvMaso = dmloaibvMaso;
    }

    public DmVungMien getDmvungmienMaso(boolean create) {
        if (create && getDmvungmienMaso() == null) {
            setDmvungmienMaso(new DmVungMien());
        }
        return getDmvungmienMaso();
    }

    public DmVungMien getDmvungmienMaso() {
        return dmvungmienMaso;
    }

    public void setDmvungmienMaso(DmVungMien dmvungmienMaso) {
        this.dmvungmienMaso = dmvungmienMaso;
    }

    public DmTinh getDmtinhMaso(boolean create) {
        if (create && getDmtinhMaso() == null) {
            setDmtinhMaso(new DmTinh());
        }
        return getDmtinhMaso();
    }

    public DmTinh getDmtinhMaso() {
        return dmtinhMaso;
    }

    public void setDmtinhMaso(DmTinh dmtinhMaso) {
        this.dmtinhMaso = dmtinhMaso;
    }

    public DmHuyen getDmhuyenMaso(boolean create) {
        if (create && getDmhuyenMaso() == null) {
            setDmhuyenMaso(new DmHuyen());
        }
        return getDmhuyenMaso();
    }

    public DmHuyen getDmhuyenMaso() {
        return dmhuyenMaso;
    }

    public void setDmhuyenMaso(DmHuyen dmhuyenMaso) {
        this.dmhuyenMaso = dmhuyenMaso;
    }

    public DmTuyen getDmtuyenMaso(boolean create) {
        if (create && getDmtuyenMaso() == null) {
            setDmtuyenMaso(new DmTuyen());
        }
        return getDmtuyenMaso();
    }

    public DmTuyen getDmtuyenMaso() {
        return dmtuyenMaso;
    }

    public void setDmtuyenMaso(DmTuyen dmtuyenMaso) {
        this.dmtuyenMaso = dmtuyenMaso;
    }

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
        hash += (getDmbenhvienMaso() != null ? getDmbenhvienMaso().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmBenhVien)) {
            return false;
        }
        DmBenhVien other = (DmBenhVien) object;
        if ((this.getDmbenhvienMaso() == null && other.getDmbenhvienMaso() != null) || (this.getDmbenhvienMaso() != null && !this.dmbenhvienMaso.equals(other.dmbenhvienMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmBenhVien[dmbenhvienMaso=" + getDmbenhvienMaso() + "]";
    }

    /**
     * @return the dmbenhvienDefault
     */
    public String getDmbenhvienDefault() {
        return dmbenhvienDefault;
    }

    /**
     * @param dmbenhvienDefault the dmbenhvienDefault to set
     */
    public void setDmbenhvienDefault(String dmbenhvienDefault) {
        this.dmbenhvienDefault = dmbenhvienDefault;
    }
}


