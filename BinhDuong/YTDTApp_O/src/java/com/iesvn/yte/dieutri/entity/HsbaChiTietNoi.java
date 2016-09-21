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
 * @author thanh
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_CHI_TIET_NOI")
@NamedQueries({@NamedQuery(name = "HsbaChiTietNoi.findAll", query = "SELECT h FROM HsbaChiTietNoi h"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiMa", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiMa = :hsbactnoiMa"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiQtbenhly", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiQtbenhly = :hsbactnoiQtbenhly"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiTiensubenhbt", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiTiensubenhbt = :hsbactnoiTiensubenhbt"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiTiensubenhgd", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiTiensubenhgd = :hsbactnoiTiensubenhgd"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdDiung", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdDiung = :hsbactnoiDdDiung"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdMatuy", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdMatuy = :hsbactnoiDdMatuy"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdRuoubia", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdRuoubia = :hsbactnoiDdRuoubia"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdThuocla", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdThuocla = :hsbactnoiDdThuocla"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdThuoclao", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdThuoclao = :hsbactnoiDdThuoclao"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdKhac", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdKhac = :hsbactnoiDdKhac"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdDiungTg", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdDiungTg = :hsbactnoiDdDiungTg"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdMatuyTg", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdMatuyTg = :hsbactnoiDdMatuyTg"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdRuoubiaTg", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdRuoubiaTg = :hsbactnoiDdRuoubiaTg"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdThuoclaTg", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdThuoclaTg = :hsbactnoiDdThuoclaTg"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdThuoclaoTg", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdThuoclaoTg = :hsbactnoiDdThuoclaoTg"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiDdKhacTg", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiDdKhacTg = :hsbactnoiDdKhacTg"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiTuanhoan", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiTuanhoan = :hsbactnoiTuanhoan"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiHohap", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiHohap = :hsbactnoiHohap"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiTieuhoa", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiTieuhoa = :hsbactnoiTieuhoa"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiThantietnieusinhhoc", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiThantietnieusinhhoc = :hsbactnoiThantietnieusinhhoc"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiThankinh", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiThankinh = :hsbactnoiThankinh"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiCoxuongkhop", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiCoxuongkhop = :hsbactnoiCoxuongkhop"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiTmh", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiTmh = :hsbactnoiTmh"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiRhm", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiRhm = :hsbactnoiRhm"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiMat", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiMat = :hsbactnoiMat"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiNtDdBlk", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiNtDdBlk = :hsbactnoiNtDdBlk"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiTtba", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiTtba = :hsbactnoiTtba"), @NamedQuery(name = "HsbaChiTietNoi.findByHsbactnoiPb", query = "SELECT h FROM HsbaChiTietNoi h WHERE h.hsbactnoiPb = :hsbactnoiPb")})
public class HsbaChiTietNoi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_NOI")
    @SequenceGenerator(name = "HSBA_CHI_TIET_NOI", sequenceName = "HSBA_CHI_TIET_NOI_HSBACTNOI_MA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTNOI_MA")
    private Integer hsbactnoiMa;
    @Column(name = "HSBACTNOI_QTBENHLY")
    private String hsbactnoiQtbenhly;
    @Column(name = "HSBACTNOI_TIENSUBENHBT")
    private String hsbactnoiTiensubenhbt;
    @Column(name = "HSBACTNOI_TIENSUBENHGD")
    private String hsbactnoiTiensubenhgd;
    @Column(name = "HSBACTNOI_DD_DIUNG")
    private Boolean hsbactnoiDdDiung;
    @Column(name = "HSBACTNOI_DD_MATUY")
    private Boolean hsbactnoiDdMatuy;
    @Column(name = "HSBACTNOI_DD_RUOUBIA")
    private Boolean hsbactnoiDdRuoubia;
    @Column(name = "HSBACTNOI_DD_THUOCLA")
    private Boolean hsbactnoiDdThuocla;
    @Column(name = "HSBACTNOI_DD_THUOCLAO")
    private Boolean hsbactnoiDdThuoclao;
    @Column(name = "HSBACTNOI_DD_KHAC")
    private Boolean hsbactnoiDdKhac;
    @Column(name = "HSBACTNOI_DD_DIUNG_TG")
    private String hsbactnoiDdDiungTg;
    @Column(name = "HSBACTNOI_DD_MATUY_TG")
    private String hsbactnoiDdMatuyTg;
    @Column(name = "HSBACTNOI_DD_RUOUBIA_TG")
    private String hsbactnoiDdRuoubiaTg;
    @Column(name = "HSBACTNOI_DD_THUOCLA_TG")
    private String hsbactnoiDdThuoclaTg;
    @Column(name = "HSBACTNOI_DD_THUOCLAO_TG")
    private String hsbactnoiDdThuoclaoTg;
    @Column(name = "HSBACTNOI_DD_KHAC_TG")
    private String hsbactnoiDdKhacTg;
    @Column(name = "HSBACTNOI_TUANHOAN")
    private String hsbactnoiTuanhoan;
    @Column(name = "HSBACTNOI_HOHAP")
    private String hsbactnoiHohap;
    @Column(name = "HSBACTNOI_TIEUHOA")
    private String hsbactnoiTieuhoa;
    @Column(name = "HSBACTNOI_THANTIETNIEUSINHHOC")
    private String hsbactnoiThantietnieusinhhoc;
    @Column(name = "HSBACTNOI_THANKINH")
    private String hsbactnoiThankinh;
    @Column(name = "HSBACTNOI_COXUONGKHOP")
    private String hsbactnoiCoxuongkhop;
    @Column(name = "HSBACTNOI_TMH")
    private String hsbactnoiTmh;
    @Column(name = "HSBACTNOI_RHM")
    private String hsbactnoiRhm;
    @Column(name = "HSBACTNOI_MAT")
    private String hsbactnoiMat;
    @Column(name = "HSBACTNOI_NT_DD_BLK")
    private String hsbactnoiNtDdBlk;
    @Column(name = "HSBACTNOI_TTBA")
    private String hsbactnoiTtba;
    @Column(name = "HSBACTNOI_LYDOVAOV")
    private String hsbactnoiLydovaov;
    @Column(name = "HSBACTNOI_NGAYBENHTHU")
    private Integer hsbactnoiNgaybenhthu;
    @Column(name = "HSBACTNOI_TOANTHAN")
    private String hsbactnoiToanthan;
    @Column(name = "HSBACTNOI_QTBL_DBLS")
    private String hsbactnoiQtblDbls;
    @Column(name = "HSBACTNOI_TTKQXNCLS")
    private String hsbactnoiTtkqxncls;
    @Column(name = "HSBACTNOI_PPDIEUTRI")
    private String hsbactnoiPpdieutri;
    @Column(name = "HSBACTNOI_TTNGUOIBENHRAV")
    private String hsbactnoiTtnguoibenhrav;
    @Column(name = "HSBACTNOI_HUONGDT_CDTT")
    private String hsbactnoiHuongdtCdtt;
    @Column(name = "HSBACTNOI_SOTOXQUANG")
    private Integer hsbactnoiSotoxquang;
    @Column(name = "HSBACTNOI_SOTOCTSCANNER")
    private Integer hsbactnoiSotoctscanner;
    @Column(name = "HSBACTNOI_SOTOSIEUAM")
    private Integer hsbactnoiSotosieuam;
    @Column(name = "HSBACTNOI_SOTOXN")
    private Integer hsbactnoiSotoxn;
    @Column(name = "HSBACTNOI_SOTOKHAC")
    private Integer hsbactnoiSotokhac;
    @Column(name = "HSBACTNOI_SOTOLOAIKHAC")
    private String hsbactnoiSotoloaikhac;
    @Column(name = "HSBACTNOI_PB")
    private Integer hsbactnoiPb;

    @Column(name = "HSBACTNOI_TONGSOTO")
    private Integer hsbactnoiTongsoto;



    @JoinColumn(name = "HSBACTNOI_BSLAMBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactnoiBSlamba;

     @JoinColumn(name = "HSBACTNOI_NGUOIGIAOBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactnoiNguoigiaoba;

     @JoinColumn(name = "HSBACTNOI_NGUOINHANBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactnoiNguoinhanba;

       @JoinColumn(name = "HSBACTNOI_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactnoiBSdieutri;

    @JoinColumn(name = "HSBACTNOI_TIENLUONG", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc hsbactnoiTienluong;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;

    public HsbaChiTietNoi() {
    }

    public HsbaChiTietNoi(Integer hsbactnoiMa) {
        this.hsbactnoiMa = hsbactnoiMa;
    }

    public Integer getHsbactnoiMa() {
        return hsbactnoiMa;
    }

    public void setHsbactnoiMa(Integer hsbactnoiMa) {
        this.hsbactnoiMa = hsbactnoiMa;
    }

    public String getHsbactnoiQtbenhly() {
        return hsbactnoiQtbenhly;
    }

    public void setHsbactnoiQtbenhly(String hsbactnoiQtbenhly) {
        this.hsbactnoiQtbenhly = hsbactnoiQtbenhly;
    }

    public String getHsbactnoiTiensubenhbt() {
        return hsbactnoiTiensubenhbt;
    }

    public void setHsbactnoiTiensubenhbt(String hsbactnoiTiensubenhbt) {
        this.hsbactnoiTiensubenhbt = hsbactnoiTiensubenhbt;
    }

    public String getHsbactnoiTiensubenhgd() {
        return hsbactnoiTiensubenhgd;
    }

    public void setHsbactnoiTiensubenhgd(String hsbactnoiTiensubenhgd) {
        this.hsbactnoiTiensubenhgd = hsbactnoiTiensubenhgd;
    }

    public Boolean getHsbactnoiDdDiung() {
        return hsbactnoiDdDiung;
    }

    public void setHsbactnoiDdDiung(Boolean hsbactnoiDdDiung) {
        this.hsbactnoiDdDiung = hsbactnoiDdDiung;
    }

    public Boolean getHsbactnoiDdMatuy() {
        return hsbactnoiDdMatuy;
    }

    public void setHsbactnoiDdMatuy(Boolean hsbactnoiDdMatuy) {
        this.hsbactnoiDdMatuy = hsbactnoiDdMatuy;
    }

    public Boolean getHsbactnoiDdRuoubia() {
        return hsbactnoiDdRuoubia;
    }

    public void setHsbactnoiDdRuoubia(Boolean hsbactnoiDdRuoubia) {
        this.hsbactnoiDdRuoubia = hsbactnoiDdRuoubia;
    }

    public Boolean getHsbactnoiDdThuocla() {
        return hsbactnoiDdThuocla;
    }

    public void setHsbactnoiDdThuocla(Boolean hsbactnoiDdThuocla) {
        this.hsbactnoiDdThuocla = hsbactnoiDdThuocla;
    }

    public Boolean getHsbactnoiDdThuoclao() {
        return hsbactnoiDdThuoclao;
    }

    public void setHsbactnoiDdThuoclao(Boolean hsbactnoiDdThuoclao) {
        this.hsbactnoiDdThuoclao = hsbactnoiDdThuoclao;
    }

    public Boolean getHsbactnoiDdKhac() {
        return hsbactnoiDdKhac;
    }

    public void setHsbactnoiDdKhac(Boolean hsbactnoiDdKhac) {
        this.hsbactnoiDdKhac = hsbactnoiDdKhac;
    }

    public String getHsbactnoiDdDiungTg() {
        return hsbactnoiDdDiungTg;
    }

    public void setHsbactnoiDdDiungTg(String hsbactnoiDdDiungTg) {
        this.hsbactnoiDdDiungTg = hsbactnoiDdDiungTg;
    }

    public String getHsbactnoiDdMatuyTg() {
        return hsbactnoiDdMatuyTg;
    }

    public void setHsbactnoiDdMatuyTg(String hsbactnoiDdMatuyTg) {
        this.hsbactnoiDdMatuyTg = hsbactnoiDdMatuyTg;
    }

    public String getHsbactnoiDdRuoubiaTg() {
        return hsbactnoiDdRuoubiaTg;
    }

    public void setHsbactnoiDdRuoubiaTg(String hsbactnoiDdRuoubiaTg) {
        this.hsbactnoiDdRuoubiaTg = hsbactnoiDdRuoubiaTg;
    }

    public String getHsbactnoiDdThuoclaTg() {
        return hsbactnoiDdThuoclaTg;
    }

    public void setHsbactnoiDdThuoclaTg(String hsbactnoiDdThuoclaTg) {
        this.hsbactnoiDdThuoclaTg = hsbactnoiDdThuoclaTg;
    }

    public String getHsbactnoiDdThuoclaoTg() {
        return hsbactnoiDdThuoclaoTg;
    }

    public void setHsbactnoiDdThuoclaoTg(String hsbactnoiDdThuoclaoTg) {
        this.hsbactnoiDdThuoclaoTg = hsbactnoiDdThuoclaoTg;
    }

    public String getHsbactnoiDdKhacTg() {
        return hsbactnoiDdKhacTg;
    }

    public void setHsbactnoiDdKhacTg(String hsbactnoiDdKhacTg) {
        this.hsbactnoiDdKhacTg = hsbactnoiDdKhacTg;
    }

    public String getHsbactnoiTuanhoan() {
        return hsbactnoiTuanhoan;
    }

    public void setHsbactnoiTuanhoan(String hsbactnoiTuanhoan) {
        this.hsbactnoiTuanhoan = hsbactnoiTuanhoan;
    }

    public String getHsbactnoiHohap() {
        return hsbactnoiHohap;
    }

    public void setHsbactnoiHohap(String hsbactnoiHohap) {
        this.hsbactnoiHohap = hsbactnoiHohap;
    }

    public String getHsbactnoiTieuhoa() {
        return hsbactnoiTieuhoa;
    }

    public void setHsbactnoiTieuhoa(String hsbactnoiTieuhoa) {
        this.hsbactnoiTieuhoa = hsbactnoiTieuhoa;
    }

    public String getHsbactnoiThantietnieusinhhoc() {
        return hsbactnoiThantietnieusinhhoc;
    }

    public void setHsbactnoiThantietnieusinhhoc(String hsbactnoiThantietnieusinhhoc) {
        this.hsbactnoiThantietnieusinhhoc = hsbactnoiThantietnieusinhhoc;
    }

    public String getHsbactnoiThankinh() {
        return hsbactnoiThankinh;
    }

    public void setHsbactnoiThankinh(String hsbactnoiThankinh) {
        this.hsbactnoiThankinh = hsbactnoiThankinh;
    }

    public String getHsbactnoiCoxuongkhop() {
        return hsbactnoiCoxuongkhop;
    }

    public void setHsbactnoiCoxuongkhop(String hsbactnoiCoxuongkhop) {
        this.hsbactnoiCoxuongkhop = hsbactnoiCoxuongkhop;
    }

    public String getHsbactnoiTmh() {
        return hsbactnoiTmh;
    }

    public void setHsbactnoiTmh(String hsbactnoiTmh) {
        this.hsbactnoiTmh = hsbactnoiTmh;
    }

    public String getHsbactnoiRhm() {
        return hsbactnoiRhm;
    }

    public void setHsbactnoiRhm(String hsbactnoiRhm) {
        this.hsbactnoiRhm = hsbactnoiRhm;
    }

    public String getHsbactnoiMat() {
        return hsbactnoiMat;
    }

    public void setHsbactnoiMat(String hsbactnoiMat) {
        this.hsbactnoiMat = hsbactnoiMat;
    }

    public String getHsbactnoiNtDdBlk() {
        return hsbactnoiNtDdBlk;
    }

    public void setHsbactnoiNtDdBlk(String hsbactnoiNtDdBlk) {
        this.hsbactnoiNtDdBlk = hsbactnoiNtDdBlk;
    }

    public String getHsbactnoiTtba() {
        return hsbactnoiTtba;
    }

    public void setHsbactnoiTtba(String hsbactnoiTtba) {
        this.hsbactnoiTtba = hsbactnoiTtba;
    }

    public Integer getHsbactnoiPb() {
        return hsbactnoiPb;
    }

    public void setHsbactnoiPb(Integer hsbactnoiPb) {
        this.hsbactnoiPb = hsbactnoiPb;
    }

    public DmThuoc getHsbactnoiTienluong() {
        return hsbactnoiTienluong;
    }

    public DmThuoc getHsbactnoiTienluong(boolean create) {
        if (create && getHsbactnoiTienluong() == null) {
            setHsbactnoiTienluong(new DmThuoc());
        }
        return getHsbactnoiTienluong();

    }

    public void setHsbactnoiTienluong(DmThuoc hsbactnoiTienluong) {
        this.hsbactnoiTienluong = hsbactnoiTienluong;
    }

    public HsbaChuyenMon getHsbacmMa(boolean create) {
        if (create && getHsbacmMa() == null) {
            setHsbacmMa(new HsbaChuyenMon());
        }
        return getHsbacmMa();

    }

    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHsbactnoiMa() != null ? getHsbactnoiMa().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietNoi)) {
            return false;
        }
        HsbaChiTietNoi other = (HsbaChiTietNoi) object;
        if ((this.getHsbactnoiMa() == null && other.getHsbactnoiMa() != null) || (this.getHsbactnoiMa() != null && !this.hsbactnoiMa.equals(other.hsbactnoiMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ddxdddd.HsbaChiTietNoi[hsbactnoiMa=" + getHsbactnoiMa() + "]";
    }

    /**
     * @return the hsbactnoiLydovaov
     */
    public String getHsbactnoiLydovaov() {
        return hsbactnoiLydovaov;
    }

    /**
     * @param hsbactnoiLydovaov the hsbactnoiLydovaov to set
     */
    public void setHsbactnoiLydovaov(String hsbactnoiLydovaov) {
        this.hsbactnoiLydovaov = hsbactnoiLydovaov;
    }

    /**
     * @return the hsbactnoiNgaybenhthu
     */
    public Integer getHsbactnoiNgaybenhthu() {
        return hsbactnoiNgaybenhthu;
    }

    /**
     * @param hsbactnoiNgaybenhthu the hsbactnoiNgaybenhthu to set
     */
    public void setHsbactnoiNgaybenhthu(Integer hsbactnoiNgaybenhthu) {
        this.hsbactnoiNgaybenhthu = hsbactnoiNgaybenhthu;
    }

    /**
     * @return the hsbactnoiToanthan
     */
    public String getHsbactnoiToanthan() {
        return hsbactnoiToanthan;
    }

    /**
     * @param hsbactnoiToanthan the hsbactnoiToanthan to set
     */
    public void setHsbactnoiToanthan(String hsbactnoiToanthan) {
        this.hsbactnoiToanthan = hsbactnoiToanthan;
    }

    /**
     * @return the hsbactnoiQtblDbls
     */
    public String getHsbactnoiQtblDbls() {
        return hsbactnoiQtblDbls;
    }

    /**
     * @param hsbactnoiQtblDbls the hsbactnoiQtblDbls to set
     */
    public void setHsbactnoiQtblDbls(String hsbactnoiQtblDbls) {
        this.hsbactnoiQtblDbls = hsbactnoiQtblDbls;
    }

    /**
     * @return the hsbactnoiTtkqxncls
     */
    public String getHsbactnoiTtkqxncls() {
        return hsbactnoiTtkqxncls;
    }

    /**
     * @param hsbactnoiTtkqxncls the hsbactnoiTtkqxncls to set
     */
    public void setHsbactnoiTtkqxncls(String hsbactnoiTtkqxncls) {
        this.hsbactnoiTtkqxncls = hsbactnoiTtkqxncls;
    }

    /**
     * @return the hsbactnoiPpdieutri
     */
    public String getHsbactnoiPpdieutri() {
        return hsbactnoiPpdieutri;
    }

    /**
     * @param hsbactnoiPpdieutri the hsbactnoiPpdieutri to set
     */
    public void setHsbactnoiPpdieutri(String hsbactnoiPpdieutri) {
        this.hsbactnoiPpdieutri = hsbactnoiPpdieutri;
    }

    /**
     * @return the hsbactnoiTtnguoibenhrav
     */
    public String getHsbactnoiTtnguoibenhrav() {
        return hsbactnoiTtnguoibenhrav;
    }

    /**
     * @param hsbactnoiTtnguoibenhrav the hsbactnoiTtnguoibenhrav to set
     */
    public void setHsbactnoiTtnguoibenhrav(String hsbactnoiTtnguoibenhrav) {
        this.hsbactnoiTtnguoibenhrav = hsbactnoiTtnguoibenhrav;
    }

    /**
     * @return the hsbactnoiHuongdtCdtt
     */
    public String getHsbactnoiHuongdtCdtt() {
        return hsbactnoiHuongdtCdtt;
    }

    /**
     * @param hsbactnoiHuongdtCdtt the hsbactnoiHuongdtCdtt to set
     */
    public void setHsbactnoiHuongdtCdtt(String hsbactnoiHuongdtCdtt) {
        this.hsbactnoiHuongdtCdtt = hsbactnoiHuongdtCdtt;
    }

    /**
     * @return the hsbactnoiSotoxquang
     */
    public Integer getHsbactnoiSotoxquang() {
        return hsbactnoiSotoxquang;
    }

    /**
     * @param hsbactnoiSotoxquang the hsbactnoiSotoxquang to set
     */
    public void setHsbactnoiSotoxquang(Integer hsbactnoiSotoxquang) {
        this.hsbactnoiSotoxquang = hsbactnoiSotoxquang;
    }

    /**
     * @return the hsbactnoiSotoctscanner
     */
    public Integer getHsbactnoiSotoctscanner() {
        return hsbactnoiSotoctscanner;
    }

    /**
     * @param hsbactnoiSotoctscanner the hsbactnoiSotoctscanner to set
     */
    public void setHsbactnoiSotoctscanner(Integer hsbactnoiSotoctscanner) {
        this.hsbactnoiSotoctscanner = hsbactnoiSotoctscanner;
    }

    /**
     * @return the hsbactnoiSotosieuam
     */
    public Integer getHsbactnoiSotosieuam() {
        return hsbactnoiSotosieuam;
    }

    /**
     * @param hsbactnoiSotosieuam the hsbactnoiSotosieuam to set
     */
    public void setHsbactnoiSotosieuam(Integer hsbactnoiSotosieuam) {
        this.hsbactnoiSotosieuam = hsbactnoiSotosieuam;
    }

    /**
     * @return the hsbactnoiSotoxn
     */
    public Integer getHsbactnoiSotoxn() {
        return hsbactnoiSotoxn;
    }

    /**
     * @param hsbactnoiSotoxn the hsbactnoiSotoxn to set
     */
    public void setHsbactnoiSotoxn(Integer hsbactnoiSotoxn) {
        this.hsbactnoiSotoxn = hsbactnoiSotoxn;
    }

    /**
     * @return the hsbactnoiSotokhac
     */
    public Integer getHsbactnoiSotokhac() {
        return hsbactnoiSotokhac;
    }

    /**
     * @param hsbactnoiSotokhac the hsbactnoiSotokhac to set
     */
    public void setHsbactnoiSotokhac(Integer hsbactnoiSotokhac) {
        this.hsbactnoiSotokhac = hsbactnoiSotokhac;
    }

    /**
     * @return the hsbactnoiSotoloaikhac
     */
    public String getHsbactnoiSotoloaikhac() {
        return hsbactnoiSotoloaikhac;
    }

    /**
     * @param hsbactnoiSotoloaikhac the hsbactnoiSotoloaikhac to set
     */
    public void setHsbactnoiSotoloaikhac(String hsbactnoiSotoloaikhac) {
        this.hsbactnoiSotoloaikhac = hsbactnoiSotoloaikhac;
    }

    /**
     * @return the hsbactnoiTongsoto
     */
    public Integer getHsbactnoiTongsoto() {
        return hsbactnoiTongsoto;
    }

    /**
     * @param hsbactnoiTongsoto the hsbactnoiTongsoto to set
     */
    public void setHsbactnoiTongsoto(Integer hsbactnoiTongsoto) {
        this.hsbactnoiTongsoto = hsbactnoiTongsoto;
    }

    /**
     * @return the hsbactnoiBSlamba
     */
    public DtDmNhanVien getHsbactnoiBSlamba() {
        return hsbactnoiBSlamba;
    }

     public DtDmNhanVien getHsbactnoiBSlamba(boolean create) {
           if (create && hsbactnoiBSlamba == null) {
            hsbactnoiBSlamba = new DtDmNhanVien();
        }
        return hsbactnoiBSlamba;

    }

    /**
     * @param hsbactnoiBSlamba the hsbactnoiBSlamba to set
     */
    public void setHsbactnoiBSlamba(DtDmNhanVien hsbactnoiBSlamba) {
        this.hsbactnoiBSlamba = hsbactnoiBSlamba;
    }

    /**
     * @return the hsbactnoiNguoigiaoba
     */
    public DtDmNhanVien getHsbactnoiNguoigiaoba() {
        return hsbactnoiNguoigiaoba;
    }
 public DtDmNhanVien getHsbactnoiNguoigiaoba(boolean create) {
           if (create && hsbactnoiNguoigiaoba == null) {
            hsbactnoiNguoigiaoba = new DtDmNhanVien();
        }
        return hsbactnoiNguoigiaoba;

    }
    /**
     * @param hsbactnoiNguoigiaoba the hsbactnoiNguoigiaoba to set
     */
    public void setHsbactnoiNguoigiaoba(DtDmNhanVien hsbactnoiNguoigiaoba) {
        this.hsbactnoiNguoigiaoba = hsbactnoiNguoigiaoba;
    }

    /**
     * @return the hsbactnoiNguoinhanba
     */
    public DtDmNhanVien getHsbactnoiNguoinhanba() {
        return hsbactnoiNguoinhanba;
    }

     public DtDmNhanVien getHsbactnoiNguoinhanba(boolean create) {
           if (create && hsbactnoiNguoinhanba == null) {
            hsbactnoiNguoinhanba = new DtDmNhanVien();
        }
        return hsbactnoiNguoinhanba;

    }

    /**
     * @param hsbactnoiNguoinhanba the hsbactnoiNguoinhanba to set
     */
    public void setHsbactnoiNguoinhanba(DtDmNhanVien hsbactnoiNguoinhanba) {
        this.hsbactnoiNguoinhanba = hsbactnoiNguoinhanba;
    }

    /**
     * @return the hsbactnoiBSdieutri
     */
    public DtDmNhanVien getHsbactnoiBSdieutri() {
        return hsbactnoiBSdieutri;
    }

     public DtDmNhanVien getHsbactnoiBSdieutri(boolean create) {
           if (create && hsbactnoiBSdieutri == null) {
            hsbactnoiBSdieutri = new DtDmNhanVien();
        }
        return hsbactnoiBSdieutri;

    }


    /**
     * @param hsbactnoiBSdieutri the hsbactnoiBSdieutri to set
     */
    public void setHsbactnoiBSdieutri(DtDmNhanVien hsbactnoiBSdieutri) {
        this.hsbactnoiBSdieutri = hsbactnoiBSdieutri;
    }
}
