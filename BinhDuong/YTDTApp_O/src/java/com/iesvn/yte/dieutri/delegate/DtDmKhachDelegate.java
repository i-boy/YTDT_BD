/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmKhachInterface;

import com.iesvn.yte.dieutri.entity.DtDmKhach;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmKhachDelegate {
private DtDmKhachInterface dtdmkhachService;
public static DtDmKhachDelegate getInstance() {
return new DtDmKhachDelegate();
}
private DtDmKhachInterface lookupService() {
return (DtDmKhachInterface)LookupServiceUtils.lookupService("DtDmKhachFacade");
}

public     void create(DtDmKhach dtDmKhach) {
if(dtdmkhachService == null) dtdmkhachService = lookupService();
dtdmkhachService.create(dtDmKhach);
}

public     void edit(DtDmKhach dtDmKhach) {
if(dtdmkhachService == null) dtdmkhachService = lookupService();
dtdmkhachService.edit(dtDmKhach);
}

public     void remove(DtDmKhach dtDmKhach) {
if(dtdmkhachService == null) dtdmkhachService = lookupService();
dtdmkhachService.remove(dtDmKhach);
}

public     DtDmKhach find(Object id) {
if(dtdmkhachService == null) dtdmkhachService = lookupService();
return dtdmkhachService.find(id);
}

public     List<DtDmKhach> findAll() {
if(dtdmkhachService == null) dtdmkhachService = lookupService();
return dtdmkhachService.findAll();
}

}


