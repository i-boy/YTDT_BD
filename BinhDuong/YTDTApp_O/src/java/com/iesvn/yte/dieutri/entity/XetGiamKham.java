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
@Table(name = "XET_GIAM_KHAM")
@NamedQueries({})
public class XetGiamKham implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "XETGIAMKHAM_MA", nullable = false)
    private String xetgiamkhamMa;
    @Column(name = "XETGIAMKHAM_NGAY")
    @Temporal(TemporalType.DATE)
    private Date xetgiamkhamNgay;
    @Column(name = "XETGIAMKHAM_LYDO")
    private String xetgiamkhamLydo;
    @Column(name = "XETGIAMKHAM_CUM")
    private Short xetgiamkhamCum;
    @Column(name = "XETGIAMKHAM_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date xetgiamkhamNgaygiocn;
    @Column(name = "XETGIAMKHAM_MAKIEMTRA")
    private String xetgiamkhamMakiemtra;
    @Column(name = "XETGIAMKHAM_LOAIMIEN")
    private String xetgiamkhamLoaimien;
    @Column(name = "XETGIAMKHAM_DOITUONG")
    private String xetgiamkhamDoituong;
    @Column(name = "XETGIAMKHAM_INPHIEU")
    private String xetgiamkhamInphieu;
    @Column(name = "XETGIAMKHAM_STATUS")
    private String xetgiamkhamStatus;
    @JoinColumn(name = "TIEPDON_MA", referencedColumnName = "TIEPDON_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private TiepDon tiepdonMa;
    @JoinColumn(name = "XETGIAMKHAM_BANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham xetgiamkhamBankham;
    @JoinColumn(name = "XETGIAMKHAM_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa xetgiamkhamKhoa;
    @JoinColumn(name = "XETGIAMKHAM_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien xetgiamkhamBacsi;
    @JoinColumn(name = "XETGIAMKHAM_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien xetgiamkhamNhanviencn;

    public XetGiamKham() {
    }

    public XetGiamKham(String xetgiamkhamMa) {
        this.xetgiamkhamMa = xetgiamkhamMa;
    }

    public String getXetgiamkhamMa() {
        return xetgiamkhamMa;
    }

    public void setXetgiamkhamMa(String xetgiamkhamMa) {
        this.xetgiamkhamMa = xetgiamkhamMa;
    }

    public Date getXetgiamkhamNgay() {
        return xetgiamkhamNgay;
    }

    public void setXetgiamkhamNgay(Date xetgiamkhamNgay) {
        this.xetgiamkhamNgay = xetgiamkhamNgay;
    }

    public String getXetgiamkhamLydo() {
        return xetgiamkhamLydo;
    }

    public void setXetgiamkhamLydo(String xetgiamkhamLydo) {
        this.xetgiamkhamLydo = xetgiamkhamLydo;
    }

    public Short getXetgiamkhamCum() {
        return xetgiamkhamCum;
    }

    public void setXetgiamkhamCum(Short xetgiamkhamCum) {
        this.xetgiamkhamCum = xetgiamkhamCum;
    }

    public Date getXetgiamkhamNgaygiocn() {
        return xetgiamkhamNgaygiocn;
    }

    public void setXetgiamkhamNgaygiocn(Date xetgiamkhamNgaygiocn) {
        this.xetgiamkhamNgaygiocn = xetgiamkhamNgaygiocn;
    }

    public String getXetgiamkhamMakiemtra() {
        return xetgiamkhamMakiemtra;
    }

    public void setXetgiamkhamMakiemtra(String xetgiamkhamMakiemtra) {
        this.xetgiamkhamMakiemtra = xetgiamkhamMakiemtra;
    }

    public String getXetgiamkhamLoaimien() {
        return xetgiamkhamLoaimien;
    }

    public void setXetgiamkhamLoaimien(String xetgiamkhamLoaimien) {
        this.xetgiamkhamLoaimien = xetgiamkhamLoaimien;
    }

    public String getXetgiamkhamDoituong() {
        return xetgiamkhamDoituong;
    }

    public void setXetgiamkhamDoituong(String xetgiamkhamDoituong) {
        this.xetgiamkhamDoituong = xetgiamkhamDoituong;
    }

    public String getXetgiamkhamInphieu() {
        return xetgiamkhamInphieu;
    }

    public void setXetgiamkhamInphieu(String xetgiamkhamInphieu) {
        this.xetgiamkhamInphieu = xetgiamkhamInphieu;
    }

    public String getXetgiamkhamStatus() {
        return xetgiamkhamStatus;
    }

    public void setXetgiamkhamStatus(String xetgiamkhamStatus) {
        this.xetgiamkhamStatus = xetgiamkhamStatus;
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

    public DtDmBanKham getXetgiamkhamBankham(boolean create) {
if(create && xetgiamkhamBankham == null) xetgiamkhamBankham = new DtDmBanKham();
return xetgiamkhamBankham;
}
    public DtDmBanKham getXetgiamkhamBankham() {
        return xetgiamkhamBankham;
    }

    public void setXetgiamkhamBankham(DtDmBanKham xetgiamkhamBankham) {
        this.xetgiamkhamBankham = xetgiamkhamBankham;
    }

    public DmKhoa getXetgiamkhamKhoa(boolean create) {
if(create && xetgiamkhamKhoa == null) xetgiamkhamKhoa = new DmKhoa();
return xetgiamkhamKhoa;
}
    public DmKhoa getXetgiamkhamKhoa() {
        return xetgiamkhamKhoa;
    }

    public void setXetgiamkhamKhoa(DmKhoa xetgiamkhamKhoa) {
        this.xetgiamkhamKhoa = xetgiamkhamKhoa;
    }

    public DtDmNhanVien getXetgiamkhamBacsi(boolean create) {
if(create && xetgiamkhamBacsi == null) xetgiamkhamBacsi = new DtDmNhanVien();
return xetgiamkhamBacsi;
}
    public DtDmNhanVien getXetgiamkhamBacsi() {
        return xetgiamkhamBacsi;
    }

    public void setXetgiamkhamBacsi(DtDmNhanVien xetgiamkhamBacsi) {
        this.xetgiamkhamBacsi = xetgiamkhamBacsi;
    }

    public DtDmNhanVien getXetgiamkhamNhanviencn(boolean create) {
if(create && xetgiamkhamNhanviencn == null) xetgiamkhamNhanviencn = new DtDmNhanVien();
return xetgiamkhamNhanviencn;
}
    public DtDmNhanVien getXetgiamkhamNhanviencn() {
        return xetgiamkhamNhanviencn;
    }

    public void setXetgiamkhamNhanviencn(DtDmNhanVien xetgiamkhamNhanviencn) {
        this.xetgiamkhamNhanviencn = xetgiamkhamNhanviencn;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xetgiamkhamMa != null ? xetgiamkhamMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XetGiamKham)) {
            return false;
        }
        XetGiamKham other = (XetGiamKham) object;
        if ((this.xetgiamkhamMa == null && other.xetgiamkhamMa != null) || (this.xetgiamkhamMa != null && !this.xetgiamkhamMa.equals(other.xetgiamkhamMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.XetGiamKham[xetgiamkhamMa=" + xetgiamkhamMa + "]";
    }

}


