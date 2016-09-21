/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;

import com.iesvn.yte.dieutri.entity.GiayNghiViecHuongBhxh;
import com.iesvn.yte.dieutri.intf.GiayNghiViecHuongBhxhInterface;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class GiayNghiViecHuongBhxhDelegate {
private GiayNghiViecHuongBhxhInterface gnvhbhxhService;
public static GiayNghiViecHuongBhxhDelegate getInstance() {
return new GiayNghiViecHuongBhxhDelegate();
}
private GiayNghiViecHuongBhxhInterface lookupService() {
return (GiayNghiViecHuongBhxhInterface)LookupServiceUtils.lookupService("GiayNghiViecHuongBhxhFacade");
}

public     void create(GiayNghiViecHuongBhxh giayNghiViecHuongBhxh) {
if(gnvhbhxhService == null) gnvhbhxhService = lookupService();
gnvhbhxhService.create(giayNghiViecHuongBhxh);
}

public     void edit(GiayNghiViecHuongBhxh giayNghiViecHuongBhxh) {
if(gnvhbhxhService == null) gnvhbhxhService = lookupService();
gnvhbhxhService.edit(giayNghiViecHuongBhxh);
}

public     void remove(GiayNghiViecHuongBhxh giayNghiViecHuongBhxh) {
if(gnvhbhxhService == null) gnvhbhxhService = lookupService();
gnvhbhxhService.remove(giayNghiViecHuongBhxh);
}

public     GiayNghiViecHuongBhxh find(Object id) {
if(gnvhbhxhService == null) gnvhbhxhService = lookupService();
return gnvhbhxhService.find(id);
}
public     List<GiayNghiViecHuongBhxh> findAll() {
if(gnvhbhxhService == null) gnvhbhxhService = lookupService();
return gnvhbhxhService.findAll();
}

 public GiayNghiViecHuongBhxh findByThamKhamMa(Integer thamkhamma){
     if(gnvhbhxhService == null) gnvhbhxhService = lookupService();
return gnvhbhxhService.findByThamKhamMa(thamkhamma);
 }

}


