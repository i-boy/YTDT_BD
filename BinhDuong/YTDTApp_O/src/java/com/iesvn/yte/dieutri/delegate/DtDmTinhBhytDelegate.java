/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmTinhBhytInterface;

import com.iesvn.yte.dieutri.entity.DtDmTinhBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmTinhBhytDelegate {
private DtDmTinhBhytInterface dtdmtinhbhytService;
public static DtDmTinhBhytDelegate getInstance() {
return new DtDmTinhBhytDelegate();
}
private DtDmTinhBhytInterface lookupService() {
return (DtDmTinhBhytInterface)LookupServiceUtils.lookupService("DtDmTinhBhytFacade");
}

public     void create(DtDmTinhBhyt dtDmTinhBhyt) {
if(dtdmtinhbhytService == null) dtdmtinhbhytService = lookupService();
dtdmtinhbhytService.create(dtDmTinhBhyt);
}

public     void edit(DtDmTinhBhyt dtDmTinhBhyt) {
if(dtdmtinhbhytService == null) dtdmtinhbhytService = lookupService();
dtdmtinhbhytService.edit(dtDmTinhBhyt);
}

public     void remove(DtDmTinhBhyt dtDmTinhBhyt) {
if(dtdmtinhbhytService == null) dtdmtinhbhytService = lookupService();
dtdmtinhbhytService.remove(dtDmTinhBhyt);
}

public     DtDmTinhBhyt find(Object id) {
if(dtdmtinhbhytService == null) dtdmtinhbhytService = lookupService();
return dtdmtinhbhytService.find(id);
}

public     List<DtDmTinhBhyt> findAll() {
if(dtdmtinhbhytService == null) dtdmtinhbhytService = lookupService();
return dtdmtinhbhytService.findAll();
}

}


