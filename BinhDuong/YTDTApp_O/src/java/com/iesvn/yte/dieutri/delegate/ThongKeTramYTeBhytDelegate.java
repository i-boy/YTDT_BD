/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.ThongKeTramYTeBhyt;
import com.iesvn.yte.dieutri.intf.ThongKeTramYTeBhytInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author thanh
 */
public class ThongKeTramYTeBhytDelegate {
    private ThongKeTramYTeBhytInterface thongKeTramYTeBhytService;

    public static ThongKeTramYTeBhytDelegate getInstance() {
        return new ThongKeTramYTeBhytDelegate();
    }
    private ThongKeTramYTeBhytInterface lookupService() {
        return (ThongKeTramYTeBhytInterface) LookupServiceUtils.lookupService("ThongKeTramYTeBhytFacade");
    }
    
   public void create(ThongKeTramYTeBhyt thongKeTramYTeBhyt) {
        if (thongKeTramYTeBhytService == null) {
            thongKeTramYTeBhytService = lookupService();
        }
        thongKeTramYTeBhytService.create(thongKeTramYTeBhyt);
    }

    public void edit(ThongKeTramYTeBhyt thongKeTramYTeBhyt) {
        if (thongKeTramYTeBhytService == null) {
            thongKeTramYTeBhytService = lookupService();
        }
        thongKeTramYTeBhytService.edit(thongKeTramYTeBhyt);
    }

    public void remove(ThongKeTramYTeBhyt thongKeTramYTeBhyt) {
        if (thongKeTramYTeBhytService == null) {
            thongKeTramYTeBhytService = lookupService();
        }
        thongKeTramYTeBhytService.remove(thongKeTramYTeBhyt);
    }

    public ThongKeTramYTeBhyt find(Object id) {
        if (thongKeTramYTeBhytService == null) {
            thongKeTramYTeBhytService = lookupService();
        }
        return thongKeTramYTeBhytService.find(id);
    }

    public List<ThongKeTramYTeBhyt> findAll() {
        if (thongKeTramYTeBhytService == null) {
            thongKeTramYTeBhytService = lookupService();
        }
        return thongKeTramYTeBhytService.findAll();
    }
    public String luuTruTTThongKeTramYTeBHYT (List<ThongKeTramYTeBhyt> lstTKTram){
        
          if (thongKeTramYTeBhytService == null) {
            thongKeTramYTeBhytService = lookupService();
        }
        return thongKeTramYTeBhytService.luuTruTTThongKeTramYTeBHYT(lstTKTram);
    }
     public List<ThongKeTramYTeBhyt> findByThangNamTramYTe(String thang, String nam, String matramyte, Boolean noitru) {
         
            if (thongKeTramYTeBhytService == null) {
            thongKeTramYTeBhytService = lookupService();
        }
        return thongKeTramYTeBhytService.findByThangNamTramYTe(thang,nam,matramyte,noitru);
     }
        
}
