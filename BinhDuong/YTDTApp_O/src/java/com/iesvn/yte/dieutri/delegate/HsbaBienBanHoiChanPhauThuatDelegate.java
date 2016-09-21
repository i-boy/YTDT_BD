/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuat;
import com.iesvn.yte.dieutri.intf.HsbaBienBanHoiChanPhauThuatInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author quang
 */
public class HsbaBienBanHoiChanPhauThuatDelegate {
private HsbaBienBanHoiChanPhauThuatInterface hsbaBienBanHoiChanPhauThuatService;
public static HsbaBienBanHoiChanPhauThuatDelegate getInstance() {
return new HsbaBienBanHoiChanPhauThuatDelegate();
}
private HsbaBienBanHoiChanPhauThuatInterface lookupService() {
return (HsbaBienBanHoiChanPhauThuatInterface)LookupServiceUtils.lookupService("HsbaBienBanHoiChanPhauThuatFacade");
}

public     void create(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat) {
if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
hsbaBienBanHoiChanPhauThuatService.create(hsbaBienBanHoiChanPhauThuat);
}

public     void edit(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat) {
if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
hsbaBienBanHoiChanPhauThuatService.edit(hsbaBienBanHoiChanPhauThuat);
}

public     void remove(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat) {
if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
hsbaBienBanHoiChanPhauThuatService.remove(hsbaBienBanHoiChanPhauThuat);
}

public     HsbaBienBanHoiChanPhauThuat find(Object id) {
if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
return hsbaBienBanHoiChanPhauThuatService.find(id);
}

public     List<HsbaBienBanHoiChanPhauThuat> findAll() {
if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
return hsbaBienBanHoiChanPhauThuatService.findAll();
}

public String insert(HsbaBienBanHoiChanPhauThuat obj){
    if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
    return hsbaBienBanHoiChanPhauThuatService.insert(obj);
}
public String update(HsbaBienBanHoiChanPhauThuat obj){
    if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
    return hsbaBienBanHoiChanPhauThuatService.update(obj);
}
public HsbaBienBanHoiChanPhauThuat findByHsbabbhcptMa(String ma){
    if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
    return hsbaBienBanHoiChanPhauThuatService.findByHsbabbhcptMa(ma);
}

public List<DtDmNhanVien> findBacsitpkByHsbabbhcptMa(String ma){
    if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
    return hsbaBienBanHoiChanPhauThuatService.findBacsitpkByHsbabbhcptMa(ma);
}
public List<DtDmNhanVien> findBacsigmByHsbabbhcptMa(String ma){
    if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
    return hsbaBienBanHoiChanPhauThuatService.findBacsigmByHsbabbhcptMa(ma);
}
public List<DtDmNhanVien> findBacsiptvByHsbabbhcptMa(String ma){
    if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
    return hsbaBienBanHoiChanPhauThuatService.findBacsiptvByHsbabbhcptMa(ma);
}


//public     HsbaBienBanHoiChanPhauThuat createHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList) {
//public     String createHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList) {
public     String createHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiptvList, List<DtDmNhanVien> bacsigmList, List<DtDmNhanVien> bacsitpkList) {
	if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
	return hsbaBienBanHoiChanPhauThuatService.createHsbaBienBanHoiChanPhauThuat(hsbaBienBanHoiChanPhauThuat, bacsiptvList,bacsigmList,bacsitpkList);
}
//public     HsbaBienBanHoiChanPhauThuat editHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList) {
//public     String editHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList) {
public     String editHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiptvList, List<DtDmNhanVien> bacsigmList, List<DtDmNhanVien> bacsitpkList) {
	if(hsbaBienBanHoiChanPhauThuatService == null) hsbaBienBanHoiChanPhauThuatService = lookupService();
	return hsbaBienBanHoiChanPhauThuatService.editHsbaBienBanHoiChanPhauThuat(hsbaBienBanHoiChanPhauThuat, bacsiptvList,bacsigmList,bacsitpkList);
}

}
