/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
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
@Table(name = "CT_XUAT_KHO")
@NamedQueries({})
public class CtXuatKho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_XUAT_KHO_CTXUATKHO_MA")
    @SequenceGenerator(name = "CT_XUAT_KHO_CTXUATKHO_MA", sequenceName = "CT_XUAT_KHO_CTXUATKHO_MA_SEQ", allocationSize = 1)
    @Column(name = "CTXUATKHO_MA", nullable = false)
    private Integer ctxuatkhoMa;
    @Column(name = "CTXUATKHO_NAMNHAP")
    private String ctxuatkhoNamnhap;
    @Column(name = "CTXUATKHO_NGAYHANDUNG")
    private String ctxuatkhoNgayhandung;
    @Column(name = "CTXUATKHO_THANGHANDUNG")
    private String ctxuatkhoThanghandung;
    @Column(name = "CTXUATKHO_NAMHANDUNG")
    private String ctxuatkhoNamhandung;
    @Column(name = "CTXUATKHO_DONGIA")
    private Double ctxuatkhoDongia;
    @Column(name = "CTXUATKHO_DONGIABAN")
    private Double ctxuatkhoDongiaban;
    @Column(name = "CTXUATKHO_LO")
    private String ctxuatkhoLo;
    @Column(name = "CTXUATKHO_MALK")
    private String ctxuatkhoMalk;
    @Column(name = "CTXUATKHO_SODANGKY")
    private String ctxuatkhoSodangky;
    @Column(name = "CTXUATKHO_THUTU")
    private Short ctxuatkhoThutu;
    @Column(name = "CTXUATKHO_SOLUONG")
    private Double ctxuatkhoSoluong;
    @Column(name = "CTXUATKHO_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctxuatkhoNgaygiocn;
    @JoinColumn(name = "DMNCT_MASO", referencedColumnName = "DMNCT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonChuongTrinh dmnctMaso;
    @JoinColumn(name = "DMNGUONKINHPHI_MASO", referencedColumnName = "DMNGUONKINHPHI_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonKinhPhi dmnguonkinhphiMaso;
    @JoinColumn(name = "DMNHASANXUAT_MASO", referencedColumnName = "DMNHASANXUAT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaSanXuat dmnhasanxuatMaso;
    @JoinColumn(name = "DMQUOCGIA_MASO", referencedColumnName = "DMQUOCGIA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmQuocGia dmquocgiaMaso;
    @JoinColumn(name = "DMTHUOC_MASO", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc dmthuocMaso;
    @JoinColumn(name = "PHIEUXUATKHO_MA", referencedColumnName = "PHIEUXUATKHO_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuXuatKho phieuxuatkhoMa;

    @Column(name = "TONKHO_MA")
    private Integer tonKhoMa;
    
    public CtXuatKho() {
    }

    public CtXuatKho(Integer ctxuatkhoMa) {
        this.ctxuatkhoMa = ctxuatkhoMa;
    }

    public Integer getCtxuatkhoMa() {
        return ctxuatkhoMa;
    }

    public void setCtxuatkhoMa(Integer ctxuatkhoMa) {
        this.ctxuatkhoMa = ctxuatkhoMa;
    }

    public String getCtxuatkhoNamnhap() {
        return ctxuatkhoNamnhap;
    }

    public void setCtxuatkhoNamnhap(String ctxuatkhoNamnhap) {
        this.ctxuatkhoNamnhap = ctxuatkhoNamnhap;
    }

    public String getCtxuatkhoNgayhandung() {
        return ctxuatkhoNgayhandung;
    }

    public void setCtxuatkhoNgayhandung(String ctxuatkhoNgayhandung) {
        this.ctxuatkhoNgayhandung = ctxuatkhoNgayhandung;
    }

    public String getCtxuatkhoThanghandung() {
        return ctxuatkhoThanghandung;
    }

    public void setCtxuatkhoThanghandung(String ctxuatkhoThanghandung) {
        this.ctxuatkhoThanghandung = ctxuatkhoThanghandung;
    }

    public String getCtxuatkhoNamhandung() {
        return ctxuatkhoNamhandung;
    }

    public void setCtxuatkhoNamhandung(String ctxuatkhoNamhandung) {
        this.ctxuatkhoNamhandung = ctxuatkhoNamhandung;
    }

    public Double getCtxuatkhoDongia() {
        return ctxuatkhoDongia;
    }

    public void setCtxuatkhoDongia(Double ctxuatkhoDongia) {
        this.ctxuatkhoDongia = ctxuatkhoDongia;
    }

    public Double getCtxuatkhoDongiaban() {
        return ctxuatkhoDongiaban;
    }

    public void setCtxuatkhoDongiaban(Double ctxuatkhoDongiaban) {
        this.ctxuatkhoDongiaban = ctxuatkhoDongiaban;
    }

    public String getCtxuatkhoLo() {
        return ctxuatkhoLo;
    }

    public void setCtxuatkhoLo(String ctxuatkhoLo) {
        this.ctxuatkhoLo = ctxuatkhoLo;
    }

    public String getCtxuatkhoMalk() {
        return ctxuatkhoMalk;
    }

    public void setCtxuatkhoMalk(String ctxuatkhoMalk) {
        this.ctxuatkhoMalk = ctxuatkhoMalk;
    }

    public String getCtxuatkhoSodangky() {
        return ctxuatkhoSodangky;
    }

    public void setCtxuatkhoSodangky(String ctxuatkhoSodangky) {
        this.ctxuatkhoSodangky = ctxuatkhoSodangky;
    }

    public Short getCtxuatkhoThutu() {
        return ctxuatkhoThutu;
    }

    public void setCtxuatkhoThutu(Short ctxuatkhoThutu) {
        this.ctxuatkhoThutu = ctxuatkhoThutu;
    }

    public Double getCtxuatkhoSoluong() {
        return ctxuatkhoSoluong;
    }

    public void setCtxuatkhoSoluong(Double ctxuatkhoSoluong) {
        this.ctxuatkhoSoluong = ctxuatkhoSoluong;
    }

    public Date getCtxuatkhoNgaygiocn() {
        return ctxuatkhoNgaygiocn;
    }

    public void setCtxuatkhoNgaygiocn(Date ctxuatkhoNgaygiocn) {
        this.ctxuatkhoNgaygiocn = ctxuatkhoNgaygiocn;
    }

    public DmNguonChuongTrinh getDmnctMaso(boolean create) {
if(create && dmnctMaso == null) dmnctMaso = new DmNguonChuongTrinh();
return dmnctMaso;
}
    public DmNguonChuongTrinh getDmnctMaso() {
        return dmnctMaso;
    }

    public void setDmnctMaso(DmNguonChuongTrinh dmnctMaso) {
        this.dmnctMaso = dmnctMaso;
    }

    public DmNguonKinhPhi getDmnguonkinhphiMaso(boolean create) {
if(create && dmnguonkinhphiMaso == null) dmnguonkinhphiMaso = new DmNguonKinhPhi();
return dmnguonkinhphiMaso;
}
    public DmNguonKinhPhi getDmnguonkinhphiMaso() {
        return dmnguonkinhphiMaso;
    }

    public void setDmnguonkinhphiMaso(DmNguonKinhPhi dmnguonkinhphiMaso) {
        this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
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

    public DmQuocGia getDmquocgiaMaso(boolean create) {
if(create && dmquocgiaMaso == null) dmquocgiaMaso = new DmQuocGia();
return dmquocgiaMaso;
}
    public DmQuocGia getDmquocgiaMaso() {
        return dmquocgiaMaso;
    }

    public void setDmquocgiaMaso(DmQuocGia dmquocgiaMaso) {
        this.dmquocgiaMaso = dmquocgiaMaso;
    }

    public DmThuoc getDmthuocMaso(boolean create) {
if(create && dmthuocMaso == null) dmthuocMaso = new DmThuoc();
return dmthuocMaso;
}
    public DmThuoc getDmthuocMaso() {
        return dmthuocMaso;
    }

    public void setDmthuocMaso(DmThuoc dmthuocMaso) {
        this.dmthuocMaso = dmthuocMaso;
    }

    public PhieuXuatKho getPhieuxuatkhoMa(boolean create) {
if(create && phieuxuatkhoMa == null) phieuxuatkhoMa = new PhieuXuatKho();
return phieuxuatkhoMa;
}
    public PhieuXuatKho getPhieuxuatkhoMa() {
        return phieuxuatkhoMa;
    }

    public void setPhieuxuatkhoMa(PhieuXuatKho phieuxuatkhoMa) {
        this.phieuxuatkhoMa = phieuxuatkhoMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctxuatkhoMa != null ? ctxuatkhoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtXuatKho)) {
            return false;
        }
        CtXuatKho other = (CtXuatKho) object;
        if ((this.ctxuatkhoMa == null && other.ctxuatkhoMa != null) || (this.ctxuatkhoMa != null && !this.ctxuatkhoMa.equals(other.ctxuatkhoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.CtXuatKho[ctxuatkhoMa=" + ctxuatkhoMa + "]";
    }

    public Integer getTonKhoMa() {
        return tonKhoMa;
    }

    public void setTonKhoMa(Integer tonKhoMa) {
        this.tonKhoMa = tonKhoMa;
    }
}


