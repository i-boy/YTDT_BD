/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaSan;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsbaSanInterface {

public     HsbaSan create(HsbaSan hsbaSan);

public     void edit(HsbaSan hsbaSan);

public     void remove(HsbaSan hsbaSan);

public     HsbaSan find(Object id);

public     List<HsbaSan> findAll();

}


