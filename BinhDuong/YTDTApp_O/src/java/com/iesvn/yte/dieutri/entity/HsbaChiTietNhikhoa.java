/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author halylam
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_CHI_TIET_NHIKHOA")
@NamedQueries({
    @NamedQuery(name = "HsbaChiTietNhikhoa.findAll", query = "SELECT h FROM HsbaChiTietNhikhoa h")})
public class HsbaChiTietNhikhoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_NHIKHOA")
    @SequenceGenerator(name = "HSBA_CHI_TIET_NHIKHOA", sequenceName = "HSBA_CHI_TIET_NHIKHOA_HSBACTNH", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTNHIKHOA_MA")
    private Integer hsbactnhikhoaMa;
    @Column(name = "HSBACTNHIKHOA_QTBENHLY")
    private String hsbactnhikhoaQtbenhly;
    @Column(name = "HSBACTNHIKHOA_TIENSUBENHBT")
    private String hsbactnhikhoaTiensubenhbt;
    @Column(name = "HSBACTNHIKHOA_TIENSUBENHGD")
    private String hsbactnhikhoaTiensubenhgd;
    @Column(name = "HSBACTNHIKHOA_TUANHOAN")
    private String hsbactnhikhoaTuanhoan;
    @Column(name = "HSBACTNHIKHOA_HOHAP")
    private String hsbactnhikhoaHohap;
    @Column(name = "HSBACTNHIKHOA_TIEUHOA")
    private String hsbactnhikhoaTieuhoa;
    @Column(name = "HSBACTNHIKHOA_THANTIETNIEUSHOC")
    private String hsbactnhikhoaThantietnieusinhhoc;
    @Column(name = "HSBACTNHIKHOA_THANKINH")
    private String hsbactnhikhoaThankinh;
    @Column(name = "HSBACTNHIKHOA_COXUONGKHOP")
    private String hsbactnhikhoaCoxuongkhop;
    @Column(name = "HSBACTNHIKHOA_TMH_RHM_MAT_DDK")
    private String hsbactnhikhoaTmhRhmMatDinhduongKhac;
    @Column(name = "HSBACTNHIKHOA_TTBA")
    private String hsbactnhikhoaTtba;
    @Column(name = "HSBACTNHIKHOA_TIENLUONG")
    private String hsbactnhikhoaTienluong;
    @Column(name = "HSBACTNHIKHOA_LYDOVAOV")
    private String hsbactnhikhoaLydovaov;
    @Column(name = "HSBACTNHIKHOA_NGAYBENHTHU")
    private Integer hsbactnhikhoaNgaybenhthu;
    @Column(name = "HSBACTNHIKHOA_TOANTHAN")
    private String hsbactnhikhoaToanthan;
    @Column(name = "HSBACTNHIKHOA_QTBL_DBLS")
    private String hsbactnhikhoaQtblDbls;
    @Column(name = "HSBACTNHIKHOA_TTKQXNCLS")
    private String hsbactnhikhoaTtkqxncls;
    @Column(name = "HSBACTNHIKHOA_PPDIEUTRI")
    private String hsbactnhikhoaPpdieutri;
    @Column(name = "HSBACTNHIKHOA_TTNGUOIBENHRAV")
    private String hsbactnhikhoaTtnguoibenhrav;
    @Column(name = "HSBACTNHIKHOA_HUONGDT_CDTT")
    private String hsbactnhikhoaHuongdtCdtt;
    @Column(name = "HSBACTNHIKHOA_SOTOXQUANG")
    private Integer hsbactnhikhoaSotoxquang;
    @Column(name = "HSBACTNHIKHOA_SOTOCTSCANNER")
    private Integer hsbactnhikhoaSotoctscanner;
    @Column(name = "HSBACTNHIKHOA_SOTOSIEUAM")
    private Integer hsbactnhikhoaSotosieuam;
    @Column(name = "HSBACTNHIKHOA_SOTOXN")
    private Integer hsbactnhikhoaSotoxn;
    @Column(name = "HSBACTNHIKHOA_SOTOKHAC")
    private Integer hsbactnhikhoaSotokhac;
    @Column(name = "HSBACTNHIKHOA_SOTOLOAIKHAC")
    private String hsbactnhikhoaSotoloaikhac;
    @Column(name = "HSBACTNHIKHOA_TONGSOTO")
    private Integer hsbactnhikhoaTongsoto;
    @Column(name = "HSBACTNHIKHOA_HOTEN_BO")
    private String hsbactnhikhoaHotenBo;
    @Column(name = "HSBACTNHIKHOA_HOTEN_ME")
    private String hsbactnhikhoaHotenMe;
    @Column(name = "HSBACTNHIKHOA_TDVH_BO")
    private String hsbactnhikhoaTdvhBo;
    @Column(name = "HSBACTNHIKHOA_TDVH_ME")
    private String hsbactnhikhoaTdvhMe;
    @Column(name = "HSBACTNHIKHOA_NGHENGHIEP_BO")
    private String hsbactnhikhoaNghenghiepBo;
    @Column(name = "HSBACTNHIKHOA_NGHENGHIEP_ME")
    private String hsbactnhikhoaNghenghiepMe;
    @Column(name = "HSBACTNHIKHOA_CONTHUMAY")
    private String hsbactnhikhoaConthumay;
    @Column(name = "HSBACTNHIKHOA_SINHDUTHANG")
    private String hsbactnhikhoaSinhduthang;
    @Column(name = "HSBACTNHIKHOA_SINHDENON")
    private String hsbactnhikhoaSinhdenon;
    @Column(name = "HSBACTNHIKHOA_SINHNAOHUT")
    private String hsbactnhikhoaSinhnaohut;
    @Column(name = "HSBACTNHIKHOA_SINHSONG")
    private String hsbactnhikhoaSinhsong;
    @Column(name = "HSBACTNHIKHOA_DETHUONG")
    private Boolean hsbactnhikhoaDethuong;
    @Column(name = "HSBACTNHIKHOA_FORCEPS")
    private Boolean hsbactnhikhoaForceps;
    @Column(name = "HSBACTNHIKHOA_GIACHUT")
    private Boolean hsbactnhikhoaGiachut;
    @Column(name = "HSBACTNHIKHOA_DEPHAUTHUAT")
    private Boolean hsbactnhikhoaDephauthuat;
    @Column(name = "HSBACTNHIKHOA_DECHIHUY")
    private Boolean hsbactnhikhoaDechihuy;
    @Column(name = "HSBACTNHIKHOA_DEKHAC")
    private Boolean hsbactnhikhoaDekhac;
    @Column(name = "HSBACTNHIKHOA_CANNANGLUCSINH")
    private String hsbactnhikhoaCannanglucsinh;
    @Column(name = "HSBACTNHIKHOA_DITATBAMSINH")
    private Boolean hsbactnhikhoaDitatbamsinh;
    @Column(name = "HSBACTNHIKHOA_TATBAMSINH")
    private String hsbactnhikhoaTatbamsinh;
    @Column(name = "HSBACTNHIKHOA_PT_TINHTHAN")
    private String hsbactnhikhoaPtTinhthan;
    @Column(name = "HSBACTNHIKHOA_PT_VANDONG")
    private String hsbactnhikhoaPtVandong;
    @Column(name = "HSBACTNHIKHOA_SINHTRUONG_BLK")
    private String hsbactnhikhoaSinhtruongBlkhac;
    @Column(name = "HSBACTNHIKHOA_NUOIDUONG_SUAME")
    private Boolean hsbactnhikhoaNuoiduongSuame;
    @Column(name = "HSBACTNHIKHOA_NUOIDUONG_NTAO")
    private Boolean hsbactnhikhoaNuoiduongNhantao;
    @Column(name = "HSBACTNHIKHOA_NUOIDUONG_HONHOP")
    private Boolean hsbactnhikhoaNuoiduongHonhop;
    @Column(name = "HSBACTNHIKHOA_CAISUATHANGTHU")
    private String hsbactnhikhoaCaisuathangthu;
    @Column(name = "HSBACTNHIKHOA_CHAMSOC_TNHATRE")
    private Boolean hsbactnhikhoaChamsocTainhatre;
    @Column(name = "HSBACTNHIKHOA_CHAMSOC_TAINHA")
    private Boolean hsbactnhikhoaChamsocTainha;
    @Column(name = "HSBACTNHIKHOA_TIEMCHUNG_LAO")
    private Boolean hsbactnhikhoaTiemchungLao;
    @Column(name = "HSBACTNHIKHOA_TIEMCHUNG_BLIET")
    private Boolean hsbactnhikhoaTiemchungBailiet;
    @Column(name = "HSBACTNHIKHOA_TIEMCHUNG_SOI")
    private Boolean hsbactnhikhoaTiemchungSoi;
    @Column(name = "HSBACTNHIKHOA_TIEMCHUNG_HOGA")
    private Boolean hsbactnhikhoaTiemchungHoga;
    @Column(name = "HSBACTNHIKHOA_TIEMCHUNG_UONVAN")
    private Boolean hsbactnhikhoaTiemchungUonvan;
    @Column(name = "HSBACTNHIKHOA_TIEMCHUNG_BHAU")
    private Boolean hsbactnhikhoaTiemchungBachhau;
    @Column(name = "HSBACTNHIKHOA_TIEMCHUNG_KHAC")
    private Boolean hsbactnhikhoaTiemchungKhac;
    @Column(name = "HSBACTNHIKHOA_BKDUOCTIEMCHUNG")
    private String hsbactnhikhoaBenhkhacduoctiemchung;
    @Column(name = "HSBACTNHIKHOA_CHIEUCAO")
    private String hsbactnhikhoaChieucao;
    @Column(name = "HSBACTNHIKHOA_VONGNGUC")
    private String hsbactnhikhoaVongnguc;
    @Column(name = "HSBACTNHIKHOA_VONGDAU")
    private String hsbactnhikhoaVongdau;
    @Column(name = "HSBACTNHIKHOA_XETNGHIEMCANLAM")
    private String hsbactnhikhoaXetnghiemcanlam;
    @Column(name = "HSBACTNHIKHOA_HUONGDIEUTRI")
    private String hsbactnhikhoaHuongdieutri;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;
    @JoinColumn(name = "HSBACTNHIKHOA_BSLAMBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactnhikhoaBslamba;
    @JoinColumn(name = "HSBACTNHIKHOA_NGUOIGIAOBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactnhikhoaNguoigiaoba;
    @JoinColumn(name = "HSBACTNHIKHOA_NGUOINHANBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactnhikhoaNguoinhanba;
    @JoinColumn(name = "HSBACTNHIKHOA_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactnhikhoaBsdieutri;

    public HsbaChiTietNhikhoa() {
    }

    public HsbaChiTietNhikhoa(Integer hsbactnhikhoaMa) {
        this.hsbactnhikhoaMa = hsbactnhikhoaMa;
    }

    public Integer getHsbactnhikhoaMa() {
        return hsbactnhikhoaMa;
    }

    public void setHsbactnhikhoaMa(Integer hsbactnhikhoaMa) {
        this.hsbactnhikhoaMa = hsbactnhikhoaMa;
    }

    public String getHsbactnhikhoaQtbenhly() {
        return hsbactnhikhoaQtbenhly;
    }

    public void setHsbactnhikhoaQtbenhly(String hsbactnhikhoaQtbenhly) {
        this.hsbactnhikhoaQtbenhly = hsbactnhikhoaQtbenhly;
    }

    public String getHsbactnhikhoaTiensubenhbt() {
        return hsbactnhikhoaTiensubenhbt;
    }

    public void setHsbactnhikhoaTiensubenhbt(String hsbactnhikhoaTiensubenhbt) {
        this.hsbactnhikhoaTiensubenhbt = hsbactnhikhoaTiensubenhbt;
    }

    public String getHsbactnhikhoaTiensubenhgd() {
        return hsbactnhikhoaTiensubenhgd;
    }

    public void setHsbactnhikhoaTiensubenhgd(String hsbactnhikhoaTiensubenhgd) {
        this.hsbactnhikhoaTiensubenhgd = hsbactnhikhoaTiensubenhgd;
    }

    public String getHsbactnhikhoaTuanhoan() {
        return hsbactnhikhoaTuanhoan;
    }

    public void setHsbactnhikhoaTuanhoan(String hsbactnhikhoaTuanhoan) {
        this.hsbactnhikhoaTuanhoan = hsbactnhikhoaTuanhoan;
    }

    public String getHsbactnhikhoaHohap() {
        return hsbactnhikhoaHohap;
    }

    public void setHsbactnhikhoaHohap(String hsbactnhikhoaHohap) {
        this.hsbactnhikhoaHohap = hsbactnhikhoaHohap;
    }

    public String getHsbactnhikhoaTieuhoa() {
        return hsbactnhikhoaTieuhoa;
    }

    public void setHsbactnhikhoaTieuhoa(String hsbactnhikhoaTieuhoa) {
        this.hsbactnhikhoaTieuhoa = hsbactnhikhoaTieuhoa;
    }

    public String getHsbactnhikhoaThantietnieusinhhoc() {
        return hsbactnhikhoaThantietnieusinhhoc;
    }

    public void setHsbactnhikhoaThantietnieusinhhoc(String hsbactnhikhoaThantietnieusinhhoc) {
        this.hsbactnhikhoaThantietnieusinhhoc = hsbactnhikhoaThantietnieusinhhoc;
    }

    public String getHsbactnhikhoaThankinh() {
        return hsbactnhikhoaThankinh;
    }

    public void setHsbactnhikhoaThankinh(String hsbactnhikhoaThankinh) {
        this.hsbactnhikhoaThankinh = hsbactnhikhoaThankinh;
    }

    public String getHsbactnhikhoaCoxuongkhop() {
        return hsbactnhikhoaCoxuongkhop;
    }

    public void setHsbactnhikhoaCoxuongkhop(String hsbactnhikhoaCoxuongkhop) {
        this.hsbactnhikhoaCoxuongkhop = hsbactnhikhoaCoxuongkhop;
    }

    public String getHsbactnhikhoaTmhRhmMatDinhduongKhac() {
        return hsbactnhikhoaTmhRhmMatDinhduongKhac;
    }

    public void setHsbactnhikhoaTmhRhmMatDinhduongKhac(String hsbactnhikhoaTmhRhmMatDinhduongKhac) {
        this.hsbactnhikhoaTmhRhmMatDinhduongKhac = hsbactnhikhoaTmhRhmMatDinhduongKhac;
    }

    public String getHsbactnhikhoaTtba() {
        return hsbactnhikhoaTtba;
    }

    public void setHsbactnhikhoaTtba(String hsbactnhikhoaTtba) {
        this.hsbactnhikhoaTtba = hsbactnhikhoaTtba;
    }

    public String getHsbactnhikhoaTienluong() {
        return hsbactnhikhoaTienluong;
    }

    public void setHsbactnhikhoaTienluong(String hsbactnhikhoaTienluong) {
        this.hsbactnhikhoaTienluong = hsbactnhikhoaTienluong;
    }

    public String getHsbactnhikhoaLydovaov() {
        return hsbactnhikhoaLydovaov;
    }

    public void setHsbactnhikhoaLydovaov(String hsbactnhikhoaLydovaov) {
        this.hsbactnhikhoaLydovaov = hsbactnhikhoaLydovaov;
    }

    public Integer getHsbactnhikhoaNgaybenhthu() {
        return hsbactnhikhoaNgaybenhthu;
    }

    public void setHsbactnhikhoaNgaybenhthu(Integer hsbactnhikhoaNgaybenhthu) {
        this.hsbactnhikhoaNgaybenhthu = hsbactnhikhoaNgaybenhthu;
    }

    public String getHsbactnhikhoaToanthan() {
        return hsbactnhikhoaToanthan;
    }

    public void setHsbactnhikhoaToanthan(String hsbactnhikhoaToanthan) {
        this.hsbactnhikhoaToanthan = hsbactnhikhoaToanthan;
    }

    public String getHsbactnhikhoaQtblDbls() {
        return hsbactnhikhoaQtblDbls;
    }

    public void setHsbactnhikhoaQtblDbls(String hsbactnhikhoaQtblDbls) {
        this.hsbactnhikhoaQtblDbls = hsbactnhikhoaQtblDbls;
    }

    public String getHsbactnhikhoaTtkqxncls() {
        return hsbactnhikhoaTtkqxncls;
    }

    public void setHsbactnhikhoaTtkqxncls(String hsbactnhikhoaTtkqxncls) {
        this.hsbactnhikhoaTtkqxncls = hsbactnhikhoaTtkqxncls;
    }

    public String getHsbactnhikhoaPpdieutri() {
        return hsbactnhikhoaPpdieutri;
    }

    public void setHsbactnhikhoaPpdieutri(String hsbactnhikhoaPpdieutri) {
        this.hsbactnhikhoaPpdieutri = hsbactnhikhoaPpdieutri;
    }

    public String getHsbactnhikhoaTtnguoibenhrav() {
        return hsbactnhikhoaTtnguoibenhrav;
    }

    public void setHsbactnhikhoaTtnguoibenhrav(String hsbactnhikhoaTtnguoibenhrav) {
        this.hsbactnhikhoaTtnguoibenhrav = hsbactnhikhoaTtnguoibenhrav;
    }

    public String getHsbactnhikhoaHuongdtCdtt() {
        return hsbactnhikhoaHuongdtCdtt;
    }

    public void setHsbactnhikhoaHuongdtCdtt(String hsbactnhikhoaHuongdtCdtt) {
        this.hsbactnhikhoaHuongdtCdtt = hsbactnhikhoaHuongdtCdtt;
    }

    public Integer getHsbactnhikhoaSotoxquang() {
        return hsbactnhikhoaSotoxquang;
    }

    public void setHsbactnhikhoaSotoxquang(Integer hsbactnhikhoaSotoxquang) {
        this.hsbactnhikhoaSotoxquang = hsbactnhikhoaSotoxquang;
    }

    public Integer getHsbactnhikhoaSotoctscanner() {
        return hsbactnhikhoaSotoctscanner;
    }

    public void setHsbactnhikhoaSotoctscanner(Integer hsbactnhikhoaSotoctscanner) {
        this.hsbactnhikhoaSotoctscanner = hsbactnhikhoaSotoctscanner;
    }

    public Integer getHsbactnhikhoaSotosieuam() {
        return hsbactnhikhoaSotosieuam;
    }

    public void setHsbactnhikhoaSotosieuam(Integer hsbactnhikhoaSotosieuam) {
        this.hsbactnhikhoaSotosieuam = hsbactnhikhoaSotosieuam;
    }

    public Integer getHsbactnhikhoaSotoxn() {
        return hsbactnhikhoaSotoxn;
    }

    public void setHsbactnhikhoaSotoxn(Integer hsbactnhikhoaSotoxn) {
        this.hsbactnhikhoaSotoxn = hsbactnhikhoaSotoxn;
    }

    public Integer getHsbactnhikhoaSotokhac() {
        return hsbactnhikhoaSotokhac;
    }

    public void setHsbactnhikhoaSotokhac(Integer hsbactnhikhoaSotokhac) {
        this.hsbactnhikhoaSotokhac = hsbactnhikhoaSotokhac;
    }

    public String getHsbactnhikhoaSotoloaikhac() {
        return hsbactnhikhoaSotoloaikhac;
    }

    public void setHsbactnhikhoaSotoloaikhac(String hsbactnhikhoaSotoloaikhac) {
        this.hsbactnhikhoaSotoloaikhac = hsbactnhikhoaSotoloaikhac;
    }

    public Integer getHsbactnhikhoaTongsoto() {
        return hsbactnhikhoaTongsoto;
    }

    public void setHsbactnhikhoaTongsoto(Integer hsbactnhikhoaTongsoto) {
        this.hsbactnhikhoaTongsoto = hsbactnhikhoaTongsoto;
    }

    public String getHsbactnhikhoaHotenBo() {
        return hsbactnhikhoaHotenBo;
    }

    public void setHsbactnhikhoaHotenBo(String hsbactnhikhoaHotenBo) {
        this.hsbactnhikhoaHotenBo = hsbactnhikhoaHotenBo;
    }

    public String getHsbactnhikhoaHotenMe() {
        return hsbactnhikhoaHotenMe;
    }

    public void setHsbactnhikhoaHotenMe(String hsbactnhikhoaHotenMe) {
        this.hsbactnhikhoaHotenMe = hsbactnhikhoaHotenMe;
    }

    public String getHsbactnhikhoaTdvhBo() {
        return hsbactnhikhoaTdvhBo;
    }

    public void setHsbactnhikhoaTdvhBo(String hsbactnhikhoaTdvhBo) {
        this.hsbactnhikhoaTdvhBo = hsbactnhikhoaTdvhBo;
    }

    public String getHsbactnhikhoaTdvhMe() {
        return hsbactnhikhoaTdvhMe;
    }

    public void setHsbactnhikhoaTdvhMe(String hsbactnhikhoaTdvhMe) {
        this.hsbactnhikhoaTdvhMe = hsbactnhikhoaTdvhMe;
    }

    public String getHsbactnhikhoaNghenghiepBo() {
        return hsbactnhikhoaNghenghiepBo;
    }

    public void setHsbactnhikhoaNghenghiepBo(String hsbactnhikhoaNghenghiepBo) {
        this.hsbactnhikhoaNghenghiepBo = hsbactnhikhoaNghenghiepBo;
    }

    public String getHsbactnhikhoaNghenghiepMe() {
        return hsbactnhikhoaNghenghiepMe;
    }

    public void setHsbactnhikhoaNghenghiepMe(String hsbactnhikhoaNghenghiepMe) {
        this.hsbactnhikhoaNghenghiepMe = hsbactnhikhoaNghenghiepMe;
    }

    public String getHsbactnhikhoaConthumay() {
        return hsbactnhikhoaConthumay;
    }

    public void setHsbactnhikhoaConthumay(String hsbactnhikhoaConthumay) {
        this.hsbactnhikhoaConthumay = hsbactnhikhoaConthumay;
    }

    public String getHsbactnhikhoaSinhduthang() {
        return hsbactnhikhoaSinhduthang;
    }

    public void setHsbactnhikhoaSinhduthang(String hsbactnhikhoaSinhduthang) {
        this.hsbactnhikhoaSinhduthang = hsbactnhikhoaSinhduthang;
    }

    public String getHsbactnhikhoaSinhdenon() {
        return hsbactnhikhoaSinhdenon;
    }

    public void setHsbactnhikhoaSinhdenon(String hsbactnhikhoaSinhdenon) {
        this.hsbactnhikhoaSinhdenon = hsbactnhikhoaSinhdenon;
    }

    public String getHsbactnhikhoaSinhnaohut() {
        return hsbactnhikhoaSinhnaohut;
    }

    public void setHsbactnhikhoaSinhnaohut(String hsbactnhikhoaSinhnaohut) {
        this.hsbactnhikhoaSinhnaohut = hsbactnhikhoaSinhnaohut;
    }

    public String getHsbactnhikhoaSinhsong() {
        return hsbactnhikhoaSinhsong;
    }

    public void setHsbactnhikhoaSinhsong(String hsbactnhikhoaSinhsong) {
        this.hsbactnhikhoaSinhsong = hsbactnhikhoaSinhsong;
    }

    public Boolean getHsbactnhikhoaDethuong() {
        return hsbactnhikhoaDethuong;
    }

    public void setHsbactnhikhoaDethuong(Boolean hsbactnhikhoaDethuong) {
        this.hsbactnhikhoaDethuong = hsbactnhikhoaDethuong;
    }

    public Boolean getHsbactnhikhoaForceps() {
        return hsbactnhikhoaForceps;
    }

    public void setHsbactnhikhoaForceps(Boolean hsbactnhikhoaForceps) {
        this.hsbactnhikhoaForceps = hsbactnhikhoaForceps;
    }

    public Boolean getHsbactnhikhoaGiachut() {
        return hsbactnhikhoaGiachut;
    }

    public void setHsbactnhikhoaGiachut(Boolean hsbactnhikhoaGiachut) {
        this.hsbactnhikhoaGiachut = hsbactnhikhoaGiachut;
    }

    public Boolean getHsbactnhikhoaDephauthuat() {
        return hsbactnhikhoaDephauthuat;
    }

    public void setHsbactnhikhoaDephauthuat(Boolean hsbactnhikhoaDephauthuat) {
        this.hsbactnhikhoaDephauthuat = hsbactnhikhoaDephauthuat;
    }

    public Boolean getHsbactnhikhoaDechihuy() {
        return hsbactnhikhoaDechihuy;
    }

    public void setHsbactnhikhoaDechihuy(Boolean hsbactnhikhoaDechihuy) {
        this.hsbactnhikhoaDechihuy = hsbactnhikhoaDechihuy;
    }

    public Boolean getHsbactnhikhoaDekhac() {
        return hsbactnhikhoaDekhac;
    }

    public void setHsbactnhikhoaDekhac(Boolean hsbactnhikhoaDekhac) {
        this.hsbactnhikhoaDekhac = hsbactnhikhoaDekhac;
    }

    public String getHsbactnhikhoaCannanglucsinh() {
        return hsbactnhikhoaCannanglucsinh;
    }

    public void setHsbactnhikhoaCannanglucsinh(String hsbactnhikhoaCannanglucsinh) {
        this.hsbactnhikhoaCannanglucsinh = hsbactnhikhoaCannanglucsinh;
    }

    public Boolean getHsbactnhikhoaDitatbamsinh() {
        return hsbactnhikhoaDitatbamsinh;
    }

    public void setHsbactnhikhoaDitatbamsinh(Boolean hsbactnhikhoaDitatbamsinh) {
        this.hsbactnhikhoaDitatbamsinh = hsbactnhikhoaDitatbamsinh;
    }

    public String getHsbactnhikhoaTatbamsinh() {
        return hsbactnhikhoaTatbamsinh;
    }

    public void setHsbactnhikhoaTatbamsinh(String hsbactnhikhoaTatbamsinh) {
        this.hsbactnhikhoaTatbamsinh = hsbactnhikhoaTatbamsinh;
    }

    public String getHsbactnhikhoaPtTinhthan() {
        return hsbactnhikhoaPtTinhthan;
    }

    public void setHsbactnhikhoaPtTinhthan(String hsbactnhikhoaPtTinhthan) {
        this.hsbactnhikhoaPtTinhthan = hsbactnhikhoaPtTinhthan;
    }

    public String getHsbactnhikhoaPtVandong() {
        return hsbactnhikhoaPtVandong;
    }

    public void setHsbactnhikhoaPtVandong(String hsbactnhikhoaPtVandong) {
        this.hsbactnhikhoaPtVandong = hsbactnhikhoaPtVandong;
    }

    public String getHsbactnhikhoaSinhtruongBlkhac() {
        return hsbactnhikhoaSinhtruongBlkhac;
    }

    public void setHsbactnhikhoaSinhtruongBlkhac(String hsbactnhikhoaSinhtruongBlkhac) {
        this.hsbactnhikhoaSinhtruongBlkhac = hsbactnhikhoaSinhtruongBlkhac;
    }

    public Boolean getHsbactnhikhoaNuoiduongSuame() {
        return hsbactnhikhoaNuoiduongSuame;
    }

    public void setHsbactnhikhoaNuoiduongSuame(Boolean hsbactnhikhoaNuoiduongSuame) {
        this.hsbactnhikhoaNuoiduongSuame = hsbactnhikhoaNuoiduongSuame;
    }

    public Boolean getHsbactnhikhoaNuoiduongNhantao() {
        return hsbactnhikhoaNuoiduongNhantao;
    }

    public void setHsbactnhikhoaNuoiduongNhantao(Boolean hsbactnhikhoaNuoiduongNhantao) {
        this.hsbactnhikhoaNuoiduongNhantao = hsbactnhikhoaNuoiduongNhantao;
    }

    public Boolean getHsbactnhikhoaNuoiduongHonhop() {
        return hsbactnhikhoaNuoiduongHonhop;
    }

    public void setHsbactnhikhoaNuoiduongHonhop(Boolean hsbactnhikhoaNuoiduongHonhop) {
        this.hsbactnhikhoaNuoiduongHonhop = hsbactnhikhoaNuoiduongHonhop;
    }

    public String getHsbactnhikhoaCaisuathangthu() {
        return hsbactnhikhoaCaisuathangthu;
    }

    public void setHsbactnhikhoaCaisuathangthu(String hsbactnhikhoaCaisuathangthu) {
        this.hsbactnhikhoaCaisuathangthu = hsbactnhikhoaCaisuathangthu;
    }

    public Boolean getHsbactnhikhoaChamsocTainhatre() {
        return hsbactnhikhoaChamsocTainhatre;
    }

    public void setHsbactnhikhoaChamsocTainhatre(Boolean hsbactnhikhoaChamsocTainhatre) {
        this.hsbactnhikhoaChamsocTainhatre = hsbactnhikhoaChamsocTainhatre;
    }

    public Boolean getHsbactnhikhoaChamsocTainha() {
        return hsbactnhikhoaChamsocTainha;
    }

    public void setHsbactnhikhoaChamsocTainha(Boolean hsbactnhikhoaChamsocTainha) {
        this.hsbactnhikhoaChamsocTainha = hsbactnhikhoaChamsocTainha;
    }

    public Boolean getHsbactnhikhoaTiemchungLao() {
        return hsbactnhikhoaTiemchungLao;
    }

    public void setHsbactnhikhoaTiemchungLao(Boolean hsbactnhikhoaTiemchungLao) {
        this.hsbactnhikhoaTiemchungLao = hsbactnhikhoaTiemchungLao;
    }

    public Boolean getHsbactnhikhoaTiemchungBailiet() {
        return hsbactnhikhoaTiemchungBailiet;
    }

    public void setHsbactnhikhoaTiemchungBailiet(Boolean hsbactnhikhoaTiemchungBailiet) {
        this.hsbactnhikhoaTiemchungBailiet = hsbactnhikhoaTiemchungBailiet;
    }

    public Boolean getHsbactnhikhoaTiemchungSoi() {
        return hsbactnhikhoaTiemchungSoi;
    }

    public void setHsbactnhikhoaTiemchungSoi(Boolean hsbactnhikhoaTiemchungSoi) {
        this.hsbactnhikhoaTiemchungSoi = hsbactnhikhoaTiemchungSoi;
    }

    public Boolean getHsbactnhikhoaTiemchungHoga() {
        return hsbactnhikhoaTiemchungHoga;
    }

    public void setHsbactnhikhoaTiemchungHoga(Boolean hsbactnhikhoaTiemchungHoga) {
        this.hsbactnhikhoaTiemchungHoga = hsbactnhikhoaTiemchungHoga;
    }

    public Boolean getHsbactnhikhoaTiemchungUonvan() {
        return hsbactnhikhoaTiemchungUonvan;
    }

    public void setHsbactnhikhoaTiemchungUonvan(Boolean hsbactnhikhoaTiemchungUonvan) {
        this.hsbactnhikhoaTiemchungUonvan = hsbactnhikhoaTiemchungUonvan;
    }

    public Boolean getHsbactnhikhoaTiemchungBachhau() {
        return hsbactnhikhoaTiemchungBachhau;
    }

    public void setHsbactnhikhoaTiemchungBachhau(Boolean hsbactnhikhoaTiemchungBachhau) {
        this.hsbactnhikhoaTiemchungBachhau = hsbactnhikhoaTiemchungBachhau;
    }

    public Boolean getHsbactnhikhoaTiemchungKhac() {
        return hsbactnhikhoaTiemchungKhac;
    }

    public void setHsbactnhikhoaTiemchungKhac(Boolean hsbactnhikhoaTiemchungKhac) {
        this.hsbactnhikhoaTiemchungKhac = hsbactnhikhoaTiemchungKhac;
    }

    public String getHsbactnhikhoaBenhkhacduoctiemchung() {
        return hsbactnhikhoaBenhkhacduoctiemchung;
    }

    public void setHsbactnhikhoaBenhkhacduoctiemchung(String hsbactnhikhoaBenhkhacduoctiemchung) {
        this.hsbactnhikhoaBenhkhacduoctiemchung = hsbactnhikhoaBenhkhacduoctiemchung;
    }

    public String getHsbactnhikhoaChieucao() {
        return hsbactnhikhoaChieucao;
    }

    public void setHsbactnhikhoaChieucao(String hsbactnhikhoaChieucao) {
        this.hsbactnhikhoaChieucao = hsbactnhikhoaChieucao;
    }

    public String getHsbactnhikhoaVongnguc() {
        return hsbactnhikhoaVongnguc;
    }

    public void setHsbactnhikhoaVongnguc(String hsbactnhikhoaVongnguc) {
        this.hsbactnhikhoaVongnguc = hsbactnhikhoaVongnguc;
    }

    public String getHsbactnhikhoaVongdau() {
        return hsbactnhikhoaVongdau;
    }

    public void setHsbactnhikhoaVongdau(String hsbactnhikhoaVongdau) {
        this.hsbactnhikhoaVongdau = hsbactnhikhoaVongdau;
    }

    public String getHsbactnhikhoaXetnghiemcanlam() {
        return hsbactnhikhoaXetnghiemcanlam;
    }

    public void setHsbactnhikhoaXetnghiemcanlam(String hsbactnhikhoaXetnghiemcanlam) {
        this.hsbactnhikhoaXetnghiemcanlam = hsbactnhikhoaXetnghiemcanlam;
    }

    public String getHsbactnhikhoaHuongdieutri() {
        return hsbactnhikhoaHuongdieutri;
    }

    public void setHsbactnhikhoaHuongdieutri(String hsbactnhikhoaHuongdieutri) {
        this.hsbactnhikhoaHuongdieutri = hsbactnhikhoaHuongdieutri;
    }

    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    public DtDmNhanVien getHsbactnhikhoaBslamba() {
        return hsbactnhikhoaBslamba;
    }

    public void setHsbactnhikhoaBslamba(DtDmNhanVien hsbactnhikhoaBslamba) {
        this.hsbactnhikhoaBslamba = hsbactnhikhoaBslamba;
    }

    public DtDmNhanVien getHsbactnhikhoaNguoigiaoba() {
        return hsbactnhikhoaNguoigiaoba;
    }

    public void setHsbactnhikhoaNguoigiaoba(DtDmNhanVien hsbactnhikhoaNguoigiaoba) {
        this.hsbactnhikhoaNguoigiaoba = hsbactnhikhoaNguoigiaoba;
    }

    public DtDmNhanVien getHsbactnhikhoaNguoinhanba() {
        return hsbactnhikhoaNguoinhanba;
    }

    public void setHsbactnhikhoaNguoinhanba(DtDmNhanVien hsbactnhikhoaNguoinhanba) {
        this.hsbactnhikhoaNguoinhanba = hsbactnhikhoaNguoinhanba;
    }

    public DtDmNhanVien getHsbactnhikhoaBsdieutri() {
        return hsbactnhikhoaBsdieutri;
    }

    public void setHsbactnhikhoaBsdieutri(DtDmNhanVien hsbactnhikhoaBsdieutri) {
        this.hsbactnhikhoaBsdieutri = hsbactnhikhoaBsdieutri;
    }
    
    public DtDmNhanVien getHsbactnhikhoaBslamba(boolean create) {
        if (create && hsbactnhikhoaBslamba == null) {
            hsbactnhikhoaBslamba = new DtDmNhanVien();
        }
        return hsbactnhikhoaBslamba;
    }

    public DtDmNhanVien getHsbactnhikhoaNguoigiaoba(boolean create) {
        if (create && hsbactnhikhoaNguoigiaoba == null) {
            hsbactnhikhoaNguoigiaoba = new DtDmNhanVien();
        }
        return hsbactnhikhoaNguoigiaoba;
    }

    public DtDmNhanVien getHsbactnhikhoaNguoinhanba(boolean create) {
        if (create && hsbactnhikhoaNguoinhanba == null) {
            hsbactnhikhoaNguoinhanba = new DtDmNhanVien();
        }
        return hsbactnhikhoaNguoinhanba;
    }

    public DtDmNhanVien getHsbactnhikhoaBsdieutri(boolean create) {
        if (create && hsbactnhikhoaBsdieutri == null) {
            hsbactnhikhoaBsdieutri = new DtDmNhanVien();
        }
        return hsbactnhikhoaBsdieutri;
    }

    public HsbaChuyenMon getHsbacmMa(boolean create) {
        if (create && getHsbacmMa() == null) {
            setHsbacmMa(new HsbaChuyenMon());
        }
        return getHsbacmMa();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbactnhikhoaMa != null ? hsbactnhikhoaMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietNhikhoa)) {
            return false;
        }
        HsbaChiTietNhikhoa other = (HsbaChiTietNhikhoa) object;
        if ((this.hsbactnhikhoaMa == null && other.hsbactnhikhoaMa != null) || (this.hsbactnhikhoaMa != null && !this.hsbactnhikhoaMa.equals(other.hsbactnhikhoaMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.test.HsbaChiTietNhikhoa[hsbactnhikhoaMa=" + hsbactnhikhoaMa + "]";
    }

}
