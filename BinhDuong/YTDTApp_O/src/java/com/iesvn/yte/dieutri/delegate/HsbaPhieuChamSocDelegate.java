/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.HsbaPhieuChamSoc;
import com.iesvn.yte.dieutri.intf.HsbaPhieuChamSocInterface;
import com.iesvn.yte.locator.LookupServiceUtils;



import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaPhieuChamSocDelegate {

    private HsbaPhieuChamSocInterface hsbaPhieuChamSocService;

    public static HsbaPhieuChamSocDelegate getInstance() {
        return new HsbaPhieuChamSocDelegate();
    }

    private HsbaPhieuChamSocInterface lookupService() {
        return (HsbaPhieuChamSocInterface) LookupServiceUtils.lookupService("HsbaPhieuChamSocFacade");
    }

    public void create(HsbaPhieuChamSoc pcs) {
        if (hsbaPhieuChamSocService == null) {
            hsbaPhieuChamSocService = lookupService();
        }
        hsbaPhieuChamSocService.create(pcs);
    }

    public void edit(HsbaPhieuChamSoc pcs) {
        if (hsbaPhieuChamSocService == null) {
            hsbaPhieuChamSocService = lookupService();
        }
        hsbaPhieuChamSocService.edit(pcs);
    }

    public void remove(HsbaPhieuChamSoc tdt) {
        if (hsbaPhieuChamSocService == null) {
            hsbaPhieuChamSocService = lookupService();
        }
        hsbaPhieuChamSocService.remove(tdt);
    }

    public HsbaPhieuChamSoc find(Object id) {
        if (hsbaPhieuChamSocService == null) {
            hsbaPhieuChamSocService = lookupService();
        }
        return hsbaPhieuChamSocService.find(id);
    }

    public List<HsbaPhieuChamSoc> findAll() {
         if (hsbaPhieuChamSocService == null) {
            hsbaPhieuChamSocService = lookupService();
        }
        return hsbaPhieuChamSocService.findAll();
    }

    public List<HsbaPhieuChamSoc> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) {
      if (hsbaPhieuChamSocService == null) {
            hsbaPhieuChamSocService = lookupService();
        }
        return hsbaPhieuChamSocService.findBySoVaoVienAndKhoaMa(soVaoVien,khoaMa);
    }
     public List<HsbaPhieuChamSoc> findBySoVaoVien(String soVaoVien) {
         if (hsbaPhieuChamSocService == null) {
            hsbaPhieuChamSocService = lookupService();
        }
        return hsbaPhieuChamSocService.findAll();
     }

     public String createToDieuTri(List<HsbaPhieuChamSoc> listToDieuTri, String soVaoVien, String khoaMa) {
  if (hsbaPhieuChamSocService == null) {
            hsbaPhieuChamSocService = lookupService();
        }
        return hsbaPhieuChamSocService.createPhieuChamSoc(listToDieuTri,soVaoVien,khoaMa);
     }
}
