/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa;
import com.iesvn.yte.dieutri.intf.PhieuKhamChuyenKhoaInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class PhieuKhamChuyenKhoaDelegate {

    private PhieuKhamChuyenKhoaInterface PhieuKhamChuyenKhoaService;

    public static PhieuKhamChuyenKhoaDelegate getInstance() {
        return new PhieuKhamChuyenKhoaDelegate();
    }

    private PhieuKhamChuyenKhoaInterface lookupService() {
        return (PhieuKhamChuyenKhoaInterface) LookupServiceUtils.lookupService("PhieuKhamChuyenKhoaFacade");
    }

    public void create(PhieuKhamChuyenKhoa obj) {
        if (PhieuKhamChuyenKhoaService == null) {
            PhieuKhamChuyenKhoaService = lookupService();
        }
        PhieuKhamChuyenKhoaService.create(obj);
    }

    public void edit(PhieuKhamChuyenKhoa obj) {
        if (PhieuKhamChuyenKhoaService == null) {
            PhieuKhamChuyenKhoaService = lookupService();
        }
        PhieuKhamChuyenKhoaService.edit(obj);
    }

    public void remove(PhieuKhamChuyenKhoa obj) {
        if (PhieuKhamChuyenKhoaService == null) {
            PhieuKhamChuyenKhoaService = lookupService();
        }
        PhieuKhamChuyenKhoaService.remove(obj);
    }

    public PhieuKhamChuyenKhoa find(Object id) {
        if (PhieuKhamChuyenKhoaService == null) {
            PhieuKhamChuyenKhoaService = lookupService();
        }
        return PhieuKhamChuyenKhoaService.find(id);
    }

    public List<PhieuKhamChuyenKhoa> findAll() {
        if (PhieuKhamChuyenKhoaService == null) {
            PhieuKhamChuyenKhoaService = lookupService();
        }
        return PhieuKhamChuyenKhoaService.findAll();
    }

    public List<PhieuKhamChuyenKhoa> findByPhieuKhamChuyenKhoa(String maPhieu) {

        if (PhieuKhamChuyenKhoaService == null) {
            PhieuKhamChuyenKhoaService = lookupService();
        }
        return PhieuKhamChuyenKhoaService.findByPhieuKhamChuyenKhoa(maPhieu);
    }

    public PhieuKhamChuyenKhoa findByMaThamKham(Integer iMaThamKham) {

        if (PhieuKhamChuyenKhoaService == null) {
            PhieuKhamChuyenKhoaService = lookupService();
        }
        return PhieuKhamChuyenKhoaService.findByMaThamKham(iMaThamKham);
    }

    public String capNhatPhieuKhamChuyenKhoa(PhieuKhamChuyenKhoa obj, String sMaPhieu) {

        if (PhieuKhamChuyenKhoaService == null) {
            PhieuKhamChuyenKhoaService = lookupService();
        }
        return PhieuKhamChuyenKhoaService.capNhatPhieuKhamChuyenKhoa(obj, sMaPhieu);
    }
}
