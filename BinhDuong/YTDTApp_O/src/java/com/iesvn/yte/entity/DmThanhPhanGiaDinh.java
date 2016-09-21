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
@Table(name = "DM_THANH_PHAN_GIA_DINH")
@NamedQueries({})
public class DmThanhPhanGiaDinh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_THANH_PHAN_GIA_DINH")
    @SequenceGenerator(name = "DM_THANH_PHAN_GIA_DINH", sequenceName = "DM_THANH_PHAN_GIA_DINH_DMTPGD_", allocationSize = 1)
    @Column(name = "DMTPGD_MASO", nullable = false)
    private Integer dmtpgdMaso;
    @Column(name = "DMTPGD_MA", nullable = false)
    private String dmtpgdMa;
    @Column(name = "DMTPGD_TEN")
    private String dmtpgdTen;
    @Column(name = "DMTPGD_NGAYGIOCN")
    private Double dmtpgdNgaygiocn;
    @Column(name = "DMTPGD_DT")
    private Boolean dmtpgdDt;
    @Column(name = "DMTPGD_QL")
    private Boolean dmtpgdQl;
    @Column(name = "DMTPGD_DP")
    private Boolean dmtpgdDp;

    public DmThanhPhanGiaDinh() {
    }

    public DmThanhPhanGiaDinh(Integer dmtpgdMaso) {
        this.dmtpgdMaso = dmtpgdMaso;
    }

    public DmThanhPhanGiaDinh(Integer dmtpgdMaso, String dmtpgdMa) {
        this.dmtpgdMaso = dmtpgdMaso;
        this.dmtpgdMa = dmtpgdMa;
    }

    public Integer getDmtpgdMaso() {
        return dmtpgdMaso;
    }

    public void setDmtpgdMaso(Integer dmtpgdMaso) {
        this.dmtpgdMaso = dmtpgdMaso;
    }

    public String getDmtpgdMa() {
        return dmtpgdMa;
    }

    public void setDmtpgdMa(String dmtpgdMa) {
        this.dmtpgdMa = dmtpgdMa;
    }

    public String getDmtpgdTen() {
        return dmtpgdTen;
    }

    public void setDmtpgdTen(String dmtpgdTen) {
        this.dmtpgdTen = dmtpgdTen;
    }

    public Double getDmtpgdNgaygiocn() {
        return dmtpgdNgaygiocn;
    }

    public void setDmtpgdNgaygiocn(Double dmtpgdNgaygiocn) {
        this.dmtpgdNgaygiocn = dmtpgdNgaygiocn;
    }

    public Boolean getDmtpgdDt() {
        return dmtpgdDt;
    }

    public void setDmtpgdDt(Boolean dmtpgdDt) {
        this.dmtpgdDt = dmtpgdDt;
    }

    public Boolean getDmtpgdQl() {
        return dmtpgdQl;
    }

    public void setDmtpgdQl(Boolean dmtpgdQl) {
        this.dmtpgdQl = dmtpgdQl;
    }

    public Boolean getDmtpgdDp() {
        return dmtpgdDp;
    }

    public void setDmtpgdDp(Boolean dmtpgdDp) {
        this.dmtpgdDp = dmtpgdDp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmtpgdMaso != null ? dmtpgdMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmThanhPhanGiaDinh)) {
            return false;
        }
        DmThanhPhanGiaDinh other = (DmThanhPhanGiaDinh) object;
        if ((this.dmtpgdMaso == null && other.dmtpgdMaso != null) || (this.dmtpgdMaso != null && !this.dmtpgdMaso.equals(other.dmtpgdMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmThanhPhanGiaDinh[dmtpgdMaso=" + dmtpgdMaso + "]";
    }

}


