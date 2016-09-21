/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.MienGiam;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface MienGiamInterface {

public     MienGiam create(MienGiam mienGiam);

public     void edit(MienGiam mienGiam);

public     void remove(MienGiam mienGiam);

public     MienGiam find(Object id);

public     List<MienGiam> findAll();

 public List<MienGiam> getBySoVaoVien(String soVaoVien);

}


