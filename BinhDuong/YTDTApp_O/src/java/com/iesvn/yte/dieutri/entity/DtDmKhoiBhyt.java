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
@Table(name = "DT_DM_KHOI_BHYT")
@NamedQueries({})
public class DtDmKhoiBhyt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_KHOI_BHYT")
    @SequenceGenerator(name = "DT_DM_KHOI_BHYT", sequenceName = "DT_DM_KHOI_BHYT_DTDMKHOIBHYT_M", allocationSize = 1)
    @Column(name = "DTDMKHOIBHYT_MASO", nullable = false)
    private Integer dtdmkhoibhytMaso;
    @Column(name = "DTDMKHOIBHYT_MA", nullable = false)
    private String dtdmkhoibhytMa;
    @Column(name = "DTDMKHOIBHYT_TEN", nullable = false)
    private String dtdmkhoibhytTen;
    @Column(name = "DTDMKHOIBHYT_TYLEBHYT1")
    private Short dtdmkhoibhytTylebhyt1;
    @Column(name = "DTDMKHOIBHYT_TYLEBHYT2")
    private Short dtdmkhoibhytTylebhyt2;
    @Column(name = "DTDMKHOIBHYT_TYLEKTC")
    private Short dtdmkhoibhytTylektc;
    @Column(name = "DTDMKHOIBHYT_GOMYC")
    private Short dtdmkhoibhytGomyc;
    @Column(name = "DTDMKHOIBHYT_GHICHU")
    private String dtdmkhoibhytGhichu;
    @Column(name = "DTDMKHOIBHYT_NGAYGIOCN")
    private Double dtdmkhoibhytNgaygiocn;
    @Column(name = "DTDMKHOIBHYT_CHON")
    private Boolean dtdmkhoibhytChon;
    @Column(name = "DTDMKHOIBHYT_GHTYLE")
    private Double dtdmkhoibhytGioiHanTyLe;
    @Column(name = "DTDMKHOIBHYT_MINKTC")
    private Double dtdmkhoibhytMinKTC;
    @Column(name = "DTDMKHOIBHYT_TLMINKTC")
    private Double dtdmkhoibhytTLMinKTC;
    @Column(name = "DTDMKHOIBHYT_MAXKTC  ")
    private Double dtdmkhoibhytMaxKTC;
    @Column(name = "DTDMKHOIBHYT_TLMAXKTC")
    private Double dtdmkhoibhytTLMaxKTC;
    
    @Column(name = "DTDMKHOIBHYT_TYLEBHYT1_NOI_TRU")
    private Double dtdmkhoibhytTLBHYT1_NoiTru;
    @Column(name = "DTDMKHOIBHYT_TYLEBHYT2_NOI_TRU")
    private Double dtdmkhoibhytTLBHYT2_NoiTru;
    @Column(name = "DTDMKHOIBHYT_GHTYLE_NOI_TRU")
    private Double dtdmkhoibhytGHTyLeNoiTru;
    
    
