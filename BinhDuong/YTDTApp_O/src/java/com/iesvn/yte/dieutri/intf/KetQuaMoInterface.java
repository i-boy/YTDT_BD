/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.KetQuaMo;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface KetQuaMoInterface {

public     void create(KetQuaMo ketQuaMo);

public     void edit(KetQuaMo ketQuaMo);

public     void remove(KetQuaMo ketQuaMo);

public     KetQuaMo find(Object id);

public     List<KetQuaMo> findAll();

   
}


