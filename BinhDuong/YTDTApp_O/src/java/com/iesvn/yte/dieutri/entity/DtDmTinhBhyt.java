/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_TINH_BHYT")
@NamedQueries({})
public class DtDmTinhBhyt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_TINH_BHYT")
    @SequenceGenerator(name = "DT_DM_TINH_BHYT", sequenceName = "DT_DM_TINH_BHYT_DTDMTINHBHYT_M", allocationSize = 1)
    @Column(name = "DTDMTINHBHYT_MASO", nullable = false)
    private Integer dtdmtinhbhytMaso;
    @Column(name = "DTDMTINHBHYT_MA", nullable = false)
    private String dtdmtinhbhytMa;
    @Column(name = "DTDMTINHBHYT_TEN", nullable = false)
    private String dtdmtinhbhytTen;
    @Column(name = "DTDMTINHBHYT_NGAYTINH")
    @Temporal(TemporalType.DATE)
    private Date dtdmtinhbhytNgaytinh;
    @Column(name = "DTDMTINHBHYT_TEN1")
    private String dtdmtinhbhytTen1;
    @Column(name = "DTDMTINHBHYT_NGAYTINH1")
    @Temporal(TemporalType.DATE)
    private Date dtdmtinhbhytNgaytinh1;
    @Column(name = "DTDMTINHBHYT_TEN2")
    private String dtdmtinhbhytTen2;
    @Column(name = "DTDMTINHBHYT_NGAYTINH2")
    @Temporal(TemporalType.DATE)
    private Date dtdmtinhbhytNgaytinh2;
    @Column(name = "DTDMTINHBHYT_TEN3")
    private String dtdmtinhbhytTen3;
    @Column(name = "DTDMTINHBHYT_NGAYTINH3")
    @Temporal(TemporalType.DATE)
    private Date dtdmtinhbhytNgaytinh3;
    @Column(name = "DTDMTINHBHYT_NEW")
    private Boolean dtdmtinhbhytNew;
    @Column(name = "DTDMTINHBHYT_NOITINH")
    private String dtdmtinhbhytNoitinh;
    @Column(name = "DTDMTINHBHYT_NGAYGIOCN")
    private Double dtdmtinhbhytNgaygiocn;
    @Column(name = "DTDMTINHBHYT_CHON")
    private Boolean dtdmtinhbhytChon;
    
    @Column(name = "DTDMTINHBHYT_DEFA")
    private Boolean dtdmtinhbhytDefa;
    
    
