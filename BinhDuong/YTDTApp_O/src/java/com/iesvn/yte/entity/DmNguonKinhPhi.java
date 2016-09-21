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
@Table(name = "DM_NGUON_KINH_PHI")
@NamedQueries({})
public class DmNguonKinhPhi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NGUON_KINH_PHI")
    @SequenceGenerator(name = "DM_NGUON_KINH_PHI", sequenceName = "DM_NGUON_KINH_PHI_DMNGUONKINHP", allocationSize = 1)
    @Column(name = "DMNGUONKINHPHI_MASO", nullable = false)
    private Integer dmnguonkinhphiMaso;
    @Column(name = "DMNGUONKINHPHI_MA")
    private String dmnguonkinhphiMa;
    @Column(name = "DMNGUONKINHPHI_TEN", nullable = false)
    private String dmnguonkinhphiTen;
    @Column(name = "DMNGUONKINHPHI_NGAYGIOCN")
    private Double dmnguonkinhphiNgaygiocn;
    @Column(name = "DMNGUONKINHPHI_QL")
    private Boolean dmnguonkinhphiQl;
    @Column(name = "DMNGUONKINHPHI_DT")
    private Boolean dmnguonkinhphiDt;
    @Column(name = "DMNGUONKINHPHI_DP")
    private Boolean dmnguonkinhphiDp;
//    @OneToMany(mappedBy = "dmnguonkinhphiMaso")
//    private Collection<CtTraKho> ctTraKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmnguonkinhphiMaso")
//    private Collection<CtXuatKho> ctXuatKhoCollection;
//    @OneToMany(mappedBy = "dmnguonkinhphiMaso")
//    private Collection<CtPhieuDt> ctPhieuDtCollection;
//    @OneToMany(mappedBy = "dmnguonkinhphiMaso")
//    private Collection<CtXuatBh> ctXuatBhCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmnguonkinhphiMaso")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmnguonkinhphiMaso1")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection1;
//    @OneToMany(mappedBy = "dmnguonkinhphiMaso")
//    private Collection<TonKho> tonKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmnguonkinhphiMaso")
//    private Collection<DtDmKhach> dtDmKhachCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmnguonkinhphiMaso1")
//    private Collection<DtDmKhach> dtDmKhachCollection1;

    public DmNguonKinhPhi() {
    }

    public DmNguonKinhPhi(Integer dmnguonkinhphiMaso) {
        this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
    }

    public DmNguonKinhPhi(Integer dmnguonkinhphiMaso, String dmnguonkinhphiTen) {
        this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
        this.dmnguonkinhphiTen = dmnguonkinhphiTen;
    }

    public Integer getDmnguonkinhphiMaso() {
        return dmnguonkinhphiMaso;
    }

    public void setDmnguonkinhphiMaso(Integer dmnguonkinhphiMaso) {
        this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
    }

    public String getDmnguonkinhphiMa() {
        return dmnguonkinhphiMa;
    }

    public void setDmnguonkinhphiMa(String dmnguonkinhphiMa) {
        this.dmnguonkinhphiMa = dmnguonkinhphiMa;
    }

    public String getDmnguonkinhphiTen() {
        return dmnguonkinhphiTen;
    }

    public void setDmnguonkinhphiTen(String dmnguonkinhphiTen) {
        this.dmnguonkinhphiTen = dmnguonkinhphiTen;
    }

    public Double getDmnguonkinhphiNgaygiocn() {
        return dmnguonkinhphiNgaygiocn;
    }

    public void setDmnguonkinhphiNgaygiocn(Double dmnguonkinhphiNgaygiocn) {
        this.dmnguonkinhphiNgaygiocn = dmnguonkinhphiNgaygiocn;
    }

    public Boolean getDmnguonkinhphiQl() {
        return dmnguonkinhphiQl;
    }

    public void setDmnguonkinhphiQl(Boolean dmnguonkinhphiQl) {
        this.dmnguonkinhphiQl = dmnguonkinhphiQl;
    }

    public Boolean getDmnguonkinhphiDt() {
        return dmnguonkinhphiDt;
    }

    public void setDmnguonkinhphiDt(Boolean dmnguonkinhphiDt) {
        this.dmnguonkinhphiDt = dmnguonkinhphiDt;
    }

    public Boolean getDmnguonkinhphiDp() {
        return dmnguonkinhphiDp;
    }

    public void setDmnguonkinhphiDp(Boolean dmnguonkinhphiDp) {
        this.dmnguonkinhphiDp = dmnguonkinhphiDp;
    }

