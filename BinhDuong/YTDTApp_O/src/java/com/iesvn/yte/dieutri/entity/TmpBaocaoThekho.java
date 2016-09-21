/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "TMP_BAOCAO_THEKHO")

@NamedQueries({})
public class TmpBaocaoThekho implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TMP_BAOCAO")
    @SequenceGenerator(name = "TMP_BAOCAO", sequenceName = "TMP_BAOCAO_THEKHO_SEQ", allocationSize = 1)
    @Column(name = "STT",nullable = false)
    private Integer stt;
    @Column(name = "NGAYTHANG",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaythang;
    @Column(name = "CHUNGTUNXT",nullable = false)
    private String chungtunxt;
    @Column(name = "PHIEUNHAPKHO",nullable = false)
    private String phieunhapkho;
    @Column(name = "LOAIPHIEU",nullable = false)
    private String loaiphieu;
    @Column(name = "DIENGIAI")
    private String diengiai;
    @Column(name = "SOLUONGNHAP")
    private Double soluongnhap;
    @Column(name = "THANHTIENNHAP")
    private Double thanhtiennhap;
    @Column(name = "SOLUONGXUAT")
    private Double soluongxuat;
    @Column(name = "THANHTIENXUAT")
    private Double thanhtienxuat;
    @Column(name = "SOLUONGTON")
    private Double soluongton;
    @Column(name = "THANHTIENTON")
    private Double thanhtienton;

    public TmpBaocaoThekho() {
    }

    public TmpBaocaoThekho(Integer stt) {
        this.stt = stt;
    }

    public TmpBaocaoThekho(Integer stt, Date ngaythang, String chungtunxt, String phieunhapkho, String loaiphieu) {
        this.stt = stt;
        this.ngaythang = ngaythang;
        this.chungtunxt = chungtunxt;
        this.phieunhapkho = phieunhapkho;
        this.loaiphieu = loaiphieu;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public Date getNgaythang() {
        return ngaythang;
    }

    public void setNgaythang(Date ngaythang) {
        this.ngaythang = ngaythang;
    }

    public String getChungtunxt() {
        return chungtunxt;
    }

    public void setChungtunxt(String chungtunxt) {
        this.chungtunxt = chungtunxt;
    }

    public String getPhieunhapkho() {
        return phieunhapkho;
    }

    public void setPhieunhapkho(String phieunhapkho) {
        this.phieunhapkho = phieunhapkho;
    }

    public String getLoaiphieu() {
        return loaiphieu;
    }

    public void setLoaiphieu(String loaiphieu) {
        this.loaiphieu = loaiphieu;
    }

    public String getDiengiai() {
        return diengiai;
    }

    public void setDiengiai(String diengiai) {
        this.diengiai = diengiai;
    }

    public Double getSoluongnhap() {
        return soluongnhap;
    }

    public void setSoluongnhap(Double soluongnhap) {
        this.soluongnhap = soluongnhap;
    }

    public Double getThanhtiennhap() {
        return thanhtiennhap;
    }

    public void setThanhtiennhap(Double thanhtiennhap) {
        this.thanhtiennhap = thanhtiennhap;
    }

    public Double getSoluongxuat() {
        return soluongxuat;
    }

    public void setSoluongxuat(Double soluongxuat) {
        this.soluongxuat = soluongxuat;
    }

    public Double getThanhtienxuat() {
        return thanhtienxuat;
    }

    public void setThanhtienxuat(Double thanhtienxuat) {
        this.thanhtienxuat = thanhtienxuat;
    }

    public Double getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(Double soluongton) {
        this.soluongton = soluongton;
    }

    public Double getThanhtienton() {
        return thanhtienton;
    }

    public void setThanhtienton(Double thanhtienton) {
        this.thanhtienton = thanhtienton;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stt != null ? stt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpBaocaoThekho)) {
            return false;
        }
        TmpBaocaoThekho other = (TmpBaocaoThekho) object;
        if ((this.stt == null && other.stt != null) || (this.stt != null && !this.stt.equals(other.stt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.TmpBaocaoThekho[stt=" + stt + "]";
    }

}
