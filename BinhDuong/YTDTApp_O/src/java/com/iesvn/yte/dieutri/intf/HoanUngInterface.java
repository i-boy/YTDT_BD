/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HoanUng;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HoanUngInterface {

public     HoanUng create(HoanUng hoanUng);

public     void edit(HoanUng hoanUng);

public     void remove(HoanUng hoanUng);

public     HoanUng find(Object id);

public     List<HoanUng> findAll();
public Double getTongHoanUng(String hsbaSovaovien);

}


