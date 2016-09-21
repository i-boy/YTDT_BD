/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.GiayChungThuong;
import com.iesvn.yte.dieutri.intf.GiayChungThuongInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author quang
 */
public class GiayChungThuongDelegate {
private GiayChungThuongInterface giayChungThuongService;

    public static GiayChungThuongDelegate getInstance() {
        return new GiayChungThuongDelegate();
    }

    private GiayChungThuongInterface lookupService() {
        return (GiayChungThuongInterface) LookupServiceUtils.lookupService("GiayChungThuongFacade");
    }

    public void create(GiayChungThuong giayChungThuong) {
        if (giayChungThuongService == null) {
            giayChungThuongService = lookupService();
        }
        giayChungThuongService.create(giayChungThuong);
    }

    public void edit(GiayChungThuong giayChungThuong) {
        if (giayChungThuongService == null) {
            giayChungThuongService = lookupService();
        }
        giayChungThuongService.edit(giayChungThuong);
    }

    public void remove(GiayChungThuong giayChungThuong) {
        if (giayChungThuongService == null) {
            giayChungThuongService = lookupService();
        }
        giayChungThuongService.remove(giayChungThuong);
    }

    public GiayChungThuong find(Object id) {
        if (giayChungThuongService == null) {
            giayChungThuongService = lookupService();
        }
        return giayChungThuongService.find(id);
    }

    public List<GiayChungThuong> findAll() {
        if (giayChungThuongService == null) {
            giayChungThuongService = lookupService();
        }
        return giayChungThuongService.findAll();
    }

     public GiayChungThuong getGiayChungThuong(Integer thamkham) {
          if (giayChungThuongService == null) {
            giayChungThuongService = lookupService();
        }
        return giayChungThuongService.getGiayChungThuong(thamkham);
    }
}
