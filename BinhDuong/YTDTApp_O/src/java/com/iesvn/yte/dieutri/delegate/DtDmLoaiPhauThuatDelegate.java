/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmLoaiPhauThuatInterface;

import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmLoaiPhauThuatDelegate {
private DtDmLoaiPhauThuatInterface dtdmloaiphauthuatService;
public static DtDmLoaiPhauThuatDelegate getInstance() {
return new DtDmLoaiPhauThuatDelegate();
}
private DtDmLoaiPhauThuatInterface lookupService() {
return (DtDmLoaiPhauThuatInterface)LookupServiceUtils.lookupService("DtDmLoaiPhauThuatFacade");
}

public     void create(DtDmLoaiPhauThuat dtDmLoaiPhauThuat) {
if(dtdmloaiphauthuatService == null) dtdmloaiphauthuatService = lookupService();
dtdmloaiphauthuatService.create(dtDmLoaiPhauThuat);
}

public     void edit(DtDmLoaiPhauThuat dtDmLoaiPhauThuat) {
if(dtdmloaiphauthuatService == null) dtdmloaiphauthuatService = lookupService();
dtdmloaiphauthuatService.edit(dtDmLoaiPhauThuat);
}

public     void remove(DtDmLoaiPhauThuat dtDmLoaiPhauThuat) {
if(dtdmloaiphauthuatService == null) dtdmloaiphauthuatService = lookupService();
dtdmloaiphauthuatService.remove(dtDmLoaiPhauThuat);
}

public     DtDmLoaiPhauThuat find(Object id) {
if(dtdmloaiphauthuatService == null) dtdmloaiphauthuatService = lookupService();
return dtdmloaiphauthuatService.find(id);
}

public     List<DtDmLoaiPhauThuat> findAll() {
if(dtdmloaiphauthuatService == null) dtdmloaiphauthuatService = lookupService();
return dtdmloaiphauthuatService.findAll();
}

}


