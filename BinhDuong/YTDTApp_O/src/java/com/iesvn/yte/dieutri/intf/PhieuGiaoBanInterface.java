/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtPhieuGiaoBan;
import com.iesvn.yte.dieutri.entity.PhieuGiaoBan;
import com.iesvn.yte.dieutri.entity.PhieuGiaoBanThanhPhanThamDu;
import java.util.List;

/**
 *
 * @author james
 */
public interface PhieuGiaoBanInterface {

   public void create(PhieuGiaoBan phieuGiaoBan);

   public void edit(PhieuGiaoBan phieuGiaoBan);

   public void remove(PhieuGiaoBan phieuGiaoBan);

   public PhieuGiaoBan find(Object id);

   public List<PhieuGiaoBan> findAll();

   public List<PhieuGiaoBan> findByPhieuGiaoBan(String maPhieu);

   public String capNhatPhieuGiaoBan(PhieuGiaoBan phieuGiaoBan, String maPhieuGiaoBan, List<PhieuGiaoBanThanhPhanThamDu> listTPTD, List<CtPhieuGiaoBan> listCTPGB);

   public void removeAllPhieuGiaoBan(PhieuGiaoBan phieuGiaoBan);
}
