/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmHocVi;
import com.iesvn.yte.entity.NguoiDung;
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
@Table(name = "DT_DM_NHAN_VIEN")
@NamedQueries({})
public class DtDmNhanVien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_NHAN_VIEN")
    @SequenceGenerator(name = "DT_DM_NHAN_VIEN", sequenceName = "DT_DM_NHAN_VIEN_DTDMNHANVIEN_M", allocationSize = 1)
    @Column(name = "DTDMNHANVIEN_MASO", nullable = false)
    private Integer dtdmnhanvienMaso;
    @Column(name = "DTDMNHANVIEN_MA", nullable = false)
    private String dtdmnhanvienMa;
    @Column(name = "NHOM_MA")
    private String nhomMa;
    @Column(name = "DTDMNHANVIEN_TEN")
    private String dtdmnhanvienTen;
    @Column(name = "DTDMNHANVIEN_GIOITINH")
    private String dtdmnhanvienGioitinh;
    @Column(name = "DTDMNHANVIEN_NGAYSINH")
    @Temporal(TemporalType.DATE)
    private Date dtdmnhanvienNgaysinh;
    @Column(name = "DTDMNHANVIEN_MAHH")
    private String dtdmnhanvienMahh;
    @Column(name = "DTDMNHANVIEN_BIENCHE")
    private String dtdmnhanvienBienche;
    @Column(name = "DTDMNHANVIEN_DIACHI")
    private String dtdmnhanvienDiachi;
    @Column(name = "DTDMNHANVIEN_MOBILE")
    private String dtdmnhanvienMobile;
    @Column(name = "DTDMNHANVIEN_EMAIL")
    private String dtdmnhanvienEmail;
    @Column(name = "DTDMNHANVIEN_KYPHIEU")
    private Boolean dtdmnhanvienKyphieu;
    @Column(name = "DTDMNHANVIEN_DUYET")
    private Boolean dtdmnhanvienDuyet;
    @Column(name = "DTDMNHANVIEN_MO")
    private Boolean dtdmnhanvienMo;
    @Column(name = "DTDMNHANVIEN_NGHIVIEC")
    private Boolean dtdmnhanvienNghiviec;
    @Column(name = "DTDMNHANVIEN_MATMA")
    private String dtdmnhanvienMatma;
    @Column(name = "DTDMNHANVIEN_TKATM")
    private String dtdmnhanvienTkatm;
    @Column(name = "DTDMNHANVIEN_SOBHXH")
    private String dtdmnhanvienSobhxh;
    @Column(name = "DTDMNHANVIEN_NGAYGIOCN")
    private Double dtdmnhanvienNgaygiocn;
//    @Column(name = "ND_TENDANGNHAP")
//    private String ndTendangnhap;
    @Column(name = "DTDMNHANVIEN_CHON")
    private Boolean dtdmnhanvienChon;
