/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.LichMo;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface LichMoInterface {

public     void create(LichMo lichMo);

public     void edit(LichMo lichMo);

public     void remove(LichMo lichMo);

public     LichMo find(Object id);

public     List<LichMo> findAll();

}


