/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietTmhInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietTmh;

import java.util.List;
/**
 *
 * @author quang
 */
public class HsbaChiTietTmhDelegate {

    private HsbaChiTietTmhInterface hsbaChiTietTmhService;

    public static HsbaChiTietTmhDelegate getInstance() {
        return new HsbaChiTietTmhDelegate();
    }

    private HsbaChiTietTmhInterface lookupService() {
        return (HsbaChiTietTmhInterface) LookupServiceUtils.lookupService("HsbaChiTietTmhFacade");
    }

    public void create(HsbaChiTietTmh hsbaChiTietTmh) {
        if (hsbaChiTietTmhService == null) {
            hsbaChiTietTmhService = lookupService();
        }
        hsbaChiTietTmhService.create(hsbaChiTietTmh);
    }

    public void edit(HsbaChiTietTmh HsbaChiTietTmh) {
        if (hsbaChiTietTmhService == null) {
            hsbaChiTietTmhService = lookupService();
        }
        hsbaChiTietTmhService.edit(HsbaChiTietTmh);
    }

    public void remove(HsbaChiTietTmh HsbaChiTietTmh) {
        if (hsbaChiTietTmhService == null) {
            hsbaChiTietTmhService = lookupService();
        }
        hsbaChiTietTmhService.remove(HsbaChiTietTmh);
    }

    public HsbaChiTietTmh find(Object id) {
        if (hsbaChiTietTmhService == null) {
            hsbaChiTietTmhService = lookupService();
        }
        return hsbaChiTietTmhService.find(id);
    }

    public List<HsbaChiTietTmh> findAll() {
        if (hsbaChiTietTmhService == null) {
            hsbaChiTietTmhService = lookupService();
        }
        return hsbaChiTietTmhService.findAll();
    }


     public HsbaChiTietTmh findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietTmhService == null) {
            hsbaChiTietTmhService = lookupService();
        }
        return hsbaChiTietTmhService.findByHsbaCM(hsbacmmaso);
     }
}
