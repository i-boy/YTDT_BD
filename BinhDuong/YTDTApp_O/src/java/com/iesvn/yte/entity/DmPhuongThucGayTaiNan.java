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
@Table(name = "DM_PHUONG_THUC_GAY_TAI_NAN")
@NamedQueries({})
public class DmPhuongThucGayTaiNan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_PHUONG_THUC_GAY_TAI_NAN")
    @SequenceGenerator(name = "DM_PHUONG_THUC_GAY_TAI_NAN", sequenceName = "DM_PHUONG_THUC_GAY_TAI_NAN_DMP", allocationSize = 1)
    @Column(name = "DMPTGTN_MASO", nullable = false)
    private Integer dmptgtnMaso;
    @Column(name = "DMPTGTN_MA", nullable = false)
    private String dmptgtnMa;
    @Column(name = "DMPTGTN_TEN", nullable = false)
    private String dmptgtnTen;
    @Column(name = "DMPTGTN_BHYT")
    private Boolean dmptgtnBhyt;
    @Column(name = "DMPTGTN_DT")
    private Boolean dmptgtnDt;
    @Column(name = "DMPTGTN_QL")
    private Boolean dmptgtnQl;
    @Column(name = "DMPTGTN_DP")
    private Boolean dmptgtnDp;
    @Column(name = "DMPTGTN_NGAYGIOCN")
    private Double dmptgtnNgaygiocn;
//    @OneToMany(mappedBy = "dmptgtnMaso")
//    private Collection<TiepDon> tiepDonCollection;
    @JoinColumn(name = "DMTAINAN_MASO", referencedColumnName = "DMTAINAN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTaiNan dmtainanMaso;

//    @OneToMany(mappedBy = "dmptgtnMaso")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "dmptgtnMaso1")
//    private Collection<Hsba> hsbaCollection1;

    public DmPhuongThucGayTaiNan() {
    }

    public DmPhuongThucGayTaiNan(Integer dmptgtnMaso) {
        this.dmptgtnMaso = dmptgtnMaso;
    }

    public DmPhuongThucGayTaiNan(Integer dmptgtnMaso, String dmptgtnMa, String dmptgtnTen) {
        this.dmptgtnMaso = dmptgtnMaso;
        this.dmptgtnMa = dmptgtnMa;
        this.dmptgtnTen = dmptgtnTen;
    }

    public Integer getDmptgtnMaso() {
        return dmptgtnMaso;
    }

    public void setDmptgtnMaso(Integer dmptgtnMaso) {
        this.dmptgtnMaso = dmptgtnMaso;
    }

    public String getDmptgtnMa() {
        return dmptgtnMa;
    }

    public void setDmptgtnMa(String dmptgtnMa) {
        this.dmptgtnMa = dmptgtnMa;
    }

    public String getDmptgtnTen() {
        return dmptgtnTen;
    }

    public void setDmptgtnTen(String dmptgtnTen) {
        this.dmptgtnTen = dmptgtnTen;
    }

    public Boolean getDmptgtnBhyt() {
        return dmptgtnBhyt;
    }

    public void setDmptgtnBhyt(Boolean dmptgtnBhyt) {
        this.dmptgtnBhyt = dmptgtnBhyt;
    }

    public Boolean getDmptgtnDt() {
        return dmptgtnDt;
    }

    public void setDmptgtnDt(Boolean dmptgtnDt) {
        this.dmptgtnDt = dmptgtnDt;
    }

    public Boolean getDmptgtnQl() {
        return dmptgtnQl;
    }

    public void setDmptgtnQl(Boolean dmptgtnQl) {
        this.dmptgtnQl = dmptgtnQl;
    }

    public Boolean getDmptgtnDp() {
        return dmptgtnDp;
    }

    public void setDmptgtnDp(Boolean dmptgtnDp) {
        this.dmptgtnDp = dmptgtnDp;
    }

    public Double getDmptgtnNgaygiocn() {
        return dmptgtnNgaygiocn;
    }

    public void setDmptgtnNgaygiocn(Double dmptgtnNgaygiocn) {
        this.dmptgtnNgaygiocn = dmptgtnNgaygiocn;
    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }

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
        hash += (dmptgtnMaso != null ? dmptgtnMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmPhuongThucGayTaiNan)) {
            return false;
        }
        DmPhuongThucGayTaiNan other = (DmPhuongThucGayTaiNan) object;
        if ((this.dmptgtnMaso == null && other.dmptgtnMaso != null) || (this.dmptgtnMaso != null && !this.dmptgtnMaso.equals(other.dmptgtnMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmPhuongThucGayTaiNan[dmptgtnMaso=" + dmptgtnMaso + "]";
    }

}


