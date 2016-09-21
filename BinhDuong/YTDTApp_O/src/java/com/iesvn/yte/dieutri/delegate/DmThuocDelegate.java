/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.dieutri.intf.DmThuocInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DmThuocDelegate {
    private DmThuocInterface dmthuocService;

    public static DmThuocDelegate getInstance() {
        return new DmThuocDelegate();
    }

    private DmThuocInterface lookupService() {
        return (DmThuocInterface) LookupServiceUtils.lookupService("DmThuocFacade");
    }

    public void create(DmThuoc dmThuoc) {
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        dmthuocService.create(dmThuoc);
    }

    public void edit(DmThuoc dmThuoc) {
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        dmthuocService.edit(dmThuoc);
    }

    public void remove(DmThuoc dmThuoc) {
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        dmthuocService.remove(dmThuoc);
    }

    public DmThuoc find(Object id) {
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.find(id);
    }

    public List<DmThuoc> findAll() {
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.findAll();
    }

    public List<DmThuoc> findAll(Integer dmKhoaMaso){
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.findAll(dmKhoaMaso);
    }

    public List<DmThuoc> findAll(String dmKhoaMa){
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.findAll(dmKhoaMa);
    }

    public List<DmThuoc> findAll(Integer dmKhoaMaso1, Integer dmKhoaMaso2){
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.findAll(dmKhoaMaso1, dmKhoaMaso2);
    }

    public List<DmThuoc> findDongYAll(){
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.findDongYAll();
    }

    public List<DmThuoc> findDmThuocBHYT(){
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.findDmThuocBHYT();
    }

    public DmThuoc findByMaThuoc(String dmthuocMa) {
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.findByMaThuoc(dmthuocMa);
    }

    public DmThuoc findByTenThuoc(String dmthuocTen) {
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.findByTenThuoc(dmthuocTen);
    }

    public List<DmThuoc> findByLoaiPhanLoaiThuoc(String loaiThuocMa, String phanloaiThuocMa){
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.findByLoaiPhanLoaiThuoc( loaiThuocMa, phanloaiThuocMa);
    }

    public List<DmThuoc> findByLoaiPhanLoaiThuocNhomThuocDVTKho(String loaiThuocMa, String phanloaiThuocMa, Integer khoMaso){
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.findByLoaiPhanLoaiThuocNhomThuocDVTKho( loaiThuocMa, phanloaiThuocMa, khoMaso);
    }

    public List<DmThuoc> findByLoai_ListPhanLoaiThuoc(String loaiThuocMa, List<DmPhanLoaiThuoc> listPhanloaiThuocMa){
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.findByLoai_ListPhanLoaiThuoc( loaiThuocMa, listPhanloaiThuocMa);
    }

    public boolean hasThuoInPhanLoaiThuoc(Integer phanloaiThuocMaso){
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.hasThuoInPhanLoaiThuoc(phanloaiThuocMaso);
    }

    public boolean updateFieldTonKho(){
        if (dmthuocService == null) {
            dmthuocService = lookupService();
        }
        return dmthuocService.updateFieldTonKho();
    }
}
