/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.BenhNhanInterface;

import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.TiepDon;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class BenhNhanDelegate {

    private BenhNhanInterface benhnhanService;

    public static BenhNhanDelegate getInstance() {
        return new BenhNhanDelegate();
    }

    private BenhNhanInterface lookupService() {
        return (BenhNhanInterface) LookupServiceUtils.lookupService("BenhNhanFacade");
    }

    public void create(BenhNhan benhNhan) {
        if (benhnhanService == null) {
            benhnhanService = lookupService();
        }
        benhnhanService.create(benhNhan);
    }

    public void edit(BenhNhan benhNhan) {
        if (benhnhanService == null) {
            benhnhanService = lookupService();
        }
        benhnhanService.edit(benhNhan);
    }

    public void remove(BenhNhan benhNhan) {
        if (benhnhanService == null) {
            benhnhanService = lookupService();
        }
        benhnhanService.remove(benhNhan);
    }

    public BenhNhan find(Object id) {
        if (benhnhanService == null) {
            benhnhanService = lookupService();
        }
        return benhnhanService.find(id);
    }

    public List<BenhNhan> findAll() {
        if (benhnhanService == null) {
            benhnhanService = lookupService();
        }
        return benhnhanService.findAll();
    }
    
    public Long getLanVao(String benhnhanMa) {
        if (benhnhanService == null) {
            benhnhanService = lookupService();
        }
        return benhnhanService.getLanVao(benhnhanMa);
    }
        
    public List<BenhNhan> findByHoTen(String hoTen){
        if (benhnhanService == null) {
            benhnhanService = lookupService();
        }
        return benhnhanService.findByHoTen(hoTen);
    }
    public TiepDon findBySoTheBHYT(String sothebhyt){
        if (benhnhanService == null) {
            benhnhanService = lookupService();
        }
        return benhnhanService.findBySoTheBHYT(sothebhyt);
    }

    public List<BenhNhan> findByMaBN(String mabenhnhan){

     if (benhnhanService == null) {
            benhnhanService = lookupService();
        }
        return benhnhanService.findByMaBN(mabenhnhan);
    }
}