//    @OneToMany(mappedBy = "dtdmtinhbhytMaso")
//    private Collection<DtDmKcbBhyt> dtDmKcbBhytCollection;
//    @OneToMany(mappedBy = "dtdmtinhbhytMaso1")
//    private Collection<DtDmKcbBhyt> dtDmKcbBhytCollection1;
//    @OneToMany(mappedBy = "hsbabhytTinhbh")
//    private Collection<HsbaBhyt> hsbaBhytCollection;
//    @OneToMany(mappedBy = "hsbabhytTinhbh1")
//    private Collection<HsbaBhyt> hsbaBhytCollection1;
//    @OneToMany(mappedBy = "tinhbhytMa")
//    private Collection<TiepDon> tiepDonCollection;
    public DtDmTinhBhyt() {
    }

    public DtDmTinhBhyt(Integer dtdmtinhbhytMaso) {
        this.dtdmtinhbhytMaso = dtdmtinhbhytMaso;
    }

    public DtDmTinhBhyt(Integer dtdmtinhbhytMaso, String dtdmtinhbhytMa, String dtdmtinhbhytTen) {
        this.dtdmtinhbhytMaso = dtdmtinhbhytMaso;
        this.dtdmtinhbhytMa = dtdmtinhbhytMa;
        this.dtdmtinhbhytTen = dtdmtinhbhytTen;
    }

    public Integer getDtdmtinhbhytMaso() {
        return dtdmtinhbhytMaso;
    }

    public void setDtdmtinhbhytMaso(Integer dtdmtinhbhytMaso) {
        this.dtdmtinhbhytMaso = dtdmtinhbhytMaso;
    }

    public String getDtdmtinhbhytMa() {
        return dtdmtinhbhytMa;
    }

    public void setDtdmtinhbhytMa(String dtdmtinhbhytMa) {
        this.dtdmtinhbhytMa = dtdmtinhbhytMa;
    }

    public String getDtdmtinhbhytTen() {
        return dtdmtinhbhytTen;
    }

    public void setDtdmtinhbhytTen(String dtdmtinhbhytTen) {
        this.dtdmtinhbhytTen = dtdmtinhbhytTen;
    }

    public Date getDtdmtinhbhytNgaytinh() {
        return dtdmtinhbhytNgaytinh;
    }

    public void setDtdmtinhbhytNgaytinh(Date dtdmtinhbhytNgaytinh) {
        this.dtdmtinhbhytNgaytinh = dtdmtinhbhytNgaytinh;
    }

    public String getDtdmtinhbhytTen1() {
        return dtdmtinhbhytTen1;
    }

    public void setDtdmtinhbhytTen1(String dtdmtinhbhytTen1) {
        this.dtdmtinhbhytTen1 = dtdmtinhbhytTen1;
    }

    public Date getDtdmtinhbhytNgaytinh1() {
        return dtdmtinhbhytNgaytinh1;
    }

    public void setDtdmtinhbhytNgaytinh1(Date dtdmtinhbhytNgaytinh1) {
        this.dtdmtinhbhytNgaytinh1 = dtdmtinhbhytNgaytinh1;
    }

    public String getDtdmtinhbhytTen2() {
        return dtdmtinhbhytTen2;
    }

    public void setDtdmtinhbhytTen2(String dtdmtinhbhytTen2) {
        this.dtdmtinhbhytTen2 = dtdmtinhbhytTen2;
    }

    public Date getDtdmtinhbhytNgaytinh2() {
        return dtdmtinhbhytNgaytinh2;
    }

    public void setDtdmtinhbhytNgaytinh2(Date dtdmtinhbhytNgaytinh2) {
        this.dtdmtinhbhytNgaytinh2 = dtdmtinhbhytNgaytinh2;
    }

    public String getDtdmtinhbhytTen3() {
        return dtdmtinhbhytTen3;
    }

    public void setDtdmtinhbhytTen3(String dtdmtinhbhytTen3) {
        this.dtdmtinhbhytTen3 = dtdmtinhbhytTen3;
    }

    public Date getDtdmtinhbhytNgaytinh3() {
        return dtdmtinhbhytNgaytinh3;
    }

    public void setDtdmtinhbhytNgaytinh3(Date dtdmtinhbhytNgaytinh3) {
        this.dtdmtinhbhytNgaytinh3 = dtdmtinhbhytNgaytinh3;
    }

    public Boolean getDtdmtinhbhytNew() {
        return dtdmtinhbhytNew;
    }

    public void setDtdmtinhbhytNew(Boolean dtdmtinhbhytNew) {
        this.dtdmtinhbhytNew = dtdmtinhbhytNew;
    }

    public String getDtdmtinhbhytNoitinh() {
        return dtdmtinhbhytNoitinh;
    }

    public void setDtdmtinhbhytNoitinh(String dtdmtinhbhytNoitinh) {
        this.dtdmtinhbhytNoitinh = dtdmtinhbhytNoitinh;
    }

    public Double getDtdmtinhbhytNgaygiocn() {
        return dtdmtinhbhytNgaygiocn;
    }

    public void setDtdmtinhbhytNgaygiocn(Double dtdmtinhbhytNgaygiocn) {
        this.dtdmtinhbhytNgaygiocn = dtdmtinhbhytNgaygiocn;
    }

    public Boolean getDtdmtinhbhytChon() {
        return dtdmtinhbhytChon;
    }

    public void setDtdmtinhbhytChon(Boolean dtdmtinhbhytChon) {
        this.dtdmtinhbhytChon = dtdmtinhbhytChon;
    }

//    public Collection<DtDmKcbBhyt> getDtDmKcbBhytCollection() {
//        return dtDmKcbBhytCollection;
//    }
//
//    public void setDtDmKcbBhytCollection(Collection<DtDmKcbBhyt> dtDmKcbBhytCollection) {
//        this.dtDmKcbBhytCollection = dtDmKcbBhytCollection;
//    }

//    public Collection<DtDmKcbBhyt> getDtDmKcbBhytCollection1() {
//        return dtDmKcbBhytCollection1;
//    }
//
//    public void setDtDmKcbBhytCollection1(Collection<DtDmKcbBhyt> dtDmKcbBhytCollection1) {
//        this.dtDmKcbBhytCollection1 = dtDmKcbBhytCollection1;
//    }

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
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmtinhbhytMaso != null ? dtdmtinhbhytMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmTinhBhyt)) {
            return false;
        }
        DtDmTinhBhyt other = (DtDmTinhBhyt) object;
        if ((this.dtdmtinhbhytMaso == null && other.dtdmtinhbhytMaso != null) || (this.dtdmtinhbhytMaso != null && !this.dtdmtinhbhytMaso.equals(other.dtdmtinhbhytMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmTinhBhyt[dtdmtinhbhytMaso=" + dtdmtinhbhytMaso + "]";
    }

    public Boolean getDtdmtinhbhytDefa() {
        return dtdmtinhbhytDefa;
    }

    public void setDtdmtinhbhytDefa(Boolean dtdmtinhbhytDefa) {
        this.dtdmtinhbhytDefa = dtdmtinhbhytDefa;
    }
}


