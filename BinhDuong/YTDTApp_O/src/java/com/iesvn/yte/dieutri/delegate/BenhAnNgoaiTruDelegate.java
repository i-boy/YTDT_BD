/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;

import com.iesvn.yte.dieutri.entity.BenhAnNgoaiTru;

import com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru;
import com.iesvn.yte.dieutri.intf.BenhAnNgoaiTruInterface;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class BenhAnNgoaiTruDelegate {

    private BenhAnNgoaiTruInterface benhAnNgoaiTruService;

    public static BenhAnNgoaiTruDelegate getInstance() {
        return new BenhAnNgoaiTruDelegate();
    }

    private BenhAnNgoaiTruInterface lookupService() {
        return (BenhAnNgoaiTruInterface) LookupServiceUtils.lookupService("BenhAnNgoaiTruFacade");
    }

    public void create(BenhAnNgoaiTru benhAnNgoaiTru) {
        if (benhAnNgoaiTruService == null) {
            benhAnNgoaiTruService = lookupService();
        }
        benhAnNgoaiTruService.create(benhAnNgoaiTru);
    }

    public void edit(BenhAnNgoaiTru benhAnNgoaiTru) {
        if (benhAnNgoaiTruService == null) {
            benhAnNgoaiTruService = lookupService();
        }
        benhAnNgoaiTruService.edit(benhAnNgoaiTru);
    }

    public void remove(BenhAnNgoaiTru benhAnNgoaiTru) {
        if (benhAnNgoaiTruService == null) {
            benhAnNgoaiTruService = lookupService();
        }
        benhAnNgoaiTruService.remove(benhAnNgoaiTru);
    }

    public BenhAnNgoaiTru find(Object id) {
        if (benhAnNgoaiTruService == null) {
            benhAnNgoaiTruService = lookupService();
        }
        return benhAnNgoaiTruService.find(id);
    }

    public List<BenhAnNgoaiTru> findAll() {
        if (benhAnNgoaiTruService == null) {
            benhAnNgoaiTruService = lookupService();
        }
        return benhAnNgoaiTruService.findAll();
    }

    
     public BenhAnNgoaiTru getBANgoaiTru(Integer thamkham) {
          if (benhAnNgoaiTruService == null) {
            benhAnNgoaiTruService = lookupService();
        }
        return benhAnNgoaiTruService.getBANgoaiTru(thamkham);
     }
      public String capNhatBenhAnNgoaiTru(BenhAnNgoaiTru obj, String sMaPhieu, List<CtBenhAnNgoaiTru> lstCT) {
          if (benhAnNgoaiTruService == null) {
            benhAnNgoaiTruService = lookupService();
        }
        return benhAnNgoaiTruService.capNhatBenhAnNgoaiTru(obj, sMaPhieu, lstCT);
      }
      public BenhAnNgoaiTru findBySotheBHAndMaBNAndBanKham(String sotheBH, String maBN, Integer bankham){
          if (benhAnNgoaiTruService == null) {
            benhAnNgoaiTruService = lookupService();
        }
        return benhAnNgoaiTruService.findBySotheBHAndMaBNAndBanKham(sotheBH, maBN, bankham);
      }
      
}

