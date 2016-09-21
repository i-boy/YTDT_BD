/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmVoCamInterface;

import com.iesvn.yte.dieutri.entity.DtDmVoCam;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmVoCamDelegate {
private DtDmVoCamInterface dtdmvocamService;
public static DtDmVoCamDelegate getInstance() {
return new DtDmVoCamDelegate();
}
private DtDmVoCamInterface lookupService() {
return (DtDmVoCamInterface)LookupServiceUtils.lookupService("DtDmVoCamFacade");
}

public     void create(DtDmVoCam dtDmVoCam) {
if(dtdmvocamService == null) dtdmvocamService = lookupService();
dtdmvocamService.create(dtDmVoCam);
}

public     void edit(DtDmVoCam dtDmVoCam) {
if(dtdmvocamService == null) dtdmvocamService = lookupService();
dtdmvocamService.edit(dtDmVoCam);
}

public     void remove(DtDmVoCam dtDmVoCam) {
if(dtdmvocamService == null) dtdmvocamService = lookupService();
dtdmvocamService.remove(dtDmVoCam);
}

public     DtDmVoCam find(Object id) {
if(dtdmvocamService == null) dtdmvocamService = lookupService();
return dtdmvocamService.find(id);
}

public     List<DtDmVoCam> findAll() {
if(dtdmvocamService == null) dtdmvocamService = lookupService();
return dtdmvocamService.findAll();
}

}


