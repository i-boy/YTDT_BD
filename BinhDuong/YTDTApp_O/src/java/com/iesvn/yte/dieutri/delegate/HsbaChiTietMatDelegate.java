/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietMatInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietMat;

import java.util.List;
/**
 *
 * @author quang
 */
public class HsbaChiTietMatDelegate {

    private HsbaChiTietMatInterface hsbaChiTietMatService;

    public static HsbaChiTietMatDelegate getInstance() {
        return new HsbaChiTietMatDelegate();
    }

    private HsbaChiTietMatInterface lookupService() {
        return (HsbaChiTietMatInterface) LookupServiceUtils.lookupService("HsbaChiTietMatFacade");
    }

    public void create(HsbaChiTietMat hsbaChiTietMat) {
        if (hsbaChiTietMatService == null) {
            hsbaChiTietMatService = lookupService();
        }
        hsbaChiTietMatService.create(hsbaChiTietMat);
    }

    public void edit(HsbaChiTietMat HsbaChiTietMat) {
        if (hsbaChiTietMatService == null) {
            hsbaChiTietMatService = lookupService();
        }
        hsbaChiTietMatService.edit(HsbaChiTietMat);
    }

    public void remove(HsbaChiTietMat HsbaChiTietMat) {
        if (hsbaChiTietMatService == null) {
            hsbaChiTietMatService = lookupService();
        }
        hsbaChiTietMatService.remove(HsbaChiTietMat);
    }

    public HsbaChiTietMat find(Object id) {
        if (hsbaChiTietMatService == null) {
            hsbaChiTietMatService = lookupService();
        }
        return hsbaChiTietMatService.find(id);
    }

    public List<HsbaChiTietMat> findAll() {
        if (hsbaChiTietMatService == null) {
            hsbaChiTietMatService = lookupService();
        }
        return hsbaChiTietMatService.findAll();
    }


     public HsbaChiTietMat findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietMatService == null) {
            hsbaChiTietMatService = lookupService();
        }
        return hsbaChiTietMatService.findByHsbaCM(hsbacmmaso);
     }
}
