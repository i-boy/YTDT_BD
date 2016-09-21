/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.CtBenhAnNgoaiTruInterface;
import com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru;
import java.util.Date;
import java.util.List;


/**
 *
 * @author LENOVO 3000 Y410
 */
public class CtBenhAnNgoaiTruDelegate {
private CtBenhAnNgoaiTruInterface ctbantService;
public static CtBenhAnNgoaiTruDelegate getInstance() {
return new CtBenhAnNgoaiTruDelegate();
}
private CtBenhAnNgoaiTruInterface lookupService() {
return (CtBenhAnNgoaiTruInterface)LookupServiceUtils.lookupService("CtBenhAnNgoaiTruFacade");
}

public     void create(CtBenhAnNgoaiTru tiepDon) {
if(ctbantService == null) ctbantService = lookupService();
ctbantService.create(tiepDon);
}

public     void edit(CtBenhAnNgoaiTru tiepDon) {
if(ctbantService == null) ctbantService = lookupService();
ctbantService.edit(tiepDon);
}

public     void remove(CtBenhAnNgoaiTru tiepDon) {
if(ctbantService == null) ctbantService = lookupService();
ctbantService.remove(tiepDon);
}

public     CtBenhAnNgoaiTru find(Object id) {
if(ctbantService == null) ctbantService = lookupService();
return ctbantService.find(id);
}
public     List<CtBenhAnNgoaiTru> findAll() {
if(ctbantService == null) ctbantService = lookupService();
return ctbantService.findAll();
}

 public List<CtBenhAnNgoaiTru> findByBANTMa(String bantMa){
     if(ctbantService == null) ctbantService = lookupService();
return ctbantService.findByBANTMa(bantMa);
 }

}


