/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.BenhNhanCheDoAn;
import com.iesvn.yte.dieutri.entity.BenhNhanGioAn;
import com.iesvn.yte.dieutri.entity.BenhNhanPhieuBaoAn;
import com.iesvn.yte.dieutri.intf.BenhNhanPhieuBaoAnInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class BenhNhanPhieuBaoAnDelegate {

    private BenhNhanPhieuBaoAnInterface benhnhanphieubaoanService;

    public static BenhNhanPhieuBaoAnDelegate getInstance() {
        return new BenhNhanPhieuBaoAnDelegate();
    }

    private BenhNhanPhieuBaoAnInterface lookupService() {
        return (BenhNhanPhieuBaoAnInterface) LookupServiceUtils.lookupService("BenhNhanPhieuBaoAnFacade");
    }

    public void create(BenhNhanPhieuBaoAn obj) {
        if (benhnhanphieubaoanService == null) {
            benhnhanphieubaoanService = lookupService();
        }
        benhnhanphieubaoanService.create(obj);
    }

    public void edit(BenhNhanPhieuBaoAn obj) {
        if (benhnhanphieubaoanService == null) {
            benhnhanphieubaoanService = lookupService();
        }
        benhnhanphieubaoanService.edit(obj);
    }

    public void remove(BenhNhanPhieuBaoAn obj) {
        if (benhnhanphieubaoanService == null) {
            benhnhanphieubaoanService = lookupService();
        }
        benhnhanphieubaoanService.remove(obj);
    }

    public BenhNhanPhieuBaoAn find(Object id) {
        if (benhnhanphieubaoanService == null) {
            benhnhanphieubaoanService = lookupService();
        }
        return benhnhanphieubaoanService.find(id);
    }

    public List<BenhNhanPhieuBaoAn> findAll() {
        if (benhnhanphieubaoanService == null) {
            benhnhanphieubaoanService = lookupService();
        }
        return benhnhanphieubaoanService.findAll();
    }
    
    public void myCreate(BenhNhanPhieuBaoAn benhNhanPhieuBaoAn, List<BenhNhanCheDoAn> listBnCda, List<BenhNhanGioAn> listBnGa, boolean isUpdate) {
        if (benhnhanphieubaoanService == null) {
            benhnhanphieubaoanService = lookupService();
        }
        benhnhanphieubaoanService.myCreate(benhNhanPhieuBaoAn, listBnCda, listBnGa, isUpdate);
    }
   
    public List<BenhNhanPhieuBaoAn> findByPbaMaso(Integer pbaMaso) {
        if (benhnhanphieubaoanService == null) {
            benhnhanphieubaoanService = lookupService();
        }
        return benhnhanphieubaoanService.findByPbaMaso(pbaMaso);
    }
    public int removeByPbaMaso(Integer pbaMaso) {
       if (benhnhanphieubaoanService == null) {
            benhnhanphieubaoanService = lookupService();
        }
        return benhnhanphieubaoanService.removeByPbaMaso(pbaMaso); 
    }
}
