/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtXuatBh;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface CtXuatBhInterface {

public     void create(CtXuatBh ctXuatBh);

public     void edit(CtXuatBh ctXuatBh);

public     void remove(CtXuatBh ctXuatBh);

public     CtXuatBh find(Object id);

public     List<CtXuatBh> findAll();

    public java.util.List<com.iesvn.yte.dieutri.entity.CtXuatBh> findByPhieuxuatBhMa(java.lang.String phieuxuatBhMa);

}


