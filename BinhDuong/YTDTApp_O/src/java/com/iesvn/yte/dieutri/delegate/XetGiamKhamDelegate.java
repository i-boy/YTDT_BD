/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.XetGiamKhamInterface;

import com.iesvn.yte.dieutri.entity.XetGiamKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class XetGiamKhamDelegate {
private XetGiamKhamInterface xetgiamkhamService;
public static XetGiamKhamDelegate getInstance() {
return new XetGiamKhamDelegate();
}
private XetGiamKhamInterface lookupService() {
return (XetGiamKhamInterface)LookupServiceUtils.lookupService("XetGiamKhamFacade");
}

public     void create(XetGiamKham xetGiamKham) {
if(xetgiamkhamService == null) xetgiamkhamService = lookupService();
xetgiamkhamService.create(xetGiamKham);
}

public     void edit(XetGiamKham xetGiamKham) {
if(xetgiamkhamService == null) xetgiamkhamService = lookupService();
xetgiamkhamService.edit(xetGiamKham);
}

public     void remove(XetGiamKham xetGiamKham) {
if(xetgiamkhamService == null) xetgiamkhamService = lookupService();
xetgiamkhamService.remove(xetGiamKham);
}

public     XetGiamKham find(Object id) {
if(xetgiamkhamService == null) xetgiamkhamService = lookupService();
return xetgiamkhamService.find(id);
}

public     List<XetGiamKham> findAll() {
if(xetgiamkhamService == null) xetgiamkhamService = lookupService();
return xetgiamkhamService.findAll();
}

}


