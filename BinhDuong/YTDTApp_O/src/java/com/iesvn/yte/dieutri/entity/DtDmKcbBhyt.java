/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_KCB_BHYT")
@NamedQueries({})
public class DtDmKcbBhyt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_KCB_BHYT")
    @SequenceGenerator(name = "DT_DM_KCB_BHYT", sequenceName = "DT_DM_KCB_BHYT_DTDMKCBBHYT_MAS", allocationSize = 1)
    @Column(name = "DTDMKCBBHYT_MASO", nullable = false)
    private Integer dtdmkcbbhytMaso;
    @Column(name = "DTDMKCBBHYT_MA", nullable = false)
    private String dtdmkcbbhytMa;
    @Column(name = "DTDMHUYENBHYT_MA")
    private String dtdmhuyenbhytMa;
    @Column(name = "DTDMKCBBHYT_TEN", nullable = false)
    private String dtdmkcbbhytTen;
    @Column(name = "DTDMKCBBHYT_GHICHU")
    private String dtdmkcbbhytGhichu;
    @Column(name = "DTDMKCBBHYT_MAUTHE", nullable = false)
    private short dtdmkcbbhytMauthe;
    @Column(name = "DTDMKCBBHYT_NGAYGIOCN")
    private Double dtdmkcbbhytNgaygiocn;
    @Column(name = "DTDMKCBBHYT_CHON")
    private Boolean dtdmkcbbhytChon;
    
    @Column(name = "DTDMKCBBHYT_DEFA")
    private Boolean dtdmkcbbhytDefa;
        
    @JoinColumn(name = "DTDMTUYENKCB_MASO", referencedColumnName = "DTDMTUYENKCB_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmTuyenKcb dtdmtuyenkcbMaso;
    @JoinColumn(name = "DTDMTINHBHYT_MASO", referencedColumnName = "DTDMTINHBHYT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmTinhBhyt dtdmtinhbhytMaso;

    public DtDmKcbBhyt() {
    }

    public DtDmKcbBhyt(Integer dtdmkcbbhytMaso) {
        this.dtdmkcbbhytMaso = dtdmkcbbhytMaso;
    }

    public DtDmKcbBhyt(Integer dtdmkcbbhytMaso, String dtdmkcbbhytMa, String dtdmkcbbhytTen, short dtdmkcbbhytMauthe) {
        this.dtdmkcbbhytMaso = dtdmkcbbhytMaso;
        this.dtdmkcbbhytMa = dtdmkcbbhytMa;
        this.dtdmkcbbhytTen = dtdmkcbbhytTen;
        this.dtdmkcbbhytMauthe = dtdmkcbbhytMauthe;
    }

    public Integer getDtdmkcbbhytMaso() {
        return dtdmkcbbhytMaso;
    }

    public void setDtdmkcbbhytMaso(Integer dtdmkcbbhytMaso) {
        this.dtdmkcbbhytMaso = dtdmkcbbhytMaso;
    }

    public String getDtdmkcbbhytMa() {
        return dtdmkcbbhytMa;
    }

    public void setDtdmkcbbhytMa(String dtdmkcbbhytMa) {
        this.dtdmkcbbhytMa = dtdmkcbbhytMa;
    }

    public String getDtdmhuyenbhytMa() {
        return dtdmhuyenbhytMa;
    }

    public void setDtdmhuyenbhytMa(String dtdmhuyenbhytMa) {
        this.dtdmhuyenbhytMa = dtdmhuyenbhytMa;
    }

    public String getDtdmkcbbhytTen() {
        return dtdmkcbbhytTen;
    }

    public void setDtdmkcbbhytTen(String dtdmkcbbhytTen) {
        this.dtdmkcbbhytTen = dtdmkcbbhytTen;
    }

    public String getDtdmkcbbhytGhichu() {
        return dtdmkcbbhytGhichu;
    }

    public void setDtdmkcbbhytGhichu(String dtdmkcbbhytGhichu) {
        this.dtdmkcbbhytGhichu = dtdmkcbbhytGhichu;
    }

    public short getDtdmkcbbhytMauthe() {
        return dtdmkcbbhytMauthe;
    }

    public void setDtdmkcbbhytMauthe(short dtdmkcbbhytMauthe) {
        this.dtdmkcbbhytMauthe = dtdmkcbbhytMauthe;
    }

    public Double getDtdmkcbbhytNgaygiocn() {
        return dtdmkcbbhytNgaygiocn;
    }

    public void setDtdmkcbbhytNgaygiocn(Double dtdmkcbbhytNgaygiocn) {
        this.dtdmkcbbhytNgaygiocn = dtdmkcbbhytNgaygiocn;
    }

    public Boolean getDtdmkcbbhytChon() {
        return dtdmkcbbhytChon;
    }

    public void setDtdmkcbbhytChon(Boolean dtdmkcbbhytChon) {
        this.dtdmkcbbhytChon = dtdmkcbbhytChon;
    }

    public DtDmTuyenKcb getDtdmtuyenkcbMaso(boolean create) {
if(create && dtdmtuyenkcbMaso == null) dtdmtuyenkcbMaso = new DtDmTuyenKcb();
return dtdmtuyenkcbMaso;
}
    public DtDmTuyenKcb getDtdmtuyenkcbMaso() {
        return dtdmtuyenkcbMaso;
    }

    public void setDtdmtuyenkcbMaso(DtDmTuyenKcb dtdmtuyenkcbMaso) {
        this.dtdmtuyenkcbMaso = dtdmtuyenkcbMaso;
    }

    public DtDmTinhBhyt getDtdmtinhbhytMaso(boolean create) {
if(create && dtdmtinhbhytMaso == null) dtdmtinhbhytMaso = new DtDmTinhBhyt();
return dtdmtinhbhytMaso;
}
    public DtDmTinhBhyt getDtdmtinhbhytMaso() {
        return dtdmtinhbhytMaso;
    }

    public void setDtdmtinhbhytMaso(DtDmTinhBhyt dtdmtinhbhytMaso) {
        this.dtdmtinhbhytMaso = dtdmtinhbhytMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmkcbbhytMaso != null ? dtdmkcbbhytMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmKcbBhyt)) {
            return false;
        }
        DtDmKcbBhyt other = (DtDmKcbBhyt) object;
        if ((this.dtdmkcbbhytMaso == null && other.dtdmkcbbhytMaso != null) || (this.dtdmkcbbhytMaso != null && !this.dtdmkcbbhytMaso.equals(other.dtdmkcbbhytMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmKcbBhyt[dtdmkcbbhytMaso=" + dtdmkcbbhytMaso + "]";
    }

    public Boolean getDtdmkcbbhytDefa() {
        return dtdmkcbbhytDefa;
    }

    public void setDtdmkcbbhytDefa(Boolean dtdmkcbbhytDefa) {
        this.dtdmkcbbhytDefa = dtdmkcbbhytDefa;
    }
}


