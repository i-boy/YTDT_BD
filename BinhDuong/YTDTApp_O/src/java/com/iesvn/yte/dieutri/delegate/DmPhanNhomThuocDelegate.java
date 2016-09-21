/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.entity.DmPhanNhomThuoc;
import com.iesvn.yte.dieutri.intf.DmPhanNhomThuocInterface;
import com.iesvn.yte.locator.LookupServiceUtils;

import java.util.List;
/**
 *
 * @author user01
 */
public class DmPhanNhomThuocDelegate {
    private DmPhanNhomThuocInterface dmPhanNhomThuocService;

    public static DmPhanNhomThuocDelegate getInstance() {
        return new DmPhanNhomThuocDelegate();
    }
    private DmPhanNhomThuocInterface lookupService() {
        return (DmPhanNhomThuocInterface) LookupServiceUtils.lookupService("DmPhanNhomThuocFacade");
    }

    public void create(DmPhanNhomThuoc dmPhanNhomThuoc) {
        if (dmPhanNhomThuocService == null) {
            dmPhanNhomThuocService = lookupService();
        }
        dmPhanNhomThuocService.create(dmPhanNhomThuoc);
    }

    public void edit(DmPhanNhomThuoc dmPhanNhomThuoc) {
        if (dmPhanNhomThuocService == null) {
            dmPhanNhomThuocService = lookupService();
        }
        dmPhanNhomThuocService.edit(dmPhanNhomThuoc);
    }

    public void remove(DmPhanNhomThuoc dmPhanNhomThuoc) {
        if (dmPhanNhomThuocService == null) {
            dmPhanNhomThuocService = lookupService();
        }
        dmPhanNhomThuocService.remove(dmPhanNhomThuoc);
    }

    public DmPhanNhomThuoc find(Object id) {
        if (dmPhanNhomThuocService == null) {
            dmPhanNhomThuocService = lookupService();
        }
        return dmPhanNhomThuocService.find(id);
    }

    public List<DmPhanNhomThuoc> findAll() {
        if (dmPhanNhomThuocService == null) {
            dmPhanNhomThuocService = lookupService();
        }
        return dmPhanNhomThuocService.findAll();
    }
}
