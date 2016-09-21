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
@Table(name = "DM_BENH_VN")
@NamedQueries({})
public class DmBenhVn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_BENH_VN")
    @SequenceGenerator(name = "DM_BENH_VN", sequenceName = "DM_BENH_VN_DMBENHVN_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMBENHVN_MASO", nullable = false)
    private Integer dmbenhvnMaso;
    @Column(name = "DMBENHVN_MA")
    private String dmbenhvnMa;
    @Column(name = "DMBENHVN_MACHUNG")
    private String dmbenhvnMachung;
    @Column(name = "DMBENHVN_MAICD10", nullable = false)
    private String dmbenhvnMaicd10;
    @Column(name = "DMBENHVN_MABYT", nullable = false)
    private short dmbenhvnMabyt;
    @Column(name = "DMBENHVN_TEN", nullable = false)
    private String dmbenhvnTen;
    @Column(name = "DMBENHVN_MAICD10B")
    private String dmbenhvnMaicd10b;
    @Column(name = "DMBENHVN_BAOGOM")
    private String dmbenhvnBaogom;
    @Column(name = "DMBENHVN_NGAYGIOCN")
    private Double dmbenhvnNgaygiocn;
    @Column(name = "DMBENHVN_QL")
    private Boolean dmbenhvnQl;
    @Column(name = "DMBENHVN_DT")
    private Boolean dmbenhvnDt;
    @Column(name = "DMBENHVN_DP")
    private Boolean dmbenhvnDp;
