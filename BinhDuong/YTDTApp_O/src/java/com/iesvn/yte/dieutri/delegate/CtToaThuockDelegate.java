/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.CtToaThuockInterface;

import com.iesvn.yte.dieutri.entity.CtToaThuock;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class CtToaThuockDelegate {
private CtToaThuockInterface cttoathuockService;
public static CtToaThuockDelegate getInstance() {
return new CtToaThuockDelegate();
}
private CtToaThuockInterface lookupService() {
return (CtToaThuockInterface)LookupServiceUtils.lookupService("CtToaThuockFacade");
}

public     void create(CtToaThuock ctToaThuock) {
if(cttoathuockService == null) cttoathuockService = lookupService();
cttoathuockService.create(ctToaThuock);
}

public     void edit(CtToaThuock ctToaThuock) {
if(cttoathuockService == null) cttoathuockService = lookupService();
cttoathuockService.edit(ctToaThuock);
}

public     void remove(CtToaThuock ctToaThuock) {
if(cttoathuockService == null) cttoathuockService = lookupService();
cttoathuockService.remove(ctToaThuock);
}

public     CtToaThuock find(Object id) {
if(cttoathuockService == null) cttoathuockService = lookupService();
return cttoathuockService.find(id);
}

public     List<CtToaThuock> findAll() {
if(cttoathuockService == null) cttoathuockService = lookupService();
return cttoathuockService.findAll();
}

}


