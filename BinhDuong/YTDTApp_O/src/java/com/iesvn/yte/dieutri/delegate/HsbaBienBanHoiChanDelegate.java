/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaBienBanHoiChanInterface;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChan;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaBienBanHoiChanDelegate {
private HsbaBienBanHoiChanInterface hsbabienbanhoichanService;
public static HsbaBienBanHoiChanDelegate getInstance() {
return new HsbaBienBanHoiChanDelegate();
}
private HsbaBienBanHoiChanInterface lookupService() {
return (HsbaBienBanHoiChanInterface)LookupServiceUtils.lookupService("HsbaBienBanHoiChanFacade");
}

public     void create(HsbaBienBanHoiChan hsbaBienBanHoiChan) {
if(hsbabienbanhoichanService == null) hsbabienbanhoichanService = lookupService();
	hsbabienbanhoichanService.create(hsbaBienBanHoiChan);
}

public     HsbaBienBanHoiChan createHsbaBienBanHoiChan(HsbaBienBanHoiChan hsbaBienBanHoiChan, List<DtDmNhanVien> thanhVienList) {
	if(hsbabienbanhoichanService == null) hsbabienbanhoichanService = lookupService();
	return hsbabienbanhoichanService.createHsbaBienBanHoiChan(hsbaBienBanHoiChan, thanhVienList);
}
public     HsbaBienBanHoiChan editHsbaBienBanHoiChan(HsbaBienBanHoiChan hsbaBienBanHoiChan, List<DtDmNhanVien> thanhVienList) {
	if(hsbabienbanhoichanService == null) hsbabienbanhoichanService = lookupService();
	return hsbabienbanhoichanService.editHsbaBienBanHoiChan(hsbaBienBanHoiChan, thanhVienList);
}

public     void edit(HsbaBienBanHoiChan hsbaBienBanHoiChan) {
if(hsbabienbanhoichanService == null) hsbabienbanhoichanService = lookupService();
hsbabienbanhoichanService.edit(hsbaBienBanHoiChan);
}

public     void remove(HsbaBienBanHoiChan hsbaBienBanHoiChan) {
if(hsbabienbanhoichanService == null) hsbabienbanhoichanService = lookupService();
hsbabienbanhoichanService.remove(hsbaBienBanHoiChan);
}

public     HsbaBienBanHoiChan find(Object id) {
if(hsbabienbanhoichanService == null) hsbabienbanhoichanService = lookupService();
return hsbabienbanhoichanService.find(id);
}

public     List<HsbaBienBanHoiChan> findAll() {
if(hsbabienbanhoichanService == null) hsbabienbanhoichanService = lookupService();
return hsbabienbanhoichanService.findAll();
}

public List<DtDmNhanVien> findThanhVienByHsbabbhcMa(String ma) {
	if(hsbabienbanhoichanService == null) hsbabienbanhoichanService = lookupService();
	return hsbabienbanhoichanService.findThanhVienByHsbabbhcMa(ma);
}
}


