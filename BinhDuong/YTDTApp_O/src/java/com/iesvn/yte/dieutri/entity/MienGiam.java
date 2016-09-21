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
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "MIEN_GIAM")
@NamedQueries({})
public class MienGiam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MIEN_GIAM")
    @SequenceGenerator(name = "MIEN_GIAM", sequenceName = "MIEN_GIAM_MIENGIAM_MA_SEQ", allocationSize = 1)
    @Column(name = "MIENGIAM_MA", nullable = false)
    private Integer miengiamMa;
    @Column(name = "MIENGIAM_NGAYD")
    @Temporal(TemporalType.DATE)
    private Date miengiamNgayd;
    @Column(name = "MIENGIAM_NGAYC")
    @Temporal(TemporalType.DATE)
    private Date miengiamNgayc;
    @Column(name = "MIENGIAM_TYLE")
    private Short miengiamTyle;
    @Column(name = "MIENGIAM_SOTIEN")
    private Double miengiamSotien;
    @Column(name = "MIENGIAM_NGAY")
    @Temporal(TemporalType.DATE)
    private Date miengiamNgay;
    @Column(name = "MIENGIAM_NGAYGIOTT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date miengiamNgaygiott;
    @Column(name = "MIENGIAM_LYDO")
    private String miengiamLydo;
    @Column(name = "MIENGIAM_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date miengiamNgaygiocn;
    @Column(name = "MIENGIAM_MAKIEMTRA")
    private String miengiamMakiemtra;
    @Column(name = "MIENGIAM_NGAYKY")
    @Temporal(TemporalType.DATE)
    private Date miengiamNgayky;
    @Column(name = "MIENGIAM_IN")
    private String miengiamIn;
    @Column(name = "MIENGIAM_STATUS")
    private String miengiamStatus;
    @Column(name = "MIENGIAM_MAPHIEU")
    private String miengiamMaphieu;
    /*
    @JoinColumn(name = "MIENGIAM_MAPHIEU", referencedColumnName = "DONMIEN_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private DonMien miengiamMaphieu;
     */
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "MIENGIAM_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa miengiamKhoa;
    @JoinColumn(name = "MIENGIAM_NGUOIDUYET", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien miengiamNguoiduyet;
    @JoinColumn(name = "MIENGIAM_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien miengiamNhanviencn;
    @JoinColumn(name = "MIENGIAM_LOAIMIEN", referencedColumnName = "DTDMLOAIMIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiMien miengiamLoaimien;
    @JoinColumn(name = "MIENGIAM_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien miengiamThungan;
    @JoinColumn(name = "MIENGIAM_DOITUONG", referencedColumnName = "DTDMDIENMIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmDienMien miengiamDoituong;
    @JoinColumn(name = "MIENGIAM_CUM", referencedColumnName = "DTDMCUM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCum miengiamCum;

    public MienGiam() {
    }

    public MienGiam(Integer miengiamMa) {
        this.miengiamMa = miengiamMa;
    }

    public Integer getMiengiamMa() {
        return miengiamMa;
    }

    public void setMiengiamMa(Integer miengiamMa) {
        this.miengiamMa = miengiamMa;
    }

    public Date getMiengiamNgayd() {
        return miengiamNgayd;
    }

    public void setMiengiamNgayd(Date miengiamNgayd) {
        this.miengiamNgayd = miengiamNgayd;
    }

    public Date getMiengiamNgayc() {
        return miengiamNgayc;
    }

    public void setMiengiamNgayc(Date miengiamNgayc) {
        this.miengiamNgayc = miengiamNgayc;
    }

    public Short getMiengiamTyle() {
        return miengiamTyle;
    }

    public void setMiengiamTyle(Short miengiamTyle) {
        this.miengiamTyle = miengiamTyle;
    }

    public Double getMiengiamSotien() {
        return miengiamSotien;
    }

    public void setMiengiamSotien(Double miengiamSotien) {
        this.miengiamSotien = miengiamSotien;
    }

    public Date getMiengiamNgay() {
        return miengiamNgay;
    }

    public void setMiengiamNgay(Date miengiamNgay) {
        this.miengiamNgay = miengiamNgay;
    }

    public Date getMiengiamNgaygiott() {
        return miengiamNgaygiott;
    }

    public void setMiengiamNgaygiott(Date miengiamNgaygiott) {
        this.miengiamNgaygiott = miengiamNgaygiott;
    }

    public String getMiengiamLydo() {
        return miengiamLydo;
    }

    public void setMiengiamLydo(String miengiamLydo) {
        this.miengiamLydo = miengiamLydo;
    }

    public Date getMiengiamNgaygiocn() {
        return miengiamNgaygiocn;
    }

    public void setMiengiamNgaygiocn(Date miengiamNgaygiocn) {
        this.miengiamNgaygiocn = miengiamNgaygiocn;
    }

    public String getMiengiamMakiemtra() {
        return miengiamMakiemtra;
    }

    public void setMiengiamMakiemtra(String miengiamMakiemtra) {
        this.miengiamMakiemtra = miengiamMakiemtra;
    }

    public Date getMiengiamNgayky() {
        return miengiamNgayky;
    }

    public void setMiengiamNgayky(Date miengiamNgayky) {
        this.miengiamNgayky = miengiamNgayky;
    }

    public String getMiengiamIn() {
        return miengiamIn;
    }

    public void setMiengiamIn(String miengiamIn) {
        this.miengiamIn = miengiamIn;
    }

    public String getMiengiamStatus() {
        return miengiamStatus;
    }

    public void setMiengiamStatus(String miengiamStatus) {
        this.miengiamStatus = miengiamStatus;
    }
    /*
    public DonMien getMiengiamMaphieu(boolean create) {
    if(create && miengiamMaphieu == null) miengiamMaphieu = new DonMien();
    return miengiamMaphieu;
    }
    
    public DonMien getMiengiamMaphieu() {
    return miengiamMaphieu;
    }
    
    public void setMiengiamMaphieu(DonMien miengiamMaphieu) {
    this.miengiamMaphieu = miengiamMaphieu;
    }
     */

    public String getMiengiamMaphieu() {
        return miengiamMaphieu;
    }

    public void setMiengiamMaphieu(String miengiamMaphieu) {
        this.miengiamMaphieu = miengiamMaphieu;
    }

    public Hsba getHsbaSovaovien(boolean create) {
        if (create && hsbaSovaovien == null) {
            hsbaSovaovien = new Hsba();
        }
        return hsbaSovaovien;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DmKhoa getMiengiamKhoa(boolean create) {
        if (create && miengiamKhoa == null) {
            miengiamKhoa = new DmKhoa();
        }
        return miengiamKhoa;
    }

    public DmKhoa getMiengiamKhoa() {
        return miengiamKhoa;
    }

    public void setMiengiamKhoa(DmKhoa miengiamKhoa) {
        this.miengiamKhoa = miengiamKhoa;
    }

    public DtDmNhanVien getMiengiamNguoiduyet(boolean create) {
        if (create && miengiamNguoiduyet == null) {
            miengiamNguoiduyet = new DtDmNhanVien();
        }
        return miengiamNguoiduyet;
    }

    public DtDmNhanVien getMiengiamNguoiduyet() {
        return miengiamNguoiduyet;
    }

    public void setMiengiamNguoiduyet(DtDmNhanVien miengiamNguoiduyet) {
        this.miengiamNguoiduyet = miengiamNguoiduyet;
    }

    public DtDmNhanVien getMiengiamNhanviencn(boolean create) {
        if (create && miengiamNhanviencn == null) {
            miengiamNhanviencn = new DtDmNhanVien();
        }
        return miengiamNhanviencn;
    }

    public DtDmNhanVien getMiengiamNhanviencn() {
        return miengiamNhanviencn;
    }

    public void setMiengiamNhanviencn(DtDmNhanVien miengiamNhanviencn) {
        this.miengiamNhanviencn = miengiamNhanviencn;
    }

    public DtDmLoaiMien getMiengiamLoaimien(boolean create) {
        if (create && miengiamLoaimien == null) {
            miengiamLoaimien = new DtDmLoaiMien();
        }
        return miengiamLoaimien;
    }

    public DtDmLoaiMien getMiengiamLoaimien() {
        return miengiamLoaimien;
    }

    public void setMiengiamLoaimien(DtDmLoaiMien miengiamLoaimien) {
        this.miengiamLoaimien = miengiamLoaimien;
    }

    public DtDmNhanVien getMiengiamThungan(boolean create) {
        if (create && miengiamThungan == null) {
            miengiamThungan = new DtDmNhanVien();
        }
        return miengiamThungan;
    }

    public DtDmNhanVien getMiengiamThungan() {
        return miengiamThungan;
    }

    public void setMiengiamThungan(DtDmNhanVien miengiamThungan) {
        this.miengiamThungan = miengiamThungan;
    }

    public DtDmDienMien getMiengiamDoituong(boolean create) {
        if (create && miengiamDoituong == null) {
            miengiamDoituong = new DtDmDienMien();
        }
        return miengiamDoituong;
    }

    public DtDmDienMien getMiengiamDoituong() {
        return miengiamDoituong;
    }

    public void setMiengiamDoituong(DtDmDienMien miengiamDoituong) {
        this.miengiamDoituong = miengiamDoituong;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (miengiamMa != null ? miengiamMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MienGiam)) {
            return false;
        }
        MienGiam other = (MienGiam) object;
        if ((this.miengiamMa == null && other.miengiamMa != null) || (this.miengiamMa != null && !this.miengiamMa.equals(other.miengiamMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.MienGiam[miengiamMa=" + miengiamMa + "]";
    }

    public DtDmCum getMiengiamCum() {
        return miengiamCum;
    }
    
    public DtDmCum getMiengiamCum(boolean create) {
        if (create && miengiamCum == null) {
            miengiamCum = new DtDmCum();
        }
        return miengiamCum;
    }

    public void setMiengiamCum(DtDmCum miengiamCum) {
        this.miengiamCum = miengiamCum;
    }
}


