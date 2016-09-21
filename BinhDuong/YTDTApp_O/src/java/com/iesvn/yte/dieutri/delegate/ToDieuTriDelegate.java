/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.ToDieuTriInterface;
import com.iesvn.yte.dieutri.entity.ToDieuTri;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class ToDieuTriDelegate {
private ToDieuTriInterface todieutriService;
public static ToDieuTriDelegate getInstance() {
return new ToDieuTriDelegate();
}
private ToDieuTriInterface lookupService() {
return (ToDieuTriInterface)LookupServiceUtils.lookupService("ToDieuTriFacade");
}

public     void create(ToDieuTri tiepDon) {
if(todieutriService == null) todieutriService = lookupService();
todieutriService.create(tiepDon);
}

public     void edit(ToDieuTri tiepDon) {
if(todieutriService == null) todieutriService = lookupService();
todieutriService.edit(tiepDon);
}

public     void remove(ToDieuTri tiepDon) {
if(todieutriService == null) todieutriService = lookupService();
todieutriService.remove(tiepDon);
}

public     ToDieuTri find(Object id) {
if(todieutriService == null) todieutriService = lookupService();
return todieutriService.find(id);
}
public     List<ToDieuTri> findAll() {
if(todieutriService == null) todieutriService = lookupService();
return todieutriService.findAll();
}

// public List<ToDieuTri> findByHsbaCM(Integer hsbacmmaso) {
//if(todieutriService == null) todieutriService = lookupService();
//return todieutriService.findByHsbaCM(hsbacmmaso);
//}

//  public String capNhatPhieuChamSoc(List<ToDieuTri> listTDT, Integer hsbacmMaso) {
//if(todieutriService == null) todieutriService = lookupService();
//return todieutriService.capNhatToDieuTri(listTDT,hsbacmMaso);
//}

public String capNhatToDieuTri(List<ToDieuTri> listTDT, String hsbaMaso) {
    if(todieutriService == null) todieutriService = lookupService();
    return todieutriService.capNhatToDieuTri(listTDT, hsbaMaso);
}
  
  public List<ToDieuTri> findByHsba(String hsbaMaso)  {
      if(todieutriService == null) todieutriService = lookupService();
    return todieutriService.findByHsba(hsbaMaso);
  }
  
  public ToDieuTri createToDieuTri(ToDieuTri tdt) {
      if(todieutriService == null) todieutriService = lookupService();
    return todieutriService.createToDieuTri(tdt);
  }
}


