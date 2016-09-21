/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaGiayTomTat;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsbaGiayTomTatInterface {

    public String create(HsbaGiayTomTat hsbaGiayTomTat);

    public void edit(HsbaGiayTomTat hsbaGiayTomTat);

    public void remove(HsbaGiayTomTat hsbaGiayTomTat);

    public HsbaGiayTomTat find(Object id);

    public List<HsbaGiayTomTat> findAll();
    
    public HsbaGiayTomTat findByMa(String maGiay);
    
    public HsbaGiayTomTat findBySovaovien(String sovaovien) ;
}


