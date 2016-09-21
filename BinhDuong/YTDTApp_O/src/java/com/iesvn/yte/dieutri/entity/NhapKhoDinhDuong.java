/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

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
@Table(name = "NHAP_KHO_DINH_DUONG")
@NamedQueries({})
public class NhapKhoDinhDuong implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NHAP_KHO_DINH_DUONG")
    @SequenceGenerator(name = "NHAP_KHO_DINH_DUONG", sequenceName = "NHAP_KHO_DINH_DUONG_NKDD_MASO_", allocationSize = 1)
    @Column(name = "NKDD_MASO", nullable = false)
    private Integer nkddMaso;
    @Column(name = "NKDD_NGAYNHAP")
    @Temporal(TemporalType.DATE)
    private Date nkddNgaynhap;
    @Column(name = "NKDD_SOLUONG")
    private Float nkddSoluong;
    @Column(name = "NKDD_DONVITINH")
    private Short nkddDonvitinh;
    @Column(name = "NKDD_NGUOINHAP")
    private String nkddNguoinhap;
    @JoinColumn(name = "DTDMLA_MASO", referencedColumnName = "DTDMLA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiAn dtdmlaMaso;
    @JoinColumn(name = "DTDMLA2_MASO", referencedColumnName = "DTDMLA2_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiAn2 dtdmla2Maso;
    @JoinColumn(name = "DTDMNSX_MASO", referencedColumnName = "DTDMNSX_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhaSxSpdd dtdmnsxMaso;

    public NhapKhoDinhDuong() {
    }

    public NhapKhoDinhDuong(Integer nkddMaso) {
        this.nkddMaso = nkddMaso;
    }

    public Integer getNkddMaso() {
        return nkddMaso;
    }

    public void setNkddMaso(Integer nkddMaso) {
        this.nkddMaso = nkddMaso;
    }

    public Date getNkddNgaynhap() {
        return nkddNgaynhap;
    }

    public void setNkddNgaynhap(Date nkddNgaynhap) {
        this.nkddNgaynhap = nkddNgaynhap;
    }

    public Float getNkddSoluong() {
        return nkddSoluong;
    }

    public void setNkddSoluong(Float nkddSoluong) {
        this.nkddSoluong = nkddSoluong;
    }

    public Short getNkddDonvitinh() {
        return nkddDonvitinh;
    }

    public void setNkddDonvitinh(Short nkddDonvitinh) {
        this.nkddDonvitinh = nkddDonvitinh;
    }

    public String getNkddNguoinhap() {
        return nkddNguoinhap;
    }

    public void setNkddNguoinhap(String nkddNguoinhap) {
        this.nkddNguoinhap = nkddNguoinhap;
    }

    public DtDmLoaiAn getDtdmlaMaso() {
        return dtdmlaMaso;
    }
    
    public DtDmLoaiAn getDtdmlaMaso(boolean create) {
        if(create && dtdmlaMaso == null){
            dtdmlaMaso = new DtDmLoaiAn();
        }
        return dtdmlaMaso;
    }

    public void setDtdmlaMaso(DtDmLoaiAn dtdmlaMaso) {
        this.dtdmlaMaso = dtdmlaMaso;
    }

    public DtDmLoaiAn2 getDtdmla2Maso() {
        return dtdmla2Maso;
    }
    
     public DtDmLoaiAn2 getDtdmla2Maso(boolean create) {
         if(create && dtdmla2Maso == null){
             dtdmla2Maso = new DtDmLoaiAn2();
         }
        return dtdmla2Maso;
    }

    public void setDtdmla2Maso(DtDmLoaiAn2 dtdmla2Maso) {
        this.dtdmla2Maso = dtdmla2Maso;
    }

    public DtDmNhaSxSpdd getDtdmnsxMaso() {
        return dtdmnsxMaso;
    }
    
     public DtDmNhaSxSpdd getDtdmnsxMaso(boolean create) {
         if (create && dtdmnsxMaso == null){
             dtdmnsxMaso= new DtDmNhaSxSpdd();
         }
        return dtdmnsxMaso;
    }

    public void setDtdmnsxMaso(DtDmNhaSxSpdd dtdmnsxMaso) {
        this.dtdmnsxMaso = dtdmnsxMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nkddMaso != null ? nkddMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NhapKhoDinhDuong)) {
            return false;
        }
        NhapKhoDinhDuong other = (NhapKhoDinhDuong) object;
        if ((this.nkddMaso == null && other.nkddMaso != null) || (this.nkddMaso != null && !this.nkddMaso.equals(other.nkddMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.NhapKhoDinhDuong[nkddMaso=" + nkddMaso + "]";
    }

}
