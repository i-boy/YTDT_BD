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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author thanh
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_NHOM_BHYT")
public class DtDmNhomBhyt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_NHOM_BHYT")
    @SequenceGenerator(name = "DT_DM_NHOM_BHYT", sequenceName = "DT_DM_NHOM_BHYT_DTDMNHOMBHYT_M", allocationSize = 1)
    @Column(name = "DTDMNHOMBHYT_MASO", nullable = false)
    private Integer dtdmnhombhytMaso;
    @Column(name = "DTDMNHOMBHYT_MA", nullable = false)
    private String dtdmnhombhytMa;
    @Column(name = "DTDMNHOMBHYT_TEN", nullable = false)
    private String dtdmnhombhytTen;
    @Column(name = "DTDMNHOMBHYT_NGAYGIOCN")
    private Double dtdmnhombhytNgaygiocn;
    @Column(name = "DTDMNHOMBHYT_CHON")
    private Boolean dtdmnhombhytChon;
    @Column(name = "DTDMNHOMBHYT_DEFA")
    private Boolean dtdmnhombhytDefa;
    
    @JoinColumn(name = "DTDMLOPBHYT_MASO", referencedColumnName = "DTDMLOPBHYT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLopBhyt dtdmlopBhyt;
        
            
    @JoinColumn(name = "DTDMPHLOAIBHYT_MASO", referencedColumnName = "DTDMPHLOAIBHYT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmPlBhyt dtdmplBhyt;            
     
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhombhytMaso")
//    private Collection<ThongKeTramYTeBhyt> thongKeTramYTeBhytCollection;

    public DtDmNhomBhyt() {
    }

    public DtDmNhomBhyt(Integer dtdmnhombhytMaso) {
        this.dtdmnhombhytMaso = dtdmnhombhytMaso;
    }

    public DtDmNhomBhyt(Integer dtdmnhombhytMaso, String dtdmnhombhytMa, String dtdmnhombhytTen) {
        this.dtdmnhombhytMaso = dtdmnhombhytMaso;
        this.dtdmnhombhytMa = dtdmnhombhytMa;
        this.dtdmnhombhytTen = dtdmnhombhytTen;
    }

    public Integer getDtdmnhombhytMaso() {
        return dtdmnhombhytMaso;
    }

    public void setDtdmnhombhytMaso(Integer dtdmnhombhytMaso) {
        this.dtdmnhombhytMaso = dtdmnhombhytMaso;
    }

    public String getDtdmnhombhytMa() {
        return dtdmnhombhytMa;
    }

    public void setDtdmnhombhytMa(String dtdmnhombhytMa) {
        this.dtdmnhombhytMa = dtdmnhombhytMa;
    }

    public String getDtdmnhombhytTen() {
        return dtdmnhombhytTen;
    }

    public void setDtdmnhombhytTen(String dtdmnhombhytTen) {
        this.dtdmnhombhytTen = dtdmnhombhytTen;
    }

    public Double getDtdmnhombhytNgaygiocn() {
        return dtdmnhombhytNgaygiocn;
    }

    public void setDtdmnhombhytNgaygiocn(Double dtdmnhombhytNgaygiocn) {
        this.dtdmnhombhytNgaygiocn = dtdmnhombhytNgaygiocn;
    }

    public Boolean getDtdmnhombhytChon() {
        return dtdmnhombhytChon;
    }

    public void setDtdmnhombhytChon(Boolean dtdmnhombhytChon) {
        this.dtdmnhombhytChon = dtdmnhombhytChon;
    }

    public Boolean getDtdmnhombhytDefa() {
        return dtdmnhombhytDefa;
    }

    public void setDtdmnhombhytDefa(Boolean dtdmnhombhytDefa) {
        this.dtdmnhombhytDefa = dtdmnhombhytDefa;
    }

//    public Collection<ThongKeTramYTeBhyt> getThongKeTramYTeBhytCollection() {
//        return thongKeTramYTeBhytCollection;
//    }
//
//    public void setThongKeTramYTeBhytCollection(Collection<ThongKeTramYTeBhyt> thongKeTramYTeBhytCollection) {
//        this.thongKeTramYTeBhytCollection = thongKeTramYTeBhytCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmnhombhytMaso != null ? dtdmnhombhytMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmNhomBhyt)) {
            return false;
        }
        DtDmNhomBhyt other = (DtDmNhomBhyt) object;
        if ((this.dtdmnhombhytMaso == null && other.dtdmnhombhytMaso != null) || (this.dtdmnhombhytMaso != null && !this.dtdmnhombhytMaso.equals(other.dtdmnhombhytMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.DtDmNhomBhyt[dtdmnhombhytMaso=" + dtdmnhombhytMaso + "]";
    }

    public DtDmLopBhyt getDtdmlopBhyt() {
        return dtdmlopBhyt;
    }

    public void setDtdmlopBhyt(DtDmLopBhyt dtdmlopBhyt) {
        this.dtdmlopBhyt = dtdmlopBhyt;
    }

    public DtDmPlBhyt getDtdmplBhyt() {
        return dtdmplBhyt;
    }

    public void setDtdmplBhyt(DtDmPlBhyt dtdmplBhyt) {
        this.dtdmplBhyt = dtdmplBhyt;
    }

}
