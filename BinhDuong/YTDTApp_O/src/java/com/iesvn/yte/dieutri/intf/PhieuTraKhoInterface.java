/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtTraKho;
import com.iesvn.yte.dieutri.entity.TonKho;


import com.iesvn.yte.dieutri.entity.PhieuTraKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface PhieuTraKhoInterface {

    public void create(PhieuTraKho phieuTraKho);

    public void edit(PhieuTraKho phieuTraKho);

    public void remove(PhieuTraKho phieuTraKho);

    public PhieuTraKho find(Object id);

    public List<PhieuTraKho> findAll();

    public PhieuTraKho findByPhieutrakhoMa(String maPhieu);

    public PhieuTraKho findByPhieutrakhoByKhoNhan(String maPhieu, Integer khoaNhanMaso);

    public String createPhieuTra(PhieuTraKho ptk, List<CtTraKho> listCtTraKho, List<TonKho> listTkNhan, List<TonKho> listTkTra);

    public String TraPhieuDuTru(List<CtTraKho> listCTX, PhieuTraKho ptk) throws Exception;

    public boolean daTraPhieuDuTru(String phieuDt);

    public String updatePhieuTraKho(PhieuTraKho objPhieuTraKho, List<CtTraKho> listCtTraKho);

    public PhieuTraKho findPhieuTraKhoByKhoaTra(String maPhieu, Integer maKhoa);

    public String thucHienTraKho(PhieuTraKho objPhieuTraKho, List<CtTraKho> listCtTraKho, List<TonKho> listTkNhan, List<TonKho> listTkTra);
    
   public String updatePhieuDTTuTrucTraKho(List<CtTraKho> listCTX, PhieuTraKho ptk, String priority) throws Exception ;

   public PhieuTraKho findByPhieuDuTruMa(String phieudttraMa, Integer dmKhoNhanMaso);
}


