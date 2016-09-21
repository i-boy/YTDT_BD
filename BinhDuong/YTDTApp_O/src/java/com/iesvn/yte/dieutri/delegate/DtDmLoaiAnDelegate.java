/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
import com.iesvn.yte.dieutri.intf.DtDmLoaiAnInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmLoaiAnDelegate {
    private DtDmLoaiAnInterface dtdmloaianService;

    public static DtDmLoaiAnDelegate getInstance() {
        return new DtDmLoaiAnDelegate();
    }

    private DtDmLoaiAnInterface lookupService() {
        return (DtDmLoaiAnInterface) LookupServiceUtils.lookupService("DtDmLoaiAnFacade");
    }
    
    public void create(DtDmLoaiAn obj) {
        if (dtdmloaianService == null) {
            dtdmloaianService = lookupService();
        }
        dtdmloaianService.create(obj);
    }
    
    public void edit(DtDmLoaiAn obj) {
        if (dtdmloaianService == null) {
            dtdmloaianService = lookupService();
        }
        dtdmloaianService.edit(obj);
    }
    
    public void remove(DtDmLoaiAn obj) {
        if (dtdmloaianService == null) {
            dtdmloaianService = lookupService();
        }
        dtdmloaianService.remove(obj);
    }
    
    public DtDmLoaiAn find(Object id) {
        if (dtdmloaianService == null) {
            dtdmloaianService = lookupService();
        }
        return dtdmloaianService.find(id);
    }
    
    public List<DtDmLoaiAn> findAll() {
        if (dtdmloaianService == null) {
            dtdmloaianService = lookupService();
        }
        return dtdmloaianService.findAll();
    }
}
