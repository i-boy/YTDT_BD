/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
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
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DE_NGHI_CC")
@NamedQueries({})
public class DeNghiCc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DE_NGHI_CC_DENGHICC_MA")
    @SequenceGenerator(name = "DE_NGHI_CC_DENGHICC_MA", sequenceName = "DE_NGHI_CC_DENGHICC_MA_SEQ", allocationSize = 1)
    @Column(name = "DENGHICC_MA", nullable = false)
    private Integer denghiccMa;
    @Column(name = "DENGHICC_MASO")
    private String denghiccMaso;
    @Column(name = "DENGHICC_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date denghiccNgaygio;
    @Column(name = "DENGHICC_NOIDUNG")
    private String denghiccNoidung;
    @Column(name = "DENGHICC_SOTIEN")
    private Double denghiccSotien;
    @Column(name = "DENGHICC_CUM")
    private Short denghiccCum;
    @Column(name = "DENGHICC_LYDO")
    private String denghiccLydo;
    @Column(name = "DENGHICC_STATUS")
    private String denghiccStatus;
    @Column(name = "DENGHICC_PHANBIET")
    private String denghiccPhanbiet;
    @Column(name = "DENGHICC_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date denghiccNgaygiocn;
    @JoinColumn(name = "TIEPDON_MA", referencedColumnName = "TIEPDON_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private TiepDon tiepdonMa;
    @JoinColumn(name = "DENGHICC_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa denghiccKhoa;
    @JoinColumn(name = "DENGHICC_BANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham denghiccBankham;
    @JoinColumn(name = "DENGHICC_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien denghiccNhanviencn;

    public DeNghiCc() {
    }

    public DeNghiCc(Integer denghiccMa) {
        this.denghiccMa = denghiccMa;
    }

    public Integer getDenghiccMa() {
        return denghiccMa;
    }

    public void setDenghiccMa(Integer denghiccMa) {
        this.denghiccMa = denghiccMa;
    }

    public String getDenghiccMaso() {
        return denghiccMaso;
    }

    public void setDenghiccMaso(String denghiccMaso) {
        this.denghiccMaso = denghiccMaso;
    }

    public Date getDenghiccNgaygio() {
        return denghiccNgaygio;
    }

    public void setDenghiccNgaygio(Date denghiccNgaygio) {
        this.denghiccNgaygio = denghiccNgaygio;
    }

    public String getDenghiccNoidung() {
        return denghiccNoidung;
    }

    public void setDenghiccNoidung(String denghiccNoidung) {
        this.denghiccNoidung = denghiccNoidung;
    }

    public Double getDenghiccSotien() {
        return denghiccSotien;
    }

    public void setDenghiccSotien(Double denghiccSotien) {
        this.denghiccSotien = denghiccSotien;
    }

    public Short getDenghiccCum() {
        return denghiccCum;
    }

    public void setDenghiccCum(Short denghiccCum) {
        this.denghiccCum = denghiccCum;
    }

    public String getDenghiccLydo() {
        return denghiccLydo;
    }

    public void setDenghiccLydo(String denghiccLydo) {
        this.denghiccLydo = denghiccLydo;
    }

    public String getDenghiccStatus() {
        return denghiccStatus;
    }

    public void setDenghiccStatus(String denghiccStatus) {
        this.denghiccStatus = denghiccStatus;
    }

    public String getDenghiccPhanbiet() {
        return denghiccPhanbiet;
    }

    public void setDenghiccPhanbiet(String denghiccPhanbiet) {
        this.denghiccPhanbiet = denghiccPhanbiet;
    }

    public Date getDenghiccNgaygiocn() {
        return denghiccNgaygiocn;
    }

    public void setDenghiccNgaygiocn(Date denghiccNgaygiocn) {
        this.denghiccNgaygiocn = denghiccNgaygiocn;
    }

    public TiepDon getTiepdonMa(boolean create) {
if(create && tiepdonMa == null) tiepdonMa = new TiepDon();
return tiepdonMa;
}
    public TiepDon getTiepdonMa() {
        return tiepdonMa;
    }

    public void setTiepdonMa(TiepDon tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

    public DmKhoa getDenghiccKhoa(boolean create) {
if(create && denghiccKhoa == null) denghiccKhoa = new DmKhoa();
return denghiccKhoa;
}
    public DmKhoa getDenghiccKhoa() {
        return denghiccKhoa;
    }

    public void setDenghiccKhoa(DmKhoa denghiccKhoa) {
        this.denghiccKhoa = denghiccKhoa;
    }

    public DtDmBanKham getDenghiccBankham(boolean create) {
if(create && denghiccBankham == null) denghiccBankham = new DtDmBanKham();
return denghiccBankham;
}
    public DtDmBanKham getDenghiccBankham() {
        return denghiccBankham;
    }

    public void setDenghiccBankham(DtDmBanKham denghiccBankham) {
        this.denghiccBankham = denghiccBankham;
    }

    public DtDmNhanVien getDenghiccNhanviencn(boolean create) {
if(create && denghiccNhanviencn == null) denghiccNhanviencn = new DtDmNhanVien();
return denghiccNhanviencn;
}
    public DtDmNhanVien getDenghiccNhanviencn() {
        return denghiccNhanviencn;
    }

    public void setDenghiccNhanviencn(DtDmNhanVien denghiccNhanviencn) {
        this.denghiccNhanviencn = denghiccNhanviencn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (denghiccMa != null ? denghiccMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeNghiCc)) {
            return false;
        }
        DeNghiCc other = (DeNghiCc) object;
        if ((this.denghiccMa == null && other.denghiccMa != null) || (this.denghiccMa != null && !this.denghiccMa.equals(other.denghiccMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DeNghiCc[denghiccMa=" + denghiccMa + "]";
    }
}


