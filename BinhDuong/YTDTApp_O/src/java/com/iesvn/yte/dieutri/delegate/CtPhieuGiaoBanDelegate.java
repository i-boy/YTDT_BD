/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.CtPhieuGiaoBan;
import com.iesvn.yte.dieutri.intf.CtPhieuGiaoBanInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class CtPhieuGiaoBanDelegate {

    private CtPhieuGiaoBanInterface CtPhieuGiaoBanService;

    public static CtPhieuGiaoBanDelegate getInstance() {
        return new CtPhieuGiaoBanDelegate();
    }

    private CtPhieuGiaoBanInterface lookupService() {
        return (CtPhieuGiaoBanInterface) LookupServiceUtils.lookupService("CtPhieuGiaoBanFacade");
    }

    public void create(CtPhieuGiaoBan obj) {
        if (CtPhieuGiaoBanService == null) {
            CtPhieuGiaoBanService = lookupService();
        }
        CtPhieuGiaoBanService.create(obj);
    }

    public void edit(CtPhieuGiaoBan obj) {
        if (CtPhieuGiaoBanService == null) {
            CtPhieuGiaoBanService = lookupService();
        }
        CtPhieuGiaoBanService.edit(obj);
    }

    public void remove(CtPhieuGiaoBan obj) {
        if (CtPhieuGiaoBanService == null) {
            CtPhieuGiaoBanService = lookupService();
        }
        CtPhieuGiaoBanService.remove(obj);
    }

    public CtPhieuGiaoBan find(Object id) {
        if (CtPhieuGiaoBanService == null) {
            CtPhieuGiaoBanService = lookupService();
        }
        return CtPhieuGiaoBanService.find(id);
    }

    public List<CtPhieuGiaoBan> findAll() {
        if (CtPhieuGiaoBanService == null) {
            CtPhieuGiaoBanService = lookupService();
        }
        return CtPhieuGiaoBanService.findAll();
    }


    public List<CtPhieuGiaoBan> findByPhieuGiaoBan(String maPhieu) {
        if (CtPhieuGiaoBanService == null) {
            CtPhieuGiaoBanService = lookupService();
        }
        return CtPhieuGiaoBanService.findByPhieuGiaoBan(maPhieu);
    }
}
