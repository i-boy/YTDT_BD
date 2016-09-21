/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaGiayChungNhanInterface;

import com.iesvn.yte.dieutri.entity.HsbaGiayChungNhan;
import java.util.List;

/**
 *
 * @author quang
 */
public class HsbaGiayChungNhanDelegate {
private HsbaGiayChungNhanInterface hsbagiaychungnhanService;
public static HsbaGiayChungNhanDelegate getInstance() {
return new HsbaGiayChungNhanDelegate();
}
private HsbaGiayChungNhanInterface lookupService() {
return (HsbaGiayChungNhanInterface)LookupServiceUtils.lookupService("HsbaGiayChungNhanFacade");
}

public     void create(HsbaGiayChungNhan hsbaGiayChungNhan) {
if(hsbagiaychungnhanService == null) hsbagiaychungnhanService = lookupService();
hsbagiaychungnhanService.create(hsbaGiayChungNhan);
}

public     void edit(HsbaGiayChungNhan hsbaGiayChungNhan) {
if(hsbagiaychungnhanService == null) hsbagiaychungnhanService = lookupService();
hsbagiaychungnhanService.edit(hsbaGiayChungNhan);
}

public     void remove(HsbaGiayChungNhan hsbaGiayChungNhan) {
if(hsbagiaychungnhanService == null) hsbagiaychungnhanService = lookupService();
hsbagiaychungnhanService.remove(hsbaGiayChungNhan);
}

public     HsbaGiayChungNhan find(Object id) {
if(hsbagiaychungnhanService == null) hsbagiaychungnhanService = lookupService();
return hsbagiaychungnhanService.find(id);
}

public     List<HsbaGiayChungNhan> findAll() {
if(hsbagiaychungnhanService == null) hsbagiaychungnhanService = lookupService();
return hsbagiaychungnhanService.findAll();
}

    public HsbaGiayChungNhan findBySoVaoVien(java.lang.String soVaoVien) {
if(hsbagiaychungnhanService == null) hsbagiaychungnhanService = lookupService();
return hsbagiaychungnhanService.findBySoVaoVien(soVaoVien);
}

    public String insert(HsbaGiayChungNhan obj){
        if(hsbagiaychungnhanService == null) hsbagiaychungnhanService = lookupService();
        return hsbagiaychungnhanService.insert(obj);
    }
    public String update(HsbaGiayChungNhan obj){
        if(hsbagiaychungnhanService == null) hsbagiaychungnhanService = lookupService();
        return hsbagiaychungnhanService.update(obj);
    }
    public HsbaGiayChungNhan findByHsbagcnMa(String ma){
        if(hsbagiaychungnhanService == null) hsbagiaychungnhanService = lookupService();
        return hsbagiaychungnhanService.findByHsbagcnMa(ma);
    }

}


