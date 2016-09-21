/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_MQL_BHYT")
@NamedQueries({@NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytMaso", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytMaso = :dtdmmqlbhytMaso"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytMa", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytMa = :dtdmmqlbhytMa"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytTen", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytTen = :dtdmmqlbhytTen"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytTylebhyt1", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytTylebhyt1 = :dtdmmqlbhytTylebhyt1"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytTylebhyt2", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytTylebhyt2 = :dtdmmqlbhytTylebhyt2"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytGhtyle", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytGhtyle = :dtdmmqlbhytGhtyle"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytTylebhyt1NoiTru", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytTylebhyt1NoiTru = :dtdmmqlbhytTylebhyt1NoiTru"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytTylebhyt2NoiTru", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytTylebhyt2NoiTru = :dtdmmqlbhytTylebhyt2NoiTru"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytGhtyleNoiTru", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytGhtyleNoiTru = :dtdmmqlbhytGhtyleNoiTru"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytMinktc", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytMinktc = :dtdmmqlbhytMinktc"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytMaxktc", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytMaxktc = :dtdmmqlbhytMaxktc"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytTylektc", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytTylektc = :dtdmmqlbhytTylektc"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytTlminktc", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytTlminktc = :dtdmmqlbhytTlminktc"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytTlmaxktc", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytTlmaxktc = :dtdmmqlbhytTlmaxktc"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytVanchuyen", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytVanchuyen = :dtdmmqlbhytVanchuyen"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytNgaygiocn", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytNgaygiocn = :dtdmmqlbhytNgaygiocn"), @NamedQuery(name = "DtDmMqlBhyt.findByDtdmmqlbhytChon", query = "SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytChon = :dtdmmqlbhytChon")})
public class DtDmMqlBhyt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_MQL_BHYT")
    @SequenceGenerator(name = "DT_DM_MQL_BHYT", sequenceName = "DT_DM_MQL_BHYT_DTDMMQLBHYT_MAS", allocationSize = 1)
    @Column(name = "DTDMMQLBHYT_MASO", nullable = false)
    private Integer dtdmmqlbhytMaso;
    @Column(name = "DTDMMQLBHYT_MA", nullable = false)
    private String dtdmmqlbhytMa;
    @Column(name = "DTDMMQLBHYT_TEN", nullable = false)
    private String dtdmmqlbhytTen;
    @Column(name = "DTDMMQLBHYT_TYLEBHYT1")
    private Short dtdmmqlbhytTylebhyt1;
    @Column(name = "DTDMMQLBHYT_TYLEBHYT2")
    private Short dtdmmqlbhytTylebhyt2;
    @Column(name = "DTDMMQLBHYT_GHTYLE")
    private Double dtdmmqlbhytGhtyle;
    @Column(name = "DTDMMQLBHYT_TYLEBHYT1_NOI_TRU")
    private Double dtdmmqlbhytTylebhyt1NoiTru;
    @Column(name = "DTDMMQLBHYT_TYLEBHYT2_NOI_TRU")
    private Double dtdmmqlbhytTylebhyt2NoiTru;
    @Column(name = "DTDMMQLBHYT_GHTYLE_NOI_TRU")
    private Double dtdmmqlbhytGhtyleNoiTru;
    @Column(name = "DTDMMQLBHYT_MINKTC")
    private Double dtdmmqlbhytMinktc;
    @Column(name = "DTDMMQLBHYT_MAXKTC")
    private Double dtdmmqlbhytMaxktc;
    @Column(name = "DTDMMQLBHYT_TYLEKTC")
    private Short dtdmmqlbhytTylektc;
    @Column(name = "DTDMMQLBHYT_TLMINKTC")
    private Double dtdmmqlbhytTlminktc;
    @Column(name = "DTDMMQLBHYT_TLMAXKTC")
    private Double dtdmmqlbhytTlmaxktc;
    @Column(name = "DTDMMQLBHYT_VANCHUYEN")
    private Boolean dtdmmqlbhytVanchuyen;
    @Column(name = "DTDMMQLBHYT_NGAYGIOCN")
    private Double dtdmmqlbhytNgaygiocn;
    @Column(name = "DTDMMQLBHYT_CHON")
    private Boolean dtdmmqlbhytChon;

    public DtDmMqlBhyt() {
    }

    public DtDmMqlBhyt(Integer dtdmmqlbhytMaso) {
        this.dtdmmqlbhytMaso = dtdmmqlbhytMaso;
    }

    public DtDmMqlBhyt(Integer dtdmmqlbhytMaso, String dtdmmqlbhytMa, String dtdmmqlbhytTen) {
        this.dtdmmqlbhytMaso = dtdmmqlbhytMaso;
        this.dtdmmqlbhytMa = dtdmmqlbhytMa;
        this.dtdmmqlbhytTen = dtdmmqlbhytTen;
    }

    public Integer getDtdmmqlbhytMaso() {
        return dtdmmqlbhytMaso;
    }

    public void setDtdmmqlbhytMaso(Integer dtdmmqlbhytMaso) {
        this.dtdmmqlbhytMaso = dtdmmqlbhytMaso;
    }

    public String getDtdmmqlbhytMa() {
        return dtdmmqlbhytMa;
    }

    public void setDtdmmqlbhytMa(String dtdmmqlbhytMa) {
        this.dtdmmqlbhytMa = dtdmmqlbhytMa;
    }

    public String getDtdmmqlbhytTen() {
        return dtdmmqlbhytTen;
    }

    public void setDtdmmqlbhytTen(String dtdmmqlbhytTen) {
        this.dtdmmqlbhytTen = dtdmmqlbhytTen;
    }

    public Short getDtdmmqlbhytTylebhyt1() {
        return dtdmmqlbhytTylebhyt1;
    }

    public void setDtdmmqlbhytTylebhyt1(Short dtdmmqlbhytTylebhyt1) {
        this.dtdmmqlbhytTylebhyt1 = dtdmmqlbhytTylebhyt1;
    }

    public Short getDtdmmqlbhytTylebhyt2() {
        return dtdmmqlbhytTylebhyt2;
    }

    public void setDtdmmqlbhytTylebhyt2(Short dtdmmqlbhytTylebhyt2) {
        this.dtdmmqlbhytTylebhyt2 = dtdmmqlbhytTylebhyt2;
    }

    public Double getDtdmmqlbhytGhtyle() {
        return dtdmmqlbhytGhtyle;
    }

    public void setDtdmmqlbhytGhtyle(Double dtdmmqlbhytGhtyle) {
        this.dtdmmqlbhytGhtyle = dtdmmqlbhytGhtyle;
    }

    public Double getDtdmmqlbhytTylebhyt1NoiTru() {
        return dtdmmqlbhytTylebhyt1NoiTru;
    }

    public void setDtdmmqlbhytTylebhyt1NoiTru(Double dtdmmqlbhytTylebhyt1NoiTru) {
        this.dtdmmqlbhytTylebhyt1NoiTru = dtdmmqlbhytTylebhyt1NoiTru;
    }

    public Double getDtdmmqlbhytTylebhyt2NoiTru() {
        return dtdmmqlbhytTylebhyt2NoiTru;
    }

    public void setDtdmmqlbhytTylebhyt2NoiTru(Double dtdmmqlbhytTylebhyt2NoiTru) {
        this.dtdmmqlbhytTylebhyt2NoiTru = dtdmmqlbhytTylebhyt2NoiTru;
    }

    public Double getDtdmmqlbhytGhtyleNoiTru() {
        return dtdmmqlbhytGhtyleNoiTru;
    }

    public void setDtdmmqlbhytGhtyleNoiTru(Double dtdmmqlbhytGhtyleNoiTru) {
        this.dtdmmqlbhytGhtyleNoiTru = dtdmmqlbhytGhtyleNoiTru;
    }

    public Double getDtdmmqlbhytMinktc() {
        return dtdmmqlbhytMinktc;
    }

    public void setDtdmmqlbhytMinktc(Double dtdmmqlbhytMinktc) {
        this.dtdmmqlbhytMinktc = dtdmmqlbhytMinktc;
    }

    public Double getDtdmmqlbhytMaxktc() {
        return dtdmmqlbhytMaxktc;
    }

    public void setDtdmmqlbhytMaxktc(Double dtdmmqlbhytMaxktc) {
        this.dtdmmqlbhytMaxktc = dtdmmqlbhytMaxktc;
    }

    public Short getDtdmmqlbhytTylektc() {
        return dtdmmqlbhytTylektc;
    }

    public void setDtdmmqlbhytTylektc(Short dtdmmqlbhytTylektc) {
        this.dtdmmqlbhytTylektc = dtdmmqlbhytTylektc;
    }

    public Double getDtdmmqlbhytTlminktc() {
        return dtdmmqlbhytTlminktc;
    }

    public void setDtdmmqlbhytTlminktc(Double dtdmmqlbhytTlminktc) {
        this.dtdmmqlbhytTlminktc = dtdmmqlbhytTlminktc;
    }

    public Double getDtdmmqlbhytTlmaxktc() {
        return dtdmmqlbhytTlmaxktc;
    }

    public void setDtdmmqlbhytTlmaxktc(Double dtdmmqlbhytTlmaxktc) {
        this.dtdmmqlbhytTlmaxktc = dtdmmqlbhytTlmaxktc;
    }

    public Boolean getDtdmmqlbhytVanchuyen() {
        return dtdmmqlbhytVanchuyen;
    }

    public void setDtdmmqlbhytVanchuyen(Boolean dtdmmqlbhytVanchuyen) {
        this.dtdmmqlbhytVanchuyen = dtdmmqlbhytVanchuyen;
    }

    public Double getDtdmmqlbhytNgaygiocn() {
        return dtdmmqlbhytNgaygiocn;
    }

    public void setDtdmmqlbhytNgaygiocn(Double dtdmmqlbhytNgaygiocn) {
        this.dtdmmqlbhytNgaygiocn = dtdmmqlbhytNgaygiocn;
    }

    public Boolean getDtdmmqlbhytChon() {
        return dtdmmqlbhytChon;
    }

    public void setDtdmmqlbhytChon(Boolean dtdmmqlbhytChon) {
        this.dtdmmqlbhytChon = dtdmmqlbhytChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmmqlbhytMaso != null ? dtdmmqlbhytMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmMqlBhyt)) {
            return false;
        }
        DtDmMqlBhyt other = (DtDmMqlBhyt) object;
        if ((this.dtdmmqlbhytMaso == null && other.dtdmmqlbhytMaso != null) || (this.dtdmmqlbhytMaso != null && !this.dtdmmqlbhytMaso.equals(other.dtdmmqlbhytMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.entity.DtDmMqlBhyt[dtdmmqlbhytMaso=" + dtdmmqlbhytMaso + "]";
    }

}
