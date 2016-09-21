/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmKcbBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmKcbBhytInterface {

public     void create(DtDmKcbBhyt dtDmKcbBhyt);

public     void edit(DtDmKcbBhyt dtDmKcbBhyt);

public     void remove(DtDmKcbBhyt dtDmKcbBhyt);

public     DtDmKcbBhyt find(Object id);

public     List<DtDmKcbBhyt> findAll();

}


