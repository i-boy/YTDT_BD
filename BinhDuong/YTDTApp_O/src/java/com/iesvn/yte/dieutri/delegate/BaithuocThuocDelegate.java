/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.BaithuocThuocInterface;
import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class BaithuocThuocDelegate {

    private BaithuocThuocInterface baithuocThuocService;

    public static BaithuocThuocDelegate getInstance() {
        return new BaithuocThuocDelegate();
    }

    private BaithuocThuocInterface lookupService() {
        return (BaithuocThuocInterface) LookupServiceUtils.lookupService("BaithuocThuocFacade");
    }

    public void create(BaithuocThuoc baithuocThuoc) {
        if (baithuocThuocService == null) {
            baithuocThuocService = lookupService();
        }
        baithuocThuocService.create(baithuocThuoc);
    }

    public void edit(BaithuocThuoc baithuocThuoc) {
        if (baithuocThuocService == null) {
            baithuocThuocService = lookupService();
        }
        baithuocThuocService.edit(baithuocThuoc);
    }

    public void remove(BaithuocThuoc baithuocThuoc) {
        if (baithuocThuocService == null) {
            baithuocThuocService = lookupService();
        }
        baithuocThuocService.remove(baithuocThuoc);
    }

    public BaithuocThuoc find(Object id) {
        if (baithuocThuocService == null) {
            baithuocThuocService = lookupService();
        }
        return baithuocThuocService.find(id);
    }

    public void deleteAllBaithuocThuocsByMasoThuoc(Integer thuocMaso){
        if (baithuocThuocService == null) {
            baithuocThuocService = lookupService();
        }
        baithuocThuocService.deleteAllBaithuocThuocsByMasoThuoc(thuocMaso);
    }
}

