/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.CtPhieuKhamDtNgoaiTruInterface;
import com.iesvn.yte.dieutri.entity.CtPhieuKhamDtNgoaiTru;
import java.util.Date;
import java.util.List;


/**
 *
 * @author LENOVO 3000 Y410
 */
public class CtPhieuKhamDtNgoaiTruDelegate {
private CtPhieuKhamDtNgoaiTruInterface ctpkdtntService;
public static CtPhieuKhamDtNgoaiTruDelegate getInstance() {
return new CtPhieuKhamDtNgoaiTruDelegate();
}
private CtPhieuKhamDtNgoaiTruInterface lookupService() {
return (CtPhieuKhamDtNgoaiTruInterface)LookupServiceUtils.lookupService("CtPhieuKhamDtNgoaiTruFacade");
}

public     void create(CtPhieuKhamDtNgoaiTru tiepDon) {
if(ctpkdtntService == null) ctpkdtntService = lookupService();
ctpkdtntService.create(tiepDon);
}

public     void edit(CtPhieuKhamDtNgoaiTru tiepDon) {
if(ctpkdtntService == null) ctpkdtntService = lookupService();
ctpkdtntService.edit(tiepDon);
}

public     void remove(CtPhieuKhamDtNgoaiTru tiepDon) {
if(ctpkdtntService == null) ctpkdtntService = lookupService();
ctpkdtntService.remove(tiepDon);
}

public     CtPhieuKhamDtNgoaiTru find(Object id) {
if(ctpkdtntService == null) ctpkdtntService = lookupService();
return ctpkdtntService.find(id);
}
public     List<CtPhieuKhamDtNgoaiTru> findAll() {
if(ctpkdtntService == null) ctpkdtntService = lookupService();
return ctpkdtntService.findAll();
}

 public List<CtPhieuKhamDtNgoaiTru> findByPKDTNTMa(String pkdtntMa){
     if(ctpkdtntService == null) ctpkdtntService = lookupService();
return ctpkdtntService.findByPKDTNTMa(pkdtntMa);
 }

}


