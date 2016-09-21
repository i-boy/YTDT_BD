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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HOAN_UNG_KHAM")
@NamedQueries({})
public class HoanUngKham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "HOANUNGKHAM_MA", nullable = false)
    private String hoanungkhamMa;
    @Column(name = "HOANUNGKHAM_SOTIEN")
    private Double hoanungkhamSotien;
    @Column(name = "HOANUNGKHAM_SODU")
    private Double hoanungkhamSodu;
    @Column(name = "HOANUNGKHAM_NGAY")
    @Temporal(TemporalType.DATE)
    private Date hoanungkhamNgay;
    
        @JoinColumn(name = "HOANUNGKHAM_CUM", referencedColumnName = "DTDMCUM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCum hoanungkhamCum;
   
    
    
    @Column(name = "HOANUNGKHAM_KYHIEU")
    private String hoanungkhamKyhieu;
    @Column(name = "HOANUNGKHAM_QUYEN")
    private String hoanungkhamQuyen;
    @Column(name = "HOANUNGKHAM_BIENLAI")
    private String hoanungkhamBienlai;
    @Column(name = "HOANUNGKHAM_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hoanungkhamNgaygiocn;
    @Column(name = "HOANUNGKHAM_MAKIEMTRA")
    private String hoanungkhamMakiemtra;
    @Column(name = "HOANUNGKHAM_LYDO")
    private String hoanungkhamLydo;
    @Column(name = "HOANUNGKHAM_INPHIEU")
    private String hoanungkhamInphieu;
    @Column(name = "HOANUNGKHAM_STATUS")
    private String hoanungkhamStatus;
    @JoinColumn(name = "TIEPDON_MA", referencedColumnName = "TIEPDON_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private TiepDon tiepdonMa;
    @JoinColumn(name = "HOANUNGKHAM_BANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham hoanungkhamBankham;
    @JoinColumn(name = "HOANUNGKHAM_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa hoanungkhamKhoa;
    @JoinColumn(name = "HOANUNGKHAM_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hoanungkhamNhanviencn;
    @JoinColumn(name = "HOANUNGKHAM_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hoanungkhamThungan;

    public HoanUngKham() {
    }

    public HoanUngKham(String hoanungkhamMa) {
        this.hoanungkhamMa = hoanungkhamMa;
    }

    public String getHoanungkhamMa() {
        return hoanungkhamMa;
    }

    public void setHoanungkhamMa(String hoanungkhamMa) {
        this.hoanungkhamMa = hoanungkhamMa;
    }

    public Double getHoanungkhamSotien() {
        return hoanungkhamSotien;
    }

    public void setHoanungkhamSotien(Double hoanungkhamSotien) {
        this.hoanungkhamSotien = hoanungkhamSotien;
    }

    public Double getHoanungkhamSodu() {
        return hoanungkhamSodu;
    }

    public void setHoanungkhamSodu(Double hoanungkhamSodu) {
        this.hoanungkhamSodu = hoanungkhamSodu;
    }

    public Date getHoanungkhamNgay() {
        return hoanungkhamNgay;
    }

    public void setHoanungkhamNgay(Date hoanungkhamNgay) {
        this.hoanungkhamNgay = hoanungkhamNgay;
    }

    public DtDmCum getHoanungkhamCum() {
        return hoanungkhamCum;
    }

    public void setHoanungkhamCum(DtDmCum hoanungkhamCum) {
        this.hoanungkhamCum = hoanungkhamCum;
    }

    public String getHoanungkhamKyhieu() {
        return hoanungkhamKyhieu;
    }

    public void setHoanungkhamKyhieu(String hoanungkhamKyhieu) {
        this.hoanungkhamKyhieu = hoanungkhamKyhieu;
    }

    public String getHoanungkhamQuyen() {
        return hoanungkhamQuyen;
    }

    public void setHoanungkhamQuyen(String hoanungkhamQuyen) {
        this.hoanungkhamQuyen = hoanungkhamQuyen;
    }

    public String getHoanungkhamBienlai() {
        return hoanungkhamBienlai;
    }

    public void setHoanungkhamBienlai(String hoanungkhamBienlai) {
        this.hoanungkhamBienlai = hoanungkhamBienlai;
    }

    public Date getHoanungkhamNgaygiocn() {
        return hoanungkhamNgaygiocn;
    }

    public void setHoanungkhamNgaygiocn(Date hoanungkhamNgaygiocn) {
        this.hoanungkhamNgaygiocn = hoanungkhamNgaygiocn;
    }

    public String getHoanungkhamMakiemtra() {
        return hoanungkhamMakiemtra;
    }

    public void setHoanungkhamMakiemtra(String hoanungkhamMakiemtra) {
        this.hoanungkhamMakiemtra = hoanungkhamMakiemtra;
    }

    public String getHoanungkhamLydo() {
        return hoanungkhamLydo;
    }

    public void setHoanungkhamLydo(String hoanungkhamLydo) {
        this.hoanungkhamLydo = hoanungkhamLydo;
    }

    public String getHoanungkhamInphieu() {
        return hoanungkhamInphieu;
    }

    public void setHoanungkhamInphieu(String hoanungkhamInphieu) {
        this.hoanungkhamInphieu = hoanungkhamInphieu;
    }

    public String getHoanungkhamStatus() {
        return hoanungkhamStatus;
    }

    public void setHoanungkhamStatus(String hoanungkhamStatus) {
        this.hoanungkhamStatus = hoanungkhamStatus;
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

    public DtDmBanKham getHoanungkhamBankham(boolean create) {
if(create && hoanungkhamBankham == null) hoanungkhamBankham = new DtDmBanKham();
return hoanungkhamBankham;
}
    public DtDmBanKham getHoanungkhamBankham() {
        return hoanungkhamBankham;
    }

    public void setHoanungkhamBankham(DtDmBanKham hoanungkhamBankham) {
        this.hoanungkhamBankham = hoanungkhamBankham;
    }

    public DmKhoa getHoanungkhamKhoa(boolean create) {
if(create && hoanungkhamKhoa == null) hoanungkhamKhoa = new DmKhoa();
return hoanungkhamKhoa;
}
    public DmKhoa getHoanungkhamKhoa() {
        return hoanungkhamKhoa;
    }

    public void setHoanungkhamKhoa(DmKhoa hoanungkhamKhoa) {
        this.hoanungkhamKhoa = hoanungkhamKhoa;
    }

    public DtDmNhanVien getHoanungkhamNhanviencn(boolean create) {
if(create && hoanungkhamNhanviencn == null) hoanungkhamNhanviencn = new DtDmNhanVien();
return hoanungkhamNhanviencn;
}
    public DtDmNhanVien getHoanungkhamNhanviencn() {
        return hoanungkhamNhanviencn;
    }

    public void setHoanungkhamNhanviencn(DtDmNhanVien hoanungkhamNhanviencn) {
        this.hoanungkhamNhanviencn = hoanungkhamNhanviencn;
    }

    public DtDmNhanVien getHoanungkhamThungan(boolean create) {
if(create && hoanungkhamThungan == null) hoanungkhamThungan = new DtDmNhanVien();
return hoanungkhamThungan;
}
    public DtDmNhanVien getHoanungkhamThungan() {
        return hoanungkhamThungan;
    }

    public void setHoanungkhamThungan(DtDmNhanVien hoanungkhamThungan) {
        this.hoanungkhamThungan = hoanungkhamThungan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hoanungkhamMa != null ? hoanungkhamMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HoanUngKham)) {
            return false;
        }
        HoanUngKham other = (HoanUngKham) object;
        if ((this.hoanungkhamMa == null && other.hoanungkhamMa != null) || (this.hoanungkhamMa != null && !this.hoanungkhamMa.equals(other.hoanungkhamMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HoanUngKham[hoanungkhamMa=" + hoanungkhamMa + "]";
    }
}


