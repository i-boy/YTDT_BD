/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaGiayTomTatInterface;

import com.iesvn.yte.dieutri.entity.HsbaGiayTomTat;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaGiayTomTatDelegate {

    private HsbaGiayTomTatInterface hsbagiaytomtatService;

    public static HsbaGiayTomTatDelegate getInstance() {
        return new HsbaGiayTomTatDelegate();
    }

    private HsbaGiayTomTatInterface lookupService() {
        return (HsbaGiayTomTatInterface) LookupServiceUtils.lookupService("HsbaGiayTomTatFacade");
    }

    public String create(HsbaGiayTomTat hsbaGiayTomTat) {
        if (hsbagiaytomtatService == null) {
            hsbagiaytomtatService = lookupService();
        }
        return hsbagiaytomtatService.create(hsbaGiayTomTat);
    }

    public void edit(HsbaGiayTomTat hsbaGiayTomTat) {
        if (hsbagiaytomtatService == null) {
            hsbagiaytomtatService = lookupService();
        }
        hsbagiaytomtatService.edit(hsbaGiayTomTat);
    }

    public void remove(HsbaGiayTomTat hsbaGiayTomTat) {
        if (hsbagiaytomtatService == null) {
            hsbagiaytomtatService = lookupService();
        }
        hsbagiaytomtatService.remove(hsbaGiayTomTat);
    }

    public HsbaGiayTomTat find(Object id) {
        if (hsbagiaytomtatService == null) {
            hsbagiaytomtatService = lookupService();
        }
        return hsbagiaytomtatService.find(id);
    }

    public List<HsbaGiayTomTat> findAll() {
        if (hsbagiaytomtatService == null) {
            hsbagiaytomtatService = lookupService();
        }
        return hsbagiaytomtatService.findAll();
    }
    
    public HsbaGiayTomTat findByMa(String maGiay) {
        if (hsbagiaytomtatService == null) {
            hsbagiaytomtatService = lookupService();
        }
        return hsbagiaytomtatService.findByMa(maGiay);
    }
    
    public HsbaGiayTomTat findBySovaovien(String sovaovien) {
        if (hsbagiaytomtatService == null) {
            hsbagiaytomtatService = lookupService();
        }
        return hsbagiaytomtatService.findBySovaovien(sovaovien);
    }
}


