/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.PhieuXuatKhoInterface;

import com.iesvn.yte.dieutri.entity.CtXuatKho;
import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import java.util.List;
import java.util.Date;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class PhieuXuatKhoDelegate {

    private PhieuXuatKhoInterface phieuxuatkhoService;

    public static PhieuXuatKhoDelegate getInstance() {
        return new PhieuXuatKhoDelegate();
    }

    private PhieuXuatKhoInterface lookupService() {
        return (PhieuXuatKhoInterface) LookupServiceUtils.lookupService("PhieuXuatKhoFacade");
    }

    public void create(PhieuXuatKho phieuXuatKho) {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        phieuxuatkhoService.create(phieuXuatKho);
    }

    public void edit(PhieuXuatKho phieuXuatKho) {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        phieuxuatkhoService.edit(phieuXuatKho);
    }

    public void remove(PhieuXuatKho phieuXuatKho) {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        phieuxuatkhoService.remove(phieuXuatKho);
    }

    public PhieuXuatKho find(Object id) {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.find(id);
    }

    public List<PhieuXuatKho> findAll() {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.findAll();
    }

    public List<PhieuXuatKho> find(String pxkMa, Date ngayNhap, Integer loaiPhieuMaSo, Integer maNguoiLap, Integer maNguoiNhap, Integer maKhoNhan, Integer maKhoXuat){
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.find(pxkMa, ngayNhap, loaiPhieuMaSo, maNguoiLap, maNguoiNhap, maKhoNhan, maKhoXuat);
    }

    public com.iesvn.yte.dieutri.entity.PhieuXuatKho findByPhieuxuatkhoMa(java.lang.String maPhieu) {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.findByPhieuxuatkhoMa(maPhieu);
    }

    public String createPhieuXuat(PhieuXuatKho pxk, List<CtXuatKho> listCtXuatKho, List<TonKho> listTkNhan, List<TonKho> listTkXuat) {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.createPhieuXuat(pxk, listCtXuatKho, listTkNhan, listTkXuat);
    }

    public java.lang.String XuatPhieuDuTru(java.util.List<com.iesvn.yte.dieutri.entity.CtXuatKho> listCTX, com.iesvn.yte.dieutri.entity.PhieuXuatKho pxk, java.lang.String priority) throws java.lang.Exception {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.XuatPhieuDuTru(listCTX, pxk, priority);
    }

    public java.lang.String XuatPhieuDuTruTuTruc(java.util.List<com.iesvn.yte.dieutri.entity.CtXuatKho> listCTX, com.iesvn.yte.dieutri.entity.PhieuXuatKho pxk, java.lang.String priority) throws java.lang.Exception {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.XuatPhieuDuTruTuTruc(listCTX, pxk, priority);
    }

    public PhieuXuatKho findByPhieuDuTru(String maPhieuDT) {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.findByPhieuxuatkhoMa(maPhieuDT);
    }

    public PhieuXuatKho findByPhieuDuTruAndKhoXuat(String maPhieuDT, Integer dmKhoXuatMaso){
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.findByPhieuDuTruAndKhoXuat(maPhieuDT, dmKhoXuatMaso);
    }

    public boolean daXuatThuocTheoPhieuDT(String maPhieuDuTru) {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.daXuatThuocTheoPhieuDT(maPhieuDuTru);
    }

    public String upDatePhieuXuat(PhieuXuatKho pxk, List<CtXuatKho> listCtXuatKho) {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.upDatePhieuXuat(pxk, listCtXuatKho);
    }

    public PhieuXuatKho findPhieuXuatKhoByKhoXuat(String maPhieu, Integer maKhoa){
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.findPhieuXuatKhoByKhoXuat(maPhieu, maKhoa);
    }

    public PhieuXuatKho findPhieuXuatKhoByKhoaNhan(String maPhieu, Integer maKhoa) {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.findPhieuXuatKhoByKhoaNhan(maPhieu, maKhoa);
    }

    public String thucHienPhieuXuat(PhieuXuatKho pxk, List<CtXuatKho> listCtXuatKho, List<TonKho> listTkNhan, List<TonKho> listTkXuat, String priority) {
        if (phieuxuatkhoService == null) {
            phieuxuatkhoService = lookupService();
        }
        return phieuxuatkhoService.thucHienPhieuXuat(pxk, listCtXuatKho, listTkNhan, listTkXuat, priority);
    }
}


