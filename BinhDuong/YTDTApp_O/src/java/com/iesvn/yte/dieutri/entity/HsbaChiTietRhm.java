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
 * @author quang
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_CHI_TIET_RHM")
@NamedQueries({
    @NamedQuery(name = "HsbaChiTietRhm.findAll", query = "SELECT h FROM HsbaChiTietRhm h"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmMa", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmMa = :hsbactrhmMa"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmQtbenhly", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmQtbenhly = :hsbactrhmQtbenhly"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmTiensubenhbt", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmTiensubenhbt = :hsbactrhmTiensubenhbt"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmTiensubenhgd", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmTiensubenhgd = :hsbactrhmTiensubenhgd"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdDiung", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdDiung = :hsbactrhmDdDiung"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdMatuy", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdMatuy = :hsbactrhmDdMatuy"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdRuoubia", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdRuoubia = :hsbactrhmDdRuoubia"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdThuocla", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdThuocla = :hsbactrhmDdThuocla"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdThuoclao", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdThuoclao = :hsbactrhmDdThuoclao"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdKhac", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdKhac = :hsbactrhmDdKhac"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdDiungTg", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdDiungTg = :hsbactrhmDdDiungTg"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdMatuyTg", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdMatuyTg = :hsbactrhmDdMatuyTg"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdRuoubiaTg", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdRuoubiaTg = :hsbactrhmDdRuoubiaTg"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdThuoclaTg", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdThuoclaTg = :hsbactrhmDdThuoclaTg"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdThuoclaoTg", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdThuoclaoTg = :hsbactrhmDdThuoclaoTg"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmDdKhacTg", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmDdKhacTg = :hsbactrhmDdKhacTg"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmTuanhoan", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmTuanhoan = :hsbactrhmTuanhoan"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmHohap", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmHohap = :hsbactrhmHohap"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmTieuhoa", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmTieuhoa = :hsbactrhmTieuhoa"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmThantietnieusinhhoc", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmThantietnieusinhhoc = :hsbactrhmThantietnieusinhhoc"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmThankinh", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmThankinh = :hsbactrhmThankinh"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmCoxuongkhop", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmCoxuongkhop = :hsbactrhmCoxuongkhop"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmTmh", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmTmh = :hsbactrhmTmh"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmMat", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmMat = :hsbactrhmMat"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmNtDdBlk", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmNtDdBlk = :hsbactrhmNtDdBlk"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmTtba", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmTtba = :hsbactrhmTtba"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmPb", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmPb = :hsbactrhmPb"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmTienluong", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmTienluong = :hsbactrhmTienluong"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmLydovaov", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmLydovaov = :hsbactrhmLydovaov"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmNgaybenhthu", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmNgaybenhthu = :hsbactrhmNgaybenhthu"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmToanthan", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmToanthan = :hsbactrhmToanthan"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmQtblDbls", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmQtblDbls = :hsbactrhmQtblDbls"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmTtkqxncls", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmTtkqxncls = :hsbactrhmTtkqxncls"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmPpdieutri", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmPpdieutri = :hsbactrhmPpdieutri"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmTtnguoibenhrav", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmTtnguoibenhrav = :hsbactrhmTtnguoibenhrav"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmHuongdtCdtt", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmHuongdtCdtt = :hsbactrhmHuongdtCdtt"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmSotoxquang", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmSotoxquang = :hsbactrhmSotoxquang"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmSotoctscanner", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmSotoctscanner = :hsbactrhmSotoctscanner"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmSotosieuam", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmSotosieuam = :hsbactrhmSotosieuam"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmSotoxn", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmSotoxn = :hsbactrhmSotoxn"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmSotokhac", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmSotokhac = :hsbactrhmSotokhac"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmSotoloaikhac", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmSotoloaikhac = :hsbactrhmSotoloaikhac"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmTongsoto", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmTongsoto = :hsbactrhmTongsoto"),
    @NamedQuery(name = "HsbaChiTietRhm.findByChitietcoquanbenh", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.chitietcoquanbenh = :chitietcoquanbenh"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmCoquankhac", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmCoquankhac = :hsbactrhmCoquankhac"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHsbactrhmChitietbenhcoquankhac", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hsbactrhmChitietbenhcoquankhac = :hsbactrhmChitietbenhcoquankhac"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHuyethocMa", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.huyethocMa = :huyethocMa"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHuyethocKq", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.huyethocKq = :huyethocKq"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHoasinhMa", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hoasinhMa = :hoasinhMa"),
    @NamedQuery(name = "HsbaChiTietRhm.findByHoasinhKq", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.hoasinhKq = :hoasinhKq"),
    @NamedQuery(name = "HsbaChiTietRhm.findByVisinhMa", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.visinhMa = :visinhMa"),
    @NamedQuery(name = "HsbaChiTietRhm.findByVisinhKq", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.visinhKq = :visinhKq"),
    @NamedQuery(name = "HsbaChiTietRhm.findByXquangMa", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.xquangMa = :xquangMa"),
    @NamedQuery(name = "HsbaChiTietRhm.findByXquangKq", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.xquangKq = :xquangKq"),
    @NamedQuery(name = "HsbaChiTietRhm.findBySieuamMa", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.sieuamMa = :sieuamMa"),
    @NamedQuery(name = "HsbaChiTietRhm.findBySieuamKq", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.sieuamKq = :sieuamKq"),
    @NamedQuery(name = "HsbaChiTietRhm.findByNoisoiMa", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.noisoiMa = :noisoiMa"),
    @NamedQuery(name = "HsbaChiTietRhm.findByNoisoiKq", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.noisoiKq = :noisoiKq"),
    @NamedQuery(name = "HsbaChiTietRhm.findByGpbMa", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.gpbMa = :gpbMa"),
    @NamedQuery(name = "HsbaChiTietRhm.findByGpbKq", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.gpbKq = :gpbKq"),
    @NamedQuery(name = "HsbaChiTietRhm.findByXetnghiemkhacMa", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.xetnghiemkhacMa = :xetnghiemkhacMa"),
    @NamedQuery(name = "HsbaChiTietRhm.findByXetnghiemkhacKq", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.xetnghiemkhacKq = :xetnghiemkhacKq"),
    @NamedQuery(name = "HsbaChiTietRhm.findByXetnghiemTomtat", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.xetnghiemTomtat = :xetnghiemTomtat"),
    @NamedQuery(name = "HsbaChiTietRhm.findByChdanuongbenhly", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.chdanuongbenhly = :chdanuongbenhly"),
    @NamedQuery(name = "HsbaChiTietRhm.findByChdochamsoc", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.chdochamsoc = :chdochamsoc"),
    @NamedQuery(name = "HsbaChiTietRhm.findByGiaiphaubenhchitiet", query = "SELECT h FROM HsbaChiTietRhm h WHERE h.giaiphaubenhchitiet = :giaiphaubenhchitiet")})
public class HsbaChiTietRhm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_RHM")
    @SequenceGenerator(name = "HSBA_CHI_TIET_RHM", sequenceName = "HSBA_CHI_TIET_RHM_HSBACTRHM_MA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTRHM_MA")
    private Integer hsbactrhmMa;
    @Column(name = "HSBACTRHM_QTBENHLY")
    private String hsbactrhmQtbenhly;
    @Column(name = "HSBACTRHM_TIENSUBENHBT")
    private String hsbactrhmTiensubenhbt;
    @Column(name = "HSBACTRHM_TIENSUBENHGD")
    private String hsbactrhmTiensubenhgd;
    @Column(name = "HSBACTRHM_DD_DIUNG")
    private Boolean hsbactrhmDdDiung;
    @Column(name = "HSBACTRHM_DD_MATUY")
    private Boolean hsbactrhmDdMatuy;
    @Column(name = "HSBACTRHM_DD_RUOUBIA")
    private Boolean hsbactrhmDdRuoubia;
    @Column(name = "HSBACTRHM_DD_THUOCLA")
    private Boolean hsbactrhmDdThuocla;
    @Column(name = "HSBACTRHM_DD_THUOCLAO")
    private Boolean hsbactrhmDdThuoclao;
    @Column(name = "HSBACTRHM_DD_KHAC")
    private Boolean hsbactrhmDdKhac;
    @Column(name = "HSBACTRHM_DD_DIUNG_TG")
    private String hsbactrhmDdDiungTg;
    @Column(name = "HSBACTRHM_DD_MATUY_TG")
    private String hsbactrhmDdMatuyTg;
    @Column(name = "HSBACTRHM_DD_RUOUBIA_TG")
    private String hsbactrhmDdRuoubiaTg;
    @Column(name = "HSBACTRHM_DD_THUOCLA_TG")
    private String hsbactrhmDdThuoclaTg;
    @Column(name = "HSBACTRHM_DD_THUOCLAO_TG")
    private String hsbactrhmDdThuoclaoTg;
    @Column(name = "HSBACTRHM_DD_KHAC_TG")
    private String hsbactrhmDdKhacTg;
    @Column(name = "HSBACTRHM_TUANHOAN")
    private String hsbactrhmTuanhoan;
    @Column(name = "HSBACTRHM_HOHAP")
    private String hsbactrhmHohap;
    @Column(name = "HSBACTRHM_TIEUHOA")
    private String hsbactrhmTieuhoa;
    @Column(name = "HSBACTRHM_THANTIETNIEUSINHHOC")
    private String hsbactrhmThantietnieusinhhoc;
    @Column(name = "HSBACTRHM_THANKINH")
    private String hsbactrhmThankinh;
    @Column(name = "HSBACTRHM_COXUONGKHOP")
    private String hsbactrhmCoxuongkhop;
    @Column(name = "HSBACTRHM_TMH")
    private String hsbactrhmTmh;
    @Column(name = "HSBACTRHM_MAT")
    private String hsbactrhmMat;
    @Column(name = "HSBACTRHM_NT_DD_BLK")
    private String hsbactrhmNtDdBlk;
    @Column(name = "HSBACTRHM_TTBA")
    private String hsbactrhmTtba;
    @Column(name = "HSBACTRHM_PB")
    private Integer hsbactrhmPb;
    @Column(name = "HSBACTRHM_TIENLUONG")
    private String hsbactrhmTienluong;
    @Column(name = "HSBACTRHM_LYDOVAOV")
    private String hsbactrhmLydovaov;
    @Column(name = "HSBACTRHM_NGAYBENHTHU")
    private Integer hsbactrhmNgaybenhthu;
    @Column(name = "HSBACTRHM_TOANTHAN")
    private String hsbactrhmToanthan;
    @Column(name = "HSBACTRHM_QTBL_DBLS")
    private String hsbactrhmQtblDbls;
    @Column(name = "HSBACTRHM_TTKQXNCLS")
    private String hsbactrhmTtkqxncls;
    @Column(name = "HSBACTRHM_PPDIEUTRI")
    private String hsbactrhmPpdieutri;
    @Column(name = "HSBACTRHM_TTNGUOIBENHRAV")
    private String hsbactrhmTtnguoibenhrav;
    @Column(name = "HSBACTRHM_HUONGDT_CDTT")
    private String hsbactrhmHuongdtCdtt;
    @Column(name = "HSBACTRHM_SOTOXQUANG")
    private Integer hsbactrhmSotoxquang;
    @Column(name = "HSBACTRHM_SOTOCTSCANNER")
    private Integer hsbactrhmSotoctscanner;
    @Column(name = "HSBACTRHM_SOTOSIEUAM")
    private Integer hsbactrhmSotosieuam;
    @Column(name = "HSBACTRHM_SOTOXN")
    private Integer hsbactrhmSotoxn;
    @Column(name = "HSBACTRHM_SOTOKHAC")
    private Integer hsbactrhmSotokhac;
    @Column(name = "HSBACTRHM_SOTOLOAIKHAC")
    private String hsbactrhmSotoloaikhac;
    @Column(name = "HSBACTRHM_TONGSOTO")
    private Integer hsbactrhmTongsoto;
    @Column(name = "CHITIETCOQUANBENH")
    private String chitietcoquanbenh;
    @Column(name = "HSBACTRHM_COQUANKHAC")
    private String hsbactrhmCoquankhac;
    @Column(name = "HSBACTRHM_CTIETBENHCOQUANKHAC")
    private String hsbactrhmChitietbenhcoquankhac;
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

    @JoinColumn(name = "HSBACTRHM_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactrhmBsdieutri;
    @JoinColumn(name = "HSBACTRHM_NGUOINHANBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactrhmNguoinhanba;
    @JoinColumn(name = "HSBACTRHM_NGUOIGIAOBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactrhmNguoigiaoba;
    @JoinColumn(name = "HSBACTRHM_BSLAMBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactrhmBslamba;

    public DtDmNhanVien getHsbactrhmBsdieutri() {
        return hsbactrhmBsdieutri;
    }

    public void setHsbactrhmBsdieutri(DtDmNhanVien hsbactrhmBsdieutri) {
        this.hsbactrhmBsdieutri = hsbactrhmBsdieutri;
    }

    public DtDmNhanVien getHsbactrhmBslamba() {
        return hsbactrhmBslamba;
    }

    public void setHsbactrhmBslamba(DtDmNhanVien hsbactrhmBslamba) {
        this.hsbactrhmBslamba = hsbactrhmBslamba;
    }

    public DtDmNhanVien getHsbactrhmNguoigiaoba() {
        return hsbactrhmNguoigiaoba;
    }

    public void setHsbactrhmNguoigiaoba(DtDmNhanVien hsbactrhmNguoigiaoba) {
        this.hsbactrhmNguoigiaoba = hsbactrhmNguoigiaoba;
    }

    public DtDmNhanVien getHsbactrhmNguoinhanba() {
        return hsbactrhmNguoinhanba;
    }

    public void setHsbactrhmNguoinhanba(DtDmNhanVien hsbactrhmNguoinhanba) {
        this.hsbactrhmNguoinhanba = hsbactrhmNguoinhanba;
    }
    
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;

    public HsbaChiTietRhm() {
    }

    public HsbaChiTietRhm(Integer hsbactrhmMa) {
        this.hsbactrhmMa = hsbactrhmMa;
    }

    public Integer getHsbactrhmMa() {
        return hsbactrhmMa;
    }

    public void setHsbactrhmMa(Integer hsbactrhmMa) {
        this.hsbactrhmMa = hsbactrhmMa;
    }

    public String getHsbactrhmQtbenhly() {
        return hsbactrhmQtbenhly;
    }

    public void setHsbactrhmQtbenhly(String hsbactrhmQtbenhly) {
        this.hsbactrhmQtbenhly = hsbactrhmQtbenhly;
    }

    public String getHsbactrhmTiensubenhbt() {
        return hsbactrhmTiensubenhbt;
    }

    public void setHsbactrhmTiensubenhbt(String hsbactrhmTiensubenhbt) {
        this.hsbactrhmTiensubenhbt = hsbactrhmTiensubenhbt;
    }

    public String getHsbactrhmTiensubenhgd() {
        return hsbactrhmTiensubenhgd;
    }

    public void setHsbactrhmTiensubenhgd(String hsbactrhmTiensubenhgd) {
        this.hsbactrhmTiensubenhgd = hsbactrhmTiensubenhgd;
    }

    public Boolean getHsbactrhmDdDiung() {
        return hsbactrhmDdDiung;
    }

    public void setHsbactrhmDdDiung(Boolean hsbactrhmDdDiung) {
        this.hsbactrhmDdDiung = hsbactrhmDdDiung;
    }

    public Boolean getHsbactrhmDdMatuy() {
        return hsbactrhmDdMatuy;
    }

    public void setHsbactrhmDdMatuy(Boolean hsbactrhmDdMatuy) {
        this.hsbactrhmDdMatuy = hsbactrhmDdMatuy;
    }

    public Boolean getHsbactrhmDdRuoubia() {
        return hsbactrhmDdRuoubia;
    }

    public void setHsbactrhmDdRuoubia(Boolean hsbactrhmDdRuoubia) {
        this.hsbactrhmDdRuoubia = hsbactrhmDdRuoubia;
    }

    public Boolean getHsbactrhmDdThuocla() {
        return hsbactrhmDdThuocla;
    }

    public void setHsbactrhmDdThuocla(Boolean hsbactrhmDdThuocla) {
        this.hsbactrhmDdThuocla = hsbactrhmDdThuocla;
    }

    public Boolean getHsbactrhmDdThuoclao() {
        return hsbactrhmDdThuoclao;
    }

    public void setHsbactrhmDdThuoclao(Boolean hsbactrhmDdThuoclao) {
        this.hsbactrhmDdThuoclao = hsbactrhmDdThuoclao;
    }

    public Boolean getHsbactrhmDdKhac() {
        return hsbactrhmDdKhac;
    }

    public void setHsbactrhmDdKhac(Boolean hsbactrhmDdKhac) {
        this.hsbactrhmDdKhac = hsbactrhmDdKhac;
    }

    public String getHsbactrhmDdDiungTg() {
        return hsbactrhmDdDiungTg;
    }

    public void setHsbactrhmDdDiungTg(String hsbactrhmDdDiungTg) {
        this.hsbactrhmDdDiungTg = hsbactrhmDdDiungTg;
    }

    public String getHsbactrhmDdMatuyTg() {
        return hsbactrhmDdMatuyTg;
    }

    public void setHsbactrhmDdMatuyTg(String hsbactrhmDdMatuyTg) {
        this.hsbactrhmDdMatuyTg = hsbactrhmDdMatuyTg;
    }

    public String getHsbactrhmDdRuoubiaTg() {
        return hsbactrhmDdRuoubiaTg;
    }

    public void setHsbactrhmDdRuoubiaTg(String hsbactrhmDdRuoubiaTg) {
        this.hsbactrhmDdRuoubiaTg = hsbactrhmDdRuoubiaTg;
    }

    public String getHsbactrhmDdThuoclaTg() {
        return hsbactrhmDdThuoclaTg;
    }

    public void setHsbactrhmDdThuoclaTg(String hsbactrhmDdThuoclaTg) {
        this.hsbactrhmDdThuoclaTg = hsbactrhmDdThuoclaTg;
    }

    public String getHsbactrhmDdThuoclaoTg() {
        return hsbactrhmDdThuoclaoTg;
    }

    public void setHsbactrhmDdThuoclaoTg(String hsbactrhmDdThuoclaoTg) {
        this.hsbactrhmDdThuoclaoTg = hsbactrhmDdThuoclaoTg;
    }

    public String getHsbactrhmDdKhacTg() {
        return hsbactrhmDdKhacTg;
    }

    public void setHsbactrhmDdKhacTg(String hsbactrhmDdKhacTg) {
        this.hsbactrhmDdKhacTg = hsbactrhmDdKhacTg;
    }

    public String getHsbactrhmTuanhoan() {
        return hsbactrhmTuanhoan;
    }

    public void setHsbactrhmTuanhoan(String hsbactrhmTuanhoan) {
        this.hsbactrhmTuanhoan = hsbactrhmTuanhoan;
    }

    public String getHsbactrhmHohap() {
        return hsbactrhmHohap;
    }

    public void setHsbactrhmHohap(String hsbactrhmHohap) {
        this.hsbactrhmHohap = hsbactrhmHohap;
    }

    public String getHsbactrhmTieuhoa() {
        return hsbactrhmTieuhoa;
    }

    public void setHsbactrhmTieuhoa(String hsbactrhmTieuhoa) {
        this.hsbactrhmTieuhoa = hsbactrhmTieuhoa;
    }

    public String getHsbactrhmThantietnieusinhhoc() {
        return hsbactrhmThantietnieusinhhoc;
    }

    public void setHsbactrhmThantietnieusinhhoc(String hsbactrhmThantietnieusinhhoc) {
        this.hsbactrhmThantietnieusinhhoc = hsbactrhmThantietnieusinhhoc;
    }

    public String getHsbactrhmThankinh() {
        return hsbactrhmThankinh;
    }

    public void setHsbactrhmThankinh(String hsbactrhmThankinh) {
        this.hsbactrhmThankinh = hsbactrhmThankinh;
    }

    public String getHsbactrhmCoxuongkhop() {
        return hsbactrhmCoxuongkhop;
    }

    public void setHsbactrhmCoxuongkhop(String hsbactrhmCoxuongkhop) {
        this.hsbactrhmCoxuongkhop = hsbactrhmCoxuongkhop;
    }

    public String getHsbactrhmTmh() {
        return hsbactrhmTmh;
    }

    public void setHsbactrhmTmh(String hsbactrhmTmh) {
        this.hsbactrhmTmh = hsbactrhmTmh;
    }

    public String getHsbactrhmMat() {
        return hsbactrhmMat;
    }

    public void setHsbactrhmMat(String hsbactrhmMat) {
        this.hsbactrhmMat = hsbactrhmMat;
    }

    public String getHsbactrhmNtDdBlk() {
        return hsbactrhmNtDdBlk;
    }

    public void setHsbactrhmNtDdBlk(String hsbactrhmNtDdBlk) {
        this.hsbactrhmNtDdBlk = hsbactrhmNtDdBlk;
    }

    public String getHsbactrhmTtba() {
        return hsbactrhmTtba;
    }

    public void setHsbactrhmTtba(String hsbactrhmTtba) {
        this.hsbactrhmTtba = hsbactrhmTtba;
    }

    public Integer getHsbactrhmPb() {
        return hsbactrhmPb;
    }

    public void setHsbactrhmPb(Integer hsbactrhmPb) {
        this.hsbactrhmPb = hsbactrhmPb;
    }

    public String getHsbactrhmTienluong() {
        return hsbactrhmTienluong;
    }

    public void setHsbactrhmTienluong(String hsbactrhmTienluong) {
        this.hsbactrhmTienluong = hsbactrhmTienluong;
    }

    public String getHsbactrhmLydovaov() {
        return hsbactrhmLydovaov;
    }

    public void setHsbactrhmLydovaov(String hsbactrhmLydovaov) {
        this.hsbactrhmLydovaov = hsbactrhmLydovaov;
    }

    public Integer getHsbactrhmNgaybenhthu() {
        return hsbactrhmNgaybenhthu;
    }

    public void setHsbactrhmNgaybenhthu(Integer hsbactrhmNgaybenhthu) {
        this.hsbactrhmNgaybenhthu = hsbactrhmNgaybenhthu;
    }

    public String getHsbactrhmToanthan() {
        return hsbactrhmToanthan;
    }

    public void setHsbactrhmToanthan(String hsbactrhmToanthan) {
        this.hsbactrhmToanthan = hsbactrhmToanthan;
    }

    public String getHsbactrhmQtblDbls() {
        return hsbactrhmQtblDbls;
    }

    public void setHsbactrhmQtblDbls(String hsbactrhmQtblDbls) {
        this.hsbactrhmQtblDbls = hsbactrhmQtblDbls;
    }

    public String getHsbactrhmTtkqxncls() {
        return hsbactrhmTtkqxncls;
    }

    public void setHsbactrhmTtkqxncls(String hsbactrhmTtkqxncls) {
        this.hsbactrhmTtkqxncls = hsbactrhmTtkqxncls;
    }

    public String getHsbactrhmPpdieutri() {
        return hsbactrhmPpdieutri;
    }

    public void setHsbactrhmPpdieutri(String hsbactrhmPpdieutri) {
        this.hsbactrhmPpdieutri = hsbactrhmPpdieutri;
    }

    public String getHsbactrhmTtnguoibenhrav() {
        return hsbactrhmTtnguoibenhrav;
    }

    public void setHsbactrhmTtnguoibenhrav(String hsbactrhmTtnguoibenhrav) {
        this.hsbactrhmTtnguoibenhrav = hsbactrhmTtnguoibenhrav;
    }

    public String getHsbactrhmHuongdtCdtt() {
        return hsbactrhmHuongdtCdtt;
    }

    public void setHsbactrhmHuongdtCdtt(String hsbactrhmHuongdtCdtt) {
        this.hsbactrhmHuongdtCdtt = hsbactrhmHuongdtCdtt;
    }

    public Integer getHsbactrhmSotoxquang() {
        return hsbactrhmSotoxquang;
    }

    public void setHsbactrhmSotoxquang(Integer hsbactrhmSotoxquang) {
        this.hsbactrhmSotoxquang = hsbactrhmSotoxquang;
    }

    public Integer getHsbactrhmSotoctscanner() {
        return hsbactrhmSotoctscanner;
    }

    public void setHsbactrhmSotoctscanner(Integer hsbactrhmSotoctscanner) {
        this.hsbactrhmSotoctscanner = hsbactrhmSotoctscanner;
    }

    public Integer getHsbactrhmSotosieuam() {
        return hsbactrhmSotosieuam;
    }

    public void setHsbactrhmSotosieuam(Integer hsbactrhmSotosieuam) {
        this.hsbactrhmSotosieuam = hsbactrhmSotosieuam;
    }

    public Integer getHsbactrhmSotoxn() {
        return hsbactrhmSotoxn;
    }

    public void setHsbactrhmSotoxn(Integer hsbactrhmSotoxn) {
        this.hsbactrhmSotoxn = hsbactrhmSotoxn;
    }

    public Integer getHsbactrhmSotokhac() {
        return hsbactrhmSotokhac;
    }

    public void setHsbactrhmSotokhac(Integer hsbactrhmSotokhac) {
        this.hsbactrhmSotokhac = hsbactrhmSotokhac;
    }

    public String getHsbactrhmSotoloaikhac() {
        return hsbactrhmSotoloaikhac;
    }

    public void setHsbactrhmSotoloaikhac(String hsbactrhmSotoloaikhac) {
        this.hsbactrhmSotoloaikhac = hsbactrhmSotoloaikhac;
    }

    public Integer getHsbactrhmTongsoto() {
        return hsbactrhmTongsoto;
    }

    public void setHsbactrhmTongsoto(Integer hsbactrhmTongsoto) {
        this.hsbactrhmTongsoto = hsbactrhmTongsoto;
    }

    public String getChitietcoquanbenh() {
        return chitietcoquanbenh;
    }

    public void setChitietcoquanbenh(String chitietcoquanbenh) {
        this.chitietcoquanbenh = chitietcoquanbenh;
    }

    public String getHsbactrhmCoquankhac() {
        return hsbactrhmCoquankhac;
    }

    public void setHsbactrhmCoquankhac(String hsbactrhmCoquankhac) {
        this.hsbactrhmCoquankhac = hsbactrhmCoquankhac;
    }

    public String getHsbactrhmChitietbenhcoquankhac() {
        return hsbactrhmChitietbenhcoquankhac;
    }

    public void setHsbactrhmChitietbenhcoquankhac(String hsbactrhmChitietbenhcoquankhac) {
        this.hsbactrhmChitietbenhcoquankhac = hsbactrhmChitietbenhcoquankhac;
    }

    public String getHuyethocMa() {
        return huyethocMa;
    }

    public void setHuyethocMa(String huyethocMa) {
        this.huyethocMa = huyethocMa;
    }

    public String getHuyethocKq() {
        return huyethocKq;
    }

    public void setHuyethocKq(String huyethocKq) {
        this.huyethocKq = huyethocKq;
    }

    public String getHoasinhMa() {
        return hoasinhMa;
    }

    public void setHoasinhMa(String hoasinhMa) {
        this.hoasinhMa = hoasinhMa;
    }

    public String getHoasinhKq() {
        return hoasinhKq;
    }

    public void setHoasinhKq(String hoasinhKq) {
        this.hoasinhKq = hoasinhKq;
    }

    public String getVisinhMa() {
        return visinhMa;
    }

    public void setVisinhMa(String visinhMa) {
        this.visinhMa = visinhMa;
    }

    public String getVisinhKq() {
        return visinhKq;
    }

    public void setVisinhKq(String visinhKq) {
        this.visinhKq = visinhKq;
    }

    public String getXquangMa() {
        return xquangMa;
    }

    public void setXquangMa(String xquangMa) {
        this.xquangMa = xquangMa;
    }

    public String getXquangKq() {
        return xquangKq;
    }

    public void setXquangKq(String xquangKq) {
        this.xquangKq = xquangKq;
    }

    public String getSieuamMa() {
        return sieuamMa;
    }

    public void setSieuamMa(String sieuamMa) {
        this.sieuamMa = sieuamMa;
    }

    public String getSieuamKq() {
        return sieuamKq;
    }

    public void setSieuamKq(String sieuamKq) {
        this.sieuamKq = sieuamKq;
    }

    public String getNoisoiMa() {
        return noisoiMa;
    }

    public void setNoisoiMa(String noisoiMa) {
        this.noisoiMa = noisoiMa;
    }

    public String getNoisoiKq() {
        return noisoiKq;
    }

    public void setNoisoiKq(String noisoiKq) {
        this.noisoiKq = noisoiKq;
    }

    public String getGpbMa() {
        return gpbMa;
    }

    public void setGpbMa(String gpbMa) {
        this.gpbMa = gpbMa;
    }

    public String getGpbKq() {
        return gpbKq;
    }

    public void setGpbKq(String gpbKq) {
        this.gpbKq = gpbKq;
    }

    public String getXetnghiemkhacMa() {
        return xetnghiemkhacMa;
    }

    public void setXetnghiemkhacMa(String xetnghiemkhacMa) {
        this.xetnghiemkhacMa = xetnghiemkhacMa;
    }

    public String getXetnghiemkhacKq() {
        return xetnghiemkhacKq;
    }

    public void setXetnghiemkhacKq(String xetnghiemkhacKq) {
        this.xetnghiemkhacKq = xetnghiemkhacKq;
    }

    public String getXetnghiemTomtat() {
        return xetnghiemTomtat;
    }

    public void setXetnghiemTomtat(String xetnghiemTomtat) {
        this.xetnghiemTomtat = xetnghiemTomtat;
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

    public String getGiaiphaubenhchitiet() {
        return giaiphaubenhchitiet;
    }

    public void setGiaiphaubenhchitiet(String giaiphaubenhchitiet) {
        this.giaiphaubenhchitiet = giaiphaubenhchitiet;
    }

    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

     public DtDmNhanVien getHsbactrhmBslamba(boolean create) {
           if (create && hsbactrhmBslamba == null) {
            hsbactrhmBslamba = new DtDmNhanVien();
        }
        return hsbactrhmBslamba;

    }


 public DtDmNhanVien getHsbactrhmNguoigiaoba(boolean create) {
           if (create && hsbactrhmNguoigiaoba == null) {
            hsbactrhmNguoigiaoba = new DtDmNhanVien();
        }
        return hsbactrhmNguoigiaoba;

    }


     public DtDmNhanVien getHsbactrhmNguoinhanba(boolean create) {
           if (create && hsbactrhmNguoinhanba == null) {
            hsbactrhmNguoinhanba = new DtDmNhanVien();
        }
        return hsbactrhmNguoinhanba;

    }


     public DtDmNhanVien getHsbactrhmBsdieutri(boolean create) {
           if (create && hsbactrhmBsdieutri == null) {
            hsbactrhmBsdieutri = new DtDmNhanVien();
        }
        return hsbactrhmBsdieutri;

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
        hash += (hsbactrhmMa != null ? hsbactrhmMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietRhm)) {
            return false;
        }
        HsbaChiTietRhm other = (HsbaChiTietRhm) object;
        if ((this.hsbactrhmMa == null && other.hsbactrhmMa != null) || (this.hsbactrhmMa != null && !this.hsbactrhmMa.equals(other.hsbactrhmMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaChiTietRhm[hsbactrhmMa=" + hsbactrhmMa + "]";
    }

}
