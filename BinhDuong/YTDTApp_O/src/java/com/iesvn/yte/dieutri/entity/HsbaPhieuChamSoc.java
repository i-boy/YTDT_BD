/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author thanh
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_PHIEU_CHAM_SOC")
@NamedQueries({@NamedQuery(name = "HsbaPhieuChamSoc.findAll", query = "SELECT h FROM HsbaPhieuChamSoc h"), @NamedQuery(name = "HsbaPhieuChamSoc.findByHsbapcsMa", query = "SELECT h FROM HsbaPhieuChamSoc h WHERE h.hsbapcsMa = :hsbapcsMa"), @NamedQuery(name = "HsbaPhieuChamSoc.findByHsbapcsNgaygio", query = "SELECT h FROM HsbaPhieuChamSoc h WHERE h.hsbapcsNgaygio = :hsbapcsNgaygio"), @NamedQuery(name = "HsbaPhieuChamSoc.findByHsbapcsTheodoidienbien", query = "SELECT h FROM HsbaPhieuChamSoc h WHERE h.hsbapcsTheodoidienbien = :hsbapcsTheodoidienbien"), @NamedQuery(name = "HsbaPhieuChamSoc.findByHsbapcsThuchienylenhchamsoc", query = "SELECT h FROM HsbaPhieuChamSoc h WHERE h.hsbapcsThuchienylenhchamsoc = :hsbapcsThuchienylenhchamsoc")})
public class HsbaPhieuChamSoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NnkdLoaigiayphep")
//    @SequenceGenerator(name = "NnkdLoaigiayphep", sequenceName = "NnkdLoaigiayphep_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBAPCS_MA")
    private Integer hsbapcsMa;
    @Column(name = "HSBAPCS_NGAYGIO")
    @Temporal(TemporalType.DATE)
    private Date hsbapcsNgaygio;
    @Column(name = "HSBAPCS_THEODOIDIENBIEN")
    private String hsbapcsTheodoidienbien;
    @Column(name = "HSBAPCS_THUCHIENYLENHCHAMSOC")
    private String hsbapcsThuchienylenhchamsoc;
    @JoinColumn(name = "HSBAPCS_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbapcsBacsi;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;

    public HsbaPhieuChamSoc() {
    }

    public HsbaPhieuChamSoc(Integer hsbapcsMa) {
        this.hsbapcsMa = hsbapcsMa;
    }

    public Integer getHsbapcsMa() {
        return hsbapcsMa;
    }

    public void setHsbapcsMa(Integer hsbapcsMa) {
        this.hsbapcsMa = hsbapcsMa;
    }

    public Date getHsbapcsNgaygio() {
        return hsbapcsNgaygio;
    }

    public void setHsbapcsNgaygio(Date hsbapcsNgaygio) {
        this.hsbapcsNgaygio = hsbapcsNgaygio;
    }

    public String getHsbapcsTheodoidienbien() {
        return hsbapcsTheodoidienbien;
    }

    public void setHsbapcsTheodoidienbien(String hsbapcsTheodoidienbien) {
        this.hsbapcsTheodoidienbien = hsbapcsTheodoidienbien;
    }

    public String getHsbapcsThuchienylenhchamsoc() {
        return hsbapcsThuchienylenhchamsoc;
    }

    public void setHsbapcsThuchienylenhchamsoc(String hsbapcsThuchienylenhchamsoc) {
        this.hsbapcsThuchienylenhchamsoc = hsbapcsThuchienylenhchamsoc;
    }

    public DtDmNhanVien getHsbapcsBacsi() {
        return hsbapcsBacsi;
    }

    public void setHsbapcsBacsi(DtDmNhanVien hsbapcsBacsi) {
        this.hsbapcsBacsi = hsbapcsBacsi;
    }

    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbapcsMa != null ? hsbapcsMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaPhieuChamSoc)) {
            return false;
        }
        HsbaPhieuChamSoc other = (HsbaPhieuChamSoc) object;
        if ((this.hsbapcsMa == null && other.hsbapcsMa != null) || (this.hsbapcsMa != null && !this.hsbapcsMa.equals(other.hsbapcsMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ddxdddd.HsbaPhieuChamSoc[hsbapcsMa=" + hsbapcsMa + "]";
    }

}
