/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmNhaSanXuat;
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
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CT_TOA_THUOCK")
@NamedQueries({})
public class CtToaThuock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_TOA_THUOCK")
    @SequenceGenerator(name = "CT_TOA_THUOCK", sequenceName = "CT_TOA_THUOCK_CTTOATHUOCK_MA_S", allocationSize = 1)
    @Column(name = "CTTOATHUOCK_MA", nullable = false)
    private Integer cttoathuockMa;
    @Column(name = "CTTOATHUOCK_NAMNHAP")
    private Short cttoathuockNamnhap;
    @Column(name = "CTTOATHUOCK_HANDUNG")
    @Temporal(TemporalType.DATE)
    private Date cttoathuockHandung;
    @Column(name = "CTTOATHUOCK_VIENTRO")
    private String cttoathuockVientro;
    @Column(name = "CTTOATHUOCK_SOLUONG")
    private Integer cttoathuockSoluong;
    @Column(name = "CTTOATHUOCK_CACHDUNG")
    private String cttoathuockCachdung;
    @Column(name = "CTTOATHUOCK_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cttoathuockNgaygiocn;
    @JoinColumn(name = "TOATHUOCKHAM_MA", referencedColumnName = "TOATHUOCKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ToaThuocKham toathuockhamMa;
    @JoinColumn(name = "CTTOATHUOCK_MATHUOC", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc cttoathuockMathuoc;
    @JoinColumn(name = "CTTOATHUOCK_QUOCGIA", referencedColumnName = "DMQUOCGIA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmQuocGia cttoathuockQuocgia;
    @JoinColumn(name = "DMNHASANXUAT_MASO", referencedColumnName = "DMNHASANXUAT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaSanXuat dmnhasanxuatMaso;
    @JoinColumn(name = "CTTOATHUOCK_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien cttoathuockNhanviencn;

    public CtToaThuock() {
    }

    public CtToaThuock(Integer cttoathuockMa) {
        this.cttoathuockMa = cttoathuockMa;
    }

    public Integer getCttoathuockMa() {
        return cttoathuockMa;
    }

    public void setCttoathuockMa(Integer cttoathuockMa) {
        this.cttoathuockMa = cttoathuockMa;
    }

    public Short getCttoathuockNamnhap() {
        return cttoathuockNamnhap;
    }

    public void setCttoathuockNamnhap(Short cttoathuockNamnhap) {
        this.cttoathuockNamnhap = cttoathuockNamnhap;
    }

    public Date getCttoathuockHandung() {
        return cttoathuockHandung;
    }

    public void setCttoathuockHandung(Date cttoathuockHandung) {
        this.cttoathuockHandung = cttoathuockHandung;
    }

    public String getCttoathuockVientro() {
        return cttoathuockVientro;
    }

    public void setCttoathuockVientro(String cttoathuockVientro) {
        this.cttoathuockVientro = cttoathuockVientro;
    }

    public Integer getCttoathuockSoluong() {
        return cttoathuockSoluong;
    }

    public void setCttoathuockSoluong(Integer cttoathuockSoluong) {
        this.cttoathuockSoluong = cttoathuockSoluong;
    }

    public String getCttoathuockCachdung() {
        return cttoathuockCachdung;
    }

    public void setCttoathuockCachdung(String cttoathuockCachdung) {
        this.cttoathuockCachdung = cttoathuockCachdung;
    }

    public Date getCttoathuockNgaygiocn() {
        return cttoathuockNgaygiocn;
    }

    public void setCttoathuockNgaygiocn(Date cttoathuockNgaygiocn) {
        this.cttoathuockNgaygiocn = cttoathuockNgaygiocn;
    }

    public ToaThuocKham getToathuockhamMa(boolean create) {
if(create && toathuockhamMa == null) toathuockhamMa = new ToaThuocKham();
return toathuockhamMa;
}
    public ToaThuocKham getToathuockhamMa() {
        return toathuockhamMa;
    }

    public void setToathuockhamMa(ToaThuocKham toathuockhamMa) {
        this.toathuockhamMa = toathuockhamMa;
    }

    public DmThuoc getCttoathuockMathuoc(boolean create) {
if(create && cttoathuockMathuoc == null) cttoathuockMathuoc = new DmThuoc();
return cttoathuockMathuoc;
}
    public DmThuoc getCttoathuockMathuoc() {
        return cttoathuockMathuoc;
    }

    public void setCttoathuockMathuoc(DmThuoc cttoathuockMathuoc) {
        this.cttoathuockMathuoc = cttoathuockMathuoc;
    }

    public DmQuocGia getCttoathuockQuocgia(boolean create) {
if(create && cttoathuockQuocgia == null) cttoathuockQuocgia = new DmQuocGia();
return cttoathuockQuocgia;
}
    public DmQuocGia getCttoathuockQuocgia() {
        return cttoathuockQuocgia;
    }

    public void setCttoathuockQuocgia(DmQuocGia cttoathuockQuocgia) {
        this.cttoathuockQuocgia = cttoathuockQuocgia;
    }

    public DmNhaSanXuat getDmnhasanxuatMaso(boolean create) {
if(create && dmnhasanxuatMaso == null) dmnhasanxuatMaso = new DmNhaSanXuat();
return dmnhasanxuatMaso;
}
    public DmNhaSanXuat getDmnhasanxuatMaso() {
        return dmnhasanxuatMaso;
    }

    public void setDmnhasanxuatMaso(DmNhaSanXuat dmnhasanxuatMaso) {
        this.dmnhasanxuatMaso = dmnhasanxuatMaso;
    }

    public DtDmNhanVien getCttoathuockNhanviencn(boolean create) {
if(create && cttoathuockNhanviencn == null) cttoathuockNhanviencn = new DtDmNhanVien();
return cttoathuockNhanviencn;
}
    public DtDmNhanVien getCttoathuockNhanviencn() {
        return cttoathuockNhanviencn;
    }

    public void setCttoathuockNhanviencn(DtDmNhanVien cttoathuockNhanviencn) {
        this.cttoathuockNhanviencn = cttoathuockNhanviencn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cttoathuockMa != null ? cttoathuockMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtToaThuock)) {
            return false;
        }
        CtToaThuock other = (CtToaThuock) object;
        if ((this.cttoathuockMa == null && other.cttoathuockMa != null) || (this.cttoathuockMa != null && !this.cttoathuockMa.equals(other.cttoathuockMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.CtToaThuock[cttoathuockMa=" + cttoathuockMa + "]";
    }
}


