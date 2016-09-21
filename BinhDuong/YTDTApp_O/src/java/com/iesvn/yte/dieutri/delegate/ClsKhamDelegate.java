/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.ClsKhamInterface;

import com.iesvn.yte.dieutri.entity.ClsKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class ClsKhamDelegate {

    private ClsKhamInterface clskhamService;

    public static ClsKhamDelegate getInstance() {
        return new ClsKhamDelegate();
    }

    private ClsKhamInterface lookupService() {
        return (ClsKhamInterface) LookupServiceUtils.lookupService("ClsKhamFacade");
    }

    public void create(ClsKham clsKham) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        clskhamService.create(clsKham);
    }

    public void edit(ClsKham clsKham) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        clskhamService.edit(clsKham);
    }

    public void remove(ClsKham clsKham) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        clskhamService.remove(clsKham);
    }

    public ClsKham find(Object id) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.find(id);
    }

    public List<ClsKham> findAll() {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.findAll();
    }

    public java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> findByTiepdonma(java.lang.String tiepdonMa) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.findByTiepdonma(tiepdonMa);
    }

    public java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> findByBanKhamVaMaTiepDon(java.lang.String banKham, java.lang.String maTiepDon) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.findByBanKhamVaMaTiepDon(banKham, maTiepDon);
    }

    public java.lang.String createClsKham(java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> listCLS, String maTiepDon, String maBanKham) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.createClsKham(listCLS, maTiepDon, maBanKham);
    }

    public java.lang.String createClsKham(java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> listCLS, String maTiepDon) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.createClsKham(listCLS, maTiepDon);
    }

    public void removeItem(java.lang.String listclsMa, java.lang.String tiepdonMa, String maBanKham) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        clskhamService.removeItem(listclsMa, tiepdonMa, maBanKham);
    }

    public java.lang.String findTiepdonMa(java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> listCLS) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.findTiepdonMa(listCLS);
    }

    public java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> findByTiepdonMaBhyt(java.lang.String tiepdonMa) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.findByTiepdonMaBhyt(tiepdonMa);
    }

    public List<ClsKham> findByMaPhieu(String maPhieu) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.findByMaPhieu(maPhieu);

    }

    public String createClsKhamForCLSPhauThuat(List<ClsKham> listCLS, String maTiepDon, String maBanKham) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.createClsKhamForCLSPhauThuat(listCLS, maTiepDon, maBanKham);
    }

    public java.lang.String createClsKhamHoanTra(java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> listCLS, String maTiepDon, String maBanKham) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.createClsKhamHoanTra(listCLS, maTiepDon, maBanKham);
    }

    public java.lang.String createClsKhamHoanTra(java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> listCLS, String maTiepDon) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.createClsKhamHoanTra(listCLS, maTiepDon);
    }

    public List<ClsKham> findByMaPhieuHoan(String maPhieu) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.findByMaPhieuHoan(maPhieu);
    }

    public Double getSoTienTuPhieu(String maTiepDon, String maPhieu) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.getSoTienTuPhieu(maTiepDon, maPhieu);

    }

    public List<ClsKham> findByTiepdonMaVaLoaiClsKham(String tiepdonMa) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.findByTiepdonMaVaLoaiClsKham(tiepdonMa);

    }
    
    public List<ClsKham> findByTiepdonmaAndKhoa(String tiepdonMa, String khoaMaso){
         if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.findByTiepdonmaAndKhoa(tiepdonMa, khoaMaso);
    }
    public String createClsKham_v2(List<ClsKham> listCLS, String maTiepDon, String maPhieuTT) {
        if (clskhamService == null) {
            clskhamService = lookupService();
        }
        return clskhamService.createClsKham_v2(listCLS, maTiepDon, maPhieuTT);
    }    
}
