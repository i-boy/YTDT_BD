/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.CtTraKhoInterface;

import com.iesvn.yte.dieutri.entity.CtTraKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class CtTraKhoDelegate {
private CtTraKhoInterface cttrakhoService;
public static CtTraKhoDelegate getInstance() {
return new CtTraKhoDelegate();
}
private CtTraKhoInterface lookupService() {
return (CtTraKhoInterface)LookupServiceUtils.lookupService("CtTraKhoFacade");
}

public     void create(CtTraKho ctTraKho) {
if(cttrakhoService == null) cttrakhoService = lookupService();
cttrakhoService.create(ctTraKho);
}

public     void edit(CtTraKho ctTraKho) {
if(cttrakhoService == null) cttrakhoService = lookupService();
cttrakhoService.edit(ctTraKho);
}

public     void remove(CtTraKho ctTraKho) {
if(cttrakhoService == null) cttrakhoService = lookupService();
cttrakhoService.remove(ctTraKho);
}

public     CtTraKho find(Object id) {
if(cttrakhoService == null) cttrakhoService = lookupService();
return cttrakhoService.find(id);
}

public     List<CtTraKho> findAll() {
if(cttrakhoService == null) cttrakhoService = lookupService();
return cttrakhoService.findAll();
}

public List<CtTraKho> findByphieutrakhoMa(String phieutraMa){
if(cttrakhoService == null) cttrakhoService = lookupService();
return cttrakhoService.findByphieutrakhoMa(phieutraMa);
    
}


}


