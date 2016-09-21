/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietSosinhInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietSosinh;

import java.util.List;
/**
 *
 * @author quang
 */
public class HsbaChiTietSosinhDelegate {

    private HsbaChiTietSosinhInterface hsbaChiTietSosinhService;

    public static HsbaChiTietSosinhDelegate getInstance() {
        return new HsbaChiTietSosinhDelegate();
    }

    private HsbaChiTietSosinhInterface lookupService() {
        return (HsbaChiTietSosinhInterface) LookupServiceUtils.lookupService("HsbaChiTietSosinhFacade");
    }

    public void create(HsbaChiTietSosinh hsbaChiTietSosinh) {
        if (hsbaChiTietSosinhService == null) {
            hsbaChiTietSosinhService = lookupService();
        }
        hsbaChiTietSosinhService.create(hsbaChiTietSosinh);
    }

    public void edit(HsbaChiTietSosinh hsbaChiTietSosinh) {
        if (hsbaChiTietSosinhService == null) {
            hsbaChiTietSosinhService = lookupService();
        }
        hsbaChiTietSosinhService.edit(hsbaChiTietSosinh);
    }

    public void remove(HsbaChiTietSosinh hsbaChiTietSosinh) {
        if (hsbaChiTietSosinhService == null) {
            hsbaChiTietSosinhService = lookupService();
        }
        hsbaChiTietSosinhService.remove(hsbaChiTietSosinh);
    }

    public HsbaChiTietSosinh find(Object id) {
        if (hsbaChiTietSosinhService == null) {
            hsbaChiTietSosinhService = lookupService();
        }
        return hsbaChiTietSosinhService.find(id);
    }

    public List<HsbaChiTietSosinh> findAll() {
        if (hsbaChiTietSosinhService == null) {
            hsbaChiTietSosinhService = lookupService();
        }
        return hsbaChiTietSosinhService.findAll();
    }


     public HsbaChiTietSosinh findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietSosinhService == null) {
            hsbaChiTietSosinhService = lookupService();
        }
        return hsbaChiTietSosinhService.findByHsbaCM(hsbacmmaso);
     }
}
