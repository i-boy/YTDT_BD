/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmClsKetQua;
import com.iesvn.yte.dieutri.intf.DtDmClsKetQuaInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author Thanh
 */
public class DtDmClsKetQuaDelegate {
    private DtDmClsKetQuaInterface dtDmClsKetQuaService;
    
    public static DtDmClsKetQuaDelegate getInstance() {
        return new DtDmClsKetQuaDelegate();
    }

    private DtDmClsKetQuaInterface lookupService() {
        return (DtDmClsKetQuaInterface) LookupServiceUtils.lookupService("DtDmClsKetQuaFacade");
    }

    public void create(DtDmClsKetQua dtDmClsKetQua) {
        if (dtDmClsKetQuaService == null) {
            dtDmClsKetQuaService = lookupService();
        }
        dtDmClsKetQuaService.create(dtDmClsKetQua);
    }

    public void edit(DtDmClsKetQua dtDmClsKetQua) {
        if (dtDmClsKetQuaService == null) {
            dtDmClsKetQuaService = lookupService();
        }
        dtDmClsKetQuaService.edit(dtDmClsKetQua);
    }

    public void remove(DtDmClsKetQua dtDmClsKetQua) {
        if (dtDmClsKetQuaService == null) {
            dtDmClsKetQuaService = lookupService();
        }
        dtDmClsKetQuaService.remove(dtDmClsKetQua);
    }

    public DtDmClsKetQua find(Object id) {
        if (dtDmClsKetQuaService == null) {
            dtDmClsKetQuaService = lookupService();
        }
        return dtDmClsKetQuaService.find(id);
    }

    public List<DtDmClsKetQua> findAll() {
        if (dtDmClsKetQuaService == null) {
            dtDmClsKetQuaService = lookupService();
        }
        return dtDmClsKetQuaService.findAll();
    }
    
    public List<DtDmClsKetQua> findByAllConditions(String ma, String ten, Integer dtdmclsbgMaso){
        if (dtDmClsKetQuaService == null) {
            dtDmClsKetQuaService = lookupService();
        }
        return dtDmClsKetQuaService.findByAllConditions(ma, ten, dtdmclsbgMaso);
    }
}
