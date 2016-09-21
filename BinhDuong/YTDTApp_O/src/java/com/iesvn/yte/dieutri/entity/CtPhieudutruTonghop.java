/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CT_PHIEUDUTRU_TONGHOP")
@NamedQueries({
    @NamedQuery(name = "CtPhieudutruTonghop.findAll", query = "SELECT c FROM CtPhieudutruTonghop c"),
    @NamedQuery(name = "CtPhieudutruTonghop.findByCtdtMa", query = "SELECT c FROM CtPhieudutruTonghop c WHERE c.ctdtMa = :ctdtMa"),
    @NamedQuery(name = "CtPhieudutruTonghop.findByDanhsachMaso", query = "SELECT c FROM CtPhieudutruTonghop c WHERE c.danhsachMaso = :danhsachMaso"),
    @NamedQuery(name = "CtPhieudutruTonghop.findByLoai", query = "SELECT c FROM CtPhieudutruTonghop c WHERE c.loai = :loai")})
public class CtPhieudutruTonghop implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CTDT_MA")
    private Integer ctdtMa;
    @Basic(optional = false)
    @Column(name = "DANHSACH_MASO")
    private String danhsachMaso;
    @Basic(optional = false)
    @Column(name = "LOAI")
    private String loai;

    public CtPhieudutruTonghop() {
    }

    public CtPhieudutruTonghop(Integer ctdtMa) {
        this.ctdtMa = ctdtMa;
    }

    public CtPhieudutruTonghop(Integer ctdtMa, String danhsachMaso, String loai) {
        this.ctdtMa = ctdtMa;
        this.danhsachMaso = danhsachMaso;
        this.loai = loai;
    }

    public Integer getCtdtMa() {
        return ctdtMa;
    }

    public void setCtdtMa(Integer ctdtMa) {
        this.ctdtMa = ctdtMa;
    }

    public String getDanhsachMaso() {
        return danhsachMaso;
    }

    public void setDanhsachMaso(String danhsachMaso) {
        this.danhsachMaso = danhsachMaso;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctdtMa != null ? ctdtMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtPhieudutruTonghop)) {
            return false;
        }
        CtPhieudutruTonghop other = (CtPhieudutruTonghop) object;
        if ((this.ctdtMa == null && other.ctdtMa != null) || (this.ctdtMa != null && !this.ctdtMa.equals(other.ctdtMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.CtPhieudutruTonghop[ctdtMa=" + ctdtMa + "]";
    }

}
