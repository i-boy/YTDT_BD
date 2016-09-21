/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmPhongMoInterface;

import com.iesvn.yte.dieutri.entity.DtDmPhongMo;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmPhongMoDelegate {
private DtDmPhongMoInterface dtdmphongmoService;
public static DtDmPhongMoDelegate getInstance() {
return new DtDmPhongMoDelegate();
}
private DtDmPhongMoInterface lookupService() {
return (DtDmPhongMoInterface)LookupServiceUtils.lookupService("DtDmPhongMoFacade");
}

public     void create(DtDmPhongMo dtDmPhongMo) {
if(dtdmphongmoService == null) dtdmphongmoService = lookupService();
dtdmphongmoService.create(dtDmPhongMo);
}

public     void edit(DtDmPhongMo dtDmPhongMo) {
if(dtdmphongmoService == null) dtdmphongmoService = lookupService();
dtdmphongmoService.edit(dtDmPhongMo);
}

public     void remove(DtDmPhongMo dtDmPhongMo) {
if(dtdmphongmoService == null) dtdmphongmoService = lookupService();
dtdmphongmoService.remove(dtDmPhongMo);
}

public     DtDmPhongMo find(Object id) {
if(dtdmphongmoService == null) dtdmphongmoService = lookupService();
return dtdmphongmoService.find(id);
}

public     List<DtDmPhongMo> findAll() {
if(dtdmphongmoService == null) dtdmphongmoService = lookupService();
return dtdmphongmoService.findAll();
}

}


