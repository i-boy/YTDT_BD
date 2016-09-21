/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmPhong;
import com.iesvn.yte.dieutri.intf.DtDmPhongInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmPhongDelegate {

    private DtDmPhongInterface dtdmphongService;

    public static  DtDmPhongDelegate getInstance() {
        return new  DtDmPhongDelegate();
    }

    private DtDmPhongInterface lookupService() {
        return (DtDmPhongInterface) LookupServiceUtils.lookupService("DtDmPhongFacade");
    }

    public void create(DtDmPhong obj) {
        if (dtdmphongService == null) {
            dtdmphongService = lookupService();
        }
        dtdmphongService.create(obj);
    }

    public void edit(DtDmPhong obj) {
        if (dtdmphongService == null) {
            dtdmphongService = lookupService();
        }
        dtdmphongService.edit(obj);
    }

    public void remove(DtDmPhong obj) {
        if (dtdmphongService == null) {
            dtdmphongService = lookupService();
        }
        dtdmphongService.remove(obj);
    }

    public DtDmPhong find(Object id) {
        if (dtdmphongService == null) {
            dtdmphongService = lookupService();
        }
        return dtdmphongService.find(id);
    }

    public List<DtDmPhong> findAll() {
        if (dtdmphongService == null) {
            dtdmphongService = lookupService();
        }
        return dtdmphongService.findAll();
    }
}
