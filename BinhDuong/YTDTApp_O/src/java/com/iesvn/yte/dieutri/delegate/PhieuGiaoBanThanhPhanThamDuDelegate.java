/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.PhieuGiaoBan;
import com.iesvn.yte.dieutri.entity.PhieuGiaoBanThanhPhanThamDu;
import com.iesvn.yte.dieutri.intf.PhieuGiaoBanThanhPhanThamDuInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class PhieuGiaoBanThanhPhanThamDuDelegate {

    private PhieuGiaoBanThanhPhanThamDuInterface PhieuGiaoBanThanhPhanThamDuService;

    public static PhieuGiaoBanThanhPhanThamDuDelegate getInstance() {
        return new PhieuGiaoBanThanhPhanThamDuDelegate();
    }

    private PhieuGiaoBanThanhPhanThamDuInterface lookupService() {
        return (PhieuGiaoBanThanhPhanThamDuInterface) LookupServiceUtils.lookupService("PhieuGiaoBanThanhPhanThamDuFacade");
    }

    public void create(PhieuGiaoBanThanhPhanThamDu obj) {
        if (PhieuGiaoBanThanhPhanThamDuService == null) {
            PhieuGiaoBanThanhPhanThamDuService = lookupService();
        }
        PhieuGiaoBanThanhPhanThamDuService.create(obj);
    }

    public void edit(PhieuGiaoBanThanhPhanThamDu obj) {
        if (PhieuGiaoBanThanhPhanThamDuService == null) {
            PhieuGiaoBanThanhPhanThamDuService = lookupService();
        }
        PhieuGiaoBanThanhPhanThamDuService.edit(obj);
    }

    public void remove(PhieuGiaoBanThanhPhanThamDu obj) {
        if (PhieuGiaoBanThanhPhanThamDuService == null) {
            PhieuGiaoBanThanhPhanThamDuService = lookupService();
        }
        PhieuGiaoBanThanhPhanThamDuService.remove(obj);
    }

    public PhieuGiaoBanThanhPhanThamDu find(Object id) {
        if (PhieuGiaoBanThanhPhanThamDuService == null) {
            PhieuGiaoBanThanhPhanThamDuService = lookupService();
        }
        return PhieuGiaoBanThanhPhanThamDuService.find(id);
    }

    public List<PhieuGiaoBanThanhPhanThamDu> findAll() {
        if (PhieuGiaoBanThanhPhanThamDuService == null) {
            PhieuGiaoBanThanhPhanThamDuService = lookupService();
        }
        return PhieuGiaoBanThanhPhanThamDuService.findAll();
    }

    public List<PhieuGiaoBanThanhPhanThamDu> findByPhieuGiaoBan(String maPhieu) {
        if (PhieuGiaoBanThanhPhanThamDuService == null) {
            PhieuGiaoBanThanhPhanThamDuService = lookupService();
        }
        return PhieuGiaoBanThanhPhanThamDuService.findByPhieuGiaoBan(maPhieu);
    }

    public List<PhieuGiaoBanThanhPhanThamDu> findByThamDu(String maPhieu) {
        if (PhieuGiaoBanThanhPhanThamDuService == null) {
            PhieuGiaoBanThanhPhanThamDuService = lookupService();
        }
        return PhieuGiaoBanThanhPhanThamDuService.findByThamDu(maPhieu);
    }

    public List<PhieuGiaoBanThanhPhanThamDu> findByVangMat(String maPhieu) {
        if (PhieuGiaoBanThanhPhanThamDuService == null) {
            PhieuGiaoBanThanhPhanThamDuService = lookupService();
        }
        return PhieuGiaoBanThanhPhanThamDuService.findByVangMat(maPhieu);
    }

    public void capNhatPhieuGiaoBanTPTD(List<PhieuGiaoBanThanhPhanThamDu> listTPTD, PhieuGiaoBan objPhieuGiaoBan){

        if (PhieuGiaoBanThanhPhanThamDuService == null) {
            PhieuGiaoBanThanhPhanThamDuService = lookupService();
        }
        PhieuGiaoBanThanhPhanThamDuService.capNhatPhieuGiaoBanTPTD(listTPTD, objPhieuGiaoBan);
    }

    
}
