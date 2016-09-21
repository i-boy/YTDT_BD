/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmDoiTuongAn;
import com.iesvn.yte.dieutri.intf.DtDmDoiTuongAnInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmDoiTuongAnDelegate {

    private DtDmDoiTuongAnInterface dtdmdoituonganService;

    public static DtDmDoiTuongAnDelegate getInstance() {
        return new DtDmDoiTuongAnDelegate();
    }

    private DtDmDoiTuongAnInterface lookupService() {
        return (DtDmDoiTuongAnInterface) LookupServiceUtils.lookupService("DtDmDoiTuongAnFacade");
    }

    public void create(DtDmDoiTuongAn obj) {
        if (dtdmdoituonganService == null) {
            dtdmdoituonganService = lookupService();
        }
        dtdmdoituonganService.create(obj);
    }

    public void edit(DtDmDoiTuongAn obj) {
        if (dtdmdoituonganService == null) {
            dtdmdoituonganService = lookupService();
        }
        dtdmdoituonganService.edit(obj);
    }

    public void remove(DtDmDoiTuongAn obj) {
        if (dtdmdoituonganService == null) {
            dtdmdoituonganService = lookupService();
        }
        dtdmdoituonganService.remove(obj);
    }

    public DtDmDoiTuongAn find(Object id) {
        if (dtdmdoituonganService == null) {
            dtdmdoituonganService = lookupService();
        }
        return dtdmdoituonganService.find(id);
    }

    public List<DtDmDoiTuongAn> findAll() {
        if (dtdmdoituonganService == null) {
            dtdmdoituonganService = lookupService();
        }
        return dtdmdoituonganService.findAll();
    }

   
}
