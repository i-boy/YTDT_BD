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
@Table(name = "DM_HINH_THUC_XU_LY")
@NamedQueries({})
public class DmHinhThucXuLy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_HINH_THUC_XU_LY")
    @SequenceGenerator(name = "DM_HINH_THUC_XU_LY", sequenceName = "DM_HINH_THUC_XU_LY_DMHINHTHUCX", allocationSize = 1)
    @Column(name = "DMHINHTHUCXULY_MASO", nullable = false)
    private Integer dmhinhthucxulyMaso;
    @Column(name = "DMHINHTHUCXULY_MA")
    private String dmhinhthucxulyMa;
    @Column(name = "DMHINHTHUCXULY_TEN", nullable = false)
    private String dmhinhthucxulyTen;
    @Column(name = "DMHINHTHUCXULY_NGAYGIOCN")
    private Double dmhinhthucxulyNgaygiocn;
    @Column(name = "DMHINHTHUCXULY_DT")
    private Boolean dmhinhthucxulyDt;
    @Column(name = "DMHINHTHUCXULY_DP")
    private Boolean dmhinhthucxulyDp;
    @Column(name = "DMHINHTHUCXULY_QL")
    private Boolean dmhinhthucxulyQl;

    public DmHinhThucXuLy() {
    }

    public DmHinhThucXuLy(Integer dmhinhthucxulyMaso) {
        this.dmhinhthucxulyMaso = dmhinhthucxulyMaso;
    }

    public DmHinhThucXuLy(Integer dmhinhthucxulyMaso, String dmhinhthucxulyTen) {
        this.dmhinhthucxulyMaso = dmhinhthucxulyMaso;
        this.dmhinhthucxulyTen = dmhinhthucxulyTen;
    }

    public Integer getDmhinhthucxulyMaso() {
        return dmhinhthucxulyMaso;
    }

    public void setDmhinhthucxulyMaso(Integer dmhinhthucxulyMaso) {
        this.dmhinhthucxulyMaso = dmhinhthucxulyMaso;
    }

    public String getDmhinhthucxulyMa() {
        return dmhinhthucxulyMa;
    }

    public void setDmhinhthucxulyMa(String dmhinhthucxulyMa) {
        this.dmhinhthucxulyMa = dmhinhthucxulyMa;
    }

    public String getDmhinhthucxulyTen() {
        return dmhinhthucxulyTen;
    }

    public void setDmhinhthucxulyTen(String dmhinhthucxulyTen) {
        this.dmhinhthucxulyTen = dmhinhthucxulyTen;
    }

    public Double getDmhinhthucxulyNgaygiocn() {
        return dmhinhthucxulyNgaygiocn;
    }

    public void setDmhinhthucxulyNgaygiocn(Double dmhinhthucxulyNgaygiocn) {
        this.dmhinhthucxulyNgaygiocn = dmhinhthucxulyNgaygiocn;
    }

    public Boolean getDmhinhthucxulyDt() {
        return dmhinhthucxulyDt;
    }

    public void setDmhinhthucxulyDt(Boolean dmhinhthucxulyDt) {
        this.dmhinhthucxulyDt = dmhinhthucxulyDt;
    }

    public Boolean getDmhinhthucxulyDp() {
        return dmhinhthucxulyDp;
    }

    public void setDmhinhthucxulyDp(Boolean dmhinhthucxulyDp) {
        this.dmhinhthucxulyDp = dmhinhthucxulyDp;
    }

    public Boolean getDmhinhthucxulyQl() {
        return dmhinhthucxulyQl;
    }

    public void setDmhinhthucxulyQl(Boolean dmhinhthucxulyQl) {
        this.dmhinhthucxulyQl = dmhinhthucxulyQl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmhinhthucxulyMaso != null ? dmhinhthucxulyMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmHinhThucXuLy)) {
            return false;
        }
        DmHinhThucXuLy other = (DmHinhThucXuLy) object;
        if ((this.dmhinhthucxulyMaso == null && other.dmhinhthucxulyMaso != null) || (this.dmhinhthucxulyMaso != null && !this.dmhinhthucxulyMaso.equals(other.dmhinhthucxulyMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmHinhThucXuLy[dmhinhthucxulyMaso=" + dmhinhthucxulyMaso + "]";
    }

}


