/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_LOAI_HO_SO")
@NamedQueries({})
public class DmLoaiHoSo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_HO_SO")
    @SequenceGenerator(name = "DM_LOAI_HO_SO", sequenceName = "DM_LOAI_HO_SO_SEQ", allocationSize = 1)
    @Column(name = "DMLOAIHOSO_MASO", nullable = false)
    private Integer dmloaihosoMaso;
    @Column(name = "DMLOAIHOSO_MA")
    private String dmloaihosoMa;
    @Column(name = "DMLOAIHOSO_TEN")
    private String dmloaihosoTen;
    @Column(name = "DMLOAIHOSO_GHICHU")
    private String dmloaihosoGhichu;
    @Column(name = "DMLOAIHOSO_NGAYGIOCN")
    private Double dmloaihosoNgaygiocn;
    @Column(name = "DMLOAIHOSO_DT")
    private Boolean dmloaihosoDt;
    @Column(name = "DMLOAIHOSO_QL")
    private Boolean dmloaihosoQl;
    @Column(name = "DMLOAIHOSO_DP")
    private Boolean dmloaihosoDp;

    public DmLoaiHoSo() {
    }

    public DmLoaiHoSo(Integer dmloaihosoMaso) {
        this.dmloaihosoMaso = dmloaihosoMaso;
    }

    public Integer getDmloaihosoMaso() {
        return dmloaihosoMaso;
    }

    public void setDmloaihosoMaso(Integer dmloaihosoMaso) {
        this.dmloaihosoMaso = dmloaihosoMaso;
    }

    public String getDmloaihosoMa() {
        return dmloaihosoMa;
    }

    public void setDmloaihosoMa(String dmloaihosoMa) {
        this.dmloaihosoMa = dmloaihosoMa;
    }

    public String getDmloaihosoTen() {
        return dmloaihosoTen;
    }

    public void setDmloaihosoTen(String dmloaihosoTen) {
        this.dmloaihosoTen = dmloaihosoTen;
    }

    public String getDmloaihosoGhichu() {
        return dmloaihosoGhichu;
    }

    public void setDmloaihosoGhichu(String dmloaihosoGhichu) {
        this.dmloaihosoGhichu = dmloaihosoGhichu;
    }

    public Double getDmloaihosoNgaygiocn() {
        return dmloaihosoNgaygiocn;
    }

    public void setDmloaihosoNgaygiocn(Double dmloaihosoNgaygiocn) {
        this.dmloaihosoNgaygiocn = dmloaihosoNgaygiocn;
    }

    public Boolean getDmloaihosoDt() {
        return dmloaihosoDt;
    }

    public void setDmloaihosoDt(Boolean dmloaihosoDt) {
        this.dmloaihosoDt = dmloaihosoDt;
    }

    public Boolean getDmloaihosoQl() {
        return dmloaihosoQl;
    }

    public void setDmloaihosoQl(Boolean dmloaihosoQl) {
        this.dmloaihosoQl = dmloaihosoQl;
    }

    public Boolean getDmloaihosoDp() {
        return dmloaihosoDp;
    }

    public void setDmloaihosoDp(Boolean dmloaihosoDp) {
        this.dmloaihosoDp = dmloaihosoDp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmloaihosoMaso != null ? dmloaihosoMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmLoaiHoSo)) {
            return false;
        }
        DmLoaiHoSo other = (DmLoaiHoSo) object;
        if ((this.dmloaihosoMaso == null && other.dmloaihosoMaso != null) || (this.dmloaihosoMaso != null && !this.dmloaihosoMaso.equals(other.dmloaihosoMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmLoaiHoSo[dmloaihosoMaso=" + dmloaihosoMaso + "]";
    }

}


