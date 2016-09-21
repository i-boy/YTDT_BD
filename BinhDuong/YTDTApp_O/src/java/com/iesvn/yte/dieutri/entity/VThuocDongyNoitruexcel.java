/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;
/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "V_THUOC_DONGY_NOITRUEXCEL")
@NamedQueries({
    @NamedQuery(name = "VThuocDongyNoitruexcel.findAll", query = "SELECT v FROM VThuocDongyNoitruexcel v")})
public class VThuocDongyNoitruexcel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Id")
    private String id;
    @Basic(optional = false)
    @Column(name = "HSBA_SOVAOVIEN")
    private String hsbaSovaovien;
    @Basic(optional = false)
    @Column(name = "stt_nh")
    private int sttNh;
    @Column(name = "tien_bn")
    private Double tienBn;
    @Column(name = "tong_tien")
    private Double tongTien;

    public VThuocDongyNoitruexcel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(String hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public int getSttNh() {
        return sttNh;
    }

    public void setSttNh(int sttNh) {
        this.sttNh = sttNh;
    }

    public Double getTienBn() {
        return tienBn;
    }

    public void setTienBn(Double tienBn) {
        this.tienBn = tienBn;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

}
