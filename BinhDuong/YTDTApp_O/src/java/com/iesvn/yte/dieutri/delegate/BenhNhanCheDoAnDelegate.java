/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.BenhNhanCheDoAn;
import com.iesvn.yte.dieutri.intf.BenhNhanCheDoAnInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class BenhNhanCheDoAnDelegate {

    private BenhNhanCheDoAnInterface benhnhanchedoanService;

    public static BenhNhanCheDoAnDelegate getInstance() {
        return new BenhNhanCheDoAnDelegate();
    }

    private BenhNhanCheDoAnInterface lookupService() {
        return (BenhNhanCheDoAnInterface) LookupServiceUtils.lookupService("BenhNhanCheDoAnFacade");
    }

    public void create(BenhNhanCheDoAn obj) {
        if (benhnhanchedoanService == null) {
            benhnhanchedoanService = lookupService();
        }
        benhnhanchedoanService.create(obj);
    }

    public void edit(BenhNhanCheDoAn obj) {
        if (benhnhanchedoanService == null) {
            benhnhanchedoanService = lookupService();
        }
        benhnhanchedoanService.edit(obj);
    }

    public void remove(BenhNhanCheDoAn obj) {
        if (benhnhanchedoanService == null) {
            benhnhanchedoanService = lookupService();
        }
        benhnhanchedoanService.remove(obj);
    }

    public BenhNhanCheDoAn find(Object id) {
        if (benhnhanchedoanService == null) {
            benhnhanchedoanService = lookupService();
        }
        return benhnhanchedoanService.find(id);
    }

    public List<BenhNhanCheDoAn> findAll() {
        if (benhnhanchedoanService == null) {
            benhnhanchedoanService = lookupService();
        }
        return benhnhanchedoanService.findAll();
    }
    
    public List<BenhNhanCheDoAn> findByBnPbaMaso(Integer bnpbaMaso) {
        if (benhnhanchedoanService == null) {
            benhnhanchedoanService = lookupService();
        }
        return benhnhanchedoanService.findByBnPbaMaso(bnpbaMaso);
    }
   
}
