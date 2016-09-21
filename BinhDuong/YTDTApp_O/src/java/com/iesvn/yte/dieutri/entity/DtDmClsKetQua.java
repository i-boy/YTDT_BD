/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Thanh
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_CLS_KET_QUA")
@NamedQueries({@NamedQuery(name = "DtDmClsKetQua.findByDtdtclskqMaso", query = "SELECT d FROM DtDmClsKetQua d WHERE d.dtdtclskqMaso = :dtdtclskqMaso"), @NamedQuery(name = "DtDmClsKetQua.findByDtdmclskqMa", query = "SELECT d FROM DtDmClsKetQua d WHERE d.dtdmclskqMa = :dtdmclskqMa"), @NamedQuery(name = "DtDmClsKetQua.findByDtdmclskqTen", query = "SELECT d FROM DtDmClsKetQua d WHERE d.dtdmclskqTen = :dtdmclskqTen")})
public class DtDmClsKetQua implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_CLS_KET_QUA")
    @SequenceGenerator(name = "DT_DM_CLS_KET_QUA", sequenceName = "DT_DM_CLS_KET_QUA_DTDTCLSKQ_MA", allocationSize = 1)
    @Column(name = "DTDTCLSKQ_MASO", nullable = false)    
    private Integer dtdtclskqMaso;
    @Column(name = "DTDMCLSKQ_MA")
    private String dtdmclskqMa;
    @Column(name = "DTDMCLSKQ_TEN")
    private String dtdmclskqTen;
    @Lob
    @Column(name = "DTDMCLSKQ_GHI_CHU")
    private String dtdmclskqGhiChu;
    @JoinColumn(name = "DTDMCLSBG_MASO", referencedColumnName = "DTDMCLSBG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmClsBangGia dtdmclsbgMaso;
    @Column(name = "DTDMCLSKQ_NGAYGIOCN")
    private Double dtdmclskqNgaygiocn;

    public DtDmClsKetQua() {
    }

    public DtDmClsKetQua(Integer dtdtclskqMaso) {
        this.dtdtclskqMaso = dtdtclskqMaso;
    }

    public Integer getDtdtclskqMaso() {
        return dtdtclskqMaso;
    }

    public void setDtdtclskqMaso(Integer dtdtclskqMaso) {
        this.dtdtclskqMaso = dtdtclskqMaso;
    }

    public String getDtdmclskqMa() {
        return dtdmclskqMa;
    }

    public void setDtdmclskqMa(String dtdmclskqMa) {
        this.dtdmclskqMa = dtdmclskqMa;
    }

    public String getDtdmclskqTen() {
        return dtdmclskqTen;
    }

    public void setDtdmclskqTen(String dtdmclskqTen) {
        this.dtdmclskqTen = dtdmclskqTen;
    }

    public String getDtdmclskqGhiChu() {
        return dtdmclskqGhiChu;
    }

    public void setDtdmclskqGhiChu(String dtdmclskqGhiChu) {
        this.dtdmclskqGhiChu = dtdmclskqGhiChu;
    }

    public DtDmClsBangGia getDtdmclsbgMaso() {
        return dtdmclsbgMaso;
    }

    public void setDtdmclsbgMaso(DtDmClsBangGia dtdmclsbgMaso) {
        this.dtdmclsbgMaso = dtdmclsbgMaso;
    }

    public Double getDtdmclskqNgaygiocn() {
        return dtdmclskqNgaygiocn;
    }

    public void setDtdmclskqNgaygiocn(Double dtdmclskqNgaygiocn) {
        this.dtdmclskqNgaygiocn = dtdmclskqNgaygiocn;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdtclskqMaso != null ? dtdtclskqMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmClsKetQua)) {
            return false;
        }
        DtDmClsKetQua other = (DtDmClsKetQua) object;
        if ((this.dtdtclskqMaso == null && other.dtdtclskqMaso != null) || (this.dtdtclskqMaso != null && !this.dtdtclskqMaso.equals(other.dtdtclskqMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DtDmClsKetQua[dtdtclskqMaso=" + dtdtclskqMaso + "]";
    }

}
