/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
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
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_DIEN_MIEN")
@NamedQueries({})
public class DtDmDienMien implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_DIEN_MIEN")
    @SequenceGenerator(name = "DT_DM_DIEN_MIEN", sequenceName = "DT_DM_DIEN_MIEN_DTDMDIENMIEN_M", allocationSize = 1)
    @Column(name = "DTDMDIENMIEN_MASO", nullable = false)
    private Integer dtdmdienmienMaso;
     
     
   
    @Column(name = "DTDMDIENMIEN_MA", nullable = false)
    private String dtdmdienmienMa;
    
    
            
            
    @Column(name = "DTDMDIENMIEN_TEN", nullable = false)
    private String dtdmdienmienTen;
    @Column(name = "DTDMDIENMIEN_NGAYGIOCN")
    private Double dtdmdienmienNgaygiocn;
    @Column(name = "DTDMDIENMIEN_CHON")
    private Boolean dtdmdienmienChon;

    public DtDmDienMien() {
    }

    public DtDmDienMien(String dtdmdienmienMa) {
        this.dtdmdienmienMa = dtdmdienmienMa;
    }

    public DtDmDienMien(String dtdmdienmienMa, String dtdmdienmienTen) {
        this.dtdmdienmienMa = dtdmdienmienMa;
        this.dtdmdienmienTen = dtdmdienmienTen;
    }

    public String getDtdmdienmienMa() {
        return dtdmdienmienMa;
    }

    public void setDtdmdienmienMa(String dtdmdienmienMa) {
        this.dtdmdienmienMa = dtdmdienmienMa;
    }

    public String getDtdmdienmienTen() {
        return dtdmdienmienTen;
    }

    public void setDtdmdienmienTen(String dtdmdienmienTen) {
        this.dtdmdienmienTen = dtdmdienmienTen;
    }

    public Double getDtdmdienmienNgaygiocn() {
        return dtdmdienmienNgaygiocn;
    }

    public void setDtdmdienmienNgaygiocn(Double dtdmdienmienNgaygiocn) {
        this.dtdmdienmienNgaygiocn = dtdmdienmienNgaygiocn;
    }

    public Boolean getDtdmdienmienChon() {
        return dtdmdienmienChon;
    }

    public void setDtdmdienmienChon(Boolean dtdmdienmienChon) {
        this.dtdmdienmienChon = dtdmdienmienChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmdienmienMa != null ? dtdmdienmienMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmDienMien)) {
            return false;
        }
        DtDmDienMien other = (DtDmDienMien) object;
        if ((this.dtdmdienmienMa == null && other.dtdmdienmienMa != null) || (this.dtdmdienmienMa != null && !this.dtdmdienmienMa.equals(other.dtdmdienmienMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmDienMien[dtdmdienmienMa=" + dtdmdienmienMa + "]";
    }

    public Integer getDtdmdienmienMaso() {
        return dtdmdienmienMaso;
    }

    public void setDtdmdienmienMaso(Integer dtdmdienmienMaso) {
        this.dtdmdienmienMaso = dtdmdienmienMaso;
    }
}


