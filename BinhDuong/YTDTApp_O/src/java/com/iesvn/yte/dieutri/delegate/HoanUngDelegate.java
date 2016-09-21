/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HoanUngInterface;

import com.iesvn.yte.dieutri.entity.HoanUng;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HoanUngDelegate {
private HoanUngInterface hoanungService;
public static HoanUngDelegate getInstance() {
return new HoanUngDelegate();
}
private HoanUngInterface lookupService() {
return (HoanUngInterface)LookupServiceUtils.lookupService("HoanUngFacade");
}

public     HoanUng create(HoanUng hoanUng) {
if(hoanungService == null) hoanungService = lookupService();
return hoanungService.create(hoanUng);
}

public     void edit(HoanUng hoanUng) {
if(hoanungService == null) hoanungService = lookupService();
hoanungService.edit(hoanUng);
}

public     void remove(HoanUng hoanUng) {
if(hoanungService == null) hoanungService = lookupService();
hoanungService.remove(hoanUng);
}

public     HoanUng find(Object id) {
if(hoanungService == null) hoanungService = lookupService();
return hoanungService.find(id);
}

public     List<HoanUng> findAll() {
if(hoanungService == null) hoanungService = lookupService();
return hoanungService.findAll();
}
public Double getTongHoanUng(String hsbaSovaovien){
    if(hoanungService == null) hoanungService = lookupService();
return hoanungService.getTongHoanUng(hsbaSovaovien);
}

}


