/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmKhoInterface;

import com.iesvn.yte.dieutri.entity.DtDmKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmKhoDelegate {
private DtDmKhoInterface dtdmkhoService;
public static DtDmKhoDelegate getInstance() {
return new DtDmKhoDelegate();
}
private DtDmKhoInterface lookupService() {
return (DtDmKhoInterface)LookupServiceUtils.lookupService("DtDmKhoFacade");
}

public     void create(DtDmKho dtDmKho) {
if(dtdmkhoService == null) dtdmkhoService = lookupService();
dtdmkhoService.create(dtDmKho);
}

public     void edit(DtDmKho dtDmKho) {
if(dtdmkhoService == null) dtdmkhoService = lookupService();
dtdmkhoService.edit(dtDmKho);
}

public     void remove(DtDmKho dtDmKho) {
if(dtdmkhoService == null) dtdmkhoService = lookupService();
dtdmkhoService.remove(dtDmKho);
}

public     DtDmKho find(Object id) {
if(dtdmkhoService == null) dtdmkhoService = lookupService();
return dtdmkhoService.find(id);
}

public     List<DtDmKho> findAll() {
if(dtdmkhoService == null) dtdmkhoService = lookupService();
return dtdmkhoService.findAll();
}

}


