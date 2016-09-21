/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.DtDmClsBangGiaInterface;

import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import com.iesvn.yte.entity.DmKhoa;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class DtDmClsBangGiaDelegate {

    private DtDmClsBangGiaInterface dtdmclsbanggiaService;
    private static DtDmClsBangGiaDelegate objDtDmClsBangGiaDelegate;

    public static DtDmClsBangGiaDelegate getInstance() {
        if (objDtDmClsBangGiaDelegate == null) {
            objDtDmClsBangGiaDelegate = new DtDmClsBangGiaDelegate();
        }
        return objDtDmClsBangGiaDelegate;
    }

    private DtDmClsBangGiaInterface lookupService() {
        return (DtDmClsBangGiaInterface) LookupServiceUtils.lookupService("DtDmClsBangGiaFacade");
    }

    public void create(DtDmClsBangGia dtDmClsBangGia) {
        if (dtdmclsbanggiaService == null) {
            dtdmclsbanggiaService = lookupService();
        }
        dtdmclsbanggiaService.create(dtDmClsBangGia);
    }

    public void edit(DtDmClsBangGia dtDmClsBangGia) {
        if (dtdmclsbanggiaService == null) {
            dtdmclsbanggiaService = lookupService();
        }
        dtdmclsbanggiaService.edit(dtDmClsBangGia);
    }

    public void remove(DtDmClsBangGia dtDmClsBangGia) {
        if (dtdmclsbanggiaService == null) {
            dtdmclsbanggiaService = lookupService();
        }
        dtdmclsbanggiaService.remove(dtDmClsBangGia);
    }

    public DtDmClsBangGia find(Object id) {
        if (dtdmclsbanggiaService == null) {
            dtdmclsbanggiaService = lookupService();
        }
        return dtdmclsbanggiaService.find(id);
    }

    public List<DtDmClsBangGia> findAll() {
        if (dtdmclsbanggiaService == null) {
            dtdmclsbanggiaService = lookupService();
        }
        return dtdmclsbanggiaService.findAll();
    }

    public List<DtDmClsBangGia> getDtDmClsBangGiaByMaSoKhoa(Integer maSoKhoa) {
        if (dtdmclsbanggiaService == null) {
            dtdmclsbanggiaService = lookupService();
        }
        return dtdmclsbanggiaService.getDtDmClsBangGiaByMaSoKhoa(maSoKhoa);
    }
    public List<DtDmClsBangGia> findByAllConditions(String ma, String diengiai, Integer phanloai, boolean searchDaxoa) {
        if (dtdmclsbanggiaService == null) {
            dtdmclsbanggiaService = lookupService();
        }
        return dtdmclsbanggiaService.findByAllConditions(ma, diengiai, phanloai, searchDaxoa);
    }
    public void create(DtDmClsBangGia dtDmClsBangGia, DmKhoa dmkhoa) {
        if (dtdmclsbanggiaService == null) {
            dtdmclsbanggiaService = lookupService();
        }
        dtdmclsbanggiaService.create(dtDmClsBangGia, dmkhoa);
    }
    
    public void edit(DtDmClsBangGia dtDmClsBangGia, DtDmClsKhoa clsKhoa) {
        if (dtdmclsbanggiaService == null) {
            dtdmclsbanggiaService = lookupService();
        }
        dtdmclsbanggiaService.edit(dtDmClsBangGia, clsKhoa);
    }

    public DtDmClsBangGia findByMa(String ma){
        if (dtdmclsbanggiaService == null) {
            dtdmclsbanggiaService = lookupService();
        }
       return  dtdmclsbanggiaService.findByMa(ma);
    }
}


