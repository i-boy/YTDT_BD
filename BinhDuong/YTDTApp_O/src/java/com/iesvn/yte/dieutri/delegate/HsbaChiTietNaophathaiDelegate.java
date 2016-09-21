/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietNaophathaiInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNaophathai;

import java.util.List;
/**
 *
 * @author quang
 */
public class HsbaChiTietNaophathaiDelegate {

    private HsbaChiTietNaophathaiInterface hsbaChiTietNaophathaiService;

    public static HsbaChiTietNaophathaiDelegate getInstance() {
        return new HsbaChiTietNaophathaiDelegate();
    }

    private HsbaChiTietNaophathaiInterface lookupService() {
        return (HsbaChiTietNaophathaiInterface) LookupServiceUtils.lookupService("HsbaChiTietNaophathaiFacade");
    }

    public HsbaChiTietNaophathai create(HsbaChiTietNaophathai hsbaChiTietNaophathai) {
        if (hsbaChiTietNaophathaiService == null) {
            hsbaChiTietNaophathaiService = lookupService();
        }
        return hsbaChiTietNaophathaiService.create(hsbaChiTietNaophathai);
    }

    public void edit(HsbaChiTietNaophathai hsbaChiTietNaophathai) {
        if (hsbaChiTietNaophathaiService == null) {
            hsbaChiTietNaophathaiService = lookupService();
        }
        hsbaChiTietNaophathaiService.edit(hsbaChiTietNaophathai);
    }

    public void remove(HsbaChiTietNaophathai hsbaChiTietNaophathai) {
        if (hsbaChiTietNaophathaiService == null) {
            hsbaChiTietNaophathaiService = lookupService();
        }
        hsbaChiTietNaophathaiService.remove(hsbaChiTietNaophathai);
    }

    public HsbaChiTietNaophathai find(Object id) {
        if (hsbaChiTietNaophathaiService == null) {
            hsbaChiTietNaophathaiService = lookupService();
        }
        return hsbaChiTietNaophathaiService.find(id);
    }

    public List<HsbaChiTietNaophathai> findAll() {
        if (hsbaChiTietNaophathaiService == null) {
            hsbaChiTietNaophathaiService = lookupService();
        }
        return hsbaChiTietNaophathaiService.findAll();
    }


     public HsbaChiTietNaophathai findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietNaophathaiService == null) {
            hsbaChiTietNaophathaiService = lookupService();
        }
        return hsbaChiTietNaophathaiService.findByHsbaCM(hsbacmmaso);
     }
}
