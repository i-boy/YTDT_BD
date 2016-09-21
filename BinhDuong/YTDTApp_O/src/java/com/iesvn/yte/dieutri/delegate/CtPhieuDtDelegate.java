/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.CtPhieuDtInterface;

import com.iesvn.yte.dieutri.entity.CtPhieuDt;
import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import java.util.List;
import java.util.HashMap;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class CtPhieuDtDelegate {

    public final static int LANH_THUOC_KHOA_PHONG = 0;
    public final static int LANH_THUOC_TU_TRUC = 1;
    public final static int TRA_THUOC_KHOA_PHONG = 2;
    public final static int TRA_THUOC_TU_TRUC = 3;

    private CtPhieuDtInterface ctphieudtService;

    public static CtPhieuDtDelegate getInstance() {
        return new CtPhieuDtDelegate();
    }

    private CtPhieuDtInterface lookupService() {
        return (CtPhieuDtInterface) LookupServiceUtils.lookupService("CtPhieuDtFacade");
    }

    public void create(CtPhieuDt ctPhieuDt) {
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        ctphieudtService.create(ctPhieuDt);
    }

    public void edit(CtPhieuDt ctPhieuDt) {
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        ctphieudtService.edit(ctPhieuDt);
    }

    public void remove(CtPhieuDt ctPhieuDt) {
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        ctphieudtService.remove(ctPhieuDt);
    }

    public CtPhieuDt find(Object id) {
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.find(id);
    }

    public List<CtPhieuDt> findAll() {
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.findAll();
    }

    public java.util.List<com.iesvn.yte.dieutri.entity.CtPhieuDt> findByPhieuDuTruMa(java.lang.String phieudtMa) {
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.findByPhieuDuTruMa(phieudtMa);
    }

    public PhieuDuTru findByPhieuDuTruPhanBiet(String maPhieu, Integer phanBiet) {
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.findByPhieuDuTruPhanBiet(maPhieu, phanBiet);
    }

    public PhieuDuTru findByPhieuDuTruPhanBietKho(String maPhieu, Integer phanBiet, Integer khoNhanMaso){
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.findByPhieuDuTruPhanBietKho(maPhieu, phanBiet, khoNhanMaso);
    }
    public PhieuDuTru findByPhieuDuTruKhoXuatPhanBiet(String maPhieu, Integer phanBiet, Integer khoXuatMa){
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.findByPhieuDuTruKhoXuatPhanBiet(maPhieu, phanBiet, khoXuatMa);
    }

    public java.lang.String capNhatPhieuDuTru(java.util.List<com.iesvn.yte.dieutri.entity.CtPhieuDt> listCTPhieuDuTru, List<ThuocNoiTru> listTNT, com.iesvn.yte.dieutri.entity.PhieuDuTru phieuDuTru, java.lang.String phieudtMa, String priority) {
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.capNhatPhieuDuTru(listCTPhieuDuTru, listTNT, phieuDuTru, phieudtMa, priority);
    }

    public String huyPhieuDuTru(String maPhieuDuTru) {
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.huyPhieuDuTru(maPhieuDuTru);

    }

    public String huyPhieuDuTru_New(String maPhieuDuTru){
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.huyPhieuDuTru_New(maPhieuDuTru);
    }

    public String huyPhieuDuTruTra(String maPhieuDuTru) {
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.huyPhieuDuTruTra(maPhieuDuTru);

    }

    public String huyPhieuDuTruTra_New(String maPhieuDuTru){
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.huyPhieuDuTruTra_New(maPhieuDuTru);
    }

    public String capNhatPhieuDuTruTra(List<CtPhieuDt> listCTPhieuDuTru, List<ThuocNoiTru> listTNT, PhieuDuTru phieuDuTru, String phieudtMa) {

        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.capNhatPhieuDuTruTra(listCTPhieuDuTru, listTNT, phieuDuTru, phieudtMa);
    }

    public String updatePhieuDuTru(PhieuDuTru objPhieuDuTru, List<CtPhieuDt> listCtPhieuDt, List<CtPhieuDt> listCtPhieuDtDel) {
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.updatePhieuDuTru(objPhieuDuTru, listCtPhieuDt, listCtPhieuDtDel);
    }

    public String updatePhieuDuTruLinhNGT(PhieuDuTru objPhieuDuTru, List<CtPhieuDt> listCtPhieuDt, List<CtPhieuDt> listCtPhieuDtDel, List<Integer> listThuocPhongKhamMa, HashMap<Integer,List> ctdt_matpk, String priority){
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.updatePhieuDuTruLinhNGT(objPhieuDuTru, listCtPhieuDt, listCtPhieuDtDel, listThuocPhongKhamMa, ctdt_matpk, priority);
    }

    public String updatePhieuDuTruLinhNT(PhieuDuTru objPhieuDuTru, List<CtPhieuDt> listCtPhieuDt, List<CtPhieuDt> listCtPhieuDtDel, List<Integer> listThuocPhongKhamMa, HashMap<Integer,List> ctdt_matpk, String priority){
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.updatePhieuDuTruLinhNT(objPhieuDuTru, listCtPhieuDt, listCtPhieuDtDel, listThuocPhongKhamMa, ctdt_matpk, priority);
    }

    public boolean daXuatThuocTheoPhieuDT_New(String maPhieuDuTru){
        if (ctphieudtService == null) {
            ctphieudtService = lookupService();
        }
        return ctphieudtService.daXuatThuocTheoPhieuDT_New(maPhieuDuTru);
    }
}