//    public Collection<CtTraKho> getCtTraKhoCollection() {
//        return ctTraKhoCollection;
//    }
//
//    public void setCtTraKhoCollection(Collection<CtTraKho> ctTraKhoCollection) {
//        this.ctTraKhoCollection = ctTraKhoCollection;
//    }

//    public Collection<CtXuatKho> getCtXuatKhoCollection() {
//        return ctXuatKhoCollection;
//    }
//
//    public void setCtXuatKhoCollection(Collection<CtXuatKho> ctXuatKhoCollection) {
//        this.ctXuatKhoCollection = ctXuatKhoCollection;
//    }

//    public Collection<CtPhieuDt> getCtPhieuDtCollection() {
//        return ctPhieuDtCollection;
//    }
//
//    public void setCtPhieuDtCollection(Collection<CtPhieuDt> ctPhieuDtCollection) {
//        this.ctPhieuDtCollection = ctPhieuDtCollection;
//    }

//    public Collection<CtXuatBh> getCtXuatBhCollection() {
//        return ctXuatBhCollection;
//    }
//
//    public void setCtXuatBhCollection(Collection<CtXuatBh> ctXuatBhCollection) {
//        this.ctXuatBhCollection = ctXuatBhCollection;
//    }

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection() {
//        return phieuNhapKhoCollection;
//    }
//
//    public void setPhieuNhapKhoCollection(Collection<PhieuNhapKho> phieuNhapKhoCollection) {
//        this.phieuNhapKhoCollection = phieuNhapKhoCollection;
//    }

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection1() {
//        return phieuNhapKhoCollection1;
//    }
//
//    public void setPhieuNhapKhoCollection1(Collection<PhieuNhapKho> phieuNhapKhoCollection1) {
//        this.phieuNhapKhoCollection1 = phieuNhapKhoCollection1;
//    }

//    public Collection<TonKho> getTonKhoCollection() {
//        return tonKhoCollection;
//    }
//
//    public void setTonKhoCollection(Collection<TonKho> tonKhoCollection) {
//        this.tonKhoCollection = tonKhoCollection;
//    }

//    public Collection<DtDmKhach> getDtDmKhachCollection() {
//        return dtDmKhachCollection;
//    }
//
//    public void setDtDmKhachCollection(Collection<DtDmKhach> dtDmKhachCollection) {
//        this.dtDmKhachCollection = dtDmKhachCollection;
//    }

//    public Collection<DtDmKhach> getDtDmKhachCollection1() {
//        return dtDmKhachCollection1;
//    }
//
//    public void setDtDmKhachCollection1(Collection<DtDmKhach> dtDmKhachCollection1) {
//        this.dtDmKhachCollection1 = dtDmKhachCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmnguonkinhphiMaso != null ? dmnguonkinhphiMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmNguonKinhPhi)) {
            return false;
        }
        DmNguonKinhPhi other = (DmNguonKinhPhi) object;
        if ((this.dmnguonkinhphiMaso == null && other.dmnguonkinhphiMaso != null) || (this.dmnguonkinhphiMaso != null && !this.dmnguonkinhphiMaso.equals(other.dmnguonkinhphiMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmNguonKinhPhi[dmnguonkinhphiMaso=" + dmnguonkinhphiMaso + "]";
    }

}


