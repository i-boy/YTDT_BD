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
@Table(name = "DM_QUY")
@NamedQueries({})
public class DmQuy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_QUY_DMQUY")
    @SequenceGenerator(name = "DM_QUY_DMQUY", sequenceName = "DM_QUY_DMQUY_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMQUY_MASO", nullable = false)
    private Short dmquyMaso;
    @Column(name = "DMQUY_MA", nullable = false)
    private String dmquyMa;
    @Column(name = "DMQUY_TEN")
    private String dmquyTen;
    @Column(name = "DMQUY_NGAYGIOCN")
    private Double dmquyNgaygiocn;
    @Column(name = "DMQUY_CHON")
    private Boolean dmquyChon;

    public DmQuy() {
    }

    public DmQuy(Short dmquyMaso) {
        this.dmquyMaso = dmquyMaso;
    }

    public DmQuy(Short dmquyMaso, String dmquyMa) {
        this.dmquyMaso = dmquyMaso;
        this.dmquyMa = dmquyMa;
    }

    public Short getDmquyMaso() {
        return dmquyMaso;
    }

    public void setDmquyMaso(Short dmquyMaso) {
        this.dmquyMaso = dmquyMaso;
    }

    public String getDmquyMa() {
        return dmquyMa;
    }

    public void setDmquyMa(String dmquyMa) {
        this.dmquyMa = dmquyMa;
    }

    public String getDmquyTen() {
        return dmquyTen;
    }

    public void setDmquyTen(String dmquyTen) {
        this.dmquyTen = dmquyTen;
    }

    public Double getDmquyNgaygiocn() {
        return dmquyNgaygiocn;
    }

    public void setDmquyNgaygiocn(Double dmquyNgaygiocn) {
        this.dmquyNgaygiocn = dmquyNgaygiocn;
    }

    public Boolean getDmquyChon() {
        return dmquyChon;
    }

    public void setDmquyChon(Boolean dmquyChon) {
        this.dmquyChon = dmquyChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmquyMaso != null ? dmquyMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmQuy)) {
            return false;
        }
        DmQuy other = (DmQuy) object;
        if ((this.dmquyMaso == null && other.dmquyMaso != null) || (this.dmquyMaso != null && !this.dmquyMaso.equals(other.dmquyMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmQuy[dmquyMaso=" + dmquyMaso + "]";
    }

}


