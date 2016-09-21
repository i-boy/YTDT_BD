/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmBanKhamInterface;

import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmBanKhamDelegate {
private DtDmBanKhamInterface dtdmbankhamService;
public static DtDmBanKhamDelegate getInstance() {
return new DtDmBanKhamDelegate();
}
private DtDmBanKhamInterface lookupService() {
return (DtDmBanKhamInterface)LookupServiceUtils.lookupService("DtDmBanKhamFacade");
}

public     void create(DtDmBanKham dtDmBanKham) {
if(dtdmbankhamService == null) dtdmbankhamService = lookupService();
dtdmbankhamService.create(dtDmBanKham);
}

public     void edit(DtDmBanKham dtDmBanKham) {
if(dtdmbankhamService == null) dtdmbankhamService = lookupService();
dtdmbankhamService.edit(dtDmBanKham);
}

public     void remove(DtDmBanKham dtDmBanKham) {
if(dtdmbankhamService == null) dtdmbankhamService = lookupService();
dtdmbankhamService.remove(dtDmBanKham);
}

public     DtDmBanKham find(Object id) {
if(dtdmbankhamService == null) dtdmbankhamService = lookupService();
return dtdmbankhamService.find(id);
}

public     List<DtDmBanKham> findAll() {
if(dtdmbankhamService == null) dtdmbankhamService = lookupService();
return dtdmbankhamService.findAll();
}
public List<DtDmBanKham> findByMaTiepDon(String maTiepDon){
    if(dtdmbankhamService == null) dtdmbankhamService = lookupService();
return dtdmbankhamService.findByMaTiepDon(maTiepDon);
}
}


