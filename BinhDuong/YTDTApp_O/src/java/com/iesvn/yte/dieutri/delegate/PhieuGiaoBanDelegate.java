/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.CtPhieuGiaoBan;
import com.iesvn.yte.dieutri.entity.PhieuGiaoBan;
import com.iesvn.yte.dieutri.entity.PhieuGiaoBanThanhPhanThamDu;
import com.iesvn.yte.dieutri.intf.PhieuGiaoBanInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class PhieuGiaoBanDelegate {

    private PhieuGiaoBanInterface PhieuGiaoBanService;

    public static PhieuGiaoBanDelegate getInstance() {
        return new PhieuGiaoBanDelegate();
    }

    private PhieuGiaoBanInterface lookupService() {
        return (PhieuGiaoBanInterface) LookupServiceUtils.lookupService("PhieuGiaoBanFacade");
    }

    public void create(PhieuGiaoBan obj) {
        if (PhieuGiaoBanService == null) {
            PhieuGiaoBanService = lookupService();
        }
        PhieuGiaoBanService.create(obj);
    }

    public void edit(PhieuGiaoBan obj) {
        if (PhieuGiaoBanService == null) {
            PhieuGiaoBanService = lookupService();
        }
        PhieuGiaoBanService.edit(obj);
    }

    public void remove(PhieuGiaoBan obj) {
        if (PhieuGiaoBanService == null) {
            PhieuGiaoBanService = lookupService();
        }
        PhieuGiaoBanService.remove(obj);
    }

    public PhieuGiaoBan find(Object id) {
        if (PhieuGiaoBanService == null) {
            PhieuGiaoBanService = lookupService();
        }
        return PhieuGiaoBanService.find(id);
    }

    public List<PhieuGiaoBan> findAll() {
        if (PhieuGiaoBanService == null) {
            PhieuGiaoBanService = lookupService();
        }
        return PhieuGiaoBanService.findAll();
    }

    public  List<PhieuGiaoBan> findByPhieuGiaoBan(String maPhieu) {
        if (PhieuGiaoBanService == null) {
            PhieuGiaoBanService = lookupService();
        }
        return PhieuGiaoBanService.findByPhieuGiaoBan(maPhieu);
    }

    public String capNhatPhieuGiaoBan(PhieuGiaoBan phieuGiaoBan, String maPhieuGiaoBan, List<PhieuGiaoBanThanhPhanThamDu> listTPTD,List<CtPhieuGiaoBan> listCTPGB){
        if (PhieuGiaoBanService == null) {
            PhieuGiaoBanService = lookupService();
        }
        return PhieuGiaoBanService.capNhatPhieuGiaoBan(phieuGiaoBan, maPhieuGiaoBan, listTPTD,listCTPGB);
    }

    public void removeAllPhieuGiaoBan(PhieuGiaoBan phieuGiaoBan){
        if (PhieuGiaoBanService == null) {
            PhieuGiaoBanService = lookupService();
        }
        PhieuGiaoBanService.removeAllPhieuGiaoBan(phieuGiaoBan);
    }

    
}
