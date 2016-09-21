/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author james
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_LOI_DAN")
@NamedQueries({})
public class DtDmLoiDan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_LOI_DAN")
    @SequenceGenerator(name = "DT_DM_LOI_DAN", sequenceName = "DT_DM_LOI_DAN_DTDMLD_MASO_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "DTDMLD_MASO")
    private Integer dtdmldMaso;
    @Basic(optional = false)
    @Column(name = "DTDMLD_MA")
    private String dtdmldMa;
    @Basic(optional = false)
    @Column(name = "DTDMLD_TEN")
    private String dtdmldTen;
    @Basic(optional = false)
    @Column(name = "DTDMLD_NGAYGIOCN")
    private double dtdmldNgaygiocn;
    @Column(name = "DTDMLD_CHON")
    private Integer dtdmldChon;

    public DtDmLoiDan() {
    }

    public DtDmLoiDan(Integer dtdmldMaso) {
        this.dtdmldMaso = dtdmldMaso;
    }

    public DtDmLoiDan(Integer dtdmldMaso, String dtdmldMa, String dtdmldTen, double dtdmldNgaygiocn) {
        this.dtdmldMaso = dtdmldMaso;
        this.dtdmldMa = dtdmldMa;
        this.dtdmldTen = dtdmldTen;
        this.dtdmldNgaygiocn = dtdmldNgaygiocn;
    }

    public Integer getDtdmldMaso() {
        return dtdmldMaso;
    }

    public void setDtdmldMaso(Integer dtdmldMaso) {
        this.dtdmldMaso = dtdmldMaso;
    }

    public String getDtdmldMa() {
        return dtdmldMa;
    }

    public void setDtdmldMa(String dtdmldMa) {
        this.dtdmldMa = dtdmldMa;
    }

    public String getDtdmldTen() {
        return dtdmldTen;
    }

    public void setDtdmldTen(String dtdmldTen) {
        this.dtdmldTen = dtdmldTen;
    }

    public double getDtdmldNgaygiocn() {
        return dtdmldNgaygiocn;
    }

    public void setDtdmldNgaygiocn(double dtdmldNgaygiocn) {
        this.dtdmldNgaygiocn = dtdmldNgaygiocn;
    }

    public Integer getDtdmldChon() {
        return dtdmldChon;
    }

    public void setDtdmldChon(Integer dtdmldChon) {
        this.dtdmldChon = dtdmldChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmldMaso != null ? dtdmldMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmLoiDan)) {
            return false;
        }
        DtDmLoiDan other = (DtDmLoiDan) object;
        if ((this.dtdmldMaso == null && other.dtdmldMaso != null) || (this.dtdmldMaso != null && !this.dtdmldMaso.equals(other.dtdmldMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "source.DtDmLoiDan[dtdmldMaso=" + dtdmldMaso + "]";
    }

}
