/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DeNghiCcInterface;

import com.iesvn.yte.dieutri.entity.DeNghiCc;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DeNghiCcDelegate {
private DeNghiCcInterface denghiccService;
public static DeNghiCcDelegate getInstance() {
return new DeNghiCcDelegate();
}
private DeNghiCcInterface lookupService() {
return (DeNghiCcInterface)LookupServiceUtils.lookupService("DeNghiCcFacade");
}

public     void create(DeNghiCc deNghiCc) {
if(denghiccService == null) denghiccService = lookupService();
denghiccService.create(deNghiCc);
}

public     void edit(DeNghiCc deNghiCc) {
if(denghiccService == null) denghiccService = lookupService();
denghiccService.edit(deNghiCc);
}

public     void remove(DeNghiCc deNghiCc) {
if(denghiccService == null) denghiccService = lookupService();
denghiccService.remove(deNghiCc);
}

public     DeNghiCc find(Object id) {
if(denghiccService == null) denghiccService = lookupService();
return denghiccService.find(id);
}

public     List<DeNghiCc> findAll() {
if(denghiccService == null) denghiccService = lookupService();
return denghiccService.findAll();
}

}


