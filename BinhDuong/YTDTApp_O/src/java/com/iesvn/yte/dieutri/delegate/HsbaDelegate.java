/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaInterface;

import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaNop;
import com.iesvn.yte.dieutri.entity.ThuocNoiTruXuatVien;
import com.iesvn.yte.dieutri.entity.TmpDataBhyt;
import com.iesvn.yte.entity.DmTang;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaDelegate {

    private HsbaInterface hsbaService;

    public static HsbaDelegate getInstance() {
        return new HsbaDelegate();
    }

    private HsbaInterface lookupService() {
        return (HsbaInterface) LookupServiceUtils.lookupService("HsbaFacade");
    }

    public void create(Hsba hsba) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        hsbaService.create(hsba);
    }

    public void edit(Hsba hsba) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        hsbaService.edit(hsba);
    }

    public void remove(Hsba hsba) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        hsbaService.remove(hsba);
    }

    public Hsba find(Object id) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.find(id);
    }

    public List<Hsba> findAll() {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.findAll();
    }

    public java.util.List<com.iesvn.yte.dieutri.entity.Hsba> findByBenhNhan(java.lang.String maBenhNhan) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.findByBenhNhan(maBenhNhan);
    }

    public com.iesvn.yte.dieutri.entity.Hsba findByBenhNhanLastHsba(java.lang.String maBenhNhan) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.findByBenhNhanLastHsba(maBenhNhan);
    }

    public com.iesvn.yte.dieutri.entity.Hsba findByTiepDonMa(java.lang.String maTiepDon) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.findByTiepDonMa(maTiepDon);
    }

    public java.lang.String capNhatThongTinNhapVien(Hsba hsba, HsbaBhyt hsbaBhyt, BenhNhan benhNhan, DmTang tangChuyenDen) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.capNhatThongTinNhapVien(hsba, hsbaBhyt, benhNhan, tangChuyenDen);
    }

    public String capNhatThongTinHanhChinhBN(Hsba hsba, HsbaBhyt hsbaBhyt, BenhNhan benhNhan) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.capNhatThongTinHanhChinhBN(hsba, hsbaBhyt, benhNhan);
    }

    public String capNhatThongTinHSBA(Hsba hsba, HsbaNop hsbaNop, HsbaChuyenVien hsbaCV) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.capNhatThongTinHSBA(hsba, hsbaNop, hsbaCV);
    }

    public Hsba findByHsbaAndKhoaDangDieuTri(String sovaovien, String makhoa) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.findByHsbaAndKhoaDangDieuTri(sovaovien, makhoa);
    }

    public List<Hsba> findKhoaDangDieuTri(String makhoa) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.findKhoaDangDieuTri(makhoa);
    }

    public List<Hsba> findBySoVaoVienHoTenNgayGioVaoVien(String soVaoVien, String hoTen, Date tuNgay, Date denNgay) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.findBySoVaoVienHoTenNgayGioVaoVien(soVaoVien, hoTen, tuNgay, denNgay);
    }

    public List<TmpDataBhyt> exportDataNoiTru(Date fromDate, Date toDate, boolean xoaDulieuTam, String congSoNgayDieuTri) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.exportDataNoiTru(fromDate, toDate, xoaDulieuTam, congSoNgayDieuTri);
    }

    public List<Hsba> findBySoTheBHYT(String sothe) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.findBySoTheBHYT(sothe);
    }

    public boolean deleteHsba(String hsbaSovaovien) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.deleteHsba(hsbaSovaovien);
    }

    public boolean deleteHsbacmCuoi(String hsbaSovaovien) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.deleteHsbacmCuoi(hsbaSovaovien);
    }

    public boolean deleteHsbarv(String hsbaSovaovien) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.deleteHsbarv(hsbaSovaovien);
    }

    public List<ThuocNoiTruXuatVien> findTntXuatVienBySoBenhAn(String sovaovien) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.findTntXuatVienBySoBenhAn(sovaovien);
    }

    public Hsba findByThongTinBenhNhan(String hoten, Integer gtMaso, String namsinh, String gio, String ngay) {
        if (hsbaService == null) {
            hsbaService = lookupService();
        }
        return hsbaService.findByThongTinBenhNhan(hoten, gtMaso, namsinh, gio, ngay);
    }
    
}
