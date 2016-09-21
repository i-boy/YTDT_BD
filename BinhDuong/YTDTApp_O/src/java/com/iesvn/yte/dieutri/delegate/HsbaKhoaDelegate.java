/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.intf.HsbaKhoaInterface;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ThanhDo
 */
public class HsbaKhoaDelegate {

    private HsbaKhoaInterface hsbaKhoaService;

    public static HsbaKhoaDelegate getInstance() {
        return new HsbaKhoaDelegate();
    }

    private HsbaKhoaInterface lookupService() {
        return (HsbaKhoaInterface) LookupServiceUtils.lookupService("HsbaKhoaFacade");
    }

    public void create(HsbaKhoa HsbaKhoa) {
        if (hsbaKhoaService == null) {
            hsbaKhoaService = lookupService();
        }
        hsbaKhoaService.create(HsbaKhoa);
    }

    public void edit(HsbaKhoa HsbaKhoa) {
        if (hsbaKhoaService == null) {
            hsbaKhoaService = lookupService();
        }
        hsbaKhoaService.edit(HsbaKhoa);
    }

    public void remove(HsbaKhoa HsbaKhoa) {
        if (hsbaKhoaService == null) {
            hsbaKhoaService = lookupService();
        }
        hsbaKhoaService.remove(HsbaKhoa);
    }

    public HsbaKhoa find(Object id) {
        if (hsbaKhoaService == null) {
            hsbaKhoaService = lookupService();
        }
        return hsbaKhoaService.find(id);
    }

    public List<HsbaKhoa> findAll() {
        if (hsbaKhoaService == null) {
            hsbaKhoaService = lookupService();
        }
        return hsbaKhoaService.findAll();
    }

    public HsbaKhoa findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) {
        if (hsbaKhoaService == null) {
            hsbaKhoaService = lookupService();
        }
        return hsbaKhoaService.findBySoVaoVienAndKhoaMa(soVaoVien, khoaMa);
    }
    
    public HsbaKhoa findBySoVaoVienAndKhoaMaAndTang(String soVaoVien, String khoaMa, Integer dmtangMaso) {
        if (hsbaKhoaService == null) {
            hsbaKhoaService = lookupService();
        }
        return hsbaKhoaService.findBySoVaoVienAndKhoaMaAndTang(soVaoVien, khoaMa, dmtangMaso);
    }

    public HsbaKhoa findBySoVaoVienLastHsbaKhoa(String soVaoVien) {
        if (hsbaKhoaService == null) {
            hsbaKhoaService = lookupService();
        }
        return hsbaKhoaService.findBySoVaoVienLastHsbaKhoa(soVaoVien);
    }

    public String benhNhanChuyenKhoa(HsbaKhoa hsbaKhoa, DmKhoa khoaChuyenDen, Date ngayGioChuyenKhoa, DmTang tangChuyenDen) {
        if (hsbaKhoaService == null) {
            hsbaKhoaService = lookupService();
        }
        return hsbaKhoaService.benhNhanChuyenKhoa(hsbaKhoa, khoaChuyenDen, ngayGioChuyenKhoa, tangChuyenDen);
    }

    public List<HsbaKhoa> findBySoVaoVien(String soVaoVien) {

        if (hsbaKhoaService == null) {
            hsbaKhoaService = lookupService();
        }
        return hsbaKhoaService.findBySoVaoVien(soVaoVien);

    }
}
