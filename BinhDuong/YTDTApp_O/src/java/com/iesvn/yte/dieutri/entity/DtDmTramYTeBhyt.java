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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author thanh
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_TRAM_Y_TE_BHYT")
public class DtDmTramYTeBhyt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_TRAM_Y_TE_BHYT")
    @SequenceGenerator(name = "DT_DM_TRAM_Y_TE_BHYT", sequenceName = "DT_DM_TRAM_Y_TE_BHYT_DTDMTRAMY", allocationSize = 1)
    @Column(name = "DTDMTRAMYTEBHYT_MASO", nullable = false)
    private Integer dtdmtramytebhytMaso;
    @Column(name = "DTDMTRAMYTEBHYT_MA", nullable = false)
    private String dtdmtramytebhytMa;
    @Column(name = "DTDMTRAMYTEBHYT_TEN", nullable = false)
    private String dtdmtramytebhytTen;
    @Column(name = "DTDMTRAMYTEBHYT_NGAYGIOCN")
    private Double dtdmtramytebhytNgaygiocn;
    @Column(name = "DTDMTRAMYTEBHYT_CHON")
    private Boolean dtdmtramytebhytChon;
    @Column(name = "DTDMTRAMYTEBHYT_DEFA")
    private Boolean dtdmtramytebhytDefa;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmtramytebhytMaso")
//    private Collection<ThongKeTramYTeBhyt> thongKeTramYTeBhytCollection;

    public DtDmTramYTeBhyt() {
    }

    public DtDmTramYTeBhyt(Integer dtdmtramytebhytMaso) {
        this.dtdmtramytebhytMaso = dtdmtramytebhytMaso;
    }

    public DtDmTramYTeBhyt(Integer dtdmtramytebhytMaso, String dtdmtramytebhytMa, String dtdmtramytebhytTen) {
        this.dtdmtramytebhytMaso = dtdmtramytebhytMaso;
        this.dtdmtramytebhytMa = dtdmtramytebhytMa;
        this.dtdmtramytebhytTen = dtdmtramytebhytTen;
    }

    public Integer getDtdmtramytebhytMaso() {
        return dtdmtramytebhytMaso;
    }

    public void setDtdmtramytebhytMaso(Integer dtdmtramytebhytMaso) {
        this.dtdmtramytebhytMaso = dtdmtramytebhytMaso;
    }

    public String getDtdmtramytebhytMa() {
        return dtdmtramytebhytMa;
    }

    public void setDtdmtramytebhytMa(String dtdmtramytebhytMa) {
        this.dtdmtramytebhytMa = dtdmtramytebhytMa;
    }

    public String getDtdmtramytebhytTen() {
        return dtdmtramytebhytTen;
    }

    public void setDtdmtramytebhytTen(String dtdmtramytebhytTen) {
        this.dtdmtramytebhytTen = dtdmtramytebhytTen;
    }

    public Double getDtdmtramytebhytNgaygiocn() {
        return dtdmtramytebhytNgaygiocn;
    }

    public void setDtdmtramytebhytNgaygiocn(Double dtdmtramytebhytNgaygiocn) {
        this.dtdmtramytebhytNgaygiocn = dtdmtramytebhytNgaygiocn;
    }

    public Boolean getDtdmtramytebhytChon() {
        return dtdmtramytebhytChon;
    }

    public void setDtdmtramytebhytChon(Boolean dtdmtramytebhytChon) {
        this.dtdmtramytebhytChon = dtdmtramytebhytChon;
    }

    public Boolean getDtdmtramytebhytDefa() {
        return dtdmtramytebhytDefa;
    }

    public void setDtdmtramytebhytDefa(Boolean dtdmtramytebhytDefa) {
        this.dtdmtramytebhytDefa = dtdmtramytebhytDefa;
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
        hash += (dtdmtramytebhytMaso != null ? dtdmtramytebhytMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmTramYTeBhyt)) {
            return false;
        }
        DtDmTramYTeBhyt other = (DtDmTramYTeBhyt) object;
        if ((this.dtdmtramytebhytMaso == null && other.dtdmtramytebhytMaso != null) || (this.dtdmtramytebhytMaso != null && !this.dtdmtramytebhytMaso.equals(other.dtdmtramytebhytMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.DtDmTramYTeBhyt[dtdmtramytebhytMaso=" + dtdmtramytebhytMaso + "]";
    }

}
