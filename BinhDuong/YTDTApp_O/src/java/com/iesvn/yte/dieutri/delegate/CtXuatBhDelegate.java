/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.CtXuatBhInterface;

import com.iesvn.yte.dieutri.entity.CtXuatBh;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class CtXuatBhDelegate {
private CtXuatBhInterface ctxuatbhService;
public static CtXuatBhDelegate getInstance() {
return new CtXuatBhDelegate();
}
private CtXuatBhInterface lookupService() {
return (CtXuatBhInterface)LookupServiceUtils.lookupService("CtXuatBhFacade");
}

public     void create(CtXuatBh ctXuatBh) {
if(ctxuatbhService == null) ctxuatbhService = lookupService();
ctxuatbhService.create(ctXuatBh);
}

public     void edit(CtXuatBh ctXuatBh) {
if(ctxuatbhService == null) ctxuatbhService = lookupService();
ctxuatbhService.edit(ctXuatBh);
}

public     void remove(CtXuatBh ctXuatBh) {
if(ctxuatbhService == null) ctxuatbhService = lookupService();
ctxuatbhService.remove(ctXuatBh);
}

public     CtXuatBh find(Object id) {
if(ctxuatbhService == null) ctxuatbhService = lookupService();
return ctxuatbhService.find(id);
}

public     List<CtXuatBh> findAll() {
if(ctxuatbhService == null) ctxuatbhService = lookupService();
return ctxuatbhService.findAll();
}

    public java.util.List<com.iesvn.yte.dieutri.entity.CtXuatBh> findByPhieuxuatBhMa(java.lang.String phieuxuatBhMa) {
if(ctxuatbhService == null) ctxuatbhService = lookupService();
return ctxuatbhService.findByPhieuxuatBhMa(phieuxuatBhMa);
}

}


