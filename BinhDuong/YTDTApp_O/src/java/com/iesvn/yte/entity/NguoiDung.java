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
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "NGUOI_DUNG")
@NamedQueries({})
public class NguoiDung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NGUOI_DUNG_ND")
    @SequenceGenerator(name = "NGUOI_DUNG_ND", sequenceName = "NGUOI_DUNG_ND_MASO_SEQ", allocationSize = 1)
    @Column(name = "ND_MASO", nullable = false)
    private Integer ndMaso;
    @Column(name = "ND_TENDANGNHAP", nullable = false)
    private String ndTendangnhap;
    @Column(name = "ND_MADANGNHAP")
    private String ndMadangnhap;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ndMaso")
//    private Collection<NguoiDungVaiTro> nguoiDungVaiTroCollection;
    public NguoiDung() {
    }

    public NguoiDung(Integer ndMaso) {
        this.ndMaso = ndMaso;
    }

    public NguoiDung(Integer ndMaso, String ndTendangnhap) {
        this.ndMaso = ndMaso;
        this.ndTendangnhap = ndTendangnhap;
    }

    public Integer getNdMaso() {
        return ndMaso;
    }

    public void setNdMaso(Integer ndMaso) {
        this.ndMaso = ndMaso;
    }

    public String getNdTendangnhap() {
        return ndTendangnhap;
    }

    public void setNdTendangnhap(String ndTendangnhap) {
        this.ndTendangnhap = ndTendangnhap;
    }

    public String getNdMadangnhap() {
        return ndMadangnhap;
    }

    public void setNdMadangnhap(String ndMadangnhap) {
        this.ndMadangnhap = ndMadangnhap;
    }

//    public Collection<NguoiDungVaiTro> getNguoiDungVaiTroCollection() {
//        return nguoiDungVaiTroCollection;
//    }
//
//    public void setNguoiDungVaiTroCollection(Collection<NguoiDungVaiTro> nguoiDungVaiTroCollection) {
//        this.nguoiDungVaiTroCollection = nguoiDungVaiTroCollection;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ndMaso != null ? ndMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NguoiDung)) {
            return false;
        }
        NguoiDung other = (NguoiDung) object;
        if ((this.ndMaso == null && other.ndMaso != null) || (this.ndMaso != null && !this.ndMaso.equals(other.ndMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.NguoiDung[ndMaso=" + ndMaso + "]";
    }
}


