/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
 * @author james
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CT_PHIEU_GIAO_BAN")
@NamedQueries({})
public class CtPhieuGiaoBan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_PHIEU_GIAO_BAN")
    @SequenceGenerator(name = "CT_PHIEU_GIAO_BAN", sequenceName = "CT_PHIEU_GIAO_BAN_CTPGB_MASO_S", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "CTPGB_MASO")
    private Integer ctpgbMaso;
    @Column(name = "CTPGB_SOLUONG")
    private Integer ctpgbSoluong;
    @Column(name = "CTPGB_NGAYGIOCN")
    private Double ctpgbNgaygiocn;
    @JoinColumn(name = "PBA_MASO", referencedColumnName = "PHIEUBAOAN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuBaoAn pbaMaso;
    @JoinColumn(name = "CTPGB_PGB_MA", referencedColumnName = "PGB_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuGiaoBan ctpgbPgbMa;

    public CtPhieuGiaoBan() {
    }

    public CtPhieuGiaoBan(Integer ctpgbMaso) {
        this.ctpgbMaso = ctpgbMaso;
    }

    public Integer getCtpgbMaso() {
        return ctpgbMaso;
    }

    public void setCtpgbMaso(Integer ctpgbMaso) {
        this.ctpgbMaso = ctpgbMaso;
    }

    public Integer getCtpgbSoluong() {
        return ctpgbSoluong;
    }

    public void setCtpgbSoluong(Integer ctpgbSoluong) {
        this.ctpgbSoluong = ctpgbSoluong;
    }

    public Double getCtpgbNgaygiocn() {
        return ctpgbNgaygiocn;
    }

    public void setCtpgbNgaygiocn(Double ctpgbNgaygiocn) {
        this.ctpgbNgaygiocn = ctpgbNgaygiocn;
    }

    public PhieuBaoAn getPbaMaso() {
        return pbaMaso;
    }

    public void setPbaMaso(PhieuBaoAn pbaMaso) {
        this.pbaMaso = pbaMaso;
    }

    public PhieuGiaoBan getCtpgbPgbMa() {
        return ctpgbPgbMa;
    }

    public void setCtpgbPgbMa(PhieuGiaoBan ctpgbPgbMa) {
        this.ctpgbPgbMa = ctpgbPgbMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctpgbMaso != null ? ctpgbMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtPhieuGiaoBan)) {
            return false;
        }
        CtPhieuGiaoBan other = (CtPhieuGiaoBan) object;
        if ((this.ctpgbMaso == null && other.ctpgbMaso != null) || (this.ctpgbMaso != null && !this.ctpgbMaso.equals(other.ctpgbMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.CtPhieuGiaoBan[ctpgbMaso=" + ctpgbMaso + "]";
    }

}
