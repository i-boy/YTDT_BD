/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DonMienInterface;

import com.iesvn.yte.dieutri.entity.DonMien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DonMienDelegate {
private DonMienInterface donmienService;
public static DonMienDelegate getInstance() {
return new DonMienDelegate();
}
private DonMienInterface lookupService() {
return (DonMienInterface)LookupServiceUtils.lookupService("DonMienFacade");
}

public     void create(DonMien donMien) {
if(donmienService == null) donmienService = lookupService();
donmienService.create(donMien);
}

public     void edit(DonMien donMien) {
if(donmienService == null) donmienService = lookupService();
donmienService.edit(donMien);
}

public     void remove(DonMien donMien) {
if(donmienService == null) donmienService = lookupService();
donmienService.remove(donMien);
}

public     DonMien find(Object id) {
if(donmienService == null) donmienService = lookupService();
return donmienService.find(id);
}

public     List<DonMien> findAll() {
if(donmienService == null) donmienService = lookupService();
return donmienService.findAll();
}

}


