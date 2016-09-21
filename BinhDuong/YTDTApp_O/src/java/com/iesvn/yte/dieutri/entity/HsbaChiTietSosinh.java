/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author halylam
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_CHI_TIET_SOSINH")
@NamedQueries({
    @NamedQuery(name = "HsbaChiTietSosinh.findAll", query = "SELECT h FROM HsbaChiTietSosinh h"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhMa", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhMa = :hsbactsosinhMa"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhQtbenhly", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhQtbenhly = :hsbactsosinhQtbenhly"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhLydovaov", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhLydovaov = :hsbactsosinhLydovaov"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhNgaybenhthu", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhNgaybenhthu = :hsbactsosinhNgaybenhthu"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhQtblDbls", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhQtblDbls = :hsbactsosinhQtblDbls"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhTtkqxncls", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhTtkqxncls = :hsbactsosinhTtkqxncls"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhPpdieutri", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhPpdieutri = :hsbactsosinhPpdieutri"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhTtnguoibenhrav", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhTtnguoibenhrav = :hsbactsosinhTtnguoibenhrav"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhHuongdtCdtt", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhHuongdtCdtt = :hsbactsosinhHuongdtCdtt"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhSotoxquang", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhSotoxquang = :hsbactsosinhSotoxquang"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhSotoctscanner", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhSotoctscanner = :hsbactsosinhSotoctscanner"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhSotosieuam", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhSotosieuam = :hsbactsosinhSotosieuam"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhSotoxn", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhSotoxn = :hsbactsosinhSotoxn"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhSotokhac", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhSotokhac = :hsbactsosinhSotokhac"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhSotoloaikhac", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhSotoloaikhac = :hsbactsosinhSotoloaikhac"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhTongsoto", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhTongsoto = :hsbactsosinhTongsoto"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhHotenBo", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhHotenBo = :hsbactsosinhHotenBo"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhHotenMe", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhHotenMe = :hsbactsosinhHotenMe"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhNgaysinhBo", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhNgaysinhBo = :hsbactsosinhNgaysinhBo"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhNgaysinhMe", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhNgaysinhMe = :hsbactsosinhNgaysinhMe"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhDelanmay", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhDelanmay = :hsbactsosinhDelanmay"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhNhommaume", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhNhommaume = :hsbactsosinhNhommaume"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhSinhduthang", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhSinhduthang = :hsbactsosinhSinhduthang"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhSinhdenon", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhSinhdenon = :hsbactsosinhSinhdenon"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhSinhnaohut", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhSinhnaohut = :hsbactsosinhSinhnaohut"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhSinhsong", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhSinhsong = :hsbactsosinhSinhsong"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhDethuong", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhDethuong = :hsbactsosinhDethuong"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCanthiep", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCanthiep = :hsbactsosinhCanthiep"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhLydocanthiep", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhLydocanthiep = :hsbactsosinhLydocanthiep"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhVooiluc", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhVooiluc = :hsbactsosinhVooiluc"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhDeluc", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhDeluc = :hsbactsosinhDeluc"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhMausac", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhMausac = :hsbactsosinhMausac"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhTinhtrangKhocngay", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhTinhtrangKhocngay = :hsbactsosinhTinhtrangKhocngay"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhTinhtrangNgat", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhTinhtrangNgat = :hsbactsosinhTinhtrangNgat"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhTinhtrangKhac", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhTinhtrangKhac = :hsbactsosinhTinhtrangKhac"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhHotennguoidode", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhHotennguoidode = :hsbactsosinhHotennguoidode"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhApgar1phut", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhApgar1phut = :hsbactsosinhApgar1phut"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhApgar5phut", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhApgar5phut = :hsbactsosinhApgar5phut"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhApgar10phut", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhApgar10phut = :hsbactsosinhApgar10phut"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCannanglucsinh", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCannanglucsinh = :hsbactsosinhCannanglucsinh"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhTinhtrangdinhduong", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhTinhtrangdinhduong = :hsbactsosinhTinhtrangdinhduong"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhHoisinhHutdich", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhHoisinhHutdich = :hsbactsosinhHoisinhHutdich"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhHoisinhXoaboptim", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhHoisinhXoaboptim = :hsbactsosinhHoisinhXoaboptim"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhHoisinhThoo2", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhHoisinhThoo2 = :hsbactsosinhHoisinhThoo2"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhHoisinhDatnoikhiquan", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhHoisinhDatnoikhiquan = :hsbactsosinhHoisinhDatnoikhiquan"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhHoisinhBopbongo2", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhHoisinhBopbongo2 = :hsbactsosinhHoisinhBopbongo2"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhHoisinhKhac", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhHoisinhKhac = :hsbactsosinhHoisinhKhac"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhHotennguoichuyensosinh", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhHotennguoichuyensosinh = :hsbactsosinhHotennguoichuyensosinh"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhDitatbamsinh", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhDitatbamsinh = :hsbactsosinhDitatbamsinh"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCohaumon", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCohaumon = :hsbactsosinhCohaumon"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCutheditat", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCutheditat = :hsbactsosinhCutheditat"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhTinhhinhsosinhvaokhoa", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhTinhhinhsosinhvaokhoa = :hsbactsosinhTinhhinhsosinhvaokhoa"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhTinhtrangtoanthan", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhTinhtrangtoanthan = :hsbactsosinhTinhtrangtoanthan"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhMausacdaHonghao", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhMausacdaHonghao = :hsbactsosinhMausacdaHonghao"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhMausacdaXanhtai", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhMausacdaXanhtai = :hsbactsosinhMausacdaXanhtai"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhMausacdaVang", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhMausacdaVang = :hsbactsosinhMausacdaVang"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhMausacdaTim", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhMausacdaTim = :hsbactsosinhMausacdaTim"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhMausacdaKhac", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhMausacdaKhac = :hsbactsosinhMausacdaKhac"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhChieudai", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhChieudai = :hsbactsosinhChieudai"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCannang", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCannang = :hsbactsosinhCannang"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhVongdau", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhVongdau = :hsbactsosinhVongdau"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhToanthanNhiptho", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhToanthanNhiptho = :hsbactsosinhToanthanNhiptho"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhToanthanNhietdo", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhToanthanNhietdo = :hsbactsosinhToanthanNhietdo"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCoquankhacNhiptho", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCoquankhacNhiptho = :hsbactsosinhCoquankhacNhiptho"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCoquankhacNghephoi", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCoquankhacNghephoi = :hsbactsosinhCoquankhacNghephoi"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCoquankhacSilverman", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCoquankhacSilverman = :hsbactsosinhCoquankhacSilverman"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCoquankhacNhiptim", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCoquankhacNhiptim = :hsbactsosinhCoquankhacNhiptim"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCoquankhacBung", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCoquankhacBung = :hsbactsosinhCoquankhacBung"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCoquankhacSinhducngoai", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCoquankhacSinhducngoai = :hsbactsosinhCoquankhacSinhducngoai"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCoquankhacXuongkhop", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCoquankhacXuongkhop = :hsbactsosinhCoquankhacXuongkhop"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCoquankhacThankinhPhanxa", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCoquankhacThankinhPhanxa = :hsbactsosinhCoquankhacThankinhPhanxa"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhCoquankhacThankinhTruonglucco", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhCoquankhacThankinhTruonglucco = :hsbactsosinhCoquankhacThankinhTruonglucco"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhTtba", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhTtba = :hsbactsosinhTtba"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhXetnghiemcanlamsan", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhXetnghiemcanlamsan = :hsbactsosinhXetnghiemcanlamsan"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhNghenghiepBo", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhNghenghiepBo = :hsbactsosinhNghenghiepBo"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhNghenghiepMe", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhNghenghiepMe = :hsbactsosinhNghenghiepMe"),
    @NamedQuery(name = "HsbaChiTietSosinh.findByHsbactsosinhChidinhtheodoi", query = "SELECT h FROM HsbaChiTietSosinh h WHERE h.hsbactsosinhChidinhtheodoi = :hsbactsosinhChidinhtheodoi")})
