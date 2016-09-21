/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CAU_HINH")
@NamedQueries({})
public class CauHinh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CHBV_MASO", nullable = false)
    private Integer chbvMaso;

     @Column(name = "CHBV_NAME")
    private String chbvName;

    @Column(name = "CHBV_GIATRI")
    private String chbvGiatri;

    @Column(name = "CHBV_NGAYGIOCN")
    private Double chbvNgaygiocn;
    @Column(name = "CHBV_CHON")
    private Boolean chbvChon;

    public CauHinh() {
    }

    public CauHinh(String chbvName) {
        this.chbvName = chbvName;
    }

    public String getChbvName() {
        return chbvName;
    }

    public void setChbvName(String chbvName) {
        this.chbvName = chbvName;
    }

    public String getChbvGiatri() {
        return chbvGiatri;
    }

    public void setChbvGiatri(String chbvGiatri) {
        this.chbvGiatri = chbvGiatri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chbvName != null ? chbvName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauHinh)) {
            return false;
        }
        CauHinh other = (CauHinh) object;
        if ((this.chbvName == null && other.chbvName != null) || (this.chbvName != null && !this.chbvName.equals(other.chbvName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.CauHinh[chbvName=" + chbvName + "]";
    }

    /**
     * @return the chbvNgaygiocn
     */
    public Double getChbvNgaygiocn() {
        return chbvNgaygiocn;
    }

    /**
     * @param chbvNgaygiocn the chbvNgaygiocn to set
     */
    public void setChbvNgaygiocn(Double chbvNgaygiocn) {
        this.chbvNgaygiocn = chbvNgaygiocn;
    }

    /**
     * @return the chbvChon
     */
    public Boolean getChbvChon() {
        return chbvChon;
    }

    /**
     * @param chbvChon the chbvChon to set
     */
    public void setChbvChon(Boolean chbvChon) {
        this.chbvChon = chbvChon;
    }

    /**
     * @return the chbvMaso
     */
    public Integer getChbvMaso() {
        return chbvMaso;
    }

    /**
     * @param chbvMaso the chbvMaso to set
     */
    public void setChbvMaso(Integer chbvMaso) {
        this.chbvMaso = chbvMaso;
    }
}


