/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsThtoankBackupInterface;

import com.iesvn.yte.dieutri.entity.HsThtoankBackup;
import java.util.List;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsThtoankBackupDelegate {

    private HsThtoankBackupInterface hsthtoankBackupService;

    public static HsThtoankBackupDelegate getInstance() {
        return new HsThtoankBackupDelegate();
    }

    private HsThtoankBackupInterface lookupService() {
        return (HsThtoankBackupInterface) LookupServiceUtils.lookupService("HsThtoankBackupFacade");
    }

    public void create(HsThtoankBackup hsThtoankBackup) {
        if (hsthtoankBackupService == null) {
            hsthtoankBackupService = lookupService();
        }
        hsthtoankBackupService.create(hsThtoankBackup);
    }

    public void edit(HsThtoankBackup hsThtoankBackup) {
        if (hsthtoankBackupService == null) {
            hsthtoankBackupService = lookupService();
        }
        hsthtoankBackupService.edit(hsThtoankBackup);
    }

    public void remove(HsThtoankBackup hsThtoankBackup) {
        if (hsthtoankBackupService == null) {
            hsthtoankBackupService = lookupService();
        }
        hsthtoankBackupService.remove(hsThtoankBackup);
    }

    public HsThtoankBackup find(Object id) {
        if (hsthtoankBackupService == null) {
            hsthtoankBackupService = lookupService();
        }
        return hsthtoankBackupService.find(id);
    }

    public List<HsThtoankBackup> findAll() {
        if (hsthtoankBackupService == null) {
            hsthtoankBackupService = lookupService();
        }
        return hsthtoankBackupService.findAll();
    } 
    
    public HsThtoankBackup findByMaPhieu(String maPhieuTT) {
        if (hsthtoankBackupService == null) {
            hsthtoankBackupService = lookupService();
        }
        return hsthtoankBackupService.findByMaPhieu(maPhieuTT);
    }
   public HsThtoankBackup findBytiepdonMa(String tiepdonMa, int recordReturn) {
        if (hsthtoankBackupService == null) {
            hsthtoankBackupService = lookupService();
        }
        return hsthtoankBackupService.findBytiepdonMa(tiepdonMa, recordReturn);
    }
}


