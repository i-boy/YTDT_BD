/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmNhaCungCap;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmThuoc;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "KIEM_KE_KHO")
@NamedQueries({})
public class KiemKeKho implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KIEM_KE_KHO_KIEMKE_MA")
    @SequenceGenerator(name = "KIEM_KE_KHO_KIEMKE_MA", sequenceName = "KIEM_KE_KHO_KIEMKE_MA_SEQ", allocationSize = 1)
    @Column(name = "KIEMKE_MA")
    private Integer kiemkeMa;
    @Column(name = "KIEMKE_MALIENKET")
    private String kiemkeMalienket;
    @Column(name = "KIEMKE_NAMNHAP")
    private String kiemkeNamnhap;
    @Column(name = "KIEMKE_NGAYHANDUNG")
    private String kiemkeNgayhandung;
    @Column(name = "KIEMKE_THANGHANDUNG")
    private String kiemkeThanghandung;
    @Column(name = "KIEMKE_NAMHANDUNG")
    private String kiemkeNamhandung;
    @Column(name = "KIEMKE_DONGIA")
    private Double kiemkeDongia;
    @Column(name = "KIEMKE_DONGIABAN")
    private Double kiemkeDongiaban;
    @Column(name = "KIEMKE_MUCTHUE")
    private Float kiemkeMucthue;
    @Column(name = "KIEMKE_LO")
    private String kiemkeLo;
    @Column(name = "KIEMKE_SODANGKY")
    private String kiemkeSodangky;
    @Column(name = "KIEMKE_NGAYKIEM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date kiemkeNgaykiem;
    @Column(name = "KIEMKE_TON")
    private Double kiemkeTon;
    @Column(name = "KIEMKE_TONTT")
    private Double kiemkeTontt;
    @Column(name = "KIEMKE_PHANBIET")
    private String kiemkePhanbiet;
    @Column(name = "KIEMKE_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date kiemkeNgaygiocn;
    @Column(name = "TONKHO_MA")
    private Integer tonkhoMa;
    @Column(name = "KIEMKE_LYDO")
    private String kiemkeLydo;
    @JoinColumn(name = "KIEMKE_MAPHIEUKIEM", referencedColumnName = "MAPHIEUKIEM")
    @ManyToOne (fetch=FetchType.LAZY)
    private KiemKe kiemkeMaphieukiem;
    @JoinColumn(name = "KIEMKE_NOIBAN", referencedColumnName = "DMNHACUNGCAP_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaCungCap kiemkeNoiban;
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
    @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;

    public KiemKeKho() {
    }

    public KiemKeKho(Integer kiemkeMa) {
        this.kiemkeMa = kiemkeMa;
    }

    public Integer getKiemkeMa() {
        return kiemkeMa;
    }

    public void setKiemkeMa(Integer kiemkeMa) {
        this.kiemkeMa = kiemkeMa;
    }

    public String getKiemkeMalienket() {
        return kiemkeMalienket;
    }

    public void setKiemkeMalienket(String kiemkeMalienket) {
        this.kiemkeMalienket = kiemkeMalienket;
    }

    public String getKiemkeNamnhap() {
        return kiemkeNamnhap;
    }

    public void setKiemkeNamnhap(String kiemkeNamnhap) {
        this.kiemkeNamnhap = kiemkeNamnhap;
    }

    public String getKiemkeNgayhandung() {
        return kiemkeNgayhandung;
    }

    public void setKiemkeNgayhandung(String kiemkeNgayhandung) {
        this.kiemkeNgayhandung = kiemkeNgayhandung;
    }

    public String getKiemkeThanghandung() {
        return kiemkeThanghandung;
    }

    public void setKiemkeThanghandung(String kiemkeThanghandung) {
        this.kiemkeThanghandung = kiemkeThanghandung;
    }

    public String getKiemkeNamhandung() {
        return kiemkeNamhandung;
    }

    public void setKiemkeNamhandung(String kiemkeNamhandung) {
        this.kiemkeNamhandung = kiemkeNamhandung;
    }

    public Double getKiemkeDongia() {
        return kiemkeDongia;
    }

    public void setKiemkeDongia(Double kiemkeDongia) {
        this.kiemkeDongia = kiemkeDongia;
    }

    public Double getKiemkeDongiaban() {
        return kiemkeDongiaban;
    }

    public void setKiemkeDongiaban(Double kiemkeDongiaban) {
        this.kiemkeDongiaban = kiemkeDongiaban;
    }

    public Float getKiemkeMucthue() {
        return kiemkeMucthue;
    }

    public void setKiemkeMucthue(Float kiemkeMucthue) {
        this.kiemkeMucthue = kiemkeMucthue;
    }

    public String getKiemkeLo() {
        return kiemkeLo;
    }

    public void setKiemkeLo(String kiemkeLo) {
        this.kiemkeLo = kiemkeLo;
    }

    public String getKiemkeSodangky() {
        return kiemkeSodangky;
    }

    public void setKiemkeSodangky(String kiemkeSodangky) {
        this.kiemkeSodangky = kiemkeSodangky;
    }

    public Date getKiemkeNgaykiem() {
        return kiemkeNgaykiem;
    }

    public void setKiemkeNgaykiem(Date kiemkeNgaykiem) {
        this.kiemkeNgaykiem = kiemkeNgaykiem;
    }

    public Double getKiemkeTon() {
        return kiemkeTon;
    }

    public void setKiemkeTon(Double kiemkeTon) {
        this.kiemkeTon = kiemkeTon;
    }

    public Double getKiemkeTontt() {
        return kiemkeTontt;
    }

    public void setKiemkeTontt(Double kiemkeTontt) {
        this.kiemkeTontt = kiemkeTontt;
    }

    public String getKiemkePhanbiet() {
        return kiemkePhanbiet;
    }

    public void setKiemkePhanbiet(String kiemkePhanbiet) {
        this.kiemkePhanbiet = kiemkePhanbiet;
    }

    public Date getKiemkeNgaygiocn() {
        return kiemkeNgaygiocn;
    }

    public void setKiemkeNgaygiocn(Date kiemkeNgaygiocn) {
        this.kiemkeNgaygiocn = kiemkeNgaygiocn;
    }

    public Integer getTonkhoMa() {
        return tonkhoMa;
    }

    public void setTonkhoMa(Integer tonkhoMa) {
        this.tonkhoMa = tonkhoMa;
    }

    public String getKiemkeLydo() {
        return kiemkeLydo;
    }

    public void setKiemkeLydo(String kiemkeLydo) {
        this.kiemkeLydo = kiemkeLydo;
    }

    public KiemKe getKiemkeMaphieukiem() {
        return kiemkeMaphieukiem;
    }

    public void setKiemkeMaphieukiem(KiemKe kiemkeMaphieukiem) {
        this.kiemkeMaphieukiem = kiemkeMaphieukiem;
    }

    public DmNhaCungCap getKiemkeNoiban() {
        return kiemkeNoiban;
    }

    public DmNhaCungCap getKiemkeNoiban(boolean create) {
        if(create && kiemkeNoiban == null) kiemkeNoiban = new DmNhaCungCap();
        return kiemkeNoiban;
    }

    public void setKiemkeNoiban(DmNhaCungCap kiemkeNoiban) {
        this.kiemkeNoiban = kiemkeNoiban;
    }

    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    public DmKhoa getDmkhoaMaso(boolean create) {
        if(create && dmkhoaMaso == null) dmkhoaMaso = new DmKhoa();
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    public DmNguonChuongTrinh getDmnctMaso() {
        return dmnctMaso;
    }

    public DmNguonChuongTrinh getDmnctMaso(boolean create) {
        if(create && dmnctMaso == null) dmnctMaso = new DmNguonChuongTrinh();
        return dmnctMaso;
    }

    public void setDmnctMaso(DmNguonChuongTrinh dmnctMaso) {
        this.dmnctMaso = dmnctMaso;
    }

    public DmNguonKinhPhi getDmnguonkinhphiMaso() {
        return dmnguonkinhphiMaso;
    }

    public DmNguonKinhPhi getDmnguonkinhphiMaso(boolean create) {
        if(create && dmnguonkinhphiMaso == null) dmnguonkinhphiMaso = new DmNguonKinhPhi();
        return dmnguonkinhphiMaso;
    }

    public void setDmnguonkinhphiMaso(DmNguonKinhPhi dmnguonkinhphiMaso) {
        this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
    }

    public DmNhaSanXuat getDmnhasanxuatMaso() {
        return dmnhasanxuatMaso;
    }

    public DmNhaSanXuat getDmnhasanxuatMaso(boolean create) {
        if(create && dmnhasanxuatMaso == null) dmnhasanxuatMaso = new DmNhaSanXuat();
        return dmnhasanxuatMaso;
    }

    public void setDmnhasanxuatMaso(DmNhaSanXuat dmnhasanxuatMaso) {
        this.dmnhasanxuatMaso = dmnhasanxuatMaso;
    }

    public DmQuocGia getDmquocgiaMaso() {
        return dmquocgiaMaso;
    }

    public DmQuocGia getDmquocgiaMaso(boolean create) {
        if(create && dmquocgiaMaso == null) dmquocgiaMaso = new DmQuocGia();
        return dmquocgiaMaso;
    }

    public void setDmquocgiaMaso(DmQuocGia dmquocgiaMaso) {
        this.dmquocgiaMaso = dmquocgiaMaso;
    }

    public DmThuoc getDmthuocMaso() {
        return dmthuocMaso;
    }

    public DmThuoc getDmthuocMaso(boolean create) {
        if(create && dmthuocMaso == null) dmthuocMaso = new DmThuoc();
        return dmthuocMaso;
    }

    public void setDmthuocMaso(DmThuoc dmthuocMaso) {
        this.dmthuocMaso = dmthuocMaso;
    }

    public DtDmNhanVien getDtdmnhanvienCn() {
        return dtdmnhanvienCn;
    }

    public DtDmNhanVien getDtdmnhanvienCn(boolean create) {
        if(create && dtdmnhanvienCn == null) dtdmnhanvienCn = new DtDmNhanVien();
        return dtdmnhanvienCn;
    }

    public void setDtdmnhanvienCn(DtDmNhanVien dtdmnhanvienCn) {
        this.dtdmnhanvienCn = dtdmnhanvienCn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kiemkeMa != null ? kiemkeMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KiemKeKho)) {
            return false;
        }
        KiemKeKho other = (KiemKeKho) object;
        if ((this.kiemkeMa == null && other.kiemkeMa != null) || (this.kiemkeMa != null && !this.kiemkeMa.equals(other.kiemkeMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.KiemKeKho[kiemkeMa=" + kiemkeMa + "]";
    }
}
