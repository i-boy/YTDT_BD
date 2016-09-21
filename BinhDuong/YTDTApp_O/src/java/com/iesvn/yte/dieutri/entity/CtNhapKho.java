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
@Table(name = "CT_NHAP_KHO")
@NamedQueries({})
public class CtNhapKho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_NHAP_KHO_CTNHAPKHO_MA")
    @SequenceGenerator(name = "CT_NHAP_KHO_CTNHAPKHO_MA", sequenceName = "CT_NHAP_KHO_CTNHAPKHO_MA_SEQ", allocationSize = 1)
    @Column(name = "CTNHAPKHO_MA", nullable = false)
    private Integer ctnhapkhoMa;
    @Column(name = "CTNHAPKHO_MALK")
    private String ctnhapkhoMalk;
    @Column(name = "CTNHAPKHO_NAMNHAP")
    private String ctnhapkhoNamnhap;
    @Column(name = "CTNHAPKHO_NGAYHANDUNG")
    private String ctnhapkhoNgayhandung;
    @Column(name = "CTNHAPKHO_THANGHANDUNG")
    private String ctnhapkhoThanghandung;
    @Column(name = "CTNHAPKHO_NAMHANDUNG")
    private String ctnhapkhoNamhandung;
    @Column(name = "CTNHAPKHO_DONGIA")
    private double ctnhapkhoDongia;
    @Column(name = "CTNHAPKHO_DONGIABAN")
    private Double ctnhapkhoDongiaban;
    @Column(name = "CTNHAPKHO_LO")
    private String ctnhapkhoLo;
    @Column(name = "CTNHAPKHO_SODANGKY")
    private String ctnhapkhoSodangky;
    @Column(name = "CTNHAPKHO_THUTU")
    private Integer ctnhapkhoThutu;
    @Column(name = "CTNHAPKHO_SOLUONG")
    private Double ctnhapkhoSoluong;
    @Column(name = "CTNHAPKHO_QUYCACH")
    private Integer ctnhapkhoQuycach;
    @Column(name = "CTNHAPKHO_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctnhapkhoNgaygiocn;
    @JoinColumn(name = "DMNHASANXUAT_MASO", referencedColumnName = "DMNHASANXUAT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaSanXuat dmnhasanxuatMaso;
    @JoinColumn(name = "DMQUOCGIA_MASO", referencedColumnName = "DMQUOCGIA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmQuocGia dmquocgiaMaso;
    @JoinColumn(name = "DMTHUOC_MASO", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc dmthuocMaso;
    @JoinColumn(name = "PHIEUNHAPKHO_MA", referencedColumnName = "PHIEUNHAPKHO_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuNhapKho phieunhapkhoMa;

    @Column(name = "TONKHO_MA")
    private Integer tonKhoMa;
    
    public CtNhapKho() {
        if(ctnhapkhoSoluong==null)
            ctnhapkhoSoluong = Double.parseDouble("0");
    }

    public CtNhapKho(Integer ctnhapkhoMa) {
        this.ctnhapkhoMa = ctnhapkhoMa;
    }

    public CtNhapKho(Integer ctnhapkhoMa, String ctnhapkhoNamnhap, String ctnhapkhoThanghandung, String ctnhapkhoNamhandung, double ctnhapkhoDongia, Double ctnhapkhoSoluong) {
        this.ctnhapkhoMa = ctnhapkhoMa;
        this.ctnhapkhoNamnhap = ctnhapkhoNamnhap;
        this.ctnhapkhoThanghandung = ctnhapkhoThanghandung;
        this.ctnhapkhoNamhandung = ctnhapkhoNamhandung;
        this.ctnhapkhoDongia = ctnhapkhoDongia;
        this.ctnhapkhoSoluong = ctnhapkhoSoluong;
    }

    public Integer getCtnhapkhoMa() {
        return ctnhapkhoMa;
    }

    public void setCtnhapkhoMa(Integer ctnhapkhoMa) {
        this.ctnhapkhoMa = ctnhapkhoMa;
    }

    public String getCtnhapkhoMalk() {
        return ctnhapkhoMalk;
    }

    public void setCtnhapkhoMalk(String ctnhapkhoMalk) {
        this.ctnhapkhoMalk = ctnhapkhoMalk;
    }

    public String getCtnhapkhoNamnhap() {
        return ctnhapkhoNamnhap;
    }

    public void setCtnhapkhoNamnhap(String ctnhapkhoNamnhap) {
        this.ctnhapkhoNamnhap = ctnhapkhoNamnhap;
    }

    public String getCtnhapkhoNgayhandung() {
        return ctnhapkhoNgayhandung;
    }

    public void setCtnhapkhoNgayhandung(String ctnhapkhoNgayhandung) {
        this.ctnhapkhoNgayhandung = ctnhapkhoNgayhandung;
    }

    public String getCtnhapkhoThanghandung() {
        return ctnhapkhoThanghandung;
    }

    public void setCtnhapkhoThanghandung(String ctnhapkhoThanghandung) {
        this.ctnhapkhoThanghandung = ctnhapkhoThanghandung;
    }

    public String getCtnhapkhoNamhandung() {
        return ctnhapkhoNamhandung;
    }

    public void setCtnhapkhoNamhandung(String ctnhapkhoNamhandung) {
        this.ctnhapkhoNamhandung = ctnhapkhoNamhandung;
    }

    public double getCtnhapkhoDongia() {
        return ctnhapkhoDongia;
    }

    public void setCtnhapkhoDongia(double ctnhapkhoDongia) {
        this.ctnhapkhoDongia = ctnhapkhoDongia;
    }

    public Double getCtnhapkhoDongiaban() {
        return ctnhapkhoDongiaban;
    }

    public void setCtnhapkhoDongiaban(Double ctnhapkhoDongiaban) {
        this.ctnhapkhoDongiaban = ctnhapkhoDongiaban;
    }

    public String getCtnhapkhoLo() {
        return ctnhapkhoLo;
    }

    public void setCtnhapkhoLo(String ctnhapkhoLo) {
        this.ctnhapkhoLo = ctnhapkhoLo;
    }

    public String getCtnhapkhoSodangky() {
        return ctnhapkhoSodangky;
    }

    public void setCtnhapkhoSodangky(String ctnhapkhoSodangky) {
        this.ctnhapkhoSodangky = ctnhapkhoSodangky;
    }

    public Integer getCtnhapkhoThutu() {
        return ctnhapkhoThutu;
    }

    public void setCtnhapkhoThutu(Integer ctnhapkhoThutu) {
        this.ctnhapkhoThutu = ctnhapkhoThutu;
    }

    public Double getCtnhapkhoSoluong() {
        return ctnhapkhoSoluong;
    }

    public void setCtnhapkhoSoluong(Double ctnhapkhoSoluong) {
        this.ctnhapkhoSoluong = ctnhapkhoSoluong;
    }

    public Integer getCtnhapkhoQuycach() {
        return ctnhapkhoQuycach;
    }

    public void setCtnhapkhoQuycach(Integer ctnhapkhoQuycach) {
        this.ctnhapkhoQuycach = ctnhapkhoQuycach;
    }

    public Date getCtnhapkhoNgaygiocn() {
        return ctnhapkhoNgaygiocn;
    }

    public void setCtnhapkhoNgaygiocn(Date ctnhapkhoNgaygiocn) {
        this.ctnhapkhoNgaygiocn = ctnhapkhoNgaygiocn;
    }

    public DmNhaSanXuat getDmnhasanxuatMaso(boolean create) {
        if (create && dmnhasanxuatMaso == null) {
            dmnhasanxuatMaso = new DmNhaSanXuat();
        }
        return dmnhasanxuatMaso;
    }

    public DmNhaSanXuat getDmnhasanxuatMaso() {
        return dmnhasanxuatMaso;
    }

    public void setDmnhasanxuatMaso(DmNhaSanXuat dmnhasanxuatMaso) {
        this.dmnhasanxuatMaso = dmnhasanxuatMaso;
    }

    public DmQuocGia getDmquocgiaMaso(boolean create) {
        if (create && dmquocgiaMaso == null) {
            dmquocgiaMaso = new DmQuocGia();
        }
        return dmquocgiaMaso;
    }

    public DmQuocGia getDmquocgiaMaso() {
        return dmquocgiaMaso;
    }

    public void setDmquocgiaMaso(DmQuocGia dmquocgiaMaso) {
        this.dmquocgiaMaso = dmquocgiaMaso;
    }

    public DmThuoc getDmthuocMaso(boolean create) {
        if (create && dmthuocMaso == null) {
            dmthuocMaso = new DmThuoc();
        }
        return dmthuocMaso;
    }

    public DmThuoc getDmthuocMaso() {
        return dmthuocMaso;
    }

    public void setDmthuocMaso(DmThuoc dmthuocMaso) {
        this.dmthuocMaso = dmthuocMaso;
    }

    public PhieuNhapKho getPhieunhapkhoMa(boolean create) {
        if (create && phieunhapkhoMa == null) {
            phieunhapkhoMa = new PhieuNhapKho();
        }
        return phieunhapkhoMa;
    }

    public PhieuNhapKho getPhieunhapkhoMa() {
        return phieunhapkhoMa;
    }

    public void setPhieunhapkhoMa(PhieuNhapKho phieunhapkhoMa) {
        this.phieunhapkhoMa = phieunhapkhoMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctnhapkhoMa != null ? ctnhapkhoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtNhapKho)) {
            return false;
        }
        CtNhapKho other = (CtNhapKho) object;
        if ((this.ctnhapkhoMa == null && other.ctnhapkhoMa != null) || (this.ctnhapkhoMa != null && !this.ctnhapkhoMa.equals(other.ctnhapkhoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.CtNhapKho[ctnhapkhoMa=" + ctnhapkhoMa + "]";
    }

    public Integer getTonKhoMa() {
        return tonKhoMa;
    }

    public void setTonKhoMa(Integer tonKhoMa) {
        this.tonKhoMa = tonKhoMa;
    }
}


