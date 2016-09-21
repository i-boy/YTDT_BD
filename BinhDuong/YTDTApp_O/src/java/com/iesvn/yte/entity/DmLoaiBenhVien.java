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
@Table(name = "DM_LOAI_BENH_VIEN")
@NamedQueries({})
public class DmLoaiBenhVien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_BENH_VIEN")
    @SequenceGenerator(name = "DM_LOAI_BENH_VIEN", sequenceName = "DM_LOAI_BENH_VIEN_DMLOAIBV_MAS", allocationSize = 1)
    @Column(name = "DMLOAIBV_MASO", nullable = false)
    private Integer dmloaibvMaso;
    @Column(name = "DMLOAIBV_MA")
    private String dmloaibvMa;
    @Column(name = "DMLOAIBV_TEN")
    private String dmloaibvTen;
    @Column(name = "DMLOAIBV_DT")
    private Boolean dmloaibvDt;
    @Column(name = "DMLOAIBV_QL")
    private Boolean dmloaibvQl;
    @Column(name = "DMLOAIBV_DP")
    private Boolean dmloaibvDp;
    @Column(name = "DMLOAIBV_NGAYGIOCN")
    private Double dmloaibvNgaygiocn;
//    @OneToMany(mappedBy = "dmloaibvMaso")
//    private Collection<DmBenhVien> dmBenhVienCollection;
//    @OneToMany(mappedBy = "dmloaibvMaso1")
//    private Collection<DmBenhVien> dmBenhVienCollection1;

    public DmLoaiBenhVien() {
    }

    public DmLoaiBenhVien(Integer dmloaibvMaso) {
        this.dmloaibvMaso = dmloaibvMaso;
    }

    public Integer getDmloaibvMaso() {
        return dmloaibvMaso;
    }

    public void setDmloaibvMaso(Integer dmloaibvMaso) {
        this.dmloaibvMaso = dmloaibvMaso;
    }

    public String getDmloaibvMa() {
        return dmloaibvMa;
    }

    public void setDmloaibvMa(String dmloaibvMa) {
        this.dmloaibvMa = dmloaibvMa;
    }

    public String getDmloaibvTen() {
        return dmloaibvTen;
    }

    public void setDmloaibvTen(String dmloaibvTen) {
        this.dmloaibvTen = dmloaibvTen;
    }

    public Boolean getDmloaibvDt() {
        return dmloaibvDt;
    }

    public void setDmloaibvDt(Boolean dmloaibvDt) {
        this.dmloaibvDt = dmloaibvDt;
    }

    public Boolean getDmloaibvQl() {
        return dmloaibvQl;
    }

    public void setDmloaibvQl(Boolean dmloaibvQl) {
        this.dmloaibvQl = dmloaibvQl;
    }

    public Boolean getDmloaibvDp() {
        return dmloaibvDp;
    }

    public void setDmloaibvDp(Boolean dmloaibvDp) {
        this.dmloaibvDp = dmloaibvDp;
    }

    public Double getDmloaibvNgaygiocn() {
        return dmloaibvNgaygiocn;
    }

    public void setDmloaibvNgaygiocn(Double dmloaibvNgaygiocn) {
        this.dmloaibvNgaygiocn = dmloaibvNgaygiocn;
    }

//    public Collection<DmBenhVien> getDmBenhVienCollection() {
//        return dmBenhVienCollection;
//    }
//
//    public void setDmBenhVienCollection(Collection<DmBenhVien> dmBenhVienCollection) {
//        this.dmBenhVienCollection = dmBenhVienCollection;
//    }

//    public Collection<DmBenhVien> getDmBenhVienCollection1() {
//        return dmBenhVienCollection1;
//    }
//
//    public void setDmBenhVienCollection1(Collection<DmBenhVien> dmBenhVienCollection1) {
//        this.dmBenhVienCollection1 = dmBenhVienCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmloaibvMaso != null ? dmloaibvMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmLoaiBenhVien)) {
            return false;
        }
        DmLoaiBenhVien other = (DmLoaiBenhVien) object;
        if ((this.dmloaibvMaso == null && other.dmloaibvMaso != null) || (this.dmloaibvMaso != null && !this.dmloaibvMaso.equals(other.dmloaibvMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmLoaiBenhVien[dmloaibvMaso=" + dmloaibvMaso + "]";
    }

}


