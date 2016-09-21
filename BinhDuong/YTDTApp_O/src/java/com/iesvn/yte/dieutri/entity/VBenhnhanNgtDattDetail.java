/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;
/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "V_BENHNHAN_NGT_DATT_DETAIL")
@NamedQueries({
    @NamedQuery(name = "VBenhnhanNgtDattDetail.findAll", query = "SELECT v FROM VBenhnhanNgtDattDetail v")})
public class VBenhnhanNgtDattDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "TIEPDON_MA", nullable = false)
    private String tiepdonMa;
    @Column(name = "NGAYGIOTT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaygiott;
    @Column(name = "ho_ten")
    private String hoTen;
    @Column(name = "nam_sinh")
    private String namSinh;
    @Column(name = "gioi_tinh")
    private String gioiTinh;
    @Column(name = "mathe")
    private String mathe;
    @Column(name = "tinh_kcb")
    private String tinhKcb;
    @Column(name = "ma_kcb")
    private String maKcb;
    @Column(name = "mabenh")
    private String mabenh;
    @Column(name = "ngay_vao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayVao;
    @Column(name = "ngay_ra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayRa;
    @Column(name = "ngaydtr")
    private int ngaydtr;
    @Column(name = "benhkhac")
    private String benhkhac;
    @Column(name = "noikcb")
    private String noikcb;
    @Column(name = "thangqt")
    private Integer thangqt;
    @Column(name = "namqt")
    private Integer namqt;
    @Column(name = "gtri_tu")
    @Temporal(TemporalType.DATE)
    private Date gtriTu;
    @Column(name = "gtri_den")
    @Temporal(TemporalType.DATE)
    private Date gtriDen;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "loaikcb")
    private String loaikcb;
    @Column(name = "noi_ttoan")
    private String noiTtoan;
    @Column(name = "sophieu")
    private String sophieu;
    @Column(name = "ma_khoa")
    private Integer maKhoa;
    @Column(name = "ma_dt")
    private String maDt;
    @Column(name = "loai_tt")
    private String loaiTt;
    @Column(name = "vanchuyen")
    private Integer vanchuyen;
    @Column(name = "ngay_g1")
    @Temporal(TemporalType.DATE)
    private Date ngayG1;
    @Column(name = "ngay_g2")
    @Temporal(TemporalType.DATE)
    private Date ngayG2;
    @Column(name = "ngay_g3")
    @Temporal(TemporalType.DATE)
    private Date ngayG3;
    @Column(name = "dieu_tri")
    private Short dieuTri;
    @Column(name = "giay_gt")
    private Boolean giayGt;
    @Column(name = "ma_bhxh")
    private String maBhxh;
    @Column(name = "noi_den")
    private String noiDen;
    @Column(name = "BENH_PHU")
    private String benhPhu;

    public VBenhnhanNgtDattDetail() {
    }

    public String getTiepdonMa() {
        return tiepdonMa;
    }

    public void setTiepdonMa(String tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

    public Date getNgaygiott() {
        return ngaygiott;
    }

    public void setNgaygiott(Date ngaygiott) {
        this.ngaygiott = ngaygiott;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMathe() {
        return mathe;
    }

    public void setMathe(String mathe) {
        this.mathe = mathe;
    }

    public String getTinhKcb() {
        return tinhKcb;
    }

    public void setTinhKcb(String tinhKcb) {
        this.tinhKcb = tinhKcb;
    }

    public String getMaKcb() {
        return maKcb;
    }

    public void setMaKcb(String maKcb) {
        this.maKcb = maKcb;
    }

    public String getMabenh() {
        return mabenh;
    }

    public void setMabenh(String mabenh) {
        this.mabenh = mabenh;
    }

    public Date getNgayVao() {
        return ngayVao;
    }

    public void setNgayVao(Date ngayVao) {
        this.ngayVao = ngayVao;
    }

    public Date getNgayRa() {
        return ngayRa;
    }

    public void setNgayRa(Date ngayRa) {
        this.ngayRa = ngayRa;
    }

    public int getNgaydtr() {
        return ngaydtr;
    }

    public void setNgaydtr(int ngaydtr) {
        this.ngaydtr = ngaydtr;
    }

    public String getBenhkhac() {
        return benhkhac;
    }

    public void setBenhkhac(String benhkhac) {
        this.benhkhac = benhkhac;
    }

    public String getNoikcb() {
        return noikcb;
    }

    public void setNoikcb(String noikcb) {
        this.noikcb = noikcb;
    }

    public Integer getThangqt() {
        return thangqt;
    }

    public void setThangqt(Integer thangqt) {
        this.thangqt = thangqt;
    }

    public Integer getNamqt() {
        return namqt;
    }

    public void setNamqt(Integer namqt) {
        this.namqt = namqt;
    }

    public Date getGtriTu() {
        return gtriTu;
    }

    public void setGtriTu(Date gtriTu) {
        this.gtriTu = gtriTu;
    }

    public Date getGtriDen() {
        return gtriDen;
    }

    public void setGtriDen(Date gtriDen) {
        this.gtriDen = gtriDen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getLoaikcb() {
        return loaikcb;
    }

    public void setLoaikcb(String loaikcb) {
        this.loaikcb = loaikcb;
    }

    public String getNoiTtoan() {
        return noiTtoan;
    }

    public void setNoiTtoan(String noiTtoan) {
        this.noiTtoan = noiTtoan;
    }

    public String getSophieu() {
        return sophieu;
    }

    public void setSophieu(String sophieu) {
        this.sophieu = sophieu;
    }

    public Integer getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(Integer maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getMaDt() {
        return maDt;
    }

    public void setMaDt(String maDt) {
        this.maDt = maDt;
    }

    public String getLoaiTt() {
        return loaiTt;
    }

    public void setLoaiTt(String loaiTt) {
        this.loaiTt = loaiTt;
    }

    public Integer getVanchuyen() {
        return vanchuyen;
    }

    public void setVanchuyen(Integer vanchuyen) {
        this.vanchuyen = vanchuyen;
    }

    public Date getNgayG1() {
        return ngayG1;
    }

    public void setNgayG1(Date ngayG1) {
        this.ngayG1 = ngayG1;
    }

    public Date getNgayG2() {
        return ngayG2;
    }

    public void setNgayG2(Date ngayG2) {
        this.ngayG2 = ngayG2;
    }

    public Date getNgayG3() {
        return ngayG3;
    }

    public void setNgayG3(Date ngayG3) {
        this.ngayG3 = ngayG3;
    }

    public Short getDieuTri() {
        return dieuTri;
    }

    public void setDieuTri(Short dieuTri) {
        this.dieuTri = dieuTri;
    }

    public Boolean getGiayGt() {
        return giayGt;
    }

    public void setGiayGt(Boolean giayGt) {
        this.giayGt = giayGt;
    }

    public String getMaBhxh() {
        return maBhxh;
    }

    public void setMaBhxh(String maBhxh) {
        this.maBhxh = maBhxh;
    }

    public String getNoiDen() {
        return noiDen;
    }

    public void setNoiDen(String noiDen) {
        this.noiDen = noiDen;
    }

    public String getBenhPhu() {
        return benhPhu;
    }

    public void setBenhPhu(String benhPhu) {
        this.benhPhu = benhPhu;
    }

}
