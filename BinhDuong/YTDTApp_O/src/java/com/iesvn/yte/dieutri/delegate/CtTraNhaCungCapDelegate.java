/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.CtTraNhaCungCap;
import com.iesvn.yte.dieutri.intf.CtTraNhaCungCapInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class CtTraNhaCungCapDelegate {

    private CtTraNhaCungCapInterface CtTraNhaCungCapService;

    public static CtTraNhaCungCapDelegate getInstance() {
        return new CtTraNhaCungCapDelegate();
    }

    private CtTraNhaCungCapInterface lookupService() {
        return (CtTraNhaCungCapInterface) LookupServiceUtils.lookupService("CtTraNhaCungCapFacade");
    }

    public void create(CtTraNhaCungCap obj) {
        if (CtTraNhaCungCapService == null) {
            CtTraNhaCungCapService = lookupService();
        }
        CtTraNhaCungCapService.create(obj);
    }

    public void edit(CtTraNhaCungCap obj) {
        if (CtTraNhaCungCapService == null) {
            CtTraNhaCungCapService = lookupService();
        }
        CtTraNhaCungCapService.edit(obj);
    }

    public void remove(CtTraNhaCungCap obj) {
        if (CtTraNhaCungCapService == null) {
            CtTraNhaCungCapService = lookupService();
        }
        CtTraNhaCungCapService.remove(obj);
    }

    public CtTraNhaCungCap find(Object id) {
        if (CtTraNhaCungCapService == null) {
            CtTraNhaCungCapService = lookupService();
        }
        return CtTraNhaCungCapService.find(id);
    }

    public List<CtTraNhaCungCap> findAll() {
        if (CtTraNhaCungCapService == null) {
            CtTraNhaCungCapService = lookupService();
        }
        return CtTraNhaCungCapService.findAll();
    }


    public List<CtTraNhaCungCap> findCtTraNhaCungCapByMaPhieu(String maPhieu) {
        if (CtTraNhaCungCapService == null) {
            CtTraNhaCungCapService = lookupService();
        }
        return CtTraNhaCungCapService.findCtTraNhaCungCapByMaPhieu(maPhieu);
    }
}
