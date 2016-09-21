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
@Table(name = "DM_TRANG_THAI_HO_SO")
@NamedQueries({})
public class DmTrangThaiHoSo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_TRANG_THAI_HO_SO")
    @SequenceGenerator(name = "DM_TRANG_THAI_HO_SO", sequenceName = "DM_TRANG_THAI_HO_SO_DMTTHS_MAS", allocationSize = 1)
    @Column(name = "DMTTHS_MASO", nullable = false)
    private Integer dmtthsMaso;
    @Column(name = "DMTTHS_MA")
    private String dmtthsMa;
    @Column(name = "DMTTHS_TEN")
    private String dmtthsTen;
    @Column(name = "DMTTHS_GHICHU")
    private String dmtthsGhichu;
    @Column(name = "DMTTHS_DT")
    private Boolean dmtthsDt;
    @Column(name = "DMTTHS_QL")
    private Boolean dmtthsQl;
    @Column(name = "DMTTHS_DP")
    private Boolean dmtthsDp;

    public DmTrangThaiHoSo() {
    }

    public DmTrangThaiHoSo(Integer dmtthsMaso) {
        this.dmtthsMaso = dmtthsMaso;
    }

    public Integer getDmtthsMaso() {
        return dmtthsMaso;
    }

    public void setDmtthsMaso(Integer dmtthsMaso) {
        this.dmtthsMaso = dmtthsMaso;
    }

    public String getDmtthsMa() {
        return dmtthsMa;
    }

    public void setDmtthsMa(String dmtthsMa) {
        this.dmtthsMa = dmtthsMa;
    }

    public String getDmtthsTen() {
        return dmtthsTen;
    }

    public void setDmtthsTen(String dmtthsTen) {
        this.dmtthsTen = dmtthsTen;
    }

    public String getDmtthsGhichu() {
        return dmtthsGhichu;
    }

    public void setDmtthsGhichu(String dmtthsGhichu) {
        this.dmtthsGhichu = dmtthsGhichu;
    }

    public Boolean getDmtthsDt() {
        return dmtthsDt;
    }

    public void setDmtthsDt(Boolean dmtthsDt) {
        this.dmtthsDt = dmtthsDt;
    }

    public Boolean getDmtthsQl() {
        return dmtthsQl;
    }

    public void setDmtthsQl(Boolean dmtthsQl) {
        this.dmtthsQl = dmtthsQl;
    }

    public Boolean getDmtthsDp() {
        return dmtthsDp;
    }

    public void setDmtthsDp(Boolean dmtthsDp) {
        this.dmtthsDp = dmtthsDp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmtthsMaso != null ? dmtthsMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmTrangThaiHoSo)) {
            return false;
        }
        DmTrangThaiHoSo other = (DmTrangThaiHoSo) object;
        if ((this.dmtthsMaso == null && other.dmtthsMaso != null) || (this.dmtthsMaso != null && !this.dmtthsMaso.equals(other.dmtthsMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmTrangThaiHoSo[dmtthsMaso=" + dmtthsMaso + "]";
    }

}


