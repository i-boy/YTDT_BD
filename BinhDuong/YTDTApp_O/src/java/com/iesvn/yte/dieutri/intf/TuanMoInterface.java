/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.TuanMo;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface TuanMoInterface {

public     void create(TuanMo tuanMo);

public     void edit(TuanMo tuanMo);

public     void remove(TuanMo tuanMo);

public     TuanMo find(Object id);

public     List<TuanMo> findAll();

}


