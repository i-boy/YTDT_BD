/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;


import com.iesvn.yte.dieutri.entity.GiayNghiViecHuongBhxh;
import com.iesvn.yte.dieutri.entity.GiayNghiViecHuongBhxh;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface GiayNghiViecHuongBhxhInterface {

   void create(GiayNghiViecHuongBhxh giayNghiViecHuongBhxh);

    void edit(GiayNghiViecHuongBhxh giayNghiViecHuongBhxh);

    void remove(GiayNghiViecHuongBhxh giayNghiViecHuongBhxh);

    GiayNghiViecHuongBhxh find(Object id);

    List<GiayNghiViecHuongBhxh> findAll();
    
    public GiayNghiViecHuongBhxh findByThamKhamMa(Integer thamkhamma) ;
}