//    @OneToMany(mappedBy = "dmbenhicdBenhvn")
//    private Collection<DmBenhIcd> dmBenhIcdCollection;
//    @OneToMany(mappedBy = "dmbenhicdBenhvn1")
//    private Collection<DmBenhIcd> dmBenhIcdCollection1;
    @JoinColumn(name = "DMCHUONGBENH_MASO", referencedColumnName = "DMCHUONGBENH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmChuongBenh dmchuongbenhMaso;

//    @OneToMany(mappedBy = "dmbenhvnMaso")
//    private Collection<DmBenhTruyenNhiem> dmBenhTruyenNhiemCollection;
//    @OneToMany(mappedBy = "dmbenhvnMaso1")
//    private Collection<DmBenhTruyenNhiem> dmBenhTruyenNhiemCollection1;

    public DmBenhVn() {
    }

    public DmBenhVn(Integer dmbenhvnMaso) {
        this.dmbenhvnMaso = dmbenhvnMaso;
    }

    public DmBenhVn(Integer dmbenhvnMaso, String dmbenhvnMaicd10, short dmbenhvnMabyt, String dmbenhvnTen) {
        this.dmbenhvnMaso = dmbenhvnMaso;
        this.dmbenhvnMaicd10 = dmbenhvnMaicd10;
        this.dmbenhvnMabyt = dmbenhvnMabyt;
        this.dmbenhvnTen = dmbenhvnTen;
    }

    public Integer getDmbenhvnMaso() {
        return dmbenhvnMaso;
    }

    public void setDmbenhvnMaso(Integer dmbenhvnMaso) {
        this.dmbenhvnMaso = dmbenhvnMaso;
    }

    public String getDmbenhvnMa() {
        return dmbenhvnMa;
    }

    public void setDmbenhvnMa(String dmbenhvnMa) {
        this.dmbenhvnMa = dmbenhvnMa;
    }

    public String getDmbenhvnMachung() {
        return dmbenhvnMachung;
    }

    public void setDmbenhvnMachung(String dmbenhvnMachung) {
        this.dmbenhvnMachung = dmbenhvnMachung;
    }

    public String getDmbenhvnMaicd10() {
        return dmbenhvnMaicd10;
    }

    public void setDmbenhvnMaicd10(String dmbenhvnMaicd10) {
        this.dmbenhvnMaicd10 = dmbenhvnMaicd10;
    }

    public short getDmbenhvnMabyt() {
        return dmbenhvnMabyt;
    }

    public void setDmbenhvnMabyt(short dmbenhvnMabyt) {
        this.dmbenhvnMabyt = dmbenhvnMabyt;
    }

    public String getDmbenhvnTen() {
        return dmbenhvnTen;
    }

    public void setDmbenhvnTen(String dmbenhvnTen) {
        this.dmbenhvnTen = dmbenhvnTen;
    }

    public String getDmbenhvnMaicd10b() {
        return dmbenhvnMaicd10b;
    }

    public void setDmbenhvnMaicd10b(String dmbenhvnMaicd10b) {
        this.dmbenhvnMaicd10b = dmbenhvnMaicd10b;
    }

    public String getDmbenhvnBaogom() {
        return dmbenhvnBaogom;
    }

    public void setDmbenhvnBaogom(String dmbenhvnBaogom) {
        this.dmbenhvnBaogom = dmbenhvnBaogom;
    }

    public Double getDmbenhvnNgaygiocn() {
        return dmbenhvnNgaygiocn;
    }

    public void setDmbenhvnNgaygiocn(Double dmbenhvnNgaygiocn) {
        this.dmbenhvnNgaygiocn = dmbenhvnNgaygiocn;
    }

    public Boolean getDmbenhvnQl() {
        return dmbenhvnQl;
    }

    public void setDmbenhvnQl(Boolean dmbenhvnQl) {
        this.dmbenhvnQl = dmbenhvnQl;
    }

    public Boolean getDmbenhvnDt() {
        return dmbenhvnDt;
    }

    public void setDmbenhvnDt(Boolean dmbenhvnDt) {
        this.dmbenhvnDt = dmbenhvnDt;
    }

    public Boolean getDmbenhvnDp() {
        return dmbenhvnDp;
    }

    public void setDmbenhvnDp(Boolean dmbenhvnDp) {
        this.dmbenhvnDp = dmbenhvnDp;
    }

//    public Collection<DmBenhIcd> getDmBenhIcdCollection() {
//        return dmBenhIcdCollection;
//    }
//
//    public void setDmBenhIcdCollection(Collection<DmBenhIcd> dmBenhIcdCollection) {
//        this.dmBenhIcdCollection = dmBenhIcdCollection;
//    }

//    public Collection<DmBenhIcd> getDmBenhIcdCollection1() {
//        return dmBenhIcdCollection1;
//    }
//
//    public void setDmBenhIcdCollection1(Collection<DmBenhIcd> dmBenhIcdCollection1) {
//        this.dmBenhIcdCollection1 = dmBenhIcdCollection1;
//    }

    public DmChuongBenh getDmchuongbenhMaso(boolean create) {
if(create && dmchuongbenhMaso == null) dmchuongbenhMaso = new DmChuongBenh();
return dmchuongbenhMaso;
}
    public DmChuongBenh getDmchuongbenhMaso() {
        return dmchuongbenhMaso;
    }

    public void setDmchuongbenhMaso(DmChuongBenh dmchuongbenhMaso) {
        this.dmchuongbenhMaso = dmchuongbenhMaso;
    }



//    public Collection<DmBenhTruyenNhiem> getDmBenhTruyenNhiemCollection() {
//        return dmBenhTruyenNhiemCollection;
//    }
//
//    public void setDmBenhTruyenNhiemCollection(Collection<DmBenhTruyenNhiem> dmBenhTruyenNhiemCollection) {
//        this.dmBenhTruyenNhiemCollection = dmBenhTruyenNhiemCollection;
//    }

//    public Collection<DmBenhTruyenNhiem> getDmBenhTruyenNhiemCollection1() {
//        return dmBenhTruyenNhiemCollection1;
//    }
//
//    public void setDmBenhTruyenNhiemCollection1(Collection<DmBenhTruyenNhiem> dmBenhTruyenNhiemCollection1) {
//        this.dmBenhTruyenNhiemCollection1 = dmBenhTruyenNhiemCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmbenhvnMaso != null ? dmbenhvnMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmBenhVn)) {
            return false;
        }
        DmBenhVn other = (DmBenhVn) object;
        if ((this.dmbenhvnMaso == null && other.dmbenhvnMaso != null) || (this.dmbenhvnMaso != null && !this.dmbenhvnMaso.equals(other.dmbenhvnMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmBenhVn[dmbenhvnMaso=" + dmbenhvnMaso + "]";
    }

}


