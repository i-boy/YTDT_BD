/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmLoaiMienInterface;

import com.iesvn.yte.dieutri.entity.DtDmLoaiMien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmLoaiMienDelegate {
private DtDmLoaiMienInterface dtdmloaimienService;
public static DtDmLoaiMienDelegate getInstance() {
return new DtDmLoaiMienDelegate();
}
private DtDmLoaiMienInterface lookupService() {
return (DtDmLoaiMienInterface)LookupServiceUtils.lookupService("DtDmLoaiMienFacade");
}

public     void create(DtDmLoaiMien dtDmLoaiMien) {
if(dtdmloaimienService == null) dtdmloaimienService = lookupService();
dtdmloaimienService.create(dtDmLoaiMien);
}

public     void edit(DtDmLoaiMien dtDmLoaiMien) {
if(dtdmloaimienService == null) dtdmloaimienService = lookupService();
dtdmloaimienService.edit(dtDmLoaiMien);
}

public     void remove(DtDmLoaiMien dtDmLoaiMien) {
if(dtdmloaimienService == null) dtdmloaimienService = lookupService();
dtdmloaimienService.remove(dtDmLoaiMien);
}

public     DtDmLoaiMien find(Object id) {
if(dtdmloaimienService == null) dtdmloaimienService = lookupService();
return dtdmloaimienService.find(id);
}

public     List<DtDmLoaiMien> findAll() {
if(dtdmloaimienService == null) dtdmloaimienService = lookupService();
return dtdmloaimienService.findAll();
}

}


