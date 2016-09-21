/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.DtDmNhanvienKhoa;
import com.iesvn.yte.dieutri.intf.DtDmNhanVienKhoaInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author THACH NGUYEN
 */
public class DtDmNhanVienKhoaDelegate {
 private DtDmNhanVienKhoaInterface dtdmnhanvienkhoaService;

    public static DtDmNhanVienKhoaDelegate getInstance() {
        return new DtDmNhanVienKhoaDelegate();
    }

    private DtDmNhanVienKhoaInterface lookupService() {
        return (DtDmNhanVienKhoaInterface) LookupServiceUtils.lookupService("DtDmNhanvienKhoaFacade");
    }

    public void create(DtDmNhanvienKhoa dtDmNhanVien) {
        if (dtdmnhanvienkhoaService == null) {
            dtdmnhanvienkhoaService = lookupService();
        }
        dtdmnhanvienkhoaService.create(dtDmNhanVien);
    }

    public void edit(DtDmNhanvienKhoa dtDmNhanVien) {
        if (dtdmnhanvienkhoaService == null) {
            dtdmnhanvienkhoaService = lookupService();
        }
        dtdmnhanvienkhoaService.edit(dtDmNhanVien);
    }

    public void remove(DtDmNhanvienKhoa dtDmNhanVien) {
        if (dtdmnhanvienkhoaService == null) {
            dtdmnhanvienkhoaService = lookupService();
        }
        dtdmnhanvienkhoaService.remove(dtDmNhanVien);
    }

    public DtDmNhanvienKhoa find(Object id) {
        if (dtdmnhanvienkhoaService == null) {
            dtdmnhanvienkhoaService = lookupService();
        }
        return dtdmnhanvienkhoaService.find(id);
    }

    public List<DtDmNhanvienKhoa> findAll() {
        if (dtdmnhanvienkhoaService == null) {
            dtdmnhanvienkhoaService = lookupService();
        }
        return dtdmnhanvienkhoaService.findAll();
    }

    public DtDmNhanvienKhoa findByMaNguoiDung(DtDmNhanVien tenNd) {
        if (dtdmnhanvienkhoaService == null) {
            dtdmnhanvienkhoaService = lookupService();
        }
        return dtdmnhanvienkhoaService.findByMaNguoiDung(tenNd);
    }


}
