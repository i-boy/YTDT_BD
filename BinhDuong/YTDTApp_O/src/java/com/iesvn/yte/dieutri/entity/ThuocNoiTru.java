/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
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
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "THUOC_NOI_TRU")
@NamedQueries({})
public class ThuocNoiTru implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THUOC_NOI_TRU")
    @SequenceGenerator(name = "THUOC_NOI_TRU", sequenceName = "THUOC_NOI_TRU_THUOCNOITRU_MA_S", allocationSize = 1)
    @Column(name = "THUOCNOITRU_MA", nullable = false)
    private Integer thuocnoitruMa;
    @Column(name = "THUOCNOITRU_MAPHIEU")
    private String thuocnoitruMaphieu;
    @Column(name = "THUOCNOITRU_PHONG")
    private String thuocnoitruPhong;
    @Column(name = "THUOCNOITRU_BOSUNG")
    private String thuocnoitruBosung;
    @Column(name = "THUOCNOITRU_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thuocnoitruNgaygio;
    @Column(name = "THUOCNOITRU_LOAI")
    private String thuocnoitruLoai;
    @Column(name = "THUOCNOITRU_YEUCAU")
    private Boolean thuocnoitruYeucau;
    @Column(name = "THUOCNOITRU_MIEN")
    private Boolean thuocnoitruMien;
    @Column(name = "THUOCNOITRU_NHI")
    private Boolean thuocnoitruNhi;
    @Column(name = "THUOCNOITRU_LAO")
    private Boolean thuocnoitruLao;
    @Column(name = "THUOCNOITRU_SOLUONG")
    private Double thuocnoitruSoluong;
    @Column(name = "THUOCNOITRU_TRA")
    private Double thuocnoitruTra;
    @Column(name = "THUOCNOITRU_DONGIA")
    private Double thuocnoitruDongia;
    @Column(name = "THUOCNOITRU_DONGIABH")
    private Double thuocnoitruDongiabh;
    @Column(name = "THUOCNOITRU_NGAYTT")
    @Temporal(TemporalType.DATE)
    private Date thuocnoitruNgaytt;
    @Column(name = "THUOCNOITRU_CUM")
    private Short thuocnoitruCum;
    @Column(name = "THUOCNOITRU_LANLINH")
    private Short thuocnoitruLanlinh;
    @Column(name = "THUOCNOITRU_SODANGKY")
    private String thuocnoitruSodangky;
    @Column(name = "THUOCNOITRU_KHONGTHU")
    private Boolean thuocnoitruKhongthu;
    @Column(name = "THUOCNOITRU_STATUS")
    private String thuocnoitruStatus;
    @Column(name = "THUOCNOITRU_NGAYINLINH")
    @Temporal(TemporalType.DATE)
    private Date thuocnoitruNgayinlinh;
    @Column(name = "THUOCNOITRU_LANINTRA")
    private Short thuocnoitruLanintra;
    @Column(name = "THUOCNOITRU_NGAYINTRA")
    @Temporal(TemporalType.DATE)
    private Date thuocnoitruNgayintra;
    @Column(name = "THUOCNOITRU_DONGIANHAP")
    private Double thuocnoitruDongianhap;
    @Column(name = "THUOCNOITRU_DONGIABAN")
    private Double thuocnoitruDongiaban;
    @Column(name = "THUOCNOITRU_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thuocnoitruNgaygiocn;
    @Column(name = "THUOCNOITRU_STT")
    private Integer thuocnoitruStt;
    @Column(name = "THUOCNOITRU_TUTRUC_PDT")
    private Integer thuocnoitruTutrucPdt;
    @Column(name = "THUOCNOITRU_NDM")
    private Boolean thuocnoitruNDM;
    @Column(name = "THUOCNOITRU_MALK")
    private String thuocnoitruMalk;
    @Column(name = "THUOCNOITRU_MAPHIEUPDTTRA")
    private String thuocnoitruMaphieupdttra;
    @Column(name = "THUOCNOITRU_MAPHIEUDT")
    private String thuocnoitruMaPhieuDT;
    @Column(name = "THUOCNOITRU_NAMNHAP")
    private String thuocnoitruNamnhap;
    @Column(name = "THUOCNOITRU_NGAYHD")
    private String thuocnoitruNgayhd;
    @Column(name = "THUOCNOITRU_THANGHD")
    private String thuocnoitruThanghd;
    @Column(name = "THUOCNOITRU_NAMHD")
    private String thuocnoitruNamhd;
    @Column(name = "THUOCNOITRU_TIENBNTRA")
    private Double thuocnoitruTienbntra;
    @Column(name = "THUOCNOITRU_DONGIATT", nullable = false)
    private int thuocnoitruDongiatt;
    @Column(name = "THUOCNOITRU_THANHTIEN", nullable = false)
    private int thuocnoitruThanhtien;
    @JoinColumn(name = "HSBA_KHOA", referencedColumnName = "HSBAKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaKhoa hsbaKhoa;
    @JoinColumn(name = "THUOCNOITRU_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thuocnoitruBacsi;
    @JoinColumn(name = "THUOCNOITRU_HANGSX", referencedColumnName = "DMNHASANXUAT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaSanXuat thuocnoitruHangsx;
    @JoinColumn(name = "THUOCNOITRU_KHO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa thuocnoitruKho;
    @JoinColumn(name = "THUOCNOITRU_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa thuocnoitruKhoa;
    @JoinColumn(name = "THUOCNOITRU_MAPHONG", referencedColumnName = "DTDMCLSBG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmClsBangGia thuocnoitruMaphong;
    @JoinColumn(name = "THUOCNOITRU_MATHUOC", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc thuocnoitruMathuoc;
    @JoinColumn(name = "THUOCNOITRU_NGUON", referencedColumnName = "DMNCT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonChuongTrinh thuocnoitruNguon;
    @JoinColumn(name = "THUOCNOITRU_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thuocnoitruNhanviencn;
    @JoinColumn(name = "THUOCNOITRU_PHANLOAI", referencedColumnName = "DMPHANLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmPhanLoaiThuoc thuocnoitruPhanloai;
    @JoinColumn(name = "THUOCNOITRU_QUOCGIA", referencedColumnName = "DMQUOCGIA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmQuocGia thuocnoitruQuocgia;
    @JoinColumn(name = "THUOCNOITRU_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thuocnoitruThungan;
    
    @JoinColumn(name = "THUOCDONGY_MASO", referencedColumnName = "THUOCDONGY_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThuocDongYNoiTru thuocdongyNoiTru;
    @Column(name = "DMBAITHUOC_MASO")
    private Integer dmbaithuocMaso;
    @JoinColumn(name = "DMDOITUONG_MASO", referencedColumnName = "DMDOITUONG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDoiTuong dmdoituongMaso;

    @Column(name = "THUOCNOITRU_CHIDANMA")
    private String thuocnoitruMaChidan;

    @Column(name = "THUOCNOITRU_CHIDAN")
    private String thuocnoitruTenchidan;
    public ThuocNoiTru() {
    }

    public ThuocNoiTru(Integer thuocnoitruMa) {
        this.thuocnoitruMa = thuocnoitruMa;
    }

    public Integer getThuocnoitruMa() {
        return thuocnoitruMa;
    }

    public void setThuocnoitruMa(Integer thuocnoitruMa) {
        this.thuocnoitruMa = thuocnoitruMa;
    }

    public String getThuocnoitruMaphieu() {
        return thuocnoitruMaphieu;
    }

    public void setThuocnoitruMaphieu(String thuocnoitruMaphieu) {
        this.thuocnoitruMaphieu = thuocnoitruMaphieu;
    }

    public String getThuocnoitruPhong() {
        return thuocnoitruPhong;
    }

    public void setThuocnoitruPhong(String thuocnoitruPhong) {
        this.thuocnoitruPhong = thuocnoitruPhong;
    }

    public String getThuocnoitruBosung() {
        return thuocnoitruBosung;
    }

    public void setThuocnoitruBosung(String thuocnoitruBosung) {
        this.thuocnoitruBosung = thuocnoitruBosung;
    }

    public Date getThuocnoitruNgaygio() {
        return thuocnoitruNgaygio;
    }

    public void setThuocnoitruNgaygio(Date thuocnoitruNgaygio) {
        this.thuocnoitruNgaygio = thuocnoitruNgaygio;
    }

    public String getThuocnoitruLoai() {
        return thuocnoitruLoai;
    }

    public void setThuocnoitruLoai(String thuocnoitruLoai) {
        this.thuocnoitruLoai = thuocnoitruLoai;
    }

    public Boolean getThuocnoitruYeucau() {
        return thuocnoitruYeucau;
    }

    public void setThuocnoitruYeucau(Boolean thuocnoitruYeucau) {
        this.thuocnoitruYeucau = thuocnoitruYeucau;
    }

    public Boolean getThuocnoitruMien() {
        return thuocnoitruMien;
    }

    public void setThuocnoitruMien(Boolean thuocnoitruMien) {
        this.thuocnoitruMien = thuocnoitruMien;
    }

    public Boolean getThuocnoitruNhi() {
        return thuocnoitruNhi;
    }

    public void setThuocnoitruNhi(Boolean thuocnoitruNhi) {
        this.thuocnoitruNhi = thuocnoitruNhi;
    }

    public Boolean getThuocnoitruLao() {
        return thuocnoitruLao;
    }

    public void setThuocnoitruLao(Boolean thuocnoitruLao) {
        this.thuocnoitruLao = thuocnoitruLao;
    }

    public Double getThuocnoitruSoluong() {
        return thuocnoitruSoluong;
    }

    public void setThuocnoitruSoluong(Double thuocnoitruSoluong) {
        this.thuocnoitruSoluong = thuocnoitruSoluong;
    }

    public Double getThuocnoitruTra() {
        return thuocnoitruTra;
    }

    public void setThuocnoitruTra(Double thuocnoitruTra) {
        this.thuocnoitruTra = thuocnoitruTra;
    }

    public Double getThuocnoitruDongia() {
        return thuocnoitruDongia;
    }

    public void setThuocnoitruDongia(Double thuocnoitruDongia) {
        this.thuocnoitruDongia = thuocnoitruDongia;
    }

    public Double getThuocnoitruDongiabh() {
        return thuocnoitruDongiabh;
    }

    public void setThuocnoitruDongiabh(Double thuocnoitruDongiabh) {
        this.thuocnoitruDongiabh = thuocnoitruDongiabh;
    }

    public Date getThuocnoitruNgaytt() {
        return thuocnoitruNgaytt;
    }

    public void setThuocnoitruNgaytt(Date thuocnoitruNgaytt) {
        this.thuocnoitruNgaytt = thuocnoitruNgaytt;
    }

    public Short getThuocnoitruCum() {
        return thuocnoitruCum;
    }

    public void setThuocnoitruCum(Short thuocnoitruCum) {
        this.thuocnoitruCum = thuocnoitruCum;
    }

    public Short getThuocnoitruLanlinh() {
        return thuocnoitruLanlinh;
    }

    public void setThuocnoitruLanlinh(Short thuocnoitruLanlinh) {
        this.thuocnoitruLanlinh = thuocnoitruLanlinh;
    }

    public String getThuocnoitruSodangky() {
        return thuocnoitruSodangky;
    }

    public void setThuocnoitruSodangky(String thuocnoitruSodangky) {
        this.thuocnoitruSodangky = thuocnoitruSodangky;
    }

    public Boolean getThuocnoitruKhongthu() {
        return thuocnoitruKhongthu;
    }

    public void setThuocnoitruKhongthu(Boolean thuocnoitruKhongthu) {
        this.thuocnoitruKhongthu = thuocnoitruKhongthu;
    }

    public String getThuocnoitruStatus() {
        return thuocnoitruStatus;
    }

    public void setThuocnoitruStatus(String thuocnoitruStatus) {
        this.thuocnoitruStatus = thuocnoitruStatus;
    }

    public Date getThuocnoitruNgayinlinh() {
        return thuocnoitruNgayinlinh;
    }

    public void setThuocnoitruNgayinlinh(Date thuocnoitruNgayinlinh) {
        this.thuocnoitruNgayinlinh = thuocnoitruNgayinlinh;
    }

    public Short getThuocnoitruLanintra() {
        return thuocnoitruLanintra;
    }

    public void setThuocnoitruLanintra(Short thuocnoitruLanintra) {
        this.thuocnoitruLanintra = thuocnoitruLanintra;
    }

    public Date getThuocnoitruNgayintra() {
        return thuocnoitruNgayintra;
    }

    public void setThuocnoitruNgayintra(Date thuocnoitruNgayintra) {
        this.thuocnoitruNgayintra = thuocnoitruNgayintra;
    }

    public Double getThuocnoitruDongianhap() {
        return thuocnoitruDongianhap;
    }

    public void setThuocnoitruDongianhap(Double thuocnoitruDongianhap) {
        this.thuocnoitruDongianhap = thuocnoitruDongianhap;
    }

    public Double getThuocnoitruDongiaban() {
        return thuocnoitruDongiaban;
    }

    public void setThuocnoitruDongiaban(Double thuocnoitruDongiaban) {
        this.thuocnoitruDongiaban = thuocnoitruDongiaban;
    }

    public Date getThuocnoitruNgaygiocn() {
        return thuocnoitruNgaygiocn;
    }

    public void setThuocnoitruNgaygiocn(Date thuocnoitruNgaygiocn) {
        this.thuocnoitruNgaygiocn = thuocnoitruNgaygiocn;
    }

    public Integer getThuocnoitruStt() {
        return thuocnoitruStt;
    }

    public void setThuocnoitruStt(Integer thuocnoitruStt) {
        this.thuocnoitruStt = thuocnoitruStt;
    }

    public Integer getThuocnoitruTutrucPdt() {
        return thuocnoitruTutrucPdt;
    }

    public void setThuocnoitruTutrucPdt(Integer thuocnoitruTutrucPdt) {
        this.thuocnoitruTutrucPdt = thuocnoitruTutrucPdt;
    }

    public String getThuocnoitruMalk() {
        return thuocnoitruMalk;
    }

    public void setThuocnoitruMalk(String thuocnoitruMalk) {
        this.thuocnoitruMalk = thuocnoitruMalk;
    }

    public String getThuocnoitruNamnhap() {
        return thuocnoitruNamnhap;
    }

    public void setThuocnoitruNamnhap(String thuocnoitruNamnhap) {
        this.thuocnoitruNamnhap = thuocnoitruNamnhap;
    }

    public String getThuocnoitruNgayhd() {
        return thuocnoitruNgayhd;
    }

    public void setThuocnoitruNgayhd(String thuocnoitruNgayhd) {
        this.thuocnoitruNgayhd = thuocnoitruNgayhd;
    }

    public String getThuocnoitruThanghd() {
        return thuocnoitruThanghd;
    }

    public void setThuocnoitruThanghd(String thuocnoitruThanghd) {
        this.thuocnoitruThanghd = thuocnoitruThanghd;
    }

    public String getThuocnoitruNamhd() {
        return thuocnoitruNamhd;
    }

    public void setThuocnoitruNamhd(String thuocnoitruNamhd) {
        this.thuocnoitruNamhd = thuocnoitruNamhd;
    }
    
    public Double getThuocnoitruTienbntra() {
        return thuocnoitruTienbntra;
    }

    public void setThuocnoitruTienbntra(Double thuocnoitruTienbntra) {
        this.thuocnoitruTienbntra = thuocnoitruTienbntra;
    }
    public int getThuocnoitruDongiatt() {
        return thuocnoitruDongiatt;
    }

    public void setThuocnoitruDongiatt(int thuocnoitruDongiatt) {
        this.thuocnoitruDongiatt = thuocnoitruDongiatt;
    }

    public int getThuocnoitruThanhtien() {
        return thuocnoitruThanhtien;
    }

    public void setThuocnoitruThanhtien(int thuocnoitruThanhtien) {
        this.thuocnoitruThanhtien = thuocnoitruThanhtien;
    }
    public DtDmNhanVien getThuocnoitruBacsi(boolean create) {
        if (create && thuocnoitruBacsi == null) {
            thuocnoitruBacsi = new DtDmNhanVien();
        }
        return thuocnoitruBacsi;
    }

    public DtDmNhanVien getThuocnoitruBacsi() {
        return thuocnoitruBacsi;
    }

    public void setThuocnoitruBacsi(DtDmNhanVien thuocnoitruBacsi) {
        this.thuocnoitruBacsi = thuocnoitruBacsi;
    }

    public DmNhaSanXuat getThuocnoitruHangsx(boolean create) {
        if (create && thuocnoitruHangsx == null) {
            thuocnoitruHangsx = new DmNhaSanXuat();
        }
        return thuocnoitruHangsx;
    }

    public DmNhaSanXuat getThuocnoitruHangsx() {
        return thuocnoitruHangsx;
    }

    public void setThuocnoitruHangsx(DmNhaSanXuat thuocnoitruHangsx) {
        this.thuocnoitruHangsx = thuocnoitruHangsx;
    }

    public DmKhoa getThuocnoitruKho(boolean create) {
        if (create && thuocnoitruKho == null) {
            thuocnoitruKho = new DmKhoa();
        }
        return thuocnoitruKho;
    }

    public DmKhoa getThuocnoitruKho() {
        return thuocnoitruKho;
    }

    public void setThuocnoitruKho(DmKhoa thuocnoitruKho) {
        this.thuocnoitruKho = thuocnoitruKho;
    }

    public DmKhoa getThuocnoitruKhoa(boolean create) {
        if (create && thuocnoitruKhoa == null) {
            thuocnoitruKhoa = new DmKhoa();
        }
        return thuocnoitruKhoa;
    }

    public DmKhoa getThuocnoitruKhoa() {
        return thuocnoitruKhoa;
    }

    public void setThuocnoitruKhoa(DmKhoa thuocnoitruKhoa) {
        this.thuocnoitruKhoa = thuocnoitruKhoa;
    }

    public DtDmClsBangGia getThuocnoitruMaphong(boolean create) {
        if (create && thuocnoitruMaphong == null) {
            thuocnoitruMaphong = new DtDmClsBangGia();
        }
        return thuocnoitruMaphong;
    }

    public DtDmClsBangGia getThuocnoitruMaphong() {
        return thuocnoitruMaphong;
    }

    public void setThuocnoitruMaphong(DtDmClsBangGia thuocnoitruMaphong) {
        this.thuocnoitruMaphong = thuocnoitruMaphong;
    }

    public DmThuoc getThuocnoitruMathuoc(boolean create) {
        if (create && thuocnoitruMathuoc == null) {
            thuocnoitruMathuoc = new DmThuoc();
        }
        return thuocnoitruMathuoc;
    }

    public DmThuoc getThuocnoitruMathuoc() {
        return thuocnoitruMathuoc;
    }

    public void setThuocnoitruMathuoc(DmThuoc thuocnoitruMathuoc) {
        this.thuocnoitruMathuoc = thuocnoitruMathuoc;
    }

    public DmNguonChuongTrinh getThuocnoitruNguon(boolean create) {
        if (create && thuocnoitruNguon == null) {
            thuocnoitruNguon = new DmNguonChuongTrinh();
        }
        return thuocnoitruNguon;
    }

    public DmNguonChuongTrinh getThuocnoitruNguon() {
        return thuocnoitruNguon;
    }

    public void setThuocnoitruNguon(DmNguonChuongTrinh thuocnoitruNguon) {
        this.thuocnoitruNguon = thuocnoitruNguon;
    }

    public DtDmNhanVien getThuocnoitruNhanviencn(boolean create) {
        if (create && thuocnoitruNhanviencn == null) {
            thuocnoitruNhanviencn = new DtDmNhanVien();
        }
        return thuocnoitruNhanviencn;
    }

    public DtDmNhanVien getThuocnoitruNhanviencn() {
        return thuocnoitruNhanviencn;
    }

    public void setThuocnoitruNhanviencn(DtDmNhanVien thuocnoitruNhanviencn) {
        this.thuocnoitruNhanviencn = thuocnoitruNhanviencn;
    }

    public DmPhanLoaiThuoc getThuocnoitruPhanloai(boolean create) {
        if (create && thuocnoitruPhanloai == null) {
            thuocnoitruPhanloai = new DmPhanLoaiThuoc();
        }
        return thuocnoitruPhanloai;
    }

    public DmPhanLoaiThuoc getThuocnoitruPhanloai() {
        return thuocnoitruPhanloai;
    }

    public void setThuocnoitruPhanloai(DmPhanLoaiThuoc thuocnoitruPhanloai) {
        this.thuocnoitruPhanloai = thuocnoitruPhanloai;
    }

    public DmQuocGia getThuocnoitruQuocgia(boolean create) {
        if (create && thuocnoitruQuocgia == null) {
            thuocnoitruQuocgia = new DmQuocGia();
        }
        return thuocnoitruQuocgia;
    }

    public DmQuocGia getThuocnoitruQuocgia() {
        return thuocnoitruQuocgia;
    }

    public void setThuocnoitruQuocgia(DmQuocGia thuocnoitruQuocgia) {
        this.thuocnoitruQuocgia = thuocnoitruQuocgia;
    }

    public DtDmNhanVien getThuocnoitruThungan(boolean create) {
        if (create && thuocnoitruThungan == null) {
            thuocnoitruThungan = new DtDmNhanVien();
        }
        return thuocnoitruThungan;
    }

    public DtDmNhanVien getThuocnoitruThungan() {
        return thuocnoitruThungan;
    }

    public void setThuocnoitruThungan(DtDmNhanVien thuocnoitruThungan) {
        this.thuocnoitruThungan = thuocnoitruThungan;
    }

    public ThuocDongYNoiTru getThuocdongyNoiTru(boolean create) {
        if (create && thuocdongyNoiTru == null) {
            thuocdongyNoiTru = new ThuocDongYNoiTru();
        }
        return thuocdongyNoiTru;
    }

    public ThuocDongYNoiTru getThuocdongyNoiTru() {
        return thuocdongyNoiTru;
    }

    public void setThuocdongyNoiTru(ThuocDongYNoiTru thuocdongyNoiTru) {
        this.thuocdongyNoiTru = thuocdongyNoiTru;
    }

    public DmDoiTuong getDmdoituongMaso() {
        return dmdoituongMaso;
    }

    public DmDoiTuong getDmdoituongMaso(boolean create) {
        if (create && dmdoituongMaso == null) {
            dmdoituongMaso = new DmDoiTuong();
        }
        return dmdoituongMaso;
    }

    public void setDmdoituongMaso(DmDoiTuong dmdoituongMaso) {
        this.dmdoituongMaso = dmdoituongMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (thuocnoitruMa != null ? thuocnoitruMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThuocNoiTru)) {
            return false;
        }
        ThuocNoiTru other = (ThuocNoiTru) object;
        if ((this.thuocnoitruMa == null && other.thuocnoitruMa != null) || (this.thuocnoitruMa != null && !this.thuocnoitruMa.equals(other.thuocnoitruMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.ThuocNoiTru[thuocnoitruMa=" + thuocnoitruMa + "]";
    }

    public Boolean getThuocnoitruNDM() {
        return thuocnoitruNDM;
    }

    public void setThuocnoitruNDM(Boolean thuocnoitruNDM) {
        this.thuocnoitruNDM = thuocnoitruNDM;
    }

    public String getThuocnoitruMaPhieuDT() {
        return thuocnoitruMaPhieuDT;
    }

    public void setThuocnoitruMaPhieuDT(String thuocnoitruMaPhieuDT) {
        this.thuocnoitruMaPhieuDT = thuocnoitruMaPhieuDT;
    }

    public HsbaKhoa getHsbaKhoa() {
        return hsbaKhoa;
    }

    public void setHsbaKhoa(HsbaKhoa hsbaKhoa) {
        this.hsbaKhoa = hsbaKhoa;
    }

    public HsbaKhoa getHsbaKhoa(boolean create) {
        if (create && hsbaKhoa == null) {
            hsbaKhoa = new HsbaKhoa();
        }
        return hsbaKhoa;
    }

    public String getThuocnoitruMaphieupdttra() {
        return thuocnoitruMaphieupdttra;
    }

    public void setThuocnoitruMaphieupdttra(String thuocnoitruMaphieupdttra) {
        this.thuocnoitruMaphieupdttra = thuocnoitruMaphieupdttra;
    }

    public Integer getDmbaithuocMaso() {
        return dmbaithuocMaso;
    }

    public void setDmbaithuocMaso(Integer dmbaithuocMaso) {
        this.dmbaithuocMaso = dmbaithuocMaso;
    }

    public String getThuocnoitruMaChidan() {
        return thuocnoitruMaChidan;
    }

    public void setThuocnoitruMaChidan(String thuocnoitruMaChidan) {
        this.thuocnoitruMaChidan = thuocnoitruMaChidan;
    }

    public String getThuocnoitruTenchidan() {
        return thuocnoitruTenchidan;
    }

    public void setThuocnoitruTenchidan(String thuocnoitruTenchidan) {
        this.thuocnoitruTenchidan = thuocnoitruTenchidan;
    }
}