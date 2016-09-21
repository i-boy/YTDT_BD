/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.CtXuatKhoInterface;

import com.iesvn.yte.dieutri.entity.CtXuatKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class CtXuatKhoDelegate {

    private CtXuatKhoInterface ctxuatkhoService;

    public static CtXuatKhoDelegate getInstance() {
        return new CtXuatKhoDelegate();
    }

    private CtXuatKhoInterface lookupService() {
        return (CtXuatKhoInterface) LookupServiceUtils.lookupService("CtXuatKhoFacade");
    }

    public void create(CtXuatKho ctXuatKho) {
        if (ctxuatkhoService == null) {
            ctxuatkhoService = lookupService();
        }
        ctxuatkhoService.create(ctXuatKho);
    }

    public void edit(CtXuatKho ctXuatKho) {
        if (ctxuatkhoService == null) {
            ctxuatkhoService = lookupService();
        }
        ctxuatkhoService.edit(ctXuatKho);
    }

    public void remove(CtXuatKho ctXuatKho) {
        if (ctxuatkhoService == null) {
            ctxuatkhoService = lookupService();
        }
        ctxuatkhoService.remove(ctXuatKho);
    }

    public CtXuatKho find(Object id) {
        if (ctxuatkhoService == null) {
            ctxuatkhoService = lookupService();
        }
        return ctxuatkhoService.find(id);
    }

    public List<CtXuatKho> findAll() {
        if (ctxuatkhoService == null) {
            ctxuatkhoService = lookupService();
        }
        return ctxuatkhoService.findAll();
    }

    public java.util.List<com.iesvn.yte.dieutri.entity.CtXuatKho> findByphieuxuatkhoMa(java.lang.String phieuxuatMa) {
        if (ctxuatkhoService == null) {
            ctxuatkhoService = lookupService();
        }
        return ctxuatkhoService.findByphieuxuatkhoMa(phieuxuatMa);
    }
}


