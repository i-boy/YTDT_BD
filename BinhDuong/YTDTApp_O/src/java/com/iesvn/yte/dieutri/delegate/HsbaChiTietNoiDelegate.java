/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietNoiInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNoi;

import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaChiTietNoiDelegate {

    private HsbaChiTietNoiInterface hsbaChiTietNoiService;

    public static HsbaChiTietNoiDelegate getInstance() {
        return new HsbaChiTietNoiDelegate();
    }

    private HsbaChiTietNoiInterface lookupService() {
        return (HsbaChiTietNoiInterface) LookupServiceUtils.lookupService("HsbaChiTietNoiFacade");
    }

    public void create(HsbaChiTietNoi hsbaChiTietNoi) {
        if (hsbaChiTietNoiService == null) {
            hsbaChiTietNoiService = lookupService();
        }
        hsbaChiTietNoiService.create(hsbaChiTietNoi);
    }

    public void edit(HsbaChiTietNoi HsbaChiTietNoi) {
        if (hsbaChiTietNoiService == null) {
            hsbaChiTietNoiService = lookupService();
        }
        hsbaChiTietNoiService.edit(HsbaChiTietNoi);
    }

    public void remove(HsbaChiTietNoi HsbaChiTietNoi) {
        if (hsbaChiTietNoiService == null) {
            hsbaChiTietNoiService = lookupService();
        }
        hsbaChiTietNoiService.remove(HsbaChiTietNoi);
    }

    public HsbaChiTietNoi find(Object id) {
        if (hsbaChiTietNoiService == null) {
            hsbaChiTietNoiService = lookupService();
        }
        return hsbaChiTietNoiService.find(id);
    }

    public List<HsbaChiTietNoi> findAll() {
        if (hsbaChiTietNoiService == null) {
            hsbaChiTietNoiService = lookupService();
        }
        return hsbaChiTietNoiService.findAll();
    }

    
     public HsbaChiTietNoi findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietNoiService == null) {
            hsbaChiTietNoiService = lookupService();
        }
        return hsbaChiTietNoiService.findByHsbaCM(hsbacmmaso);
     }
}






