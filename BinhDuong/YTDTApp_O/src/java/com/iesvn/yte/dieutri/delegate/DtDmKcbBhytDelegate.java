/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmKcbBhytInterface;

import com.iesvn.yte.dieutri.entity.DtDmKcbBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmKcbBhytDelegate {
private DtDmKcbBhytInterface dtdmkcbbhytService;
public static DtDmKcbBhytDelegate getInstance() {
return new DtDmKcbBhytDelegate();
}
private DtDmKcbBhytInterface lookupService() {
return (DtDmKcbBhytInterface)LookupServiceUtils.lookupService("DtDmKcbBhytFacade");
}

public     void create(DtDmKcbBhyt dtDmKcbBhyt) {
if(dtdmkcbbhytService == null) dtdmkcbbhytService = lookupService();
dtdmkcbbhytService.create(dtDmKcbBhyt);
}

public     void edit(DtDmKcbBhyt dtDmKcbBhyt) {
if(dtdmkcbbhytService == null) dtdmkcbbhytService = lookupService();
dtdmkcbbhytService.edit(dtDmKcbBhyt);
}

public     void remove(DtDmKcbBhyt dtDmKcbBhyt) {
if(dtdmkcbbhytService == null) dtdmkcbbhytService = lookupService();
dtdmkcbbhytService.remove(dtDmKcbBhyt);
}

public     DtDmKcbBhyt find(Object id) {
if(dtdmkcbbhytService == null) dtdmkcbbhytService = lookupService();
return dtdmkcbbhytService.find(id);
}

public     List<DtDmKcbBhyt> findAll() {
if(dtdmkcbbhytService == null) dtdmkcbbhytService = lookupService();
return dtdmkcbbhytService.findAll();
}

}


