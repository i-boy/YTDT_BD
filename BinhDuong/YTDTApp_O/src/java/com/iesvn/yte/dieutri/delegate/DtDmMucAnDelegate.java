/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmMucAn;
import com.iesvn.yte.dieutri.intf.DtDmMucAnInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmMucAnDelegate {
    private DtDmMucAnInterface dtdmmucanService;

    public static DtDmMucAnDelegate getInstance() {
        return new DtDmMucAnDelegate();
    }

    private DtDmMucAnInterface lookupService() {
        return (DtDmMucAnInterface) LookupServiceUtils.lookupService("DtDmMucAnFacade");
    }
    
    public void create(DtDmMucAn obj) {
        if (dtdmmucanService == null) {
            dtdmmucanService = lookupService();
        }
        dtdmmucanService.create(obj);
    }
    
    public void edit(DtDmMucAn obj) {
        if (dtdmmucanService == null) {
            dtdmmucanService = lookupService();
        }
        dtdmmucanService.edit(obj);
    }
    
    public void remove(DtDmMucAn obj) {
        if (dtdmmucanService == null) {
            dtdmmucanService = lookupService();
        }
        dtdmmucanService.remove(obj);
    }
    
    public DtDmMucAn find(Object id) {
        if (dtdmmucanService == null) {
            dtdmmucanService = lookupService();
        }
        return dtdmmucanService.find(id);
    }
    
    public List<DtDmMucAn> findAll() {
        if (dtdmmucanService == null) {
            dtdmmucanService = lookupService();
        }
        return dtdmmucanService.findAll();
    }
}
