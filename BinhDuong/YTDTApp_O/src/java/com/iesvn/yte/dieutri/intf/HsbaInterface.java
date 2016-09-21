/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaNop;
import com.iesvn.yte.dieutri.entity.ThuocNoiTruXuatVien;
import com.iesvn.yte.dieutri.entity.TmpDataBhyt;
import com.iesvn.yte.entity.DmTang;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsbaInterface {

    public void create(Hsba hsba);

    public void edit(Hsba hsba);

    public void remove(Hsba hsba);

    public Hsba find(Object id);

    public List<Hsba> findAll();

    public List<Hsba> findByBenhNhan(java.lang.String maBenhNhan);

    public Hsba findByBenhNhanLastHsba(java.lang.String maBenhNhan);

    public Hsba findByTiepDonMa(java.lang.String maTiepDon);

    public String capNhatThongTinNhapVien(Hsba hsba, HsbaBhyt hsbaBhyt, BenhNhan benhNhan, DmTang tangChuyenDen);

    public String capNhatThongTinHanhChinhBN(Hsba hsba, HsbaBhyt hsbaBhyt, BenhNhan benhNhan);

    public String capNhatThongTinHSBA(Hsba hsba, HsbaNop hsbaNop, HsbaChuyenVien hsbaCV);

    public Hsba findByHsbaAndKhoaDangDieuTri(String sovaovien, String makhoa);

    public List<Hsba> findKhoaDangDieuTri(String makhoa);

    public List<Hsba> findBySoVaoVienHoTenNgayGioVaoVien(String soVaoVien, String hoTen, Date tuNgay, Date denNgay);

    public List<TmpDataBhyt> exportDataNoiTru(Date fromDate, Date toDate, boolean xoaDulieuTam, String congSoNgayDieuTri);

    public List<Hsba> findBySoTheBHYT(String sothe);

    public boolean deleteHsba(String hsbaSovaovien);

    public boolean deleteHsbacmCuoi(String hsbaSovaovien);

    public boolean deleteHsbarv(String hsbaSovaovien);

    public List<ThuocNoiTruXuatVien> findTntXuatVienBySoBenhAn(String sovaovien);

    public Hsba findByThongTinBenhNhan(String hoten, Integer gtMaso, String namsinh, String gio, String ngay);
    
}


