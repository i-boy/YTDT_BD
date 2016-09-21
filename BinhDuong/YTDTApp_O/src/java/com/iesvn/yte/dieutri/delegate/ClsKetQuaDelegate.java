/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.ClsKetQua;
import com.iesvn.yte.dieutri.intf.ClsKetQuaInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author Thanh
 */
public class ClsKetQuaDelegate {
    private ClsKetQuaInterface clsKetQuaService;
    
    public static ClsKetQuaDelegate getInstance() {
        return new ClsKetQuaDelegate();
    }

    private ClsKetQuaInterface lookupService() {
        return (ClsKetQuaInterface) LookupServiceUtils.lookupService("ClsKetQuaFacade");
    }

    public void create(ClsKetQua clsKetQua) {
        if (clsKetQuaService == null) {
            clsKetQuaService = lookupService();
        }
        clsKetQuaService.create(clsKetQua);
    }

    public void edit(ClsKetQua clsKetQua) {
        if (clsKetQuaService == null) {
            clsKetQuaService = lookupService();
        }
        clsKetQuaService.edit(clsKetQua);
    }

    public void remove(ClsKetQua clsKetQua) {
        if (clsKetQuaService == null) {
            clsKetQuaService = lookupService();
        }
        clsKetQuaService.remove(clsKetQua);
    }

    public ClsKetQua find(Object id) {
        if (clsKetQuaService == null) {
            clsKetQuaService = lookupService();
        }
        return clsKetQuaService.find(id);
    }

    public List<ClsKetQua> findAll() {
        if (clsKetQuaService == null) {
            clsKetQuaService = lookupService();
        }
        return clsKetQuaService.findAll();
    }
}
