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
@Table(name = "DM_CHUC_VU")
@NamedQueries({})
public class DmChucVu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_CHUC_VU")
    @SequenceGenerator(name = "DM_CHUC_VU", sequenceName = "DM_CHUC_VU_DMCHUCVU_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMCHUCVU_MASO", nullable = false)
    private Integer dmchucvuMaso;
    @Column(name = "DMCHUCVU_MA")
    private String dmchucvuMa;
    @Column(name = "DMCHUCVU_TEN", nullable = false)
    private String dmchucvuTen;
    @Column(name = "DMCHUCVU_TENBC", nullable = false)
    private String dmchucvuTenbc;
    @Column(name = "DMCHUCVU_HESOPC")
    private Short dmchucvuHesopc;
    @Column(name = "DMCHUCVU_THUTU")
    private Short dmchucvuThutu;
    @Column(name = "DMCHUCVU_NGAYGIOCN")
    private Double dmchucvuNgaygiocn;
    @Column(name = "DMCHUCVU_QL")
    private Boolean dmchucvuQl;
    @Column(name = "DMCHUCVU_DT")
    private Boolean dmchucvuDt;
    @Column(name = "DMCHUCVU_DP")
    private Boolean dmchucvuDp;

    public DmChucVu() {
    }

    public DmChucVu(Integer dmchucvuMaso) {
        this.dmchucvuMaso = dmchucvuMaso;
    }

    public DmChucVu(Integer dmchucvuMaso, String dmchucvuTen, String dmchucvuTenbc) {
        this.dmchucvuMaso = dmchucvuMaso;
        this.dmchucvuTen = dmchucvuTen;
        this.dmchucvuTenbc = dmchucvuTenbc;
    }

    public Integer getDmchucvuMaso() {
        return dmchucvuMaso;
    }

    public void setDmchucvuMaso(Integer dmchucvuMaso) {
        this.dmchucvuMaso = dmchucvuMaso;
    }

    public String getDmchucvuMa() {
        return dmchucvuMa;
    }

    public void setDmchucvuMa(String dmchucvuMa) {
        this.dmchucvuMa = dmchucvuMa;
    }

    public String getDmchucvuTen() {
        return dmchucvuTen;
    }

    public void setDmchucvuTen(String dmchucvuTen) {
        this.dmchucvuTen = dmchucvuTen;
    }

    public String getDmchucvuTenbc() {
        return dmchucvuTenbc;
    }

    public void setDmchucvuTenbc(String dmchucvuTenbc) {
        this.dmchucvuTenbc = dmchucvuTenbc;
    }

    public Short getDmchucvuHesopc() {
        return dmchucvuHesopc;
    }

    public void setDmchucvuHesopc(Short dmchucvuHesopc) {
        this.dmchucvuHesopc = dmchucvuHesopc;
    }

    public Short getDmchucvuThutu() {
        return dmchucvuThutu;
    }

    public void setDmchucvuThutu(Short dmchucvuThutu) {
        this.dmchucvuThutu = dmchucvuThutu;
    }

    public Double getDmchucvuNgaygiocn() {
        return dmchucvuNgaygiocn;
    }

    public void setDmchucvuNgaygiocn(Double dmchucvuNgaygiocn) {
        this.dmchucvuNgaygiocn = dmchucvuNgaygiocn;
    }

    public Boolean getDmchucvuQl() {
        return dmchucvuQl;
    }

    public void setDmchucvuQl(Boolean dmchucvuQl) {
        this.dmchucvuQl = dmchucvuQl;
    }

    public Boolean getDmchucvuDt() {
        return dmchucvuDt;
    }

    public void setDmchucvuDt(Boolean dmchucvuDt) {
        this.dmchucvuDt = dmchucvuDt;
    }

    public Boolean getDmchucvuDp() {
        return dmchucvuDp;
    }

    public void setDmchucvuDp(Boolean dmchucvuDp) {
        this.dmchucvuDp = dmchucvuDp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmchucvuMaso != null ? dmchucvuMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmChucVu)) {
            return false;
        }
        DmChucVu other = (DmChucVu) object;
        if ((this.dmchucvuMaso == null && other.dmchucvuMaso != null) || (this.dmchucvuMaso != null && !this.dmchucvuMaso.equals(other.dmchucvuMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmChucVu[dmchucvuMaso=" + dmchucvuMaso + "]";
    }

}


