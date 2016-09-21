/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmNoiSinhInterface;

import com.iesvn.yte.dieutri.entity.DtDmNoiSinh;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmNoiSinhDelegate {
private DtDmNoiSinhInterface dtdmnoisinhService;
public static DtDmNoiSinhDelegate getInstance() {
return new DtDmNoiSinhDelegate();
}
private DtDmNoiSinhInterface lookupService() {
return (DtDmNoiSinhInterface)LookupServiceUtils.lookupService("DtDmNoiSinhFacade");
}

public     void create(DtDmNoiSinh dtDmNoiSinh) {
if(dtdmnoisinhService == null) dtdmnoisinhService = lookupService();
dtdmnoisinhService.create(dtDmNoiSinh);
}

public     void edit(DtDmNoiSinh dtDmNoiSinh) {
if(dtdmnoisinhService == null) dtdmnoisinhService = lookupService();
dtdmnoisinhService.edit(dtDmNoiSinh);
}

public     void remove(DtDmNoiSinh dtDmNoiSinh) {
if(dtdmnoisinhService == null) dtdmnoisinhService = lookupService();
dtdmnoisinhService.remove(dtDmNoiSinh);
}

public     DtDmNoiSinh find(Object id) {
if(dtdmnoisinhService == null) dtdmnoisinhService = lookupService();
return dtdmnoisinhService.find(id);
}

public     List<DtDmNoiSinh> findAll() {
if(dtdmnoisinhService == null) dtdmnoisinhService = lookupService();
return dtdmnoisinhService.findAll();
}

}


