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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_GIAY_CHUNG_THUONG")
@NamedQueries({})
public class HsbaGiayChungThuong implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_GIAY_CHUNG_THUONG")
    @SequenceGenerator(name = "HSBA_GIAY_CHUNG_THUONG", sequenceName = "HSBA_GIAY_CHUNG_THUONG_HSBAGCT", allocationSize = 1)
    @Column(name = "HSBAGCT_MASO", nullable = false)
    private Integer hsbagctMaso;
    @Column(name = "HSBAGCT_NOIYEUCAU")
    private String hsbagctNoiyeucau;
    @Column(name = "HSBAGCT_NGAYYC")
    @Temporal(TemporalType.DATE)
    private Date hsbagctNgayyc;
    @Column(name = "HSBAGCT_NOIDUNG")
    private String hsbagctNoidung;
    @Column(name = "HSBAGCT_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbagctNgaygiocn;
    @Column(name = "HSBAGCT_MA")
    private String hsbagctMa;
    @Column(name = "HSBAGCT_TTVV")
    private String hsbagctTtvv;
    @Column(name = "HSBAGCT_TTRV")
    private String hsbagctTtrv;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;


    public HsbaGiayChungThuong() {
    }

    public HsbaGiayChungThuong(Integer hsbagctMaso) {
        this.hsbagctMaso = hsbagctMaso;
    }

    public Integer getHsbagctMaso() {
        return hsbagctMaso;
    }

    public void setHsbagctMaso(Integer hsbagctMaso) {
        this.hsbagctMaso = hsbagctMaso;
    }

    public String getHsbagctNoiyeucau() {
        return hsbagctNoiyeucau;
    }

    public void setHsbagctNoiyeucau(String hsbagctNoiyeucau) {
        this.hsbagctNoiyeucau = hsbagctNoiyeucau;
    }

    public Date getHsbagctNgayyc() {
        return hsbagctNgayyc;
    }

    public void setHsbagctNgayyc(Date hsbagctNgayyc) {
        this.hsbagctNgayyc = hsbagctNgayyc;
    }

    public String getHsbagctNoidung() {
        return hsbagctNoidung;
    }

    public void setHsbagctNoidung(String hsbagctNoidung) {
        this.hsbagctNoidung = hsbagctNoidung;
    }

    public Date getHsbagctNgaygiocn() {
        return hsbagctNgaygiocn;
    }

    public void setHsbagctNgaygiocn(Date hsbagctNgaygiocn) {
        this.hsbagctNgaygiocn = hsbagctNgaygiocn;
    }

    public String getHsbagctMa() {
        return hsbagctMa;
    }

    public void setHsbagctMa(String hsbagctMa) {
        this.hsbagctMa = hsbagctMa;
    }
    
    public String getHsbagctTtvv() {
        return hsbagctTtvv;
    }

    public void setHsbagctTtvv(String hsbagctTtvv) {
        this.hsbagctTtvv = hsbagctTtvv;
    }

    public String getHsbagctTtrv() {
        return hsbagctTtrv;
    }

    public void setHsbagctTtrv(String hsbagctTtrv) {
        this.hsbagctTtrv = hsbagctTtrv;
    }

    public Hsba getHsbaSovaovien(boolean create) {
if(create && hsbaSovaovien == null) hsbaSovaovien = new Hsba();
return hsbaSovaovien;
}
    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DtDmNhanVien getNhanvienMa(boolean create) {
if(create && nhanvienMa == null) nhanvienMa = new DtDmNhanVien();
return nhanvienMa;
}
    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbagctMaso != null ? hsbagctMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaGiayChungThuong)) {
            return false;
        }
        HsbaGiayChungThuong other = (HsbaGiayChungThuong) object;
        if ((this.hsbagctMaso == null && other.hsbagctMaso != null) || (this.hsbagctMaso != null && !this.hsbagctMaso.equals(other.hsbagctMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsbaGiayChungThuong[hsbagctMaso=" + hsbagctMaso + "]";
    }

}


