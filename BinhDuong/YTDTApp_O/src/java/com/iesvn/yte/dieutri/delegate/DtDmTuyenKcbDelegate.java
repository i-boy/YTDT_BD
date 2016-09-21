/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmTuyenKcbInterface;

import com.iesvn.yte.dieutri.entity.DtDmTuyenKcb;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmTuyenKcbDelegate {
private DtDmTuyenKcbInterface dtdmtuyenkcbService;
public static DtDmTuyenKcbDelegate getInstance() {
return new DtDmTuyenKcbDelegate();
}
private DtDmTuyenKcbInterface lookupService() {
return (DtDmTuyenKcbInterface)LookupServiceUtils.lookupService("DtDmTuyenKcbFacade");
}

public     void create(DtDmTuyenKcb dtDmTuyenKcb) {
if(dtdmtuyenkcbService == null) dtdmtuyenkcbService = lookupService();
dtdmtuyenkcbService.create(dtDmTuyenKcb);
}

public     void edit(DtDmTuyenKcb dtDmTuyenKcb) {
if(dtdmtuyenkcbService == null) dtdmtuyenkcbService = lookupService();
dtdmtuyenkcbService.edit(dtDmTuyenKcb);
}

public     void remove(DtDmTuyenKcb dtDmTuyenKcb) {
if(dtdmtuyenkcbService == null) dtdmtuyenkcbService = lookupService();
dtdmtuyenkcbService.remove(dtDmTuyenKcb);
}

public     DtDmTuyenKcb find(Object id) {
if(dtdmtuyenkcbService == null) dtdmtuyenkcbService = lookupService();
return dtdmtuyenkcbService.find(id);
}

public     List<DtDmTuyenKcb> findAll() {
if(dtdmtuyenkcbService == null) dtdmtuyenkcbService = lookupService();
return dtdmtuyenkcbService.findAll();
}

}


