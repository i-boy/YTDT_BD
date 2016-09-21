/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

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
@Table(name = "DM_HOC_VI")
@NamedQueries({})
public class DmHocVi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_HOC_VI")
    @SequenceGenerator(name = "DM_HOC_VI", sequenceName = "DM_HOC_VI_DMHOCVI_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMHOCVI_MASO", nullable = false)
    private Integer dmhocviMaso;
    @Column(name = "DMHOCVI_MA")
    private String dmhocviMa;
    @Column(name = "DMHOCVI_TEN")
    private String dmhocviTen;
    @Column(name = "DMHOCVI_TENBC")
    private String dmhocviTenbc;
    @Column(name = "DMHOCVI_DAIHOC")
    private Boolean dmhocviDaihoc;
    @Column(name = "DMHOCVI_THUTU")
    private Short dmhocviThutu;
    @Column(name = "DMHOCVI_NGAYGIOCN")
    private Double dmhocviNgaygiocn;
    @Column(name = "DMHOCVI_QL")
    private Boolean dmhocviQl;
    @Column(name = "DMHOCVI_DT")
    private Boolean dmhocviDt;
    @Column(name = "DMHOCVI_DP")
    private Boolean dmhocviDp;
//    @OneToMany(mappedBy = "dmhocviMaso")
//    private Collection<DtDmNhanVien> dtDmNhanVienCollection;
//    @OneToMany(mappedBy = "dmhocviMaso1")
//    private Collection<DtDmNhanVien> dtDmNhanVienCollection1;
    @JoinColumn(name = "DMNHOMHOCVI_MASO", referencedColumnName = "DMNHOMHOCVI_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhomHocVi dmnhomhocviMaso;


    public DmHocVi() {
    }

    public DmHocVi(Integer dmhocviMaso) {
        this.dmhocviMaso = dmhocviMaso;
    }

    public Integer getDmhocviMaso() {
        return dmhocviMaso;
    }

    public void setDmhocviMaso(Integer dmhocviMaso) {
        this.dmhocviMaso = dmhocviMaso;
    }

    public String getDmhocviMa() {
        return dmhocviMa;
    }

    public void setDmhocviMa(String dmhocviMa) {
        this.dmhocviMa = dmhocviMa;
    }

    public String getDmhocviTen() {
        return dmhocviTen;
    }

    public void setDmhocviTen(String dmhocviTen) {
        this.dmhocviTen = dmhocviTen;
    }

    public String getDmhocviTenbc() {
        return dmhocviTenbc;
    }

    public void setDmhocviTenbc(String dmhocviTenbc) {
        this.dmhocviTenbc = dmhocviTenbc;
    }

    public Boolean getDmhocviDaihoc() {
        return dmhocviDaihoc;
    }

    public void setDmhocviDaihoc(Boolean dmhocviDaihoc) {
        this.dmhocviDaihoc = dmhocviDaihoc;
    }

    public Short getDmhocviThutu() {
        return dmhocviThutu;
    }

    public void setDmhocviThutu(Short dmhocviThutu) {
        this.dmhocviThutu = dmhocviThutu;
    }

    public Double getDmhocviNgaygiocn() {
        return dmhocviNgaygiocn;
    }

    public void setDmhocviNgaygiocn(Double dmhocviNgaygiocn) {
        this.dmhocviNgaygiocn = dmhocviNgaygiocn;
    }

    public Boolean getDmhocviQl() {
        return dmhocviQl;
    }

    public void setDmhocviQl(Boolean dmhocviQl) {
        this.dmhocviQl = dmhocviQl;
    }

    public Boolean getDmhocviDt() {
        return dmhocviDt;
    }

    public void setDmhocviDt(Boolean dmhocviDt) {
        this.dmhocviDt = dmhocviDt;
    }

    public Boolean getDmhocviDp() {
        return dmhocviDp;
    }

    public void setDmhocviDp(Boolean dmhocviDp) {
        this.dmhocviDp = dmhocviDp;
    }

//    public Collection<DtDmNhanVien> getDtDmNhanVienCollection() {
//        return dtDmNhanVienCollection;
//    }
//
//    public void setDtDmNhanVienCollection(Collection<DtDmNhanVien> dtDmNhanVienCollection) {
//        this.dtDmNhanVienCollection = dtDmNhanVienCollection;
//    }

//    public Collection<DtDmNhanVien> getDtDmNhanVienCollection1() {
//        return dtDmNhanVienCollection1;
//    }
//
//    public void setDtDmNhanVienCollection1(Collection<DtDmNhanVien> dtDmNhanVienCollection1) {
//        this.dtDmNhanVienCollection1 = dtDmNhanVienCollection1;
//    }

    public DmNhomHocVi getDmnhomhocviMaso(boolean create) {
if(create && dmnhomhocviMaso == null) dmnhomhocviMaso = new DmNhomHocVi();
return dmnhomhocviMaso;
}
    public DmNhomHocVi getDmnhomhocviMaso() {
        return dmnhomhocviMaso;
    }

    public void setDmnhomhocviMaso(DmNhomHocVi dmnhomhocviMaso) {
        this.dmnhomhocviMaso = dmnhomhocviMaso;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmhocviMaso != null ? dmhocviMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmHocVi)) {
            return false;
        }
        DmHocVi other = (DmHocVi) object;
        if ((this.dmhocviMaso == null && other.dmhocviMaso != null) || (this.dmhocviMaso != null && !this.dmhocviMaso.equals(other.dmhocviMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmHocVi[dmhocviMaso=" + dmhocviMaso + "]";
    }

}


