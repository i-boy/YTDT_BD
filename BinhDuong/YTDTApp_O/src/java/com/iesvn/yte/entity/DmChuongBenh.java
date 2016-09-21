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
@Table(name = "DM_CHUONG_BENH")
@NamedQueries({})
public class DmChuongBenh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_CHUONG_BENH")
    @SequenceGenerator(name = "DM_CHUONG_BENH", sequenceName = "DM_CHUONG_BENH_DMCHUONGBENH_MA", allocationSize = 1)
    @Column(name = "DMCHUONGBENH_MASO", nullable = false)
    private Integer dmchuongbenhMaso;
    @Column(name = "DMCHUONGBENH_MA")
    private String dmchuongbenhMa;
    @Column(name = "DMBENHICD_MA")
    private String dmbenhicdMa;
    @Column(name = "DMCHUONGBENH_TEN", nullable = false)
    private String dmchuongbenhTen;
    @Column(name = "DMCHUONGBENH_NGAYGIOCN")
    private Double dmchuongbenhNgaygiocn;
    @Column(name = "DMCHUONGBENH_DT")
    private Boolean dmchuongbenhDt;
    @Column(name = "DMCHUONGBENH_QL")
    private Boolean dmchuongbenhQl;
    @Column(name = "DMCHUONGBENH_DP")
    private Boolean dmchuongbenhDp;
//    @OneToMany(mappedBy = "dmchuongbenhMaso")
//    private Collection<DmBenhIcd> dmBenhIcdCollection;
//    @OneToMany(mappedBy = "dmchuongbenhMaso1")
//    private Collection<DmBenhIcd> dmBenhIcdCollection1;
//    @OneToMany(mappedBy = "dmchuongbenhMaso")
//    private Collection<DmBenhVn> dmBenhVnCollection;
//    @OneToMany(mappedBy = "dmchuongbenhMaso1")
//    private Collection<DmBenhVn> dmBenhVnCollection1;

    public DmChuongBenh() {
    }

    public DmChuongBenh(Integer dmchuongbenhMaso) {
        this.dmchuongbenhMaso = dmchuongbenhMaso;
    }

    public DmChuongBenh(Integer dmchuongbenhMaso, String dmchuongbenhTen) {
        this.dmchuongbenhMaso = dmchuongbenhMaso;
        this.dmchuongbenhTen = dmchuongbenhTen;
    }

    public Integer getDmchuongbenhMaso() {
        return dmchuongbenhMaso;
    }

    public void setDmchuongbenhMaso(Integer dmchuongbenhMaso) {
        this.dmchuongbenhMaso = dmchuongbenhMaso;
    }

    public String getDmchuongbenhMa() {
        return dmchuongbenhMa;
    }

    public void setDmchuongbenhMa(String dmchuongbenhMa) {
        this.dmchuongbenhMa = dmchuongbenhMa;
    }

    public String getDmbenhicdMa() {
        return dmbenhicdMa;
    }

    public void setDmbenhicdMa(String dmbenhicdMa) {
        this.dmbenhicdMa = dmbenhicdMa;
    }

    public String getDmchuongbenhTen() {
        return dmchuongbenhTen;
    }

    public void setDmchuongbenhTen(String dmchuongbenhTen) {
        this.dmchuongbenhTen = dmchuongbenhTen;
    }

    public Double getDmchuongbenhNgaygiocn() {
        return dmchuongbenhNgaygiocn;
    }

    public void setDmchuongbenhNgaygiocn(Double dmchuongbenhNgaygiocn) {
        this.dmchuongbenhNgaygiocn = dmchuongbenhNgaygiocn;
    }

    public Boolean getDmchuongbenhDt() {
        return dmchuongbenhDt;
    }

    public void setDmchuongbenhDt(Boolean dmchuongbenhDt) {
        this.dmchuongbenhDt = dmchuongbenhDt;
    }

    public Boolean getDmchuongbenhQl() {
        return dmchuongbenhQl;
    }

    public void setDmchuongbenhQl(Boolean dmchuongbenhQl) {
        this.dmchuongbenhQl = dmchuongbenhQl;
    }

    public Boolean getDmchuongbenhDp() {
        return dmchuongbenhDp;
    }

    public void setDmchuongbenhDp(Boolean dmchuongbenhDp) {
        this.dmchuongbenhDp = dmchuongbenhDp;
    }

//    public Collection<DmBenhIcd> getDmBenhIcdCollection() {
//        return dmBenhIcdCollection;
//    }
//
//    public void setDmBenhIcdCollection(Collection<DmBenhIcd> dmBenhIcdCollection) {
//        this.dmBenhIcdCollection = dmBenhIcdCollection;
//    }

//    public Collection<DmBenhIcd> getDmBenhIcdCollection1() {
//        return dmBenhIcdCollection1;
//    }
//
//    public void setDmBenhIcdCollection1(Collection<DmBenhIcd> dmBenhIcdCollection1) {
//        this.dmBenhIcdCollection1 = dmBenhIcdCollection1;
//    }

//    public Collection<DmBenhVn> getDmBenhVnCollection() {
//        return dmBenhVnCollection;
//    }
//
//    public void setDmBenhVnCollection(Collection<DmBenhVn> dmBenhVnCollection) {
//        this.dmBenhVnCollection = dmBenhVnCollection;
//    }

//    public Collection<DmBenhVn> getDmBenhVnCollection1() {
//        return dmBenhVnCollection1;
//    }
//
//    public void setDmBenhVnCollection1(Collection<DmBenhVn> dmBenhVnCollection1) {
//        this.dmBenhVnCollection1 = dmBenhVnCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmchuongbenhMaso != null ? dmchuongbenhMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmChuongBenh)) {
            return false;
        }
        DmChuongBenh other = (DmChuongBenh) object;
        if ((this.dmchuongbenhMaso == null && other.dmchuongbenhMaso != null) || (this.dmchuongbenhMaso != null && !this.dmchuongbenhMaso.equals(other.dmchuongbenhMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmChuongBenh[dmchuongbenhMaso=" + dmchuongbenhMaso + "]";
    }

}


