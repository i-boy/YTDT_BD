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
@Table(name = "HSBA_CHI_TIET_NGOAI")
@NamedQueries({@NamedQuery(name = "HsbaChiTietNgoai.findAll", query = "SELECT h FROM HsbaChiTietNgoai h"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiMa", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiMa = :hsbactngoaiMa"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiQtbenhly", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiQtbenhly = :hsbactngoaiQtbenhly"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiTiensubenhbt", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiTiensubenhbt = :hsbactngoaiTiensubenhbt"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiTiensubenhgd", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiTiensubenhgd = :hsbactngoaiTiensubenhgd"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdDiung", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdDiung = :hsbactngoaiDdDiung"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdMatuy", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdMatuy = :hsbactngoaiDdMatuy"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdRuoubia", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdRuoubia = :hsbactngoaiDdRuoubia"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdThuocla", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdThuocla = :hsbactngoaiDdThuocla"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdThuoclao", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdThuoclao = :hsbactngoaiDdThuoclao"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdKhac", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdKhac = :hsbactngoaiDdKhac"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdDiungTg", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdDiungTg = :hsbactngoaiDdDiungTg"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdMatuyTg", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdMatuyTg = :hsbactngoaiDdMatuyTg"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdRuoubiaTg", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdRuoubiaTg = :hsbactngoaiDdRuoubiaTg"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdThuoclaTg", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdThuoclaTg = :hsbactngoaiDdThuoclaTg"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdThuoclaoTg", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdThuoclaoTg = :hsbactngoaiDdThuoclaoTg"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiDdKhacTg", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiDdKhacTg = :hsbactngoaiDdKhacTg"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiTuanhoan", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiTuanhoan = :hsbactngoaiTuanhoan"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiHohap", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiHohap = :hsbactngoaiHohap"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiTieuhoa", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiTieuhoa = :hsbactngoaiTieuhoa"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiThantietnieusinhhoc", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiThantietnieusinhhoc = :hsbactngoaiThantietnieusinhhoc"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiThankinh", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiThankinh = :hsbactngoaiThankinh"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiCoxuongkhop", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiCoxuongkhop = :hsbactngoaiCoxuongkhop"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiTmh", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiTmh = :hsbactngoaiTmh"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiRhm", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiRhm = :hsbactngoaiRhm"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiMat", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiMat = :hsbactngoaiMat"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiNtDdBlk", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiNtDdBlk = :hsbactngoaiNtDdBlk"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiTtba", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiTtba = :hsbactngoaiTtba"), @NamedQuery(name = "HsbaChiTietNgoai.findByHsbactngoaiPb", query = "SELECT h FROM HsbaChiTietNgoai h WHERE h.hsbactngoaiPb = :hsbactngoaiPb")})
public class HsbaChiTietNgoai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_NGOAI")
    @SequenceGenerator(name = "HSBA_CHI_TIET_NGOAI", sequenceName = "HSBA_CHI_TIET_NGOAI_HSBACTNGOA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTNGOAI_MA")
    private Integer hsbactngoaiMa;
    @Column(name = "HSBACTNGOAI_QTBENHLY")
    private String hsbactngoaiQtbenhly;
    @Column(name = "HSBACTNGOAI_TIENSUBENHBT")
    private String hsbactngoaiTiensubenhbt;
    @Column(name = "HSBACTNGOAI_TIENSUBENHGD")
    private String hsbactngoaiTiensubenhgd;
    @Column(name = "HSBACTNGOAI_DD_DIUNG")
    private Boolean hsbactngoaiDdDiung;
    @Column(name = "HSBACTNGOAI_DD_MATUY")
    private Boolean hsbactngoaiDdMatuy;
    @Column(name = "HSBACTNGOAI_DD_RUOUBIA")
    private Boolean hsbactngoaiDdRuoubia;
    @Column(name = "HSBACTNGOAI_DD_THUOCLA")
    private Boolean hsbactngoaiDdThuocla;
    @Column(name = "HSBACTNGOAI_DD_THUOCLAO")
    private Boolean hsbactngoaiDdThuoclao;
    @Column(name = "HSBACTNGOAI_DD_KHAC")
    private Boolean hsbactngoaiDdKhac;
    @Column(name = "HSBACTNGOAI_DD_DIUNG_TG")
    private String hsbactngoaiDdDiungTg;
    @Column(name = "HSBACTNGOAI_DD_MATUY_TG")
    private String hsbactngoaiDdMatuyTg;
    @Column(name = "HSBACTNGOAI_BENHNGOAIKHOA")
    private String hsbactngoaiBenhngoaikhoa;
    @Column(name = "HSBACTNGOAI_DD_RUOUBIA_TG")
    private String hsbactngoaiDdRuoubiaTg;
    @Column(name = "HSBACTNGOAI_DD_THUOCLA_TG")
    private String hsbactngoaiDdThuoclaTg;
    @Column(name = "HSBACTNGOAI_DD_THUOCLAO_TG")
    private String hsbactngoaiDdThuoclaoTg;
    @Column(name = "HSBACTNGOAI_DD_KHAC_TG")
    private String hsbactngoaiDdKhacTg;
    @Column(name = "HSBACTNGOAI_TUANHOAN")
    private String hsbactngoaiTuanhoan;
    @Column(name = "HSBACTNGOAI_HOHAP")
    private String hsbactngoaiHohap;
    @Column(name = "HSBACTNGOAI_TIEUHOA")
    private String hsbactngoaiTieuhoa;
    @Column(name = "HSBACTNGOAI_THANTIETNIEUSH")
    private String hsbactngoaiThantietnieusinhhoc;
    @Column(name = "HSBACTNGOAI_THANKINH")
    private String hsbactngoaiThankinh;
    @Column(name = "HSBACTNGOAI_COXUONGKHOP")
    private String hsbactngoaiCoxuongkhop;
    @Column(name = "HSBACTNGOAI_TMH")
    private String hsbactngoaiTmh;
    @Column(name = "HSBACTNGOAI_RHM")
    private String hsbactngoaiRhm;
    @Column(name = "HSBACTNGOAI_MAT")
    private String hsbactngoaiMat;
    @Column(name = "HSBACTNGOAI_NT_DD_BLK")
    private String hsbactngoaiNtDdBlk;
    @Column(name = "HSBACTNGOAI_TTBA")
    private String hsbactngoaiTtba;
    @Column(name = "HSBACTNGOAI_LYDOVAOV")
    private String hsbactngoaiLydovaov;
    @Column(name = "HSBACTNGOAI_NGAYBENHTHU")
    private Integer hsbactngoaiNgaybenhthu;
    @Column(name = "HSBACTNGOAI_TOANTHAN")
    private String hsbactngoaiToanthan;
    @Column(name = "HSBACTNGOAI_QTBL_DBLS")
    private String hsbactngoaiQtblDbls;
    @Column(name = "HSBACTNGOAI_TTKQXNCLS")
    private String hsbactngoaiTtkqxncls;
    @Column(name = "HSBACTNGOAI_PPDIEUTRI")
    private String hsbactngoaiPpdieutri;
    @Column(name = "HSBACTNGOAI_TTNGUOIBENHRAV")
    private String hsbactngoaiTtnguoibenhrav;
    @Column(name = "HSBACTNGOAI_HUONGDT_CDTT")
    private String hsbactngoaiHuongdtCdtt;
    @Column(name = "HSBACTNGOAI_SOTOXQUANG")
    private Integer hsbactngoaiSotoxquang;
    @Column(name = "HSBACTNGOAI_SOTOCTSCANNER")
    private Integer hsbactngoaiSotoctscanner;
    @Column(name = "HSBACTNGOAI_SOTOSIEUAM")
    private Integer hsbactngoaiSotosieuam;
    @Column(name = "HSBACTNGOAI_SOTOXN")
    private Integer hsbactngoaiSotoxn;
    @Column(name = "HSBACTNGOAI_SOTOKHAC")
    private Integer hsbactngoaiSotokhac;
    @Column(name = "HSBACTNGOAI_SOTOLOAIKHAC")
    private String hsbactngoaiSotoloaikhac;
    @Column(name = "HSBACTNGOAI_PB")
    private Integer hsbactngoaiPb;

    @Column(name = "HSBACTNGOAI_TONGSOTO")
    private Integer hsbactngoaiTongsoto;



    @JoinColumn(name = "HSBACTNGOAI_BSLAMBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactngoaiBSlamba;

     @JoinColumn(name = "HSBACTNGOAI_NGUOIGIAOBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactngoaiNguoigiaoba;

     @JoinColumn(name = "HSBACTNGOAI_NGUOINHANBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactngoaiNguoinhanba;

       @JoinColumn(name = "HSBACTNGOAI_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactngoaiBSdieutri;

    @JoinColumn(name = "HSBACTNGOAI_TIENLUONG", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc hsbactngoaiTienluong;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;

    public String getHsbactngoaiBenhngoaikhoa() {
        return hsbactngoaiBenhngoaikhoa;
    }

    public void setHsbactngoaiBenhngoaikhoa(String hsbactngoaiBenhngoaikhoa) {
        this.hsbactngoaiBenhngoaikhoa = hsbactngoaiBenhngoaikhoa;
    }


    
    public HsbaChiTietNgoai() {
    }

    public HsbaChiTietNgoai(Integer hsbactngoaiMa) {
        this.hsbactngoaiMa = hsbactngoaiMa;
    }

    public Integer getHsbactngoaiMa() {
        return hsbactngoaiMa;
    }

    public void setHsbactngoaiMa(Integer hsbactngoaiMa) {
        this.hsbactngoaiMa = hsbactngoaiMa;
    }

    public String getHsbactngoaiQtbenhly() {
        return hsbactngoaiQtbenhly;
    }

    public void setHsbactngoaiQtbenhly(String hsbactngoaiQtbenhly) {
        this.hsbactngoaiQtbenhly = hsbactngoaiQtbenhly;
    }

    public String getHsbactngoaiTiensubenhbt() {
        return hsbactngoaiTiensubenhbt;
    }

    public void setHsbactngoaiTiensubenhbt(String hsbactngoaiTiensubenhbt) {
        this.hsbactngoaiTiensubenhbt = hsbactngoaiTiensubenhbt;
    }

    public String getHsbactngoaiTiensubenhgd() {
        return hsbactngoaiTiensubenhgd;
    }

    public void setHsbactngoaiTiensubenhgd(String hsbactngoaiTiensubenhgd) {
        this.hsbactngoaiTiensubenhgd = hsbactngoaiTiensubenhgd;
    }

    public Boolean getHsbactngoaiDdDiung() {
        return hsbactngoaiDdDiung;
    }

    public void setHsbactngoaiDdDiung(Boolean hsbactngoaiDdDiung) {
        this.hsbactngoaiDdDiung = hsbactngoaiDdDiung;
    }

    public Boolean getHsbactngoaiDdMatuy() {
        return hsbactngoaiDdMatuy;
    }

    public void setHsbactngoaiDdMatuy(Boolean hsbactngoaiDdMatuy) {
        this.hsbactngoaiDdMatuy = hsbactngoaiDdMatuy;
    }

    public Boolean getHsbactngoaiDdRuoubia() {
        return hsbactngoaiDdRuoubia;
    }

    public void setHsbactngoaiDdRuoubia(Boolean hsbactngoaiDdRuoubia) {
        this.hsbactngoaiDdRuoubia = hsbactngoaiDdRuoubia;
    }

    public Boolean getHsbactngoaiDdThuocla() {
        return hsbactngoaiDdThuocla;
    }

    public void setHsbactngoaiDdThuocla(Boolean hsbactngoaiDdThuocla) {
        this.hsbactngoaiDdThuocla = hsbactngoaiDdThuocla;
    }

    public Boolean getHsbactngoaiDdThuoclao() {
        return hsbactngoaiDdThuoclao;
    }

    public void setHsbactngoaiDdThuoclao(Boolean hsbactngoaiDdThuoclao) {
        this.hsbactngoaiDdThuoclao = hsbactngoaiDdThuoclao;
    }

    public Boolean getHsbactngoaiDdKhac() {
        return hsbactngoaiDdKhac;
    }

    public void setHsbactngoaiDdKhac(Boolean hsbactngoaiDdKhac) {
        this.hsbactngoaiDdKhac = hsbactngoaiDdKhac;
    }

    public String getHsbactngoaiDdDiungTg() {
        return hsbactngoaiDdDiungTg;
    }

    public void setHsbactngoaiDdDiungTg(String hsbactngoaiDdDiungTg) {
        this.hsbactngoaiDdDiungTg = hsbactngoaiDdDiungTg;
    }

    public String getHsbactngoaiDdMatuyTg() {
        return hsbactngoaiDdMatuyTg;
    }

    public void setHsbactngoaiDdMatuyTg(String hsbactngoaiDdMatuyTg) {
        this.hsbactngoaiDdMatuyTg = hsbactngoaiDdMatuyTg;
    }

    public String getHsbactngoaiDdRuoubiaTg() {
        return hsbactngoaiDdRuoubiaTg;
    }

    public void setHsbactngoaiDdRuoubiaTg(String hsbactngoaiDdRuoubiaTg) {
        this.hsbactngoaiDdRuoubiaTg = hsbactngoaiDdRuoubiaTg;
    }

    public String getHsbactngoaiDdThuoclaTg() {
        return hsbactngoaiDdThuoclaTg;
    }

    public void setHsbactngoaiDdThuoclaTg(String hsbactngoaiDdThuoclaTg) {
        this.hsbactngoaiDdThuoclaTg = hsbactngoaiDdThuoclaTg;
    }

    public String getHsbactngoaiDdThuoclaoTg() {
        return hsbactngoaiDdThuoclaoTg;
    }

    public void setHsbactngoaiDdThuoclaoTg(String hsbactngoaiDdThuoclaoTg) {
        this.hsbactngoaiDdThuoclaoTg = hsbactngoaiDdThuoclaoTg;
    }

    public String getHsbactngoaiDdKhacTg() {
        return hsbactngoaiDdKhacTg;
    }

    public void setHsbactngoaiDdKhacTg(String hsbactngoaiDdKhacTg) {
        this.hsbactngoaiDdKhacTg = hsbactngoaiDdKhacTg;
    }

    public String getHsbactngoaiTuanhoan() {
        return hsbactngoaiTuanhoan;
    }

    public void setHsbactngoaiTuanhoan(String hsbactngoaiTuanhoan) {
        this.hsbactngoaiTuanhoan = hsbactngoaiTuanhoan;
    }

    public String getHsbactngoaiHohap() {
        return hsbactngoaiHohap;
    }

    public void setHsbactngoaiHohap(String hsbactngoaiHohap) {
        this.hsbactngoaiHohap = hsbactngoaiHohap;
    }

    public String getHsbactngoaiTieuhoa() {
        return hsbactngoaiTieuhoa;
    }

    public void setHsbactngoaiTieuhoa(String hsbactngoaiTieuhoa) {
        this.hsbactngoaiTieuhoa = hsbactngoaiTieuhoa;
    }

    public String getHsbactngoaiThantietnieusinhhoc() {
        return hsbactngoaiThantietnieusinhhoc;
    }

    public void setHsbactngoaiThantietnieusinhhoc(String hsbactngoaiThantietnieusinhhoc) {
        this.hsbactngoaiThantietnieusinhhoc = hsbactngoaiThantietnieusinhhoc;
    }

    public String getHsbactngoaiThankinh() {
        return hsbactngoaiThankinh;
    }

    public void setHsbactngoaiThankinh(String hsbactngoaiThankinh) {
        this.hsbactngoaiThankinh = hsbactngoaiThankinh;
    }

    public String getHsbactngoaiCoxuongkhop() {
        return hsbactngoaiCoxuongkhop;
    }

    public void setHsbactngoaiCoxuongkhop(String hsbactngoaiCoxuongkhop) {
        this.hsbactngoaiCoxuongkhop = hsbactngoaiCoxuongkhop;
    }

    public String getHsbactngoaiTmh() {
        return hsbactngoaiTmh;
    }

    public void setHsbactngoaiTmh(String hsbactngoaiTmh) {
        this.hsbactngoaiTmh = hsbactngoaiTmh;
    }

    public String getHsbactngoaiRhm() {
        return hsbactngoaiRhm;
    }

    public void setHsbactngoaiRhm(String hsbactngoaiRhm) {
        this.hsbactngoaiRhm = hsbactngoaiRhm;
    }

    public String getHsbactngoaiMat() {
        return hsbactngoaiMat;
    }

    public void setHsbactngoaiMat(String hsbactngoaiMat) {
        this.hsbactngoaiMat = hsbactngoaiMat;
    }

    public String getHsbactngoaiNtDdBlk() {
        return hsbactngoaiNtDdBlk;
    }

    public void setHsbactngoaiNtDdBlk(String hsbactngoaiNtDdBlk) {
        this.hsbactngoaiNtDdBlk = hsbactngoaiNtDdBlk;
    }

    public String getHsbactngoaiTtba() {
        return hsbactngoaiTtba;
    }

    public void setHsbactngoaiTtba(String hsbactngoaiTtba) {
        this.hsbactngoaiTtba = hsbactngoaiTtba;
    }

    public Integer getHsbactngoaiPb() {
        return hsbactngoaiPb;
    }

    public void setHsbactngoaiPb(Integer hsbactngoaiPb) {
        this.hsbactngoaiPb = hsbactngoaiPb;
    }

    public DmThuoc getHsbactngoaiTienluong() {
        return hsbactngoaiTienluong;
    }

    public DmThuoc getHsbactngoaiTienluong(boolean create) {
        if (create && getHsbactngoaiTienluong() == null) {
            setHsbactngoaiTienluong(new DmThuoc());
        }
        return getHsbactngoaiTienluong();

    }

    public void setHsbactngoaiTienluong(DmThuoc hsbactngoaiTienluong) {
        this.hsbactngoaiTienluong = hsbactngoaiTienluong;
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
        hash += (getHsbactngoaiMa() != null ? getHsbactngoaiMa().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietNgoai)) {
            return false;
        }
        HsbaChiTietNgoai other = (HsbaChiTietNgoai) object;
        if ((this.getHsbactngoaiMa() == null && other.getHsbactngoaiMa() != null) || (this.getHsbactngoaiMa() != null && !this.hsbactngoaiMa.equals(other.hsbactngoaiMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ddxdddd.HsbaChiTietNgoai[hsbactngoaiMa=" + getHsbactngoaiMa() + "]";
    }

    /**
     * @return the hsbactngoaiLydovaov
     */
    public String getHsbactngoaiLydovaov() {
        return hsbactngoaiLydovaov;
    }

    /**
     * @param hsbactngoaiLydovaov the hsbactngoaiLydovaov to set
     */
    public void setHsbactngoaiLydovaov(String hsbactngoaiLydovaov) {
        this.hsbactngoaiLydovaov = hsbactngoaiLydovaov;
    }

    /**
     * @return the hsbactngoaiNgaybenhthu
     */
    public Integer getHsbactngoaiNgaybenhthu() {
        return hsbactngoaiNgaybenhthu;
    }

    /**
     * @param hsbactngoaiNgaybenhthu the hsbactngoaiNgaybenhthu to set
     */
    public void setHsbactngoaiNgaybenhthu(Integer hsbactngoaiNgaybenhthu) {
        this.hsbactngoaiNgaybenhthu = hsbactngoaiNgaybenhthu;
    }

    /**
     * @return the hsbactngoaiToanthan
     */
    public String getHsbactngoaiToanthan() {
        return hsbactngoaiToanthan;
    }

    /**
     * @param hsbactngoaiToanthan the hsbactngoaiToanthan to set
     */
    public void setHsbactngoaiToanthan(String hsbactngoaiToanthan) {
        this.hsbactngoaiToanthan = hsbactngoaiToanthan;
    }

    /**
     * @return the hsbactngoaiQtblDbls
     */
    public String getHsbactngoaiQtblDbls() {
        return hsbactngoaiQtblDbls;
    }

    /**
     * @param hsbactngoaiQtblDbls the hsbactngoaiQtblDbls to set
     */
    public void setHsbactngoaiQtblDbls(String hsbactngoaiQtblDbls) {
        this.hsbactngoaiQtblDbls = hsbactngoaiQtblDbls;
    }

    /**
     * @return the hsbactngoaiTtkqxncls
     */
    public String getHsbactngoaiTtkqxncls() {
        return hsbactngoaiTtkqxncls;
    }

    /**
     * @param hsbactngoaiTtkqxncls the hsbactngoaiTtkqxncls to set
     */
    public void setHsbactngoaiTtkqxncls(String hsbactngoaiTtkqxncls) {
        this.hsbactngoaiTtkqxncls = hsbactngoaiTtkqxncls;
    }

    /**
     * @return the hsbactngoaiPpdieutri
     */
    public String getHsbactngoaiPpdieutri() {
        return hsbactngoaiPpdieutri;
    }

    /**
     * @param hsbactngoaiPpdieutri the hsbactngoaiPpdieutri to set
     */
    public void setHsbactngoaiPpdieutri(String hsbactngoaiPpdieutri) {
        this.hsbactngoaiPpdieutri = hsbactngoaiPpdieutri;
    }

    /**
     * @return the hsbactngoaiTtnguoibenhrav
     */
    public String getHsbactngoaiTtnguoibenhrav() {
        return hsbactngoaiTtnguoibenhrav;
    }

    /**
     * @param hsbactngoaiTtnguoibenhrav the hsbactngoaiTtnguoibenhrav to set
     */
    public void setHsbactngoaiTtnguoibenhrav(String hsbactngoaiTtnguoibenhrav) {
        this.hsbactngoaiTtnguoibenhrav = hsbactngoaiTtnguoibenhrav;
    }

    /**
     * @return the hsbactngoaiHuongdtCdtt
     */
    public String getHsbactngoaiHuongdtCdtt() {
        return hsbactngoaiHuongdtCdtt;
    }

    /**
     * @param hsbactngoaiHuongdtCdtt the hsbactngoaiHuongdtCdtt to set
     */
    public void setHsbactngoaiHuongdtCdtt(String hsbactngoaiHuongdtCdtt) {
        this.hsbactngoaiHuongdtCdtt = hsbactngoaiHuongdtCdtt;
    }

    /**
     * @return the hsbactngoaiSotoxquang
     */
    public Integer getHsbactngoaiSotoxquang() {
        return hsbactngoaiSotoxquang;
    }

    /**
     * @param hsbactngoaiSotoxquang the hsbactngoaiSotoxquang to set
     */
    public void setHsbactngoaiSotoxquang(Integer hsbactngoaiSotoxquang) {
        this.hsbactngoaiSotoxquang = hsbactngoaiSotoxquang;
    }

    /**
     * @return the hsbactngoaiSotoctscanner
     */
    public Integer getHsbactngoaiSotoctscanner() {
        return hsbactngoaiSotoctscanner;
    }

    /**
     * @param hsbactngoaiSotoctscanner the hsbactngoaiSotoctscanner to set
     */
    public void setHsbactngoaiSotoctscanner(Integer hsbactngoaiSotoctscanner) {
        this.hsbactngoaiSotoctscanner = hsbactngoaiSotoctscanner;
    }

    /**
     * @return the hsbactngoaiSotosieuam
     */
    public Integer getHsbactngoaiSotosieuam() {
        return hsbactngoaiSotosieuam;
    }

    /**
     * @param hsbactngoaiSotosieuam the hsbactngoaiSotosieuam to set
     */
    public void setHsbactngoaiSotosieuam(Integer hsbactngoaiSotosieuam) {
        this.hsbactngoaiSotosieuam = hsbactngoaiSotosieuam;
    }

    /**
     * @return the hsbactngoaiSotoxn
     */
    public Integer getHsbactngoaiSotoxn() {
        return hsbactngoaiSotoxn;
    }

    /**
     * @param hsbactngoaiSotoxn the hsbactngoaiSotoxn to set
     */
    public void setHsbactngoaiSotoxn(Integer hsbactngoaiSotoxn) {
        this.hsbactngoaiSotoxn = hsbactngoaiSotoxn;
    }

    /**
     * @return the hsbactngoaiSotokhac
     */
    public Integer getHsbactngoaiSotokhac() {
        return hsbactngoaiSotokhac;
    }

    /**
     * @param hsbactngoaiSotokhac the hsbactngoaiSotokhac to set
     */
    public void setHsbactngoaiSotokhac(Integer hsbactngoaiSotokhac) {
        this.hsbactngoaiSotokhac = hsbactngoaiSotokhac;
    }

    /**
     * @return the hsbactngoaiSotoloaikhac
     */
    public String getHsbactngoaiSotoloaikhac() {
        return hsbactngoaiSotoloaikhac;
    }

    /**
     * @param hsbactngoaiSotoloaikhac the hsbactngoaiSotoloaikhac to set
     */
    public void setHsbactngoaiSotoloaikhac(String hsbactngoaiSotoloaikhac) {
        this.hsbactngoaiSotoloaikhac = hsbactngoaiSotoloaikhac;
    }

    /**
     * @return the hsbactngoaiTongsoto
     */
    public Integer getHsbactngoaiTongsoto() {
        return hsbactngoaiTongsoto;
    }

    /**
     * @param hsbactngoaiTongsoto the hsbactngoaiTongsoto to set
     */
    public void setHsbactngoaiTongsoto(Integer hsbactngoaiTongsoto) {
        this.hsbactngoaiTongsoto = hsbactngoaiTongsoto;
    }

    /**
     * @return the hsbactngoaiBSlamba
     */
    public DtDmNhanVien getHsbactngoaiBSlamba() {
        return hsbactngoaiBSlamba;
    }

     public DtDmNhanVien getHsbactngoaiBSlamba(boolean create) {
           if (create && hsbactngoaiBSlamba == null) {
            hsbactngoaiBSlamba = new DtDmNhanVien();
        }
        return hsbactngoaiBSlamba;

    }

    /**
     * @param hsbactngoaiBSlamba the hsbactngoaiBSlamba to set
     */
    public void setHsbactngoaiBSlamba(DtDmNhanVien hsbactngoaiBSlamba) {
        this.hsbactngoaiBSlamba = hsbactngoaiBSlamba;
    }

    /**
     * @return the hsbactngoaiNguoigiaoba
     */
    public DtDmNhanVien getHsbactngoaiNguoigiaoba() {
        return hsbactngoaiNguoigiaoba;
    }
 public DtDmNhanVien getHsbactngoaiNguoigiaoba(boolean create) {
           if (create && hsbactngoaiNguoigiaoba == null) {
            hsbactngoaiNguoigiaoba = new DtDmNhanVien();
        }
        return hsbactngoaiNguoigiaoba;

    }
    /**
     * @param hsbactngoaiNguoigiaoba the hsbactngoaiNguoigiaoba to set
     */
    public void setHsbactngoaiNguoigiaoba(DtDmNhanVien hsbactngoaiNguoigiaoba) {
        this.hsbactngoaiNguoigiaoba = hsbactngoaiNguoigiaoba;
    }

    /**
     * @return the hsbactngoaiNguoinhanba
     */
    public DtDmNhanVien getHsbactngoaiNguoinhanba() {
        return hsbactngoaiNguoinhanba;
    }

     public DtDmNhanVien getHsbactngoaiNguoinhanba(boolean create) {
           if (create && hsbactngoaiNguoinhanba == null) {
            hsbactngoaiNguoinhanba = new DtDmNhanVien();
        }
        return hsbactngoaiNguoinhanba;

    }

    /**
     * @param hsbactngoaiNguoinhanba the hsbactngoaiNguoinhanba to set
     */
    public void setHsbactngoaiNguoinhanba(DtDmNhanVien hsbactngoaiNguoinhanba) {
        this.hsbactngoaiNguoinhanba = hsbactngoaiNguoinhanba;
    }

    /**
     * @return the hsbactngoaiBSdieutri
     */
    public DtDmNhanVien getHsbactngoaiBSdieutri() {
        return hsbactngoaiBSdieutri;
    }

     public DtDmNhanVien getHsbactngoaiBSdieutri(boolean create) {
           if (create && hsbactngoaiBSdieutri == null) {
            hsbactngoaiBSdieutri = new DtDmNhanVien();
        }
        return hsbactngoaiBSdieutri;

    }


    /**
     * @param hsbactngoaiBSdieutri the hsbactngoaiBSdieutri to set
     */
    public void setHsbactngoaiBSdieutri(DtDmNhanVien hsbactngoaiBSdieutri) {
        this.hsbactngoaiBSdieutri = hsbactngoaiBSdieutri;
    }
}
