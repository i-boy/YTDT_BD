/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.PhieuChamSocInterface;
import com.iesvn.yte.dieutri.entity.PhieuChamSoc;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class PhieuChamSocDelegate {
private PhieuChamSocInterface phieuchamsocService;
public static PhieuChamSocDelegate getInstance() {
return new PhieuChamSocDelegate();
}
private PhieuChamSocInterface lookupService() {
return (PhieuChamSocInterface)LookupServiceUtils.lookupService("PhieuChamSocFacade");
}

public     void create(PhieuChamSoc tiepDon) {
if(phieuchamsocService == null) phieuchamsocService = lookupService();
phieuchamsocService.create(tiepDon);
}

public     void edit(PhieuChamSoc tiepDon) {
if(phieuchamsocService == null) phieuchamsocService = lookupService();
phieuchamsocService.edit(tiepDon);
}

public     void remove(PhieuChamSoc tiepDon) {
if(phieuchamsocService == null) phieuchamsocService = lookupService();
phieuchamsocService.remove(tiepDon);
}

public     PhieuChamSoc find(Object id) {
if(phieuchamsocService == null) phieuchamsocService = lookupService();
return phieuchamsocService.find(id);
}
public     List<PhieuChamSoc> findAll() {
if(phieuchamsocService == null) phieuchamsocService = lookupService();
return phieuchamsocService.findAll();
}


// public List<PhieuChamSoc> findByHsbaCM(Integer hsbacmmaso) {
//if(phieuchamsocService == null) phieuchamsocService = lookupService();
//return phieuchamsocService.findByHsbaCM(hsbacmmaso);
//}

  public String capNhatPhieuChamSoc(List<PhieuChamSoc> listPCS, Integer hsbacmMaso) {
if(phieuchamsocService == null) phieuchamsocService = lookupService();
return phieuchamsocService.capNhatPhieuChamSoc(listPCS,hsbacmMaso);
}
public List<PhieuChamSoc> findByHsba(String hsbaMaso) {
    if(phieuchamsocService == null) phieuchamsocService = lookupService();
    return phieuchamsocService.findByHsba(hsbaMaso);
}

public PhieuChamSoc createPhieuChamSoc(PhieuChamSoc pcs) {
    if(phieuchamsocService == null) phieuchamsocService = lookupService();
    return phieuchamsocService.createPhieuChamSoc(pcs);
}
}


