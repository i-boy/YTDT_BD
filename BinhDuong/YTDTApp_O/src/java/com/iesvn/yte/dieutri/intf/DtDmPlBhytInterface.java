/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmPlBhytInterface {

public     void create(DtDmPlBhyt dtDmPlBhyt);

public     void edit(DtDmPlBhyt dtDmPlBhyt);

public     void remove(DtDmPlBhyt dtDmPlBhyt);

public     DtDmPlBhyt find(Object id);

public     List<DtDmPlBhyt> findAll();

}


