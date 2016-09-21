/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmNhaSanXuat;
import javax.persistence.FetchType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author user01
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CT_XUAT_BH_XUAT_VIEN")
@NamedQueries({
    @NamedQuery(name = "CtXuatBhXuatVien.findAll", query = "SELECT c FROM CtXuatBhXuatVien c")})
public class CtXuatBhXuatVien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_XUAT_BH_XUAT_VIEN")
    @SequenceGenerator(name = "CT_XUAT_BH_XUAT_VIEN", sequenceName = "CT_XUAT_BH_XUAT_VIEN_CTXUATBHX", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "CTXUATBHXV_MA")
    private Integer ctxuatbhxvMa;
    @Column(name = "CTXUATBHXV_SOLUONG")
    private Double ctxuatbhxvSoluong;
    @Column(name = "CTXUATBHXV_MALK")
    private String ctxuatbhxvMalk;
    @Column(name = "CTXUATBHXV_NAMNHAP")
    private String ctxuatbhxvNamnhap;
    @Column(name = "CTXUATBHXV_NGAYHANDUNG")
    private String ctxuatbhxvNgayhandung;
    @Column(name = "CTXUATBHXV_THANGHANDUNG")
    private String ctxuatbhxvThanghandung;
    @Column(name = "CTXUATBHXV_NAMHANDUNG")
    private String ctxuatbhxvNamhandung;
    @Column(name = "CTXUATBHXV_DONGIA")
    private Double ctxuatbhxvDongia;
    @Column(name = "CTXUATBHXV_LO")
    private Double ctxuatbhxvLo;
    @Column(name = "CTXUATBHXV_SODANGKY")
    private Double ctxuatbhxvSodangky;
    @Column(name = "CTXUATBHXV_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctxuatbhxvNgaygiocn;
    @Column(name = "TONKHO_MA")
    private Integer tonkhoMa;
    @JoinColumn(name = "PHIEUXUATBHXV_MA", referencedColumnName = "PHIEUXUATBHXV_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private PhieuXuatBhXuatVien phieuxuatbhxvMa;

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

    public CtXuatBhXuatVien() {
    }

    public CtXuatBhXuatVien(Integer ctxuatbhxvMa) {
        this.ctxuatbhxvMa = ctxuatbhxvMa;
    }

    public Integer getCtxuatbhxvMa() {
        return ctxuatbhxvMa;
    }

    public void setCtxuatbhxvMa(Integer ctxuatbhxvMa) {
        this.ctxuatbhxvMa = ctxuatbhxvMa;
    }

    public Double getCtxuatbhxvSoluong() {
        return ctxuatbhxvSoluong;
    }

    public void setCtxuatbhxvSoluong(Double ctxuatbhxvSoluong) {
        this.ctxuatbhxvSoluong = ctxuatbhxvSoluong;
    }

    public String getCtxuatbhxvMalk() {
        return ctxuatbhxvMalk;
    }

    public void setCtxuatbhxvMalk(String ctxuatbhxvMalk) {
        this.ctxuatbhxvMalk = ctxuatbhxvMalk;
    }

    public String getCtxuatbhxvNamnhap() {
        return ctxuatbhxvNamnhap;
    }

    public void setCtxuatbhxvNamnhap(String ctxuatbhxvNamnhap) {
        this.ctxuatbhxvNamnhap = ctxuatbhxvNamnhap;
    }

    public String getCtxuatbhxvNgayhandung() {
        return ctxuatbhxvNgayhandung;
    }

    public void setCtxuatbhxvNgayhandung(String ctxuatbhxvNgayhandung) {
        this.ctxuatbhxvNgayhandung = ctxuatbhxvNgayhandung;
    }

    public String getCtxuatbhxvThanghandung() {
        return ctxuatbhxvThanghandung;
    }

    public void setCtxuatbhxvThanghandung(String ctxuatbhxvThanghandung) {
        this.ctxuatbhxvThanghandung = ctxuatbhxvThanghandung;
    }

    public String getCtxuatbhxvNamhandung() {
        return ctxuatbhxvNamhandung;
    }

    public void setCtxuatbhxvNamhandung(String ctxuatbhxvNamhandung) {
        this.ctxuatbhxvNamhandung = ctxuatbhxvNamhandung;
    }

    public Double getCtxuatbhxvDongia() {
        return ctxuatbhxvDongia;
    }

    public void setCtxuatbhxvDongia(Double ctxuatbhxvDongia) {
        this.ctxuatbhxvDongia = ctxuatbhxvDongia;
    }

    public Double getCtxuatbhxvLo() {
        return ctxuatbhxvLo;
    }

    public void setCtxuatbhxvLo(Double ctxuatbhxvLo) {
        this.ctxuatbhxvLo = ctxuatbhxvLo;
    }

    public Double getCtxuatbhxvSodangky() {
        return ctxuatbhxvSodangky;
    }

    public void setCtxuatbhxvSodangky(Double ctxuatbhxvSodangky) {
        this.ctxuatbhxvSodangky = ctxuatbhxvSodangky;
    }

    public Date getCtxuatbhxvNgaygiocn() {
        return ctxuatbhxvNgaygiocn;
    }

    public void setCtxuatbhxvNgaygiocn(Date ctxuatbhxvNgaygiocn) {
        this.ctxuatbhxvNgaygiocn = ctxuatbhxvNgaygiocn;
    }

    public Integer getTonkhoMa() {
        return tonkhoMa;
    }

    public void setTonkhoMa(Integer tonkhoMa) {
        this.tonkhoMa = tonkhoMa;
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

    public PhieuXuatBhXuatVien getPhieuxuatbhxvMa(boolean create) {
        if(create && phieuxuatbhxvMa == null) phieuxuatbhxvMa = new PhieuXuatBhXuatVien();
        return phieuxuatbhxvMa;
    }

    public PhieuXuatBhXuatVien getPhieuxuatbhxvMa() {
        return phieuxuatbhxvMa;
    }

    public void setPhieuxuatbhxvMa(PhieuXuatBhXuatVien phieuxuatbhxvMa) {
        this.phieuxuatbhxvMa = phieuxuatbhxvMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctxuatbhxvMa != null ? ctxuatbhxvMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtXuatBhXuatVien)) {
            return false;
        }
        CtXuatBhXuatVien other = (CtXuatBhXuatVien) object;
        if ((this.ctxuatbhxvMa == null && other.ctxuatbhxvMa != null) || (this.ctxuatbhxvMa != null && !this.ctxuatbhxvMa.equals(other.ctxuatbhxvMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.CtXuatBhXuatVien[ctxuatbhxvMa=" + ctxuatbhxvMa + "]";
    }

}
