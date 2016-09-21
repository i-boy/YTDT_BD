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
@Table(name = "HSBA_CHI_TIET_NAOPHATHAI")
@NamedQueries({
    @NamedQuery(name = "HsbaChiTietNaophathai.findAll", query = "SELECT h FROM HsbaChiTietNaophathai h"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiMa", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiMa = :hsbactnaophathaiMa"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiHotenchong", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiHotenchong = :hsbactnaophathaiHotenchong"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiTuoichong", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiTuoichong = :hsbactnaophathaiTuoichong"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiNghenghiepchong", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiNghenghiepchong = :hsbactnaophathaiNghenghiepchong"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiDiachichong", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiDiachichong = :hsbactnaophathaiDiachichong"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiLydovaov", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiLydovaov = :hsbactnaophathaiLydovaov"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiSolande", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiSolande = :hsbactnaophathaiSolande"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiSolandekho", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiSolandekho = :hsbactnaophathaiSolandekho"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiSolansay", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiSolansay = :hsbactnaophathaiSolansay"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiSolannao", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiSolannao = :hsbactnaophathaiSolannao"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiLydonao", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiLydonao = :hsbactnaophathaiLydonao"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiApdungpphanchesinhde", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiApdungpphanchesinhde = :hsbactnaophathaiApdungpphanchesinhde"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiDacobenhphukhoagi", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiDacobenhphukhoagi = :hsbactnaophathaiDacobenhphukhoagi"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiDachualanh", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiDachualanh = :hsbactnaophathaiDachualanh"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiDangcobenhphukhoagi", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiDangcobenhphukhoagi = :hsbactnaophathaiDangcobenhphukhoagi"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiDangchuahaykhong", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiDangchuahaykhong = :hsbactnaophathaiDangchuahaykhong"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiNgaydaukinhcuoi", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiNgaydaukinhcuoi = :hsbactnaophathaiNgaydaukinhcuoi"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiSonandochieucaotucung", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiSonandochieucaotucung = :hsbactnaophathaiSonandochieucaotucung"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiAmho", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiAmho = :hsbactnaophathaiAmho"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiAmdao", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiAmdao = :hsbactnaophathaiAmdao"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiCotucung", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiCotucung = :hsbactnaophathaiCotucung"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiTuicung", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiTuicung = :hsbactnaophathaiTuicung"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiTucung", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiTucung = :hsbactnaophathaiTucung"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiPhanphu", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiPhanphu = :hsbactnaophathaiPhanphu"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiPhanungsinhvat", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiPhanungsinhvat = :hsbactnaophathaiPhanungsinhvat"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiCongthucbanhcau", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiCongthucbanhcau = :hsbactnaophathaiCongthucbanhcau"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiSoluonghongcau", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiSoluonghongcau = :hsbactnaophathaiSoluonghongcau"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiMaudong", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiMaudong = :hsbactnaophathaiMaudong"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiMauchay", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiMauchay = :hsbactnaophathaiMauchay"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiDientim", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiDientim = :hsbactnaophathaiDientim"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiChupphoitim", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiChupphoitim = :hsbactnaophathaiChupphoitim"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiChidinhthuthuat", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiChidinhthuthuat = :hsbactnaophathaiChidinhthuthuat"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiChieusautucung", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiChieusautucung = :hsbactnaophathaiChieusautucung"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiNonghegar", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiNonghegar = :hsbactnaophathaiNonghegar"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiThuocteme", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiThuocteme = :hsbactnaophathaiThuocteme"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiOcytocime", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiOcytocime = :hsbactnaophathaiOcytocime"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiOcytocimeDonvi", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiOcytocimeDonvi = :hsbactnaophathaiOcytocimeDonvi"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiNaodungcu", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiNaodungcu = :hsbactnaophathaiNaodungcu"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiGap", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiGap = :hsbactnaophathaiGap"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiDattuinuoc", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiDattuinuoc = :hsbactnaophathaiDattuinuoc"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiThuthuatkethopkhac", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiThuthuatkethopkhac = :hsbactnaophathaiThuthuatkethopkhac"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiSlClTrungthainhaulayra", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiSlClTrungthainhaulayra = :hsbactnaophathaiSlClTrungthainhaulayra"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiBienchungluclaythai", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiBienchungluclaythai = :hsbactnaophathaiBienchungluclaythai"),
    @NamedQuery(name = "HsbaChiTietNaophathai.findByHsbactnaophathaiKetquakhixuatvien", query = "SELECT h FROM HsbaChiTietNaophathai h WHERE h.hsbactnaophathaiKetquakhixuatvien = :hsbactnaophathaiKetquakhixuatvien")})
