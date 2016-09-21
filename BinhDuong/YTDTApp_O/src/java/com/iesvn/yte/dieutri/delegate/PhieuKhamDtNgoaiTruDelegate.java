/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.CtPhieuKhamDtNgoaiTru;
import com.iesvn.yte.dieutri.entity.PhieuKhamDtNgoaiTru;
import com.iesvn.yte.dieutri.intf.PhieuKhamDtNgoaiTruInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;


/**
 *
 * @author James
 */
public class PhieuKhamDtNgoaiTruDelegate {

    private PhieuKhamDtNgoaiTruInterface PhieuKhamDtNgoaiTruService;

    public static PhieuKhamDtNgoaiTruDelegate getInstance() {
        return new PhieuKhamDtNgoaiTruDelegate();
    }

    private PhieuKhamDtNgoaiTruInterface lookupService() {
        return (PhieuKhamDtNgoaiTruInterface) LookupServiceUtils.lookupService("PhieuKhamDtNgoaiTruFacade");
    }

    public void create(PhieuKhamDtNgoaiTru obj) {
        if (PhieuKhamDtNgoaiTruService == null) {
            PhieuKhamDtNgoaiTruService = lookupService();
        }
        PhieuKhamDtNgoaiTruService.create(obj);
    }

    public void edit(PhieuKhamDtNgoaiTru obj) {
        if (PhieuKhamDtNgoaiTruService == null) {
            PhieuKhamDtNgoaiTruService = lookupService();
        }
        PhieuKhamDtNgoaiTruService.edit(obj);
    }

    public void remove(PhieuKhamDtNgoaiTru obj) {
        if (PhieuKhamDtNgoaiTruService == null) {
            PhieuKhamDtNgoaiTruService = lookupService();
        }
        PhieuKhamDtNgoaiTruService.remove(obj);
    }

    public PhieuKhamDtNgoaiTru find(Object id) {
        if (PhieuKhamDtNgoaiTruService == null) {
            PhieuKhamDtNgoaiTruService = lookupService();
        }
        return PhieuKhamDtNgoaiTruService.find(id);
    }

    public List<PhieuKhamDtNgoaiTru> findAll() {
        if (PhieuKhamDtNgoaiTruService == null) {
            PhieuKhamDtNgoaiTruService = lookupService();
        }
        return PhieuKhamDtNgoaiTruService.findAll();
    }

    public List<PhieuKhamDtNgoaiTru> findByPhieuKhamDtNgoaiTru(String maPhieu) {

        if (PhieuKhamDtNgoaiTruService == null) {
            PhieuKhamDtNgoaiTruService = lookupService();
        }
        return PhieuKhamDtNgoaiTruService.findByPhieuKhamDtNgoaiTru(maPhieu);
    }

    public String capNhatPhieuKhamDtNgoaiTru(PhieuKhamDtNgoaiTru obj, String sMaPhieu, List<CtPhieuKhamDtNgoaiTru> lstCT){

        if (PhieuKhamDtNgoaiTruService == null) {
            PhieuKhamDtNgoaiTruService = lookupService();
        }
        return PhieuKhamDtNgoaiTruService.capNhatPhieuKhamDtNgoaiTru(obj, sMaPhieu,lstCT);
    }

    public PhieuKhamDtNgoaiTru findByMaThamKham(Integer mathamkham) {
        if (PhieuKhamDtNgoaiTruService == null) {
            PhieuKhamDtNgoaiTruService = lookupService();
        }
        return PhieuKhamDtNgoaiTruService.findByMaThamKham(mathamkham);
    }
    
    public void removeAllPhieuKhamDtNgoaiTru(PhieuKhamDtNgoaiTru phieuKhamDtNgoaiTru) {
        if (PhieuKhamDtNgoaiTruService == null) {
            PhieuKhamDtNgoaiTruService = lookupService();
        }
        PhieuKhamDtNgoaiTruService.removeAllPhieuKhamDtNgoaiTru(phieuKhamDtNgoaiTru);
    }

    public PhieuKhamDtNgoaiTru findBySotheBHAndMaBNAndBanKham(String sotheBH, String maBN, Integer bankham){
        if (PhieuKhamDtNgoaiTruService == null) {
            PhieuKhamDtNgoaiTruService = lookupService();
        }
        return PhieuKhamDtNgoaiTruService.findBySotheBHAndMaBNAndBanKham(sotheBH, maBN, bankham);
    }
}
