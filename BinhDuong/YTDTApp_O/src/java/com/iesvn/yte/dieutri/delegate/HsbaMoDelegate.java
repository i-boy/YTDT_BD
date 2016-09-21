/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaMoInterface;

import com.iesvn.yte.dieutri.entity.HsbaMo;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaMoDelegate {
private HsbaMoInterface hsbamoService;
public static HsbaMoDelegate getInstance() {
return new HsbaMoDelegate();
}
private HsbaMoInterface lookupService() {
return (HsbaMoInterface)LookupServiceUtils.lookupService("HsbaMoFacade");
}

public     void create(HsbaMo hsbaMo) {
if(hsbamoService == null) hsbamoService = lookupService();
hsbamoService.create(hsbaMo);
}

public     void edit(HsbaMo hsbaMo) {
if(hsbamoService == null) hsbamoService = lookupService();
hsbamoService.edit(hsbaMo);
}

public     void remove(HsbaMo hsbaMo) {
if(hsbamoService == null) hsbamoService = lookupService();
hsbamoService.remove(hsbaMo);
}

public     HsbaMo find(Object id) {
if(hsbamoService == null) hsbamoService = lookupService();
return hsbamoService.find(id);
}

public     List<HsbaMo> findAll() {
if(hsbamoService == null) hsbamoService = lookupService();
return hsbamoService.findAll();
}

    public java.util.List<com.iesvn.yte.dieutri.entity.HsbaMo> findBySoSoVaoVienVaKhoaMa(java.lang.String soVaoVien, java.lang.String khoaMa) {
if(hsbamoService == null) hsbamoService = lookupService();
return hsbamoService.findBySoSoVaoVienVaKhoaMa(soVaoVien, khoaMa);
}

    public java.lang.String capNhatKetQuaMo(java.util.List<com.iesvn.yte.dieutri.entity.HsbaMo> lstHsbaMo, java.lang.String soVaoVien, java.lang.String khoaMa) {
if(hsbamoService == null) hsbamoService = lookupService();
return hsbamoService.capNhatKetQuaMo(lstHsbaMo, soVaoVien, khoaMa);
}
    
    public HsbaMo findByHsbaMa(String hsbaMa) {
        if(hsbamoService == null) hsbamoService = lookupService();
return hsbamoService.findByHsbaMa(hsbaMa);
    }

}


