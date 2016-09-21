/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmLoiDan;
import com.iesvn.yte.dieutri.intf.DtDmLoiDanInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmLoiDanAnDelegate {
    private DtDmLoiDanInterface DtDmLoiDanService;

    public static DtDmLoiDanAnDelegate getInstance() {
        return new DtDmLoiDanAnDelegate();
    }

    private DtDmLoiDanInterface lookupService() {
        return (DtDmLoiDanInterface) LookupServiceUtils.lookupService("DtDmLoiDanFacade");
    }
    
    public void create(DtDmLoiDan obj) {
        if (DtDmLoiDanService == null) {
            DtDmLoiDanService = lookupService();
        }
        DtDmLoiDanService.create(obj);
    }
    
    public void edit(DtDmLoiDan obj) {
        if (DtDmLoiDanService == null) {
            DtDmLoiDanService = lookupService();
        }
        DtDmLoiDanService.edit(obj);
    }
    
    public void remove(DtDmLoiDan obj) {
        if (DtDmLoiDanService == null) {
            DtDmLoiDanService = lookupService();
        }
        DtDmLoiDanService.remove(obj);
    }
    
    public DtDmLoiDan find(Object id) {
        if (DtDmLoiDanService == null) {
            DtDmLoiDanService = lookupService();
        }
        return DtDmLoiDanService.find(id);
    }
    
    public List<DtDmLoiDan> findAll() {
        if (DtDmLoiDanService == null) {
            DtDmLoiDanService = lookupService();
        }
        return DtDmLoiDanService.findAll();
    }
}
