/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.CtToaLinhkInterface;

import com.iesvn.yte.dieutri.entity.CtToaLinhk;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class CtToaLinhkDelegate {
private CtToaLinhkInterface cttoalinhkService;
public static CtToaLinhkDelegate getInstance() {
return new CtToaLinhkDelegate();
}
private CtToaLinhkInterface lookupService() {
return (CtToaLinhkInterface)LookupServiceUtils.lookupService("CtToaLinhkFacade");
}

public     void create(CtToaLinhk ctToaLinhk) {
if(cttoalinhkService == null) cttoalinhkService = lookupService();
cttoalinhkService.create(ctToaLinhk);
}

public     void edit(CtToaLinhk ctToaLinhk) {
if(cttoalinhkService == null) cttoalinhkService = lookupService();
cttoalinhkService.edit(ctToaLinhk);
}

public     void remove(CtToaLinhk ctToaLinhk) {
if(cttoalinhkService == null) cttoalinhkService = lookupService();
cttoalinhkService.remove(ctToaLinhk);
}

public     CtToaLinhk find(Object id) {
if(cttoalinhkService == null) cttoalinhkService = lookupService();
return cttoalinhkService.find(id);
}

public     List<CtToaLinhk> findAll() {
if(cttoalinhkService == null) cttoalinhkService = lookupService();
return cttoalinhkService.findAll();
}

    public java.util.List<com.iesvn.yte.dieutri.entity.CtToaLinhk> findByTiepDonMa(java.lang.String tiepdonMa) {
if(cttoalinhkService == null) cttoalinhkService = lookupService();
return cttoalinhkService.findByTiepDonMa(tiepdonMa);
}

}


