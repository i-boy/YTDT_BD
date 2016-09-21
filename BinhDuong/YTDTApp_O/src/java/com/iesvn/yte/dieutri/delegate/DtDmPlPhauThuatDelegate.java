/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmPlPhauThuatInterface;

import com.iesvn.yte.dieutri.entity.DtDmPlPhauThuat;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmPlPhauThuatDelegate {
private DtDmPlPhauThuatInterface dtdmplphauthuatService;
public static DtDmPlPhauThuatDelegate getInstance() {
return new DtDmPlPhauThuatDelegate();
}
private DtDmPlPhauThuatInterface lookupService() {
return (DtDmPlPhauThuatInterface)LookupServiceUtils.lookupService("DtDmPlPhauThuatFacade");
}

public     void create(DtDmPlPhauThuat dtDmPlPhauThuat) {
if(dtdmplphauthuatService == null) dtdmplphauthuatService = lookupService();
dtdmplphauthuatService.create(dtDmPlPhauThuat);
}

public     void edit(DtDmPlPhauThuat dtDmPlPhauThuat) {
if(dtdmplphauthuatService == null) dtdmplphauthuatService = lookupService();
dtdmplphauthuatService.edit(dtDmPlPhauThuat);
}

public     void remove(DtDmPlPhauThuat dtDmPlPhauThuat) {
if(dtdmplphauthuatService == null) dtdmplphauthuatService = lookupService();
dtdmplphauthuatService.remove(dtDmPlPhauThuat);
}

public     DtDmPlPhauThuat find(Object id) {
if(dtdmplphauthuatService == null) dtdmplphauthuatService = lookupService();
return dtdmplphauthuatService.find(id);
}

public     List<DtDmPlPhauThuat> findAll() {
if(dtdmplphauthuatService == null) dtdmplphauthuatService = lookupService();
return dtdmplphauthuatService.findAll();
}

}


