/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_BAI_THUOC")
@NamedQueries({})
public class DmBaiThuoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_BAI_THUOC")
    @SequenceGenerator(name = "DM_BAI_THUOC", sequenceName = "DM_BAI_THUOC_DMBAITHUOC_MASO_S", allocationSize = 1)
    @Column(name = "DMBAITHUOC_MASO", nullable = false)
    private Integer dmbaithuocMaso;
    @Column(name = "DMBAITHUOC_MA", nullable = false)
    private String dmbaithuocMa;
    @Column(name = "DMBAITHUOC_TEN", nullable = false)
    private String dmbaithuocTen;
    @Column(name = "DMBAITHUOC_NGAYGIOCN")
    private Double dmbaithuocNgaygiocn;
    @Column(name = "DMBAITHUOC_DT")
    private Boolean dmbaithuocDt;
    @Column(name = "DMBAITHUOC_QL")
    private Boolean dmbaithuocQl;
    @Column(name = "DMBAITHUOC_DP")
    private Boolean dmbaithuocDp;
    @Column(name = "DMBAITHUOC_GHICHU")
    private String dmbaithuocGhichu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmbaithuocMaso")
    private Collection<BaithuocThuoc> baithuocThuocCollection;

    public DmBaiThuoc() {
    }

    public DmBaiThuoc(Integer dmbaithuocMaso) {
        this.dmbaithuocMaso = dmbaithuocMaso;
    }

    public DmBaiThuoc(Integer dmbaithuocMaso, String dmbaithuocMa, String dmbaithuocTen) {
        this.dmbaithuocMaso = dmbaithuocMaso;
        this.dmbaithuocMa = dmbaithuocMa;
        this.dmbaithuocTen = dmbaithuocTen;
    }

    public Integer getDmbaithuocMaso() {
        return dmbaithuocMaso;
    }

    public void setDmbaithuocMaso(Integer dmbaithuocMaso) {
        this.dmbaithuocMaso = dmbaithuocMaso;
    }

    public String getDmbaithuocMa() {
        return dmbaithuocMa;
    }

    public void setDmbaithuocMa(String dmbaithuocMa) {
        this.dmbaithuocMa = dmbaithuocMa;
    }

    public String getDmbaithuocTen() {
        return dmbaithuocTen;
    }

    public void setDmbaithuocTen(String dmbaithuocTen) {
        this.dmbaithuocTen = dmbaithuocTen;
    }

    public Double getDmbaithuocNgaygiocn() {
        return dmbaithuocNgaygiocn;
    }

    public void setDmbaithuocNgaygiocn(Double dmbaithuocNgaygiocn) {
        this.dmbaithuocNgaygiocn = dmbaithuocNgaygiocn;
    }

    public Boolean getDmbaithuocDt() {
        return dmbaithuocDt;
    }

    public void setDmbaithuocDt(Boolean dmbaithuocDt) {
        this.dmbaithuocDt = dmbaithuocDt;
    }

    public Boolean getDmbaithuocQl() {
        return dmbaithuocQl;
    }

    public void setDmbaithuocQl(Boolean dmbaithuocQl) {
        this.dmbaithuocQl = dmbaithuocQl;
    }

    public Boolean getDmbaithuocDp() {
        return dmbaithuocDp;
    }

    public void setDmbaithuocDp(Boolean dmbaithuocDp) {
        this.dmbaithuocDp = dmbaithuocDp;
    }

    public String getDmbaithuocGhichu() {
        return dmbaithuocGhichu;
    }

    public void setDmbaithuocGhichu(String dmbaithuocGhichu) {
        this.dmbaithuocGhichu = dmbaithuocGhichu;
    }

    public Collection<BaithuocThuoc> getBaithuocThuocCollection() {
        return baithuocThuocCollection;
    }

    public void setBaithuocThuocCollection(Collection<BaithuocThuoc> baithuocThuocCollection) {
        this.baithuocThuocCollection = baithuocThuocCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmbaithuocMaso != null ? dmbaithuocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmBaiThuoc)) {
            return false;
        }
        DmBaiThuoc other = (DmBaiThuoc) object;
        if ((this.dmbaithuocMaso == null && other.dmbaithuocMaso != null) || (this.dmbaithuocMaso != null && !this.dmbaithuocMaso.equals(other.dmbaithuocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sourcepackage.DmBaiThuoc[dmbaithuocMaso=" + dmbaithuocMaso + "]";
    }

}
