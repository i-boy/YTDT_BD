/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmNhanVienInterface;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.entity.DmKhoa;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmNhanVienDelegate {

    private DtDmNhanVienInterface dtdmnhanvienService;

    public static DtDmNhanVienDelegate getInstance() {
        return new DtDmNhanVienDelegate();
    }

    private DtDmNhanVienInterface lookupService() {
        return (DtDmNhanVienInterface) LookupServiceUtils.lookupService("DtDmNhanVienFacade");
    }

    public void create(DtDmNhanVien dtDmNhanVien) {
        if (dtdmnhanvienService == null) {
            dtdmnhanvienService = lookupService();
        }
        dtdmnhanvienService.create(dtDmNhanVien);
    }

    public void edit(DtDmNhanVien dtDmNhanVien) {
        if (dtdmnhanvienService == null) {
            dtdmnhanvienService = lookupService();
        }
        dtdmnhanvienService.edit(dtDmNhanVien);
    }

    public void remove(DtDmNhanVien dtDmNhanVien) {
        if (dtdmnhanvienService == null) {
            dtdmnhanvienService = lookupService();
        }
        dtdmnhanvienService.remove(dtDmNhanVien);
    }

    public DtDmNhanVien find(Object id) {
        if (dtdmnhanvienService == null) {
            dtdmnhanvienService = lookupService();
        }
        return dtdmnhanvienService.find(id);
    }

    public List<DtDmNhanVien> findAll() {
        if (dtdmnhanvienService == null) {
            dtdmnhanvienService = lookupService();
        }
        return dtdmnhanvienService.findAll();
    }
    
    public List<DtDmBanKham> getListBanKham(Integer nvMaso) {
        if (dtdmnhanvienService == null) {
            dtdmnhanvienService = lookupService();
        }
        return dtdmnhanvienService.getListBanKham(nvMaso);
    }
    
    public List<DmKhoa> getListKhoa(Integer nvMaso) {
        if (dtdmnhanvienService == null) {
            dtdmnhanvienService = lookupService();
        }
        return dtdmnhanvienService.getListKhoa(nvMaso);
    }
    
    public DtDmNhanVien findByNd(String tenNd) {
        if (dtdmnhanvienService == null) {
            dtdmnhanvienService = lookupService();
        }
        return dtdmnhanvienService.findByNd(tenNd);
    }

    public DtDmNhanVien findByMaNhanVien(String dtdmnhanvienMa) {
        if (dtdmnhanvienService == null) {
            dtdmnhanvienService = lookupService();
        }
        return dtdmnhanvienService.findByMaNhanVien(dtdmnhanvienMa);
    }

    public DtDmNhanVien findByTenNhanVien(String dtdmnhanvienTen) {
        if (dtdmnhanvienService == null) {
            dtdmnhanvienService = lookupService();
        }
        return dtdmnhanvienService.findByTenNhanVien(dtdmnhanvienTen);
    }
    
     public List<DtDmNhanVien> findAllWithOrderBy() {
        if (dtdmnhanvienService == null) {
            dtdmnhanvienService = lookupService();
        }
        return dtdmnhanvienService.findAllWithOrderBy();
    }
}
