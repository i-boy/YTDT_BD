/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmMqlBhyt;
import com.iesvn.yte.dieutri.intf.DtDmMqlBhytInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmMqlBhytDelegate {
    private DtDmMqlBhytInterface dtdmmqlbhytService;

    public static DtDmMqlBhytDelegate getInstance() {
        return new DtDmMqlBhytDelegate();
    }

    private DtDmMqlBhytInterface lookupService() {
        return (DtDmMqlBhytInterface) LookupServiceUtils.lookupService("DtDmMqlBhytFacade");
    }
    
    public void create(DtDmMqlBhyt obj) {
        if (dtdmmqlbhytService == null) {
            dtdmmqlbhytService = lookupService();
        }
        dtdmmqlbhytService.create(obj);
    }
    
    public void edit(DtDmMqlBhyt obj) {
        if (dtdmmqlbhytService == null) {
            dtdmmqlbhytService = lookupService();
        }
        dtdmmqlbhytService.edit(obj);
    }
    
    public void remove(DtDmMqlBhyt obj) {
        if (dtdmmqlbhytService == null) {
            dtdmmqlbhytService = lookupService();
        }
        dtdmmqlbhytService.remove(obj);
    }
    
    public DtDmMqlBhyt find(Object id) {
        if (dtdmmqlbhytService == null) {
            dtdmmqlbhytService = lookupService();
        }
        return dtdmmqlbhytService.find(id);
    }
    
    public List<DtDmMqlBhyt> findAll() {
        if (dtdmmqlbhytService == null) {
            dtdmmqlbhytService = lookupService();
        }
        return dtdmmqlbhytService.findAll();
    }
    
    public DtDmMqlBhyt findByMa(String maQL) {
        if (dtdmmqlbhytService == null) {
            dtdmmqlbhytService = lookupService();
        }
        return dtdmmqlbhytService.findByMa(maQL);
    }
}
