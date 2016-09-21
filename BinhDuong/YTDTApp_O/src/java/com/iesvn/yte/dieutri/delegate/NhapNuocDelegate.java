/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.NhapNuoc;
import com.iesvn.yte.dieutri.intf.NhapNuocInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class NhapNuocDelegate {
    private NhapNuocInterface nhapnuocService;

    public static NhapNuocDelegate getInstance() {
        return new NhapNuocDelegate();
    }

    private NhapNuocInterface lookupService() {
        return (NhapNuocInterface) LookupServiceUtils.lookupService("NhapNuocFacade");
    }
    
    public void create(NhapNuoc obj) {
        if (nhapnuocService == null) {
            nhapnuocService = lookupService();
        }
        nhapnuocService.create(obj);
    }
    
    public void edit(NhapNuoc obj) {
        if (nhapnuocService == null) {
            nhapnuocService = lookupService();
        }
        nhapnuocService.edit(obj);
    }
    
    public void remove(NhapNuoc obj) {
        if (nhapnuocService == null) {
            nhapnuocService = lookupService();
        }
        nhapnuocService.remove(obj);
    }
    
    public NhapNuoc find(Object id) {
        if (nhapnuocService == null) {
            nhapnuocService = lookupService();
        }
        return nhapnuocService.find(id);
    }
    
    public List<NhapNuoc> findAll() {
        if (nhapnuocService == null) {
            nhapnuocService = lookupService();
        }
        return nhapnuocService.findAll();
    }
    public List<NhapNuoc> findByNgayNhap(Date ngayNhap) {
        if (nhapnuocService == null) {
            nhapnuocService = lookupService();
        }
        return nhapnuocService.findByNgayNhap(ngayNhap);
    }
    public NhapNuoc findByBuongNgayNhap(Integer buongMaso, Date ngayNhap)  {
        if (nhapnuocService == null) {
            nhapnuocService = lookupService();
        }
        return nhapnuocService.findByBuongNgayNhap(buongMaso, ngayNhap);
    }
    public NhapNuoc myCreate(NhapNuoc nhapnuoc) {
        if (nhapnuocService == null) {
            nhapnuocService = lookupService();
        }
        return nhapnuocService.myCreate(nhapnuoc);
    }
}
