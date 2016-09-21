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
@Table(name = "DT_DM_LOAI_PHAU_THUAT")
@NamedQueries({})
public class DtDmLoaiPhauThuat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_LOAI_PHAU_THUAT")
    @SequenceGenerator(name = "DT_DM_LOAI_PHAU_THUAT", sequenceName = "DT_DM_LOAI_PHAU_THUAT_DTDMLOAI", allocationSize = 1)
    @Column(name = "DTDMLOAIPT_MASO", nullable = false)
    private Integer dtdmloaiptMaso;
    @Column(name = "DTDMLOAIPT_MA", nullable = false)
    private String dtdmloaiptMa;
    @Column(name = "DTDMLOAIPT_TEN", nullable = false)
    private String dtdmloaiptTen;
    @Column(name = "DTDMLOAIPT_PHAUTHUAT")
    private Boolean dtdmloaiptPhauthuat;
    @Column(name = "DTDMLOAIPT_MABYTP")
    private Short dtdmloaiptMabytp;
    @Column(name = "DTDMLOAIPT_THUTHUAT")
    private Boolean dtdmloaiptThuthuat;
    @Column(name = "DTDMLOAIPT_MABYTT")
    private Short dtdmloaiptMabytt;
    @Column(name = "DTDMLOAIPT_NGAYGIOCN")
    private Double dtdmloaiptNgaygiocn;
    @Column(name = "DTDMLOAIPT_CHON")
    private Boolean dtdmloaiptChon;
//    @OneToMany(mappedBy = "dtdmphauthuatLoai1")
//    private Collection<DtDmPhauThuat> dtDmPhauThuatCollection;
//    @OneToMany(mappedBy = "dtdmphauthuatLoai11")
//    private Collection<DtDmPhauThuat> dtDmPhauThuatCollection1;
    public DtDmLoaiPhauThuat() {
    }

    public DtDmLoaiPhauThuat(Integer dtdmloaiptMaso) {
        this.dtdmloaiptMaso = dtdmloaiptMaso;
    }

    public DtDmLoaiPhauThuat(Integer dtdmloaiptMaso, String dtdmloaiptMa, String dtdmloaiptTen) {
        this.dtdmloaiptMaso = dtdmloaiptMaso;
        this.dtdmloaiptMa = dtdmloaiptMa;
        this.dtdmloaiptTen = dtdmloaiptTen;
    }

    public Integer getDtdmloaiptMaso() {
        return dtdmloaiptMaso;
    }

    public void setDtdmloaiptMaso(Integer dtdmloaiptMaso) {
        this.dtdmloaiptMaso = dtdmloaiptMaso;
    }

    public String getDtdmloaiptMa() {
        return dtdmloaiptMa;
    }

    public void setDtdmloaiptMa(String dtdmloaiptMa) {
        this.dtdmloaiptMa = dtdmloaiptMa;
    }

    public String getDtdmloaiptTen() {
        return dtdmloaiptTen;
    }

    public void setDtdmloaiptTen(String dtdmloaiptTen) {
        this.dtdmloaiptTen = dtdmloaiptTen;
    }

    public Boolean getDtdmloaiptPhauthuat() {
        return dtdmloaiptPhauthuat;
    }

    public void setDtdmloaiptPhauthuat(Boolean dtdmloaiptPhauthuat) {
        this.dtdmloaiptPhauthuat = dtdmloaiptPhauthuat;
    }

    public Short getDtdmloaiptMabytp() {
        return dtdmloaiptMabytp;
    }

    public void setDtdmloaiptMabytp(Short dtdmloaiptMabytp) {
        this.dtdmloaiptMabytp = dtdmloaiptMabytp;
    }

    public Boolean getDtdmloaiptThuthuat() {
        return dtdmloaiptThuthuat;
    }

    public void setDtdmloaiptThuthuat(Boolean dtdmloaiptThuthuat) {
        this.dtdmloaiptThuthuat = dtdmloaiptThuthuat;
    }

    public Short getDtdmloaiptMabytt() {
        return dtdmloaiptMabytt;
    }

    public void setDtdmloaiptMabytt(Short dtdmloaiptMabytt) {
        this.dtdmloaiptMabytt = dtdmloaiptMabytt;
    }

    public Double getDtdmloaiptNgaygiocn() {
        return dtdmloaiptNgaygiocn;
    }

    public void setDtdmloaiptNgaygiocn(Double dtdmloaiptNgaygiocn) {
        this.dtdmloaiptNgaygiocn = dtdmloaiptNgaygiocn;
    }

    public Boolean getDtdmloaiptChon() {
        return dtdmloaiptChon;
    }

    public void setDtdmloaiptChon(Boolean dtdmloaiptChon) {
        this.dtdmloaiptChon = dtdmloaiptChon;
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
        hash += (dtdmloaiptMaso != null ? dtdmloaiptMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmLoaiPhauThuat)) {
            return false;
        }
        DtDmLoaiPhauThuat other = (DtDmLoaiPhauThuat) object;
        if ((this.dtdmloaiptMaso == null && other.dtdmloaiptMaso != null) || (this.dtdmloaiptMaso != null && !this.dtdmloaiptMaso.equals(other.dtdmloaiptMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmLoaiPhauThuat[dtdmloaiptMaso=" + dtdmloaiptMaso + "]";
    }
}


