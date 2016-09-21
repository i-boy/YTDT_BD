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
@Table(name = "HSBA_CHI_TIET_NGOAITRU_YHCT")
@NamedQueries({
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findAll", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctMa", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctMa = :hsbactngoaitruYhctMa"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctLydovaov", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctLydovaov = :hsbactngoaitruYhctLydovaov"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctQtbenhly", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctQtbenhly = :hsbactngoaitruYhctQtbenhly"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctTiensubenhbt", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctTiensubenhbt = :hsbactngoaitruYhctTiensubenhbt"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctTiensubenhgd", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctTiensubenhgd = :hsbactngoaitruYhctTiensubenhgd"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctBophanbibenh", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctBophanbibenh = :hsbactngoaitruYhctBophanbibenh"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctXetnghiemcanthiet", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctXetnghiemcanthiet = :hsbactngoaitruYhctXetnghiemcanthiet"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctVongchan", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctVongchan = :hsbactngoaitruYhctVongchan"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctVanchan", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctVanchan = :hsbactngoaitruYhctVanchan"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctVanchan2", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctVanchan2 = :hsbactngoaitruYhctVanchan2"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctThietchan", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctThietchan = :hsbactngoaitruYhctThietchan"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctChandoanBenhdanh", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctChandoanBenhdanh = :hsbactngoaitruYhctChandoanBenhdanh"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctChandoanTangphu", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctChandoanTangphu = :hsbactngoaitruYhctChandoanTangphu"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctChandoanBatcuong", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctChandoanBatcuong = :hsbactngoaitruYhctChandoanBatcuong"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctChandoanNguyennhan", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctChandoanNguyennhan = :hsbactngoaitruYhctChandoanNguyennhan"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctDieutriPhepchua", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctDieutriPhepchua = :hsbactngoaitruYhctDieutriPhepchua"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctDieutriPhuongthuoc", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctDieutriPhuongthuoc = :hsbactngoaitruYhctDieutriPhuongthuoc"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctDieutriPhuonghuyet", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctDieutriPhuonghuyet = :hsbactngoaitruYhctDieutriPhuonghuyet"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctDieutriXoabop", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctDieutriXoabop = :hsbactngoaitruYhctDieutriXoabop"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctDieutriChedoantainha", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctDieutriChedoantainha = :hsbactngoaitruYhctDieutriChedoantainha"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctDieutriChedoholytainha", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctDieutriChedoholytainha = :hsbactngoaitruYhctDieutriChedoholytainha"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctTienluong", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctTienluong = :hsbactngoaitruYhctTienluong"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctKetquaclschinh", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctKetquaclschinh = :hsbactngoaitruYhctKetquaclschinh"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctKetquagiaiphaubenh", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctKetquagiaiphaubenh = :hsbactngoaitruYhctKetquagiaiphaubenh"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctPhaptriYhhd", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctPhaptriYhhd = :hsbactngoaitruYhctPhaptriYhhd"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctPhaptriYhct", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctPhaptriYhct = :hsbactngoaitruYhctPhaptriYhct"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctChandoanravienYhhd", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctChandoanravienYhhd = :hsbactngoaitruYhctChandoanravienYhhd"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctChandoanravienYhct", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctChandoanravienYhct = :hsbactngoaitruYhctChandoanravienYhct"),
    @NamedQuery(name = "HsbaChiTietNgoaitruYhct.findByHsbactngoaitruYhctHuongdtCdtt", query = "SELECT h FROM HsbaChiTietNgoaitruYhct h WHERE h.hsbactngoaitruYhctHuongdtCdtt = :hsbactngoaitruYhctHuongdtCdtt")})
