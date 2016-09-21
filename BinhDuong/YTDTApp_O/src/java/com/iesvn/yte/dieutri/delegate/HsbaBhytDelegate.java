/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaBhytInterface;

import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaBhytDelegate {

    private HsbaBhytInterface hsbabhytService;

    public static HsbaBhytDelegate getInstance() {
        return new HsbaBhytDelegate();
    }

    private HsbaBhytInterface lookupService() {
        return (HsbaBhytInterface) LookupServiceUtils.lookupService("HsbaBhytFacade");
    }

    public HsbaBhyt create(HsbaBhyt hsbaBhyt) {
        if (hsbabhytService == null) {
            hsbabhytService = lookupService();
        }
        return hsbabhytService.create(hsbaBhyt);
    }

    public void edit(HsbaBhyt hsbaBhyt) {
        if (hsbabhytService == null) {
            hsbabhytService = lookupService();
        }
        hsbabhytService.edit(hsbaBhyt);
    }

    public void remove(HsbaBhyt hsbaBhyt) {
        if (hsbabhytService == null) {
            hsbabhytService = lookupService();
        }
        hsbabhytService.remove(hsbaBhyt);
    }

    public HsbaBhyt find(Object id) {
        if (hsbabhytService == null) {
            hsbabhytService = lookupService();
        }
        return hsbabhytService.find(id);
    }

    public List<HsbaBhyt> findAll() {
        if (hsbabhytService == null) {
            hsbabhytService = lookupService();
        }
        return hsbabhytService.findAll();
    }

    public HsbaBhyt findBySoVaoVienKhoadangdtLastHsbaBhyt(String soVaoVien, String khoaDangdt) {
        if (hsbabhytService == null) {
            hsbabhytService = lookupService();
        }
        return hsbabhytService.findBySoVaoVienKhoadangdtLastHsbaBhyt(soVaoVien, khoaDangdt);
    }

    public java.util.List<com.iesvn.yte.dieutri.entity.HsbaBhyt> findBySoVaoVien(java.lang.String soVaoVien) {
        if (hsbabhytService == null) {
            hsbabhytService = lookupService();
        }
        return hsbabhytService.findBySoVaoVien(soVaoVien);
    }

    public com.iesvn.yte.dieutri.entity.HsbaBhyt findBySoVaoVienLastHsbaBhyt(java.lang.String soVaoVien) {
        if (hsbabhytService == null) {
            hsbabhytService = lookupService();
        }
        return hsbabhytService.findBySoVaoVienLastHsbaBhyt(soVaoVien);
    }

    public HsbaBhyt findByMaTiepDon(String tiepdonMa) {
        if (hsbabhytService == null) {
            hsbabhytService = lookupService();
        }
        return hsbabhytService.findByMaTiepDon(tiepdonMa);
    }

    public void capnhatGiaClsTheoThoiGianBaoHiem(HsbaBhyt hsbaBhyt) {
        if (hsbabhytService == null) {
            hsbabhytService = lookupService();
        }
        hsbabhytService.capnhatGiaClsTheoThoiGianBaoHiem(hsbaBhyt);
    }
}


