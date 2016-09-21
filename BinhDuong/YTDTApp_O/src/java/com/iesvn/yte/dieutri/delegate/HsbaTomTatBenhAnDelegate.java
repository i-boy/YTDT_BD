/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaTomTatBenhAnInterface;

import com.iesvn.yte.dieutri.entity.HsbaTomTatBenhAn;
import java.util.List;

/**
 *
 * @author quang
 */
public class HsbaTomTatBenhAnDelegate {
private HsbaTomTatBenhAnInterface hsbatomtatbenhanService;
public static HsbaTomTatBenhAnDelegate getInstance() {
return new HsbaTomTatBenhAnDelegate();
}
private HsbaTomTatBenhAnInterface lookupService() {
return (HsbaTomTatBenhAnInterface)LookupServiceUtils.lookupService("HsbaTomTatBenhAnFacade");
}

public     void create(HsbaTomTatBenhAn hsbaTomTatBenhAn) {
if(hsbatomtatbenhanService == null) hsbatomtatbenhanService = lookupService();
hsbatomtatbenhanService.create(hsbaTomTatBenhAn);
}

public     void edit(HsbaTomTatBenhAn hsbaTomTatBenhAn) {
if(hsbatomtatbenhanService == null) hsbatomtatbenhanService = lookupService();
hsbatomtatbenhanService.edit(hsbaTomTatBenhAn);
}

public     void remove(HsbaTomTatBenhAn hsbaTomTatBenhAn) {
if(hsbatomtatbenhanService == null) hsbatomtatbenhanService = lookupService();
hsbatomtatbenhanService.remove(hsbaTomTatBenhAn);
}

public     HsbaTomTatBenhAn find(Object id) {
if(hsbatomtatbenhanService == null) hsbatomtatbenhanService = lookupService();
return hsbatomtatbenhanService.find(id);
}

public     List<HsbaTomTatBenhAn> findAll() {
if(hsbatomtatbenhanService == null) hsbatomtatbenhanService = lookupService();
return hsbatomtatbenhanService.findAll();
}

    public HsbaTomTatBenhAn findBySoVaoVien(java.lang.String soVaoVien) {
if(hsbatomtatbenhanService == null) hsbatomtatbenhanService = lookupService();
return hsbatomtatbenhanService.findBySoVaoVien(soVaoVien);
}

    public String insert(HsbaTomTatBenhAn obj){
        if(hsbatomtatbenhanService == null) hsbatomtatbenhanService = lookupService();
        return hsbatomtatbenhanService.insert(obj);
    }
    public String update(HsbaTomTatBenhAn obj){
        if(hsbatomtatbenhanService == null) hsbatomtatbenhanService = lookupService();
        return hsbatomtatbenhanService.update(obj);
    }
    public HsbaTomTatBenhAn findByHsbattbaMa(String ma){
        if(hsbatomtatbenhanService == null) hsbatomtatbenhanService = lookupService();
        return hsbatomtatbenhanService.findByHsbattbaMa(ma);
    }
}
