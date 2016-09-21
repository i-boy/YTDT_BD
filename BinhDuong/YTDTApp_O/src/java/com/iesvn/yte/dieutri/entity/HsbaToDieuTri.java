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
@Table(name = "HSBA_TO_DIEU_TRI")
@NamedQueries({@NamedQuery(name = "HsbaToDieuTri.findAll", query = "SELECT h FROM HsbaToDieuTri h"), @NamedQuery(name = "HsbaToDieuTri.findByHsbatdtMa", query = "SELECT h FROM HsbaToDieuTri h WHERE h.hsbatdtMa = :hsbatdtMa"), @NamedQuery(name = "HsbaToDieuTri.findByHsbatdtNgaygio", query = "SELECT h FROM HsbaToDieuTri h WHERE h.hsbatdtNgaygio = :hsbatdtNgaygio"), @NamedQuery(name = "HsbaToDieuTri.findByHsbatdtTheodoidienbien", query = "SELECT h FROM HsbaToDieuTri h WHERE h.hsbatdtTheodoidienbien = :hsbatdtTheodoidienbien"), @NamedQuery(name = "HsbaToDieuTri.findByHsbatdtThuchienylenhchamsoc", query = "SELECT h FROM HsbaToDieuTri h WHERE h.hsbatdtThuchienylenhchamsoc = :hsbatdtThuchienylenhchamsoc")})
public class HsbaToDieuTri implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NnkdLoaigiayphep")
//    @SequenceGenerator(name = "NnkdLoaigiayphep", sequenceName = "NnkdLoaigiayphep_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBATDT_MA")
    private Integer hsbatdtMa;
    @Column(name = "HSBATDT_NGAYGIO")
    @Temporal(TemporalType.DATE)
    private Date hsbatdtNgaygio;
    @Column(name = "HSBATDT_THEODOIDIENBIEN")
    private String hsbatdtTheodoidienbien;
    @Column(name = "HSBATDT_THUCHIENYLENHCHAMSOC")
    private String hsbatdtThuchienylenhchamsoc;
    @JoinColumn(name = "HSBATDT_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbatdtBacsi;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;

    public HsbaToDieuTri() {
    }

    public HsbaToDieuTri(Integer hsbatdtMa) {
        this.hsbatdtMa = hsbatdtMa;
    }

    public Integer getHsbatdtMa() {
        return hsbatdtMa;
    }

    public void setHsbatdtMa(Integer hsbatdtMa) {
        this.hsbatdtMa = hsbatdtMa;
    }

    public Date getHsbatdtNgaygio() {
        return hsbatdtNgaygio;
    }

    public void setHsbatdtNgaygio(Date hsbatdtNgaygio) {
        this.hsbatdtNgaygio = hsbatdtNgaygio;
    }

    public String getHsbatdtTheodoidienbien() {
        return hsbatdtTheodoidienbien;
    }

    public void setHsbatdtTheodoidienbien(String hsbatdtTheodoidienbien) {
        this.hsbatdtTheodoidienbien = hsbatdtTheodoidienbien;
    }

    public String getHsbatdtThuchienylenhchamsoc() {
        return hsbatdtThuchienylenhchamsoc;
    }

    public void setHsbatdtThuchienylenhchamsoc(String hsbatdtThuchienylenhchamsoc) {
        this.hsbatdtThuchienylenhchamsoc = hsbatdtThuchienylenhchamsoc;
    }

    public DtDmNhanVien getHsbatdtBacsi() {
        return hsbatdtBacsi;
    }

    public void setHsbatdtBacsi(DtDmNhanVien hsbatdtBacsi) {
        this.hsbatdtBacsi = hsbatdtBacsi;
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
        hash += (hsbatdtMa != null ? hsbatdtMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaToDieuTri)) {
            return false;
        }
        HsbaToDieuTri other = (HsbaToDieuTri) object;
        if ((this.hsbatdtMa == null && other.hsbatdtMa != null) || (this.hsbatdtMa != null && !this.hsbatdtMa.equals(other.hsbatdtMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ddxdddd.HsbaToDieuTri[hsbatdtMa=" + hsbatdtMa + "]";
    }

}
