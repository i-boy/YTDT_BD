/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmPhauThuatInterface;

import com.iesvn.yte.dieutri.entity.DtDmPhauThuat;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmPhauThuatDelegate {
private DtDmPhauThuatInterface dtdmphauthuatService;
public static DtDmPhauThuatDelegate getInstance() {
return new DtDmPhauThuatDelegate();
}
private DtDmPhauThuatInterface lookupService() {
return (DtDmPhauThuatInterface)LookupServiceUtils.lookupService("DtDmPhauThuatFacade");
}

public     void create(DtDmPhauThuat dtDmPhauThuat) {
if(dtdmphauthuatService == null) dtdmphauthuatService = lookupService();
dtdmphauthuatService.create(dtDmPhauThuat);
}

public     void edit(DtDmPhauThuat dtDmPhauThuat) {
if(dtdmphauthuatService == null) dtdmphauthuatService = lookupService();
dtdmphauthuatService.edit(dtDmPhauThuat);
}

public     void remove(DtDmPhauThuat dtDmPhauThuat) {
if(dtdmphauthuatService == null) dtdmphauthuatService = lookupService();
dtdmphauthuatService.remove(dtDmPhauThuat);
}

public     DtDmPhauThuat find(Object id) {
if(dtdmphauthuatService == null) dtdmphauthuatService = lookupService();
return dtdmphauthuatService.find(id);
}

public     List<DtDmPhauThuat> findAll() {
if(dtdmphauthuatService == null) dtdmphauthuatService = lookupService();
return dtdmphauthuatService.findAll();
}

}


