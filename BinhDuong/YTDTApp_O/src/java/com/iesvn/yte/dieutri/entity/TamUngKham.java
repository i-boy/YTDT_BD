/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "TAM_UNG_KHAM")
@NamedQueries({})
public class TamUngKham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "TAMUNGKHAM_MA", nullable = false)
    private String tamungkhamMa;
    @Column(name = "TAMUNGKHAM_KHOA")
    private String tamungkhamKhoa;
    @Column(name = "TAMUNGKHAM_SOTIEN")
    private Double tamungkhamSotien;
    @Column(name = "TAMUNGKHAM_NGAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tamungkhamNgay;
    @Column(name = "TAMUNGKHAM_KYHIEU")
    private String tamungkhamKyhieu;
    @Column(name = "TAMUNGKHAM_QUYEN")
    private String tamungkhamQuyen;
    @Column(name = "TAMUNGKHAM_BIENLAI")
    private String tamungkhamBienlai;
    @Column(name = "TAMUNGKHAM_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tamungkhamNgaygiocn;
    @Column(name = "TAMUNGKHAM_MAKIEMTRA")
    private String tamungkhamMakiemtra;
    @Column(name = "TAMUNGKHAM_LYDO")
    private String tamungkhamLydo;
    @Column(name = "TAMUNGKHAM_INPHIEU")
    private String tamungkhamInphieu;
    @Column(name = "TAMUNGKHAM_STATUS")
    private String tamungkhamStatus;
    @JoinColumn(name = "TIEPDON_MA", referencedColumnName = "TIEPDON_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private TiepDon tiepdonMa;
    @JoinColumn(name = "TAMUNGKHAM_BANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham tamungkhamBankham;
    @JoinColumn(name = "TAMUNGKHAM_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien tamungkhamNhanviencn;
    @JoinColumn(name = "TAMUNGKHAM_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien tamungkhamThungan;
    @JoinColumn(name = "TAMUNGKHAM_CUM", referencedColumnName = "DTDMCUM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCum tamungkhamCum;

    public TamUngKham() {
    }

    public TamUngKham(String tamungkhamMa) {
        this.tamungkhamMa = tamungkhamMa;
    }

    public String getTamungkhamMa() {
        return tamungkhamMa;
    }

    public void setTamungkhamMa(String tamungkhamMa) {
        this.tamungkhamMa = tamungkhamMa;
    }

    public String getTamungkhamKhoa() {
        return tamungkhamKhoa;
    }

    public void setTamungkhamKhoa(String tamungkhamKhoa) {
        this.tamungkhamKhoa = tamungkhamKhoa;
    }

    public Double getTamungkhamSotien() {
        return tamungkhamSotien;
    }

    public void setTamungkhamSotien(Double tamungkhamSotien) {
        this.tamungkhamSotien = tamungkhamSotien;
    }

    public Date getTamungkhamNgay() {
        return tamungkhamNgay;
    }

    public void setTamungkhamNgay(Date tamungkhamNgay) {
        this.tamungkhamNgay = tamungkhamNgay;
    }

    public String getTamungkhamKyhieu() {
        return tamungkhamKyhieu;
    }

    public void setTamungkhamKyhieu(String tamungkhamKyhieu) {
        this.tamungkhamKyhieu = tamungkhamKyhieu;
    }

    public String getTamungkhamQuyen() {
        return tamungkhamQuyen;
    }

    public void setTamungkhamQuyen(String tamungkhamQuyen) {
        this.tamungkhamQuyen = tamungkhamQuyen;
    }

    public String getTamungkhamBienlai() {
        return tamungkhamBienlai;
    }

    public void setTamungkhamBienlai(String tamungkhamBienlai) {
        this.tamungkhamBienlai = tamungkhamBienlai;
    }

    public Date getTamungkhamNgaygiocn() {
        return tamungkhamNgaygiocn;
    }

    public void setTamungkhamNgaygiocn(Date tamungkhamNgaygiocn) {
        this.tamungkhamNgaygiocn = tamungkhamNgaygiocn;
    }

    public String getTamungkhamMakiemtra() {
        return tamungkhamMakiemtra;
    }

    public void setTamungkhamMakiemtra(String tamungkhamMakiemtra) {
        this.tamungkhamMakiemtra = tamungkhamMakiemtra;
    }

    public String getTamungkhamLydo() {
        return tamungkhamLydo;
    }

    public void setTamungkhamLydo(String tamungkhamLydo) {
        this.tamungkhamLydo = tamungkhamLydo;
    }

    public String getTamungkhamInphieu() {
        return tamungkhamInphieu;
    }

    public void setTamungkhamInphieu(String tamungkhamInphieu) {
        this.tamungkhamInphieu = tamungkhamInphieu;
    }

    public String getTamungkhamStatus() {
        return tamungkhamStatus;
    }

    public void setTamungkhamStatus(String tamungkhamStatus) {
        this.tamungkhamStatus = tamungkhamStatus;
    }

    public TiepDon getTiepdonMa(boolean create) {
        if (create && tiepdonMa == null) {
            tiepdonMa = new TiepDon();
        }
        return tiepdonMa;
    }

    public TiepDon getTiepdonMa() {
        return tiepdonMa;
    }

    public void setTiepdonMa(TiepDon tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

    public DtDmBanKham getTamungkhamBankham(boolean create) {
        if (create && tamungkhamBankham == null) {
            tamungkhamBankham = new DtDmBanKham();
        }
        return tamungkhamBankham;
    }

    public DtDmBanKham getTamungkhamBankham() {
        return tamungkhamBankham;
    }

    public void setTamungkhamBankham(DtDmBanKham tamungkhamBankham) {
        this.tamungkhamBankham = tamungkhamBankham;
    }

    public DtDmNhanVien getTamungkhamNhanviencn(boolean create) {
        if (create && tamungkhamNhanviencn == null) {
            tamungkhamNhanviencn = new DtDmNhanVien();
        }
        return tamungkhamNhanviencn;
    }

    public DtDmNhanVien getTamungkhamNhanviencn() {
        return tamungkhamNhanviencn;
    }

    public void setTamungkhamNhanviencn(DtDmNhanVien tamungkhamNhanviencn) {
        this.tamungkhamNhanviencn = tamungkhamNhanviencn;
    }

    public DtDmNhanVien getTamungkhamThungan(boolean create) {
        if (create && tamungkhamThungan == null) {
            tamungkhamThungan = new DtDmNhanVien();
        }
        return tamungkhamThungan;
    }

    public DtDmNhanVien getTamungkhamThungan() {
        return tamungkhamThungan;
    }

    public void setTamungkhamThungan(DtDmNhanVien tamungkhamThungan) {
        this.tamungkhamThungan = tamungkhamThungan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tamungkhamMa != null ? tamungkhamMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TamUngKham)) {
            return false;
        }
        TamUngKham other = (TamUngKham) object;
        if ((this.tamungkhamMa == null && other.tamungkhamMa != null) || (this.tamungkhamMa != null && !this.tamungkhamMa.equals(other.tamungkhamMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.TamUngKham[tamungkhamMa=" + tamungkhamMa + "]";
    }

    public DtDmCum getTamungkhamCum() {
        return tamungkhamCum;
    }

    public DtDmCum getTamungkhamCum(boolean create) {
        if (create && tamungkhamCum == null) {
            tamungkhamCum = new DtDmCum();
        }
        return tamungkhamCum;
    }

    public void setTamungkhamCum(DtDmCum tamungkhamCum) {
        this.tamungkhamCum = tamungkhamCum;
    }
}


