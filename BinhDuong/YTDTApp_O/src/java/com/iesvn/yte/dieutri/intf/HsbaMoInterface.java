/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaMo;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsbaMoInterface {

public     void create(HsbaMo hsbaMo);

public     void edit(HsbaMo hsbaMo);

public     void remove(HsbaMo hsbaMo);

public     HsbaMo find(Object id);

public     List<HsbaMo> findAll();

    public java.util.List<com.iesvn.yte.dieutri.entity.HsbaMo> findBySoSoVaoVienVaKhoaMa(java.lang.String soVaoVien, java.lang.String khoaMa);

    public java.lang.String capNhatKetQuaMo(java.util.List<com.iesvn.yte.dieutri.entity.HsbaMo> lstHsbaMo, java.lang.String soVaoVien, java.lang.String khoaMa);
    
    public HsbaMo findByHsbaMa(String hsbaMa) ;

}


