/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.PhieuXuatBhInterface;

import com.iesvn.yte.dieutri.entity.CtXuatBh;
import com.iesvn.yte.dieutri.entity.PhieuXuatBh;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;

import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class PhieuXuatBhDelegate {

    private PhieuXuatBhInterface phieuxuatbhService;

    public static PhieuXuatBhDelegate getInstance() {
        return new PhieuXuatBhDelegate();
    }

    private PhieuXuatBhInterface lookupService() {
        return (PhieuXuatBhInterface) LookupServiceUtils.lookupService("PhieuXuatBhFacade");
    }

    public void create(PhieuXuatBh phieuXuatBh) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        phieuxuatbhService.create(phieuXuatBh);
    }

    public void edit(PhieuXuatBh phieuXuatBh) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        phieuxuatbhService.edit(phieuXuatBh);
    }

    public void remove(PhieuXuatBh phieuXuatBh) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        phieuxuatbhService.remove(phieuXuatBh);
    }

    public PhieuXuatBh find(Object id) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.find(id);
    }

    public List<PhieuXuatBh> findAll() {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.findAll();
    }

    public String create(PhieuXuatBh phieuxuatBh, List<CtXuatBh> listCtXuatBh, List<TonKho> listTk) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.create(phieuxuatBh, listCtXuatBh, listTk);
    }

    public List<PhieuXuatBh> findByTiepDonMa(String tiepDonMa) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.findByTiepDonMa(tiepDonMa);
    }

    public List<PhieuXuatBh> findByTiepDonMa_Kho(String tiepDonMa, Integer khoMaso){
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.findByTiepDonMa_Kho(tiepDonMa, khoMaso);
    }

    public String createByThuocPhongKham(Integer KhoaMaSo, PhieuXuatBh phieuxuatBh, List<ThuocPhongKham> listTPK, List<CtXuatBh> listCtXuatBH, String priority) {
        if (phieuxuatbhService == null) {
            phieuxuatbhService = lookupService();
        }
        return phieuxuatbhService.createByThuocPhongKham(KhoaMaSo, phieuxuatBh, listTPK, listCtXuatBH, priority);
    }
}


