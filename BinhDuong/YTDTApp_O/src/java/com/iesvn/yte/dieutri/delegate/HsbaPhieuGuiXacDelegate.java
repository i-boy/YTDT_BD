/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.HsbaPhieuGuiXac;
import com.iesvn.yte.dieutri.intf.HsbaPhieuGuiXacInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author quang
 */
public class HsbaPhieuGuiXacDelegate  {
private HsbaPhieuGuiXacInterface hsbaPhieuGuiXacService;
public static HsbaPhieuGuiXacDelegate getInstance() {
return new HsbaPhieuGuiXacDelegate();
}
private HsbaPhieuGuiXacInterface lookupService() {
return (HsbaPhieuGuiXacInterface)LookupServiceUtils.lookupService("HsbaPhieuGuiXacFacade");
}

public     void create(HsbaPhieuGuiXac hsbaPhieuGuiXac) {
if(hsbaPhieuGuiXacService == null) hsbaPhieuGuiXacService = lookupService();
hsbaPhieuGuiXacService.create(hsbaPhieuGuiXac);
}

public     void edit(HsbaPhieuGuiXac hsbaPhieuGuiXac) {
if(hsbaPhieuGuiXacService == null) hsbaPhieuGuiXacService = lookupService();
hsbaPhieuGuiXacService.edit(hsbaPhieuGuiXac);
}

public     void remove(HsbaPhieuGuiXac hsbaPhieuGuiXac) {
if(hsbaPhieuGuiXacService == null) hsbaPhieuGuiXacService = lookupService();
hsbaPhieuGuiXacService.remove(hsbaPhieuGuiXac);
}

public     HsbaPhieuGuiXac find(Object id) {
if(hsbaPhieuGuiXacService == null) hsbaPhieuGuiXacService = lookupService();
return hsbaPhieuGuiXacService.find(id);
}

public     List<HsbaPhieuGuiXac> findAll() {
if(hsbaPhieuGuiXacService == null) hsbaPhieuGuiXacService = lookupService();
return hsbaPhieuGuiXacService.findAll();
}

public String insert(HsbaPhieuGuiXac obj){
    if(hsbaPhieuGuiXacService == null) hsbaPhieuGuiXacService = lookupService();
    return hsbaPhieuGuiXacService.insert(obj);
}
public String update(HsbaPhieuGuiXac obj){
    if(hsbaPhieuGuiXacService == null) hsbaPhieuGuiXacService = lookupService();
    return hsbaPhieuGuiXacService.update(obj);
}
public HsbaPhieuGuiXac findByHsbapgxMa(String ma){
    if(hsbaPhieuGuiXacService == null) hsbaPhieuGuiXacService = lookupService();
    return hsbaPhieuGuiXacService.findByHsbapgxMa(ma);
}

}


