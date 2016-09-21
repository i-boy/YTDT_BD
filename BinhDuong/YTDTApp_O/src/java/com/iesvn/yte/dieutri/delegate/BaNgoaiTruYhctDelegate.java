/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.entity.BaNgoaiTruYhct;
import com.iesvn.yte.dieutri.intf.BaNgoaiTruYhctInterface;
import java.util.List;

/**
 *
 * @author i-boy
 */
public class BaNgoaiTruYhctDelegate {

    private BaNgoaiTruYhctInterface baNgoaiTruYhctService;

    public static BaNgoaiTruYhctDelegate getInstance() {
        return new BaNgoaiTruYhctDelegate();
    }

    private BaNgoaiTruYhctInterface lookupService() {
        return (BaNgoaiTruYhctInterface) LookupServiceUtils.lookupService("BaNgoaiTruYhctFacade");
    }

    public void create(BaNgoaiTruYhct baNgoaiTruYhct) {
        if (baNgoaiTruYhctService == null) {
            baNgoaiTruYhctService = lookupService();
        }
        baNgoaiTruYhctService.create(baNgoaiTruYhct);
    }

    public void edit(BaNgoaiTruYhct baNgoaiTruYhct) {
        if (baNgoaiTruYhctService == null) {
            baNgoaiTruYhctService = lookupService();
        }
        baNgoaiTruYhctService.edit(baNgoaiTruYhct);
    }

    public void remove(BaNgoaiTruYhct baNgoaiTruYhct) {
        if (baNgoaiTruYhctService == null) {
            baNgoaiTruYhctService = lookupService();
        }
        baNgoaiTruYhctService.remove(baNgoaiTruYhct);
    }

    public BaNgoaiTruYhct find(Object id) {
        if (baNgoaiTruYhctService == null) {
            baNgoaiTruYhctService = lookupService();
        }
        return baNgoaiTruYhctService.find(id);
    }

    public List<BaNgoaiTruYhct> findAll() {
        if (baNgoaiTruYhctService == null) {
            baNgoaiTruYhctService = lookupService();
        }
        return baNgoaiTruYhctService.findAll();
    }


     public BaNgoaiTruYhct getBANgoaiTruYhct(Integer thamkham) {
          if (baNgoaiTruYhctService == null) {
            baNgoaiTruYhctService = lookupService();
        }
        return baNgoaiTruYhctService.getBANgoaiTruYhct(thamkham);
     }
      public String capNhatBenhAnNgoaiTruYhct(BaNgoaiTruYhct obj, String sMaPhieu) {
          if (baNgoaiTruYhctService == null) {
            baNgoaiTruYhctService = lookupService();
        }
        return baNgoaiTruYhctService.capNhatBenhAnNgoaiTruYhct(obj, sMaPhieu);
      }
      public BaNgoaiTruYhct findBySotheBHAndMaBNAndBanKhamYhct(String sotheBH, String maBN, Integer bankham){
          if (baNgoaiTruYhctService == null) {
            baNgoaiTruYhctService = lookupService();
        }
        return baNgoaiTruYhctService.findBySotheBHAndMaBNAndBanKhamYhct(sotheBH, maBN, bankham);
      }

}
