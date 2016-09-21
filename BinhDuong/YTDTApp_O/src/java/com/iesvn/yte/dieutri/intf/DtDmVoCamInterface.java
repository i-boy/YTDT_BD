/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmVoCam;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmVoCamInterface {

public     void create(DtDmVoCam dtDmVoCam);

public     void edit(DtDmVoCam dtDmVoCam);

public     void remove(DtDmVoCam dtDmVoCam);

public     DtDmVoCam find(Object id);

public     List<DtDmVoCam> findAll();

}


