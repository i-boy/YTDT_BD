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
@Table(name = "HSBA_CHI_TIET_PHUKHOA")
@NamedQueries({
    @NamedQuery(name = "HsbaChiTietPhukhoa.findAll", query = "SELECT h FROM HsbaChiTietPhukhoa h")})
public class HsbaChiTietPhukhoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_PHUKHOA")
    @SequenceGenerator(name = "HSBA_CHI_TIET_PHUKHOA", sequenceName = "HSBA_CHI_TIET_PHUKHOA_HSBACTPH", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTPHUKHOA_MA")
    private Integer hsbactphukhoaMa;
    @Column(name = "HSBACTPHUKHOA_QTBENHLY")
    private String hsbactphukhoaQtbenhly;
    @Column(name = "HSBACTPHUKHOA_TIENSUBENHBT")
    private String hsbactphukhoaTiensubenhbt;
    @Column(name = "HSBACTPHUKHOA_TIENSUBENHGD")
    private String hsbactphukhoaTiensubenhgd;
    @Column(name = "HSBACTPHUKHOA_THAYKINHNAM")
    private String hsbactphukhoaThaykinhnam;
    @Column(name = "HSBACTPHUKHOA_THAYKINHTUOI")
    private String hsbactphukhoaThaykinhtuoi;
    @Column(name = "HSBACTPHUKHOA_TCHATKINHNGUYET")
    private String hsbactphukhoaTinhchatkinhnguyet;
    @Column(name = "HSBACTPHUKHOA_CHUKYKINH")
    private String hsbactphukhoaChukykinh;
    @Column(name = "HSBACTPHUKHOA_SONGAYTHAYKINH")
    private String hsbactphukhoaSongaythaykinh;
    @Column(name = "HSBACTPHUKHOA_LUONGKINH")
    private String hsbactphukhoaLuongkinh;
    @Column(name = "HSBACTPHUKHOA_KINHLANCUOINGAY")
    @Temporal(TemporalType.DATE)
    private Date hsbactphukhoaKinhlancuoingay;
    @Column(name = "HSBACTPHUKHOA_CODAUBUNG")
    private Boolean hsbactphukhoaCodaubung;
    @Column(name = "HSBACTPHUKHOA_THOIGIANTRUOC")
    private Boolean hsbactphukhoaThoigiantruoc;
    @Column(name = "HSBACTPHUKHOA_THOIGIANTRONG")
    private Boolean hsbactphukhoaThoigiantrong;
    @Column(name = "HSBACTPHUKHOA_THOIGIANSAU")
    private Boolean hsbactphukhoaThoigiansau;
    @Column(name = "HSBACTPHUKHOA_LAYCHONGNAM")
    private String hsbactphukhoaLaychongnam;
    @Column(name = "HSBACTPHUKHOA_LAYCHONGTUOI")
    private String hsbactphukhoaLaychongtuoi;
    @Column(name = "HSBACTPHUKHOA_HETKINHNAM")
    private String hsbactphukhoaHetkinhnam;
    @Column(name = "HSBACTPHUKHOA_KETKINHTUOI")
    private String hsbactphukhoaKetkinhtuoi;
    @Column(name = "HSBACTPHUKHOA_BENHPKDADIEUTRI")
    private String hsbactphukhoaBenhphukhoadadieutri;
    @Column(name = "HSBACTPHUKHOA_SINHDUTHANG")
    private String hsbactphukhoaSinhduthang;
    @Column(name = "HSBACTPHUKHOA_SINHDENON")
    private String hsbactphukhoaSinhdenon;
    @Column(name = "HSBACTPHUKHOA_SINHNAOHUT")
    private String hsbactphukhoaSinhnaohut;
    @Column(name = "HSBACTPHUKHOA_SINHSONG")
    private String hsbactphukhoaSinhsong;
    @Column(name = "HSBACTPHUKHOA_DANIEMMAC")
    private String hsbactphukhoaDaniemmac;
    @Column(name = "HSBACTPHUKHOA_HACH")
    private String hsbactphukhoaHach;
    @Column(name = "HSBACTPHUKHOA_VU")
    private String hsbactphukhoaVu;
    @Column(name = "HSBACTPHUKHOA_TUANHOAN")
    private String hsbactphukhoaTuanhoan;
    @Column(name = "HSBACTPHUKHOA_HOHAP")
    private String hsbactphukhoaHohap;
    @Column(name = "HSBACTPHUKHOA_TIEUHOA")
    private String hsbactphukhoaTieuhoa;
    @Column(name = "HSBACTPHUKHOA_THANTIETNIEUSHOC")
    private String hsbactphukhoaThantietnieusinhhoc;
    @Column(name = "HSBACTPHUKHOA_THANKINH")
    private String hsbactphukhoaThankinh;
    @Column(name = "HSBACTPHUKHOA_COXUONGKHOP")
    private String hsbactphukhoaCoxuongkhop;
    @Column(name = "HSBACTPHUKHOA_COQUANKHAC")
    private String hsbactphukhoaCoquankhac;
    @Column(name = "HSBACTPHUKHOA_DAUHIEUSDUCTPHAT")
    private String hsbactphukhoaDauhieusinhducthuphat;
    @Column(name = "HSBACTPHUKHOA_MOILON")
    private String hsbactphukhoaMoilon;
    @Column(name = "HSBACTPHUKHOA_MOIBE")
    private String hsbactphukhoaMoibe;
    @Column(name = "HSBACTPHUKHOA_AMVAT")
    private String hsbactphukhoaAmvat;
    @Column(name = "HSBACTPHUKHOA_AMHO")
    private String hsbactphukhoaAmho;
    @Column(name = "HSBACTPHUKHOA_MANTRINH")
    private String hsbactphukhoaMantrinh;
    @Column(name = "HSBACTPHUKHOA_TANGSINHMON")
    private String hsbactphukhoaTangsinhmon;
    @Column(name = "HSBACTPHUKHOA_AMDAO")
    private String hsbactphukhoaAmdao;
    @Column(name = "HSBACTPHUKHOA_COTUCUNG")
    private String hsbactphukhoaCotucung;
    @Column(name = "HSBACTPHUKHOA_THANTUCUNG")
    private String hsbactphukhoaThantucung;
    @Column(name = "HSBACTPHUKHOA_PHANPHU")
    private String hsbactphukhoaPhanphu;
    @Column(name = "HSBACTPHUKHOA_CACTUICUNG")
    private String hsbactphukhoaCactuicung;
    @Column(name = "HSBACTPHUKHOA_XETNGHIEMCANLAM")
    private String hsbactphukhoaXetnghiemcanlam;
    @Column(name = "HSBACTPHUKHOA_HUONGDIEUTRI")
    private String hsbactphukhoaHuongdieutri;
    @Column(name = "HSBACTPHUKHOA_TTBA")
    private String hsbactphukhoaTtba;
    @Column(name = "HSBACTPHUKHOA_TIENLUONG")
    private String hsbactphukhoaTienluong;
    @Column(name = "HSBACTPHUKHOA_LYDOVAOV")
    private String hsbactphukhoaLydovaov;
    @Column(name = "HSBACTPHUKHOA_QTBL_DBLS")
    private String hsbactphukhoaQtblDbls;
    @Column(name = "HSBACTPHUKHOA_TTKQXNCLS")
    private String hsbactphukhoaTtkqxncls;
    @Column(name = "HSBACTPHUKHOA_PPDIEUTRI")
    private String hsbactphukhoaPpdieutri;
    @Column(name = "HSBACTPHUKHOA_TTNGUOIBENHRAV")
    private String hsbactphukhoaTtnguoibenhrav;
    @Column(name = "HSBACTPHUKHOA_HUONGDT_CDTT")
    private String hsbactphukhoaHuongdtCdtt;
    @Column(name = "HSBACTPHUKHOA_SOTOXQUANG")
    private Integer hsbactphukhoaSotoxquang;
    @Column(name = "HSBACTPHUKHOA_SOTOCTSCANNER")
    private Integer hsbactphukhoaSotoctscanner;
    @Column(name = "HSBACTPHUKHOA_SOTOSIEUAM")
    private Integer hsbactphukhoaSotosieuam;
    @Column(name = "HSBACTPHUKHOA_SOTOXN")
    private Integer hsbactphukhoaSotoxn;
    @Column(name = "HSBACTPHUKHOA_SOTOKHAC")
    private Integer hsbactphukhoaSotokhac;
    @Column(name = "HSBACTPHUKHOA_SOTOLOAIKHAC")
    private String hsbactphukhoaSotoloaikhac;
    @Column(name = "HSBACTPHUKHOA_TONGSOTO")
    private Integer hsbactphukhoaTongsoto;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;
    @JoinColumn(name = "HSBACTPHUKHOA_BSLAMBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactphukhoaBslamba;
    @JoinColumn(name = "HSBACTPHUKHOA_NGUOIGIAOBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactphukhoaNguoigiaoba;
    @JoinColumn(name = "HSBACTPHUKHOA_NGUOINHANBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactphukhoaNguoinhanba;
    @JoinColumn(name = "HSBACTPHUKHOA_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactphukhoaBsdieutri;

    public HsbaChiTietPhukhoa() {
    }

    public HsbaChiTietPhukhoa(Integer hsbactphukhoaMa) {
        this.hsbactphukhoaMa = hsbactphukhoaMa;
    }

    public Integer getHsbactphukhoaMa() {
        return hsbactphukhoaMa;
    }

    public void setHsbactphukhoaMa(Integer hsbactphukhoaMa) {
        this.hsbactphukhoaMa = hsbactphukhoaMa;
    }

    public String getHsbactphukhoaQtbenhly() {
        return hsbactphukhoaQtbenhly;
    }

    public void setHsbactphukhoaQtbenhly(String hsbactphukhoaQtbenhly) {
        this.hsbactphukhoaQtbenhly = hsbactphukhoaQtbenhly;
    }

    public String getHsbactphukhoaTiensubenhbt() {
        return hsbactphukhoaTiensubenhbt;
    }

    public void setHsbactphukhoaTiensubenhbt(String hsbactphukhoaTiensubenhbt) {
        this.hsbactphukhoaTiensubenhbt = hsbactphukhoaTiensubenhbt;
    }

    public String getHsbactphukhoaTiensubenhgd() {
        return hsbactphukhoaTiensubenhgd;
    }

    public void setHsbactphukhoaTiensubenhgd(String hsbactphukhoaTiensubenhgd) {
        this.hsbactphukhoaTiensubenhgd = hsbactphukhoaTiensubenhgd;
    }

    public String getHsbactphukhoaThaykinhnam() {
        return hsbactphukhoaThaykinhnam;
    }

    public void setHsbactphukhoaThaykinhnam(String hsbactphukhoaThaykinhnam) {
        this.hsbactphukhoaThaykinhnam = hsbactphukhoaThaykinhnam;
    }

    public String getHsbactphukhoaThaykinhtuoi() {
        return hsbactphukhoaThaykinhtuoi;
    }

    public void setHsbactphukhoaThaykinhtuoi(String hsbactphukhoaThaykinhtuoi) {
        this.hsbactphukhoaThaykinhtuoi = hsbactphukhoaThaykinhtuoi;
    }

    public String getHsbactphukhoaTinhchatkinhnguyet() {
        return hsbactphukhoaTinhchatkinhnguyet;
    }

    public void setHsbactphukhoaTinhchatkinhnguyet(String hsbactphukhoaTinhchatkinhnguyet) {
        this.hsbactphukhoaTinhchatkinhnguyet = hsbactphukhoaTinhchatkinhnguyet;
    }

    public String getHsbactphukhoaChukykinh() {
        return hsbactphukhoaChukykinh;
    }

    public void setHsbactphukhoaChukykinh(String hsbactphukhoaChukykinh) {
        this.hsbactphukhoaChukykinh = hsbactphukhoaChukykinh;
    }

    public String getHsbactphukhoaSongaythaykinh() {
        return hsbactphukhoaSongaythaykinh;
    }

    public void setHsbactphukhoaSongaythaykinh(String hsbactphukhoaSongaythaykinh) {
        this.hsbactphukhoaSongaythaykinh = hsbactphukhoaSongaythaykinh;
    }

    public String getHsbactphukhoaLuongkinh() {
        return hsbactphukhoaLuongkinh;
    }

    public void setHsbactphukhoaLuongkinh(String hsbactphukhoaLuongkinh) {
        this.hsbactphukhoaLuongkinh = hsbactphukhoaLuongkinh;
    }

    public Date getHsbactphukhoaKinhlancuoingay() {
        return hsbactphukhoaKinhlancuoingay;
    }

    public void setHsbactphukhoaKinhlancuoingay(Date hsbactphukhoaKinhlancuoingay) {
        this.hsbactphukhoaKinhlancuoingay = hsbactphukhoaKinhlancuoingay;
    }

    public Boolean getHsbactphukhoaCodaubung() {
        return hsbactphukhoaCodaubung;
    }

    public void setHsbactphukhoaCodaubung(Boolean hsbactphukhoaCodaubung) {
        this.hsbactphukhoaCodaubung = hsbactphukhoaCodaubung;
    }

    public Boolean getHsbactphukhoaThoigiantruoc() {
        return hsbactphukhoaThoigiantruoc;
    }

    public void setHsbactphukhoaThoigiantruoc(Boolean hsbactphukhoaThoigiantruoc) {
        this.hsbactphukhoaThoigiantruoc = hsbactphukhoaThoigiantruoc;
    }

    public Boolean getHsbactphukhoaThoigiantrong() {
        return hsbactphukhoaThoigiantrong;
    }

    public void setHsbactphukhoaThoigiantrong(Boolean hsbactphukhoaThoigiantrong) {
        this.hsbactphukhoaThoigiantrong = hsbactphukhoaThoigiantrong;
    }

    public Boolean getHsbactphukhoaThoigiansau() {
        return hsbactphukhoaThoigiansau;
    }

    public void setHsbactphukhoaThoigiansau(Boolean hsbactphukhoaThoigiansau) {
        this.hsbactphukhoaThoigiansau = hsbactphukhoaThoigiansau;
    }

    public String getHsbactphukhoaLaychongnam() {
        return hsbactphukhoaLaychongnam;
    }

    public void setHsbactphukhoaLaychongnam(String hsbactphukhoaLaychongnam) {
        this.hsbactphukhoaLaychongnam = hsbactphukhoaLaychongnam;
    }

    public String getHsbactphukhoaLaychongtuoi() {
        return hsbactphukhoaLaychongtuoi;
    }

    public void setHsbactphukhoaLaychongtuoi(String hsbactphukhoaLaychongtuoi) {
        this.hsbactphukhoaLaychongtuoi = hsbactphukhoaLaychongtuoi;
    }

    public String getHsbactphukhoaHetkinhnam() {
        return hsbactphukhoaHetkinhnam;
    }

    public void setHsbactphukhoaHetkinhnam(String hsbactphukhoaHetkinhnam) {
        this.hsbactphukhoaHetkinhnam = hsbactphukhoaHetkinhnam;
    }

    public String getHsbactphukhoaKetkinhtuoi() {
        return hsbactphukhoaKetkinhtuoi;
    }

    public void setHsbactphukhoaKetkinhtuoi(String hsbactphukhoaKetkinhtuoi) {
        this.hsbactphukhoaKetkinhtuoi = hsbactphukhoaKetkinhtuoi;
    }

    public String getHsbactphukhoaBenhphukhoadadieutri() {
        return hsbactphukhoaBenhphukhoadadieutri;
    }

    public void setHsbactphukhoaBenhphukhoadadieutri(String hsbactphukhoaBenhphukhoadadieutri) {
        this.hsbactphukhoaBenhphukhoadadieutri = hsbactphukhoaBenhphukhoadadieutri;
    }

    public String getHsbactphukhoaSinhduthang() {
        return hsbactphukhoaSinhduthang;
    }

    public void setHsbactphukhoaSinhduthang(String hsbactphukhoaSinhduthang) {
        this.hsbactphukhoaSinhduthang = hsbactphukhoaSinhduthang;
    }

    public String getHsbactphukhoaSinhdenon() {
        return hsbactphukhoaSinhdenon;
    }

    public void setHsbactphukhoaSinhdenon(String hsbactphukhoaSinhdenon) {
        this.hsbactphukhoaSinhdenon = hsbactphukhoaSinhdenon;
    }

    public String getHsbactphukhoaSinhnaohut() {
        return hsbactphukhoaSinhnaohut;
    }

    public void setHsbactphukhoaSinhnaohut(String hsbactphukhoaSinhnaohut) {
        this.hsbactphukhoaSinhnaohut = hsbactphukhoaSinhnaohut;
    }

    public String getHsbactphukhoaSinhsong() {
        return hsbactphukhoaSinhsong;
    }

    public void setHsbactphukhoaSinhsong(String hsbactphukhoaSinhsong) {
        this.hsbactphukhoaSinhsong = hsbactphukhoaSinhsong;
    }

    public String getHsbactphukhoaDaniemmac() {
        return hsbactphukhoaDaniemmac;
    }

    public void setHsbactphukhoaDaniemmac(String hsbactphukhoaDaniemmac) {
        this.hsbactphukhoaDaniemmac = hsbactphukhoaDaniemmac;
    }

    public String getHsbactphukhoaHach() {
        return hsbactphukhoaHach;
    }

    public void setHsbactphukhoaHach(String hsbactphukhoaHach) {
        this.hsbactphukhoaHach = hsbactphukhoaHach;
    }

    public String getHsbactphukhoaVu() {
        return hsbactphukhoaVu;
    }

    public void setHsbactphukhoaVu(String hsbactphukhoaVu) {
        this.hsbactphukhoaVu = hsbactphukhoaVu;
    }

    public String getHsbactphukhoaTuanhoan() {
        return hsbactphukhoaTuanhoan;
    }

    public void setHsbactphukhoaTuanhoan(String hsbactphukhoaTuanhoan) {
        this.hsbactphukhoaTuanhoan = hsbactphukhoaTuanhoan;
    }

    public String getHsbactphukhoaHohap() {
        return hsbactphukhoaHohap;
    }

    public void setHsbactphukhoaHohap(String hsbactphukhoaHohap) {
        this.hsbactphukhoaHohap = hsbactphukhoaHohap;
    }

    public String getHsbactphukhoaTieuhoa() {
        return hsbactphukhoaTieuhoa;
    }

    public void setHsbactphukhoaTieuhoa(String hsbactphukhoaTieuhoa) {
        this.hsbactphukhoaTieuhoa = hsbactphukhoaTieuhoa;
    }

    public String getHsbactphukhoaThantietnieusinhhoc() {
        return hsbactphukhoaThantietnieusinhhoc;
    }

    public void setHsbactphukhoaThantietnieusinhhoc(String hsbactphukhoaThantietnieusinhhoc) {
        this.hsbactphukhoaThantietnieusinhhoc = hsbactphukhoaThantietnieusinhhoc;
    }

    public String getHsbactphukhoaThankinh() {
        return hsbactphukhoaThankinh;
    }

    public void setHsbactphukhoaThankinh(String hsbactphukhoaThankinh) {
        this.hsbactphukhoaThankinh = hsbactphukhoaThankinh;
    }

    public String getHsbactphukhoaCoxuongkhop() {
        return hsbactphukhoaCoxuongkhop;
    }

    public void setHsbactphukhoaCoxuongkhop(String hsbactphukhoaCoxuongkhop) {
        this.hsbactphukhoaCoxuongkhop = hsbactphukhoaCoxuongkhop;
    }

    public String getHsbactphukhoaCoquankhac() {
        return hsbactphukhoaCoquankhac;
    }

    public void setHsbactphukhoaCoquankhac(String hsbactphukhoaCoquankhac) {
        this.hsbactphukhoaCoquankhac = hsbactphukhoaCoquankhac;
    }

    public String getHsbactphukhoaDauhieusinhducthuphat() {
        return hsbactphukhoaDauhieusinhducthuphat;
    }

    public void setHsbactphukhoaDauhieusinhducthuphat(String hsbactphukhoaDauhieusinhducthuphat) {
        this.hsbactphukhoaDauhieusinhducthuphat = hsbactphukhoaDauhieusinhducthuphat;
    }

    public String getHsbactphukhoaMoilon() {
        return hsbactphukhoaMoilon;
    }

    public void setHsbactphukhoaMoilon(String hsbactphukhoaMoilon) {
        this.hsbactphukhoaMoilon = hsbactphukhoaMoilon;
    }

    public String getHsbactphukhoaMoibe() {
        return hsbactphukhoaMoibe;
    }

    public void setHsbactphukhoaMoibe(String hsbactphukhoaMoibe) {
        this.hsbactphukhoaMoibe = hsbactphukhoaMoibe;
    }

    public String getHsbactphukhoaAmvat() {
        return hsbactphukhoaAmvat;
    }

    public void setHsbactphukhoaAmvat(String hsbactphukhoaAmvat) {
        this.hsbactphukhoaAmvat = hsbactphukhoaAmvat;
    }

    public String getHsbactphukhoaAmho() {
        return hsbactphukhoaAmho;
    }

    public void setHsbactphukhoaAmho(String hsbactphukhoaAmho) {
        this.hsbactphukhoaAmho = hsbactphukhoaAmho;
    }

    public String getHsbactphukhoaMantrinh() {
        return hsbactphukhoaMantrinh;
    }

    public void setHsbactphukhoaMantrinh(String hsbactphukhoaMantrinh) {
        this.hsbactphukhoaMantrinh = hsbactphukhoaMantrinh;
    }

    public String getHsbactphukhoaTangsinhmon() {
        return hsbactphukhoaTangsinhmon;
    }

    public void setHsbactphukhoaTangsinhmon(String hsbactphukhoaTangsinhmon) {
        this.hsbactphukhoaTangsinhmon = hsbactphukhoaTangsinhmon;
    }

    public String getHsbactphukhoaAmdao() {
        return hsbactphukhoaAmdao;
    }

    public void setHsbactphukhoaAmdao(String hsbactphukhoaAmdao) {
        this.hsbactphukhoaAmdao = hsbactphukhoaAmdao;
    }

    public String getHsbactphukhoaCotucung() {
        return hsbactphukhoaCotucung;
    }

    public void setHsbactphukhoaCotucung(String hsbactphukhoaCotucung) {
        this.hsbactphukhoaCotucung = hsbactphukhoaCotucung;
    }

    public String getHsbactphukhoaThantucung() {
        return hsbactphukhoaThantucung;
    }

    public void setHsbactphukhoaThantucung(String hsbactphukhoaThantucung) {
        this.hsbactphukhoaThantucung = hsbactphukhoaThantucung;
    }

    public String getHsbactphukhoaPhanphu() {
        return hsbactphukhoaPhanphu;
    }

    public void setHsbactphukhoaPhanphu(String hsbactphukhoaPhanphu) {
        this.hsbactphukhoaPhanphu = hsbactphukhoaPhanphu;
    }

    public String getHsbactphukhoaCactuicung() {
        return hsbactphukhoaCactuicung;
    }

    public void setHsbactphukhoaCactuicung(String hsbactphukhoaCactuicung) {
        this.hsbactphukhoaCactuicung = hsbactphukhoaCactuicung;
    }

    public String getHsbactphukhoaXetnghiemcanlam() {
        return hsbactphukhoaXetnghiemcanlam;
    }

    public void setHsbactphukhoaXetnghiemcanlam(String hsbactphukhoaXetnghiemcanlam) {
        this.hsbactphukhoaXetnghiemcanlam = hsbactphukhoaXetnghiemcanlam;
    }

    public String getHsbactphukhoaHuongdieutri() {
        return hsbactphukhoaHuongdieutri;
    }

    public void setHsbactphukhoaHuongdieutri(String hsbactphukhoaHuongdieutri) {
        this.hsbactphukhoaHuongdieutri = hsbactphukhoaHuongdieutri;
    }

    public String getHsbactphukhoaTtba() {
        return hsbactphukhoaTtba;
    }

    public void setHsbactphukhoaTtba(String hsbactphukhoaTtba) {
        this.hsbactphukhoaTtba = hsbactphukhoaTtba;
    }

    public String getHsbactphukhoaTienluong() {
        return hsbactphukhoaTienluong;
    }

    public void setHsbactphukhoaTienluong(String hsbactphukhoaTienluong) {
        this.hsbactphukhoaTienluong = hsbactphukhoaTienluong;
    }

    public String getHsbactphukhoaLydovaov() {
        return hsbactphukhoaLydovaov;
    }

    public void setHsbactphukhoaLydovaov(String hsbactphukhoaLydovaov) {
        this.hsbactphukhoaLydovaov = hsbactphukhoaLydovaov;
    }

    public String getHsbactphukhoaQtblDbls() {
        return hsbactphukhoaQtblDbls;
    }

    public void setHsbactphukhoaQtblDbls(String hsbactphukhoaQtblDbls) {
        this.hsbactphukhoaQtblDbls = hsbactphukhoaQtblDbls;
    }

    public String getHsbactphukhoaTtkqxncls() {
        return hsbactphukhoaTtkqxncls;
    }

    public void setHsbactphukhoaTtkqxncls(String hsbactphukhoaTtkqxncls) {
        this.hsbactphukhoaTtkqxncls = hsbactphukhoaTtkqxncls;
    }

    public String getHsbactphukhoaPpdieutri() {
        return hsbactphukhoaPpdieutri;
    }

    public void setHsbactphukhoaPpdieutri(String hsbactphukhoaPpdieutri) {
        this.hsbactphukhoaPpdieutri = hsbactphukhoaPpdieutri;
    }

    public String getHsbactphukhoaTtnguoibenhrav() {
        return hsbactphukhoaTtnguoibenhrav;
    }

    public void setHsbactphukhoaTtnguoibenhrav(String hsbactphukhoaTtnguoibenhrav) {
        this.hsbactphukhoaTtnguoibenhrav = hsbactphukhoaTtnguoibenhrav;
    }

    public String getHsbactphukhoaHuongdtCdtt() {
        return hsbactphukhoaHuongdtCdtt;
    }

    public void setHsbactphukhoaHuongdtCdtt(String hsbactphukhoaHuongdtCdtt) {
        this.hsbactphukhoaHuongdtCdtt = hsbactphukhoaHuongdtCdtt;
    }

    public Integer getHsbactphukhoaSotoxquang() {
        return hsbactphukhoaSotoxquang;
    }

    public void setHsbactphukhoaSotoxquang(Integer hsbactphukhoaSotoxquang) {
        this.hsbactphukhoaSotoxquang = hsbactphukhoaSotoxquang;
    }

    public Integer getHsbactphukhoaSotoctscanner() {
        return hsbactphukhoaSotoctscanner;
    }

    public void setHsbactphukhoaSotoctscanner(Integer hsbactphukhoaSotoctscanner) {
        this.hsbactphukhoaSotoctscanner = hsbactphukhoaSotoctscanner;
    }

    public Integer getHsbactphukhoaSotosieuam() {
        return hsbactphukhoaSotosieuam;
    }

    public void setHsbactphukhoaSotosieuam(Integer hsbactphukhoaSotosieuam) {
        this.hsbactphukhoaSotosieuam = hsbactphukhoaSotosieuam;
    }

    public Integer getHsbactphukhoaSotoxn() {
        return hsbactphukhoaSotoxn;
    }

    public void setHsbactphukhoaSotoxn(Integer hsbactphukhoaSotoxn) {
        this.hsbactphukhoaSotoxn = hsbactphukhoaSotoxn;
    }

    public Integer getHsbactphukhoaSotokhac() {
        return hsbactphukhoaSotokhac;
    }

    public void setHsbactphukhoaSotokhac(Integer hsbactphukhoaSotokhac) {
        this.hsbactphukhoaSotokhac = hsbactphukhoaSotokhac;
    }

    public String getHsbactphukhoaSotoloaikhac() {
        return hsbactphukhoaSotoloaikhac;
    }

    public void setHsbactphukhoaSotoloaikhac(String hsbactphukhoaSotoloaikhac) {
        this.hsbactphukhoaSotoloaikhac = hsbactphukhoaSotoloaikhac;
    }

    public Integer getHsbactphukhoaTongsoto() {
        return hsbactphukhoaTongsoto;
    }

    public void setHsbactphukhoaTongsoto(Integer hsbactphukhoaTongsoto) {
        this.hsbactphukhoaTongsoto = hsbactphukhoaTongsoto;
    }

    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    public DtDmNhanVien getHsbactphukhoaBslamba() {
        return hsbactphukhoaBslamba;
    }

    public void setHsbactphukhoaBslamba(DtDmNhanVien hsbactphukhoaBslamba) {
        this.hsbactphukhoaBslamba = hsbactphukhoaBslamba;
    }

    public DtDmNhanVien getHsbactphukhoaNguoigiaoba() {
        return hsbactphukhoaNguoigiaoba;
    }

    public void setHsbactphukhoaNguoigiaoba(DtDmNhanVien hsbactphukhoaNguoigiaoba) {
        this.hsbactphukhoaNguoigiaoba = hsbactphukhoaNguoigiaoba;
    }

    public DtDmNhanVien getHsbactphukhoaNguoinhanba() {
        return hsbactphukhoaNguoinhanba;
    }

    public void setHsbactphukhoaNguoinhanba(DtDmNhanVien hsbactphukhoaNguoinhanba) {
        this.hsbactphukhoaNguoinhanba = hsbactphukhoaNguoinhanba;
    }

    public DtDmNhanVien getHsbactphukhoaBsdieutri() {
        return hsbactphukhoaBsdieutri;
    }

    public void setHsbactphukhoaBsdieutri(DtDmNhanVien hsbactphukhoaBsdieutri) {
        this.hsbactphukhoaBsdieutri = hsbactphukhoaBsdieutri;
    }

    public DtDmNhanVien getHsbactphukhoaBslamba(boolean create) {
        if (create && hsbactphukhoaBslamba == null) {
            hsbactphukhoaBslamba = new DtDmNhanVien();
        }
        return hsbactphukhoaBslamba;
    }

    public DtDmNhanVien getHsbactphukhoaNguoigiaoba(boolean create) {
        if (create && hsbactphukhoaNguoigiaoba == null) {
            hsbactphukhoaNguoigiaoba = new DtDmNhanVien();
        }
        return hsbactphukhoaNguoigiaoba;
    }

    public DtDmNhanVien getHsbactphukhoaNguoinhanba(boolean create) {
        if (create && hsbactphukhoaNguoinhanba == null) {
            hsbactphukhoaNguoinhanba = new DtDmNhanVien();
        }
        return hsbactphukhoaNguoinhanba;
    }

    public DtDmNhanVien getHsbactphukhoaBsdieutri(boolean create) {
        if (create && hsbactphukhoaBsdieutri == null) {
            hsbactphukhoaBsdieutri = new DtDmNhanVien();
        }
        return hsbactphukhoaBsdieutri;
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
        hash += (hsbactphukhoaMa != null ? hsbactphukhoaMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietPhukhoa)) {
            return false;
        }
        HsbaChiTietPhukhoa other = (HsbaChiTietPhukhoa) object;
        if ((this.hsbactphukhoaMa == null && other.hsbactphukhoaMa != null) || (this.hsbactphukhoaMa != null && !this.hsbactphukhoaMa.equals(other.hsbactphukhoaMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.test.HsbaChiTietPhukhoa[hsbactphukhoaMa=" + hsbactphukhoaMa + "]";
    }

}
