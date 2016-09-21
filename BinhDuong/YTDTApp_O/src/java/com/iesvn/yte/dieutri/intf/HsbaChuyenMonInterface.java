/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsbaChuyenMonInterface {

public     void create(HsbaChuyenMon hsbaChuyenMon);

public     void edit(HsbaChuyenMon hsbaChuyenMon);

public     void remove(HsbaChuyenMon hsbaChuyenMon);

public     HsbaChuyenMon find(Object id);

public     List<HsbaChuyenMon> findAll();

    public com.iesvn.yte.dieutri.entity.HsbaChuyenMon findBySoVaoVien_MaKhoa(java.lang.String soVaoVien, java.lang.String maKhoa);

    public java.util.List<com.iesvn.yte.dieutri.entity.HsbaChuyenMon> findByMaKhoa_ChuaCapNhat(java.lang.String maKhoa);

    public java.util.List<com.iesvn.yte.dieutri.entity.HsbaChuyenMon> findBySoVaoVien(java.lang.String soVaoVien);

    public java.util.List<com.iesvn.yte.dieutri.entity.HsbaChuyenMon> findByMaLoaiKhoaTuNgayAndDenNgay(java.lang.String maLoaiKhoa, java.util.Date tuNgay, java.util.Date denNgay);

    public int findTonDauKy(java.lang.String maLoaiKhoa, java.util.Date tuNgay);

    public java.lang.String capNhatHoSoBenhAn(com.iesvn.yte.dieutri.entity.Hsba hsba, com.iesvn.yte.dieutri.entity.HsbaChuyenMon hsbaCM, com.iesvn.yte.dieutri.entity.HsbaChuyenVien hsbaCV, com.iesvn.yte.dieutri.entity.HsbaNop hsbaNop, com.iesvn.yte.dieutri.entity.BenhNhan benhNhan);

    public com.iesvn.yte.dieutri.entity.HsbaChuyenMon findByTiepDonMa(java.lang.String maTiepDon);
    public HsbaChuyenMon findBySoVaoVien_LastHSBACM(String soVaoVien) ;
    public String xoaHSBAChuyenMon(String soVaoVien);
}


