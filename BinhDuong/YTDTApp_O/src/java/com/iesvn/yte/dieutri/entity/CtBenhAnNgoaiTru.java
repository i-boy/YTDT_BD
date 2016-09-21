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
 * @author halylam
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CT_BENH_AN_NGOAI_TRU")
@NamedQueries({@NamedQuery(name = "CtBenhAnNgoaiTru.findAll", query = "SELECT c FROM CtBenhAnNgoaiTru c"), @NamedQuery(name = "CtBenhAnNgoaiTru.findByCtbantMaso", query = "SELECT c FROM CtBenhAnNgoaiTru c WHERE c.ctbantMaso = :ctbantMaso"), @NamedQuery(name = "CtBenhAnNgoaiTru.findByCtbantNgay", query = "SELECT c FROM CtBenhAnNgoaiTru c WHERE c.ctbantNgay = :ctbantNgay"), @NamedQuery(name = "CtBenhAnNgoaiTru.findByCtbantDienbienbenh", query = "SELECT c FROM CtBenhAnNgoaiTru c WHERE c.ctbantDienbienbenh = :ctbantDienbienbenh"), @NamedQuery(name = "CtBenhAnNgoaiTru.findByCtbantYlenh", query = "SELECT c FROM CtBenhAnNgoaiTru c WHERE c.ctbantYlenh = :ctbantYlenh")})
public class CtBenhAnNgoaiTru implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_BENH_AN_NGOAI_TRU_CTBANT")
    @SequenceGenerator(name = "CT_BENH_AN_NGOAI_TRU_CTBANT", sequenceName = "CT_BENH_AN_NGOAI_TRU_CTBANT_MA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "CTBANT_MASO")
    private Integer ctbantMaso;
    @Column(name = "CTBANT_NGAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctbantNgay;
    @Column(name = "CTBANT_DIENBIENBENH")
    private String ctbantDienbienbenh;
    @Column(name = "CTBANT_YLENH")
    private String ctbantYlenh;
    @JoinColumn(name = "BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien bacsi;
    @JoinColumn(name = "BENH_AN_NGOAI_TRU", referencedColumnName = "BANT_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private BenhAnNgoaiTru benhAnNgoaiTru;

    public CtBenhAnNgoaiTru() {
    }

    public CtBenhAnNgoaiTru(Integer ctbantMaso) {
        this.ctbantMaso = ctbantMaso;
    }

    public Integer getCtbantMaso() {
        return ctbantMaso;
    }

    public void setCtbantMaso(Integer ctbantMaso) {
        this.ctbantMaso = ctbantMaso;
    }

    public Date getCtbantNgay() {
        return ctbantNgay;
    }

    public void setCtbantNgay(Date ctbantNgay) {
        this.ctbantNgay = ctbantNgay;
    }

    public String getCtbantDienbienbenh() {
        return ctbantDienbienbenh;
    }

    public void setCtbantDienbienbenh(String ctbantDienbienbenh) {
        this.ctbantDienbienbenh = ctbantDienbienbenh;
    }

    public String getCtbantYlenh() {
        return ctbantYlenh;
    }

    public void setCtbantYlenh(String ctbantYlenh) {
        this.ctbantYlenh = ctbantYlenh;
    }

    public DtDmNhanVien getBacsi() {
        return bacsi;
    }

    public void setBacsi(DtDmNhanVien bacsi) {
        this.bacsi = bacsi;
    }

    public BenhAnNgoaiTru getBenhAnNgoaiTru() {
        return benhAnNgoaiTru;
    }

    public void setBenhAnNgoaiTru(BenhAnNgoaiTru benhAnNgoaiTru) {
        this.benhAnNgoaiTru = benhAnNgoaiTru;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctbantMaso != null ? ctbantMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtBenhAnNgoaiTru)) {
            return false;
        }
        CtBenhAnNgoaiTru other = (CtBenhAnNgoaiTru) object;
        if ((this.ctbantMaso == null && other.ctbantMaso != null) || (this.ctbantMaso != null && !this.ctbantMaso.equals(other.ctbantMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru[ctbantMaso=" + ctbantMaso + "]";
    }

}
