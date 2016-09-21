/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmKhoiBhytInterface;

import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmKhoiBhytDelegate {
private DtDmKhoiBhytInterface dtdmkhoibhytService;
public static DtDmKhoiBhytDelegate getInstance() {
return new DtDmKhoiBhytDelegate();
}
private DtDmKhoiBhytInterface lookupService() {
return (DtDmKhoiBhytInterface)LookupServiceUtils.lookupService("DtDmKhoiBhytFacade");
}

public     void create(DtDmKhoiBhyt dtDmKhoiBhyt) {
if(dtdmkhoibhytService == null) dtdmkhoibhytService = lookupService();
dtdmkhoibhytService.create(dtDmKhoiBhyt);
}

public     void edit(DtDmKhoiBhyt dtDmKhoiBhyt) {
if(dtdmkhoibhytService == null) dtdmkhoibhytService = lookupService();
dtdmkhoibhytService.edit(dtDmKhoiBhyt);
}

public     void remove(DtDmKhoiBhyt dtDmKhoiBhyt) {
if(dtdmkhoibhytService == null) dtdmkhoibhytService = lookupService();
dtdmkhoibhytService.remove(dtDmKhoiBhyt);
}

public     DtDmKhoiBhyt find(Object id) {
if(dtdmkhoibhytService == null) dtdmkhoibhytService = lookupService();
return dtdmkhoibhytService.find(id);
}

public     List<DtDmKhoiBhyt> findAll() {
if(dtdmkhoibhytService == null) dtdmkhoibhytService = lookupService();
return dtdmkhoibhytService.findAll();
}

}


