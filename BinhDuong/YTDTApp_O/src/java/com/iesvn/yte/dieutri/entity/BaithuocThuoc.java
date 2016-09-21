/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmThuoc;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "BAITHUOC_THUOC")
@NamedQueries({})
public class BaithuocThuoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BAITHUOC_THUOC")
    @SequenceGenerator(name = "BAITHUOC_THUOC", sequenceName = "BAITHUOC_THUOC_BAITHUOCTHUOC_M", allocationSize = 1)
    @Column(name = "BAITHUOCTHUOC_MASO", nullable = false)
    private Integer baithuocthuocMaso;
    @Column(name = "BAITHUOCTHUOC_NGAYGIOCN")
    private Double baithuocthuocNgaygiocn;
    @Column(name = "BAITHUOCTHUOC_SOLUONG", nullable = false)
    private int baithuocthuocSoluong;
    @JoinColumn(name = "DMTHUOC_MASO", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc dmthuocMaso;
    @JoinColumn(name = "DMBAITHUOC_MASO", referencedColumnName = "DMBAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBaiThuoc dmbaithuocMaso;

    public BaithuocThuoc() {
    }

    public BaithuocThuoc(Integer baithuocthuocMaso) {
        this.baithuocthuocMaso = baithuocthuocMaso;
    }

    public BaithuocThuoc(Integer baithuocthuocMaso, int baithuocthuocSoluong) {
        this.baithuocthuocMaso = baithuocthuocMaso;
        this.baithuocthuocSoluong = baithuocthuocSoluong;
    }

    public Integer getBaithuocthuocMaso() {
        return baithuocthuocMaso;
    }

    public void setBaithuocthuocMaso(Integer baithuocthuocMaso) {
        this.baithuocthuocMaso = baithuocthuocMaso;
    }

    public Double getBaithuocthuocNgaygiocn() {
        return baithuocthuocNgaygiocn;
    }

    public void setBaithuocthuocNgaygiocn(Double baithuocthuocNgaygiocn) {
        this.baithuocthuocNgaygiocn = baithuocthuocNgaygiocn;
    }

    public int getBaithuocthuocSoluong() {
        return baithuocthuocSoluong;
    }

    public void setBaithuocthuocSoluong(int baithuocthuocSoluong) {
        this.baithuocthuocSoluong = baithuocthuocSoluong;
    }

    public DmThuoc getDmthuocMaso() {
        return dmthuocMaso;
    }

    public void setDmthuocMaso(DmThuoc dmthuocMaso) {
        this.dmthuocMaso = dmthuocMaso;
    }

    public DmBaiThuoc getDmbaithuocMaso() {
        return dmbaithuocMaso;
    }

    public void setDmbaithuocMaso(DmBaiThuoc dmbaithuocMaso) {
        this.dmbaithuocMaso = dmbaithuocMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (baithuocthuocMaso != null ? baithuocthuocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaithuocThuoc)) {
            return false;
        }
        BaithuocThuoc other = (BaithuocThuoc) object;
        if ((this.baithuocthuocMaso == null && other.baithuocthuocMaso != null) || (this.baithuocthuocMaso != null && !this.baithuocthuocMaso.equals(other.baithuocthuocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sourcepackage.BaithuocThuoc[baithuocthuocMaso=" + baithuocthuocMaso + "]";
    }

}
