/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.PhieuTheoDoiTruyenDichInterface;
import com.iesvn.yte.dieutri.entity.PhieuTheoDoiTruyenDich;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class PhieuTheoDoiTruyenDichDelegate {
private PhieuTheoDoiTruyenDichInterface phieutheodoitruyendichService;
public static PhieuTheoDoiTruyenDichDelegate getInstance() {
return new PhieuTheoDoiTruyenDichDelegate();
}
private PhieuTheoDoiTruyenDichInterface lookupService() {
return (PhieuTheoDoiTruyenDichInterface)LookupServiceUtils.lookupService("PhieuTheoDoiTruyenDichFacade");
}

public     void create(PhieuTheoDoiTruyenDich phieuTheoDoiTruyenDich) {
if(phieutheodoitruyendichService == null) phieutheodoitruyendichService = lookupService();
phieutheodoitruyendichService.create(phieuTheoDoiTruyenDich);
}

public     void edit(PhieuTheoDoiTruyenDich phieuTheoDoiTruyenDich) {
if(phieutheodoitruyendichService == null) phieutheodoitruyendichService = lookupService();
phieutheodoitruyendichService.edit(phieuTheoDoiTruyenDich);
}

public     void remove(PhieuTheoDoiTruyenDich phieuTheoDoiTruyenDich) {
if(phieutheodoitruyendichService == null) phieutheodoitruyendichService = lookupService();
phieutheodoitruyendichService.remove(phieuTheoDoiTruyenDich);
}

public     PhieuTheoDoiTruyenDich find(Object id) {
if(phieutheodoitruyendichService == null) phieutheodoitruyendichService = lookupService();
return phieutheodoitruyendichService.find(id);
}
public     List<PhieuTheoDoiTruyenDich> findAll() {
if(phieutheodoitruyendichService == null) phieutheodoitruyendichService = lookupService();
return phieutheodoitruyendichService.findAll();
}

public List<PhieuTheoDoiTruyenDich> findByHsba(String hsbaMaso) {
    if(phieutheodoitruyendichService == null) phieutheodoitruyendichService = lookupService();
    return phieutheodoitruyendichService.findByHsba(hsbaMaso);
}

public PhieuTheoDoiTruyenDich createPhieuTheoDoiTruyenDich(PhieuTheoDoiTruyenDich ptdtd) {
    if(phieutheodoitruyendichService == null) phieutheodoitruyendichService = lookupService();
    return phieutheodoitruyendichService.createPhieuTheoDoiTruyenDich(ptdtd);
}
}