public class HsbaChiTietNgoaitruYhct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_NGOAITRU_YHCT")
    @SequenceGenerator(name = "HSBA_CHI_TIET_NGOAITRU_YHCT", sequenceName = "HSBA_CHI_TIET_NGOAITRU_YHCT_HS", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTNGOAITRU_YHCT_MA")
    private Integer hsbactngoaitruYhctMa;
    @Column(name = "HSBACTNGOAITRU_YHCT_LYDOVAOV")
    private String hsbactngoaitruYhctLydovaov;
    @Column(name = "HSBACTNGOAITRU_YHCT_QTBENHLY")
    private String hsbactngoaitruYhctQtbenhly;
    @Column(name = "HSBACTNGOAITRU_YHCT_TSUBENHBT")
    private String hsbactngoaitruYhctTiensubenhbt;
    @Column(name = "HSBACTNGOAITRU_YHCT_TSUBENHGD")
    private String hsbactngoaitruYhctTiensubenhgd;
    @Column(name = "HSBACTNGOAITRU_YHCT_BPBIBENH")
    private String hsbactngoaitruYhctBophanbibenh;
    @Column(name = "HSBACTNGOAITRU_YHCT_XNCANTHIET")
    private String hsbactngoaitruYhctXetnghiemcanthiet;
    @Column(name = "HSBACTNGOAITRU_YHCT_VONGCHAN")
    private String hsbactngoaitruYhctVongchan;
    @Column(name = "HSBACTNGOAITRU_YHCT_VANCHAN")
    private String hsbactngoaitruYhctVanchan;
    @Column(name = "HSBACTNGOAITRU_YHCT_VANCHAN2")
    private String hsbactngoaitruYhctVanchan2;
    @Column(name = "HSBACTNGOAITRU_YHCT_THIETCHAN")
    private String hsbactngoaitruYhctThietchan;
    @Column(name = "HSBACTNGOAITRU_YHCT_CDBENHDANH")
    private String hsbactngoaitruYhctChandoanBenhdanh;
    @Column(name = "HSBACTNGOAITRU_YHCT_CDTANGPHU")
    private String hsbactngoaitruYhctChandoanTangphu;
    @Column(name = "HSBACTNGOAITRU_YHCT_CDBATCUONG")
    private String hsbactngoaitruYhctChandoanBatcuong;
    @Column(name = "HSBACTNGOAITRU_YHCT_CDNGNHAN")
    private String hsbactngoaitruYhctChandoanNguyennhan;
    @Column(name = "HSBACTNGOAITRU_YHCT_DTPHEPCHUA")
    private String hsbactngoaitruYhctDieutriPhepchua;
    @Column(name = "HSBACTNGOAITRU_YHCT_DTPHTHUOC")
    private String hsbactngoaitruYhctDieutriPhuongthuoc;
    @Column(name = "HSBACTNGOAITRU_YHCT_DTPHHUYET")
    private String hsbactngoaitruYhctDieutriPhuonghuyet;
    @Column(name = "HSBACTNGOAITRU_YHCT_DTXOABOP")
    private String hsbactngoaitruYhctDieutriXoabop;
    @Column(name = "HSBACTNGOAITRU_YHCT_DTCDATNHA")
    private String hsbactngoaitruYhctDieutriChedoantainha;
    @Column(name = "HSBACTNGOAITRU_YHCT_DTCDHLTNHA")
    private String hsbactngoaitruYhctDieutriChedoholytainha;
    @Column(name = "HSBACTNGOAITRU_YHCT_TIENLUONG")
    private String hsbactngoaitruYhctTienluong;
    @Column(name = "HSBACTNGOAITRU_YHCT_KQCLSCHINH")
    private String hsbactngoaitruYhctKetquaclschinh;
    @Column(name = "HSBACTNGOAITRU_YHCT_KQGPBENH")
    private String hsbactngoaitruYhctKetquagiaiphaubenh;
    @Column(name = "HSBACTNGOAITRU_YHCT_PT_YHHD")
    private String hsbactngoaitruYhctPhaptriYhhd;
    @Column(name = "HSBACTNGOAITRU_YHCT_PT_YHCT")
    private String hsbactngoaitruYhctPhaptriYhct;
    @Column(name = "HSBACTNGOAITRU_YHCT_CDRV_YHHD")
    private String hsbactngoaitruYhctChandoanravienYhhd;
    @Column(name = "HSBACTNGOAITRU_YHCT_CDRV_YHCT")
    private String hsbactngoaitruYhctChandoanravienYhct;
    @Column(name = "HSBACTNGOAITRU_YHCT_HDT_CDTT")
    private String hsbactngoaitruYhctHuongdtCdtt;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;
    @JoinColumn(name = "HSBACTNGOAITRU_YHCT_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactngoaitruYhctBsdieutri;

    public HsbaChiTietNgoaitruYhct() {
    }

    public HsbaChiTietNgoaitruYhct(Integer hsbactngoaitruYhctMa) {
        this.hsbactngoaitruYhctMa = hsbactngoaitruYhctMa;
    }

    public Integer getHsbactngoaitruYhctMa() {
        return hsbactngoaitruYhctMa;
    }

    public void setHsbactngoaitruYhctMa(Integer hsbactngoaitruYhctMa) {
        this.hsbactngoaitruYhctMa = hsbactngoaitruYhctMa;
    }

    public String getHsbactngoaitruYhctLydovaov() {
        return hsbactngoaitruYhctLydovaov;
    }

    public void setHsbactngoaitruYhctLydovaov(String hsbactngoaitruYhctLydovaov) {
        this.hsbactngoaitruYhctLydovaov = hsbactngoaitruYhctLydovaov;
    }

    public String getHsbactngoaitruYhctQtbenhly() {
        return hsbactngoaitruYhctQtbenhly;
    }

    public void setHsbactngoaitruYhctQtbenhly(String hsbactngoaitruYhctQtbenhly) {
        this.hsbactngoaitruYhctQtbenhly = hsbactngoaitruYhctQtbenhly;
    }

    public String getHsbactngoaitruYhctTiensubenhbt() {
        return hsbactngoaitruYhctTiensubenhbt;
    }

    public void setHsbactngoaitruYhctTiensubenhbt(String hsbactngoaitruYhctTiensubenhbt) {
        this.hsbactngoaitruYhctTiensubenhbt = hsbactngoaitruYhctTiensubenhbt;
    }

    public String getHsbactngoaitruYhctTiensubenhgd() {
        return hsbactngoaitruYhctTiensubenhgd;
    }

    public void setHsbactngoaitruYhctTiensubenhgd(String hsbactngoaitruYhctTiensubenhgd) {
        this.hsbactngoaitruYhctTiensubenhgd = hsbactngoaitruYhctTiensubenhgd;
    }

    public String getHsbactngoaitruYhctBophanbibenh() {
        return hsbactngoaitruYhctBophanbibenh;
    }

    public void setHsbactngoaitruYhctBophanbibenh(String hsbactngoaitruYhctBophanbibenh) {
        this.hsbactngoaitruYhctBophanbibenh = hsbactngoaitruYhctBophanbibenh;
    }

    public String getHsbactngoaitruYhctXetnghiemcanthiet() {
        return hsbactngoaitruYhctXetnghiemcanthiet;
    }

    public void setHsbactngoaitruYhctXetnghiemcanthiet(String hsbactngoaitruYhctXetnghiemcanthiet) {
        this.hsbactngoaitruYhctXetnghiemcanthiet = hsbactngoaitruYhctXetnghiemcanthiet;
    }

    public String getHsbactngoaitruYhctVongchan() {
        return hsbactngoaitruYhctVongchan;
    }

    public void setHsbactngoaitruYhctVongchan(String hsbactngoaitruYhctVongchan) {
        this.hsbactngoaitruYhctVongchan = hsbactngoaitruYhctVongchan;
    }

    public String getHsbactngoaitruYhctVanchan() {
        return hsbactngoaitruYhctVanchan;
    }

    public void setHsbactngoaitruYhctVanchan(String hsbactngoaitruYhctVanchan) {
        this.hsbactngoaitruYhctVanchan = hsbactngoaitruYhctVanchan;
    }

    public String getHsbactngoaitruYhctVanchan2() {
        return hsbactngoaitruYhctVanchan2;
    }

    public void setHsbactngoaitruYhctVanchan2(String hsbactngoaitruYhctVanchan2) {
        this.hsbactngoaitruYhctVanchan2 = hsbactngoaitruYhctVanchan2;
    }

    public String getHsbactngoaitruYhctThietchan() {
        return hsbactngoaitruYhctThietchan;
    }

    public void setHsbactngoaitruYhctThietchan(String hsbactngoaitruYhctThietchan) {
        this.hsbactngoaitruYhctThietchan = hsbactngoaitruYhctThietchan;
    }

    public String getHsbactngoaitruYhctChandoanBenhdanh() {
        return hsbactngoaitruYhctChandoanBenhdanh;
    }

    public void setHsbactngoaitruYhctChandoanBenhdanh(String hsbactngoaitruYhctChandoanBenhdanh) {
        this.hsbactngoaitruYhctChandoanBenhdanh = hsbactngoaitruYhctChandoanBenhdanh;
    }

    public String getHsbactngoaitruYhctChandoanTangphu() {
        return hsbactngoaitruYhctChandoanTangphu;
    }

    public void setHsbactngoaitruYhctChandoanTangphu(String hsbactngoaitruYhctChandoanTangphu) {
        this.hsbactngoaitruYhctChandoanTangphu = hsbactngoaitruYhctChandoanTangphu;
    }

    public String getHsbactngoaitruYhctChandoanBatcuong() {
        return hsbactngoaitruYhctChandoanBatcuong;
    }

    public void setHsbactngoaitruYhctChandoanBatcuong(String hsbactngoaitruYhctChandoanBatcuong) {
        this.hsbactngoaitruYhctChandoanBatcuong = hsbactngoaitruYhctChandoanBatcuong;
    }

    public String getHsbactngoaitruYhctChandoanNguyennhan() {
        return hsbactngoaitruYhctChandoanNguyennhan;
    }

    public void setHsbactngoaitruYhctChandoanNguyennhan(String hsbactngoaitruYhctChandoanNguyennhan) {
        this.hsbactngoaitruYhctChandoanNguyennhan = hsbactngoaitruYhctChandoanNguyennhan;
    }

    public String getHsbactngoaitruYhctDieutriPhepchua() {
        return hsbactngoaitruYhctDieutriPhepchua;
    }

    public void setHsbactngoaitruYhctDieutriPhepchua(String hsbactngoaitruYhctDieutriPhepchua) {
        this.hsbactngoaitruYhctDieutriPhepchua = hsbactngoaitruYhctDieutriPhepchua;
    }

    public String getHsbactngoaitruYhctDieutriPhuongthuoc() {
        return hsbactngoaitruYhctDieutriPhuongthuoc;
    }

    public void setHsbactngoaitruYhctDieutriPhuongthuoc(String hsbactngoaitruYhctDieutriPhuongthuoc) {
        this.hsbactngoaitruYhctDieutriPhuongthuoc = hsbactngoaitruYhctDieutriPhuongthuoc;
    }

    public String getHsbactngoaitruYhctDieutriPhuonghuyet() {
        return hsbactngoaitruYhctDieutriPhuonghuyet;
    }

    public void setHsbactngoaitruYhctDieutriPhuonghuyet(String hsbactngoaitruYhctDieutriPhuonghuyet) {
        this.hsbactngoaitruYhctDieutriPhuonghuyet = hsbactngoaitruYhctDieutriPhuonghuyet;
    }

    public String getHsbactngoaitruYhctDieutriXoabop() {
        return hsbactngoaitruYhctDieutriXoabop;
    }

    public void setHsbactngoaitruYhctDieutriXoabop(String hsbactngoaitruYhctDieutriXoabop) {
        this.hsbactngoaitruYhctDieutriXoabop = hsbactngoaitruYhctDieutriXoabop;
    }

    public String getHsbactngoaitruYhctDieutriChedoantainha() {
        return hsbactngoaitruYhctDieutriChedoantainha;
    }

    public void setHsbactngoaitruYhctDieutriChedoantainha(String hsbactngoaitruYhctDieutriChedoantainha) {
        this.hsbactngoaitruYhctDieutriChedoantainha = hsbactngoaitruYhctDieutriChedoantainha;
    }

    public String getHsbactngoaitruYhctDieutriChedoholytainha() {
        return hsbactngoaitruYhctDieutriChedoholytainha;
    }

    public void setHsbactngoaitruYhctDieutriChedoholytainha(String hsbactngoaitruYhctDieutriChedoholytainha) {
        this.hsbactngoaitruYhctDieutriChedoholytainha = hsbactngoaitruYhctDieutriChedoholytainha;
    }

    public String getHsbactngoaitruYhctTienluong() {
        return hsbactngoaitruYhctTienluong;
    }

    public void setHsbactngoaitruYhctTienluong(String hsbactngoaitruYhctTienluong) {
        this.hsbactngoaitruYhctTienluong = hsbactngoaitruYhctTienluong;
    }

    public String getHsbactngoaitruYhctKetquaclschinh() {
        return hsbactngoaitruYhctKetquaclschinh;
    }

    public void setHsbactngoaitruYhctKetquaclschinh(String hsbactngoaitruYhctKetquaclschinh) {
        this.hsbactngoaitruYhctKetquaclschinh = hsbactngoaitruYhctKetquaclschinh;
    }

    public String getHsbactngoaitruYhctKetquagiaiphaubenh() {
        return hsbactngoaitruYhctKetquagiaiphaubenh;
    }

    public void setHsbactngoaitruYhctKetquagiaiphaubenh(String hsbactngoaitruYhctKetquagiaiphaubenh) {
        this.hsbactngoaitruYhctKetquagiaiphaubenh = hsbactngoaitruYhctKetquagiaiphaubenh;
    }

    public String getHsbactngoaitruYhctPhaptriYhhd() {
        return hsbactngoaitruYhctPhaptriYhhd;
    }

    public void setHsbactngoaitruYhctPhaptriYhhd(String hsbactngoaitruYhctPhaptriYhhd) {
        this.hsbactngoaitruYhctPhaptriYhhd = hsbactngoaitruYhctPhaptriYhhd;
    }

    public String getHsbactngoaitruYhctPhaptriYhct() {
        return hsbactngoaitruYhctPhaptriYhct;
    }

    public void setHsbactngoaitruYhctPhaptriYhct(String hsbactngoaitruYhctPhaptriYhct) {
        this.hsbactngoaitruYhctPhaptriYhct = hsbactngoaitruYhctPhaptriYhct;
    }

    public String getHsbactngoaitruYhctChandoanravienYhhd() {
        return hsbactngoaitruYhctChandoanravienYhhd;
    }

    public void setHsbactngoaitruYhctChandoanravienYhhd(String hsbactngoaitruYhctChandoanravienYhhd) {
        this.hsbactngoaitruYhctChandoanravienYhhd = hsbactngoaitruYhctChandoanravienYhhd;
    }

    public String getHsbactngoaitruYhctChandoanravienYhct() {
        return hsbactngoaitruYhctChandoanravienYhct;
    }

    public void setHsbactngoaitruYhctChandoanravienYhct(String hsbactngoaitruYhctChandoanravienYhct) {
        this.hsbactngoaitruYhctChandoanravienYhct = hsbactngoaitruYhctChandoanravienYhct;
    }

    public String getHsbactngoaitruYhctHuongdtCdtt() {
        return hsbactngoaitruYhctHuongdtCdtt;
    }

    public void setHsbactngoaitruYhctHuongdtCdtt(String hsbactngoaitruYhctHuongdtCdtt) {
        this.hsbactngoaitruYhctHuongdtCdtt = hsbactngoaitruYhctHuongdtCdtt;
    }

    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    public DtDmNhanVien getHsbactngoaitruYhctBsdieutri() {
        return hsbactngoaitruYhctBsdieutri;
    }

    public void setHsbactngoaitruYhctBsdieutri(DtDmNhanVien hsbactngoaitruYhctBsdieutri) {
        this.hsbactngoaitruYhctBsdieutri = hsbactngoaitruYhctBsdieutri;
    }

       public DtDmNhanVien getHsbactngoaitruYhctBsdieutri(boolean create) {
        if (create && hsbactngoaitruYhctBsdieutri == null) {
            hsbactngoaitruYhctBsdieutri = new DtDmNhanVien();
        }
        return hsbactngoaitruYhctBsdieutri;
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
        hash += (hsbactngoaitruYhctMa != null ? hsbactngoaitruYhctMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietNgoaitruYhct)) {
            return false;
        }
        HsbaChiTietNgoaitruYhct other = (HsbaChiTietNgoaitruYhct) object;
        if ((this.hsbactngoaitruYhctMa == null && other.hsbactngoaitruYhctMa != null) || (this.hsbactngoaitruYhctMa != null && !this.hsbactngoaitruYhctMa.equals(other.hsbactngoaitruYhctMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.test.HsbaChiTietNgoaitruYhct[hsbactngoaitruYhctMa=" + hsbactngoaitruYhctMa + "]";
    }

}
