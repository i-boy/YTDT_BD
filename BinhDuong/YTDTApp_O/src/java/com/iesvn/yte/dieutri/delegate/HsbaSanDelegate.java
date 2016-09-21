/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaSanInterface;

import com.iesvn.yte.dieutri.entity.HsbaSan;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaSanDelegate {
private HsbaSanInterface hsbasanService;
public static HsbaSanDelegate getInstance() {
return new HsbaSanDelegate();
}
private HsbaSanInterface lookupService() {
return (HsbaSanInterface)LookupServiceUtils.lookupService("HsbaSanFacade");
}

public     HsbaSan create(HsbaSan hsbaSan) {
if(hsbasanService == null) hsbasanService = lookupService();
return hsbasanService.create(hsbaSan);
}

public     void edit(HsbaSan hsbaSan) {
if(hsbasanService == null) hsbasanService = lookupService();
hsbasanService.edit(hsbaSan);
}

public     void remove(HsbaSan hsbaSan) {
if(hsbasanService == null) hsbasanService = lookupService();
hsbasanService.remove(hsbaSan);
}

public     HsbaSan find(Object id) {
if(hsbasanService == null) hsbasanService = lookupService();
return hsbasanService.find(id);
}

public     List<HsbaSan> findAll() {
if(hsbasanService == null) hsbasanService = lookupService();
return hsbasanService.findAll();
}

}


