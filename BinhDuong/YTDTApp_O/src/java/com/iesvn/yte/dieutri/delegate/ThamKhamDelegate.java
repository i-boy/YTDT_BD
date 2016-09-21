/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.ThamKhamInterface;

import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.entity.DmKhoa;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class ThamKhamDelegate {

    private ThamKhamInterface thamkhamService;

    public static ThamKhamDelegate getInstance() {
        return new ThamKhamDelegate();
    }

    private ThamKhamInterface lookupService() {
        return (ThamKhamInterface) LookupServiceUtils.lookupService("ThamKhamFacade");
    }

    public void create(ThamKham thamKham) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        thamkhamService.create(thamKham);
    }

    public void edit(ThamKham thamKham) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        thamkhamService.edit(thamKham);
    }

    public void remove(ThamKham thamKham) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        thamkhamService.remove(thamKham);
    }

    public ThamKham find(Object id) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.find(id);
    }

    public List<ThamKham> findAll() {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.findAll();
    }

    public List<ThamKham> findThamKhamByBanKhamMaAndNgay(String banKhamMa, Date ngayThamKham) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.findThamKhamByBanKhamMaAndNgay(banKhamMa, ngayThamKham);
    }

    public ThamKham findByBanKhamVaMaTiepDon(String banKham, String maTiepDon) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.findByBanKhamVaMaTiepDon(banKham, maTiepDon);
    }

    public ThamKham findByMaTiepDon(String maTiepDon) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.findByMaTiepDon(maTiepDon);
    }

    public ThamKham findThamKhamByMaTiepDonFirst(String maTiepDon) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.findThamKhamByMaTiepDonFirst(maTiepDon);
    }

    public ThamKham findByBanKhamWithLoaiKhoaVaMaTiepDon(String dtdmloaikhoaMa, String maTiepDon) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.findByBanKhamWithLoaiKhoaVaMaTiepDon(dtdmloaikhoaMa, maTiepDon);
    }

    public String thamKhamVaXuTri(HsThtoank hsttk, ThamKham thamkham, TiepDon tiepdon, BenhNhan benhnhan, Boolean chuyenVaoNT, String cvntOption) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.thamKhamVaXuTri(hsttk, thamkham, tiepdon, benhnhan, chuyenVaoNT, cvntOption);
    }

    public String vaoNoiTru_ChuyenSoLieu(HsThtoank hsttk, ThamKham thamkham, TiepDon tiepdon, BenhNhan benhnhan, Boolean chuyenVaoNT, String huongXuLy, String cvntOption) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.vaoNoiTru_ChuyenSoLieu(hsttk, thamkham, tiepdon, benhnhan, chuyenVaoNT, huongXuLy, cvntOption);
    }

    public List<ThamKham> findThamKhamByNgay(Date ngayThamKham) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.findThamKhamByNgay(ngayThamKham);
    }

    public String chuyenSoLieuVaoNoiTru(Hsba hsba, HsbaKhoa hsbaKhoa, DmKhoa khoa, String matiepdon) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.chuyenSoLieuVaoNoiTru(hsba, hsbaKhoa, khoa, matiepdon);
    }

    public String getMaTiepDonByGoiBN(String maBanKham) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.getMaTiepDonByGoiBN(maBanKham);

    }

    public String xoaSoThuThuBNKham(String maTiepDon) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.xoaSoThuThuBNKham(maTiepDon);

    }

    public List<ThamKham> findAllByMaTiepDon(String maTiepDon) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.findAllByMaTiepDon(maTiepDon);
    }

    public ThamKham getLastThamKhamWithSoTheBHYTAndBanKham(String tiepdonSothebh, Integer dtdmbankhamMaso) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.getLastThamKhamWithSoTheBHYTAndBanKham(tiepdonSothebh, dtdmbankhamMaso);
    }

    public String vaoNoiTru_Temp(ThamKham thamkham, TiepDon tiepdon, String hsbaType, DmKhoa khoaNoitru) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.vaoNoiTru_Temp(thamkham, tiepdon, hsbaType, khoaNoitru);
    }

    public List<ClsKham> getListClsBanKhamTruoc(String tiepdonMa, Integer thamkhamMa) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.getListClsBanKhamTruoc(tiepdonMa, thamkhamMa);
    }

    public List<ThuocPhongKham> getListThuocBanKhamTruoc(String tiepdonMa, Integer thamkhamMa) {
        if (thamkhamService == null) {
            thamkhamService = lookupService();
        }
        return thamkhamService.getListThuocBanKhamTruoc(tiepdonMa, thamkhamMa);
    }
}
