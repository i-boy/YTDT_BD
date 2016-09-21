/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmPbClsInterface;

import com.iesvn.yte.dieutri.entity.DtDmPbCls;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmPbClsDelegate {
private DtDmPbClsInterface dtdmpbclsService;
public static DtDmPbClsDelegate getInstance() {
return new DtDmPbClsDelegate();
}
private DtDmPbClsInterface lookupService() {
return (DtDmPbClsInterface)LookupServiceUtils.lookupService("DtDmPbClsFacade");
}

public     void create(DtDmPbCls dtDmPbCls) {
if(dtdmpbclsService == null) dtdmpbclsService = lookupService();
dtdmpbclsService.create(dtDmPbCls);
}

public     void edit(DtDmPbCls dtDmPbCls) {
if(dtdmpbclsService == null) dtdmpbclsService = lookupService();
dtdmpbclsService.edit(dtDmPbCls);
}

public     void remove(DtDmPbCls dtDmPbCls) {
if(dtdmpbclsService == null) dtdmpbclsService = lookupService();
dtdmpbclsService.remove(dtDmPbCls);
}

public     DtDmPbCls find(Object id) {
if(dtdmpbclsService == null) dtdmpbclsService = lookupService();
return dtdmpbclsService.find(id);
}

public     List<DtDmPbCls> findAll() {
if(dtdmpbclsService == null) dtdmpbclsService = lookupService();
return dtdmpbclsService.findAll();
}

}


