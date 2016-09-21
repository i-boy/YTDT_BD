/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.GiayCnSucKhoe;
import com.iesvn.yte.dieutri.intf.GiayCnSucKhoeInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;


/**
 *
 * @author root
 */
public class GiayCnSucKhoeDelegate {

    private GiayCnSucKhoeInterface GiayCnSucKhoeService;

    public static GiayCnSucKhoeDelegate getInstance() {
        return new GiayCnSucKhoeDelegate();
    }

    private GiayCnSucKhoeInterface lookupService() {
        return (GiayCnSucKhoeInterface) LookupServiceUtils.lookupService("GiayCnSucKhoeFacade");
    }

    public void create(GiayCnSucKhoe obj) {
        if (GiayCnSucKhoeService == null) {
            GiayCnSucKhoeService = lookupService();
        }
        GiayCnSucKhoeService.create(obj);
    }

    public void edit(GiayCnSucKhoe obj) {
        if (GiayCnSucKhoeService == null) {
            GiayCnSucKhoeService = lookupService();
        }
        GiayCnSucKhoeService.edit(obj);
    }

    public void remove(GiayCnSucKhoe obj) {
        if (GiayCnSucKhoeService == null) {
            GiayCnSucKhoeService = lookupService();
        }
        GiayCnSucKhoeService.remove(obj);
    }

    public GiayCnSucKhoe find(Object id) {
        if (GiayCnSucKhoeService == null) {
            GiayCnSucKhoeService = lookupService();
        }
        return GiayCnSucKhoeService.find(id);
    }

    public List<GiayCnSucKhoe> findAll() {
        if (GiayCnSucKhoeService == null) {
            GiayCnSucKhoeService = lookupService();
        }
        return GiayCnSucKhoeService.findAll();
    }

    public List<GiayCnSucKhoe> findByGiayCnSucKhoe(String maPhieu) {

        if (GiayCnSucKhoeService == null) {
            GiayCnSucKhoeService = lookupService();
        }
        return GiayCnSucKhoeService.findByGiayCnSucKhoe(maPhieu);
    }

    public GiayCnSucKhoe findByMaThamKham(Integer maThamKham) {

        if (GiayCnSucKhoeService == null) {
            GiayCnSucKhoeService = lookupService();
        }
        return GiayCnSucKhoeService.findByMaThamKham(maThamKham);
    }

    public void capNhatGiayCnSucKhoe(GiayCnSucKhoe obj) {

        if (GiayCnSucKhoeService == null) {
            GiayCnSucKhoeService = lookupService();
        }
        GiayCnSucKhoeService.capNhatGiayCnSucKhoe(obj);
    }
}
