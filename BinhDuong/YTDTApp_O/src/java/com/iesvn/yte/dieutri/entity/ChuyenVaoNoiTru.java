/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ies
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CHUYEN_VAO_NOI_TRU")
@NamedQueries({
    @NamedQuery(name = "ChuyenVaoNoiTru.findAll", query = "SELECT c FROM ChuyenVaoNoiTru c")})
public class ChuyenVaoNoiTru implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CVNT_TIEPDON_MA")
    private String cvntTIEPDONMA;
    @Column(name = "CVNT_NGAYGIO_CHUYEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cvntNGAYGIOCHUYEN;
    @Column(name = "CVNT_TIEN_CHUA_TT")
    private Double cvntTIENCHUATT;
    @Column(name = "CVNT_GHICHU")
    private String cvntGHICHU;
    @Column(name = "CVNT_DA_TT")
    private Boolean cvntDATT;
    @Column(name = "CVNT_NGAYGIO_THANHTOAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cvntNGAYGIOTHANHTOAN;
    @JoinColumn(name = "CVNT_BANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham cvntBANKHAM;
    @JoinColumn(name = "CVNT_DOITUONG_MA", referencedColumnName = "DMDOITUONG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDoiTuong cvntDOITUONGMA;
    @JoinColumn(name = "CVNT_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa cvntKHOA;
    @JoinColumn(name = "CVNT_MACHDOANBD", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd cvntMACHDOANBD;
    @JoinColumn(name = "CVNT_NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien cvntNHANVIENMA;
    @JoinColumn(name = "CVNT_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private Hsba cvntSOVAOVIEN;
    @JoinColumn(name = "CVNT_TIEPDON_MA", referencedColumnName = "TIEPDON_MA", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TiepDon tiepDon;

    public ChuyenVaoNoiTru() {
    }

    public ChuyenVaoNoiTru(String cvntTIEPDONMA) {
        this.cvntTIEPDONMA = cvntTIEPDONMA;
    }

    public String getCvntTIEPDONMA() {
        return cvntTIEPDONMA;
    }

    public void setCvntTIEPDONMA(String cvntTIEPDONMA) {
        this.cvntTIEPDONMA = cvntTIEPDONMA;
    }

    public Date getCvntNGAYGIOCHUYEN() {
        return cvntNGAYGIOCHUYEN;
    }

    public void setCvntNGAYGIOCHUYEN(Date cvntNGAYGIOCHUYEN) {
        this.cvntNGAYGIOCHUYEN = cvntNGAYGIOCHUYEN;
    }

    public Double getCvntTIENCHUATT() {
        return cvntTIENCHUATT;
    }

    public void setCvntTIENCHUATT(Double cvntTIENCHUATT) {
        this.cvntTIENCHUATT = cvntTIENCHUATT;
    }

    public String getCvntGHICHU() {
        return cvntGHICHU;
    }

    public void setCvntGHICHU(String cvntGHICHU) {
        this.cvntGHICHU = cvntGHICHU;
    }

    public Boolean getCvntDATT() {
        return cvntDATT;
    }

    public void setCvntDATT(Boolean cvntDATT) {
        this.cvntDATT = cvntDATT;
    }

    public Date getCvntNGAYGIOTHANHTOAN() {
        return cvntNGAYGIOTHANHTOAN;
    }

    public void setCvntNGAYGIOTHANHTOAN(Date cvntNGAYGIOTHANHTOAN) {
        this.cvntNGAYGIOTHANHTOAN = cvntNGAYGIOTHANHTOAN;
    }

    public DtDmBanKham getCvntBANKHAM() {
        return cvntBANKHAM;
    }

    public void setCvntBANKHAM(DtDmBanKham cvntBANKHAM) {
        this.cvntBANKHAM = cvntBANKHAM;
    }

    public DmDoiTuong getCvntDOITUONGMA() {
        return cvntDOITUONGMA;
    }

    public void setCvntDOITUONGMA(DmDoiTuong cvntDOITUONGMA) {
        this.cvntDOITUONGMA = cvntDOITUONGMA;
    }

    public DmKhoa getCvntKHOA() {
        return cvntKHOA;
    }

    public void setCvntKHOA(DmKhoa cvntKHOA) {
        this.cvntKHOA = cvntKHOA;
    }

    public DmBenhIcd getCvntMACHDOANBD() {
        return cvntMACHDOANBD;
    }

    public void setCvntMACHDOANBD(DmBenhIcd cvntMACHDOANBD) {
        this.cvntMACHDOANBD = cvntMACHDOANBD;
    }

    public DtDmNhanVien getCvntNHANVIENMA() {
        return cvntNHANVIENMA;
    }

    public void setCvntNHANVIENMA(DtDmNhanVien cvntNHANVIENMA) {
        this.cvntNHANVIENMA = cvntNHANVIENMA;
    }

    public Hsba getCvntSOVAOVIEN() {
        return cvntSOVAOVIEN;
    }

    public void setCvntSOVAOVIEN(Hsba cvntSOVAOVIEN) {
        this.cvntSOVAOVIEN = cvntSOVAOVIEN;
    }

    public TiepDon getTiepDon() {
        return tiepDon;
    }

    public void setTiepDon(TiepDon tiepDon) {
        this.tiepDon = tiepDon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cvntTIEPDONMA != null ? cvntTIEPDONMA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChuyenVaoNoiTru)) {
            return false;
        }
        ChuyenVaoNoiTru other = (ChuyenVaoNoiTru) object;
        if ((this.cvntTIEPDONMA == null && other.cvntTIEPDONMA != null) || (this.cvntTIEPDONMA != null && !this.cvntTIEPDONMA.equals(other.cvntTIEPDONMA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaentity.ChuyenVaoNoiTru[cvntTIEPDONMA=" + cvntTIEPDONMA + "]";
    }

}
