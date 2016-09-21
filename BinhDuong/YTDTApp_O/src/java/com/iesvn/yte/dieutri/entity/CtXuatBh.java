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
@Table(name = "CT_XUAT_BH")
@NamedQueries({})
public class CtXuatBh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_XUAT_BH_CTXUATBH_MA")
    @SequenceGenerator(name = "CT_XUAT_BH_CTXUATBH_MA", sequenceName = "CT_XUAT_BH_CTXUATBH_MA_SEQ", allocationSize = 1)
    @Column(name = "CTXUATBH_MA", nullable = false)
    private Integer ctxuatbhMa;
    @Column(name = "CTXUATBH_THUTU")
    private Integer ctxuatbhThutu;
    @Column(name = "CTXUATBH_SOLUONG")
    private Double ctxuatbhSoluong;
    @Column(name = "CTXUATBH_MALK")
    private String ctxuatbhMalk;
    @Column(name = "CTXUATBH_NAMNHAP")
    private String ctxuatbhNamnhap;
    @Column(name = "CTXUATBH_NGAYHANDUNG")
    private String ctxuatbhNgayhandung;
    @Column(name = "CTXUATBH_THANGHANDUNG")
    private String ctxuatbhThanghandung;
    @Column(name = "CTXUATBH_NAMHANDUNG")
    private String ctxuatbhNamhandung;
    @Column(name = "CTXUATBH_DONGIA")
    private Double ctxuatbhDongia;
    @Column(name = "CTXUATBH_DONGIABAN")
    private Double ctxuatbhDongiaban;
    @Column(name = "CTXUATBH_LO")
    private String ctxuatbhLo;
    @Column(name = "CTXUATBH_SODANGKY")
    private Double ctxuatbhSodangky;
    @Column(name = "CTXUATBH_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctxuatbhNgaygiocn;
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
    @JoinColumn(name = "PHIEUXUATBH_MA", referencedColumnName = "PHIEUXUATBH_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuXuatBh phieuxuatbhMa;
    
    @Column(name = "TONKHO_MA")
    private Integer tonKhoMa;

    public CtXuatBh() {
    }

    public CtXuatBh(Integer ctxuatbhMa) {
        this.ctxuatbhMa = ctxuatbhMa;
    }

    public Integer getCtxuatbhMa() {
        return ctxuatbhMa;
    }

    public void setCtxuatbhMa(Integer ctxuatbhMa) {
        this.ctxuatbhMa = ctxuatbhMa;
    }

    public Integer getCtxuatbhThutu() {
        return ctxuatbhThutu;
    }

    public void setCtxuatbhThutu(Integer ctxuatbhThutu) {
        this.ctxuatbhThutu = ctxuatbhThutu;
    }

    public Double getCtxuatbhSoluong() {
        return ctxuatbhSoluong;
    }

    public void setCtxuatbhSoluong(Double ctxuatbhSoluong) {
        this.ctxuatbhSoluong = ctxuatbhSoluong;
    }

    public String getCtxuatbhMalk() {
        return ctxuatbhMalk;
    }

    public void setCtxuatbhMalk(String ctxuatbhMalk) {
        this.ctxuatbhMalk = ctxuatbhMalk;
    }

    public String getCtxuatbhNamnhap() {
        return ctxuatbhNamnhap;
    }

    public void setCtxuatbhNamnhap(String ctxuatbhNamnhap) {
        this.ctxuatbhNamnhap = ctxuatbhNamnhap;
    }

    public String getCtxuatbhNgayhandung() {
        return ctxuatbhNgayhandung;
    }

    public void setCtxuatbhNgayhandung(String ctxuatbhNgayhandung) {
        this.ctxuatbhNgayhandung = ctxuatbhNgayhandung;
    }

    public String getCtxuatbhThanghandung() {
        return ctxuatbhThanghandung;
    }

    public void setCtxuatbhThanghandung(String ctxuatbhThanghandung) {
        this.ctxuatbhThanghandung = ctxuatbhThanghandung;
    }

    public String getCtxuatbhNamhandung() {
        return ctxuatbhNamhandung;
    }

    public void setCtxuatbhNamhandung(String ctxuatbhNamhandung) {
        this.ctxuatbhNamhandung = ctxuatbhNamhandung;
    }

    public Double getCtxuatbhDongia() {
        return ctxuatbhDongia;
    }

    public void setCtxuatbhDongia(Double ctxuatbhDongia) {
        this.ctxuatbhDongia = ctxuatbhDongia;
    }

    public Double getCtxuatbhDongiaban() {
        return ctxuatbhDongiaban;
    }

    public void setCtxuatbhDongiaban(Double ctxuatbhDongiaban) {
        this.ctxuatbhDongiaban = ctxuatbhDongiaban;
    }

    public String getCtxuatbhLo() {
        return ctxuatbhLo;
    }

    public void setCtxuatbhLo(String ctxuatbhLo) {
        this.ctxuatbhLo = ctxuatbhLo;
    }

    public Double getCtxuatbhSodangky() {
        return ctxuatbhSodangky;
    }

    public void setCtxuatbhSodangky(Double ctxuatbhSodangky) {
        this.ctxuatbhSodangky = ctxuatbhSodangky;
    }

    public Date getCtxuatbhNgaygiocn() {
        return ctxuatbhNgaygiocn;
    }

    public void setCtxuatbhNgaygiocn(Date ctxuatbhNgaygiocn) {
        this.ctxuatbhNgaygiocn = ctxuatbhNgaygiocn;
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

    public PhieuXuatBh getPhieuxuatbhMa(boolean create) {
if(create && phieuxuatbhMa == null) phieuxuatbhMa = new PhieuXuatBh();
return phieuxuatbhMa;
}
    public PhieuXuatBh getPhieuxuatbhMa() {
        return phieuxuatbhMa;
    }

    public void setPhieuxuatbhMa(PhieuXuatBh phieuxuatbhMa) {
        this.phieuxuatbhMa = phieuxuatbhMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctxuatbhMa != null ? ctxuatbhMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtXuatBh)) {
            return false;
        }
        CtXuatBh other = (CtXuatBh) object;
        if ((this.ctxuatbhMa == null && other.ctxuatbhMa != null) || (this.ctxuatbhMa != null && !this.ctxuatbhMa.equals(other.ctxuatbhMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.CtXuatBh[ctxuatbhMa=" + ctxuatbhMa + "]";
    }

    public Integer getTonKhoMa() {
        return tonKhoMa;
    }

    public void setTonKhoMa(Integer tonKhoMa) {
        this.tonKhoMa = tonKhoMa;
    }
}


