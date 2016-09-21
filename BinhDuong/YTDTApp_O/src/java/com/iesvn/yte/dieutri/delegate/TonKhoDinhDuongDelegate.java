/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.TonKhoDinhDuong;
import com.iesvn.yte.dieutri.intf.TonKhoDinhDuongInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class TonKhoDinhDuongDelegate {
    private TonKhoDinhDuongInterface tonkhodinhduongService;

    public static TonKhoDinhDuongDelegate getInstance() {
        return new TonKhoDinhDuongDelegate();
    }

    private TonKhoDinhDuongInterface lookupService() {
        return (TonKhoDinhDuongInterface) LookupServiceUtils.lookupService("TonKhoDinhDuongFacade");
    }
    
    public void create(TonKhoDinhDuong obj) {
        if (tonkhodinhduongService == null) {
            tonkhodinhduongService = lookupService();
        }
        tonkhodinhduongService.create(obj);
    }
    
    public void edit(TonKhoDinhDuong obj) {
        if (tonkhodinhduongService == null) {
            tonkhodinhduongService = lookupService();
        }
        tonkhodinhduongService.edit(obj);
    }
    
    public void remove(TonKhoDinhDuong obj) {
        if (tonkhodinhduongService == null) {
            tonkhodinhduongService = lookupService();
        }
        tonkhodinhduongService.remove(obj);
    }
    
    public TonKhoDinhDuong find(Object id) {
        if (tonkhodinhduongService == null) {
            tonkhodinhduongService = lookupService();
        }
        return tonkhodinhduongService.find(id);
    }
    
    public List<TonKhoDinhDuong> findAll() {
        if (tonkhodinhduongService == null) {
            tonkhodinhduongService = lookupService();
        }
        return tonkhodinhduongService.findAll();
    }
    public TonKhoDinhDuong findLastTKByNhaSX(Integer loaianMaso, Integer loaian2Maso, Integer nhasxMaso) {
        if (tonkhodinhduongService == null) {
            tonkhodinhduongService = lookupService();
        }
        return tonkhodinhduongService.findLastTKByNhaSX(loaianMaso, loaian2Maso, nhasxMaso);
    }
    
    public int tinhSoTon(Integer loaianMaso, Integer loaian2Maso, Integer nhasxMaso, Date ngayxuat) {
        if (tonkhodinhduongService == null) {
            tonkhodinhduongService = lookupService();
        }
        return tonkhodinhduongService.tinhSoTon(loaianMaso, loaian2Maso, nhasxMaso, ngayxuat);
    }
}
