/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ThanhDo
 */
@Entity  @org.hibernate.annotations.Proxy(lazy=false)

@Table(name = "HSBA_KHOA")
@NamedQueries({})
public class HsbaKhoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_KHOA")
    @SequenceGenerator(name = "HSBA_KHOA", sequenceName = "HSBA_KHOA_HSBAKHOA_MASO_SEQ", allocationSize = 1)

    @Column(name = "HSBAKHOA_MASO", nullable = false)
    private Integer hsbakhoaMaso;
    @Column(name = "HSBAKHOA_LAN", nullable = false)
    private Integer hsbakhoaLan;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "KHOA_MA", referencedColumnName = "DMKHOA_MASO")
   @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa khoaMa;
    @JoinColumn(name = "DMTANG_MASO", referencedColumnName = "DMTANG_MASO")
   @ManyToOne (fetch=FetchType.LAZY)
    private DmTang dmtangMaso;

    public HsbaKhoa() {
    }

    public HsbaKhoa(Integer hsbakhoaMaso) {
        this.hsbakhoaMaso = hsbakhoaMaso;
    }

    public HsbaKhoa(Integer hsbakhoaMaso, int hsbakhoaLan) {
        this.hsbakhoaMaso = hsbakhoaMaso;
        this.hsbakhoaLan = hsbakhoaLan;
    }

    public Integer getHsbakhoaMaso() {
        return hsbakhoaMaso;
    }

    public void setHsbakhoaMaso(Integer hsbakhoaMaso) {
        this.hsbakhoaMaso = hsbakhoaMaso;
    }

    public Integer getHsbakhoaLan() {
        return hsbakhoaLan;
    }

    public void setHsbakhoaLan(Integer hsbakhoaLan) {
        this.hsbakhoaLan = hsbakhoaLan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbakhoaMaso != null ? hsbakhoaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaKhoa)) {
            return false;
        }
        HsbaKhoa other = (HsbaKhoa) object;
        if ((this.hsbakhoaMaso == null && other.hsbakhoaMaso != null) || (this.hsbakhoaMaso != null && !this.hsbakhoaMaso.equals(other.hsbakhoaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaKhoa[hsbakhoaMaso=" + hsbakhoaMaso + "]";
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DmKhoa getKhoaMa() {
        return khoaMa;
    }

    public void setKhoaMa(DmKhoa khoaMa) {
        this.khoaMa = khoaMa;
    }

    public DmTang getDmtangMaso() {
        return dmtangMaso;
    }

    public void setDmtangMaso(DmTang dmtangMaso) {
        this.dmtangMaso = dmtangMaso;
    }
}
