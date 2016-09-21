/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.NhapKhoDinhDuong;
import com.iesvn.yte.dieutri.intf.NhapKhoDinhDuongInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class NhapKhoDinhDuongDelegate {
    private NhapKhoDinhDuongInterface nhapkhodinhduongService;

    public static NhapKhoDinhDuongDelegate getInstance() {
        return new NhapKhoDinhDuongDelegate();
    }

    private NhapKhoDinhDuongInterface lookupService() {
        return (NhapKhoDinhDuongInterface) LookupServiceUtils.lookupService("NhapKhoDinhDuongFacade");
    }
    
    public void create(NhapKhoDinhDuong obj) {
        if (nhapkhodinhduongService == null) {
            nhapkhodinhduongService = lookupService();
        }
        nhapkhodinhduongService.create(obj);
    }
    
    public void edit(NhapKhoDinhDuong obj) {
        if (nhapkhodinhduongService == null) {
            nhapkhodinhduongService = lookupService();
        }
        nhapkhodinhduongService.edit(obj);
    }
    
    public void remove(NhapKhoDinhDuong obj) {
        if (nhapkhodinhduongService == null) {
            nhapkhodinhduongService = lookupService();
        }
        nhapkhodinhduongService.remove(obj);
    }
    
    public NhapKhoDinhDuong find(Object id) {
        if (nhapkhodinhduongService == null) {
            nhapkhodinhduongService = lookupService();
        }
        return nhapkhodinhduongService.find(id);
    }
    
    public List<NhapKhoDinhDuong> findAll() {
        if (nhapkhodinhduongService == null) {
            nhapkhodinhduongService = lookupService();
        }
        return nhapkhodinhduongService.findAll();
    }
    
    public List<NhapKhoDinhDuong> findByDate(Date date) {
        if (nhapkhodinhduongService == null) {
            nhapkhodinhduongService = lookupService();
        }
        return nhapkhodinhduongService.findByDate(date);
    }
    
     public NhapKhoDinhDuong myCreate(NhapKhoDinhDuong nhapkho){
         if (nhapkhodinhduongService == null) {
            nhapkhodinhduongService = lookupService();
        }
        return nhapkhodinhduongService.myCreate(nhapkho);
     }
}
