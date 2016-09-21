/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_KHOA")
@NamedQueries({})
public class DmKhoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_KHOA_BC")
    @SequenceGenerator(name = "DM_KHOA_BC", sequenceName = "DM_KHOA_DMKHOA_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMKHOA_MASO", nullable = false)
    private Integer dmkhoaMaso;
    @Column(name = "DMKHOA_MA", nullable = false)
    private String dmkhoaMa;
    @Column(name = "DMKHOA_CON")
    private String dmkhoaCon;
    @Column(name = "DMKHOA_KYHIEU")
    private String dmkhoaKyhieu;
    @Column(name = "DMKHOA_MAKHOADA")
    private String dmkhoaMakhoada;
    @Column(name = "DMKHOA_TEN", nullable = false)
    private String dmkhoaTen;
    @Column(name = "DMKHOA_TEN2")
    private String dmkhoaTen2;
    @Column(name = "DMKHOA_TENBC")
    private String dmkhoaTenbc;
    @Column(name = "DMKHOA_CLS")
    private Boolean dmkhoaCls;
    @Column(name = "DMKHOA_DIENTHOAI")
    private String dmkhoaDienthoai;
    @Column(name = "DMKHOA_NOITRU")
    private Boolean dmkhoaNoitru;
    @Column(name = "DMKHOA_KHAM")
    private Boolean dmkhoaKham;
    @Column(name = "DMKHOA_NGOAITRU")
    private Boolean dmkhoaNgoaitru;
    @Column(name = "DMKHOA_KHAMICD")
    private Boolean dmkhoaKhamicd;
    @Column(name = "DMKHOA_KHAMCC")
    private Boolean dmkhoaKhamcc;
    @Column(name = "DMKHOA_SOTRET")
    private Boolean dmkhoaSotret;
    @Column(name = "DMKHOA_PHTHUAT")
    private Boolean dmkhoaPhthuat;
    @Column(name = "DMKHOA_NHAPPT")
    private Boolean dmkhoaNhappt;
    @Column(name = "DMKHOA_GIUONGKH")
    private Integer dmkhoaGiuongkh;
    @Column(name = "DMKHOA_GIUONGTK")
    private Integer dmkhoaGiuongtk;
    @Column(name = "DMKHOA_YEUCAU")
    private Boolean dmkhoaYeucau;
    @Column(name = "DMKHOA_CAPMAU")
    private Boolean dmkhoaCapmau;
    @Column(name = "DMKHOA_DUOC")
    private Boolean dmkhoaDuoc;
    @Column(name = "DMKHOA_THUPHI")
    private Boolean dmkhoaThuphi;
    @Column(name = "DMKHOA_CUM")
    private Integer dmkhoaCum;
    @Column(name = "DMKHOA_GIATRAN")
    private Double dmkhoaGiatran;
    @Column(name = "DMKHOA_TIENGANH")
    private Boolean dmkhoaTienganh;
    @Column(name = "DMKHOA_THUTU")
    private Integer dmkhoaThutu;
    @Column(name = "DMKHOA_DVVESINH")
    private Boolean dmkhoaDvvesinh;
    @Column(name = "DMKHOA_NGAYGIOCN")
    private Double dmkhoaNgaygiocn;
    @Column(name = "DMKHOA_QL")
    private Boolean dmkhoaQl;
    @Column(name = "DMKHOA_DT")
    private Boolean dmkhoaDt;
    @Column(name = "DMKHOA_DP")
    private Boolean dmkhoaDp;
    @Column(name = "DMKHOA_KHO")
    private String dmkhoaKho;
    @Column(name = "DMKHOA_TINHCHAT")
    private String dmkhoaTinhChat;
    @Column(name = "DMKHOA_DAKHOA")
    private Boolean dmkhoaDaKhoa; //dda khoa

