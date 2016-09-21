/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmChiDanInterface;

import com.iesvn.yte.dieutri.entity.DtDmChiDan;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmChiDanDelegate {
private DtDmChiDanInterface dtdmchidanService;
public static DtDmChiDanDelegate getInstance() {
return new DtDmChiDanDelegate();
}
private DtDmChiDanInterface lookupService() {
return (DtDmChiDanInterface)LookupServiceUtils.lookupService("DtDmChiDanFacade");
}

public     void create(DtDmChiDan dtDmChiDan) {
if(dtdmchidanService == null) dtdmchidanService = lookupService();
dtdmchidanService.create(dtDmChiDan);
}

public     void edit(DtDmChiDan dtDmChiDan) {
if(dtdmchidanService == null) dtdmchidanService = lookupService();
dtdmchidanService.edit(dtDmChiDan);
}

public     void remove(DtDmChiDan dtDmChiDan) {
if(dtdmchidanService == null) dtdmchidanService = lookupService();
dtdmchidanService.remove(dtDmChiDan);
}

public     DtDmChiDan find(Object id) {
if(dtdmchidanService == null) dtdmchidanService = lookupService();
return dtdmchidanService.find(id);
}

public     DtDmChiDan findByMa(String maChidan) {
if(dtdmchidanService == null) dtdmchidanService = lookupService();
return dtdmchidanService.findByMa(maChidan);
}

public     List<DtDmChiDan> findAll() {
if(dtdmchidanService == null) dtdmchidanService = lookupService();
return dtdmchidanService.findAll();
}

}


