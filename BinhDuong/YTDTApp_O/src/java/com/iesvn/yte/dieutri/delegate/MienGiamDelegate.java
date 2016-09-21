/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.MienGiamInterface;

import com.iesvn.yte.dieutri.entity.MienGiam;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class MienGiamDelegate {
private MienGiamInterface miengiamService;
public static MienGiamDelegate getInstance() {
return new MienGiamDelegate();
}
private MienGiamInterface lookupService() {
return (MienGiamInterface)LookupServiceUtils.lookupService("MienGiamFacade");
}

public     MienGiam create(MienGiam mienGiam) {
if(miengiamService == null) miengiamService = lookupService();
return miengiamService.create(mienGiam);
}

public     void edit(MienGiam mienGiam) {
if(miengiamService == null) miengiamService = lookupService();
miengiamService.edit(mienGiam);
}

public     void remove(MienGiam mienGiam) {
if(miengiamService == null) miengiamService = lookupService();
miengiamService.remove(mienGiam);
}

public     MienGiam find(Object id) {
if(miengiamService == null) miengiamService = lookupService();
return miengiamService.find(id);
}

public     List<MienGiam> findAll() {
if(miengiamService == null) miengiamService = lookupService();
return miengiamService.findAll();
}

 public List<MienGiam> getBySoVaoVien(String soVaoVien){
     
     if(miengiamService == null) miengiamService = lookupService();
return miengiamService.getBySoVaoVien(soVaoVien);
 }

}


