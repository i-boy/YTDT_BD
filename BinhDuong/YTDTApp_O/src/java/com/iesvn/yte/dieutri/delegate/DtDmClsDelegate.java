/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmClsInterface;

import com.iesvn.yte.dieutri.entity.DtDmCls;
import com.iesvn.yte.entity.DmKhoa;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmClsDelegate {

    private DtDmClsInterface dtdmclsService;

    public static DtDmClsDelegate getInstance() {
        return new DtDmClsDelegate();
    }

    private DtDmClsInterface lookupService() {
        return (DtDmClsInterface) LookupServiceUtils.lookupService("DtDmClsFacade");
    }

    public void create(DtDmCls dtDmCls) {
        if (dtdmclsService == null) {
            dtdmclsService = lookupService();
        }
        dtdmclsService.create(dtDmCls);
    }

    public void edit(DtDmCls dtDmCls) {
        if (dtdmclsService == null) {
            dtdmclsService = lookupService();
        }
        dtdmclsService.edit(dtDmCls);
    }

    public void remove(DtDmCls dtDmCls) {
        if (dtdmclsService == null) {
            dtdmclsService = lookupService();
        }
        dtdmclsService.remove(dtDmCls);
    }

    public DtDmCls find(Object id) {
        if (dtdmclsService == null) {
            dtdmclsService = lookupService();
        }
        return dtdmclsService.find(id);
    }

    public List<DtDmCls> findAll() {
        if (dtdmclsService == null) {
            dtdmclsService = lookupService();
        }
        return dtdmclsService.findAll();
    }
    
    public List<DmKhoa> getListDmKhoa(Integer clsMaso) {
        if (dtdmclsService == null) {
            dtdmclsService = lookupService();
        }
        return dtdmclsService.getListDmKhoa(clsMaso);
    }
}


