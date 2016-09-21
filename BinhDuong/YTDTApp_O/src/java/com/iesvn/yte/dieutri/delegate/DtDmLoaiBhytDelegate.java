/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmLoaiBhytInterface;

import com.iesvn.yte.dieutri.entity.DtDmLoaiBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmLoaiBhytDelegate {
private DtDmLoaiBhytInterface dtdmloaibhytService;
public static DtDmLoaiBhytDelegate getInstance() {
return new DtDmLoaiBhytDelegate();
}
private DtDmLoaiBhytInterface lookupService() {
return (DtDmLoaiBhytInterface)LookupServiceUtils.lookupService("DtDmLoaiBhytFacade");
}

public     void create(DtDmLoaiBhyt dtDmLoaiBhyt) {
if(dtdmloaibhytService == null) dtdmloaibhytService = lookupService();
dtdmloaibhytService.create(dtDmLoaiBhyt);
}

public     void edit(DtDmLoaiBhyt dtDmLoaiBhyt) {
if(dtdmloaibhytService == null) dtdmloaibhytService = lookupService();
dtdmloaibhytService.edit(dtDmLoaiBhyt);
}

public     void remove(DtDmLoaiBhyt dtDmLoaiBhyt) {
if(dtdmloaibhytService == null) dtdmloaibhytService = lookupService();
dtdmloaibhytService.remove(dtDmLoaiBhyt);
}

public     DtDmLoaiBhyt find(Object id) {
if(dtdmloaibhytService == null) dtdmloaibhytService = lookupService();
return dtdmloaibhytService.find(id);
}

public     List<DtDmLoaiBhyt> findAll() {
if(dtdmloaibhytService == null) dtdmloaibhytService = lookupService();
return dtdmloaibhytService.findAll();
}

}


