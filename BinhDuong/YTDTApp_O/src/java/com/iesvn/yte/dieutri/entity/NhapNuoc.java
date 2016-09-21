/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HP
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "NHAP_NUOC")
@NamedQueries({})
public class NhapNuoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NHAP_NUOC_NHAPNUOC_MASO")
    @SequenceGenerator(name = "NHAP_NUOC_NHAPNUOC_MASO", sequenceName = "NHAP_NUOC_NHAPNUOC_MASO_SEQ", allocationSize = 1)
    @Column(name = "NHAPNUOC_MASO", nullable = false)
    private Integer nhapnuocMaso;
    @Column(name = "NHAPNUOC_NGAY")
    @Temporal(TemporalType.DATE)
    private Date nhapnuocNgay;
    @Column(name = "NHAPNUOC_SOLUONG")
    private Integer nhapnuocSoluong;
    @JoinColumn(name = "DTDMB_MASO", referencedColumnName = "DTDMB_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBuong dtdmbMaso;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;

    @JoinColumn(name = "DMTANG_MASO", referencedColumnName = "DMTANG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTang dmtangMaso;

    public NhapNuoc() {
    }

    public NhapNuoc(Integer nhapnuocMaso) {
        this.nhapnuocMaso = nhapnuocMaso;
    }

    public Integer getNhapnuocMaso() {
        return nhapnuocMaso;
    }

    public void setNhapnuocMaso(Integer nhapnuocMaso) {
        this.nhapnuocMaso = nhapnuocMaso;
    }

    public Date getNhapnuocNgay() {
        return nhapnuocNgay;
    }

    public void setNhapnuocNgay(Date nhapnuocNgay) {
        this.nhapnuocNgay = nhapnuocNgay;
    }

    public Integer getNhapnuocSoluong() {
        return nhapnuocSoluong;
    }

    public void setNhapnuocSoluong(Integer nhapnuocSoluong) {
        this.nhapnuocSoluong = nhapnuocSoluong;
    }

    public DtDmBuong getDtdmbMaso() {
        return dtdmbMaso;
    }

    public void setDtdmbMaso(DtDmBuong dtdmbMaso) {
        this.dtdmbMaso = dtdmbMaso;
    }

    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    public DmTang getDmtangMaso() {
        return dmtangMaso;
    }

    public void setDmtangMaso(DmTang dmtangMaso) {
        this.dmtangMaso = dmtangMaso;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nhapnuocMaso != null ? nhapnuocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NhapNuoc)) {
            return false;
        }
        NhapNuoc other = (NhapNuoc) object;
        if ((this.nhapnuocMaso == null && other.nhapnuocMaso != null) || (this.nhapnuocMaso != null && !this.nhapnuocMaso.equals(other.nhapnuocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.NhapNuoc[nhapnuocMaso=" + nhapnuocMaso + "]";
    }

}
