/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaGiayRaVien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsbaGiayRaVienInterface {

public     void create(HsbaGiayRaVien hsbaGiayRaVien);

public     void edit(HsbaGiayRaVien hsbaGiayRaVien);

public     void remove(HsbaGiayRaVien hsbaGiayRaVien);

public     HsbaGiayRaVien find(Object id);

public     List<HsbaGiayRaVien> findAll();
public String insert(HsbaGiayRaVien obj);
public String update(HsbaGiayRaVien obj);
public HsbaGiayRaVien findByHsbagrvMa(String ma);
public HsbaGiayRaVien findBySoVaoVien(String soVaoVien) ;

}


