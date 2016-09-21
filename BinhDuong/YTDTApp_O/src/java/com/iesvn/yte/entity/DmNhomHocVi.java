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
@Table(name = "DM_NHOM_HOC_VI")
@NamedQueries({})
public class DmNhomHocVi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NHOM_HOC_VI")
    @SequenceGenerator(name = "DM_NHOM_HOC_VI", sequenceName = "DM_NHOM_HOC_VI_DMNHOMHOCVI_MAS", allocationSize = 1)
    @Column(name = "DMNHOMHOCVI_MASO", nullable = false)
    private Integer dmnhomhocviMaso;
    @Column(name = "DMNHOMHOCVI_MA")
    private String dmnhomhocviMa;
    @Column(name = "DMNHOMHOCVI_TEN")
    private String dmnhomhocviTen;
    @Column(name = "DMNHOMHOCVI_DAIHOC")
    private Boolean dmnhomhocviDaihoc;
    @Column(name = "DMNHOMHOCVI_TENBC")
    private String dmnhomhocviTenbc;
    @Column(name = "DMNHOMHOCVI_THUTU")
    private Short dmnhomhocviThutu;
    @Column(name = "DMNHOMHOCVI_NGAYGIOCN")
    private Double dmnhomhocviNgaygiocn;
    @Column(name = "DMNHOMHOCVI_DT")
    private Boolean dmnhomhocviDt;
    @Column(name = "DMNHOMHOCVI_DL")
    private Boolean dmnhomhocviDl;
    @Column(name = "DMNHOMHOCVI_DP")
    private Boolean dmnhomhocviDp;
//    @OneToMany(mappedBy = "dmnhomhocviMaso")
//    private Collection<DmHocVi> dmHocViCollection;
//    @OneToMany(mappedBy = "dmnhomhocviMaso1")
//    private Collection<DmHocVi> dmHocViCollection1;

    public DmNhomHocVi() {
    }

    public DmNhomHocVi(Integer dmnhomhocviMaso) {
        this.dmnhomhocviMaso = dmnhomhocviMaso;
    }

    public Integer getDmnhomhocviMaso() {
        return dmnhomhocviMaso;
    }

    public void setDmnhomhocviMaso(Integer dmnhomhocviMaso) {
        this.dmnhomhocviMaso = dmnhomhocviMaso;
    }

    public String getDmnhomhocviMa() {
        return dmnhomhocviMa;
    }

    public void setDmnhomhocviMa(String dmnhomhocviMa) {
        this.dmnhomhocviMa = dmnhomhocviMa;
    }

    public String getDmnhomhocviTen() {
        return dmnhomhocviTen;
    }

    public void setDmnhomhocviTen(String dmnhomhocviTen) {
        this.dmnhomhocviTen = dmnhomhocviTen;
    }

    public Boolean getDmnhomhocviDaihoc() {
        return dmnhomhocviDaihoc;
    }

    public void setDmnhomhocviDaihoc(Boolean dmnhomhocviDaihoc) {
        this.dmnhomhocviDaihoc = dmnhomhocviDaihoc;
    }

    public String getDmnhomhocviTenbc() {
        return dmnhomhocviTenbc;
    }

    public void setDmnhomhocviTenbc(String dmnhomhocviTenbc) {
        this.dmnhomhocviTenbc = dmnhomhocviTenbc;
    }

    public Short getDmnhomhocviThutu() {
        return dmnhomhocviThutu;
    }

    public void setDmnhomhocviThutu(Short dmnhomhocviThutu) {
        this.dmnhomhocviThutu = dmnhomhocviThutu;
    }

    public Double getDmnhomhocviNgaygiocn() {
        return dmnhomhocviNgaygiocn;
    }

    public void setDmnhomhocviNgaygiocn(Double dmnhomhocviNgaygiocn) {
        this.dmnhomhocviNgaygiocn = dmnhomhocviNgaygiocn;
    }

    public Boolean getDmnhomhocviDt() {
        return dmnhomhocviDt;
    }

    public void setDmnhomhocviDt(Boolean dmnhomhocviDt) {
        this.dmnhomhocviDt = dmnhomhocviDt;
    }

    public Boolean getDmnhomhocviDl() {
        return dmnhomhocviDl;
    }

    public void setDmnhomhocviDl(Boolean dmnhomhocviDl) {
        this.dmnhomhocviDl = dmnhomhocviDl;
    }

    public Boolean getDmnhomhocviDp() {
        return dmnhomhocviDp;
    }

    public void setDmnhomhocviDp(Boolean dmnhomhocviDp) {
        this.dmnhomhocviDp = dmnhomhocviDp;
    }

//    public Collection<DmHocVi> getDmHocViCollection() {
//        return dmHocViCollection;
//    }
//
//    public void setDmHocViCollection(Collection<DmHocVi> dmHocViCollection) {
//        this.dmHocViCollection = dmHocViCollection;
//    }

//    public Collection<DmHocVi> getDmHocViCollection1() {
//        return dmHocViCollection1;
//    }
//
//    public void setDmHocViCollection1(Collection<DmHocVi> dmHocViCollection1) {
//        this.dmHocViCollection1 = dmHocViCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmnhomhocviMaso != null ? dmnhomhocviMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmNhomHocVi)) {
            return false;
        }
        DmNhomHocVi other = (DmNhomHocVi) object;
        if ((this.dmnhomhocviMaso == null && other.dmnhomhocviMaso != null) || (this.dmnhomhocviMaso != null && !this.dmnhomhocviMaso.equals(other.dmnhomhocviMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmNhomHocVi[dmnhomhocviMaso=" + dmnhomhocviMaso + "]";
    }

}


