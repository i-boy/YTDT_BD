/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtNhapKho;
import com.iesvn.yte.dieutri.entity.PhieuNhapKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface PhieuNhapKhoInterface {

public     void create(PhieuNhapKho phieuNhapKho);

public     void edit(PhieuNhapKho phieuNhapKho);

public     void remove(PhieuNhapKho phieuNhapKho);

public     PhieuNhapKho find(Object id);

public     List<PhieuNhapKho> findAll();
public List<PhieuNhapKho> find(String pnkMa, Date ngayNhap, Integer loaiPhieuMa, String soChungTu, String soHD);

    public com.iesvn.yte.dieutri.entity.PhieuNhapKho findByPhieunhapkhoMa(java.lang.String id);

    public java.lang.String getMaPhieu();

    public String createPhieuNhap(PhieuNhapKho pn, List<CtNhapKho> listCNK, List<TonKho> listTk);

    public String createPhieuNhap(PhieuNhapKho pn, List<CtNhapKho> listCNK, Integer khoaMaso);

     public boolean checkExisted(String soHoaDon, Date ngayHoaDon);

}


