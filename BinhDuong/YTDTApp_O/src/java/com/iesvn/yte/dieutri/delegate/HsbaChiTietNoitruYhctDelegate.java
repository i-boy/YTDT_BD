/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietNoitruYhctInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNoitruYhct;

import java.util.List;
/**
 *
 * @author quang
 */
public class HsbaChiTietNoitruYhctDelegate {

    private HsbaChiTietNoitruYhctInterface hsbaChiTietNoitruYhctService;

    public static HsbaChiTietNoitruYhctDelegate getInstance() {
        return new HsbaChiTietNoitruYhctDelegate();
    }

    private HsbaChiTietNoitruYhctInterface lookupService() {
        return (HsbaChiTietNoitruYhctInterface) LookupServiceUtils.lookupService("HsbaChiTietNoitruYhctFacade");
    }

    public void create(HsbaChiTietNoitruYhct hsbaChiTietNoitruYhct) {
        if (hsbaChiTietNoitruYhctService == null) {
            hsbaChiTietNoitruYhctService = lookupService();
        }
        hsbaChiTietNoitruYhctService.create(hsbaChiTietNoitruYhct);
    }

    public void edit(HsbaChiTietNoitruYhct hsbaChiTietNoitruYhct) {
        if (hsbaChiTietNoitruYhctService == null) {
            hsbaChiTietNoitruYhctService = lookupService();
        }
        hsbaChiTietNoitruYhctService.edit(hsbaChiTietNoitruYhct);
    }

    public void remove(HsbaChiTietNoitruYhct hsbaChiTietNoitruYhct) {
        if (hsbaChiTietNoitruYhctService == null) {
            hsbaChiTietNoitruYhctService = lookupService();
        }
        hsbaChiTietNoitruYhctService.remove(hsbaChiTietNoitruYhct);
    }

    public HsbaChiTietNoitruYhct find(Object id) {
        if (hsbaChiTietNoitruYhctService == null) {
            hsbaChiTietNoitruYhctService = lookupService();
        }
        return hsbaChiTietNoitruYhctService.find(id);
    }

    public List<HsbaChiTietNoitruYhct> findAll() {
        if (hsbaChiTietNoitruYhctService == null) {
            hsbaChiTietNoitruYhctService = lookupService();
        }
        return hsbaChiTietNoitruYhctService.findAll();
    }


     public HsbaChiTietNoitruYhct findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietNoitruYhctService == null) {
            hsbaChiTietNoitruYhctService = lookupService();
        }
        return hsbaChiTietNoitruYhctService.findByHsbaCM(hsbacmmaso);
     }
}
