/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user01
 */
@Entity 
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "TMP_DATA_BHYT")
@NamedQueries({
    @NamedQuery(name = "TmpDataBhyt.findAll", query = "SELECT t FROM TmpDataBhyt t")})
public class TmpDataBhyt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "TIEP_DON")
    private String tiepDon;
    @Column(name = "HO_TEN")
    private String hoTen;
    @Column(name = "NAM_SINH")
    private Integer namSinh;
    @Column(name = "GIOI_TINH")
    private Short gioiTinh;
    @Column(name = "MA_THE")
    private String maThe;
    @Column(name = "MA_DKBD")
    private String maDkbd;
    @Column(name = "MA_BENH")
    private String maBenh;
    @Column(name = "NGAY_VAO")
    private String ngayVao;
    @Column(name = "NGAY_RA")
    private String ngayRa;
    @Column(name = "NGAY_DTR")
    private Integer ngayDtr;
    @Column(name = "TIEN_XETNGHIEM")
    private Double tienXetnghiem;
    @Column(name = "TIEN_CDHA")
    private Double tienCdha;
    @Column(name = "TIEN_THUOC")
    private Double tienThuoc;
    @Column(name = "TIEN_MAU")
    private Double tienMau;
    @Column(name = "TIEN_PTTT")
    private Double tienPttt;
    @Column(name = "TIEN_VTYTTH")
    private Double tienVtytth;
    @Column(name = "TIEN_DVKTC")
    private Double tienDvktc;
    @Column(name = "TIEN_KTG")
    private Double tienKtg;
    @Column(name = "TIEN_KHAM")
    private Double tienKham;
    @Column(name = "TIEN_VANCHUYEN")
    private Double tienVanchuyen;
    @Column(name = "TONG_CHI")
    private Double tongChi;
    @Column(name = "TIEN_BNTRA")
    private Double tienBntra;
    @Column(name = "TIEN_BHXH")
    private Double tienBhxh;
    @Column(name = "TIEN_NGOAIDS")
    private Double tienNgoaids;
    @Column(name = "LYDO_VV")
    private Integer lydoVv;
    @Column(name = "BENH_KHAC")
    private String benhKhac;
    @Column(name = "NOI_KCB")
    private String noiKcb;
    @Column(name = "THANG_QT")
    private Integer thangQt;
    @Column(name = "NAM_QT")
    private Integer namQt;
    @Column(name = "GT_TU")
    private String gtTu;
    @Column(name = "GT_DEN")
    private String gtDen;
    @Column(name = "DIA_CHI")
    private String diaChi;
    @Column(name = "GIAM_DINH")
    private String giamDinh;
    @Column(name = "XUAT_TOAN")
    private String xuatToan;
    @Column(name = "LYDO_XUATTOAN")
    private String lydoXuattoan;
    @Column(name = "DA_TUYEN")
    private Integer daTuyen;
    @Column(name = "VUOT_TRAN")
    private Integer vuotTran;
    @Column(name = "LOAI_KCB")
    private String loaiKcb;
    @Column(name = "NOI_THANHTOAN")
    private String noiThanhtoan;
    @Column(name = "SO_PHIEU")
    private String soPhieu;
    @Column(name = "MA_KHOA")
    private Integer maKhoa;
    @Column(name = "TUYEN")
    private String tuyen;

    public TmpDataBhyt() {
    }

    public TmpDataBhyt(String tiepDon) {
        this.tiepDon = tiepDon;
    }

    public TmpDataBhyt(String tiepDon, String hoTen, String maThe, String maDkbd, String diaChi, String giamDinh, String xuatToan, String lydoXuattoan, Integer daTuyen, Integer vuotTran, String loaiKcb, String noiThanhtoan, String soPhieu) {
        this.tiepDon = tiepDon;
        this.hoTen = hoTen;
        this.maThe = maThe;
        this.maDkbd = maDkbd;
        this.diaChi = diaChi;
        this.giamDinh = giamDinh;
        this.xuatToan = xuatToan;
        this.lydoXuattoan = lydoXuattoan;
        this.daTuyen = daTuyen;
        this.vuotTran = vuotTran;
        this.loaiKcb = loaiKcb;
        this.noiThanhtoan = noiThanhtoan;
        this.soPhieu = soPhieu;
    }

    public String getTiepDon() {
        return tiepDon;
    }

    public void setTiepDon(String tiepDon) {
        this.tiepDon = tiepDon;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Integer getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Integer namSinh) {
        this.namSinh = namSinh;
    }

    public Short getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Short gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getMaDkbd() {
        return maDkbd;
    }

    public void setMaDkbd(String maDkbd) {
        this.maDkbd = maDkbd;
    }

    public String getMaBenh() {
        return maBenh;
    }

    public void setMaBenh(String maBenh) {
        this.maBenh = maBenh;
    }

    public String getNgayVao() {
        return ngayVao;
    }

    public void setNgayVao(String ngayVao) {
        this.ngayVao = ngayVao;
    }

    public String getNgayRa() {
        return ngayRa;
    }

    public void setNgayRa(String ngayRa) {
        this.ngayRa = ngayRa;
    }

    public Integer getNgayDtr() {
        return ngayDtr;
    }

    public void setNgayDtr(Integer ngayDtr) {
        this.ngayDtr = ngayDtr;
    }

    public Double getTienXetnghiem() {
        return tienXetnghiem;
    }

    public void setTienXetnghiem(Double tienXetnghiem) {
        this.tienXetnghiem = tienXetnghiem;
    }

    public Double getTienCdha() {
        return tienCdha;
    }

    public void setTienCdha(Double tienCdha) {
        this.tienCdha = tienCdha;
    }

    public Double getTienThuoc() {
        return tienThuoc;
    }

    public void setTienThuoc(Double tienThuoc) {
        this.tienThuoc = tienThuoc;
    }

    public Double getTienMau() {
        return tienMau;
    }

    public void setTienMau(Double tienMau) {
        this.tienMau = tienMau;
    }

    public Double getTienPttt() {
        return tienPttt;
    }

    public void setTienPttt(Double tienPttt) {
        this.tienPttt = tienPttt;
    }

    public Double getTienVtytth() {
        return tienVtytth;
    }

    public void setTienVtytth(Double tienVtytth) {
        this.tienVtytth = tienVtytth;
    }

    public Double getTienDvktc() {
        return tienDvktc;
    }

    public void setTienDvktc(Double tienDvktc) {
        this.tienDvktc = tienDvktc;
    }

    public Double getTienKtg() {
        return tienKtg;
    }

    public void setTienKtg(Double tienKtg) {
        this.tienKtg = tienKtg;
    }

    public Double getTienKham() {
        return tienKham;
    }

    public void setTienKham(Double tienKham) {
        this.tienKham = tienKham;
    }

    public Double getTienVanchuyen() {
        return tienVanchuyen;
    }

    public void setTienVanchuyen(Double tienVanchuyen) {
        this.tienVanchuyen = tienVanchuyen;
    }

    public Double getTongChi() {
        return tongChi;
    }

    public void setTongChi(Double tongChi) {
        this.tongChi = tongChi;
    }

    public Double getTienBntra() {
        return tienBntra;
    }

    public void setTienBntra(Double tienBntra) {
        this.tienBntra = tienBntra;
    }

    public Double getTienBhxh() {
        return tienBhxh;
    }

    public void setTienBhxh(Double tienBhxh) {
        this.tienBhxh = tienBhxh;
    }

    public Double getTienNgoaids() {
        return tienNgoaids;
    }

    public void setTienNgoaids(Double tienNgoaids) {
        this.tienNgoaids = tienNgoaids;
    }

    public Integer getLydoVv() {
        return lydoVv;
    }

    public void setLydoVv(Integer lydoVv) {
        this.lydoVv = lydoVv;
    }

    public String getBenhKhac() {
        return benhKhac;
    }

    public void setBenhKhac(String benhKhac) {
        this.benhKhac = benhKhac;
    }

    public String getNoiKcb() {
        return noiKcb;
    }

    public void setNoiKcb(String noiKcb) {
        this.noiKcb = noiKcb;
    }

    public Integer getThangQt() {
        return thangQt;
    }

    public void setThangQt(Integer thangQt) {
        this.thangQt = thangQt;
    }

    public Integer getNamQt() {
        return namQt;
    }

    public void setNamQt(Integer namQt) {
        this.namQt = namQt;
    }

    public String getGtTu() {
        return gtTu;
    }

    public void setGtTu(String gtTu) {
        this.gtTu = gtTu;
    }

    public String getGtDen() {
        return gtDen;
    }

    public void setGtDen(String gtDen) {
        this.gtDen = gtDen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGiamDinh() {
        return giamDinh;
    }

    public void setGiamDinh(String giamDinh) {
        this.giamDinh = giamDinh;
    }

    public String getXuatToan() {
        return xuatToan;
    }

    public void setXuatToan(String xuatToan) {
        this.xuatToan = xuatToan;
    }

    public String getLydoXuattoan() {
        return lydoXuattoan;
    }

    public void setLydoXuattoan(String lydoXuattoan) {
        this.lydoXuattoan = lydoXuattoan;
    }

    public Integer getDaTuyen() {
        return daTuyen;
    }

    public void setDaTuyen(Integer daTuyen) {
        this.daTuyen = daTuyen;
    }

    public Integer getVuotTran() {
        return vuotTran;
    }

    public void setVuotTran(Integer vuotTran) {
        this.vuotTran = vuotTran;
    }

    public String getLoaiKcb() {
        return loaiKcb;
    }

    public void setLoaiKcb(String loaiKcb) {
        this.loaiKcb = loaiKcb;
    }

    public String getNoiThanhtoan() {
        return noiThanhtoan;
    }

    public void setNoiThanhtoan(String noiThanhtoan) {
        this.noiThanhtoan = noiThanhtoan;
    }

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public Integer getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(Integer maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTuyen() {
        return tuyen;
    }

    public void setTuyen(String tuyen) {
        this.tuyen = tuyen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tiepDon != null ? tiepDon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpDataBhyt)) {
            return false;
        }
        TmpDataBhyt other = (TmpDataBhyt) object;
        if ((this.tiepDon == null && other.tiepDon != null) || (this.tiepDon != null && !this.tiepDon.equals(other.tiepDon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.TmpDataBhyt[tiepDon=" + tiepDon + "]";
    }

}
