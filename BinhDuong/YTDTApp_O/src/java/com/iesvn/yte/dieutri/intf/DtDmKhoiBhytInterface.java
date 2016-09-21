/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmKhoiBhytInterface {

public     void create(DtDmKhoiBhyt dtDmKhoiBhyt);

public     void edit(DtDmKhoiBhyt dtDmKhoiBhyt);

public     void remove(DtDmKhoiBhyt dtDmKhoiBhyt);

public     DtDmKhoiBhyt find(Object id);

public     List<DtDmKhoiBhyt> findAll();

}


