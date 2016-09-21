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
@Table(name = "DM_KET_QUA_DIEU_TRI")
@NamedQueries({})
public class DmKetQuaDieuTri implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_KET_QUA_DIEU_TRI")
    @SequenceGenerator(name = "DM_KET_QUA_DIEU_TRI", sequenceName = "DM_KET_QUA_DIEU_TRI_DMKQDT_MAS", allocationSize = 1)
    @Column(name = "DMKQDT_MASO", nullable = false)
    private Integer dmkqdtMaso;
    @Column(name = "DMKQDT_MA", nullable = false)
    private String dmkqdtMa;
    @Column(name = "DMKQDT_TEN", nullable = false)
    private String dmkqdtTen;
    @Column(name = "DMKQDT_GHICHU")
    private String dmkqdtGhichu;
    @Column(name = "DMKQDT_NGAYGIOCN")
    private Double dmkqdtNgaygiocn;
    @Column(name = "DMKQDT_DT")
    private Boolean dmkqdtDt;
    @Column(name = "DMKQDT_QL")
    private Boolean dmkqdtQl;
    @Column(name = "DMKQDT_DP")
    private Boolean dmkqdtDp;
//    @OneToMany(mappedBy = "hsbaKetqua")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "hsbaKetqua1")
//    private Collection<Hsba> hsbaCollection1;

    public DmKetQuaDieuTri() {
    }

    public DmKetQuaDieuTri(Integer dmkqdtMaso) {
        this.dmkqdtMaso = dmkqdtMaso;
    }

    public DmKetQuaDieuTri(Integer dmkqdtMaso, String dmkqdtMa, String dmkqdtTen) {
        this.dmkqdtMaso = dmkqdtMaso;
        this.dmkqdtMa = dmkqdtMa;
        this.dmkqdtTen = dmkqdtTen;
    }

    public Integer getDmkqdtMaso() {
        return dmkqdtMaso;
    }

    public void setDmkqdtMaso(Integer dmkqdtMaso) {
        this.dmkqdtMaso = dmkqdtMaso;
    }

    public String getDmkqdtMa() {
        return dmkqdtMa;
    }

    public void setDmkqdtMa(String dmkqdtMa) {
        this.dmkqdtMa = dmkqdtMa;
    }

    public String getDmkqdtTen() {
        return dmkqdtTen;
    }

    public void setDmkqdtTen(String dmkqdtTen) {
        this.dmkqdtTen = dmkqdtTen;
    }

    public String getDmkqdtGhichu() {
        return dmkqdtGhichu;
    }

    public void setDmkqdtGhichu(String dmkqdtGhichu) {
        this.dmkqdtGhichu = dmkqdtGhichu;
    }

    public Double getDmkqdtNgaygiocn() {
        return dmkqdtNgaygiocn;
    }

    public void setDmkqdtNgaygiocn(Double dmkqdtNgaygiocn) {
        this.dmkqdtNgaygiocn = dmkqdtNgaygiocn;
    }

    public Boolean getDmkqdtDt() {
        return dmkqdtDt;
    }

    public void setDmkqdtDt(Boolean dmkqdtDt) {
        this.dmkqdtDt = dmkqdtDt;
    }

    public Boolean getDmkqdtQl() {
        return dmkqdtQl;
    }

    public void setDmkqdtQl(Boolean dmkqdtQl) {
        this.dmkqdtQl = dmkqdtQl;
    }

    public Boolean getDmkqdtDp() {
        return dmkqdtDp;
    }

    public void setDmkqdtDp(Boolean dmkqdtDp) {
        this.dmkqdtDp = dmkqdtDp;
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
        hash += (dmkqdtMaso != null ? dmkqdtMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmKetQuaDieuTri)) {
            return false;
        }
        DmKetQuaDieuTri other = (DmKetQuaDieuTri) object;
        if ((this.dmkqdtMaso == null && other.dmkqdtMaso != null) || (this.dmkqdtMaso != null && !this.dmkqdtMaso.equals(other.dmkqdtMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmKetQuaDieuTri[dmkqdtMaso=" + dmkqdtMaso + "]";
    }

}


