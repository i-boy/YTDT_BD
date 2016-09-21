/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_LOAI_PHIEU")
@NamedQueries({
    @NamedQuery(name = "DmLoaiPhieu.findAll", query = "SELECT d FROM DmLoaiPhieu d")})
public class DmLoaiPhieu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_LOAI_PHIEU")
    @SequenceGenerator(name = "DM_LOAI_PHIEU", sequenceName = "DM_LOAI_PHIEU_DMLOAIPHIEU_MASO", allocationSize = 1)
    @Column(name = "DMLOAIPHIEU_MASO", nullable = false)
    private Integer dmloaiphieuMaso;
    @Column(name = "DMLOAIPHIEU_MA")
    private String dmloaiphieuMa;
    @Column(name = "DMLOAIPHIEU_TEN")
    private String dmloaiphieuTen;
    @Column(name = "DMLOAIPHIEU_DVT")
    private String dmloaiphieuDvt;
    @Column(name = "DMLOAIPHIEU_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dmloaiphieuNgaygiocn;
    @JoinColumn(name = "DMLOAITHUOC_MASO", referencedColumnName = "DMLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiThuoc dmloaithuocMaso;

    public DmLoaiPhieu() {
    }

    public DmLoaiPhieu(Integer dmloaiphieuMaso, DmLoaiThuoc dmloaithuocMaso) {
        this.dmloaiphieuMaso = dmloaiphieuMaso;
        this.dmloaithuocMaso = dmloaithuocMaso;
    }

    public DmLoaiPhieu(Integer dmloaiphieuMaso, String dmloaiphieuMa, String dmloaiphieuTen, DmLoaiThuoc dmloaithuocMaso) {
        this.dmloaiphieuMaso = dmloaiphieuMaso;
        this.dmloaiphieuMa = dmloaiphieuMa;
        this.dmloaiphieuTen = dmloaiphieuTen;
        this.dmloaithuocMaso = dmloaithuocMaso;
    }

    public Integer getDmloaiphieuMaso() {
        return dmloaiphieuMaso;
    }

    public void setDmloaiphieuMaso(Integer dmloaiphieuMaso) {
        this.dmloaiphieuMaso = dmloaiphieuMaso;
    }

    public String getDmloaiphieuMa() {
        return dmloaiphieuMa;
    }

    public void setDmloaiphieuMa(String dmloaiphieuMa) {
        this.dmloaiphieuMa = dmloaiphieuMa;
    }

    public String getDmloaiphieuTen() {
        return dmloaiphieuTen;
    }

    public void setDmloaiphieuTen(String dmloaiphieuTen) {
        this.dmloaiphieuTen = dmloaiphieuTen;
    }

    public String getDmloaiphieuDvt() {
        return dmloaiphieuDvt;
    }

    public void setDmloaiphieuDvt(String dmloaiphieuDvt) {
        this.dmloaiphieuDvt = dmloaiphieuDvt;
    }

    public Date getDmloaiphieuNgaygiocn() {
        return dmloaiphieuNgaygiocn;
    }

    public void setDmloaiphieuNgaygiocn(Date dmloaiphieuNgaygiocn) {
        this.dmloaiphieuNgaygiocn = dmloaiphieuNgaygiocn;
    }

    public DmLoaiThuoc getDmloaithuocMaso(boolean create) {
        if(create && dmloaithuocMaso == null) dmloaithuocMaso = new DmLoaiThuoc();
        return dmloaithuocMaso;
    }
    public DmLoaiThuoc getDmloaithuocMaso() {
        return dmloaithuocMaso;
    }

    public void setDmloaithuocMaso(DmLoaiThuoc dmloaithuocMaso) {
        this.dmloaithuocMaso = dmloaithuocMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmloaiphieuMaso != null ? dmloaiphieuMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmLoaiPhieu)) {
            return false;
        }
        DmLoaiPhieu other = (DmLoaiPhieu) object;
        if ((this.dmloaiphieuMaso == null && other.dmloaiphieuMaso != null) || (this.dmloaiphieuMaso != null && !this.dmloaiphieuMaso.equals(other.dmloaiphieuMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.entity.DmLoaiPhieu[dmloaiphieuMaso=" + dmloaiphieuMaso + "]";
    }

}
