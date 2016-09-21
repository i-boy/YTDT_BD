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
@Table(name = "DM_NHOM_BAO_CAO_THUOC")
@NamedQueries({})
public class DmNhomBaoCaoThuoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NHOM_BAO_CAO_THUOC")
    @SequenceGenerator(name = "DM_NHOM_BAO_CAO_THUOC", sequenceName = "DM_NHOM_BAO_CAO_THUOC_DMNHOMBC", allocationSize = 1)
    @Column(name = "DMNHOMBCTHUOC_MASO", nullable = false)
    private Integer dmnhombcthuocMaso;
    @Column(name = "DMNHOMBCTHUOC_MA")
    private String dmnhombcthuocMa;
    @Column(name = "DMNHOMBCTHUOC_MAPHU")
    private String dmnhombcthuocMaphu;
    @Column(name = "DMNHOMBCTHUOC_TEN", nullable = false)
    private String dmnhombcthuocTen;
    @Column(name = "DMNHOMBCTHUOC_THUTU")
    private String dmnhombcthuocThutu;
    @Column(name = "DMNHOMBCTHUOC_GHICHU")
    private String dmnhombcthuocGhichu;
    @Column(name = "DMNHANVIEN_CN")
    private String dmnhanvienCn;
    @Column(name = "DMNHOMBCTHUOC_NGAYGIOCN")
    private Double dmnhombcthuocNgaygiocn;
    @Column(name = "DMNHOMBCTHUOC_DT")
    private Boolean dmnhombcthuocDt;
    @Column(name = "DMNHOMBCTHUOC_QL")
    private Boolean dmnhombcthuocQl;
    @Column(name = "DMNHOMBCTHUOC_DP")
    private Boolean dmnhombcthuocDp;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmnhombcthuocMaso")
//    private Collection<DmNhomBaoCaoLoaiThuoc> dmNhomBaoCaoLoaiThuocCollection;
//    @OneToMany(mappedBy = "dmnhombcthuocMaso")
//    private Collection<DmPhanNhomThuoc> dmPhanNhomThuocCollection;
//    @OneToMany(mappedBy = "dmnhombcthuocMaso1")
//    private Collection<DmPhanNhomThuoc> dmPhanNhomThuocCollection1;

    public DmNhomBaoCaoThuoc() {
    }

    public DmNhomBaoCaoThuoc(Integer dmnhombcthuocMaso) {
        this.dmnhombcthuocMaso = dmnhombcthuocMaso;
    }

    public DmNhomBaoCaoThuoc(Integer dmnhombcthuocMaso, String dmnhombcthuocTen) {
        this.dmnhombcthuocMaso = dmnhombcthuocMaso;
        this.dmnhombcthuocTen = dmnhombcthuocTen;
    }

    public Integer getDmnhombcthuocMaso() {
        return dmnhombcthuocMaso;
    }

    public void setDmnhombcthuocMaso(Integer dmnhombcthuocMaso) {
        this.dmnhombcthuocMaso = dmnhombcthuocMaso;
    }

    public String getDmnhombcthuocMa() {
        return dmnhombcthuocMa;
    }

    public void setDmnhombcthuocMa(String dmnhombcthuocMa) {
        this.dmnhombcthuocMa = dmnhombcthuocMa;
    }

    public String getDmnhombcthuocMaphu() {
        return dmnhombcthuocMaphu;
    }

    public void setDmnhombcthuocMaphu(String dmnhombcthuocMaphu) {
        this.dmnhombcthuocMaphu = dmnhombcthuocMaphu;
    }

    public String getDmnhombcthuocTen() {
        return dmnhombcthuocTen;
    }

    public void setDmnhombcthuocTen(String dmnhombcthuocTen) {
        this.dmnhombcthuocTen = dmnhombcthuocTen;
    }

    public String getDmnhombcthuocThutu() {
        return dmnhombcthuocThutu;
    }

    public void setDmnhombcthuocThutu(String dmnhombcthuocThutu) {
        this.dmnhombcthuocThutu = dmnhombcthuocThutu;
    }

    public String getDmnhombcthuocGhichu() {
        return dmnhombcthuocGhichu;
    }

    public void setDmnhombcthuocGhichu(String dmnhombcthuocGhichu) {
        this.dmnhombcthuocGhichu = dmnhombcthuocGhichu;
    }

    public String getDmnhanvienCn() {
        return dmnhanvienCn;
    }

    public void setDmnhanvienCn(String dmnhanvienCn) {
        this.dmnhanvienCn = dmnhanvienCn;
    }

    public Double getDmnhombcthuocNgaygiocn() {
        return dmnhombcthuocNgaygiocn;
    }

    public void setDmnhombcthuocNgaygiocn(Double dmnhombcthuocNgaygiocn) {
        this.dmnhombcthuocNgaygiocn = dmnhombcthuocNgaygiocn;
    }

    public Boolean getDmnhombcthuocDt() {
        return dmnhombcthuocDt;
    }

    public void setDmnhombcthuocDt(Boolean dmnhombcthuocDt) {
        this.dmnhombcthuocDt = dmnhombcthuocDt;
    }

    public Boolean getDmnhombcthuocQl() {
        return dmnhombcthuocQl;
    }

    public void setDmnhombcthuocQl(Boolean dmnhombcthuocQl) {
        this.dmnhombcthuocQl = dmnhombcthuocQl;
    }

    public Boolean getDmnhombcthuocDp() {
        return dmnhombcthuocDp;
    }

    public void setDmnhombcthuocDp(Boolean dmnhombcthuocDp) {
        this.dmnhombcthuocDp = dmnhombcthuocDp;
    }

//    public Collection<DmNhomBaoCaoLoaiThuoc> getDmNhomBaoCaoLoaiThuocCollection() {
//        return dmNhomBaoCaoLoaiThuocCollection;
//    }
//
//    public void setDmNhomBaoCaoLoaiThuocCollection(Collection<DmNhomBaoCaoLoaiThuoc> dmNhomBaoCaoLoaiThuocCollection) {
//        this.dmNhomBaoCaoLoaiThuocCollection = dmNhomBaoCaoLoaiThuocCollection;
//    }

//    public Collection<DmPhanNhomThuoc> getDmPhanNhomThuocCollection() {
//        return dmPhanNhomThuocCollection;
//    }
//
//    public void setDmPhanNhomThuocCollection(Collection<DmPhanNhomThuoc> dmPhanNhomThuocCollection) {
//        this.dmPhanNhomThuocCollection = dmPhanNhomThuocCollection;
//    }

//    public Collection<DmPhanNhomThuoc> getDmPhanNhomThuocCollection1() {
//        return dmPhanNhomThuocCollection1;
//    }
//
//    public void setDmPhanNhomThuocCollection1(Collection<DmPhanNhomThuoc> dmPhanNhomThuocCollection1) {
//        this.dmPhanNhomThuocCollection1 = dmPhanNhomThuocCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmnhombcthuocMaso != null ? dmnhombcthuocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmNhomBaoCaoThuoc)) {
            return false;
        }
        DmNhomBaoCaoThuoc other = (DmNhomBaoCaoThuoc) object;
        if ((this.dmnhombcthuocMaso == null && other.dmnhombcthuocMaso != null) || (this.dmnhombcthuocMaso != null && !this.dmnhombcthuocMaso.equals(other.dmnhombcthuocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmNhomBaoCaoThuoc[dmnhombcthuocMaso=" + dmnhombcthuocMaso + "]";
    }

}


