/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

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
@Table(name = "DT_DM_PL_PHAU_THUAT")
@NamedQueries({})
public class DtDmPlPhauThuat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_PL_PHAU_THUAT")
    @SequenceGenerator(name = "DT_DM_PL_PHAU_THUAT", sequenceName = "DT_DM_PL_PHAU_THUAT_DTDMPLPT_M", allocationSize = 1)
    @Column(name = "DTDMPLPT_MASO", nullable = false)
    private Integer dtdmplptMaso;
    @Column(name = "DTDMPLPT_MA", nullable = false)
    private String dtdmplptMa;
    @Column(name = "DTDMPLPT_MAPHU")
    private String dtdmplptMaphu;
    @Column(name = "DTDMPLPT_TEN", nullable = false)
    private String dtdmplptTen;
    @Column(name = "DTDMPLPT_THUOCKHOA")
    private String dtdmplptThuockhoa;
    @Column(name = "DTDMPLPT_NGAYGIOCN")
    private Double dtdmplptNgaygiocn;
    @Column(name = "DTDMPLPT_CHON")
    private Boolean dtdmplptChon;
//    @OneToMany(mappedBy = "dtdmphauthuatPhanloai1")
//    private Collection<DtDmPhauThuat> dtDmPhauThuatCollection;
//    @OneToMany(mappedBy = "dtdmphauthuatPhanloai11")
//    private Collection<DtDmPhauThuat> dtDmPhauThuatCollection1;
    public DtDmPlPhauThuat() {
    }

    public DtDmPlPhauThuat(Integer dtdmplptMaso) {
        this.dtdmplptMaso = dtdmplptMaso;
    }

    public DtDmPlPhauThuat(Integer dtdmplptMaso, String dtdmplptMa, String dtdmplptTen) {
        this.dtdmplptMaso = dtdmplptMaso;
        this.dtdmplptMa = dtdmplptMa;
        this.dtdmplptTen = dtdmplptTen;
    }

    public Integer getDtdmplptMaso() {
        return dtdmplptMaso;
    }

    public void setDtdmplptMaso(Integer dtdmplptMaso) {
        this.dtdmplptMaso = dtdmplptMaso;
    }

    public String getDtdmplptMa() {
        return dtdmplptMa;
    }

    public void setDtdmplptMa(String dtdmplptMa) {
        this.dtdmplptMa = dtdmplptMa;
    }

    public String getDtdmplptMaphu() {
        return dtdmplptMaphu;
    }

    public void setDtdmplptMaphu(String dtdmplptMaphu) {
        this.dtdmplptMaphu = dtdmplptMaphu;
    }

    public String getDtdmplptTen() {
        return dtdmplptTen;
    }

    public void setDtdmplptTen(String dtdmplptTen) {
        this.dtdmplptTen = dtdmplptTen;
    }

    public String getDtdmplptThuockhoa() {
        return dtdmplptThuockhoa;
    }

    public void setDtdmplptThuockhoa(String dtdmplptThuockhoa) {
        this.dtdmplptThuockhoa = dtdmplptThuockhoa;
    }

    public Double getDtdmplptNgaygiocn() {
        return dtdmplptNgaygiocn;
    }

    public void setDtdmplptNgaygiocn(Double dtdmplptNgaygiocn) {
        this.dtdmplptNgaygiocn = dtdmplptNgaygiocn;
    }

    public Boolean getDtdmplptChon() {
        return dtdmplptChon;
    }

    public void setDtdmplptChon(Boolean dtdmplptChon) {
        this.dtdmplptChon = dtdmplptChon;
    }

//    public Collection<DtDmPhauThuat> getDtDmPhauThuatCollection() {
//        return dtDmPhauThuatCollection;
//    }
//
//    public void setDtDmPhauThuatCollection(Collection<DtDmPhauThuat> dtDmPhauThuatCollection) {
//        this.dtDmPhauThuatCollection = dtDmPhauThuatCollection;
//    }

//    public Collection<DtDmPhauThuat> getDtDmPhauThuatCollection1() {
//        return dtDmPhauThuatCollection1;
//    }
//
//    public void setDtDmPhauThuatCollection1(Collection<DtDmPhauThuat> dtDmPhauThuatCollection1) {
//        this.dtDmPhauThuatCollection1 = dtDmPhauThuatCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmplptMaso != null ? dtdmplptMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmPlPhauThuat)) {
            return false;
        }
        DtDmPlPhauThuat other = (DtDmPlPhauThuat) object;
        if ((this.dtdmplptMaso == null && other.dtdmplptMaso != null) || (this.dtdmplptMaso != null && !this.dtdmplptMaso.equals(other.dtdmplptMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmPlPhauThuat[dtdmplptMaso=" + dtdmplptMaso + "]";
    }
}


