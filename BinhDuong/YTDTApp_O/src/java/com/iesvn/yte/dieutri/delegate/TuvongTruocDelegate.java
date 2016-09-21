/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.TuvongTruocInterface;
import com.iesvn.yte.dieutri.entity.TuvongTruoc;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class TuvongTruocDelegate {
private TuvongTruocInterface tuvongTruocService;

    public static TuvongTruocDelegate getInstance() {
        return new TuvongTruocDelegate();
    }

    private TuvongTruocInterface lookupService() {
        return (TuvongTruocInterface) LookupServiceUtils.lookupService("TuvongTruocFacade");
    }

    public void create(TuvongTruoc tuvongTruoc) {
        if (tuvongTruocService == null) {
            tuvongTruocService = lookupService();
        }
        tuvongTruocService.create(tuvongTruoc);
    }

    public void edit(TuvongTruoc tuvongTruoc) {
        if (tuvongTruocService == null) {
            tuvongTruocService = lookupService();
        }
        tuvongTruocService.edit(tuvongTruoc);
    }

    public void remove(TuvongTruoc tuvongTruoc) {
        if (tuvongTruocService == null) {
            tuvongTruocService = lookupService();
        }
        tuvongTruocService.remove(tuvongTruoc);
    }

    public TuvongTruoc find(Object id) {
        if (tuvongTruocService == null) {
            tuvongTruocService = lookupService();
        }
        return tuvongTruocService.find(id);
    }

    public List<TuvongTruoc> findAll() {
        if (tuvongTruocService == null) {
            tuvongTruocService = lookupService();
        }
        return tuvongTruocService.findAll();
    }

     public TuvongTruoc getTuvongTruoc(Integer thamkham) {
          if (tuvongTruocService == null) {
            tuvongTruocService = lookupService();
        }
        return tuvongTruocService.getTuvongTruoc(thamkham);
     }
//      public String capNhatTuvongTruoc(TuvongTruoc obj, String sMaPhieu) {
//          if (tuvongTruocService == null) {
//            tuvongTruocService = lookupService();
//        }
//        return tuvongTruocService.capNhatTuvongTruoc(obj, sMaPhieu);
//      }
}


