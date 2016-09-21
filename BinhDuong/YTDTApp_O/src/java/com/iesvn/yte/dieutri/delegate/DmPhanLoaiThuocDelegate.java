/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.dieutri.intf.DmPhanLoaiThuocInterface;
import com.iesvn.yte.locator.LookupServiceUtils;

import java.util.List;
/**
 *
 * @author ThoVNA
 */
public class DmPhanLoaiThuocDelegate {
    private DmPhanLoaiThuocInterface dmPhanLoaiThuocService;

    public static DmPhanLoaiThuocDelegate getInstance() {
        return new DmPhanLoaiThuocDelegate();
    }
    private DmPhanLoaiThuocInterface lookupService() {
        return (DmPhanLoaiThuocInterface) LookupServiceUtils.lookupService("DmPhanLoaiThuocFacade");
    }
    public void create(DmPhanLoaiThuoc dmPhanLoaiThuoc) {
        if (dmPhanLoaiThuocService == null) {
            dmPhanLoaiThuocService = lookupService();
        }
        dmPhanLoaiThuocService.create(dmPhanLoaiThuoc);
    }

    public void edit(DmPhanLoaiThuoc dmPhanLoaiThuoc) {
        if (dmPhanLoaiThuocService == null) {
            dmPhanLoaiThuocService = lookupService();
        }
        dmPhanLoaiThuocService.edit(dmPhanLoaiThuoc);
    }

    public void remove(DmPhanLoaiThuoc dmPhanLoaiThuoc) {
        if (dmPhanLoaiThuocService == null) {
            dmPhanLoaiThuocService = lookupService();
        }
        dmPhanLoaiThuocService.remove(dmPhanLoaiThuoc);
    }

    public DmPhanLoaiThuoc find(Object id) {
        if (dmPhanLoaiThuocService == null) {
            dmPhanLoaiThuocService = lookupService();
        }
        return dmPhanLoaiThuocService.find(id);
    }

    public List<DmPhanLoaiThuoc> findAll() {
        if (dmPhanLoaiThuocService == null) {
            dmPhanLoaiThuocService = lookupService();
        }
        return dmPhanLoaiThuocService.findAll();
    }

    public List<DmPhanLoaiThuoc> findByDtdmloaiMa(String maLoai) {
        if (dmPhanLoaiThuocService == null) {
            dmPhanLoaiThuocService = lookupService();
        }
        return dmPhanLoaiThuocService.findByDtdmloaiMa(maLoai);
    }

    public List<DmPhanLoaiThuoc> findByMaAndTenAndDmloaiMa(String ma, String ten, Integer dmLoaithuocMaso){
        if (dmPhanLoaiThuocService == null) {
            dmPhanLoaiThuocService = lookupService();
        }
        return dmPhanLoaiThuocService.findByMaAndTenAndDmloaiMa(ma, ten, dmLoaithuocMaso);
    }
}
