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
@Table(name = "VAI_TRO")
@NamedQueries({})
public class VaiTro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VAI_TRO")
    @SequenceGenerator(name = "VAI_TRO", sequenceName = "VAI_TRO_VAITRO_MASO_SEQ", allocationSize = 1)
    @Column(name = "VAITRO_MASO", nullable = false)
    private Integer vaitroMaso;
    @Column(name = "VAITRO_MA", nullable = false)
    private String vaitroMa;
    @Column(name = "VAITRO_TEN")
    private String vaitroTen;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vaitroMaso")
//    private Collection<NguoiDungVaiTro> nguoiDungVaiTroCollection;

    public VaiTro() {
    }

    public VaiTro(Integer vaitroMaso) {
        this.vaitroMaso = vaitroMaso;
    }

    public VaiTro(Integer vaitroMaso, String vaitroMa) {
        this.vaitroMaso = vaitroMaso;
        this.vaitroMa = vaitroMa;
    }

    public Integer getVaitroMaso() {
        return vaitroMaso;
    }

    public void setVaitroMaso(Integer vaitroMaso) {
        this.vaitroMaso = vaitroMaso;
    }

    public String getVaitroMa() {
        return vaitroMa;
    }

    public void setVaitroMa(String vaitroMa) {
        this.vaitroMa = vaitroMa;
    }

    public String getVaitroTen() {
        return vaitroTen;
    }

    public void setVaitroTen(String vaitroTen) {
        this.vaitroTen = vaitroTen;
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
        hash += (vaitroMaso != null ? vaitroMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VaiTro)) {
            return false;
        }
        VaiTro other = (VaiTro) object;
        if ((this.vaitroMaso == null && other.vaitroMaso != null) || (this.vaitroMaso != null && !this.vaitroMaso.equals(other.vaitroMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.VaiTro[vaitroMaso=" + vaitroMaso + "]";
    }

}


