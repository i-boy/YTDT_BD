/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmCheDoAn;
import com.iesvn.yte.dieutri.intf.DtDmCheDoAnInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmCheDoAnDelegate {
    private DtDmCheDoAnInterface dtdmchedoanService;

    public static DtDmCheDoAnDelegate getInstance() {
        return new DtDmCheDoAnDelegate();
    }

    private DtDmCheDoAnInterface lookupService() {
        return (DtDmCheDoAnInterface) LookupServiceUtils.lookupService("DtDmCheDoAnFacade");
    }
    
    public void create(DtDmCheDoAn obj) {
        if (dtdmchedoanService == null) {
            dtdmchedoanService = lookupService();
        }
        dtdmchedoanService.create(obj);
    }
    
    public void edit(DtDmCheDoAn obj) {
        if (dtdmchedoanService == null) {
            dtdmchedoanService = lookupService();
        }
        dtdmchedoanService.edit(obj);
    }
    
    public void remove(DtDmCheDoAn obj) {
        if (dtdmchedoanService == null) {
            dtdmchedoanService = lookupService();
        }
        dtdmchedoanService.remove(obj);
    }
    
    public DtDmCheDoAn find(Object id) {
        if (dtdmchedoanService == null) {
            dtdmchedoanService = lookupService();
        }
        return dtdmchedoanService.find(id);
    }
    
    public List<DtDmCheDoAn> findAll() {
        if (dtdmchedoanService == null) {
            dtdmchedoanService = lookupService();
        }
        return dtdmchedoanService.findAll();
    }
}
