/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.KiemKeInterface;

import com.iesvn.yte.dieutri.entity.KiemKe;
import com.iesvn.yte.dieutri.entity.KiemKePhanLoaiThuoc;
import com.iesvn.yte.dieutri.entity.KiemKeKho;
import java.util.List;
import java.util.Date;

import com.iesvn.yte.entity.DmPhanLoaiThuoc;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class KiemKeDelegate {

    private KiemKeInterface kiemkeService;

    public static KiemKeDelegate getInstance() {
        return new KiemKeDelegate();
    }

    private KiemKeInterface lookupService() {
        return (KiemKeInterface) LookupServiceUtils.lookupService("KiemKeFacade");
    }

    public void create(KiemKe kiemKe) {
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        kiemkeService.create(kiemKe);
    }

    public void edit(KiemKe kiemKe) {
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        kiemkeService.edit(kiemKe);
    }

    public void remove(KiemKe kiemKe) {
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        kiemkeService.remove(kiemKe);
    }

    public KiemKe find(Object id) {
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.find(id);
    }

    public List<KiemKe> findAll() {
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.findAll();
    }

    public KiemKe findByKho(String maKho) {
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.findByKho(maKho);
    }

    public String getMaPhieu() {
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.getMaPhieu();
    }
    
    public List<KiemKe> findByMaPhieuKiem(String maPhieuKiem) {
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.findByMaPhieuKiem(maPhieuKiem);
    }

    public List<KiemKe> findByDieuKienTimKiem(String maPhieuKiem, Date ngayKiemKeTu, Date ngayKiemKeDen, Integer dmKhoaMaso, Integer dmLoaiThuocMaso, List<DmPhanLoaiThuoc> lstDmPLThuoc, String trangthai){
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.findByDieuKienTimKiem(maPhieuKiem, ngayKiemKeTu, ngayKiemKeDen, dmKhoaMaso, dmLoaiThuocMaso, lstDmPLThuoc, trangthai);
    }

    public List<KiemKe> findByKiemKeJob(int thoihandongKiemKe){
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.findByKiemKeJob(thoihandongKiemKe);
    }

    public String getListPhanLoaiThuocMa(String maphieukiem){
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.getListPhanLoaiThuocMa(maphieukiem);
    }

    public List<KiemKePhanLoaiThuoc> getListKiemKePhanLoaiThuoc(String maphieukiem){
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.getListKiemKePhanLoaiThuoc(maphieukiem);
    }

    public String taoBangKiemKe(KiemKe kiemke, String listPL, List<DmPhanLoaiThuoc> listDmPLThuocKK, String lthuoc, boolean chon) throws Exception{
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.taoBangKiemKe(kiemke, listPL, listDmPLThuocKK, lthuoc, chon);
    }

    public String hoantatKiemKe(KiemKe kiemke){
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.hoantatKiemKe(kiemke);
    }

    public String huyKiemKe(KiemKe kiemke){
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.huyKiemKe(kiemke);
    }

    public String capnhatSoLieuKiemKe(KiemKe kiemke, List<KiemKeKho> lstKiemKeKho){
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.capnhatSoLieuKiemKe(kiemke, lstKiemKeKho);
    }

    public boolean dangKiemKe(String malienket, Integer khoaMaso){
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.dangKiemKe(malienket, khoaMaso);
    }

    public boolean isExistedKiemKe(String loaiThuocMa, Integer khoaMaso){
        if (kiemkeService == null) {
            kiemkeService = lookupService();
        }
        return kiemkeService.isExistedKiemKe(loaiThuocMa, khoaMaso);
    }
}


