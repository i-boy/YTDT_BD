/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmBuong;
import com.iesvn.yte.dieutri.intf.DtDmBuongInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmBuongDelegate {
    private DtDmBuongInterface dtdmbuongService;

    public static DtDmBuongDelegate getInstance() {
        return new DtDmBuongDelegate();
    }

    private DtDmBuongInterface lookupService() {
        return (DtDmBuongInterface) LookupServiceUtils.lookupService("DtDmBuongFacade");
    }
    
    public void create(DtDmBuong obj) {
        if (dtdmbuongService == null) {
            dtdmbuongService = lookupService();
        }
        dtdmbuongService.create(obj);
    }
    
    public void edit(DtDmBuong obj) {
        if (dtdmbuongService == null) {
            dtdmbuongService = lookupService();
        }
        dtdmbuongService.edit(obj);
    }
    
    public void remove(DtDmBuong obj) {
        if (dtdmbuongService == null) {
            dtdmbuongService = lookupService();
        }
        dtdmbuongService.remove(obj);
    }
    
    public DtDmBuong find(Object id) {
        if (dtdmbuongService == null) {
            dtdmbuongService = lookupService();
        }
        return dtdmbuongService.find(id);
    }
    
    public List<DtDmBuong> findAll() {
        if (dtdmbuongService == null) {
            dtdmbuongService = lookupService();
        }
        return dtdmbuongService.findAll();
    }
}
