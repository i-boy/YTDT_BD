/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.ToaThuocKhamInterface;

import com.iesvn.yte.dieutri.entity.ToaThuocKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class ToaThuocKhamDelegate {
private ToaThuocKhamInterface toathuockhamService;
public static ToaThuocKhamDelegate getInstance() {
return new ToaThuocKhamDelegate();
}
private ToaThuocKhamInterface lookupService() {
return (ToaThuocKhamInterface)LookupServiceUtils.lookupService("ToaThuocKhamFacade");
}

public     void create(ToaThuocKham toaThuocKham) {
if(toathuockhamService == null) toathuockhamService = lookupService();
toathuockhamService.create(toaThuocKham);
}

public     void edit(ToaThuocKham toaThuocKham) {
if(toathuockhamService == null) toathuockhamService = lookupService();
toathuockhamService.edit(toaThuocKham);
}

public     void remove(ToaThuocKham toaThuocKham) {
if(toathuockhamService == null) toathuockhamService = lookupService();
toathuockhamService.remove(toaThuocKham);
}

public     ToaThuocKham find(Object id) {
if(toathuockhamService == null) toathuockhamService = lookupService();
return toathuockhamService.find(id);
}

public     List<ToaThuocKham> findAll() {
if(toathuockhamService == null) toathuockhamService = lookupService();
return toathuockhamService.findAll();
}

}


