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
@Table(name = "DT_DM_PHAU_THUAT")
@NamedQueries({})
public class DtDmPhauThuat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_PHAU_THUAT")
    @SequenceGenerator(name = "DT_DM_PHAU_THUAT", sequenceName = "DT_DM_PHAU_THUAT_DTDMPHAUTHUAT", allocationSize = 1)
    @Column(name = "DTDMPHAUTHUAT_MASO", nullable = false)
    private Integer dtdmphauthuatMaso;
    @Column(name = "DTDMPHAUTHUAT_MA", nullable = false)
    private String dtdmphauthuatMa;
    @Column(name = "DTDMPHAUTHUAT_TEN", nullable = false)
    private String dtdmphauthuatTen;
    @Column(name = "DTDMPHAUTHUAT_LOAI2")
    private Integer dtdmphauthuatLoai2;
    @Column(name = "DTDMPHAUTHUAT_PHANBIET")
    private String dtdmphauthuatPhanbiet;
    @Column(name = "DTDMPHAUTHUAT_PHANLOAI2")
    private Integer dtdmphauthuatPhanloai2;
    @Column(name = "DTDMPHAUTHUAT_GOPCA")
    private Short dtdmphauthuatGopca;
    @Column(name = "DTDMPHAUTHUAT_MO")
    private Boolean dtdmphauthuatMo;
    @Column(name = "DTDMPHAUTHUAT_CHON1")
    private Boolean dtdmphauthuatChon1;
    @Column(name = "DTDMPHAUTHUAT_CHON2")
    private Boolean dtdmphauthuatChon2;
    @Column(name = "DTDMPHAUTHUAT_LIENHE")
    private String dtdmphauthuatLienhe;
    @Column(name = "DTDMPHAUTHUAT_MAMO")
    private String dtdmphauthuatMamo;
    @Column(name = "DTDMPHAUTHUAT_ORDER")
    private Short dtdmphauthuatOrder;
    @Column(name = "DTDMPHAUTHUAT_NGAYGIOCN")
    private Double dtdmphauthuatNgaygiocn;
    @Column(name = "DTDMPHAUTHUAT_CHON")
       private Boolean dtdmphauthuatChon;