public class HsbaChiTietNaophathai implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_NAOPHATHAI")
    @SequenceGenerator(name = "HSBA_CHI_TIET_NAOPHATHAI", sequenceName = "HSBA_CHI_TIET_NAOPHATHAI_HSBAC", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTNAOPHATHAI_MA")
    private Integer hsbactnaophathaiMa;
    @Column(name = "HSBACTNAOPHATHAI_HOTENCHONG")
    private String hsbactnaophathaiHotenchong;
    @Column(name = "HSBACTNAOPHATHAI_TUOICHONG")
    private String hsbactnaophathaiTuoichong;
    @Column(name = "HSBACTNAOPHATHAI_NNCHONG")
    private String hsbactnaophathaiNghenghiepchong;
    @Column(name = "HSBACTNAOPHATHAI_DIACHICHONG")
    private String hsbactnaophathaiDiachichong;
    @Column(name = "HSBACTNAOPHATHAI_LYDOVAOV")
    private String hsbactnaophathaiLydovaov;
    @Column(name = "HSBACTNAOPHATHAI_SOLANDE")
    private String hsbactnaophathaiSolande;
    @Column(name = "HSBACTNAOPHATHAI_SOLANDEKHO")
    private String hsbactnaophathaiSolandekho;
    @Column(name = "HSBACTNAOPHATHAI_SOLANSAY")
    private String hsbactnaophathaiSolansay;
    @Column(name = "HSBACTNAOPHATHAI_SOLANNAO")
    private String hsbactnaophathaiSolannao;
    @Column(name = "HSBACTNAOPHATHAI_LYDONAO")
    private String hsbactnaophathaiLydonao;
    @Column(name = "HSBACTNAOPHATHAI_ADPCSINHDE")
    private String hsbactnaophathaiApdungpphanchesinhde;
    @Column(name = "HSBACTNAOPHATHAI_DACOBENHPKGI")
    private String hsbactnaophathaiDacobenhphukhoagi;
    @Column(name = "HSBACTNAOPHATHAI_DACHUALANH")
    private String hsbactnaophathaiDachualanh;
    @Column(name = "HSBACTNAOPHATHAI_DNGCOBENHPKGI")
    private String hsbactnaophathaiDangcobenhphukhoagi;
    @Column(name = "HSBACTNAOPHATHAI_DANGCHUAHK")
    private String hsbactnaophathaiDangchuahaykhong;
    @Column(name = "HSBACTNAOPHATHAI_NDAUKINHCUOI")
    @Temporal(TemporalType.DATE)
    private Date hsbactnaophathaiNgaydaukinhcuoi;
    @Column(name = "HSBACTNAOPHATHAI_SONANDOCCAOTC")
    private String hsbactnaophathaiSonandochieucaotucung;
    @Column(name = "HSBACTNAOPHATHAI_AMHO")
    private String hsbactnaophathaiAmho;
    @Column(name = "HSBACTNAOPHATHAI_AMDAO")
    private String hsbactnaophathaiAmdao;
    @Column(name = "HSBACTNAOPHATHAI_COTUCUNG")
    private String hsbactnaophathaiCotucung;
    @Column(name = "HSBACTNAOPHATHAI_TUICUNG")
    private String hsbactnaophathaiTuicung;
    @Column(name = "HSBACTNAOPHATHAI_TUCUNG")
    private String hsbactnaophathaiTucung;
    @Column(name = "HSBACTNAOPHATHAI_PHANPHU")
    private String hsbactnaophathaiPhanphu;
    @Column(name = "HSBACTNAOPHATHAI_PUSINHVAT")
    private String hsbactnaophathaiPhanungsinhvat;
    @Column(name = "HSBACTNAOPHATHAI_CTHUCBANHCAU")
    private String hsbactnaophathaiCongthucbanhcau;
    @Column(name = "HSBACTNAOPHATHAI_SLHONGCAU")
    private String hsbactnaophathaiSoluonghongcau;
    @Column(name = "HSBACTNAOPHATHAI_MAUDONG")
    private String hsbactnaophathaiMaudong;
    @Column(name = "HSBACTNAOPHATHAI_MAUCHAY")
    private String hsbactnaophathaiMauchay;
    @Column(name = "HSBACTNAOPHATHAI_DIENTIM")
    private String hsbactnaophathaiDientim;
    @Column(name = "HSBACTNAOPHATHAI_CHUPPHOITIM")
    private String hsbactnaophathaiChupphoitim;
    @Column(name = "HSBACTNAOPHATHAI_CHIDINHTT")
    private String hsbactnaophathaiChidinhthuthuat;
    @Column(name = "HSBACTNAOPHATHAI_CHIEUCAOTC")
    private String hsbactnaophathaiChieucaotucung;
    @Column(name = "HSBACTNAOPHATHAI_CHIEUSAUTC")
    private String hsbactnaophathaiChieusautucung;
    @Column(name = "HSBACTNAOPHATHAI_NONGHEGAR")
    private String hsbactnaophathaiNonghegar;
    @Column(name = "HSBACTNAOPHATHAI_THUOCTEME")
    private String hsbactnaophathaiThuocteme;
    @Column(name = "HSBACTNAOPHATHAI_OCYTOCIME")
    private String hsbactnaophathaiOcytocime;
    @Column(name = "HSBACTNAOPHATHAI_OCYTOCIME_DV")
    private String hsbactnaophathaiOcytocimeDonvi;
    @Column(name = "HSBACTNAOPHATHAI_NAODUNGCU")
    private String hsbactnaophathaiNaodungcu;
    @Column(name = "HSBACTNAOPHATHAI_GAP")
    private String hsbactnaophathaiGap;
    @Column(name = "HSBACTNAOPHATHAI_DATTUINUOC")
    private String hsbactnaophathaiDattuinuoc;
    @Column(name = "HSBACTNAOPHATHAI_TTKETHOPKHAC")
    private String hsbactnaophathaiThuthuatkethopkhac;
    @Column(name = "HSBACTNAOPHATHAI_SL_CLTTNLAYRA")
    private String hsbactnaophathaiSlClTrungthainhaulayra;
    @Column(name = "HSBACTNAOPHATHAI_BCLUCLAYTHAI")
    private String hsbactnaophathaiBienchungluclaythai;
    @Column(name = "HSBACTNAOPHATHAI_KQKHIXUATVIEN")
    private String hsbactnaophathaiKetquakhixuatvien;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;
    @JoinColumn(name = "HSBACTNAOPHATHAI_BSLAMBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactnaophathaiBslamba;
    @JoinColumn(name = "HSBACTNAOPHATHAI_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactnaophathaiBsdieutri;
   

    public HsbaChiTietNaophathai() {
    }

    public HsbaChiTietNaophathai(Integer hsbactnaophathaiMa) {
        this.hsbactnaophathaiMa = hsbactnaophathaiMa;
    }

    public Integer getHsbactnaophathaiMa() {
        return hsbactnaophathaiMa;
    }

    public void setHsbactnaophathaiMa(Integer hsbactnaophathaiMa) {
        this.hsbactnaophathaiMa = hsbactnaophathaiMa;
    }

    public String getHsbactnaophathaiHotenchong() {
        return hsbactnaophathaiHotenchong;
    }

    public void setHsbactnaophathaiHotenchong(String hsbactnaophathaiHotenchong) {
        this.hsbactnaophathaiHotenchong = hsbactnaophathaiHotenchong;
    }

    public String getHsbactnaophathaiTuoichong() {
        return hsbactnaophathaiTuoichong;
    }

    public void setHsbactnaophathaiTuoichong(String hsbactnaophathaiTuoichong) {
        this.hsbactnaophathaiTuoichong = hsbactnaophathaiTuoichong;
    }

    public String getHsbactnaophathaiNghenghiepchong() {
        return hsbactnaophathaiNghenghiepchong;
    }

    public void setHsbactnaophathaiNghenghiepchong(String hsbactnaophathaiNghenghiepchong) {
        this.hsbactnaophathaiNghenghiepchong = hsbactnaophathaiNghenghiepchong;
    }

    public String getHsbactnaophathaiDiachichong() {
        return hsbactnaophathaiDiachichong;
    }

    public void setHsbactnaophathaiDiachichong(String hsbactnaophathaiDiachichong) {
        this.hsbactnaophathaiDiachichong = hsbactnaophathaiDiachichong;
    }

    public String getHsbactnaophathaiLydovaov() {
        return hsbactnaophathaiLydovaov;
    }

    public void setHsbactnaophathaiLydovaov(String hsbactnaophathaiLydovaov) {
        this.hsbactnaophathaiLydovaov = hsbactnaophathaiLydovaov;
    }

    public String getHsbactnaophathaiSolande() {
        return hsbactnaophathaiSolande;
    }

    public void setHsbactnaophathaiSolande(String hsbactnaophathaiSolande) {
        this.hsbactnaophathaiSolande = hsbactnaophathaiSolande;
    }

    public String getHsbactnaophathaiSolandekho() {
        return hsbactnaophathaiSolandekho;
    }

    public void setHsbactnaophathaiSolandekho(String hsbactnaophathaiSolandekho) {
        this.hsbactnaophathaiSolandekho = hsbactnaophathaiSolandekho;
    }

    public String getHsbactnaophathaiSolansay() {
        return hsbactnaophathaiSolansay;
    }

    public void setHsbactnaophathaiSolansay(String hsbactnaophathaiSolansay) {
        this.hsbactnaophathaiSolansay = hsbactnaophathaiSolansay;
    }

    public String getHsbactnaophathaiSolannao() {
        return hsbactnaophathaiSolannao;
    }

    public void setHsbactnaophathaiSolannao(String hsbactnaophathaiSolannao) {
        this.hsbactnaophathaiSolannao = hsbactnaophathaiSolannao;
    }

    public String getHsbactnaophathaiLydonao() {
        return hsbactnaophathaiLydonao;
    }

    public void setHsbactnaophathaiLydonao(String hsbactnaophathaiLydonao) {
        this.hsbactnaophathaiLydonao = hsbactnaophathaiLydonao;
    }

    public String getHsbactnaophathaiApdungpphanchesinhde() {
        return hsbactnaophathaiApdungpphanchesinhde;
    }

    public void setHsbactnaophathaiApdungpphanchesinhde(String hsbactnaophathaiApdungpphanchesinhde) {
        this.hsbactnaophathaiApdungpphanchesinhde = hsbactnaophathaiApdungpphanchesinhde;
    }

    public String getHsbactnaophathaiDacobenhphukhoagi() {
        return hsbactnaophathaiDacobenhphukhoagi;
    }

    public void setHsbactnaophathaiDacobenhphukhoagi(String hsbactnaophathaiDacobenhphukhoagi) {
        this.hsbactnaophathaiDacobenhphukhoagi = hsbactnaophathaiDacobenhphukhoagi;
    }

    public String getHsbactnaophathaiDachualanh() {
        return hsbactnaophathaiDachualanh;
    }

    public void setHsbactnaophathaiDachualanh(String hsbactnaophathaiDachualanh) {
        this.hsbactnaophathaiDachualanh = hsbactnaophathaiDachualanh;
    }

    public String getHsbactnaophathaiDangcobenhphukhoagi() {
        return hsbactnaophathaiDangcobenhphukhoagi;
    }

    public void setHsbactnaophathaiDangcobenhphukhoagi(String hsbactnaophathaiDangcobenhphukhoagi) {
        this.hsbactnaophathaiDangcobenhphukhoagi = hsbactnaophathaiDangcobenhphukhoagi;
    }

    public String getHsbactnaophathaiDangchuahaykhong() {
        return hsbactnaophathaiDangchuahaykhong;
    }

    public void setHsbactnaophathaiDangchuahaykhong(String hsbactnaophathaiDangchuahaykhong) {
        this.hsbactnaophathaiDangchuahaykhong = hsbactnaophathaiDangchuahaykhong;
    }

    public Date getHsbactnaophathaiNgaydaukinhcuoi() {
        return hsbactnaophathaiNgaydaukinhcuoi;
    }

    public void setHsbactnaophathaiNgaydaukinhcuoi(Date hsbactnaophathaiNgaydaukinhcuoi) {
        this.hsbactnaophathaiNgaydaukinhcuoi = hsbactnaophathaiNgaydaukinhcuoi;
    }

    public String getHsbactnaophathaiSonandochieucaotucung() {
        return hsbactnaophathaiSonandochieucaotucung;
    }

    public void setHsbactnaophathaiSonandochieucaotucung(String hsbactnaophathaiSonandochieucaotucung) {
        this.hsbactnaophathaiSonandochieucaotucung = hsbactnaophathaiSonandochieucaotucung;
    }

    public String getHsbactnaophathaiAmho() {
        return hsbactnaophathaiAmho;
    }

    public void setHsbactnaophathaiAmho(String hsbactnaophathaiAmho) {
        this.hsbactnaophathaiAmho = hsbactnaophathaiAmho;
    }

    public String getHsbactnaophathaiAmdao() {
        return hsbactnaophathaiAmdao;
    }

    public void setHsbactnaophathaiAmdao(String hsbactnaophathaiAmdao) {
        this.hsbactnaophathaiAmdao = hsbactnaophathaiAmdao;
    }

    public String getHsbactnaophathaiCotucung() {
        return hsbactnaophathaiCotucung;
    }

    public void setHsbactnaophathaiCotucung(String hsbactnaophathaiCotucung) {
        this.hsbactnaophathaiCotucung = hsbactnaophathaiCotucung;
    }

    public String getHsbactnaophathaiTuicung() {
        return hsbactnaophathaiTuicung;
    }

    public void setHsbactnaophathaiTuicung(String hsbactnaophathaiTuicung) {
        this.hsbactnaophathaiTuicung = hsbactnaophathaiTuicung;
    }

    public String getHsbactnaophathaiTucung() {
        return hsbactnaophathaiTucung;
    }

    public void setHsbactnaophathaiTucung(String hsbactnaophathaiTucung) {
        this.hsbactnaophathaiTucung = hsbactnaophathaiTucung;
    }

    public String getHsbactnaophathaiPhanphu() {
        return hsbactnaophathaiPhanphu;
    }

    public void setHsbactnaophathaiPhanphu(String hsbactnaophathaiPhanphu) {
        this.hsbactnaophathaiPhanphu = hsbactnaophathaiPhanphu;
    }

    public String getHsbactnaophathaiPhanungsinhvat() {
        return hsbactnaophathaiPhanungsinhvat;
    }

    public void setHsbactnaophathaiPhanungsinhvat(String hsbactnaophathaiPhanungsinhvat) {
        this.hsbactnaophathaiPhanungsinhvat = hsbactnaophathaiPhanungsinhvat;
    }

    public String getHsbactnaophathaiCongthucbanhcau() {
        return hsbactnaophathaiCongthucbanhcau;
    }

    public void setHsbactnaophathaiCongthucbanhcau(String hsbactnaophathaiCongthucbanhcau) {
        this.hsbactnaophathaiCongthucbanhcau = hsbactnaophathaiCongthucbanhcau;
    }

    public String getHsbactnaophathaiSoluonghongcau() {
        return hsbactnaophathaiSoluonghongcau;
    }

    public void setHsbactnaophathaiSoluonghongcau(String hsbactnaophathaiSoluonghongcau) {
        this.hsbactnaophathaiSoluonghongcau = hsbactnaophathaiSoluonghongcau;
    }

    public String getHsbactnaophathaiMaudong() {
        return hsbactnaophathaiMaudong;
    }

    public void setHsbactnaophathaiMaudong(String hsbactnaophathaiMaudong) {
        this.hsbactnaophathaiMaudong = hsbactnaophathaiMaudong;
    }

    public String getHsbactnaophathaiMauchay() {
        return hsbactnaophathaiMauchay;
    }

    public void setHsbactnaophathaiMauchay(String hsbactnaophathaiMauchay) {
        this.hsbactnaophathaiMauchay = hsbactnaophathaiMauchay;
    }

    public String getHsbactnaophathaiDientim() {
        return hsbactnaophathaiDientim;
    }

    public void setHsbactnaophathaiDientim(String hsbactnaophathaiDientim) {
        this.hsbactnaophathaiDientim = hsbactnaophathaiDientim;
    }

    public String getHsbactnaophathaiChupphoitim() {
        return hsbactnaophathaiChupphoitim;
    }

    public void setHsbactnaophathaiChupphoitim(String hsbactnaophathaiChupphoitim) {
        this.hsbactnaophathaiChupphoitim = hsbactnaophathaiChupphoitim;
    }

    public String getHsbactnaophathaiChidinhthuthuat() {
        return hsbactnaophathaiChidinhthuthuat;
    }

    public void setHsbactnaophathaiChidinhthuthuat(String hsbactnaophathaiChidinhthuthuat) {
        this.hsbactnaophathaiChidinhthuthuat = hsbactnaophathaiChidinhthuthuat;
    }

    public String getHsbactnaophathaiChieusautucung() {
        return hsbactnaophathaiChieusautucung;
    }

    public void setHsbactnaophathaiChieusautucung(String hsbactnaophathaiChieusautucung) {
        this.hsbactnaophathaiChieusautucung = hsbactnaophathaiChieusautucung;
    }

    public String getHsbactnaophathaiNonghegar() {
        return hsbactnaophathaiNonghegar;
    }

    public void setHsbactnaophathaiNonghegar(String hsbactnaophathaiNonghegar) {
        this.hsbactnaophathaiNonghegar = hsbactnaophathaiNonghegar;
    }

    public String getHsbactnaophathaiThuocteme() {
        return hsbactnaophathaiThuocteme;
    }

    public void setHsbactnaophathaiThuocteme(String hsbactnaophathaiThuocteme) {
        this.hsbactnaophathaiThuocteme = hsbactnaophathaiThuocteme;
    }

    public String getHsbactnaophathaiOcytocime() {
        return hsbactnaophathaiOcytocime;
    }

    public void setHsbactnaophathaiOcytocime(String hsbactnaophathaiOcytocime) {
        this.hsbactnaophathaiOcytocime = hsbactnaophathaiOcytocime;
    }

    public String getHsbactnaophathaiOcytocimeDonvi() {
        return hsbactnaophathaiOcytocimeDonvi;
    }

    public void setHsbactnaophathaiOcytocimeDonvi(String hsbactnaophathaiOcytocimeDonvi) {
        this.hsbactnaophathaiOcytocimeDonvi = hsbactnaophathaiOcytocimeDonvi;
    }

    public String getHsbactnaophathaiNaodungcu() {
        return hsbactnaophathaiNaodungcu;
    }

    public void setHsbactnaophathaiNaodungcu(String hsbactnaophathaiNaodungcu) {
        this.hsbactnaophathaiNaodungcu = hsbactnaophathaiNaodungcu;
    }

    public String getHsbactnaophathaiGap() {
        return hsbactnaophathaiGap;
    }

    public void setHsbactnaophathaiGap(String hsbactnaophathaiGap) {
        this.hsbactnaophathaiGap = hsbactnaophathaiGap;
    }

    public String getHsbactnaophathaiDattuinuoc() {
        return hsbactnaophathaiDattuinuoc;
    }

    public void setHsbactnaophathaiDattuinuoc(String hsbactnaophathaiDattuinuoc) {
        this.hsbactnaophathaiDattuinuoc = hsbactnaophathaiDattuinuoc;
    }

    public String getHsbactnaophathaiThuthuatkethopkhac() {
        return hsbactnaophathaiThuthuatkethopkhac;
    }

    public void setHsbactnaophathaiThuthuatkethopkhac(String hsbactnaophathaiThuthuatkethopkhac) {
        this.hsbactnaophathaiThuthuatkethopkhac = hsbactnaophathaiThuthuatkethopkhac;
    }

    public String getHsbactnaophathaiSlClTrungthainhaulayra() {
        return hsbactnaophathaiSlClTrungthainhaulayra;
    }

    public void setHsbactnaophathaiSlClTrungthainhaulayra(String hsbactnaophathaiSlClTrungthainhaulayra) {
        this.hsbactnaophathaiSlClTrungthainhaulayra = hsbactnaophathaiSlClTrungthainhaulayra;
    }

    public String getHsbactnaophathaiBienchungluclaythai() {
        return hsbactnaophathaiBienchungluclaythai;
    }

    public void setHsbactnaophathaiBienchungluclaythai(String hsbactnaophathaiBienchungluclaythai) {
        this.hsbactnaophathaiBienchungluclaythai = hsbactnaophathaiBienchungluclaythai;
    }

    public String getHsbactnaophathaiKetquakhixuatvien() {
        return hsbactnaophathaiKetquakhixuatvien;
    }

    public void setHsbactnaophathaiKetquakhixuatvien(String hsbactnaophathaiKetquakhixuatvien) {
        this.hsbactnaophathaiKetquakhixuatvien = hsbactnaophathaiKetquakhixuatvien;
    }

    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    public DtDmNhanVien getHsbactnaophathaiBslamba() {
        return hsbactnaophathaiBslamba;
    }

    public void setHsbactnaophathaiBslamba(DtDmNhanVien hsbactnaophathaiBslamba) {
        this.hsbactnaophathaiBslamba = hsbactnaophathaiBslamba;
    }

    public DtDmNhanVien getHsbactnaophathaiBsdieutri() {
        return hsbactnaophathaiBsdieutri;
    }

    public void setHsbactnaophathaiBsdieutri(DtDmNhanVien hsbactnaophathaiBsdieutri) {
        this.hsbactnaophathaiBsdieutri = hsbactnaophathaiBsdieutri;
    }

    public String getHsbactnaophathaiChieucaotucung() {
        return hsbactnaophathaiChieucaotucung;
    }

    public void setHsbactnaophathaiChieucaotucung(String hsbactnaophathaiChieucaotucung) {
        this.hsbactnaophathaiChieucaotucung = hsbactnaophathaiChieucaotucung;
    }

    public DtDmNhanVien getHsbactnaophathaiBsdieutri(boolean create) {
        if (create && hsbactnaophathaiBsdieutri == null) {
            hsbactnaophathaiBsdieutri = new DtDmNhanVien();
        }
        return hsbactnaophathaiBsdieutri;
    }

    public DtDmNhanVien getHsbactnaophathaiBslamba(boolean create) {
        if (create && hsbactnaophathaiBslamba == null) {
            hsbactnaophathaiBslamba = new DtDmNhanVien();
        }
        return hsbactnaophathaiBslamba;
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
        hash += (hsbactnaophathaiMa != null ? hsbactnaophathaiMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietNaophathai)) {
            return false;
        }
        HsbaChiTietNaophathai other = (HsbaChiTietNaophathai) object;
        if ((this.hsbactnaophathaiMa == null && other.hsbactnaophathaiMa != null) || (this.hsbactnaophathaiMa != null && !this.hsbactnaophathaiMa.equals(other.hsbactnaophathaiMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.test.HsbaChiTietNaophathai[hsbactnaophathaiMa=" + hsbactnaophathaiMa + "]";
    }

}
