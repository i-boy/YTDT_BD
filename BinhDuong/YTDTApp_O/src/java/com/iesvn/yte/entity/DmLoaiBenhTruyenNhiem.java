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
@Table(name = "DM_LOAI_BENH_TRUYEN_NHIEM")
@NamedQueries({})
public class DmLoaiBenhTruyenNhiem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_BENH_TRUYEN_NHIEM")
    @SequenceGenerator(name = "DM_LOAI_BENH_TRUYEN_NHIEM", sequenceName = "DM_LOAI_BENH_TRUYEN_NHIEM_DMLB", allocationSize = 1)
    @Column(name = "DMLBTN_MASO", nullable = false)
    private Integer dmlbtnMaso;
    @Column(name = "DMLBTN_MA")
    private String dmlbtnMa;
    @Column(name = "DMLBTN_TEN", nullable = false)
    private String dmlbtnTen;
    @Column(name = "DMLBTN_GHICHU")
    private String dmlbtnGhichu;
    @Column(name = "DMLBTN_NGAYGIOCN")
    private Double dmlbtnNgaygiocn;
    @Column(name = "DMLBTN_DT")
    private Boolean dmlbtnDt;
    @Column(name = "DMLBTN_QL")
    private Boolean dmlbtnQl;
    @Column(name = "DMLBTN_DP")
    private Boolean dmlbtnDp;
//    @OneToMany(mappedBy = "dmlbtnMaso")
//    private Collection<DmBenhTruyenNhiem> dmBenhTruyenNhiemCollection;
//    @OneToMany(mappedBy = "dmlbtnMaso1")
//    private Collection<DmBenhTruyenNhiem> dmBenhTruyenNhiemCollection1;

    public DmLoaiBenhTruyenNhiem() {
    }

    public DmLoaiBenhTruyenNhiem(Integer dmlbtnMaso) {
        this.dmlbtnMaso = dmlbtnMaso;
    }

    public DmLoaiBenhTruyenNhiem(Integer dmlbtnMaso, String dmlbtnTen) {
        this.dmlbtnMaso = dmlbtnMaso;
        this.dmlbtnTen = dmlbtnTen;
    }

    public Integer getDmlbtnMaso() {
        return dmlbtnMaso;
    }

    public void setDmlbtnMaso(Integer dmlbtnMaso) {
        this.dmlbtnMaso = dmlbtnMaso;
    }

    public String getDmlbtnMa() {
        return dmlbtnMa;
    }

    public void setDmlbtnMa(String dmlbtnMa) {
        this.dmlbtnMa = dmlbtnMa;
    }

    public String getDmlbtnTen() {
        return dmlbtnTen;
    }

    public void setDmlbtnTen(String dmlbtnTen) {
        this.dmlbtnTen = dmlbtnTen;
    }

    public String getDmlbtnGhichu() {
        return dmlbtnGhichu;
    }

    public void setDmlbtnGhichu(String dmlbtnGhichu) {
        this.dmlbtnGhichu = dmlbtnGhichu;
    }

    public Double getDmlbtnNgaygiocn() {
        return dmlbtnNgaygiocn;
    }

    public void setDmlbtnNgaygiocn(Double dmlbtnNgaygiocn) {
        this.dmlbtnNgaygiocn = dmlbtnNgaygiocn;
    }

    public Boolean getDmlbtnDt() {
        return dmlbtnDt;
    }

    public void setDmlbtnDt(Boolean dmlbtnDt) {
        this.dmlbtnDt = dmlbtnDt;
    }

    public Boolean getDmlbtnQl() {
        return dmlbtnQl;
    }

    public void setDmlbtnQl(Boolean dmlbtnQl) {
        this.dmlbtnQl = dmlbtnQl;
    }

    public Boolean getDmlbtnDp() {
        return dmlbtnDp;
    }

    public void setDmlbtnDp(Boolean dmlbtnDp) {
        this.dmlbtnDp = dmlbtnDp;
    }

//    public Collection<DmBenhTruyenNhiem> getDmBenhTruyenNhiemCollection() {
//        return dmBenhTruyenNhiemCollection;
//    }
//
//    public void setDmBenhTruyenNhiemCollection(Collection<DmBenhTruyenNhiem> dmBenhTruyenNhiemCollection) {
//        this.dmBenhTruyenNhiemCollection = dmBenhTruyenNhiemCollection;
//    }

//    public Collection<DmBenhTruyenNhiem> getDmBenhTruyenNhiemCollection1() {
//        return dmBenhTruyenNhiemCollection1;
//    }
//
//    public void setDmBenhTruyenNhiemCollection1(Collection<DmBenhTruyenNhiem> dmBenhTruyenNhiemCollection1) {
//        this.dmBenhTruyenNhiemCollection1 = dmBenhTruyenNhiemCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmlbtnMaso != null ? dmlbtnMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmLoaiBenhTruyenNhiem)) {
            return false;
        }
        DmLoaiBenhTruyenNhiem other = (DmLoaiBenhTruyenNhiem) object;
        if ((this.dmlbtnMaso == null && other.dmlbtnMaso != null) || (this.dmlbtnMaso != null && !this.dmlbtnMaso.equals(other.dmlbtnMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmLoaiBenhTruyenNhiem[dmlbtnMaso=" + dmlbtnMaso + "]";
    }

}


