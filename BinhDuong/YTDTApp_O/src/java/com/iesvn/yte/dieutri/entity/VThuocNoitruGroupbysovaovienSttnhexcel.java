/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;
/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "V_THUOC_NTRU_GBSOVV_STTNHEXCEL")
@NamedQueries({
    @NamedQuery(name = "VThuocNoitruGroupbysovaovienSttnhexcel.findAll", query = "SELECT v FROM VThuocNoitruGroupbysovaovienSttnhexcel v")})
public class VThuocNoitruGroupbysovaovienSttnhexcel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Lob
    @Column(name = "Id")
    private byte[] id;
    @Basic(optional = false)
    @Column(name = "HSBA_SOVAOVIEN")
    private String hsbaSovaovien;
    @Column(name = "stt_nh")
    private Short sttNh;
    @Column(name = "tong_tien")
    private Double tongTien;
    @Column(name = "tien_bn")
    private Double tienBn;

    public VThuocNoitruGroupbysovaovienSttnhexcel() {
    }

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public String getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(String hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public Short getSttNh() {
        return sttNh;
    }

    public void setSttNh(Short sttNh) {
        this.sttNh = sttNh;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Double getTienBn() {
        return tienBn;
    }

    public void setTienBn(Double tienBn) {
        this.tienBn = tienBn;
    }

}
