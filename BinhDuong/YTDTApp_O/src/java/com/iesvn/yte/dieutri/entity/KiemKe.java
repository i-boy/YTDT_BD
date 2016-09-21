/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmKhoa;
import javax.persistence.FetchType;

/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "KIEM_KE")
@NamedQueries({})
public class KiemKe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MAPHIEUKIEM")
    private String maphieukiem;
    @Column(name = "NGAYKIEMKE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaykiemke;
    @Column(name = "NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaygiocn;
    @Column(name = "TRANGTHAI")
    private String trangthai;
    @JoinColumn(name = "DMLOAITHUOC_MASO", referencedColumnName = "DMLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiThuoc dmloaithuocMaso;
    @JoinColumn(name = "NGUOIKIEMKE", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienKiemke;
    @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;

    public KiemKe() {
    }

    public KiemKe(String maphieukiem) {
        this.maphieukiem = maphieukiem;
    }

    public KiemKe(String maphieukiem, Date ngaykiemke, Date ngaygiocn, String trangthai) {
        this.maphieukiem = maphieukiem;
        this.ngaykiemke = ngaykiemke;
        this.ngaygiocn = ngaygiocn;
        this.trangthai = trangthai;
    }

    public String getMaphieukiem() {
        return maphieukiem;
    }

    public void setMaphieukiem(String maphieukiem) {
        this.maphieukiem = maphieukiem;
    }

    public Date getNgaykiemke() {
        return ngaykiemke;
    }

    public void setNgaykiemke(Date ngaykiemke) {
        this.ngaykiemke = ngaykiemke;
    }

    public Date getNgaygiocn() {
        return ngaygiocn;
    }

    public void setNgaygiocn(Date ngaygiocn) {
        this.ngaygiocn = ngaygiocn;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
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

    public DtDmNhanVien getDtdmnhanvienKiemke(boolean create) {
        if (create && dtdmnhanvienKiemke == null) {
            dtdmnhanvienKiemke = new DtDmNhanVien();
        }
        return dtdmnhanvienKiemke;
    }

    public DtDmNhanVien getDtdmnhanvienKiemke() {
        return dtdmnhanvienKiemke;
    }

    public void setDtdmnhanvienKiemke(DtDmNhanVien dtdmnhanvienKiemke) {
        this.dtdmnhanvienKiemke = dtdmnhanvienKiemke;
    }

    public DtDmNhanVien getDtdmnhanvienCn(boolean create) {
        if (create && dtdmnhanvienCn == null) {
            dtdmnhanvienCn = new DtDmNhanVien();
        }
        return dtdmnhanvienCn;
    }

    public DtDmNhanVien getDtdmnhanvienCn() {
        return dtdmnhanvienCn;
    }

    public void setDtdmnhanvienCn(DtDmNhanVien dtdmnhanvienCn) {
        this.dtdmnhanvienCn = dtdmnhanvienCn;
    }

    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    public DmKhoa getDmkhoaMaso(boolean create) {
        if(create && dmkhoaMaso == null) dmkhoaMaso = new DmKhoa();
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maphieukiem != null ? maphieukiem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KiemKe)) {
            return false;
        }
        KiemKe other = (KiemKe) object;
        if ((this.maphieukiem == null && other.maphieukiem != null) || (this.maphieukiem != null && !this.maphieukiem.equals(other.maphieukiem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.KiemKe[maphieukiem=" + maphieukiem + "]";
    }

}
