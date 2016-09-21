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
@Table(name = "DM_DIEU_TRI")
@NamedQueries({})
public class DmDieuTri implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_DIEU_TRI")
    @SequenceGenerator(name = "DM_DIEU_TRI", sequenceName = "DM_DIEU_TRI_DMDIEUTRI_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMDIEUTRI_MASO", nullable = false)
    private Integer dmdieutriMaso;
    @Column(name = "DMDIEUTRI_MA")
    private String dmdieutriMa;
    @Column(name = "DMDIEUTRI_TEN", nullable = false)
    private String dmdieutriTen;
    @Column(name = "DMDIEUTRI_DIENGIAI2")
    private String dmdieutriDiengiai2;
    @Column(name = "DMDIEUTRI_DEFA")
    private Boolean dmdieutriDefa;
    @Column(name = "DMDIEUTRI_NGAYGIOCN")
    private Double dmdieutriNgaygiocn;
    @Column(name = "DMDIEUTRI_QL")
    private Boolean dmdieutriQl;
    @Column(name = "DMDIEUTRI_DT")
    private Boolean dmdieutriDt;
    @Column(name = "DMDIEUTRI_DP")
    private Boolean dmdieutriDp;
//    @OneToMany(mappedBy = "thamkhamDieutri")
//    private Collection<ThamKham> thamKhamCollection;
//    @OneToMany(mappedBy = "thamkhamDieutri1")
//    private Collection<ThamKham> thamKhamCollection1;
//    @OneToMany(mappedBy = "dieutriMa")
//    private Collection<TiepDon> tiepDonCollection;
//    @OneToMany(mappedBy = "hsbacmHuongdieutri")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection;
//    @OneToMany(mappedBy = "hsbacmHuongdieutri1")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection1;
//    @OneToMany(mappedBy = "hsbaDieutri")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "hsbaDieutri1")
//    private Collection<Hsba> hsbaCollection1;

    public DmDieuTri() {
    }

    public DmDieuTri(Integer dmdieutriMaso) {
        this.dmdieutriMaso = dmdieutriMaso;
    }

    public DmDieuTri(Integer dmdieutriMaso, String dmdieutriTen) {
        this.dmdieutriMaso = dmdieutriMaso;
        this.dmdieutriTen = dmdieutriTen;
    }

    public Integer getDmdieutriMaso() {
        return dmdieutriMaso;
    }

    public void setDmdieutriMaso(Integer dmdieutriMaso) {
        this.dmdieutriMaso = dmdieutriMaso;
    }

    public String getDmdieutriMa() {
        return dmdieutriMa;
    }

    public void setDmdieutriMa(String dmdieutriMa) {
        this.dmdieutriMa = dmdieutriMa;
    }

  

    public String getDmdieutriDiengiai2() {
        return dmdieutriDiengiai2;
    }

    public void setDmdieutriDiengiai2(String dmdieutriDiengiai2) {
        this.dmdieutriDiengiai2 = dmdieutriDiengiai2;
    }

    public Boolean getDmdieutriDefa() {
        return dmdieutriDefa;
    }

    public void setDmdieutriDefa(Boolean dmdieutriDefa) {
        this.dmdieutriDefa = dmdieutriDefa;
    }

    public Double getDmdieutriNgaygiocn() {
        return dmdieutriNgaygiocn;
    }

    public void setDmdieutriNgaygiocn(Double dmdieutriNgaygiocn) {
        this.dmdieutriNgaygiocn = dmdieutriNgaygiocn;
    }

    public Boolean getDmdieutriQl() {
        return dmdieutriQl;
    }

    public void setDmdieutriQl(Boolean dmdieutriQl) {
        this.dmdieutriQl = dmdieutriQl;
    }

    public Boolean getDmdieutriDt() {
        return dmdieutriDt;
    }

    public void setDmdieutriDt(Boolean dmdieutriDt) {
        this.dmdieutriDt = dmdieutriDt;
    }

    public Boolean getDmdieutriDp() {
        return dmdieutriDp;
    }

    public void setDmdieutriDp(Boolean dmdieutriDp) {
        this.dmdieutriDp = dmdieutriDp;
    }

//    public Collection<ThamKham> getThamKhamCollection() {
//        return thamKhamCollection;
//    }
//
//    public void setThamKhamCollection(Collection<ThamKham> thamKhamCollection) {
//        this.thamKhamCollection = thamKhamCollection;
//    }

//    public Collection<ThamKham> getThamKhamCollection1() {
//        return thamKhamCollection1;
//    }
//
//    public void setThamKhamCollection1(Collection<ThamKham> thamKhamCollection1) {
//        this.thamKhamCollection1 = thamKhamCollection1;
//    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection() {
//        return hsbaChuyenMonCollection;
//    }
//
//    public void setHsbaChuyenMonCollection(Collection<HsbaChuyenMon> hsbaChuyenMonCollection) {
//        this.hsbaChuyenMonCollection = hsbaChuyenMonCollection;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection1() {
//        return hsbaChuyenMonCollection1;
//    }
//
//    public void setHsbaChuyenMonCollection1(Collection<HsbaChuyenMon> hsbaChuyenMonCollection1) {
//        this.hsbaChuyenMonCollection1 = hsbaChuyenMonCollection1;
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
        hash += (dmdieutriMaso != null ? dmdieutriMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmDieuTri)) {
            return false;
        }
        DmDieuTri other = (DmDieuTri) object;
        if ((this.dmdieutriMaso == null && other.dmdieutriMaso != null) || (this.dmdieutriMaso != null && !this.dmdieutriMaso.equals(other.dmdieutriMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmDieuTri[dmdieutriMaso=" + dmdieutriMaso + "]";
    }

    public String getDmdieutriTen() {
        return dmdieutriTen;
    }

    public void setDmdieutriTen(String dmdieutriTen) {
        this.dmdieutriTen = dmdieutriTen;
    }

}


