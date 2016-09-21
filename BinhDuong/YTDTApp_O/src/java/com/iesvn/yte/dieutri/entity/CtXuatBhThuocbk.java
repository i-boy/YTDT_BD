/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.iesvn.yte.entity.DmThuoc;
import javax.persistence.FetchType;

/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CT_XUAT_BH_THUOCBK")
@NamedQueries({})
public class CtXuatBhThuocbk implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TONKHO_MA")
    private Integer tonkhoMa;
    @Basic(optional = false)
    @Column(name = "TIEPDON_MA")
    private String tiepdonMa;
    @Column(name = "CTXUATBHTD_MALK")
    private String ctxuatbhtdMalk;
    @Column(name = "THUOCPHONGKHAM_MA")
    private Integer thuocphongkhamMa;
    @JoinColumn(name = "DMTHUOC_MASO", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc dmthuocMaso;

    public CtXuatBhThuocbk() {
    }

    public CtXuatBhThuocbk(Integer tonkhoMa) {
        this.tonkhoMa = tonkhoMa;
    }

    public CtXuatBhThuocbk(Integer tonkhoMa, String tiepdonMa) {
        this.tonkhoMa = tonkhoMa;
        this.tiepdonMa = tiepdonMa;
    }

    public Integer getTonkhoMa() {
        return tonkhoMa;
    }

    public void setTonkhoMa(Integer tonkhoMa) {
        this.tonkhoMa = tonkhoMa;
    }

    public String getTiepdonMa() {
        return tiepdonMa;
    }

    public void setTiepdonMa(String tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

    public String getCtxuatbhtdMalk() {
        return ctxuatbhtdMalk;
    }

    public void setCtxuatbhtdMalk(String ctxuatbhtdMalk) {
        this.ctxuatbhtdMalk = ctxuatbhtdMalk;
    }

    public Integer getThuocphongkhamMa() {
        return thuocphongkhamMa;
    }

    public void setThuocphongkhamMa(Integer thuocphongkhamMa) {
        this.thuocphongkhamMa = thuocphongkhamMa;
    }

    public DmThuoc getDmthuocMaso() {
        return dmthuocMaso;
    }

    public void setDmthuocMaso(DmThuoc dmthuocMaso) {
        this.dmthuocMaso = dmthuocMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tonkhoMa != null ? tonkhoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtXuatBhThuocbk)) {
            return false;
        }
        CtXuatBhThuocbk other = (CtXuatBhThuocbk) object;
        if ((this.tonkhoMa == null && other.tonkhoMa != null) || (this.tonkhoMa != null && !this.tonkhoMa.equals(other.tonkhoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.CtXuatBhThuocbk[tonkhoMa=" + tonkhoMa + "]";
    }

}
