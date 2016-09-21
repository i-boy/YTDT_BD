/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmDienMienInterface;

import com.iesvn.yte.dieutri.entity.DtDmDienMien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmDienMienDelegate {
private DtDmDienMienInterface dtdmdienmienService;
public static DtDmDienMienDelegate getInstance() {
return new DtDmDienMienDelegate();
}
private DtDmDienMienInterface lookupService() {
return (DtDmDienMienInterface)LookupServiceUtils.lookupService("DtDmDienMienFacade");
}

public     void create(DtDmDienMien dtDmDienMien) {
if(dtdmdienmienService == null) dtdmdienmienService = lookupService();
dtdmdienmienService.create(dtDmDienMien);
}

public     void edit(DtDmDienMien dtDmDienMien) {
if(dtdmdienmienService == null) dtdmdienmienService = lookupService();
dtdmdienmienService.edit(dtDmDienMien);
}

public     void remove(DtDmDienMien dtDmDienMien) {
if(dtdmdienmienService == null) dtdmdienmienService = lookupService();
dtdmdienmienService.remove(dtDmDienMien);
}

public     DtDmDienMien find(Object id) {
if(dtdmdienmienService == null) dtdmdienmienService = lookupService();
return dtdmdienmienService.find(id);
}

public     List<DtDmDienMien> findAll() {
if(dtdmdienmienService == null) dtdmdienmienService = lookupService();
return dtdmdienmienService.findAll();
}

}


