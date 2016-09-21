/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.GiayCvNbBhyt;
import com.iesvn.yte.dieutri.intf.GiayCvNbBhytInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class GiayCvNbBhytDelegate {

    private GiayCvNbBhytInterface GiayCvNbBhytService;

    public static GiayCvNbBhytDelegate getInstance() {
        return new GiayCvNbBhytDelegate();
    }

    private GiayCvNbBhytInterface lookupService() {
        return (GiayCvNbBhytInterface) LookupServiceUtils.lookupService("GiayCvNbBhytFacade");
    }

    public void create(GiayCvNbBhyt obj) {
        if (GiayCvNbBhytService == null) {
            GiayCvNbBhytService = lookupService();
        }
        GiayCvNbBhytService.create(obj);
    }

    public void edit(GiayCvNbBhyt obj) {
        if (GiayCvNbBhytService == null) {
            GiayCvNbBhytService = lookupService();
        }
        GiayCvNbBhytService.edit(obj);
    }

    public void remove(GiayCvNbBhyt obj) {
        if (GiayCvNbBhytService == null) {
            GiayCvNbBhytService = lookupService();
        }
        GiayCvNbBhytService.remove(obj);
    }

    public GiayCvNbBhyt find(Object id) {
        if (GiayCvNbBhytService == null) {
            GiayCvNbBhytService = lookupService();
        }
        return GiayCvNbBhytService.find(id);
    }

    public List<GiayCvNbBhyt> findAll() {
        if (GiayCvNbBhytService == null) {
            GiayCvNbBhytService = lookupService();
        }
        return GiayCvNbBhytService.findAll();
    }

    public List<GiayCvNbBhyt> findByGiayCvNbBhyt(String maPhieu) {

        if (GiayCvNbBhytService == null) {
            GiayCvNbBhytService = lookupService();
        }
        return GiayCvNbBhytService.findByGiayCvNbBhyt(maPhieu);
    }

    public GiayCvNbBhyt findByMaThamKham(Integer maThamKham) {

        if (GiayCvNbBhytService == null) {
            GiayCvNbBhytService = lookupService();
        }
        return GiayCvNbBhytService.findByMaThamKham(maThamKham);
    }

    public String capNhatGiayCvNbBhyt(GiayCvNbBhyt obj, String sMaPhieu){

        if (GiayCvNbBhytService == null) {
            GiayCvNbBhytService = lookupService();
        }
        return GiayCvNbBhytService.capNhatGiayCvNbBhyt(obj, sMaPhieu);
    }
}
