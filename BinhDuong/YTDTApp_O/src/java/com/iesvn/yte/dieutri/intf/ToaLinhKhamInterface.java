/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtToaLinhk;
import com.iesvn.yte.dieutri.entity.ToaLinhKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface ToaLinhKhamInterface {

public     void create(ToaLinhKham toaLinhKham);

public     void edit(ToaLinhKham toaLinhKham);

public     void remove(ToaLinhKham toaLinhKham);

public     ToaLinhKham find(Object id);

public     List<ToaLinhKham> findAll();

 public List<CtToaLinhk> findByMaTiepDonAndMaBanKham(String maTiepDon, String maBanKham) ;
  public boolean createToaLinhKham(List<ToaLinhKham> listTpk);

}