//    @OneToMany(mappedBy = "hsbamoMamo")
//    private Collection<HsbaMo> hsbaMoCollection;
//    @OneToMany(mappedBy = "hsbamoMamo1")
//    private Collection<HsbaMo> hsbaMoCollection1;
    @JoinColumn(name = "DTDMPHAUTHUAT_LOAI1", referencedColumnName = "DTDMLOAIPT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiPhauThuat dtdmphauthuatLoai1;
    @JoinColumn(name = "DTDMPHAUTHUAT_PHANLOAI1", referencedColumnName = "DTDMPLPT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmPlPhauThuat dtdmphauthuatPhanloai1;

    public DtDmPhauThuat() {
    }

    public DtDmPhauThuat(Integer dtdmphauthuatMaso) {
        this.dtdmphauthuatMaso = dtdmphauthuatMaso;
    }

    public DtDmPhauThuat(Integer dtdmphauthuatMaso, String dtdmphauthuatMa, String dtdmphauthuatTen) {
        this.dtdmphauthuatMaso = dtdmphauthuatMaso;
        this.dtdmphauthuatMa = dtdmphauthuatMa;
        this.dtdmphauthuatTen = dtdmphauthuatTen;
    }

    public Integer getDtdmphauthuatMaso() {
        return dtdmphauthuatMaso;
    }

    public void setDtdmphauthuatMaso(Integer dtdmphauthuatMaso) {
        this.dtdmphauthuatMaso = dtdmphauthuatMaso;
    }

    public String getDtdmphauthuatMa() {
        return dtdmphauthuatMa;
    }

    public void setDtdmphauthuatMa(String dtdmphauthuatMa) {
        this.dtdmphauthuatMa = dtdmphauthuatMa;
    }

    public String getDtdmphauthuatTen() {
        return dtdmphauthuatTen;
    }

    public void setDtdmphauthuatTen(String dtdmphauthuatTen) {
        this.dtdmphauthuatTen = dtdmphauthuatTen;
    }

    public Integer getDtdmphauthuatLoai2() {
        return dtdmphauthuatLoai2;
    }

    public void setDtdmphauthuatLoai2(Integer dtdmphauthuatLoai2) {
        this.dtdmphauthuatLoai2 = dtdmphauthuatLoai2;
    }

    public String getDtdmphauthuatPhanbiet() {
        return dtdmphauthuatPhanbiet;
    }

    public void setDtdmphauthuatPhanbiet(String dtdmphauthuatPhanbiet) {
        this.dtdmphauthuatPhanbiet = dtdmphauthuatPhanbiet;
    }

    public Integer getDtdmphauthuatPhanloai2() {
        return dtdmphauthuatPhanloai2;
    }

    public void setDtdmphauthuatPhanloai2(Integer dtdmphauthuatPhanloai2) {
        this.dtdmphauthuatPhanloai2 = dtdmphauthuatPhanloai2;
    }

    public Short getDtdmphauthuatGopca() {
        return dtdmphauthuatGopca;
    }

    public void setDtdmphauthuatGopca(Short dtdmphauthuatGopca) {
        this.dtdmphauthuatGopca = dtdmphauthuatGopca;
    }

    public Boolean getDtdmphauthuatMo() {
        return dtdmphauthuatMo;
    }

    public void setDtdmphauthuatMo(Boolean dtdmphauthuatMo) {
        this.dtdmphauthuatMo = dtdmphauthuatMo;
    }

    public Boolean getDtdmphauthuatChon1() {
        return dtdmphauthuatChon1;
    }

    public void setDtdmphauthuatChon1(Boolean dtdmphauthuatChon1) {
        this.dtdmphauthuatChon1 = dtdmphauthuatChon1;
    }

    public Boolean getDtdmphauthuatChon2() {
        return dtdmphauthuatChon2;
    }

    public void setDtdmphauthuatChon2(Boolean dtdmphauthuatChon2) {
        this.dtdmphauthuatChon2 = dtdmphauthuatChon2;
    }

    public String getDtdmphauthuatLienhe() {
        return dtdmphauthuatLienhe;
    }

    public void setDtdmphauthuatLienhe(String dtdmphauthuatLienhe) {
        this.dtdmphauthuatLienhe = dtdmphauthuatLienhe;
    }

    public String getDtdmphauthuatMamo() {
        return dtdmphauthuatMamo;
    }

    public void setDtdmphauthuatMamo(String dtdmphauthuatMamo) {
        this.dtdmphauthuatMamo = dtdmphauthuatMamo;
    }

    public Short getDtdmphauthuatOrder() {
        return dtdmphauthuatOrder;
    }

    public void setDtdmphauthuatOrder(Short dtdmphauthuatOrder) {
        this.dtdmphauthuatOrder = dtdmphauthuatOrder;
    }

    public Double getDtdmphauthuatNgaygiocn() {
        return dtdmphauthuatNgaygiocn;
    }

    public void setDtdmphauthuatNgaygiocn(Double dtdmphauthuatNgaygiocn) {
        this.dtdmphauthuatNgaygiocn = dtdmphauthuatNgaygiocn;
    }

  



//    public Collection<HsbaMo> getHsbaMoCollection() {
//        return hsbaMoCollection;
//    }
//
//    public void setHsbaMoCollection(Collection<HsbaMo> hsbaMoCollection) {
//        this.hsbaMoCollection = hsbaMoCollection;
//    }

//    public Collection<HsbaMo> getHsbaMoCollection1() {
//        return hsbaMoCollection1;
//    }
//
//    public void setHsbaMoCollection1(Collection<HsbaMo> hsbaMoCollection1) {
//        this.hsbaMoCollection1 = hsbaMoCollection1;
//    }
    public DtDmLoaiPhauThuat getDtdmphauthuatLoai1(boolean create) {
if(create && dtdmphauthuatLoai1 == null) dtdmphauthuatLoai1 = new DtDmLoaiPhauThuat();
return dtdmphauthuatLoai1;
}
    public DtDmLoaiPhauThuat getDtdmphauthuatLoai1() {
        return dtdmphauthuatLoai1;
    }

    public void setDtdmphauthuatLoai1(DtDmLoaiPhauThuat dtdmphauthuatLoai1) {
        this.dtdmphauthuatLoai1 = dtdmphauthuatLoai1;
    }

    public DtDmPlPhauThuat getDtdmphauthuatPhanloai1(boolean create) {
if(create && dtdmphauthuatPhanloai1 == null) dtdmphauthuatPhanloai1 = new DtDmPlPhauThuat();
return dtdmphauthuatPhanloai1;
}
    public DtDmPlPhauThuat getDtdmphauthuatPhanloai1() {
        return dtdmphauthuatPhanloai1;
    }

    public void setDtdmphauthuatPhanloai1(DtDmPlPhauThuat dtdmphauthuatPhanloai1) {
        this.dtdmphauthuatPhanloai1 = dtdmphauthuatPhanloai1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmphauthuatMaso != null ? dtdmphauthuatMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmPhauThuat)) {
            return false;
        }
        DtDmPhauThuat other = (DtDmPhauThuat) object;
        if ((this.dtdmphauthuatMaso == null && other.dtdmphauthuatMaso != null) || (this.dtdmphauthuatMaso != null && !this.dtdmphauthuatMaso.equals(other.dtdmphauthuatMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmPhauThuat[dtdmphauthuatMaso=" + dtdmphauthuatMaso + "]";
    }

    public Boolean getDtdmphauthuatChon() {
        return dtdmphauthuatChon;
    }

    public void setDtdmphauthuatChon(Boolean dtdmphauthuatChon) {
        this.dtdmphauthuatChon = dtdmphauthuatChon;
    }
}


