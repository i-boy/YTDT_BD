/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;
/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "V_TONKHO_KHOCHINH")
@NamedQueries({
    @NamedQuery(name = "VTonkhoKhochinh.findAll", query = "SELECT v FROM VTonkhoKhochinh v")})
public class VTonkhoKhochinh implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id")
    private String id;
    @Basic(optional = false)
    @Column(name = "NGAYTHANG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaythang;
    @Basic(optional = false)
    @Column(name = "CHUNGTUNXT")
    private String chungtunxt;
    @Column(name = "PHIEUNHAPKHO")
    private String phieunhapkho;
    @Basic(optional = false)
    @Column(name = "LOAIPHIEU")
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

    public VTonkhoKhochinh() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
