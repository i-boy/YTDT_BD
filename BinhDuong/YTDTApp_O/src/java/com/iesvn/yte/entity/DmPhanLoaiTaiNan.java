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
@Table(name = "DM_PHAN_LOAI_TAI_NAN")
@NamedQueries({})
public class DmPhanLoaiTaiNan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_PHAN_LOAI_TAI_NAN")
    @SequenceGenerator(name = "DM_PHAN_LOAI_TAI_NAN", sequenceName = "DM_PHAN_LOAI_TAI_NAN_DMPLTAINA", allocationSize = 1)
    @Column(name = "DMPLTAINAN_MASO", nullable = false)
    private Integer dmpltainanMaso;
    @Column(name = "DMPLTAINAN_MA", nullable = false)
    private String dmpltainanMa;
    @Column(name = "DMPLTAINAN_TEN", nullable = false)
    private String dmpltainanTen;
    @Column(name = "DMPLTAINAN_TEN2")
    private String dmpltainanTen2;
    @Column(name = "DMPLTAINAN_BHYT")
    private Boolean dmpltainanBhyt;
    @Column(name = "DMPLTAINAN_BHYT2")
    private Boolean dmpltainanBhyt2;
    @Column(name = "DMPLTAINAN_TAPTIN")
    private String dmpltainanTaptin;
    @Column(name = "DMPLTAINAN_NGAYGIOCN")
    private Double dmpltainanNgaygiocn;
    @Column(name = "DMPLTAINAN_DT")
    private Boolean dmpltainanDt;
    @Column(name = "DMPLTAINAN_QL")
    private Boolean dmpltainanQl;
    @Column(name = "DMPLTAINAN_DP")
    private Boolean dmpltainanDp;
    @JoinColumn(name = "DMTAINAN_MASO", referencedColumnName = "DMTAINAN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTaiNan dmtainanMaso;

//    @OneToMany(mappedBy = "tainanMa")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "tainanMa1")
//    private Collection<Hsba> hsbaCollection1;

    public DmPhanLoaiTaiNan() {
    }

    public DmPhanLoaiTaiNan(Integer dmpltainanMaso) {
        this.dmpltainanMaso = dmpltainanMaso;
    }

    public DmPhanLoaiTaiNan(Integer dmpltainanMaso, String dmpltainanMa, String dmpltainanTen) {
        this.dmpltainanMaso = dmpltainanMaso;
        this.dmpltainanMa = dmpltainanMa;
        this.dmpltainanTen = dmpltainanTen;
    }

    public Integer getDmpltainanMaso() {
        return dmpltainanMaso;
    }

    public void setDmpltainanMaso(Integer dmpltainanMaso) {
        this.dmpltainanMaso = dmpltainanMaso;
    }

    public String getDmpltainanMa() {
        return dmpltainanMa;
    }

    public void setDmpltainanMa(String dmpltainanMa) {
        this.dmpltainanMa = dmpltainanMa;
    }

    public String getDmpltainanTen() {
        return dmpltainanTen;
    }

    public void setDmpltainanTen(String dmpltainanTen) {
        this.dmpltainanTen = dmpltainanTen;
    }

    public String getDmpltainanTen2() {
        return dmpltainanTen2;
    }

    public void setDmpltainanTen2(String dmpltainanTen2) {
        this.dmpltainanTen2 = dmpltainanTen2;
    }

    public Boolean getDmpltainanBhyt() {
        return dmpltainanBhyt;
    }

    public void setDmpltainanBhyt(Boolean dmpltainanBhyt) {
        this.dmpltainanBhyt = dmpltainanBhyt;
    }

    public Boolean getDmpltainanBhyt2() {
        return dmpltainanBhyt2;
    }

    public void setDmpltainanBhyt2(Boolean dmpltainanBhyt2) {
        this.dmpltainanBhyt2 = dmpltainanBhyt2;
    }

    public String getDmpltainanTaptin() {
        return dmpltainanTaptin;
    }

    public void setDmpltainanTaptin(String dmpltainanTaptin) {
        this.dmpltainanTaptin = dmpltainanTaptin;
    }

    public Double getDmpltainanNgaygiocn() {
        return dmpltainanNgaygiocn;
    }

    public void setDmpltainanNgaygiocn(Double dmpltainanNgaygiocn) {
        this.dmpltainanNgaygiocn = dmpltainanNgaygiocn;
    }

    public Boolean getDmpltainanDt() {
        return dmpltainanDt;
    }

    public void setDmpltainanDt(Boolean dmpltainanDt) {
        this.dmpltainanDt = dmpltainanDt;
    }

    public Boolean getDmpltainanQl() {
        return dmpltainanQl;
    }

    public void setDmpltainanQl(Boolean dmpltainanQl) {
        this.dmpltainanQl = dmpltainanQl;
    }

    public Boolean getDmpltainanDp() {
        return dmpltainanDp;
    }

    public void setDmpltainanDp(Boolean dmpltainanDp) {
        this.dmpltainanDp = dmpltainanDp;
    }

    public DmTaiNan getDmtainanMaso(boolean create) {
if(create && dmtainanMaso == null) dmtainanMaso = new DmTaiNan();
return dmtainanMaso;
}
    public DmTaiNan getDmtainanMaso() {
        return dmtainanMaso;
    }

    public void setDmtainanMaso(DmTaiNan dmtainanMaso) {
        this.dmtainanMaso = dmtainanMaso;
    }


//    public Collection<Hsba> getHsbaCollection() {
//        return hsbaCollection;
//    }
//
//    public void setHsbaCollection(Collection<Hsba> hsbaCollection) {
//        this.hsbaCollection = hsbaCollection;
//    }

//    public Collection<Hsba> getHsbaCollection1() {
//        return hsbaCollection1;
//    }
//
//    public void setHsbaCollection1(Collection<Hsba> hsbaCollection1) {
//        this.hsbaCollection1 = hsbaCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmpltainanMaso != null ? dmpltainanMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmPhanLoaiTaiNan)) {
            return false;
        }
        DmPhanLoaiTaiNan other = (DmPhanLoaiTaiNan) object;
        if ((this.dmpltainanMaso == null && other.dmpltainanMaso != null) || (this.dmpltainanMaso != null && !this.dmpltainanMaso.equals(other.dmpltainanMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmPhanLoaiTaiNan[dmpltainanMaso=" + dmpltainanMaso + "]";
    }

}


