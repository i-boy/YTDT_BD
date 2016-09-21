/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.dieutri.intf.DmLoaiThuocInterface;
import com.iesvn.yte.locator.LookupServiceUtils;

import java.util.HashMap;
import java.util.List;
/**
 *
 * @author ThoVNA
 */
public class DmLoaiThuocDelegate {
    private DmLoaiThuocInterface dmLoaiThuocService;

    public static DmLoaiThuocDelegate getInstance() {
        return new DmLoaiThuocDelegate();
    }
    private DmLoaiThuocInterface lookupService() {
        return (DmLoaiThuocInterface) LookupServiceUtils.lookupService("DmLoaiThuocFacade");
    }
    public void create(DmLoaiThuoc dmLoaiThuoc){
        if (dmLoaiThuocService == null) {
            dmLoaiThuocService = lookupService();
        }
        dmLoaiThuocService.create(dmLoaiThuoc);
    }
    public void edit(DmLoaiThuoc dmLoaiThuoc){
        if (dmLoaiThuocService == null) {
            dmLoaiThuocService = lookupService();
        }
        dmLoaiThuocService.edit(dmLoaiThuoc);
    }
    public void remove(DmLoaiThuoc dmLoaiThuoc){
        if (dmLoaiThuocService == null) {
            dmLoaiThuocService = lookupService();
        }
        dmLoaiThuocService.remove(dmLoaiThuoc);
    }
    public DmLoaiThuoc find(Object id){
        if (dmLoaiThuocService == null) {
            dmLoaiThuocService = lookupService();
        }
        return dmLoaiThuocService.find(id);
    }
    public List<DmLoaiThuoc> findAll(){
        if (dmLoaiThuocService == null) {
            dmLoaiThuocService = lookupService();
        }
        return dmLoaiThuocService.findAll();
    }
    public HashMap<String,DmLoaiThuoc> findAllDm(){
        if (dmLoaiThuocService == null) {
            dmLoaiThuocService = lookupService();
        }
        return dmLoaiThuocService.findAllDm();
    }
}
