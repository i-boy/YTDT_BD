/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.XuatKhoDinhDuong;
import com.iesvn.yte.dieutri.intf.XuatKhoDinhDuongInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class XuatKhoDinhDuongDelegate {
    private XuatKhoDinhDuongInterface xuatkhodinhduongService;

    public static XuatKhoDinhDuongDelegate getInstance() {
        return new XuatKhoDinhDuongDelegate();
    }

    private XuatKhoDinhDuongInterface lookupService() {
        return (XuatKhoDinhDuongInterface) LookupServiceUtils.lookupService("XuatKhoDinhDuongFacade");
    }
    
    public void create(XuatKhoDinhDuong obj) {
        if (xuatkhodinhduongService == null) {
            xuatkhodinhduongService = lookupService();
        }
        xuatkhodinhduongService.create(obj);
    }
    
    public void edit(XuatKhoDinhDuong obj) {
        if (xuatkhodinhduongService == null) {
            xuatkhodinhduongService = lookupService();
        }
        xuatkhodinhduongService.edit(obj);
    }
    
    public void remove(XuatKhoDinhDuong obj) {
        if (xuatkhodinhduongService == null) {
            xuatkhodinhduongService = lookupService();
        }
        xuatkhodinhduongService.remove(obj);
    }
    
    public XuatKhoDinhDuong find(Object id) {
        if (xuatkhodinhduongService == null) {
            xuatkhodinhduongService = lookupService();
        }
        return xuatkhodinhduongService.find(id);
    }
    
    public List<XuatKhoDinhDuong> findAll() {
        if (xuatkhodinhduongService == null) {
            xuatkhodinhduongService = lookupService();
        }
        return xuatkhodinhduongService.findAll();
    }
    
     public List<XuatKhoDinhDuong> findByDate(Date date) {
        if (xuatkhodinhduongService == null) {
            xuatkhodinhduongService = lookupService();
        }
        return xuatkhodinhduongService.findByDate(date);
    }
     
     public XuatKhoDinhDuong myCreate(XuatKhoDinhDuong xuatKho) {
        if (xuatkhodinhduongService == null) {
            xuatkhodinhduongService = lookupService();
        }
        return xuatkhodinhduongService.myCreate(xuatKho);
    }
}
