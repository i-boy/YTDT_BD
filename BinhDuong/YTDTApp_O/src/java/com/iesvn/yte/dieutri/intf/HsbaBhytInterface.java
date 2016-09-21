/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsbaBhytInterface {

public     HsbaBhyt create(HsbaBhyt hsbaBhyt);

public     void edit(HsbaBhyt hsbaBhyt);

public     void remove(HsbaBhyt hsbaBhyt);

public     HsbaBhyt find(Object id);

public     List<HsbaBhyt> findAll();
/**
 * 
 * @param soVaoVien so benh an
 * @param khoaDangdt khoa dang dieu tri
 * @return HsbaBhyt moi nhat
 */
public HsbaBhyt findBySoVaoVienKhoadangdtLastHsbaBhyt(String soVaoVien, String khoaDangdt);

    public java.util.List<com.iesvn.yte.dieutri.entity.HsbaBhyt> findBySoVaoVien(java.lang.String soVaoVien);

    public com.iesvn.yte.dieutri.entity.HsbaBhyt findBySoVaoVienLastHsbaBhyt(java.lang.String soVaoVien);
public HsbaBhyt findByMaTiepDon(String tiepdonMa);
public void capnhatGiaClsTheoThoiGianBaoHiem(HsbaBhyt hsbaBhyt) ;
}


