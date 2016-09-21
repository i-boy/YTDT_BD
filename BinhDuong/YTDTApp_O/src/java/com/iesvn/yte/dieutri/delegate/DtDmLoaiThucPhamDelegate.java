/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmLoaiThucPham;
import com.iesvn.yte.dieutri.intf.DtDmLoaiThucPhamInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmLoaiThucPhamDelegate {
    private DtDmLoaiThucPhamInterface dtdmloaithucphamService;

    public static DtDmLoaiThucPhamDelegate getInstance() {
        return new DtDmLoaiThucPhamDelegate();
    }

    private DtDmLoaiThucPhamInterface lookupService() {
        return (DtDmLoaiThucPhamInterface) LookupServiceUtils.lookupService("DtDmLoaiThucPhamFacade");
    }
    
    public void create(DtDmLoaiThucPham obj) {
        if (dtdmloaithucphamService == null) {
            dtdmloaithucphamService = lookupService();
        }
        dtdmloaithucphamService.create(obj);
    }
    
    public void edit(DtDmLoaiThucPham obj) {
        if (dtdmloaithucphamService == null) {
            dtdmloaithucphamService = lookupService();
        }
        dtdmloaithucphamService.edit(obj);
    }
    
    public void remove(DtDmLoaiThucPham obj) {
        if (dtdmloaithucphamService == null) {
            dtdmloaithucphamService = lookupService();
        }
        dtdmloaithucphamService.remove(obj);
    }
    
    public DtDmLoaiThucPham find(Object id) {
        if (dtdmloaithucphamService == null) {
            dtdmloaithucphamService = lookupService();
        }
        return dtdmloaithucphamService.find(id);
    }
    
    public List<DtDmLoaiThucPham> findAll() {
        if (dtdmloaithucphamService == null) {
            dtdmloaithucphamService = lookupService();
        }
        return dtdmloaithucphamService.findAll();
    }
}
