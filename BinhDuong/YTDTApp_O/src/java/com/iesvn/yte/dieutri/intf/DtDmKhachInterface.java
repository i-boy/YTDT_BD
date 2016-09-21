/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmKhach;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmKhachInterface {

public     void create(DtDmKhach dtDmKhach);

public     void edit(DtDmKhach dtDmKhach);

public     void remove(DtDmKhach dtDmKhach);

public     DtDmKhach find(Object id);

public     List<DtDmKhach> findAll();

}


