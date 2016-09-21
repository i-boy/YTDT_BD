/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChuyenVienInterface;

import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaChuyenVienDelegate {
private HsbaChuyenVienInterface hsbachuyenvienService;
public static HsbaChuyenVienDelegate getInstance() {
return new HsbaChuyenVienDelegate();
}
private HsbaChuyenVienInterface lookupService() {
return (HsbaChuyenVienInterface)LookupServiceUtils.lookupService("HsbaChuyenVienFacade");
}

public     void create(HsbaChuyenVien hsbaChuyenVien) {
if(hsbachuyenvienService == null) hsbachuyenvienService = lookupService();
hsbachuyenvienService.create(hsbaChuyenVien);
}

public     void edit(HsbaChuyenVien hsbaChuyenVien) {
if(hsbachuyenvienService == null) hsbachuyenvienService = lookupService();
hsbachuyenvienService.edit(hsbaChuyenVien);
}

public     void remove(HsbaChuyenVien hsbaChuyenVien) {
if(hsbachuyenvienService == null) hsbachuyenvienService = lookupService();
hsbachuyenvienService.remove(hsbaChuyenVien);
}

public     HsbaChuyenVien find(Object id) {
if(hsbachuyenvienService == null) hsbachuyenvienService = lookupService();
return hsbachuyenvienService.find(id);
}

public     List<HsbaChuyenVien> findAll() {
if(hsbachuyenvienService == null) hsbachuyenvienService = lookupService();
return hsbachuyenvienService.findAll();
}

    public com.iesvn.yte.dieutri.entity.HsbaChuyenVien findBySoVaoVien(java.lang.String soVaoVien) {
if(hsbachuyenvienService == null) hsbachuyenvienService = lookupService();
return hsbachuyenvienService.findBySoVaoVien(soVaoVien);
}
    
    public String insert(HsbaChuyenVien obj){
        if(hsbachuyenvienService == null) hsbachuyenvienService = lookupService();
        return hsbachuyenvienService.insert(obj);
    }
    public String update(HsbaChuyenVien obj){
        if(hsbachuyenvienService == null) hsbachuyenvienService = lookupService();
        return hsbachuyenvienService.update(obj);
    }
    public HsbaChuyenVien findByHsbacvMa(String ma){
        if(hsbachuyenvienService == null) hsbachuyenvienService = lookupService();
        return hsbachuyenvienService.findByHsbacvMa(ma);
    }

}


