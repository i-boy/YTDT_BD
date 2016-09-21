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
@Table(name = "DM_LOAI_NGUON_CHUONG_TRINH")
@NamedQueries({})
public class DmLoaiNguonChuongTrinh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_NGUON_CHUONG_TRINH")
    @SequenceGenerator(name = "DM_LOAI_NGUON_CHUONG_TRINH", sequenceName = "DM_LOAI_NGUON_CHUONG_TRINH_DML", allocationSize = 1)
    @Column(name = "DMLNCT_MASO", nullable = false)
    private Integer dmlnctMaso;
    @Column(name = "DMLNCT_MA", nullable = false)
    private String dmlnctMa;
    @Column(name = "DMLNCT_TEN")
    private String dmlnctTen;
    @Column(name = "DMLNCT_GHICHU")
    private String dmlnctGhichu;
    @Column(name = "DMLNCT_DT")
    private Boolean dmlnctDt;
    @Column(name = "DMLNCT_QL")
    private Boolean dmlnctQl;
    @Column(name = "DMLNCT_DP")
    private Boolean dmlnctDp;
    @Column(name = "DMLNCT_NGAYGIOCN")
    private Double dmlnctNgaygiocn;
//    @OneToMany(mappedBy = "dmlnctMaso")
//    private Collection<DmNguonChuongTrinh> dmNguonChuongTrinhCollection;
//    @OneToMany(mappedBy = "dmlnctMaso1")
//    private Collection<DmNguonChuongTrinh> dmNguonChuongTrinhCollection1;

    public DmLoaiNguonChuongTrinh() {
    }

    public DmLoaiNguonChuongTrinh(Integer dmlnctMaso) {
        this.dmlnctMaso = dmlnctMaso;
    }

    public DmLoaiNguonChuongTrinh(Integer dmlnctMaso, String dmlnctMa) {
        this.dmlnctMaso = dmlnctMaso;
        this.dmlnctMa = dmlnctMa;
    }

    public Integer getDmlnctMaso() {
        return dmlnctMaso;
    }

    public void setDmlnctMaso(Integer dmlnctMaso) {
        this.dmlnctMaso = dmlnctMaso;
    }

    public String getDmlnctMa() {
        return dmlnctMa;
    }

    public void setDmlnctMa(String dmlnctMa) {
        this.dmlnctMa = dmlnctMa;
    }

    public String getDmlnctTen() {
        return dmlnctTen;
    }

    public void setDmlnctTen(String dmlnctTen) {
        this.dmlnctTen = dmlnctTen;
    }

    public String getDmlnctGhichu() {
        return dmlnctGhichu;
    }

    public void setDmlnctGhichu(String dmlnctGhichu) {
        this.dmlnctGhichu = dmlnctGhichu;
    }

    public Boolean getDmlnctDt() {
        return dmlnctDt;
    }

    public void setDmlnctDt(Boolean dmlnctDt) {
        this.dmlnctDt = dmlnctDt;
    }

    public Boolean getDmlnctQl() {
        return dmlnctQl;
    }

    public void setDmlnctQl(Boolean dmlnctQl) {
        this.dmlnctQl = dmlnctQl;
    }

    public Boolean getDmlnctDp() {
        return dmlnctDp;
    }

    public void setDmlnctDp(Boolean dmlnctDp) {
        this.dmlnctDp = dmlnctDp;
    }

    public Double getDmlnctNgaygiocn() {
        return dmlnctNgaygiocn;
    }

    public void setDmlnctNgaygiocn(Double dmlnctNgaygiocn) {
        this.dmlnctNgaygiocn = dmlnctNgaygiocn;
    }

//    public Collection<DmNguonChuongTrinh> getDmNguonChuongTrinhCollection() {
//        return dmNguonChuongTrinhCollection;
//    }
//
//    public void setDmNguonChuongTrinhCollection(Collection<DmNguonChuongTrinh> dmNguonChuongTrinhCollection) {
//        this.dmNguonChuongTrinhCollection = dmNguonChuongTrinhCollection;
//    }

//    public Collection<DmNguonChuongTrinh> getDmNguonChuongTrinhCollection1() {
//        return dmNguonChuongTrinhCollection1;
//    }
//
//    public void setDmNguonChuongTrinhCollection1(Collection<DmNguonChuongTrinh> dmNguonChuongTrinhCollection1) {
//        this.dmNguonChuongTrinhCollection1 = dmNguonChuongTrinhCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmlnctMaso != null ? dmlnctMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmLoaiNguonChuongTrinh)) {
            return false;
        }
        DmLoaiNguonChuongTrinh other = (DmLoaiNguonChuongTrinh) object;
        if ((this.dmlnctMaso == null && other.dmlnctMaso != null) || (this.dmlnctMaso != null && !this.dmlnctMaso.equals(other.dmlnctMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmLoaiNguonChuongTrinh[dmlnctMaso=" + dmlnctMaso + "]";
    }

}


