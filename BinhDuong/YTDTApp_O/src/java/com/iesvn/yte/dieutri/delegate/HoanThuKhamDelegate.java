/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HoanThuKhamInterface;

import com.iesvn.yte.dieutri.entity.HoanThuKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HoanThuKhamDelegate {
private HoanThuKhamInterface hoanthukhamService;
public static HoanThuKhamDelegate getInstance() {
return new HoanThuKhamDelegate();
}
private HoanThuKhamInterface lookupService() {
return (HoanThuKhamInterface)LookupServiceUtils.lookupService("HoanThuKhamFacade");
}

public     void create(HoanThuKham hoanThuKham) {
if(hoanthukhamService == null) hoanthukhamService = lookupService();
hoanthukhamService.create(hoanThuKham);
}

public     void edit(HoanThuKham hoanThuKham) {
if(hoanthukhamService == null) hoanthukhamService = lookupService();
hoanthukhamService.edit(hoanThuKham);
}

public     void remove(HoanThuKham hoanThuKham) {
if(hoanthukhamService == null) hoanthukhamService = lookupService();
hoanthukhamService.remove(hoanThuKham);
}

public     HoanThuKham find(Object id) {
if(hoanthukhamService == null) hoanthukhamService = lookupService();
return hoanthukhamService.find(id);
}

public     List<HoanThuKham> findAll() {
if(hoanthukhamService == null) hoanthukhamService = lookupService();
return hoanthukhamService.findAll();
}

}


