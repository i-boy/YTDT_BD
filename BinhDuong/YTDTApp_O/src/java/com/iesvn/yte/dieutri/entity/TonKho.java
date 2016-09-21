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
import com.iesvn.yte.entity.DmKhoa;
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
@Table(name = "TON_KHO")
@NamedQueries({})
public class TonKho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TON_KHO")
    @SequenceGenerator(name = "TON_KHO", sequenceName = "TON_KHO_TONKHO_MA_SEQ", allocationSize = 1)
    @Column(name = "TONKHO_MA", nullable = false)
    private Integer tonkhoMa;
    @Column(name = "TONKHO_MAPHIEUKIEM")
    private String tonkhoMaphieukiem;
    @Column(name = "TONKHO_MALIENKET")
    private String tonkhoMalienket;
    @Column(name = "TONKHO_NAMNHAP")
    private String tonkhoNamnhap;
    @Column(name = "TONKHO_NGAYHANDUNG")
    private String tonkhoNgayhandung;
    @Column(name = "TONKHO_THANGHANDUNG")
    private String tonkhoThanghandung;
    @Column(name = "TONKHO_NAMHANDUNG")
    private String tonkhoNamhandung;
    @Column(name = "TONKHO_DONGIA")
    private Double tonkhoDongia;
    @Column(name = "TONKHO_DONGIABAN")
    private Double tonkhoDongiaban;
    @Column(name = "TONKHO_MUCTHUE")
    private Float tonkhoMucthue;
    @Column(name = "TONKHO_LO")
    private String tonkhoLo;
    @Column(name = "TONKHO_SODANGKY")
    private String tonkhoSodangky;
    @Column(name = "TONKHO_TONDAU")
    private Double tonkhoTondau;
    @Column(name = "TONKHO_NHAP")
    private Double tonkhoNhap;
    @Column(name = "TONKHO_TRA")
    private Double tonkhoTra;
    @Column(name = "TONKHO_XUAT")
    private Double tonkhoXuat;
    @Column(name = "TONKHO_TON")
    private Double tonkhoTon;
    @Column(name = "TONKHO_HIENTHI")
    private Boolean tonkhoHienthi;
    @Column(name = "TONKHO_CAPPHAT")
    private Boolean tonkhoCapphat;
    @Column(name = "TONKHO_PHANBIET")
    private String tonkhoPhanbiet;
    @Column(name = "TONKHO_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tonkhoNgaygiocn;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;
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
    @JoinColumn(name = "DTDMKHO_MASO", referencedColumnName = "DTDMKHO_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmKho dtdmkhoMaso;
    @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;
    @JoinColumn(name = "TONKHO_NOIBAN", referencedColumnName = "DMNHACUNGCAP_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaCungCap dmNhaCungCap;
    @JoinColumn(name = "PHIEUNHAPKHO_MA", referencedColumnName = "PHIEUNHAPKHO_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuNhapKho phieunhapkhoMa;

    public TonKho() {
    }

    public TonKho(Integer tonkhoMa) {
        this.tonkhoMa = tonkhoMa;
    }

    public Integer getTonkhoMa() {
        return tonkhoMa;
    }

    public void setTonkhoMa(Integer tonkhoMa) {
        this.tonkhoMa = tonkhoMa;
    }

    public String getTonkhoMalienket() {
        return tonkhoMalienket;
    }

    public void setTonkhoMalienket(String tonkhoMalienket) {
        this.tonkhoMalienket = tonkhoMalienket;
    }

    public String getTonkhoNamnhap() {
        return tonkhoNamnhap;
    }

    public void setTonkhoNamnhap(String tonkhoNamnhap) {
        this.tonkhoNamnhap = tonkhoNamnhap;
    }

    public String getTonkhoNgayhandung() {
        return tonkhoNgayhandung;
    }

    public void setTonkhoNgayhandung(String tonkhoNgayhandung) {
        this.tonkhoNgayhandung = tonkhoNgayhandung;
    }

    public String getTonkhoThanghandung() {
        return tonkhoThanghandung;
    }

    public void setTonkhoThanghandung(String tonkhoThanghandung) {
        this.tonkhoThanghandung = tonkhoThanghandung;
    }

    public String getTonkhoNamhandung() {
        return tonkhoNamhandung;
    }

    public void setTonkhoNamhandung(String tonkhoNamhandung) {
        this.tonkhoNamhandung = tonkhoNamhandung;
    }

    public Double getTonkhoDongia() {
        return tonkhoDongia;
    }

    public void setTonkhoDongia(Double tonkhoDongia) {
        this.tonkhoDongia = tonkhoDongia;
    }

    public Double getTonkhoDongiaban() {
        return tonkhoDongiaban;
    }

    public void setTonkhoDongiaban(Double tonkhoDongiaban) {
        this.tonkhoDongiaban = tonkhoDongiaban;
    }

    public Float getTonkhoMucthue() {
        return tonkhoMucthue;
    }

    public void setTonkhoMucthue(Float tonkhoMucthue) {
        this.tonkhoMucthue = tonkhoMucthue;
    }

    public String getTonkhoLo() {
        return tonkhoLo;
    }

    public void setTonkhoLo(String tonkhoLo) {
        this.tonkhoLo = tonkhoLo;
    }

    public String getTonkhoSodangky() {
        return tonkhoSodangky;
    }

    public void setTonkhoSodangky(String tonkhoSodangky) {
        this.tonkhoSodangky = tonkhoSodangky;
    }

    public Double getTonkhoTondau() {
        return tonkhoTondau;
    }

    public void setTonkhoTondau(Double tonkhoTondau) {
        this.tonkhoTondau = tonkhoTondau;
    }

    public Double getTonkhoNhap() {
        return tonkhoNhap;
    }

    public void setTonkhoNhap(Double tonkhoNhap) {
        this.tonkhoNhap = tonkhoNhap;
    }

    public Double getTonkhoTra() {
        return tonkhoTra;
    }

    public void setTonkhoTra(Double tonkhoTra) {
        this.tonkhoTra = tonkhoTra;
    }

    public Double getTonkhoXuat() {
        return tonkhoXuat;
    }

    public void setTonkhoXuat(Double tonkhoXuat) {
        this.tonkhoXuat = tonkhoXuat;
    }

    public Double getTonkhoTon() {
        return tonkhoTon;
    }

    public void setTonkhoTon(Double tonkhoTon) {
        this.tonkhoTon = tonkhoTon;
    }

    public Boolean getTonkhoHienthi() {
        return tonkhoHienthi;
    }

    public void setTonkhoHienthi(Boolean tonkhoHienthi) {
        this.tonkhoHienthi = tonkhoHienthi;
    }

    public Boolean getTonkhoCapphat() {
        return tonkhoCapphat;
    }

    public void setTonkhoCapphat(Boolean tonkhoCapphat) {
        this.tonkhoCapphat = tonkhoCapphat;
    }

    public String getTonkhoPhanbiet() {
        return tonkhoPhanbiet;
    }

    public void setTonkhoPhanbiet(String tonkhoPhanbiet) {
        this.tonkhoPhanbiet = tonkhoPhanbiet;
    }

    public Date getTonkhoNgaygiocn() {
        return tonkhoNgaygiocn;
    }

    public void setTonkhoNgaygiocn(Date tonkhoNgaygiocn) {
        this.tonkhoNgaygiocn = tonkhoNgaygiocn;
    }

    public DmKhoa getDmkhoaMaso(boolean create) {
        if (create && dmkhoaMaso == null) {
            dmkhoaMaso = new DmKhoa();
        }
        return dmkhoaMaso;
    }

    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    public DmNguonChuongTrinh getDmnctMaso(boolean create) {
        if (create && dmnctMaso == null) {
            dmnctMaso = new DmNguonChuongTrinh();
        }
        return dmnctMaso;
    }

    public DmNguonChuongTrinh getDmnctMaso() {
        return dmnctMaso;
    }

    public void setDmnctMaso(DmNguonChuongTrinh dmnctMaso) {
        this.dmnctMaso = dmnctMaso;
    }

    public DmNguonKinhPhi getDmnguonkinhphiMaso(boolean create) {
        if (create && dmnguonkinhphiMaso == null) {
            dmnguonkinhphiMaso = new DmNguonKinhPhi();
        }
        return dmnguonkinhphiMaso;
    }

    public DmNguonKinhPhi getDmnguonkinhphiMaso() {
        return dmnguonkinhphiMaso;
    }

    public void setDmnguonkinhphiMaso(DmNguonKinhPhi dmnguonkinhphiMaso) {
        this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
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

    public DtDmKho getDtdmkhoMaso(boolean create) {
        if (create && dtdmkhoMaso == null) {
            dtdmkhoMaso = new DtDmKho();
        }
        return dtdmkhoMaso;
    }

    public DtDmKho getDtdmkhoMaso() {
        return dtdmkhoMaso;
    }

    public void setDtdmkhoMaso(DtDmKho dtdmkhoMaso) {
        this.dtdmkhoMaso = dtdmkhoMaso;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tonkhoMa != null ? tonkhoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TonKho)) {
            return false;
        }
        TonKho other = (TonKho) object;
        if ((this.tonkhoMa == null && other.tonkhoMa != null) || (this.tonkhoMa != null && !this.tonkhoMa.equals(other.tonkhoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.TonKho[tonkhoMa=" + tonkhoMa + "]";
    }

    public DmNhaCungCap getDmNhaCungCap() {
        return dmNhaCungCap;
    }

    public void setDmNhaCungCap(DmNhaCungCap dmNhaCungCap) {
        this.dmNhaCungCap = dmNhaCungCap;
    }

    public PhieuNhapKho getPhieunhapkhoMa() {
        return phieunhapkhoMa;
    }
    
    public PhieuNhapKho getPhieunhapkhoMa(Boolean create) {
        if (create && phieunhapkhoMa == null) {
            phieunhapkhoMa = new PhieuNhapKho();
        }
        return phieunhapkhoMa;
    }

    public void setPhieunhapkhoMa(PhieuNhapKho phieunhapkhoMa) {
        this.phieunhapkhoMa = phieunhapkhoMa;
    }

    public String getTonkhoMaphieukiem() {
        return tonkhoMaphieukiem;
    }

    public void setTonkhoMaphieukiem(String tonkhoMaphieukiem) {
        this.tonkhoMaphieukiem = tonkhoMaphieukiem;
    }
}


