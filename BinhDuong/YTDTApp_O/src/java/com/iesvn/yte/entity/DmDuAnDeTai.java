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
@Table(name = "DM_DU_AN_DE_TAI")
@NamedQueries({})
public class DmDuAnDeTai implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_DU_AN_DE_TAI")
    @SequenceGenerator(name = "DM_DU_AN_DE_TAI", sequenceName = "DM_DU_AN_DE_TAI_DMDUANDETAI_MA", allocationSize = 1)
    @Column(name = "DMDUANDETAI_MASO", nullable = false)
    private Integer dmduandetaiMaso;
    @Column(name = "DMDUANDETAI_MA")
    private String dmduandetaiMa;
    @Column(name = "DMDUANDETAI_TEN")
    private String dmduandetaiTen;
    @Column(name = "DMDUANDETAI_NGAYGIOCN")
    private Double dmduandetaiNgaygiocn;
    @Column(name = "DMDUANDETAI_DT")
    private Boolean dmduandetaiDt;
    @Column(name = "DMDUANDETAI_DP")
    private Boolean dmduandetaiDp;
    @Column(name = "DMDUANDETAI_QL")
    private Boolean dmduandetaiQl;

    public DmDuAnDeTai() {
    }

    public DmDuAnDeTai(Integer dmduandetaiMaso) {
        this.dmduandetaiMaso = dmduandetaiMaso;
    }

    public Integer getDmduandetaiMaso() {
        return dmduandetaiMaso;
    }

    public void setDmduandetaiMaso(Integer dmduandetaiMaso) {
        this.dmduandetaiMaso = dmduandetaiMaso;
    }

    public String getDmduandetaiMa() {
        return dmduandetaiMa;
    }

    public void setDmduandetaiMa(String dmduandetaiMa) {
        this.dmduandetaiMa = dmduandetaiMa;
    }

    public String getDmduandetaiTen() {
        return dmduandetaiTen;
    }

    public void setDmduandetaiTen(String dmduandetaiTen) {
        this.dmduandetaiTen = dmduandetaiTen;
    }

    public Double getDmduandetaiNgaygiocn() {
        return dmduandetaiNgaygiocn;
    }

    public void setDmduandetaiNgaygiocn(Double dmduandetaiNgaygiocn) {
        this.dmduandetaiNgaygiocn = dmduandetaiNgaygiocn;
    }

    public Boolean getDmduandetaiDt() {
        return dmduandetaiDt;
    }

    public void setDmduandetaiDt(Boolean dmduandetaiDt) {
        this.dmduandetaiDt = dmduandetaiDt;
    }

    public Boolean getDmduandetaiDp() {
        return dmduandetaiDp;
    }

    public void setDmduandetaiDp(Boolean dmduandetaiDp) {
        this.dmduandetaiDp = dmduandetaiDp;
    }

    public Boolean getDmduandetaiQl() {
        return dmduandetaiQl;
    }

    public void setDmduandetaiQl(Boolean dmduandetaiQl) {
        this.dmduandetaiQl = dmduandetaiQl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmduandetaiMaso != null ? dmduandetaiMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmDuAnDeTai)) {
            return false;
        }
        DmDuAnDeTai other = (DmDuAnDeTai) object;
        if ((this.dmduandetaiMaso == null && other.dmduandetaiMaso != null) || (this.dmduandetaiMaso != null && !this.dmduandetaiMaso.equals(other.dmduandetaiMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmDuAnDeTai[dmduandetaiMaso=" + dmduandetaiMaso + "]";
    }

}


