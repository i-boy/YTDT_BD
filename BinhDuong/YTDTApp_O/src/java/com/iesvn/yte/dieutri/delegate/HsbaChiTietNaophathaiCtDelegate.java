/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietNaophathaiCtInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNaophathaiCt;

import java.util.List;
/**
 *
 * @author quang
 */
public class HsbaChiTietNaophathaiCtDelegate {

    private HsbaChiTietNaophathaiCtInterface hsbaChiTietNaophathaiCtService;

    public static HsbaChiTietNaophathaiCtDelegate getInstance() {
        return new HsbaChiTietNaophathaiCtDelegate();
    }

    private HsbaChiTietNaophathaiCtInterface lookupService() {
        return (HsbaChiTietNaophathaiCtInterface) LookupServiceUtils.lookupService("HsbaChiTietNaophathaiCtFacade");
    }

    public HsbaChiTietNaophathaiCt create(HsbaChiTietNaophathaiCt hsbaChiTietNaophathaiCt) {
        if (hsbaChiTietNaophathaiCtService == null) {
            hsbaChiTietNaophathaiCtService = lookupService();
        }
        return hsbaChiTietNaophathaiCtService.create(hsbaChiTietNaophathaiCt);
    }

    public void edit(HsbaChiTietNaophathaiCt hsbaChiTietNaophathaiCt) {
        if (hsbaChiTietNaophathaiCtService == null) {
            hsbaChiTietNaophathaiCtService = lookupService();
        }
        hsbaChiTietNaophathaiCtService.edit(hsbaChiTietNaophathaiCt);
    }

    public void remove(HsbaChiTietNaophathaiCt hsbaChiTietNaophathaiCt) {
        if (hsbaChiTietNaophathaiCtService == null) {
            hsbaChiTietNaophathaiCtService = lookupService();
        }
        hsbaChiTietNaophathaiCtService.remove(hsbaChiTietNaophathaiCt);
    }

    public HsbaChiTietNaophathaiCt find(Object id) {
        if (hsbaChiTietNaophathaiCtService == null) {
            hsbaChiTietNaophathaiCtService = lookupService();
        }
        return hsbaChiTietNaophathaiCtService.find(id);
    }

    public List<HsbaChiTietNaophathaiCt> findAll() {
        if (hsbaChiTietNaophathaiCtService == null) {
            hsbaChiTietNaophathaiCtService = lookupService();
        }
        return hsbaChiTietNaophathaiCtService.findAll();
    }


      public List<HsbaChiTietNaophathaiCt> findByHsbaChiTietNaophathaiMaso(Integer hsbaChiTietNaophathaiMa){
          if (hsbaChiTietNaophathaiCtService == null) {
            hsbaChiTietNaophathaiCtService = lookupService();
        }
        return hsbaChiTietNaophathaiCtService.findByHsbaChiTietNaophathaiMaso(hsbaChiTietNaophathaiMa);
     }
}
