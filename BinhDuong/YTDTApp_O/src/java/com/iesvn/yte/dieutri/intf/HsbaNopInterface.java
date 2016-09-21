/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaNop;
import java.util.Date;
import java.util.List;


/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsbaNopInterface {

public     void create(HsbaNop hsbaNop);

public     void edit(HsbaNop hsbaNop);

public     void remove(HsbaNop hsbaNop);

public     HsbaNop find(Object id);

public     List<HsbaNop> findAll();

public com.iesvn.yte.dieutri.entity.HsbaNop findBySoVaoVien(java.lang.String soVaoVien);

public HsbaNop findBySoLuuTru(String soLuuTru);

public int soBAtrongngay(Date ngay);
}


