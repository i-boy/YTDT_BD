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
@Table(name = "HSBA_CHI_TIET_TMH")
@NamedQueries({
    @NamedQuery(name = "HsbaChiTietTmh.findAll", query = "SELECT h FROM HsbaChiTietTmh h"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhMa", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhMa = :hsbacttmhMa"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhQtbenhly", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhQtbenhly = :hsbacttmhQtbenhly"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhTiensubenhbt", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhTiensubenhbt = :hsbacttmhTiensubenhbt"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhTiensubenhgd", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhTiensubenhgd = :hsbacttmhTiensubenhgd"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdDiung", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdDiung = :hsbacttmhDdDiung"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdMatuy", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdMatuy = :hsbacttmhDdMatuy"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdRuoubia", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdRuoubia = :hsbacttmhDdRuoubia"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdThuocla", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdThuocla = :hsbacttmhDdThuocla"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdThuoclao", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdThuoclao = :hsbacttmhDdThuoclao"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdKhac", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdKhac = :hsbacttmhDdKhac"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdDiungTg", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdDiungTg = :hsbacttmhDdDiungTg"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdMatuyTg", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdMatuyTg = :hsbacttmhDdMatuyTg"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdRuoubiaTg", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdRuoubiaTg = :hsbacttmhDdRuoubiaTg"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdThuoclaTg", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdThuoclaTg = :hsbacttmhDdThuoclaTg"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdThuoclaoTg", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdThuoclaoTg = :hsbacttmhDdThuoclaoTg"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhDdKhacTg", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhDdKhacTg = :hsbacttmhDdKhacTg"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhTuanhoan", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhTuanhoan = :hsbacttmhTuanhoan"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhHohap", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhHohap = :hsbacttmhHohap"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhTieuhoa", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhTieuhoa = :hsbacttmhTieuhoa"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhThantietnieusinhhoc", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhThantietnieusinhhoc = :hsbacttmhThantietnieusinhhoc"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhThankinh", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhThankinh = :hsbacttmhThankinh"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhCoxuongkhop", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhCoxuongkhop = :hsbacttmhCoxuongkhop"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhRhm", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhRhm = :hsbacttmhRhm"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhMat", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhMat = :hsbacttmhMat"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhTiendinh", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhTiendinh = :hsbacttmhTiendinh"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhTtba", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhTtba = :hsbacttmhTtba"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhPb", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhPb = :hsbacttmhPb"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhLydovaov", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhLydovaov = :hsbacttmhLydovaov"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhNgaybenhthu", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhNgaybenhthu = :hsbacttmhNgaybenhthu"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhToanthan", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhToanthan = :hsbacttmhToanthan"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhQtblDbls", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhQtblDbls = :hsbacttmhQtblDbls"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhTtkqxncls", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhTtkqxncls = :hsbacttmhTtkqxncls"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhPpdieutri", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhPpdieutri = :hsbacttmhPpdieutri"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhTtnguoibenhrav", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhTtnguoibenhrav = :hsbacttmhTtnguoibenhrav"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhHuongdtCdtt", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhHuongdtCdtt = :hsbacttmhHuongdtCdtt"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhSotoxquang", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhSotoxquang = :hsbacttmhSotoxquang"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhSotoctscanner", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhSotoctscanner = :hsbacttmhSotoctscanner"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhSotosieuam", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhSotosieuam = :hsbacttmhSotosieuam"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhSotoxn", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhSotoxn = :hsbacttmhSotoxn"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhSotokhac", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhSotokhac = :hsbacttmhSotokhac"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhSotoloaikhac", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhSotoloaikhac = :hsbacttmhSotoloaikhac"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhTongsoto", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhTongsoto = :hsbacttmhTongsoto"),
    @NamedQuery(name = "HsbaChiTietTmh.findByChitietcoquanbenh", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.chitietcoquanbenh = :chitietcoquanbenh"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHsbacttmhChitietbenhcoquankhac", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hsbacttmhChitietbenhcoquankhac = :hsbacttmhChitietbenhcoquankhac"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHuyethocMa", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.huyethocMa = :huyethocMa"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHuyethocKq", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.huyethocKq = :huyethocKq"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHoasinhMa", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hoasinhMa = :hoasinhMa"),
    @NamedQuery(name = "HsbaChiTietTmh.findByHoasinhKq", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.hoasinhKq = :hoasinhKq"),
    @NamedQuery(name = "HsbaChiTietTmh.findByVisinhMa", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.visinhMa = :visinhMa"),
    @NamedQuery(name = "HsbaChiTietTmh.findByVisinhKq", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.visinhKq = :visinhKq"),
    @NamedQuery(name = "HsbaChiTietTmh.findByXquangMa", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.xquangMa = :xquangMa"),
    @NamedQuery(name = "HsbaChiTietTmh.findByXquangKq", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.xquangKq = :xquangKq"),
    @NamedQuery(name = "HsbaChiTietTmh.findBySieuamMa", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.sieuamMa = :sieuamMa"),
    @NamedQuery(name = "HsbaChiTietTmh.findBySieuamKq", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.sieuamKq = :sieuamKq"),
    @NamedQuery(name = "HsbaChiTietTmh.findByNoisoiMa", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.noisoiMa = :noisoiMa"),
    @NamedQuery(name = "HsbaChiTietTmh.findByNoisoiKq", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.noisoiKq = :noisoiKq"),
    @NamedQuery(name = "HsbaChiTietTmh.findByGpbMa", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.gpbMa = :gpbMa"),
    @NamedQuery(name = "HsbaChiTietTmh.findByGpbKq", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.gpbKq = :gpbKq"),
    @NamedQuery(name = "HsbaChiTietTmh.findByXetnghiemkhacMa", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.xetnghiemkhacMa = :xetnghiemkhacMa"),
    @NamedQuery(name = "HsbaChiTietTmh.findByXetnghiemkhacKq", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.xetnghiemkhacKq = :xetnghiemkhacKq"),
    @NamedQuery(name = "HsbaChiTietTmh.findByXetnghiemTomtat", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.xetnghiemTomtat = :xetnghiemTomtat"),
    @NamedQuery(name = "HsbaChiTietTmh.findByChdanuongbenhly", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.chdanuongbenhly = :chdanuongbenhly"),
    @NamedQuery(name = "HsbaChiTietTmh.findByChdochamsoc", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.chdochamsoc = :chdochamsoc"),
    @NamedQuery(name = "HsbaChiTietTmh.findByGiaiphaubenhchitiet", query = "SELECT h FROM HsbaChiTietTmh h WHERE h.giaiphaubenhchitiet = :giaiphaubenhchitiet")})
public class HsbaChiTietTmh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_TMH")
    @SequenceGenerator(name = "HSBA_CHI_TIET_TMH", sequenceName = "HSBA_CHI_TIET_TMH_HSBACTTMH_MA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTTMH_MA")
    private Integer hsbacttmhMa;
    @Column(name = "HSBACTTMH_QTBENHLY")
    private String hsbacttmhQtbenhly;
    @Column(name = "HSBACTTMH_TIENSUBENHBT")
    private String hsbacttmhTiensubenhbt;
    @Column(name = "HSBACTTMH_TIENSUBENHGD")
    private String hsbacttmhTiensubenhgd;
    @Column(name = "HSBACTTMH_DD_DIUNG")
    private Boolean hsbacttmhDdDiung;
    @Column(name = "HSBACTTMH_DD_MATUY")
    private Boolean hsbacttmhDdMatuy;
    @Column(name = "HSBACTTMH_DD_RUOUBIA")
    private Boolean hsbacttmhDdRuoubia;
    @Column(name = "HSBACTTMH_DD_THUOCLA")
    private Boolean hsbacttmhDdThuocla;
    @Column(name = "HSBACTTMH_DD_THUOCLAO")
    private Boolean hsbacttmhDdThuoclao;
    @Column(name = "HSBACTTMH_DD_KHAC")
    private Boolean hsbacttmhDdKhac;
    @Column(name = "HSBACTTMH_DD_DIUNG_TG")
    private String hsbacttmhDdDiungTg;
    @Column(name = "HSBACTTMH_DD_MATUY_TG")
    private String hsbacttmhDdMatuyTg;
    @Column(name = "HSBACTTMH_DD_RUOUBIA_TG")
    private String hsbacttmhDdRuoubiaTg;
    @Column(name = "HSBACTTMH_DD_THUOCLA_TG")
    private String hsbacttmhDdThuoclaTg;
    @Column(name = "HSBACTTMH_DD_THUOCLAO_TG")
    private String hsbacttmhDdThuoclaoTg;
    @Column(name = "HSBACTTMH_DD_KHAC_TG")
    private String hsbacttmhDdKhacTg;
    @Column(name = "HSBACTTMH_TUANHOAN")
    private String hsbacttmhTuanhoan;
    @Column(name = "HSBACTTMH_HOHAP")
    private String hsbacttmhHohap;
    @Column(name = "HSBACTTMH_TIEUHOA")
    private String hsbacttmhTieuhoa;
    @Column(name = "HSBACTTMH_THANTIETNIEUSINHHOC")
    private String hsbacttmhThantietnieusinhhoc;
    @Column(name = "HSBACTTMH_THANKINH")
    private String hsbacttmhThankinh;
    @Column(name = "HSBACTTMH_COXUONGKHOP")
    private String hsbacttmhCoxuongkhop;
    @Column(name = "HSBACTTMH_RHM")
    private String hsbacttmhRhm;
    @Column(name = "HSBACTTMH_MAT")
    private String hsbacttmhMat;
    @Column(name = "HSBACTTMH_TIENDINH")
    private String hsbacttmhTiendinh;
    @Column(name = "HSBACTTMH_TTBA")
    private String hsbacttmhTtba;
    @Column(name = "HSBACTTMH_PB")
    private Integer hsbacttmhPb;
    @Column(name = "HSBACTTMH_LYDOVAOV")
    private String hsbacttmhLydovaov;
    @Column(name = "HSBACTTMH_NGAYBENHTHU")
    private Integer hsbacttmhNgaybenhthu;
    @Column(name = "HSBACTTMH_TOANTHAN")
    private String hsbacttmhToanthan;
    @Column(name = "HSBACTTMH_QTBL_DBLS")
    private String hsbacttmhQtblDbls;
    @Column(name = "HSBACTTMH_TTKQXNCLS")
    private String hsbacttmhTtkqxncls;
    @Column(name = "HSBACTTMH_PPDIEUTRI")
    private String hsbacttmhPpdieutri;
    @Column(name = "HSBACTTMH_TTNGUOIBENHRAV")
    private String hsbacttmhTtnguoibenhrav;
    @Column(name = "HSBACTTMH_HUONGDT_CDTT")
    private String hsbacttmhHuongdtCdtt;
    @Column(name = "HSBACTTMH_SOTOXQUANG")
    private Integer hsbacttmhSotoxquang;
    @Column(name = "HSBACTTMH_SOTOCTSCANNER")
    private Integer hsbacttmhSotoctscanner;
    @Column(name = "HSBACTTMH_SOTOSIEUAM")
    private Integer hsbacttmhSotosieuam;
    @Column(name = "HSBACTTMH_SOTOXN")
    private Integer hsbacttmhSotoxn;
    @Column(name = "HSBACTTMH_SOTOKHAC")
    private Integer hsbacttmhSotokhac;
    @Column(name = "HSBACTTMH_SOTOLOAIKHAC")
    private String hsbacttmhSotoloaikhac;
    @Column(name = "HSBACTTMH_TONGSOTO")
    private Integer hsbacttmhTongsoto;
    @Column(name = "CHITIETCOQUANBENH")
    private String chitietcoquanbenh;
    @Column(name = "HSBACTTMH_CHTIETBENHCOQUANKHAC")
    private String hsbacttmhChitietbenhcoquankhac;
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
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;
    @JoinColumn(name = "HSBACTTMH_BSLAMBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbacttmhBslamba;
    @JoinColumn(name = "HSBACTTMH_NGUOIGIAOBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbacttmhNguoigiaoba;
    @JoinColumn(name = "HSBACTTMH_NGUOINHANBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbacttmhNguoinhanba;
    @JoinColumn(name = "HSBACTTMH_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbacttmhBsdieutri;
    @Column(name = "HSBACTTMH_TIENLUONG")
    private String hsbacttmhTienluong;

    public HsbaChiTietTmh() {
    }

    public HsbaChiTietTmh(Integer hsbacttmhMa) {
        this.hsbacttmhMa = hsbacttmhMa;
    }

    public Integer getHsbacttmhMa() {
        return hsbacttmhMa;
    }

    public void setHsbacttmhMa(Integer hsbacttmhMa) {
        this.hsbacttmhMa = hsbacttmhMa;
    }

    public String getHsbacttmhQtbenhly() {
        return hsbacttmhQtbenhly;
    }

    public void setHsbacttmhQtbenhly(String hsbacttmhQtbenhly) {
        this.hsbacttmhQtbenhly = hsbacttmhQtbenhly;
    }

    public String getHsbacttmhTiensubenhbt() {
        return hsbacttmhTiensubenhbt;
    }

    public void setHsbacttmhTiensubenhbt(String hsbacttmhTiensubenhbt) {
        this.hsbacttmhTiensubenhbt = hsbacttmhTiensubenhbt;
    }

    public String getHsbacttmhTiensubenhgd() {
        return hsbacttmhTiensubenhgd;
    }

    public void setHsbacttmhTiensubenhgd(String hsbacttmhTiensubenhgd) {
        this.hsbacttmhTiensubenhgd = hsbacttmhTiensubenhgd;
    }

    public Boolean getHsbacttmhDdDiung() {
        return hsbacttmhDdDiung;
    }

    public void setHsbacttmhDdDiung(Boolean hsbacttmhDdDiung) {
        this.hsbacttmhDdDiung = hsbacttmhDdDiung;
    }

    public Boolean getHsbacttmhDdMatuy() {
        return hsbacttmhDdMatuy;
    }

    public void setHsbacttmhDdMatuy(Boolean hsbacttmhDdMatuy) {
        this.hsbacttmhDdMatuy = hsbacttmhDdMatuy;
    }

    public Boolean getHsbacttmhDdRuoubia() {
        return hsbacttmhDdRuoubia;
    }

    public void setHsbacttmhDdRuoubia(Boolean hsbacttmhDdRuoubia) {
        this.hsbacttmhDdRuoubia = hsbacttmhDdRuoubia;
    }

    public Boolean getHsbacttmhDdThuocla() {
        return hsbacttmhDdThuocla;
    }

    public void setHsbacttmhDdThuocla(Boolean hsbacttmhDdThuocla) {
        this.hsbacttmhDdThuocla = hsbacttmhDdThuocla;
    }

    public Boolean getHsbacttmhDdThuoclao() {
        return hsbacttmhDdThuoclao;
    }

    public void setHsbacttmhDdThuoclao(Boolean hsbacttmhDdThuoclao) {
        this.hsbacttmhDdThuoclao = hsbacttmhDdThuoclao;
    }

    public Boolean getHsbacttmhDdKhac() {
        return hsbacttmhDdKhac;
    }

    public void setHsbacttmhDdKhac(Boolean hsbacttmhDdKhac) {
        this.hsbacttmhDdKhac = hsbacttmhDdKhac;
    }

    public String getHsbacttmhDdDiungTg() {
        return hsbacttmhDdDiungTg;
    }

    public void setHsbacttmhDdDiungTg(String hsbacttmhDdDiungTg) {
        this.hsbacttmhDdDiungTg = hsbacttmhDdDiungTg;
    }

    public String getHsbacttmhDdMatuyTg() {
        return hsbacttmhDdMatuyTg;
    }

    public void setHsbacttmhDdMatuyTg(String hsbacttmhDdMatuyTg) {
        this.hsbacttmhDdMatuyTg = hsbacttmhDdMatuyTg;
    }

    public String getHsbacttmhDdRuoubiaTg() {
        return hsbacttmhDdRuoubiaTg;
    }

    public void setHsbacttmhDdRuoubiaTg(String hsbacttmhDdRuoubiaTg) {
        this.hsbacttmhDdRuoubiaTg = hsbacttmhDdRuoubiaTg;
    }

    public String getHsbacttmhDdThuoclaTg() {
        return hsbacttmhDdThuoclaTg;
    }

    public void setHsbacttmhDdThuoclaTg(String hsbacttmhDdThuoclaTg) {
        this.hsbacttmhDdThuoclaTg = hsbacttmhDdThuoclaTg;
    }

    public String getHsbacttmhDdThuoclaoTg() {
        return hsbacttmhDdThuoclaoTg;
    }

    public void setHsbacttmhDdThuoclaoTg(String hsbacttmhDdThuoclaoTg) {
        this.hsbacttmhDdThuoclaoTg = hsbacttmhDdThuoclaoTg;
    }

    public String getHsbacttmhDdKhacTg() {
        return hsbacttmhDdKhacTg;
    }

    public void setHsbacttmhDdKhacTg(String hsbacttmhDdKhacTg) {
        this.hsbacttmhDdKhacTg = hsbacttmhDdKhacTg;
    }

    public String getHsbacttmhTuanhoan() {
        return hsbacttmhTuanhoan;
    }

    public void setHsbacttmhTuanhoan(String hsbacttmhTuanhoan) {
        this.hsbacttmhTuanhoan = hsbacttmhTuanhoan;
    }

    public String getHsbacttmhHohap() {
        return hsbacttmhHohap;
    }

    public void setHsbacttmhHohap(String hsbacttmhHohap) {
        this.hsbacttmhHohap = hsbacttmhHohap;
    }

    public String getHsbacttmhTieuhoa() {
        return hsbacttmhTieuhoa;
    }

    public void setHsbacttmhTieuhoa(String hsbacttmhTieuhoa) {
        this.hsbacttmhTieuhoa = hsbacttmhTieuhoa;
    }

    public String getHsbacttmhThantietnieusinhhoc() {
        return hsbacttmhThantietnieusinhhoc;
    }

    public void setHsbacttmhThantietnieusinhhoc(String hsbacttmhThantietnieusinhhoc) {
        this.hsbacttmhThantietnieusinhhoc = hsbacttmhThantietnieusinhhoc;
    }

    public String getHsbacttmhThankinh() {
        return hsbacttmhThankinh;
    }

    public void setHsbacttmhThankinh(String hsbacttmhThankinh) {
        this.hsbacttmhThankinh = hsbacttmhThankinh;
    }

    public String getHsbacttmhCoxuongkhop() {
        return hsbacttmhCoxuongkhop;
    }

    public void setHsbacttmhCoxuongkhop(String hsbacttmhCoxuongkhop) {
        this.hsbacttmhCoxuongkhop = hsbacttmhCoxuongkhop;
    }

    public String getHsbacttmhRhm() {
        return hsbacttmhRhm;
    }

    public void setHsbacttmhRhm(String hsbacttmhRhm) {
        this.hsbacttmhRhm = hsbacttmhRhm;
    }

    public String getHsbacttmhMat() {
        return hsbacttmhMat;
    }

    public void setHsbacttmhMat(String hsbacttmhMat) {
        this.hsbacttmhMat = hsbacttmhMat;
    }

    public String getHsbacttmhTiendinh() {
        return hsbacttmhTiendinh;
    }

    public void setHsbacttmhTiendinh(String hsbacttmhTiendinh) {
        this.hsbacttmhTiendinh = hsbacttmhTiendinh;
    }

    public String getHsbacttmhTtba() {
        return hsbacttmhTtba;
    }

    public void setHsbacttmhTtba(String hsbacttmhTtba) {
        this.hsbacttmhTtba = hsbacttmhTtba;
    }

    public Integer getHsbacttmhPb() {
        return hsbacttmhPb;
    }

    public void setHsbacttmhPb(Integer hsbacttmhPb) {
        this.hsbacttmhPb = hsbacttmhPb;
    }

    public String getHsbacttmhLydovaov() {
        return hsbacttmhLydovaov;
    }

    public void setHsbacttmhLydovaov(String hsbacttmhLydovaov) {
        this.hsbacttmhLydovaov = hsbacttmhLydovaov;
    }

    public Integer getHsbacttmhNgaybenhthu() {
        return hsbacttmhNgaybenhthu;
    }

    public void setHsbacttmhNgaybenhthu(Integer hsbacttmhNgaybenhthu) {
        this.hsbacttmhNgaybenhthu = hsbacttmhNgaybenhthu;
    }

    public String getHsbacttmhToanthan() {
        return hsbacttmhToanthan;
    }

    public void setHsbacttmhToanthan(String hsbacttmhToanthan) {
        this.hsbacttmhToanthan = hsbacttmhToanthan;
    }

    public String getHsbacttmhQtblDbls() {
        return hsbacttmhQtblDbls;
    }

    public void setHsbacttmhQtblDbls(String hsbacttmhQtblDbls) {
        this.hsbacttmhQtblDbls = hsbacttmhQtblDbls;
    }

    public String getHsbacttmhTtkqxncls() {
        return hsbacttmhTtkqxncls;
    }

    public void setHsbacttmhTtkqxncls(String hsbacttmhTtkqxncls) {
        this.hsbacttmhTtkqxncls = hsbacttmhTtkqxncls;
    }

    public String getHsbacttmhPpdieutri() {
        return hsbacttmhPpdieutri;
    }

    public void setHsbacttmhPpdieutri(String hsbacttmhPpdieutri) {
        this.hsbacttmhPpdieutri = hsbacttmhPpdieutri;
    }

    public String getHsbacttmhTtnguoibenhrav() {
        return hsbacttmhTtnguoibenhrav;
    }

    public void setHsbacttmhTtnguoibenhrav(String hsbacttmhTtnguoibenhrav) {
        this.hsbacttmhTtnguoibenhrav = hsbacttmhTtnguoibenhrav;
    }

    public String getHsbacttmhHuongdtCdtt() {
        return hsbacttmhHuongdtCdtt;
    }

    public void setHsbacttmhHuongdtCdtt(String hsbacttmhHuongdtCdtt) {
        this.hsbacttmhHuongdtCdtt = hsbacttmhHuongdtCdtt;
    }

    public Integer getHsbacttmhSotoxquang() {
        return hsbacttmhSotoxquang;
    }

    public void setHsbacttmhSotoxquang(Integer hsbacttmhSotoxquang) {
        this.hsbacttmhSotoxquang = hsbacttmhSotoxquang;
    }

    public Integer getHsbacttmhSotoctscanner() {
        return hsbacttmhSotoctscanner;
    }

    public void setHsbacttmhSotoctscanner(Integer hsbacttmhSotoctscanner) {
        this.hsbacttmhSotoctscanner = hsbacttmhSotoctscanner;
    }

    public Integer getHsbacttmhSotosieuam() {
        return hsbacttmhSotosieuam;
    }

    public void setHsbacttmhSotosieuam(Integer hsbacttmhSotosieuam) {
        this.hsbacttmhSotosieuam = hsbacttmhSotosieuam;
    }

    public Integer getHsbacttmhSotoxn() {
        return hsbacttmhSotoxn;
    }

    public void setHsbacttmhSotoxn(Integer hsbacttmhSotoxn) {
        this.hsbacttmhSotoxn = hsbacttmhSotoxn;
    }

    public Integer getHsbacttmhSotokhac() {
        return hsbacttmhSotokhac;
    }

    public void setHsbacttmhSotokhac(Integer hsbacttmhSotokhac) {
        this.hsbacttmhSotokhac = hsbacttmhSotokhac;
    }

    public String getHsbacttmhSotoloaikhac() {
        return hsbacttmhSotoloaikhac;
    }

    public void setHsbacttmhSotoloaikhac(String hsbacttmhSotoloaikhac) {
        this.hsbacttmhSotoloaikhac = hsbacttmhSotoloaikhac;
    }

    public Integer getHsbacttmhTongsoto() {
        return hsbacttmhTongsoto;
    }

    public void setHsbacttmhTongsoto(Integer hsbacttmhTongsoto) {
        this.hsbacttmhTongsoto = hsbacttmhTongsoto;
    }

    public String getChitietcoquanbenh() {
        return chitietcoquanbenh;
    }

    public void setChitietcoquanbenh(String chitietcoquanbenh) {
        this.chitietcoquanbenh = chitietcoquanbenh;
    }

    public String getHsbacttmhChitietbenhcoquankhac() {
        return hsbacttmhChitietbenhcoquankhac;
    }

    public void setHsbacttmhChitietbenhcoquankhac(String hsbacttmhChitietbenhcoquankhac) {
        this.hsbacttmhChitietbenhcoquankhac = hsbacttmhChitietbenhcoquankhac;
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

    public DtDmNhanVien getHsbacttmhBslamba() {
        return hsbacttmhBslamba;
    }

    public void setHsbacttmhBslamba(DtDmNhanVien hsbacttmhBslamba) {
        this.hsbacttmhBslamba = hsbacttmhBslamba;
    }

    public DtDmNhanVien getHsbacttmhNguoigiaoba() {
        return hsbacttmhNguoigiaoba;
    }

    public void setHsbacttmhNguoigiaoba(DtDmNhanVien hsbacttmhNguoigiaoba) {
        this.hsbacttmhNguoigiaoba = hsbacttmhNguoigiaoba;
    }

    public DtDmNhanVien getHsbacttmhNguoinhanba() {
        return hsbacttmhNguoinhanba;
    }

    public void setHsbacttmhNguoinhanba(DtDmNhanVien hsbacttmhNguoinhanba) {
        this.hsbacttmhNguoinhanba = hsbacttmhNguoinhanba;
    }

    public DtDmNhanVien getHsbacttmhBsdieutri() {
        return hsbacttmhBsdieutri;
    }

    public void setHsbacttmhBsdieutri(DtDmNhanVien hsbacttmhBsdieutri) {
        this.hsbacttmhBsdieutri = hsbacttmhBsdieutri;
    }

    public String getHsbacttmhTienluong() {
        return hsbacttmhTienluong;
    }

    public void setHsbacttmhTienluong(String hsbacttmhTienluong) {
        this.hsbacttmhTienluong = hsbacttmhTienluong;
    }
    
    public DtDmNhanVien getHsbacttmhBslamba(boolean create) {
        if (create && hsbacttmhBslamba == null) {
            hsbacttmhBslamba = new DtDmNhanVien();
        }
        return hsbacttmhBslamba;
    }

    public DtDmNhanVien getHsbacttmhNguoigiaoba(boolean create) {
        if (create && hsbacttmhNguoigiaoba == null) {
            hsbacttmhNguoigiaoba = new DtDmNhanVien();
        }
        return hsbacttmhNguoigiaoba;
    }

    public DtDmNhanVien getHsbacttmhNguoinhanba(boolean create) {
        if (create && hsbacttmhNguoinhanba == null) {
            hsbacttmhNguoinhanba = new DtDmNhanVien();
        }
        return hsbacttmhNguoinhanba;
    }

    public DtDmNhanVien getHsbacttmhBsdieutri(boolean create) {
        if (create && hsbacttmhBsdieutri == null) {
            hsbacttmhBsdieutri = new DtDmNhanVien();
        }
        return hsbacttmhBsdieutri;
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
        hash += (hsbacttmhMa != null ? hsbacttmhMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietTmh)) {
            return false;
        }
        HsbaChiTietTmh other = (HsbaChiTietTmh) object;
        if ((this.hsbacttmhMa == null && other.hsbacttmhMa != null) || (this.hsbacttmhMa != null && !this.hsbacttmhMa.equals(other.hsbacttmhMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.test.HsbaChiTietTmh[hsbacttmhMa=" + hsbacttmhMa + "]";
    }

}
