/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.CtXuatBhXuatVienInterface;

import com.iesvn.yte.dieutri.entity.CtXuatBhXuatVien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class CtXuatBhXuatVienDelegate {
    private CtXuatBhXuatVienInterface ctxuatbhService;
    public static CtXuatBhXuatVienDelegate getInstance() {
        return new CtXuatBhXuatVienDelegate();
    }
    private CtXuatBhXuatVienInterface lookupService() {
        return (CtXuatBhXuatVienInterface)LookupServiceUtils.lookupService("CtXuatBhXuatVienFacade");
    }

    public void create(CtXuatBhXuatVien ctXuatBh) {
        if(ctxuatbhService == null) ctxuatbhService = lookupService();
        ctxuatbhService.create(ctXuatBh);
    }

    public void edit(CtXuatBhXuatVien ctXuatBh) {
        if(ctxuatbhService == null) ctxuatbhService = lookupService();
        ctxuatbhService.edit(ctXuatBh);
    }

    public void remove(CtXuatBhXuatVien ctXuatBh) {
        if(ctxuatbhService == null) ctxuatbhService = lookupService();
        ctxuatbhService.remove(ctXuatBh);
    }

    public CtXuatBhXuatVien find(Object id) {
        if(ctxuatbhService == null) ctxuatbhService = lookupService();
        return ctxuatbhService.find(id);
    }

    public List<CtXuatBhXuatVien> findAll() {
        if(ctxuatbhService == null) ctxuatbhService = lookupService();
        return ctxuatbhService.findAll();
    }

    public java.util.List<CtXuatBhXuatVien> findByPhieuxuatBhXuatVienMa(String phieuxuatBhMa) {
        if(ctxuatbhService == null) ctxuatbhService = lookupService();
        return ctxuatbhService.findByPhieuxuatBhXuatVienMa(phieuxuatBhMa);
    }
}


