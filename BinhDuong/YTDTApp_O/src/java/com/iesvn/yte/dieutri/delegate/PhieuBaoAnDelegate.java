/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.PhieuBaoAn;
import com.iesvn.yte.dieutri.intf.PhieuBaoAnInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class PhieuBaoAnDelegate {

    private PhieuBaoAnInterface phieubaoanService;

    public static PhieuBaoAnDelegate getInstance() {
        return new PhieuBaoAnDelegate();
    }

    private PhieuBaoAnInterface lookupService() {
        return (PhieuBaoAnInterface) LookupServiceUtils.lookupService("PhieuBaoAnFacade");
    }

    public void create(PhieuBaoAn obj) {
        if (phieubaoanService == null) {
            phieubaoanService = lookupService();
        }
        phieubaoanService.create(obj);
    }

    public void edit(PhieuBaoAn obj) {
        if (phieubaoanService == null) {
            phieubaoanService = lookupService();
        }
        phieubaoanService.edit(obj);
    }

    public void remove(PhieuBaoAn obj) {
        if (phieubaoanService == null) {
            phieubaoanService = lookupService();
        }
        phieubaoanService.remove(obj);
    }

    public PhieuBaoAn find(Object id) {
        if (phieubaoanService == null) {
            phieubaoanService = lookupService();
        }
        return phieubaoanService.find(id);
    }

    public List<PhieuBaoAn> findAll() {
        if (phieubaoanService == null) {
            phieubaoanService = lookupService();
        }
        return phieubaoanService.findAll();
    }

    public List<PhieuBaoAn> findByKhoaLoaiAn(String maKhoa, String maLoaiAn){
        if (phieubaoanService == null) {
            phieubaoanService = lookupService();
        }
        return phieubaoanService.findByKhoaLoaiAn(maKhoa, maLoaiAn);
    }

    public String capnhatphieubaoan(List<PhieuBaoAn> listPhieuBaoAn,
            String maphieuBaoAn) {

        if (phieubaoanService == null) {
            phieubaoanService = lookupService();
        }
        return phieubaoanService.capnhatphieubaoan(listPhieuBaoAn, maphieuBaoAn);
    }

    public List<PhieuBaoAn> findByPhieuBaoAn(String maPhieu) {

        if (phieubaoanService == null) {
            phieubaoanService = lookupService();
        }
        return phieubaoanService.findByPhieuBaoAn(maPhieu);
    }
    
    public PhieuBaoAn findByKhoaNgayAn(String maKhoa, Date ngayAn) {
        if (phieubaoanService == null) {
            phieubaoanService = lookupService();
        }
        return phieubaoanService.findByKhoaNgayAn(maKhoa, ngayAn);
    }
    
    public PhieuBaoAn myCreate(PhieuBaoAn phieuBaoAn, boolean isUpdate) {
        if (phieubaoanService == null) {
            phieubaoanService = lookupService();
        }
        return phieubaoanService.myCreate(phieuBaoAn, isUpdate);
    }
    public PhieuBaoAn findByKhoaNgayAn2(String maKhoa, Date ngayAn) {
        if (phieubaoanService == null) {
            phieubaoanService = lookupService();
        }
        return phieubaoanService.findByKhoaNgayAn2(maKhoa, ngayAn);
    }
}
