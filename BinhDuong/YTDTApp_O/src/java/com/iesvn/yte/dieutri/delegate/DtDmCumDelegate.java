/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.intf.DtDmCumInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmCumDelegate {
    private DtDmCumInterface dtdmcumService;

    public static DtDmCumDelegate getInstance() {
        return new DtDmCumDelegate();
    }

    private DtDmCumInterface lookupService() {
        return (DtDmCumInterface) LookupServiceUtils.lookupService("DtDmCumFacade");
    }
    
    public void create(DtDmCum dtDmCum) {
        if (dtdmcumService == null) {
            dtdmcumService = lookupService();
        }
        dtdmcumService.create(dtDmCum);
    }
    
    public void edit(DtDmCum dtDmCum) {
        if (dtdmcumService == null) {
            dtdmcumService = lookupService();
        }
        dtdmcumService.edit(dtDmCum);
    }
    
    public void remove(DtDmCum dtDmCum) {
        if (dtdmcumService == null) {
            dtdmcumService = lookupService();
        }
        dtdmcumService.remove(dtDmCum);
    }
    
    public DtDmCum find(Object id) {
        if (dtdmcumService == null) {
            dtdmcumService = lookupService();
        }
        return dtdmcumService.find(id);
    }
    
    public List<DtDmCum> findAll() {
        if (dtdmcumService == null) {
            dtdmcumService = lookupService();
        }
        return dtdmcumService.findAll();
    }
}
