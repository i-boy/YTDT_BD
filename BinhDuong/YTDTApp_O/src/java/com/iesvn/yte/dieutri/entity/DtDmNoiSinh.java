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
@Table(name = "DT_DM_NOI_SINH")
@NamedQueries({})
public class DtDmNoiSinh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_NOI_SINH")
    @SequenceGenerator(name = "DT_DM_NOI_SINH", sequenceName = "DT_DM_NOI_SINH_DTDMNOISINH_MAS", allocationSize = 1)
    @Column(name = "DTDMNOISINH_MASO", nullable = false)
    private Integer dtdmnoisinhMaso;
    @Column(name = "DTDMNOISINH_MA", nullable = false)
    private String dtdmnoisinhMa;
    @Column(name = "DTDMNOISINH_TEN")
    private String dtdmnoisinhTen;
    @Column(name = "DTDMNOISINH_MAPHU")
    private String dtdmnoisinhMaphu;
    @Column(name = "DTDMNOISINH_PHANLOAI")
    private String dtdmnoisinhPhanloai;
    @Column(name = "DTDMNOISINH_GHICHU")
    private String dtdmnoisinhGhichu;
    @Column(name = "DTDMNOISINH_NGAYGIOCN")
    private Double dtdmnoisinhNgaygiocn;
    @Column(name = "DTDMNOISINH_CHON")
    private Boolean dtdmnoisinhChon;
//    @OneToMany(mappedBy = "noisinhMa")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection;
//    @OneToMany(mappedBy = "noisinhMa1")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection1;
//    @OneToMany(mappedBy = "noisinhMa")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "noisinhMa1")
//    private Collection<Hsba> hsbaCollection1;
    public DtDmNoiSinh() {
    }

    public DtDmNoiSinh(Integer dtdmnoisinhMaso) {
        this.dtdmnoisinhMaso = dtdmnoisinhMaso;
    }

    public DtDmNoiSinh(Integer dtdmnoisinhMaso, String dtdmnoisinhMa) {
        this.dtdmnoisinhMaso = dtdmnoisinhMaso;
        this.dtdmnoisinhMa = dtdmnoisinhMa;
    }

    public Integer getDtdmnoisinhMaso() {
        return dtdmnoisinhMaso;
    }

    public void setDtdmnoisinhMaso(Integer dtdmnoisinhMaso) {
        this.dtdmnoisinhMaso = dtdmnoisinhMaso;
    }

    public String getDtdmnoisinhMa() {
        return dtdmnoisinhMa;
    }

    public void setDtdmnoisinhMa(String dtdmnoisinhMa) {
        this.dtdmnoisinhMa = dtdmnoisinhMa;
    }

    public String getDtdmnoisinhTen() {
        return dtdmnoisinhTen;
    }

    public void setDtdmnoisinhTen(String dtdmnoisinhTen) {
        this.dtdmnoisinhTen = dtdmnoisinhTen;
    }

    public String getDtdmnoisinhMaphu() {
        return dtdmnoisinhMaphu;
    }

    public void setDtdmnoisinhMaphu(String dtdmnoisinhMaphu) {
        this.dtdmnoisinhMaphu = dtdmnoisinhMaphu;
    }

    public String getDtdmnoisinhPhanloai() {
        return dtdmnoisinhPhanloai;
    }

    public void setDtdmnoisinhPhanloai(String dtdmnoisinhPhanloai) {
        this.dtdmnoisinhPhanloai = dtdmnoisinhPhanloai;
    }

    public String getDtdmnoisinhGhichu() {
        return dtdmnoisinhGhichu;
    }

    public void setDtdmnoisinhGhichu(String dtdmnoisinhGhichu) {
        this.dtdmnoisinhGhichu = dtdmnoisinhGhichu;
    }

    public Double getDtdmnoisinhNgaygiocn() {
        return dtdmnoisinhNgaygiocn;
    }

    public void setDtdmnoisinhNgaygiocn(Double dtdmnoisinhNgaygiocn) {
        this.dtdmnoisinhNgaygiocn = dtdmnoisinhNgaygiocn;
    }

    public Boolean getDtdmnoisinhChon() {
        return dtdmnoisinhChon;
    }

    public void setDtdmnoisinhChon(Boolean dtdmnoisinhChon) {
        this.dtdmnoisinhChon = dtdmnoisinhChon;
    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection() {
//        return hsbaChuyenMonCollection;
//    }
//
//    public void setHsbaChuyenMonCollection(Collection<HsbaChuyenMon> hsbaChuyenMonCollection) {
//        this.hsbaChuyenMonCollection = hsbaChuyenMonCollection;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection1() {
//        return hsbaChuyenMonCollection1;
//    }
//
//    public void setHsbaChuyenMonCollection1(Collection<HsbaChuyenMon> hsbaChuyenMonCollection1) {
//        this.hsbaChuyenMonCollection1 = hsbaChuyenMonCollection1;
//    }

//    public Collection<Hsba> getHsbaCollection() {
//        return hsbaCollection;
//    }
//
//    public void setHsbaCollection(Collection<Hsba> hsbaCollection) {
//        this.hsbaCollection = hsbaCollection;
//    }

//    public Collection<Hsba> getHsbaCollection1() {
//        return hsbaCollection1;
//    }
//
//    public void setHsbaCollection1(Collection<Hsba> hsbaCollection1) {
//        this.hsbaCollection1 = hsbaCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmnoisinhMaso != null ? dtdmnoisinhMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmNoiSinh)) {
            return false;
        }
        DtDmNoiSinh other = (DtDmNoiSinh) object;
        if ((this.dtdmnoisinhMaso == null && other.dtdmnoisinhMaso != null) || (this.dtdmnoisinhMaso != null && !this.dtdmnoisinhMaso.equals(other.dtdmnoisinhMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmNoiSinh[dtdmnoisinhMaso=" + dtdmnoisinhMaso + "]";
    }
}


