/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmLyDoCvInterface;

import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmLyDoCvDelegate {
private DtDmLyDoCvInterface dtdmlydocvService;
public static DtDmLyDoCvDelegate getInstance() {
return new DtDmLyDoCvDelegate();
}
private DtDmLyDoCvInterface lookupService() {
return (DtDmLyDoCvInterface)LookupServiceUtils.lookupService("DtDmLyDoCvFacade");
}

public     void create(DtDmLyDoCv dtDmLyDoCv) {
if(dtdmlydocvService == null) dtdmlydocvService = lookupService();
dtdmlydocvService.create(dtDmLyDoCv);
}

public     void edit(DtDmLyDoCv dtDmLyDoCv) {
if(dtdmlydocvService == null) dtdmlydocvService = lookupService();
dtdmlydocvService.edit(dtDmLyDoCv);
}

public     void remove(DtDmLyDoCv dtDmLyDoCv) {
if(dtdmlydocvService == null) dtdmlydocvService = lookupService();
dtdmlydocvService.remove(dtDmLyDoCv);
}

public     DtDmLyDoCv find(Object id) {
if(dtdmlydocvService == null) dtdmlydocvService = lookupService();
return dtdmlydocvService.find(id);
}

public     List<DtDmLyDoCv> findAll() {
if(dtdmlydocvService == null) dtdmlydocvService = lookupService();
return dtdmlydocvService.findAll();
}

}


