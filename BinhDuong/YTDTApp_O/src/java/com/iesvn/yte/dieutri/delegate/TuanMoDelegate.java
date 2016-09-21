/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.TuanMoInterface;

import com.iesvn.yte.dieutri.entity.TuanMo;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class TuanMoDelegate {
private TuanMoInterface tuanmoService;
public static TuanMoDelegate getInstance() {
return new TuanMoDelegate();
}
private TuanMoInterface lookupService() {
return (TuanMoInterface)LookupServiceUtils.lookupService("TuanMoFacade");
}

public     void create(TuanMo tuanMo) {
if(tuanmoService == null) tuanmoService = lookupService();
tuanmoService.create(tuanMo);
}

public     void edit(TuanMo tuanMo) {
if(tuanmoService == null) tuanmoService = lookupService();
tuanmoService.edit(tuanMo);
}

public     void remove(TuanMo tuanMo) {
if(tuanmoService == null) tuanmoService = lookupService();
tuanmoService.remove(tuanMo);
}

public     TuanMo find(Object id) {
if(tuanmoService == null) tuanmoService = lookupService();
return tuanmoService.find(id);
}

public     List<TuanMo> findAll() {
if(tuanmoService == null) tuanmoService = lookupService();
return tuanmoService.findAll();
}

}


