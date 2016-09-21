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
@Table(name = "DM_NGHE_NGHIEP_BAO_CAO")
@NamedQueries({})
public class DmNgheNghiepBaoCao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NGHE_NGHIEP_BAO_CAO")
    @SequenceGenerator(name = "DM_NGHE_NGHIEP_BAO_CAO", sequenceName = "DM_NGHE_NGHIEP_BAO_CAO_DMNGHEN", allocationSize = 1)
    @Column(name = "DMNGHENGHIEPBC_MASO", nullable = false)
    private Integer dmnghenghiepbcMaso;
    @Column(name = "DMNGHENGHIEPBC_MA", nullable = false)
    private String dmnghenghiepbcMa;
    @Column(name = "DMNGHENGHIEPBC_TEN", nullable = false)
    private String dmnghenghiepbcTen;
    @Column(name = "DMNGHENGHIEPBC_NGAYGIOCN")
    private Double dmnghenghiepbcNgaygiocn;
    @Column(name = "DMNGHENGHIEPBC_QL")
    private Boolean dmnghenghiepbcQl;
    @Column(name = "DMNGHENGHIEPBC_DT")
    private Boolean dmnghenghiepbcDt;
    @Column(name = "DMNGHENGHIEPBC_DP")
    private Boolean dmnghenghiepbcDp;
//    @OneToMany(mappedBy = "dmnghenghiepPhanloai")
//    private Collection<DmNgheNghiep> dmNgheNghiepCollection;
//    @OneToMany(mappedBy = "dmnghenghiepPhanloai1")
//    private Collection<DmNgheNghiep> dmNgheNghiepCollection1;

    public DmNgheNghiepBaoCao() {
    }

    public DmNgheNghiepBaoCao(Integer dmnghenghiepbcMaso) {
        this.dmnghenghiepbcMaso = dmnghenghiepbcMaso;
    }

    public DmNgheNghiepBaoCao(Integer dmnghenghiepbcMaso, String dmnghenghiepbcMa, String dmnghenghiepbcTen) {
        this.dmnghenghiepbcMaso = dmnghenghiepbcMaso;
        this.dmnghenghiepbcMa = dmnghenghiepbcMa;
        this.dmnghenghiepbcTen = dmnghenghiepbcTen;
    }

    public Integer getDmnghenghiepbcMaso() {
        return dmnghenghiepbcMaso;
    }

    public void setDmnghenghiepbcMaso(Integer dmnghenghiepbcMaso) {
        this.dmnghenghiepbcMaso = dmnghenghiepbcMaso;
    }

    public String getDmnghenghiepbcMa() {
        return dmnghenghiepbcMa;
    }

    public void setDmnghenghiepbcMa(String dmnghenghiepbcMa) {
        this.dmnghenghiepbcMa = dmnghenghiepbcMa;
    }

    public String getDmnghenghiepbcTen() {
        return dmnghenghiepbcTen;
    }

    public void setDmnghenghiepbcTen(String dmnghenghiepbcTen) {
        this.dmnghenghiepbcTen = dmnghenghiepbcTen;
    }

    public Double getDmnghenghiepbcNgaygiocn() {
        return dmnghenghiepbcNgaygiocn;
    }

    public void setDmnghenghiepbcNgaygiocn(Double dmnghenghiepbcNgaygiocn) {
        this.dmnghenghiepbcNgaygiocn = dmnghenghiepbcNgaygiocn;
    }

    public Boolean getDmnghenghiepbcQl() {
        return dmnghenghiepbcQl;
    }

    public void setDmnghenghiepbcQl(Boolean dmnghenghiepbcQl) {
        this.dmnghenghiepbcQl = dmnghenghiepbcQl;
    }

    public Boolean getDmnghenghiepbcDt() {
        return dmnghenghiepbcDt;
    }

    public void setDmnghenghiepbcDt(Boolean dmnghenghiepbcDt) {
        this.dmnghenghiepbcDt = dmnghenghiepbcDt;
    }

    public Boolean getDmnghenghiepbcDp() {
        return dmnghenghiepbcDp;
    }

    public void setDmnghenghiepbcDp(Boolean dmnghenghiepbcDp) {
        this.dmnghenghiepbcDp = dmnghenghiepbcDp;
    }

//    public Collection<DmNgheNghiep> getDmNgheNghiepCollection() {
//        return dmNgheNghiepCollection;
//    }
//
//    public void setDmNgheNghiepCollection(Collection<DmNgheNghiep> dmNgheNghiepCollection) {
//        this.dmNgheNghiepCollection = dmNgheNghiepCollection;
//    }

//    public Collection<DmNgheNghiep> getDmNgheNghiepCollection1() {
//        return dmNgheNghiepCollection1;
//    }
//
//    public void setDmNgheNghiepCollection1(Collection<DmNgheNghiep> dmNgheNghiepCollection1) {
//        this.dmNgheNghiepCollection1 = dmNgheNghiepCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmnghenghiepbcMaso != null ? dmnghenghiepbcMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmNgheNghiepBaoCao)) {
            return false;
        }
        DmNgheNghiepBaoCao other = (DmNgheNghiepBaoCao) object;
        if ((this.dmnghenghiepbcMaso == null && other.dmnghenghiepbcMaso != null) || (this.dmnghenghiepbcMaso != null && !this.dmnghenghiepbcMaso.equals(other.dmnghenghiepbcMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmNgheNghiepBaoCao[dmnghenghiepbcMaso=" + dmnghenghiepbcMaso + "]";
    }

}


