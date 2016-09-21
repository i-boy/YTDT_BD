/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;


import com.iesvn.yte.dieutri.entity.HsbaToDieuTri;
import com.iesvn.yte.dieutri.intf.HsbaToDieuTriInterface;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaToDieuTriDelegate {

    private HsbaToDieuTriInterface hsbaToDieuTriService;

    public static HsbaToDieuTriDelegate getInstance() {
        return new HsbaToDieuTriDelegate();
    }

    private HsbaToDieuTriInterface lookupService() {
        return (HsbaToDieuTriInterface) LookupServiceUtils.lookupService("HsbaToDieuTriFacade");
    }

    public void create(HsbaToDieuTri tdt) {
        if (hsbaToDieuTriService == null) {
            hsbaToDieuTriService = lookupService();
        }
        hsbaToDieuTriService.create(tdt);
    }

    public void edit(HsbaToDieuTri tdt) {
        if (hsbaToDieuTriService == null) {
            hsbaToDieuTriService = lookupService();
        }
        hsbaToDieuTriService.edit(tdt);
    }

    public void remove(HsbaToDieuTri tdt) {
        if (hsbaToDieuTriService == null) {
            hsbaToDieuTriService = lookupService();
        }
        hsbaToDieuTriService.remove(tdt);
    }

    public HsbaToDieuTri find(Object id) {
        if (hsbaToDieuTriService == null) {
            hsbaToDieuTriService = lookupService();
        }
        return hsbaToDieuTriService.find(id);
    }

    public List<HsbaToDieuTri> findAll() {
        if (hsbaToDieuTriService == null) {
            hsbaToDieuTriService = lookupService();
        }
        return hsbaToDieuTriService.findAll();
    }

    public List<HsbaToDieuTri> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) {
      if (hsbaToDieuTriService == null) {
            hsbaToDieuTriService = lookupService();
        }
        return hsbaToDieuTriService.findBySoVaoVienAndKhoaMa(soVaoVien,khoaMa);
    }
     public List<HsbaToDieuTri> findBySoVaoVien(String soVaoVien) {
           if (hsbaToDieuTriService == null) {
            hsbaToDieuTriService = lookupService();
        }
        return hsbaToDieuTriService.findAll();
     }

     public String createToDieuTri(List<HsbaToDieuTri> listToDieuTri, String soVaoVien, String khoaMa) {
  if (hsbaToDieuTriService == null) {
            hsbaToDieuTriService = lookupService();
        }
        return hsbaToDieuTriService.createToDieuTri(listToDieuTri,soVaoVien,khoaMa);
     }
}