//    @OneToMany(mappedBy = "hsbabhytKhoibh")
//    private Collection<HsbaBhyt> hsbaBhytCollection;
//    @OneToMany(mappedBy = "hsbabhytKhoibh1")
//    private Collection<HsbaBhyt> hsbaBhytCollection1;
//    @OneToMany(mappedBy = "khoibhytMa")
//    private Collection<TiepDon> tiepDonCollection;
    @JoinColumn(name = "DTDMKHOIBHYT_LOAI", referencedColumnName = "DTDMLOAIBHYT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiBhyt dtdmkhoibhytLoai;
    @JoinColumn(name = "DTDMKHOIBHYT_PHANLOAI", referencedColumnName = "DTDMPHLOAIBHYT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmPlBhyt dtdmkhoibhytPhanloai;

    @JoinColumn(name = "DTDMNHOMBHYT2_MASO", referencedColumnName = "DTDMNHOMBHYT2_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhomBHYT2 dtDmNhomBHYT2;

    public DtDmKhoiBhyt() {
    }

    public DtDmKhoiBhyt(Integer dtdmkhoibhytMaso) {
        this.dtdmkhoibhytMaso = dtdmkhoibhytMaso;
    }

    public DtDmKhoiBhyt(Integer dtdmkhoibhytMaso, String dtdmkhoibhytMa, String dtdmkhoibhytTen) {
        this.dtdmkhoibhytMaso = dtdmkhoibhytMaso;
        this.dtdmkhoibhytMa = dtdmkhoibhytMa;
        this.dtdmkhoibhytTen = dtdmkhoibhytTen;
    }

    public Integer getDtdmkhoibhytMaso() {
        return dtdmkhoibhytMaso;
    }

    public void setDtdmkhoibhytMaso(Integer dtdmkhoibhytMaso) {
        this.dtdmkhoibhytMaso = dtdmkhoibhytMaso;
    }

    public String getDtdmkhoibhytMa() {
        return dtdmkhoibhytMa;
    }

    public void setDtdmkhoibhytMa(String dtdmkhoibhytMa) {
        this.dtdmkhoibhytMa = dtdmkhoibhytMa;
    }

    public String getDtdmkhoibhytTen() {
        return dtdmkhoibhytTen;
    }

    public void setDtdmkhoibhytTen(String dtdmkhoibhytTen) {
        this.dtdmkhoibhytTen = dtdmkhoibhytTen;
    }

    public Short getDtdmkhoibhytTylebhyt1() {
        return dtdmkhoibhytTylebhyt1;
    }

    public void setDtdmkhoibhytTylebhyt1(Short dtdmkhoibhytTylebhyt1) {
        this.dtdmkhoibhytTylebhyt1 = dtdmkhoibhytTylebhyt1;
    }

    public Short getDtdmkhoibhytTylebhyt2() {
        return dtdmkhoibhytTylebhyt2;
    }

    public void setDtdmkhoibhytTylebhyt2(Short dtdmkhoibhytTylebhyt2) {
        this.dtdmkhoibhytTylebhyt2 = dtdmkhoibhytTylebhyt2;
    }

    public Short getDtdmkhoibhytTylektc() {
        return dtdmkhoibhytTylektc;
    }

    public void setDtdmkhoibhytTylektc(Short dtdmkhoibhytTylektc) {
        this.dtdmkhoibhytTylektc = dtdmkhoibhytTylektc;
    }

    public Short getDtdmkhoibhytGomyc() {
        return dtdmkhoibhytGomyc;
    }

    public void setDtdmkhoibhytGomyc(Short dtdmkhoibhytGomyc) {
        this.dtdmkhoibhytGomyc = dtdmkhoibhytGomyc;
    }

    public String getDtdmkhoibhytGhichu() {
        return dtdmkhoibhytGhichu;
    }

    public void setDtdmkhoibhytGhichu(String dtdmkhoibhytGhichu) {
        this.dtdmkhoibhytGhichu = dtdmkhoibhytGhichu;
    }

    public Double getDtdmkhoibhytNgaygiocn() {
        return dtdmkhoibhytNgaygiocn;
    }

    public void setDtdmkhoibhytNgaygiocn(Double dtdmkhoibhytNgaygiocn) {
        this.dtdmkhoibhytNgaygiocn = dtdmkhoibhytNgaygiocn;
    }

    public Boolean getDtdmkhoibhytChon() {
        return dtdmkhoibhytChon;
    }

    public void setDtdmkhoibhytChon(Boolean dtdmkhoibhytChon) {
        this.dtdmkhoibhytChon = dtdmkhoibhytChon;
    }

//    public Collection<HsbaBhyt> getHsbaBhytCollection() {
//        return hsbaBhytCollection;
//    }
//
//    public void setHsbaBhytCollection(Collection<HsbaBhyt> hsbaBhytCollection) {
//        this.hsbaBhytCollection = hsbaBhytCollection;
//    }

//    public Collection<HsbaBhyt> getHsbaBhytCollection1() {
//        return hsbaBhytCollection1;
//    }
//
//    public void setHsbaBhytCollection1(Collection<HsbaBhyt> hsbaBhytCollection1) {
//        this.hsbaBhytCollection1 = hsbaBhytCollection1;
//    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }
    public DtDmLoaiBhyt getDtdmkhoibhytLoai(boolean create) {
        if (create && dtdmkhoibhytLoai == null) {
            dtdmkhoibhytLoai = new DtDmLoaiBhyt();
        }
        return dtdmkhoibhytLoai;
    }

    public DtDmLoaiBhyt getDtdmkhoibhytLoai() {
        return dtdmkhoibhytLoai;
    }

    public void setDtdmkhoibhytLoai(DtDmLoaiBhyt dtdmkhoibhytLoai) {
        this.dtdmkhoibhytLoai = dtdmkhoibhytLoai;
    }

    public DtDmPlBhyt getDtdmkhoibhytPhanloai(boolean create) {
        if (create && dtdmkhoibhytPhanloai == null) {
            dtdmkhoibhytPhanloai = new DtDmPlBhyt();
        }
        return dtdmkhoibhytPhanloai;
    }

    public DtDmPlBhyt getDtdmkhoibhytPhanloai() {
        return dtdmkhoibhytPhanloai;
    }

    public void setDtdmkhoibhytPhanloai(DtDmPlBhyt dtdmkhoibhytPhanloai) {
        this.dtdmkhoibhytPhanloai = dtdmkhoibhytPhanloai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmkhoibhytMaso != null ? dtdmkhoibhytMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmKhoiBhyt)) {
            return false;
        }
        DtDmKhoiBhyt other = (DtDmKhoiBhyt) object;
        if ((this.dtdmkhoibhytMaso == null && other.dtdmkhoibhytMaso != null) || (this.dtdmkhoibhytMaso != null && !this.dtdmkhoibhytMaso.equals(other.dtdmkhoibhytMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmKhoiBhyt[dtdmkhoibhytMaso=" + dtdmkhoibhytMaso + "]";
    }

    public Double getDtdmkhoibhytGioiHanTyLe() {
        return dtdmkhoibhytGioiHanTyLe;
    }

    public void setDtdmkhoibhytGioiHanTyLe(Double dtdmkhoibhytGioiHanTyLe) {
        this.dtdmkhoibhytGioiHanTyLe = dtdmkhoibhytGioiHanTyLe;
    }

    public Double getDtdmkhoibhytMinKTC() {
        return dtdmkhoibhytMinKTC;
    }

    public void setDtdmkhoibhytMinKTC(Double dtdmkhoibhytMinKTC) {
        this.dtdmkhoibhytMinKTC = dtdmkhoibhytMinKTC;
    }

    public Double getDtdmkhoibhytTLMinKTC() {
        return dtdmkhoibhytTLMinKTC;
    }

    public void setDtdmkhoibhytTLMinKTC(Double dtdmkhoibhytTLMinKTC) {
        this.dtdmkhoibhytTLMinKTC = dtdmkhoibhytTLMinKTC;
    }

    public Double getDtdmkhoibhytMaxKTC() {
        return dtdmkhoibhytMaxKTC;
    }

    public void setDtdmkhoibhytMaxKTC(Double dtdmkhoibhytMaxKTC) {
        this.dtdmkhoibhytMaxKTC = dtdmkhoibhytMaxKTC;
    }

    public Double getDtdmkhoibhytTLMaxKTC() {
        return dtdmkhoibhytTLMaxKTC;
    }

    public void setDtdmkhoibhytTLMaxKTC(Double dtdmkhoibhytTLMaxKTC) {
        this.dtdmkhoibhytTLMaxKTC = dtdmkhoibhytTLMaxKTC;
    }

    public Double getDtdmkhoibhytTLBHYT1_NoiTru() {
        return dtdmkhoibhytTLBHYT1_NoiTru;
    }

    public void setDtdmkhoibhytTLBHYT1_NoiTru(Double dtdmkhoibhytTLBHYT1_NoiTru) {
        this.dtdmkhoibhytTLBHYT1_NoiTru = dtdmkhoibhytTLBHYT1_NoiTru;
    }

    public Double getDtdmkhoibhytTLBHYT2_NoiTru() {
        return dtdmkhoibhytTLBHYT2_NoiTru;
    }

    public void setDtdmkhoibhytTLBHYT2_NoiTru(Double dtdmkhoibhytTLBHYT2_NoiTru) {
        this.dtdmkhoibhytTLBHYT2_NoiTru = dtdmkhoibhytTLBHYT2_NoiTru;
    }

    public Double getDtdmkhoibhytGHTyLeNoiTru() {
        return dtdmkhoibhytGHTyLeNoiTru;
    }

    public void setDtdmkhoibhytGHTyLeNoiTru(Double dtdmkhoibhytGHTyLeNoiTru) {
        this.dtdmkhoibhytGHTyLeNoiTru = dtdmkhoibhytGHTyLeNoiTru;
    }

    /**
     * @return the dtDmNhomBHYT2
     */
    public DtDmNhomBHYT2 getDtDmNhomBHYT2() {
        return dtDmNhomBHYT2;
    }

    /**
     * @param dtDmNhomBHYT2 the dtDmNhomBHYT2 to set
     */
    public void setDtDmNhomBHYT2(DtDmNhomBHYT2 dtDmNhomBHYT2) {
        this.dtDmNhomBHYT2 = dtDmNhomBHYT2;
    }
}


