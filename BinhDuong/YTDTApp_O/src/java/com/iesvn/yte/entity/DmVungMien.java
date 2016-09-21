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
@Table(name = "DM_VUNG_MIEN")
@NamedQueries({})
public class DmVungMien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_VUNG_MIEN_DMVUNGMIEN")
    @SequenceGenerator(name = "DM_VUNG_MIEN_DMVUNGMIEN", sequenceName = "DM_VUNG_MIEN_DMVUNGMIEN_MASO_S", allocationSize = 1)
    @Column(name = "DMVUNGMIEN_MASO", nullable = false)
    private Integer dmvungmienMaso;
    @Column(name = "DMVUNGMIEN_MA")
    private String dmvungmienMa;
    @Column(name = "DMVUNGMIEN_TEN", nullable = false)
    private String dmvungmienTen;
    @Column(name = "DMVUNGMIEN_NGAYGIOCN")
    private Double dmvungmienNgaygiocn;
    @Column(name = "DMVUNGMIEN_CHON")
    private Boolean dmvungmienChon;
//    @OneToMany(mappedBy = "dmvungmienMaso")
//    private Collection<DmBenhVien> dmBenhVienCollection;
//    @OneToMany(mappedBy = "dmvungmienMaso1")
//    private Collection<DmBenhVien> dmBenhVienCollection1;

    public DmVungMien() {
    }

    public DmVungMien(Integer dmvungmienMaso) {
        this.dmvungmienMaso = dmvungmienMaso;
    }

    public DmVungMien(Integer dmvungmienMaso, String dmvungmienTen) {
        this.dmvungmienMaso = dmvungmienMaso;
        this.dmvungmienTen = dmvungmienTen;
    }

    public Integer getDmvungmienMaso() {
        return dmvungmienMaso;
    }

    public void setDmvungmienMaso(Integer dmvungmienMaso) {
        this.dmvungmienMaso = dmvungmienMaso;
    }

    public String getDmvungmienMa() {
        return dmvungmienMa;
    }

    public void setDmvungmienMa(String dmvungmienMa) {
        this.dmvungmienMa = dmvungmienMa;
    }

    public String getDmvungmienTen() {
        return dmvungmienTen;
    }

    public void setDmvungmienTen(String dmvungmienTen) {
        this.dmvungmienTen = dmvungmienTen;
    }

    public Double getDmvungmienNgaygiocn() {
        return dmvungmienNgaygiocn;
    }

    public void setDmvungmienNgaygiocn(Double dmvungmienNgaygiocn) {
        this.dmvungmienNgaygiocn = dmvungmienNgaygiocn;
    }

    public Boolean getDmvungmienChon() {
        return dmvungmienChon;
    }

    public void setDmvungmienChon(Boolean dmvungmienChon) {
        this.dmvungmienChon = dmvungmienChon;
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
        hash += (dmvungmienMaso != null ? dmvungmienMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmVungMien)) {
            return false;
        }
        DmVungMien other = (DmVungMien) object;
        if ((this.dmvungmienMaso == null && other.dmvungmienMaso != null) || (this.dmvungmienMaso != null && !this.dmvungmienMaso.equals(other.dmvungmienMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmVungMien[dmvungmienMaso=" + dmvungmienMaso + "]";
    }

}