//    @OneToMany(mappedBy = "hsbabbhcChutoa")
//    private Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection;
//    @OneToMany(mappedBy = "hsbabbhcThuky")
//    private Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection1;
//    @OneToMany(mappedBy = "hsbabbhcNhanviencn")
//    private Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection2;
//    @OneToMany(mappedBy = "hsbabbhcChutoa1")
//    private Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection3;
//    @OneToMany(mappedBy = "hsbabbhcNhanviencn1")
//    private Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection4;
//    @OneToMany(mappedBy = "hsbabbhcThuky1")
//    private Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection5;
//    @OneToMany(mappedBy = "hoanungNhanviencn")
//    private Collection<HoanUng> hoanUngCollection;
//    @OneToMany(mappedBy = "hoanungThungan")
//    private Collection<HoanUng> hoanUngCollection1;
//    @OneToMany(mappedBy = "hoanungNhanviencn1")
//    private Collection<HoanUng> hoanUngCollection2;
//    @OneToMany(mappedBy = "hoanungThungan1")
//    private Collection<HoanUng> hoanUngCollection3;
//    @OneToMany(mappedBy = "thuocnoitruNhanviencn")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection;
//    @OneToMany(mappedBy = "thuocnoitruThungan")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection1;
//    @OneToMany(mappedBy = "thuocnoitruBacsi")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection2;
//    @OneToMany(mappedBy = "thuocnoitruBacsi1")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection3;
//    @OneToMany(mappedBy = "thuocnoitruNhanviencn1")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection4;
//    @OneToMany(mappedBy = "thuocnoitruThungan1")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection5;
//    @OneToMany(mappedBy = "hsthtoankNhanviencn")
//    private Collection<HsThtoank> hsThtoankCollection;
//    @OneToMany(mappedBy = "hsthtoankThungan")
//    private Collection<HsThtoank> hsThtoankCollection1;
//    @OneToMany(mappedBy = "hsthtoankNhanviencn1")
//    private Collection<HsThtoank> hsThtoankCollection2;
//    @OneToMany(mappedBy = "hsthtoankThungan1")
//    private Collection<HsThtoank> hsThtoankCollection3;
//    @OneToMany(mappedBy = "dtdmnhanvienBacsi")
//    private Collection<PhieuXuatBh> phieuXuatBhCollection;
//    @OneToMany(mappedBy = "dtdmnhanvienCn")
//    private Collection<PhieuXuatBh> phieuXuatBhCollection1;
//    @OneToMany(mappedBy = "dtdmnhanvienPhat")
//    private Collection<PhieuXuatBh> phieuXuatBhCollection2;
//    @OneToMany(mappedBy = "clsmoBacsi")
//    private Collection<ClsMo> clsMoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clsmoThuchien")
//    private Collection<ClsMo> clsMoCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clsmoNhanviencn")
//    private Collection<ClsMo> clsMoCollection2;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clsmoThungan")
//    private Collection<ClsMo> clsMoCollection3;
//    @OneToMany(mappedBy = "clsmoBacsi1")
//    private Collection<ClsMo> clsMoCollection4;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clsmoNhanviencn1")
//    private Collection<ClsMo> clsMoCollection5;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clsmoThuchien1")
//    private Collection<ClsMo> clsMoCollection6;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clsmoThungan1")
//    private Collection<ClsMo> clsMoCollection7;
    @JoinColumn(name = "DMHOCVI_MASO", referencedColumnName = "DMHOCVI_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmHocVi dmhocviMaso;
    @JoinColumn(name = "ND_MASO", referencedColumnName = "ND_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private NguoiDung ndMaso;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienMaso")
//    private Collection<HsbaBienBanHoiChanThanhVien> hsbaBienBanHoiChanThanhVienCollection;
//    @OneToMany(mappedBy = "xetgiamkhamBacsi")
//    private Collection<XetGiamKham> xetGiamKhamCollection;
//    @OneToMany(mappedBy = "xetgiamkhamNhanviencn")
//    private Collection<XetGiamKham> xetGiamKhamCollection1;
//    @OneToMany(mappedBy = "xetgiamkhamBacsi1")
//    private Collection<XetGiamKham> xetGiamKhamCollection2;
//    @OneToMany(mappedBy = "xetgiamkhamNhanviencn1")
//    private Collection<XetGiamKham> xetGiamKhamCollection3;
//    @OneToMany(mappedBy = "hoanthuNhanviencn")
//    private Collection<HoanThu> hoanThuCollection;
//    @OneToMany(mappedBy = "hoanthuThungan")
//    private Collection<HoanThu> hoanThuCollection1;
//    @OneToMany(mappedBy = "hoanthuNhanviencn1")
//    private Collection<HoanThu> hoanThuCollection2;
//    @OneToMany(mappedBy = "hoanthuThungan1")
//    private Collection<HoanThu> hoanThuCollection3;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clskhamThungan")
//    private Collection<ClsKham> clsKhamCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clskhamThuchien")
//    private Collection<ClsKham> clsKhamCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clskhamMabs")
//    private Collection<ClsKham> clsKhamCollection2;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clskhamNhanviencn")
//    private Collection<ClsKham> clsKhamCollection3;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clskhamMabs1")
//    private Collection<ClsKham> clsKhamCollection4;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clskhamNhanviencn1")
//    private Collection<ClsKham> clsKhamCollection5;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clskhamThuchien1")
//    private Collection<ClsKham> clsKhamCollection6;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clskhamThungan1")
//    private Collection<ClsKham> clsKhamCollection7;
//    @OneToMany(mappedBy = "hoanungkhamNhanviencn")
//    private Collection<HoanUngKham> hoanUngKhamCollection;
//    @OneToMany(mappedBy = "hoanungkhamThungan")
//    private Collection<HoanUngKham> hoanUngKhamCollection1;
//    @OneToMany(mappedBy = "hoanungkhamNhanviencn1")
//    private Collection<HoanUngKham> hoanUngKhamCollection2;
//    @OneToMany(mappedBy = "hoanungkhamThungan1")
//    private Collection<HoanUngKham> hoanUngKhamCollection3;
//    @OneToMany(mappedBy = "thamkhamBacsi")
//    private Collection<ThamKham> thamKhamCollection;
//    @OneToMany(mappedBy = "thamkhamNhanviencn")
//    private Collection<ThamKham> thamKhamCollection1;
//    @OneToMany(mappedBy = "thamkhamBacsi1")
//    private Collection<ThamKham> thamKhamCollection2;
//    @OneToMany(mappedBy = "thamkhamNhanviencn1")
//    private Collection<ThamKham> thamKhamCollection3;
//    @OneToMany(mappedBy = "tamungkhamNhanviencn")
//    private Collection<TamUngKham> tamUngKhamCollection;
//    @OneToMany(mappedBy = "tamungkhamThungan")
//    private Collection<TamUngKham> tamUngKhamCollection1;
//    @OneToMany(mappedBy = "tamungkhamNhanviencn1")
//    private Collection<TamUngKham> tamUngKhamCollection2;
//    @OneToMany(mappedBy = "tamungkhamThungan1")
//    private Collection<TamUngKham> tamUngKhamCollection3;
//    @OneToMany(mappedBy = "toalinhkhamBacsi")
//    private Collection<ToaLinhKham> toaLinhKhamCollection;
//    @OneToMany(mappedBy = "toalinhkhamNhanviencn")
//    private Collection<ToaLinhKham> toaLinhKhamCollection1;
//    @OneToMany(mappedBy = "toalinhkhamBacsi1")
//    private Collection<ToaLinhKham> toaLinhKhamCollection2;
//    @OneToMany(mappedBy = "toalinhkhamNhanviencn1")
//    private Collection<ToaLinhKham> toaLinhKhamCollection3;
//    @OneToMany(mappedBy = "hsbamoBacsi")
//    private Collection<HsbaMo> hsbaMoCollection;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<HsbaMo> hsbaMoCollection1;
//    @OneToMany(mappedBy = "hsbamoBacsi1")
//    private Collection<HsbaMo> hsbaMoCollection2;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<HsbaMo> hsbaMoCollection3;
//    @OneToMany(mappedBy = "hoanthukhamNhanviencn")
//    private Collection<HoanThuKham> hoanThuKhamCollection;
//    @OneToMany(mappedBy = "hoanthukhamThungan")
//    private Collection<HoanThuKham> hoanThuKhamCollection1;
//    @OneToMany(mappedBy = "hoanthukhamNhanviencn1")
//    private Collection<HoanThuKham> hoanThuKhamCollection2;
//    @OneToMany(mappedBy = "hoanthukhamThungan1")
//    private Collection<HoanThuKham> hoanThuKhamCollection3;
//    @OneToMany(mappedBy = "hsbacvBschuyen")
//    private Collection<HsbaChuyenVien> hsbaChuyenVienCollection;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<HsbaChuyenVien> hsbaChuyenVienCollection1;
//    @OneToMany(mappedBy = "hsbacvBschuyen1")
//    private Collection<HsbaChuyenVien> hsbaChuyenVienCollection2;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<HsbaChuyenVien> hsbaChuyenVienCollection3;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "donmienNhanviencn")
//    private Collection<DonMien> donMienCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "donmienNhanviencn1")
//    private Collection<DonMien> donMienCollection1;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<TuanMo> tuanMoCollection;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<TuanMo> tuanMoCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienMaso")
//    private Collection<DtDmBacSiBanKham> dtDmBacSiBanKhamCollection;
//    @OneToMany(mappedBy = "tiepdonBacsi")
//    private Collection<TiepDon> tiepDonCollection;
//    @OneToMany(mappedBy = "tiepdonBschuyen")
//    private Collection<TiepDon> tiepDonCollection1;
//    @OneToMany(mappedBy = "tiepdonNguoiduyet")
//    private Collection<TiepDon> tiepDonCollection2;
//    @OneToMany(mappedBy = "tiepdonNhanviencn")
//    private Collection<TiepDon> tiepDonCollection3;
//    @OneToMany(mappedBy = "tiepdonThungan")
//    private Collection<TiepDon> tiepDonCollection4;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<HsbaGiayRaVien> hsbaGiayRaVienCollection;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<HsbaGiayRaVien> hsbaGiayRaVienCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienNhan")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienPhat")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienBacsi")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection2;
//    @OneToMany(mappedBy = "dtdmnhanvienCn")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection3;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienBacsi1")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection4;
//    @OneToMany(mappedBy = "dtdmnhanvienCn1")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection5;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienNhan1")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection6;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienPhat1")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection7;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienMaso")
//    private Collection<DtDmNhanvienKhoa> dtDmNhanvienKhoaCollection;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<HsbaGiayTomTat> hsbaGiayTomTatCollection;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<HsbaGiayTomTat> hsbaGiayTomTatCollection1;
//    @OneToMany(mappedBy = "toathuockhamBacsi")
//    private Collection<ToaThuocKham> toaThuocKhamCollection;
//    @OneToMany(mappedBy = "toathuockhamNhanviencn")
//    private Collection<ToaThuocKham> toaThuocKhamCollection1;
//    @OneToMany(mappedBy = "toathuockhamBacsi1")
//    private Collection<ToaThuocKham> toaThuocKhamCollection2;
//    @OneToMany(mappedBy = "toathuockhamNhanviencn1")
//    private Collection<ToaThuocKham> toaThuocKhamCollection3;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienCn")
//    private Collection<CtPhieuDt> ctPhieuDtCollection;
//    @OneToMany(mappedBy = "dtdmnhanvienBacsi1")
//    private Collection<DtDmBanKham> dtDmBanKhamCollection;
//    @OneToMany(mappedBy = "dtdmnhanvienBacsi2")
//    private Collection<DtDmBanKham> dtDmBanKhamCollection1;
//    @OneToMany(mappedBy = "dtdmnhanvienBacsi3")
//    private Collection<DtDmBanKham> dtDmBanKhamCollection2;
//    @OneToMany(mappedBy = "dtdmnhanvienBacsi11")
//    private Collection<DtDmBanKham> dtDmBanKhamCollection3;
//    @OneToMany(mappedBy = "dtdmnhanvienBacsi21")
//    private Collection<DtDmBanKham> dtDmBanKhamCollection4;
//    @OneToMany(mappedBy = "dtdmnhanvienBacsi31")
//    private Collection<DtDmBanKham> dtDmBanKhamCollection5;
//    @OneToMany(mappedBy = "hsbacmBacsi")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection1;
//    @OneToMany(mappedBy = "hsbacmBacsi1")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection2;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection3;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cttoalinhkNhanviencn")
//    private Collection<CtToaLinhk> ctToaLinhkCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cttoalinhkNhanviencn1")
//    private Collection<CtToaLinhk> ctToaLinhkCollection1;
//    @OneToMany(mappedBy = "thuocphongkhamNhanviencn")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection;
//    @OneToMany(mappedBy = "thuocphongkhamThungan")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection1;
//    @OneToMany(mappedBy = "thuocphongkhamBacsi")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection2;
//    @OneToMany(mappedBy = "thuocphongkhamBacsi1")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection3;
//    @OneToMany(mappedBy = "thuocphongkhamNhanviencn1")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection4;
//    @OneToMany(mappedBy = "thuocphongkhamThungan1")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection5;
//    @OneToMany(mappedBy = "hsbanopNguoinop")
//    private Collection<HsbaNop> hsbaNopCollection;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<HsbaNop> hsbaNopCollection1;
//    @OneToMany(mappedBy = "hsbanopNguoinop1")
//    private Collection<HsbaNop> hsbaNopCollection2;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<HsbaNop> hsbaNopCollection3;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<KetQuaMo> ketQuaMoCollection;
//    @OneToMany(mappedBy = "machinh")
//    private Collection<KetQuaMo> ketQuaMoCollection1;
//    @OneToMany(mappedBy = "maphu1")
//    private Collection<KetQuaMo> ketQuaMoCollection2;
//    @OneToMany(mappedBy = "maphu2")
//    private Collection<KetQuaMo> ketQuaMoCollection3;
//    @OneToMany(mappedBy = "magayme1")
//    private Collection<KetQuaMo> ketQuaMoCollection4;
//    @OneToMany(mappedBy = "magayme2")
//    private Collection<KetQuaMo> ketQuaMoCollection5;
//    @OneToMany(mappedBy = "machinh1")
//    private Collection<KetQuaMo> ketQuaMoCollection6;
//    @OneToMany(mappedBy = "magayme11")
//    private Collection<KetQuaMo> ketQuaMoCollection7;
//    @OneToMany(mappedBy = "magayme21")
//    private Collection<KetQuaMo> ketQuaMoCollection8;
//    @OneToMany(mappedBy = "maphu11")
//    private Collection<KetQuaMo> ketQuaMoCollection9;
//    @OneToMany(mappedBy = "maphu21")
//    private Collection<KetQuaMo> ketQuaMoCollection10;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<KetQuaMo> ketQuaMoCollection11;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienTieplieu")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection;
//    @OneToMany(mappedBy = "dtdmnhanvienTt")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection1;
//    @OneToMany(mappedBy = "dtdmnhanvienCn")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection2;
//    @OneToMany(mappedBy = "dtdmnhanvienCn1")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection3;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnhanvienTieplieu1")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection4;
//    @OneToMany(mappedBy = "dtdmnhanvienTt1")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection5;
//    @OneToMany(mappedBy = "dtdmnhanvienCn")
//    private Collection<TonKho> tonKhoCollection;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<LichMo> lichMoCollection;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<LichMo> lichMoCollection1;
//    @OneToMany(mappedBy = "tamungNhanviencn")
//    private Collection<TamUng> tamUngCollection;
//    @OneToMany(mappedBy = "tamungThungan")
//    private Collection<TamUng> tamUngCollection1;
//    @OneToMany(mappedBy = "tamungNhanviencn1")
//    private Collection<TamUng> tamUngCollection2;
//    @OneToMany(mappedBy = "tamungThungan1")
//    private Collection<TamUng> tamUngCollection3;
//    @OneToMany(mappedBy = "miengiamNguoiduyet")
//    private Collection<MienGiam> mienGiamCollection;
//    @OneToMany(mappedBy = "miengiamNhanviencn")
//    private Collection<MienGiam> mienGiamCollection1;
//    @OneToMany(mappedBy = "miengiamThungan")
//    private Collection<MienGiam> mienGiamCollection2;
//    @OneToMany(mappedBy = "miengiamNguoiduyet1")
//    private Collection<MienGiam> mienGiamCollection3;
//    @OneToMany(mappedBy = "miengiamNhanviencn1")
//    private Collection<MienGiam> mienGiamCollection4;
//    @OneToMany(mappedBy = "miengiamThungan1")
//    private Collection<MienGiam> mienGiamCollection5;
//    @OneToMany(mappedBy = "hsthtoanNhanviencn")
//    private Collection<HsThtoan> hsThtoanCollection;
//    @OneToMany(mappedBy = "hsthtoanThungan")
//    private Collection<HsThtoan> hsThtoanCollection1;
//    @OneToMany(mappedBy = "hsthtoanNhanviencn1")
//    private Collection<HsThtoan> hsThtoanCollection2;
//    @OneToMany(mappedBy = "hsthtoanThungan1")
//    private Collection<HsThtoan> hsThtoanCollection3;
//    @OneToMany(mappedBy = "dtdmkhoThukho")
//    private Collection<DtDmKho> dtDmKhoCollection;
//    @OneToMany(mappedBy = "dtdmkhoThukho1")
//    private Collection<DtDmKho> dtDmKhoCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "denghiccNhanviencn")
//    private Collection<DeNghiCc> deNghiCcCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "denghiccNhanviencn1")
//    private Collection<DeNghiCc> deNghiCcCollection1;
//    @OneToMany(mappedBy = "moyeucauBacsi")
//    private Collection<MoYeuCau> moYeuCauCollection;
//    @OneToMany(mappedBy = "moyeucauBacsi2")
//    private Collection<MoYeuCau> moYeuCauCollection1;
//    @OneToMany(mappedBy = "moyeucauBacsi3")
//    private Collection<MoYeuCau> moYeuCauCollection2;
//    @OneToMany(mappedBy = "moyeucauNhanviencn")
//    private Collection<MoYeuCau> moYeuCauCollection3;
//    @OneToMany(mappedBy = "moyeucauThungan")
//    private Collection<MoYeuCau> moYeuCauCollection4;
//    @OneToMany(mappedBy = "moyeucauBacsi1")
//    private Collection<MoYeuCau> moYeuCauCollection5;
//    @OneToMany(mappedBy = "moyeucauBacsi21")
//    private Collection<MoYeuCau> moYeuCauCollection6;
//    @OneToMany(mappedBy = "moyeucauBacsi31")
//    private Collection<MoYeuCau> moYeuCauCollection7;
//    @OneToMany(mappedBy = "moyeucauNhanviencn1")
//    private Collection<MoYeuCau> moYeuCauCollection8;
//    @OneToMany(mappedBy = "moyeucauThungan1")
//    private Collection<MoYeuCau> moYeuCauCollection9;
//    @OneToMany(mappedBy = "dtdmnhanvienCn")
//    private Collection<PhieuDuTru> phieuDuTruCollection;
//    @OneToMany(mappedBy = "dtdmnhanvienLapphieu")
//    private Collection<PhieuDuTru> phieuDuTruCollection1;
//    @OneToMany(mappedBy = "dtdmnhanvienBacsiky")
//    private Collection<PhieuDuTru> phieuDuTruCollection2;
//    @OneToMany(mappedBy = "dtdmnhanvienBacsiky1")
//    private Collection<PhieuDuTru> phieuDuTruCollection3;
//    @OneToMany(mappedBy = "dtdmnhanvienCn1")
//    private Collection<PhieuDuTru> phieuDuTruCollection4;
//    @OneToMany(mappedBy = "dtdmnhanvienLapphieu1")
//    private Collection<PhieuDuTru> phieuDuTruCollection5;
//    @OneToMany(mappedBy = "dtdmnhanvienBacsi")
//    private Collection<PhieuTraKho> phieuTraKhoCollection;
//    @OneToMany(mappedBy = "dtdmnhanvienCn")
//    private Collection<PhieuTraKho> phieuTraKhoCollection1;
//    @OneToMany(mappedBy = "dtdmnhanvienLapphieu")
//    private Collection<PhieuTraKho> phieuTraKhoCollection2;
//    @OneToMany(mappedBy = "dtdmnhanvienPhat")
//    private Collection<PhieuTraKho> phieuTraKhoCollection3;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cttoathuockNhanviencn")
//    private Collection<CtToaThuock> ctToaThuockCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cttoathuockNhanviencn1")
//    private Collection<CtToaThuock> ctToaThuockCollection1;
//    @OneToMany(mappedBy = "dtdmnhanvienCn")
//    private Collection<KiemKeKho> kiemKeKhoCollection;
//    @OneToMany(mappedBy = "dtdmnhanvienCn1")
//    private Collection<KiemKeKho> kiemKeKhoCollection1;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<Hsba> hsbaCollection1;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<BenhNhan> benhNhanCollection;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<BenhNhan> benhNhanCollection1;
//    @OneToMany(mappedBy = "nhanvienMa")
//    private Collection<HsbaGiayChungThuong> hsbaGiayChungThuongCollection;
//    @OneToMany(mappedBy = "nhanvienMa1")
//    private Collection<HsbaGiayChungThuong> hsbaGiayChungThuongCollection1;
    public DtDmNhanVien() {
    }

    public DtDmNhanVien(Integer dtdmnhanvienMaso) {
        this.dtdmnhanvienMaso = dtdmnhanvienMaso;
    }

    public DtDmNhanVien(Integer dtdmnhanvienMaso, String dtdmnhanvienMa) {
        this.dtdmnhanvienMaso = dtdmnhanvienMaso;
        this.dtdmnhanvienMa = dtdmnhanvienMa;
    }

    public Integer getDtdmnhanvienMaso() {
        return dtdmnhanvienMaso;
    }

    public void setDtdmnhanvienMaso(Integer dtdmnhanvienMaso) {
        this.dtdmnhanvienMaso = dtdmnhanvienMaso;
    }

    public String getDtdmnhanvienMa() {
        return dtdmnhanvienMa;
    }

    public void setDtdmnhanvienMa(String dtdmnhanvienMa) {
        this.dtdmnhanvienMa = dtdmnhanvienMa;
    }

    public String getNhomMa() {
        return nhomMa;
    }

    public void setNhomMa(String nhomMa) {
        this.nhomMa = nhomMa;
    }

    public String getDtdmnhanvienTen() {
        return dtdmnhanvienTen;
    }

    public void setDtdmnhanvienTen(String dtdmnhanvienTen) {
        this.dtdmnhanvienTen = dtdmnhanvienTen;
    }

    public String getDtdmnhanvienGioitinh() {
        return dtdmnhanvienGioitinh;
    }

    public void setDtdmnhanvienGioitinh(String dtdmnhanvienGioitinh) {
        this.dtdmnhanvienGioitinh = dtdmnhanvienGioitinh;
    }

    public Date getDtdmnhanvienNgaysinh() {
        return dtdmnhanvienNgaysinh;
    }

    public void setDtdmnhanvienNgaysinh(Date dtdmnhanvienNgaysinh) {
        this.dtdmnhanvienNgaysinh = dtdmnhanvienNgaysinh;
    }

    public String getDtdmnhanvienMahh() {
        return dtdmnhanvienMahh;
    }

    public void setDtdmnhanvienMahh(String dtdmnhanvienMahh) {
        this.dtdmnhanvienMahh = dtdmnhanvienMahh;
    }

    public String getDtdmnhanvienBienche() {
        return dtdmnhanvienBienche;
    }

    public void setDtdmnhanvienBienche(String dtdmnhanvienBienche) {
        this.dtdmnhanvienBienche = dtdmnhanvienBienche;
    }

    public String getDtdmnhanvienDiachi() {
        return dtdmnhanvienDiachi;
    }

    public void setDtdmnhanvienDiachi(String dtdmnhanvienDiachi) {
        this.dtdmnhanvienDiachi = dtdmnhanvienDiachi;
    }

    public String getDtdmnhanvienMobile() {
        return dtdmnhanvienMobile;
    }

    public void setDtdmnhanvienMobile(String dtdmnhanvienMobile) {
        this.dtdmnhanvienMobile = dtdmnhanvienMobile;
    }

    public String getDtdmnhanvienEmail() {
        return dtdmnhanvienEmail;
    }

    public void setDtdmnhanvienEmail(String dtdmnhanvienEmail) {
        this.dtdmnhanvienEmail = dtdmnhanvienEmail;
    }

    public Boolean getDtdmnhanvienKyphieu() {
        return dtdmnhanvienKyphieu;
    }

    public void setDtdmnhanvienKyphieu(Boolean dtdmnhanvienKyphieu) {
        this.dtdmnhanvienKyphieu = dtdmnhanvienKyphieu;
    }

    public Boolean getDtdmnhanvienDuyet() {
        return dtdmnhanvienDuyet;
    }

    public void setDtdmnhanvienDuyet(Boolean dtdmnhanvienDuyet) {
        this.dtdmnhanvienDuyet = dtdmnhanvienDuyet;
    }

    public Boolean getDtdmnhanvienMo() {
        return dtdmnhanvienMo;
    }

    public void setDtdmnhanvienMo(Boolean dtdmnhanvienMo) {
        this.dtdmnhanvienMo = dtdmnhanvienMo;
    }

    public Boolean getDtdmnhanvienNghiviec() {
        return dtdmnhanvienNghiviec;
    }

    public void setDtdmnhanvienNghiviec(Boolean dtdmnhanvienNghiviec) {
        this.dtdmnhanvienNghiviec = dtdmnhanvienNghiviec;
    }

    public String getDtdmnhanvienMatma() {
        return dtdmnhanvienMatma;
    }

    public void setDtdmnhanvienMatma(String dtdmnhanvienMatma) {
        this.dtdmnhanvienMatma = dtdmnhanvienMatma;
    }

    public String getDtdmnhanvienTkatm() {
        return dtdmnhanvienTkatm;
    }

    public void setDtdmnhanvienTkatm(String dtdmnhanvienTkatm) {
        this.dtdmnhanvienTkatm = dtdmnhanvienTkatm;
    }

    public String getDtdmnhanvienSobhxh() {
        return dtdmnhanvienSobhxh;
    }

    public void setDtdmnhanvienSobhxh(String dtdmnhanvienSobhxh) {
        this.dtdmnhanvienSobhxh = dtdmnhanvienSobhxh;
    }

    public Double getDtdmnhanvienNgaygiocn() {
        return dtdmnhanvienNgaygiocn;
    }

    public void setDtdmnhanvienNgaygiocn(Double dtdmnhanvienNgaygiocn) {
        this.dtdmnhanvienNgaygiocn = dtdmnhanvienNgaygiocn;
    }

    public Boolean getDtdmnhanvienChon() {
        return dtdmnhanvienChon;
    }

    public void setDtdmnhanvienChon(Boolean dtdmnhanvienChon) {
        this.dtdmnhanvienChon = dtdmnhanvienChon;
    }

//    public Collection<HsbaBienBanHoiChan> getHsbaBienBanHoiChanCollection() {
//        return hsbaBienBanHoiChanCollection;
//    }
//
//    public void setHsbaBienBanHoiChanCollection(Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection) {
//        this.hsbaBienBanHoiChanCollection = hsbaBienBanHoiChanCollection;
//    }

//    public Collection<HsbaBienBanHoiChan> getHsbaBienBanHoiChanCollection1() {
//        return hsbaBienBanHoiChanCollection1;
//    }
//
//    public void setHsbaBienBanHoiChanCollection1(Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection1) {
//        this.hsbaBienBanHoiChanCollection1 = hsbaBienBanHoiChanCollection1;
//    }

//    public Collection<HsbaBienBanHoiChan> getHsbaBienBanHoiChanCollection2() {
//        return hsbaBienBanHoiChanCollection2;
//    }
//
//    public void setHsbaBienBanHoiChanCollection2(Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection2) {
//        this.hsbaBienBanHoiChanCollection2 = hsbaBienBanHoiChanCollection2;
//    }

//    public Collection<HsbaBienBanHoiChan> getHsbaBienBanHoiChanCollection3() {
//        return hsbaBienBanHoiChanCollection3;
//    }
//
//    public void setHsbaBienBanHoiChanCollection3(Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection3) {
//        this.hsbaBienBanHoiChanCollection3 = hsbaBienBanHoiChanCollection3;
//    }

//    public Collection<HsbaBienBanHoiChan> getHsbaBienBanHoiChanCollection4() {
//        return hsbaBienBanHoiChanCollection4;
//    }
//
//    public void setHsbaBienBanHoiChanCollection4(Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection4) {
//        this.hsbaBienBanHoiChanCollection4 = hsbaBienBanHoiChanCollection4;
//    }

//    public Collection<HsbaBienBanHoiChan> getHsbaBienBanHoiChanCollection5() {
//        return hsbaBienBanHoiChanCollection5;
//    }
//
//    public void setHsbaBienBanHoiChanCollection5(Collection<HsbaBienBanHoiChan> hsbaBienBanHoiChanCollection5) {
//        this.hsbaBienBanHoiChanCollection5 = hsbaBienBanHoiChanCollection5;
//    }

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

//    public Collection<HoanUng> getHoanUngCollection2() {
//        return hoanUngCollection2;
//    }
//
//    public void setHoanUngCollection2(Collection<HoanUng> hoanUngCollection2) {
//        this.hoanUngCollection2 = hoanUngCollection2;
//    }

//    public Collection<HoanUng> getHoanUngCollection3() {
//        return hoanUngCollection3;
//    }
//
//    public void setHoanUngCollection3(Collection<HoanUng> hoanUngCollection3) {
//        this.hoanUngCollection3 = hoanUngCollection3;
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

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection4() {
//        return thuocNoiTruCollection4;
//    }
//
//    public void setThuocNoiTruCollection4(Collection<ThuocNoiTru> thuocNoiTruCollection4) {
//        this.thuocNoiTruCollection4 = thuocNoiTruCollection4;
//    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection5() {
//        return thuocNoiTruCollection5;
//    }
//
//    public void setThuocNoiTruCollection5(Collection<ThuocNoiTru> thuocNoiTruCollection5) {
//        this.thuocNoiTruCollection5 = thuocNoiTruCollection5;
//    }

//    public Collection<HsThtoank> getHsThtoankCollection() {
//        return hsThtoankCollection;
//    }
//
//    public void setHsThtoankCollection(Collection<HsThtoank> hsThtoankCollection) {
//        this.hsThtoankCollection = hsThtoankCollection;
//    }

//    public Collection<HsThtoank> getHsThtoankCollection1() {
//        return hsThtoankCollection1;
//    }
//
//    public void setHsThtoankCollection1(Collection<HsThtoank> hsThtoankCollection1) {
//        this.hsThtoankCollection1 = hsThtoankCollection1;
//    }

//    public Collection<HsThtoank> getHsThtoankCollection2() {
//        return hsThtoankCollection2;
//    }
//
//    public void setHsThtoankCollection2(Collection<HsThtoank> hsThtoankCollection2) {
//        this.hsThtoankCollection2 = hsThtoankCollection2;
//    }

//    public Collection<HsThtoank> getHsThtoankCollection3() {
//        return hsThtoankCollection3;
//    }
//
//    public void setHsThtoankCollection3(Collection<HsThtoank> hsThtoankCollection3) {
//        this.hsThtoankCollection3 = hsThtoankCollection3;
//    }

//    public Collection<PhieuXuatBh> getPhieuXuatBhCollection() {
//        return phieuXuatBhCollection;
//    }
//
//    public void setPhieuXuatBhCollection(Collection<PhieuXuatBh> phieuXuatBhCollection) {
//        this.phieuXuatBhCollection = phieuXuatBhCollection;
//    }

//    public Collection<PhieuXuatBh> getPhieuXuatBhCollection1() {
//        return phieuXuatBhCollection1;
//    }
//
//    public void setPhieuXuatBhCollection1(Collection<PhieuXuatBh> phieuXuatBhCollection1) {
//        this.phieuXuatBhCollection1 = phieuXuatBhCollection1;
//    }

//    public Collection<PhieuXuatBh> getPhieuXuatBhCollection2() {
//        return phieuXuatBhCollection2;
//    }
//
//    public void setPhieuXuatBhCollection2(Collection<PhieuXuatBh> phieuXuatBhCollection2) {
//        this.phieuXuatBhCollection2 = phieuXuatBhCollection2;
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

//    public Collection<ClsMo> getClsMoCollection4() {
//        return clsMoCollection4;
//    }
//
//    public void setClsMoCollection4(Collection<ClsMo> clsMoCollection4) {
//        this.clsMoCollection4 = clsMoCollection4;
//    }

//    public Collection<ClsMo> getClsMoCollection5() {
//        return clsMoCollection5;
//    }
//
//    public void setClsMoCollection5(Collection<ClsMo> clsMoCollection5) {
//        this.clsMoCollection5 = clsMoCollection5;
//    }

//    public Collection<ClsMo> getClsMoCollection6() {
//        return clsMoCollection6;
//    }
//
//    public void setClsMoCollection6(Collection<ClsMo> clsMoCollection6) {
//        this.clsMoCollection6 = clsMoCollection6;
//    }

//    public Collection<ClsMo> getClsMoCollection7() {
//        return clsMoCollection7;
//    }
//
//    public void setClsMoCollection7(Collection<ClsMo> clsMoCollection7) {
//        this.clsMoCollection7 = clsMoCollection7;
//    }
    public DmHocVi getDmhocviMaso(boolean create) {
if(create && dmhocviMaso == null) dmhocviMaso = new DmHocVi();
return dmhocviMaso;
}
    public DmHocVi getDmhocviMaso() {
        return dmhocviMaso;
    }

    public void setDmhocviMaso(DmHocVi dmhocviMaso) {
        this.dmhocviMaso = dmhocviMaso;
    }

//    public Collection<HsbaBienBanHoiChanThanhVien> getHsbaBienBanHoiChanThanhVienCollection() {
//        return hsbaBienBanHoiChanThanhVienCollection;
//    }
//
//    public void setHsbaBienBanHoiChanThanhVienCollection(Collection<HsbaBienBanHoiChanThanhVien> hsbaBienBanHoiChanThanhVienCollection) {
//        this.hsbaBienBanHoiChanThanhVienCollection = hsbaBienBanHoiChanThanhVienCollection;
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

//    public Collection<XetGiamKham> getXetGiamKhamCollection2() {
//        return xetGiamKhamCollection2;
//    }
//
//    public void setXetGiamKhamCollection2(Collection<XetGiamKham> xetGiamKhamCollection2) {
//        this.xetGiamKhamCollection2 = xetGiamKhamCollection2;
//    }

//    public Collection<XetGiamKham> getXetGiamKhamCollection3() {
//        return xetGiamKhamCollection3;
//    }
//
//    public void setXetGiamKhamCollection3(Collection<XetGiamKham> xetGiamKhamCollection3) {
//        this.xetGiamKhamCollection3 = xetGiamKhamCollection3;
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

//    public Collection<HoanThu> getHoanThuCollection2() {
//        return hoanThuCollection2;
//    }
//
//    public void setHoanThuCollection2(Collection<HoanThu> hoanThuCollection2) {
//        this.hoanThuCollection2 = hoanThuCollection2;
//    }

//    public Collection<HoanThu> getHoanThuCollection3() {
//        return hoanThuCollection3;
//    }
//
//    public void setHoanThuCollection3(Collection<HoanThu> hoanThuCollection3) {
//        this.hoanThuCollection3 = hoanThuCollection3;
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

//    public Collection<ClsKham> getClsKhamCollection4() {
//        return clsKhamCollection4;
//    }
//
//    public void setClsKhamCollection4(Collection<ClsKham> clsKhamCollection4) {
//        this.clsKhamCollection4 = clsKhamCollection4;
//    }

//    public Collection<ClsKham> getClsKhamCollection5() {
//        return clsKhamCollection5;
//    }
//
//    public void setClsKhamCollection5(Collection<ClsKham> clsKhamCollection5) {
//        this.clsKhamCollection5 = clsKhamCollection5;
//    }

//    public Collection<ClsKham> getClsKhamCollection6() {
//        return clsKhamCollection6;
//    }
//
//    public void setClsKhamCollection6(Collection<ClsKham> clsKhamCollection6) {
//        this.clsKhamCollection6 = clsKhamCollection6;
//    }

//    public Collection<ClsKham> getClsKhamCollection7() {
//        return clsKhamCollection7;
//    }
//
//    public void setClsKhamCollection7(Collection<ClsKham> clsKhamCollection7) {
//        this.clsKhamCollection7 = clsKhamCollection7;
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

//    public Collection<HoanUngKham> getHoanUngKhamCollection2() {
//        return hoanUngKhamCollection2;
//    }
//
//    public void setHoanUngKhamCollection2(Collection<HoanUngKham> hoanUngKhamCollection2) {
//        this.hoanUngKhamCollection2 = hoanUngKhamCollection2;
//    }

//    public Collection<HoanUngKham> getHoanUngKhamCollection3() {
//        return hoanUngKhamCollection3;
//    }
//
//    public void setHoanUngKhamCollection3(Collection<HoanUngKham> hoanUngKhamCollection3) {
//        this.hoanUngKhamCollection3 = hoanUngKhamCollection3;
//    }

//    public Collection<ThamKham> getThamKhamCollection() {
//        return thamKhamCollection;
//    }
//
//    public void setThamKhamCollection(Collection<ThamKham> thamKhamCollection) {
//        this.thamKhamCollection = thamKhamCollection;
//    }

//    public Collection<ThamKham> getThamKhamCollection1() {
//        return thamKhamCollection1;
//    }
//
//    public void setThamKhamCollection1(Collection<ThamKham> thamKhamCollection1) {
//        this.thamKhamCollection1 = thamKhamCollection1;
//    }

//    public Collection<ThamKham> getThamKhamCollection2() {
//        return thamKhamCollection2;
//    }
//
//    public void setThamKhamCollection2(Collection<ThamKham> thamKhamCollection2) {
//        this.thamKhamCollection2 = thamKhamCollection2;
//    }

//    public Collection<ThamKham> getThamKhamCollection3() {
//        return thamKhamCollection3;
//    }
//
//    public void setThamKhamCollection3(Collection<ThamKham> thamKhamCollection3) {
//        this.thamKhamCollection3 = thamKhamCollection3;
//    }

//    public Collection<TamUngKham> getTamUngKhamCollection() {
//        return tamUngKhamCollection;
//    }
//
//    public void setTamUngKhamCollection(Collection<TamUngKham> tamUngKhamCollection) {
//        this.tamUngKhamCollection = tamUngKhamCollection;
//    }

//    public Collection<TamUngKham> getTamUngKhamCollection1() {
//        return tamUngKhamCollection1;
//    }
//
//    public void setTamUngKhamCollection1(Collection<TamUngKham> tamUngKhamCollection1) {
//        this.tamUngKhamCollection1 = tamUngKhamCollection1;
//    }

//    public Collection<TamUngKham> getTamUngKhamCollection2() {
//        return tamUngKhamCollection2;
//    }
//
//    public void setTamUngKhamCollection2(Collection<TamUngKham> tamUngKhamCollection2) {
//        this.tamUngKhamCollection2 = tamUngKhamCollection2;
//    }

//    public Collection<TamUngKham> getTamUngKhamCollection3() {
//        return tamUngKhamCollection3;
//    }
//
//    public void setTamUngKhamCollection3(Collection<TamUngKham> tamUngKhamCollection3) {
//        this.tamUngKhamCollection3 = tamUngKhamCollection3;
//    }

//    public Collection<ToaLinhKham> getToaLinhKhamCollection() {
//        return toaLinhKhamCollection;
//    }
//
//    public void setToaLinhKhamCollection(Collection<ToaLinhKham> toaLinhKhamCollection) {
//        this.toaLinhKhamCollection = toaLinhKhamCollection;
//    }

//    public Collection<ToaLinhKham> getToaLinhKhamCollection1() {
//        return toaLinhKhamCollection1;
//    }
//
//    public void setToaLinhKhamCollection1(Collection<ToaLinhKham> toaLinhKhamCollection1) {
//        this.toaLinhKhamCollection1 = toaLinhKhamCollection1;
//    }

//    public Collection<ToaLinhKham> getToaLinhKhamCollection2() {
//        return toaLinhKhamCollection2;
//    }
//
//    public void setToaLinhKhamCollection2(Collection<ToaLinhKham> toaLinhKhamCollection2) {
//        this.toaLinhKhamCollection2 = toaLinhKhamCollection2;
//    }

//    public Collection<ToaLinhKham> getToaLinhKhamCollection3() {
//        return toaLinhKhamCollection3;
//    }
//
//    public void setToaLinhKhamCollection3(Collection<ToaLinhKham> toaLinhKhamCollection3) {
//        this.toaLinhKhamCollection3 = toaLinhKhamCollection3;
//    }

//    public Collection<HsbaMo> getHsbaMoCollection() {
//        return hsbaMoCollection;
//    }
//
//    public void setHsbaMoCollection(Collection<HsbaMo> hsbaMoCollection) {
//        this.hsbaMoCollection = hsbaMoCollection;
//    }

//    public Collection<HsbaMo> getHsbaMoCollection1() {
//        return hsbaMoCollection1;
//    }
//
//    public void setHsbaMoCollection1(Collection<HsbaMo> hsbaMoCollection1) {
//        this.hsbaMoCollection1 = hsbaMoCollection1;
//    }

//    public Collection<HsbaMo> getHsbaMoCollection2() {
//        return hsbaMoCollection2;
//    }
//
//    public void setHsbaMoCollection2(Collection<HsbaMo> hsbaMoCollection2) {
//        this.hsbaMoCollection2 = hsbaMoCollection2;
//    }

//    public Collection<HsbaMo> getHsbaMoCollection3() {
//        return hsbaMoCollection3;
//    }
//
//    public void setHsbaMoCollection3(Collection<HsbaMo> hsbaMoCollection3) {
//        this.hsbaMoCollection3 = hsbaMoCollection3;
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

//    public Collection<HoanThuKham> getHoanThuKhamCollection2() {
//        return hoanThuKhamCollection2;
//    }
//
//    public void setHoanThuKhamCollection2(Collection<HoanThuKham> hoanThuKhamCollection2) {
//        this.hoanThuKhamCollection2 = hoanThuKhamCollection2;
//    }

//    public Collection<HoanThuKham> getHoanThuKhamCollection3() {
//        return hoanThuKhamCollection3;
//    }
//
//    public void setHoanThuKhamCollection3(Collection<HoanThuKham> hoanThuKhamCollection3) {
//        this.hoanThuKhamCollection3 = hoanThuKhamCollection3;
//    }

//    public Collection<HsbaChuyenVien> getHsbaChuyenVienCollection() {
//        return hsbaChuyenVienCollection;
//    }
//
//    public void setHsbaChuyenVienCollection(Collection<HsbaChuyenVien> hsbaChuyenVienCollection) {
//        this.hsbaChuyenVienCollection = hsbaChuyenVienCollection;
//    }

//    public Collection<HsbaChuyenVien> getHsbaChuyenVienCollection1() {
//        return hsbaChuyenVienCollection1;
//    }
//
//    public void setHsbaChuyenVienCollection1(Collection<HsbaChuyenVien> hsbaChuyenVienCollection1) {
//        this.hsbaChuyenVienCollection1 = hsbaChuyenVienCollection1;
//    }

//    public Collection<HsbaChuyenVien> getHsbaChuyenVienCollection2() {
//        return hsbaChuyenVienCollection2;
//    }
//
//    public void setHsbaChuyenVienCollection2(Collection<HsbaChuyenVien> hsbaChuyenVienCollection2) {
//        this.hsbaChuyenVienCollection2 = hsbaChuyenVienCollection2;
//    }

//    public Collection<HsbaChuyenVien> getHsbaChuyenVienCollection3() {
//        return hsbaChuyenVienCollection3;
//    }
//
//    public void setHsbaChuyenVienCollection3(Collection<HsbaChuyenVien> hsbaChuyenVienCollection3) {
//        this.hsbaChuyenVienCollection3 = hsbaChuyenVienCollection3;
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

//    public Collection<DtDmBacSiBanKham> getDtDmBacSiBanKhamCollection() {
//        return dtDmBacSiBanKhamCollection;
//    }
//
//    public void setDtDmBacSiBanKhamCollection(Collection<DtDmBacSiBanKham> dtDmBacSiBanKhamCollection) {
//        this.dtDmBacSiBanKhamCollection = dtDmBacSiBanKhamCollection;
//    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }

//    public Collection<TiepDon> getTiepDonCollection1() {
//        return tiepDonCollection1;
//    }
//
//    public void setTiepDonCollection1(Collection<TiepDon> tiepDonCollection1) {
//        this.tiepDonCollection1 = tiepDonCollection1;
//    }

//    public Collection<TiepDon> getTiepDonCollection2() {
//        return tiepDonCollection2;
//    }
//
//    public void setTiepDonCollection2(Collection<TiepDon> tiepDonCollection2) {
//        this.tiepDonCollection2 = tiepDonCollection2;
//    }

//    public Collection<TiepDon> getTiepDonCollection3() {
//        return tiepDonCollection3;
//    }
//
//    public void setTiepDonCollection3(Collection<TiepDon> tiepDonCollection3) {
//        this.tiepDonCollection3 = tiepDonCollection3;
//    }

//    public Collection<TiepDon> getTiepDonCollection4() {
//        return tiepDonCollection4;
//    }
//
//    public void setTiepDonCollection4(Collection<TiepDon> tiepDonCollection4) {
//        this.tiepDonCollection4 = tiepDonCollection4;
//    }

//    public Collection<HsbaGiayRaVien> getHsbaGiayRaVienCollection() {
//        return hsbaGiayRaVienCollection;
//    }
//
//    public void setHsbaGiayRaVienCollection(Collection<HsbaGiayRaVien> hsbaGiayRaVienCollection) {
//        this.hsbaGiayRaVienCollection = hsbaGiayRaVienCollection;
//    }

//    public Collection<HsbaGiayRaVien> getHsbaGiayRaVienCollection1() {
//        return hsbaGiayRaVienCollection1;
//    }
//
//    public void setHsbaGiayRaVienCollection1(Collection<HsbaGiayRaVien> hsbaGiayRaVienCollection1) {
//        this.hsbaGiayRaVienCollection1 = hsbaGiayRaVienCollection1;
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

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection4() {
//        return phieuXuatKhoCollection4;
//    }
//
//    public void setPhieuXuatKhoCollection4(Collection<PhieuXuatKho> phieuXuatKhoCollection4) {
//        this.phieuXuatKhoCollection4 = phieuXuatKhoCollection4;
//    }

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection5() {
//        return phieuXuatKhoCollection5;
//    }
//
//    public void setPhieuXuatKhoCollection5(Collection<PhieuXuatKho> phieuXuatKhoCollection5) {
//        this.phieuXuatKhoCollection5 = phieuXuatKhoCollection5;
//    }

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection6() {
//        return phieuXuatKhoCollection6;
//    }
//
//    public void setPhieuXuatKhoCollection6(Collection<PhieuXuatKho> phieuXuatKhoCollection6) {
//        this.phieuXuatKhoCollection6 = phieuXuatKhoCollection6;
//    }

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection7() {
//        return phieuXuatKhoCollection7;
//    }
//
//    public void setPhieuXuatKhoCollection7(Collection<PhieuXuatKho> phieuXuatKhoCollection7) {
//        this.phieuXuatKhoCollection7 = phieuXuatKhoCollection7;
//    }

//    public Collection<DtDmNhanvienKhoa> getDtDmNhanvienKhoaCollection() {
//        return dtDmNhanvienKhoaCollection;
//    }
//
//    public void setDtDmNhanvienKhoaCollection(Collection<DtDmNhanvienKhoa> dtDmNhanvienKhoaCollection) {
//        this.dtDmNhanvienKhoaCollection = dtDmNhanvienKhoaCollection;
//    }

//    public Collection<HsbaGiayTomTat> getHsbaGiayTomTatCollection() {
//        return hsbaGiayTomTatCollection;
//    }
//
//    public void setHsbaGiayTomTatCollection(Collection<HsbaGiayTomTat> hsbaGiayTomTatCollection) {
//        this.hsbaGiayTomTatCollection = hsbaGiayTomTatCollection;
//    }

//    public Collection<HsbaGiayTomTat> getHsbaGiayTomTatCollection1() {
//        return hsbaGiayTomTatCollection1;
//    }
//
//    public void setHsbaGiayTomTatCollection1(Collection<HsbaGiayTomTat> hsbaGiayTomTatCollection1) {
//        this.hsbaGiayTomTatCollection1 = hsbaGiayTomTatCollection1;
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

//    public Collection<ToaThuocKham> getToaThuocKhamCollection2() {
//        return toaThuocKhamCollection2;
//    }
//
//    public void setToaThuocKhamCollection2(Collection<ToaThuocKham> toaThuocKhamCollection2) {
//        this.toaThuocKhamCollection2 = toaThuocKhamCollection2;
//    }

//    public Collection<ToaThuocKham> getToaThuocKhamCollection3() {
//        return toaThuocKhamCollection3;
//    }
//
//    public void setToaThuocKhamCollection3(Collection<ToaThuocKham> toaThuocKhamCollection3) {
//        this.toaThuocKhamCollection3 = toaThuocKhamCollection3;
//    }

//    public Collection<CtPhieuDt> getCtPhieuDtCollection() {
//        return ctPhieuDtCollection;
//    }
//
//    public void setCtPhieuDtCollection(Collection<CtPhieuDt> ctPhieuDtCollection) {
//        this.ctPhieuDtCollection = ctPhieuDtCollection;
//    }

//    public Collection<DtDmBanKham> getDtDmBanKhamCollection() {
//        return dtDmBanKhamCollection;
//    }
//
//    public void setDtDmBanKhamCollection(Collection<DtDmBanKham> dtDmBanKhamCollection) {
//        this.dtDmBanKhamCollection = dtDmBanKhamCollection;
//    }

//    public Collection<DtDmBanKham> getDtDmBanKhamCollection1() {
//        return dtDmBanKhamCollection1;
//    }
//
//    public void setDtDmBanKhamCollection1(Collection<DtDmBanKham> dtDmBanKhamCollection1) {
//        this.dtDmBanKhamCollection1 = dtDmBanKhamCollection1;
//    }

//    public Collection<DtDmBanKham> getDtDmBanKhamCollection2() {
//        return dtDmBanKhamCollection2;
//    }
//
//    public void setDtDmBanKhamCollection2(Collection<DtDmBanKham> dtDmBanKhamCollection2) {
//        this.dtDmBanKhamCollection2 = dtDmBanKhamCollection2;
//    }

//    public Collection<DtDmBanKham> getDtDmBanKhamCollection3() {
//        return dtDmBanKhamCollection3;
//    }
//
//    public void setDtDmBanKhamCollection3(Collection<DtDmBanKham> dtDmBanKhamCollection3) {
//        this.dtDmBanKhamCollection3 = dtDmBanKhamCollection3;
//    }

//    public Collection<DtDmBanKham> getDtDmBanKhamCollection4() {
//        return dtDmBanKhamCollection4;
//    }
//
//    public void setDtDmBanKhamCollection4(Collection<DtDmBanKham> dtDmBanKhamCollection4) {
//        this.dtDmBanKhamCollection4 = dtDmBanKhamCollection4;
//    }

//    public Collection<DtDmBanKham> getDtDmBanKhamCollection5() {
//        return dtDmBanKhamCollection5;
//    }
//
//    public void setDtDmBanKhamCollection5(Collection<DtDmBanKham> dtDmBanKhamCollection5) {
//        this.dtDmBanKhamCollection5 = dtDmBanKhamCollection5;
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

//    public Collection<ThuocPhongKham> getThuocPhongKhamCollection2() {
//        return thuocPhongKhamCollection2;
//    }
//
//    public void setThuocPhongKhamCollection2(Collection<ThuocPhongKham> thuocPhongKhamCollection2) {
//        this.thuocPhongKhamCollection2 = thuocPhongKhamCollection2;
//    }

//    public Collection<ThuocPhongKham> getThuocPhongKhamCollection3() {
//        return thuocPhongKhamCollection3;
//    }
//
//    public void setThuocPhongKhamCollection3(Collection<ThuocPhongKham> thuocPhongKhamCollection3) {
//        this.thuocPhongKhamCollection3 = thuocPhongKhamCollection3;
//    }

//    public Collection<ThuocPhongKham> getThuocPhongKhamCollection4() {
//        return thuocPhongKhamCollection4;
//    }
//
//    public void setThuocPhongKhamCollection4(Collection<ThuocPhongKham> thuocPhongKhamCollection4) {
//        this.thuocPhongKhamCollection4 = thuocPhongKhamCollection4;
//    }

//    public Collection<ThuocPhongKham> getThuocPhongKhamCollection5() {
//        return thuocPhongKhamCollection5;
//    }
//
//    public void setThuocPhongKhamCollection5(Collection<ThuocPhongKham> thuocPhongKhamCollection5) {
//        this.thuocPhongKhamCollection5 = thuocPhongKhamCollection5;
//    }

//    public Collection<HsbaNop> getHsbaNopCollection() {
//        return hsbaNopCollection;
//    }
//
//    public void setHsbaNopCollection(Collection<HsbaNop> hsbaNopCollection) {
//        this.hsbaNopCollection = hsbaNopCollection;
//    }

//    public Collection<HsbaNop> getHsbaNopCollection1() {
//        return hsbaNopCollection1;
//    }
//
//    public void setHsbaNopCollection1(Collection<HsbaNop> hsbaNopCollection1) {
//        this.hsbaNopCollection1 = hsbaNopCollection1;
//    }

//    public Collection<HsbaNop> getHsbaNopCollection2() {
//        return hsbaNopCollection2;
//    }
//
//    public void setHsbaNopCollection2(Collection<HsbaNop> hsbaNopCollection2) {
//        this.hsbaNopCollection2 = hsbaNopCollection2;
//    }

//    public Collection<HsbaNop> getHsbaNopCollection3() {
//        return hsbaNopCollection3;
//    }
//
//    public void setHsbaNopCollection3(Collection<HsbaNop> hsbaNopCollection3) {
//        this.hsbaNopCollection3 = hsbaNopCollection3;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection() {
//        return ketQuaMoCollection;
//    }
//
//    public void setKetQuaMoCollection(Collection<KetQuaMo> ketQuaMoCollection) {
//        this.ketQuaMoCollection = ketQuaMoCollection;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection1() {
//        return ketQuaMoCollection1;
//    }
//
//    public void setKetQuaMoCollection1(Collection<KetQuaMo> ketQuaMoCollection1) {
//        this.ketQuaMoCollection1 = ketQuaMoCollection1;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection2() {
//        return ketQuaMoCollection2;
//    }
//
//    public void setKetQuaMoCollection2(Collection<KetQuaMo> ketQuaMoCollection2) {
//        this.ketQuaMoCollection2 = ketQuaMoCollection2;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection3() {
//        return ketQuaMoCollection3;
//    }
//
//    public void setKetQuaMoCollection3(Collection<KetQuaMo> ketQuaMoCollection3) {
//        this.ketQuaMoCollection3 = ketQuaMoCollection3;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection4() {
//        return ketQuaMoCollection4;
//    }
//
//    public void setKetQuaMoCollection4(Collection<KetQuaMo> ketQuaMoCollection4) {
//        this.ketQuaMoCollection4 = ketQuaMoCollection4;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection5() {
//        return ketQuaMoCollection5;
//    }
//
//    public void setKetQuaMoCollection5(Collection<KetQuaMo> ketQuaMoCollection5) {
//        this.ketQuaMoCollection5 = ketQuaMoCollection5;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection6() {
//        return ketQuaMoCollection6;
//    }
//
//    public void setKetQuaMoCollection6(Collection<KetQuaMo> ketQuaMoCollection6) {
//        this.ketQuaMoCollection6 = ketQuaMoCollection6;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection7() {
//        return ketQuaMoCollection7;
//    }
//
//    public void setKetQuaMoCollection7(Collection<KetQuaMo> ketQuaMoCollection7) {
//        this.ketQuaMoCollection7 = ketQuaMoCollection7;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection8() {
//        return ketQuaMoCollection8;
//    }
//
//    public void setKetQuaMoCollection8(Collection<KetQuaMo> ketQuaMoCollection8) {
//        this.ketQuaMoCollection8 = ketQuaMoCollection8;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection9() {
//        return ketQuaMoCollection9;
//    }
//
//    public void setKetQuaMoCollection9(Collection<KetQuaMo> ketQuaMoCollection9) {
//        this.ketQuaMoCollection9 = ketQuaMoCollection9;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection10() {
//        return ketQuaMoCollection10;
//    }
//
//    public void setKetQuaMoCollection10(Collection<KetQuaMo> ketQuaMoCollection10) {
//        this.ketQuaMoCollection10 = ketQuaMoCollection10;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection11() {
//        return ketQuaMoCollection11;
//    }
//
//    public void setKetQuaMoCollection11(Collection<KetQuaMo> ketQuaMoCollection11) {
//        this.ketQuaMoCollection11 = ketQuaMoCollection11;
//    }

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection() {
//        return phieuNhapKhoCollection;
//    }
//
//    public void setPhieuNhapKhoCollection(Collection<PhieuNhapKho> phieuNhapKhoCollection) {
//        this.phieuNhapKhoCollection = phieuNhapKhoCollection;
//    }

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection1() {
//        return phieuNhapKhoCollection1;
//    }
//
//    public void setPhieuNhapKhoCollection1(Collection<PhieuNhapKho> phieuNhapKhoCollection1) {
//        this.phieuNhapKhoCollection1 = phieuNhapKhoCollection1;
//    }

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection2() {
//        return phieuNhapKhoCollection2;
//    }
//
//    public void setPhieuNhapKhoCollection2(Collection<PhieuNhapKho> phieuNhapKhoCollection2) {
//        this.phieuNhapKhoCollection2 = phieuNhapKhoCollection2;
//    }

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection3() {
//        return phieuNhapKhoCollection3;
//    }
//
//    public void setPhieuNhapKhoCollection3(Collection<PhieuNhapKho> phieuNhapKhoCollection3) {
//        this.phieuNhapKhoCollection3 = phieuNhapKhoCollection3;
//    }

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection4() {
//        return phieuNhapKhoCollection4;
//    }
//
//    public void setPhieuNhapKhoCollection4(Collection<PhieuNhapKho> phieuNhapKhoCollection4) {
//        this.phieuNhapKhoCollection4 = phieuNhapKhoCollection4;
//    }

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection5() {
//        return phieuNhapKhoCollection5;
//    }
//
//    public void setPhieuNhapKhoCollection5(Collection<PhieuNhapKho> phieuNhapKhoCollection5) {
//        this.phieuNhapKhoCollection5 = phieuNhapKhoCollection5;
//    }

//    public Collection<TonKho> getTonKhoCollection() {
//        return tonKhoCollection;
//    }
//
//    public void setTonKhoCollection(Collection<TonKho> tonKhoCollection) {
//        this.tonKhoCollection = tonKhoCollection;
//    }

//    public Collection<LichMo> getLichMoCollection() {
//        return lichMoCollection;
//    }
//
//    public void setLichMoCollection(Collection<LichMo> lichMoCollection) {
//        this.lichMoCollection = lichMoCollection;
//    }

//    public Collection<LichMo> getLichMoCollection1() {
//        return lichMoCollection1;
//    }
//
//    public void setLichMoCollection1(Collection<LichMo> lichMoCollection1) {
//        this.lichMoCollection1 = lichMoCollection1;
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

//    public Collection<TamUng> getTamUngCollection2() {
//        return tamUngCollection2;
//    }
//
//    public void setTamUngCollection2(Collection<TamUng> tamUngCollection2) {
//        this.tamUngCollection2 = tamUngCollection2;
//    }

//    public Collection<TamUng> getTamUngCollection3() {
//        return tamUngCollection3;
//    }
//
//    public void setTamUngCollection3(Collection<TamUng> tamUngCollection3) {
//        this.tamUngCollection3 = tamUngCollection3;
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

//    public Collection<MienGiam> getMienGiamCollection2() {
//        return mienGiamCollection2;
//    }
//
//    public void setMienGiamCollection2(Collection<MienGiam> mienGiamCollection2) {
//        this.mienGiamCollection2 = mienGiamCollection2;
//    }

//    public Collection<MienGiam> getMienGiamCollection3() {
//        return mienGiamCollection3;
//    }
//
//    public void setMienGiamCollection3(Collection<MienGiam> mienGiamCollection3) {
//        this.mienGiamCollection3 = mienGiamCollection3;
//    }

//    public Collection<MienGiam> getMienGiamCollection4() {
//        return mienGiamCollection4;
//    }
//
//    public void setMienGiamCollection4(Collection<MienGiam> mienGiamCollection4) {
//        this.mienGiamCollection4 = mienGiamCollection4;
//    }

//    public Collection<MienGiam> getMienGiamCollection5() {
//        return mienGiamCollection5;
//    }
//
//    public void setMienGiamCollection5(Collection<MienGiam> mienGiamCollection5) {
//        this.mienGiamCollection5 = mienGiamCollection5;
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

//    public Collection<HsThtoan> getHsThtoanCollection2() {
//        return hsThtoanCollection2;
//    }
//
//    public void setHsThtoanCollection2(Collection<HsThtoan> hsThtoanCollection2) {
//        this.hsThtoanCollection2 = hsThtoanCollection2;
//    }

//    public Collection<HsThtoan> getHsThtoanCollection3() {
//        return hsThtoanCollection3;
//    }
//
//    public void setHsThtoanCollection3(Collection<HsThtoan> hsThtoanCollection3) {
//        this.hsThtoanCollection3 = hsThtoanCollection3;
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

//    public Collection<MoYeuCau> getMoYeuCauCollection2() {
//        return moYeuCauCollection2;
//    }
//
//    public void setMoYeuCauCollection2(Collection<MoYeuCau> moYeuCauCollection2) {
//        this.moYeuCauCollection2 = moYeuCauCollection2;
//    }

//    public Collection<MoYeuCau> getMoYeuCauCollection3() {
//        return moYeuCauCollection3;
//    }
//
//    public void setMoYeuCauCollection3(Collection<MoYeuCau> moYeuCauCollection3) {
//        this.moYeuCauCollection3 = moYeuCauCollection3;
//    }

//    public Collection<MoYeuCau> getMoYeuCauCollection4() {
//        return moYeuCauCollection4;
//    }
//
//    public void setMoYeuCauCollection4(Collection<MoYeuCau> moYeuCauCollection4) {
//        this.moYeuCauCollection4 = moYeuCauCollection4;
//    }

//    public Collection<MoYeuCau> getMoYeuCauCollection5() {
//        return moYeuCauCollection5;
//    }
//
//    public void setMoYeuCauCollection5(Collection<MoYeuCau> moYeuCauCollection5) {
//        this.moYeuCauCollection5 = moYeuCauCollection5;
//    }

//    public Collection<MoYeuCau> getMoYeuCauCollection6() {
//        return moYeuCauCollection6;
//    }
//
//    public void setMoYeuCauCollection6(Collection<MoYeuCau> moYeuCauCollection6) {
//        this.moYeuCauCollection6 = moYeuCauCollection6;
//    }

//    public Collection<MoYeuCau> getMoYeuCauCollection7() {
//        return moYeuCauCollection7;
//    }
//
//    public void setMoYeuCauCollection7(Collection<MoYeuCau> moYeuCauCollection7) {
//        this.moYeuCauCollection7 = moYeuCauCollection7;
//    }

//    public Collection<MoYeuCau> getMoYeuCauCollection8() {
//        return moYeuCauCollection8;
//    }
//
//    public void setMoYeuCauCollection8(Collection<MoYeuCau> moYeuCauCollection8) {
//        this.moYeuCauCollection8 = moYeuCauCollection8;
//    }

//    public Collection<MoYeuCau> getMoYeuCauCollection9() {
//        return moYeuCauCollection9;
//    }
//
//    public void setMoYeuCauCollection9(Collection<MoYeuCau> moYeuCauCollection9) {
//        this.moYeuCauCollection9 = moYeuCauCollection9;
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

//    public Collection<PhieuDuTru> getPhieuDuTruCollection4() {
//        return phieuDuTruCollection4;
//    }
//
//    public void setPhieuDuTruCollection4(Collection<PhieuDuTru> phieuDuTruCollection4) {
//        this.phieuDuTruCollection4 = phieuDuTruCollection4;
//    }

//    public Collection<PhieuDuTru> getPhieuDuTruCollection5() {
//        return phieuDuTruCollection5;
//    }
//
//    public void setPhieuDuTruCollection5(Collection<PhieuDuTru> phieuDuTruCollection5) {
//        this.phieuDuTruCollection5 = phieuDuTruCollection5;
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

//    public Collection<PhieuTraKho> getPhieuTraKhoCollection2() {
//        return phieuTraKhoCollection2;
//    }
//
//    public void setPhieuTraKhoCollection2(Collection<PhieuTraKho> phieuTraKhoCollection2) {
//        this.phieuTraKhoCollection2 = phieuTraKhoCollection2;
//    }

//    public Collection<PhieuTraKho> getPhieuTraKhoCollection3() {
//        return phieuTraKhoCollection3;
//    }
//
//    public void setPhieuTraKhoCollection3(Collection<PhieuTraKho> phieuTraKhoCollection3) {
//        this.phieuTraKhoCollection3 = phieuTraKhoCollection3;
//    }

//    public Collection<CtToaThuock> getCtToaThuockCollection() {
//        return ctToaThuockCollection;
//    }
//
//    public void setCtToaThuockCollection(Collection<CtToaThuock> ctToaThuockCollection) {
//        this.ctToaThuockCollection = ctToaThuockCollection;
//    }

//    public Collection<CtToaThuock> getCtToaThuockCollection1() {
//        return ctToaThuockCollection1;
//    }
//
//    public void setCtToaThuockCollection1(Collection<CtToaThuock> ctToaThuockCollection1) {
//        this.ctToaThuockCollection1 = ctToaThuockCollection1;
//    }

//    public Collection<KiemKeKho> getKiemKeKhoCollection() {
//        return kiemKeKhoCollection;
//    }
//
//    public void setKiemKeKhoCollection(Collection<KiemKeKho> kiemKeKhoCollection) {
//        this.kiemKeKhoCollection = kiemKeKhoCollection;
//    }

//    public Collection<KiemKeKho> getKiemKeKhoCollection1() {
//        return kiemKeKhoCollection1;
//    }
//
//    public void setKiemKeKhoCollection1(Collection<KiemKeKho> kiemKeKhoCollection1) {
//        this.kiemKeKhoCollection1 = kiemKeKhoCollection1;
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

//    public Collection<BenhNhan> getBenhNhanCollection() {
//        return benhNhanCollection;
//    }
//
//    public void setBenhNhanCollection(Collection<BenhNhan> benhNhanCollection) {
//        this.benhNhanCollection = benhNhanCollection;
//    }

//    public Collection<BenhNhan> getBenhNhanCollection1() {
//        return benhNhanCollection1;
//    }
//
//    public void setBenhNhanCollection1(Collection<BenhNhan> benhNhanCollection1) {
//        this.benhNhanCollection1 = benhNhanCollection1;
//    }

//    public Collection<HsbaGiayChungThuong> getHsbaGiayChungThuongCollection() {
//        return hsbaGiayChungThuongCollection;
//    }
//
//    public void setHsbaGiayChungThuongCollection(Collection<HsbaGiayChungThuong> hsbaGiayChungThuongCollection) {
//        this.hsbaGiayChungThuongCollection = hsbaGiayChungThuongCollection;
//    }

//    public Collection<HsbaGiayChungThuong> getHsbaGiayChungThuongCollection1() {
//        return hsbaGiayChungThuongCollection1;
//    }
//
//    public void setHsbaGiayChungThuongCollection1(Collection<HsbaGiayChungThuong> hsbaGiayChungThuongCollection1) {
//        this.hsbaGiayChungThuongCollection1 = hsbaGiayChungThuongCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmnhanvienMaso != null ? dtdmnhanvienMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmNhanVien)) {
            return false;
        }
        DtDmNhanVien other = (DtDmNhanVien) object;
        if ((this.dtdmnhanvienMaso == null && other.dtdmnhanvienMaso != null) || (this.dtdmnhanvienMaso != null && !this.dtdmnhanvienMaso.equals(other.dtdmnhanvienMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmNhanVien[dtdmnhanvienMaso=" + dtdmnhanvienMaso + "]";
    }

    public NguoiDung getNdMaso(boolean create) {
if(create && ndMaso == null) ndMaso = new NguoiDung();
return ndMaso;
}
    public NguoiDung getNdMaso() {
        return ndMaso;
    }

    public void setNdMaso(NguoiDung ndMaso) {
        this.ndMaso = ndMaso;
    }
}


