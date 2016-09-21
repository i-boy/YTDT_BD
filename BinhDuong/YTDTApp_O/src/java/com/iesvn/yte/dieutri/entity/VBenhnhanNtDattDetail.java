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
@Table(name = "V_BENHNHAN_NT_DATT_DETAIL")
@NamedQueries({
    @NamedQuery(name = "VBenhnhanNtDattDetail.findAll", query = "SELECT v FROM VBenhnhanNtDattDetail v")})
public class VBenhnhanNtDattDetail implements Serializable {
    private static final long serialVersionUID = 1L;
   @Id
    @Column(name = "benhan", nullable = false)
    private String benhan;
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
    @Column(name = "ma_benh")
    private String maBenh;
    @Column(name = "ngay_vao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayVao;
    @Column(name = "ngay_ra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayRa;
    @Column(name = "ten_benh")
    private String tenBenh;
    @Column(name = "noi_kham")
    private String noiKham;
    @Column(name = "ngay_tt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTt;
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
    @Column(name = "vanchuyen")
    private Integer vanchuyen;
    @Column(name = "loai_tt")
    private String loaiTt;
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
    @Column(name = "GIAY_CV")
    private Short giayCv;
    @Column(name = "noi_den")
    private String noiDen;
    @Column(name = "ma_bhxh")
    private String maBhxh;
    @Column(name = "BENH_PHU")
    private String benhPhu;

    public VBenhnhanNtDattDetail() {
    }

    public String getBenhan() {
        return benhan;
    }

    public void setBenhan(String benhan) {
        this.benhan = benhan;
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

    public String getMaBenh() {
        return maBenh;
    }

    public void setMaBenh(String maBenh) {
        this.maBenh = maBenh;
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

    public String getTenBenh() {
        return tenBenh;
    }

    public void setTenBenh(String tenBenh) {
        this.tenBenh = tenBenh;
    }

    public String getNoiKham() {
        return noiKham;
    }

    public void setNoiKham(String noiKham) {
        this.noiKham = noiKham;
    }

    public Date getNgayTt() {
        return ngayTt;
    }

    public void setNgayTt(Date ngayTt) {
        this.ngayTt = ngayTt;
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

    public Integer getVanchuyen() {
        return vanchuyen;
    }

    public void setVanchuyen(Integer vanchuyen) {
        this.vanchuyen = vanchuyen;
    }

    public String getLoaiTt() {
        return loaiTt;
    }

    public void setLoaiTt(String loaiTt) {
        this.loaiTt = loaiTt;
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

    public Short getGiayCv() {
        return giayCv;
    }

    public void setGiayCv(Short giayCv) {
        this.giayCv = giayCv;
    }

    public String getNoiDen() {
        return noiDen;
    }

    public void setNoiDen(String noiDen) {
        this.noiDen = noiDen;
    }

    public String getMaBhxh() {
        return maBhxh;
    }

    public void setMaBhxh(String maBhxh) {
        this.maBhxh = maBhxh;
    }

    public String getBenhPhu() {
        return benhPhu;
    }

    public void setBenhPhu(String benhPhu) {
        this.benhPhu = benhPhu;
    }

}
