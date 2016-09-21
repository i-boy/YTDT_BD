/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtTraNhaCungCap;
import com.iesvn.yte.dieutri.entity.PhieuTraNhaCungCap;
import com.iesvn.yte.dieutri.entity.TonKho;
import java.util.List;

/**
 *
 * @author thanh
 */
public interface PhieuTraNhaCungCapInterface {

    public void create(PhieuTraNhaCungCap phieuTraNhaCungCap);

    public void edit(PhieuTraNhaCungCap phieuTraNhaCungCap);

    public void remove(PhieuTraNhaCungCap phieuTraNhaCungCap);

    public PhieuTraNhaCungCap find(Object id);

    public PhieuTraNhaCungCap findPhieuTraNhaCungCapByMa(String obj);

    public List<PhieuTraNhaCungCap> findAll();

    public String createPhieuTraNhaCungCap(PhieuTraNhaCungCap pxk, List<CtTraNhaCungCap> listCtTraNhaCungCap, List<TonKho> listTkXuat);

    public String updatePhieuTraNhaCungCap(PhieuTraNhaCungCap objPhieuTraNCC, List<CtTraNhaCungCap> listCtTraNCC, List<TonKho> listTkXuat);
}
