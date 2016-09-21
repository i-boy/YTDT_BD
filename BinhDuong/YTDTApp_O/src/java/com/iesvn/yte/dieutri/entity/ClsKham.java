/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmDoiTuong;
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
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CLS_KHAM")
@NamedQueries({})
public class ClsKham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLS_KHAM_CLSKHAM_MA")
    @SequenceGenerator(name = "CLS_KHAM_CLSKHAM_MA", sequenceName = "CLS_KHAM_CLSKHAM_MA_SEQ", allocationSize = 1)
    @Column(name = "CLSKHAM_MA", nullable = false)
    private Integer clskhamMa;
    @Column(name = "CLSKHAM_MAPHIEU")
    private String clskhamMaphieu;
    @Column(name = "CLSKHAM_MAPHIEUK")
    private String clskhamMaphieuk;
    @Column(name = "CLSKHAM_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clskhamNgaygio;
    @Column(name = "CLSKHAM_NDM")
    private Boolean clskhamNdm;
    @Column(name = "CLSKHAM_LOAI")
    private String clskhamLoai;
    @Column(name = "CLSKHAM_KTCAO")
    private Boolean clskhamKtcao;
    @Column(name = "CLSKHAM_YEUCAU")
    private Boolean clskhamYeucau;
    @Column(name = "CLSKHAM_MIEN")
    private Boolean clskhamMien;
    @Column(name = "CLSKHAM_CHEDO")
    private String clskhamChedo;
    @Column(name = "CLSKHAM_DICHVU")
    private Boolean clskhamDichvu;
    @Column(name = "CLSKHAM_LAN")
    private Short clskhamLan;
    @Column(name = "CLSKHAM_TRA")
    private Short clskhamTra;
    @Column(name = "CLSKHAM_PHANDV")
    private Double clskhamPhandv;
    @Column(name = "CLSKHAM_DONGIA")
    private Double clskhamDongia;
    @Column(name = "CLSKHAM_DONGIABH")
    private Double clskhamDongiabh;
    @Column(name = "CLSKHAM_DONGIABNTRA")
    private Double clskhamDongiabntra;
    @Column(name = "CLSKHAM_KHONGTHU")
    private Boolean clskhamKhongthu;
    @Column(name = "CLSKHAM_TYLEBHKTC")
    private Short clskhamTylebhktc;
    @Column(name = "CLSKHAM_NGAYGIOTT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clskhamNgaygiott;
    @Column(name = "CLSKHAM_NGAYGIOHOANTHU")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clskhamNgaygioHoanThu;
    @Column(name = "CLSKHAM_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clskhamNgaygiocn;
    @Column(name = "CLSKHAM_CHANDOAN")
    private String clskhamChandoan;
    @Column(name = "CLSKHAM_KETQUA")
    private String clskhamKetqua;
    @Column(name = "CLSKHAM_DUONGTINH")
    private String clskhamDuongtinh;
    @Column(name = "CLSKHAM_TIEUBAN")
    private Short clskhamTieuban;
    @Column(name = "CLSKHAM_LAM")
    private Short clskhamLam;
    @Column(name = "CLSKHAM_ORD")
    private Short clskhamOrd;
    @Column(name = "CLSKHAM_STATUS")
    private String clskhamStatus;
    @Column(name = "CLSKHAM_XETGIAM")
    private Boolean clskhamXetgiam;
    @Column(name = "CLSKHAM_TYLEGIAM")
    private Short clskhamTylegiam;
    @Column(name = "CLSKHAM_INTEM")
    private Boolean clskhamIntem;
    @Column(name = "CLSKHAM_MAPHIEUD")
    private String clskhamMaphieud;
    @Column(name = "CLSKHAM_KYHIEU")
    private String clskhamKyHieu;
    @Column(name = "CLSKHAM_QUYEN")
    private String clskhamQuyen;
    @Column(name = "CLSKHAM_BIENLAI")
    private String clskhamBienLai;
    @Column(name = "CLSKHAM_KYHIEUHOAN")
    private String clskhamKyHieuHoan;
    @Column(name = "CLSKHAM_QUYENHOAN")
    private String clskhamQuyenHoan;
    @Column(name = "CLSKHAM_BIENLAIHOAN")
    private String clskhamBienLaiHoan;
    @Column(name = "CLSKHAM_DATT")
    private Boolean clskhamDatt;
    @Column(name = "CLSKHAM_TH")
    private Boolean clskhamTh;
    @Lob
    @Column(name = "CLSKHAM_IMG")
    private byte[] clskhamImg;
    @Column(name = "CLSKHAM_NOIDUNGTHU")
    private String clskhamNoidungthu;
    @Column(name = "CLSKHAM_DULIEUHINHANH")
    private String clskhamDulieuhinhanh;
    @Column(name = "CLSKHAM_GHICHU")
    private String clskhamGhichu;
    @JoinColumn(name = "CLSKHAM_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien clskhamThungan;
    @JoinColumn(name = "CLSKHAM_THUNGANHOAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien clskhamThunganHoan;
    @JoinColumn(name = "CLSKHAM_THUCHIEN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien clskhamThuchien;
    @JoinColumn(name = "CLSKHAM_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa clskhamKhoa;
    @JoinColumn(name = "CLSKHAM_MAHANG", referencedColumnName = "DTDMCLSBG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmClsBangGia clskhamMahang;
    @JoinColumn(name = "CLSKHAM_MALOAI", referencedColumnName = "DTDMCLS_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCls clskhamMaloai;
    @JoinColumn(name = "CLSKHAM_CHEDOTT", referencedColumnName = "DMDOITUONG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDoiTuong clskhamChedott;
    @JoinColumn(name = "CLSKHAM_MABS", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien clskhamMabs;
    @JoinColumn(name = "CLSKHAM_KHOA2", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa clskhamKhoa2;
    @JoinColumn(name = "CLSKHAM_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien clskhamNhanviencn;
    @JoinColumn(name = "CLSKHAM_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham clskhamThamkham;
    @JoinColumn(name = "CLSKHAM_CUM", referencedColumnName = "DTDMCUM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCum clskhamCum;
    @JoinColumn(name = "CLSKHAM_CUMHOAN", referencedColumnName = "DTDMCUM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCum clskhamCumHoan;
    @Column(name = "CLSKHAM_HH_KETLUAN")
    private String clskhamHHKetluan;
    @Column(name = "CLSKHAM_HH_DONVIMAU")
    private String clskhamHHDonvimau;
    @Column(name = "CLSKHAM_HH_NHOMMAUBN")
    private String clskhamHHNhommaubn;
    @Column(name = "CLSKHAM_HH_NHOMMAUDV")
    private String clskhamHHNhommaudv;
    @Column(name = "CLSKHAM_HH_CHECKTIEUCAU")
    private Boolean clskhamHHChecktieucau;
    @Column(name = "CLSKHAM_HH_CHECKHONGCAU")
    private Boolean clskhamHHCheckhongcau;
    @Column(name = "CLSKHAM_HH_CHECKPLASMA")
    private Boolean clskhamHHCheckplasma;
    @Column(name = "CLSKHAM_HH_CHECKMAULANG")
    private Boolean clskhamHHCheckmaulang;
    @Column(name = "CLSKHAM_HH_ANTI_A")
    private String clskhamHHAntiA;
    @Column(name = "CLSKHAM_HH_ANTI_B")
    private String clskhamHHAntiB;
    @Column(name = "CLSKHAM_HH_ANTI_D")
    private String clskhamHHAntiD;
    @Column(name = "CLSKHAM_HH_ENZ")
    private String clskhamHHEnz;
    @Column(name = "CLSKHAM_HH_AHG")
    private String clskhamHHAhg;
    @Column(name = "CLSKHAM_LOAI2")
    private String clskhamLoai2;
    @Column(name = "CLSKHAM_SOML")
    private Integer clskhamSoml;
    @Column(name = "CLSKHAM_DONGIATT", nullable = false)
    private int clskhamDongiatt;
    @Column(name = "CLSKHAM_THANHTIEN", nullable = false)
    private int clskhamThanhtien;
    public ClsKham() {
    }
    
    public ClsKham(Integer clskhamMa) {
        this.clskhamMa = clskhamMa;
    }
    
    public String getClskhamHHAhg() {
        return clskhamHHAhg;
    }
    
    public void setClskhamHHAhg(String clskhamHHAhg) {
        this.clskhamHHAhg = clskhamHHAhg;
    }

    public String getClskhamHHAntiA() {
        return clskhamHHAntiA;
    }

    public void setClskhamHHAntiA(String clskhamHHAntiA) {
        this.clskhamHHAntiA = clskhamHHAntiA;
    }

    public String getClskhamHHAntiB() {
        return clskhamHHAntiB;
    }

    public void setClskhamHHAntiB(String clskhamHHAntiB) {
        this.clskhamHHAntiB = clskhamHHAntiB;
    }

    public String getClskhamHHAntiD() {
        return clskhamHHAntiD;
    }

    public void setClskhamHHAntiD(String clskhamHHAntiD) {
        this.clskhamHHAntiD = clskhamHHAntiD;
    }

    public String getClskhamHHEnz() {
        return clskhamHHEnz;
    }

    public void setClskhamHHEnz(String clskhamHHEnz) {
        this.clskhamHHEnz = clskhamHHEnz;
    }

    public Boolean getClskhamHHCheckhongcau() {
        return clskhamHHCheckhongcau;
    }

    public void setClskhamHHCheckhongcau(Boolean clskhamHHCheckhongcau) {
        this.clskhamHHCheckhongcau = clskhamHHCheckhongcau;
    }

    public Boolean getClskhamHHCheckmaulang() {
        return clskhamHHCheckmaulang;
    }

    public void setClskhamHHCheckmaulang(Boolean clskhamHHCheckmaulang) {
        this.clskhamHHCheckmaulang = clskhamHHCheckmaulang;
    }

    public Boolean getClskhamHHCheckplasma() {
        return clskhamHHCheckplasma;
    }

    public void setClskhamHHCheckplasma(Boolean clskhamHHCheckplasma) {
        this.clskhamHHCheckplasma = clskhamHHCheckplasma;
    }

    public Boolean getClskhamHHChecktieucau() {
        return clskhamHHChecktieucau;
    }

    public void setClskhamHHChecktieucau(Boolean clskhamHHChecktieucau) {
        this.clskhamHHChecktieucau = clskhamHHChecktieucau;
    }

    public String getClskhamHHDonvimau() {
        return clskhamHHDonvimau;
    }

    public void setClskhamHHDonvimau(String clskhamHHDonvimau) {
        this.clskhamHHDonvimau = clskhamHHDonvimau;
    }

    public String getClskhamHHNhommaubn() {
        return clskhamHHNhommaubn;
    }

    public void setClskhamHHNhommaubn(String clskhamHHNhommaubn) {
        this.clskhamHHNhommaubn = clskhamHHNhommaubn;
    }

    public String getClskhamHHNhommaudv() {
        return clskhamHHNhommaudv;
    }

    public void setClskhamHHNhommaudv(String clskhamHHNhommaudv) {
        this.clskhamHHNhommaudv = clskhamHHNhommaudv;
    }

    public String getClskhamHHKetluan() {
        return clskhamHHKetluan;
    }

    public void setClskhamHHKetluan(String clskhamHHKetluan) {
        this.clskhamHHKetluan = clskhamHHKetluan;
    }

    public Integer getClskhamMa() {
        return clskhamMa;
    }

    public void setClskhamMa(Integer clskhamMa) {
        this.clskhamMa = clskhamMa;
    }

    public String getClskhamMaphieu() {
        return clskhamMaphieu;
    }

    public void setClskhamMaphieu(String clskhamMaphieu) {
        this.clskhamMaphieu = clskhamMaphieu;
    }

    public String getClskhamMaphieuk() {
        return clskhamMaphieuk;
    }

    public void setClskhamMaphieuk(String clskhamMaphieuk) {
        this.clskhamMaphieuk = clskhamMaphieuk;
    }

    public Date getClskhamNgaygio() {
        return clskhamNgaygio;
    }

    public void setClskhamNgaygio(Date clskhamNgaygio) {
        this.clskhamNgaygio = clskhamNgaygio;
    }

    public Boolean getClskhamNdm() {
        return clskhamNdm;
    }

    public void setClskhamNdm(Boolean clskhamNdm) {
        this.clskhamNdm = clskhamNdm;
    }

    public String getClskhamLoai() {
        return clskhamLoai;
    }

    public void setClskhamLoai(String clskhamLoai) {
        this.clskhamLoai = clskhamLoai;
    }

    public Boolean getClskhamKtcao() {
        return clskhamKtcao;
    }

    public void setClskhamKtcao(Boolean clskhamKtcao) {
        this.clskhamKtcao = clskhamKtcao;
    }

    public Boolean getClskhamYeucau() {
        return clskhamYeucau;
    }

    public void setClskhamYeucau(Boolean clskhamYeucau) {
        this.clskhamYeucau = clskhamYeucau;
    }

    public Boolean getClskhamMien() {
        return clskhamMien;
    }

    public void setClskhamMien(Boolean clskhamMien) {
        this.clskhamMien = clskhamMien;
    }

    public String getClskhamChedo() {
        return clskhamChedo;
    }

    public void setClskhamChedo(String clskhamChedo) {
        this.clskhamChedo = clskhamChedo;
    }

    public Boolean getClskhamDichvu() {
        return clskhamDichvu;
    }

    public void setClskhamDichvu(Boolean clskhamDichvu) {
        this.clskhamDichvu = clskhamDichvu;
    }

    public Short getClskhamLan() {
        return clskhamLan;
    }

    public void setClskhamLan(Short clskhamLan) {
        this.clskhamLan = clskhamLan;
    }

    public Short getClskhamTra() {
        return clskhamTra;
    }

    public void setClskhamTra(Short clskhamTra) {
        this.clskhamTra = clskhamTra;
    }

    public Double getClskhamPhandv() {
        return clskhamPhandv;
    }

    public void setClskhamPhandv(Double clskhamPhandv) {
        this.clskhamPhandv = clskhamPhandv;
    }

    public Double getClskhamDongia() {
        return clskhamDongia;
    }

    public void setClskhamDongia(Double clskhamDongia) {
        this.clskhamDongia = clskhamDongia;
    }

    public Double getClskhamDongiabh() {
        return clskhamDongiabh;
    }

    public void setClskhamDongiabh(Double clskhamDongiabh) {
        this.clskhamDongiabh = clskhamDongiabh;
    }

    public Boolean getClskhamKhongthu() {
        return clskhamKhongthu;
    }

    public void setClskhamKhongthu(Boolean clskhamKhongthu) {
        this.clskhamKhongthu = clskhamKhongthu;
    }

    public Short getClskhamTylebhktc() {
        return clskhamTylebhktc;
    }

    public void setClskhamTylebhktc(Short clskhamTylebhktc) {
        this.clskhamTylebhktc = clskhamTylebhktc;
    }

    public Date getClskhamNgaygiott() {
        return clskhamNgaygiott;
    }

    public void setClskhamNgaygiott(Date clskhamNgaygiott) {
        this.clskhamNgaygiott = clskhamNgaygiott;
    }

    public DtDmCum getClskhamCum() {
        return clskhamCum;
    }

    public void setClskhamCum(DtDmCum clskhamCum) {
        this.clskhamCum = clskhamCum;
    }

    public Date getClskhamNgaygiocn() {
        return clskhamNgaygiocn;
    }

    public void setClskhamNgaygiocn(Date clskhamNgaygiocn) {
        this.clskhamNgaygiocn = clskhamNgaygiocn;
    }

    public String getClskhamChandoan() {
        return clskhamChandoan;
    }

    public void setClskhamChandoan(String clskhamChandoan) {
        this.clskhamChandoan = clskhamChandoan;
    }

    public String getClskhamKetqua() {
        return clskhamKetqua;
    }

    public void setClskhamKetqua(String clskhamKetqua) {
        this.clskhamKetqua = clskhamKetqua;
    }

    public String getClskhamDuongtinh() {
        return clskhamDuongtinh;
    }

    public void setClskhamDuongtinh(String clskhamDuongtinh) {
        this.clskhamDuongtinh = clskhamDuongtinh;
    }

    public Short getClskhamTieuban() {
        return clskhamTieuban;
    }

    public void setClskhamTieuban(Short clskhamTieuban) {
        this.clskhamTieuban = clskhamTieuban;
    }

    public Short getClskhamLam() {
        return clskhamLam;
    }

    public void setClskhamLam(Short clskhamLam) {
        this.clskhamLam = clskhamLam;
    }

    public Short getClskhamOrd() {
        return clskhamOrd;
    }

    public void setClskhamOrd(Short clskhamOrd) {
        this.clskhamOrd = clskhamOrd;
    }

    public String getClskhamStatus() {
        return clskhamStatus;
    }

    public void setClskhamStatus(String clskhamStatus) {
        this.clskhamStatus = clskhamStatus;
    }

    public Boolean getClskhamXetgiam() {
        return clskhamXetgiam;
    }

    public void setClskhamXetgiam(Boolean clskhamXetgiam) {
        this.clskhamXetgiam = clskhamXetgiam;
    }

    public Short getClskhamTylegiam() {
        return clskhamTylegiam;
    }

    public void setClskhamTylegiam(Short clskhamTylegiam) {
        this.clskhamTylegiam = clskhamTylegiam;
    }

    public Boolean getClskhamIntem() {
        return clskhamIntem;
    }

    public void setClskhamIntem(Boolean clskhamIntem) {
        this.clskhamIntem = clskhamIntem;
    }

    public String getClskhamMaphieud() {
        return clskhamMaphieud;
    }

    public void setClskhamMaphieud(String clskhamMaphieud) {
        this.clskhamMaphieud = clskhamMaphieud;
    }

    public Boolean getClskhamDatt() {
        return clskhamDatt;
    }

    public void setClskhamDatt(Boolean clskhamDatt) {
        this.clskhamDatt = clskhamDatt;
    }

    public DtDmNhanVien getClskhamThungan(boolean create) {
        if (create && clskhamThungan == null) {
            clskhamThungan = new DtDmNhanVien();
        }
        return clskhamThungan;
    }

    public DtDmNhanVien getClskhamThungan() {
        return clskhamThungan;
    }

    public void setClskhamThungan(DtDmNhanVien clskhamThungan) {
        this.clskhamThungan = clskhamThungan;
    }

    public DtDmNhanVien getClskhamThuchien(boolean create) {
        if (create && clskhamThuchien == null) {
            clskhamThuchien = new DtDmNhanVien();
        }
        return clskhamThuchien;
    }

    public DtDmNhanVien getClskhamThuchien() {
        return clskhamThuchien;
    }

    public void setClskhamThuchien(DtDmNhanVien clskhamThuchien) {
        this.clskhamThuchien = clskhamThuchien;
    }

    public DmKhoa getClskhamKhoa(boolean create) {
        if (create && clskhamKhoa == null) {
            clskhamKhoa = new DmKhoa();
        }
        return clskhamKhoa;
    }

    public DmKhoa getClskhamKhoa() {
        return clskhamKhoa;
    }

    public void setClskhamKhoa(DmKhoa clskhamKhoa) {
        this.clskhamKhoa = clskhamKhoa;
    }

    public DtDmClsBangGia getClskhamMahang(boolean create) {
        if (create && clskhamMahang == null) {
            clskhamMahang = new DtDmClsBangGia();
        }
        return clskhamMahang;
    }

    public DtDmClsBangGia getClskhamMahang() {
        return clskhamMahang;
    }

    public void setClskhamMahang(DtDmClsBangGia clskhamMahang) {
        this.clskhamMahang = clskhamMahang;
    }

    public DtDmCls getClskhamMaloai(boolean create) {
        if (create && clskhamMaloai == null) {
            clskhamMaloai = new DtDmCls();
        }
        return clskhamMaloai;
    }

    public DtDmCls getClskhamMaloai() {
        return clskhamMaloai;
    }

    public void setClskhamMaloai(DtDmCls clskhamMaloai) {
        this.clskhamMaloai = clskhamMaloai;
    }

    public DmDoiTuong getClskhamChedott(boolean create) {
        if (create && clskhamChedott == null) {
            clskhamChedott = new DmDoiTuong();
        }
        return clskhamChedott;
    }

    public DmDoiTuong getClskhamChedott() {
        return clskhamChedott;
    }

    public void setClskhamChedott(DmDoiTuong clskhamChedott) {
        this.clskhamChedott = clskhamChedott;
    }

    public DtDmNhanVien getClskhamMabs(boolean create) {
        if (create && clskhamMabs == null) {
            clskhamMabs = new DtDmNhanVien();
        }
        return clskhamMabs;
    }

    public DtDmNhanVien getClskhamMabs() {
        return clskhamMabs;
    }

    public void setClskhamMabs(DtDmNhanVien clskhamMabs) {
        this.clskhamMabs = clskhamMabs;
    }

    public DmKhoa getClskhamKhoa2(boolean create) {
        if (create && clskhamKhoa2 == null) {
            clskhamKhoa2 = new DmKhoa();
        }
        return clskhamKhoa2;
    }

    public DmKhoa getClskhamKhoa2() {
        return clskhamKhoa2;
    }

    public void setClskhamKhoa2(DmKhoa clskhamKhoa2) {
        this.clskhamKhoa2 = clskhamKhoa2;
    }

    public DtDmNhanVien getClskhamNhanviencn(boolean create) {
        if (create && clskhamNhanviencn == null) {
            clskhamNhanviencn = new DtDmNhanVien();
        }
        return clskhamNhanviencn;
    }

    public DtDmNhanVien getClskhamNhanviencn() {
        return clskhamNhanviencn;
    }

    public void setClskhamNhanviencn(DtDmNhanVien clskhamNhanviencn) {
        this.clskhamNhanviencn = clskhamNhanviencn;
    }

    public ThamKham getClskhamThamkham(boolean create) {
        if (create && clskhamThamkham == null) {
            clskhamThamkham = new ThamKham();
        }
        return clskhamThamkham;
    }

    public ThamKham getClskhamThamkham() {
        return clskhamThamkham;
    }

    public void setClskhamThamkham(ThamKham clskhamThamkham) {
        this.clskhamThamkham = clskhamThamkham;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clskhamMa != null ? clskhamMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClsKham)) {
            return false;
        }
        ClsKham other = (ClsKham) object;
        if ((this.clskhamMa == null && other.clskhamMa != null) || (this.clskhamMa != null && !this.clskhamMa.equals(other.clskhamMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.ClsKham[clskhamMa=" + clskhamMa + "]";
    }

    public Date getClskhamNgaygioHoanThu() {
        return clskhamNgaygioHoanThu;
    }

    public void setClskhamNgaygioHoanThu(Date clskhamNgaygioHoanThu) {
        this.clskhamNgaygioHoanThu = clskhamNgaygioHoanThu;
    }

    public DtDmNhanVien getClskhamThunganHoan() {
        return clskhamThunganHoan;
    }

    public void setClskhamThunganHoan(DtDmNhanVien clskhamThunganHoan) {
        this.clskhamThunganHoan = clskhamThunganHoan;
    }

    public String getClskhamKyHieu() {
        return clskhamKyHieu;
    }

    public void setClskhamKyHieu(String clskhamKyHieu) {
        this.clskhamKyHieu = clskhamKyHieu;
    }

    public String getClskhamQuyen() {
        return clskhamQuyen;
    }

    public void setClskhamQuyen(String clskhamQuyen) {
        this.clskhamQuyen = clskhamQuyen;
    }

    public String getClskhamBienLai() {
        return clskhamBienLai;
    }

    public void setClskhamBienLai(String clskhamBienLai) {
        this.clskhamBienLai = clskhamBienLai;
    }

    public String getClskhamKyHieuHoan() {
        return clskhamKyHieuHoan;
    }

    public void setClskhamKyHieuHoan(String clskhamKyHieuHoan) {
        this.clskhamKyHieuHoan = clskhamKyHieuHoan;
    }

    public String getClskhamQuyenHoan() {
        return clskhamQuyenHoan;
    }

    public void setClskhamQuyenHoan(String clskhamQuyenHoan) {
        this.clskhamQuyenHoan = clskhamQuyenHoan;
    }

    public String getClskhamBienLaiHoan() {
        return clskhamBienLaiHoan;
    }

    public void setClskhamBienLaiHoan(String clskhamBienLaiHoan) {
        this.clskhamBienLaiHoan = clskhamBienLaiHoan;
    }

    public DtDmCum getClskhamCumHoan() {
        return clskhamCumHoan;
    }

    public void setClskhamCumHoan(DtDmCum clskhamCumHoan) {
        this.clskhamCumHoan = clskhamCumHoan;
    }

    public Double getClskhamDongiabntra() {
        return clskhamDongiabntra;
    }

    public void setClskhamDongiabntra(Double clskhamDongiabntra) {
        this.clskhamDongiabntra = clskhamDongiabntra;
    }

    /**
     * @return the clskhamTh
     */
    public Boolean getClskhamTh() {
        return clskhamTh;
    }

    /**
     * @param clskhamTh the clskhamTh to set
     */
    public void setClskhamTh(Boolean clskhamTh) {
        this.clskhamTh = clskhamTh;
    }

    /**
     * @return the clskhamImg
     */
    public byte[] getClskhamImg() {
        return clskhamImg;
    }

    /**
     * @param clskhamImg the clskhamImg to set
     */
    public void setClskhamImg(byte[] clskhamImg) {
        this.clskhamImg = clskhamImg;
    }

    /**
     * @return the clskhamNoidungthu
     */
    public String getClskhamNoidungthu() {
        return clskhamNoidungthu;
    }

    /**
     * @param clskhamNoidungthu the clskhamNoidungthu to set
     */
    public void setClskhamNoidungthu(String clskhamNoidungthu) {
        this.clskhamNoidungthu = clskhamNoidungthu;
    }

    public String getClskhamDulieuhinhanh() {
        return clskhamDulieuhinhanh;
    }

    public void setClskhamDulieuhinhanh(String clskhamDulieuhinhanh) {
        this.clskhamDulieuhinhanh = clskhamDulieuhinhanh;
    }

    public String getClskhamGhichu() {
        return clskhamGhichu;
    }

    public void setClskhamGhichu(String clskhamGhichu) {
        this.clskhamGhichu = clskhamGhichu;
    }
    public String getClskhamLoai2() {
        return clskhamLoai2;
    }

    public void setClskhamLoai2(String clskhamLoai2) {
        this.clskhamLoai2 = clskhamLoai2;
    }

    public Integer getClskhamSoml() {
        return clskhamSoml;
    }

    public void setClskhamSoml(Integer clskhamSoml) {
        this.clskhamSoml = clskhamSoml;
    }
    public int getClskhamDongiatt() {
        return clskhamDongiatt;
    }

    public void setClskhamDongiatt(int clskhamDongiatt) {
        this.clskhamDongiatt = clskhamDongiatt;
    }

    public int getClskhamThanhtien() {
        return clskhamThanhtien;
    }

    public void setClskhamThanhtien(int clskhamThanhtien) {
        this.clskhamThanhtien = clskhamThanhtien;
    }
}