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
 * @author quang
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_TOM_TAT_BENH_AN")
@NamedQueries({
    @NamedQuery(name = "HsbaTomTatBenhAn.findAll", query = "SELECT h FROM HsbaTomTatBenhAn h"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaMaso", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaMaso = :hsbattbaMaso"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaNgaygiocn", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaNgaygiocn = :hsbattbaNgaygiocn"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaMa", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaMa = :hsbattbaMa"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaDienbienlamsang", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaDienbienlamsang = :hsbattbaDienbienlamsang"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCacxetnghiem", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCacxetnghiem = :hsbattbaCacxetnghiem"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaKhamchuyenkhoa", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaKhamchuyenkhoa = :hsbattbaKhamchuyenkhoa"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaPpthBvlao", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaPpthBvlao = :hsbattbaPpthBvlao"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaRavienSongayduongsuc", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaRavienSongayduongsuc = :hsbattbaRavienSongayduongsuc"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaRavienLaodongnhe", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaRavienLaodongnhe = :hsbattbaRavienLaodongnhe"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaRavienBoiduong", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaRavienBoiduong = :hsbattbaRavienBoiduong"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCvTinhthan", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCvTinhthan = :hsbattbaCvTinhthan"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCvMach", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCvMach = :hsbattbaCvMach"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCvNhiet", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCvNhiet = :hsbattbaCvNhiet"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCvHa", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCvHa = :hsbattbaCvHa"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCvBangcodinh", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCvBangcodinh = :hsbattbaCvBangcodinh"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCvTruyendich", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCvTruyendich = :hsbattbaCvTruyendich"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCvMau", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCvMau = :hsbattbaCvMau"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCvNguoihotong", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCvNguoihotong = :hsbattbaCvNguoihotong"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCvDieutringoaiKhoa", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCvDieutringoaiKhoa = :hsbattbaCvDieutringoaiKhoa"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCvDieutringoaiKhu", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCvDieutringoaiKhu = :hsbattbaCvDieutringoaiKhu"),
    @NamedQuery(name = "HsbaTomTatBenhAn.findByHsbattbaCvDieutringoaiKham", query = "SELECT h FROM HsbaTomTatBenhAn h WHERE h.hsbattbaCvDieutringoaiKham = :hsbattbaCvDieutringoaiKham")})
