/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaToDieuTri;
import java.util.List;

/**
 *
 * @author thanh
 */
public interface HsbaToDieuTriInterface {
  void create(HsbaToDieuTri hsbaToDieuTri);

    void edit(HsbaToDieuTri hsbaToDieuTri);

    void remove(HsbaToDieuTri hsbaToDieuTri);

    HsbaToDieuTri find(Object id);

    List<HsbaToDieuTri> findAll();
    public String createToDieuTri(List<HsbaToDieuTri> listToDieuTri, String soVaoVien, String khoaMa);
     public List<HsbaToDieuTri> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) ;
      public List<HsbaToDieuTri> findBySoVaoVien(String soVaoVien) ;
}
