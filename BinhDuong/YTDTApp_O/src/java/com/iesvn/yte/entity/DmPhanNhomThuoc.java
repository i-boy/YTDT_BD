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
@Table(name = "DM_PHAN_NHOM_THUOC")
@NamedQueries({})
public class DmPhanNhomThuoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "M_PHAN_NHOM_THUOC")
    @SequenceGenerator(name = "M_PHAN_NHOM_THUOC", sequenceName = "DM_PHAN_NHOM_THUOC_DMPHANNHOMT", allocationSize = 1)
    @Column(name = "DMPHANNHOMTHUOC_MASO", nullable = false)
    private Integer dmphannhomthuocMaso;
    @Column(name = "DMPHANNHOMTHUOC_MA", nullable = false)
    private String dmphannhomthuocMa;
    @Column(name = "DMPHANNHOMTHUOC_MAPHU")
    private String dmphannhomthuocMaphu;
    @Column(name = "DMPHANNHOMTHUOC_TEN", nullable = false)
    private String dmphannhomthuocTen;
    @Column(name = "DMPHANNHOMTHUOC_THUTU")
    private String dmphannhomthuocThutu;
    @Column(name = "DMPHANNHOMTHUOC_GHICHU")
    private String dmphannhomthuocGhichu;
    @Column(name = "DMNHANVIEN_CN")
    private String dmnhanvienCn;
    @Column(name = "DMPHANNHOMTHUOC_NGAYGIOCN")
    private Double dmphannhomthuocNgaygiocn;
    @Column(name = "DMPHANNHOMTHUOC_DT")
    private Boolean dmphannhomthuocDt;
    @Column(name = "DMPHANNHOMTHUOC_QL")
    private Boolean dmphannhomthuocQl;
    @Column(name = "DMPHANNHOMTHUOC_DP")
    private Boolean dmphannhomthuocDp;
    @JoinColumn(name = "DMNHOMBCTHUOC_MASO", referencedColumnName = "DMNHOMBCTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhomBaoCaoThuoc dmnhombcthuocMaso;

//    @OneToMany(mappedBy = "dmphannhomthuocMaso2")
//    private Collection<DmThuoc> dmThuocCollection;
//    @OneToMany(mappedBy = "dmphannhomthuocMaso")
//    private Collection<DmThuoc> dmThuocCollection1;
//    @OneToMany(mappedBy = "dmphannhomthuocMaso1")
//    private Collection<DmThuoc> dmThuocCollection2;
//    @OneToMany(mappedBy = "dmphannhomthuocMaso21")
//    private Collection<DmThuoc> dmThuocCollection3;

    public DmPhanNhomThuoc() {
    }

    public DmPhanNhomThuoc(Integer dmphannhomthuocMaso) {
        this.dmphannhomthuocMaso = dmphannhomthuocMaso;
    }

    public DmPhanNhomThuoc(Integer dmphannhomthuocMaso, String dmphannhomthuocMa, String dmphannhomthuocTen) {
        this.dmphannhomthuocMaso = dmphannhomthuocMaso;
        this.dmphannhomthuocMa = dmphannhomthuocMa;
        this.dmphannhomthuocTen = dmphannhomthuocTen;
    }

    public Integer getDmphannhomthuocMaso() {
        return dmphannhomthuocMaso;
    }

    public void setDmphannhomthuocMaso(Integer dmphannhomthuocMaso) {
        this.dmphannhomthuocMaso = dmphannhomthuocMaso;
    }

    public String getDmphannhomthuocMa() {
        return dmphannhomthuocMa;
    }

    public void setDmphannhomthuocMa(String dmphannhomthuocMa) {
        this.dmphannhomthuocMa = dmphannhomthuocMa;
    }

    public String getDmphannhomthuocMaphu() {
        return dmphannhomthuocMaphu;
    }

    public void setDmphannhomthuocMaphu(String dmphannhomthuocMaphu) {
        this.dmphannhomthuocMaphu = dmphannhomthuocMaphu;
    }

    public String getDmphannhomthuocTen() {
        return dmphannhomthuocTen;
    }

    public void setDmphannhomthuocTen(String dmphannhomthuocTen) {
        this.dmphannhomthuocTen = dmphannhomthuocTen;
    }

    public String getDmphannhomthuocThutu() {
        return dmphannhomthuocThutu;
    }

    public void setDmphannhomthuocThutu(String dmphannhomthuocThutu) {
        this.dmphannhomthuocThutu = dmphannhomthuocThutu;
    }

    public String getDmphannhomthuocGhichu() {
        return dmphannhomthuocGhichu;
    }

    public void setDmphannhomthuocGhichu(String dmphannhomthuocGhichu) {
        this.dmphannhomthuocGhichu = dmphannhomthuocGhichu;
    }

    public String getDmnhanvienCn() {
        return dmnhanvienCn;
    }

    public void setDmnhanvienCn(String dmnhanvienCn) {
        this.dmnhanvienCn = dmnhanvienCn;
    }

    public Double getDmphannhomthuocNgaygiocn() {
        return dmphannhomthuocNgaygiocn;
    }

    public void setDmphannhomthuocNgaygiocn(Double dmphannhomthuocNgaygiocn) {
        this.dmphannhomthuocNgaygiocn = dmphannhomthuocNgaygiocn;
    }

    public Boolean getDmphannhomthuocDt() {
        return dmphannhomthuocDt;
    }

    public void setDmphannhomthuocDt(Boolean dmphannhomthuocDt) {
        this.dmphannhomthuocDt = dmphannhomthuocDt;
    }

    public Boolean getDmphannhomthuocQl() {
        return dmphannhomthuocQl;
    }

    public void setDmphannhomthuocQl(Boolean dmphannhomthuocQl) {
        this.dmphannhomthuocQl = dmphannhomthuocQl;
    }

    public Boolean getDmphannhomthuocDp() {
        return dmphannhomthuocDp;
    }

    public void setDmphannhomthuocDp(Boolean dmphannhomthuocDp) {
        this.dmphannhomthuocDp = dmphannhomthuocDp;
    }

    public DmNhomBaoCaoThuoc getDmnhombcthuocMaso(boolean create) {
if(create && dmnhombcthuocMaso == null) dmnhombcthuocMaso = new DmNhomBaoCaoThuoc();
return dmnhombcthuocMaso;
}
    public DmNhomBaoCaoThuoc getDmnhombcthuocMaso() {
        return dmnhombcthuocMaso;
    }

    public void setDmnhombcthuocMaso(DmNhomBaoCaoThuoc dmnhombcthuocMaso) {
        this.dmnhombcthuocMaso = dmnhombcthuocMaso;
    }


//    public Collection<DmThuoc> getDmThuocCollection() {
//        return dmThuocCollection;
//    }
//
//    public void setDmThuocCollection(Collection<DmThuoc> dmThuocCollection) {
//        this.dmThuocCollection = dmThuocCollection;
//    }

//    public Collection<DmThuoc> getDmThuocCollection1() {
//        return dmThuocCollection1;
//    }
//
//    public void setDmThuocCollection1(Collection<DmThuoc> dmThuocCollection1) {
//        this.dmThuocCollection1 = dmThuocCollection1;
//    }

//    public Collection<DmThuoc> getDmThuocCollection2() {
//        return dmThuocCollection2;
//    }
//
//    public void setDmThuocCollection2(Collection<DmThuoc> dmThuocCollection2) {
//        this.dmThuocCollection2 = dmThuocCollection2;
//    }

//    public Collection<DmThuoc> getDmThuocCollection3() {
//        return dmThuocCollection3;
//    }
//
//    public void setDmThuocCollection3(Collection<DmThuoc> dmThuocCollection3) {
//        this.dmThuocCollection3 = dmThuocCollection3;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmphannhomthuocMaso != null ? dmphannhomthuocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmPhanNhomThuoc)) {
            return false;
        }
        DmPhanNhomThuoc other = (DmPhanNhomThuoc) object;
        if ((this.dmphannhomthuocMaso == null && other.dmphannhomthuocMaso != null) || (this.dmphannhomthuocMaso != null && !this.dmphannhomthuocMaso.equals(other.dmphannhomthuocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmPhanNhomThuoc[dmphannhomthuocMaso=" + dmphannhomthuocMaso + "]";
    }

}


