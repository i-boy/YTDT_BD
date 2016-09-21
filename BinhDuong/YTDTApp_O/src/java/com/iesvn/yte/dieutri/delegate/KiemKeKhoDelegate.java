/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.KiemKeKhoInterface;

import com.iesvn.yte.dieutri.entity.KiemKeKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class KiemKeKhoDelegate {

    private KiemKeKhoInterface kiemkekhoService;

    public static KiemKeKhoDelegate getInstance() {
        return new KiemKeKhoDelegate();
    }

    private KiemKeKhoInterface lookupService() {
        return (KiemKeKhoInterface) LookupServiceUtils.lookupService("KiemKeKhoFacade");
    }

    public void create(KiemKeKho kiemKeKho) {
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        kiemkekhoService.create(kiemKeKho);
    }

    public void edit(KiemKeKho kiemKeKho) {
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        kiemkekhoService.edit(kiemKeKho);
    }

    public void remove(KiemKeKho kiemKeKho) {
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        kiemkekhoService.remove(kiemKeKho);
    }

    public KiemKeKho find(Object id) {
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        return kiemkekhoService.find(id);
    }

    public List<KiemKeKho> findAll() {
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        return kiemkekhoService.findAll();
    }

    public Number getAllKiemKeKhoTotal(String maPhieuKiem){
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        return kiemkekhoService.getAllKiemKeKhoTotal(maPhieuKiem);
    }

    public List<KiemKeKho> getItemKiemKeKhos(String maPhieuKiem, int limit, int offset){
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        return kiemkekhoService.getItemKiemKeKhos(maPhieuKiem, limit, offset);
    }

    public KiemKeKho findByKhoAndMaLienKet(String maKho, String maLk) {
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        return kiemkekhoService.findByKhoAndMaLienKet(maKho, maLk);
    }

    public String updateAndEditTonKhoDau(KiemKeKho obj) {
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        return kiemkekhoService.updateAndEditTonKhoDau(obj);
    }

    public List<KiemKeKho> findKiemKeKhoForCapNhatSL() {
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        return kiemkekhoService.findKiemKeKhoForCapNhatSL();
    }

    public List<KiemKeKho> findKiemKeKhoForCapNhatSL(String khoa, String thuoc, String nct, String nkp, String nsx, String quocgia, String namnhap, String ngay, String thang, String nam) {
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        return kiemkekhoService.findKiemKeKhoForCapNhatSL(khoa, thuoc, nct, nkp, nsx, quocgia, namnhap, ngay, thang, nam);
    }

    public List<KiemKeKho> findKiemKeKhoForCapNhatSLGUI(String makiemke, Integer loaiThuocMaso, Integer phanloaiThuocMaso, Integer thuocMaso, Integer nctMaso, Integer nkpMaso){
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        return kiemkekhoService.findKiemKeKhoForCapNhatSLGUI(makiemke, loaiThuocMaso, phanloaiThuocMaso, thuocMaso, nctMaso, nkpMaso);
    }

    public String getMaPhieu() {
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        return kiemkekhoService.getMaPhieu();
    }
    
    public List<KiemKeKho> findByMaPhieuKiem(String maPhieuKiem) {
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        return kiemkekhoService.findByMaPhieuKiem(maPhieuKiem);
    }
    
    public void updateKiemKeKho(List<KiemKeKho> listKiemKeKho){
        if (kiemkekhoService == null) {
            kiemkekhoService = lookupService();
        }
        kiemkekhoService.updateKiemKeKho(listKiemKeKho);
    }
}


