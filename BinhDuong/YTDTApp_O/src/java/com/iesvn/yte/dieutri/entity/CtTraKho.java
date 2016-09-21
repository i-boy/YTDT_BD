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
@Table(name = "CT_TRA_KHO")
@NamedQueries({})
public class CtTraKho implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_TRA_KHO_CTTRAKHO_MA")
    @SequenceGenerator(name = "CT_TRA_KHO_CTTRAKHO_MA", sequenceName = "CT_TRA_KHO_CTTRAKHO_MA_SEQ", allocationSize = 1)
    @Column(name = "CTTRAKHO_MA", nullable = false)
    private Integer cttrakhoMa;
    @Column(name = "CTTRAKHO_THUTU")
    private Short cttrakhoThutu;
    @Column(name = "CTTRAKHO_SOLUONG")
    private Double cttrakhoSoluong;
    @Column(name = "CTTRAKHO_MALK")
    private String cttrakhoMalk;
    @Column(name = "CTTRAKHO_NAMNHAP")
    private String cttrakhoNamnhap;
    @Column(name = "CTTRAKHO_NGAYHANDUNG")
    private String cttrakhoNgayhandung;
    @Column(name = "CTTRAKHO_THANGHANDUNG")
    private String cttrakhoThanghandung;
    @Column(name = "CTTRAKHO_NAMHANDUNG")
    private String cttrakhoNamhandung;
    @Column(name = "CTTRAKHO_DONGIA")
    private Double cttrakhoDongia;
    @Column(name = "CTTRAKHO_DONGIABAN")
    private Double cttrakhoDongiaban;
    @Column(name = "CTTRAKHO_LO")
    private String cttrakhoLo;
    @Column(name = "CTXUATKHO_SODANGKY")
    private String ctxuatkhoSodangky;
    
    
    
    @Column(name = "TONKHO_MA")
    private Integer tonkhoMa;        
            
    @Column(name = "CTTRAKHO_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cttrakhoNgaygiocn;
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
    @JoinColumn(name = "PHIEUTRAKHO_MA", referencedColumnName = "PHIEUTRAKHO_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuTraKho phieutrakhoMa;

    public CtTraKho() {
    }

    public CtTraKho(Integer cttrakhoMa) {
        this.cttrakhoMa = cttrakhoMa;
    }

    public Integer getCttrakhoMa() {
        return cttrakhoMa;
    }

    public void setCttrakhoMa(Integer cttrakhoMa) {
        this.cttrakhoMa = cttrakhoMa;
    }

    public Short getCttrakhoThutu() {
        return cttrakhoThutu;
    }

    public void setCttrakhoThutu(Short cttrakhoThutu) {
        this.cttrakhoThutu = cttrakhoThutu;
    }

    public Double getCttrakhoSoluong() {
        return cttrakhoSoluong;
    }

    public void setCttrakhoSoluong(Double cttrakhoSoluong) {
        this.cttrakhoSoluong = cttrakhoSoluong;
    }

    public String getCttrakhoMalk() {
        return cttrakhoMalk;
    }

    public void setCttrakhoMalk(String cttrakhoMalk) {
        this.cttrakhoMalk = cttrakhoMalk;
    }

    public String getCttrakhoNamnhap() {
        return cttrakhoNamnhap;
    }

    public void setCttrakhoNamnhap(String cttrakhoNamnhap) {
        this.cttrakhoNamnhap = cttrakhoNamnhap;
    }

    public String getCttrakhoNgayhandung() {
        return cttrakhoNgayhandung;
    }

    public void setCttrakhoNgayhandung(String cttrakhoNgayhandung) {
        this.cttrakhoNgayhandung = cttrakhoNgayhandung;
    }

    public String getCttrakhoThanghandung() {
        return cttrakhoThanghandung;
    }

    public void setCttrakhoThanghandung(String cttrakhoThanghandung) {
        this.cttrakhoThanghandung = cttrakhoThanghandung;
    }

    public String getCttrakhoNamhandung() {
        return cttrakhoNamhandung;
    }

    public void setCttrakhoNamhandung(String cttrakhoNamhandung) {
        this.cttrakhoNamhandung = cttrakhoNamhandung;
    }

    public Double getCttrakhoDongia() {
        return cttrakhoDongia;
    }

    public void setCttrakhoDongia(Double cttrakhoDongia) {
        this.cttrakhoDongia = cttrakhoDongia;
    }

    public Double getCttrakhoDongiaban() {
        return cttrakhoDongiaban;
    }

    public void setCttrakhoDongiaban(Double cttrakhoDongiaban) {
        this.cttrakhoDongiaban = cttrakhoDongiaban;
    }

    public String getCttrakhoLo() {
        return cttrakhoLo;
    }

    public void setCttrakhoLo(String cttrakhoLo) {
        this.cttrakhoLo = cttrakhoLo;
    }

    public String getCtxuatkhoSodangky() {
        return ctxuatkhoSodangky;
    }

    public void setCtxuatkhoSodangky(String ctxuatkhoSodangky) {
        this.ctxuatkhoSodangky = ctxuatkhoSodangky;
    }

    public Date getCttrakhoNgaygiocn() {
        return cttrakhoNgaygiocn;
    }

    public void setCttrakhoNgaygiocn(Date cttrakhoNgaygiocn) {
        this.cttrakhoNgaygiocn = cttrakhoNgaygiocn;
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

    public PhieuTraKho getPhieutrakhoMa(boolean create) {
if(create && phieutrakhoMa == null) phieutrakhoMa = new PhieuTraKho();
return phieutrakhoMa;
}
    public PhieuTraKho getPhieutrakhoMa() {
        return phieutrakhoMa;
    }

    public void setPhieutrakhoMa(PhieuTraKho phieutrakhoMa) {
        this.phieutrakhoMa = phieutrakhoMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cttrakhoMa != null ? cttrakhoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtTraKho)) {
            return false;
        }
        CtTraKho other = (CtTraKho) object;
        if ((this.cttrakhoMa == null && other.cttrakhoMa != null) || (this.cttrakhoMa != null && !this.cttrakhoMa.equals(other.cttrakhoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.CtTraKho[cttrakhoMa=" + cttrakhoMa + "]";
    }

    public Integer getTonkhoMa() {
        return tonkhoMa;
    }

    public void setTonkhoMa(Integer tonkhoMa) {
        this.tonkhoMa = tonkhoMa;
    }

}


