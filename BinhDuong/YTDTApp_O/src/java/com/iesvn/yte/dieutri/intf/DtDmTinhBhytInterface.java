/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmTinhBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmTinhBhytInterface {

public     void create(DtDmTinhBhyt dtDmTinhBhyt);

public     void edit(DtDmTinhBhyt dtDmTinhBhyt);

public     void remove(DtDmTinhBhyt dtDmTinhBhyt);

public     DtDmTinhBhyt find(Object id);

public     List<DtDmTinhBhyt> findAll();

}


