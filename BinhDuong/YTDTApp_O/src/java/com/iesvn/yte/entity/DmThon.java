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
@Table(name = "DM_THON")
@NamedQueries({})
public class DmThon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_THON")
    @SequenceGenerator(name = "DM_THON", sequenceName = "DM_THON_DMTHON_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMTHON_MASO", nullable = false)
    private Integer dmthonMaso;
    @Column(name = "DMTHON_MA")
    private String dmthonMa;
    @Column(name = "DMTHON_TEN")
    private String dmthonTen;
    @Column(name = "DMTHON_NGAYGIOCN")
    private Double dmthonNgaygiocn;
    @Column(name = "DMTHON_CHON")
    private Boolean dmthonChon;
    @JoinColumn(name = "DMXA_MASO", referencedColumnName = "DMXA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmXa dmxaMaso;


    public DmThon() {
    }

    public DmThon(Integer dmthonMaso) {
        this.dmthonMaso = dmthonMaso;
    }

    public Integer getDmthonMaso() {
        return dmthonMaso;
    }

    public void setDmthonMaso(Integer dmthonMaso) {
        this.dmthonMaso = dmthonMaso;
    }

    public String getDmthonMa() {
        return dmthonMa;
    }

    public void setDmthonMa(String dmthonMa) {
        this.dmthonMa = dmthonMa;
    }

    public String getDmthonTen() {
        return dmthonTen;
    }

    public void setDmthonTen(String dmthonTen) {
        this.dmthonTen = dmthonTen;
    }

    public Double getDmthonNgaygiocn() {
        return dmthonNgaygiocn;
    }

    public void setDmthonNgaygiocn(Double dmthonNgaygiocn) {
        this.dmthonNgaygiocn = dmthonNgaygiocn;
    }

    public Boolean getDmthonChon() {
        return dmthonChon;
    }

    public void setDmthonChon(Boolean dmthonChon) {
        this.dmthonChon = dmthonChon;
    }

    public DmXa getDmxaMaso(boolean create) {
if(create && dmxaMaso == null) dmxaMaso = new DmXa();
return dmxaMaso;
}
    public DmXa getDmxaMaso() {
        return dmxaMaso;
    }

    public void setDmxaMaso(DmXa dmxaMaso) {
        this.dmxaMaso = dmxaMaso;
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmthonMaso != null ? dmthonMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmThon)) {
            return false;
        }
        DmThon other = (DmThon) object;
        if ((this.dmthonMaso == null && other.dmthonMaso != null) || (this.dmthonMaso != null && !this.dmthonMaso.equals(other.dmthonMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmThon[dmthonMaso=" + dmthonMaso + "]";
    }

}


