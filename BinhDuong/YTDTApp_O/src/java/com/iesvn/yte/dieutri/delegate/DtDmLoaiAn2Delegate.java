/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmLoaiAn2;
import com.iesvn.yte.dieutri.intf.DtDmLoaiAn2Interface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmLoaiAn2Delegate {

    private DtDmLoaiAn2Interface dtdmloaian2Service;

    public static DtDmLoaiAn2Delegate getInstance() {
        return new DtDmLoaiAn2Delegate();
    }

    private DtDmLoaiAn2Interface lookupService() {
        return (DtDmLoaiAn2Interface) LookupServiceUtils.lookupService("DtDmLoaiAn2Facade");
    }

    public void create(DtDmLoaiAn2 obj) {
        if (dtdmloaian2Service == null) {
            dtdmloaian2Service = lookupService();
        }
        dtdmloaian2Service.create(obj);
    }

    public void edit(DtDmLoaiAn2 obj) {
        if (dtdmloaian2Service == null) {
            dtdmloaian2Service = lookupService();
        }
        dtdmloaian2Service.edit(obj);
    }

    public void remove(DtDmLoaiAn2 obj) {
        if (dtdmloaian2Service == null) {
            dtdmloaian2Service = lookupService();
        }
        dtdmloaian2Service.remove(obj);
    }

    public DtDmLoaiAn2 find(Object id) {
        if (dtdmloaian2Service == null) {
            dtdmloaian2Service = lookupService();
        }
        return dtdmloaian2Service.find(id);
    }

    public List<DtDmLoaiAn2> findAll() {
        if (dtdmloaian2Service == null) {
            dtdmloaian2Service = lookupService();
        }
        return dtdmloaian2Service.findAll();
    }

    public List<DtDmLoaiAn2> findByLoai(int loaian) {
        if (dtdmloaian2Service == null) {
            dtdmloaian2Service = lookupService();
        }
        return dtdmloaian2Service.findByLoai(loaian);
    }
   
}
