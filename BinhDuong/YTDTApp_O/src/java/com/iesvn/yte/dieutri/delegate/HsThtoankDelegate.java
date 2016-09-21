/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsThtoankInterface;

import com.iesvn.yte.dieutri.entity.HsThtoank;
import java.util.List;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import java.util.Date;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsThtoankDelegate {

    private HsThtoankInterface hsthtoankService;

    public static HsThtoankDelegate getInstance() {
        return new HsThtoankDelegate();
    }

    private HsThtoankInterface lookupService() {
        return (HsThtoankInterface) LookupServiceUtils.lookupService("HsThtoankFacade");
    }

    public void create(HsThtoank hsThtoank) {
        if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        hsthtoankService.create(hsThtoank);
    }

    public Boolean edit(HsThtoank hsThtoank) {
        if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        return hsthtoankService.edit(hsThtoank);
    }

    public void remove(HsThtoank hsThtoank) {
        if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        hsthtoankService.remove(hsThtoank);
    }

    public HsThtoank find(Object id) {
        if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        return hsthtoankService.find(id);
    }

    public List<HsThtoank> findAll() {
        if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        return hsthtoankService.findAll();
    }

    public HsThtoank findBytiepdonMa(String tiepdonMa) {
        if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        return hsthtoankService.findBytiepdonMa(tiepdonMa);
    }

    public HsThtoank findAllBytiepdonMa(String tiepdonMa) {
        if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        return hsthtoankService.findAllBytiepdonMa(tiepdonMa);
    }

    public HsThtoank findByMaPhieu(String maPhieuTT) {
        if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        return hsthtoankService.findByMaPhieu(maPhieuTT);
    }

     public HsThtoank findBytiepdonMaLast(String tiepdonMa) {
        if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        return hsthtoankService.findBytiepdonMaLast(tiepdonMa);
    }

     public String capNhatTTHsttk(HsThtoank hsThtoank, List<ClsKham> clslist, List<ThuocPhongKham> lstTNT, boolean capnhattonkho){

           if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        return hsthtoankService.capNhatTTHsttk(hsThtoank,clslist,lstTNT, capnhattonkho);
      }

     public List<HsThtoank> findByNgayTiepdon(Date tungay, Date denngay) {
          if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        return hsthtoankService.findByNgayTiepdon(tungay, denngay);
     } 
     
     public int updateNgayGioTTAndDaTT2Null(Date tungay, Date denngay) {
          if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        return hsthtoankService.updateNgayGioTTAndDaTT2Null(tungay, denngay);
     }
  public List<HsThtoank> findByNgayTiepdonAndDaTT(Date tungay, Date denngay) {
         if (hsthtoankService == null) {
            hsthtoankService = lookupService();
        }
        return hsthtoankService.findByNgayTiepdonAndDaTT(tungay, denngay);
     }
}