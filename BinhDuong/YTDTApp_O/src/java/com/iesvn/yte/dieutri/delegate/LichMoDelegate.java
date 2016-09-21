/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.LichMoInterface;

import com.iesvn.yte.dieutri.entity.LichMo;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class LichMoDelegate {
private LichMoInterface lichmoService;
public static LichMoDelegate getInstance() {
return new LichMoDelegate();
}
private LichMoInterface lookupService() {
return (LichMoInterface)LookupServiceUtils.lookupService("LichMoFacade");
}

public     void create(LichMo lichMo) {
if(lichmoService == null) lichmoService = lookupService();
lichmoService.create(lichMo);
}

public     void edit(LichMo lichMo) {
if(lichmoService == null) lichmoService = lookupService();
lichmoService.edit(lichMo);
}

public     void remove(LichMo lichMo) {
if(lichmoService == null) lichmoService = lookupService();
lichmoService.remove(lichMo);
}

public     LichMo find(Object id) {
if(lichmoService == null) lichmoService = lookupService();
return lichmoService.find(id);
}

public     List<LichMo> findAll() {
if(lichmoService == null) lichmoService = lookupService();
return lichmoService.findAll();
}

}


