/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmThuoc;
import java.io.Serializable;
import java.util.Date;
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
 * @author HP
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "PHIEU_THEO_DOI_TRUYEN_DICH")
@NamedQueries({
    @NamedQuery(name = "PhieuTheoDoiTruyenDich.findByPtdtdMaso", query = "SELECT p FROM PhieuTheoDoiTruyenDich p WHERE p.ptdtdMaso = :ptdtdMaso"),
    @NamedQuery(name = "PhieuTheoDoiTruyenDich.findByPtdtdTocdo", query = "SELECT p FROM PhieuTheoDoiTruyenDich p WHERE p.ptdtdTocdo = :ptdtdTocdo"),
    @NamedQuery(name = "PhieuTheoDoiTruyenDich.findByPtdtdBatdau", query = "SELECT p FROM PhieuTheoDoiTruyenDich p WHERE p.ptdtdBatdau = :ptdtdBatdau"),
    @NamedQuery(name = "PhieuTheoDoiTruyenDich.findByPtdtdKetthuc", query = "SELECT p FROM PhieuTheoDoiTruyenDich p WHERE p.ptdtdKetthuc = :ptdtdKetthuc"),
    @NamedQuery(name = "PhieuTheoDoiTruyenDich.findByPtdtdSoluong", query = "SELECT p FROM PhieuTheoDoiTruyenDich p WHERE p.ptdtdSoluong = :ptdtdSoluong")})
public class PhieuTheoDoiTruyenDich implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHIEU_THEO_DOI_TRUYEN_DICH")
    @SequenceGenerator(name = "PHIEU_THEO_DOI_TRUYEN_DICH", sequenceName = "PHIEU_THEO_DOI_TRUYEN_DICH_PTD", allocationSize = 1)
    @Column(name = "PTDTD_MASO", nullable = false)
    private Integer ptdtdMaso;
    @Column(name = "PTDTD_TOCDO")
    private Integer ptdtdTocdo;
    @Column(name = "PTDTD_BATDAU")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ptdtdBatdau;
    @Column(name = "PTDTD_KETTHUC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ptdtdKetthuc;
    @Column(name = "PTDTD_SOLUONG")
    private Short ptdtdSoluong;
    @Column(name = "SOLO_SOSX")
    private String soloSosx;
    @JoinColumn(name = "TONKHO_MA", referencedColumnName = "TONKHO_MA")
    @ManyToOne(fetch = FetchType.LAZY)
    private TonKho tonkhoMa;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne(fetch = FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "DMTHUOC_MASO", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmThuoc dmthuocMaso;
    @JoinColumn(name = "BACSI_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DtDmNhanVien bacsiMaso;
    @JoinColumn(name = "YTA_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DtDmNhanVien ytaMaso;

    public PhieuTheoDoiTruyenDich() {
    }

    public PhieuTheoDoiTruyenDich(Integer ptdtdMaso) {
        this.ptdtdMaso = ptdtdMaso;
    }

    public Integer getPtdtdMaso() {
        return ptdtdMaso;
    }

    public void setPtdtdMaso(Integer ptdtdMaso) {
        this.ptdtdMaso = ptdtdMaso;
    }

    public Integer getPtdtdTocdo() {
        return ptdtdTocdo;
    }

    public void setPtdtdTocdo(Integer ptdtdTocdo) {
        this.ptdtdTocdo = ptdtdTocdo;
    }

    public Date getPtdtdBatdau() {
        return ptdtdBatdau;
    }

    public void setPtdtdBatdau(Date ptdtdBatdau) {
        this.ptdtdBatdau = ptdtdBatdau;
    }

    public Date getPtdtdKetthuc() {
        return ptdtdKetthuc;
    }

    public void setPtdtdKetthuc(Date ptdtdKetthuc) {
        this.ptdtdKetthuc = ptdtdKetthuc;
    }

    public Short getPtdtdSoluong() {
        return ptdtdSoluong;
    }

    public void setPtdtdSoluong(Short ptdtdSoluong) {
        this.ptdtdSoluong = ptdtdSoluong;
    }

    public TonKho getTonkhoMa() {
        return tonkhoMa;
    }

    public void setTonkhoMa(TonKho tonkhoMa) {
        this.tonkhoMa = tonkhoMa;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DmThuoc getDmthuocMaso() {
        return dmthuocMaso;
    }

    public void setDmthuocMaso(DmThuoc dmthuocMaso) {
        this.dmthuocMaso = dmthuocMaso;
    }

    public DtDmNhanVien getBacsiMaso() {
        return bacsiMaso;
    }

    public void setBacsiMaso(DtDmNhanVien bacsiMaso) {
        this.bacsiMaso = bacsiMaso;
    }

    public DtDmNhanVien getYtaMaso() {
        return ytaMaso;
    }

    public void setYtaMaso(DtDmNhanVien ytaMaso) {
        this.ytaMaso = ytaMaso;
    }

    public String getSoloSosx() {
        return soloSosx;
    }

    public void setSoloSosx(String soloSosx) {
        this.soloSosx = soloSosx;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptdtdMaso != null ? ptdtdMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuTheoDoiTruyenDich)) {
            return false;
        }
        PhieuTheoDoiTruyenDich other = (PhieuTheoDoiTruyenDich) object;
        if ((this.ptdtdMaso == null && other.ptdtdMaso != null) || (this.ptdtdMaso != null && !this.ptdtdMaso.equals(other.ptdtdMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.PhieuTheoDoiTruyenDich[ptdtdMaso=" + ptdtdMaso + "]";
    }
}
