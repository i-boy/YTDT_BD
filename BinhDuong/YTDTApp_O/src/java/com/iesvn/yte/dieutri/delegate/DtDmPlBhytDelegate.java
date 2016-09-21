/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmPlBhytInterface;

import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmPlBhytDelegate {
private DtDmPlBhytInterface dtdmplbhytService;
public static DtDmPlBhytDelegate getInstance() {
return new DtDmPlBhytDelegate();
}
private DtDmPlBhytInterface lookupService() {
return (DtDmPlBhytInterface)LookupServiceUtils.lookupService("DtDmPlBhytFacade");
}

public     void create(DtDmPlBhyt dtDmPlBhyt) {
if(dtdmplbhytService == null) dtdmplbhytService = lookupService();
dtdmplbhytService.create(dtDmPlBhyt);
}

public     void edit(DtDmPlBhyt dtDmPlBhyt) {
if(dtdmplbhytService == null) dtdmplbhytService = lookupService();
dtdmplbhytService.edit(dtDmPlBhyt);
}

public     void remove(DtDmPlBhyt dtDmPlBhyt) {
if(dtdmplbhytService == null) dtdmplbhytService = lookupService();
dtdmplbhytService.remove(dtDmPlBhyt);
}

public     DtDmPlBhyt find(Object id) {
if(dtdmplbhytService == null) dtdmplbhytService = lookupService();
return dtdmplbhytService.find(id);
}

public     List<DtDmPlBhyt> findAll() {
if(dtdmplbhytService == null) dtdmplbhytService = lookupService();
return dtdmplbhytService.findAll();
}

}


