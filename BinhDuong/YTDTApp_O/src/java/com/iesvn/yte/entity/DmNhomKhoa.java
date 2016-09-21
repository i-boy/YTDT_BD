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
@Table(name = "DM_NHOM_KHOA")
@NamedQueries({})
public class DmNhomKhoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NHOM_KHO")
    @SequenceGenerator(name = "DM_NHOM_KHO", sequenceName = "DM_NHOM_KHOA_DMNHOMKHOA_MASO_S", allocationSize = 1)
    @Column(name = "DMNHOMKHOA_MASO", nullable = false)
    private Integer dmnhomkhoaMaso;
    @Column(name = "DMNHOMKHOA_MA")
    private String dmnhomkhoaMa;
    @Column(name = "DMNHOMKHOA_TEN", nullable = false)
    private String dmnhomkhoaTen;
    @Column(name = "DMNHOMKHOA_NGAYGIOCN")
    private Double dmnhomkhoaNgaygiocn;
    @Column(name = "DMNHOMKHOA_DT")
    private Boolean dmnhomkhoaDt;
    @Column(name = "DMNHOMKHOA_QL")
    private Boolean dmnhomkhoaQl;
    @Column(name = "DMNHOMKHOA_DP")
    private Boolean dmnhomkhoaDp;
//    @OneToMany(mappedBy = "dmnhomkhoaMaso")
//    private Collection<DmKhoa> dmKhoaCollection;
//    @OneToMany(mappedBy = "dmnhomkhoaMaso1")
//    private Collection<DmKhoa> dmKhoaCollection1;

    public DmNhomKhoa() {
    }

    public DmNhomKhoa(Integer dmnhomkhoaMaso) {
        this.dmnhomkhoaMaso = dmnhomkhoaMaso;
    }

    public DmNhomKhoa(Integer dmnhomkhoaMaso, String dmnhomkhoaTen) {
        this.dmnhomkhoaMaso = dmnhomkhoaMaso;
        this.dmnhomkhoaTen = dmnhomkhoaTen;
    }

    public Integer getDmnhomkhoaMaso() {
        return dmnhomkhoaMaso;
    }

    public void setDmnhomkhoaMaso(Integer dmnhomkhoaMaso) {
        this.dmnhomkhoaMaso = dmnhomkhoaMaso;
    }

    public String getDmnhomkhoaMa() {
        return dmnhomkhoaMa;
    }

    public void setDmnhomkhoaMa(String dmnhomkhoaMa) {
        this.dmnhomkhoaMa = dmnhomkhoaMa;
    }

    public String getDmnhomkhoaTen() {
        return dmnhomkhoaTen;
    }

    public void setDmnhomkhoaTen(String dmnhomkhoaTen) {
        this.dmnhomkhoaTen = dmnhomkhoaTen;
    }

    public Double getDmnhomkhoaNgaygiocn() {
        return dmnhomkhoaNgaygiocn;
    }

    public void setDmnhomkhoaNgaygiocn(Double dmnhomkhoaNgaygiocn) {
        this.dmnhomkhoaNgaygiocn = dmnhomkhoaNgaygiocn;
    }

    public Boolean getDmnhomkhoaDt() {
        return dmnhomkhoaDt;
    }

    public void setDmnhomkhoaDt(Boolean dmnhomkhoaDt) {
        this.dmnhomkhoaDt = dmnhomkhoaDt;
    }

    public Boolean getDmnhomkhoaQl() {
        return dmnhomkhoaQl;
    }

    public void setDmnhomkhoaQl(Boolean dmnhomkhoaQl) {
        this.dmnhomkhoaQl = dmnhomkhoaQl;
    }

    public Boolean getDmnhomkhoaDp() {
        return dmnhomkhoaDp;
    }

    public void setDmnhomkhoaDp(Boolean dmnhomkhoaDp) {
        this.dmnhomkhoaDp = dmnhomkhoaDp;
    }

//    public Collection<DmKhoa> getDmKhoaCollection() {
//        return dmKhoaCollection;
//    }
//
//    public void setDmKhoaCollection(Collection<DmKhoa> dmKhoaCollection) {
//        this.dmKhoaCollection = dmKhoaCollection;
//    }

//    public Collection<DmKhoa> getDmKhoaCollection1() {
//        return dmKhoaCollection1;
//    }
//
//    public void setDmKhoaCollection1(Collection<DmKhoa> dmKhoaCollection1) {
//        this.dmKhoaCollection1 = dmKhoaCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmnhomkhoaMaso != null ? dmnhomkhoaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmNhomKhoa)) {
            return false;
        }
        DmNhomKhoa other = (DmNhomKhoa) object;
        if ((this.dmnhomkhoaMaso == null && other.dmnhomkhoaMaso != null) || (this.dmnhomkhoaMaso != null && !this.dmnhomkhoaMaso.equals(other.dmnhomkhoaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmNhomKhoa[dmnhomkhoaMaso=" + dmnhomkhoaMaso + "]";
    }

}


