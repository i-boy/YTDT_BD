/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmThuoc;
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
 * @author quang
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_CHI_TIET_MAT")
@NamedQueries({
    @NamedQuery(name = "HsbaChiTietMat.findAll", query = "SELECT h FROM HsbaChiTietMat h"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatMa", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatMa = :hsbactmatMa"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatQtbenhly", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatQtbenhly = :hsbactmatQtbenhly"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatTiensubenhbt", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatTiensubenhbt = :hsbactmatTiensubenhbt"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatTiensubenhgd", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatTiensubenhgd = :hsbactmatTiensubenhgd"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdDiung", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdDiung = :hsbactmatDdDiung"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdMatuy", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdMatuy = :hsbactmatDdMatuy"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdRuoubia", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdRuoubia = :hsbactmatDdRuoubia"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdThuocla", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdThuocla = :hsbactmatDdThuocla"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdThuoclao", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdThuoclao = :hsbactmatDdThuoclao"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdKhac", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdKhac = :hsbactmatDdKhac"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdDiungTg", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdDiungTg = :hsbactmatDdDiungTg"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdMatuyTg", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdMatuyTg = :hsbactmatDdMatuyTg"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdRuoubiaTg", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdRuoubiaTg = :hsbactmatDdRuoubiaTg"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdThuoclaTg", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdThuoclaTg = :hsbactmatDdThuoclaTg"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdThuoclaoTg", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdThuoclaoTg = :hsbactmatDdThuoclaoTg"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatDdKhacTg", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatDdKhacTg = :hsbactmatDdKhacTg"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatTuanhoan", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatTuanhoan = :hsbactmatTuanhoan"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatHohap", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatHohap = :hsbactmatHohap"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatTieuhoa", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatTieuhoa = :hsbactmatTieuhoa"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatThantietnieusinhhoc", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatThantietnieusinhhoc = :hsbactmatThantietnieusinhhoc"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatThankinh", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatThankinh = :hsbactmatThankinh"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatCoxuongkhop", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatCoxuongkhop = :hsbactmatCoxuongkhop"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatTmh", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatTmh = :hsbactmatTmh"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatRhm", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatRhm = :hsbactmatRhm"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatMat", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatMat = :hsbactmatMat"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatNtDdBlk", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatNtDdBlk = :hsbactmatNtDdBlk"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatTtba", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatTtba = :hsbactmatTtba"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatPb", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatPb = :hsbactmatPb"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatTienluong", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatTienluong = :hsbactmatTienluong"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatLydovaov", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatLydovaov = :hsbactmatLydovaov"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatNgaybenhthu", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatNgaybenhthu = :hsbactmatNgaybenhthu"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatToanthan", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatToanthan = :hsbactmatToanthan"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatQtblDbls", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatQtblDbls = :hsbactmatQtblDbls"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatTtkqxncls", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatTtkqxncls = :hsbactmatTtkqxncls"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatPpdieutri", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatPpdieutri = :hsbactmatPpdieutri"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatTtnguoibenhrav", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatTtnguoibenhrav = :hsbactmatTtnguoibenhrav"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatHuongdtCdtt", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatHuongdtCdtt = :hsbactmatHuongdtCdtt"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatSotoxquang", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatSotoxquang = :hsbactmatSotoxquang"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatSotoctscanner", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatSotoctscanner = :hsbactmatSotoctscanner"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatSotosieuam", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatSotosieuam = :hsbactmatSotosieuam"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatSotoxn", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatSotoxn = :hsbactmatSotoxn"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatSotokhac", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatSotokhac = :hsbactmatSotokhac"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatSotoloaikhac", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatSotoloaikhac = :hsbactmatSotoloaikhac"),
    @NamedQuery(name = "HsbaChiTietMat.findByHsbactmatTongsoto", query = "SELECT h FROM HsbaChiTietMat h WHERE h.hsbactmatTongsoto = :hsbactmatTongsoto")})
