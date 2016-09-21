/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.TamUngInterface;

import com.iesvn.yte.dieutri.entity.TamUng;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class TamUngDelegate {
private TamUngInterface tamungService;
public static TamUngDelegate getInstance() {
return new TamUngDelegate();
}
private TamUngInterface lookupService() {
return (TamUngInterface)LookupServiceUtils.lookupService("TamUngFacade");
}

public     TamUng create(TamUng tamUng) {
if(tamungService == null) tamungService = lookupService();
return tamungService.create(tamUng);
}

public     void edit(TamUng tamUng) {
if(tamungService == null) tamungService = lookupService();
tamungService.edit(tamUng);
}

public     void remove(TamUng tamUng) {
if(tamungService == null) tamungService = lookupService();
tamungService.remove(tamUng);
}

public     TamUng find(Object id) {
if(tamungService == null) tamungService = lookupService();
return tamungService.find(id);
}

public     List<TamUng> findAll() {
if(tamungService == null) tamungService = lookupService();
return tamungService.findAll();
}

 public TamUng findByMaHsba(String maHsba) {
      if (tamungService == null) {
            tamungService = lookupService();
        }
        return tamungService.findByMaHsba(maHsba);
 }

 public Double getTongTamUng(String hsbaSovaovien){
     if(tamungService == null) tamungService = lookupService();
return tamungService.getTongTamUng( hsbaSovaovien);
 }
 
 public int countSolanTamUngByHsba(String hsbaSovaovien) {
    if(tamungService == null) tamungService = lookupService();
    return tamungService.countSolanTamUngByHsba(hsbaSovaovien);
 }

}


