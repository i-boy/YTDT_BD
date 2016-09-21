/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsThtoanInterface;

import com.iesvn.yte.dieutri.entity.HsThtoan;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsThtoanDelegate {
private HsThtoanInterface hsthtoanService;
public static HsThtoanDelegate getInstance() {
return new HsThtoanDelegate();
}
private HsThtoanInterface lookupService() {
return (HsThtoanInterface)LookupServiceUtils.lookupService("HsThtoanFacade");
}

public     void create(HsThtoan hsThtoan) {
if(hsthtoanService == null) hsthtoanService = lookupService();
hsthtoanService.create(hsThtoan);
}

public     void edit(HsThtoan hsThtoan) {
if(hsthtoanService == null) hsthtoanService = lookupService();
hsthtoanService.edit(hsThtoan);
}

public     void remove(HsThtoan hsThtoan) {
if(hsthtoanService == null) hsthtoanService = lookupService();
hsthtoanService.remove(hsThtoan);
}

public     HsThtoan find(Object id) {
if(hsthtoanService == null) hsthtoanService = lookupService();
return hsthtoanService.find(id);
}

public     List<HsThtoan> findAll() {
if(hsthtoanService == null) hsthtoanService = lookupService();
return hsthtoanService.findAll();
}

    public com.iesvn.yte.dieutri.entity.HsThtoan findBySovaovien(java.lang.String hsbaSovaovien) {
if(hsthtoanService == null) hsthtoanService = lookupService();
return hsthtoanService.findBySovaovien(hsbaSovaovien);
}

    public java.lang.String capNhatTTRaVien(com.iesvn.yte.dieutri.entity.HsThtoan hstt, com.iesvn.yte.dieutri.entity.Hsba hsba, com.iesvn.yte.dieutri.entity.HsbaChuyenVien hsbaCV) throws java.lang.Exception {
if(hsthtoanService == null) hsthtoanService = lookupService();
return hsthtoanService.capNhatTTRaVien(hstt, hsba, hsbaCV);
}
    
    public List<HsThtoan> findHsttCoThuPhi(String maSovaovien, String hoten){
        if(hsthtoanService == null) hsthtoanService = lookupService();
        return hsthtoanService.findHsttCoThuPhi(maSovaovien, hoten);
    }
public List<HsThtoan> findByNgayVaoVien(Date tungay, Date denngay) {
    if(hsthtoanService == null) hsthtoanService = lookupService();
        return hsthtoanService.findByNgayVaoVien(tungay, denngay);
}
public List<HsThtoan> findByNgayRaVien(Date tungay, Date denngay) {
    if(hsthtoanService == null) hsthtoanService = lookupService();
        return hsthtoanService.findByNgayRaVien(tungay, denngay);
}
}