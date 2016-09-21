/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
import com.iesvn.yte.dieutri.intf.HsbaPhieuPhauThuatThuThuatInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author i-boy
 */
public class HsbaPhieuPhauThuatThuThuatDelegate {

    private HsbaPhieuPhauThuatThuThuatInterface hsbaPhieuPhauThuatThuThuatService;

    public static HsbaPhieuPhauThuatThuThuatDelegate getInstance() {
        return new HsbaPhieuPhauThuatThuThuatDelegate();
    }

    private HsbaPhieuPhauThuatThuThuatInterface lookupService() {
        return (HsbaPhieuPhauThuatThuThuatInterface) LookupServiceUtils.lookupService("HsbaPhieuPhauThuatThuThuatFacade");
    }

    public void create(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        hsbaPhieuPhauThuatThuThuatService.create(hsbaPhieuPhauThuatThuThuat);
    }

    public void edit(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        hsbaPhieuPhauThuatThuThuatService.edit(hsbaPhieuPhauThuatThuThuat);
    }

    public void remove(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        hsbaPhieuPhauThuatThuThuatService.remove(hsbaPhieuPhauThuatThuThuat);
    }

    public HsbaPhieuPhauThuatThuThuat find(Object id) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.find(id);
    }

    public List<HsbaPhieuPhauThuatThuThuat> findAll() {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.findAll();
    }

    public String insert(HsbaPhieuPhauThuatThuThuat obj) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.insert(obj);
    }

    public String update(HsbaPhieuPhauThuatThuThuat obj) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.update(obj);
    }

    public HsbaPhieuPhauThuatThuThuat findByHsbapptttMa(String ma) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.findByHsbapptttMa(ma);
    }

    public List<DtDmNhanVien> findBacsiByHsbapptttMa(String ma) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.findBacsiByHsbapptttMa(ma);
    }

    public List<DtDmNhanVien> findBacsigmByHsbapptttMa(String ma) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.findBacsigmByHsbapptttMa(ma);
    }

    public String createHsbaPhieuPhauThuatThuThuat(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.createHsbaPhieuPhauThuatThuThuat(hsbaPhieuPhauThuatThuThuat, bacsiList, bacsigmList);
    }

    public String editHsbaPhieuPhauThuatThuThuat(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.editHsbaPhieuPhauThuatThuThuat(hsbaPhieuPhauThuatThuThuat, bacsiList, bacsigmList);
    }

    public HsbaPhieuPhauThuatThuThuat findByHsba(String hsbaSovaovien) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.findByHsba(hsbaSovaovien);
    }

    public List<HsbaPhieuPhauThuatThuThuat> findAllByHsba(String hsbaSovaovien) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.findAllByHsba(hsbaSovaovien);
    }
    
    public String getPtvByHsba(String hsbaSovaovien) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.getPtvByHsba(hsbaSovaovien);
    }

    public String getPtvByHsbappttt(HsbaPhieuPhauThuatThuThuat ppttt) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.getPtvByHsbappttt(ppttt);
    }

    public String getPtvByHsbappttt(String ma) {
        if (hsbaPhieuPhauThuatThuThuatService == null) {
            hsbaPhieuPhauThuatThuThuatService = lookupService();
        }
        return hsbaPhieuPhauThuatThuThuatService.getPtvByHsbappttt(ma);
    }
}
