/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmGioAn;
import com.iesvn.yte.dieutri.intf.DtDmGioAnInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmGioAnDelegate {

    private DtDmGioAnInterface dtdmgioanService;

    public static DtDmGioAnDelegate getInstance() {
        return new DtDmGioAnDelegate();
    }

    private DtDmGioAnInterface lookupService() {
        return (DtDmGioAnInterface) LookupServiceUtils.lookupService("DtDmGioAnFacade");
    }

    public void create(DtDmGioAn obj) {
        if (dtdmgioanService == null) {
            dtdmgioanService = lookupService();
        }
        dtdmgioanService.create(obj);
    }

    public void edit(DtDmGioAn obj) {
        if (dtdmgioanService == null) {
            dtdmgioanService = lookupService();
        }
        dtdmgioanService.edit(obj);
    }

    public void remove(DtDmGioAn obj) {
        if (dtdmgioanService == null) {
            dtdmgioanService = lookupService();
        }
        dtdmgioanService.remove(obj);
    }

    public DtDmGioAn find(Object id) {
        if (dtdmgioanService == null) {
            dtdmgioanService = lookupService();
        }
        return dtdmgioanService.find(id);
    }

    public List<DtDmGioAn> findAll() {
        if (dtdmgioanService == null) {
            dtdmgioanService = lookupService();
        }
        return dtdmgioanService.findAll();
    }

   
}
