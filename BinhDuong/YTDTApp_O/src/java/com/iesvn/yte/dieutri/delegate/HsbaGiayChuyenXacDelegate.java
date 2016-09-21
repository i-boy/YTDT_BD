/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.HsbaGiayChuyenXac;
import com.iesvn.yte.dieutri.intf.HsbaGiayChuyenXacInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author quang
 */
public class HsbaGiayChuyenXacDelegate  {
private HsbaGiayChuyenXacInterface hsbaGiayChuyenXacService;
public static HsbaGiayChuyenXacDelegate getInstance() {
return new HsbaGiayChuyenXacDelegate();
}
private HsbaGiayChuyenXacInterface lookupService() {
return (HsbaGiayChuyenXacInterface)LookupServiceUtils.lookupService("HsbaGiayChuyenXacFacade");
}

public     void create(HsbaGiayChuyenXac hsbaGiayChuyenXac) {
if(hsbaGiayChuyenXacService == null) hsbaGiayChuyenXacService = lookupService();
hsbaGiayChuyenXacService.create(hsbaGiayChuyenXac);
}

public     void edit(HsbaGiayChuyenXac hsbaGiayChuyenXac) {
if(hsbaGiayChuyenXacService == null) hsbaGiayChuyenXacService = lookupService();
hsbaGiayChuyenXacService.edit(hsbaGiayChuyenXac);
}

public     void remove(HsbaGiayChuyenXac hsbaGiayChuyenXac) {
if(hsbaGiayChuyenXacService == null) hsbaGiayChuyenXacService = lookupService();
hsbaGiayChuyenXacService.remove(hsbaGiayChuyenXac);
}

public     HsbaGiayChuyenXac find(Object id) {
if(hsbaGiayChuyenXacService == null) hsbaGiayChuyenXacService = lookupService();
return hsbaGiayChuyenXacService.find(id);
}

public     List<HsbaGiayChuyenXac> findAll() {
if(hsbaGiayChuyenXacService == null) hsbaGiayChuyenXacService = lookupService();
return hsbaGiayChuyenXacService.findAll();
}

public String insert(HsbaGiayChuyenXac obj){
    if(hsbaGiayChuyenXacService == null) hsbaGiayChuyenXacService = lookupService();
    return hsbaGiayChuyenXacService.insert(obj);
}

public String update(HsbaGiayChuyenXac obj){
    if(hsbaGiayChuyenXacService == null) hsbaGiayChuyenXacService = lookupService();
    return hsbaGiayChuyenXacService.update(obj);
}

public HsbaGiayChuyenXac findByHsbagcxMa(String ma){
    if(hsbaGiayChuyenXacService == null) hsbaGiayChuyenXacService = lookupService();
    return hsbaGiayChuyenXacService.findByHsbagcxMa(ma);
}

}


