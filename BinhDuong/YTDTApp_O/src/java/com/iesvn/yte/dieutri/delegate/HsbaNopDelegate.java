/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaNopInterface;

import com.iesvn.yte.dieutri.entity.HsbaNop;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaNopDelegate {
private HsbaNopInterface hsbanopService;
public static HsbaNopDelegate getInstance() {
return new HsbaNopDelegate();
}
private HsbaNopInterface lookupService() {
return (HsbaNopInterface)LookupServiceUtils.lookupService("HsbaNopFacade");
}

public     void create(HsbaNop hsbaNop) {
if(hsbanopService == null) hsbanopService = lookupService();
hsbanopService.create(hsbaNop);
}

public     void edit(HsbaNop hsbaNop) {
if(hsbanopService == null) hsbanopService = lookupService();
hsbanopService.edit(hsbaNop);
}

public     void remove(HsbaNop hsbaNop) {
if(hsbanopService == null) hsbanopService = lookupService();
hsbanopService.remove(hsbaNop);
}

public     HsbaNop find(Object id) {
if(hsbanopService == null) hsbanopService = lookupService();
return hsbanopService.find(id);
}

public     List<HsbaNop> findAll() {
if(hsbanopService == null) hsbanopService = lookupService();
return hsbanopService.findAll();
}

public com.iesvn.yte.dieutri.entity.HsbaNop findBySoVaoVien(java.lang.String soVaoVien) {
    if(hsbanopService == null) hsbanopService = lookupService();
return hsbanopService.findBySoVaoVien(soVaoVien);
}
    
public HsbaNop findBySoLuuTru(String soLuuTru) {
    if(hsbanopService == null) hsbanopService = lookupService();
return hsbanopService.findBySoLuuTru(soLuuTru);
    }

public int soBAtrongngay(Date ngay) {
    if(hsbanopService == null) hsbanopService = lookupService();
return hsbanopService.soBAtrongngay(ngay);
    }


}


