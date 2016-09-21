/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
import com.iesvn.yte.dieutri.intf.ChuyenVaoNoiTruInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author ies
 */
public class ChuyenVaoNoiTruDelegate {

    private ChuyenVaoNoiTruInterface ChuyenVaoNoiTruService;

    public static ChuyenVaoNoiTruDelegate getInstance() {
        return new ChuyenVaoNoiTruDelegate();
    }

    private ChuyenVaoNoiTruInterface lookupService() {
        return (ChuyenVaoNoiTruInterface) LookupServiceUtils.lookupService("ChuyenVaoNoiTruFacade");
    }

    public void create(ChuyenVaoNoiTru chuyenVaoNoiTru) {
        if (ChuyenVaoNoiTruService == null) {
            ChuyenVaoNoiTruService = lookupService();
        }
        ChuyenVaoNoiTruService.create(chuyenVaoNoiTru);
    }

    public void edit(ChuyenVaoNoiTru chuyenVaoNoiTru) {
        if (ChuyenVaoNoiTruService == null) {
            ChuyenVaoNoiTruService = lookupService();
        }
        ChuyenVaoNoiTruService.edit(chuyenVaoNoiTru);
    }

    public void remove(ChuyenVaoNoiTru chuyenVaoNoiTru) {
        if (ChuyenVaoNoiTruService == null) {
            ChuyenVaoNoiTruService = lookupService();
        }
        ChuyenVaoNoiTruService.remove(chuyenVaoNoiTru);
    }

    public ChuyenVaoNoiTru find(Object id) {
        if (ChuyenVaoNoiTruService == null) {
            ChuyenVaoNoiTruService = lookupService();
        }
        return ChuyenVaoNoiTruService.find(id);
    }
    
    public ChuyenVaoNoiTru findByMaTiepDon(String maTiepDon) {
        if (ChuyenVaoNoiTruService == null) {
            ChuyenVaoNoiTruService = lookupService();
        }
        return ChuyenVaoNoiTruService.findByMaTiepDon(maTiepDon);
    }
    public List<ChuyenVaoNoiTru> findAll() {
        if (ChuyenVaoNoiTruService == null) {
            ChuyenVaoNoiTruService = lookupService();
        }
        return ChuyenVaoNoiTruService.findAll();
    }

    public java.util.List<com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru> findRange(int[] range) {
        if (ChuyenVaoNoiTruService == null) {
            ChuyenVaoNoiTruService = lookupService();
        }
        return ChuyenVaoNoiTruService.findRange(range);
    }

    public int count(){
        if (ChuyenVaoNoiTruService == null) {
            ChuyenVaoNoiTruService = lookupService();
        }
        return ChuyenVaoNoiTruService.count();
    }
    public ChuyenVaoNoiTru findBySoVaoVien(String sovaovien) {
        if (ChuyenVaoNoiTruService == null) {
            ChuyenVaoNoiTruService = lookupService();
        }
        return ChuyenVaoNoiTruService.findBySoVaoVien(sovaovien);
    }
}
