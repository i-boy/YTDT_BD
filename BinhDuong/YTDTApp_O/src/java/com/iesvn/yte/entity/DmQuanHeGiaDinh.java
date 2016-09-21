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
@Table(name = "DM_QUAN_HE_GIA_DINH")
@NamedQueries({})
public class DmQuanHeGiaDinh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_QUAN_HE_GIA_DINH")
    @SequenceGenerator(name = "DM_QUAN_HE_GIA_DINH", sequenceName = "DM_QUAN_HE_GIA_DINH_DMQHGD_MAS", allocationSize = 1)
    @Column(name = "DMQHGD_MASO", nullable = false)
    private Integer dmqhgdMaso;
    @Column(name = "DMQHGD_MA")
    private String dmqhgdMa;
    @Column(name = "DMQHGD_TEN", nullable = false)
    private String dmqhgdTen;
    @Column(name = "DMQHGD_DT")
    private Boolean dmqhgdDt;
    @Column(name = "DMQHGD_QL")
    private Boolean dmqhgdQl;
    @Column(name = "DMQHGD_DP")
    private Boolean dmqhgdDp;
    @Column(name = "DMQHGD_NGAYGIOCN")
    private Double dmqhgdNgaygiocn;

    public DmQuanHeGiaDinh() {
    }

    public DmQuanHeGiaDinh(Integer dmqhgdMaso) {
        this.dmqhgdMaso = dmqhgdMaso;
    }

    public DmQuanHeGiaDinh(Integer dmqhgdMaso, String dmqhgdTen) {
        this.dmqhgdMaso = dmqhgdMaso;
        this.dmqhgdTen = dmqhgdTen;
    }

    public Integer getDmqhgdMaso() {
        return dmqhgdMaso;
    }

    public void setDmqhgdMaso(Integer dmqhgdMaso) {
        this.dmqhgdMaso = dmqhgdMaso;
    }

    public String getDmqhgdMa() {
        return dmqhgdMa;
    }

    public void setDmqhgdMa(String dmqhgdMa) {
        this.dmqhgdMa = dmqhgdMa;
    }

    public String getDmqhgdTen() {
        return dmqhgdTen;
    }

    public void setDmqhgdTen(String dmqhgdTen) {
        this.dmqhgdTen = dmqhgdTen;
    }

    public Boolean getDmqhgdDt() {
        return dmqhgdDt;
    }

    public void setDmqhgdDt(Boolean dmqhgdDt) {
        this.dmqhgdDt = dmqhgdDt;
    }

    public Boolean getDmqhgdQl() {
        return dmqhgdQl;
    }

    public void setDmqhgdQl(Boolean dmqhgdQl) {
        this.dmqhgdQl = dmqhgdQl;
    }

    public Boolean getDmqhgdDp() {
        return dmqhgdDp;
    }

    public void setDmqhgdDp(Boolean dmqhgdDp) {
        this.dmqhgdDp = dmqhgdDp;
    }

    public Double getDmqhgdNgaygiocn() {
        return dmqhgdNgaygiocn;
    }

    public void setDmqhgdNgaygiocn(Double dmqhgdNgaygiocn) {
        this.dmqhgdNgaygiocn = dmqhgdNgaygiocn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmqhgdMaso != null ? dmqhgdMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmQuanHeGiaDinh)) {
            return false;
        }
        DmQuanHeGiaDinh other = (DmQuanHeGiaDinh) object;
        if ((this.dmqhgdMaso == null && other.dmqhgdMaso != null) || (this.dmqhgdMaso != null && !this.dmqhgdMaso.equals(other.dmqhgdMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmQuanHeGiaDinh[dmqhgdMaso=" + dmqhgdMaso + "]";
    }

}


