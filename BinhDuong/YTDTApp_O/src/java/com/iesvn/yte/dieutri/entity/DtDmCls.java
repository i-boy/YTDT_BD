/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

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
@Table(name = "DT_DM_CLS")
@NamedQueries({})
public class DtDmCls implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_CLS_DTDMCLS")
    @SequenceGenerator(name = "DT_DM_CLS_DTDMCLS", sequenceName = "DT_DM_CLS_DTDMCLS_MASO_SEQ", allocationSize = 1)
    @Column(name = "DTDMCLS_MASO", nullable = false)
    private Integer dtdmclsMaso;
    @Column(name = "DTDMCLS_MA", nullable = false)
    private String dtdmclsMa;
    @Column(name = "DTDMCLS_TEN", nullable = false)
    private String dtdmclsTen;

    @Column(name = "DTDMCLS_CHUDAU")
    private String dtdmclsChudau;
    @Column(name = "DTDMCLS_THUTUBC")
    private Short dtdmclsThutubc;
    @Column(name = "DTDMCLS_NGAYGIOCN")
    private Double dtdmclsNgaygiocn;
    @Column(name = "DTDMCLS_CHON")
    private Boolean dtdmclsChon;
    @Column(name = "DTDMPBCLS_MA")
    private String dtdmpbclsMa;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmclsMaso")
