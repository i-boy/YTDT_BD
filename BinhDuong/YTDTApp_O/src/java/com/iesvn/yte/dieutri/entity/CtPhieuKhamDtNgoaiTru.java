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
@Table(name = "CT_PHIEU_KHAM_DT_NGOAI_TRU")
@NamedQueries({})
public class CtPhieuKhamDtNgoaiTru implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_PHIEU_KHAM_DT_NGOAI_TRU")
    @SequenceGenerator(name = "CT_PHIEU_KHAM_DT_NGOAI_TRU", sequenceName = "CT_PHIEU_KHAM_DT_NGOAI_TRU_CTP", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "CTPKDTNT_MASO")
    private Integer ctpkdtntMaso;
    @Column(name = "CTPKDTNT_NGAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctpkdtntNgay;
    @Column(name = "CTPKDTNT_DIEUTRI")
    private String ctpkdtntDieutri;
    @Column(name = "CHANDOAN")
    private String chandoan;
    @JoinColumn(name = "BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien bacsi;
    @JoinColumn(name = "PHIEU_KCB_NGOAI_TRU", referencedColumnName = "PKDTNT_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuKhamDtNgoaiTru phieuKcbNgoaiTru;

    public CtPhieuKhamDtNgoaiTru() {
    }

    public CtPhieuKhamDtNgoaiTru(Integer ctpkdtntMaso) {
        this.ctpkdtntMaso = ctpkdtntMaso;
    }

    public Integer getCtpkdtntMaso() {
        return ctpkdtntMaso;
    }

    public void setCtpkdtntMaso(Integer ctpkdtntMaso) {
        this.ctpkdtntMaso = ctpkdtntMaso;
    }

    public Date getCtpkdtntNgay() {
        return ctpkdtntNgay;
    }

    public void setCtpkdtntNgay(Date ctpkdtntNgay) {
        this.ctpkdtntNgay = ctpkdtntNgay;
    }

    public String getCtpkdtntDieutri() {
        return ctpkdtntDieutri;
    }

    public void setCtpkdtntDieutri(String ctpkdtntDieutri) {
        this.ctpkdtntDieutri = ctpkdtntDieutri;
    }

    public String getChandoan() {
        return chandoan;
    }

    public void setChandoan(String chandoan) {
        this.chandoan = chandoan;
    }

    public DtDmNhanVien getBacsi() {
        return bacsi;
    }

    public void setBacsi(DtDmNhanVien bacsi) {
        this.bacsi = bacsi;
    }

    public PhieuKhamDtNgoaiTru getPhieuKcbNgoaiTru() {
        return phieuKcbNgoaiTru;
    }

    public void setPhieuKcbNgoaiTru(PhieuKhamDtNgoaiTru phieuKcbNgoaiTru) {
        this.phieuKcbNgoaiTru = phieuKcbNgoaiTru;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctpkdtntMaso != null ? ctpkdtntMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtPhieuKhamDtNgoaiTru)) {
            return false;
        }
        CtPhieuKhamDtNgoaiTru other = (CtPhieuKhamDtNgoaiTru) object;
        if ((this.ctpkdtntMaso == null && other.ctpkdtntMaso != null) || (this.ctpkdtntMaso != null && !this.ctpkdtntMaso.equals(other.ctpkdtntMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.CtPhieuKhamDtNgoaiTru[ctpkdtntMaso=" + ctpkdtntMaso + "]";
    }

}
