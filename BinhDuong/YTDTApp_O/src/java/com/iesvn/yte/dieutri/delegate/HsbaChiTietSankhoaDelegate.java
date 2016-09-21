/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietSankhoaInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietSankhoa;

import java.util.List;
/**
 *
 * @author quang
 */
public class HsbaChiTietSankhoaDelegate {

    private HsbaChiTietSankhoaInterface hsbaChiTietSankhoaService;

    public static HsbaChiTietSankhoaDelegate getInstance() {
        return new HsbaChiTietSankhoaDelegate();
    }

    private HsbaChiTietSankhoaInterface lookupService() {
        return (HsbaChiTietSankhoaInterface) LookupServiceUtils.lookupService("HsbaChiTietSankhoaFacade");
    }

    public void create(HsbaChiTietSankhoa hsbaChiTietSankhoa) {
        if (hsbaChiTietSankhoaService == null) {
            hsbaChiTietSankhoaService = lookupService();
        }
        hsbaChiTietSankhoaService.create(hsbaChiTietSankhoa);
    }

    public void edit(HsbaChiTietSankhoa hsbaChiTietSankhoa) {
        if (hsbaChiTietSankhoaService == null) {
            hsbaChiTietSankhoaService = lookupService();
        }
        hsbaChiTietSankhoaService.edit(hsbaChiTietSankhoa);
    }

    public void remove(HsbaChiTietSankhoa hsbaChiTietSankhoa) {
        if (hsbaChiTietSankhoaService == null) {
            hsbaChiTietSankhoaService = lookupService();
        }
        hsbaChiTietSankhoaService.remove(hsbaChiTietSankhoa);
    }

    public HsbaChiTietSankhoa find(Object id) {
        if (hsbaChiTietSankhoaService == null) {
            hsbaChiTietSankhoaService = lookupService();
        }
        return hsbaChiTietSankhoaService.find(id);
    }

    public List<HsbaChiTietSankhoa> findAll() {
        if (hsbaChiTietSankhoaService == null) {
            hsbaChiTietSankhoaService = lookupService();
        }
        return hsbaChiTietSankhoaService.findAll();
    }


     public HsbaChiTietSankhoa findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietSankhoaService == null) {
            hsbaChiTietSankhoaService = lookupService();
        }
        return hsbaChiTietSankhoaService.findByHsbaCM(hsbacmmaso);
     }
}
