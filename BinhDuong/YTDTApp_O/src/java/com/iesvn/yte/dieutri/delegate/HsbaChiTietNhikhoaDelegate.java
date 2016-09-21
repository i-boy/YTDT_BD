/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietNhikhoaInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNhikhoa;

import java.util.List;
/**
 *
 * @author quang
 */
public class HsbaChiTietNhikhoaDelegate {

    private HsbaChiTietNhikhoaInterface hsbaChiTietNhikhoaService;

    public static HsbaChiTietNhikhoaDelegate getInstance() {
        return new HsbaChiTietNhikhoaDelegate();
    }

    private HsbaChiTietNhikhoaInterface lookupService() {
        return (HsbaChiTietNhikhoaInterface) LookupServiceUtils.lookupService("HsbaChiTietNhikhoaFacade");
    }

    public void create(HsbaChiTietNhikhoa hsbaChiTietNhikhoa) {
        if (hsbaChiTietNhikhoaService == null) {
            hsbaChiTietNhikhoaService = lookupService();
        }
        hsbaChiTietNhikhoaService.create(hsbaChiTietNhikhoa);
    }

    public void edit(HsbaChiTietNhikhoa hsbaChiTietNhikhoa) {
        if (hsbaChiTietNhikhoaService == null) {
            hsbaChiTietNhikhoaService = lookupService();
        }
        hsbaChiTietNhikhoaService.edit(hsbaChiTietNhikhoa);
    }

    public void remove(HsbaChiTietNhikhoa hsbaChiTietNhikhoa) {
        if (hsbaChiTietNhikhoaService == null) {
            hsbaChiTietNhikhoaService = lookupService();
        }
        hsbaChiTietNhikhoaService.remove(hsbaChiTietNhikhoa);
    }

    public HsbaChiTietNhikhoa find(Object id) {
        if (hsbaChiTietNhikhoaService == null) {
            hsbaChiTietNhikhoaService = lookupService();
        }
        return hsbaChiTietNhikhoaService.find(id);
    }

    public List<HsbaChiTietNhikhoa> findAll() {
        if (hsbaChiTietNhikhoaService == null) {
            hsbaChiTietNhikhoaService = lookupService();
        }
        return hsbaChiTietNhikhoaService.findAll();
    }


     public HsbaChiTietNhikhoa findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietNhikhoaService == null) {
            hsbaChiTietNhikhoaService = lookupService();
        }
        return hsbaChiTietNhikhoaService.findByHsbaCM(hsbacmmaso);
     }
}
