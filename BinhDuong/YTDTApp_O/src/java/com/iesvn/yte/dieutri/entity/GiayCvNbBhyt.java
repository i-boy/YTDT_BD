/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmBenhVien;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author james
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "GIAY_CV_NB_BHYT")
@NamedQueries({})
public class GiayCvNbBhyt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GCVBHYT_MA")
    private String gcvbhytMa;
    @Column(name = "GCVBHYT_NOICHUYENDEN")
    private String gcvbhytNoichuyenden;
    @Column(name = "GCVBHYT_KCT")
    private Boolean gcvbhytKct;
    @Column(name = "GCVBHYT_DADTNGOAITRU")
    private Boolean gcvbhytDadtngoaitru;
    @Column(name = "GCVBHYT_DADTNOITRU")
    private Boolean gcvbhytDadtnoitru;
    @Column(name = "GCVBHYT_LIDOCHUYENVIEN")
    private String gcvbhytLidochuyenvien;
    @JoinColumn(name = "GCVBHYT_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham gcvbhytThamkham;
    @JoinColumn(name = "GCVBHYT_BSKHAM", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien gcvbhytBskham;

    @Column(name = "GCVBHYT_CBBHXH")
    private String gcvbhytCBBHXH;
    @Column(name = "GCVBHYT_DAUHIEULAMSANG")
    private String gcvbhytDauhieulamsang;
    @Column(name = "GCVBHYT_TINHTRANGNGUOIBENH")
    private String gcvbhytTinhtrangnguoibenh;
    @Column(name = "GCVBHYT_PHUONGTIENVANCHUYEN")
    private String gcvbhytPhuongtienvanchuyen;
    @Column(name = "GCVBHYT_TEMP1")
    private String gcvbhytTemp1;
    @Column(name = "GCVBHYT_TEMP2")
    private String gcvbhytTemp2;
    @JoinColumn(name = "GCVBHYT_GIAMDOC", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien gcvbhytGiamdoc;
    
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
    @Column(name = "DIEUTRI2_TUNGAY")
    @Temporal(TemporalType.DATE)
    private Date dieutri2Tungay;
    @Column(name = "DIEUTRI2_DENNGAY")
    @Temporal(TemporalType.DATE)
    private Date dieutri2Denngay;
    @Column(name = "GCVBHYT_HUONGDIEUTRI")
    private String gcvbhytHuongdieutri;
    @Column(name = "GCVBHYT_NGAYGIOCHUYEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gcvbhytNgaygiochuyen;

    public GiayCvNbBhyt() {
    }

    public GiayCvNbBhyt(String gcvbhytMa) {
        this.gcvbhytMa = gcvbhytMa;
    }

    public String getGcvbhytMa() {
        return gcvbhytMa;
    }

    public void setGcvbhytMa(String gcvbhytMa) {
        this.gcvbhytMa = gcvbhytMa;
    }

    public String getGcvbhytNoichuyenden() {
        return gcvbhytNoichuyenden;
    }

    public void setGcvbhytNoichuyenden(String gcvbhytNoichuyenden) {
        this.gcvbhytNoichuyenden = gcvbhytNoichuyenden;
    }

    public Boolean getGcvbhytKct() {
        return gcvbhytKct;
    }

    public void setGcvbhytKct(Boolean gcvbhytKct) {
        this.gcvbhytKct = gcvbhytKct;
    }

    public Boolean getGcvbhytDadtngoaitru() {
        return gcvbhytDadtngoaitru;
    }

    public void setGcvbhytDadtngoaitru(Boolean gcvbhytDadtngoaitru) {
        this.gcvbhytDadtngoaitru = gcvbhytDadtngoaitru;
    }

    public Boolean getGcvbhytDadtnoitru() {
        return gcvbhytDadtnoitru;
    }

    public void setGcvbhytDadtnoitru(Boolean gcvbhytDadtnoitru) {
        this.gcvbhytDadtnoitru = gcvbhytDadtnoitru;
    }

    public String getGcvbhytLidochuyenvien() {
        return gcvbhytLidochuyenvien;
    }

    public void setGcvbhytLidochuyenvien(String gcvbhytLidochuyenvien) {
        this.gcvbhytLidochuyenvien = gcvbhytLidochuyenvien;
    }

    public String getGcvbhytDauhieulamsang() {
        return gcvbhytDauhieulamsang;
    }

    public void setGcvbhytDauhieulamsang(String gcvbhytDauhieulamsang) {
        this.gcvbhytDauhieulamsang = gcvbhytDauhieulamsang;
    }

    public String getGcvbhytTinhtrangnguoibenh() {
        return gcvbhytTinhtrangnguoibenh;
    }

    public void setGcvbhytTinhtrangnguoibenh(String gcvbhytTinhtrangnguoibenh) {
        this.gcvbhytTinhtrangnguoibenh = gcvbhytTinhtrangnguoibenh;
    }

    public String getGcvbhytPhuongtienvanchuyen() {
        return gcvbhytPhuongtienvanchuyen;
    }

    public void setGcvbhytPhuongtienvanchuyen(String gcvbhytPhuongtienvanchuyen) {
        this.gcvbhytPhuongtienvanchuyen = gcvbhytPhuongtienvanchuyen;
    }

    public String getGcvbhytTemp1() {
        return gcvbhytTemp1;
    }

    public void setGcvbhytTemp1(String gcvbhytTemp1) {
        this.gcvbhytTemp1 = gcvbhytTemp1;
    }

    public String getGcvbhytTemp2() {
        return gcvbhytTemp2;
    }

    public void setGcvbhytTemp2(String gcvbhytTemp2) {
        this.gcvbhytTemp2 = gcvbhytTemp2;
    }
    public ThamKham getGcvbhytThamkham() {
        return gcvbhytThamkham;
    }

    public ThamKham getGcvbhytThamkham(Boolean create) {
        if(create&&gcvbhytThamkham==null)
            gcvbhytThamkham=new ThamKham();
        return gcvbhytThamkham;
    }

    public void setGcvbhytThamkham(ThamKham gcvbhytThamkham) {
        this.gcvbhytThamkham = gcvbhytThamkham;
    }

    public DtDmNhanVien getGcvbhytBskham() {
        return gcvbhytBskham;
    }

     public DtDmNhanVien getGcvbhytBskham(boolean create) {
         if(create&&gcvbhytBskham==null)
             gcvbhytBskham=new DtDmNhanVien();
        return gcvbhytBskham;
    }

    public void setGcvbhytBskham(DtDmNhanVien gcvbhytBskham) {
        this.gcvbhytBskham = gcvbhytBskham;
    }

    public DtDmNhanVien getGcvbhytGiamdoc() {
        return gcvbhytGiamdoc;
    }

    public DtDmNhanVien getGcvbhytGiamdoc(boolean create) {
        if(create&&gcvbhytGiamdoc==null)
            gcvbhytGiamdoc=new DtDmNhanVien();
        return gcvbhytGiamdoc;
    }

    public void setGcvbhytGiamdoc(DtDmNhanVien gcvbhytGiamdoc) {
        this.gcvbhytGiamdoc = gcvbhytGiamdoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gcvbhytMa != null ? gcvbhytMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GiayCvNbBhyt)) {
            return false;
        }
        GiayCvNbBhyt other = (GiayCvNbBhyt) object;
        if ((this.gcvbhytMa == null && other.gcvbhytMa != null) || (this.gcvbhytMa != null && !this.gcvbhytMa.equals(other.gcvbhytMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.test.GiayCvNbBhyt[gcvbhytMa=" + gcvbhytMa + "]";
    }

    /**
     * @return the gcvbhytCBBHXH
     */
    public String getGcvbhytCBBHXH() {
        return gcvbhytCBBHXH;
    }

    /**
     * @param gcvbhytCBBHXH the gcvbhytCBBHXH to set
     */
    public void setGcvbhytCBBHXH(String gcvbhytCBBHXH) {
        this.gcvbhytCBBHXH = gcvbhytCBBHXH;
    }

    public DmBenhVien getDieutri1Donvi() {
        return dieutri1Donvi;
    }
    
    public DmBenhVien getDieutri1Donvi(boolean create) {
        if (create && getDieutri1Donvi() == null) {
            setDieutri1Donvi(new DmBenhVien());
        }
        return getDieutri1Donvi();
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

    public Date getDieutri2Tungay() {
        return dieutri2Tungay;
    }

    public void setDieutri2Tungay(Date dieutri2Tungay) {
        this.dieutri2Tungay = dieutri2Tungay;
    }

    public Date getDieutri2Denngay() {
        return dieutri2Denngay;
    }

    public void setDieutri2Denngay(Date dieutri2Denngay) {
        this.dieutri2Denngay = dieutri2Denngay;
    }

    public String getGcvbhytHuongdieutri() {
        return gcvbhytHuongdieutri;
    }

    public void setGcvbhytHuongdieutri(String gcvbhytHuongdieutri) {
        this.gcvbhytHuongdieutri = gcvbhytHuongdieutri;
    }

    public Date getGcvbhytNgaygiochuyen() {
        return gcvbhytNgaygiochuyen;
    }

    public void setGcvbhytNgaygiochuyen(Date gcvbhytNgaygiochuyen) {
        this.gcvbhytNgaygiochuyen = gcvbhytNgaygiochuyen;
    }

}