//    private Collection<DtDmClsKhoa> dtDmClsKhoaCollection;
//    @OneToMany(mappedBy = "clskhamMaloai")
//    private Collection<ClsKham> clsKhamCollection;
//    @OneToMany(mappedBy = "clskhamMaloai1")
//    private Collection<ClsKham> clsKhamCollection1;
//    @OneToMany(mappedBy = "dtdmclsbgMaloai")
//    private Collection<DtDmClsBangGia> dtDmClsBangGiaCollection;
//    @OneToMany(mappedBy = "dtdmclsbgPhanloai")
//    private Collection<DtDmClsBangGia> dtDmClsBangGiaCollection1;
//    @OneToMany(mappedBy = "dtdmclsbgMaloai1")
//    private Collection<DtDmClsBangGia> dtDmClsBangGiaCollection2;
//    @OneToMany(mappedBy = "dtdmclsbgPhanloai1")
//    private Collection<DtDmClsBangGia> dtDmClsBangGiaCollection3;
    @JoinColumn(name = "DTDMCLS_PHANBIET", referencedColumnName = "DTDMPBCLS_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmPbCls dtdmclsPhanbiet;

    public DtDmCls() {
    }

    public DtDmCls(Integer dtdmclsMaso) {
        this.dtdmclsMaso = dtdmclsMaso;
    }

    public DtDmCls(Integer dtdmclsMaso, String dtdmclsMa, String dtdmclsTen) {
        this.dtdmclsMaso = dtdmclsMaso;
        this.dtdmclsMa = dtdmclsMa;
        this.dtdmclsTen = dtdmclsTen;
    }

    public Integer getDtdmclsMaso() {
        return dtdmclsMaso;
    }

    public void setDtdmclsMaso(Integer dtdmclsMaso) {
        this.dtdmclsMaso = dtdmclsMaso;
    }

    public String getDtdmclsMa() {
        return dtdmclsMa;
    }

    public void setDtdmclsMa(String dtdmclsMa) {
        this.dtdmclsMa = dtdmclsMa;
    }

    public String getDtdmclsTen() {
        return dtdmclsTen;
    }

    public void setDtdmclsTen(String dtdmclsTen) {
        this.dtdmclsTen = dtdmclsTen;
    }

   

    public String getDtdmclsChudau() {
        return dtdmclsChudau;
    }

    public void setDtdmclsChudau(String dtdmclsChudau) {
        this.dtdmclsChudau = dtdmclsChudau;
    }

    public Short getDtdmclsThutubc() {
        return dtdmclsThutubc;
    }

    public void setDtdmclsThutubc(Short dtdmclsThutubc) {
        this.dtdmclsThutubc = dtdmclsThutubc;
    }

    public Double getDtdmclsNgaygiocn() {
        return dtdmclsNgaygiocn;
    }

    public void setDtdmclsNgaygiocn(Double dtdmclsNgaygiocn) {
        this.dtdmclsNgaygiocn = dtdmclsNgaygiocn;
    }

    public Boolean getDtdmclsChon() {
        return dtdmclsChon;
    }

    public void setDtdmclsChon(Boolean dtdmclsChon) {
        this.dtdmclsChon = dtdmclsChon;
    }

    public String getDtdmpbclsMa() {
        return dtdmpbclsMa;
    }

    public void setDtdmpbclsMa(String dtdmpbclsMa) {
        this.dtdmpbclsMa = dtdmpbclsMa;
    }

//    public Collection<DtDmClsKhoa> getDtDmClsKhoaCollection() {
//        return dtDmClsKhoaCollection;
//    }
//
//    public void setDtDmClsKhoaCollection(Collection<DtDmClsKhoa> dtDmClsKhoaCollection) {
//        this.dtDmClsKhoaCollection = dtDmClsKhoaCollection;
//    }

//    public Collection<ClsKham> getClsKhamCollection() {
//        return clsKhamCollection;
//    }
//
//    public void setClsKhamCollection(Collection<ClsKham> clsKhamCollection) {
//        this.clsKhamCollection = clsKhamCollection;
//    }

//    public Collection<ClsKham> getClsKhamCollection1() {
//        return clsKhamCollection1;
//    }
//
//    public void setClsKhamCollection1(Collection<ClsKham> clsKhamCollection1) {
//        this.clsKhamCollection1 = clsKhamCollection1;
//    }

//    public Collection<DtDmClsBangGia> getDtDmClsBangGiaCollection() {
//        return dtDmClsBangGiaCollection;
//    }
//
//    public void setDtDmClsBangGiaCollection(Collection<DtDmClsBangGia> dtDmClsBangGiaCollection) {
//        this.dtDmClsBangGiaCollection = dtDmClsBangGiaCollection;
//    }

//    public Collection<DtDmClsBangGia> getDtDmClsBangGiaCollection1() {
//        return dtDmClsBangGiaCollection1;
//    }
//
//    public void setDtDmClsBangGiaCollection1(Collection<DtDmClsBangGia> dtDmClsBangGiaCollection1) {
//        this.dtDmClsBangGiaCollection1 = dtDmClsBangGiaCollection1;
//    }

//    public Collection<DtDmClsBangGia> getDtDmClsBangGiaCollection2() {
//        return dtDmClsBangGiaCollection2;
//    }
//
//    public void setDtDmClsBangGiaCollection2(Collection<DtDmClsBangGia> dtDmClsBangGiaCollection2) {
//        this.dtDmClsBangGiaCollection2 = dtDmClsBangGiaCollection2;
//    }

//    public Collection<DtDmClsBangGia> getDtDmClsBangGiaCollection3() {
//        return dtDmClsBangGiaCollection3;
//    }
//
//    public void setDtDmClsBangGiaCollection3(Collection<DtDmClsBangGia> dtDmClsBangGiaCollection3) {
//        this.dtDmClsBangGiaCollection3 = dtDmClsBangGiaCollection3;
//    }
    public DtDmPbCls getDtdmclsPhanbiet(boolean create) {
if(create && dtdmclsPhanbiet == null) dtdmclsPhanbiet = new DtDmPbCls();
return dtdmclsPhanbiet;
}
    public DtDmPbCls getDtdmclsPhanbiet() {
        return dtdmclsPhanbiet;
    }

    public void setDtdmclsPhanbiet(DtDmPbCls dtdmclsPhanbiet) {
        this.dtdmclsPhanbiet = dtdmclsPhanbiet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmclsMaso != null ? dtdmclsMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmCls)) {
            return false;
        }
        DtDmCls other = (DtDmCls) object;
        if ((this.dtdmclsMaso == null && other.dtdmclsMaso != null) || (this.dtdmclsMaso != null && !this.dtdmclsMaso.equals(other.dtdmclsMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmCls[dtdmclsMaso=" + dtdmclsMaso + "]";
    }
}


