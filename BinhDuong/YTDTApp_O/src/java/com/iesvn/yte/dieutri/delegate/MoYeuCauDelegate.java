/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.MoYeuCauInterface;

import com.iesvn.yte.dieutri.entity.MoYeuCau;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class MoYeuCauDelegate {
private MoYeuCauInterface moyeucauService;
public static MoYeuCauDelegate getInstance() {
return new MoYeuCauDelegate();
}
private MoYeuCauInterface lookupService() {
return (MoYeuCauInterface)LookupServiceUtils.lookupService("MoYeuCauFacade");
}

public     void create(MoYeuCau moYeuCau) {
if(moyeucauService == null) moyeucauService = lookupService();
moyeucauService.create(moYeuCau);
}

public     void edit(MoYeuCau moYeuCau) {
if(moyeucauService == null) moyeucauService = lookupService();
moyeucauService.edit(moYeuCau);
}

public     void remove(MoYeuCau moYeuCau) {
if(moyeucauService == null) moyeucauService = lookupService();
moyeucauService.remove(moYeuCau);
}

public     MoYeuCau find(Object id) {
if(moyeucauService == null) moyeucauService = lookupService();
return moyeucauService.find(id);
}

public     List<MoYeuCau> findAll() {
if(moyeucauService == null) moyeucauService = lookupService();
return moyeucauService.findAll();
}

}


