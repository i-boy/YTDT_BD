/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HoanThuInterface;

import com.iesvn.yte.dieutri.entity.HoanThu;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HoanThuDelegate {
private HoanThuInterface hoanthuService;
public static HoanThuDelegate getInstance() {
return new HoanThuDelegate();
}
private HoanThuInterface lookupService() {
return (HoanThuInterface)LookupServiceUtils.lookupService("HoanThuFacade");
}

public     void create(HoanThu hoanThu) {
if(hoanthuService == null) hoanthuService = lookupService();
hoanthuService.create(hoanThu);
}

public     void edit(HoanThu hoanThu) {
if(hoanthuService == null) hoanthuService = lookupService();
hoanthuService.edit(hoanThu);
}

public     void remove(HoanThu hoanThu) {
if(hoanthuService == null) hoanthuService = lookupService();
hoanthuService.remove(hoanThu);
}

public     HoanThu find(Object id) {
if(hoanthuService == null) hoanthuService = lookupService();
return hoanthuService.find(id);
}

public     List<HoanThu> findAll() {
if(hoanthuService == null) hoanthuService = lookupService();
return hoanthuService.findAll();
}

}


