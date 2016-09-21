/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChiTietPhukhoaInterface;

import com.iesvn.yte.dieutri.entity.HsbaChiTietPhukhoa;

import java.util.List;
/**
 *
 * @author quang
 */
public class HsbaChiTietPhukhoaDelegate {

    private HsbaChiTietPhukhoaInterface hsbaChiTietPhukhoaService;

    public static HsbaChiTietPhukhoaDelegate getInstance() {
        return new HsbaChiTietPhukhoaDelegate();
    }

    private HsbaChiTietPhukhoaInterface lookupService() {
        return (HsbaChiTietPhukhoaInterface) LookupServiceUtils.lookupService("HsbaChiTietPhukhoaFacade");
    }

    public void create(HsbaChiTietPhukhoa hsbaChiTietPhukhoa) {
        if (hsbaChiTietPhukhoaService == null) {
            hsbaChiTietPhukhoaService = lookupService();
        }
        hsbaChiTietPhukhoaService.create(hsbaChiTietPhukhoa);
    }

    public void edit(HsbaChiTietPhukhoa hsbaChiTietPhukhoa) {
        if (hsbaChiTietPhukhoaService == null) {
            hsbaChiTietPhukhoaService = lookupService();
        }
        hsbaChiTietPhukhoaService.edit(hsbaChiTietPhukhoa);
    }

    public void remove(HsbaChiTietPhukhoa hsbaChiTietPhukhoa) {
        if (hsbaChiTietPhukhoaService == null) {
            hsbaChiTietPhukhoaService = lookupService();
        }
        hsbaChiTietPhukhoaService.remove(hsbaChiTietPhukhoa);
    }

    public HsbaChiTietPhukhoa find(Object id) {
        if (hsbaChiTietPhukhoaService == null) {
            hsbaChiTietPhukhoaService = lookupService();
        }
        return hsbaChiTietPhukhoaService.find(id);
    }

    public List<HsbaChiTietPhukhoa> findAll() {
        if (hsbaChiTietPhukhoaService == null) {
            hsbaChiTietPhukhoaService = lookupService();
        }
        return hsbaChiTietPhukhoaService.findAll();
    }


     public HsbaChiTietPhukhoa findByHsbaCM(Integer hsbacmmaso) {
          if (hsbaChiTietPhukhoaService == null) {
            hsbaChiTietPhukhoaService = lookupService();
        }
        return hsbaChiTietPhukhoaService.findByHsbaCM(hsbacmmaso);
     }
}
