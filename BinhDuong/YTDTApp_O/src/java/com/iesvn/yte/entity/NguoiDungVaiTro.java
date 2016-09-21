/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "NGUOI_DUNG_VAI_TRO")
@NamedQueries({})
public class NguoiDungVaiTro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NGUOI_DUNG_VAI_TRO")
    @SequenceGenerator(name = "NGUOI_DUNG_VAI_TRO", sequenceName = "NGUOI_DUNG_VAI_TRO_NDVAITRO_MA", allocationSize = 1)
    @Column(name = "NDVAITRO_MASO", nullable = false)
    private Integer ndvaitroMaso;
    @JoinColumn(name = "ND_MASO", referencedColumnName = "ND_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private NguoiDung ndMaso;
    @JoinColumn(name = "VAITRO_MASO", referencedColumnName = "VAITRO_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private VaiTro vaitroMaso;

    public NguoiDungVaiTro() {
    }

    public NguoiDungVaiTro(Integer ndvaitroMaso) {
        this.ndvaitroMaso = ndvaitroMaso;
    }

    public Integer getNdvaitroMaso() {
        return ndvaitroMaso;
    }

    public void setNdvaitroMaso(Integer ndvaitroMaso) {
        this.ndvaitroMaso = ndvaitroMaso;
    }

    public NguoiDung getNdMaso(boolean create) {
if(create && ndMaso == null) ndMaso = new NguoiDung();
return ndMaso;
}
    public NguoiDung getNdMaso() {
        return ndMaso;
    }

    public void setNdMaso(NguoiDung ndMaso) {
        this.ndMaso = ndMaso;
    }

    public VaiTro getVaitroMaso(boolean create) {
if(create && vaitroMaso == null) vaitroMaso = new VaiTro();
return vaitroMaso;
}
    public VaiTro getVaitroMaso() {
        return vaitroMaso;
    }

    public void setVaitroMaso(VaiTro vaitroMaso) {
        this.vaitroMaso = vaitroMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ndvaitroMaso != null ? ndvaitroMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NguoiDungVaiTro)) {
            return false;
        }
        NguoiDungVaiTro other = (NguoiDungVaiTro) object;
        if ((this.ndvaitroMaso == null && other.ndvaitroMaso != null) || (this.ndvaitroMaso != null && !this.ndvaitroMaso.equals(other.ndvaitroMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.NguoiDungVaiTro[ndvaitroMaso=" + ndvaitroMaso + "]";
    }

}


