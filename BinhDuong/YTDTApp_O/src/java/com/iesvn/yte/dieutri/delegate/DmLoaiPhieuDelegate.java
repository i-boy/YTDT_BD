/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.entity.DmLoaiPhieu;
import com.iesvn.yte.dieutri.intf.DmLoaiPhieuInterface;
import com.iesvn.yte.locator.LookupServiceUtils;

import java.util.List;
/**
 *
 * @author user01
 */
public class DmLoaiPhieuDelegate {
    private DmLoaiPhieuInterface dmLoaiPhieuService;

    public static DmLoaiPhieuDelegate getInstance() {
        return new DmLoaiPhieuDelegate();
    }
    private DmLoaiPhieuInterface lookupService() {
        return (DmLoaiPhieuInterface) LookupServiceUtils.lookupService("DmLoaiPhieuFacade");
    }

    public void create(DmLoaiPhieu dmLoaiPhieu) {
        if (dmLoaiPhieuService == null) {
            dmLoaiPhieuService = lookupService();
        }
        dmLoaiPhieuService.create(dmLoaiPhieu);
    }

    public void edit(DmLoaiPhieu dmLoaiPhieu) {
        if (dmLoaiPhieuService == null) {
            dmLoaiPhieuService = lookupService();
        }
        dmLoaiPhieuService.edit(dmLoaiPhieu);
    }

    public void remove(DmLoaiPhieu dmLoaiPhieu) {
        if (dmLoaiPhieuService == null) {
            dmLoaiPhieuService = lookupService();
        }
        dmLoaiPhieuService.remove(dmLoaiPhieu);
    }

    public DmLoaiPhieu find(Object id) {
        if (dmLoaiPhieuService == null) {
            dmLoaiPhieuService = lookupService();
        }
        return dmLoaiPhieuService.find(id);
    }

    public List<DmLoaiPhieu> findAll() {
        if (dmLoaiPhieuService == null) {
            dmLoaiPhieuService = lookupService();
        }
        return dmLoaiPhieuService.findAll();
    }
}
