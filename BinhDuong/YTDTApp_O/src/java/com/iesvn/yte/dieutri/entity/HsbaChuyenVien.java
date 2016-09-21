/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmBenhVien;
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
@Table(name = "HSBA_CHUYEN_VIEN")
@NamedQueries({})
public class HsbaChuyenVien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHUYEN_VIEN")
    @SequenceGenerator(name = "HSBA_CHUYEN_VIEN", sequenceName = "HSBA_CHUYEN_VIEN_HSBACHUYENVIE", allocationSize = 1)
    @Column(name = "HSBACHUYENVIEN_MASO", nullable = false)
    private Integer hsbachuyenvienMaso;
    @Column(name = "HSBACV_NGAYCAP")
    @Temporal(TemporalType.DATE)
    private Date hsbacvNgaycap;
    @Column(name = "HSBACV_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbacvNgaygiocn;
    @Column(name = "HSBACV_NGAGIOCHVIEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbacvNgagiochvien;
    @Column(name = "HSBACV_MA")
    private String hsbacvMa;
    @Column(name = "HSBACV_NOIYEUCAU")
    private String hsbacvNoiyeucau;
    @Column(name = "HSBACV_NGAYYC")
    @Temporal(TemporalType.DATE)
    private Date hsbacvNgayyc;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "HSBACV_BSCHUYEN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbacvBschuyen;

    @JoinColumn(name = "HSBACV_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbacvBsdieutri;

    @JoinColumn(name = "HSBACV_LYDOCHUYENV", referencedColumnName = "DTDMLYDOCV_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLyDoCv hsbacvLydochuyenv;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "HSBACV_CHVIENDEN", referencedColumnName = "DMBENHVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhVien hsbacvChvienden;


    @Column(name = "HSBACV_DAUHIEULAMSANG")
    private String hsbacvDauhieulamsang;
    @Column(name = "HSBACV_XETNGHIEM")
    private String hsbacvXetnghiem;
    @Column(name = "HSBACV_THUOCDADUNG")
    private String hsbacvThuocdadung;
    @Column(name = "HSBACV_TINHTRANGNGUOIBENH")
    private String hsbacvTinhtrangnguoibenh;
    @Column(name = "HSBACV_PHUONGTIENVANCHUYEN")
    private String hsbacvPhuongtienvanchuyen;
    
    @JoinColumn(name = "DIEUTRI1_DONVI", referencedColumnName = "DMBENHVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhVien dieutri1Donvi;
    @Column(name = "DIEUTRI1_TUYEN")
    private String dieutri1Tuyen;
    @Column(name = "DIEUTRI1_TUNGAY")
    @Temporal(TemporalType.DATE)
    private Date dieutri1Tungay;
    @Column(name = "DIEUTRI1_DENNGAY")
    @Temporal(TemporalType.DATE)
    private Date dieutri1Denngay;
    @Column(name = "HSBACV_HUONGDIEUTRI")
    private String hsbacvHuongdieutri;
    @Column(name = "HSBACV_LYDO_RADIO")
    private String hsbacvLydoRadio;

    public String getHsbacvDauhieulamsang() {
        return hsbacvDauhieulamsang;
    }

    public void setHsbacvDauhieulamsang(String hsbacvDauhieulamsang) {
        this.hsbacvDauhieulamsang = hsbacvDauhieulamsang;
    }

    public String getHsbacvPhuongtienvanchuyen() {
        return hsbacvPhuongtienvanchuyen;
    }

    public void setHsbacvPhuongtienvanchuyen(String hsbacvPhuongtienvanchuyen) {
        this.hsbacvPhuongtienvanchuyen = hsbacvPhuongtienvanchuyen;
    }

    public String getHsbacvThuocdadung() {
        return hsbacvThuocdadung;
    }

    public void setHsbacvThuocdadung(String hsbacvThuocdadung) {
        this.hsbacvThuocdadung = hsbacvThuocdadung;
    }

    public String getHsbacvTinhtrangnguoibenh() {
        return hsbacvTinhtrangnguoibenh;
    }

    public void setHsbacvTinhtrangnguoibenh(String hsbacvTinhtrangnguoibenh) {
        this.hsbacvTinhtrangnguoibenh = hsbacvTinhtrangnguoibenh;
    }

    public String getHsbacvXetnghiem() {
        return hsbacvXetnghiem;
    }

    public void setHsbacvXetnghiem(String hsbacvXetnghiem) {
        this.hsbacvXetnghiem = hsbacvXetnghiem;
    }

    public DtDmNhanVien getHsbacvBsdieutri() {
        return hsbacvBsdieutri;
    }

    public DtDmNhanVien getHsbacvBsdieutri(boolean create) {
if(create && hsbacvBsdieutri == null) hsbacvBsdieutri = new DtDmNhanVien();
return hsbacvBsdieutri;
}

    public void setHsbacvBsdieutri(DtDmNhanVien hsbacvBsdieutri) {
        this.hsbacvBsdieutri = hsbacvBsdieutri;
    }

    public HsbaChuyenVien() {
    }

    public HsbaChuyenVien(Integer hsbachuyenvienMaso) {
        this.hsbachuyenvienMaso = hsbachuyenvienMaso;
    }

    public Integer getHsbachuyenvienMaso() {
        return hsbachuyenvienMaso;
    }

    public void setHsbachuyenvienMaso(Integer hsbachuyenvienMaso) {
        this.hsbachuyenvienMaso = hsbachuyenvienMaso;
    }

    public Date getHsbacvNgaycap() {
        return hsbacvNgaycap;
    }

    public void setHsbacvNgaycap(Date hsbacvNgaycap) {
        this.hsbacvNgaycap = hsbacvNgaycap;
    }

    public Date getHsbacvNgaygiocn() {
        return hsbacvNgaygiocn;
    }

    public void setHsbacvNgaygiocn(Date hsbacvNgaygiocn) {
        this.hsbacvNgaygiocn = hsbacvNgaygiocn;
    }

    public Date getHsbacvNgagiochvien() {
        return hsbacvNgagiochvien;
    }

    public void setHsbacvNgagiochvien(Date hsbacvNgagiochvien) {
        this.hsbacvNgagiochvien = hsbacvNgagiochvien;
    }

    public Hsba getHsbaSovaovien(boolean create) {
if(create && hsbaSovaovien == null) hsbaSovaovien = new Hsba();
return hsbaSovaovien;
}
    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DtDmNhanVien getHsbacvBschuyen(boolean create) {
if(create && hsbacvBschuyen == null) hsbacvBschuyen = new DtDmNhanVien();
return hsbacvBschuyen;
}
    public DtDmNhanVien getHsbacvBschuyen() {
        return hsbacvBschuyen;
    }

    public void setHsbacvBschuyen(DtDmNhanVien hsbacvBschuyen) {
        this.hsbacvBschuyen = hsbacvBschuyen;
    }

    public DtDmLyDoCv getHsbacvLydochuyenv(boolean create) {
if(create && hsbacvLydochuyenv == null) hsbacvLydochuyenv = new DtDmLyDoCv();
return hsbacvLydochuyenv;
}
    public DtDmLyDoCv getHsbacvLydochuyenv() {
        return hsbacvLydochuyenv;
    }

    public void setHsbacvLydochuyenv(DtDmLyDoCv hsbacvLydochuyenv) {
        this.hsbacvLydochuyenv = hsbacvLydochuyenv;
    }

    public DtDmNhanVien getNhanvienMa(boolean create) {
if(create && nhanvienMa == null) nhanvienMa = new DtDmNhanVien();
return nhanvienMa;
}
    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    public DmBenhVien getHsbacvChvienden(boolean create) {
        if (create && hsbacvChvienden == null) {
            hsbacvChvienden = new DmBenhVien();
        }
        return hsbacvChvienden;
    }
    
    public DmBenhVien getHsbacvChvienden() {
        return hsbacvChvienden;
    }

    public void setHsbacvChvienden(DmBenhVien hsbacvChvienden) {
        this.hsbacvChvienden = hsbacvChvienden;
    }

   public String getHsbacvMa() {
        return hsbacvMa;
    }

    public void setHsbacvMa(String hsbacvMa) {
        this.hsbacvMa = hsbacvMa;
    }

    public String getHsbacvNoiyeucau() {
        return hsbacvNoiyeucau;
    }

    public void setHsbacvNoiyeucau(String hsbacvNoiyeucau) {
        this.hsbacvNoiyeucau = hsbacvNoiyeucau;
    }
    public Date getHsbacvNgayyc() {
        return hsbacvNgayyc;
    }

    public void setHsbacvNgayyc(Date hsbacvNgayyc) {
        this.hsbacvNgayyc = hsbacvNgayyc;
    }
    
    public DmBenhVien getDieutri1Donvi(boolean create) {
        if (create && dieutri1Donvi == null) {
            dieutri1Donvi = new DmBenhVien();
        }
        return dieutri1Donvi;
    }
    
    public DmBenhVien getDieutri1Donvi() {
        return dieutri1Donvi;
    }

    public void setDieutri1Donvi(DmBenhVien dieutri1Donvi) {
        this.dieutri1Donvi = dieutri1Donvi;
    }

    public String getDieutri1Tuyen() {
        return dieutri1Tuyen;
    }

    public void setDieutri1Tuyen(String dieutri1Tuyen) {
        this.dieutri1Tuyen = dieutri1Tuyen;
    }

    public Date getDieutri1Tungay() {
        return dieutri1Tungay;
    }

    public void setDieutri1Tungay(Date dieutri1Tungay) {
        this.dieutri1Tungay = dieutri1Tungay;
    }

    public Date getDieutri1Denngay() {
        return dieutri1Denngay;
    }

    public void setDieutri1Denngay(Date dieutri1Denngay) {
        this.dieutri1Denngay = dieutri1Denngay;
    }

    public String getHsbacvHuongdieutri() {
        return hsbacvHuongdieutri;
    }

    public void setHsbacvHuongdieutri(String hsbacvHuongdieutri) {
        this.hsbacvHuongdieutri = hsbacvHuongdieutri;
    }

    public String getHsbacvLydoRadio() {
        return hsbacvLydoRadio;
    }

    public void setHsbacvLydoRadio(String hsbacvLydoRadio) {
        this.hsbacvLydoRadio = hsbacvLydoRadio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbachuyenvienMaso != null ? hsbachuyenvienMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChuyenVien)) {
            return false;
        }
        HsbaChuyenVien other = (HsbaChuyenVien) object;
        if ((this.hsbachuyenvienMaso == null && other.hsbachuyenvienMaso != null) || (this.hsbachuyenvienMaso != null && !this.hsbachuyenvienMaso.equals(other.hsbachuyenvienMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsbaChuyenVien[hsbachuyenvienMaso=" + hsbachuyenvienMaso + "]";
    }

}


