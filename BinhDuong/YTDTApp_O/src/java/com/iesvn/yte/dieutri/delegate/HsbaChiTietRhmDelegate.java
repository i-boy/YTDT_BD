/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietRhmInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietRhm;

import java.util.List;
/**
 *
 * @author quang
 */
public class HsbaChiTietRhmDelegate {

    private HsbaChiTietRhmInterface hsbaChiTietRhmService;

    public static HsbaChiTietRhmDelegate getInstance() {
        return new HsbaChiTietRhmDelegate();
    }

    private HsbaChiTietRhmInterface lookupService() {
        return (HsbaChiTietRhmInterface) LookupServiceUtils.lookupService("HsbaChiTietRhmFacade");
    }

    public void create(HsbaChiTietRhm hsbaChiTietRhm) {
        if (hsbaChiTietRhmService == null) {
            hsbaChiTietRhmService = lookupService();
        }
        hsbaChiTietRhmService.create(hsbaChiTietRhm);
    }

    public void edit(HsbaChiTietRhm HsbaChiTietRhm) {
        if (hsbaChiTietRhmService == null) {
            hsbaChiTietRhmService = lookupService();
        }
        hsbaChiTietRhmService.edit(HsbaChiTietRhm);
    }

    public void remove(HsbaChiTietRhm HsbaChiTietRhm) {
        if (hsbaChiTietRhmService == null) {
            hsbaChiTietRhmService = lookupService();
        }
        hsbaChiTietRhmService.remove(HsbaChiTietRhm);
    }

    public HsbaChiTietRhm find(Object id) {
        if (hsbaChiTietRhmService == null) {
            hsbaChiTietRhmService = lookupService();
        }
        return hsbaChiTietRhmService.find(id);
    }

    public List<HsbaChiTietRhm> findAll() {
        if (hsbaChiTietRhmService == null) {
            hsbaChiTietRhmService = lookupService();
        }
        return hsbaChiTietRhmService.findAll();
    }


     public HsbaChiTietRhm findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietRhmService == null) {
            hsbaChiTietRhmService = lookupService();
        }
        return hsbaChiTietRhmService.findByHsbaCM(hsbacmmaso);
     }
}
