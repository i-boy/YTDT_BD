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
@Table(name = "DM_NGHE_NGHIEP")
@NamedQueries({})
public class DmNgheNghiep implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NGHE_NGHIEP")
    @SequenceGenerator(name = "DM_NGHE_NGHIEP", sequenceName = "DM_NGHE_NGHIEP_DMNGHENGHIEP_MA", allocationSize = 1)
    @Column(name = "DMNGHENGHIEP_MASO", nullable = false)
    private Integer dmnghenghiepMaso;
    @Column(name = "DMNGHENGHIEP_MA", nullable = false)
    private String dmnghenghiepMa;
    @Column(name = "DMNGHENGHIEP_TEN", nullable = false)
    private String dmnghenghiepTen;
    @Column(name = "DMNGHENGHIEP_PHANLOAI2")
    private String dmnghenghiepPhanloai2;
    @Column(name = "DMNGHENGHIEP_AGEMIN")
    private Integer dmnghenghiepAgemin;
    @Column(name = "DMNGHENGHIEP_AGEMAX")
    private Integer dmnghenghiepAgemax;
    @Column(name = "DMNGHENGHIEP_NGAYGIOCN")
    private Double dmnghenghiepNgaygiocn;
    @Column(name = "DMNGHENGHIEP_QL")
    private Boolean dmnghenghiepQl;
    @Column(name = "DMNGHENGHIEP_DT")
    private Boolean dmnghenghiepDt;
    @Column(name = "DMNGHENGHIEP_DP")
    private Boolean dmnghenghiepDp;
    @JoinColumn(name = "DMNGHENGHIEP_PHANLOAI", referencedColumnName = "DMNGHENGHIEPBC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNgheNghiepBaoCao dmnghenghiepPhanloai;

//    @OneToMany(mappedBy = "benhnhanNghe")
//    private Collection<BenhNhan> benhNhanCollection;
//    @OneToMany(mappedBy = "benhnhanNghe1")
//    private Collection<BenhNhan> benhNhanCollection1;

    public DmNgheNghiep() {
    }

    public DmNgheNghiep(Integer dmnghenghiepMaso) {
        this.dmnghenghiepMaso = dmnghenghiepMaso;
    }

    public DmNgheNghiep(Integer dmnghenghiepMaso, String dmnghenghiepMa, String dmnghenghiepTen) {
        this.dmnghenghiepMaso = dmnghenghiepMaso;
        this.dmnghenghiepMa = dmnghenghiepMa;
        this.dmnghenghiepTen = dmnghenghiepTen;
    }

    public Integer getDmnghenghiepMaso() {
        return dmnghenghiepMaso;
    }

    public void setDmnghenghiepMaso(Integer dmnghenghiepMaso) {
        this.dmnghenghiepMaso = dmnghenghiepMaso;
    }

    public String getDmnghenghiepMa() {
        return dmnghenghiepMa;
    }

    public void setDmnghenghiepMa(String dmnghenghiepMa) {
        this.dmnghenghiepMa = dmnghenghiepMa;
    }

    public String getDmnghenghiepTen() {
        return dmnghenghiepTen;
    }

    public void setDmnghenghiepTen(String dmnghenghiepTen) {
        this.dmnghenghiepTen = dmnghenghiepTen;
    }

    public String getDmnghenghiepPhanloai2() {
        return dmnghenghiepPhanloai2;
    }

    public void setDmnghenghiepPhanloai2(String dmnghenghiepPhanloai2) {
        this.dmnghenghiepPhanloai2 = dmnghenghiepPhanloai2;
    }

    public Integer getDmnghenghiepAgemin() {
        return dmnghenghiepAgemin;
    }

    public void setDmnghenghiepAgemin(Integer dmnghenghiepAgemin) {
        this.dmnghenghiepAgemin = dmnghenghiepAgemin;
    }

    public Integer getDmnghenghiepAgemax() {
        return dmnghenghiepAgemax;
    }

    public void setDmnghenghiepAgemax(Integer dmnghenghiepAgemax) {
        this.dmnghenghiepAgemax = dmnghenghiepAgemax;
    }

    public Double getDmnghenghiepNgaygiocn() {
        return dmnghenghiepNgaygiocn;
    }

    public void setDmnghenghiepNgaygiocn(Double dmnghenghiepNgaygiocn) {
        this.dmnghenghiepNgaygiocn = dmnghenghiepNgaygiocn;
    }

    public Boolean getDmnghenghiepQl() {
        return dmnghenghiepQl;
    }

    public void setDmnghenghiepQl(Boolean dmnghenghiepQl) {
        this.dmnghenghiepQl = dmnghenghiepQl;
    }

    public Boolean getDmnghenghiepDt() {
        return dmnghenghiepDt;
    }

    public void setDmnghenghiepDt(Boolean dmnghenghiepDt) {
        this.dmnghenghiepDt = dmnghenghiepDt;
    }

    public Boolean getDmnghenghiepDp() {
        return dmnghenghiepDp;
    }

    public void setDmnghenghiepDp(Boolean dmnghenghiepDp) {
        this.dmnghenghiepDp = dmnghenghiepDp;
    }

    public DmNgheNghiepBaoCao getDmnghenghiepPhanloai(boolean create) {
if(create && dmnghenghiepPhanloai == null) dmnghenghiepPhanloai = new DmNgheNghiepBaoCao();
return dmnghenghiepPhanloai;
}
    public DmNgheNghiepBaoCao getDmnghenghiepPhanloai() {
        return dmnghenghiepPhanloai;
    }

    public void setDmnghenghiepPhanloai(DmNgheNghiepBaoCao dmnghenghiepPhanloai) {
        this.dmnghenghiepPhanloai = dmnghenghiepPhanloai;
    }



//    public Collection<BenhNhan> getBenhNhanCollection() {
//        return benhNhanCollection;
//    }
//
//    public void setBenhNhanCollection(Collection<BenhNhan> benhNhanCollection) {
//        this.benhNhanCollection = benhNhanCollection;
//    }

//    public Collection<BenhNhan> getBenhNhanCollection1() {
//        return benhNhanCollection1;
//    }
//
//    public void setBenhNhanCollection1(Collection<BenhNhan> benhNhanCollection1) {
//        this.benhNhanCollection1 = benhNhanCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmnghenghiepMaso != null ? dmnghenghiepMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmNgheNghiep)) {
            return false;
        }
        DmNgheNghiep other = (DmNgheNghiep) object;
        if ((this.dmnghenghiepMaso == null && other.dmnghenghiepMaso != null) || (this.dmnghenghiepMaso != null && !this.dmnghenghiepMaso.equals(other.dmnghenghiepMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmNgheNghiep[dmnghenghiepMaso=" + dmnghenghiepMaso + "]";
    }

}


