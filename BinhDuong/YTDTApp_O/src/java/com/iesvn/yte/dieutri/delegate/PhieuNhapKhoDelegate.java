/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.PhieuNhapKhoInterface;

import com.iesvn.yte.dieutri.entity.CtNhapKho;
import com.iesvn.yte.dieutri.entity.PhieuNhapKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class PhieuNhapKhoDelegate {
private PhieuNhapKhoInterface phieunhapkhoService;
public static PhieuNhapKhoDelegate getInstance() {
return new PhieuNhapKhoDelegate();
}
private PhieuNhapKhoInterface lookupService() {
return (PhieuNhapKhoInterface)LookupServiceUtils.lookupService("PhieuNhapKhoFacade");
}

public     void create(PhieuNhapKho phieuNhapKho) {
if(phieunhapkhoService == null) phieunhapkhoService = lookupService();
phieunhapkhoService.create(phieuNhapKho);
}

public     void edit(PhieuNhapKho phieuNhapKho) {
if(phieunhapkhoService == null) phieunhapkhoService = lookupService();
phieunhapkhoService.edit(phieuNhapKho);
}

public     void remove(PhieuNhapKho phieuNhapKho) {
if(phieunhapkhoService == null) phieunhapkhoService = lookupService();
phieunhapkhoService.remove(phieuNhapKho);
}

public     PhieuNhapKho find(Object id) {
if(phieunhapkhoService == null) phieunhapkhoService = lookupService();
return phieunhapkhoService.find(id);
}

public     List<PhieuNhapKho> findAll() {
if(phieunhapkhoService == null) phieunhapkhoService = lookupService();
return phieunhapkhoService.findAll();
}

public List<PhieuNhapKho> find(String pnkMa, Date ngayNhap, Integer loaiPhieuMa, String soChungTu, String soHD){
    if(phieunhapkhoService == null) phieunhapkhoService = lookupService();
    return phieunhapkhoService.find(pnkMa, ngayNhap, loaiPhieuMa, soChungTu, soHD);
}

    public com.iesvn.yte.dieutri.entity.PhieuNhapKho findByPhieunhapkhoMa(java.lang.String id) {
if(phieunhapkhoService == null) phieunhapkhoService = lookupService();
return phieunhapkhoService.findByPhieunhapkhoMa(id);
}

    public java.lang.String getMaPhieu() {
if(phieunhapkhoService == null) phieunhapkhoService = lookupService();
return phieunhapkhoService.getMaPhieu();
}

    public String createPhieuNhap(PhieuNhapKho pn, List<CtNhapKho> listCNK, List<TonKho> listTk) {
if(phieunhapkhoService == null) phieunhapkhoService = lookupService();
return phieunhapkhoService.createPhieuNhap(pn, listCNK, listTk);
}

    public String createPhieuNhap(PhieuNhapKho pn, List<CtNhapKho> listCNK, Integer khoaMaso){
        if(phieunhapkhoService == null) phieunhapkhoService = lookupService();
        return phieunhapkhoService.createPhieuNhap(pn, listCNK, khoaMaso);
    }


       public boolean checkExisted(String soHoaDon, Date ngayHoaDon){
if(phieunhapkhoService == null) phieunhapkhoService = lookupService();
return phieunhapkhoService.checkExisted(soHoaDon, ngayHoaDon);

}
}

