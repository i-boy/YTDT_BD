/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmSanPhamDinhDuong;
import com.iesvn.yte.dieutri.intf.DtDmSanPhamDinhDuongInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmSanPhamDinhDuongDelegate {

    private DtDmSanPhamDinhDuongInterface dtdmsanphamdinhduongService;

    public static DtDmSanPhamDinhDuongDelegate getInstance() {
        return new DtDmSanPhamDinhDuongDelegate();
    }

    private DtDmSanPhamDinhDuongInterface lookupService() {
        return (DtDmSanPhamDinhDuongInterface) LookupServiceUtils.lookupService("DtDmSanPhamDinhDuongFacade");
    }

    public void create(DtDmSanPhamDinhDuong obj) {
        if (dtdmsanphamdinhduongService == null) {
            dtdmsanphamdinhduongService = lookupService();
        }
        dtdmsanphamdinhduongService.create(obj);
    }

    public void edit(DtDmSanPhamDinhDuong obj) {
        if (dtdmsanphamdinhduongService == null) {
            dtdmsanphamdinhduongService = lookupService();
        }
        dtdmsanphamdinhduongService.edit(obj);
    }

    public void remove(DtDmSanPhamDinhDuong obj) {
        if (dtdmsanphamdinhduongService == null) {
            dtdmsanphamdinhduongService = lookupService();
        }
        dtdmsanphamdinhduongService.remove(obj);
    }

    public DtDmSanPhamDinhDuong find(Object id) {
        if (dtdmsanphamdinhduongService == null) {
            dtdmsanphamdinhduongService = lookupService();
        }
        return dtdmsanphamdinhduongService.find(id);
    }

    public List<DtDmSanPhamDinhDuong> findAll() {
        if (dtdmsanphamdinhduongService == null) {
            dtdmsanphamdinhduongService = lookupService();
        }
        return dtdmsanphamdinhduongService.findAll();
    }

   
}
