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
@Table(name = "DM_LOAI_SINH")
@NamedQueries({})
public class DmLoaiSinh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_SINH")
    @SequenceGenerator(name = "DM_LOAI_SINH", sequenceName = "DM_LOAI_SINH_DMLOAISINH_MASO_S", allocationSize = 1)
    @Column(name = "DMLOAISINH_MASO", nullable = false)
    private Integer dmloaisinhMaso;
    @Column(name = "DMLOAISINH_MA")
    private String dmloaisinhMa;
    @Column(name = "DMLOAISINH_TEN", nullable = false)
    private String dmloaisinhTen;
    @Column(name = "DMLOAISINH_NGAYGIOCN")
    private Double dmloaisinhNgaygiocn;
    @Column(name = "DMLOAISINH_DT")
    private Boolean dmloaisinhDt;
    @Column(name = "DMLOAISINH_QL")
    private Boolean dmloaisinhQl;
    @Column(name = "DMLOAISINH_DP")
    private Boolean dmloaisinhDp;
//    @OneToMany(mappedBy = "dmloaisinhMaso")
//    private Collection<HsbaSan> hsbaSanCollection;
//    @OneToMany(mappedBy = "dmloaisinhMaso1")
//    private Collection<HsbaSan> hsbaSanCollection1;

    public DmLoaiSinh() {
    }

    public DmLoaiSinh(Integer dmloaisinhMaso) {
        this.dmloaisinhMaso = dmloaisinhMaso;
    }

    public DmLoaiSinh(Integer dmloaisinhMaso, String dmloaisinhTen) {
        this.dmloaisinhMaso = dmloaisinhMaso;
        this.dmloaisinhTen = dmloaisinhTen;
    }

    public Integer getDmloaisinhMaso() {
        return dmloaisinhMaso;
    }

    public void setDmloaisinhMaso(Integer dmloaisinhMaso) {
        this.dmloaisinhMaso = dmloaisinhMaso;
    }

    public String getDmloaisinhMa() {
        return dmloaisinhMa;
    }

    public void setDmloaisinhMa(String dmloaisinhMa) {
        this.dmloaisinhMa = dmloaisinhMa;
    }

    public String getDmloaisinhTen() {
        return dmloaisinhTen;
    }

    public void setDmloaisinhTen(String dmloaisinhTen) {
        this.dmloaisinhTen = dmloaisinhTen;
    }

    public Double getDmloaisinhNgaygiocn() {
        return dmloaisinhNgaygiocn;
    }

    public void setDmloaisinhNgaygiocn(Double dmloaisinhNgaygiocn) {
        this.dmloaisinhNgaygiocn = dmloaisinhNgaygiocn;
    }

    public Boolean getDmloaisinhDt() {
        return dmloaisinhDt;
    }

    public void setDmloaisinhDt(Boolean dmloaisinhDt) {
        this.dmloaisinhDt = dmloaisinhDt;
    }

    public Boolean getDmloaisinhQl() {
        return dmloaisinhQl;
    }

    public void setDmloaisinhQl(Boolean dmloaisinhQl) {
        this.dmloaisinhQl = dmloaisinhQl;
    }

    public Boolean getDmloaisinhDp() {
        return dmloaisinhDp;
    }

    public void setDmloaisinhDp(Boolean dmloaisinhDp) {
        this.dmloaisinhDp = dmloaisinhDp;
    }

//    public Collection<HsbaSan> getHsbaSanCollection() {
//        return hsbaSanCollection;
//    }
//
//    public void setHsbaSanCollection(Collection<HsbaSan> hsbaSanCollection) {
//        this.hsbaSanCollection = hsbaSanCollection;
//    }

//    public Collection<HsbaSan> getHsbaSanCollection1() {
//        return hsbaSanCollection1;
//    }
//
//    public void setHsbaSanCollection1(Collection<HsbaSan> hsbaSanCollection1) {
//        this.hsbaSanCollection1 = hsbaSanCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmloaisinhMaso != null ? dmloaisinhMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmLoaiSinh)) {
            return false;
        }
        DmLoaiSinh other = (DmLoaiSinh) object;
        if ((this.dmloaisinhMaso == null && other.dmloaisinhMaso != null) || (this.dmloaisinhMaso != null && !this.dmloaisinhMaso.equals(other.dmloaisinhMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmLoaiSinh[dmloaisinhMaso=" + dmloaisinhMaso + "]";
    }

}


