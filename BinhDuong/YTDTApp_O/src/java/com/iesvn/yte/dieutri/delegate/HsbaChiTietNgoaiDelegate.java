/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietNgoaiInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNgoai;

import java.util.List;


/**
 *
 * @author quang
 */
public class HsbaChiTietNgoaiDelegate {

    private HsbaChiTietNgoaiInterface hsbaChiTietNgoaiService;

    public static HsbaChiTietNgoaiDelegate getInstance() {
        return new HsbaChiTietNgoaiDelegate();
    }

    private HsbaChiTietNgoaiInterface lookupService() {
        return (HsbaChiTietNgoaiInterface) LookupServiceUtils.lookupService("HsbaChiTietNgoaiFacade");
    }

    public void create(HsbaChiTietNgoai hsbaChiTietNgoai) {
        if (hsbaChiTietNgoaiService == null) {
            hsbaChiTietNgoaiService = lookupService();
        }
        hsbaChiTietNgoaiService.create(hsbaChiTietNgoai);
    }

    public void edit(HsbaChiTietNgoai HsbaChiTietNgoai) {
        if (hsbaChiTietNgoaiService == null) {
            hsbaChiTietNgoaiService = lookupService();
        }
        hsbaChiTietNgoaiService.edit(HsbaChiTietNgoai);
    }

    public void remove(HsbaChiTietNgoai HsbaChiTietNgoai) {
        if (hsbaChiTietNgoaiService == null) {
            hsbaChiTietNgoaiService = lookupService();
        }
        hsbaChiTietNgoaiService.remove(HsbaChiTietNgoai);
    }

    public HsbaChiTietNgoai find(Object id) {
        if (hsbaChiTietNgoaiService == null) {
            hsbaChiTietNgoaiService = lookupService();
        }
        return hsbaChiTietNgoaiService.find(id);
    }

    public List<HsbaChiTietNgoai> findAll() {
        if (hsbaChiTietNgoaiService == null) {
            hsbaChiTietNgoaiService = lookupService();
        }
        return hsbaChiTietNgoaiService.findAll();
    }


     public HsbaChiTietNgoai findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietNgoaiService == null) {
            hsbaChiTietNgoaiService = lookupService();
        }
        return hsbaChiTietNgoaiService.findByHsbaCM(hsbacmmaso);
     }
}






