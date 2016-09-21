/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.TmpBaocaoThekho;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DieuTriUtilInterface;

import com.iesvn.yte.entity.VaiTro;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
/**
 *
 * @author LENOVO 3000 Y410
 */
public class DieuTriUtilDelegate {

    private DieuTriUtilInterface dieutriutilService;

    public static DieuTriUtilDelegate getInstance() {
        return new DieuTriUtilDelegate();
    }

    private DieuTriUtilInterface lookupService() {
        return (DieuTriUtilInterface) LookupServiceUtils.lookupService("DieuTriUtilFacade");
    }

    public java.util.List<java.lang.Object> findByNgayGioCN(java.lang.Double ngaycn, java.lang.String clsName, java.lang.String fieldNgayGioCNName) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findByNgayGioCN(ngaycn, clsName, fieldNgayGioCNName);
    }

    public Object findByMaWithFormatMaPhieu(String ma, String clsName, String fieldMaName) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findByMaWithFormatMaPhieu(ma, clsName, fieldMaName);
    }

    public Object findByMaWithFormatMa(String ma, String clsName, String fieldMaName) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findByMaWithFormatMa(ma, clsName, fieldMaName);
    }

    public java.lang.Object findByMa(java.lang.String ma, java.lang.String clsName, java.lang.String fieldNgayGioCNName) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findByMa(ma, clsName, fieldNgayGioCNName);
    }

    public Object findByMaSo(Integer maso, String clsName, String fieldMaSoName) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findByMaSo(maso, clsName, fieldMaSoName);
    }

    public javax.persistence.EntityManager getEm() {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.getEm();
    }

    public void setEm(javax.persistence.EntityManager em) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        dieutriutilService.setEm(em);
    }

    public List<VaiTro> getVaiTroByNguoiDungTenDangNhap(String tenDangNhap) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.getVaiTroByNguoiDungTenDangNhap(tenDangNhap);
    }

    public Integer getSoKhamBenh(String maBanKham) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.getSoKhamBenh(maBanKham);
    }

    public String getProperty(String myKey) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.getProperty(myKey);
    }

    public String formatMa(String ma) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.formatMa(ma);
    }

    public String formatMaPhieu(String ma) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.formatMaPhieu(ma);
    }

    public List findByAllConditions(String clsName, String fieldMa, String fieldTen, String fieldDT, String ma, String ten, boolean dt) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findByAllConditions(clsName, fieldMa, fieldTen, fieldDT, ma, ten, dt);
    }

    public List findByAllConditionsSortAsc(String clsName, String fieldMa, String fieldTen, String fieldDT, String ma, String ten, boolean dt) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findByAllConditionsSortAsc(clsName, fieldMa, fieldTen, fieldDT, ma, ten, dt);
    }

    public List findByAllConditions(String clsName, String fieldMa, String fieldTen, String ma, String ten) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findByAllConditions(clsName, fieldMa, fieldTen, ma, ten);
    }

    public List findMaLike(String clsName, String fieldMa, String fieldDT, String ma, boolean dt) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findMaLike(clsName, fieldMa, fieldDT, ma, dt);
    }
    
    public List findTenLike(String clsName, String fieldTen, String ten){
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findTenLike(clsName, fieldTen, ten);
    }

    public List findAll(String clsName, String fieldDT, boolean dt) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findAll(clsName, fieldDT, dt);
    }

    public List findAll(String clsName) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findAll(clsName);
    }

    public List findAllChacon(String clsNameCon, String fieldMasoCon, Integer masocon) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findAllChacon(clsNameCon, fieldMasoCon, masocon);
    }

    public void create(Object ob) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        dieutriutilService.create(ob);
    }

    public void edit(Object ob) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        dieutriutilService.edit(ob);
    }

    public void remove(Object ob) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        dieutriutilService.remove(ob);
    }

    public double getGiaCuoi(String maThuoc) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.getGiaCuoi(maThuoc);
    }

    public double getTonKhoByMaThuocAndMaKhoa(String maThuoc, String maKhoa) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.getTonKhoByMaThuocAndMaKhoa(maThuoc, maKhoa);
    }
    
    public List findByAllConditionsSortAsc(String clsName, String fieldMa, String fieldTen,String fieldDT, String fieldPhanloai, String ma,String ten, Integer phanloai) {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findByAllConditionsSortAsc(clsName, fieldMa, fieldTen, fieldDT, fieldPhanloai, ma, ten, phanloai);
    }
    public List findKhoaNhapNuoc() {
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findKhoaNhapNuoc();
    }

    public HashMap<String,DmDoiTuong> findByDoiTuongThuPhi(){
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.findByDoiTuongThuPhi();
    }

    public boolean exportDataForTheKho(Integer khoMaso, Date tuNgay, Date denNgay, String maLienket, Double tonDau, Double dongia, String loaiIn){
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.exportDataForTheKho(khoMaso, tuNgay, denNgay, maLienket, tonDau, dongia, loaiIn);
    }

    public List<DmPhanLoaiThuoc> getListDmPhanLoaiThuoc(Integer dmLoaiThuocMaso, boolean notIn){
        if (dieutriutilService == null) {
            dieutriutilService = lookupService();
        }
        return dieutriutilService.getListDmPhanLoaiThuoc(dmLoaiThuocMaso, notIn);
    }
}