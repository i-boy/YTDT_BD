/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmDongThem;
import com.iesvn.yte.dieutri.intf.DtDmDongThemInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmDongThemDelegate {

    private DtDmDongThemInterface dtdmdongthemService;

    public static DtDmDongThemDelegate getInstance() {
        return new DtDmDongThemDelegate();
    }

    private DtDmDongThemInterface lookupService() {
        return (DtDmDongThemInterface) LookupServiceUtils.lookupService("DtDmDongThemFacade");
    }

    public void create(DtDmDongThem obj) {
        if (dtdmdongthemService == null) {
            dtdmdongthemService = lookupService();
        }
        dtdmdongthemService.create(obj);
    }

    public void edit(DtDmDongThem obj) {
        if (dtdmdongthemService == null) {
            dtdmdongthemService = lookupService();
        }
        dtdmdongthemService.edit(obj);
    }

    public void remove(DtDmDongThem obj) {
        if (dtdmdongthemService == null) {
            dtdmdongthemService = lookupService();
        }
        dtdmdongthemService.remove(obj);
    }

    public DtDmDongThem find(Object id) {
        if (dtdmdongthemService == null) {
            dtdmdongthemService = lookupService();
        }
        return dtdmdongthemService.find(id);
    }

    public List<DtDmDongThem> findAll() {
        if (dtdmdongthemService == null) {
            dtdmdongthemService = lookupService();
        }
        return dtdmdongthemService.findAll();
    }

   
}
