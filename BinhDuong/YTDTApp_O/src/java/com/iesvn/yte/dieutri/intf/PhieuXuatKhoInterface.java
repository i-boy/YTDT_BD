/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtXuatKho;
import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import java.util.List;
import java.util.Date;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface PhieuXuatKhoInterface {

    public void create(PhieuXuatKho phieuXuatKho);

    public void edit(PhieuXuatKho phieuXuatKho);

    public void remove(PhieuXuatKho phieuXuatKho);

    public PhieuXuatKho find(Object id);

    public List<PhieuXuatKho> findAll();

    public List<PhieuXuatKho> find(String pxkMa, Date ngayNhap, Integer loaiPhieuMaSo, Integer maNguoiLap, Integer maNguoiNhap, Integer maKhoNhan, Integer maKhoXuat);

    public com.iesvn.yte.dieutri.entity.PhieuXuatKho findByPhieuxuatkhoMa(java.lang.String maPhieu);

    public String createPhieuXuat(PhieuXuatKho pxk, List<CtXuatKho> listCtXuatKho, List<TonKho> listTkNhan, List<TonKho> listTkXuat);

    public java.lang.String XuatPhieuDuTru(java.util.List<com.iesvn.yte.dieutri.entity.CtXuatKho> listCTX, com.iesvn.yte.dieutri.entity.PhieuXuatKho pxk, java.lang.String priority) throws java.lang.Exception;

    public java.lang.String XuatPhieuDuTruTuTruc(List<CtXuatKho> listCTX, PhieuXuatKho pxk, String priority) throws java.lang.Exception;

    public PhieuXuatKho findByPhieuDuTru(String maPhieuDT);

    public PhieuXuatKho findByPhieuDuTruAndKhoXuat(String maPhieuDT, Integer dmKhoXuatMaso);

    public boolean daXuatThuocTheoPhieuDT(String maPhieuDuTru);

    public String upDatePhieuXuat(PhieuXuatKho pxk, List<CtXuatKho> listCtXuatKho);

    public PhieuXuatKho findPhieuXuatKhoByKhoXuat(String maPhieu, Integer maKhoa);

    public PhieuXuatKho findPhieuXuatKhoByKhoaNhan(String maPhieu, Integer maKhoa);

    public String thucHienPhieuXuat(PhieuXuatKho pxk, List<CtXuatKho> listCtXuatKho, List<TonKho> listTkNhan, List<TonKho> listTkXuat, String priority) ;
}


