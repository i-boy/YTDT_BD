/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmKhoInterface {

public     void create(DtDmKho dtDmKho);

public     void edit(DtDmKho dtDmKho);

public     void remove(DtDmKho dtDmKho);

public     DtDmKho find(Object id);

public     List<DtDmKho> findAll();

}


