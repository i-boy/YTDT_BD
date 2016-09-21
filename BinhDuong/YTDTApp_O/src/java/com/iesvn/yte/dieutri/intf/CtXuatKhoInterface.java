/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtXuatKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface CtXuatKhoInterface {

public     void create(CtXuatKho ctXuatKho);

public     void edit(CtXuatKho ctXuatKho);

public     void remove(CtXuatKho ctXuatKho);

public     CtXuatKho find(Object id);

public     List<CtXuatKho> findAll();

    public java.util.List<com.iesvn.yte.dieutri.entity.CtXuatKho> findByphieuxuatkhoMa(java.lang.String phieuxuatMa);

}


