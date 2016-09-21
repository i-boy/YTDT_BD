/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmClsKhoaInterface;

import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import com.iesvn.yte.dieutri.intf.DtDmClsKhoaInterface;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmClsKhoaDelegate {

    private DtDmClsKhoaInterface DtDmClsKhoaService;

    public static DtDmClsKhoaDelegate getInstance() {
        return new DtDmClsKhoaDelegate();
    }

    private DtDmClsKhoaInterface lookupService() {
        return (DtDmClsKhoaInterface) LookupServiceUtils.lookupService("DtDmClsKhoaFacade");
    }

    public void create(DtDmClsKhoa dtDmLyDoCv) {
        if (DtDmClsKhoaService == null) {
            DtDmClsKhoaService = lookupService();
        }
        DtDmClsKhoaService.create(dtDmLyDoCv);
    }

    public void edit(DtDmClsKhoa dtDmLyDoCv) {
        if (DtDmClsKhoaService == null) {
            DtDmClsKhoaService = lookupService();
        }
        DtDmClsKhoaService.edit(dtDmLyDoCv);
    }

    public void remove(DtDmClsKhoa dtDmLyDoCv) {
        if (DtDmClsKhoaService == null) {
            DtDmClsKhoaService = lookupService();
        }
        DtDmClsKhoaService.remove(dtDmLyDoCv);
    }

    public DtDmClsKhoa find(Object id) {
        if (DtDmClsKhoaService == null) {
            DtDmClsKhoaService = lookupService();
        }
        return DtDmClsKhoaService.find(id);
    }

    public List<DtDmClsKhoa> findAll() {
        if (DtDmClsKhoaService == null) {
            DtDmClsKhoaService = lookupService();
        }
        return DtDmClsKhoaService.findAll();
    }

    public List<DtDmClsKhoa> findByMaSoKhoa(Integer maSoKhoa) {
        if (DtDmClsKhoaService == null) {
            DtDmClsKhoaService = lookupService();
        }
        return DtDmClsKhoaService.findByMaSoKhoa(maSoKhoa);
    }
        
    public DtDmClsKhoa findByMaClsBangGia(Integer maSoClsBg) {
        if (DtDmClsKhoaService == null) {
            DtDmClsKhoaService = lookupService();
        }
        return DtDmClsKhoaService.findByMaClsBangGia(maSoClsBg);
    }
    
    public List<DtDmClsKhoa> findByMaSoCLS(Integer masoCLS){
         if (DtDmClsKhoaService == null) {
            DtDmClsKhoaService = lookupService();
        }
        return DtDmClsKhoaService.findByMaSoCLS(masoCLS);
    }
}


