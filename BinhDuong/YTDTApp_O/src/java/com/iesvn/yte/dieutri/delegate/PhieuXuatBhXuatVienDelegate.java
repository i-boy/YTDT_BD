/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.PhieuXuatBhXuatVienInterface;

import com.iesvn.yte.dieutri.entity.CtXuatBhXuatVien;
import com.iesvn.yte.dieutri.entity.PhieuXuatBhXuatVien;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;

import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class PhieuXuatBhXuatVienDelegate {

    private PhieuXuatBhXuatVienInterface phieuxuatbhService;

    public static PhieuXuatBhXuatVienDelegate getInstance() {
        return new PhieuXuatBhXuatVienDelegate();
    }

    private PhieuXuatBhXuatVienInterface lookupService() {
        return (PhieuXuatBhXuatVienInterface) LookupServiceUtils.lookupService("PhieuXuatBhXuatVienFacade");
    }

    public void create(PhieuXuatBhXuatVien phieuXuatBh) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        phieuxuatbhService.create(phieuXuatBh);
    }

    public void edit(PhieuXuatBhXuatVien phieuXuatBh) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        phieuxuatbhService.edit(phieuXuatBh);
    }

    public void remove(PhieuXuatBhXuatVien phieuXuatBh) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        phieuxuatbhService.remove(phieuXuatBh);
    }

    public PhieuXuatBhXuatVien find(Object id) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.find(id);
    }

    public List<PhieuXuatBhXuatVien> findAll() {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.findAll();
    }

    public String create(PhieuXuatBhXuatVien phieuxuatBh, List<CtXuatBhXuatVien> listCtXuatBh, List<TonKho> listTk) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.create(phieuxuatBh, listCtXuatBh, listTk);
    }

    public List<PhieuXuatBhXuatVien> findBySoVaoVien(String soVaovien) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.findBySoVaoVien(soVaovien);
    }

    public List<PhieuXuatBhXuatVien> findBySovaovien_Kho(String soVaovien, Integer khoMaso){
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.findBySovaovien_Kho(soVaovien, khoMaso);
    }

    public String createByThuocNoiTru(int KhoaMaSo, PhieuXuatBhXuatVien phieuxuatBh, List<ThuocNoiTru> listTNT, List<CtXuatBhXuatVien> listCtXuatBH) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.createByThuocNoiTru(KhoaMaSo, phieuxuatBh, listTNT, listCtXuatBH);
    }
}