public class HsbaChiTietSosinh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_SOSINH")
    @SequenceGenerator(name = "HSBA_CHI_TIET_SOSINH", sequenceName = "HSBA_CHI_TIET_SOSINH_HSBACTSOS", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTSOSINH_MA")
    private Integer hsbactsosinhMa;
    @Column(name = "HSBACTSOSINH_QTBENHLY")
    private String hsbactsosinhQtbenhly;
    @Column(name = "HSBACTSOSINH_LYDOVAOV")
    private String hsbactsosinhLydovaov;
    @Column(name = "HSBACTSOSINH_NGAYBENHTHU")
    private Integer hsbactsosinhNgaybenhthu;
    @Column(name = "HSBACTSOSINH_QTBL_DBLS")
    private String hsbactsosinhQtblDbls;
    @Column(name = "HSBACTSOSINH_TTKQXNCLS")
    private String hsbactsosinhTtkqxncls;
    @Column(name = "HSBACTSOSINH_PPDIEUTRI")
    private String hsbactsosinhPpdieutri;
    @Column(name = "HSBACTSOSINH_TTNGUOIBENHRAV")
    private String hsbactsosinhTtnguoibenhrav;
    @Column(name = "HSBACTSOSINH_HUONGDT_CDTT")
    private String hsbactsosinhHuongdtCdtt;
    @Column(name = "HSBACTSOSINH_SOTOXQUANG")
    private Integer hsbactsosinhSotoxquang;
    @Column(name = "HSBACTSOSINH_SOTOCTSCANNER")
    private Integer hsbactsosinhSotoctscanner;
    @Column(name = "HSBACTSOSINH_SOTOSIEUAM")
    private Integer hsbactsosinhSotosieuam;
    @Column(name = "HSBACTSOSINH_SOTOXN")
    private Integer hsbactsosinhSotoxn;
    @Column(name = "HSBACTSOSINH_SOTOKHAC")
    private Integer hsbactsosinhSotokhac;
    @Column(name = "HSBACTSOSINH_SOTOLOAIKHAC")
    private String hsbactsosinhSotoloaikhac;
    @Column(name = "HSBACTSOSINH_TONGSOTO")
    private Integer hsbactsosinhTongsoto;
    @Column(name = "HSBACTSOSINH_HOTEN_BO")
    private String hsbactsosinhHotenBo;
    @Column(name = "HSBACTSOSINH_HOTEN_ME")
    private String hsbactsosinhHotenMe;
    @Column(name = "HSBACTSOSINH_NGAYSINH_BO")
    @Temporal(TemporalType.DATE)
    private Date hsbactsosinhNgaysinhBo;
    @Column(name = "HSBACTSOSINH_NGAYSINH_ME")
    @Temporal(TemporalType.DATE)
    private Date hsbactsosinhNgaysinhMe;
    @Column(name = "HSBACTSOSINH_DELANMAY")
    private String hsbactsosinhDelanmay;
    @Column(name = "HSBACTSOSINH_NHOMMAUME")
    private String hsbactsosinhNhommaume;
    @Column(name = "HSBACTSOSINH_SINHDUTHANG")
    private Boolean hsbactsosinhSinhduthang;
    @Column(name = "HSBACTSOSINH_SINHDENON")
    private Boolean hsbactsosinhSinhdenon;
    @Column(name = "HSBACTSOSINH_SINHNAOHUT")
    private Boolean hsbactsosinhSinhnaohut;
    @Column(name = "HSBACTSOSINH_SINHSONG")
    private Boolean hsbactsosinhSinhsong;
    @Column(name = "HSBACTSOSINH_DETHUONG")
    private Boolean hsbactsosinhDethuong;
    @Column(name = "HSBACTSOSINH_CANTHIEP")
    private Boolean hsbactsosinhCanthiep;
    @Column(name = "HSBACTSOSINH_LYDOCANTHIEP")
    private String hsbactsosinhLydocanthiep;
    @Column(name = "HSBACTSOSINH_VOOILUC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbactsosinhVooiluc;
    @Column(name = "HSBACTSOSINH_DELUC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbactsosinhDeluc;
    @Column(name = "HSBACTSOSINH_MAUSAC")
    private String hsbactsosinhMausac;
    @Column(name = "HSBACTSOSINH_TTRANG_KHOCNGAY")
    private Boolean hsbactsosinhTinhtrangKhocngay;
    @Column(name = "HSBACTSOSINH_TINHTRANG_NGAT")
    private Boolean hsbactsosinhTinhtrangNgat;
    @Column(name = "HSBACTSOSINH_TINHTRANG_KHAC")
    private Boolean hsbactsosinhTinhtrangKhac;
    @Column(name = "HSBACTSOSINH_HOTENNGUOIDODE")
    private String hsbactsosinhHotennguoidode;
    @Column(name = "HSBACTSOSINH_APGAR1PHUT")
    private String hsbactsosinhApgar1phut;
    @Column(name = "HSBACTSOSINH_APGAR5PHUT")
    private String hsbactsosinhApgar5phut;
    @Column(name = "HSBACTSOSINH_APGAR10PHUT")
    private String hsbactsosinhApgar10phut;
    @Column(name = "HSBACTSOSINH_CANNANGLUCSINH")
    private String hsbactsosinhCannanglucsinh;
    @Column(name = "HSBACTSOSINH_TTRANGDINHDUONG")
    private String hsbactsosinhTinhtrangdinhduong;
    @Column(name = "HSBACTSOSINH_HOISINH_HUTDICH")
    private Boolean hsbactsosinhHoisinhHutdich;
    @Column(name = "HSBACTSOSINH_HOISINH_XOABOPTIM")
    private Boolean hsbactsosinhHoisinhXoaboptim;
    @Column(name = "HSBACTSOSINH_HOISINH_THOO2")
    private Boolean hsbactsosinhHoisinhThoo2;
    @Column(name = "HSBACTSOSINH_HOISINH_DATNKQUAN")
    private Boolean hsbactsosinhHoisinhDatnoikhiquan;
    @Column(name = "HSBACTSOSINH_HOISINH_BOPBONGO2")
    private Boolean hsbactsosinhHoisinhBopbongo2;
    @Column(name = "HSBACTSOSINH_HOISINH_KHAC")
    private Boolean hsbactsosinhHoisinhKhac;
    @Column(name = "HSBACTSOSINH_HTENNGCHUYENSSINH")
    private String hsbactsosinhHotennguoichuyensosinh;
    @Column(name = "HSBACTSOSINH_DITATBAMSINH")
    private Boolean hsbactsosinhDitatbamsinh;
    @Column(name = "HSBACTSOSINH_COHAUMON")
    private Boolean hsbactsosinhCohaumon;
    @Column(name = "HSBACTSOSINH_CUTHEDITAT")
    private String hsbactsosinhCutheditat;
    @Column(name = "HSBACTSOSINH_THSOSINHVAOKHOA")
    private String hsbactsosinhTinhhinhsosinhvaokhoa;
    @Column(name = "HSBACTSOSINH_TINHTRANGTOANTHAN")
    private String hsbactsosinhTinhtrangtoanthan;
    @Column(name = "HSBACTSOSINH_MAUSACDA_HONGHAO")
    private Boolean hsbactsosinhMausacdaHonghao;
    @Column(name = "HSBACTSOSINH_MAUSACDA_XANHTAI")
    private Boolean hsbactsosinhMausacdaXanhtai;
    @Column(name = "HSBACTSOSINH_MAUSACDA_VANG")
    private Boolean hsbactsosinhMausacdaVang;
    @Column(name = "HSBACTSOSINH_MAUSACDA_TIM")
    private Boolean hsbactsosinhMausacdaTim;
    @Column(name = "HSBACTSOSINH_MAUSACDA_KHAC")
    private Boolean hsbactsosinhMausacdaKhac;
    @Column(name = "HSBACTSOSINH_CHIEUDAI")
    private String hsbactsosinhChieudai;
    @Column(name = "HSBACTSOSINH_CANNANG")
    private String hsbactsosinhCannang;
    @Column(name = "HSBACTSOSINH_VONGDAU")
    private String hsbactsosinhVongdau;
    @Column(name = "HSBACTSOSINH_TOANTHAN_NHIPTHO")
    private String hsbactsosinhToanthanNhiptho;
    @Column(name = "HSBACTSOSINH_TOANTHAN_NHIETDO")
    private String hsbactsosinhToanthanNhietdo;
    @Column(name = "HSBACTSOSINH_CQKHAC_NHIPTHO")
    private String hsbactsosinhCoquankhacNhiptho;
    @Column(name = "HSBACTSOSINH_CQKHAC_NGHEPHOI")
    private String hsbactsosinhCoquankhacNghephoi;
    @Column(name = "HSBACTSOSINH_CQKHAC_SILVERMAN")
    private String hsbactsosinhCoquankhacSilverman;
    @Column(name = "HSBACTSOSINH_CQKHAC_NHIPTIM")
    private String hsbactsosinhCoquankhacNhiptim;
    @Column(name = "HSBACTSOSINH_COQUANKHAC_BUNG")
    private String hsbactsosinhCoquankhacBung;
    @Column(name = "HSBACTSOSINH_CQKHAC_SDUCNGOAI")
    private String hsbactsosinhCoquankhacSinhducngoai;
    @Column(name = "HSBACTSOSINH_CQKHAC_XUONGKHOP")
    private String hsbactsosinhCoquankhacXuongkhop;
    @Column(name = "HSBACTSOSINH_CQKHAC_TKINH_PXA")
    private String hsbactsosinhCoquankhacThankinhPhanxa;
    @Column(name = "HSBACTSOSINH_CQKHAC_TKINH_TLCO")
    private String hsbactsosinhCoquankhacThankinhTruonglucco;
    @Column(name = "HSBACTSOSINH_TTBA")
    private String hsbactsosinhTtba;
    @Column(name = "HSBACTSOSINH_XETNGHIEMCLAMSAN")
    private String hsbactsosinhXetnghiemcanlamsan;
    @Column(name = "HSBACTSOSINH_NGHENGHIEP_BO")
    private String hsbactsosinhNghenghiepBo;
    @Column(name = "HSBACTSOSINH_NGHENGHIEP_ME")
    private String hsbactsosinhNghenghiepMe;
    @Column(name = "HSBACTSOSINH_CHIDINHTHEODOI")
    private String hsbactsosinhChidinhtheodoi;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;
    @JoinColumn(name = "HSBACTSOSINH_BSLAMBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactsosinhBslamba;
    @JoinColumn(name = "HSBACTSOSINH_NGUOIGIAOBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactsosinhNguoigiaoba;
    @JoinColumn(name = "HSBACTSOSINH_NGUOINHANBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactsosinhNguoinhanba;
    @JoinColumn(name = "HSBACTSOSINH_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactsosinhBsdieutri;

    public HsbaChiTietSosinh() {
    }

    public HsbaChiTietSosinh(Integer hsbactsosinhMa) {
        this.hsbactsosinhMa = hsbactsosinhMa;
    }

    public String getHsbactsosinhNghenghiepBo() {
        return hsbactsosinhNghenghiepBo;
    }

    public void setHsbactsosinhNghenghiepBo(String hsbactsosinhNghenghiepBo) {
        this.hsbactsosinhNghenghiepBo = hsbactsosinhNghenghiepBo;
    }

    public String getHsbactsosinhNghenghiepMe() {
        return hsbactsosinhNghenghiepMe;
    }

    public void setHsbactsosinhNghenghiepMe(String hsbactsosinhNghenghiepMe) {
        this.hsbactsosinhNghenghiepMe = hsbactsosinhNghenghiepMe;
    }

    public Integer getHsbactsosinhMa() {
        return hsbactsosinhMa;
    }

    public void setHsbactsosinhMa(Integer hsbactsosinhMa) {
        this.hsbactsosinhMa = hsbactsosinhMa;
    }

    public String getHsbactsosinhQtbenhly() {
        return hsbactsosinhQtbenhly;
    }

    public void setHsbactsosinhQtbenhly(String hsbactsosinhQtbenhly) {
        this.hsbactsosinhQtbenhly = hsbactsosinhQtbenhly;
    }

    public String getHsbactsosinhLydovaov() {
        return hsbactsosinhLydovaov;
    }

    public void setHsbactsosinhLydovaov(String hsbactsosinhLydovaov) {
        this.hsbactsosinhLydovaov = hsbactsosinhLydovaov;
    }

    public Integer getHsbactsosinhNgaybenhthu() {
        return hsbactsosinhNgaybenhthu;
    }

    public void setHsbactsosinhNgaybenhthu(Integer hsbactsosinhNgaybenhthu) {
        this.hsbactsosinhNgaybenhthu = hsbactsosinhNgaybenhthu;
    }

    public String getHsbactsosinhQtblDbls() {
        return hsbactsosinhQtblDbls;
    }

    public void setHsbactsosinhQtblDbls(String hsbactsosinhQtblDbls) {
        this.hsbactsosinhQtblDbls = hsbactsosinhQtblDbls;
    }

    public String getHsbactsosinhTtkqxncls() {
        return hsbactsosinhTtkqxncls;
    }

    public void setHsbactsosinhTtkqxncls(String hsbactsosinhTtkqxncls) {
        this.hsbactsosinhTtkqxncls = hsbactsosinhTtkqxncls;
    }

    public String getHsbactsosinhPpdieutri() {
        return hsbactsosinhPpdieutri;
    }

    public void setHsbactsosinhPpdieutri(String hsbactsosinhPpdieutri) {
        this.hsbactsosinhPpdieutri = hsbactsosinhPpdieutri;
    }

    public String getHsbactsosinhTtnguoibenhrav() {
        return hsbactsosinhTtnguoibenhrav;
    }

    public void setHsbactsosinhTtnguoibenhrav(String hsbactsosinhTtnguoibenhrav) {
        this.hsbactsosinhTtnguoibenhrav = hsbactsosinhTtnguoibenhrav;
    }

    public String getHsbactsosinhHuongdtCdtt() {
        return hsbactsosinhHuongdtCdtt;
    }

    public void setHsbactsosinhHuongdtCdtt(String hsbactsosinhHuongdtCdtt) {
        this.hsbactsosinhHuongdtCdtt = hsbactsosinhHuongdtCdtt;
    }

    public Integer getHsbactsosinhSotoxquang() {
        return hsbactsosinhSotoxquang;
    }

    public void setHsbactsosinhSotoxquang(Integer hsbactsosinhSotoxquang) {
        this.hsbactsosinhSotoxquang = hsbactsosinhSotoxquang;
    }

    public Integer getHsbactsosinhSotoctscanner() {
        return hsbactsosinhSotoctscanner;
    }

    public void setHsbactsosinhSotoctscanner(Integer hsbactsosinhSotoctscanner) {
        this.hsbactsosinhSotoctscanner = hsbactsosinhSotoctscanner;
    }

    public Integer getHsbactsosinhSotosieuam() {
        return hsbactsosinhSotosieuam;
    }

    public void setHsbactsosinhSotosieuam(Integer hsbactsosinhSotosieuam) {
        this.hsbactsosinhSotosieuam = hsbactsosinhSotosieuam;
    }

    public Integer getHsbactsosinhSotoxn() {
        return hsbactsosinhSotoxn;
    }

    public void setHsbactsosinhSotoxn(Integer hsbactsosinhSotoxn) {
        this.hsbactsosinhSotoxn = hsbactsosinhSotoxn;
    }

    public Integer getHsbactsosinhSotokhac() {
        return hsbactsosinhSotokhac;
    }

    public void setHsbactsosinhSotokhac(Integer hsbactsosinhSotokhac) {
        this.hsbactsosinhSotokhac = hsbactsosinhSotokhac;
    }

    public String getHsbactsosinhSotoloaikhac() {
        return hsbactsosinhSotoloaikhac;
    }

    public void setHsbactsosinhSotoloaikhac(String hsbactsosinhSotoloaikhac) {
        this.hsbactsosinhSotoloaikhac = hsbactsosinhSotoloaikhac;
    }

    public Integer getHsbactsosinhTongsoto() {
        return hsbactsosinhTongsoto;
    }

    public void setHsbactsosinhTongsoto(Integer hsbactsosinhTongsoto) {
        this.hsbactsosinhTongsoto = hsbactsosinhTongsoto;
    }

    public String getHsbactsosinhHotenBo() {
        return hsbactsosinhHotenBo;
    }

    public void setHsbactsosinhHotenBo(String hsbactsosinhHotenBo) {
        this.hsbactsosinhHotenBo = hsbactsosinhHotenBo;
    }

    public String getHsbactsosinhHotenMe() {
        return hsbactsosinhHotenMe;
    }

    public void setHsbactsosinhHotenMe(String hsbactsosinhHotenMe) {
        this.hsbactsosinhHotenMe = hsbactsosinhHotenMe;
    }

    public Date getHsbactsosinhNgaysinhBo() {
        return hsbactsosinhNgaysinhBo;
    }

    public void setHsbactsosinhNgaysinhBo(Date hsbactsosinhNgaysinhBo) {
        this.hsbactsosinhNgaysinhBo = hsbactsosinhNgaysinhBo;
    }

    public Date getHsbactsosinhNgaysinhMe() {
        return hsbactsosinhNgaysinhMe;
    }

    public void setHsbactsosinhNgaysinhMe(Date hsbactsosinhNgaysinhMe) {
        this.hsbactsosinhNgaysinhMe = hsbactsosinhNgaysinhMe;
    }

    public String getHsbactsosinhDelanmay() {
        return hsbactsosinhDelanmay;
    }

    public void setHsbactsosinhDelanmay(String hsbactsosinhDelanmay) {
        this.hsbactsosinhDelanmay = hsbactsosinhDelanmay;
    }

    public String getHsbactsosinhNhommaume() {
        return hsbactsosinhNhommaume;
    }

    public void setHsbactsosinhNhommaume(String hsbactsosinhNhommaume) {
        this.hsbactsosinhNhommaume = hsbactsosinhNhommaume;
    }

    public Boolean getHsbactsosinhSinhduthang() {
        return hsbactsosinhSinhduthang;
    }

    public void setHsbactsosinhSinhduthang(Boolean hsbactsosinhSinhduthang) {
        this.hsbactsosinhSinhduthang = hsbactsosinhSinhduthang;
    }

    public Boolean getHsbactsosinhSinhdenon() {
        return hsbactsosinhSinhdenon;
    }

    public void setHsbactsosinhSinhdenon(Boolean hsbactsosinhSinhdenon) {
        this.hsbactsosinhSinhdenon = hsbactsosinhSinhdenon;
    }

    public Boolean getHsbactsosinhSinhnaohut() {
        return hsbactsosinhSinhnaohut;
    }

    public void setHsbactsosinhSinhnaohut(Boolean hsbactsosinhSinhnaohut) {
        this.hsbactsosinhSinhnaohut = hsbactsosinhSinhnaohut;
    }

    public Boolean getHsbactsosinhSinhsong() {
        return hsbactsosinhSinhsong;
    }

    public void setHsbactsosinhSinhsong(Boolean hsbactsosinhSinhsong) {
        this.hsbactsosinhSinhsong = hsbactsosinhSinhsong;
    }

    public Boolean getHsbactsosinhDethuong() {
        return hsbactsosinhDethuong;
    }

    public void setHsbactsosinhDethuong(Boolean hsbactsosinhDethuong) {
        this.hsbactsosinhDethuong = hsbactsosinhDethuong;
    }

    public Boolean getHsbactsosinhCanthiep() {
        return hsbactsosinhCanthiep;
    }

    public void setHsbactsosinhCanthiep(Boolean hsbactsosinhCanthiep) {
        this.hsbactsosinhCanthiep = hsbactsosinhCanthiep;
    }

    public String getHsbactsosinhLydocanthiep() {
        return hsbactsosinhLydocanthiep;
    }

    public void setHsbactsosinhLydocanthiep(String hsbactsosinhLydocanthiep) {
        this.hsbactsosinhLydocanthiep = hsbactsosinhLydocanthiep;
    }

    public Date getHsbactsosinhVooiluc() {
        return hsbactsosinhVooiluc;
    }

    public void setHsbactsosinhVooiluc(Date hsbactsosinhVooiluc) {
        this.hsbactsosinhVooiluc = hsbactsosinhVooiluc;
    }

    public Date getHsbactsosinhDeluc() {
        return hsbactsosinhDeluc;
    }

    public void setHsbactsosinhDeluc(Date hsbactsosinhDeluc) {
        this.hsbactsosinhDeluc = hsbactsosinhDeluc;
    }

    public String getHsbactsosinhMausac() {
        return hsbactsosinhMausac;
    }

    public void setHsbactsosinhMausac(String hsbactsosinhMausac) {
        this.hsbactsosinhMausac = hsbactsosinhMausac;
    }

    public Boolean getHsbactsosinhTinhtrangKhocngay() {
        return hsbactsosinhTinhtrangKhocngay;
    }

    public void setHsbactsosinhTinhtrangKhocngay(Boolean hsbactsosinhTinhtrangKhocngay) {
        this.hsbactsosinhTinhtrangKhocngay = hsbactsosinhTinhtrangKhocngay;
    }

    public Boolean getHsbactsosinhTinhtrangNgat() {
        return hsbactsosinhTinhtrangNgat;
    }

    public void setHsbactsosinhTinhtrangNgat(Boolean hsbactsosinhTinhtrangNgat) {
        this.hsbactsosinhTinhtrangNgat = hsbactsosinhTinhtrangNgat;
    }

    public Boolean getHsbactsosinhTinhtrangKhac() {
        return hsbactsosinhTinhtrangKhac;
    }

    public void setHsbactsosinhTinhtrangKhac(Boolean hsbactsosinhTinhtrangKhac) {
        this.hsbactsosinhTinhtrangKhac = hsbactsosinhTinhtrangKhac;
    }

    public String getHsbactsosinhHotennguoidode() {
        return hsbactsosinhHotennguoidode;
    }

    public void setHsbactsosinhHotennguoidode(String hsbactsosinhHotennguoidode) {
        this.hsbactsosinhHotennguoidode = hsbactsosinhHotennguoidode;
    }

    public String getHsbactsosinhApgar1phut() {
        return hsbactsosinhApgar1phut;
    }

    public void setHsbactsosinhApgar1phut(String hsbactsosinhApgar1phut) {
        this.hsbactsosinhApgar1phut = hsbactsosinhApgar1phut;
    }

    public String getHsbactsosinhApgar5phut() {
        return hsbactsosinhApgar5phut;
    }

    public void setHsbactsosinhApgar5phut(String hsbactsosinhApgar5phut) {
        this.hsbactsosinhApgar5phut = hsbactsosinhApgar5phut;
    }

    public String getHsbactsosinhApgar10phut() {
        return hsbactsosinhApgar10phut;
    }

    public void setHsbactsosinhApgar10phut(String hsbactsosinhApgar10phut) {
        this.hsbactsosinhApgar10phut = hsbactsosinhApgar10phut;
    }

    public String getHsbactsosinhCannanglucsinh() {
        return hsbactsosinhCannanglucsinh;
    }

    public void setHsbactsosinhCannanglucsinh(String hsbactsosinhCannanglucsinh) {
        this.hsbactsosinhCannanglucsinh = hsbactsosinhCannanglucsinh;
    }

    public String getHsbactsosinhTinhtrangdinhduong() {
        return hsbactsosinhTinhtrangdinhduong;
    }

    public void setHsbactsosinhTinhtrangdinhduong(String hsbactsosinhTinhtrangdinhduong) {
        this.hsbactsosinhTinhtrangdinhduong = hsbactsosinhTinhtrangdinhduong;
    }

    public Boolean getHsbactsosinhHoisinhHutdich() {
        return hsbactsosinhHoisinhHutdich;
    }

    public void setHsbactsosinhHoisinhHutdich(Boolean hsbactsosinhHoisinhHutdich) {
        this.hsbactsosinhHoisinhHutdich = hsbactsosinhHoisinhHutdich;
    }

    public Boolean getHsbactsosinhHoisinhXoaboptim() {
        return hsbactsosinhHoisinhXoaboptim;
    }

    public void setHsbactsosinhHoisinhXoaboptim(Boolean hsbactsosinhHoisinhXoaboptim) {
        this.hsbactsosinhHoisinhXoaboptim = hsbactsosinhHoisinhXoaboptim;
    }

    public Boolean getHsbactsosinhHoisinhThoo2() {
        return hsbactsosinhHoisinhThoo2;
    }

    public void setHsbactsosinhHoisinhThoo2(Boolean hsbactsosinhHoisinhThoo2) {
        this.hsbactsosinhHoisinhThoo2 = hsbactsosinhHoisinhThoo2;
    }

    public Boolean getHsbactsosinhHoisinhDatnoikhiquan() {
        return hsbactsosinhHoisinhDatnoikhiquan;
    }

    public void setHsbactsosinhHoisinhDatnoikhiquan(Boolean hsbactsosinhHoisinhDatnoikhiquan) {
        this.hsbactsosinhHoisinhDatnoikhiquan = hsbactsosinhHoisinhDatnoikhiquan;
    }

    public Boolean getHsbactsosinhHoisinhBopbongo2() {
        return hsbactsosinhHoisinhBopbongo2;
    }

    public void setHsbactsosinhHoisinhBopbongo2(Boolean hsbactsosinhHoisinhBopbongo2) {
        this.hsbactsosinhHoisinhBopbongo2 = hsbactsosinhHoisinhBopbongo2;
    }

    public Boolean getHsbactsosinhHoisinhKhac() {
        return hsbactsosinhHoisinhKhac;
    }

    public void setHsbactsosinhHoisinhKhac(Boolean hsbactsosinhHoisinhKhac) {
        this.hsbactsosinhHoisinhKhac = hsbactsosinhHoisinhKhac;
    }

    public String getHsbactsosinhHotennguoichuyensosinh() {
        return hsbactsosinhHotennguoichuyensosinh;
    }

    public void setHsbactsosinhHotennguoichuyensosinh(String hsbactsosinhHotennguoichuyensosinh) {
        this.hsbactsosinhHotennguoichuyensosinh = hsbactsosinhHotennguoichuyensosinh;
    }

    public Boolean getHsbactsosinhDitatbamsinh() {
        return hsbactsosinhDitatbamsinh;
    }

    public void setHsbactsosinhDitatbamsinh(Boolean hsbactsosinhDitatbamsinh) {
        this.hsbactsosinhDitatbamsinh = hsbactsosinhDitatbamsinh;
    }

    public Boolean getHsbactsosinhCohaumon() {
        return hsbactsosinhCohaumon;
    }

    public void setHsbactsosinhCohaumon(Boolean hsbactsosinhCohaumon) {
        this.hsbactsosinhCohaumon = hsbactsosinhCohaumon;
    }

    public String getHsbactsosinhCutheditat() {
        return hsbactsosinhCutheditat;
    }

    public void setHsbactsosinhCutheditat(String hsbactsosinhCutheditat) {
        this.hsbactsosinhCutheditat = hsbactsosinhCutheditat;
    }

    public String getHsbactsosinhTinhhinhsosinhvaokhoa() {
        return hsbactsosinhTinhhinhsosinhvaokhoa;
    }

    public void setHsbactsosinhTinhhinhsosinhvaokhoa(String hsbactsosinhTinhhinhsosinhvaokhoa) {
        this.hsbactsosinhTinhhinhsosinhvaokhoa = hsbactsosinhTinhhinhsosinhvaokhoa;
    }

    public String getHsbactsosinhTinhtrangtoanthan() {
        return hsbactsosinhTinhtrangtoanthan;
    }

    public void setHsbactsosinhTinhtrangtoanthan(String hsbactsosinhTinhtrangtoanthan) {
        this.hsbactsosinhTinhtrangtoanthan = hsbactsosinhTinhtrangtoanthan;
    }

    public Boolean getHsbactsosinhMausacdaHonghao() {
        return hsbactsosinhMausacdaHonghao;
    }

    public void setHsbactsosinhMausacdaHonghao(Boolean hsbactsosinhMausacdaHonghao) {
        this.hsbactsosinhMausacdaHonghao = hsbactsosinhMausacdaHonghao;
    }

    public Boolean getHsbactsosinhMausacdaXanhtai() {
        return hsbactsosinhMausacdaXanhtai;
    }

    public void setHsbactsosinhMausacdaXanhtai(Boolean hsbactsosinhMausacdaXanhtai) {
        this.hsbactsosinhMausacdaXanhtai = hsbactsosinhMausacdaXanhtai;
    }

    public Boolean getHsbactsosinhMausacdaVang() {
        return hsbactsosinhMausacdaVang;
    }

    public void setHsbactsosinhMausacdaVang(Boolean hsbactsosinhMausacdaVang) {
        this.hsbactsosinhMausacdaVang = hsbactsosinhMausacdaVang;
    }

    public Boolean getHsbactsosinhMausacdaTim() {
        return hsbactsosinhMausacdaTim;
    }

    public void setHsbactsosinhMausacdaTim(Boolean hsbactsosinhMausacdaTim) {
        this.hsbactsosinhMausacdaTim = hsbactsosinhMausacdaTim;
    }

    public Boolean getHsbactsosinhMausacdaKhac() {
        return hsbactsosinhMausacdaKhac;
    }

    public void setHsbactsosinhMausacdaKhac(Boolean hsbactsosinhMausacdaKhac) {
        this.hsbactsosinhMausacdaKhac = hsbactsosinhMausacdaKhac;
    }

    public String getHsbactsosinhChieudai() {
        return hsbactsosinhChieudai;
    }

    public void setHsbactsosinhChieudai(String hsbactsosinhChieudai) {
        this.hsbactsosinhChieudai = hsbactsosinhChieudai;
    }

    public String getHsbactsosinhCannang() {
        return hsbactsosinhCannang;
    }

    public void setHsbactsosinhCannang(String hsbactsosinhCannang) {
        this.hsbactsosinhCannang = hsbactsosinhCannang;
    }

    public String getHsbactsosinhVongdau() {
        return hsbactsosinhVongdau;
    }

    public void setHsbactsosinhVongdau(String hsbactsosinhVongdau) {
        this.hsbactsosinhVongdau = hsbactsosinhVongdau;
    }

    public String getHsbactsosinhToanthanNhiptho() {
        return hsbactsosinhToanthanNhiptho;
    }

    public void setHsbactsosinhToanthanNhiptho(String hsbactsosinhToanthanNhiptho) {
        this.hsbactsosinhToanthanNhiptho = hsbactsosinhToanthanNhiptho;
    }

    public String getHsbactsosinhToanthanNhietdo() {
        return hsbactsosinhToanthanNhietdo;
    }

    public void setHsbactsosinhToanthanNhietdo(String hsbactsosinhToanthanNhietdo) {
        this.hsbactsosinhToanthanNhietdo = hsbactsosinhToanthanNhietdo;
    }

    public String getHsbactsosinhCoquankhacNhiptho() {
        return hsbactsosinhCoquankhacNhiptho;
    }

    public void setHsbactsosinhCoquankhacNhiptho(String hsbactsosinhCoquankhacNhiptho) {
        this.hsbactsosinhCoquankhacNhiptho = hsbactsosinhCoquankhacNhiptho;
    }

    public String getHsbactsosinhCoquankhacNghephoi() {
        return hsbactsosinhCoquankhacNghephoi;
    }

    public void setHsbactsosinhCoquankhacNghephoi(String hsbactsosinhCoquankhacNghephoi) {
        this.hsbactsosinhCoquankhacNghephoi = hsbactsosinhCoquankhacNghephoi;
    }

    public String getHsbactsosinhCoquankhacSilverman() {
        return hsbactsosinhCoquankhacSilverman;
    }

    public void setHsbactsosinhCoquankhacSilverman(String hsbactsosinhCoquankhacSilverman) {
        this.hsbactsosinhCoquankhacSilverman = hsbactsosinhCoquankhacSilverman;
    }

    public String getHsbactsosinhCoquankhacNhiptim() {
        return hsbactsosinhCoquankhacNhiptim;
    }

    public void setHsbactsosinhCoquankhacNhiptim(String hsbactsosinhCoquankhacNhiptim) {
        this.hsbactsosinhCoquankhacNhiptim = hsbactsosinhCoquankhacNhiptim;
    }

    public String getHsbactsosinhCoquankhacBung() {
        return hsbactsosinhCoquankhacBung;
    }

    public void setHsbactsosinhCoquankhacBung(String hsbactsosinhCoquankhacBung) {
        this.hsbactsosinhCoquankhacBung = hsbactsosinhCoquankhacBung;
    }

    public String getHsbactsosinhCoquankhacSinhducngoai() {
        return hsbactsosinhCoquankhacSinhducngoai;
    }

    public void setHsbactsosinhCoquankhacSinhducngoai(String hsbactsosinhCoquankhacSinhducngoai) {
        this.hsbactsosinhCoquankhacSinhducngoai = hsbactsosinhCoquankhacSinhducngoai;
    }

    public String getHsbactsosinhCoquankhacXuongkhop() {
        return hsbactsosinhCoquankhacXuongkhop;
    }

    public void setHsbactsosinhCoquankhacXuongkhop(String hsbactsosinhCoquankhacXuongkhop) {
        this.hsbactsosinhCoquankhacXuongkhop = hsbactsosinhCoquankhacXuongkhop;
    }

    public String getHsbactsosinhCoquankhacThankinhPhanxa() {
        return hsbactsosinhCoquankhacThankinhPhanxa;
    }

    public void setHsbactsosinhCoquankhacThankinhPhanxa(String hsbactsosinhCoquankhacThankinhPhanxa) {
        this.hsbactsosinhCoquankhacThankinhPhanxa = hsbactsosinhCoquankhacThankinhPhanxa;
    }

    public String getHsbactsosinhCoquankhacThankinhTruonglucco() {
        return hsbactsosinhCoquankhacThankinhTruonglucco;
    }

    public void setHsbactsosinhCoquankhacThankinhTruonglucco(String hsbactsosinhCoquankhacThankinhTruonglucco) {
        this.hsbactsosinhCoquankhacThankinhTruonglucco = hsbactsosinhCoquankhacThankinhTruonglucco;
    }

    public String getHsbactsosinhTtba() {
        return hsbactsosinhTtba;
    }

    public void setHsbactsosinhTtba(String hsbactsosinhTtba) {
        this.hsbactsosinhTtba = hsbactsosinhTtba;
    }

    public String getHsbactsosinhXetnghiemcanlamsan() {
        return hsbactsosinhXetnghiemcanlamsan;
    }

    public void setHsbactsosinhXetnghiemcanlamsan(String hsbactsosinhXetnghiemcanlamsan) {
        this.hsbactsosinhXetnghiemcanlamsan = hsbactsosinhXetnghiemcanlamsan;
    }

    public String getHsbactsosinhChidinhtheodoi() {
        return hsbactsosinhChidinhtheodoi;
    }

    public void setHsbactsosinhChidinhtheodoi(String hsbactsosinhChidinhtheodoi) {
        this.hsbactsosinhChidinhtheodoi = hsbactsosinhChidinhtheodoi;
    }

    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    public DtDmNhanVien getHsbactsosinhBslamba() {
        return hsbactsosinhBslamba;
    }

    public void setHsbactsosinhBslamba(DtDmNhanVien hsbactsosinhBslamba) {
        this.hsbactsosinhBslamba = hsbactsosinhBslamba;
    }

    public DtDmNhanVien getHsbactsosinhNguoigiaoba() {
        return hsbactsosinhNguoigiaoba;
    }

    public void setHsbactsosinhNguoigiaoba(DtDmNhanVien hsbactsosinhNguoigiaoba) {
        this.hsbactsosinhNguoigiaoba = hsbactsosinhNguoigiaoba;
    }

    public DtDmNhanVien getHsbactsosinhNguoinhanba() {
        return hsbactsosinhNguoinhanba;
    }

    public void setHsbactsosinhNguoinhanba(DtDmNhanVien hsbactsosinhNguoinhanba) {
        this.hsbactsosinhNguoinhanba = hsbactsosinhNguoinhanba;
    }

    public DtDmNhanVien getHsbactsosinhBsdieutri() {
        return hsbactsosinhBsdieutri;
    }

    public void setHsbactsosinhBsdieutri(DtDmNhanVien hsbactsosinhBsdieutri) {
        this.hsbactsosinhBsdieutri = hsbactsosinhBsdieutri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbactsosinhMa != null ? hsbactsosinhMa.hashCode() : 0);
        return hash;
    }

    public DtDmNhanVien getHsbactsosinhBslamba(boolean create) {
        if (create && hsbactsosinhBslamba == null) {
            hsbactsosinhBslamba = new DtDmNhanVien();
        }
        return hsbactsosinhBslamba;
    }

    public DtDmNhanVien getHsbactsosinhNguoigiaoba(boolean create) {
        if (create && hsbactsosinhNguoigiaoba == null) {
            hsbactsosinhNguoigiaoba = new DtDmNhanVien();
        }
        return hsbactsosinhNguoigiaoba;
    }

    public DtDmNhanVien getHsbactsosinhNguoinhanba(boolean create) {
        if (create && hsbactsosinhNguoinhanba == null) {
            hsbactsosinhNguoinhanba = new DtDmNhanVien();
        }
        return hsbactsosinhNguoinhanba;
    }

    public DtDmNhanVien getHsbactsosinhBsdieutri(boolean create) {
        if (create && hsbactsosinhBsdieutri == null) {
            hsbactsosinhBsdieutri = new DtDmNhanVien();
        }
        return hsbactsosinhBsdieutri;
    }

    public HsbaChuyenMon getHsbacmMa(boolean create) {
        if (create && getHsbacmMa() == null) {
            setHsbacmMa(new HsbaChuyenMon());
        }
        return getHsbacmMa();
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietSosinh)) {
            return false;
        }
        HsbaChiTietSosinh other = (HsbaChiTietSosinh) object;
        if ((this.hsbactsosinhMa == null && other.hsbactsosinhMa != null) || (this.hsbactsosinhMa != null && !this.hsbactsosinhMa.equals(other.hsbactsosinhMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.test.HsbaChiTietSosinh[hsbactsosinhMa=" + hsbactsosinhMa + "]";
    }

}
