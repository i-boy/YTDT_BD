/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.KetQuaMoInterface;

import com.iesvn.yte.dieutri.entity.KetQuaMo;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class KetQuaMoDelegate {
private KetQuaMoInterface ketquamoService;
public static KetQuaMoDelegate getInstance() {
return new KetQuaMoDelegate();
}
private KetQuaMoInterface lookupService() {
return (KetQuaMoInterface)LookupServiceUtils.lookupService("KetQuaMoFacade");
}

public     void create(KetQuaMo ketQuaMo) {
if(ketquamoService == null) ketquamoService = lookupService();
ketquamoService.create(ketQuaMo);
}

public     void edit(KetQuaMo ketQuaMo) {
if(ketquamoService == null) ketquamoService = lookupService();
ketquamoService.edit(ketQuaMo);
}

public     void remove(KetQuaMo ketQuaMo) {
if(ketquamoService == null) ketquamoService = lookupService();
ketquamoService.remove(ketQuaMo);
}

public     KetQuaMo find(Object id) {
if(ketquamoService == null) ketquamoService = lookupService();
return ketquamoService.find(id);
}

public     List<KetQuaMo> findAll() {
if(ketquamoService == null) ketquamoService = lookupService();
return ketquamoService.findAll();
}

   
}


