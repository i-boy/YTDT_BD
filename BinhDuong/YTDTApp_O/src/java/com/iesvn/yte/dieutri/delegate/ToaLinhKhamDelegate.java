/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.dieutri.entity.CtToaLinhk;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.ToaLinhKhamInterface;

import com.iesvn.yte.dieutri.entity.ToaLinhKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class ToaLinhKhamDelegate {
private ToaLinhKhamInterface toalinhkhamService;
public static ToaLinhKhamDelegate getInstance() {
return new ToaLinhKhamDelegate();
}
private ToaLinhKhamInterface lookupService() {
return (ToaLinhKhamInterface)LookupServiceUtils.lookupService("ToaLinhKhamFacade");
}

public     void create(ToaLinhKham toaLinhKham) {
if(toalinhkhamService == null) toalinhkhamService = lookupService();
toalinhkhamService.create(toaLinhKham);
}

public     void edit(ToaLinhKham toaLinhKham) {
if(toalinhkhamService == null) toalinhkhamService = lookupService();
toalinhkhamService.edit(toaLinhKham);
}

public     void remove(ToaLinhKham toaLinhKham) {
if(toalinhkhamService == null) toalinhkhamService = lookupService();
toalinhkhamService.remove(toaLinhKham);
}

public     ToaLinhKham find(Object id) {
if(toalinhkhamService == null) toalinhkhamService = lookupService();
return toalinhkhamService.find(id);
}

public     List<ToaLinhKham> findAll() {
if(toalinhkhamService == null) toalinhkhamService = lookupService();
return toalinhkhamService.findAll();
}
public List<CtToaLinhk> findByMaTiepDonAndMaBanKham(String maTiepDon, String maBanKham) {
     if(toalinhkhamService == null) toalinhkhamService = lookupService();
return toalinhkhamService.findByMaTiepDonAndMaBanKham( maTiepDon,  maBanKham);

 }
 public boolean createToaLinhKham(List<ToaLinhKham> listTpk){
     
     if(toalinhkhamService == null) toalinhkhamService = lookupService();
return toalinhkhamService.createToaLinhKham( listTpk);

 }
}


