/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaGiayChungThuongInterface;

import com.iesvn.yte.dieutri.entity.HsbaGiayChungThuong;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaGiayChungThuongDelegate {
private HsbaGiayChungThuongInterface hsbagiaychungthuongService;
public static HsbaGiayChungThuongDelegate getInstance() {
return new HsbaGiayChungThuongDelegate();
}
private HsbaGiayChungThuongInterface lookupService() {
return (HsbaGiayChungThuongInterface)LookupServiceUtils.lookupService("HsbaGiayChungThuongFacade");
}

public     void create(HsbaGiayChungThuong hsbaGiayChungThuong) {
if(hsbagiaychungthuongService == null) hsbagiaychungthuongService = lookupService();
hsbagiaychungthuongService.create(hsbaGiayChungThuong);
}

public     void edit(HsbaGiayChungThuong hsbaGiayChungThuong) {
if(hsbagiaychungthuongService == null) hsbagiaychungthuongService = lookupService();
hsbagiaychungthuongService.edit(hsbaGiayChungThuong);
}

public     void remove(HsbaGiayChungThuong hsbaGiayChungThuong) {
if(hsbagiaychungthuongService == null) hsbagiaychungthuongService = lookupService();
hsbagiaychungthuongService.remove(hsbaGiayChungThuong);
}

public     HsbaGiayChungThuong find(Object id) {
if(hsbagiaychungthuongService == null) hsbagiaychungthuongService = lookupService();
return hsbagiaychungthuongService.find(id);
}

public     List<HsbaGiayChungThuong> findAll() {
if(hsbagiaychungthuongService == null) hsbagiaychungthuongService = lookupService();
return hsbagiaychungthuongService.findAll();
}

public String insert(HsbaGiayChungThuong gct){
    if(hsbagiaychungthuongService == null) hsbagiaychungthuongService = lookupService();
    return hsbagiaychungthuongService.insert( gct);
}

public String update(HsbaGiayChungThuong gct){
    if(hsbagiaychungthuongService == null) hsbagiaychungthuongService = lookupService();
    return hsbagiaychungthuongService.update( gct);
}

public HsbaGiayChungThuong findByHsbagctMa(String ma){
    if(hsbagiaychungthuongService == null) hsbagiaychungthuongService = lookupService();
    return hsbagiaychungthuongService.findByHsbagctMa( ma);
}

}


