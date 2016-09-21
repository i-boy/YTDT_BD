/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.PhieuDuTruInterface;

import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class PhieuDuTruDelegate {

    private PhieuDuTruInterface phieudutruService;

    public static PhieuDuTruDelegate getInstance() {
        return new PhieuDuTruDelegate();
    }

    private PhieuDuTruInterface lookupService() {
        return (PhieuDuTruInterface) LookupServiceUtils.lookupService("PhieuDuTruFacade");
    }

    public void create(PhieuDuTru phieuDuTru) {
        if (phieudutruService == null) {
            phieudutruService = lookupService();
        }
        phieudutruService.create(phieuDuTru);
    }

    public void edit(PhieuDuTru phieuDuTru) {
        if (phieudutruService == null) {
            phieudutruService = lookupService();
        }
        phieudutruService.edit(phieuDuTru);
    }

    public void remove(PhieuDuTru phieuDuTru) {
        if (phieudutruService == null) {
            phieudutruService = lookupService();
        }
        phieudutruService.remove(phieuDuTru);
    }

    public PhieuDuTru find(Object id) {
        if (phieudutruService == null) {
            phieudutruService = lookupService();
        }
        return phieudutruService.find(id);
    }

    public List<PhieuDuTru> findAll() {
        if (phieudutruService == null) {
            phieudutruService = lookupService();
        }
        return phieudutruService.findAll();
    }

    public boolean removeAllPhieuDuTru(PhieuDuTru phieuDuTru) {
        if (phieudutruService == null) {
            phieudutruService = lookupService();
        }
        return phieudutruService.removeAllPhieuDuTru(phieuDuTru);
    }
}


