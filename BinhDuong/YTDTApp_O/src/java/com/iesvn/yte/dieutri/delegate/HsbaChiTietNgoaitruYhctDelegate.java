/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietNgoaitruYhctInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNgoaitruYhct;

import java.util.List;
/**
 *
 * @author quang
 */
public class HsbaChiTietNgoaitruYhctDelegate {

    private HsbaChiTietNgoaitruYhctInterface hsbaChiTietNgoaitruYhctService;

    public static HsbaChiTietNgoaitruYhctDelegate getInstance() {
        return new HsbaChiTietNgoaitruYhctDelegate();
    }

    private HsbaChiTietNgoaitruYhctInterface lookupService() {
        return (HsbaChiTietNgoaitruYhctInterface) LookupServiceUtils.lookupService("HsbaChiTietNgoaitruYhctFacade");
    }

    public void create(HsbaChiTietNgoaitruYhct hsbaChiTietNgoaitruYhct) {
        if (hsbaChiTietNgoaitruYhctService == null) {
            hsbaChiTietNgoaitruYhctService = lookupService();
        }
        hsbaChiTietNgoaitruYhctService.create(hsbaChiTietNgoaitruYhct);
    }

    public void edit(HsbaChiTietNgoaitruYhct hsbaChiTietNgoaitruYhct) {
        if (hsbaChiTietNgoaitruYhctService == null) {
            hsbaChiTietNgoaitruYhctService = lookupService();
        }
        hsbaChiTietNgoaitruYhctService.edit(hsbaChiTietNgoaitruYhct);
    }

    public void remove(HsbaChiTietNgoaitruYhct hsbaChiTietNgoaitruYhct) {
        if (hsbaChiTietNgoaitruYhctService == null) {
            hsbaChiTietNgoaitruYhctService = lookupService();
        }
        hsbaChiTietNgoaitruYhctService.remove(hsbaChiTietNgoaitruYhct);
    }

    public HsbaChiTietNgoaitruYhct find(Object id) {
        if (hsbaChiTietNgoaitruYhctService == null) {
            hsbaChiTietNgoaitruYhctService = lookupService();
        }
        return hsbaChiTietNgoaitruYhctService.find(id);
    }

    public List<HsbaChiTietNgoaitruYhct> findAll() {
        if (hsbaChiTietNgoaitruYhctService == null) {
            hsbaChiTietNgoaitruYhctService = lookupService();
        }
        return hsbaChiTietNgoaitruYhctService.findAll();
    }


     public HsbaChiTietNgoaitruYhct findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietNgoaitruYhctService == null) {
            hsbaChiTietNgoaitruYhctService = lookupService();
        }
        return hsbaChiTietNgoaitruYhctService.findByHsbaCM(hsbacmmaso);
     }
}
