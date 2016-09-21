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
@Table(name = "DM_DIA_DIEM")
@NamedQueries({})
public class DmDiaDiem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_DIA_DIEM")
    @SequenceGenerator(name = "DM_DIA_DIEM", sequenceName = "DM_DIA_DIEM_DMDIADIEM_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMDIADIEM_MASO", nullable = false)
    private Integer dmdiadiemMaso;
    @Column(name = "DMDIADIEM_MA")
    private String dmdiadiemMa;
    @Column(name = "DMDIADIEM_TEN", nullable = false)
    private String dmdiadiemTen;
    @Column(name = "DMDIADIEM_NGAYGIOCN")
    private Double dmdiadiemNgaygiocn;
    @Column(name = "DMDIADIEM_QL")
    private Boolean dmdiadiemQl;
    @Column(name = "DMDIADIEM_DT")
    private Boolean dmdiadiemDt;
    @Column(name = "DMDIADIEM_DP")
    private Boolean dmdiadiemDp;
//    @OneToMany(mappedBy = "diadiemMa")
//    private Collection<TiepDon> tiepDonCollection;
//    @OneToMany(mappedBy = "diadiemMa")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "diadiemMa1")
//    private Collection<Hsba> hsbaCollection1;

    public DmDiaDiem() {
    }

    public DmDiaDiem(Integer dmdiadiemMaso) {
        this.dmdiadiemMaso = dmdiadiemMaso;
    }

    public DmDiaDiem(Integer dmdiadiemMaso, String dmdiadiemTen) {
        this.dmdiadiemMaso = dmdiadiemMaso;
        this.dmdiadiemTen = dmdiadiemTen;
    }

    public Integer getDmdiadiemMaso() {
        return dmdiadiemMaso;
    }

    public void setDmdiadiemMaso(Integer dmdiadiemMaso) {
        this.dmdiadiemMaso = dmdiadiemMaso;
    }

    public String getDmdiadiemMa() {
        return dmdiadiemMa;
    }

    public void setDmdiadiemMa(String dmdiadiemMa) {
        this.dmdiadiemMa = dmdiadiemMa;
    }

    public String getDmdiadiemTen() {
        return dmdiadiemTen;
    }

    public void setDmdiadiemTen(String dmdiadiemTen) {
        this.dmdiadiemTen = dmdiadiemTen;
    }

    public Double getDmdiadiemNgaygiocn() {
        return dmdiadiemNgaygiocn;
    }

    public void setDmdiadiemNgaygiocn(Double dmdiadiemNgaygiocn) {
        this.dmdiadiemNgaygiocn = dmdiadiemNgaygiocn;
    }

    public Boolean getDmdiadiemQl() {
        return dmdiadiemQl;
    }

    public void setDmdiadiemQl(Boolean dmdiadiemQl) {
        this.dmdiadiemQl = dmdiadiemQl;
    }

    public Boolean getDmdiadiemDt() {
        return dmdiadiemDt;
    }

    public void setDmdiadiemDt(Boolean dmdiadiemDt) {
        this.dmdiadiemDt = dmdiadiemDt;
    }

    public Boolean getDmdiadiemDp() {
        return dmdiadiemDp;
    }

    public void setDmdiadiemDp(Boolean dmdiadiemDp) {
        this.dmdiadiemDp = dmdiadiemDp;
    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
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
        hash += (dmdiadiemMaso != null ? dmdiadiemMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmDiaDiem)) {
            return false;
        }
        DmDiaDiem other = (DmDiaDiem) object;
        if ((this.dmdiadiemMaso == null && other.dmdiadiemMaso != null) || (this.dmdiadiemMaso != null && !this.dmdiadiemMaso.equals(other.dmdiadiemMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmDiaDiem[dmdiadiemMaso=" + dmdiadiemMaso + "]";
    }

}


