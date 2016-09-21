/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd;
import com.iesvn.yte.dieutri.intf.DtDmNhaSxSpddInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmNhaSxSpddDelegate {
    private DtDmNhaSxSpddInterface dtdmnhasxspddService;

    public static DtDmNhaSxSpddDelegate getInstance() {
        return new DtDmNhaSxSpddDelegate();
    }

    private DtDmNhaSxSpddInterface lookupService() {
        return (DtDmNhaSxSpddInterface) LookupServiceUtils.lookupService("DtDmNhaSxSpddFacade");
    }
    
    public void create(DtDmNhaSxSpdd obj) {
        if (dtdmnhasxspddService == null) {
            dtdmnhasxspddService = lookupService();
        }
        dtdmnhasxspddService.create(obj);
    }
    
    public void edit(DtDmNhaSxSpdd obj) {
        if (dtdmnhasxspddService == null) {
            dtdmnhasxspddService = lookupService();
        }
        dtdmnhasxspddService.edit(obj);
    }
    
    public void remove(DtDmNhaSxSpdd obj) {
        if (dtdmnhasxspddService == null) {
            dtdmnhasxspddService = lookupService();
        }
        dtdmnhasxspddService.remove(obj);
    }
    
    public DtDmNhaSxSpdd find(Object id) {
        if (dtdmnhasxspddService == null) {
            dtdmnhasxspddService = lookupService();
        }
        return dtdmnhasxspddService.find(id);
    }
    
    public List<DtDmNhaSxSpdd> findAll() {
        if (dtdmnhasxspddService == null) {
            dtdmnhasxspddService = lookupService();
        }
        return dtdmnhasxspddService.findAll();
    }
}