public class HsbaChiTietMat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_MAT")
    @SequenceGenerator(name = "HSBA_CHI_TIET_MAT", sequenceName = "HSBA_CHI_TIET_MAT_HSBACTMAT_MA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTMAT_MA")
    private Integer hsbactmatMa;
    @Column(name = "HSBACTMAT_QTBENHLY")
    private String hsbactmatQtbenhly;
    @Column(name = "HSBACTMAT_TIENSUBENHBT")
    private String hsbactmatTiensubenhbt;
    @Column(name = "HSBACTMAT_TIENSUBENHGD")
    private String hsbactmatTiensubenhgd;
    @Column(name = "HSBACTMAT_DD_DIUNG")
    private Boolean hsbactmatDdDiung;
    @Column(name = "HSBACTMAT_DD_MATUY")
    private Boolean hsbactmatDdMatuy;
    @Column(name = "HSBACTMAT_DD_RUOUBIA")
    private Boolean hsbactmatDdRuoubia;
    @Column(name = "HSBACTMAT_DD_THUOCLA")
    private Boolean hsbactmatDdThuocla;
    @Column(name = "HSBACTMAT_DD_THUOCLAO")
    private Boolean hsbactmatDdThuoclao;
    @Column(name = "HSBACTMAT_DD_KHAC")
    private Boolean hsbactmatDdKhac;
    @Column(name = "HSBACTMAT_DD_DIUNG_TG")
    private String hsbactmatDdDiungTg;
    @Column(name = "HSBACTMAT_DD_MATUY_TG")
    private String hsbactmatDdMatuyTg;
    @Column(name = "HSBACTMAT_DD_RUOUBIA_TG")
    private String hsbactmatDdRuoubiaTg;
    @Column(name = "HSBACTMAT_DD_THUOCLA_TG")
    private String hsbactmatDdThuoclaTg;
    @Column(name = "HSBACTMAT_DD_THUOCLAO_TG")
    private String hsbactmatDdThuoclaoTg;
    @Column(name = "HSBACTMAT_DD_KHAC_TG")
    private String hsbactmatDdKhacTg;
    @Column(name = "HSBACTMAT_TUANHOAN")
    private String hsbactmatTuanhoan;
    @Column(name = "HSBACTMAT_HOHAP")
    private String hsbactmatHohap;
    @Column(name = "HSBACTMAT_TIEUHOA")
    private String hsbactmatTieuhoa;
    @Column(name = "HSBACTMAT_THANTIETNIEUSINHHOC")
    private String hsbactmatThantietnieusinhhoc;
    @Column(name = "HSBACTMAT_THANKINH")
    private String hsbactmatThankinh;
    @Column(name = "HSBACTMAT_COXUONGKHOP")
    private String hsbactmatCoxuongkhop;
    @Column(name = "HSBACTMAT_TMH")
    private String hsbactmatTmh;
    @Column(name = "HSBACTMAT_RHM")
    private String hsbactmatRhm;
    @Column(name = "HSBACTMAT_MAT")
    private String hsbactmatMat;
    @Column(name = "HSBACTMAT_NT_DD_BLK")
    private String hsbactmatNtDdBlk;
    @Column(name = "HSBACTMAT_TTBA")
    private String hsbactmatTtba;
    @Column(name = "HSBACTMAT_PB")
    private Integer hsbactmatPb;
    @JoinColumn(name = "HSBACTMAT_TIENLUONG", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc hsbactmatTienluong;
    @Column(name = "HSBACTMAT_LYDOVAOV")
    private String hsbactmatLydovaov;
    @Column(name = "HSBACTMAT_NGAYBENHTHU")
    private Integer hsbactmatNgaybenhthu;
    @Column(name = "HSBACTMAT_TOANTHAN")
    private String hsbactmatToanthan;
    @Column(name = "HSBACTMAT_QTBL_DBLS")
    private String hsbactmatQtblDbls;
    @Column(name = "HSBACTMAT_TTKQXNCLS")
    private String hsbactmatTtkqxncls;
    @Column(name = "HSBACTMAT_PPDIEUTRI")
    private String hsbactmatPpdieutri;
    @Column(name = "HSBACTMAT_TTNGUOIBENHRAV")
    private String hsbactmatTtnguoibenhrav;
    @Column(name = "HSBACTMAT_HUONGDT_CDTT")
    private String hsbactmatHuongdtCdtt;
    @Column(name = "HSBACTMAT_SOTOXQUANG")
    private Integer hsbactmatSotoxquang;
    @Column(name = "HSBACTMAT_SOTOCTSCANNER")
    private Integer hsbactmatSotoctscanner;
    @Column(name = "HSBACTMAT_SOTOSIEUAM")
    private Integer hsbactmatSotosieuam;
    @Column(name = "HSBACTMAT_SOTOXN")
    private Integer hsbactmatSotoxn;
    @Column(name = "HSBACTMAT_SOTOKHAC")
    private Integer hsbactmatSotokhac;
    @Column(name = "HSBACTMAT_SOTOLOAIKHAC")
    private String hsbactmatSotoloaikhac;
    @Column(name = "HSBACTMAT_TONGSOTO")
    private Integer hsbactmatTongsoto;
    @Column(name = "HSBACTMAT_COQUANKHAC")
    private String hsbactmatCoquankhac;
     @Column(name = "HSBACTMAT_CTBENHCOQUANKHAC")
    private String hsbactmatChitietbenhcoquankhac;


    @JoinColumn(name = "HSBACTMAT_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactmatBsdieutri;
    @JoinColumn(name = "HSBACTMAT_NGUOINHANBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactmatNguoinhanba;
    @JoinColumn(name = "HSBACTMAT_NGUOIGIAOBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactmatNguoigiaoba;
    @JoinColumn(name = "HSBACTMAT_BSLAMBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactmatBslamba;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;

    @Column(name = "TLKHONGKINH_TRAI")
    private String tlkhongkinhTrai;
    @Column(name = "TLKHONGKINH_PHAI")
    private String tlkhongkinhPhai;
    @Column(name = "TLCOKINH_TRAI")
    private String tlcokinhTrai;
    @Column(name = "TLCOKINH_PHAI")
    private String tlcokinhPhai;
    @Column(name = "NHANAP_TRAI")
    private String nhanapTrai;
    @Column(name = "NHANAP_PHAI")
    private String nhanapPhai;
    @Column(name = "THITRUONG_TRAI")
    private String thitruongTrai;
    @Column(name = "THITRUONG_PHAI")
    private String thitruongPhai;
    @Column(name = "LEDAO_TRAI")
    private String ledaoTrai;
    @Column(name = "LEDAO_PHAI")
    private String ledaoPhai;
    @Column(name = "MIMAT_TRAI")
    private String mimatTrai;
    @Column(name = "MIMAT_PHAI")
    private String mimatPhai;
    @Column(name = "HOCMAT_TRAI")
    private String hocmatTrai;
    @Column(name = "HOCMAT_PHAI")
    private String hocmatPhai;
    @Column(name = "KETMAC_TRAI")
    private String ketmacTrai;
    @Column(name = "KETMAC_PHAI")
    private String ketmacPhai;
    @Column(name = "GIACMAC_TRAI")
    private String giacmacTrai;
    @Column(name = "GIACMAC_PHAI")
    private String giacmacPhai;
    @Column(name = "TIENPHONG_TRAI")
    private String tienphongTrai;
    @Column(name = "TIENPHONG_PHAI")
    private String tienphongPhai;
    @Column(name = "MONGMAT_TRAI")
    private String mongmatTrai;
    @Column(name = "MONGMAT_PHAI")
    private String mongmatPhai;
    @Column(name = "PHANXADONGTU_TRAI")
    private String phanxadongtuTrai;
    @Column(name = "PHANXADONGTU_PHAI")
    private String phanxadongtuPhai;
    @Column(name = "THETHUYTINH_TRAI")
    private String thethuytinhTrai;
    @Column(name = "THETHUYTINH_PHAI")
    private String thethuytinhPhai;
    @Column(name = "DICHKINH_TRAI")
    private String dichkinhTrai;
    @Column(name = "DICHKINH_PHAI")
    private String dichkinhPhai;
    @Column(name = "SOIANHDONGTU_TRAI")
    private String soianhdongtuTrai;
    @Column(name = "SOIANHDONGTU_PHAI")
    private String soianhdongtuPhai;
    @Column(name = "TINHHINHNHANCAU_TRAI")
    private String tinhhinhnhancauTrai;
    @Column(name = "TINHHINHNHANCAU_PHAI")
    private String tinhhinhnhancauPhai;
    @Column(name = "VANDONGNHANCAU_TRAI")
    private String vandongnhancauTrai;
    @Column(name = "VANDONGNHANCAU_PHAI")
    private String vandongnhancauPhai;
    @Column(name = "DAYMAT_TRAI")
    private String daymatTrai;
    @Column(name = "DAYMAT_PHAI")
    private String daymatPhai;
    @Column(name = "CHITIETCOQUANBENH")
    private String chitietcoquanbenh;

    @Column(name = "LEDAO_TRAIMA")
    private String ledaoTraima;
    @Column(name = "LEDAO_PHAIMA")
    private String ledaoPhaima;
    @Column(name = "MIMAT_TRAIMA")
    private String mimatTraima;
    @Column(name = "MIMAT_PHAIMA")
    private String mimatPhaima;
    @Column(name = "HOCMAT_TRAIMA")
    private String hocmatTraima;
    @Column(name = "HOCMAT_PHAIMA")
    private String hocmatPhaima;
    @Column(name = "KETMAC_TRAIMA")
    private String ketmacTraima;
    @Column(name = "KETMAC_PHAIMA")
    private String ketmacPhaima;
    @Column(name = "GIACMAC_TRAIMA")
    private String giacmacTraima;
    @Column(name = "GIACMAC_PHAIMA")
    private String giacmacPhaima;
    @Column(name = "TIENPHONG_TRAIMA")
    private String tienphongTraima;
    @Column(name = "TIENPHONG_PHAIMA")
    private String tienphongPhaima;
    @Column(name = "MONGMAT_TRAIMA")
    private String mongmatTraima;
    @Column(name = "MONGMAT_PHAIMA")
    private String mongmatPhaima;
    @Column(name = "PHANXADONGTU_TRAIMA")
    private String phanxadongtuTraima;
    @Column(name = "PHANXADONGTU_PHAIMA")
    private String phanxadongtuPhaima;
    @Column(name = "THETHUYTINH_TRAIMA")
    private String thethuytinhTraima;
    @Column(name = "THETHUYTINH_PHAIMA")
    private String thethuytinhPhaima;
    @Column(name = "DICHKINH_TRAIMA")
    private String dichkinhTraima;
    @Column(name = "DICHKINH_PHAIMA")
    private String dichkinhPhaima;
    @Column(name = "SOIANHDONGTU_TRAIMA")
    private String soianhdongtuTraima;
    @Column(name = "SOIANHDONGTU_PHAIMA")
    private String soianhdongtuPhaima;
    @Column(name = "TINHHINHNHANCAU_TRAIMA")
    private String tinhhinhnhancauTraima;
    @Column(name = "TINHHINHNHANCAU_PHAIMA")
    private String tinhhinhnhancauPhaima;
    @Column(name = "VANDONGNHANCAU_TRAIMA")
    private String vandongnhancauTraima;
    @Column(name = "VANDONGNHANCAU_PHAIMA")
    private String vandongnhancauPhaima;
    @Column(name = "DAYMAT_TRAIMA")
    private String daymatTraima;
    @Column(name = "DAYMAT_PHAIMA")
    private String daymatPhaima;


    @Column(name = "HUYETHOC_MA")
    private String huyethocMa;
    @Column(name = "HUYETHOC_KQ")
    private String huyethocKq;
    @Column(name = "HOASINH_MA")
    private String hoasinhMa;
    @Column(name = "HOASINH_KQ")
    private String hoasinhKq;
    @Column(name = "VISINH_MA")
    private String visinhMa;
    @Column(name = "VISINH_KQ")
    private String visinhKq;
    @Column(name = "XQUANG_MA")
    private String xquangMa;
    @Column(name = "XQUANG_KQ")
    private String xquangKq;
    @Column(name = "SIEUAM_MA")
    private String sieuamMa;
    @Column(name = "SIEUAM_KQ")
    private String sieuamKq;
    @Column(name = "NOISOI_MA")
    private String noisoiMa;
    @Column(name = "NOISOI_KQ")
    private String noisoiKq;
    @Column(name = "GPB_MA")
    private String gpbMa;
    @Column(name = "GPB_KQ")
    private String gpbKq;
    @Column(name = "XETNGHIEMKHAC_MA")
    private String xetnghiemkhacMa;
    @Column(name = "XETNGHIEMKHAC_KQ")
    private String xetnghiemkhacKq;
    @Column(name = "XETNGHIEM_TOMTAT")
    private String xetnghiemTomtat;

        @Column(name = "CHDANUONGBENHLY")
    private String chdanuongbenhly;
    @Column(name = "CHDOCHAMSOC")
    private String chdochamsoc;

    @Column(name = "GIAIPHAUBENHCHITIET")
    private String giaiphaubenhchitiet;
    @Column(name = "TLRAVKHONGKINH_MT")
    private String tlravkhongkinhMt;
    @Column(name = "TLRAVKHONGKINH_MP")
    private String tlravkhongkinhMp;
    @Column(name = "TLRAVCOKINH_MT")
    private String tlravcokinhMt;
    @Column(name = "TLRAVCOKINH_MP")
    private String tlravcokinhMp;
    @Column(name = "NARAV_MP")
    private String naravMp;
    @Column(name = "NARAV_MT")
    private String naravMt;
    @Column(name = "TTRAV_MP")
    private String ttravMp;
    @Column(name = "TTRAV_MT")
    private String ttravMt;
    @Column(name = "TIENLUONG")
    private String tienLuong;

    public String getGiaiphaubenhchitiet() {
        return giaiphaubenhchitiet;
    }

    public void setGiaiphaubenhchitiet(String giaiphaubenhchitiet) {
        this.giaiphaubenhchitiet = giaiphaubenhchitiet;
    }

    public String getNaravMp() {
        return naravMp;
    }

    public void setNaravMp(String naravMp) {
        this.naravMp = naravMp;
    }

    public String getNaravMt() {
        return naravMt;
    }

    public void setNaravMt(String naravMt) {
        this.naravMt = naravMt;
    }

    public String getTlravcokinhMp() {
        return tlravcokinhMp;
    }

    public void setTlravcokinhMp(String tlravcokinhMp) {
        this.tlravcokinhMp = tlravcokinhMp;
    }

    public String getTlravcokinhMt() {
        return tlravcokinhMt;
    }

    public void setTlravcokinhMt(String tlravcokinhMt) {
        this.tlravcokinhMt = tlravcokinhMt;
    }

    public String getTlravkhongkinhMp() {
        return tlravkhongkinhMp;
    }

    public void setTlravkhongkinhMp(String tlravkhongkinhMp) {
        this.tlravkhongkinhMp = tlravkhongkinhMp;
    }

    public String getTlravkhongkinhMt() {
        return tlravkhongkinhMt;
    }

    public void setTlravkhongkinhMt(String tlravkhongkinhMt) {
        this.tlravkhongkinhMt = tlravkhongkinhMt;
    }

    public String getTtravMp() {
        return ttravMp;
    }

    public void setTtravMp(String ttravMp) {
        this.ttravMp = ttravMp;
    }

    public String getTtravMt() {
        return ttravMt;
    }

    public void setTtravMt(String ttravMt) {
        this.ttravMt = ttravMt;
    }
    
    public String getChdanuongbenhly() {
        return chdanuongbenhly;
    }

    public void setChdanuongbenhly(String chdanuongbenhly) {
        this.chdanuongbenhly = chdanuongbenhly;
    }

    public String getChdochamsoc() {
        return chdochamsoc;
    }

    public void setChdochamsoc(String chdochamsoc) {
        this.chdochamsoc = chdochamsoc;
    }

    public String getGpbKq() {
        return gpbKq;
    }

    public void setGpbKq(String gpbKq) {
        this.gpbKq = gpbKq;
    }

    public String getGpbMa() {
        return gpbMa;
    }

    public void setGpbMa(String gpbMa) {
        this.gpbMa = gpbMa;
    }

    public String getHoasinhKq() {
        return hoasinhKq;
    }

    public void setHoasinhKq(String hoasinhKq) {
        this.hoasinhKq = hoasinhKq;
    }

    public String getHoasinhMa() {
        return hoasinhMa;
    }

    public void setHoasinhMa(String hoasinhMa) {
        this.hoasinhMa = hoasinhMa;
    }

    public String getHuyethocKq() {
        return huyethocKq;
    }

    public void setHuyethocKq(String huyethocKq) {
        this.huyethocKq = huyethocKq;
    }

    public String getHuyethocMa() {
        return huyethocMa;
    }

    public void setHuyethocMa(String huyethocMa) {
        this.huyethocMa = huyethocMa;
    }

    public String getNoisoiKq() {
        return noisoiKq;
    }

    public void setNoisoiKq(String noisoiKq) {
        this.noisoiKq = noisoiKq;
    }

    public String getNoisoiMa() {
        return noisoiMa;
    }

    public void setNoisoiMa(String noisoiMa) {
        this.noisoiMa = noisoiMa;
    }

    public String getSieuamKq() {
        return sieuamKq;
    }

    public void setSieuamKq(String sieuamKq) {
        this.sieuamKq = sieuamKq;
    }

    public String getSieuamMa() {
        return sieuamMa;
    }

    public void setSieuamMa(String sieuamMa) {
        this.sieuamMa = sieuamMa;
    }

    public String getVisinhKq() {
        return visinhKq;
    }

    public void setVisinhKq(String visinhKq) {
        this.visinhKq = visinhKq;
    }

    public String getVisinhMa() {
        return visinhMa;
    }

    public void setVisinhMa(String visinhMa) {
        this.visinhMa = visinhMa;
    }

    public String getXetnghiemTomtat() {
        return xetnghiemTomtat;
    }

    public void setXetnghiemTomtat(String xetnghiemTomtat) {
        this.xetnghiemTomtat = xetnghiemTomtat;
    }

    public String getXetnghiemkhacKq() {
        return xetnghiemkhacKq;
    }

    public void setXetnghiemkhacKq(String xetnghiemkhacKq) {
        this.xetnghiemkhacKq = xetnghiemkhacKq;
    }

    public String getXetnghiemkhacMa() {
        return xetnghiemkhacMa;
    }

    public void setXetnghiemkhacMa(String xetnghiemkhacMa) {
        this.xetnghiemkhacMa = xetnghiemkhacMa;
    }

    public String getXquangKq() {
        return xquangKq;
    }

    public void setXquangKq(String xquangKq) {
        this.xquangKq = xquangKq;
    }

    public String getXquangMa() {
        return xquangMa;
    }

    public void setXquangMa(String xquangMa) {
        this.xquangMa = xquangMa;
    }
    
    public String getHsbactmatChitietbenhcoquankhac() {
        return hsbactmatChitietbenhcoquankhac;
    }

    public void setHsbactmatChitietbenhcoquankhac(String hsbactmatChitietbenhcoquankhac) {
        this.hsbactmatChitietbenhcoquankhac = hsbactmatChitietbenhcoquankhac;
    }


    public String getHsbactmatCoquankhac() {
        return hsbactmatCoquankhac;
    }

    public void setHsbactmatCoquankhac(String hsbactmatCoquankhac) {
        this.hsbactmatCoquankhac = hsbactmatCoquankhac;
    }

    public String getDaymatPhaima() {
        return daymatPhaima;
    }

    public void setDaymatPhaima(String daymatPhaima) {
        this.daymatPhaima = daymatPhaima;
    }

    public String getDaymatTraima() {
        return daymatTraima;
    }

    public void setDaymatTraima(String daymatTraima) {
        this.daymatTraima = daymatTraima;
    }

    public String getDichkinhPhaima() {
        return dichkinhPhaima;
    }

    public void setDichkinhPhaima(String dichkinhPhaima) {
        this.dichkinhPhaima = dichkinhPhaima;
    }

    public String getDichkinhTraima() {
        return dichkinhTraima;
    }

    public void setDichkinhTraima(String dichkinhTraima) {
        this.dichkinhTraima = dichkinhTraima;
    }

    public String getGiacmacPhaima() {
        return giacmacPhaima;
    }

    public void setGiacmacPhaima(String giacmacPhaima) {
        this.giacmacPhaima = giacmacPhaima;
    }

    public String getGiacmacTraima() {
        return giacmacTraima;
    }

    public void setGiacmacTraima(String giacmacTraima) {
        this.giacmacTraima = giacmacTraima;
    }

    public String getHocmatPhaima() {
        return hocmatPhaima;
    }

    public void setHocmatPhaima(String hocmatPhaima) {
        this.hocmatPhaima = hocmatPhaima;
    }

    public String getHocmatTraima() {
        return hocmatTraima;
    }

    public void setHocmatTraima(String hocmatTraima) {
        this.hocmatTraima = hocmatTraima;
    }

    public String getKetmacPhaima() {
        return ketmacPhaima;
    }

    public void setKetmacPhaima(String ketmacPhaima) {
        this.ketmacPhaima = ketmacPhaima;
    }

    public String getKetmacTraima() {
        return ketmacTraima;
    }

    public void setKetmacTraima(String ketmacTraima) {
        this.ketmacTraima = ketmacTraima;
    }

    public String getLedaoPhaima() {
        return ledaoPhaima;
    }

    public void setLedaoPhaima(String ledaoPhaima) {
        this.ledaoPhaima = ledaoPhaima;
    }

    public String getLedaoTraima() {
        return ledaoTraima;
    }

    public void setLedaoTraima(String ledaoTraima) {
        this.ledaoTraima = ledaoTraima;
    }

    public String getMimatPhaima() {
        return mimatPhaima;
    }

    public void setMimatPhaima(String mimatPhaima) {
        this.mimatPhaima = mimatPhaima;
    }

    public String getMimatTraima() {
        return mimatTraima;
    }

    public void setMimatTraima(String mimatTraima) {
        this.mimatTraima = mimatTraima;
    }

    public String getMongmatPhaima() {
        return mongmatPhaima;
    }

    public void setMongmatPhaima(String mongmatPhaima) {
        this.mongmatPhaima = mongmatPhaima;
    }

    public String getMongmatTraima() {
        return mongmatTraima;
    }

    public void setMongmatTraima(String mongmatTraima) {
        this.mongmatTraima = mongmatTraima;
    }

    public String getPhanxadongtuPhaima() {
        return phanxadongtuPhaima;
    }

    public void setPhanxadongtuPhaima(String phanxadongtuPhaima) {
        this.phanxadongtuPhaima = phanxadongtuPhaima;
    }

    public String getPhanxadongtuTraima() {
        return phanxadongtuTraima;
    }

    public void setPhanxadongtuTraima(String phanxadongtuTraima) {
        this.phanxadongtuTraima = phanxadongtuTraima;
    }

    public String getSoianhdongtuPhaima() {
        return soianhdongtuPhaima;
    }

    public void setSoianhdongtuPhaima(String soianhdongtuPhaima) {
        this.soianhdongtuPhaima = soianhdongtuPhaima;
    }

    public String getSoianhdongtuTraima() {
        return soianhdongtuTraima;
    }

    public void setSoianhdongtuTraima(String soianhdongtuTraima) {
        this.soianhdongtuTraima = soianhdongtuTraima;
    }

    public String getThethuytinhPhaima() {
        return thethuytinhPhaima;
    }

    public void setThethuytinhPhaima(String thethuytinhPhaima) {
        this.thethuytinhPhaima = thethuytinhPhaima;
    }

    public String getThethuytinhTraima() {
        return thethuytinhTraima;
    }

    public void setThethuytinhTraima(String thethuytinhTraima) {
        this.thethuytinhTraima = thethuytinhTraima;
    }

    public String getTienphongPhaima() {
        return tienphongPhaima;
    }

    public void setTienphongPhaima(String tienphongPhaima) {
        this.tienphongPhaima = tienphongPhaima;
    }

    public String getTienphongTraima() {
        return tienphongTraima;
    }

    public void setTienphongTraima(String tienphongTraima) {
        this.tienphongTraima = tienphongTraima;
    }

    public String getTinhhinhnhancauPhaima() {
        return tinhhinhnhancauPhaima;
    }

    public void setTinhhinhnhancauPhaima(String tinhhinhnhancauPhaima) {
        this.tinhhinhnhancauPhaima = tinhhinhnhancauPhaima;
    }

    public String getTinhhinhnhancauTraima() {
        return tinhhinhnhancauTraima;
    }

    public void setTinhhinhnhancauTraima(String tinhhinhnhancauTraima) {
        this.tinhhinhnhancauTraima = tinhhinhnhancauTraima;
    }

    public String getVandongnhancauPhaima() {
        return vandongnhancauPhaima;
    }

    public void setVandongnhancauPhaima(String vandongnhancauPhaima) {
        this.vandongnhancauPhaima = vandongnhancauPhaima;
    }

    public String getVandongnhancauTraima() {
        return vandongnhancauTraima;
    }

    public void setVandongnhancauTraima(String vandongnhancauTraima) {
        this.vandongnhancauTraima = vandongnhancauTraima;
    }
    

    public String getChitietcoquanbenh() {
        return chitietcoquanbenh;
    }

    public void setChitietcoquanbenh(String chitietcoquanbenh) {
        this.chitietcoquanbenh = chitietcoquanbenh;
    }

    public String getDaymatPhai() {
        return daymatPhai;
    }

    public void setDaymatPhai(String daymatPhai) {
        this.daymatPhai = daymatPhai;
    }

    public String getDaymatTrai() {
        return daymatTrai;
    }

    public void setDaymatTrai(String daymatTrai) {
        this.daymatTrai = daymatTrai;
    }

    public String getDichkinhPhai() {
        return dichkinhPhai;
    }

    public void setDichkinhPhai(String dichkinhPhai) {
        this.dichkinhPhai = dichkinhPhai;
    }

    public String getDichkinhTrai() {
        return dichkinhTrai;
    }

    public void setDichkinhTrai(String dichkinhTrai) {
        this.dichkinhTrai = dichkinhTrai;
    }

    public String getGiacmacPhai() {
        return giacmacPhai;
    }

    public void setGiacmacPhai(String giacmacPhai) {
        this.giacmacPhai = giacmacPhai;
    }

    public String getGiacmacTrai() {
        return giacmacTrai;
    }

    public void setGiacmacTrai(String giacmacTrai) {
        this.giacmacTrai = giacmacTrai;
    }

    public String getHocmatPhai() {
        return hocmatPhai;
    }

    public void setHocmatPhai(String hocmatPhai) {
        this.hocmatPhai = hocmatPhai;
    }

    public String getHocmatTrai() {
        return hocmatTrai;
    }

    public void setHocmatTrai(String hocmatTrai) {
        this.hocmatTrai = hocmatTrai;
    }

    public String getKetmacPhai() {
        return ketmacPhai;
    }

    public void setKetmacPhai(String ketmacPhai) {
        this.ketmacPhai = ketmacPhai;
    }

    public String getKetmacTrai() {
        return ketmacTrai;
    }

    public void setKetmacTrai(String ketmacTrai) {
        this.ketmacTrai = ketmacTrai;
    }

    public String getLedaoPhai() {
        return ledaoPhai;
    }

    public void setLedaoPhai(String ledaoPhai) {
        this.ledaoPhai = ledaoPhai;
    }

    public String getLedaoTrai() {
        return ledaoTrai;
    }

    public void setLedaoTrai(String ledaoTrai) {
        this.ledaoTrai = ledaoTrai;
    }

    public String getMimatPhai() {
        return mimatPhai;
    }

    public void setMimatPhai(String mimatPhai) {
        this.mimatPhai = mimatPhai;
    }

    public String getMimatTrai() {
        return mimatTrai;
    }

    public void setMimatTrai(String mimatTrai) {
        this.mimatTrai = mimatTrai;
    }

    public String getMongmatPhai() {
        return mongmatPhai;
    }

    public void setMongmatPhai(String mongmatPhai) {
        this.mongmatPhai = mongmatPhai;
    }

    public String getMongmatTrai() {
        return mongmatTrai;
    }

    public void setMongmatTrai(String mongmatTrai) {
        this.mongmatTrai = mongmatTrai;
    }

    public String getNhanapPhai() {
        return nhanapPhai;
    }

    public void setNhanapPhai(String nhanapPhai) {
        this.nhanapPhai = nhanapPhai;
    }

    public String getNhanapTrai() {
        return nhanapTrai;
    }

    public void setNhanapTrai(String nhanapTrai) {
        this.nhanapTrai = nhanapTrai;
    }

    public String getPhanxadongtuPhai() {
        return phanxadongtuPhai;
    }

    public void setPhanxadongtuPhai(String phanxadongtuPhai) {
        this.phanxadongtuPhai = phanxadongtuPhai;
    }

    public String getPhanxadongtuTrai() {
        return phanxadongtuTrai;
    }

    public void setPhanxadongtuTrai(String phanxadongtuTrai) {
        this.phanxadongtuTrai = phanxadongtuTrai;
    }

    public String getSoianhdongtuPhai() {
        return soianhdongtuPhai;
    }

    public void setSoianhdongtuPhai(String soianhdongtuPhai) {
        this.soianhdongtuPhai = soianhdongtuPhai;
    }

    public String getSoianhdongtuTrai() {
        return soianhdongtuTrai;
    }

    public void setSoianhdongtuTrai(String soianhdongtuTrai) {
        this.soianhdongtuTrai = soianhdongtuTrai;
    }

    public String getThethuytinhPhai() {
        return thethuytinhPhai;
    }

    public void setThethuytinhPhai(String thethuytinhPhai) {
        this.thethuytinhPhai = thethuytinhPhai;
    }

    public String getThethuytinhTrai() {
        return thethuytinhTrai;
    }

    public void setThethuytinhTrai(String thethuytinhTrai) {
        this.thethuytinhTrai = thethuytinhTrai;
    }

    public String getThitruongPhai() {
        return thitruongPhai;
    }

    public void setThitruongPhai(String thitruongPhai) {
        this.thitruongPhai = thitruongPhai;
    }

    public String getThitruongTrai() {
        return thitruongTrai;
    }

    public void setThitruongTrai(String thitruongTrai) {
        this.thitruongTrai = thitruongTrai;
    }

    public String getTienphongPhai() {
        return tienphongPhai;
    }

    public void setTienphongPhai(String tienphongPhai) {
        this.tienphongPhai = tienphongPhai;
    }

    public String getTienphongTrai() {
        return tienphongTrai;
    }

    public void setTienphongTrai(String tienphongTrai) {
        this.tienphongTrai = tienphongTrai;
    }

    public String getTinhhinhnhancauPhai() {
        return tinhhinhnhancauPhai;
    }

    public void setTinhhinhnhancauPhai(String tinhhinhnhancauPhai) {
        this.tinhhinhnhancauPhai = tinhhinhnhancauPhai;
    }

    public String getTinhhinhnhancauTrai() {
        return tinhhinhnhancauTrai;
    }

    public void setTinhhinhnhancauTrai(String tinhhinhnhancauTrai) {
        this.tinhhinhnhancauTrai = tinhhinhnhancauTrai;
    }

    public String getTlcokinhPhai() {
        return tlcokinhPhai;
    }

    public void setTlcokinhPhai(String tlcokinhPhai) {
        this.tlcokinhPhai = tlcokinhPhai;
    }

    public String getTlcokinhTrai() {
        return tlcokinhTrai;
    }

    public void setTlcokinhTrai(String tlcokinhTrai) {
        this.tlcokinhTrai = tlcokinhTrai;
    }

    public String getTlkhongkinhPhai() {
        return tlkhongkinhPhai;
    }

    public void setTlkhongkinhPhai(String tlkhongkinhPhai) {
        this.tlkhongkinhPhai = tlkhongkinhPhai;
    }

    public String getTlkhongkinhTrai() {
        return tlkhongkinhTrai;
    }

    public void setTlkhongkinhTrai(String tlkhongkinhTrai) {
        this.tlkhongkinhTrai = tlkhongkinhTrai;
    }

    public String getVandongnhancauPhai() {
        return vandongnhancauPhai;
    }

    public void setVandongnhancauPhai(String vandongnhancauPhai) {
        this.vandongnhancauPhai = vandongnhancauPhai;
    }

    public String getVandongnhancauTrai() {
        return vandongnhancauTrai;
    }

    public void setVandongnhancauTrai(String vandongnhancauTrai) {
        this.vandongnhancauTrai = vandongnhancauTrai;
    }

    public HsbaChiTietMat() {
    }

    public HsbaChiTietMat(Integer hsbactmatMa) {
        this.hsbactmatMa = hsbactmatMa;
    }

    public Integer getHsbactmatMa() {
        return hsbactmatMa;
    }

    public void setHsbactmatMa(Integer hsbactmatMa) {
        this.hsbactmatMa = hsbactmatMa;
    }

    public String getHsbactmatQtbenhly() {
        return hsbactmatQtbenhly;
    }

    public void setHsbactmatQtbenhly(String hsbactmatQtbenhly) {
        this.hsbactmatQtbenhly = hsbactmatQtbenhly;
    }

    public String getHsbactmatTiensubenhbt() {
        return hsbactmatTiensubenhbt;
    }

    public void setHsbactmatTiensubenhbt(String hsbactmatTiensubenhbt) {
        this.hsbactmatTiensubenhbt = hsbactmatTiensubenhbt;
    }

    public String getHsbactmatTiensubenhgd() {
        return hsbactmatTiensubenhgd;
    }

    public void setHsbactmatTiensubenhgd(String hsbactmatTiensubenhgd) {
        this.hsbactmatTiensubenhgd = hsbactmatTiensubenhgd;
    }

    public Boolean getHsbactmatDdDiung() {
        return hsbactmatDdDiung;
    }

    public void setHsbactmatDdDiung(Boolean hsbactmatDdDiung) {
        this.hsbactmatDdDiung = hsbactmatDdDiung;
    }

    public Boolean getHsbactmatDdMatuy() {
        return hsbactmatDdMatuy;
    }

    public void setHsbactmatDdMatuy(Boolean hsbactmatDdMatuy) {
        this.hsbactmatDdMatuy = hsbactmatDdMatuy;
    }

    public Boolean getHsbactmatDdRuoubia() {
        return hsbactmatDdRuoubia;
    }

    public void setHsbactmatDdRuoubia(Boolean hsbactmatDdRuoubia) {
        this.hsbactmatDdRuoubia = hsbactmatDdRuoubia;
    }

    public Boolean getHsbactmatDdThuocla() {
        return hsbactmatDdThuocla;
    }

    public void setHsbactmatDdThuocla(Boolean hsbactmatDdThuocla) {
        this.hsbactmatDdThuocla = hsbactmatDdThuocla;
    }

    public Boolean getHsbactmatDdThuoclao() {
        return hsbactmatDdThuoclao;
    }

    public void setHsbactmatDdThuoclao(Boolean hsbactmatDdThuoclao) {
        this.hsbactmatDdThuoclao = hsbactmatDdThuoclao;
    }

    public Boolean getHsbactmatDdKhac() {
        return hsbactmatDdKhac;
    }

    public void setHsbactmatDdKhac(Boolean hsbactmatDdKhac) {
        this.hsbactmatDdKhac = hsbactmatDdKhac;
    }

    public String getHsbactmatDdDiungTg() {
        return hsbactmatDdDiungTg;
    }

    public void setHsbactmatDdDiungTg(String hsbactmatDdDiungTg) {
        this.hsbactmatDdDiungTg = hsbactmatDdDiungTg;
    }

    public String getHsbactmatDdMatuyTg() {
        return hsbactmatDdMatuyTg;
    }

    public void setHsbactmatDdMatuyTg(String hsbactmatDdMatuyTg) {
        this.hsbactmatDdMatuyTg = hsbactmatDdMatuyTg;
    }

    public String getHsbactmatDdRuoubiaTg() {
        return hsbactmatDdRuoubiaTg;
    }

    public void setHsbactmatDdRuoubiaTg(String hsbactmatDdRuoubiaTg) {
        this.hsbactmatDdRuoubiaTg = hsbactmatDdRuoubiaTg;
    }

    public String getHsbactmatDdThuoclaTg() {
        return hsbactmatDdThuoclaTg;
    }

    public void setHsbactmatDdThuoclaTg(String hsbactmatDdThuoclaTg) {
        this.hsbactmatDdThuoclaTg = hsbactmatDdThuoclaTg;
    }

    public String getHsbactmatDdThuoclaoTg() {
        return hsbactmatDdThuoclaoTg;
    }

    public void setHsbactmatDdThuoclaoTg(String hsbactmatDdThuoclaoTg) {
        this.hsbactmatDdThuoclaoTg = hsbactmatDdThuoclaoTg;
    }

    public String getHsbactmatDdKhacTg() {
        return hsbactmatDdKhacTg;
    }

    public void setHsbactmatDdKhacTg(String hsbactmatDdKhacTg) {
        this.hsbactmatDdKhacTg = hsbactmatDdKhacTg;
    }

    public String getHsbactmatTuanhoan() {
        return hsbactmatTuanhoan;
    }

    public void setHsbactmatTuanhoan(String hsbactmatTuanhoan) {
        this.hsbactmatTuanhoan = hsbactmatTuanhoan;
    }

    public String getHsbactmatHohap() {
        return hsbactmatHohap;
    }

    public void setHsbactmatHohap(String hsbactmatHohap) {
        this.hsbactmatHohap = hsbactmatHohap;
    }

    public String getHsbactmatTieuhoa() {
        return hsbactmatTieuhoa;
    }

    public void setHsbactmatTieuhoa(String hsbactmatTieuhoa) {
        this.hsbactmatTieuhoa = hsbactmatTieuhoa;
    }

    public String getHsbactmatThantietnieusinhhoc() {
        return hsbactmatThantietnieusinhhoc;
    }

    public void setHsbactmatThantietnieusinhhoc(String hsbactmatThantietnieusinhhoc) {
        this.hsbactmatThantietnieusinhhoc = hsbactmatThantietnieusinhhoc;
    }

    public String getHsbactmatThankinh() {
        return hsbactmatThankinh;
    }

    public void setHsbactmatThankinh(String hsbactmatThankinh) {
        this.hsbactmatThankinh = hsbactmatThankinh;
    }

    public String getHsbactmatCoxuongkhop() {
        return hsbactmatCoxuongkhop;
    }

    public void setHsbactmatCoxuongkhop(String hsbactmatCoxuongkhop) {
        this.hsbactmatCoxuongkhop = hsbactmatCoxuongkhop;
    }

    public String getHsbactmatTmh() {
        return hsbactmatTmh;
    }

    public void setHsbactmatTmh(String hsbactmatTmh) {
        this.hsbactmatTmh = hsbactmatTmh;
    }

    public String getHsbactmatRhm() {
        return hsbactmatRhm;
    }

    public void setHsbactmatRhm(String hsbactmatRhm) {
        this.hsbactmatRhm = hsbactmatRhm;
    }

    public String getHsbactmatMat() {
        return hsbactmatMat;
    }

    public void setHsbactmatMat(String hsbactmatMat) {
        this.hsbactmatMat = hsbactmatMat;
    }

    public String getHsbactmatNtDdBlk() {
        return hsbactmatNtDdBlk;
    }

    public void setHsbactmatNtDdBlk(String hsbactmatNtDdBlk) {
        this.hsbactmatNtDdBlk = hsbactmatNtDdBlk;
    }

    public String getHsbactmatTtba() {
        return hsbactmatTtba;
    }

    public void setHsbactmatTtba(String hsbactmatTtba) {
        this.hsbactmatTtba = hsbactmatTtba;
    }

    public Integer getHsbactmatPb() {
        return hsbactmatPb;
    }

    public void setHsbactmatPb(Integer hsbactmatPb) {
        this.hsbactmatPb = hsbactmatPb;
    }

    public String getHsbactmatLydovaov() {
        return hsbactmatLydovaov;
    }

    public void setHsbactmatLydovaov(String hsbactmatLydovaov) {
        this.hsbactmatLydovaov = hsbactmatLydovaov;
    }

    public Integer getHsbactmatNgaybenhthu() {
        return hsbactmatNgaybenhthu;
    }

    public void setHsbactmatNgaybenhthu(Integer hsbactmatNgaybenhthu) {
        this.hsbactmatNgaybenhthu = hsbactmatNgaybenhthu;
    }

    public String getHsbactmatToanthan() {
        return hsbactmatToanthan;
    }

    public void setHsbactmatToanthan(String hsbactmatToanthan) {
        this.hsbactmatToanthan = hsbactmatToanthan;
    }

    public String getHsbactmatQtblDbls() {
        return hsbactmatQtblDbls;
    }

    public void setHsbactmatQtblDbls(String hsbactmatQtblDbls) {
        this.hsbactmatQtblDbls = hsbactmatQtblDbls;
    }

    public String getHsbactmatTtkqxncls() {
        return hsbactmatTtkqxncls;
    }

    public void setHsbactmatTtkqxncls(String hsbactmatTtkqxncls) {
        this.hsbactmatTtkqxncls = hsbactmatTtkqxncls;
    }

    public String getHsbactmatPpdieutri() {
        return hsbactmatPpdieutri;
    }

    public void setHsbactmatPpdieutri(String hsbactmatPpdieutri) {
        this.hsbactmatPpdieutri = hsbactmatPpdieutri;
    }

    public String getHsbactmatTtnguoibenhrav() {
        return hsbactmatTtnguoibenhrav;
    }

    public void setHsbactmatTtnguoibenhrav(String hsbactmatTtnguoibenhrav) {
        this.hsbactmatTtnguoibenhrav = hsbactmatTtnguoibenhrav;
    }

    public String getHsbactmatHuongdtCdtt() {
        return hsbactmatHuongdtCdtt;
    }

    public void setHsbactmatHuongdtCdtt(String hsbactmatHuongdtCdtt) {
        this.hsbactmatHuongdtCdtt = hsbactmatHuongdtCdtt;
    }

    public Integer getHsbactmatSotoxquang() {
        return hsbactmatSotoxquang;
    }

    public void setHsbactmatSotoxquang(Integer hsbactmatSotoxquang) {
        this.hsbactmatSotoxquang = hsbactmatSotoxquang;
    }

    public Integer getHsbactmatSotoctscanner() {
        return hsbactmatSotoctscanner;
    }

    public void setHsbactmatSotoctscanner(Integer hsbactmatSotoctscanner) {
        this.hsbactmatSotoctscanner = hsbactmatSotoctscanner;
    }

    public Integer getHsbactmatSotosieuam() {
        return hsbactmatSotosieuam;
    }

    public void setHsbactmatSotosieuam(Integer hsbactmatSotosieuam) {
        this.hsbactmatSotosieuam = hsbactmatSotosieuam;
    }

    public Integer getHsbactmatSotoxn() {
        return hsbactmatSotoxn;
    }

    public void setHsbactmatSotoxn(Integer hsbactmatSotoxn) {
        this.hsbactmatSotoxn = hsbactmatSotoxn;
    }

    public Integer getHsbactmatSotokhac() {
        return hsbactmatSotokhac;
    }

    public String getTienLuong() {
        return tienLuong;
    }

    public void setTienLuong(String tienLuong) {
        this.tienLuong = tienLuong;
    }

    public void setHsbactmatSotokhac(Integer hsbactmatSotokhac) {
        this.hsbactmatSotokhac = hsbactmatSotokhac;
    }

    public String getHsbactmatSotoloaikhac() {
        return hsbactmatSotoloaikhac;
    }

    public void setHsbactmatSotoloaikhac(String hsbactmatSotoloaikhac) {
        this.hsbactmatSotoloaikhac = hsbactmatSotoloaikhac;
    }

    public Integer getHsbactmatTongsoto() {
        return hsbactmatTongsoto;
    }

    public void setHsbactmatTongsoto(Integer hsbactmatTongsoto) {
        this.hsbactmatTongsoto = hsbactmatTongsoto;
    }

    public DtDmNhanVien getHsbactmatBsdieutri() {
        return hsbactmatBsdieutri;
    }

    public void setHsbactmatBsdieutri(DtDmNhanVien hsbactmatBsdieutri) {
        this.hsbactmatBsdieutri = hsbactmatBsdieutri;
    }

    public DtDmNhanVien getHsbactmatBslamba() {
        return hsbactmatBslamba;
    }

    public void setHsbactmatBslamba(DtDmNhanVien hsbactmatBslamba) {
        this.hsbactmatBslamba = hsbactmatBslamba;
    }

    public DtDmNhanVien getHsbactmatNguoigiaoba() {
        return hsbactmatNguoigiaoba;
    }

    public void setHsbactmatNguoigiaoba(DtDmNhanVien hsbactmatNguoigiaoba) {
        this.hsbactmatNguoigiaoba = hsbactmatNguoigiaoba;
    }

    public DtDmNhanVien getHsbactmatNguoinhanba() {
        return hsbactmatNguoinhanba;
    }

    public void setHsbactmatNguoinhanba(DtDmNhanVien hsbactmatNguoinhanba) {
        this.hsbactmatNguoinhanba = hsbactmatNguoinhanba;
    }



    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }



     public DtDmNhanVien getHsbactmatBslamba(boolean create) {
           if (create && hsbactmatBslamba == null) {
            hsbactmatBslamba = new DtDmNhanVien();
        }
        return hsbactmatBslamba;

    }


 public DtDmNhanVien getHsbactmatNguoigiaoba(boolean create) {
           if (create && hsbactmatNguoigiaoba == null) {
            hsbactmatNguoigiaoba = new DtDmNhanVien();
        }
        return hsbactmatNguoigiaoba;

    }


     public DtDmNhanVien getHsbactmatNguoinhanba(boolean create) {
           if (create && hsbactmatNguoinhanba == null) {
            hsbactmatNguoinhanba = new DtDmNhanVien();
        }
        return hsbactmatNguoinhanba;

    }

    

     public DtDmNhanVien getHsbactmatBsdieutri(boolean create) {
           if (create && hsbactmatBsdieutri == null) {
            hsbactmatBsdieutri = new DtDmNhanVien();
        }
        return hsbactmatBsdieutri;

    }

     public DmThuoc getHsbactmatTienluong() {
        return hsbactmatTienluong;
    }

    public void setHsbactmatTienluong(DmThuoc hsbactmatTienluong) {
        this.hsbactmatTienluong = hsbactmatTienluong;
    }


   public DmThuoc getHsbactnoiTienluong(boolean create) {
        if (create && getHsbactmatTienluong() == null) {
            setHsbactmatTienluong(new DmThuoc());
        }
        return getHsbactmatTienluong();

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
        hash += (hsbactmatMa != null ? hsbactmatMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietMat)) {
            return false;
        }
        HsbaChiTietMat other = (HsbaChiTietMat) object;
        if ((this.hsbactmatMa == null && other.hsbactmatMa != null) || (this.hsbactmatMa != null && !this.hsbactmatMa.equals(other.hsbactmatMa))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaChiTietMat[hsbactmatMa=" + hsbactmatMa + "]";
    }

}