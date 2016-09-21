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
@Table(name = "DM_KHOA_BC")
@NamedQueries({})
public class DmKhoaBc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_KHOA_BC")
    @SequenceGenerator(name = "DM_KHOA_BC", sequenceName = "DM_KHOA_BC_DMKHOABC_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMKHOABC_MASO", nullable = false)
    private Integer dmkhoabcMaso;
    @Column(name = "DMKHOABC_MA")
    private String dmkhoabcMa;
    @Column(name = "DMKHOABC_MABYT", nullable = false)
    private short dmkhoabcMabyt;
    @Column(name = "DMKHOABC_MABYT3", nullable = false)
    private short dmkhoabcMabyt3;
    @Column(name = "DMKHOABC_TEN", nullable = false)
    private String dmkhoabcTen;
    @Column(name = "DMKHOABC_NHOM")
    private Short dmkhoabcNhom;
    @Column(name = "DMKHOABC_THUTUCBC", nullable = false)
    private short dmkhoabcThutucbc;
    @Column(name = "DMKHOABC_NGAYGIOCN")
    private Double dmkhoabcNgaygiocn;

    public DmKhoaBc() {
    }

    public DmKhoaBc(Integer dmkhoabcMaso) {
        this.dmkhoabcMaso = dmkhoabcMaso;
    }

    public DmKhoaBc(Integer dmkhoabcMaso, short dmkhoabcMabyt, short dmkhoabcMabyt3, String dmkhoabcTen, short dmkhoabcThutucbc) {
        this.dmkhoabcMaso = dmkhoabcMaso;
        this.dmkhoabcMabyt = dmkhoabcMabyt;
        this.dmkhoabcMabyt3 = dmkhoabcMabyt3;
        this.dmkhoabcTen = dmkhoabcTen;
        this.dmkhoabcThutucbc = dmkhoabcThutucbc;
    }

    public Integer getDmkhoabcMaso() {
        return dmkhoabcMaso;
    }

    public void setDmkhoabcMaso(Integer dmkhoabcMaso) {
        this.dmkhoabcMaso = dmkhoabcMaso;
    }

    public String getDmkhoabcMa() {
        return dmkhoabcMa;
    }

    public void setDmkhoabcMa(String dmkhoabcMa) {
        this.dmkhoabcMa = dmkhoabcMa;
    }

    public short getDmkhoabcMabyt() {
        return dmkhoabcMabyt;
    }

    public void setDmkhoabcMabyt(short dmkhoabcMabyt) {
        this.dmkhoabcMabyt = dmkhoabcMabyt;
    }

    public short getDmkhoabcMabyt3() {
        return dmkhoabcMabyt3;
    }

    public void setDmkhoabcMabyt3(short dmkhoabcMabyt3) {
        this.dmkhoabcMabyt3 = dmkhoabcMabyt3;
    }

    public String getDmkhoabcTen() {
        return dmkhoabcTen;
    }

    public void setDmkhoabcTen(String dmkhoabcTen) {
        this.dmkhoabcTen = dmkhoabcTen;
    }

    public Short getDmkhoabcNhom() {
        return dmkhoabcNhom;
    }

    public void setDmkhoabcNhom(Short dmkhoabcNhom) {
        this.dmkhoabcNhom = dmkhoabcNhom;
    }

    public short getDmkhoabcThutucbc() {
        return dmkhoabcThutucbc;
    }

    public void setDmkhoabcThutucbc(short dmkhoabcThutucbc) {
        this.dmkhoabcThutucbc = dmkhoabcThutucbc;
    }

    public Double getDmkhoabcNgaygiocn() {
        return dmkhoabcNgaygiocn;
    }

    public void setDmkhoabcNgaygiocn(Double dmkhoabcNgaygiocn) {
        this.dmkhoabcNgaygiocn = dmkhoabcNgaygiocn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmkhoabcMaso != null ? dmkhoabcMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmKhoaBc)) {
            return false;
        }
        DmKhoaBc other = (DmKhoaBc) object;
        if ((this.dmkhoabcMaso == null && other.dmkhoabcMaso != null) || (this.dmkhoabcMaso != null && !this.dmkhoabcMaso.equals(other.dmkhoabcMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmKhoaBc[dmkhoabcMaso=" + dmkhoabcMaso + "]";
    }

}


