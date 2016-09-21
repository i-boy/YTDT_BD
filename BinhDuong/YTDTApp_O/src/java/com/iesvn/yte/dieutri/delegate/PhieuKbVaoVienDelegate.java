/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.PhieuKbVaoVien;
import com.iesvn.yte.dieutri.intf.PhieuKbVaoVienInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class PhieuKbVaoVienDelegate {

    private PhieuKbVaoVienInterface PhieuKbVaoVienService;

    public static PhieuKbVaoVienDelegate getInstance() {
        return new PhieuKbVaoVienDelegate();
    }

    private PhieuKbVaoVienInterface lookupService() {
        return (PhieuKbVaoVienInterface) LookupServiceUtils.lookupService("PhieuKbVaoVienFacade");
    }

    public void create(PhieuKbVaoVien obj) {
        if (PhieuKbVaoVienService == null) {
            PhieuKbVaoVienService = lookupService();
        }
        PhieuKbVaoVienService.create(obj);
    }

    public void edit(PhieuKbVaoVien obj) {
        if (PhieuKbVaoVienService == null) {
            PhieuKbVaoVienService = lookupService();
        }
        PhieuKbVaoVienService.edit(obj);
    }

    public void remove(PhieuKbVaoVien obj) {
        if (PhieuKbVaoVienService == null) {
            PhieuKbVaoVienService = lookupService();
        }
        PhieuKbVaoVienService.remove(obj);
    }

    public PhieuKbVaoVien find(Object id) {
        if (PhieuKbVaoVienService == null) {
            PhieuKbVaoVienService = lookupService();
        }
        return PhieuKbVaoVienService.find(id);
    }

    public List<PhieuKbVaoVien> findAll() {
        if (PhieuKbVaoVienService == null) {
            PhieuKbVaoVienService = lookupService();
        }
        return PhieuKbVaoVienService.findAll();
    }

    public List<PhieuKbVaoVien> findByPhieuKbVaoVien(String maPhieu) {

        if (PhieuKbVaoVienService == null) {
            PhieuKbVaoVienService = lookupService();
        }
        return PhieuKbVaoVienService.findByPhieuKbVaoVien(maPhieu);
    }

    public PhieuKbVaoVien findByMaThamKham(Integer maThamKham) {

        if (PhieuKbVaoVienService == null) {
            PhieuKbVaoVienService = lookupService();
        }
        return PhieuKbVaoVienService.findByMaThamKham(maThamKham);
    }

    public String capNhatPhieuKbVaoVien(PhieuKbVaoVien obj, String sMaPhieu){

        if (PhieuKbVaoVienService == null) {
            PhieuKbVaoVienService = lookupService();
        }
        return PhieuKbVaoVienService.capNhatPhieuKbVaoVien(obj, sMaPhieu);
    }
}
