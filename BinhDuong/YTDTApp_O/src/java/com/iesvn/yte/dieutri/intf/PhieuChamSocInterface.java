/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.PhieuChamSoc;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface PhieuChamSocInterface {

public     void create(PhieuChamSoc phieuChamSoc);

public     void edit(PhieuChamSoc phieuChamSoc);

public     void remove(PhieuChamSoc phieuChamSoc);

public     PhieuChamSoc find(Object id);

public     List<PhieuChamSoc> findAll();

 //public List<PhieuChamSoc> findByHsbaCM(Integer hsbacmmaso) ;

  public String capNhatPhieuChamSoc(List<PhieuChamSoc> listPCS, Integer hsbacmMaso) ;
  
  public List<PhieuChamSoc> findByHsba(String hsbaMaso) ;
  
  public PhieuChamSoc createPhieuChamSoc(PhieuChamSoc pcs) ;
}


