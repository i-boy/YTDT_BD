/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.BenhNhanGioAn;
import com.iesvn.yte.dieutri.intf.BenhNhanGioAnInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class BenhNhanGioAnDelegate {

    private BenhNhanGioAnInterface benhnhangioanService;

    public static BenhNhanGioAnDelegate getInstance() {
        return new BenhNhanGioAnDelegate();
    }

    private BenhNhanGioAnInterface lookupService() {
        return (BenhNhanGioAnInterface) LookupServiceUtils.lookupService("BenhNhanGioAnFacade");
    }

    public void create(BenhNhanGioAn obj) {
        if (benhnhangioanService == null) {
            benhnhangioanService = lookupService();
        }
        benhnhangioanService.create(obj);
    }

    public void edit(BenhNhanGioAn obj) {
        if (benhnhangioanService == null) {
            benhnhangioanService = lookupService();
        }
        benhnhangioanService.edit(obj);
    }

    public void remove(BenhNhanGioAn obj) {
        if (benhnhangioanService == null) {
            benhnhangioanService = lookupService();
        }
        benhnhangioanService.remove(obj);
    }

    public BenhNhanGioAn find(Object id) {
        if (benhnhangioanService == null) {
            benhnhangioanService = lookupService();
        }
        return benhnhangioanService.find(id);
    }

    public List<BenhNhanGioAn> findAll() {
        if (benhnhangioanService == null) {
            benhnhangioanService = lookupService();
        }
        return benhnhangioanService.findAll();
    }
    
    public List<BenhNhanGioAn> findByBnPbaMaso(Integer bnpbaMaso) {   
        if (benhnhangioanService == null) {
            benhnhangioanService = lookupService();
        }
        return benhnhangioanService.findByBnPbaMaso(bnpbaMaso);
    }
   
}