//    @OneToMany(mappedBy = "hoanungKhoa")
//    private Collection<HoanUng> hoanUngCollection;
//    @OneToMany(mappedBy = "hoanungKhoa1")
//    private Collection<HoanUng> hoanUngCollection1;
//    @OneToMany(mappedBy = "thuocnoitruKho")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection;
//    @OneToMany(mappedBy = "thuocnoitruKhoa")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection1;
//    @OneToMany(mappedBy = "thuocnoitruKho1")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection2;
//    @OneToMany(mappedBy = "thuocnoitruKhoa1")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection3;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmkhoaMaso")
//    private Collection<DtDmClsKhoa> dtDmClsKhoaCollection;
//    @OneToMany(mappedBy = "clsmoKhoa")
//    private Collection<ClsMo> clsMoCollection;
//    @OneToMany(mappedBy = "clsmoKhoathuchien")
//    private Collection<ClsMo> clsMoCollection1;
//    @OneToMany(mappedBy = "clsmoKhoa1")
//    private Collection<ClsMo> clsMoCollection2;
//    @OneToMany(mappedBy = "clsmoKhoathuchien1")
//    private Collection<ClsMo> clsMoCollection3;
//    @OneToMany(mappedBy = "xetgiamkhamKhoa")
//    private Collection<XetGiamKham> xetGiamKhamCollection;
//    @OneToMany(mappedBy = "xetgiamkhamKhoa1")
//    private Collection<XetGiamKham> xetGiamKhamCollection1;
//    @OneToMany(mappedBy = "hoanthuKhoa")
//    private Collection<HoanThu> hoanThuCollection;
//    @OneToMany(mappedBy = "hoanthuKhoa1")
//    private Collection<HoanThu> hoanThuCollection1;
//    @OneToMany(mappedBy = "clskhamKhoa")
//    private Collection<ClsKham> clsKhamCollection;
//    @OneToMany(mappedBy = "clskhamKhoa2")
//    private Collection<ClsKham> clsKhamCollection1;
//    @OneToMany(mappedBy = "clskhamKhoa1")
//    private Collection<ClsKham> clsKhamCollection2;
//    @OneToMany(mappedBy = "clskhamKhoa21")
//    private Collection<ClsKham> clsKhamCollection3;
//    @OneToMany(mappedBy = "hoanungkhamKhoa")
//    private Collection<HoanUngKham> hoanUngKhamCollection;
//    @OneToMany(mappedBy = "hoanungkhamKhoa1")
//    private Collection<HoanUngKham> hoanUngKhamCollection1;
//    @OneToMany(mappedBy = "khoaLienhe")
//    private Collection<DtDmPhongMo> dtDmPhongMoCollection;
//    @OneToMany(mappedBy = "khoaLienhe1")
//    private Collection<DtDmPhongMo> dtDmPhongMoCollection1;
//    @OneToMany(mappedBy = "hoanthukhamKhoa")
//    private Collection<HoanThuKham> hoanThuKhamCollection;
//    @OneToMany(mappedBy = "hoanthukhamKhoa1")
//    private Collection<HoanThuKham> hoanThuKhamCollection1;
//    @OneToMany(mappedBy = "donmienKhoa")
//    private Collection<DonMien> donMienCollection;
//    @OneToMany(mappedBy = "donmienKhoa1")
//    private Collection<DonMien> donMienCollection1;
//    @OneToMany(mappedBy = "khoaMa")
//    private Collection<TuanMo> tuanMoCollection;
//    @OneToMany(mappedBy = "khoaMa1")
//    private Collection<TuanMo> tuanMoCollection1;
//    @OneToMany(mappedBy = "tiepdonChkhoa")
//    private Collection<TiepDon> tiepDonCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmkhoaXuat")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmkhoaNhan")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmkhoaNhan1")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection2;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmkhoaXuat1")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection3;
    @JoinColumn(name = "DMLOAIKHOA_MA", referencedColumnName = "DMLOAIKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiKhoa dmloaikhoaMa;
//    @OneToMany(mappedBy = "dmkhoaMakhoas")
//    private Collection<DmKhoa> dmKhoaCollection;
    @JoinColumn(name = "DMKHOA_MAKHOAS", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMakhoas;
    @JoinColumn(name = "DMNHOMKHOA_MASO", referencedColumnName = "DMNHOMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhomKhoa dmnhomkhoaMaso;
    @JoinColumn(name = "DMKHOA_MABYT", referencedColumnName = "DMKHOABYT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoaByt dmkhoaMabyt;
    @JoinColumn(name = "DMKHOA_MABYT3", referencedColumnName = "DMKHOABYT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoaByt dmkhoaMabyt3;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmkhoaMaso")
//    private Collection<DtDmNhanvienKhoa> dtDmNhanvienKhoaCollection;
//    @OneToMany(mappedBy = "toathuockhamKho")
//    private Collection<ToaThuocKham> toaThuocKhamCollection;
//    @OneToMany(mappedBy = "toathuockhamKho1")
//    private Collection<ToaThuocKham> toaThuocKhamCollection1;
//    @OneToMany(mappedBy = "khoaMa")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection;
//    @OneToMany(mappedBy = "hsbacmChuyenkhoa")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection1;
//    @OneToMany(mappedBy = "hsbacmChuyenkhoa1")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection2;
//    @OneToMany(mappedBy = "khoaMa1")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection3;
//    @OneToMany(mappedBy = "cttoalinhkKho")
//    private Collection<CtToaLinhk> ctToaLinhkCollection;
//    @OneToMany(mappedBy = "cttoalinhkKho1")
//    private Collection<CtToaLinhk> ctToaLinhkCollection1;
//    @OneToMany(mappedBy = "thuocphongkhamKhoa")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection;
//    @OneToMany(mappedBy = "thuocphongkhamKhoa1")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection1;
//    @OneToMany(mappedBy = "dmkhoaMaso")
//    private Collection<TonKho> tonKhoCollection;
//    @OneToMany(mappedBy = "tamungKhoa")
//    private Collection<TamUng> tamUngCollection;
//    @OneToMany(mappedBy = "tamungKhoa1")
//    private Collection<TamUng> tamUngCollection1;
//    @OneToMany(mappedBy = "miengiamKhoa")
//    private Collection<MienGiam> mienGiamCollection;
//    @OneToMany(mappedBy = "miengiamKhoa1")
//    private Collection<MienGiam> mienGiamCollection1;
//    @OneToMany(mappedBy = "hsthtoanKhoa")
//    private Collection<HsThtoan> hsThtoanCollection;
//    @OneToMany(mappedBy = "hsthtoanKhoa1")
//    private Collection<HsThtoan> hsThtoanCollection1;
//    @OneToMany(mappedBy = "dtdmkhoKhocha")
//    private Collection<DtDmKho> dtDmKhoCollection;
//    @OneToMany(mappedBy = "dtdmkhoKhocha1")
//    private Collection<DtDmKho> dtDmKhoCollection1;
//    @OneToMany(mappedBy = "denghiccKhoa")
//    private Collection<DeNghiCc> deNghiCcCollection;
//    @OneToMany(mappedBy = "denghiccKhoa1")
//    private Collection<DeNghiCc> deNghiCcCollection1;
//    @OneToMany(mappedBy = "moyeucauKhoa")
//    private Collection<MoYeuCau> moYeuCauCollection;
//    @OneToMany(mappedBy = "moyeucauKhoa1")
//    private Collection<MoYeuCau> moYeuCauCollection1;
//    @OneToMany(mappedBy = "dmkhoaMaso")
//    private Collection<PhieuDuTru> phieuDuTruCollection;
//    @OneToMany(mappedBy = "phieudtMakho")
//    private Collection<PhieuDuTru> phieuDuTruCollection1;
//    @OneToMany(mappedBy = "dmkhoaMaso1")
//    private Collection<PhieuDuTru> phieuDuTruCollection2;
//    @OneToMany(mappedBy = "phieudtMakho1")
//    private Collection<PhieuDuTru> phieuDuTruCollection3;
//    @OneToMany(mappedBy = "dmkhoaNhan")
//    private Collection<PhieuTraKho> phieuTraKhoCollection;
//    @OneToMany(mappedBy = "dmkhoaTra")
//    private Collection<PhieuTraKho> phieuTraKhoCollection1;
//    @OneToMany(mappedBy = "hsbaKhoadangdt")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "hsbaKhoavaov")
//    private Collection<Hsba> hsbaCollection1;
//    @OneToMany(mappedBy = "hsbaKhoarav")
//    private Collection<Hsba> hsbaCollection2;
//    @OneToMany(mappedBy = "hsbaTiepnhan")
//    private Collection<Hsba> hsbaCollection3;
//    @OneToMany(mappedBy = "hsbaKhoadangdt1")
//    private Collection<Hsba> hsbaCollection4;
//    @OneToMany(mappedBy = "hsbaKhoarav1")
//    private Collection<Hsba> hsbaCollection5;
//    @OneToMany(mappedBy = "hsbaKhoavaov1")
//    private Collection<Hsba> hsbaCollection6;
//    @OneToMany(mappedBy = "dmkhoaMaso")
//    private Collection<DtDmKhach> dtDmKhachCollection;
//    @OneToMany(mappedBy = "dmkhoaMaso1")
//    private Collection<DtDmKhach> dtDmKhachCollection1;
    public DmKhoa() {
    }

    public DmKhoa(Integer dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    public DmKhoa(Integer dmkhoaMaso, String dmkhoaMa, String dmkhoaTen) {
        this.dmkhoaMaso = dmkhoaMaso;
        this.dmkhoaMa = dmkhoaMa;
        this.dmkhoaTen = dmkhoaTen;
    }

    public Integer getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(Integer dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    public String getDmkhoaMa() {
        return dmkhoaMa;
    }

    public void setDmkhoaMa(String dmkhoaMa) {
        this.dmkhoaMa = dmkhoaMa;
    }

    public String getDmkhoaCon() {
        return dmkhoaCon;
    }

    public void setDmkhoaCon(String dmkhoaCon) {
        this.dmkhoaCon = dmkhoaCon;
    }

    public String getDmkhoaKyhieu() {
        return dmkhoaKyhieu;
    }

    public void setDmkhoaKyhieu(String dmkhoaKyhieu) {
        this.dmkhoaKyhieu = dmkhoaKyhieu;
    }

    public String getDmkhoaMakhoada() {
        return dmkhoaMakhoada;
    }

    public void setDmkhoaMakhoada(String dmkhoaMakhoada) {
        this.dmkhoaMakhoada = dmkhoaMakhoada;
    }

    public String getDmkhoaTen() {
        return dmkhoaTen;
    }

    public void setDmkhoaTen(String dmkhoaTen) {
        this.dmkhoaTen = dmkhoaTen;
    }

    public String getDmkhoaTen2() {
        return dmkhoaTen2;
    }

    public void setDmkhoaTen2(String dmkhoaTen2) {
        this.dmkhoaTen2 = dmkhoaTen2;
    }

    public String getDmkhoaTenbc() {
        return dmkhoaTenbc;
    }

    public void setDmkhoaTenbc(String dmkhoaTenbc) {
        this.dmkhoaTenbc = dmkhoaTenbc;
    }

    public Boolean getDmkhoaCls() {
        return dmkhoaCls;
    }

    public void setDmkhoaCls(Boolean dmkhoaCls) {
        this.dmkhoaCls = dmkhoaCls;
    }

    public String getDmkhoaDienthoai() {
        return dmkhoaDienthoai;
    }

    public void setDmkhoaDienthoai(String dmkhoaDienthoai) {
        this.dmkhoaDienthoai = dmkhoaDienthoai;
    }

    public Boolean getDmkhoaNoitru() {
        return dmkhoaNoitru;
    }

    public void setDmkhoaNoitru(Boolean dmkhoaNoitru) {
        this.dmkhoaNoitru = dmkhoaNoitru;
    }

    public Boolean getDmkhoaKham() {
        return dmkhoaKham;
    }

    public void setDmkhoaKham(Boolean dmkhoaKham) {
        this.dmkhoaKham = dmkhoaKham;
    }

    public Boolean getDmkhoaNgoaitru() {
        return dmkhoaNgoaitru;
    }

    public void setDmkhoaNgoaitru(Boolean dmkhoaNgoaitru) {
        this.dmkhoaNgoaitru = dmkhoaNgoaitru;
    }

    public Boolean getDmkhoaKhamicd() {
        return dmkhoaKhamicd;
    }

    public void setDmkhoaKhamicd(Boolean dmkhoaKhamicd) {
        this.dmkhoaKhamicd = dmkhoaKhamicd;
    }

    public Boolean getDmkhoaKhamcc() {
        return dmkhoaKhamcc;
    }

    public void setDmkhoaKhamcc(Boolean dmkhoaKhamcc) {
        this.dmkhoaKhamcc = dmkhoaKhamcc;
    }

    public Boolean getDmkhoaSotret() {
        return dmkhoaSotret;
    }

    public void setDmkhoaSotret(Boolean dmkhoaSotret) {
        this.dmkhoaSotret = dmkhoaSotret;
    }

    public Boolean getDmkhoaPhthuat() {
        return dmkhoaPhthuat;
    }

    public void setDmkhoaPhthuat(Boolean dmkhoaPhthuat) {
        this.dmkhoaPhthuat = dmkhoaPhthuat;
    }

    public Boolean getDmkhoaNhappt() {
        return dmkhoaNhappt;
    }

    public void setDmkhoaNhappt(Boolean dmkhoaNhappt) {
        this.dmkhoaNhappt = dmkhoaNhappt;
    }

    public Integer getDmkhoaGiuongkh() {
        return dmkhoaGiuongkh;
    }

    public void setDmkhoaGiuongkh(Integer dmkhoaGiuongkh) {
        this.dmkhoaGiuongkh = dmkhoaGiuongkh;
    }

    public Integer getDmkhoaGiuongtk() {
        return dmkhoaGiuongtk;
    }

    public void setDmkhoaGiuongtk(Integer dmkhoaGiuongtk) {
        this.dmkhoaGiuongtk = dmkhoaGiuongtk;
    }

    public Boolean getDmkhoaYeucau() {
        return dmkhoaYeucau;
    }

    public void setDmkhoaYeucau(Boolean dmkhoaYeucau) {
        this.dmkhoaYeucau = dmkhoaYeucau;
    }

    public Boolean getDmkhoaCapmau() {
        return dmkhoaCapmau;
    }

    public void setDmkhoaCapmau(Boolean dmkhoaCapmau) {
        this.dmkhoaCapmau = dmkhoaCapmau;
    }

    public Boolean getDmkhoaDuoc() {
        return dmkhoaDuoc;
    }

    public void setDmkhoaDuoc(Boolean dmkhoaDuoc) {
        this.dmkhoaDuoc = dmkhoaDuoc;
    }

    public Boolean getDmkhoaThuphi() {
        return dmkhoaThuphi;
    }

    public void setDmkhoaThuphi(Boolean dmkhoaThuphi) {
        this.dmkhoaThuphi = dmkhoaThuphi;
    }

    public Integer getDmkhoaCum() {
        return dmkhoaCum;
    }

    public void setDmkhoaCum(Integer dmkhoaCum) {
        this.dmkhoaCum = dmkhoaCum;
    }

    public Double getDmkhoaGiatran() {
        return dmkhoaGiatran;
    }

    public void setDmkhoaGiatran(Double dmkhoaGiatran) {
        this.dmkhoaGiatran = dmkhoaGiatran;
    }

    public Boolean getDmkhoaTienganh() {
        return dmkhoaTienganh;
    }

    public void setDmkhoaTienganh(Boolean dmkhoaTienganh) {
        this.dmkhoaTienganh = dmkhoaTienganh;
    }

    public Integer getDmkhoaThutu() {
        return dmkhoaThutu;
    }

    public void setDmkhoaThutu(Integer dmkhoaThutu) {
        this.dmkhoaThutu = dmkhoaThutu;
    }

    public Boolean getDmkhoaDvvesinh() {
        return dmkhoaDvvesinh;
    }

    public void setDmkhoaDvvesinh(Boolean dmkhoaDvvesinh) {
        this.dmkhoaDvvesinh = dmkhoaDvvesinh;
    }

    public Double getDmkhoaNgaygiocn() {
        return dmkhoaNgaygiocn;
    }

    public void setDmkhoaNgaygiocn(Double dmkhoaNgaygiocn) {
        this.dmkhoaNgaygiocn = dmkhoaNgaygiocn;
    }

    public Boolean getDmkhoaQl() {
        return dmkhoaQl;
    }

    public void setDmkhoaQl(Boolean dmkhoaQl) {
        this.dmkhoaQl = dmkhoaQl;
    }

    public Boolean getDmkhoaDt() {
        return dmkhoaDt;
    }

    public void setDmkhoaDt(Boolean dmkhoaDt) {
        this.dmkhoaDt = dmkhoaDt;
    }

    public Boolean getDmkhoaDp() {
        return dmkhoaDp;
    }

    public void setDmkhoaDp(Boolean dmkhoaDp) {
        this.dmkhoaDp = dmkhoaDp;
    }

    public String getDmkhoaKho() {
        return dmkhoaKho;
    }

    public void setDmkhoaKho(String dmkhoaKho) {
        this.dmkhoaKho = dmkhoaKho;
    }

//    public Collection<HoanUng> getHoanUngCollection() {
//        return hoanUngCollection;
//    }
//
//    public void setHoanUngCollection(Collection<HoanUng> hoanUngCollection) {
//        this.hoanUngCollection = hoanUngCollection;
//    }

//    public Collection<HoanUng> getHoanUngCollection1() {
//        return hoanUngCollection1;
//    }
//
//    public void setHoanUngCollection1(Collection<HoanUng> hoanUngCollection1) {
//        this.hoanUngCollection1 = hoanUngCollection1;
//    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection() {
//        return thuocNoiTruCollection;
//    }
//
//    public void setThuocNoiTruCollection(Collection<ThuocNoiTru> thuocNoiTruCollection) {
//        this.thuocNoiTruCollection = thuocNoiTruCollection;
//    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection1() {
//        return thuocNoiTruCollection1;
//    }
//
//    public void setThuocNoiTruCollection1(Collection<ThuocNoiTru> thuocNoiTruCollection1) {
//        this.thuocNoiTruCollection1 = thuocNoiTruCollection1;
//    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection2() {
//        return thuocNoiTruCollection2;
//    }
//
//    public void setThuocNoiTruCollection2(Collection<ThuocNoiTru> thuocNoiTruCollection2) {
//        this.thuocNoiTruCollection2 = thuocNoiTruCollection2;
//    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection3() {
//        return thuocNoiTruCollection3;
//    }
//
//    public void setThuocNoiTruCollection3(Collection<ThuocNoiTru> thuocNoiTruCollection3) {
//        this.thuocNoiTruCollection3 = thuocNoiTruCollection3;
//    }

//    public Collection<DtDmClsKhoa> getDtDmClsKhoaCollection() {
//        return dtDmClsKhoaCollection;
//    }
//
//    public void setDtDmClsKhoaCollection(Collection<DtDmClsKhoa> dtDmClsKhoaCollection) {
//        this.dtDmClsKhoaCollection = dtDmClsKhoaCollection;
//    }

//    public Collection<ClsMo> getClsMoCollection() {
//        return clsMoCollection;
//    }
//
//    public void setClsMoCollection(Collection<ClsMo> clsMoCollection) {
//        this.clsMoCollection = clsMoCollection;
//    }

//    public Collection<ClsMo> getClsMoCollection1() {
//        return clsMoCollection1;
//    }
//
//    public void setClsMoCollection1(Collection<ClsMo> clsMoCollection1) {
//        this.clsMoCollection1 = clsMoCollection1;
//    }

//    public Collection<ClsMo> getClsMoCollection2() {
//        return clsMoCollection2;
//    }
//
//    public void setClsMoCollection2(Collection<ClsMo> clsMoCollection2) {
//        this.clsMoCollection2 = clsMoCollection2;
//    }

//    public Collection<ClsMo> getClsMoCollection3() {
//        return clsMoCollection3;
//    }
//
//    public void setClsMoCollection3(Collection<ClsMo> clsMoCollection3) {
//        this.clsMoCollection3 = clsMoCollection3;
//    }

//    public Collection<XetGiamKham> getXetGiamKhamCollection() {
//        return xetGiamKhamCollection;
//    }
//
//    public void setXetGiamKhamCollection(Collection<XetGiamKham> xetGiamKhamCollection) {
//        this.xetGiamKhamCollection = xetGiamKhamCollection;
//    }

//    public Collection<XetGiamKham> getXetGiamKhamCollection1() {
//        return xetGiamKhamCollection1;
//    }
//
//    public void setXetGiamKhamCollection1(Collection<XetGiamKham> xetGiamKhamCollection1) {
//        this.xetGiamKhamCollection1 = xetGiamKhamCollection1;
//    }

//    public Collection<HoanThu> getHoanThuCollection() {
//        return hoanThuCollection;
//    }
//
//    public void setHoanThuCollection(Collection<HoanThu> hoanThuCollection) {
//        this.hoanThuCollection = hoanThuCollection;
//    }

//    public Collection<HoanThu> getHoanThuCollection1() {
//        return hoanThuCollection1;
//    }
//
//    public void setHoanThuCollection1(Collection<HoanThu> hoanThuCollection1) {
//        this.hoanThuCollection1 = hoanThuCollection1;
//    }

//    public Collection<ClsKham> getClsKhamCollection() {
//        return clsKhamCollection;
//    }
//
//    public void setClsKhamCollection(Collection<ClsKham> clsKhamCollection) {
//        this.clsKhamCollection = clsKhamCollection;
//    }

//    public Collection<ClsKham> getClsKhamCollection1() {
//        return clsKhamCollection1;
//    }
//
//    public void setClsKhamCollection1(Collection<ClsKham> clsKhamCollection1) {
//        this.clsKhamCollection1 = clsKhamCollection1;
//    }

//    public Collection<ClsKham> getClsKhamCollection2() {
//        return clsKhamCollection2;
//    }
//
//    public void setClsKhamCollection2(Collection<ClsKham> clsKhamCollection2) {
//        this.clsKhamCollection2 = clsKhamCollection2;
//    }

//    public Collection<ClsKham> getClsKhamCollection3() {
//        return clsKhamCollection3;
//    }
//
//    public void setClsKhamCollection3(Collection<ClsKham> clsKhamCollection3) {
//        this.clsKhamCollection3 = clsKhamCollection3;
//    }

//    public Collection<HoanUngKham> getHoanUngKhamCollection() {
//        return hoanUngKhamCollection;
//    }
//
//    public void setHoanUngKhamCollection(Collection<HoanUngKham> hoanUngKhamCollection) {
//        this.hoanUngKhamCollection = hoanUngKhamCollection;
//    }

//    public Collection<HoanUngKham> getHoanUngKhamCollection1() {
//        return hoanUngKhamCollection1;
//    }
//
//    public void setHoanUngKhamCollection1(Collection<HoanUngKham> hoanUngKhamCollection1) {
//        this.hoanUngKhamCollection1 = hoanUngKhamCollection1;
//    }

//    public Collection<DtDmPhongMo> getDtDmPhongMoCollection() {
//        return dtDmPhongMoCollection;
//    }
//
//    public void setDtDmPhongMoCollection(Collection<DtDmPhongMo> dtDmPhongMoCollection) {
//        this.dtDmPhongMoCollection = dtDmPhongMoCollection;
//    }

//    public Collection<DtDmPhongMo> getDtDmPhongMoCollection1() {
//        return dtDmPhongMoCollection1;
//    }
//
//    public void setDtDmPhongMoCollection1(Collection<DtDmPhongMo> dtDmPhongMoCollection1) {
//        this.dtDmPhongMoCollection1 = dtDmPhongMoCollection1;
//    }

//    public Collection<HoanThuKham> getHoanThuKhamCollection() {
//        return hoanThuKhamCollection;
//    }
//
//    public void setHoanThuKhamCollection(Collection<HoanThuKham> hoanThuKhamCollection) {
//        this.hoanThuKhamCollection = hoanThuKhamCollection;
//    }

//    public Collection<HoanThuKham> getHoanThuKhamCollection1() {
//        return hoanThuKhamCollection1;
//    }
//
//    public void setHoanThuKhamCollection1(Collection<HoanThuKham> hoanThuKhamCollection1) {
//        this.hoanThuKhamCollection1 = hoanThuKhamCollection1;
//    }

//    public Collection<DonMien> getDonMienCollection() {
//        return donMienCollection;
//    }
//
//    public void setDonMienCollection(Collection<DonMien> donMienCollection) {
//        this.donMienCollection = donMienCollection;
//    }

//    public Collection<DonMien> getDonMienCollection1() {
//        return donMienCollection1;
//    }
//
//    public void setDonMienCollection1(Collection<DonMien> donMienCollection1) {
//        this.donMienCollection1 = donMienCollection1;
//    }

//    public Collection<TuanMo> getTuanMoCollection() {
//        return tuanMoCollection;
//    }
//
//    public void setTuanMoCollection(Collection<TuanMo> tuanMoCollection) {
//        this.tuanMoCollection = tuanMoCollection;
//    }

//    public Collection<TuanMo> getTuanMoCollection1() {
//        return tuanMoCollection1;
//    }
//
//    public void setTuanMoCollection1(Collection<TuanMo> tuanMoCollection1) {
//        this.tuanMoCollection1 = tuanMoCollection1;
//    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection() {
//        return phieuXuatKhoCollection;
//    }
//
//    public void setPhieuXuatKhoCollection(Collection<PhieuXuatKho> phieuXuatKhoCollection) {
//        this.phieuXuatKhoCollection = phieuXuatKhoCollection;
//    }

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection1() {
//        return phieuXuatKhoCollection1;
//    }
//
//    public void setPhieuXuatKhoCollection1(Collection<PhieuXuatKho> phieuXuatKhoCollection1) {
//        this.phieuXuatKhoCollection1 = phieuXuatKhoCollection1;
//    }

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection2() {
//        return phieuXuatKhoCollection2;
//    }
//
//    public void setPhieuXuatKhoCollection2(Collection<PhieuXuatKho> phieuXuatKhoCollection2) {
//        this.phieuXuatKhoCollection2 = phieuXuatKhoCollection2;
//    }

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection3() {
//        return phieuXuatKhoCollection3;
//    }
//
//    public void setPhieuXuatKhoCollection3(Collection<PhieuXuatKho> phieuXuatKhoCollection3) {
//        this.phieuXuatKhoCollection3 = phieuXuatKhoCollection3;
//    }
    public DmLoaiKhoa getDmloaikhoaMa(boolean create) {
        if (create && dmloaikhoaMa == null) {
            dmloaikhoaMa = new DmLoaiKhoa();
        }
        return dmloaikhoaMa;
    }

    public DmLoaiKhoa getDmloaikhoaMa() {
        return dmloaikhoaMa;
    }

    public void setDmloaikhoaMa(DmLoaiKhoa dmloaikhoaMa) {
        this.dmloaikhoaMa = dmloaikhoaMa;
    }

//    public Collection<DmKhoa> getDmKhoaCollection() {
//        return dmKhoaCollection;
//    }
//
//    public void setDmKhoaCollection(Collection<DmKhoa> dmKhoaCollection) {
//        this.dmKhoaCollection = dmKhoaCollection;
//    }
    public DmKhoa getDmkhoaMakhoas(boolean create) {
        if (create && dmkhoaMakhoas == null) {
            dmkhoaMakhoas = new DmKhoa();
        }
        return dmkhoaMakhoas;
    }

    public DmKhoa getDmkhoaMakhoas() {
        return dmkhoaMakhoas;
    }

    public void setDmkhoaMakhoas(DmKhoa dmkhoaMakhoas) {
        this.dmkhoaMakhoas = dmkhoaMakhoas;
    }

    public DmNhomKhoa getDmnhomkhoaMaso(boolean create) {
        if (create && dmnhomkhoaMaso == null) {
            dmnhomkhoaMaso = new DmNhomKhoa();
        }
        return dmnhomkhoaMaso;
    }

    public DmNhomKhoa getDmnhomkhoaMaso() {
        return dmnhomkhoaMaso;
    }

    public void setDmnhomkhoaMaso(DmNhomKhoa dmnhomkhoaMaso) {
        this.dmnhomkhoaMaso = dmnhomkhoaMaso;
    }

    public DmKhoaByt getDmkhoaMabyt(boolean create) {
        if (create && dmkhoaMabyt == null) {
            dmkhoaMabyt = new DmKhoaByt();
        }
        return dmkhoaMabyt;
    }

    public DmKhoaByt getDmkhoaMabyt() {
        return dmkhoaMabyt;
    }

    public void setDmkhoaMabyt(DmKhoaByt dmkhoaMabyt) {
        this.dmkhoaMabyt = dmkhoaMabyt;
    }

    public DmKhoaByt getDmkhoaMabyt3(boolean create) {
        if (create && dmkhoaMabyt3 == null) {
            dmkhoaMabyt3 = new DmKhoaByt();
        }
        return dmkhoaMabyt3;
    }

    public DmKhoaByt getDmkhoaMabyt3() {
        return dmkhoaMabyt3;
    }

    public void setDmkhoaMabyt3(DmKhoaByt dmkhoaMabyt3) {
        this.dmkhoaMabyt3 = dmkhoaMabyt3;
    }

//    public Collection<DtDmNhanvienKhoa> getDtDmNhanvienKhoaCollection() {
//        return dtDmNhanvienKhoaCollection;
//    }
//
//    public void setDtDmNhanvienKhoaCollection(Collection<DtDmNhanvienKhoa> dtDmNhanvienKhoaCollection) {
//        this.dtDmNhanvienKhoaCollection = dtDmNhanvienKhoaCollection;
//    }

//    public Collection<ToaThuocKham> getToaThuocKhamCollection() {
//        return toaThuocKhamCollection;
//    }
//
//    public void setToaThuocKhamCollection(Collection<ToaThuocKham> toaThuocKhamCollection) {
//        this.toaThuocKhamCollection = toaThuocKhamCollection;
//    }

//    public Collection<ToaThuocKham> getToaThuocKhamCollection1() {
//        return toaThuocKhamCollection1;
//    }
//
//    public void setToaThuocKhamCollection1(Collection<ToaThuocKham> toaThuocKhamCollection1) {
//        this.toaThuocKhamCollection1 = toaThuocKhamCollection1;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection() {
//        return hsbaChuyenMonCollection;
//    }
//
//    public void setHsbaChuyenMonCollection(Collection<HsbaChuyenMon> hsbaChuyenMonCollection) {
//        this.hsbaChuyenMonCollection = hsbaChuyenMonCollection;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection1() {
//        return hsbaChuyenMonCollection1;
//    }
//
//    public void setHsbaChuyenMonCollection1(Collection<HsbaChuyenMon> hsbaChuyenMonCollection1) {
//        this.hsbaChuyenMonCollection1 = hsbaChuyenMonCollection1;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection2() {
//        return hsbaChuyenMonCollection2;
//    }
//
//    public void setHsbaChuyenMonCollection2(Collection<HsbaChuyenMon> hsbaChuyenMonCollection2) {
//        this.hsbaChuyenMonCollection2 = hsbaChuyenMonCollection2;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection3() {
//        return hsbaChuyenMonCollection3;
//    }
//
//    public void setHsbaChuyenMonCollection3(Collection<HsbaChuyenMon> hsbaChuyenMonCollection3) {
//        this.hsbaChuyenMonCollection3 = hsbaChuyenMonCollection3;
//    }

//    public Collection<CtToaLinhk> getCtToaLinhkCollection() {
//        return ctToaLinhkCollection;
//    }
//
//    public void setCtToaLinhkCollection(Collection<CtToaLinhk> ctToaLinhkCollection) {
//        this.ctToaLinhkCollection = ctToaLinhkCollection;
//    }

//    public Collection<CtToaLinhk> getCtToaLinhkCollection1() {
//        return ctToaLinhkCollection1;
//    }
//
//    public void setCtToaLinhkCollection1(Collection<CtToaLinhk> ctToaLinhkCollection1) {
//        this.ctToaLinhkCollection1 = ctToaLinhkCollection1;
//    }

//    public Collection<ThuocPhongKham> getThuocPhongKhamCollection() {
//        return thuocPhongKhamCollection;
//    }
//
//    public void setThuocPhongKhamCollection(Collection<ThuocPhongKham> thuocPhongKhamCollection) {
//        this.thuocPhongKhamCollection = thuocPhongKhamCollection;
//    }

//    public Collection<ThuocPhongKham> getThuocPhongKhamCollection1() {
//        return thuocPhongKhamCollection1;
//    }
//
//    public void setThuocPhongKhamCollection1(Collection<ThuocPhongKham> thuocPhongKhamCollection1) {
//        this.thuocPhongKhamCollection1 = thuocPhongKhamCollection1;
//    }

//    public Collection<TonKho> getTonKhoCollection() {
//        return tonKhoCollection;
//    }
//
//    public void setTonKhoCollection(Collection<TonKho> tonKhoCollection) {
//        this.tonKhoCollection = tonKhoCollection;
//    }

//    public Collection<TamUng> getTamUngCollection() {
//        return tamUngCollection;
//    }
//
//    public void setTamUngCollection(Collection<TamUng> tamUngCollection) {
//        this.tamUngCollection = tamUngCollection;
//    }

//    public Collection<TamUng> getTamUngCollection1() {
//        return tamUngCollection1;
//    }
//
//    public void setTamUngCollection1(Collection<TamUng> tamUngCollection1) {
//        this.tamUngCollection1 = tamUngCollection1;
//    }

//    public Collection<MienGiam> getMienGiamCollection() {
//        return mienGiamCollection;
//    }
//
//    public void setMienGiamCollection(Collection<MienGiam> mienGiamCollection) {
//        this.mienGiamCollection = mienGiamCollection;
//    }

//    public Collection<MienGiam> getMienGiamCollection1() {
//        return mienGiamCollection1;
//    }
//
//    public void setMienGiamCollection1(Collection<MienGiam> mienGiamCollection1) {
//        this.mienGiamCollection1 = mienGiamCollection1;
//    }

//    public Collection<HsThtoan> getHsThtoanCollection() {
//        return hsThtoanCollection;
//    }
//
//    public void setHsThtoanCollection(Collection<HsThtoan> hsThtoanCollection) {
//        this.hsThtoanCollection = hsThtoanCollection;
//    }

//    public Collection<HsThtoan> getHsThtoanCollection1() {
//        return hsThtoanCollection1;
//    }
//
//    public void setHsThtoanCollection1(Collection<HsThtoan> hsThtoanCollection1) {
//        this.hsThtoanCollection1 = hsThtoanCollection1;
//    }

//    public Collection<DtDmKho> getDtDmKhoCollection() {
//        return dtDmKhoCollection;
//    }
//
//    public void setDtDmKhoCollection(Collection<DtDmKho> dtDmKhoCollection) {
//        this.dtDmKhoCollection = dtDmKhoCollection;
//    }

//    public Collection<DtDmKho> getDtDmKhoCollection1() {
//        return dtDmKhoCollection1;
//    }
//
//    public void setDtDmKhoCollection1(Collection<DtDmKho> dtDmKhoCollection1) {
//        this.dtDmKhoCollection1 = dtDmKhoCollection1;
//    }

//    public Collection<DeNghiCc> getDeNghiCcCollection() {
//        return deNghiCcCollection;
//    }
//
//    public void setDeNghiCcCollection(Collection<DeNghiCc> deNghiCcCollection) {
//        this.deNghiCcCollection = deNghiCcCollection;
//    }

//    public Collection<DeNghiCc> getDeNghiCcCollection1() {
//        return deNghiCcCollection1;
//    }
//
//    public void setDeNghiCcCollection1(Collection<DeNghiCc> deNghiCcCollection1) {
//        this.deNghiCcCollection1 = deNghiCcCollection1;
//    }

//    public Collection<MoYeuCau> getMoYeuCauCollection() {
//        return moYeuCauCollection;
//    }
//
//    public void setMoYeuCauCollection(Collection<MoYeuCau> moYeuCauCollection) {
//        this.moYeuCauCollection = moYeuCauCollection;
//    }

//    public Collection<MoYeuCau> getMoYeuCauCollection1() {
//        return moYeuCauCollection1;
//    }
//
//    public void setMoYeuCauCollection1(Collection<MoYeuCau> moYeuCauCollection1) {
//        this.moYeuCauCollection1 = moYeuCauCollection1;
//    }

//    public Collection<PhieuDuTru> getPhieuDuTruCollection() {
//        return phieuDuTruCollection;
//    }
//
//    public void setPhieuDuTruCollection(Collection<PhieuDuTru> phieuDuTruCollection) {
//        this.phieuDuTruCollection = phieuDuTruCollection;
//    }

//    public Collection<PhieuDuTru> getPhieuDuTruCollection1() {
//        return phieuDuTruCollection1;
//    }
//
//    public void setPhieuDuTruCollection1(Collection<PhieuDuTru> phieuDuTruCollection1) {
//        this.phieuDuTruCollection1 = phieuDuTruCollection1;
//    }

//    public Collection<PhieuDuTru> getPhieuDuTruCollection2() {
//        return phieuDuTruCollection2;
//    }
//
//    public void setPhieuDuTruCollection2(Collection<PhieuDuTru> phieuDuTruCollection2) {
//        this.phieuDuTruCollection2 = phieuDuTruCollection2;
//    }

//    public Collection<PhieuDuTru> getPhieuDuTruCollection3() {
//        return phieuDuTruCollection3;
//    }
//
//    public void setPhieuDuTruCollection3(Collection<PhieuDuTru> phieuDuTruCollection3) {
//        this.phieuDuTruCollection3 = phieuDuTruCollection3;
//    }

//    public Collection<PhieuTraKho> getPhieuTraKhoCollection() {
//        return phieuTraKhoCollection;
//    }
//
//    public void setPhieuTraKhoCollection(Collection<PhieuTraKho> phieuTraKhoCollection) {
//        this.phieuTraKhoCollection = phieuTraKhoCollection;
//    }

//    public Collection<PhieuTraKho> getPhieuTraKhoCollection1() {
//        return phieuTraKhoCollection1;
//    }
//
//    public void setPhieuTraKhoCollection1(Collection<PhieuTraKho> phieuTraKhoCollection1) {
//        this.phieuTraKhoCollection1 = phieuTraKhoCollection1;
//    }

//    public Collection<Hsba> getHsbaCollection() {
//        return hsbaCollection;
//    }
//
//    public void setHsbaCollection(Collection<Hsba> hsbaCollection) {
//        this.hsbaCollection = hsbaCollection;
//    }

//    public Collection<Hsba> getHsbaCollection1() {
//        return hsbaCollection1;
//    }
//
//    public void setHsbaCollection1(Collection<Hsba> hsbaCollection1) {
//        this.hsbaCollection1 = hsbaCollection1;
//    }

//    public Collection<Hsba> getHsbaCollection2() {
//        return hsbaCollection2;
//    }
//
//    public void setHsbaCollection2(Collection<Hsba> hsbaCollection2) {
//        this.hsbaCollection2 = hsbaCollection2;
//    }

//    public Collection<Hsba> getHsbaCollection3() {
//        return hsbaCollection3;
//    }
//
//    public void setHsbaCollection3(Collection<Hsba> hsbaCollection3) {
//        this.hsbaCollection3 = hsbaCollection3;
//    }

//    public Collection<Hsba> getHsbaCollection4() {
//        return hsbaCollection4;
//    }
//
//    public void setHsbaCollection4(Collection<Hsba> hsbaCollection4) {
//        this.hsbaCollection4 = hsbaCollection4;
//    }

//    public Collection<Hsba> getHsbaCollection5() {
//        return hsbaCollection5;
//    }
//
//    public void setHsbaCollection5(Collection<Hsba> hsbaCollection5) {
//        this.hsbaCollection5 = hsbaCollection5;
//    }

//    public Collection<Hsba> getHsbaCollection6() {
//        return hsbaCollection6;
//    }
//
//    public void setHsbaCollection6(Collection<Hsba> hsbaCollection6) {
//        this.hsbaCollection6 = hsbaCollection6;
//    }

//    public Collection<DtDmKhach> getDtDmKhachCollection() {
//        return dtDmKhachCollection;
//    }
//
//    public void setDtDmKhachCollection(Collection<DtDmKhach> dtDmKhachCollection) {
//        this.dtDmKhachCollection = dtDmKhachCollection;
//    }

//    public Collection<DtDmKhach> getDtDmKhachCollection1() {
//        return dtDmKhachCollection1;
//    }
//
//    public void setDtDmKhachCollection1(Collection<DtDmKhach> dtDmKhachCollection1) {
//        this.dtDmKhachCollection1 = dtDmKhachCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmkhoaMaso != null ? dmkhoaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmKhoa)) {
            return false;
        }
        DmKhoa other = (DmKhoa) object;
        if ((this.dmkhoaMaso == null && other.dmkhoaMaso != null) || (this.dmkhoaMaso != null && !this.dmkhoaMaso.equals(other.dmkhoaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmKhoa[dmkhoaMaso=" + dmkhoaMaso + "]";
    }

    public String getDmkhoaTinhChat() {
        return dmkhoaTinhChat;
    }

    public void setDmkhoaTinhChat(String dmkhoaTinhChat) {
        this.dmkhoaTinhChat = dmkhoaTinhChat;
    }

    public Boolean getDmkhoaDaKhoa() {
        return dmkhoaDaKhoa;
    }

    public void setDmkhoaDaKhoa(Boolean dmkhoaDaKhoa) {
        this.dmkhoaDaKhoa = dmkhoaDaKhoa;
    }
}


