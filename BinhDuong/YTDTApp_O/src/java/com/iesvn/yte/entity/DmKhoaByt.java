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
@Table(name = "DM_KHOA_BYT")
@NamedQueries({})
public class DmKhoaByt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_KHOA_BYT")
    @SequenceGenerator(name = "DM_KHOA_BYT", sequenceName = "DM_KHOA_BYT_DMKHOABYT_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMKHOABYT_MASO", nullable = false)
    private Integer dmkhoabytMaso;
    @Column(name = "DMKHOABYT_MA")
    private String dmkhoabytMa;
    @Column(name = "DMKHOABYT_MABYT3")
    private String dmkhoabytMabyt3;
    @Column(name = "DMKHOABYT_TEN")
    private String dmkhoabytTen;
    @Column(name = "DMKHOABYT_NHOM")
    private Integer dmkhoabytNhom;
    @Column(name = "DMKHOABYT_THUTUBC")
    private Integer dmkhoabytThutubc;
    @Column(name = "DMKHOABYT_NGAYGIOCN")
    private Double dmkhoabytNgaygiocn;
    
    @Column(name = "DMKHOABYT_DT")
    private Boolean dmkhoabytDt;
    @Column(name = "DMKHOABYT_QL")
    private Boolean dmkhoabytQl;
    @Column(name = "DMKHOABYT_DP")
    private Boolean dmkhoabytDp;
    

//    @OneToMany(mappedBy = "dmkhoaMabyt")
//    private Collection<DmKhoa> dmKhoaCollection;
//    @OneToMany(mappedBy = "dmkhoaMabyt3")
//    private Collection<DmKhoa> dmKhoaCollection1;
//    @OneToMany(mappedBy = "dmkhoaMabyt1")
//    private Collection<DmKhoa> dmKhoaCollection2;
//    @OneToMany(mappedBy = "dmkhoaMabyt31")
//    private Collection<DmKhoa> dmKhoaCollection3;
    public DmKhoaByt() {
    }

    public DmKhoaByt(Integer dmkhoabytMaso) {
        this.dmkhoabytMaso = dmkhoabytMaso;
    }

    public Integer getDmkhoabytMaso() {
        return dmkhoabytMaso;
    }

    public void setDmkhoabytMaso(Integer dmkhoabytMaso) {
        this.dmkhoabytMaso = dmkhoabytMaso;
    }

    public String getDmkhoabytMa() {
        return dmkhoabytMa;
    }

    public void setDmkhoabytMa(String dmkhoabytMa) {
        this.dmkhoabytMa = dmkhoabytMa;
    }

    public String getDmkhoabytMabyt3() {
        return dmkhoabytMabyt3;
    }

    public void setDmkhoabytMabyt3(String dmkhoabytMabyt3) {
        this.dmkhoabytMabyt3 = dmkhoabytMabyt3;
    }

    public String getDmkhoabytTen() {
        return dmkhoabytTen;
    }

    public void setDmkhoabytTen(String dmkhoabytTen) {
        this.dmkhoabytTen = dmkhoabytTen;
    }

    public Integer getDmkhoabytNhom() {
        return dmkhoabytNhom;
    }

    public void setDmkhoabytNhom(Integer dmkhoabytNhom) {
        this.dmkhoabytNhom = dmkhoabytNhom;
    }

    public Integer getDmkhoabytThutubc() {
        return dmkhoabytThutubc;
    }

    public void setDmkhoabytThutubc(Integer dmkhoabytThutubc) {
        this.dmkhoabytThutubc = dmkhoabytThutubc;
    }

    public Double getDmkhoabytNgaygiocn() {
        return dmkhoabytNgaygiocn;
    }

    public void setDmkhoabytNgaygiocn(Double dmkhoabytNgaygiocn) {
        this.dmkhoabytNgaygiocn = dmkhoabytNgaygiocn;
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

//    public Collection<DmKhoa> getDmKhoaCollection2() {
//        return dmKhoaCollection2;
//    }
//
//    public void setDmKhoaCollection2(Collection<DmKhoa> dmKhoaCollection2) {
//        this.dmKhoaCollection2 = dmKhoaCollection2;
//    }

//    public Collection<DmKhoa> getDmKhoaCollection3() {
//        return dmKhoaCollection3;
//    }
//
//    public void setDmKhoaCollection3(Collection<DmKhoa> dmKhoaCollection3) {
//        this.dmKhoaCollection3 = dmKhoaCollection3;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmkhoabytMaso != null ? dmkhoabytMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmKhoaByt)) {
            return false;
        }
        DmKhoaByt other = (DmKhoaByt) object;
        if ((this.dmkhoabytMaso == null && other.dmkhoabytMaso != null) || (this.dmkhoabytMaso != null && !this.dmkhoabytMaso.equals(other.dmkhoabytMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmKhoaByt[dmkhoabytMaso=" + dmkhoabytMaso + "]";
    }

    public Boolean getDmkhoabytDt() {
        return dmkhoabytDt;
    }

    public void setDmkhoabytDt(Boolean dmkhoabytDt) {
        this.dmkhoabytDt = dmkhoabytDt;
    }

    public Boolean getDmkhoabytQl() {
        return dmkhoabytQl;
    }

    public void setDmkhoabytQl(Boolean dmkhoabytQl) {
        this.dmkhoabytQl = dmkhoabytQl;
    }

    public Boolean getDmkhoabytDp() {
        return dmkhoabytDp;
    }

    public void setDmkhoabytDp(Boolean dmkhoabytDp) {
        this.dmkhoabytDp = dmkhoabytDp;
    }
}


