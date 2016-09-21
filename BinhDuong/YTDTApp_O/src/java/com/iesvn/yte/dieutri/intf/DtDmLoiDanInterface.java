/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmLoiDan;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmLoiDanInterface {

   public void create(DtDmLoiDan dtDmLoiDan);

   public  void edit(DtDmLoiDan dtDmLoiDan);

   public  void remove(DtDmLoiDan dtDmLoiDan);

   public  DtDmLoiDan find(Object id);

   public  List<DtDmLoiDan> findAll();
}
