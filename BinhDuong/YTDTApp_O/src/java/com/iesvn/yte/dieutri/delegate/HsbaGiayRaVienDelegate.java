/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaGiayRaVienInterface;

import com.iesvn.yte.dieutri.entity.HsbaGiayRaVien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaGiayRaVienDelegate {
private HsbaGiayRaVienInterface hsbagiayravienService;
public static HsbaGiayRaVienDelegate getInstance() {
return new HsbaGiayRaVienDelegate();
}
private HsbaGiayRaVienInterface lookupService() {
return (HsbaGiayRaVienInterface)LookupServiceUtils.lookupService("HsbaGiayRaVienFacade");
}

public     void create(HsbaGiayRaVien hsbaGiayRaVien) {
if(hsbagiayravienService == null) hsbagiayravienService = lookupService();
hsbagiayravienService.create(hsbaGiayRaVien);
}

public     void edit(HsbaGiayRaVien hsbaGiayRaVien) {
if(hsbagiayravienService == null) hsbagiayravienService = lookupService();
hsbagiayravienService.edit(hsbaGiayRaVien);
}

public     void remove(HsbaGiayRaVien hsbaGiayRaVien) {
if(hsbagiayravienService == null) hsbagiayravienService = lookupService();
hsbagiayravienService.remove(hsbaGiayRaVien);
}

public     HsbaGiayRaVien find(Object id) {
if(hsbagiayravienService == null) hsbagiayravienService = lookupService();
return hsbagiayravienService.find(id);
}

public     List<HsbaGiayRaVien> findAll() {
if(hsbagiayravienService == null) hsbagiayravienService = lookupService();
return hsbagiayravienService.findAll();
}

public String insert(HsbaGiayRaVien obj){
    if(hsbagiayravienService == null) hsbagiayravienService = lookupService();
    return hsbagiayravienService.insert(obj);
}
public String update(HsbaGiayRaVien obj){
    if(hsbagiayravienService == null) hsbagiayravienService = lookupService();
    return hsbagiayravienService.update(obj);
}
public HsbaGiayRaVien findByHsbagrvMa(String ma){
    if(hsbagiayravienService == null) hsbagiayravienService = lookupService();
    return hsbagiayravienService.findByHsbagrvMa(ma);
}
public HsbaGiayRaVien findBySoVaoVien(String soVaoVien){
    if(hsbagiayravienService == null) hsbagiayravienService = lookupService();
    return hsbagiayravienService.findBySoVaoVien(soVaoVien);
}
}


