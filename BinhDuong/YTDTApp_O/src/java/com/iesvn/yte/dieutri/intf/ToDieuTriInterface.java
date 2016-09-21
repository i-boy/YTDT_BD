/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.ToDieuTri;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface ToDieuTriInterface {

public     void create(ToDieuTri todieutri);

public     void edit(ToDieuTri todieutri);

public     void remove(ToDieuTri todieutri);

public     ToDieuTri find(Object id);

public     List<ToDieuTri> findAll();

// public List<ToDieuTri> findByHsbaCM(Integer hsbacmmaso) ;

  //public String capNhatToDieuTri(List<ToDieuTri> listTDT, Integer hsbacmMaso) ;
  
  public String capNhatToDieuTri(List<ToDieuTri> listTDT, String hsbaMaso) ;
  
  public List<ToDieuTri> findByHsba(String hsbaMaso) ;
  
  public ToDieuTri createToDieuTri(ToDieuTri tdt) ;
}


