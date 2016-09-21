/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.intf.NguoiDungInterface;
import com.iesvn.yte.entity.NguoiDung;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author ThanhDo
 */
public class NguoiDungDelegate {

    private NguoiDungInterface nguoiDungService;

    public static NguoiDungDelegate getInstance() {
        return new NguoiDungDelegate();
    }

    private NguoiDungInterface lookupService() {
        return (NguoiDungInterface) LookupServiceUtils.lookupService("NguoiDungFacade");
    }

    public void create(NguoiDung nguoiDung) {
        if (nguoiDungService == null) {
            nguoiDungService = lookupService();
        }
        nguoiDungService.create(nguoiDung);
    }

    public void edit(NguoiDung nguoiDung) {
        if (nguoiDungService == null) {
            nguoiDungService = lookupService();
        }
        nguoiDungService.edit(nguoiDung);

    }

    public void remove(NguoiDung nguoiDung) {
        if (nguoiDungService == null) {
            nguoiDungService = lookupService();
        }
        nguoiDungService.remove(nguoiDung);
    }

    public NguoiDung find(Object id) {
        if (nguoiDungService == null) {
            nguoiDungService = lookupService();
        }
        return nguoiDungService.find(id);
    }

    public NguoiDung findByMa(String maND) {
        if (nguoiDungService == null) {
            nguoiDungService = lookupService();
        }
        return nguoiDungService.findByMa(maND);
    }

    public List<NguoiDung> findAll() {
        if (nguoiDungService == null) {
            nguoiDungService = lookupService();
        }
        return nguoiDungService.findAll();
    }
    
    public Boolean createNguoiDung(NguoiDung nd, DtDmNhanVien nv) {
        if (nguoiDungService == null) {
            nguoiDungService = lookupService();
        }
        return nguoiDungService.createNguoiDung(nd, nv);
    }
}
