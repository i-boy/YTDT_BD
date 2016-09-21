/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmKhoa;
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
@Table(name = "CT_TOA_LINHK")
@NamedQueries({})
public class CtToaLinhk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_TOA_LINHK_CTTOALINHK_MA")
    @SequenceGenerator(name = "CT_TOA_LINHK_CTTOALINHK_MA", sequenceName = "CT_TOA_LINHK_CTTOALINHK_MA_SEQ", allocationSize = 1)
    @Column(name = "CTTOALINHK_MA", nullable = false)
    private Integer cttoalinhkMa;
    @Column(name = "CTTOALINHK_NAMNHAP")
    private Short cttoalinhkNamnhap;
    @Column(name = "CTTOALINHK_HANDUNG")
    @Temporal(TemporalType.DATE)
    private Date cttoalinhkHandung;
    @Column(name = "CTTOALINHK_VIENTRO")
    private String cttoalinhkVientro;
    @Column(name = "CTTOALINHK_YEUCAU")
    private Boolean cttoalinhkYeucau;
    @Column(name = "CTTOALINHK_YC")
    private Boolean cttoalinhkYc;
    @Column(name = "CTTOALINHK_MIEN")
    private Boolean cttoalinhkMien;
    @Column(name = "CTTOALINHK_DONGIABAN")
    private Double cttoalinhkDongiaban;
    @Column(name = "CTTOALINHK_NDM")
    private Boolean cttoalinhkNdm;
    @Column(name = "CTTOALINHK_KHONGTHU")
    private Boolean cttoalinhkKhongthu;
    @Column(name = "CTTOALINHK_LO")
    private String cttoalinhkLo;
    @Column(name = "CTTOALINHK_SODANGKY")
    private String cttoalinhkSodangky;
    @Column(name = "CTTOALINHK_SOLUONG")
    private Integer cttoalinhkSoluong;
    @Column(name = "CTTOALINHK_CACHDUNG")
    private String cttoalinhkCachdung;
    @Column(name = "CTTOALINHK_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cttoalinhkNgaygiocn;
    @JoinColumn(name = "TOALINHKHAM_MA", referencedColumnName = "TOALINHKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ToaLinhKham toalinhkhamMa;
    @JoinColumn(name = "CTTOALINHK_MATHUOC", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc cttoalinhkMathuoc;
    @JoinColumn(name = "CTTOALINHK_QUOCGIA", referencedColumnName = "DMQUOCGIA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmQuocGia cttoalinhkQuocgia;
    @JoinColumn(name = "DMNHASANXUAT_MASO", referencedColumnName = "DMNHASANXUAT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaSanXuat dmnhasanxuatMaso;
    @JoinColumn(name = "CTTOALINHK_KHO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa cttoalinhkKho;
    @JoinColumn(name = "CTTOALINHK_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien cttoalinhkNhanviencn;


    public CtToaLinhk() {
    }

    public CtToaLinhk(Integer cttoalinhkMa) {
        this.cttoalinhkMa = cttoalinhkMa;
    }

    public Integer getCttoalinhkMa() {
        return cttoalinhkMa;
    }

    public void setCttoalinhkMa(Integer cttoalinhkMa) {
        this.cttoalinhkMa = cttoalinhkMa;
    }

    public Short getCttoalinhkNamnhap() {
        return cttoalinhkNamnhap;
    }

    public void setCttoalinhkNamnhap(Short cttoalinhkNamnhap) {
        this.cttoalinhkNamnhap = cttoalinhkNamnhap;
    }

    public Date getCttoalinhkHandung() {
        return cttoalinhkHandung;
    }

    public void setCttoalinhkHandung(Date cttoalinhkHandung) {
        this.cttoalinhkHandung = cttoalinhkHandung;
    }

    public String getCttoalinhkVientro() {
        return cttoalinhkVientro;
    }

    public void setCttoalinhkVientro(String cttoalinhkVientro) {
        this.cttoalinhkVientro = cttoalinhkVientro;
    }

    public Boolean getCttoalinhkYeucau() {
        return cttoalinhkYeucau;
    }

    public void setCttoalinhkYeucau(Boolean cttoalinhkYeucau) {
        this.cttoalinhkYeucau = cttoalinhkYeucau;
    }

    public Boolean getCttoalinhkYc() {
        return cttoalinhkYc;
    }

    public void setCttoalinhkYc(Boolean cttoalinhkYc) {
        this.cttoalinhkYc = cttoalinhkYc;
    }

    public Boolean getCttoalinhkMien() {
        return cttoalinhkMien;
    }

    public void setCttoalinhkMien(Boolean cttoalinhkMien) {
        this.cttoalinhkMien = cttoalinhkMien;
    }

    public Double getCttoalinhkDongiaban() {
        return cttoalinhkDongiaban;
    }

    public void setCttoalinhkDongiaban(Double cttoalinhkDongiaban) {
        this.cttoalinhkDongiaban = cttoalinhkDongiaban;
    }

    public Boolean getCttoalinhkNdm() {
        return cttoalinhkNdm;
    }

    public void setCttoalinhkNdm(Boolean cttoalinhkNdm) {
        this.cttoalinhkNdm = cttoalinhkNdm;
    }

    public Boolean getCttoalinhkKhongthu() {
        return cttoalinhkKhongthu;
    }

    public void setCttoalinhkKhongthu(Boolean cttoalinhkKhongthu) {
        this.cttoalinhkKhongthu = cttoalinhkKhongthu;
    }

    public String getCttoalinhkLo() {
        return cttoalinhkLo;
    }

    public void setCttoalinhkLo(String cttoalinhkLo) {
        this.cttoalinhkLo = cttoalinhkLo;
    }

    public String getCttoalinhkSodangky() {
        return cttoalinhkSodangky;
    }

    public void setCttoalinhkSodangky(String cttoalinhkSodangky) {
        this.cttoalinhkSodangky = cttoalinhkSodangky;
    }

    public Integer getCttoalinhkSoluong() {
        return cttoalinhkSoluong;
    }

    public void setCttoalinhkSoluong(Integer cttoalinhkSoluong) {
        this.cttoalinhkSoluong = cttoalinhkSoluong;
    }

    public String getCttoalinhkCachdung() {
        return cttoalinhkCachdung;
    }

    public void setCttoalinhkCachdung(String cttoalinhkCachdung) {
        this.cttoalinhkCachdung = cttoalinhkCachdung;
    }

    public Date getCttoalinhkNgaygiocn() {
        return cttoalinhkNgaygiocn;
    }

    public void setCttoalinhkNgaygiocn(Date cttoalinhkNgaygiocn) {
        this.cttoalinhkNgaygiocn = cttoalinhkNgaygiocn;
    }

    public ToaLinhKham getToalinhkhamMa(boolean create) {
if(create && toalinhkhamMa == null) toalinhkhamMa = new ToaLinhKham();
return toalinhkhamMa;
}
    public ToaLinhKham getToalinhkhamMa() {
        return toalinhkhamMa;
    }

    public void setToalinhkhamMa(ToaLinhKham toalinhkhamMa) {
        this.toalinhkhamMa = toalinhkhamMa;
    }

    public DmThuoc getCttoalinhkMathuoc(boolean create) {
if(create && cttoalinhkMathuoc == null) cttoalinhkMathuoc = new DmThuoc();
return cttoalinhkMathuoc;
}
    public DmThuoc getCttoalinhkMathuoc() {
        return cttoalinhkMathuoc;
    }

    public void setCttoalinhkMathuoc(DmThuoc cttoalinhkMathuoc) {
        this.cttoalinhkMathuoc = cttoalinhkMathuoc;
    }

    public DmQuocGia getCttoalinhkQuocgia(boolean create) {
if(create && cttoalinhkQuocgia == null) cttoalinhkQuocgia = new DmQuocGia();
return cttoalinhkQuocgia;
}
    public DmQuocGia getCttoalinhkQuocgia() {
        return cttoalinhkQuocgia;
    }

    public void setCttoalinhkQuocgia(DmQuocGia cttoalinhkQuocgia) {
        this.cttoalinhkQuocgia = cttoalinhkQuocgia;
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

    public DmKhoa getCttoalinhkKho(boolean create) {
if(create && cttoalinhkKho == null) cttoalinhkKho = new DmKhoa();
return cttoalinhkKho;
}
    public DmKhoa getCttoalinhkKho() {
        return cttoalinhkKho;
    }

    public void setCttoalinhkKho(DmKhoa cttoalinhkKho) {
        this.cttoalinhkKho = cttoalinhkKho;
    }

    public DtDmNhanVien getCttoalinhkNhanviencn(boolean create) {
if(create && cttoalinhkNhanviencn == null) cttoalinhkNhanviencn = new DtDmNhanVien();
return cttoalinhkNhanviencn;
}
    public DtDmNhanVien getCttoalinhkNhanviencn() {
        return cttoalinhkNhanviencn;
    }

    public void setCttoalinhkNhanviencn(DtDmNhanVien cttoalinhkNhanviencn) {
        this.cttoalinhkNhanviencn = cttoalinhkNhanviencn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cttoalinhkMa != null ? cttoalinhkMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtToaLinhk)) {
            return false;
        }
        CtToaLinhk other = (CtToaLinhk) object;
        if ((this.cttoalinhkMa == null && other.cttoalinhkMa != null) || (this.cttoalinhkMa != null && !this.cttoalinhkMa.equals(other.cttoalinhkMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.CtToaLinhk[cttoalinhkMa=" + cttoalinhkMa + "]";
    }
}


