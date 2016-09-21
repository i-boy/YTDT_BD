/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.TamUng;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface TamUngInterface {

public     TamUng create(TamUng tamUng);

public     void edit(TamUng tamUng);

public     void remove(TamUng tamUng);

public     TamUng find(Object id);

 public TamUng findByMaHsba(String maHsba);

public     List<TamUng> findAll();

 public Double getTongTamUng(String hsbaSovaovien);
 
 public int countSolanTamUngByHsba(String hsbaSovaovien) ;

}


