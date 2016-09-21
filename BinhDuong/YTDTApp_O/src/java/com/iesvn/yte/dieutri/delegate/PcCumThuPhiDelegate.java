/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.dieutri.intf.PcCumThuPhiInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class PcCumThuPhiDelegate {
    private PcCumThuPhiInterface pccumthuphiService;

    public static PcCumThuPhiDelegate getInstance() {
        return new PcCumThuPhiDelegate();
    }

    private PcCumThuPhiInterface lookupService() {
        return (PcCumThuPhiInterface) LookupServiceUtils.lookupService("PcCumThuPhiFacade");
    }
    
    public void create(PcCumThuPhi pcCumThuPhi) {
        if (pccumthuphiService == null) {
            pccumthuphiService = lookupService();
        }
        pccumthuphiService.create(pcCumThuPhi);
    }
    
    public void edit(PcCumThuPhi pcCumThuPhi) {
        if (pccumthuphiService == null) {
            pccumthuphiService = lookupService();
        }
        pccumthuphiService.edit(pcCumThuPhi);
    }
    
    public void remove(PcCumThuPhi pcCumThuPhi) {
        if (pccumthuphiService == null) {
            pccumthuphiService = lookupService();
        }
        pccumthuphiService.remove(pcCumThuPhi);
    }
    
    public PcCumThuPhi find(Object id) {
        if (pccumthuphiService == null) {
            pccumthuphiService = lookupService();
        }
        return pccumthuphiService.find(id);
    }
    
    public List<PcCumThuPhi> findAll() {
        if (pccumthuphiService == null) {
            pccumthuphiService = lookupService();
        }
        return pccumthuphiService.findAll();
    }
    
    public int ghinhan(List<PcCumThuPhi> list){
        if (pccumthuphiService == null) {
            pccumthuphiService = lookupService();
        }
        return pccumthuphiService.ghinhan(list);
    }
    
    public PcCumThuPhi findPcCumThuPhiByNgayAndNhanVien(Date date, String nvMa){
        if (pccumthuphiService == null) {
            pccumthuphiService = lookupService();
        }
        return pccumthuphiService.findPcCumThuPhiByNgayAndNhanVien(date, nvMa);
    }
    
    public List<PcCumThuPhi> findPcCumThuPhi(Date tungay, Date denngay, String cum){
        if (pccumthuphiService == null) {
            pccumthuphiService = lookupService();
        }
        return pccumthuphiService.findPcCumThuPhi(tungay, denngay, cum);
    }
}
