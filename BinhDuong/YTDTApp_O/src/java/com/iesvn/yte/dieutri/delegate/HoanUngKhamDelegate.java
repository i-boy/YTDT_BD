/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HoanUngKhamInterface;

import com.iesvn.yte.dieutri.entity.HoanUngKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HoanUngKhamDelegate {
private HoanUngKhamInterface hoanungkhamService;
public static HoanUngKhamDelegate getInstance() {
return new HoanUngKhamDelegate();
}
private HoanUngKhamInterface lookupService() {
return (HoanUngKhamInterface)LookupServiceUtils.lookupService("HoanUngKhamFacade");
}

public     void create(HoanUngKham hoanUngKham) {
if(hoanungkhamService == null) hoanungkhamService = lookupService();
hoanungkhamService.create(hoanUngKham);
}

public     void edit(HoanUngKham hoanUngKham) {
if(hoanungkhamService == null) hoanungkhamService = lookupService();
hoanungkhamService.edit(hoanUngKham);
}

public     void remove(HoanUngKham hoanUngKham) {
if(hoanungkhamService == null) hoanungkhamService = lookupService();
hoanungkhamService.remove(hoanUngKham);
}

public     HoanUngKham find(Object id) {
if(hoanungkhamService == null) hoanungkhamService = lookupService();
return hoanungkhamService.find(id);
}

public     List<HoanUngKham> findAll() {
if(hoanungkhamService == null) hoanungkhamService = lookupService();
return hoanungkhamService.findAll();
}
  public Double getTongHoanUng(String maTiepdon){
      if(hoanungkhamService == null) hoanungkhamService = lookupService();
return hoanungkhamService.getTongHoanUng(maTiepdon);
  }

}


