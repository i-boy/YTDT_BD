/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
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
@Table(name = "XUAT_KHO_DINH_DUONG")
@NamedQueries({})
public class XuatKhoDinhDuong implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XUAT_KHO_DINH_DUONG")
    @SequenceGenerator(name = "XUAT_KHO_DINH_DUONG", sequenceName = "XUAT_KHO_DINH_DUONG_XKDD_MASO_", allocationSize = 1)
    @Column(name = "XKDD_MASO", nullable = false)
    private Integer xkddMaso;
    @Column(name = "XKDD_NGAYXUAT")
    @Temporal(TemporalType.DATE)
    private Date xkddNgayxuat;
    @Column(name = "XKDD_SOLUONG")
    private Float xkddSoluong;
    @Column(name = "XKDD_DONVITINH")
    private Short xkddDonvitinh;
    @Column(name = "XKDD_NGUOIXUAT")
    private String xkddNguoixuat;
    @JoinColumn(name = "DTDMNSX_MASO", referencedColumnName = "DTDMNSX_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhaSxSpdd dtdmnsxMaso;
    @JoinColumn(name = "DTDMLA_MASO", referencedColumnName = "DTDMLA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiAn dtdmlaMaso;
    @JoinColumn(name = "DTDMLA2_MASO", referencedColumnName = "DTDMLA2_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiAn2 dtdmla2Maso;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;

    public XuatKhoDinhDuong() {
    }

    public XuatKhoDinhDuong(Integer xkddMaso) {
        this.xkddMaso = xkddMaso;
    }

    public Integer getXkddMaso() {
        return xkddMaso;
    }

    public void setXkddMaso(Integer xkddMaso) {
        this.xkddMaso = xkddMaso;
    }

    public Date getXkddNgayxuat() {
        return xkddNgayxuat;
    }

    public void setXkddNgayxuat(Date xkddNgayxuat) {
        this.xkddNgayxuat = xkddNgayxuat;
    }

    public Float getXkddSoluong() {
        return xkddSoluong;
    }

    public void setXkddSoluong(Float xkddSoluong) {
        this.xkddSoluong = xkddSoluong;
    }

    public Short getXkddDonvitinh() {
        return xkddDonvitinh;
    }

    public void setXkddDonvitinh(Short xkddDonvitinh) {
        this.xkddDonvitinh = xkddDonvitinh;
    }

    public String getXkddNguoixuat() {
        return xkddNguoixuat;
    }

    public void setXkddNguoixuat(String xkddNguoixuat) {
        this.xkddNguoixuat = xkddNguoixuat;
    }

    public DtDmNhaSxSpdd getDtdmnsxMaso() {
        return dtdmnsxMaso;
    }
    
     public DtDmNhaSxSpdd getDtdmnsxMaso(boolean create) {
         if (create && dtdmnsxMaso == null){
             dtdmnsxMaso = new DtDmNhaSxSpdd();
         }
        return dtdmnsxMaso;
    }

    public void setDtdmnsxMaso(DtDmNhaSxSpdd dtdmnsxMaso) {
        this.dtdmnsxMaso = dtdmnsxMaso;
    }

    public DtDmLoaiAn getDtdmlaMaso() {
        return dtdmlaMaso;
    }
    
    public DtDmLoaiAn getDtdmlaMaso(boolean create) {
         if (create && dtdmlaMaso == null){
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
         if (create && dtdmla2Maso == null){
             dtdmla2Maso = new DtDmLoaiAn2();
         }
        return dtdmla2Maso;
    }

    public void setDtdmla2Maso(DtDmLoaiAn2 dtdmla2Maso) {
        this.dtdmla2Maso = dtdmla2Maso;
    }

    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }
    
     public DmKhoa getDmkhoaMaso(boolean create) {
         if (create && dmkhoaMaso == null){
             dmkhoaMaso = new DmKhoa();
         }
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xkddMaso != null ? xkddMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XuatKhoDinhDuong)) {
            return false;
        }
        XuatKhoDinhDuong other = (XuatKhoDinhDuong) object;
        if ((this.xkddMaso == null && other.xkddMaso != null) || (this.xkddMaso != null && !this.xkddMaso.equals(other.xkddMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.XuatKhoDinhDuong[xkddMaso=" + xkddMaso + "]";
    }

}
