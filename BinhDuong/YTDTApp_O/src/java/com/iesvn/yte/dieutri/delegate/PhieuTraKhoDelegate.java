/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.PhieuTraKhoInterface;
import com.iesvn.yte.dieutri.entity.CtTraKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.PhieuTraKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class PhieuTraKhoDelegate {

    private PhieuTraKhoInterface phieutrakhoService;

    public static PhieuTraKhoDelegate getInstance() {
        return new PhieuTraKhoDelegate();
    }

    private PhieuTraKhoInterface lookupService() {
        return (PhieuTraKhoInterface) LookupServiceUtils.lookupService("PhieuTraKhoFacade");
    }

    public void create(PhieuTraKho phieuTraKho) {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        phieutrakhoService.create(phieuTraKho);
    }

    public void edit(PhieuTraKho phieuTraKho) {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        phieutrakhoService.edit(phieuTraKho);
    }

    public void remove(PhieuTraKho phieuTraKho) {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        phieutrakhoService.remove(phieuTraKho);
    }

    public PhieuTraKho find(Object id) {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.find(id);
    }

    public List<PhieuTraKho> findAll() {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.findAll();
    }

    public PhieuTraKho findByPhieutrakhoMa(String maPhieu) {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.findByPhieutrakhoMa(maPhieu);
    }

    public PhieuTraKho findByPhieutrakhoByKhoNhan(String maPhieu, Integer khoaMaso) {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.findByPhieutrakhoByKhoNhan(maPhieu, khoaMaso);
    }

    public PhieuTraKho findPhieuTraKhoByKhoaTra(String maPhieu, Integer maKhoa) {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.findPhieuTraKhoByKhoaTra(maPhieu, maKhoa);
    }

    public String createPhieuTra(PhieuTraKho ptk, List<CtTraKho> listCtTraKho, List<TonKho> listTkNhan, List<TonKho> listTkTra) {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.createPhieuTra(ptk, listCtTraKho, listTkNhan, listTkTra);
    }

    public String TraPhieuDuTru(List<CtTraKho> listCTX, PhieuTraKho ptk) throws Exception {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.TraPhieuDuTru(listCTX, ptk);
    }

    public boolean daTraPhieuDuTru(String maPhieuDuTru) {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.daTraPhieuDuTru(maPhieuDuTru);
    }

    public String updatePhieuTraKho(PhieuTraKho objPhieuTraKho, List<CtTraKho> listCtTraKho) {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.updatePhieuTraKho(objPhieuTraKho, listCtTraKho);
    }

    public String thucHienTraKho(PhieuTraKho objPhieuTraKho, List<CtTraKho> listCtTraKho, List<TonKho> listTkNhan, List<TonKho> listTkTra) {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.thucHienTraKho(objPhieuTraKho, listCtTraKho, listTkNhan, listTkTra);
    }

    public String updatePhieuDTTuTrucTraKho(List<CtTraKho> listCTX, PhieuTraKho ptk, String priority) throws Exception {
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.updatePhieuDTTuTrucTraKho(listCTX, ptk, priority);
    }

    public PhieuTraKho findByPhieuDuTruMa(String phieudttraMa, Integer dmKhoNhanMaso){
        if (phieutrakhoService == null) {
            phieutrakhoService = lookupService();
        }
        return phieutrakhoService.findByPhieuDuTruMa(phieudttraMa, dmKhoNhanMaso);
    }
}


