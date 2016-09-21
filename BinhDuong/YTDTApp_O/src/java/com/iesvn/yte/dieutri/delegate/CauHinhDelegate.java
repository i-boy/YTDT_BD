/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.CauHinhInterface;

import com.iesvn.yte.dieutri.entity.CauHinh;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class CauHinhDelegate {
private CauHinhInterface cauhinhService;
public static CauHinhDelegate getInstance() {
return new CauHinhDelegate();
}
private CauHinhInterface lookupService() {
return (CauHinhInterface)LookupServiceUtils.lookupService("CauHinhFacade");
}

public     void create(CauHinh cauHinh) {
if(cauhinhService == null) cauhinhService = lookupService();
cauhinhService.create(cauHinh);
}

public     void edit(CauHinh cauHinh) {
if(cauhinhService == null) cauhinhService = lookupService();
cauhinhService.edit(cauHinh);
}

public     void remove(CauHinh cauHinh) {
if(cauhinhService == null) cauhinhService = lookupService();
cauhinhService.remove(cauHinh);
}

public     CauHinh find(Object id) {
if(cauhinhService == null) cauhinhService = lookupService();
return cauhinhService.find(id);
}

public     List<CauHinh> findAll() {
if(cauhinhService == null) cauhinhService = lookupService();
return cauhinhService.findAll();
}

    public java.lang.String getMaTiepDonAndMaBenhNhan() {
if(cauhinhService == null) cauhinhService = lookupService();
return cauhinhService.getMaTiepDonAndMaBenhNhan();
}

    public java.lang.String getMaTiepDon() {
if(cauhinhService == null) cauhinhService = lookupService();
return cauhinhService.getMaTiepDon();
}

}