public class HsbaTomTatBenhAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_TOM_TAT_BENH_AN")
    @SequenceGenerator(name = "HSBA_TOM_TAT_BENH_AN", sequenceName = "HSBA_TOM_TAT_BENH_AN_HSBATTBA_", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBATTBA_MASO")
    private Integer hsbattbaMaso;
    @Column(name = "HSBATTBA_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbattbaNgaygiocn;
    @Column(name = "HSBATTBA_NGAYCAP")
    @Temporal(TemporalType.DATE)
    private Date hsbattbaNgaycap;
    @Column(name = "HSBATTBA_MA")
    private String hsbattbaMa;
    @Column(name = "HSBATTBA_DIENBIENLAMSANG")
    private String hsbattbaDienbienlamsang;
    @Column(name = "HSBATTBA_CACXETNGHIEM")
    private String hsbattbaCacxetnghiem;
    @Column(name = "HSBATTBA_KHAMCHUYENKHOA")
    private String hsbattbaKhamchuyenkhoa;
    @Column(name = "HSBATTBA_PPTH_BVLAO")
    private String hsbattbaPpthBvlao;
    @Column(name = "HSBATTBA_RAVIEN_SONGAYDUONGSUC")
    private String hsbattbaRavienSongayduongsuc;
    @Column(name = "HSBATTBA_RAVIEN_LAODONGNHE")
    private String hsbattbaRavienLaodongnhe;
    @Column(name = "HSBATTBA_RAVIEN_BOIDUONG")
    private String hsbattbaRavienBoiduong;
    @Column(name = "HSBATTBA_CV_TINHTHAN")
    private String hsbattbaCvTinhthan;
    @Column(name = "HSBATTBA_CV_MACH")
    private Double hsbattbaCvMach;
    @Column(name = "HSBATTBA_CV_NHIET")
    private Double hsbattbaCvNhiet;
    @Column(name = "HSBATTBA_CV_HA")
    private Double hsbattbaCvHa;
    @Column(name = "HSBATTBA_CV_BANGCODINH")
    private String hsbattbaCvBangcodinh;
    @Column(name = "HSBATTBA_CV_TRUYENDICH")
    private String hsbattbaCvTruyendich;
    @Column(name = "HSBATTBA_CV_MAU")
    private String hsbattbaCvMau;
    @Column(name = "HSBATTBA_CV_NGUOIHOTONG")
    private String hsbattbaCvNguoihotong;
    @Column(name = "HSBATTBA_CV_DIEUTRINGOAI_KHOA")
    private String hsbattbaCvDieutringoaiKhoa;
    @Column(name = "HSBATTBA_CV_DIEUTRINGOAI_KHU")
    private String hsbattbaCvDieutringoaiKhu;
    @Column(name = "HSBATTBA_CV_DIEUTRINGOAI_KHAM")
    private String hsbattbaCvDieutringoaiKham;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "HSBATTBA_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbattbaBacsi;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private Hsba hsbaSovaovien;

    public HsbaTomTatBenhAn() {
    }

    public HsbaTomTatBenhAn(Integer hsbattbaMaso) {
        this.hsbattbaMaso = hsbattbaMaso;
    }

    public Integer getHsbattbaMaso() {
        return hsbattbaMaso;
    }

    public void setHsbattbaMaso(Integer hsbattbaMaso) {
        this.hsbattbaMaso = hsbattbaMaso;
    }

    public Date getHsbattbaNgaygiocn() {
        return hsbattbaNgaygiocn;
    }

    public void setHsbattbaNgaygiocn(Date hsbattbaNgaygiocn) {
        this.hsbattbaNgaygiocn = hsbattbaNgaygiocn;
    }

    public String getHsbattbaMa() {
        return hsbattbaMa;
    }

    public void setHsbattbaMa(String hsbattbaMa) {
        this.hsbattbaMa = hsbattbaMa;
    }

    public String getHsbattbaDienbienlamsang() {
        return hsbattbaDienbienlamsang;
    }

    public void setHsbattbaDienbienlamsang(String hsbattbaDienbienlamsang) {
        this.hsbattbaDienbienlamsang = hsbattbaDienbienlamsang;
    }

    public String getHsbattbaCacxetnghiem() {
        return hsbattbaCacxetnghiem;
    }

    public void setHsbattbaCacxetnghiem(String hsbattbaCacxetnghiem) {
        this.hsbattbaCacxetnghiem = hsbattbaCacxetnghiem;
    }

    public String getHsbattbaKhamchuyenkhoa() {
        return hsbattbaKhamchuyenkhoa;
    }

    public void setHsbattbaKhamchuyenkhoa(String hsbattbaKhamchuyenkhoa) {
        this.hsbattbaKhamchuyenkhoa = hsbattbaKhamchuyenkhoa;
    }

    public String getHsbattbaPpthBvlao() {
        return hsbattbaPpthBvlao;
    }

    public void setHsbattbaPpthBvlao(String hsbattbaPpthBvlao) {
        this.hsbattbaPpthBvlao = hsbattbaPpthBvlao;
    }

    public String getHsbattbaRavienSongayduongsuc() {
        return hsbattbaRavienSongayduongsuc;
    }

    public void setHsbattbaRavienSongayduongsuc(String hsbattbaRavienSongayduongsuc) {
        this.hsbattbaRavienSongayduongsuc = hsbattbaRavienSongayduongsuc;
    }

    public String getHsbattbaRavienLaodongnhe() {
        return hsbattbaRavienLaodongnhe;
    }

    public void setHsbattbaRavienLaodongnhe(String hsbattbaRavienLaodongnhe) {
        this.hsbattbaRavienLaodongnhe = hsbattbaRavienLaodongnhe;
    }

    public String getHsbattbaRavienBoiduong() {
        return hsbattbaRavienBoiduong;
    }

    public void setHsbattbaRavienBoiduong(String hsbattbaRavienBoiduong) {
        this.hsbattbaRavienBoiduong = hsbattbaRavienBoiduong;
    }

    public String getHsbattbaCvTinhthan() {
        return hsbattbaCvTinhthan;
    }

    public void setHsbattbaCvTinhthan(String hsbattbaCvTinhthan) {
        this.hsbattbaCvTinhthan = hsbattbaCvTinhthan;
    }

    public Double getHsbattbaCvMach() {
        return hsbattbaCvMach;
    }

    public void setHsbattbaCvMach(Double hsbattbaCvMach) {
        this.hsbattbaCvMach = hsbattbaCvMach;
    }

    public Double getHsbattbaCvNhiet() {
        return hsbattbaCvNhiet;
    }

    public void setHsbattbaCvNhiet(Double hsbattbaCvNhiet) {
        this.hsbattbaCvNhiet = hsbattbaCvNhiet;
    }

    public Double getHsbattbaCvHa() {
        return hsbattbaCvHa;
    }

    public void setHsbattbaCvHa(Double hsbattbaCvHa) {
        this.hsbattbaCvHa = hsbattbaCvHa;
    }

    public String getHsbattbaCvBangcodinh() {
        return hsbattbaCvBangcodinh;
    }

    public void setHsbattbaCvBangcodinh(String hsbattbaCvBangcodinh) {
        this.hsbattbaCvBangcodinh = hsbattbaCvBangcodinh;
    }

    public String getHsbattbaCvTruyendich() {
        return hsbattbaCvTruyendich;
    }

    public void setHsbattbaCvTruyendich(String hsbattbaCvTruyendich) {
        this.hsbattbaCvTruyendich = hsbattbaCvTruyendich;
    }

    public String getHsbattbaCvMau() {
        return hsbattbaCvMau;
    }

    public void setHsbattbaCvMau(String hsbattbaCvMau) {
        this.hsbattbaCvMau = hsbattbaCvMau;
    }

    public String getHsbattbaCvNguoihotong() {
        return hsbattbaCvNguoihotong;
    }

    public void setHsbattbaCvNguoihotong(String hsbattbaCvNguoihotong) {
        this.hsbattbaCvNguoihotong = hsbattbaCvNguoihotong;
    }

    public String getHsbattbaCvDieutringoaiKhoa() {
        return hsbattbaCvDieutringoaiKhoa;
    }

    public void setHsbattbaCvDieutringoaiKhoa(String hsbattbaCvDieutringoaiKhoa) {
        this.hsbattbaCvDieutringoaiKhoa = hsbattbaCvDieutringoaiKhoa;
    }

    public String getHsbattbaCvDieutringoaiKhu() {
        return hsbattbaCvDieutringoaiKhu;
    }

    public void setHsbattbaCvDieutringoaiKhu(String hsbattbaCvDieutringoaiKhu) {
        this.hsbattbaCvDieutringoaiKhu = hsbattbaCvDieutringoaiKhu;
    }

    public String getHsbattbaCvDieutringoaiKham() {
        return hsbattbaCvDieutringoaiKham;
    }

    public void setHsbattbaCvDieutringoaiKham(String hsbattbaCvDieutringoaiKham) {
        this.hsbattbaCvDieutringoaiKham = hsbattbaCvDieutringoaiKham;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public Hsba getHsbaSovaovien(boolean create) {
        if((hsbaSovaovien == null)&&(create)) hsbaSovaovien = new Hsba();
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DtDmNhanVien getHsbattbaBacsi() {
        return hsbattbaBacsi;
    }

    public DtDmNhanVien getHsbattbaBacsi(boolean create) {
        if(create && hsbattbaBacsi == null) hsbattbaBacsi = new DtDmNhanVien();
        return hsbattbaBacsi;
    }

    public void setHsbattbaBacsi(DtDmNhanVien hsbattbaBacsi) {
        this.hsbattbaBacsi = hsbattbaBacsi;
    }

    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public DtDmNhanVien getNhanvienMa(boolean create) {
        if(create && nhanvienMa ==null) nhanvienMa= new DtDmNhanVien();
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    public Date getHsbattbaNgaycap() {
        return hsbattbaNgaycap;
    }

    public void setHsbattbaNgaycap(Date hsbattbaNgaycap) {
        this.hsbattbaNgaycap = hsbattbaNgaycap;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbattbaMaso != null ? hsbattbaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaTomTatBenhAn)) {
            return false;
        }
        HsbaTomTatBenhAn other = (HsbaTomTatBenhAn) object;
        if ((this.hsbattbaMaso == null && other.hsbattbaMaso != null) || (this.hsbattbaMaso != null && !this.hsbattbaMaso.equals(other.hsbattbaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaTomTatBenhAn[hsbattbaMaso=" + hsbattbaMaso + "]";
    }

}
