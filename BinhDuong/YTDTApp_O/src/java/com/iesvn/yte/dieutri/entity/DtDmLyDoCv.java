/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

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
@Table(name = "DT_DM_LY_DO_CV")
@NamedQueries({})
public class DtDmLyDoCv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_LY_DO_CV")
    @SequenceGenerator(name = "DT_DM_LY_DO_CV", sequenceName = "DT_DM_LY_DO_CV_DTDMLYDOCV_MASO", allocationSize = 1)
    @Column(name = "DTDMLYDOCV_MASO", nullable = false)
    private Integer dtdmlydocvMaso;
    @Column(name = "DTDMLYDOCV_MA", nullable = false)
    private String dtdmlydocvMa;
    @Column(name = "DTDMLYDOCV_TEN", nullable = false)
    private String dtdmlydocvTen;
    @Column(name = "DTDMLYDOCV_NGAYGIOCN")
    private Double dtdmlydocvNgaygiocn;
    @Column(name = "DTDMLYDOCV_CHON")
    private Boolean dtdmlydocvChon;
//    @OneToMany(mappedBy = "hsbacvLydochuyenv")
//    private Collection<HsbaChuyenVien> hsbaChuyenVienCollection;
//    @OneToMany(mappedBy = "hsbacvLydochuyenv1")
//    private Collection<HsbaChuyenVien> hsbaChuyenVienCollection1;
//    @OneToMany(mappedBy = "tiepdonLydochvi")
//    private Collection<TiepDon> tiepDonCollection;
    public DtDmLyDoCv() {
    }

    public DtDmLyDoCv(Integer dtdmlydocvMaso) {
        this.dtdmlydocvMaso = dtdmlydocvMaso;
    }

    public DtDmLyDoCv(Integer dtdmlydocvMaso, String dtdmlydocvMa, String dtdmlydocvTen) {
        this.dtdmlydocvMaso = dtdmlydocvMaso;
        this.dtdmlydocvMa = dtdmlydocvMa;
        this.dtdmlydocvTen = dtdmlydocvTen;
    }

    public Integer getDtdmlydocvMaso() {
        return dtdmlydocvMaso;
    }

    public void setDtdmlydocvMaso(Integer dtdmlydocvMaso) {
        this.dtdmlydocvMaso = dtdmlydocvMaso;
    }

    public String getDtdmlydocvMa() {
        return dtdmlydocvMa;
    }

    public void setDtdmlydocvMa(String dtdmlydocvMa) {
        this.dtdmlydocvMa = dtdmlydocvMa;
    }

    public String getDtdmlydocvTen() {
        return dtdmlydocvTen;
    }

    public void setDtdmlydocvTen(String dtdmlydocvTen) {
        this.dtdmlydocvTen = dtdmlydocvTen;
    }

    public Double getDtdmlydocvNgaygiocn() {
        return dtdmlydocvNgaygiocn;
    }

    public void setDtdmlydocvNgaygiocn(Double dtdmlydocvNgaygiocn) {
        this.dtdmlydocvNgaygiocn = dtdmlydocvNgaygiocn;
    }

    public Boolean getDtdmlydocvChon() {
        return dtdmlydocvChon;
    }

    public void setDtdmlydocvChon(Boolean dtdmlydocvChon) {
        this.dtdmlydocvChon = dtdmlydocvChon;
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
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmlydocvMaso != null ? dtdmlydocvMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmLyDoCv)) {
            return false;
        }
        DtDmLyDoCv other = (DtDmLyDoCv) object;
        if ((this.dtdmlydocvMaso == null && other.dtdmlydocvMaso != null) || (this.dtdmlydocvMaso != null && !this.dtdmlydocvMaso.equals(other.dtdmlydocvMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmLyDoCv[dtdmlydocvMaso=" + dtdmlydocvMaso + "]";
    }
}


