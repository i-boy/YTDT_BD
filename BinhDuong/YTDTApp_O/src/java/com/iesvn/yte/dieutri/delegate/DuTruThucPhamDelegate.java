/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DuTruThucPham;
import com.iesvn.yte.dieutri.intf.DuTruThucPhamInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class DuTruThucPhamDelegate {
    private DuTruThucPhamInterface dutruthucphamService;

    public static DuTruThucPhamDelegate getInstance() {
        return new DuTruThucPhamDelegate();
    }

    private DuTruThucPhamInterface lookupService() {
        return (DuTruThucPhamInterface) LookupServiceUtils.lookupService("DuTruThucPhamFacade");
    }
    
    public void create(DuTruThucPham obj) {
        if (dutruthucphamService == null) {
            dutruthucphamService = lookupService();
        }
        dutruthucphamService.create(obj);
    }
    
    public void edit(DuTruThucPham obj) {
        if (dutruthucphamService == null) {
            dutruthucphamService = lookupService();
        }
        dutruthucphamService.edit(obj);
    }
    
    public void remove(DuTruThucPham obj) {
        if (dutruthucphamService == null) {
            dutruthucphamService = lookupService();
        }
        dutruthucphamService.remove(obj);
    }
    
    public DuTruThucPham find(Object id) {
        if (dutruthucphamService == null) {
            dutruthucphamService = lookupService();
        }
        return dutruthucphamService.find(id);
    }
    
    public List<DuTruThucPham> findAll() {
        if (dutruthucphamService == null) {
            dutruthucphamService = lookupService();
        }
        return dutruthucphamService.findAll();
    }
    
    public DuTruThucPham findByLoaiTPNgayDutru(String maLoaiTP, Date ngaynhap) {
        if (dutruthucphamService == null) {
            dutruthucphamService = lookupService();
        }
        return dutruthucphamService.findByLoaiTPNgayDutru(maLoaiTP, ngaynhap);
    }
    
    public List<DuTruThucPham> findByDate(Date date){
        if (dutruthucphamService == null) {
            dutruthucphamService = lookupService();
        }
        return dutruthucphamService.findByDate(date);
    }
}
