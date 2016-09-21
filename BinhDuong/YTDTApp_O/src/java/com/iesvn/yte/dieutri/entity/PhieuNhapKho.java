/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmNhaCungCap;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PHIEU_NHAP_KHO")
@NamedQueries({})
public class PhieuNhapKho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PHIEUNHAPKHO_MA", nullable = false)
    private String phieunhapkhoMa;
    @Column(name = "PHIEUNHAPKHO_SOHOADON", nullable = false)
    private String phieunhapkhoSohoadon;
    @Column(name = "PHIEUNHAPKHO_NGAYHOADON", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date phieunhapkhoNgayhoadon;
    @Column(name = "PHIEUNHAPKHO_CHUNGTU")
    private String phieunhapkhoChungtu;
    @Column(name = "PHIEUNHAPKHO_MUCTHUE")
    private Double phieunhapkhoMucthue;
    @Column(name = "PHIEUNHAPKHO_THUE")
    private Double phieunhapkhoThue;
    @Column(name = "PHIEUNHAPKHO_THANHTIEN")
    private Double phieunhapkhoThanhtien;
    @Column(name = "PHIEUNHAPKHO_TAIKHOAN")
    private String phieunhapkhoTaikhoan;
    @Column(name = "PHIEUNHAPKHO_DOIUNG")
    private String phieunhapkhoDoiung;
    @Column(name = "PHIEUNHAPKHO_TRONSO")
    private Double phieunhapkhoTronso;
    @Column(name = "PHIEUNHAPKHO_NGAYTT")
    @Temporal(TemporalType.DATE)
    private Date phieunhapkhoNgaytt;
    @Column(name = "PHIEUNHAPKHO_CHUNGTUTT")
    private String phieunhapkhoChungtutt;
    @Column(name = "PHIEUNHAPKHO_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieunhapkhoNgaygiocn;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phieunhapkhoMa")
//    private Collection<CtNhapKho> ctNhapKhoCollection;
    @JoinColumn(name = "DMLOAITHUOC_MASO", referencedColumnName = "DMLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiThuoc dmloaithuocMaso;
    @JoinColumn(name = "DTDMNHANVIEN_TIEPLIEU", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienTieplieu;
    @JoinColumn(name = "DTDMNHANVIEN_TT", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienTt;
    @JoinColumn(name = "DMNCT_MASO", referencedColumnName = "DMNCT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonChuongTrinh dmnctMaso;
    @JoinColumn(name = "DMNGUONKINHPHI_MASO", referencedColumnName = "DMNGUONKINHPHI_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonKinhPhi dmnguonkinhphiMaso;
    @JoinColumn(name = "DTDMNOIBAN_MA", referencedColumnName = "DMNHACUNGCAP_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaCungCap dtdmnoibanMa;
    @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;
    @JoinColumn(name = "DTDMKHO_MASO", referencedColumnName = "DTDMKHO_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmKho dtdmkhoMaso;

    public PhieuNhapKho() {
    }

    public PhieuNhapKho(String phieunhapkhoMa) {
        this.phieunhapkhoMa = phieunhapkhoMa;
    }

    public PhieuNhapKho(String phieunhapkhoMa, String phieunhapkhoSohoadon, Date phieunhapkhoNgayhoadon) {
        this.phieunhapkhoMa = phieunhapkhoMa;
        this.phieunhapkhoSohoadon = phieunhapkhoSohoadon;
        this.phieunhapkhoNgayhoadon = phieunhapkhoNgayhoadon;
    }

    public String getPhieunhapkhoMa() {
        return phieunhapkhoMa;
    }

    public void setPhieunhapkhoMa(String phieunhapkhoMa) {
        this.phieunhapkhoMa = phieunhapkhoMa;
    }

    public String getPhieunhapkhoSohoadon() {
        return phieunhapkhoSohoadon;
    }

    public void setPhieunhapkhoSohoadon(String phieunhapkhoSohoadon) {
        this.phieunhapkhoSohoadon = phieunhapkhoSohoadon;
    }

    public Date getPhieunhapkhoNgayhoadon() {
        return phieunhapkhoNgayhoadon;
    }

    public void setPhieunhapkhoNgayhoadon(Date phieunhapkhoNgayhoadon) {
        this.phieunhapkhoNgayhoadon = phieunhapkhoNgayhoadon;
    }

    public String getPhieunhapkhoChungtu() {
        return phieunhapkhoChungtu;
    }

    public void setPhieunhapkhoChungtu(String phieunhapkhoChungtu) {
        this.phieunhapkhoChungtu = phieunhapkhoChungtu;
    }

    public Double getPhieunhapkhoMucthue() {
        return phieunhapkhoMucthue;
    }

    public void setPhieunhapkhoMucthue(Double phieunhapkhoMucthue) {
        this.phieunhapkhoMucthue = phieunhapkhoMucthue;
    }

    public Double getPhieunhapkhoThue() {
        return phieunhapkhoThue;
    }

    public void setPhieunhapkhoThue(Double phieunhapkhoThue) {
        this.phieunhapkhoThue = phieunhapkhoThue;
    }

    public Double getPhieunhapkhoThanhtien() {
        return phieunhapkhoThanhtien;
    }

    public void setPhieunhapkhoThanhtien(Double phieunhapkhoThanhtien) {
        this.phieunhapkhoThanhtien = phieunhapkhoThanhtien;
    }

    public String getPhieunhapkhoTaikhoan() {
        return phieunhapkhoTaikhoan;
    }

    public void setPhieunhapkhoTaikhoan(String phieunhapkhoTaikhoan) {
        this.phieunhapkhoTaikhoan = phieunhapkhoTaikhoan;
    }

    public String getPhieunhapkhoDoiung() {
        return phieunhapkhoDoiung;
    }

    public void setPhieunhapkhoDoiung(String phieunhapkhoDoiung) {
        this.phieunhapkhoDoiung = phieunhapkhoDoiung;
    }

    public Double getPhieunhapkhoTronso() {
        return phieunhapkhoTronso;
    }

    public void setPhieunhapkhoTronso(Double phieunhapkhoTronso) {
        this.phieunhapkhoTronso = phieunhapkhoTronso;
    }

    public Date getPhieunhapkhoNgaytt() {
        return phieunhapkhoNgaytt;
    }

    public void setPhieunhapkhoNgaytt(Date phieunhapkhoNgaytt) {
        this.phieunhapkhoNgaytt = phieunhapkhoNgaytt;
    }

    public String getPhieunhapkhoChungtutt() {
        return phieunhapkhoChungtutt;
    }

    public void setPhieunhapkhoChungtutt(String phieunhapkhoChungtutt) {
        this.phieunhapkhoChungtutt = phieunhapkhoChungtutt;
    }

    public Date getPhieunhapkhoNgaygiocn() {
        return phieunhapkhoNgaygiocn;
    }

    public void setPhieunhapkhoNgaygiocn(Date phieunhapkhoNgaygiocn) {
        this.phieunhapkhoNgaygiocn = phieunhapkhoNgaygiocn;
    }

//    public Collection<CtNhapKho> getCtNhapKhoCollection() {
//        return ctNhapKhoCollection;
//    }
//
//    public void setCtNhapKhoCollection(Collection<CtNhapKho> ctNhapKhoCollection) {
//        this.ctNhapKhoCollection = ctNhapKhoCollection;
//    }
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

    public DtDmNhanVien getDtdmnhanvienTieplieu(boolean create) {
if(create && dtdmnhanvienTieplieu == null) dtdmnhanvienTieplieu = new DtDmNhanVien();
return dtdmnhanvienTieplieu;
}
    public DtDmNhanVien getDtdmnhanvienTieplieu() {
        return dtdmnhanvienTieplieu;
    }

    public void setDtdmnhanvienTieplieu(DtDmNhanVien dtdmnhanvienTieplieu) {
        this.dtdmnhanvienTieplieu = dtdmnhanvienTieplieu;
    }

    public DtDmNhanVien getDtdmnhanvienTt(boolean create) {
if(create && dtdmnhanvienTt == null) dtdmnhanvienTt = new DtDmNhanVien();
return dtdmnhanvienTt;
}
    public DtDmNhanVien getDtdmnhanvienTt() {
        return dtdmnhanvienTt;
    }

    public void setDtdmnhanvienTt(DtDmNhanVien dtdmnhanvienTt) {
        this.dtdmnhanvienTt = dtdmnhanvienTt;
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

    public DmNhaCungCap getDtdmnoibanMa(boolean create) {
if(create && dtdmnoibanMa == null) dtdmnoibanMa = new DmNhaCungCap();
return dtdmnoibanMa;
}
    public DmNhaCungCap getDtdmnoibanMa() {
        return dtdmnoibanMa;
    }

    public void setDtdmnoibanMa(DmNhaCungCap dtdmnoibanMa) {
        this.dtdmnoibanMa = dtdmnoibanMa;
    }

    public DtDmNhanVien getDtdmnhanvienCn(boolean create) {
if(create && dtdmnhanvienCn == null) dtdmnhanvienCn = new DtDmNhanVien();
return dtdmnhanvienCn;
}
    public DtDmNhanVien getDtdmnhanvienCn() {
        return dtdmnhanvienCn;
    }

    public void setDtdmnhanvienCn(DtDmNhanVien dtdmnhanvienCn) {
        this.dtdmnhanvienCn = dtdmnhanvienCn;
    }

    public DtDmKho getDtdmkhoMaso(boolean create) {
if(create && dtdmkhoMaso == null) dtdmkhoMaso = new DtDmKho();
return dtdmkhoMaso;
}
    public DtDmKho getDtdmkhoMaso() {
        return dtdmkhoMaso;
    }

    public void setDtdmkhoMaso(DtDmKho dtdmkhoMaso) {
        this.dtdmkhoMaso = dtdmkhoMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phieunhapkhoMa != null ? phieunhapkhoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuNhapKho)) {
            return false;
        }
        PhieuNhapKho other = (PhieuNhapKho) object;
        if ((this.phieunhapkhoMa == null && other.phieunhapkhoMa != null) || (this.phieunhapkhoMa != null && !this.phieunhapkhoMa.equals(other.phieunhapkhoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.PhieuNhapKho[phieunhapkhoMa=" + phieunhapkhoMa + "]";
    }
}


