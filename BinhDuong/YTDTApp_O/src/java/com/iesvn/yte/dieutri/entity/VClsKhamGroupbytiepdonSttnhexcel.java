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
@Table(name = "V_CLS_KHAM_GBTIEPDON_STTNH")
@NamedQueries({
    @NamedQuery(name = "VClsKhamGroupbytiepdonSttnhexcel.findAll", query = "SELECT v FROM VClsKhamGroupbytiepdonSttnhexcel v")})
public class VClsKhamGroupbytiepdonSttnhexcel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Lob
    @Column(name = "Id")
    private byte[] id;
    @Basic(optional = false)
    @Column(name = "TIEPDON_MA")
    private String tiepdonMa;
    @Column(name = "stt_nh")
    private Integer sttNh;
    @Column(name = "tong_tien")
    private Double tongTien;
    @Column(name = "tien_bn")
    private Double tienBn;

    public VClsKhamGroupbytiepdonSttnhexcel() {
    }

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public String getTiepdonMa() {
        return tiepdonMa;
    }

    public void setTiepdonMa(String tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

    public Integer getSttNh() {
        return sttNh;
    }

    public void setSttNh(Integer sttNh) {
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
