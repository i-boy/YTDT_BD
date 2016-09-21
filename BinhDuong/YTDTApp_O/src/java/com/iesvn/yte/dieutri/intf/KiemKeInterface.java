/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.KiemKe;
import com.iesvn.yte.dieutri.entity.KiemKeKho;
import com.iesvn.yte.dieutri.entity.KiemKePhanLoaiThuoc;
import java.util.List;
import java.util.Date;

import com.iesvn.yte.entity.DmPhanLoaiThuoc;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface KiemKeInterface {
    public     void create(KiemKe kiemKeKho);
    public     void edit(KiemKe kiemKeKho);
    public     void remove(KiemKe kiemKeKho);
    public     KiemKe find(Object id);
    public     List<KiemKe> findAll();
    public KiemKe findByKho(String maKho);
    public String getMaPhieu();
    public List<KiemKe> findByMaPhieuKiem(String maPhieuKiem);
    public List<KiemKe> findByDieuKienTimKiem(String maPhieuKiem, Date ngayKiemKeTu, Date ngayKiemKeDen, Integer dmKhoaMaso, Integer dmLoaiThuocMaso, List<DmPhanLoaiThuoc> lstDmPLThuoc, String trangthai);
    public List<KiemKe> findByKiemKeJob(int thoihandongKiemKe);
    public String getListPhanLoaiThuocMa(String maphieukiem);
    public List<KiemKePhanLoaiThuoc> getListKiemKePhanLoaiThuoc(String maphieukiem);
    public String taoBangKiemKe(KiemKe kiemke, String listPL, List<DmPhanLoaiThuoc> listDmPLThuocKK, String lthuoc, boolean chon) throws Exception;
    public String hoantatKiemKe(KiemKe kiemke);
    public String huyKiemKe(KiemKe kiemke);
    public String capnhatSoLieuKiemKe(KiemKe kiemke, List<KiemKeKho> lstKiemKeKho);
    public boolean dangKiemKe(String malienket, Integer khoaMaso);
    public boolean isExistedKiemKe(String loaiThuocMa, Integer khoaMaso);
}


