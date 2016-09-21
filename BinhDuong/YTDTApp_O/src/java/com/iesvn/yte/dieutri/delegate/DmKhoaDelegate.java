/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiKhoa;
import com.iesvn.yte.dieutri.intf.DmKhoaInterface;
import com.iesvn.yte.locator.LookupServiceUtils;

import java.util.List;
/**
 *
 * @author ThoVNA
 */
public class DmKhoaDelegate {
    private DmKhoaInterface dmKhoaService;

    public static DmKhoaDelegate getInstance() {
        return new DmKhoaDelegate();
    }
    private DmKhoaInterface lookupService() {
        return (DmKhoaInterface) LookupServiceUtils.lookupService("DmKhoaFacade");
    }

    public void create(DmKhoa dmKhoa) {
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        dmKhoaService.create(dmKhoa);
    }

    public void edit(DmKhoa dmKhoa) {
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        dmKhoaService.edit(dmKhoa);
    }

    public void remove(DmKhoa dmKhoa) {
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        dmKhoaService.remove(dmKhoa);
    }

    public DmKhoa find(Object id) {
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.find(id);
    }

    public List<DmKhoa> findAll() {
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.findAll();
    }

    public Integer findMaSoByMa(String dmkhoaMa) {
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.findMaSoByMa(dmkhoaMa);
    }

    public List<DmLoaiKhoa> findAllLoaiKhoaHavingKhoa() {
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.findAllLoaiKhoaHavingKhoa();
    }

    public List<DmKhoa> getKhoaHavingLoaiKhoaMa(String loaiKhoaMa) {
       if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.getKhoaHavingLoaiKhoaMa(loaiKhoaMa);
    }
    //Tho add
    public List<DmKhoa> getKhoLe() {
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.getKhoLe();
    }

    public List<DmKhoa> getKhoChinh_KhoLe() {
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.getKhoChinh_KhoLe();
    }

    public List<DmKhoa> getKhoaLamSang() {
         if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.getKhoaLamSang();
    }
    //phuc.lc add
    public List<DmKhoa> getKhoaCanLamSang() {
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.getKhoaCanLamSang();
    }

    public List<DmKhoa> getKhoLe_TL_TD(){
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.getKhoLe_TL_TD();
    }

    public List<DmKhoa> getKhoaNT(){
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.getKhoaNT();
    }

    public List<DmKhoa> getKhoaNgT(){
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.getKhoaNgT();
    }

    public Integer findMaSoByMasoThuoc(Integer dmthuocMaso) {
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.findMaSoByMasoThuoc(dmthuocMaso);
    }

    public List<DmKhoa> getKhoaNhom12(){
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.getKhoaNhom12();
    }

    public List<DmKhoa> getKhoaNhom12NOTINKhoDuoc(){
        if (dmKhoaService == null) {
            dmKhoaService = lookupService();
        }
        return dmKhoaService.getKhoaNhom12NOTINKhoDuoc();
    }
}
