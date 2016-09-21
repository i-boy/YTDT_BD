/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.TamUngKhamInterface;

import com.iesvn.yte.dieutri.entity.TamUngKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class TamUngKhamDelegate {
private TamUngKhamInterface tamungkhamService;
public static TamUngKhamDelegate getInstance() {
return new TamUngKhamDelegate();
}
private TamUngKhamInterface lookupService() {
return (TamUngKhamInterface)LookupServiceUtils.lookupService("TamUngKhamFacade");
}

public     TamUngKham create(TamUngKham tamUngKham) {
if(tamungkhamService == null) tamungkhamService = lookupService();
return tamungkhamService.create(tamUngKham);
}

public     void edit(TamUngKham tamUngKham) {
if(tamungkhamService == null) tamungkhamService = lookupService();
tamungkhamService.edit(tamUngKham);
}

public     void remove(TamUngKham tamUngKham) {
if(tamungkhamService == null) tamungkhamService = lookupService();
tamungkhamService.remove(tamUngKham);
}

public     TamUngKham find(Object id) {
if(tamungkhamService == null) tamungkhamService = lookupService();
return tamungkhamService.find(id);
}

public     List<TamUngKham> findAll() {
if(tamungkhamService == null) tamungkhamService = lookupService();
return tamungkhamService.findAll();
}

public Double getTongTamUng(String maTiepdon){
     if(tamungkhamService == null) tamungkhamService = lookupService();
return tamungkhamService.getTongTamUng(maTiepdon);
 }

public List<TamUngKham> getListTamUngChuaTT(String maTiepdon){
     if(tamungkhamService == null) tamungkhamService = lookupService();
return tamungkhamService.getListTamUngChuaTT(maTiepdon);
 }
public List<TamUngKham> getListTamUngDaTT(String maTiepdon){
   if(tamungkhamService == null) tamungkhamService = lookupService();
return tamungkhamService.getListTamUngDaTT(maTiepdon); 
}
public int countSolanTamUngByTiepdon(String maTiepdon){
    if(tamungkhamService == null) tamungkhamService = lookupService();
    return tamungkhamService.countSolanTamUngByTiepdon(maTiepdon); 
}
}