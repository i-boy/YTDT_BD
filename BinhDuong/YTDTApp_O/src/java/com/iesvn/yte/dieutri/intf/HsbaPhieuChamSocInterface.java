/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaPhieuChamSoc;
import java.util.List;

/**
 *
 * @author thanh
 */
public interface HsbaPhieuChamSocInterface {
 void create(HsbaPhieuChamSoc hsbaPhieuChamSoc);

    void edit(HsbaPhieuChamSoc hsbaPhieuChamSoc);

    void remove(HsbaPhieuChamSoc hsbaPhieuChamSoc);

    HsbaPhieuChamSoc find(Object id);

    List<HsbaPhieuChamSoc> findAll();

    public String createPhieuChamSoc(List<HsbaPhieuChamSoc> listToDieuTri, String soVaoVien, String khoaMa);
     public List<HsbaPhieuChamSoc> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) ;
      public List<HsbaPhieuChamSoc> findBySoVaoVien(String soVaoVien) ;
}
