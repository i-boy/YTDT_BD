package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaGiayChungNhanPhauThuatInterface;

import com.iesvn.yte.dieutri.entity.HsbaGiayChungNhanPhauThuat;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaGiayChungNhanPhauThuatDelegate {
private HsbaGiayChungNhanPhauThuatInterface HsbaGiayChungNhanPhauThuatService;
public static HsbaGiayChungNhanPhauThuatDelegate getInstance() {
return new HsbaGiayChungNhanPhauThuatDelegate();
}
private HsbaGiayChungNhanPhauThuatInterface lookupService() {
return (HsbaGiayChungNhanPhauThuatInterface)LookupServiceUtils.lookupService("HsbaGiayChungNhanPhauThuatFacade");
}

public     void create(HsbaGiayChungNhanPhauThuat HsbaGiayChungNhanPhauThuat) {
if(HsbaGiayChungNhanPhauThuatService == null) HsbaGiayChungNhanPhauThuatService = lookupService();
HsbaGiayChungNhanPhauThuatService.create(HsbaGiayChungNhanPhauThuat);
}

public     void edit(HsbaGiayChungNhanPhauThuat HsbaGiayChungNhanPhauThuat) {
if(HsbaGiayChungNhanPhauThuatService == null) HsbaGiayChungNhanPhauThuatService = lookupService();
HsbaGiayChungNhanPhauThuatService.edit(HsbaGiayChungNhanPhauThuat);
}

public     void remove(HsbaGiayChungNhanPhauThuat HsbaGiayChungNhanPhauThuat) {
if(HsbaGiayChungNhanPhauThuatService == null) HsbaGiayChungNhanPhauThuatService = lookupService();
HsbaGiayChungNhanPhauThuatService.remove(HsbaGiayChungNhanPhauThuat);
}

public     HsbaGiayChungNhanPhauThuat find(Object id) {
if(HsbaGiayChungNhanPhauThuatService == null) HsbaGiayChungNhanPhauThuatService = lookupService();
return HsbaGiayChungNhanPhauThuatService.find(id);
}

public     List<HsbaGiayChungNhanPhauThuat> findAll() {
if(HsbaGiayChungNhanPhauThuatService == null) HsbaGiayChungNhanPhauThuatService = lookupService();
return HsbaGiayChungNhanPhauThuatService.findAll();
}

public String insert(HsbaGiayChungNhanPhauThuat gct){
    if(HsbaGiayChungNhanPhauThuatService == null) HsbaGiayChungNhanPhauThuatService = lookupService();
    return HsbaGiayChungNhanPhauThuatService.insert( gct);
}

public String update(HsbaGiayChungNhanPhauThuat gct){
    if(HsbaGiayChungNhanPhauThuatService == null) HsbaGiayChungNhanPhauThuatService = lookupService();
    return HsbaGiayChungNhanPhauThuatService.update( gct);
}

public HsbaGiayChungNhanPhauThuat findByHsbagcnptMa(String ma){
    if(HsbaGiayChungNhanPhauThuatService == null) HsbaGiayChungNhanPhauThuatService = lookupService();
    return HsbaGiayChungNhanPhauThuatService.findByHsbagcnptMa( ma);
}

}



