/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaLapTrichBienBanHoiChan;
import com.iesvn.yte.dieutri.intf.HsbaLapTrichBienBanHoiChanInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author quang
 */
public class HsbaLapTrichBienBanHoiChanDelegate {
private HsbaLapTrichBienBanHoiChanInterface hsbaLapTrichBienBanHoiChanService;
public static HsbaLapTrichBienBanHoiChanDelegate getInstance() {
return new HsbaLapTrichBienBanHoiChanDelegate();
}
private HsbaLapTrichBienBanHoiChanInterface lookupService() {
return (HsbaLapTrichBienBanHoiChanInterface)LookupServiceUtils.lookupService("HsbaLapTrichBienBanHoiChanFacade");
}

public     void create(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan) {
if(hsbaLapTrichBienBanHoiChanService == null) hsbaLapTrichBienBanHoiChanService = lookupService();
hsbaLapTrichBienBanHoiChanService.create(hsbaLapTrichBienBanHoiChan);
}

public     void edit(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan) {
if(hsbaLapTrichBienBanHoiChanService == null) hsbaLapTrichBienBanHoiChanService = lookupService();
hsbaLapTrichBienBanHoiChanService.edit(hsbaLapTrichBienBanHoiChan);
}

public     void remove(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan) {
if(hsbaLapTrichBienBanHoiChanService == null) hsbaLapTrichBienBanHoiChanService = lookupService();
hsbaLapTrichBienBanHoiChanService.remove(hsbaLapTrichBienBanHoiChan);
}

public     HsbaLapTrichBienBanHoiChan find(Object id) {
if(hsbaLapTrichBienBanHoiChanService == null) hsbaLapTrichBienBanHoiChanService = lookupService();
return hsbaLapTrichBienBanHoiChanService.find(id);
}

public     List<HsbaLapTrichBienBanHoiChan> findAll() {
if(hsbaLapTrichBienBanHoiChanService == null) hsbaLapTrichBienBanHoiChanService = lookupService();
return hsbaLapTrichBienBanHoiChanService.findAll();
}

public String insert(HsbaLapTrichBienBanHoiChan obj){
    if(hsbaLapTrichBienBanHoiChanService == null) hsbaLapTrichBienBanHoiChanService = lookupService();
    return hsbaLapTrichBienBanHoiChanService.insert(obj);
}
public String update(HsbaLapTrichBienBanHoiChan obj){
    if(hsbaLapTrichBienBanHoiChanService == null) hsbaLapTrichBienBanHoiChanService = lookupService();
    return hsbaLapTrichBienBanHoiChanService.update(obj);
}
public HsbaLapTrichBienBanHoiChan findByHsbaltbbhcMa(String ma){
    if(hsbaLapTrichBienBanHoiChanService == null) hsbaLapTrichBienBanHoiChanService = lookupService();
    return hsbaLapTrichBienBanHoiChanService.findByHsbaltbbhcMa(ma);
}

public List<DtDmNhanVien> findBacsiByHsbaltbbhcMa(String ma){
    if(hsbaLapTrichBienBanHoiChanService == null) hsbaLapTrichBienBanHoiChanService = lookupService();
    return hsbaLapTrichBienBanHoiChanService.findBacsiByHsbaltbbhcMa(ma);
}

//public     HsbaLapTrichBienBanHoiChan createHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList) {
public     String createHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList) {
//public     String createHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList) {
	if(hsbaLapTrichBienBanHoiChanService == null) hsbaLapTrichBienBanHoiChanService = lookupService();
	return hsbaLapTrichBienBanHoiChanService.createHsbaLapTrichBienBanHoiChan(hsbaLapTrichBienBanHoiChan, bacsiList);
}
//public     HsbaLapTrichBienBanHoiChan editHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList) {
public     String editHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList) {
//public     String editHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList,List<DtDmNhanVien> bacsigmList) {
	if(hsbaLapTrichBienBanHoiChanService == null) hsbaLapTrichBienBanHoiChanService = lookupService();
	return hsbaLapTrichBienBanHoiChanService.editHsbaLapTrichBienBanHoiChan(hsbaLapTrichBienBanHoiChan, bacsiList);
}

}
