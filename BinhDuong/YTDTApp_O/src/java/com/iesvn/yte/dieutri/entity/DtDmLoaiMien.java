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
@Table(name = "DT_DM_LOAI_MIEN")
@NamedQueries({})
public class DtDmLoaiMien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_LOAI_MIEN")
    @SequenceGenerator(name = "DT_DM_LOAI_MIEN", sequenceName = "DT_DM_LOAI_MIEN_DTDMLOAIMIEN_M", allocationSize = 1)
    @Column(name = "DTDMLOAIMIEN_MASO", nullable = false)
    private Integer dtdmloaimienMaso;
    @Column(name = "DTDMLOAIMIEN_MA", nullable = false)
    private String dtdmloaimienMa;
    @Column(name = "DTDMLOAIMIEN_TEN", nullable = false)
    private String dtdmloaimienTen;
    @Column(name = "DTDMLOAIMIEN_NGAYGIOCN")
    private Double dtdmloaimienNgaygiocn;
    @Column(name = "DTDMLOAIMIEN_CHON")
    private Boolean dtdmloaimienChon;
//    @OneToMany(mappedBy = "miengiamLoaimien")
//    private Collection<MienGiam> mienGiamCollection;
//    @OneToMany(mappedBy = "miengiamLoaimien1")
//    private Collection<MienGiam> mienGiamCollection1;
    public DtDmLoaiMien() {
    }

    public DtDmLoaiMien(Integer dtdmloaimienMaso) {
        this.dtdmloaimienMaso = dtdmloaimienMaso;
    }

    public DtDmLoaiMien(Integer dtdmloaimienMaso, String dtdmloaimienMa, String dtdmloaimienTen) {
        this.dtdmloaimienMaso = dtdmloaimienMaso;
        this.dtdmloaimienMa = dtdmloaimienMa;
        this.dtdmloaimienTen = dtdmloaimienTen;
    }

    public Integer getDtdmloaimienMaso() {
        return dtdmloaimienMaso;
    }

    public void setDtdmloaimienMaso(Integer dtdmloaimienMaso) {
        this.dtdmloaimienMaso = dtdmloaimienMaso;
    }

    public String getDtdmloaimienMa() {
        return dtdmloaimienMa;
    }

    public void setDtdmloaimienMa(String dtdmloaimienMa) {
        this.dtdmloaimienMa = dtdmloaimienMa;
    }

    public String getDtdmloaimienTen() {
        return dtdmloaimienTen;
    }

    public void setDtdmloaimienTen(String dtdmloaimienTen) {
        this.dtdmloaimienTen = dtdmloaimienTen;
    }

    public Double getDtdmloaimienNgaygiocn() {
        return dtdmloaimienNgaygiocn;
    }

    public void setDtdmloaimienNgaygiocn(Double dtdmloaimienNgaygiocn) {
        this.dtdmloaimienNgaygiocn = dtdmloaimienNgaygiocn;
    }

    public Boolean getDtdmloaimienChon() {
        return dtdmloaimienChon;
    }

    public void setDtdmloaimienChon(Boolean dtdmloaimienChon) {
        this.dtdmloaimienChon = dtdmloaimienChon;
    }

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
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmloaimienMaso != null ? dtdmloaimienMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmLoaiMien)) {
            return false;
        }
        DtDmLoaiMien other = (DtDmLoaiMien) object;
        if ((this.dtdmloaimienMaso == null && other.dtdmloaimienMaso != null) || (this.dtdmloaimienMaso != null && !this.dtdmloaimienMaso.equals(other.dtdmloaimienMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmLoaiMien[dtdmloaimienMaso=" + dtdmloaimienMaso + "]";
    }
}


