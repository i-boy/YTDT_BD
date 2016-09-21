/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "DM_BENH_TRUYEN_NHIEM")
@NamedQueries({})
public class DmBenhTruyenNhiem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_BENH_TRUYEN_NHIEM")
    @SequenceGenerator(name = "DM_BENH_TRUYEN_NHIEM", sequenceName = "DM_BENH_TRUYEN_NHIEM_DMBTN_MAS", allocationSize = 1)
    @Column(name = "DMBTN_MASO", nullable = false)
    private Integer dmbtnMaso;
    @Column(name = "DMBTN_ICD10")
    private String dmbtnIcd10;
    @Column(name = "DMBTN_TC")
    private String dmbtnTc;
    @Column(name = "DMBTN_YTDT")
    private String dmbtnYtdt;
    @Column(name = "DMBTN_GHICHU")
    private String dmbtnGhichu;
    @Column(name = "DMBTN_CHON", nullable = false)
    private boolean dmbtnChon;
    @Column(name = "DMBTN_NGAYGIOCN")
    private Double dmbtnNgaygiocn;
//    @OneToMany(mappedBy = "tiepdonMadich")
//    private Collection<TiepDon> tiepDonCollection;
    @JoinColumn(name = "DMBENHVN_MASO", referencedColumnName = "DMBENHVN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhVn dmbenhvnMaso;
    @JoinColumn(name = "DMLBTN_MASO", referencedColumnName = "DMLBTN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiBenhTruyenNhiem dmlbtnMaso;


    public DmBenhTruyenNhiem() {
    }

    public DmBenhTruyenNhiem(Integer dmbtnMaso) {
        this.dmbtnMaso = dmbtnMaso;
    }

    public DmBenhTruyenNhiem(Integer dmbtnMaso, boolean dmbtnChon) {
        this.dmbtnMaso = dmbtnMaso;
        this.dmbtnChon = dmbtnChon;
    }

    public Integer getDmbtnMaso() {
        return dmbtnMaso;
    }

    public void setDmbtnMaso(Integer dmbtnMaso) {
        this.dmbtnMaso = dmbtnMaso;
    }

    public String getDmbtnIcd10() {
        return dmbtnIcd10;
    }

    public void setDmbtnIcd10(String dmbtnIcd10) {
        this.dmbtnIcd10 = dmbtnIcd10;
    }

    public String getDmbtnTc() {
        return dmbtnTc;
    }

    public void setDmbtnTc(String dmbtnTc) {
        this.dmbtnTc = dmbtnTc;
    }

    public String getDmbtnYtdt() {
        return dmbtnYtdt;
    }

    public void setDmbtnYtdt(String dmbtnYtdt) {
        this.dmbtnYtdt = dmbtnYtdt;
    }

    public String getDmbtnGhichu() {
        return dmbtnGhichu;
    }

    public void setDmbtnGhichu(String dmbtnGhichu) {
        this.dmbtnGhichu = dmbtnGhichu;
    }

    public boolean getDmbtnChon() {
        return dmbtnChon;
    }

    public void setDmbtnChon(boolean dmbtnChon) {
        this.dmbtnChon = dmbtnChon;
    }

    public Double getDmbtnNgaygiocn() {
        return dmbtnNgaygiocn;
    }

    public void setDmbtnNgaygiocn(Double dmbtnNgaygiocn) {
        this.dmbtnNgaygiocn = dmbtnNgaygiocn;
    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }

    public DmBenhVn getDmbenhvnMaso(boolean create) {
if(create && dmbenhvnMaso == null) dmbenhvnMaso = new DmBenhVn();
return dmbenhvnMaso;
}
    public DmBenhVn getDmbenhvnMaso() {
        return dmbenhvnMaso;
    }

    public void setDmbenhvnMaso(DmBenhVn dmbenhvnMaso) {
        this.dmbenhvnMaso = dmbenhvnMaso;
    }

    public DmLoaiBenhTruyenNhiem getDmlbtnMaso(boolean create) {
if(create && dmlbtnMaso == null) dmlbtnMaso = new DmLoaiBenhTruyenNhiem();
return dmlbtnMaso;
}
    public DmLoaiBenhTruyenNhiem getDmlbtnMaso() {
        return dmlbtnMaso;
    }

    public void setDmlbtnMaso(DmLoaiBenhTruyenNhiem dmlbtnMaso) {
        this.dmlbtnMaso = dmlbtnMaso;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmbtnMaso != null ? dmbtnMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmBenhTruyenNhiem)) {
            return false;
        }
        DmBenhTruyenNhiem other = (DmBenhTruyenNhiem) object;
        if ((this.dmbtnMaso == null && other.dmbtnMaso != null) || (this.dmbtnMaso != null && !this.dmbtnMaso.equals(other.dmbtnMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmBenhTruyenNhiem[dmbtnMaso=" + dmbtnMaso + "]";
    }

}


