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
@Table(name = "DM_LOAI_CAN_BO")
@NamedQueries({})
public class DmLoaiCanBo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_CAN_BO")
    @SequenceGenerator(name = "DM_LOAI_CAN_BO", sequenceName = "DM_LOAI_CAN_BO_DMLOAICANBO_MAS", allocationSize = 1)
    @Column(name = "DMLOAICANBO_MASO", nullable = false)
    private Integer dmloaicanboMaso;
    @Column(name = "DMLOAICANBO_MA")
    private String dmloaicanboMa;
    @Column(name = "DMLOAICANBO_TEN")
    private String dmloaicanboTen;
    @Column(name = "DMLOAICANBO_NGAYGIOCN")
    private Double dmloaicanboNgaygiocn;
    @Column(name = "DMLOAICANBO_DT")
    private Boolean dmloaicanboDt;
    @Column(name = "DMLOAICANBO_QL")
    private Boolean dmloaicanboQl;
    @Column(name = "DMLOAICANBO_DP")
    private Boolean dmloaicanboDp;

    public DmLoaiCanBo() {
    }

    public DmLoaiCanBo(Integer dmloaicanboMaso) {
        this.dmloaicanboMaso = dmloaicanboMaso;
    }

    public Integer getDmloaicanboMaso() {
        return dmloaicanboMaso;
    }

    public void setDmloaicanboMaso(Integer dmloaicanboMaso) {
        this.dmloaicanboMaso = dmloaicanboMaso;
    }

    public String getDmloaicanboMa() {
        return dmloaicanboMa;
    }

    public void setDmloaicanboMa(String dmloaicanboMa) {
        this.dmloaicanboMa = dmloaicanboMa;
    }

    public String getDmloaicanboTen() {
        return dmloaicanboTen;
    }

    public void setDmloaicanboTen(String dmloaicanboTen) {
        this.dmloaicanboTen = dmloaicanboTen;
    }

    public Double getDmloaicanboNgaygiocn() {
        return dmloaicanboNgaygiocn;
    }

    public void setDmloaicanboNgaygiocn(Double dmloaicanboNgaygiocn) {
        this.dmloaicanboNgaygiocn = dmloaicanboNgaygiocn;
    }

    public Boolean getDmloaicanboDt() {
        return dmloaicanboDt;
    }

    public void setDmloaicanboDt(Boolean dmloaicanboDt) {
        this.dmloaicanboDt = dmloaicanboDt;
    }

    public Boolean getDmloaicanboQl() {
        return dmloaicanboQl;
    }

    public void setDmloaicanboQl(Boolean dmloaicanboQl) {
        this.dmloaicanboQl = dmloaicanboQl;
    }

    public Boolean getDmloaicanboDp() {
        return dmloaicanboDp;
    }

    public void setDmloaicanboDp(Boolean dmloaicanboDp) {
        this.dmloaicanboDp = dmloaicanboDp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmloaicanboMaso != null ? dmloaicanboMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmLoaiCanBo)) {
            return false;
        }
        DmLoaiCanBo other = (DmLoaiCanBo) object;
        if ((this.dmloaicanboMaso == null && other.dmloaicanboMaso != null) || (this.dmloaicanboMaso != null && !this.dmloaicanboMaso.equals(other.dmloaicanboMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmLoaiCanBo[dmloaicanboMaso=" + dmloaicanboMaso + "]";
    }

}


