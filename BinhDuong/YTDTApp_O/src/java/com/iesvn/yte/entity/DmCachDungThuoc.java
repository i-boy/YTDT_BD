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
@Table(name = "DM_CACH_DUNG_THUOC")
@NamedQueries({})
public class DmCachDungThuoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_CACH_DUNG_THUOC")
    @SequenceGenerator(name = "DM_CACH_DUNG_THUOC", sequenceName = "DM_CACH_DUNG_THUOC_DMCACHDUNGT", allocationSize = 1)
    @Column(name = "DMCACHDUNGTHUOC_MASO", nullable = false)
    private Integer dmcachdungthuocMaso;
    @Column(name = "DMCACHDUNGTHUOC_MA", nullable = false)
    private String dmcachdungthuocMa;
    @Column(name = "DMCACHDUNGTHUOC_MAPHU")
    private String dmcachdungthuocMaphu;
    @Column(name = "DMCACHDUNGTHUOC_TEN", nullable = false)
    private String dmcachdungthuocTen;
    @Column(name = "DMCACHDUNGTHUOC_NGAYGIOCN")
    private Double dmcachdungthuocNgaygiocn;
    @Column(name = "DMCACHDUNGTHUOC_DT")
    private Boolean dmcachdungthuocDt;
    @Column(name = "DMCACHDUNGTHUOC_QL")
    private Boolean dmcachdungthuocQl;
    @Column(name = "DMCACHDUNGTHUOC_DP")
    private Boolean dmcachdungthuocDp;
//    @OneToMany(mappedBy = "dmcachdungMaso")
//    private Collection<DmThuoc> dmThuocCollection;
//    @OneToMany(mappedBy = "dmcachdungMaso1")
//    private Collection<DmThuoc> dmThuocCollection1;

    public DmCachDungThuoc() {
    }

    public DmCachDungThuoc(Integer dmcachdungthuocMaso) {
        this.dmcachdungthuocMaso = dmcachdungthuocMaso;
    }

    public DmCachDungThuoc(Integer dmcachdungthuocMaso, String dmcachdungthuocMa, String dmcachdungthuocTen) {
        this.dmcachdungthuocMaso = dmcachdungthuocMaso;
        this.dmcachdungthuocMa = dmcachdungthuocMa;
        this.dmcachdungthuocTen = dmcachdungthuocTen;
    }

    public Integer getDmcachdungthuocMaso() {
        return dmcachdungthuocMaso;
    }

    public void setDmcachdungthuocMaso(Integer dmcachdungthuocMaso) {
        this.dmcachdungthuocMaso = dmcachdungthuocMaso;
    }

    public String getDmcachdungthuocMa() {
        return dmcachdungthuocMa;
    }

    public void setDmcachdungthuocMa(String dmcachdungthuocMa) {
        this.dmcachdungthuocMa = dmcachdungthuocMa;
    }

    public String getDmcachdungthuocMaphu() {
        return dmcachdungthuocMaphu;
    }

    public void setDmcachdungthuocMaphu(String dmcachdungthuocMaphu) {
        this.dmcachdungthuocMaphu = dmcachdungthuocMaphu;
    }

    public String getDmcachdungthuocTen() {
        return dmcachdungthuocTen;
    }

    public void setDmcachdungthuocTen(String dmcachdungthuocTen) {
        this.dmcachdungthuocTen = dmcachdungthuocTen;
    }

    public Double getDmcachdungthuocNgaygiocn() {
        return dmcachdungthuocNgaygiocn;
    }

    public void setDmcachdungthuocNgaygiocn(Double dmcachdungthuocNgaygiocn) {
        this.dmcachdungthuocNgaygiocn = dmcachdungthuocNgaygiocn;
    }

    public Boolean getDmcachdungthuocDt() {
        return dmcachdungthuocDt;
    }

    public void setDmcachdungthuocDt(Boolean dmcachdungthuocDt) {
        this.dmcachdungthuocDt = dmcachdungthuocDt;
    }

    public Boolean getDmcachdungthuocQl() {
        return dmcachdungthuocQl;
    }

    public void setDmcachdungthuocQl(Boolean dmcachdungthuocQl) {
        this.dmcachdungthuocQl = dmcachdungthuocQl;
    }

    public Boolean getDmcachdungthuocDp() {
        return dmcachdungthuocDp;
    }

    public void setDmcachdungthuocDp(Boolean dmcachdungthuocDp) {
        this.dmcachdungthuocDp = dmcachdungthuocDp;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmcachdungthuocMaso != null ? dmcachdungthuocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmCachDungThuoc)) {
            return false;
        }
        DmCachDungThuoc other = (DmCachDungThuoc) object;
        if ((this.dmcachdungthuocMaso == null && other.dmcachdungthuocMaso != null) || (this.dmcachdungthuocMaso != null && !this.dmcachdungthuocMaso.equals(other.dmcachdungthuocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmCachDungThuoc[dmcachdungthuocMaso=" + dmcachdungthuocMaso + "]";
    }

}


