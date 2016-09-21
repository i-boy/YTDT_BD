/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.CtNhapKhoInterface;

import com.iesvn.yte.dieutri.entity.CtNhapKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class CtNhapKhoDelegate {
private CtNhapKhoInterface ctnhapkhoService;
public static CtNhapKhoDelegate getInstance() {
return new CtNhapKhoDelegate();
}
private CtNhapKhoInterface lookupService() {
return (CtNhapKhoInterface)LookupServiceUtils.lookupService("CtNhapKhoFacade");
}

public     void create(CtNhapKho ctNhapKho) {
if(ctnhapkhoService == null) ctnhapkhoService = lookupService();
ctnhapkhoService.create(ctNhapKho);
}

public     void edit(CtNhapKho ctNhapKho) {
if(ctnhapkhoService == null) ctnhapkhoService = lookupService();
ctnhapkhoService.edit(ctNhapKho);
}

public     void remove(CtNhapKho ctNhapKho) {
if(ctnhapkhoService == null) ctnhapkhoService = lookupService();
ctnhapkhoService.remove(ctNhapKho);
}

public     CtNhapKho find(Object id) {
if(ctnhapkhoService == null) ctnhapkhoService = lookupService();
return ctnhapkhoService.find(id);
}

public     List<CtNhapKho> findAll() {
if(ctnhapkhoService == null) ctnhapkhoService = lookupService();
return ctnhapkhoService.findAll();
}

    public java.util.List<com.iesvn.yte.dieutri.entity.CtNhapKho> findByPhieuNhapKho(java.lang.String pnkMa) {
if(ctnhapkhoService == null) ctnhapkhoService = lookupService();
return ctnhapkhoService.findByPhieuNhapKho(pnkMa);
}

}


