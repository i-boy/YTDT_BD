package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.CtTraNhaCungCap;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.entity.PhieuTraNhaCungCap;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.intf.PhieuTraNhaCungCapInterface;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class PhieuTraNhaCungCapDelegate {

    private PhieuTraNhaCungCapInterface phieutranhacungcapService;

    public static PhieuTraNhaCungCapDelegate getInstance() {
        return new PhieuTraNhaCungCapDelegate();
    }

    private PhieuTraNhaCungCapInterface lookupService() {
        return (PhieuTraNhaCungCapInterface) LookupServiceUtils.lookupService("PhieuTraNhaCungCapFacade");
    }

    public void create(PhieuTraNhaCungCap phieuTraNhaCC) {
        if (phieutranhacungcapService == null) {
            phieutranhacungcapService = lookupService();
        }
        phieutranhacungcapService.create(phieuTraNhaCC);
    }

    public void edit(PhieuTraNhaCungCap phieuTraNhaCC) {
        if (phieutranhacungcapService == null) {
            phieutranhacungcapService = lookupService();
        }
        phieutranhacungcapService.edit(phieuTraNhaCC);
    }

    public void remove(PhieuTraNhaCungCap phieuTraNhaCC) {
        if (phieutranhacungcapService == null) {
            phieutranhacungcapService = lookupService();
        }
        phieutranhacungcapService.remove(phieuTraNhaCC);
    }

    public PhieuTraNhaCungCap find(Object id) {
        if (phieutranhacungcapService == null) {
            phieutranhacungcapService = lookupService();
        }
        return phieutranhacungcapService.find(id);
    }

    public List<PhieuTraNhaCungCap> findAll() {
        if (phieutranhacungcapService == null) {
            phieutranhacungcapService = lookupService();
        }
        return phieutranhacungcapService.findAll();
    }

    public String createPhieuTraNhaCungCap(PhieuTraNhaCungCap pxk, List<CtTraNhaCungCap> listCtTraNhaCungCap, List<TonKho> listTkXuat) {
        if (phieutranhacungcapService == null) {
            phieutranhacungcapService = lookupService();
        }
        return phieutranhacungcapService.createPhieuTraNhaCungCap(pxk, listCtTraNhaCungCap, listTkXuat);
    }

    public String updatePhieuTraNhaCungCap(PhieuTraNhaCungCap objPhieuTraNCC, List<CtTraNhaCungCap> listCtTraNCC, List<TonKho> listTkXuat) {
        if (phieutranhacungcapService == null) {
            phieutranhacungcapService = lookupService();
        }
        return phieutranhacungcapService.updatePhieuTraNhaCungCap(objPhieuTraNCC, listCtTraNCC, listTkXuat);
    }

    public PhieuTraNhaCungCap findPhieuTraNhaCungCapByMa(String maPhieu) {
        if (phieutranhacungcapService == null) {
            phieutranhacungcapService = lookupService();
        }
        return phieutranhacungcapService.findPhieuTraNhaCungCapByMa(maPhieu);
    }
}

